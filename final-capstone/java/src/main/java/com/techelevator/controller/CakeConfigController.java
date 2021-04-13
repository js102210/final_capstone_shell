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


    /**
     * Gets the list of *all* cake configs, whether or not available. Employee only.
     * @return - returns the list of ALL cake configs regardless of availability.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/configs", method = RequestMethod.GET)
    public List <CakeConfig> getAllConfigsFromDb() {
        return cakeConfigDAO.getAllConfigs ();
    }

    /**
     * Gets the list of available cake configs. Used for customer portion of website.
     * @return returns a list of available cake configs.
     */
    @RequestMapping(path = "/configs/available", method = RequestMethod.GET)
    public List <CakeConfig> getAvailableConfigsFromDb() {
        return cakeConfigDAO.getAvailableConfigs ();
    }



    /**
     * Creates a new cake config. Employee only.
     * @param cakeConfig - the Cake Config object passed in from front end.
     * @return - returns the id of the new cake config.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/configs", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Integer add(@RequestBody CakeConfig cakeConfig) {
        return cakeConfigDAO.addCakeConfig (cakeConfig);
    }


    /**
     * flips a cake config's availability. Employee only. Please note that this is not strictly RESTful design,
     * but it takes far fewer system resources.
     * @param id - the id of the cake config to be flipped
     * @return - returns the new flipped boolean value of isAvailable.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/configs/flip/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Boolean configFlipStatus(@PathVariable int id) {
        return cakeConfigDAO.flipConfigStatus (id);
    }


}

