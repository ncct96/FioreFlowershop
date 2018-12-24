/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

import com.mycompany.fioreflowershop.adt.CatalogPackageInterface;
import com.mycompany.fioreflowershop.adt.CatalogPackageList;
import java.util.Date;

/**
 *
 * @author Zion Tseu
 */
public class CatalogOrders extends Order {
    private String orderID;
    private CatalogPackageInterface<CatalogPackage> catalogPack = new CatalogPackageList<>();
    private Date retrieveTime;

    public CatalogOrders() {
    }

    public CatalogOrders(String orderID, CatalogPackageInterface<CatalogPackage> catalogPack,
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

    public CatalogPackageInterface<CatalogPackage> getCatalogPack() {
        return catalogPack;
    }

    public void setCatalogPack(CatalogPackageInterface<CatalogPackage> catalogPack) {
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
