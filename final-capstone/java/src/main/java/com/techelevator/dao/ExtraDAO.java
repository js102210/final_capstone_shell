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
     * inserts a new Extra item in the datastore
     * @param extraName String name of the Extra
     * @param priceMod BigDecimal price of the Extra item
     * @return int ID of the newly created Extra item
     */
    int createExtra(String extraName, BigDecimal priceMod);

    /**
     * make updates to an existing Extra
     * @param extra Extra the Extra to change, with all updated properties
     * @param ID int ID of the Extra to change
     * @return Extra with all properties updated
     */
     Extra updateExtra(Extra extra, int ID);

    /**
     * removes an Extra from the datastore
     * @param ID int ID of the Extra to delete
     * @return String confirmation that the Extra item was deleted (probably the name plus " was deleted."
     */
    String deleteExtra(int ID);
}
