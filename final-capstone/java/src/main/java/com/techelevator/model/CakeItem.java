package com.techelevator.model;

import java.math.BigDecimal;

//this will be our cake object
public class CakeItem {
    private int cakeItemId;
    private String cakeItemStyle;
    private String cakeItemSize;
    private String cakeItemMessage = "";
    private CakeConfig cakeItemConfig;
    private BigDecimal cakeItemPrice;

    /**
     * Constructor for cake item
     * Note- other properties of cake item should be read from the config passed as an arg
     * @param itemId int
     * @param itemConfig CakeConfig
     * query should correctly populate with properties, from the DB config if it's a standard cake,
     *  or from the input if the config is 'custom'
     * @param itemStyle String
     * @param itemSize String
     * @param  itemMessage String
     * defaults to empty string from db if not provided on insert
     * @param itemPrice BigDecimal
     */
    public CakeItem(int itemId, CakeConfig itemConfig, String itemStyle, String itemSize, String itemMessage, BigDecimal itemPrice){
        this.cakeItemId = itemId;
        this.cakeItemConfig = itemConfig;
        this.cakeItemSize = itemSize;
        this.cakeItemStyle = itemStyle;
        this.cakeItemMessage = itemMessage;
        this.cakeItemPrice = itemPrice;
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

    public CakeConfig getCakeItemConfig() {
        return cakeItemConfig;
    }

    public void setCakeItemConfig(CakeConfig cakeItemConfig) {
        this.cakeItemConfig = cakeItemConfig;
    }



}
