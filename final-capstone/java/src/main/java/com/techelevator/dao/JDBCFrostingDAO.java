package com.techelevator.dao;

import com.techelevator.model.Frosting;

import java.math.BigDecimal;
import java.util.List;

public class JDBCFrostingDAO implements FrostingDAO{
    @Override
    public List<Frosting> getAvailableFlavors() {
        return null;
    }

    @Override
    public List<Frosting> getAllFrostings() {
        return null;
    }

    @Override
    public int createFrosting(String frostingName, BigDecimal priceMod) {
        return 0;
    }

    @Override
    public boolean flipAvailability(int ID) {
        return false;
    }

    @Override
    public String deleteFrosting(int ID) {
        return null;
    }
}
