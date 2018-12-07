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
    private CorporateCustomer corp;
    private Date datepay;
    
    public InvoiceHistory(){
        
    }
    
    public InvoiceHistory(int invoiceNum ,CatalogOrders cart, CorporateCustomer corp, Date paid){
        invoiceNumber = "IH"+invoiceNum;
        this.catalogOrder = cart;
        this.corp = corp;
        this.datepay = paid;
    }

    public Date getDatepay() {
        return datepay;
    }

    public void setDatepay(Date datepay) {
        this.datepay = datepay;
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

    public CorporateCustomer getCorp() {
        return corp;
    }

    public void setCorp(CorporateCustomer corp) {
        this.corp = corp;
    }
    
}
