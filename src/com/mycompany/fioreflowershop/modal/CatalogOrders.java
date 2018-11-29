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
    private Date retrieveDate; // pickup or delivery date
    private int itemQuantity;
    private double itemPrice;
    private boolean paymentStatus;
    private int discountRate;

    public CatalogOrders() {
    }

    public CatalogOrders(Date orderDate, User user, Date retrieveDate, CatalogPackage catalogPack, int itemQuantity, double itemPrice, boolean paymentStatus, int discountRate, int orderID, String orderType) {
        super(orderID, orderType, orderDate, user);
        this.retrieveDate = retrieveDate;
        this.catalogPack = catalogPack;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
        this.paymentStatus = paymentStatus;
        this.discountRate = discountRate;
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

    public Date getRetrieveDate() {
        return retrieveDate;
    }

    public void setRetrieveDate(Date retrieveDate) {
        this.retrieveDate = retrieveDate;
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
        return "CatalogOrder1{" + "itemName=" + catalogPack.getName() + ", itemStyle=" + catalogPack.getStyle() + ", itemSize=" + catalogPack.getSize() + ", itemFlower=" + catalogPack.getFlower() + ", itemAccesscory=" + catalogPack.getAccessory() + ", itemQuantity=" + catalogPack.getQuantity() + ", itemPrice=" + catalogPack.getPrice() + ", corporate=" + super.getUser() + ", customer=" + super.getUser() + ", orderDate=" + super.getOrderDate() + '}';
    }

}
