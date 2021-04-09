package com.techelevator.controller;
import com.techelevator.dao.OrderDAO;
import com.techelevator.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
public class OrderController {
    private OrderDAO orderDAO;
    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
    /**
     * place an order and all of its cake items into the database
     * @param order Order object
     */
    @RequestMapping( path= "/placeOrder", method = RequestMethod.POST)
    @ResponseStatus (value= HttpStatus.CREATED)
    public Integer addOrder(@RequestBody Order order) throws ParseException {
       return orderDAO.placeOrder (order);
    }

}