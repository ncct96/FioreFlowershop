/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.modal.InvoiceHistory;
import com.mycompany.fioreflowershop.modal.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class InvoicePaymentTest {
    
    public InvoicePaymentTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of invoiceMaintenance method, of class InvoicePayment.
     */
    @Test
    public void testInvoiceMaintenance() {
        System.out.println("invoiceMaintenance");
        InvoicePayment.invoiceMaintenance();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of totalPrice method, of class InvoicePayment.
     */
    @Test
    public void testTotalPrice() {
        System.out.println("totalPrice");
        double price = 0.0;
        double quantity = 0.0;
        double expResult = 0.0;
        double result = InvoicePayment.totalPrice(price, quantity);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of discountPrice method, of class InvoicePayment.
     */
    @Test
    public void testDiscountPrice() {
        System.out.println("discountPrice");
        double price = 0.0;
        double quantity = 0.0;
        double discountRate = 0.0;
        double expResult = 0.0;
        double result = InvoicePayment.discountPrice(price, quantity, discountRate);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewPaymentHistory1 method, of class InvoicePayment.
     */
    @Test
    public void testViewPaymentHistory1() {
        System.out.println("viewPaymentHistory1");
        InvoicePayment.viewPaymentHistory1();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewPaymentHistory2 method, of class InvoicePayment.
     */
    @Test
    public void testViewPaymentHistory2() {
        System.out.println("viewPaymentHistory2");
        InvoiceHistory ih = null;
        String invoiceID = "";
        InvoicePayment.viewPaymentHistory2(ih, invoiceID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateInvoiceP1 method, of class InvoicePayment.
     */
    @Test
    public void testGenerateInvoiceP1() {
        System.out.println("generateInvoiceP1");
        InvoicePayment.generateInvoiceP1();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of invoiceMenu method, of class InvoicePayment.
     */
    @Test
    public void testInvoiceMenu() {
        System.out.println("invoiceMenu");
        User user = null;
        InvoicePayment.invoiceMenu(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of invoiceMenuFooter method, of class InvoicePayment.
     */
    @Test
    public void testInvoiceMenuFooter() {
        System.out.println("invoiceMenuFooter");
        double totalPrice = 0.0;
        double discountPrice = 0.0;
        InvoicePayment.invoiceMenuFooter(totalPrice, discountPrice);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateInvoiceP2 method, of class InvoicePayment.
     */
    @Test
    public void testGenerateInvoiceP2() {
        System.out.println("generateInvoiceP2");
        User user = null;
        InvoicePayment.generateInvoiceP2(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of invoicePaymentP1 method, of class InvoicePayment.
     */
    @Test
    public void testInvoicePaymentP1() {
        System.out.println("invoicePaymentP1");
        InvoicePayment.invoicePaymentP1();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of invoicePaymentP2 method, of class InvoicePayment.
     */
    @Test
    public void testInvoicePaymentP2() {
        System.out.println("invoicePaymentP2");
        User user = null;
        InvoicePayment.invoicePaymentP2(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
