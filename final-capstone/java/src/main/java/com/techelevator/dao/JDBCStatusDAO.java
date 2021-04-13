package com.techelevator.dao;

import com.techelevator.model.Status;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCStatusDAO implements StatusDAO{
    private JdbcTemplate jdbcTemplate;
    public JDBCStatusDAO(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate;}

    public List<Status> getStatuses(){
        List<Status> statusList = new ArrayList<>();
        String sqlStatusGet = "SELECT * FROM statuses ORDER BY status_id;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sqlStatusGet);
        while(result.next()){
            Status theStatus = mapRowToStatus(result);
            statusList.add(theStatus);
        }
        return statusList;
    }

    public Status mapRowToStatus(SqlRowSet result){
        Status theStatus = new Status();
        theStatus.setStatusID(result.getInt("status_id"));
        theStatus.setStatusName(result.getString("status_name"));
        return theStatus;
    }
}
