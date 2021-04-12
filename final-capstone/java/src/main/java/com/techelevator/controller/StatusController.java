package com.techelevator.controller;

import com.techelevator.dao.StatusDAO;

import com.techelevator.model.Status;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class StatusController {

    private final StatusDAO statusDAO;
    public StatusController(StatusDAO statusDAO){
        this.statusDAO = statusDAO;
    }

    /**
     * brings back a list of status JSONs. Employee only.
     * @return - returns the list of status JSONs.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/statuses", method = RequestMethod.GET)
    public List<Status> getStatuses(){
        return statusDAO.getStatuses();
    }
}
