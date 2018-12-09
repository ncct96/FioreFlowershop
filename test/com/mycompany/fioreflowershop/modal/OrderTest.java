/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nicholas
 */
public class OrderTest {
    
    public OrderTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getOrderType method, of class Order.
     */
    @Test
    public void testGetOrderType() {
        System.out.println("getOrderType");
        Order instance = new Order();
        String expResult = "";
        String result = instance.getOrderType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class Order.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        Order instance = new Order();
        User expResult = null;
        User result = instance.getUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUser method, of class Order.
     */
    @Test
    public void testSetUser() {
        System.out.println("setUser");
        User user = null;
        Order instance = new Order();
        instance.setUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrderType method, of class Order.
     */
    @Test
    public void testSetOrderType() {
        System.out.println("setOrderType");
        String orderType = "";
        Order instance = new Order();
        instance.setOrderType(orderType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrderDate method, of class Order.
     */
    @Test
    public void testGetOrderDate() {
        System.out.println("getOrderDate");
        Order instance = new Order();
        Date expResult = null;
        Date result = instance.getOrderDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrderDate method, of class Order.
     */
    @Test
    public void testSetOrderDate() {
        System.out.println("setOrderDate");
        Date date = null;
        Order instance = new Order();
        instance.setOrderDate(date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrderStatus method, of class Order.
     */
    @Test
    public void testGetOrderStatus() {
        System.out.println("getOrderStatus");
        Order instance = new Order();
        String expResult = "";
        String result = instance.getOrderStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrderStatus method, of class Order.
     */
    @Test
    public void testSetOrderStatus() {
        System.out.println("setOrderStatus");
        String orderStatus = "";
        Order instance = new Order();
        instance.setOrderStatus(orderStatus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrderAmt method, of class Order.
     */
    @Test
    public void testGetOrderAmt() {
        System.out.println("getOrderAmt");
        Order instance = new Order();
        double expResult = 0.0;
        double result = instance.getOrderAmt();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrderAmt method, of class Order.
     */
    @Test
    public void testSetOrderAmt() {
        System.out.println("setOrderAmt");
        double orderAmt = 0.0;
        Order instance = new Order();
        instance.setOrderAmt(orderAmt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPaymentStatus method, of class Order.
     */
    @Test
    public void testIsPaymentStatus() {
        System.out.println("isPaymentStatus");
        Order instance = new Order();
        boolean expResult = false;
        boolean result = instance.isPaymentStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPaymentStatus method, of class Order.
     */
    @Test
    public void testSetPaymentStatus() {
        System.out.println("setPaymentStatus");
        boolean paymentStatus = false;
        Order instance = new Order();
        instance.setPaymentStatus(paymentStatus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRetrieveDate method, of class Order.
     */
    @Test
    public void testGetRetrieveDate() {
        System.out.println("getRetrieveDate");
        Order instance = new Order();
        Date expResult = null;
        Date result = instance.getRetrieveDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPaymentTime method, of class Order.
     */
    @Test
    public void testGetPaymentTime() {
        System.out.println("getPaymentTime");
        Order instance = new Order();
        Date expResult = null;
        Date result = instance.getPaymentTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPaymentTime method, of class Order.
     */
    @Test
    public void testSetPaymentTime() {
        System.out.println("setPaymentTime");
        Date paymentTime = null;
        Order instance = new Order();
        instance.setPaymentTime(paymentTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRetrieveDate method, of class Order.
     */
    @Test
    public void testSetRetrieveDate() {
        System.out.println("setRetrieveDate");
        Date retrieveDate = null;
        Order instance = new Order();
        instance.setRetrieveDate(retrieveDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
