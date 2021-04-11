package com.techelevator.dao;

import com.techelevator.model.Filling;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCFillingDAO implements FillingDAO{
    private final JdbcTemplate jdbcTemplate;
    public JDBCFillingDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Filling> getAvailableFillings() {
        String sqlToGetAvailableFillings = "SELECT * FROM fillings WHERE is_available = TRUE;";
        List<Filling> availableFillings = new ArrayList<>();
        SqlRowSet result = jdbcTemplate.queryForRowSet(sqlToGetAvailableFillings);
        while (result.next()){
            Filling filling = mapRowToFilling(result);
            availableFillings.add(filling);
        }
        return availableFillings;
    }

    @Override
    public List<Filling> getAllFillings() {
        String sqlToGetAllFillings = "SELECT * FROM fillings;";
        List<Filling> allFillings = new ArrayList<>();
        SqlRowSet result = jdbcTemplate.queryForRowSet(sqlToGetAllFillings);
        while (result.next()){
            Filling filling = mapRowToFilling(result);
            allFillings.add(filling);
        }
        return allFillings;
    }

    @Override
    public int createFilling(String FillingName, BigDecimal priceMod) {
        return 0;
    }

    @Override
    public boolean flipAvailability(int ID) {
        return false;
    }

    @Override
    public String deleteFilling(int ID) {
        return null;
    }

    @Override
    public void update(Filling filling) {
        String sqlToUpdateFilling = "UPDATE filling SET filling_name = ?, is_available = ?, price_mod = ?" +
                "WHERE filling_id = ?;";
        jdbcTemplate.update (sqlToUpdateFilling, filling.getFillingName (), filling.isAvailable (), filling.getPriceMod (),
                filling.getFillingID ());
    }

    public Filling mapRowToFilling(SqlRowSet result){
        Filling filling = new Filling();
        filling.setFillingID(result.getInt("filling_id"));
        filling.setFillingName(result.getString("filling_name"));
        filling.setIsAvailable(result.getBoolean("is_available"));
        filling.setPriceMod(result.getBigDecimal("price_mod"));
        return filling;
    }
}
