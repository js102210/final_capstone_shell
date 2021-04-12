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

    //get all Frostings - need auth
    @PreAuthorize ("isAuthenticated()")
    @RequestMapping(path = "/frostings", method = RequestMethod.GET)
    public List <Frosting> getAllFrostingsFromDb() {
        return frostingDAO.getAllFrostings ();
    }

    //get available Frostings
    @RequestMapping(path = "/frostings/available", method = RequestMethod.GET)
    public List <Frosting> getAvailableFrostingsFromDb() {
        return frostingDAO.getAvailableFrostings ();
    }


    @PreAuthorize ("isAuthenticated()")
    @RequestMapping(path = "/frostings/{id}", method = RequestMethod.PUT)
    public Frosting update(@PathVariable int id,
                           @RequestBody Frosting frosting) {
        frostingDAO.updateFrosting (frosting, id);
        return frosting;
    }


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/frostings", method = RequestMethod.POST)
    public int createNewFrosting(@RequestBody Frosting newFrosting) {
        return frostingDAO.createFrosting (newFrosting);
    }


    @PreAuthorize ("isAuthenticated()")
    @RequestMapping(path = "frostings/flip/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Boolean frostingFlipStatus(@PathVariable int id) {
        return frostingDAO.flipAvailability (id);
    }


}
