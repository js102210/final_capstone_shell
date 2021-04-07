package com.techelevator.controller;

import com.techelevator.dao.OrderDAO;
import com.techelevator.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
=======
<<<<<<< HEAD

>>>>>>> c0dff22c3b85235e3b023610f3772359f78265b3
@RestController
public class OrderController {
    private OrderDAO orderDAO;
    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    /**
<<<<<<< HEAD
     * place an order and all of its cake items into the database
     * @param order Order object
     */
    @RequestMapping( path= "/placeOrder", method = RequestMethod.POST)
=======
     *
     */
    @RequestMapping( path= "/placeorder", method = RequestMethod.POST)
=======
@RestController
public class OrderController {
    private OrderDAO orderDAO;
    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
    /**
     *
     */
    @RequestMapping( path= "/placeOrder", method = RequestMethod.POST)
>>>>>>> 96ea632251e2be4fbdb3acdf8e5c4d7827c8eb95
>>>>>>> c0dff22c3b85235e3b023610f3772359f78265b3
    @ResponseStatus (value= HttpStatus.CREATED)
    public void addOrder(@RequestBody Order order) {
        orderDAO.placeOrder (order);
    }
}
