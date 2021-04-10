package com.techelevator.controller;

import com.techelevator.dao.SizeDAO;

import com.techelevator.model.Size;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class SizeController {
    private final SizeDAO sizeDAO;

    public SizeController(SizeDAO sizeDAO) {
        this.sizeDAO = sizeDAO;
    }

    @RequestMapping(path = "/sizes", method = RequestMethod.GET)
    public List <Size> getAllSizesFromDb() {
        return sizeDAO.getAllSizes ();
    }


    @RequestMapping(path = "/sizes/available", method = RequestMethod.GET)
    public List<Size> getAvailableSizesFromDb() {
        return sizeDAO.getAvailableSizes ();
    }

}
