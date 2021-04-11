package com.techelevator.dao;


import com.techelevator.model.Style;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Component
public class JDBCStyleDAO implements StyleDAO{


    private final JdbcTemplate jdbcTemplate;
    public JDBCStyleDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List <Style> getAvailableStyles() {

        String sqlToGetAvailableStyles = "SELECT * FROM styles WHERE is_available = TRUE;";
        List<Style> availableStyles = new ArrayList<>();
        SqlRowSet result = jdbcTemplate.queryForRowSet(sqlToGetAvailableStyles);
        while (result.next()) {
            Style theStyle = mapRowToStyle(result);
            availableStyles.add(theStyle);
        }
    return availableStyles;
    }
        @Override
        public List<Style> getAllStyles() {
            String sqlToGetAllStyles = "SELECT * FROM styles ;";
            List<Style> allStyles = new ArrayList<>();
            SqlRowSet result = jdbcTemplate.queryForRowSet(sqlToGetAllStyles);
            while (result.next()) {
                Style theStyle = mapRowToStyle(result);
                allStyles.add(theStyle);
            }
            return allStyles;
        }

        @Override
        public int createStyle(String styleName, BigDecimal priceMod) {
            return 0;
        }

        @Override
        public boolean flipAvailability(int ID) {
            return false;
        }

        @Override
        public String deleteStyle(int ID) {
            return null;
        }


        public Style mapRowToStyle(SqlRowSet result){
            Style theStyle = new Style();
            theStyle.setStyleID(result.getInt("style_id"));
            theStyle.setStyleName(result.getString("style_name"));
            theStyle.setIsAvailable(result.getBoolean("is_available"));
            theStyle.setPriceMod(result.getBigDecimal("price_mod"));
            return theStyle;
        }
    }

