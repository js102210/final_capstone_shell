package com.techelevator.dao;

import com.techelevator.model.CakeItemDTO;

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
}
