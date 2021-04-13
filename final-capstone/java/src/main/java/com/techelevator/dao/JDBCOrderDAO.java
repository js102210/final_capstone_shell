package com.techelevator.dao;

import com.techelevator.model.CakeItemDTO;
import com.techelevator.model.Order;
import org.apache.tomcat.jni.Local;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JDBCOrderDAO implements OrderDAO {
    private JdbcTemplate jdbcTemplate;
    private CakeItemDAO cakeItemDAO;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

    public JDBCOrderDAO(JdbcTemplate jdbcTemplate, CakeItemDAO cakeItemDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.cakeItemDAO = cakeItemDAO;
    }


    @Override
    public Integer placeOrder(Order order) throws ParseException {
        String sqlToInsertOrder = "INSERT INTO orders (status_id, total_price, date_placed, pickup_date, pickup_time, customer_name, customer_phone_number)\n" +
                "\tVALUES (1, ?, CURRENT_DATE, ?, ?, ?, ?) RETURNING order_id;";
        Date pickupDate = dateFormat.parse(order.getOrderPickupDate());
        LocalTime pickupTime = LocalTime.parse(order.getOrderPickupTime(), timeFormat);
        Integer newOrderID = jdbcTemplate.queryForObject(sqlToInsertOrder, Integer.class, order.getOrderPriceTotal(),
                pickupDate, pickupTime, order.getCustomerName(), order.getCustomerPhoneNumber());
        //puts each cake item in order into the database.
        for (CakeItemDTO cakeItem : order.getItemsInOrder()) {
            cakeItemDAO.addCakeItem(cakeItem, newOrderID);
        }
        return newOrderID;
    }

    @Override
    public List<Order> getAllOrders() {

        //gets the basic order objects, without the cakeItems
        String sqlGetAllOrders = "SELECT * FROM orders ORDER BY order_id;";
        List<Order> allOrders = new ArrayList<>();
        SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetAllOrders);
        //map result rows to Orders
        while (result.next()) {
            Order order = mapRowToOrder(result);
            allOrders.add(order);
        }

        //for each Order, grabs its CakeItemDTOs as a list, casts the list to an array, then sets it with SetItemsInOrder
        for (Order order : allOrders) {
            int orderID = order.getOrderID();
            List<CakeItemDTO> orderCakeItems= cakeItemDAO.getCakeItemDTOsForOrder(orderID);
            CakeItemDTO[] cakeItemArray = orderCakeItems.toArray(new CakeItemDTO[0]);
            order.setItemsInOrder(cakeItemArray);
        }
            return allOrders;

    }

    @Override
    public List<Order> getOrdersByStatus(int statusID) {

        //gets the basic order objects, without the cakeItems
        String sqlGetAllOrders = "SELECT * FROM orders WHERE status_id = ? ORDER BY order_id ;";
        List<Order> allOrders = new ArrayList<>();
        SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetAllOrders, statusID);
        //map result rows to Orders
        while (result.next()) {
            Order order = mapRowToOrder(result);
            allOrders.add(order);
        }

        //for each Order, grabs its CakeItemDTOs as a list, casts the list to an array, then sets it with SetItemsInOrder
        for (Order order : allOrders) {
            int orderID = order.getOrderID();
            List<CakeItemDTO> orderCakeItems= cakeItemDAO.getCakeItemDTOsForOrder(orderID);
            CakeItemDTO[] cakeItemArray = orderCakeItems.toArray(new CakeItemDTO[0]);
            order.setItemsInOrder(cakeItemArray);
        }
        return allOrders;

    }


    @Override
    public Order updateOrder(Order order, int orderId) throws ParseException {

        //formats pickupDate and pickupTime correctly for Postgres
        Date pickupDate = dateFormat.parse(order.getOrderPickupDate());
        LocalTime pickupTime = LocalTime.parse(order.getOrderPickupTime(), timeFormat);
        //update order on orders table.
        String sqlUpdateOrderStatus =
                "UPDATE orders \n" +
                        "SET status_id = ?,\n" +
                        "total_price = ?,\n" +
                        "pickup_date = ?,\n" +
                        "pickup_time = ?,\n" +
                        "customer_name = ?,\n" +
                        "customer_phone_number = ?,\n" +
                        "date_last_updated = CURRENT_DATE\n" +
                        "WHERE order_id = ?;";
        jdbcTemplate.update(sqlUpdateOrderStatus, order.getOrderStatusID(), order.getOrderPriceTotal(),
                pickupDate, pickupTime, order.getCustomerName(), order.getCustomerPhoneNumber(), order.getOrderID()
        );
        //updates the individual CakeItems. the updateCakeItem method will also update the cake_items_extras table.
        for(CakeItemDTO theCakeItem: order.getItemsInOrder()){
            cakeItemDAO.updateCakeItem(theCakeItem, orderId);
        }

        return order;

    }

    @Override
    public void changeOrderStatus(int orderID, int statusID){
        String sqlUpdateOrderStatusStatement = "UPDATE orders SET status_id = ? WHERE order_id = ? ;";
        jdbcTemplate.update(sqlUpdateOrderStatusStatement, statusID, orderID);
    }



    public Order mapRowToOrder(SqlRowSet result) {
        Order order = new Order();
        order.setOrderID(result.getInt("order_id"));
        order.setOrderStatusID(result.getInt("status_id"));
        order.setOrderPriceTotal(result.getBigDecimal("total_price"));
        order.setOrderDatePlaced(result.getString("date_placed"));
        order.setOrderPickupDate(result.getString("pickup_date"));
        order.setOrderPickupTime(result.getString("pickup_time"));
        order.setCustomerName(result.getString("customer_name"));
        order.setCustomerPhoneNumber(result.getString("customer_phone_number"));
        return order;

    }

}


//   just in case needed for later

//    private OrderDTO getOrderByOrderID(int orderID) {
//        String sqlGetOrderByID = "SELECT * FROM orders WHERE order_id = ?;";
//        SqlRowSet rowSet = jdbcTemplate.queryForRowSet (sqlGetOrderByID, orderID);
//        OrderDTO orderDTO = new OrderDTO;
//        rowSet.next();
//        order.setOrderID (rowSet.getInt ("order_id"));
//                    order.setOrderStatus (rowSet.getInt ("status_id"));
//                    order.setOrderPriceTotal (rowSet.getBigDecimal ("total_price"));
//                    order.setOrderDatePlaced (rowSet.getString ("date_placed"));
//                    order.setOrderPickupDate (rowSet.getString ("pickup_date"));
//                    order.setOrderPickupTime (rowSet.getString ("pickup_time"));
//                    order.setCustomerName (rowSet.getString ("customer_name"));
//                    order.setCustomerPhoneNumber (rowSet.getString ("customer_phone_number"));


//                    Order order = new Order();
//                    order.setOrderID (rowSet.getInt ("order_id"));
//                    order.setOrderStatus (rowSet.getInt ("status_id"));
//                    order.setOrderPriceTotal (rowSet.getBigDecimal ("total_price"));
//                    order.setOrderDatePlaced (rowSet.getString ("date_placed"));
//                    order.setOrderPickupDate (rowSet.getString ("pickup_date"));
//                    order.setOrderPickupTime (rowSet.getString ("pickup_time"));
//                    order.setCustomerName (rowSet.getString ("customer_name"));
//                    order.setCustomerPhoneNumber (rowSet.getString ("customer_phone_number"));