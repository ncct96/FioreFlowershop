/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop.modal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Chiu Peeng
 */
public class CustomizedPackage{

    private static int orderNo = 1;
    private int orderNum;
    private String orderDate, deliveryDate;
    private Item style, size, flower, accessory, priority, deliveryType;
    private Consumer customer;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public Item getDeliveryType() {
        return deliveryType;
    }

    public Date getOrderDate() {
        Date date = new Date();
        try{
            date = df.parse(orderDate);
        }catch(Exception e){
            
        }
        return date;
    }

    public String getOrderDateString() {
        return orderDate;
    }

    public Consumer getCustomer() {
        return customer;
    }

    public void setCustomer(Consumer customer) {
        this.customer = customer;
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

    public Date getDeliveryDate() {
        Date date = new Date();
        try{
            date = df.parse(deliveryDate);
        }catch(Exception e){
            
        }
        return date;
    }

    public String getDeliveryDateString() {
        return deliveryDate;
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

    public CustomizedPackage(Item style, Item size, Item flower, Item accessory, Item priority, Item deliveryType, Consumer customer) {
        orderNum = orderNo;
        ++orderNo;
       
        Date todayDate = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, priority.getQuantity());      
        Date addDate = cal.getTime();
        
        deliveryDate = df.format(addDate);
        orderDate = df.format(todayDate);
        
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
    }

    public double CalculateOrder() {
        return (style.getPrice() + (flower.getPrice() * size.getPrice()) + accessory.getPrice()) * priority.getPrice() + deliveryType.getPrice();
    }

    public void minusQuantity() {
        flower.minusQuantity();
        accessory.minusQuantity();
    }

}
