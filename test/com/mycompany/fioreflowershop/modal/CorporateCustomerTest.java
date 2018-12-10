/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

import com.mycompany.fioreflowershop.FioreFlowershop;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class CorporateCustomerTest {
    CorporateCustomer instance;
    public CorporateCustomerTest() {
        instance = new CorporateCustomer();
        instance.setCompany("Company");
        instance.setCreditSpent(1000);
        instance.setMonthlyLimit(2000);
        instance.setPaymentStatus(true);
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
        boolean paymentStat = true;
        instance.setPaymentStatus(paymentStat);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getPaymentStatus method, of class CorporateCustomer.
     */
    @Test
    public void testGetPaymentStatus() {
        System.out.println("getPaymentStatus");
        boolean expResult = true;
        boolean result = instance.getPaymentStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getCreditSpent method, of class CorporateCustomer.
     */
    @Test
    public void testGetCreditSpent() {
        System.out.println("getCreditSpent");
        double expResult = 1000.0;
        double result = instance.getCreditSpent();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setCreditSpent method, of class CorporateCustomer.
     */
    @Test
    public void testSetCreditSpent() {
        System.out.println("setCreditSpent");
        double creditSpent = 1000.0;
        instance.setCreditSpent(creditSpent);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getCompany method, of class CorporateCustomer.
     */
    @Test
    public void testGetCompany() {
        System.out.println("getCompany");
        String expResult = "Company";
        String result = instance.getCompany();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setCompany method, of class CorporateCustomer.
     */
    @Test
    public void testSetCompany() {
        System.out.println("setCompany");
        String company = "Company";
        instance.setCompany(company);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getMonthlyLimit method, of class CorporateCustomer.
     */
    @Test
    public void testGetMonthlyLimit() {
        System.out.println("getMonthlyLimit");
        int expResult = 2000;
        int result = instance.getMonthlyLimit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setMonthlyLimit method, of class CorporateCustomer.
     */
    @Test
    public void testSetMonthlyLimit() {
        System.out.println("setMonthlyLimit");
        int monthlyLimit = 2000;
        instance.setMonthlyLimit(monthlyLimit);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of toString method, of class CorporateCustomer.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "\n"+FioreFlowershop.ConsoleColors.BLUE+"Username : "+null+FioreFlowershop.ConsoleColors.RESET
                +"\n"+FioreFlowershop.ConsoleColors.BLUE+"Email : "+null+FioreFlowershop.ConsoleColors.RESET
                +"\n"+FioreFlowershop.ConsoleColors.BLUE+"Contact Number : "+null+FioreFlowershop.ConsoleColors.RESET
                +"\n"+FioreFlowershop.ConsoleColors.BLUE+"Address : "+null+FioreFlowershop.ConsoleColors.RESET
                +"\n"+FioreFlowershop.ConsoleColors.BLUE+"Password : "+null+FioreFlowershop.ConsoleColors.RESET
                +"\n"+FioreFlowershop.ConsoleColors.BLUE+"Company Name : Company"+FioreFlowershop.ConsoleColors.RESET
                +"\n"+FioreFlowershop.ConsoleColors.BLUE+"Credit Spent : 1000.0"+FioreFlowershop.ConsoleColors.RESET
                +"\n"+FioreFlowershop.ConsoleColors.BLUE+"Preset Credit Limit : 2000"+FioreFlowershop.ConsoleColors.RESET;
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
