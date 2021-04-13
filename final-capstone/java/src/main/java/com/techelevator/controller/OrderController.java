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


    /**
     * Updates an order on all its parameters, including the its CakeItemDTOs and their associated Extras.
     * @param id the order ID of the order.
     * @param order the complete Order JSON.
     * @return returns the order itself.
     * @throws ParseException
     */
    @PreAuthorize ("isAuthenticated()")
    @RequestMapping(path = "orders/{id}", method = RequestMethod.PUT)
    public Order updateOrder(@PathVariable int id, @RequestBody Order order) throws ParseException {
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

    /**
     * Updates the order status for the indicated order. This is not strictly RESTful design but updating the entire order
     * as in updateOrder above is a much more intensive drain on system resources, touching up to three different tables.
     * @param orderID - the ID of the order to have its status updated
     * @param statusID - the new statusID for the order
     * @return - returns the int of the new statusID for the order.
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/orders/{orderID}/status/{statusID}", method = RequestMethod.PATCH)
    public int updateOrderStatus(@PathVariable int orderID, @PathVariable int statusID){
        orderDAO.changeOrderStatus(orderID, statusID);
        return statusID;
    }

}