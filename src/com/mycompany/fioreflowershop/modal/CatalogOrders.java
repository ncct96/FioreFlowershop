/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

import com.mycompany.fioreflowershop.adt.LinkedList;
import java.util.Date;

/**
 *
 * @author Zion Tseu
 */
public class CatalogOrders extends Order {
    private String orderID;
    private LinkedList<CatalogPackage> catalogPack = new LinkedList<>();
    private Date retrieveTime;

    public CatalogOrders() {
    }

    public CatalogOrders(String orderID, LinkedList<CatalogPackage> catalogPack,
            String orderType, Date orderDate, User user, String orderStatus, double orderAmt,
            boolean paymentStatus, Date retrieveDate, Date retrieveTime) {
        super(orderType, orderDate, user, orderStatus, orderAmt, paymentStatus, retrieveDate);
        this.orderID = orderID;
        this.catalogPack = catalogPack;     
        this.retrieveTime = retrieveTime;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public LinkedList<CatalogPackage> getCatalogPack() {
        return catalogPack;
    }

    public void setCatalogPack(LinkedList<CatalogPackage> catalogPack) {
        this.catalogPack = catalogPack;
    }   

    public Date getRetrieveTime() {
        return retrieveTime;
    }

    public void setRetrieveTime(Date retrieveTime) {
        this.retrieveTime = retrieveTime;
    }
    
    @Override
    public String toString() {
        return "CatalogOrders{" + "orderID=" + orderID + ", catalogPack=" + catalogPack + ", retrieveTime=" + retrieveTime + '}';
    }   

}
