package com.techelevator.dao;

import com.techelevator.model.CakeConfig;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCCakeConfigDAO implements CakeConfigDAO {
    private final JdbcTemplate jdbcTemplate;


    public JDBCCakeConfigDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List <CakeConfig> getAllConfigs() {
        String sqlForAllConfigs =
                "SELECT *  FROM cake_config;";
        List <CakeConfig> allConfigs = new ArrayList <> ();
        SqlRowSet result = jdbcTemplate.queryForRowSet (sqlForAllConfigs);
        while (result.next()) {
            CakeConfig config = mapRowToCakeConfig (result);
            allConfigs.add (config);
        }
        return allConfigs;
    }

    @Override
    public List <CakeConfig> getAvailableConfigs() {
        String sqlForAllConfigs =
                "SELECT *  FROM cake_config WHERE is_available = true;";
        List <CakeConfig> allConfigs = new ArrayList <> ();
        SqlRowSet result = jdbcTemplate.queryForRowSet (sqlForAllConfigs);
        while (result.next()) {
            CakeConfig config = mapRowToCakeConfig (result);
            allConfigs.add (config);
        }
        return allConfigs;
    }


    @Override
    public Integer addCakeConfig(CakeConfig configToAdd) {
        String sqlToAddNewCakeConfig = "INSERT INTO cake_config " +
                "(cake_config_name, cake_config_img_url, cake_config_description, flavor_id, frosting_id, filling_id)\n" +
                "\tVALUES(?, ?, ?, ?, ?, ?) RETURNING cake_config_id;";
        Integer newID = jdbcTemplate.queryForObject (sqlToAddNewCakeConfig, Integer.class, configToAdd.getCakeConfigName(), configToAdd.getCakeConfigUrl(),
                configToAdd.getCakeConfigDescription(), configToAdd.getCakeConfigFlavorID(), configToAdd.getCakeConfigFrostingID(), configToAdd.getCakeConfigFillingID()
        );
        return newID;
    }

    @Override
    public boolean flipConfigStatus(int id) {
        String sqlFlipStatusStatement = "UPDATE cake_config SET is_available = NOT is_available WHERE cake_config_id = ? RETURNING is_available ;";

        Boolean result = jdbcTemplate.queryForObject (sqlFlipStatusStatement, Boolean.class, id);

        return result;
    }

    public CakeConfig mapRowToCakeConfig(SqlRowSet result) {
        CakeConfig config = new CakeConfig ();
        config.setCakeConfigID (result.getInt ("cake_config_id"));
        config.setCakeConfigName (result.getString ("cake_config_name"));
        config.setCakeConfigUrl (result.getString ("cake_config_img_url"));
        config.setCakeConfigDescription (result.getString ("cake_config_description"));
        config.setCakeConfigFillingID (result.getInt ("filling_id"));
        config.setCakeConfigFlavorID (result.getInt ("flavor_id"));
        config.setCakeConfigFrostingID (result.getInt ("frosting_id"));
        config.setIsAvailable(result.getBoolean("is_available"));
        return config;

    }

    @Override
    public int getCakeIdFromQuery(SqlRowSet result){
        return result.getInt("cake_config_id");
    }

}



