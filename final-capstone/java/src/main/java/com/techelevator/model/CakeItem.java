package com.techelevator.model;

import java.math.BigDecimal;

//this will be our cake object
public class CakeItem {
    private int cakeItemId;
    private String cakeItemStyle;
    private String cakeItemSize;
    private String cakeItemMessage = "";
    private String cakeItemFilling;
    private String cakeItemFrosting;
    private String cakeItemFlavor;
    private BigDecimal cakeItemPrice;

    /**
     * constructor for cake item
     * @param itemId int
     * @param itemStyle String
     * @param itemSize String
     * @param itemMessage String
     * @param itemPrice BigDecimal
     * @param cakeItemFilling String
     * @param cakeItemFrosting String
     * @param cakeItemFlavor String
     */
    public CakeItem(int itemId, String itemStyle, String itemSize, String itemMessage, BigDecimal itemPrice,
                    String cakeItemFilling, String cakeItemFrosting, String cakeItemFlavor){
        this.cakeItemId = itemId;
        this.cakeItemSize = itemSize;
        this.cakeItemStyle = itemStyle;
        this.cakeItemMessage = itemMessage;
        this.cakeItemPrice = itemPrice;
        this.cakeItemFilling = cakeItemFilling;
        this.cakeItemFrosting = cakeItemFrosting;
        this.cakeItemFlavor = cakeItemFlavor;
    }

    public int getCakeItemId() {
        return cakeItemId;
    }

    public void setCakeItemId(int cakeItemId) {
        this.cakeItemId = cakeItemId;
    }

    public String getCakeItemStyle() {
        return cakeItemStyle;
    }

    public void setCakeItemStyle(String cakeItemStyle) {
        this.cakeItemStyle = cakeItemStyle;
    }

    public String getCakeItemSize() {
        return cakeItemSize;
    }

    public void setCakeItemSize(String cakeItemSize) {
        this.cakeItemSize = cakeItemSize;
    }

    public String getCakeMessage() {
        return cakeItemMessage;
    }

    public void setCakeMessage(String cakeMessage) {
        this.cakeItemMessage = cakeMessage;
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

    public String getCakeItemFilling() {
        return cakeItemFilling;
    }

    public void setCakeItemFilling(String cakeItemFilling) {
        this.cakeItemFilling = cakeItemFilling;
    }

    public String getCakeItemFrosting() {
        return cakeItemFrosting;
    }

    public void setCakeItemFrosting(String cakeItemFrosting) {
        this.cakeItemFrosting = cakeItemFrosting;
    }

    public String getCakeItemFlavor() {
        return cakeItemFlavor;
    }

    public void setCakeItemFlavor(String cakeItemFlavor) {
        this.cakeItemFlavor = cakeItemFlavor;
    }

}
