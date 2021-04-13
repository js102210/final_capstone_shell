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
     * creates a new flavor from a flavor JSON
     * @param newFlavor - the flavor JSON from front end
     * @return - returns the id of the new flavor
     */
    int createFlavor(Flavor newFlavor);

     /**
     * flips the availability status of a flavor. If true then it becomes false, and vice versa. Returns the
     * new value if is_available in the "flavors" table.
     * @param ID - the id of the flavor item
     * @return boolean - the updated boolean of the flavor for is_available
     */
    boolean flipAvailability(int ID);

    /**
     * removes a Flavor from the datastore. Not implemented due to current scope.
     * @param ID int ID of the Flavor to delete
     * @return String confirmation that the Flavor was deleted (probably the name plus " was deleted."
     */
    String deleteFlavor(int ID);

    /**
     * make updates to an existing Flavor
     * @param flavor the Flavor to change, with all updated properties
     * @param flavor of the Flavor to change
     * @return Flavor with all properties updated
     */
    Flavor updateFlavor(Flavor flavor, int id);


}
