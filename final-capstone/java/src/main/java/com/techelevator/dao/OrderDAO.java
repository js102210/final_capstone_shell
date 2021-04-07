package com.techelevator.dao;

import com.techelevator.model.Order;

public interface OrderDAO {
    /**
     * write an order of one or more cake items to the database
     * @param order order containing an array of cake items
     */
    void placeOrder(Order order);
}
