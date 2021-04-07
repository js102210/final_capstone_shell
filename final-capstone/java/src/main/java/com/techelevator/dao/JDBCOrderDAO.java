package com.techelevator.dao;

import com.techelevator.model.CakeItemDTO;
import com.techelevator.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class JDBCOrderDAO  implements  OrderDAO{
    private JdbcTemplate jdbcTemplate;
    private CakeItemDAO cakeItemDAO;
    public JDBCOrderDAO(JdbcTemplate jdbcTemplate, CakeItemDAO cakeItemDAO){
        this.jdbcTemplate = jdbcTemplate;
        this.cakeItemDAO = cakeItemDAO;
    }


    @Override
    public void placeOrder(Order order) {
        String sqlToInsertOrder = "INSERT INTO orders (status_id, total_price, datetime_placed, datetime_delivery, " +
                "customer_name, customer_phone_number)\n" +
                "\tVALUES (1, ?, ?, ?, ?, ?) RETURNING order_id;";
        //TODO change the datetime representations when we get that whole thing figured out
       Integer newID = jdbcTemplate.queryForObject(sqlToInsertOrder, Integer.class, order.getOrderPriceTotal(), Timestamp.valueOf("2021-01-01 12:12:12"),

                Timestamp.valueOf("2021-01-01 12:12:12"),  order.getCustomerName(), order.getCustomerPhoneNumber());
        for (CakeItemDTO cakeItem : order.getItemsInOrder()){
            cakeItemDAO.addCakeItem(cakeItem, newID);
        }

    }
}
