package com.techelevator.controller;

import com.techelevator.dao.StyleDAO;


import com.techelevator.model.Style;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class StyleController {
    private final StyleDAO styleDAO;

    public StyleController(StyleDAO styleDAO) {
        this.styleDAO = styleDAO;
    }

    //need auth
    @RequestMapping(path = "/styles", method = RequestMethod.GET)
    public List <Style> getAllStylesFromDb() {
        return styleDAO.getAllStyles ();
    }

    @RequestMapping(path = "/styles/available", method = RequestMethod.GET)
    public List <Style> getAvailableStylesFromDb() {
        return styleDAO.getAvailableStyles ();
    }


    @PreAuthorize ("hasRole('employee')")

    //need auth

    @RequestMapping(path = "/styles", method = RequestMethod.POST)
    public int createNewStyle(@RequestBody  Style newStyle) {
        return styleDAO.createStyle (newStyle);
    }


    @PreAuthorize("hasRole('employee')")

    //need auth

    @RequestMapping(path = "/styles/flip/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Boolean styleFlipStatus(@PathVariable int id) {
        return styleDAO.flipAvailability (id);
    }
}
