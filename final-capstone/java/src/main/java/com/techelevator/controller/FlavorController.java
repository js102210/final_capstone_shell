package com.techelevator.controller;

import com.techelevator.dao.FlavorDAO;
import com.techelevator.model.Flavor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class FlavorController {
    private final FlavorDAO flavorDAO;
    public FlavorController(FlavorDAO flavorDAO){
        this.flavorDAO = flavorDAO;
    }

    @RequestMapping(path = "/flavors", method = RequestMethod.GET)
    public List<Flavor> getAllFlavorsFromDb(){
        return flavorDAO.getAllFlavors();
    }

    @RequestMapping(path = "/flavors/available", method = RequestMethod.GET)
    public List<Flavor> getAvailableFlavorsFromDb() {
        return flavorDAO.getAvailableFlavors();
    }


}
