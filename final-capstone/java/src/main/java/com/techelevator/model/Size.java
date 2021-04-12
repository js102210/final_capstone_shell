package com.techelevator.model;

import java.math.BigDecimal;

public class Size {
    private int sizeID;
    private String sizeName;
    private String sizeDescription;
    private boolean isAvailable;
    private BigDecimal priceMod;

    public int getSizeID() {
        return sizeID;
    }

    public void setSizeID(int sizeID) {
        this.sizeID = sizeID;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getSizeDescription() {
        return sizeDescription;
    }

    public void setSizeDescription(String sizeDescription) {
        this.sizeDescription = sizeDescription;
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
