package com.techelevator.dao;

import com.techelevator.model.Filling;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCFillingDAO implements FillingDAO {
    private final JdbcTemplate jdbcTemplate;
    private final CakeConfigDAO cakeConfigDAO;

    public JDBCFillingDAO(JdbcTemplate jdbcTemplate, CakeConfigDAO cakeConfigDAO) {
        this.cakeConfigDAO = cakeConfigDAO;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List <Filling> getAvailableFillings() {
        String sqlToGetAvailableFillings = "SELECT * FROM fillings WHERE is_available = TRUE ORDER BY filling_id ;";
        List <Filling> availableFillings = new ArrayList<>();
        SqlRowSet result = jdbcTemplate.queryForRowSet (sqlToGetAvailableFillings);
        while (result.next()) {
            Filling filling = mapRowToFilling (result);
            availableFillings.add (filling);
        }
        return availableFillings;
    }

    @Override
    public List <Filling> getAllFillings() {
        String sqlToGetAllFillings = "SELECT * FROM fillings ORDER BY filling_id ;";
        List <Filling> allFillings = new ArrayList <> ();
        SqlRowSet result = jdbcTemplate.queryForRowSet (sqlToGetAllFillings);
        while (result.next()) {
            Filling filling = mapRowToFilling (result);
            allFillings.add (filling);
        }
        return allFillings;
    }



    @Override
    public int createFilling(Filling newFilling) {
        String sqlToAddNewFilling = "INSERT INTO fillings (filling_name, price_mod) VALUES (?, ?) RETURNING filling_id ;";
        Integer newID = jdbcTemplate.queryForObject (sqlToAddNewFilling, Integer.class, newFilling.getFillingName(), newFilling.getPriceMod());
        return newID;
    }

    //not implemented - currently out of scope for our sprints.
    @Override
    public String deleteFilling(int ID) {
        return null;
    }

    @Override
    public Filling updateFilling(Filling filling, int fillingID) {
        String sqlToUpdateFilling = "UPDATE fillings\n" +
                "SET filling_name = ?,\n" +
                "is_available = ?,\n" +
                "price_mod = ?\n" +
                "WHERE filling_id = ?; ";
        jdbcTemplate.update (sqlToUpdateFilling, filling.getFillingName(), filling.isAvailable(), filling.getPriceMod(),
                fillingID);
        return filling;
    }

    @Override
    public boolean flipAvailability(int id) {
        String sqlFlipStatusStatement = "UPDATE fillings SET is_available = NOT is_available WHERE filling_id = ? RETURNING is_available ;";
        Boolean result = jdbcTemplate.queryForObject (sqlFlipStatusStatement, Boolean.class, id);
        //flips availability for cake configs associated with this filling if the filling is now unavailable and the
        //configs are currently available.
        if(result == false){
            String sqlFindConfigsToMakeUnavail = "SELECT cake_config_id FROM cake_config WHERE filling_id = ? AND is_available = TRUE ;";
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

    public Filling mapRowToFilling(SqlRowSet result) {
        Filling filling = new Filling ();
        filling.setFillingID (result.getInt ("filling_id"));
        filling.setFillingName (result.getString ("filling_name"));
        filling.setIsAvailable (result.getBoolean ("is_available"));
        filling.setPriceMod (result.getBigDecimal ("price_mod"));
        return filling;
    }
}
