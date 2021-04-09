package com.techelevator.dao;

import com.techelevator.model.Order;

import java.text.ParseException;
import java.util.List;

public interface OrderDAO {
    /**
     * write an order of one or more cake items to the database
     * @param order order containing an array of cake items
     * @return Integer id of the new order
     */
   Integer placeOrder(Order order) throws ParseException;
//    List <Order> getAllOrders();

}
