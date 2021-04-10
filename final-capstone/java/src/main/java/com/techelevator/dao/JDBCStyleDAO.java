package com.techelevator.dao;


import com.techelevator.model.Style;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
@Component
public class JDBCStyleDAO implements StyleDAO{
    @Override
    public List <Style> getAvailableStyles() {
        return null;
    }

    @Override
    public List<Style> getAllStyles() {
        return null;
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
}

