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
 * @author Admin
 */
public class InvoiceHistoryTest {
    
    public InvoiceHistoryTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getDatepay method, of class InvoiceHistory.
     */
    @Test
    public void testGetDatepay() {
        System.out.println("getDatepay");
        InvoiceHistory instance = new InvoiceHistory();
        Date expResult = null;
        Date result = instance.getDatepay();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDatepay method, of class InvoiceHistory.
     */
    @Test
    public void testSetDatepay() {
        System.out.println("setDatepay");
        Date datepay = null;
        InvoiceHistory instance = new InvoiceHistory();
        instance.setDatepay(datepay);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInvoiceNumber method, of class InvoiceHistory.
     */
    @Test
    public void testGetInvoiceNumber() {
        System.out.println("getInvoiceNumber");
        InvoiceHistory instance = new InvoiceHistory();
        String expResult = "";
        String result = instance.getInvoiceNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInvoiceNumber method, of class InvoiceHistory.
     */
    @Test
    public void testSetInvoiceNumber() {
        System.out.println("setInvoiceNumber");
        String invoiceNumber = "";
        InvoiceHistory instance = new InvoiceHistory();
        instance.setInvoiceNumber(invoiceNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCatalogOrder method, of class InvoiceHistory.
     */
    @Test
    public void testGetCatalogOrder() {
        System.out.println("getCatalogOrder");
        InvoiceHistory instance = new InvoiceHistory();
        LinkedList<CatalogOrders> expResult = null;
        LinkedList<CatalogOrders> result = instance.getCatalogOrder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCatalogOrder method, of class InvoiceHistory.
     */
    @Test
    public void testSetCatalogOrder() {
        System.out.println("setCatalogOrder");
        LinkedList<CatalogOrders> catalogOrder = null;
        InvoiceHistory instance = new InvoiceHistory();
        instance.setCatalogOrder(catalogOrder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCorp method, of class InvoiceHistory.
     */
    @Test
    public void testGetCorp() {
        System.out.println("getCorp");
        InvoiceHistory instance = new InvoiceHistory();
        CorporateCustomer expResult = null;
        CorporateCustomer result = instance.getCorp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCorp method, of class InvoiceHistory.
     */
    @Test
    public void testSetCorp() {
        System.out.println("setCorp");
        CorporateCustomer corp = null;
        InvoiceHistory instance = new InvoiceHistory();
        instance.setCorp(corp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
