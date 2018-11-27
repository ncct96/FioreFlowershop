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

    private int orderID;
    private String orderType;
    private Date date;
    private User user;
    private double paymentAmt;

    public Order() {
    }

    public Order(int orderID, String orderType, Date date, User user, double paymentAmt) {
        this.orderID = orderID;
        this.orderType = orderType;
        this.date = date;
        this.user = user;
        this.paymentAmt = paymentAmt;
    }

    public int getOrderID() {
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

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = new Date();
    }

    public double getPaymentAmt() {
        return paymentAmt;
    }

    public void setPaymentAmt(double paymentAmt) {
        this.paymentAmt = paymentAmt;
    }

}
