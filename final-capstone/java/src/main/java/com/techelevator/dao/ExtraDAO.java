package com.techelevator.dao;

import com.techelevator.model.Extra;

import java.math.BigDecimal;
import java.util.List;

public interface ExtraDAO {
    /**
     * gets all available Extras
     * @return List<Extra> containing all Extra items available
     */
    List <Extra> getAvailableExtras();

    /**
     * gets all Extras in database
     * @return List<Extra> containing all Extras from database
     */
    List<Extra> getAllExtras();

    /**
     * creates a new Extra in the database.
     * @param newExtra - the extra object made from the JSON passed from the front end
     * @return - returns the id of the new extra.
     */
    int createExtra(Extra newExtra);

    /**
     * make updates to an existing Extra
     * @param extra Extra the Extra to change, with all updated properties
     * @param extraID int ID of the Extra to change
     * @return Extra with all properties updated
     */
     Extra updateExtra(Extra extra, int extraID);

    /**
     * removes an Extra from the datastore
     * @param ID int ID of the Extra to delete
     * @return String confirmation that the Extra item was deleted (probably the name plus " was deleted.")
     */
    String deleteExtra(int ID);

    /**
     * flips the availability status of an extra. If true then it becomes false, and vice versa. Returns the
     * new value if is_available in the "extras" table.
     * @param id - the id of the extra item
     * @return boolean - the updated boolean of the extra for is_available
     */
    boolean flipAvailability(int id);

    /**
     * returns an Array of the Extras associated with a given Cake Item.
     * @param cakeItemID - the ID of the Cake Item.
     * @return - returns the Array of the Extras for the given Cake Item.
     */
    Extra[] getExtrasForCake(int cakeItemID);
}
