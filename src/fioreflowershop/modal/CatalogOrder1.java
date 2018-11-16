/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop.modal;

import java.util.Date;

/**
 *
 * @author Zion Tseu
 */
public class CatalogOrder1 {
//    private CatalogPackage item;
    private String itemName;
    private String itemStyle;
    private String itemSize;
    private String itemFlower;
    private String itemAccesscory;
    private int itemQuantity;
    private double itemPrice;
    private CorporateCustomer corporate = new CorporateCustomer();
    private Consumer customer = new Consumer();
    private Date orderDate;
    private Long pickupTime; //or delivery time   
    private String orderType;

    public CatalogOrder1() {
    }
    
    public CatalogOrder1(Consumer customer, String orderType, Long pickupTime, String itemName, String itemStyle, String itemSize, String itemFlower, String itemAccessory,double itemPrice,int itemQuantity) {
        this.customer = customer;
        this.orderType = orderType;
        this.pickupTime = pickupTime;
        this.itemName = itemName;
        this.itemStyle = itemStyle;
        this.itemSize = itemSize;
        this.itemFlower = itemFlower;
        this.itemAccesscory = itemAccessory;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;    
    }
    
    public CatalogOrder1(CorporateCustomer corporate,String orderType, Long pickupTime, String itemName, String itemStyle, String itemSize, String itemFlower, String itemAccessory,double itemPrice,int itemQuantity) {
        this.corporate = corporate;
        this.orderType = orderType;
        this.pickupTime = pickupTime;
        this.itemName = itemName;
        this.itemStyle = itemStyle;
        this.itemSize = itemSize;
        this.itemFlower = itemFlower;
        this.itemAccesscory = itemAccessory;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;    
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(String itemStyle) {
        this.itemStyle = itemStyle;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public String getItemFlower() {
        return itemFlower;
    }

    public void setItemFlower(String itemFlower) {
        this.itemFlower = itemFlower;
    }

    public String getItemAccesscory() {
        return itemAccesscory;
    }

    public void setItemAccesscory(String itemAccesscory) {
        this.itemAccesscory = itemAccesscory;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Long getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Long pickupTime) {
        this.pickupTime = pickupTime;
    }

    @Override
    public String toString() {
        return "CatalogOrder1{" + "itemName=" + itemName + ", itemStyle=" + itemStyle + ", itemSize=" + itemSize + ", itemFlower=" + itemFlower + ", itemAccesscory=" + itemAccesscory + ", itemQuantity=" + itemQuantity + ", itemPrice=" + itemPrice + ", corporate=" + corporate + ", customer=" + customer + ", orderDate=" + orderDate + ", pickupTime=" + pickupTime + '}';
    }
    
}
