package com.techelevator.model;

import java.math.BigDecimal;

public class Frosting {
    private int frostingID;
    private String frostingName;
    private boolean isAvailable;
    private BigDecimal priceMod;

    public int getFrostingID() {
        return frostingID;
    }

    public void setFrostingID(int frostingID) {
        this.frostingID = frostingID;
    }

    public String getFrostingName() {
        return frostingName;
    }

    public void setFrostingName(String frostingName) {
        this.frostingName = frostingName;
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
