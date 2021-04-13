package com.techelevator.dao;

import com.techelevator.model.CakeConfig;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public interface CakeConfigDAO {
    /**
     * populate list with all configs in database (including custom)
     * @return list<CakeConfig>
     */
    List <CakeConfig> getAllConfigs();

    /**
     * populate list with available configs in database, including custom.
     * @return list<CakeConfig>>
     */
    public List <CakeConfig> getAvailableConfigs();

    /**
     * add a new cakeConfig to the DB
     *
     * @param configToAdd
     * @return Integer, id of the new Config
     */
    Integer addCakeConfig(CakeConfig configToAdd);

    /**
     * flips the availability status of a cake config. If it was true, it becomes false, and vice versa. Returns the
     * new value if is_available in the cake config table.
     * @param id - the id of the cake config
     * @return boolean - the updated boolean of the cake config for is_available
     */
    boolean flipConfigStatus(int id);

    /**
     * returns the cake id from a query
     * @param result - the resulting cake object from a table query
     * @return - the cake item's id
     */
    int getCakeIdFromQuery(SqlRowSet result);

}
