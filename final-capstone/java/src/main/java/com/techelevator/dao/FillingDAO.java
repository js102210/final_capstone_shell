package com.techelevator.dao;

import com.techelevator.model.Filling;


import java.math.BigDecimal;
import java.util.List;

public interface FillingDAO {
    /**
     * gets all Fillings that are available
     * @return List<Filling> containing all Flavors available
     */
    List<Filling> getAvailableFillings();

    /**
     * gets all Fillings in database
     * @return List<Filling> containing all Flavors from database
     */
    List<Filling> getAllFillings();

    /**
     * inserts a new Flavor in the datastore
     * @param FillingName String name of the Filling
     * @param priceMod BigDecimal how much the Filling
     * @return int ID of the newly created Filling
     */
    int createFilling(String FillingName, BigDecimal priceMod);



    /**
     * removes a Filling from the datastore
     * @param ID int ID of the Filling to delete
     * @return String confirmation that the Filling was deleted (probably the name plus " was deleted."
     */
    String deleteFilling(int ID);

    /**
     * make updates to an existing Filling
     * @param filling filling the size to change, with all updated properties
     * @param fillingID int ID of the Size to change
     * @return filling with all properties updated
     */
    Filling updateFilling(Filling filling, int fillingID);
}
