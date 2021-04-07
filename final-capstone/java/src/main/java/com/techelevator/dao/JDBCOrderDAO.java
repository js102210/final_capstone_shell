package com.techelevator.dao;

import com.techelevator.model.CakeItem;
import com.techelevator.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
                "\tVALUES (1, ?, ?, ?, ?, ?);";
        //TODO change the datetime representations when we get that whole thing figured out
        jdbcTemplate.update(sqlToInsertOrder, order.getOrderPriceTotal(), "2021-04-06 01:01:01",
                "2021-04-06 01:01:01",  order.getCustomerName(), order.getCustomerPhoneNumber());
        for (CakeItem cakeItem : order.getItemsInOrder()){
            cakeItemDAO.addCakeItem(cakeItem, order.getOrderId());
        }
        
    }
}
