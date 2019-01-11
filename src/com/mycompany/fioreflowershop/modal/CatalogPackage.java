/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

import com.mycompany.fioreflowershop.adt.LinkedList;
import com.mycompany.fioreflowershop.adt.ListIteratorInterface;

/**
 *
 * @author Woo
 */
public class CatalogPackage implements Comparable<CatalogPackage> {

    //Data field
    private String name;
    private Item style, size, flower;
    private Item season;
    private Item flowerPot, accessory, priority, deliveryType;
    private String productType, promoMonth;
    private int promoYear;
    private double price;
    private int discountRate;
    private String status;
    private int userQuantity;
    private String flowerNeeded;
    private ListIteratorInterface<Item> flowerList = new LinkedList<Item>();

    public CatalogPackage() {
    }

    public CatalogPackage(String name, Item style, Item size, Item accessory, String productType, String promoMonth, int promoYear, double price, int discountRate, String status, String flowerNeeded) {
        this.name = name;
        this.style = style;
        this.size = size;
        this.accessory = accessory;
        this.productType = productType;
        this.promoMonth = promoMonth;
        this.promoYear = promoYear;
        this.price = price;
        this.discountRate = discountRate;
        this.status = status;
        this.flowerNeeded = flowerNeeded;
    }

    public CatalogPackage(String name, Item style, Item size, Item season, Item flowerPot, Item accessory, String productType, String promoMonth, int promoYear, double price, int discountRate, String status, String flowerNeeded) {
        this.name = name;
        this.style = style;
        this.size = size;
        this.season = season;
        this.flowerPot = flowerPot;
        this.accessory = accessory;
        this.productType = productType;
        this.promoMonth = promoMonth;
        this.promoYear = promoYear;
        this.price = price;
        this.discountRate = discountRate;
        this.status = status;
        this.flowerNeeded = flowerNeeded;
    }

    public CatalogPackage(String name, Item style, Item size, Item season, Item flowerPot, Item accessory, String productType, String promoMonth, int promoYear, double price, int discountRate, String status, int userQuantity, String flowerNeeded) {
        this.name = name;
        this.style = style;
        this.size = size;
        this.season = season;
        this.flowerPot = flowerPot;
        this.accessory = accessory;
        this.priority = priority;
        this.deliveryType = deliveryType;
        this.productType = productType;
        this.promoMonth = promoMonth;
        this.promoYear = promoYear;
        this.price = price;
        this.discountRate = discountRate;
        this.status = status;
        this.userQuantity = userQuantity;
        this.flowerNeeded = flowerNeeded;
    }    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item getStyle() {
        return style;
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

    public Item getSeason() {
        return season;
    }

    public void setSeason(Item season) {
        this.season = season;
    }

    public Item getFlowerPot() {
        return flowerPot;
    }

    public void setFlowerPot(Item flowerPot) {
        this.flowerPot = flowerPot;
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

    public Item getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Item deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getPromoMonth() {
        return promoMonth;
    }

    public void setPromoMonth(String promoMonth) {
        this.promoMonth = promoMonth;
    }

    public int getPromoYear() {
        return promoYear;
    }

    public void setPromoYear(int promoYear) {
        this.promoYear = promoYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(int discountRate) {
        this.discountRate = discountRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserQuantity() {
        return userQuantity;
    }

    public void setUserQuantity(int userQuantity) {
        this.userQuantity = userQuantity;
    }

    public double CalculateOrder() {
        double flowerPrice = 0;
        for (int i = 1; i < flowerList.getTotalEntries(); i++) {
            flowerPrice += flowerList.getItem(i).getPrice();
        }
        return (style.getPrice() + (flowerPrice * size.getPrice()) + accessory.getPrice()) * priority.getPrice() + deliveryType.getPrice();
    }
    
    public ListIteratorInterface<Item> getFlowerList() {
        return flowerList;
    }

    public void setFlowerList(ListIteratorInterface<Item> flowerList) {
        this.flowerList = flowerList;
    }

    public String getFlowerNeeded() {
        return flowerNeeded;
    }

    public void setFlowerNeeded(String flowerNeeded) {
        this.flowerNeeded = flowerNeeded;
    }

    @Override
    public String toString() {
        return "name=" + name + ", style=" + style + ", size=" + size + ", flower=" + flower + ", season=" + season + ", flowerPot=" + flowerPot + ", accessory=" + accessory + ", priority=" + priority + ", deliveryType=" + deliveryType + ", productType=" + productType + ", promoMonth=" + promoMonth + ", promoYear=" + promoYear + ", price=" + price + ", discountRate=" + discountRate + ", status=" + status + ", userQuantity=" + userQuantity + ", flowerNeeded=" + flowerNeeded + ", flowerList=" + flowerList;
    }

    @Override
    public int compareTo(CatalogPackage o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
