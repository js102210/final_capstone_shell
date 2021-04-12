package com.techelevator.model;

import java.math.BigDecimal;


public class    Order {
    private int orderID;
    private int orderStatusID;
    private BigDecimal orderPriceTotal;
    private String orderDatePlaced;
    private String orderPickupDate;
    private String orderPickupTime;
    private CakeItemDTO[] itemsInOrder;
    private String customerName;
    private String customerPhoneNumber;



    /**
     * Constructor for Order
     * @param orderID int
     * @param orderStatusID int
     * @param orderPriceTotal BigDecimal
     * @param  orderDatePlaced LocalDate
     * @param orderPickupDate LocalDate
     * @param orderPickupTime LocalTime 
     * @param itemsInOrder CakeItems[]
     * @param customerName String
     * @param customerPhoneNumber String
     */
    public Order(int orderID, int orderStatusID, BigDecimal orderPriceTotal, String orderDatePlaced, String orderPickupDate,
                 String orderPickupTime, CakeItemDTO[] itemsInOrder, String customerName, String customerPhoneNumber){
        this.orderID = orderID;
        this.orderStatusID = orderStatusID;
        this.orderPriceTotal = orderPriceTotal;
        this.orderDatePlaced = orderDatePlaced;
        this.orderPickupDate = orderPickupDate;
        this.orderPickupTime = orderPickupTime;
        this.itemsInOrder = itemsInOrder;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;

    }

    public Order(int order_id, String status_id, String total_price, String date_placed, String pickup_date, String pickup_time, String customer_name, String customer_phone_number) {
    }
    public Order(){

    }

    public int getOrderStatusID() {
        return orderStatusID;
    }

    public void setOrderStatusID(int orderStatusID) {
        this.orderStatusID = orderStatusID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getOrderStatus() {
        return orderStatusID;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatusID = orderStatusID;
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
