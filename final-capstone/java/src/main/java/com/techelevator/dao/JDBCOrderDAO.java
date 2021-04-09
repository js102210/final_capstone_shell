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
public class JDBCOrderDAO  implements  OrderDAO{
    private JdbcTemplate jdbcTemplate;
    private CakeItemDAO cakeItemDAO;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
    public JDBCOrderDAO(JdbcTemplate jdbcTemplate, CakeItemDAO cakeItemDAO){
        this.jdbcTemplate = jdbcTemplate;
        this.cakeItemDAO = cakeItemDAO;
    }


    @Override
    public Integer placeOrder(Order order) throws ParseException {
        String sqlToInsertOrder = "INSERT INTO orders (status_id, total_price, date_placed, pickup_date, pickup_time, customer_name, customer_phone_number)\n" +
                "\tVALUES (1, ?, CURRENT_DATE, ?, ?, ?, ?) RETURNING order_id;";
        //TODO change the datetime representations when we get that whole thing figured out
        Date pickupDate = dateFormat.parse(order.getOrderPickupDate());
       LocalTime pickupTime = LocalTime.parse(order.getOrderPickupTime(), timeFormat);
       Integer newID = jdbcTemplate.queryForObject(sqlToInsertOrder, Integer.class, order.getOrderPriceTotal(), pickupDate, pickupTime,order.getCustomerName(), order.getCustomerPhoneNumber());
        for (CakeItemDTO cakeItem : order.getItemsInOrder()){
            cakeItemDAO.addCakeItem(cakeItem, newID);
        }
        return newID;
    }

    @Override
    public List <Order> getAllOrders(){
        List<Order> result = new ArrayList <> ();
        String sqlGetAllOrders = "SELECT * FROM orders ORDER BY order_id;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet (sqlGetAllOrders);

        while(rowSet.next ()) {
            Order order = new Order();
            order.setOrderId (rowSet.getInt ("order_id"));
            order.setOrderStatus (rowSet.getString ("status_id"));
            order.setOrderPriceTotal (rowSet.getBigDecimal ("total_price"));
            order.setOrderDatePlaced (rowSet.getString ("date_placed"));
            order.setOrderPickupDate (rowSet.getString ("pickup_date"));
            order.setOrderPickupTime (rowSet.getString ("pickup_time"));
            order.setCustomerName (rowSet.getString ("customer_name"));
            order.setCustomerPhoneNumber (rowSet.getString ("customer_phone_number"));
        }
        return result;
    }
}
