package com.techelevator.dao;

import com.techelevator.model.CakeItemDTO;
import com.techelevator.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Component
public class JDBCOrderDAO  implements  OrderDAO{
    private JdbcTemplate jdbcTemplate;
    private CakeItemDAO cakeItemDAO;
    public JDBCOrderDAO(JdbcTemplate jdbcTemplate, CakeItemDAO cakeItemDAO){
        this.jdbcTemplate = jdbcTemplate;
        this.cakeItemDAO = cakeItemDAO;
    }


    @Override
    public void placeOrder(Order order) {
        String sqlToInsertOrder = "INSERT INTO orders (status_id, total_price, datetime_placed, datetime_delivery, " +
                "customer_name, customer_phone_number)\n" +
                "\tVALUES (1, ?, ?, ?, ?, ?) RETURNING order_id;";
        //TODO change the datetime representations when we get that whole thing figured out

       Integer newID = jdbcTemplate.queryForObject(sqlToInsertOrder, Integer.class, order.getOrderPriceTotal(),
               order.getOrderPlacedDateTime (), order.getOrderDeliveryDateTime (),order.getCustomerName(), order.getCustomerPhoneNumber());


        for (CakeItemDTO cakeItem : order.getItemsInOrder()){
            cakeItemDAO.addCakeItem(cakeItem, newID);
        }

//            public static Date parse( String input ) throws java.text.ParseException {
//
//            //SimpleDateFormat uses GMT[-or+]hh:mm for the timezone which breaks it
//
//            SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ssz" );
//
//            //timezone indicator
//            if ( input.endsWith( "Z" ) ) {
//                input = input.substring( 0, input.length() - 1) + "GMT-00:00";
//            } else {
//                int inset = 6;
//
//                String s0 = input.substring( 0, input.length() - inset );
//                String s1 = input.substring( input.length() - inset, input.length() );
//
//                input = s0 + "GMT" + s1;
//            }
//
//            return df.parse( input );
//
//            }
//
//             public static String toString( Date date ) {
//
//            SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ssz" );
//
//            TimeZone tz = TimeZone.getTimeZone( "UTC" );
//
//            df.setTimeZone( tz );
//
//            String output = df.format( date );
//
//            int inset0 = 9;
//            int inset1 = 6;
//
//            String s0 = output.substring( 0, output.length() - inset0 );
//            String s1 = output.substring( output.length() - inset1, output.length() );
//
//            String result = s0 + s1;
//
//            result = result.replaceAll( "UTC", "+00:00" );
//
//            return result;
//
//            }

    }
}
