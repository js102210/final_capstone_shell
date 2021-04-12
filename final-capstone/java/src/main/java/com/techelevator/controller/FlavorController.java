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

    //need auth
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/flavors", method = RequestMethod.GET)
    public List <Flavor> getAllFlavorsFromDb() {
        return flavorDAO.getAllFlavors ();
    }

    @RequestMapping(path = "/flavors/available", method = RequestMethod.GET)
    public List <Flavor> getAvailableFlavorsFromDb() {
        return flavorDAO.getAvailableFlavors ();
    }

<<<<<<< HEAD
    @PreAuthorize ("hasRole('employee')")
=======
    //need auth
    @PreAuthorize("isAuthenticated()")
>>>>>>> 07ba7f07768140b39a5acc50df6d8a085c23fe60
    @RequestMapping(path = "/flavors/{flavor_id}", method = RequestMethod.PUT)
    public void update(@PathVariable int flavor_id, @RequestBody Flavor flavor) {
        flavorDAO.update (flavor);
    }

<<<<<<< HEAD
    @PreAuthorize ("hasRole('employee')")
=======
    //need auth
    @PreAuthorize("isAuthenticated()")
>>>>>>> 07ba7f07768140b39a5acc50df6d8a085c23fe60
    @RequestMapping(path = "/flavors", method = RequestMethod.POST)
    public int createNewFlavor(@RequestBody Flavor newFlavor) {
        return flavorDAO.createFlavor (newFlavor);
    }

<<<<<<< HEAD
    @PreAuthorize("hasRole('employee')")
=======
    //need auth
    @PreAuthorize("isAuthenticated()")
>>>>>>> 07ba7f07768140b39a5acc50df6d8a085c23fe60
    @RequestMapping(path = "flavors/flip/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Boolean flavorFlipStatus(@PathVariable int id) {
        return flavorDAO.flipAvailability (id);
    }

}
