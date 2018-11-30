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

    private CatalogPackage catalogPack = new CatalogPackage();
    private int itemQuantity;
    private double itemPrice;  
    private int discountRate; 

    public CatalogOrders() {
    }

    public CatalogOrders(int itemQuantity, double itemPrice, int discountRate, String orderID, String orderType, Date orderDate, User user, String orderStatus, double orderAmt, boolean paymentStatus, Date retrieveDate) {
        super(orderID, orderType, orderDate, user, orderStatus, orderAmt, paymentStatus, retrieveDate);
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
        this.discountRate = discountRate;
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

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

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
