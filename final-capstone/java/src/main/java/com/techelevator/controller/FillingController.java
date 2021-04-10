package com.techelevator.controller;

import com.techelevator.dao.FillingDAO;
import com.techelevator.model.Filling;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}