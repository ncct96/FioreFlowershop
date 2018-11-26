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
    private String name, style, size, flower, accessory, productType, promoMonth;
    private int promoYear, quantity;
    private double price;
    private int discountRate;

    public CatalogPackage() {
    }

    public CatalogPackage(String name, String style, String size, String flower, String accessory, String productType, String promoMonth, int promoYear, int quantity, double price, int discountRate) {
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

    @Override
    public String toString() {
        return "CatalogPackage{" + "name=" + name + ", style=" + style + ", size=" + size + ", flower=" + flower + ", accessory=" + accessory + ", productType=" + productType + ", promoMonth=" + promoMonth + ", promoYear=" + promoYear + ", quantity=" + quantity + ", price=" + price + ", discountRate=" + discountRate + '}';
    }
    
    @Override
    public int compareTo(CatalogPackage o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
