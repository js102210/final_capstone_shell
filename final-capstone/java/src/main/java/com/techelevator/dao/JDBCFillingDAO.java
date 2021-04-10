package com.techelevator.dao;

import com.techelevator.model.Filling;

import java.math.BigDecimal;
import java.util.List;

public class JDBCFillingDAO implements FillingDAO{
    @Override
    public List<Filling> getAvailableFillings() {
        return null;
    }

    @Override
    public List<Filling> getAllFillings() {
        return null;
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
}
