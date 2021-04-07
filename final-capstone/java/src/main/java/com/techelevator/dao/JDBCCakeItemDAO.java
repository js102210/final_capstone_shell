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
    String sqlToAddCakeItem = "INSERT INTO cake_items (cake_style_id, cake_size_id, flavor_id, frosting_id, " +
            "filling_id, message, config_id,\n" +
            "item_price, order_id)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    jdbcTemplate.update(sqlToAddCakeItem, cakeItem.getCakeItemStyleID(), cakeItem.getCakeItemSizeID(), cakeItem.getCakeItemFlavorID(),
            cakeItem.getCakeItemFrostingID(), cakeItem.getCakeItemFillingID(), cakeItem.getCakeItemMessage(), cakeItem.getCakeItemConfigId(),
            cakeItem.getCakeItemPrice(), orderID);
    }
}
