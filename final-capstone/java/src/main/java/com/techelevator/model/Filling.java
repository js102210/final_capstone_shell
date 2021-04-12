package com.techelevator.model;

import java.math.BigDecimal;

public class Filling {
    private int fillingID;
    private String fillingName;
    private boolean isAvailable;
    private BigDecimal priceMod;

    public int getFillingID() {
        return fillingID;
    }

    public void setFillingID(int fillingID) {
        this.fillingID = fillingID;
    }

    public String getFillingName() {
        return fillingName;
    }

    public void setFillingName(String fillingName) {
        this.fillingName = fillingName;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean available) {
        isAvailable = available;
    }

    public BigDecimal getPriceMod() {
        return priceMod;
    }

    public void setPriceMod(BigDecimal priceMod) {
        this.priceMod = priceMod;
    }


}
