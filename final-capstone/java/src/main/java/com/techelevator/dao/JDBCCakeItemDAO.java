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
        //adds the cake item to the cake_items table
        String sqlToAddCakeItem = "INSERT INTO cake_items (cake_style_id, cake_size_id, flavor_id, frosting_id, " +
                "filling_id, message, config_id,\n" +
                "item_price, order_id)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING cake_item_id ;";
        //need to rework this to bring back the newly inserted CakeItem's id, like we did in the orderJDBC methods
        int cakeItemID = jdbcTemplate.queryForObject(sqlToAddCakeItem, Integer.class, cakeItem.getCakeItemStyleID(), cakeItem.getCakeItemSizeID(), cakeItem.getCakeItemFlavorID(),
                cakeItem.getCakeItemFrostingID(), cakeItem.getCakeItemFillingID(), cakeItem.getCakeItemMessage(), cakeItem.getCakeItemConfigID(),
                cakeItem.getCakeItemPrice(), orderID
        );
        //adds the extras to the cake_item_extras table if extras exist
        if (cakeItem.getCakeItemExtras() != null && cakeItem.getCakeItemExtras().length > 0){
            for (Extra theExtra : cakeItem.getCakeItemExtras()) {
                String sqlAddExtraToCakeItem = "INSERT INTO cake_item_extras (cake_item_id, extra_id) " +
                        "VALUES (?, ?) ;";
                jdbcTemplate.update(sqlAddExtraToCakeItem, cakeItemID, theExtra.getExtraID());
            }
    }
    }

    @Override
    public void updateCakeItem(CakeItemDTO cakeItemDTO, int orderID){
        String sqlUpdateCakeItem = "UPDATE cake_items SET " +
                "cake_style_id = ? ," +
                "cake_size_id = ? ," +
                "flavor_id = ? ," +
                "frosting_id = ? ," +
                "filling_id = ? ," +
                "message = ? ," +
                "config_id = ? ," +
                "item_price = ? ," +
                "WHERE cake_item_id = ? AND order_id = ?";
        jdbcTemplate.update(sqlUpdateCakeItem,
                cakeItemDTO.getCakeItemStyleID(),
                cakeItemDTO.getCakeItemSizeID(),
                cakeItemDTO.getCakeItemFlavorID(),
                cakeItemDTO.getCakeItemFrostingID(),
                cakeItemDTO.getCakeItemFillingID(),
                cakeItemDTO.getCakeItemMessage(),
                cakeItemDTO.getCakeItemConfigID(),
                cakeItemDTO.getCakeItemPrice(),
                cakeItemDTO.getCakeItemID(),
                orderID);

        //delete old extras from the cake_item_extras table, we'll just recreate them fresh. Easier than
        //going line by line.
        String deleteOutdatedExtras = "DELETE FROM cake_item_extras WHERE cake_item_id = ? ;";
        jdbcTemplate.update(deleteOutdatedExtras, cakeItemDTO.getCakeItemID());
        //adds the updatedextras to the cake_item_extras table if the extras exist
        if(cakeItemDTO.getCakeItemExtras() != null && cakeItemDTO.getCakeItemExtras().length > 0) {
            for (Extra theExtra : cakeItemDTO.getCakeItemExtras()) {
                String sqlAddExtraToCakeItem = "INSERT INTO cake_item_extras (cake_item_id, extra_id) " +
                        "VALUES (?, ?) ;";
                jdbcTemplate.update(sqlAddExtraToCakeItem, cakeItemDTO.getCakeItemID(), theExtra.getExtraID());
            }
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
        theCakeItemDTO.setCakeItemFillingID(result.getInt("filling_id"));
        theCakeItemDTO.setCakeItemMessage(result.getString("message"));
        theCakeItemDTO.setCakeItemConfigID(result.getInt("config_id"));
        theCakeItemDTO.setCakeItemPrice(result.getBigDecimal("item_price"));
        theCakeItemDTO.setCakeItemOrderID(result.getInt("order_id"));
        return theCakeItemDTO;
    }

    @Override
    public List<CakeItemDTO> getCakeItemDTOsForOrder(int orderID){
        String sqlCakeItemDTOQuery = "SELECT * FROM cake_items WHERE order_id = ? ORDER BY cake_item_id;";
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

