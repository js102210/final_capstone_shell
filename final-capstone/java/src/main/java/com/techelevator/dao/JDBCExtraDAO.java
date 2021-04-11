package com.techelevator.dao;

import com.techelevator.model.Extra;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JDBCExtraDAO implements ExtraDAO{
    private final JdbcTemplate jdbcTemplate;
    public JDBCExtraDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List <Extra> getAvailableExtras() {
        String sqlToGetAvailableExtras = "SELECT * FROM extras WHERE is_available = TRUE;";
        List<Extra> availableExtras = new ArrayList<>();
        SqlRowSet result = jdbcTemplate.queryForRowSet(sqlToGetAvailableExtras);
        while (result.next()){
            Extra extra = mapRowToExtra(result);
            availableExtras.add(extra);
        }
        return availableExtras;
    }

    @Override
    public List<Extra> getAllExtras() {
        String sqlToGetAllExtras = "SELECT * FROM extras;";
        List<Extra> allExtras = new ArrayList<>();
        SqlRowSet result = jdbcTemplate.queryForRowSet(sqlToGetAllExtras);
        while (result.next()){
            Extra extra = mapRowToExtra(result);
            allExtras.add(extra);
        }
        return allExtras;
    }

    @Override
    public int createExtra(String extraName, BigDecimal priceMod) {
        return 0;
    }

    @Override
    public Extra updateExtra(Extra extra, int ID) {
        return null;
    }
    @Override
    public String deleteExtra(int ID) {
        return null;
    }

    public Extra mapRowToExtra (SqlRowSet result){
        Extra extra = new Extra();
        extra.setExtraID(result.getInt("extra_id"));
        extra.setExtraName(result.getString("extra_name"));
        extra.setAvailable(result.getBoolean("is_available"));
        extra.setPriceMod(result.getBigDecimal("price_mod"));
        return extra;
    }
}
