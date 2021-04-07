package com.techelevator.model;

import java.math.BigDecimal;

//this will be our cake object
public class CakeItemDTO {
    private int cakeItemId;
    private int cakeItemStyleID;
    private int cakeItemSizeID;
    private String cakeItemMessage = "";
    private int cakeItemFillingID;
    private int cakeItemFrostingID;
    private int cakeItemFlavorID;

    private int cakeItemConfigId;
    private BigDecimal cakeItemPrice;

    /**
     * constructor for cake item
     *
     * @param itemStyle String
     * @param itemSize String
     * @param itemMessage String
     * @param itemPrice BigDecimal
     * @param cakeItemFilling String
     * @param cakeItemFrosting String
     * @param cakeItemFlavor String
     */
    public CakeItemDTO(int itemStyle, int itemSize, String itemMessage, BigDecimal itemPrice,
                       int cakeItemFilling, int cakeItemFrosting, int cakeItemFlavor){

        this.cakeItemSizeID = itemSize;
        this.cakeItemStyleID = itemStyle;
        this.cakeItemMessage = itemMessage;
        this.cakeItemPrice = itemPrice;
        this.cakeItemFillingID = cakeItemFilling;
        this.cakeItemFrostingID = cakeItemFrosting;
        this.cakeItemFlavorID = cakeItemFlavor;
    }

    public int getCakeItemId() {
        return cakeItemId;
    }

    public void setCakeItemId(int cakeItemId) {
        this.cakeItemId = cakeItemId;
    }

    public int getCakeItemStyleID() {
        return cakeItemStyleID;
    }

    public void setCakeItemStyleID(int cakeItemStyleID) {
        this.cakeItemStyleID = cakeItemStyleID;
    }

    public int getCakeItemSizeID() {
        return cakeItemSizeID;
    }

    public void setCakeItemSizeID(int cakeItemSizeID) {
        this.cakeItemSizeID = cakeItemSizeID;
    }


    public String getCakeItemMessage() {
        return cakeItemMessage;
    }

    public void setCakeItemMessage(String cakeItemMessage) {
        this.cakeItemMessage = cakeItemMessage;
    }

    public BigDecimal getCakeItemPrice() {
        return cakeItemPrice;
    }

    public void setCakeItemPrice(BigDecimal cakeItemPrice) {
        this.cakeItemPrice = cakeItemPrice;
    }

    public int getCakeItemFillingID() {
        return cakeItemFillingID;
    }

    public void setCakeItemFillingID(int cakeItemFillingID) {
        this.cakeItemFillingID = cakeItemFillingID;
    }

    public int getCakeItemFrostingID() {
        return cakeItemFrostingID;
    }

    public void setCakeItemFrostingID(int cakeItemFrostingID) {
        this.cakeItemFrostingID = cakeItemFrostingID;
    }

    public int getCakeItemFlavorID() {
        return cakeItemFlavorID;
    }

    public void setCakeItemFlavorID(int cakeItemFlavorID) {
        this.cakeItemFlavorID = cakeItemFlavorID;
    }


    public int getCakeItemConfigId() {
        return cakeItemConfigId;
    }

    public void setCakeItemConfigId(int cakeItemConfigId) {
        this.cakeItemConfigId = cakeItemConfigId;
    }

}
