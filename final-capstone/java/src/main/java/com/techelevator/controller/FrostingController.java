package com.techelevator.controller;

import com.techelevator.dao.FrostingDAO;

import com.techelevator.model.Frosting;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
public class FrostingController {
    private final FrostingDAO frostingDAO;

    public FrostingController(FrostingDAO frostingDAO) {
        this.frostingDAO = frostingDAO;
    }

    /**
     * returns a list of all frostings. Employee only.
     * @return - returns a list of all frosting JSONs.
     */
    @PreAuthorize ("isAuthenticated()")
    @RequestMapping(path = "/frostings", method = RequestMethod.GET)
    public List <Frosting> getAllFrostingsFromDb() {
        return frostingDAO.getAllFrostings ();
    }

    /**
     * returns a list of all available frostings. For the customer side of the site.
     * @return - returns a list of available frosting JSONS.
     */
    @RequestMapping(path = "/frostings/available", method = RequestMethod.GET)
    public List <Frosting> getAvailableFrostingsFromDb() {
        return frostingDAO.getAvailableFrostings ();
    }


    /**
     * updates a given Frosting. Employee only.
     * @param id - the id of the frosting to update
     * @param frosting - the frostings JSON with the new information, passed from the front end.
     * @return - returns the new frosting info.
     */
    @PreAuthorize ("isAuthenticated()")
    @RequestMapping(path = "/frostings/{id}", method = RequestMethod.PUT)
    public Frosting update(@PathVariable int id,
                           @RequestBody Frosting frosting) {
        frostingDAO.updateFrosting (frosting, id);
        return frosting;
    }

    /**
     * creates a new frosting. Employee only.
     * @param newFrosting - a frosting JSON with the info for the new frosting.
     * @return - returns the id of the new frosting.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/frostings", method = RequestMethod.POST)
    public int createNewFrosting(@RequestBody Frosting newFrosting) {
        return frostingDAO.createFrosting (newFrosting);
    }

    /**
     * flips the availability of a frosting. Employee only. If the frosting flips to being unavailable,
     * flips any associated available cake configs to unavailable. No effect on configs if a frosting flips to
     * available. Not strictly RESTful design, but we made the design call to do it this way for simplicity
     * and consuming less server resources.
     * @param id - id of the frosting to flip the availability of.
     * @return - returns the new isAvailable of the frosting.
     */
    @PreAuthorize ("isAuthenticated()")
    @RequestMapping(path = "frostings/flip/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Boolean frostingFlipStatus(@PathVariable int id) {
        return frostingDAO.flipAvailability (id);
    }


}
