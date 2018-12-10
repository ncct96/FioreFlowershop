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
 * @author Admin
 */
public class CorporateCustomerTest {
    
    public CorporateCustomerTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of setPaymentStatus method, of class CorporateCustomer.
     */
    @Test
    public void testSetPaymentStatus() {
        System.out.println("setPaymentStatus");
        boolean paymentStat = false;
        CorporateCustomer instance = new CorporateCustomer();
        instance.setPaymentStatus(paymentStat);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPaymentStatus method, of class CorporateCustomer.
     */
    @Test
    public void testGetPaymentStatus() {
        System.out.println("getPaymentStatus");
        CorporateCustomer instance = new CorporateCustomer();
        boolean expResult = false;
        boolean result = instance.getPaymentStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCreditSpent method, of class CorporateCustomer.
     */
    @Test
    public void testGetCreditSpent() {
        System.out.println("getCreditSpent");
        CorporateCustomer instance = new CorporateCustomer();
        double expResult = 0.0;
        double result = instance.getCreditSpent();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCreditSpent method, of class CorporateCustomer.
     */
    @Test
    public void testSetCreditSpent() {
        System.out.println("setCreditSpent");
        double creditSpent = 0.0;
        CorporateCustomer instance = new CorporateCustomer();
        instance.setCreditSpent(creditSpent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompany method, of class CorporateCustomer.
     */
    @Test
    public void testGetCompany() {
        System.out.println("getCompany");
        CorporateCustomer instance = new CorporateCustomer();
        String expResult = "";
        String result = instance.getCompany();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCompany method, of class CorporateCustomer.
     */
    @Test
    public void testSetCompany() {
        System.out.println("setCompany");
        String company = "";
        CorporateCustomer instance = new CorporateCustomer();
        instance.setCompany(company);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMonthlyLimit method, of class CorporateCustomer.
     */
    @Test
    public void testGetMonthlyLimit() {
        System.out.println("getMonthlyLimit");
        CorporateCustomer instance = new CorporateCustomer();
        int expResult = 0;
        int result = instance.getMonthlyLimit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMonthlyLimit method, of class CorporateCustomer.
     */
    @Test
    public void testSetMonthlyLimit() {
        System.out.println("setMonthlyLimit");
        int monthlyLimit = 0;
        CorporateCustomer instance = new CorporateCustomer();
        instance.setMonthlyLimit(monthlyLimit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class CorporateCustomer.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        CorporateCustomer instance = new CorporateCustomer();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
