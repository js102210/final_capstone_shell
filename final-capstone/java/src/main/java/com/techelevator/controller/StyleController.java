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

    /**
     * Returns a list of all styles. Employee only.
     * @return - returns a list of Style JSONs regardless of availability.
     */
    @PreAuthorize ("isAuthenticated()")
    @RequestMapping(path = "/styles", method = RequestMethod.GET)
    public List <Style> getAllStylesFromDb() {
        return styleDAO.getAllStyles ();
    }

    /**
     * Returns a list of available styles. For the customer side of the app.
     * @return - returns a list of available Style JSONs.
     */
    @RequestMapping(path = "/styles/available", method = RequestMethod.GET)
    public List <Style> getAvailableStylesFromDb() {
        return styleDAO.getAvailableStyles ();
    }

    /**
     * Creates a new Style from a Style JSON passed from the front end. Employee only.
     * @param newStyle - the JSON for the new style.
     * @return - returns the style_id for the new Style.
     */
    @PreAuthorize ("isAuthenticated()")
    @RequestMapping(path = "/styles", method = RequestMethod.POST)
    public int createNewStyle(@RequestBody  Style newStyle) {
        return styleDAO.createStyle (newStyle);
    }

    /**
     * flips the availability of a given Style. Does not affect cake configs because
     * configs are style agnostic. Not strictly RESTful but this is a conscious decision for ease of maintenance
     * and lessening demand on system resources.
     * @param id - the id of the Style to flip.
     * @return - returns the new isAvailable status of the style.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/styles/flip/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Boolean styleFlipStatus(@PathVariable int id) {
        return styleDAO.flipAvailability (id);
    }
}
