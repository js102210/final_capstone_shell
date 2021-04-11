package com.techelevator.controller;

import com.techelevator.dao.ExtraDAO;
import com.techelevator.model.Extra;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class ExtraController {
    private final ExtraDAO extraDAO;
    public ExtraController(ExtraDAO extraDAO){
        this.extraDAO = extraDAO;
    }

    @RequestMapping(path = "/extras", method = RequestMethod.GET)
    List<Extra> getAllExtras(){
        return extraDAO.getAllExtras();
    }

    @RequestMapping(path = "/extras/available", method = RequestMethod.GET)
    List<Extra> getAvailableExtras(){
        return extraDAO.getAvailableExtras();
    }

}
