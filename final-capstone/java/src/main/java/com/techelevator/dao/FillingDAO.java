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
     * changes the availability status of a Filling
     * @param ID int ID of the Filling to change availability for
     * @return boolean the availability status of the Filling after the change
     */
    //note we're going to do this with a whole JSON object so this method probably doesn't make sense

    boolean flipAvailability(int ID);

    /**
     * removes a Filling from the datastore
     * @param ID int ID of the Filling to delete
     * @return String confirmation that the Filling was deleted (probably the name plus " was deleted."
     */
    String deleteFilling(int ID);

    void update(Filling filling);
}
