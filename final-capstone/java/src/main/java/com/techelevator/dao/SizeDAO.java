package com.techelevator.dao;

import com.techelevator.model.Size;

import java.math.BigDecimal;
import java.util.List;

public interface SizeDAO {
    /**
     * gets available sizes
     * @return List<Sizes> for all available sizes
     */
    List <Size> getAvailableSizes();

    /**
     * gets all Flavors in database
     * @return List<Flavor> containing all Flavors from database
     */
    List <Size> getAllSizes();

    /**
     * Creates a new Size. Employee only.
     * @param newSize - the size JSON for the new size
     * @return - returns the ID of the new size.
     */
    int createSize(Size newSize);

    /**
     * make updates to an existing Size
     * @param size   Size the size to change, with all updated properties
     * @param sizeID int ID of the Size to change
     * @return Size with all properties updated
     */
    Size updateSize(Size size, int sizeID);

    /**
     * removes a Size from the datastore. not fully implemented because of scope.
     * @param id int ID of the Size to delete
     */
    void deleteSize(int id);

    /**
     * changes the availability status of a Size.
     * @param id int ID of the Size changing availability
     * @return boolean for the availability status of the Size after the change
     */
    boolean flipAvailability(int id);


}
