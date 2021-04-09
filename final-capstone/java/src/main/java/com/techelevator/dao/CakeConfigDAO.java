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

}
