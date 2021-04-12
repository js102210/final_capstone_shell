package com.techelevator.dao;

import com.techelevator.model.Frosting;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCFrostingDAO implements FrostingDAO {
    private final JdbcTemplate jdbcTemplate;

    public JDBCFrostingDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List <Frosting> getAvailableFrostings() {
        String sqlToGetAvailableFrostings = "SELECT * FROM frostings WHERE is_available = TRUE;";
        List <Frosting> availableFrostings = new ArrayList <> ();
        SqlRowSet result = jdbcTemplate.queryForRowSet (sqlToGetAvailableFrostings);
        while (result.next ()) {
            Frosting frosting = mapRowToFrosting (result);
            availableFrostings.add (frosting);
        }
        return availableFrostings;
    }

    @Override
    public List <Frosting> getAllFrostings() {
        String sqlToGetAllFrostings = "SELECT * FROM frostings;";
        List <Frosting> allFrostings = new ArrayList <> ();
        SqlRowSet result = jdbcTemplate.queryForRowSet (sqlToGetAllFrostings);
        while (result.next ()) {
            Frosting frosting = mapRowToFrosting (result);
            allFrostings.add (frosting);
        }
        return allFrostings;
    }

    @Override
    public int createFrosting(String frostingName, BigDecimal priceMod) {
        return 0;
    }

    @Override
    public int createFrosting(Frosting newFrosting) {
        return 0;
    }

    @Override
    public boolean flipAvailability(int id) {
        String sqlFlipStatusStatement = "UPDATE frostings SET is_available = NOT is_available WHERE frosting_id = ? RETURNING is_available ;";

        Boolean result = jdbcTemplate.queryForObject (sqlFlipStatusStatement, Boolean.class, id);

        return result;
    }

    @Override
    public String deleteFrosting(int ID) {
        return null;
    }

    @Override
    public void update(Frosting frosting) {
        String sqlToUpdateFrosting = "UPDATE frosting SET frosting_name = ?, is_available = ?, price_mod = ?" +
                "WHERE frosting_id = ?;";
        jdbcTemplate.update (sqlToUpdateFrosting, frosting.getFrostingName (), frosting.isAvailable (), frosting.getPriceMod (),
                frosting.getFrostingID ()
        );
    }

    @Override
    public void updateFrosting(Frosting frosting, int id) {

    }

    public Frosting mapRowToFrosting(SqlRowSet result) {
        Frosting frosting = new Frosting ();
        frosting.setFrostingID (result.getInt ("frosting_id"));
        frosting.setFrostingName (result.getString ("frosting_name"));
        frosting.setIsAvailable (result.getBoolean ("is_available"));
        frosting.setPriceMod (result.getBigDecimal ("price_mod"));
        return frosting;
    }
}
