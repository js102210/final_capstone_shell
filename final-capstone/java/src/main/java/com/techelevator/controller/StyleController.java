package com.techelevator.controller;


import com.techelevator.dao.StyleDAO;


import com.techelevator.model.Style;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class StyleController {
    private final StyleDAO styleDAO;
    public StyleController(StyleDAO styleDAO) {
        this.styleDAO = styleDAO;
    }

    @RequestMapping(path = "/styles", method = RequestMethod.GET)
    public List <Style> getAllStylesFromDb() {
        return styleDAO.getAllStyles ();
    }

    @RequestMapping(path = "/styles/available", method = RequestMethod.GET)
    public List<Style> getAvailableStylesFromDb() {
        return styleDAO.getAvailableStyles ();
    }
}
