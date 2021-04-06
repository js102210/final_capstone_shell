package com.techelevator.model;

public class CakeConfig {
    private int cakeConfigId;
    private String cakeConfigName;
    private String cakeConfigUrl;
    private String cakeConfigDescription;
    private String cakeConfigFilling;
    private String cakeConfigFrosting;
    private String cakeConfigFlavor;

    /**
     * Constructor for CakeConfig, should be passed into cakeItems as an arg, can also be used to populate menu
     * Standard configs will query from DB to map properties, 'custom' config will set properties by user input
     * @param cakeConfigId int
     * @param cakeConfigName String
     * @param cakeConfigUrl String
     * @param cakeConfigDescription String
     * @param cakeConfigFilling String
     * @param cakeConfigFrosting String
     * @param cakeConfigFlavor Sting
     */
    public CakeConfig(int cakeConfigId, String cakeConfigName, String cakeConfigUrl, String cakeConfigDescription, String cakeConfigFilling,
     String cakeConfigFrosting, String cakeConfigFlavor){
        this.cakeConfigId = cakeConfigId;
        this.cakeConfigName = cakeConfigName;
        this.cakeConfigUrl = cakeConfigUrl;
        this.cakeConfigDescription = cakeConfigDescription;
        this.cakeConfigFilling = cakeConfigFilling;
        this.cakeConfigFrosting = cakeConfigFrosting;
        this.cakeConfigFlavor = cakeConfigFlavor;

    }


    public int getCakeConfigId() {
        return cakeConfigId;
    }

    public void setCakeConfigId(int cakeConfigId) {
        this.cakeConfigId = cakeConfigId;
    }

    public String getCakeConfigName() {
        return cakeConfigName;
    }

    public void setCakeConfigName(String cakeConfigName) {
        this.cakeConfigName = cakeConfigName;
    }

    public String getCakeConfigUrl() {
        return cakeConfigUrl;
    }

    public void setCakeConfigUrl(String cakeConfigUrl) {
        this.cakeConfigUrl = cakeConfigUrl;
    }

    public String getCakeConfigDescription() {
        return cakeConfigDescription;
    }

    public void setCakeConfigDescription(String cakeConfigDescription) {
        this.cakeConfigDescription = cakeConfigDescription;
    }

    public String getCakeConfigFilling() {
        return cakeConfigFilling;
    }

    public void setCakeConfigFilling(String cakeConfigFilling) {
        this.cakeConfigFilling = cakeConfigFilling;
    }

    public String getCakeConfigFrosting() {
        return cakeConfigFrosting;
    }

    public void setCakeConfigFrosting(String cakeConfigFrosting) {
        this.cakeConfigFrosting = cakeConfigFrosting;
    }

    public String getCakeConfigFlavor() {
        return cakeConfigFlavor;
    }

    public void setCakeConfigFlavor(String cakeConfigFlavor) {
        this.cakeConfigFlavor = cakeConfigFlavor;
    }

}
