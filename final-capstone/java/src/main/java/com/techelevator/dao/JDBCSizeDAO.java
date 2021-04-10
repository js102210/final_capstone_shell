package com.techelevator.dao;


import com.techelevator.model.Size;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
@Component
public class JDBCSizeDAO implements SizeDAO {
    @Override
    public List <Size> getAvailableSizes() {
        return null;
    }

    @Override
    public List<Size> getAllSizes() {
        return null;
    }

    @Override
    public int createSize(String SizeName, String sizeDescription, BigDecimal priceMod) {
        return 0;
    }

    @Override
    public boolean flipAvailability(int ID) {
        return false;
    }

    @Override
    public String deleteSize(int ID) {
        return null;
    }
}

