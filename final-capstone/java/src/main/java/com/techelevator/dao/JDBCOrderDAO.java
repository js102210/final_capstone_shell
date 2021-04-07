package com.techelevator.dao;

import com.techelevator.model.Order;
import org.springframework.stereotype.Component;

@Component
public class JDBCOrderDAO  implements  OrderDAO{


    @Override
    public void placeOrder(Order order) {

    }
}
