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
public class CatalogOrder1 {
//    private CatalogPackage item;
    private CorporateCustomer corporate = new CorporateCustomer();
    private Consumer customer = new Consumer();
    private CatalogPackage catalogPack = new CatalogPackage();
    private String orderDate; //date of the order being made
    private String retrieveDate; // pickup or delivery date
    private String pickupTime; //or delivery time   
    private String orderType;
    private int itemQuantity;
    private double itemPrice;
    private boolean paymentStatus;
    private int discountRate;

    public CatalogOrder1() {
    }
    
    public CatalogOrder1(Consumer customer,String orderDate, String orderType, String retrieveDate, CatalogPackage catalog,double itemPrice,int itemQuantity) {
        this.customer = customer;
        this.orderDate = orderDate;
        this.orderType = orderType;
        this.retrieveDate = retrieveDate;
        this.catalogPack = catalog;  
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }
    
    public CatalogOrder1(CorporateCustomer corporate, String orderDate, String orderType, String retrieveDate, CatalogPackage catalog,double itemPrice,int itemQuantity, boolean status) {
        this.corporate = corporate;
        this.orderDate = orderDate;
        this.orderType = orderType;
        this.retrieveDate = retrieveDate;
        this.paymentStatus = status;
        this.catalogPack = catalog;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    public CatalogOrder1(CorporateCustomer corporateCustomer, String delivery, long pickupTime, CatalogPackage catalogPackage, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setCatalogPackage(CatalogPackage catalog){
        this.catalogPack = catalog;
    }
    
    public CatalogPackage getCatalogPackage(){
        return catalogPack;
    }

    public void setPaymentStat(boolean paymentStat){
        this.paymentStatus = paymentStat;
    }
    
    public boolean getPaymentStat(){
        return paymentStatus;
    }
    
    public CorporateCustomer getCorporate() {
        return corporate;
    }

    public void setCorporate(CorporateCustomer corporate) {
        this.corporate = corporate;
    }

    public Consumer getCustomer() {
        return customer;
    }

    public void setCustomer(Consumer customer) {
        this.customer = customer;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getRetrieveDate() {
        return retrieveDate;
    }

    public void setRetrieveDate(String retrieveDate) {
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
        return "CatalogOrder1{" + "itemName=" + catalogPack.getName() + ", itemStyle=" + catalogPack.getStyle() + ", itemSize=" + catalogPack.getSize() + ", itemFlower=" + catalogPack.getFlower() + ", itemAccesscory=" + catalogPack.getAccessory() + ", itemQuantity=" + catalogPack.getQuantity() + ", itemPrice=" + catalogPack.getPrice() + ", corporate=" + corporate + ", customer=" + customer + ", orderDate=" + orderDate + ", pickupTime=" + pickupTime + '}';
    }
    
}
