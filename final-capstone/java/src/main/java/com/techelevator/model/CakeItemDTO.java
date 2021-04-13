package com.techelevator.model;

import java.math.BigDecimal;

//this will be our cake object
public class CakeItemDTO {
    private int cakeItemID;
    private int cakeItemStyleID;
    private int cakeItemSizeID;
    private String cakeItemMessage = "";
    private int cakeItemFillingID;
    private int cakeItemFrostingID;
    private int cakeItemFlavorID;
    private Extra[] cakeItemExtras;
    private int cakeItemConfigID;
    private int cakeItemOrderID;
    private BigDecimal cakeItemPrice;

    /**
     * blank constructor for CakeItemDTO.
     */
    public CakeItemDTO(){};

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
     * @param cakeItemOrderID int
     */
    public CakeItemDTO(int itemStyle, int itemSize, String itemMessage, BigDecimal itemPrice,
                       int cakeItemFilling, int cakeItemFrosting, int cakeItemFlavor, int cakeItemOrderID){

        this.cakeItemSizeID = itemSize;
        this.cakeItemStyleID = itemStyle;
        this.cakeItemMessage = itemMessage;
        this.cakeItemPrice = itemPrice;
        this.cakeItemFillingID = cakeItemFilling;
        this.cakeItemFrostingID = cakeItemFrosting;
        this.cakeItemFlavorID = cakeItemFlavor;
        this.cakeItemOrderID = cakeItemOrderID;
    }

    public int getCakeItemID() {
        return cakeItemID;
    }

    public void setCakeItemID(int cakeItemID) {
        this.cakeItemID = cakeItemID;
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

    public void setCakeItemExtras(Extra[] cakeItemExtras){ this.cakeItemExtras = cakeItemExtras; }

    public Extra[] getCakeItemExtras() { return cakeItemExtras; }

    public int getCakeItemConfigID() {
        return cakeItemConfigID;
    }

    public void setCakeItemConfigID(int cakeItemConfigID) {
        this.cakeItemConfigID = cakeItemConfigID;
    }

    public int getCakeItemOrderID(){
        return cakeItemOrderID;
    }

    public void setCakeItemOrderID(int cakeItemOrderID){
        this.cakeItemOrderID = cakeItemOrderID;
    }
}
