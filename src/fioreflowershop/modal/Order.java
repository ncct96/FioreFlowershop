/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop.modal;

import java.util.function.Consumer;

/**
 *
 * @author Nicholas
 */
public class Order {

    private int orderID;
    private String orderType;
    private CorporateCustomer corp;
    private Consumer con;

    public Order(int orderID, String orderType, CorporateCustomer corp) {
        this.orderID = orderID;
        this.orderType = orderType;
        this.corp = corp;
    }

    public Order(int orderID, String orderType, Consumer con) {
        this.orderID = orderID;
        this.orderType = orderType;
        this.con = con;
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

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setCorp(CorporateCustomer corp) {
        this.corp = corp;
    }

}
