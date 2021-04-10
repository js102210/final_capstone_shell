package com.techelevator.dao;

import com.techelevator.model.Flavor;

import java.math.BigDecimal;
import java.util.List;

public class JDBCFlavorDAO implements FlavorDAO{
    @Override
    public List<Flavor> getAvailableFlavors() {
        return null;
    }

    @Override
    public List<Flavor> getAllFlavors() {
        return null;
    }

    @Override
    public int createFlavor(String flavorName, BigDecimal priceMod) {
        return 0;
    }

    @Override
    public boolean flipAvailability(int ID) {
        return false;
    }

    @Override
    public String deleteFlavor(int ID) {
        return null;
    }
}
