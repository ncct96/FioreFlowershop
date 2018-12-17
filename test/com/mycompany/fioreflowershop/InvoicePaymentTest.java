/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.adt.LinkedList;
import com.mycompany.fioreflowershop.modal.CatalogOrders;
import com.mycompany.fioreflowershop.modal.CatalogPackage;
import com.mycompany.fioreflowershop.modal.CorporateCustomer;
import com.mycompany.fioreflowershop.modal.InvoiceHistory;
import com.mycompany.fioreflowershop.modal.User;
import java.util.Calendar;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class InvoicePaymentTest {
    LinkedList<InvoiceHistory> ih = InvoicePayment.getPaymentHistory();
    Date today = Calendar.getInstance().getTime();
    LinkedList<CatalogOrders> co = FioreFlowershop.getCatalogOrder();
    LinkedList<CatalogPackage> catalogPack1 = new LinkedList<>();
    CorporateCustomer cor = new CorporateCustomer("JunitTest", "junitTest@example.com", "0123456789", "Junit Test Address", "abcdef", "Junit Test Company", 5000, true);
    //Zion made changes here (added 2 "" for flower pot and flower arrangement parameter)
    CatalogPackage cp1 = new CatalogPackage("FlowerTest", "Stylish", "TestSize", "Vase", "Valentine", "RoseTest", "Ribbons", "Test Product", "12", 2018, 10, 50, 20, 5);
    CatalogOrders ct1 = new CatalogOrders("C1", catalogPack1, "Pick Up", today, cor, "Order Status", 308, false, today, today);
    InvoiceHistory ih1 = new InvoiceHistory(100, co, cor, today);
    
    public InvoicePaymentTest() {
        catalogPack1.add(cp1);
        co.add(ct1);
    }
    
    @Before
    public void setUp() {
    }
    
    /**
     * Test of totalPrice method, of class InvoicePayment.
     */
    @Test
    public void testTotalPrice() {
        System.out.println("\nCalculate Total Price");
        double price = 50.0;
        double quantity = 4.0;
        double expResult = 200.0;
        double result = InvoicePayment.totalPrice(price, quantity);
        assertEquals(expResult, result, 0.0);
        System.out.println("Price : "+price+", Quantity : "+quantity+", Result : " + result);
    }

    /**
     * Test of discountPrice method, of class InvoicePayment.
     */
    @Test
    public void testDiscountPrice() {
        System.out.println("\nCalculate Discount Price");
        double price = 50.0;
        double quantity = 4.0;
        double discountRate = 10.0;
        double expResult = price*quantity*10/100;
        double result = InvoicePayment.discountPrice(price, quantity, discountRate);
        assertEquals(expResult, result, 0.0);
        System.out.println("Price : "+price+", Quantity : "+quantity+", Result : " + result + "Discount : "+discountRate);
    }

    /**
     * Test of viewPaymentHistory2 method, of class InvoicePayment.
     */
    @Test
    public void testViewPaymentHistory2() {
        System.out.println("\nView Payment History");
        ih.add(ih1);
        String invoiceID = "IH100";
        InvoicePayment.viewPaymentHistory2(ih1, invoiceID);
    }
    
    /**
     * Test of generateInvoiceP2 method, of class InvoicePayment.
     */
    @Test
    public void testGenerateInvoiceP2() {
        System.out.println("\nGenerate Invoice");
        InvoicePayment.generateInvoiceP2(cor);
    }

    /**
     * Test of invoicePaymentP2 method, of class InvoicePayment.
     */
//    @Test
//    public void testInvoicePaymentP2() {
//        System.out.println("invoicePaymentP2");
//        InvoicePayment.invoicePaymentP2(cor);
//    }
    
}
