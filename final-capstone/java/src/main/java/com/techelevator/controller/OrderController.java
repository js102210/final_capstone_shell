package com.techelevator.controller;
import com.techelevator.dao.OrderDAO;
import com.techelevator.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin
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
    @ResponseStatus (HttpStatus.CREATED)
    public Integer addOrder(@RequestBody Order order) throws ParseException {
       return orderDAO.placeOrder (order);
    }

//  @PreAuthorize ("employee")
<<<<<<< HEAD
    @RequestMapping(value = "orders/{id}", method = RequestMethod.PUT)
    public void updateOrderStatus (@PathVariable int id, @RequestBody Order order) {
=======
    @PutMapping("/orders/{id}")
    public void updateOrderStatus (@PathVariable int orderID, @RequestBody Order order) {
>>>>>>> 782a05cf3e3ac2d134777e54451760dacea74f52
        orderDAO.updateOrderStatus(order);
    }

    @RequestMapping("/orders")
    public List <Order> getAllOrders() {
        return orderDAO.getAllOrders ();
    }



}