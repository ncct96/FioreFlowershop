/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop.modal;

/**
 *
 * @author Nicholas
 */
public class Order {
    private int orderID;
    private String orderType;
    private CorporateCustomer corp;
    private CustomizedPackage customPackage;

    public Order(int orderID, String orderType, CorporateCustomer corp, CustomizedPackage customPackage) {
        this.orderID = orderID;
        this.orderType = orderType;
        this.corp = corp;
        this.customPackage = customPackage;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getOrderType() {
        return orderType;
    }

    public CorporateCustomer getCorp() {
        return corp;
    }

    public CustomizedPackage getCustomPackage() {
        return customPackage;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setCorp(CorporateCustomer corp) {
        this.corp = corp;
    }

    public void setCustomPackage(CustomizedPackage customPackage) {
        this.customPackage = customPackage;
    }
    
    
    
}
