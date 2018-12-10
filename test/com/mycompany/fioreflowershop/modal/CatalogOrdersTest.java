/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

import com.mycompany.fioreflowershop.adt.LinkedList;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zion Tseu
 */
public class CatalogOrdersTest {
    
    public CatalogOrdersTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getOrderID method, of class CatalogOrders.
     */
    @Test
    public void testGetOrderID() {
        System.out.println("getOrderID");
        CatalogOrders instance = new CatalogOrders();
        String expResult = "";
        String result = instance.getOrderID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrderID method, of class CatalogOrders.
     */
    @Test
    public void testSetOrderID() {
        System.out.println("setOrderID");
        String orderID = "";
        CatalogOrders instance = new CatalogOrders();
        instance.setOrderID(orderID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCatalogPack method, of class CatalogOrders.
     */
    @Test
    public void testGetCatalogPack() {
        System.out.println("getCatalogPack");
        CatalogOrders instance = new CatalogOrders();
        LinkedList<CatalogPackage> expResult = null;
        LinkedList<CatalogPackage> result = instance.getCatalogPack();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCatalogPack method, of class CatalogOrders.
     */
    @Test
    public void testSetCatalogPack() {
        System.out.println("setCatalogPack");
        LinkedList<CatalogPackage> catalogPack = null;
        CatalogOrders instance = new CatalogOrders();
        instance.setCatalogPack(catalogPack);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRetrieveTime method, of class CatalogOrders.
     */
    @Test
    public void testGetRetrieveTime() {
        System.out.println("getRetrieveTime");
        CatalogOrders instance = new CatalogOrders();
        Date expResult = null;
        Date result = instance.getRetrieveTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRetrieveTime method, of class CatalogOrders.
     */
    @Test
    public void testSetRetrieveTime() {
        System.out.println("setRetrieveTime");
        Date retrieveTime = null;
        CatalogOrders instance = new CatalogOrders();
        instance.setRetrieveTime(retrieveTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class CatalogOrders.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        CatalogOrders instance = new CatalogOrders();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
