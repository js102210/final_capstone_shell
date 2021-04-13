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
     * creates a new filling in the database from the filling JSON passed in by front end.
     * @param newFilling - the filling object created from the filling JSON passed in by front end.
     * @return - returns the id of the new filling.
     */
    int createFilling(Filling newFilling);

    /**
     * removes a Filling from the datastore. not implemented as it's out of scope.
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

    /**
     * flips the availability status of a filling. If true then it becomes false, and vice versa. Returns the
     * new value if is_available in the "fillings" table.
     * @param id - the id of the extra item
     * @return boolean - the updated boolean of the filling for is_available
     */
    boolean flipAvailability(int id);
}
