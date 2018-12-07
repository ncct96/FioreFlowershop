/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class InvoiceHistory {
    private String invoiceNumber;
    private CatalogOrders catalogOrder;
    private User user;
    private Date datepay;
    
    public InvoiceHistory(){
        
    }
    
    public InvoiceHistory(int invoiceNum ,CatalogOrders cart, User user, Date paid){
        invoiceNumber = "IH"+invoiceNum;
        this.catalogOrder = cart;
        this.user = user;
        this.datepay = paid;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public CatalogOrders getCatalogOrder() {
        return catalogOrder;
    }

    public void setCatalogOrder(CatalogOrders catalogOrder) {
        this.catalogOrder = catalogOrder;
    }

    public User getUser() {
        return user;
    }

    public void setCorp(User user) {
        this.user = user;
    }
    
}
