package com.techelevator.dao;

import com.techelevator.model.CakeConfig;

import java.util.List;

public interface CakeConfigDAO {
    /**
     * populate list with all configs in database (including custom)
     * @return list<CakeConfig>
     */
    List<CakeConfig> getAllConfigs();

    /**
     * populate list with available configs in database, including custom.
     * @return list<CakeConfig>>
     * */
    public List<CakeConfig> getAvailableConfigs();

    /**
     * looks up an Config by name and instantiates object with that config's properties.
     * Note- for 'custom' config, method/query must be written to instead populate with provided input
     * @param configName name of CakeConfig
     * @return CakeConfig
     */
    CakeConfig getCakeConfig(String configName);

    /**
     * add a new cakeConfig to the DB
     * @param configToAdd
     * @return Integer, id of the new Config
     */
    Integer addCakeConfig(CakeConfig configToAdd);

    /**
     * flips the availability status of a cake config. If it was true, it becomes false, and vice versa. Returns the
     * new value if is_available in the cake config table.
     * @param id - the id of the cake confit
     * @return boolean - the updated boolean of the cake config for is_available
     * */
    boolean flipConfigStatus(int id);

}
