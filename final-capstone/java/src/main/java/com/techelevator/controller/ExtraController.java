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

    @RequestMapping(path = "/extras", method = RequestMethod.GET)
    public List <Extra> getAllExtras() {
        return extraDAO.getAllExtras ();
    }

    @RequestMapping(path = "/extras/available", method = RequestMethod.GET)
    public List <Extra> getAvailableExtras() {
        return extraDAO.getAvailableExtras ();
    }

    @PreAuthorize("hasRole('employee')")
    @RequestMapping(path = "extras/{id}", method = RequestMethod.PUT)
    public Extra updateExtra(@PathVariable int id, @RequestBody Extra extra) {
        extraDAO.updateExtra (extra, id);
        return extra;
    }

    @PreAuthorize ("hasRole('employee')")
    @RequestMapping(path = "/extras", method = RequestMethod.POST)
    public int createNewExtra(Extra newExtra) {
        return extraDAO.createExtra (newExtra);
    }

    @PreAuthorize ("hasRole('employee')")
    @RequestMapping(path = "/extras/flip/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Boolean extraFlipStatus(@PathVariable int id) {
        return extraDAO.flipAvailability (id);
    }
}
