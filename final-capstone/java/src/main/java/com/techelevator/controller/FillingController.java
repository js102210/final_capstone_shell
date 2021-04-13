package com.techelevator.controller;

import com.techelevator.dao.FillingDAO;
import com.techelevator.model.Filling;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * gets all fillings. Employee only.
     * @return - returns a list of all fillings regardless of availability status.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/fillings", method = RequestMethod.GET)
    public List <Filling> getAllFillingsFromDb() {
        return fillingDAO.getAllFillings ();
    }

    /**
     * gets a list of available fillings. Used on customer side of site.
     * @return - returns a list of available fillings.
     */
    @RequestMapping(path = "/fillings/available", method = RequestMethod.GET)
    public List <Filling> getAvailableFillingsFromDb() {
        return fillingDAO.getAvailableFillings ();
    }


    /**
     * updates a filling's information. Employee only.
     * @param id - the id of the filling to update.
     * @param filling - the filling JSON returned from the front end.
     * @return - returns the updated Filling's info.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/fillings/{id}", method = RequestMethod.PUT)
    public Filling update(@PathVariable int id, @RequestBody Filling filling) {
        fillingDAO.updateFilling (filling, id);
        return filling;
    }


    /**
     * Creates a new filling. Employee only.
     * @param newFilling - the new filling JSON returned from the front end.
     * @return - returns the new Filling's id.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/fillings", method = RequestMethod.POST)
    public int createNewFilling(@RequestBody Filling newFilling) {
        return fillingDAO.createFilling (newFilling);
    }


    /**
     * Flips the availability of the indicated filling. Employee only. If the filling flips to unavailable, will also
     * make any configs that use the filling flip to unavailable. Nothing happens with associated configs
     * if the filling flips to available. Not strictly RESTful design, but we made this design decision to
     * conserve system resources.
     * @param id - the id of the filling to flip.
     * @return - returns the new availability status of the filling from its is_available field on DB.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/fillings/flip/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Boolean fillingFlipStatus(@PathVariable int id) {
        return fillingDAO.flipAvailability (id);
    }

}