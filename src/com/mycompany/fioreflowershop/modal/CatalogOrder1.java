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
    private Date orderDate;
    private Date pickupTime; //or delivery time   
    private String orderType;
    private boolean paymentStatus;
    private int discountRate;

    public CatalogOrder1() {
    }
    
    public CatalogOrder1(Consumer customer, String orderType, Date orderdate, CatalogPackage catalog) {
        this.customer = customer;
        this.orderType = orderType;
        this.orderDate = orderdate;
        this.catalogPack = catalog;    
    }
    
    public CatalogOrder1(CorporateCustomer corporate,String orderType, Date orderdate, CatalogPackage catalog, boolean status) {
        this.corporate = corporate;
        this.orderType = orderType;
        this.orderDate = orderdate;
        this.paymentStatus = status;
        this.catalogPack = catalog;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
    }

    @Override
    public String toString() {
        return "CatalogOrder1{" + "itemName=" + catalogPack.getName() + ", itemStyle=" + catalogPack.getStyle() + ", itemSize=" + catalogPack.getSize() + ", itemFlower=" + catalogPack.getFlower() + ", itemAccesscory=" + catalogPack.getAccessory() + ", itemQuantity=" + catalogPack.getQuantity() + ", itemPrice=" + catalogPack.getPrice() + ", corporate=" + corporate + ", customer=" + customer + ", orderDate=" + orderDate + ", pickupTime=" + pickupTime + '}';
    }
    
}
