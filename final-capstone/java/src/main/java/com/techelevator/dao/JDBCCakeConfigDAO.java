package com.techelevator.dao;

import com.techelevator.model.CakeConfig;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCCakeConfigDAO implements CakeConfigDAO{
private JdbcTemplate jdbcTemplate;
public JDBCCakeConfigDAO(JdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
}

    @Override
    public List<CakeConfig> getAllConfigs() {
        String sqlForAllConfigs = "SELECT cake_config.cake_config_id, cake_config.cake_config_name, cake_config.cake_config_img_url,\n" +
                " cake_config.cake_config_description, fillings.filling_name, frostings.frosting_name, flavors.flavor_name\n" +
                "FROM cake_config\n" +
                "INNER JOIN fillings ON fillings.filling_id = cake_config.filling_id\n" +
                "INNER JOIN frostings ON frostings.frosting_id = cake_config.frosting_id\n" +
                "INNER JOIN flavors ON flavors.flavor_id = cake_config.flavor_id;";
        List<CakeConfig> allConfigs = new ArrayList<>();
        SqlRowSet result = jdbcTemplate.queryForRowSet(sqlForAllConfigs);
        while (result.next()){
            CakeConfig config = mapRowToCakeConfig(result);
            allConfigs.add(config);
        }
        return allConfigs;
    }

    @Override
    public CakeConfig getCakeConfig(String configName) {
        return null;
    }

    public CakeConfig mapRowToCakeConfig (SqlRowSet result){
        return new CakeConfig(result.getInt("cake_config_id"),
         result.getString("cake_config_name"), result.getString("cake_config_img_url"),
         result.getString("cake_config_description"), result.getString("filling_name"),
          result.getString("flavor_name"), result.getString("frosting_name"));
    }

}
