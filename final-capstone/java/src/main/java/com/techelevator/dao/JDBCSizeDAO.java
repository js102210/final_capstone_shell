package com.techelevator.dao;

import com.techelevator.model.Size;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCSizeDAO implements SizeDAO {
    private final JdbcTemplate jdbcTemplate;


    public JDBCSizeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List <Size> getAvailableSizes() {
        String sqlToGetAvailableSizes = "SELECT * FROM sizes WHERE is_available = TRUE ORDER BY size_id ;";
        List <Size> availableSizes = new ArrayList<>();
        SqlRowSet result = jdbcTemplate.queryForRowSet (sqlToGetAvailableSizes);
        while (result.next()) {
            Size size = mapRowToSize(result);
            availableSizes.add(size);
        }
        return availableSizes;
    }

    @Override
    public List <Size> getAllSizes() {
        String sqlToGetAllSizes = "SELECT * FROM sizes ORDER BY size_id ;";
        List <Size> allSizes = new ArrayList<>();
        SqlRowSet result = jdbcTemplate.queryForRowSet (sqlToGetAllSizes);
        while (result.next()) {
            Size size = mapRowToSize(result);
            allSizes.add(size);
        }
        return allSizes;
    }


    @Override
    public int createSize(Size newSize) {
        String sqlToAddNewSize = "INSERT INTO sizes (size_name, size_description, price_mod) VALUES (?, ?, ?) RETURNING size_id ;";
        Integer newID = jdbcTemplate.queryForObject(sqlToAddNewSize, Integer.class, newSize.getSizeName(),
                newSize.getSizeDescription(), newSize.getPriceMod());
        return newID;
    }

    @Override
    public Size updateSize(Size size, int sizeID) {
        String sqlToUpdateSize = "UPDATE size\n" +
                "SET size_name = ?,\n" +
                "SET size_description,\n" +
                "is_available = ?,\n" +
                "price_mod = ?\n" +
                "WHERE size_id = ?; ";
        jdbcTemplate.update(sqlToUpdateSize, size.getSizeName(), size.getSizeDescription(), size.isAvailable(),
                size.getPriceMod(), sizeID);
        return size;
    }

    @Override
    public void deleteSize(int id) {
        String sqlToDeleteSize = "DELETE FROM sizes WHERE size_id=?;";
        jdbcTemplate.update (sqlToDeleteSize, id);
    }

    @Override
    public boolean flipAvailability(int id) {
        String sqlFlipStatusStatement = "UPDATE sizes SET is_available = NOT is_available WHERE size_id = ? RETURNING is_available ;";
        Boolean result = jdbcTemplate.queryForObject(sqlFlipStatusStatement, Boolean.class, id);
        return result;
    }

    public Size mapRowToSize(SqlRowSet result) {
        Size size = new Size();
        size.setSizeID(result.getInt("size_id"));
        size.setSizeName(result.getString("size_name"));
        size.setSizeDescription(result.getString("size_description"));
        size.setIsAvailable(result.getBoolean("is_available"));
        size.setPriceMod(result.getBigDecimal("price_mod"));
        return size;
    }
}

