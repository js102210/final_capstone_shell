package com.techelevator.controller;

import com.techelevator.dao.ExtraDAO;
import com.techelevator.model.Extra;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ExtraController {
    private final ExtraDAO extraDAO;

    public ExtraController(ExtraDAO extraDAO) {
        this.extraDAO = extraDAO;
    }

    /**
     * Gets list of all extras regardless of availability. Employee only.
     * @return - returns the list of all extras.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/extras", method = RequestMethod.GET)
    public List <Extra> getAllExtras() {
        return extraDAO.getAllExtras ();
    }

    /**
     * Gets list of available extras. Used for customer part of site.
     * @return - returns the list of available extras.
     */
    @RequestMapping(path = "/extras/available", method = RequestMethod.GET)
    public List <Extra> getAvailableExtras() {
        return extraDAO.getAvailableExtras ();
    }


    /**
     * updates an existing Extra. employee only.
     * @param id - id of the extra to be updated.
     * @param extra - the JSON of the Extra's new information.
     * @return - returns the Extra
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "extras/{id}", method = RequestMethod.PUT)
    public Extra updateExtra(@PathVariable int id, @RequestBody Extra extra) {
        extraDAO.updateExtra (extra, id);
        return extra;
    }


    /**
     * Creates a new extra. Employee only.
     * @param newExtra - the JSON for the new Extra passed from front end.
     * @return - returns the id of the newly minted Extra.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/extras", method = RequestMethod.POST)
    public int createNewExtra(@RequestBody Extra newExtra) {
        return extraDAO.createExtra (newExtra);
    }


    /**
     * Flips the availability boolean for the indicated extra. Employee only. Please note
     * that this isn't strictly RESTful design, but it uses much less system resources and is far easier to
     * troubleshoot.
     * @param id - the id of the extra to flip status.
     * @return - returns the new boolean for is_available for the extra.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/extras/flip/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Boolean extraFlipStatus(@PathVariable int id) {
        return extraDAO.flipAvailability (id);
    }
}
