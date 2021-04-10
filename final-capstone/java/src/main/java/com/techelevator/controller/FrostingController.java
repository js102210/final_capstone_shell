package com.techelevator.controller;

import com.techelevator.dao.FrostingDAO;
import com.techelevator.model.Frosting;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class FrostingController {
    private final FrostingDAO frostingDAO;
    public FrostingController(FrostingDAO frostingDAO){
        this.frostingDAO = frostingDAO;
    }

    //get all Frostings
    @RequestMapping(path = "/frostings", method = RequestMethod.GET)
    public List<Frosting> getAllFrostingsFromDb(){
        return frostingDAO.getAllFrostings();
    }

    //get available Frostings
    @RequestMapping(path = "/frostings/available", method = RequestMethod.GET)
    public List<Frosting> getAvailableFrostingsFromDb(){
        return frostingDAO.getAvailableFrostings();
    }
}
