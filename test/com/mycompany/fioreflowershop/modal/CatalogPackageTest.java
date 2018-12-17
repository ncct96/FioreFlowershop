/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Woo Guo Hau
 */
public class CatalogPackageTest {
    
    private CatalogPackage instance;
    
    public CatalogPackageTest() {
    }
    
    @Before
    public void setUp() {
        //Zion made changes here (added 2 "" for flower pot and flower arrangement parameter)
        this.instance = new CatalogPackage("Package 1", "Style 1", "Small", "", "", "Flower 1", "Bear 1", "Flower arrangement", "December", 2018, 10, 90.00, 10, 5);
    }

    /**
     * Test of getName method, of class CatalogPackage.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "Package 1";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class CatalogPackage.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Package 1";
        instance.setName(name);
    }

    /**
     * Test of getStyle method, of class CatalogPackage.
     */
    @Test
    public void testGetStyle() {
        System.out.println("getStyle");
        String expResult = "Style 1";
        String result = instance.getStyle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStyle method, of class CatalogPackage.
     */
    @Test
    public void testSetStyle() {
        System.out.println("setStyle");
        String style = "Style 1";
        instance.setStyle(style);
    }

    /**
     * Test of getSize method, of class CatalogPackage.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        String expResult = "Small";
        String result = instance.getSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSize method, of class CatalogPackage.
     */
    @Test
    public void testSetSize() {
        System.out.println("setSize");
        String size = "Small";
        instance.setSize(size);
    }

    /**
     * Test of getFlower method, of class CatalogPackage.
     */
    @Test
    public void testGetFlower() {
        System.out.println("getFlower");
        String expResult = "Flower 1";
        String result = instance.getFlower();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFlower method, of class CatalogPackage.
     */
    @Test
    public void testSetFlower() {
        System.out.println("setFlower");
        String flower = "Flower 1";
        instance.setFlower(flower);
    }

    /**
     * Test of getAccessory method, of class CatalogPackage.
     */
    @Test
    public void testGetAccessory() {
        System.out.println("getAccessory");
        String expResult = "Bear 1";
        String result = instance.getAccessory();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAccessory method, of class CatalogPackage.
     */
    @Test
    public void testSetAccessory() {
        System.out.println("setAccessory");
        String accessory = "Bear 1";
        instance.setAccessory(accessory);
    }

    /**
     * Test of getProductType method, of class CatalogPackage.
     */
    @Test
    public void testGetProductType() {
        System.out.println("getProductType");
        String expResult = "Flower arrangement";
        String result = instance.getProductType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setProductType method, of class CatalogPackage.
     */
    @Test
    public void testSetProductType() {
        System.out.println("setProductType");
        String productType = "Flower arrangement";
        instance.setProductType(productType);
    }

    /**
     * Test of getPromoMonth method, of class CatalogPackage.
     */
    @Test
    public void testGetPromoMonth() {
        System.out.println("getPromoMonth");
        String expResult = "December";
        String result = instance.getPromoMonth();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPromoMonth method, of class CatalogPackage.
     */
    @Test
    public void testSetPromoMonth() {
        System.out.println("setPromoMonth");
        String promoMonth = "December";
        instance.setPromoMonth(promoMonth);
    }

    /**
     * Test of getPromoYear method, of class CatalogPackage.
     */
    @Test
    public void testGetPromoYear() {
        System.out.println("getPromoYear");
        int expResult = 2018;
        int result = instance.getPromoYear();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPromoYear method, of class CatalogPackage.
     */
    @Test
    public void testSetPromoYear() {
        System.out.println("setPromoYear");
        int promoYear = 2018;
        instance.setPromoYear(promoYear);
    }

    /**
     * Test of getQuantity method, of class CatalogPackage.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        int expResult = 10;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setQuantity method, of class CatalogPackage.
     */
    @Test
    public void testSetQuantity() {
        System.out.println("setQuantity");
        int quantity = 10;
        instance.setQuantity(quantity);
    }

    /**
     * Test of getPrice method, of class CatalogPackage.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        double expResult = 90.00;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPrice method, of class CatalogPackage.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        double price = 90.00;
        instance.setPrice(price);
    }

    /**
     * Test of getDiscountRate method, of class CatalogPackage.
     */
    @Test
    public void testGetDiscountRate() {
        System.out.println("getDiscountRate");
        int expResult = 10;
        int result = instance.getDiscountRate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDiscountRate method, of class CatalogPackage.
     */
    @Test
    public void testSetDiscountRate() {
        System.out.println("setDiscountRate");
        int discountRate = 10;
        instance.setDiscountRate(discountRate);
    }

    /**
     * Test of getUserQuantity method, of class CatalogPackage.
     */
    @Test
    public void testGetUserQuantity() {
        System.out.println("getUserQuantity");
        int expResult = 5;
        int result = instance.getUserQuantity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserQuantity method, of class CatalogPackage.
     */
    @Test
    public void testSetUserQuantity() {
        System.out.println("setUserQuantity");
        int userQuantity = 5;
        instance.setUserQuantity(userQuantity);
    }
}
