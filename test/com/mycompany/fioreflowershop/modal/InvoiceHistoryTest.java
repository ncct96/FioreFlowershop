/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

import com.mycompany.fioreflowershop.adt.LinkedList;
import java.util.Calendar;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class InvoiceHistoryTest {
    public InvoiceHistory instance; public Date datepay;
    public LinkedList<CatalogOrders> catalogOrd;
    public CorporateCustomer corpCust;
    public InvoiceHistoryTest() {
        datepay = Calendar.getInstance().getTime();
        catalogOrd = new LinkedList<>();
        instance = new InvoiceHistory();
        corpCust = new CorporateCustomer();
        instance.setDatepay(datepay);
        instance.setInvoiceNumber("IH100");
        instance.setCatalogOrder(catalogOrd);
        instance.setCorp(corpCust);
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
        Date expResult = Calendar.getInstance().getTime();
        Date result = instance.getDatepay();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setDatepay method, of class InvoiceHistory.
     */
    @Test
    public void testSetDatepay() {
        System.out.println("setDatepay");
        instance.setDatepay(datepay);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getInvoiceNumber method, of class InvoiceHistory.
     */
    @Test
    public void testGetInvoiceNumber() {
        System.out.println("getInvoiceNumber");
        String expResult = "IH100";
        String result = instance.getInvoiceNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setInvoiceNumber method, of class InvoiceHistory.
     */
    @Test
    public void testSetInvoiceNumber() {
        System.out.println("setInvoiceNumber");
        String invoiceNumber = "IH100";
        instance.setInvoiceNumber(invoiceNumber);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getCatalogOrder method, of class InvoiceHistory.
     */
    @Test
    public void testGetCatalogOrder() {
        System.out.println("getCatalogOrder");
        LinkedList<CatalogOrders> expResult = new LinkedList<>();
        LinkedList<CatalogOrders> result = instance.getCatalogOrder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setCatalogOrder method, of class InvoiceHistory.
     */
    @Test
    public void testSetCatalogOrder() {
        System.out.println("setCatalogOrder");
        LinkedList<CatalogOrders> catalogOrder = null;
        instance.setCatalogOrder(catalogOrd);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getCorp method, of class InvoiceHistory.
     */
    @Test
    public void testGetCorp() {
        System.out.println("getCorp");
        CorporateCustomer expResult = new CorporateCustomer();
        CorporateCustomer result = instance.getCorp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setCorp method, of class InvoiceHistory.
     */
    @Test
    public void testSetCorp() {
        System.out.println("setCorp");
        CorporateCustomer corp = null;
        instance.setCorp(corpCust);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
