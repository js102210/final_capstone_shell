package com.techelevator.model;

public class CakeConfig {
    private int cakeConfigId;
    private String cakeConfigName;
    private String cakeConfigUrl;
    private String cakeConfigDescription;
    private int cakeConfigFillingID;
    private int cakeConfigFrostingID;
    private int cakeConfigFlavorID;

    /**
     * Constructor for an existing CakeConfig
     * @param cakeConfigId int
     * @param cakeConfigName String
     * @param cakeConfigUrl String
     * @param cakeConfigDescription String
     * @param cakeConfigFillingID int
     * @param cakeConfigFrostingID int
     * @param cakeConfigFlavorID int
     */
    public CakeConfig(int cakeConfigId, String cakeConfigName, String cakeConfigUrl, String cakeConfigDescription, int cakeConfigFillingID,
     int cakeConfigFrostingID, int cakeConfigFlavorID){
        this.cakeConfigId = cakeConfigId;
        this.cakeConfigName = cakeConfigName;
        this.cakeConfigUrl = cakeConfigUrl;
        this.cakeConfigDescription = cakeConfigDescription;
        this.cakeConfigFillingID = cakeConfigFillingID;
        this.cakeConfigFrostingID = cakeConfigFrostingID;
        this.cakeConfigFlavorID = cakeConfigFlavorID;

    }

    /**
     * Constructor for adding instantiating a new Cake Config in memory to add it to the datastore, where it will be assigned an id
     * @param cakeConfigName String
     * @param cakeConfigUrl String
     * @param cakeConfigDescription String
     * @param cakeConfigFillingID int
     * @param cakeConfigFrostingID int
     * @param cakeConfigFlavorID int
     */
    public CakeConfig(String cakeConfigName, String cakeConfigUrl, String cakeConfigDescription, int cakeConfigFillingID,
                      int cakeConfigFrostingID, int cakeConfigFlavorID){
        this.cakeConfigName = cakeConfigName;
        this.cakeConfigUrl = cakeConfigUrl;
        this.cakeConfigDescription = cakeConfigDescription;
        this.cakeConfigFillingID = cakeConfigFillingID;
        this.cakeConfigFrostingID = cakeConfigFrostingID;
        this.cakeConfigFlavorID = cakeConfigFlavorID;
    }
    public CakeConfig(){

    };


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

    public int getCakeConfigFillingID() {
        return cakeConfigFillingID;
    }

    public void setCakeConfigFillingID(int cakeConfigFillingID) {
        this.cakeConfigFillingID = cakeConfigFillingID;
    }

    public int getCakeConfigFrostingID() {
        return cakeConfigFrostingID;
    }

    public void setCakeConfigFrostingID(int cakeConfigFrostingID) {
        this.cakeConfigFrostingID = cakeConfigFrostingID;
    }

    public int getCakeConfigFlavorID() {
        return cakeConfigFlavorID;
    }

    public void setCakeConfigFlavorID(int cakeConfigFlavorID) {
        this.cakeConfigFlavorID = cakeConfigFlavorID;
    }

}
