package com.techelevator.controller;

import com.techelevator.dao.FillingDAO;
import com.techelevator.model.Filling;
import org.springframework.http.HttpStatus;
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
    public List <Filling> getAllFillingsFromDb() {
        return fillingDAO.getAllFillings ();
    }

    //get available fillings
    @RequestMapping(path = "/fillings/available", method = RequestMethod.GET)
    public List <Filling> getAvailableFillingsFromDb() {
        return fillingDAO.getAvailableFillings ();
    }

    @RequestMapping(path = "/fillings/{id}", method = RequestMethod.PUT)
    public Filling update(@PathVariable int id, @RequestBody Filling filling) {
        fillingDAO.updateFilling (filling, id);
        return filling;
    }

    @RequestMapping(path = "/fillings", method = RequestMethod.POST)
    public int createNewFilling(@RequestBody Filling newFilling) {
        return fillingDAO.createFilling (newFilling);
    }

    @RequestMapping(path = "/fillings/flip/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Boolean fillingFlipStatus(@PathVariable int id) {
        return fillingDAO.flipAvailability (id);
    }

}