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

    private String orderType;
    private Date orderDate;
    private Date retrieveDate; // For pickup/delivery date
    private User user;
    private String orderStatus;
    private boolean paymentStatus = false;
    private double orderAmt;

    public Order() {
    }

    public Order(String orderType, Date orderDate, User user, String orderStatus, double orderAmt, boolean paymentStatus, Date retrieveDate) {
        this.orderType = orderType;
        this.orderDate = orderDate;
        this.user = user;
        this.orderStatus = orderStatus;
        this.orderAmt = orderAmt;
        this.paymentStatus = paymentStatus;
        this.retrieveDate = retrieveDate;
    }

    public Order(String orderType, Date orderDate, User user) {
        this.orderType = orderType;
        this.orderDate = orderDate;
        this.user = user;
    }

    public Order(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
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

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getRetrieveDate() {
        return retrieveDate;
    }

    public void setRetrieveDate(Date retrieveDate) {
        this.retrieveDate = retrieveDate;
    }

}
