package com.techelevator.controller;

import com.techelevator.dao.OrderDAO;
import com.techelevator.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD

@RestController
public class OrderController {
    private OrderDAO orderDAO;

    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    /**
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
    @ResponseStatus (value= HttpStatus.CREATED)
    public void addOrder(@RequestBody Order order) {
        orderDAO.placeOrder (order);
    }
}
