package com.techelevator.dao;

import com.techelevator.model.Flavor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCFlavorDAO implements FlavorDAO {
    private final JdbcTemplate jdbcTemplate;
    private final CakeConfigDAO cakeConfigDAO;

    public JDBCFlavorDAO(JdbcTemplate jdbcTemplate, CakeConfigDAO cakeConfigDAO) {
        this.cakeConfigDAO = cakeConfigDAO;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List <Flavor> getAvailableFlavors() {
        String sqlToGetAvailableFlavors = "SELECT * FROM flavors WHERE is_available = TRUE ORDER BY flavor_id ;";
        List <Flavor> availableFlavors = new ArrayList<>();
        SqlRowSet result = jdbcTemplate.queryForRowSet (sqlToGetAvailableFlavors);
        while (result.next()) {
            Flavor flavor = mapRowToFlavor(result);
            availableFlavors.add(flavor);
        }
        return availableFlavors;
    }

    @Override
    public List <Flavor> getAllFlavors(){
        String sqlToGetAllFlavors = "SELECT * FROM flavors ORDER BY flavor_id;";
        List <Flavor> allFlavors = new ArrayList <> ();
        SqlRowSet result = jdbcTemplate.queryForRowSet(sqlToGetAllFlavors);
        while (result.next()) {
            Flavor flavor = mapRowToFlavor(result);
            allFlavors.add(flavor);
        }
        return allFlavors;
    }



    @Override
    public int createFlavor(Flavor newFlavor) {
        String sqlToAddNewFlavor = "INSERT INTO flavors (flavor_name, price_mod) VALUES (?, ?) RETURNING flavor_id ;";
        Integer newID = jdbcTemplate.queryForObject (sqlToAddNewFlavor, Integer.class, newFlavor.getFlavorName(), newFlavor.getPriceMod());
        return newID;
    }

    @Override
    public boolean flipAvailability(int id) {
        String sqlFlipStatusStatement = "UPDATE flavors SET is_available = NOT is_available WHERE flavor_id = ? RETURNING is_available ;";
        Boolean result = jdbcTemplate.queryForObject (sqlFlipStatusStatement, Boolean.class, id);
        //flips availability for cake configs associated with this flavor if the flavor is now unavailable and the
        //configs are currently available.
        if(result == false){
            String sqlFindConfigsToMakeUnavail = "SELECT cake_config_id FROM cake_config WHERE flavor_id = ? AND is_available = TRUE ;";
            SqlRowSet cakeConfigIds = jdbcTemplate.queryForRowSet(sqlFindConfigsToMakeUnavail, id);
            while(cakeConfigIds.next()){
                int theConfigId = cakeConfigDAO.getCakeIdFromQuery(cakeConfigIds);
                cakeConfigDAO.flipConfigStatus(theConfigId);
            }
            return result;
        } else {
            return result;
        }
    }

    @Override
    public String deleteFlavor(int ID) {
        return null;
    }



    @Override
    public Flavor updateFlavor(Flavor flavor, int flavorID) {
        String sqlToUpdateFlavor = "UPDATE flavors SET flavor_name = ?, is_available = ?, price_mod = ?" +
                "WHERE flavor_id = ?;";
        jdbcTemplate.update (sqlToUpdateFlavor, flavor.getFlavorName(), flavor.isAvailable(), flavor.getPriceMod(),
                flavorID);
        return flavor;
    }


    public Flavor mapRowToFlavor(SqlRowSet result) {
        Flavor flavor = new Flavor();
        flavor.setFlavorID (result.getInt("flavor_id"));
        flavor.setFlavorName (result.getString("flavor_name"));
        flavor.setIsAvailable (result.getBoolean("is_available"));
        flavor.setPriceMod (result.getBigDecimal("price_mod"));
        return flavor;
    }


}
