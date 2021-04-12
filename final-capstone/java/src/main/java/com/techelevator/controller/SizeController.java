package com.techelevator.controller;

import com.techelevator.dao.SizeDAO;

import com.techelevator.model.Size;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class SizeController {
    private final SizeDAO sizeDAO;

    public SizeController(SizeDAO sizeDAO) {
        this.sizeDAO = sizeDAO;
    }

    //need auth
    @RequestMapping(path = "/sizes", method = RequestMethod.GET)
    public List <Size> getAllSizesFromDb() {
        return sizeDAO.getAllSizes ();
    }


    @RequestMapping(path = "/sizes/available", method = RequestMethod.GET)
    public List <Size> getAvailableSizesFromDb() {
        return sizeDAO.getAvailableSizes ();
    }

<<<<<<< HEAD
    @PreAuthorize ("hasRole('employee')")
=======
    //need auth
>>>>>>> 07ba7f07768140b39a5acc50df6d8a085c23fe60
    @RequestMapping(path = "/sizes", method = RequestMethod.POST)
    public int createNewSize(@RequestBody Size newSize) {
        return sizeDAO.createSize (newSize);
    }

<<<<<<< HEAD
    @PreAuthorize("hasRole('employee')")
=======
    //need auth
>>>>>>> 07ba7f07768140b39a5acc50df6d8a085c23fe60
    @RequestMapping(path = "/sizes/flip/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Boolean styleFlipStatus(@PathVariable int id) {
        return sizeDAO.flipAvailability (id);
    }
}
