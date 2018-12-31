/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

import java.util.Calendar;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nicholas
 */
public class OrderTest {
    
    public String orderType = "Delivery";
    public String orderStatus = "Processing";
    public User user = new User("ncct96", "ncct96@gmail.com", "01659191413", "PV13", "9608");
    public boolean isPaymentStatus = true;
    public Calendar validRetrieveDate = Calendar.getInstance();
    public Date orderDate, retrieveDate;
    public Order order;
    
    public OrderTest() {
        validRetrieveDate.setTime(new Date()); // Now use today date.
        validRetrieveDate.add(Calendar.DATE, 2); // Adding 5 days
        retrieveDate = validRetrieveDate.getTime();
        orderDate = validRetrieveDate.getTime();
        
        order = new Order(orderType, orderDate, user, orderStatus, 5, isPaymentStatus, retrieveDate);
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
        String expResult = "Delivery";
        String result = order.getOrderType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getUser method, of class Order.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        User expResult = user;
        User result = order.getUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setUser method, of class Order.
     */
    @Test
    public void testSetUser() {
        System.out.println("setUser");
        User user = new User("ncct", "ncct96@gmail.com", "01659191413", "PV13", "9608");
        order.setUser(user);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setOrderType method, of class Order.
     */
    @Test
    public void testSetOrderType() {
        System.out.println("setOrderType");
        String orderType = "Pick Up";
        order.setOrderType(orderType);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getOrderDate method, of class Order.
     */
    @Test
    public void testGetOrderDate() {
        System.out.println("getOrderDate");
        Date expResult = orderDate;
        Date result = order.getOrderDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setOrderDate method, of class Order.
     */
    @Test
    public void testSetOrderDate() {
        System.out.println("setOrderDate");
        Calendar validRetrieveDate = Calendar.getInstance();
        validRetrieveDate.setTime(new Date()); // Now use today date.
        validRetrieveDate.add(Calendar.DATE, 2); // Adding 5 days
        Date retrieveTime = validRetrieveDate.getTime();
        order.setOrderDate(retrieveTime);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getOrderStatus method, of class Order.
     */
    @Test
    public void testGetOrderStatus() {
        System.out.println("getOrderStatus");
        String expResult = "Processing";
        String result = order.getOrderStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setOrderStatus method, of class Order.
     */
    @Test
    public void testSetOrderStatus() {
        System.out.println("setOrderStatus");
        String orderStatus = "Done";
        order.setOrderStatus(orderStatus);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getOrderAmt method, of class Order.
     */
    @Test
    public void testGetOrderAmt() {
        System.out.println("getOrderAmt");
        double expResult = 5.0;
        double result = order.getOrderAmt();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setOrderAmt method, of class Order.
     */
    @Test
    public void testSetOrderAmt() {
        System.out.println("setOrderAmt");
        double orderAmt = 10.0;
        order.setOrderAmt(orderAmt);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of isPaymentStatus method, of class Order.
     */
    @Test
    public void testIsPaymentStatus() {
        System.out.println("isPaymentStatus");
        boolean expResult = true;
        boolean result = order.isPaymentStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setPaymentStatus method, of class Order.
     */
    @Test
    public void testSetPaymentStatus() {
        System.out.println("setPaymentStatus");
        boolean paymentStatus = false;
        order.setPaymentStatus(paymentStatus);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getRetrieveDate method, of class Order.
     */
    @Test
    public void testGetRetrieveDate() {
        System.out.println("getRetrieveDate");
        Date expResult = retrieveDate;
        Date result = order.getDeliveryDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRetrieveDate method, of class Order.
     */
    @Test
    public void testSetRetrieveDate() {
        System.out.println("setRetrieveDate");
        Calendar validRetrieveDate = Calendar.getInstance();
        validRetrieveDate.setTime(new Date()); // Now use today date.
        validRetrieveDate.add(Calendar.DATE, 2); // Adding 5 days
        Date retrieveDate = validRetrieveDate.getTime();
        order.setDeliveryDate(retrieveDate);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
