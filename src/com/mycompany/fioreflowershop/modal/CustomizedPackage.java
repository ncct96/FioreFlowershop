/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

import com.mycompany.fioreflowershop.adt.LinkedList;
import com.mycompany.fioreflowershop.adt.ListIteratorInterface;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Chiu Peeng
 */
public class CustomizedPackage extends Order {

    private String orderID;
    private static int orderNo = 1;
    private String orderDate, deliveryDate;
    private Item style, size, flower, accessory, priority, deliveryType;
    private User user;
    private boolean paymentStatus = false;
    private ListIteratorInterface<Item> flowerList = new LinkedList<Item>();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public CustomizedPackage(Item style, Item size, Item accessory, Item priority, Item deliveryType, User user, boolean paymentStatus) {
        super(paymentStatus, user);
        this.user = user;
        orderID = "CP" + orderNo;
        this.orderID = orderID;
        ++orderNo;

        Date todayDate = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, priority.getQuantity());
        Date addDate = cal.getTime();

        super.setDeliveryDate(addDate);
        super.setPriority(priority);
        super.setID(orderID);
        super.setOrderStatus("Pending");
        super.setOrderType(deliveryType.getName());
        deliveryDate = df.format(addDate);
        orderDate = df.format(todayDate);

        this.style = style;
        this.size = size;
        this.accessory = accessory;
        this.priority = priority;
        this.deliveryType = deliveryType;
    }

    public CustomizedPackage(Item style, Item size, Item accessory, Item priority, Item deliveryType, Date deliveryDate, User user, boolean paymentStatus) {
        super(paymentStatus, user);
        this.user = user;
        orderID = "CP" + orderNo;
        this.orderID = orderID;
        ++orderNo;

        Date todayDate = Calendar.getInstance().getTime();

        super.setDeliveryDate(deliveryDate);
        super.setPriority(priority);
        super.setID(orderID);
        super.setOrderStatus("Pending");
        super.setOrderType(deliveryType.getName());
        this.deliveryDate = df.format(deliveryDate);
        orderDate = df.format(todayDate);

        this.style = style;
        this.size = size;
        this.accessory = accessory;
        this.priority = priority;
        this.deliveryType = deliveryType;
    }

    public CustomizedPackage() {
        orderID = "CP" + orderNo;
        ++orderNo;
    }

    public Item getDeliveryType() {
        return deliveryType;
    }

    public Date getOrderDate() {
        Date date = new Date();
        try {
            date = df.parse(orderDate);
        } catch (Exception e) {

        }
        return date;
    }

    public String getOrderDateString() {
        return orderDate;
    }

    public void setOrderDateString(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = df.format(orderDate);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDeliveryType(Item deliveryType) {
        super.setOrderType(deliveryType.getName());
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

    public String getOrderID() {
        return orderID;
    }

    @Override
    public int getOrderNumber() {
        return Integer.parseInt(orderID.substring(2));
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getDeliveryDate() {
        Date date = new Date();
        try {
            date = df.parse(deliveryDate);
        } catch (Exception e) {

        }
        return date;
    }

    public String getDeliveryDateString() {
        return deliveryDate;
    }

    public void setDeliveryDateString(String deliveryDate) throws ParseException {
        this.deliveryDate = deliveryDate;
        super.setDeliveryDate(df.parse(deliveryDate));
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = df.format(deliveryDate);
        super.setDeliveryDate(deliveryDate);
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
        super.setPriority(priority);
    }

    public double CalculateOrder() {
        double flowerPrice = 0;
        for (int i = 1; i <= flowerList.getTotalEntries(); i++) {
            flowerPrice += flowerList.getItem(i).getPrice();
        }
        return (style.getPrice() + (flowerPrice * size.getPrice()) + accessory.getPrice()) * priority.getPrice() + deliveryType.getPrice();
    }

    public void minusQuantity() {
        for (int i = 1; i <= flowerList.getTotalEntries(); i++) {
            flowerList.getItem(i).minusQuantity();
        }
        accessory.minusQuantity();
    }

    public ListIteratorInterface<Item> getFlowerList() {
        return flowerList;
    }

    public void setFlowerList(ListIteratorInterface<Item> flowerList) {
        this.flowerList = flowerList;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
