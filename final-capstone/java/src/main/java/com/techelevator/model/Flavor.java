package com.techelevator.model;

import java.math.BigDecimal;

public class Flavor {
    private int flavorID;
    private String flavorName;
    private boolean isAvailable;
    private BigDecimal priceMod;

    public int getFlavorID() {
        return flavorID;
    }

    public void setFlavorID(int flavorID) {
        this.flavorID = flavorID;
    }

    public String getFlavorName() {
        return flavorName;
    }

    public void setFlavorName(String flavorName) {
        this.flavorName = flavorName;
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
