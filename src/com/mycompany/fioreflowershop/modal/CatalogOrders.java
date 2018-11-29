/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

import java.util.Date;

/**
 *
 * @author Zion Tseu
 */
public class CatalogOrders extends Order {
//    private CatalogPackage item;

    private User user;
    private CatalogPackage catalogPack = new CatalogPackage();
    private Date orderDate; //date of the order being made
    private Date retrieveDate; // pickup or delivery date
    private String pickupTime; //or delivery time   
    private String orderType;
    private int itemQuantity;
    private double itemPrice;
    private boolean paymentStatus;
    private int discountRate;

    public CatalogOrders() {
    }

    public CatalogOrders(User user, Date orderDate, String orderType, Date retrieveDate, CatalogPackage catalog, double itemPrice, int itemQuantity) {
        this.user = user;
        this.orderDate = orderDate;
        this.orderType = orderType;
        this.retrieveDate = retrieveDate;
        this.catalogPack = catalog;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    public CatalogOrders(User user, Date orderDate, String orderType, Date retrieveDate, CatalogPackage catalog, double itemPrice, int itemQuantity, boolean status) {
        this.user = user;
        this.orderDate = orderDate;
        this.orderType = orderType;
        this.retrieveDate = retrieveDate;
        this.paymentStatus = status;
        this.catalogPack = catalog;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    public void setCatalogPackage(CatalogPackage catalog) {
        this.catalogPack = catalog;
    }

    public CatalogPackage getCatalogPackage() {
        return catalogPack;
    }

    public void setPaymentStat(boolean paymentStat) {
        this.paymentStatus = paymentStat;
    }

    public boolean getPaymentStat() {
        return paymentStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public Date getRetrieveDate() {
        return retrieveDate;
    }

    public void setRetrieveDate(Date retrieveDate) {
        this.retrieveDate = retrieveDate;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public String toString() {
        return "CatalogOrder1{" + "itemName=" + catalogPack.getName() + ", itemStyle=" + catalogPack.getStyle() + ", itemSize=" + catalogPack.getSize() + ", itemFlower=" + catalogPack.getFlower() + ", itemAccesscory=" + catalogPack.getAccessory() + ", itemQuantity=" + catalogPack.getQuantity() + ", itemPrice=" + catalogPack.getPrice() + ", corporate=" + user + ", customer=" + user + ", orderDate=" + orderDate + ", pickupTime=" + pickupTime + '}';
    }

}
