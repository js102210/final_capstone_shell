package com.techelevator.dao;

import com.techelevator.model.CakeItemDTO;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public interface CakeItemDAO {
    /**
     * Returns a list of the cake items in a given order
     * @param orderId int
     * id for the order
     * @return List<CakeItem>
     */
    List<CakeItemDTO> getCakeItemsForOrder(int orderId);

    /**
     * write a cake item from an order to the database
     * @param cakeItem cakeItem
     * @param orderID int
     */
    void addCakeItem(CakeItemDTO cakeItem, int orderID);

    /**
     * Maps a row from a SQL search of cake_items table into a CakeItemDTO object.
     * @param result - a SQLRowSet from a query
     * @return = returns the CakeItemDTO.
     */
    CakeItemDTO mapRowToCakeItemDTO(SqlRowSet result);

    /**
     * returns a list of Cake Item DTOs associated with an order
     * @param orderID - the id of the order
     * @return - returns a list of CakeItemDTOs
     */
    List<CakeItemDTO> getCakeItemDTOsForOrder(int orderID);
}
