package com.techelevator.controller;

import com.techelevator.dao.CakeItemDAO;
import com.techelevator.dao.OrderDAO;
import com.techelevator.model.CakeItemDTO;
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
    private CakeItemDAO cakeItemDAO;

    public OrderController(OrderDAO orderDAO, CakeItemDAO cakeItemDAO) {
        this.cakeItemDAO = cakeItemDAO;
        this.orderDAO = orderDAO;
    }

    /**
     * place an order and all of its cake items into the database
     *
     * @param order Order object
     */
    @RequestMapping(path = "/placeOrder", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Integer addOrder(@RequestBody Order order) throws ParseException {
        return orderDAO.placeOrder (order);
    }


    // this needs to be rewritten - we are doing orders/status/{statusId} to update order status using the
    // PATCH method.
    @PreAuthorize ("isAuthenticated()")
    @RequestMapping(path = "orders/{id}", method = RequestMethod.PUT)
    public Order updateOrderStatus(@PathVariable int id, @RequestBody Order order) throws ParseException {
        orderDAO.updateOrder (order, id);
        return order;
    }

    /**
     * returns all orders. Employee only.
     * @return - gives a list of all order JSONs.
     */
    @PreAuthorize ("isAuthenticated()")
    @RequestMapping(path = "/orders", method = RequestMethod.GET)
    public List <Order> getAllOrders() {
        return orderDAO.getAllOrders ();
    }


    /**
     * gets the cakes for a particular Order.
     * @param orderID - the id of the order. Path Variable.
     * @return - returns a list of the order's cakeItemDTOs.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/orders/{orderID}/cakes", method = RequestMethod.GET)
    public List<CakeItemDTO> getOrdersCakes(@PathVariable int orderID){
        return cakeItemDAO.getCakeItemDTOsForOrder(orderID);
    }

    /**
     * Returns a list of Orders with the indicated status.
     * @param statusID = the statusID passed from the front end.
     * @return = returns a list of Orders with the indicated status.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/orders/status/{statusID}", method = RequestMethod.GET)
    public List<Order> getOrdersByStatus(@PathVariable int statusID) {
        return orderDAO.getOrdersByStatus(statusID);
    }

}