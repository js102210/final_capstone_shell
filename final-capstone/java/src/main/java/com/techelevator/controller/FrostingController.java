package com.techelevator.controller;

import com.techelevator.dao.FrostingDAO;

import com.techelevator.model.Frosting;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @RequestMapping(path = "/frostings/{frosting_id}", method = RequestMethod.PUT)
    public void update (@PathVariable int frostingID, String frostingName, boolean isAvailable, BigDecimal priceMod,
                        @RequestBody Frosting frosting) {
        frostingDAO.update(frosting);
    }

}
