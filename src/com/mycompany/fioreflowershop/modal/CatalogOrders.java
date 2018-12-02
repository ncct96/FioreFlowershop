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

    private String orderID;
    private static int orderNo = 1000;
    private CatalogPackage catalogPack = new CatalogPackage();
    private int itemQuantity;
//    private double itemPrice;
    private int discountRate;

    public CatalogOrders() {
    }

    public CatalogOrders(String orderID, CatalogPackage catalogPack, int itemQuantity, int discountRate, String orderType, Date orderDate, User user, String orderStatus, double orderAmt, boolean paymentStatus, Date retrieveDate) {
        super(orderType, orderDate, user, orderStatus, orderAmt, paymentStatus, retrieveDate);
        this.orderID = orderID + orderNo;
        ++orderNo;
        this.catalogPack = catalogPack;
        this.itemQuantity = itemQuantity;
//        this.itemPrice = itemPrice;
        this.discountRate = discountRate;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setCatalogPack(CatalogPackage catalog) {
        this.catalogPack = catalog;
    }

    public CatalogPackage getCatalogPack() {
        return catalogPack;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

//    public double getItemPrice() {
//        return itemPrice;
//    }
//
//    public void setItemPrice(double itemPrice) {
//        this.itemPrice = itemPrice;
//    }

    public int getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(int discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public String toString() {
        return "CatalogOrder1{" + "itemName=" + catalogPack.getName() + ", itemStyle=" + catalogPack.getStyle() + ", itemSize=" + catalogPack.getSize() + ", itemFlower=" + catalogPack.getFlower() + ", itemAccesscory=" + catalogPack.getAccessory() + ", itemQuantity=" + catalogPack.getQuantity() + ", itemPrice=" + catalogPack.getPrice() + ", corporate=" + super.getUser() + ", customer=" + super.getUser() + ", orderDate=" + super.getOrderDate() + '}';
    }

}
