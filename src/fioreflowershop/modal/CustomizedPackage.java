/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop.modal;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Chiu Peeng
 */
public class CustomizedPackage {
    private static int orderNo = 1;
    private int orderNum;
    private Date date = new Date();
    private Item style, size, flower, accessory, priority, deliveryType;
    private Customer customer = new Customer();

    public Item getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Item deliveryType) {
        this.deliveryType = deliveryType;
    }

    public static int getOrderNo() {
        return orderNo;
    }

    public static void setOrderNo(int orderNo) {
        CustomizedPackage.orderNo = orderNo;
    }

    public Item getStyle() {
        return style;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStyle(Item style) {
        this.style = style;
    }

    public Item getSize() {
        return size;
    }

    public void setSize(Item size) {
        this.size = size;
    }

    public Item getFlower() {
        return flower;
    }

    public void setFlower(Item flower) {
        this.flower = flower;
    }

    public Item getAccessory() {
        return accessory;
    }

    public void setAccessory(Item accessory) {
        this.accessory = accessory;
    }

    public Item getPriority() {
        return priority;
    }

    public void setPriority(Item priority) {
        this.priority = priority;
    }

    public CustomizedPackage(Item style, Item size, Item flower, Item accessory, Item priority, Item deliveryType, Customer customer) {
        orderNum = orderNo;
        ++orderNo;
        date = Calendar.getInstance().getTime();
        this.style = style;
        this.size = size;
        this.flower = flower;
        this.accessory = accessory;
        this.priority = priority;
        this.deliveryType = deliveryType;
        this.customer = customer;
    }

    public CustomizedPackage() {
        orderNum = orderNo;
        ++orderNo;
        date = Calendar.getInstance().getTime();
    }
    
    public double CalculateOrder(){
        return (style.getPrice() + (flower.getPrice() * size.getPrice()) + accessory.getPrice()) * priority.getPrice() + deliveryType.getPrice();
    }
    
    public void minusQuantity(){
        flower.minusQuantity();
        accessory.minusQuantity();
    }
}
