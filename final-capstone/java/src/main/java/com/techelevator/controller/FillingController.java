package com.techelevator.controller;

import com.techelevator.dao.FillingDAO;
import com.techelevator.model.Filling;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
public class FillingController {
    private final FillingDAO fillingDAO;

    public FillingController(FillingDAO fillingDAO) {
        this.fillingDAO = fillingDAO;
    }

    //get all fillings
    @RequestMapping(path = "/fillings", method = RequestMethod.GET)
    public List<Filling> getAllFillingsFromDb(){
        return fillingDAO.getAllFillings();
    }

    //get available fillings
    @RequestMapping(path = "/fillings/available", method = RequestMethod.GET)
    public List<Filling> getAvailableFillingsFromDb(){
        return fillingDAO.getAvailableFillings();
    }

    @RequestMapping(path = "/fillings/{filling_id}", method = RequestMethod.PUT)
    public void update (@PathVariable int fillingID, String fillingName, boolean isAvailable, BigDecimal priceMod,
                        @RequestBody Filling filling) {
        fillingDAO.update(filling);
    }
}