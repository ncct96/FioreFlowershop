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
public class Order{

    private int orderID;
    private String orderType;
    private Date date;
    private CorporateCustomer corp = new CorporateCustomer();
    private Consumer con = new Consumer();

    public Order() {
    }

    public Order(int orderID, String orderType, CorporateCustomer corp, Date date) {
        this.orderID = orderID;
        this.orderType = orderType;
        this.corp = corp;
        this.date = date;
    }

    public Order(int orderID, String orderType, Consumer con, Date date) {
        this.orderID = orderID;
        this.orderType = orderType;
        this.con = con;
        this.date = date;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getOrderType() {
        return orderType;
    }

    public CorporateCustomer getCorp() {
        return corp;
    }

    public Consumer getCon() {
        return con;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setCorp(CorporateCustomer corp) {
        this.corp = corp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = new Date();
    }
    
}
