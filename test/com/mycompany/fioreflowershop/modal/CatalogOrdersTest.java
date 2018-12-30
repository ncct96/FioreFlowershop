/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

import com.mycompany.fioreflowershop.adt.CatalogPackageInterface;
import com.mycompany.fioreflowershop.adt.CatalogPackageList;
import com.mycompany.fioreflowershop.adt.LinkedList;
import java.util.Calendar;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zion Tseu
 */
public class CatalogOrdersTest {
    private CatalogOrders CO1, CO2;
    private int order = 1001;
    private String orderID = "CO" + String.valueOf(order);
    private String orderType = "Delivery";
    private boolean paymentStatus = false;
    private Date orderDate = new Date();
    private String orderStatus = "Processed";
    private double orderAmt = 0;
    private Date retrieveDate;
    private Date retrieveTime;
    private CatalogPackageInterface<CatalogPackage> catalogPackage = new CatalogPackageList<>();
    private User corporate = new CorporateCustomer();                    
    public CatalogOrdersTest() {
    }
    
    @Before
    public void setUp() {
        Calendar validRetrieveDate = Calendar.getInstance();
        validRetrieveDate.setTime(new Date()); // Now use today date.
        validRetrieveDate.add(Calendar.DATE, 2); // Adding 5 days
        retrieveDate = validRetrieveDate.getTime();
        retrieveTime = validRetrieveDate.getTime();
        
//        catalogPackage.addProduct(new CatalogPackage("FlowerStrong", "Stylish", "Small", "", "", "Rose", "Ribbons", "Product Type", "12", 2018, 10, 50, 20, 5));
//        catalogPackage.addProduct(new CatalogPackage("FlowerWeak", "Colourful", "Medium", "", "", "Lavender", "Bow Tie", "Product Type", "11", 2018, 20, 30, 10, 5));
//        catalogPackage.addProduct(new CatalogPackage("FlowerMedium", "Elegant", "Large", "", "", "Sunflower", "Belt", "Product Type", "11", 2018, 15, 40, 5, 2));
        
        corporate = new CorporateCustomer("Noice", "noice@example.com", "0123456789", "Petaling Jaya", "abcdef", "Not your business", 5000, true);
        
//        CO1 = new CatalogOrders(orderID, catalogPackage,orderType, orderDate, corporate, orderStatus, orderAmt, paymentStatus, retrieveDate, retrieveTime);
    }

    /**
     * Test of getOrderID method, of class CatalogOrders.
     */
    @Test
    public void testGetOrderID() {
        System.out.println("getOrderID");
        
        String expResult = "CO1001";
        String result = CO1.getOrderID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrderID method, of class CatalogOrders.
     */
    @Test
    public void testSetOrderID() {
        System.out.println("setOrderID");
        String orderID = "CO1002";       
        CO1.setOrderID(orderID);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getCatalogPack method, of class CatalogOrders.
     */
    @Test
    public void testGetCatalogPack() {
        System.out.println("getCatalogPack");       
        CatalogPackageInterface<CatalogPackage> expResult = catalogPackage;
        CatalogPackageInterface<CatalogPackage> result = CO1.getCatalogPack();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setCatalogPack method, of class CatalogOrders.
     */
    @Test
    public void testSetCatalogPack() {
        System.out.println("setCatalogPack");
        CatalogPackageInterface<CatalogPackage> catalogPack = catalogPackage;
        CatalogOrders instance = new CatalogOrders();
        instance.setCatalogPack(catalogPack);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getRetrieveTime method, of class CatalogOrders.
     */
    @Test
    public void testGetRetrieveTime() {
        System.out.println("getRetrieveTime");       
        Date expResult = retrieveTime;
        Date result = CO1.getRetrieveTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setRetrieveTime method, of class CatalogOrders.
     */
    @Test
    public void testSetRetrieveTime() {
        System.out.println("setRetrieveTime");
        Calendar validRetrieveDate = Calendar.getInstance();
        validRetrieveDate.setTime(new Date()); // Now use today date.
        validRetrieveDate.add(Calendar.DATE, 2); // Adding 5 days
        Date retrieveTime = validRetrieveDate.getTime();
        CatalogOrders instance = new CatalogOrders();
        instance.setRetrieveTime(retrieveTime);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of toString method, of class CatalogOrders.
     */
    @Test
    public void testToString() {
        System.out.println("toString");       
        String expResult = CO1.toString();
        String result = CO1.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
