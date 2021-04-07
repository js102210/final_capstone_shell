package com.techelevator.dao;

import com.techelevator.model.CakeItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class JDBCCakeItemDAO implements CakeItemDAO{
    private JdbcTemplate jdbcTemplate;
    public JDBCCakeItemDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<CakeItem> getCakeItemsForOrder(int orderId) {
        return null;
    }

    @Override
    public void addCakeItem(CakeItem cakeItem, int orderID) {

    }
}
