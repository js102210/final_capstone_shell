package com.techelevator.model;

import java.math.BigDecimal;

public class Flavor {
    private int flavorId;
    private String flavorName;
    private boolean isAvailable;
    private BigDecimal priceMod;
    public int getFlavorId() {
        return flavorId;
    }

    public void setFlavorId(int flavorId) {
        this.flavorId = flavorId;
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

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public BigDecimal getPriceMod() {
        return priceMod;
    }

    public void setPriceMod(BigDecimal priceMod) {
        this.priceMod = priceMod;
    }


}
