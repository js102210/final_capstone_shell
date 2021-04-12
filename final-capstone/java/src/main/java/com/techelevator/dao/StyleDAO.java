package com.techelevator.dao;

import com.techelevator.model.Style;
import java.util.List;

public interface StyleDAO {
    /**
     * gets all available Styles
     * @return List<Style> containing all available Styles
     */
    List <Style> getAvailableStyles();

    /**
     * gets all Styles in database
     * @return List<Style> containing all Styles in database
     */
    List<Style> getAllStyles();

    /**
     * make updates to an existing Style
     * @param style the Style to change, with all updated properties
     * @param styleID int ID of the Style to change
     * @return Style with all properties updated
     */
    Style updateStyle(Style style, int styleID);

    /**
     * inserts a new Style into the datastore
     * @param newStyle the new style as a Style object
     * @return int ID of the new Style
     */
    int createStyle(Style newStyle);

    /**
     * changes the availability status of a Style
     * @param ID int ID of the Style changing availability
     * @return boolean for the availability status of the Style after the change
     */
    boolean flipAvailability(int ID);

    /**
     * removes a Style from the datastore
     * @param id int ID of the Style to delete
     */
    void deleteStyle(int id);
}
