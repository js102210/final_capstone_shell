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


    public JDBCFlavorDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List <Flavor> getAvailableFlavors() {
        String sqlToGetAvailableFlavors = "SELECT * FROM flavors WHERE is_available = TRUE;";
        List <Flavor> availableFlavors = new ArrayList <> ();
        SqlRowSet result = jdbcTemplate.queryForRowSet (sqlToGetAvailableFlavors);
        while (result.next ()) {
            Flavor flavor = mapRowToFlavor (result);
            availableFlavors.add (flavor);
        }
        return availableFlavors;
    }

    @Override
    public List <Flavor> getAllFlavors() {
        String sqlToGetAllFlavors = "SELECT * FROM flavors;";
        List <Flavor> allFlavors = new ArrayList <> ();
        SqlRowSet result = jdbcTemplate.queryForRowSet (sqlToGetAllFlavors);
        while (result.next ()) {
            Flavor flavor = mapRowToFlavor (result);
            allFlavors.add (flavor);
        }
        return allFlavors;
    }

    @Override
    public int createFlavor(String flavorName, BigDecimal priceMod) {
        return 0;
    }

    @Override
    public int createFlavor(Flavor newFlavor) {
        return 0;
    }

    @Override
    public boolean flipAvailability(int id) {
        String sqlFlipStatusStatement = "UPDATE flavors SET is_available = NOT is_available WHERE flavor_id = ? RETURNING is_available ;";

        Boolean result = jdbcTemplate.queryForObject (sqlFlipStatusStatement, Boolean.class, id);

        return result;
    }

    @Override
    public String deleteFlavor(int ID) {
        return null;
    }



    @Override
    public Flavor updateFlavor(Flavor flavor, int flavorID) {
        String sqlToUpdateFlavor = "UPDATE flavors SET flavor_name = ?, is_available = ?, price_mod = ?" +
                "WHERE flavor_id = ?;";
        jdbcTemplate.update (sqlToUpdateFlavor, flavor.getFlavorName (), flavor.isAvailable (), flavor.getPriceMod (),
                flavorID
        );
        return flavor;
    }


    public Flavor mapRowToFlavor(SqlRowSet result) {
        Flavor flavor = new Flavor ();
        flavor.setFlavorID (result.getInt ("flavor_id"));
        flavor.setFlavorName (result.getString ("flavor_name"));
        flavor.setIsAvailable (result.getBoolean ("is_available"));
        flavor.setPriceMod (result.getBigDecimal ("price_mod"));
        return flavor;
    }
}
