package com.techelevator.dao;

import com.techelevator.model.CakeItemDTO;
import com.techelevator.model.Extra;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCCakeItemDAO implements CakeItemDAO {
    private JdbcTemplate jdbcTemplate;
    private ExtraDAO extraDAO;

    public JDBCCakeItemDAO(JdbcTemplate jdbcTemplate, ExtraDAO extraDAO) {
        this.extraDAO = extraDAO;
        this.jdbcTemplate = jdbcTemplate;
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
        //implement adding the array of Extras into the table
        for (Extra theExtra: cakeItem.getCakeItemExtras()){
            String sqlAddExtraToCakeItem = "INSERT INTO cake_item_extras (cake_item_id, extra_id) " +
                    "VALUES (?, ?) ;";
            jdbcTemplate.update(sqlAddExtraToCakeItem, cakeItem.getCakeItemID(), theExtra.getExtraID());
        }
    }

    @Override
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

    @Override
    public List<CakeItemDTO> getCakeItemDTOsForOrder(int orderID){
        String sqlCakeItemDTOQuery = "SELECT * FROM cake_items WHERE order_id = ? ;";
        List<CakeItemDTO> orderCakeItems = new ArrayList<>();
        SqlRowSet result = jdbcTemplate.queryForRowSet(sqlCakeItemDTOQuery, orderID);
        while(result.next()){
            CakeItemDTO theCakeItemDTO = mapRowToCakeItemDTO(result);
            orderCakeItems.add(theCakeItemDTO);
        }
        //grab extras for cakeItems using ExtraDAO method.
        for(CakeItemDTO theCake: orderCakeItems){
            int theCakeID = theCake.getCakeItemID();
            theCake.setCakeItemExtras(extraDAO.getExtrasForCake(theCakeID));
        }
        return orderCakeItems;
    }

}

