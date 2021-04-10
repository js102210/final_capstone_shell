package com.techelevator.dao;

import com.techelevator.model.Extra;


import java.math.BigDecimal;
import java.util.List;

public class JDBCExtraDAO implements ExtraDAO{
    @Override
    public List <Extra> getAvailableExtras() {
        return null;
    }

    @Override
    public List<Extra> getAllExtras() {
        return null;
    }

    @Override
    public int createExtra(String extraName, BigDecimal priceMod) {
        return 0;
    }

    @Override
    public boolean flipAvailability(int ID) {
        return false;
    }

    @Override
    public String deleteExtra(int ID) {
        return null;
    }
}
