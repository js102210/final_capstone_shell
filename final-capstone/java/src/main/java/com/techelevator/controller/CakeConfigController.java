package com.techelevator.controller;

import com.techelevator.dao.CakeConfigDAO;
import com.techelevator.model.CakeConfig;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CakeConfigController {

    private final CakeConfigDAO cakeConfigDAO;

    public CakeConfigController(CakeConfigDAO cakeConfigDAO) {
        this.cakeConfigDAO = cakeConfigDAO;
    }

    //get all configs
    @RequestMapping(path = "/configs", method = RequestMethod.GET)
    public List <CakeConfig> getAllConfigsFromDb() {
        return cakeConfigDAO.getAllConfigs ();
    }


   @RequestMapping(path = "/configs", method = RequestMethod.POST)
   @ResponseStatus(value = HttpStatus.CREATED)
   public Integer add(@RequestBody CakeConfig cakeConfig) {
   return  cakeConfigDAO.addCakeConfig(cakeConfig);
    }


}

