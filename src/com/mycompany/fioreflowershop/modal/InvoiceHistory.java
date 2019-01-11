/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

import com.mycompany.fioreflowershop.CatalogOrder;
import com.mycompany.fioreflowershop.adt.LinkedList;
import com.mycompany.fioreflowershop.adt.OrderList;
import com.mycompany.fioreflowershop.adt.OrderListInterface;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class InvoiceHistory {
    private String invoiceNumber;
    private OrderListInterface<CatalogOrders> catalogOrder = new OrderList<>();
    private CorporateCustomer corp;
    private Date datepay;
    
    public InvoiceHistory(){
        
    }
    
    public InvoiceHistory(int invoiceNum , OrderListInterface<CatalogOrders> cart, CorporateCustomer corp, Date paid){
        setInvoiceNumber("IH"+invoiceNum);
        setCatalogOrder(cart);
        setCorp(corp);
        setDatepay(paid);
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

    public OrderListInterface<CatalogOrders> getCatalogOrder() {
        return catalogOrder;
    }

    public void setCatalogOrder(OrderListInterface<CatalogOrders> catalogOrder) {
        this.catalogOrder = catalogOrder;
    }

    public CorporateCustomer getCorp() {
        return corp;
    }

    public void setCorp(CorporateCustomer corp) {
        this.corp = corp;
    }
    
}
