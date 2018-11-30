/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

import java.util.Date;

/**
 *
 * @author Nicholas
 */
public class Order {

    private String orderID;
    private String orderType;
    private Date orderDate;
    private User user;
    private String orderStatus;
    private double orderAmt;

    public Order() {
    }

    public Order(String orderID, String orderType, Date orderDate, User user, String orderStatus, double orderAmt) {
        this.orderID = orderID;
        this.orderType = orderType;
        this.orderDate = orderDate;
        this.user = user;
        this.orderStatus = orderStatus;
        this.orderAmt = orderAmt;
    }

    public Order(String orderID, String orderType, Date orderDate, User user) {
        this.orderID = orderID;
        this.orderType = orderType;
        this.orderDate = orderDate;
        this.user = user;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getOrderType() {
        return orderType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date date) {
        this.orderDate = new Date();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(double orderAmt) {
        this.orderAmt = orderAmt;
    }

}
