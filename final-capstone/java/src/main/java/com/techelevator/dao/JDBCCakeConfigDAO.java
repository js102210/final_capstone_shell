package com.techelevator.dao;

import com.techelevator.model.CakeConfig;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCCakeConfigDAO implements CakeConfigDAO{
private final JdbcTemplate jdbcTemplate;
public JDBCCakeConfigDAO(JdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
}

@Override
 public List<CakeConfig> getAllConfigs() {
        String sqlForAllConfigs =
                "SELECT *  FROM cake_config;";
        List<CakeConfig> allConfigs = new ArrayList<>();
        SqlRowSet result = jdbcTemplate.queryForRowSet(sqlForAllConfigs);
        while (result.next()){
            CakeConfig config = mapRowToCakeConfig(result);
            allConfigs.add(config);
        }
        return allConfigs;
    }

    //TODO
    @Override
    public CakeConfig getCakeConfig(String configName) {
        return null;
    }

    @Override
    public Integer addCakeConfig(CakeConfig configToAdd) {
        String sqlToAddNewCakeConfig = "INSERT INTO cake_config " +
                "(cake_config_name, cake_config_img_url, cake_config_description, flavor_id, frosting_id, filling_id)\n" +
                "\tVALUES(?, ?, ?, ?, ?, ?) RETURNING cake_config_id;";
        Integer newID = jdbcTemplate.queryForObject(sqlToAddNewCakeConfig, Integer.class, configToAdd.getCakeConfigName(), configToAdd.getCakeConfigUrl(),
                configToAdd.getCakeConfigDescription(), configToAdd.getCakeConfigFlavorID(), configToAdd.getCakeConfigFrostingID(), configToAdd.getCakeConfigFillingID());
        return newID;
    }


    public CakeConfig mapRowToCakeConfig (SqlRowSet result){
     return new CakeConfig(result.getInt("cake_config_id"),
     result.getString("cake_config_name"), result.getString("cake_config_img_url"),
     result.getString("cake_config_description"), result.getInt("filling_id"),
     result.getInt("flavor_id"), result.getInt("frosting_id"));
    }

}
