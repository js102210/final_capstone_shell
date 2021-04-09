package com.techelevator.controller;

import com.techelevator.dao.CakeConfigDAO;
import com.techelevator.model.CakeConfig;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CakeConfigController {

    private CakeConfigDAO cakeConfigDAO;

    public CakeConfigController(CakeConfigDAO cakeConfigDAO) {
        this.cakeConfigDAO = cakeConfigDAO;
    }

    //get all configs
    @RequestMapping(path = "/configs", method = RequestMethod.GET)
    public List <CakeConfig> getAllConfigsFromDb() {
        return cakeConfigDAO.getAllConfigs ();
    }


// post CakeConfigs
//    @PostMapping("/cakeConfigs")
//    @ResponseStatus(value = HttpStatus.CREATED)
//    public void add(@RequestBody CakeConfig cakeConfig) {
//        cakeConfigDAO.addCakeConfig(cakeConfig);
//    }


    // delete CakeConfigs
//    @DeleteMapping("/cakeConfigs/{id}")
//    public void delete(@PathVariable int id){
//        cakeConfigDAO.deleteCakeConfig (id);
//    }


    //put
//    @PutMapping("/cakeConfigs/{id}")
//    public void update(@PathVariable int id, @RequestBody CakeConfig cakeConfig) {
//        cakeConfigDAO.updateCakeConfig (cakeConfig);
//    }
}

