package com.techelevator.dao;

import com.techelevator.model.CakeItemDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JDBCCakeItemDAO implements CakeItemDAO {
    private JdbcTemplate jdbcTemplate;

    public JDBCCakeItemDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List <CakeItemDTO> getCakeItemsForOrder(int orderId) {
        return null;
    }

    @Override
    public void addCakeItem(CakeItemDTO cakeItem, int orderID) {
        String sqlToAddCakeItem = "INSERT INTO cake_items (cake_style_id, cake_size_id, flavor_id, frosting_id, " +
                "filling_id, message, config_id,\n" +
                "item_price, order_id)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update (sqlToAddCakeItem, cakeItem.getCakeItemStyleID (), cakeItem.getCakeItemSizeID (), cakeItem.getCakeItemFlavorID (),
                cakeItem.getCakeItemFrostingID (), cakeItem.getCakeItemFillingID (), cakeItem.getCakeItemMessage (), cakeItem.getCakeItemConfigID (),
                cakeItem.getCakeItemPrice (), orderID
        );
    }

    public CakeItemDTO mapRowToCakeItemDTO(SqlRowSet result){
        CakeItemDTO theCakeItemDTO = new CakeItemDTO();
        theCakeItemDTO.setCakeItemID(result.getInt("cake_item_id"));
        theCakeItemDTO.setCakeItemStyleID(result.getInt("cake_style_id"));
        theCakeItemDTO.setCakeItemSizeID(result.getInt("cake_size_id"));
        theCakeItemDTO.setCakeItemFlavorID(result.getInt("flavor_id"));
        theCakeItemDTO.setCakeItemFrostingID(result.getInt("frosting_id"));
        theCakeItemDTO.setCakeItemMessage(result.getString("message"));
        theCakeItemDTO.setCakeItemConfigID(result.getInt("config_id"));
        theCakeItemDTO.setCakeItemPrice(result.getBigDecimal("item_price"));
        theCakeItemDTO.setCakeItemOrderID(result.getInt("order_id"));
        return theCakeItemDTO;
    }
}
