package com.techelevator.model;

import java.math.BigDecimal;
<<<<<<< HEAD
=======
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
>>>>>>> e0b810eb6f708bac1fd4cba177e3ee02b9ee0ec0

public class    Order {
    private int orderId;
    private String orderStatus;
    private BigDecimal orderPriceTotal;
    private String orderDatePlaced;
    private String orderPickupDate;
    private String orderPickupTime;
    private CakeItemDTO[] itemsInOrder;
    private String customerName;
    private String customerPhoneNumber;

    /**
     * Constructor for Order
     * @param orderId int
     * @param orderStatus String
     * @param orderPriceTotal BigDecimal
     * @param  orderDatePlaced LocalDate
     * @param orderPickupDate LocalDate
     * @param orderPickupTime LocalTime 
     * @param itemsInOrder CakeItems[]
     * @param customerName String
     * @param customerPhoneNumber String
     */
    public Order(int orderId, String orderStatus, BigDecimal orderPriceTotal, String orderDatePlaced, String orderPickupDate,
                 String orderPickupTime, CakeItemDTO[] itemsInOrder, String customerName, String customerPhoneNumber){
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderPriceTotal = orderPriceTotal;
        this.orderDatePlaced = orderDatePlaced;
        this.orderPickupDate = orderPickupDate;
        this.orderPickupTime = orderPickupTime;
        this.itemsInOrder = itemsInOrder;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;

    }
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getOrderPriceTotal() {
        return orderPriceTotal;
    }

    public void setOrderPriceTotal(BigDecimal orderPriceTotal) {
        this.orderPriceTotal = orderPriceTotal;
    }

    public String getOrderDatePlaced() {
        return orderDatePlaced;
    }

    public void setOrderDatePlaced(String orderDatePlaced) {
    this.orderDatePlaced = orderDatePlaced;
    }

    public String getOrderPickupDate() {
        return orderPickupDate;
    }

    public void setOrderPickupDate(String orderPickupDate) {
        this.orderPickupDate = orderPickupDate;
    }

    public String getOrderPickupTime() {
        return orderPickupTime;
    }

    public void setOrderPickupTime(String orderPickupTime) {
        this.orderPickupTime = orderPickupTime;
    }

    public CakeItemDTO[] getItemsInOrder() {
        return itemsInOrder;
    }

    public void setItemsInOrder(CakeItemDTO[] itemsInOrder) {
        this.itemsInOrder = itemsInOrder;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }




}
