package com.techelevator.dao;


import com.techelevator.model.Extra;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCExtraDAO implements ExtraDAO {
    private final JdbcTemplate jdbcTemplate;

    public JDBCExtraDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List <Extra> getAvailableExtras() {
        String sqlToGetAvailableExtras = "SELECT * FROM extras WHERE is_available = TRUE ORDER BY extra_id;";
        List <Extra> availableExtras = new ArrayList <> ();
        SqlRowSet result = jdbcTemplate.queryForRowSet (sqlToGetAvailableExtras);
        while (result.next()) {
            Extra extra = mapRowToExtra (result);
            availableExtras.add (extra);
        }
        return availableExtras;
    }

    @Override
    public List <Extra> getAllExtras() {
        String sqlToGetAllExtras = "SELECT * FROM extras ORDER BY extra_id;";
        List <Extra> allExtras = new ArrayList <> ();
        SqlRowSet result = jdbcTemplate.queryForRowSet (sqlToGetAllExtras);
        while (result.next()) {
            Extra extra = mapRowToExtra(result);
            allExtras.add (extra);
        }
        return allExtras;
    }


    @Override
    public int createExtra(Extra newExtra) {
        String sqlToAddNewExtra = "INSERT INTO extras (extra_name, price_mod) VALUES (?, ?) RETURNING extra_id ;";
        Integer newID = jdbcTemplate.queryForObject (sqlToAddNewExtra, Integer.class, newExtra.getExtraName(), newExtra.getPriceMod());
        return newID;
    }

    @Override
    public Extra updateExtra(Extra extra, int ID) {
        String sqlToUpdateExtra =
                "UPDATE extras\n" +
                        "SET extra_name = ?,\n" +
                        "is_available = ?,\n" +
                        "price_mod = ?\n" +
                        "WHERE extra_id = ?;";
        jdbcTemplate.update (sqlToUpdateExtra, extra.getExtraName(), extra.isAvailable(),
                extra.getPriceMod(), ID
        );
        return extra;
    }

    @Override
    public String deleteExtra(int ID) {
        return null;
    }

    @Override
    public boolean flipAvailability(int id) {
        String sqlFlipStatusStatement = "UPDATE extras SET is_available = NOT is_available WHERE extra_id = ? RETURNING is_available ;";

        Boolean result = jdbcTemplate.queryForObject (sqlFlipStatusStatement, Boolean.class, id);

        return result;
    }

    @Override
    public Extra[] getExtrasForCake(int cakeItemID){
        List<Extra> extraList = new ArrayList<>();
        String sqlExtraLookupStatement = "SELECT extras.extra_id, extra_name, is_available, price_mod FROM extras JOIN cake_item_extras " +
                "ON extras.extra_id = cake_item_extras.extra_id WHERE cake_item_extras.cake_item_id = ? ORDER BY cake_item_extras.extra_id";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sqlExtraLookupStatement, cakeItemID);
        //loop through results to populate the List of Extras.
        while(result.next()){
            Extra theExtra = mapRowToExtra(result);
            extraList.add(theExtra);
        }
        //convert the extraList into an array
        Extra[] cakeItemExtrasArray = extraList.toArray(new Extra[0]);

        return cakeItemExtrasArray;
    }

    public Extra mapRowToExtra(SqlRowSet result) {
        Extra extra = new Extra();
        extra.setExtraID (result.getInt ("extra_id"));
        extra.setExtraName (result.getString ("extra_name"));
        extra.setIsAvailable (result.getBoolean ("is_available"));
        extra.setPriceMod (result.getBigDecimal ("price_mod"));
        return extra;
    }
}
