package com.techelevator.model;

import java.math.BigDecimal;

public class Style {
    private int styleID;
    private String styleName;
    private boolean isAvailable;
    private BigDecimal priceMod;

    public int getStyleID() {
        return styleID;
    }

    public void setStyleID(int styleID) {
        this.styleID = styleID;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
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
