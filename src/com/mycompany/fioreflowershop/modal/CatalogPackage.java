/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

/**
 *
 * @author Woo
 */
public class CatalogPackage implements Comparable<CatalogPackage>{
    //Data field
    private String name, style, size, flowerPot, floralArrangement,flower, accessory, productType, promoMonth, status;
    private int promoYear, quantity;
    private double price;
    private int discountRate;
    private int userQuantity;

    public CatalogPackage() {
    }

    public CatalogPackage(String name, String style, String size, String flowerPot, String floralArrangement, String flower, String accessory, String productType, String promoMonth, int promoYear, int quantity, double price, int discountRate, String status) {
        this.name = name;
        this.style = style;
        this.size = size;
        this.flowerPot = flowerPot;
        this.floralArrangement = floralArrangement;
        this.flower = flower;
        this.accessory = accessory;
        this.productType = productType;
        this.promoMonth = promoMonth;
        this.promoYear = promoYear;
        this.quantity = quantity;
        this.price = price;
        this.discountRate = discountRate;
        this.status = status;
    }

    public CatalogPackage(String name, String style, String size, String flower, String accessory, String productType, String promoMonth, int promoYear, int quantity, double price, int discountRate, int userQuantity) {
        this.name = name;
        this.style = style;
        this.size = size;
        this.flower = flower;
        this.accessory = accessory;
        this.productType = productType;
        this.promoMonth = promoMonth;
        this.promoYear = promoYear;
        this.quantity = quantity;
        this.price = price;
        this.discountRate = discountRate;
        this.userQuantity = userQuantity;
    }
     
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFlowerPot() {
        return flowerPot;
    }

    public void setFlowerPot(String flowerPot) {
        this.flowerPot = flowerPot;
    }

    public String getFloralArrangement() {
        return floralArrangement;
    }

    public void setFloralArrangement(String floralArrangement) {
        this.floralArrangement = floralArrangement;
    }

    public String getFlower() {
        return flower;
    }

    public void setFlower(String flower) {
        this.flower = flower;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public int getUserQuantity() {
        return userQuantity;
    }

    public void setUserQuantity(int userQuantity) {
        this.userQuantity = userQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "name =" + name + ", style =" + style + ", size =" + size + ", flowerPot =" + flowerPot + ", floralArrangement =" + floralArrangement + ", flower =" + flower + ", accessory =" + accessory + ", productType =" + productType + ", promoMonth =" + promoMonth + ", status =" + status + ", promoYear =" + promoYear + ", quantity =" + quantity + ", price =" + price + ", discountRate =" + discountRate + ", userQuantity =" + userQuantity;
    }

    
    
    @Override
    public int compareTo(CatalogPackage o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
