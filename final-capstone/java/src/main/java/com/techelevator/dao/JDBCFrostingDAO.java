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
    private final CakeConfigDAO cakeConfigDAO;

    public JDBCFrostingDAO(JdbcTemplate jdbcTemplate, CakeConfigDAO cakeConfigDAO) {
        this.cakeConfigDAO = cakeConfigDAO;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List <Frosting> getAvailableFrostings() {
        String sqlToGetAvailableFrostings = "SELECT * FROM frostings WHERE is_available = TRUE ORDER BY frosting_id ;";
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
        String sqlToGetAllFrostings = "SELECT * FROM frostings ORDER BY frosting_id ;";
        List <Frosting> allFrostings = new ArrayList <> ();
        SqlRowSet result = jdbcTemplate.queryForRowSet (sqlToGetAllFrostings);
        while (result.next ()) {
            Frosting frosting = mapRowToFrosting (result);
            allFrostings.add (frosting);
        }
        return allFrostings;
    }

    @Override
    public int createFrosting(Frosting newFrosting) {
        String sqlToAddNewFrosting = "INSERT INTO frostings (frosting_name, price_mod) VALUES (?, ?) RETURNING frosting_id ;";
        Integer newID = jdbcTemplate.queryForObject (sqlToAddNewFrosting, Integer.class, newFrosting.getFrostingName (), newFrosting.getPriceMod ());
        return newID;
    }

    @Override
    public boolean flipAvailability(int id) {
        String sqlFlipStatusStatement = "UPDATE frostings SET is_available = NOT is_available WHERE frosting_id = ? RETURNING is_available ;";
        Boolean result = jdbcTemplate.queryForObject (sqlFlipStatusStatement, Boolean.class, id);
        //flips availability for cake configs associated with this frosting if the frosting is now unavailable and the
        //configs are currently available.
        if(result == false){
            String sqlFindConfigsToMakeUnavail = "SELECT cake_config_id FROM cake_config WHERE frosting_id = ? AND is_available = TRUE ;";
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
    public void deleteFrosting(int id) {
        String sqlToDeleteFrosting = "DELETE FROM frostings WHERE frosting_id=?;";
        jdbcTemplate.update(sqlToDeleteFrosting, id);
    }

    @Override
    public void updateFrosting(Frosting frosting, int id) {
        String sqlToUpdateFrosting = "UPDATE frosting SET frosting_name = ?,  price_mod = ?" +
                "WHERE frosting_id = ?;";
        jdbcTemplate.update (sqlToUpdateFrosting, frosting.getFrostingName(), frosting.getPriceMod(),
                id );
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
