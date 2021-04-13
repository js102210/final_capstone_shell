package com.techelevator.controller;

import com.techelevator.dao.FlavorDAO;

import com.techelevator.model.Flavor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
public class FlavorController {
    private final FlavorDAO flavorDAO;
    public FlavorController(FlavorDAO flavorDAO) {
        this.flavorDAO = flavorDAO;
    }

    /**
     * Returns all flavors regardless of availability. Employee only.
     * @return - returns list of all flavors as flavor JSONs.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/flavors", method = RequestMethod.GET)
    public List <Flavor> getAllFlavorsFromDb() {
        return flavorDAO.getAllFlavors ();
    }

    /**
     * Returns all available flavors for customer side of site.
     * @return - returns list of all available flavors as flavor JSONs.
     */
    @RequestMapping(path = "/flavors/available", method = RequestMethod.GET)
    public List <Flavor> getAvailableFlavorsFromDb() {
        return flavorDAO.getAvailableFlavors ();
    }


    /**
     * Updates an existing flavor with new information from the complete flavor JSON passed from front end.
     * employee only.
     * @param flavor_id - the id of the flavor to update.
     * @param flavor - the JSON for the flavor to update with all of the new information.
     * @return - returns updated flavor.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/flavors/{flavor_id}", method = RequestMethod.PUT)
    public Flavor update(@PathVariable int flavor_id, @RequestBody Flavor flavor) {
        return flavorDAO.updateFlavor(flavor, flavor_id);
    }


    /**
     * Create a new flavor from the flavor JSON passed from front end. Employee only.
     * @param newFlavor - the flavor JSON for the new flavor.
     * @return - returns the new flavor's id.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/flavors", method = RequestMethod.POST)
    public int createNewFlavor(@RequestBody Flavor newFlavor) {
        return flavorDAO.createFlavor (newFlavor);
    }


    /**
     * flips the availability status for a flavor. Employee only. Will also flip associated configs to unavailable if the new
     * availability is false. Does not automatically flip associated configs to available if the flavor is now avail.
     * This is not strictly RESTful design but we made the decision to do this for simplicity and using much less
     * system resources.
     * @param id - id of the flavor to be flipped.
     * @return - returns the new availability status for the flavor.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "flavors/flip/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Boolean flavorFlipStatus(@PathVariable int id) {
        return flavorDAO.flipAvailability (id);
    }

}
