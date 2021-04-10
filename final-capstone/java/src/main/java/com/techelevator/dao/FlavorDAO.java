package com.techelevator.dao;

import com.techelevator.model.Flavor;

import java.math.BigDecimal;
import java.util.List;

public interface FlavorDAO {
    /**
     * gets all Flavors that are available
     * @return List<Flavor> containing all Flavors available
     */
    List<Flavor> getAvailableFlavors();

    /**
     * gets all Flavors in database
     * @return List<Flavor> containing all Flavors from database
     */
    List<Flavor> getAllFlavors();

    /**
     * inserts a new Flavor in the datastore
     * @param flavorName String name of the Flavor
     * @param priceMod BigDecimal how much the Flavor
     * @return int ID of the newly created Flavor
     */
    int createFlavor(String flavorName, BigDecimal priceMod);

    /**
     * changes the availability status of a Flavor
     * @param ID int ID of the Flavor to change availability for
     * @return boolean the availability status of the Flavor after the change
     */

    //note possibly change this
    boolean flipAvailability(int ID);

    /**
     * removes a Flavor from the datastore
     * @param ID int ID of the Flavor to delete
     * @return String confirmation that the Flavor was deleted (probably the name plus " was deleted."
     */
    String deleteFlavor(int ID);
}
