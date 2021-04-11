package com.techelevator.controller;

import com.techelevator.dao.FlavorDAO;

import com.techelevator.model.Flavor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @RequestMapping(path = "/flavors/{id}", method = RequestMethod.PUT)
    public Flavor update (@PathVariable int id, @RequestBody Flavor flavor) {
        flavorDAO.updateFlavor(flavor, id);
        return flavor;
    }

}
