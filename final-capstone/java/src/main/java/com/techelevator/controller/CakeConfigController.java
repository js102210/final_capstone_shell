package com.techelevator.controller;

import com.techelevator.dao.CakeConfigDAO;
import com.techelevator.model.CakeConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CakeConfigController {

    private CakeConfigDAO cakeConfigDAO;
    public CakeConfigController(CakeConfigDAO cakeConfigDAO){
        this.cakeConfigDAO = cakeConfigDAO;
    }

    //get all configs
    @RequestMapping(path = "/configs", method = RequestMethod.GET)
    public List<CakeConfig> getAllConfigsFromDb(){
        return cakeConfigDAO.getAllConfigs();
    }

}
