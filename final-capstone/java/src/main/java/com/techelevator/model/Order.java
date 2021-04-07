package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class    Order {
    private int orderId;
    private String orderStatus;
    private BigDecimal orderPriceTotal;
    private LocalDateTime orderPlacedDateTime;
    private LocalDateTime orderDeliveryDateTime;
    private CakeItemDTO[] itemsInOrder;
    private String customerName;
    private String customerPhoneNumber;

    /**
     * Constructor for Order
     * @param orderId int
     * @param orderStatus String
     * @param orderPriceTotal BigDecimal
     * @param orderPlacedDateTime LocalDateTime
     * @param orderDeliveryDateTime LocalDateTime
     * @param itemsInOrder CakeItems[]
     * @param customerName String
     * @param customerPhoneNumber String
     */
    public Order(int orderId, String orderStatus, BigDecimal orderPriceTotal, LocalDateTime orderPlacedDateTime, LocalDateTime orderDeliveryDateTime,
                 CakeItemDTO[] itemsInOrder, String customerName, String customerPhoneNumber){
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderPriceTotal = orderPriceTotal;
        this.orderPlacedDateTime = orderPlacedDateTime;
        this.orderDeliveryDateTime = orderDeliveryDateTime;
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

    public LocalDateTime getOrderPlacedDateTime() {
        return orderPlacedDateTime;
    }

    public void setOrderPlacedDateTime(LocalDateTime orderPlacedDateTime) {
        this.orderPlacedDateTime = orderPlacedDateTime;
    }

    public LocalDateTime getOrderDeliveryDateTime() {
        return orderDeliveryDateTime;
    }

    public void setOrderDeliveryDateTime(LocalDateTime orderDeliveryDateTime) {
        this.orderDeliveryDateTime = orderDeliveryDateTime;
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
