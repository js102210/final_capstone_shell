package com.techelevator.dao;

import com.techelevator.model.Style;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCStyleDAO implements StyleDAO {


    private final JdbcTemplate jdbcTemplate;

    public JDBCStyleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List <Style> getAvailableStyles() {

        String sqlToGetAvailableStyles = "SELECT * FROM styles WHERE is_available = TRUE ORDER BY style_id ;";
        List <Style> availableStyles = new ArrayList<>();
        SqlRowSet result = jdbcTemplate.queryForRowSet(sqlToGetAvailableStyles);
        while (result.next()) {
            Style theStyle = mapRowToStyle(result);
            availableStyles.add(theStyle);
        }
        return availableStyles;
    }

    @Override
    public List <Style> getAllStyles(){
        String sqlToGetAllStyles = "SELECT * FROM styles ORDER BY style_id ;";
        List <Style> allStyles = new ArrayList<>();
        SqlRowSet result = jdbcTemplate.queryForRowSet (sqlToGetAllStyles);
        while (result.next()) {
            Style theStyle = mapRowToStyle(result);
            allStyles.add(theStyle);
        }
        return allStyles;
    }

    @Override
    public Style updateStyle(Style style, int styleID) {
        String sqlToUpdateStyle = "UPDATE styles\n" +
                "SET style_name = ?,\n" +
                "is_available = ?,\n" +
                "price_mod = ?\n" +
                "WHERE style_id = ?; ";
        jdbcTemplate.update (sqlToUpdateStyle, style.getStyleName(), style.isAvailable(), style.getPriceMod(),
                styleID);
        return style;
    }

    @Override
    public int createStyle(Style newStyle) {
        String sqlToAddNewStyle = "INSERT INTO styles (style_name, price_mod) VALUES (?, ?) RETURNING style_id ;";
        Integer newID = jdbcTemplate.queryForObject (sqlToAddNewStyle, Integer.class, newStyle.getStyleName(),
                newStyle.getPriceMod());
        return newID;
    }

    @Override
    public boolean flipAvailability(int id) {
        String sqlFlipStatusStatement = "UPDATE styles SET is_available = NOT is_available WHERE style_id = ? RETURNING is_available ;";
        Boolean result = jdbcTemplate.queryForObject (sqlFlipStatusStatement, Boolean.class, id);
        return result;
    }

    @Override
    public void deleteStyle(int id) {
        String sqlToDeleteStyle = "DELETE FROM styles WHERE style_id=?;";
        jdbcTemplate.update(sqlToDeleteStyle, id);
    }


    public Style mapRowToStyle(SqlRowSet result) {
        Style theStyle = new Style();
        theStyle.setStyleID(result.getInt("style_id"));
        theStyle.setStyleName(result.getString("style_name"));
        theStyle.setIsAvailable(result.getBoolean("is_available"));
        theStyle.setPriceMod(result.getBigDecimal("price_mod"));
        return theStyle;
    }
}

