package com.techelevator.controller;

import com.techelevator.dao.CakeConfigDAO;
import com.techelevator.model.CakeConfig;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CakeConfigController {

    private final CakeConfigDAO cakeConfigDAO;

    public CakeConfigController(CakeConfigDAO cakeConfigDAO) {
        this.cakeConfigDAO = cakeConfigDAO;
    }

    //get all configs - this needs to require authorization. employee only.
    @PreAuthorize ("hasRole('employee')")
    @RequestMapping(path = "/configs", method = RequestMethod.GET)
    public List <CakeConfig> getAllConfigsFromDb() {
        return cakeConfigDAO.getAllConfigs ();
    }

    //gets *available* configs for customers
    @RequestMapping(path = "/configs/available", method = RequestMethod.GET)
    public List <CakeConfig> getAvailableConfigsFromDb() {
        return cakeConfigDAO.getAvailableConfigs ();
    }

    //this also requires authorization. employee only.
    @PreAuthorize("hasRole('employee')")
    @RequestMapping(path = "/configs", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Integer add(@RequestBody CakeConfig cakeConfig) {
        return cakeConfigDAO.addCakeConfig (cakeConfig);
    }

    @PreAuthorize ("hasRole('employee')")
    @RequestMapping(path = "/configs/flip/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Boolean configFlipStatus(@PathVariable int id) {
        return cakeConfigDAO.flipConfigStatus (id);
    }


}

