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

    /**
     * returns all sizes from the database. Employee only.
     * @return - gives a list of all Size JSONs regardless of availability.
     */
    @PreAuthorize ("isAuthenticated()")
    @RequestMapping(path = "/sizes", method = RequestMethod.GET)
    public List <Size> getAllSizesFromDb() {
        return sizeDAO.getAllSizes ();
    }

    /**
     * returns a list of available sizes from the database. For customer side of app.
     * @return - gives a list of available size JSONs
     */
    @RequestMapping(path = "/sizes/available", method = RequestMethod.GET)
    public List <Size> getAvailableSizesFromDb() {
        return sizeDAO.getAvailableSizes ();
    }

    /**
     * Creates a new Size. Employee only.
     * @param newSize - the new Size JSON passed from the front end.
     * @return - returns the int of the new Size's size_id.
     */
    @PreAuthorize ("isAuthenticated()")
    @RequestMapping(path = "/sizes", method = RequestMethod.POST)
    public int createNewSize(@RequestBody Size newSize) {
        return sizeDAO.createSize (newSize);
    }

    /**
     * flips the availability of the given Size. Does not affect config availability, since all
     * configs are size-agnostic. This is not RESTful design, but we are implementing it this way
     * as a conscious decision for simplicity and using less system resources.
     * @param id - the id of the size to flip availability
     * @return - returns the new isAvailable value for the Size.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/sizes/flip/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Boolean styleFlipStatus(@PathVariable int id) {
        return sizeDAO.flipAvailability (id);
    }
}
