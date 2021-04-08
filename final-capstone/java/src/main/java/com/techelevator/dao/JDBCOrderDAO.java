package com.techelevator.dao;

import com.techelevator.model.CakeItemDTO;
import com.techelevator.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

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
       Integer newID = jdbcTemplate.queryForObject(sqlToInsertOrder, Integer.class, order.getOrderPriceTotal(), Timestamp.valueOf("2021-01-01 12:12:12"),

                Timestamp.valueOf("2021-01-01 12:12:12"),  order.getCustomerName(), order.getCustomerPhoneNumber());
        for (CakeItemDTO cakeItem : order.getItemsInOrder()){
            cakeItemDAO.addCakeItem(cakeItem, newID);
        }
        /**
         * To parse string from JSON using "CreatedOn" date:
         * DateTimeFormatter jsonDateFormatter = new DateTimeFormatterBuilder()
         *  .appendLiteral("/Date(")
         *  .appendValue(ChronoField.INSTANT_SECONDS)
         *  .appendLiteral(")/")
         *  .toFormatter();
         *
         * String createdOn = "/Date(GIANT NUMBER OF SECONDS SINCE CREATION)/";
         * Instant created = jsonDateFormatter.parse(createdOn, Instant::from);
         * System.out.println("Created on" + created);
         *
         * OR a simpler less elegant way:
         *
         * String input = "2021-01-01 12:12:12"
         * try {
         *      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         *      LocalDate date = LocalDate.parse(input, formatter);
         *      System.out.printf("%s%n", date);
         *      }
         *      catch (DateTimeParseException e) {
         *      System.out.printf("%s is not valid!%n", input);
         *      throw e;
         *      }
         */

    }
}
