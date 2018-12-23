/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.adt.CatalogPackageInterface;
import com.mycompany.fioreflowershop.adt.CatalogPackageList;
import com.mycompany.fioreflowershop.adt.LinkedList;
import com.mycompany.fioreflowershop.adt.OrderList;
import com.mycompany.fioreflowershop.adt.OrderListInterface;
import com.mycompany.fioreflowershop.modal.CatalogOrders;
import com.mycompany.fioreflowershop.modal.CatalogPackage;
import com.mycompany.fioreflowershop.modal.Consumer;
import com.mycompany.fioreflowershop.modal.CorporateCustomer;
import com.mycompany.fioreflowershop.modal.CustomizedPackage;
import com.mycompany.fioreflowershop.modal.Order;
import java.util.Calendar;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nicholas
 */
public class DeliveryTest {

    // Initialize Lists
    private static CatalogPackageInterface<CatalogPackage> catalogPack1 = new CatalogPackageList<>();
    private static OrderListInterface<CatalogOrders> catalogOrder = new OrderList<>();
    private static OrderListInterface<Order> testOrder = new OrderList<>();
    private static OrderListInterface<CustomizedPackage> customizeOrder = new OrderList<>();
    private static OrderListInterface<Order> result = new OrderList<>();

    Consumer testCon = new Consumer("testingCon", "testingCon", "testing@example.com", "0125566922", "Pahang");
    CorporateCustomer testCorp = new CorporateCustomer("testingCorp", "1estingCorp@example.com", "0123456789", "PV 21", "abcdef", "Testing Corp", 5000, true);

    // Initialize Date
    Date todayDate = new Date();
    Date inputDate;
    Calendar cal = Calendar.getInstance();

    // Initialize Some Orders
    CatalogOrders ct1 = new CatalogOrders("C1", catalogPack1, "Delivery", todayDate, testCon, "Order Status", 308, false, todayDate, todayDate);

    public DeliveryTest() {

        cal.set(Calendar.YEAR, 2018);
        cal.set(Calendar.DAY_OF_MONTH, 13);
        cal.set(Calendar.MONTH, 10);
        cal.set(Calendar.HOUR_OF_DAY, 03);
        cal.set(Calendar.MINUTE, 30);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date inputDate = cal.getTime();

        catalogOrder.addOrder(ct1);

        testOrder.addOrder(ct1);

        customizeOrder = FioreFlowershop.getReadyOrder();
    }

    @Before
    public void setUp() {

    }

    /**
     * Test of genReceipt method, of class Delivery.
     */
    @Test
    public void testGenReceipt() {
        System.out.println("genReceipt");
        CatalogOrders ct2 = new CatalogOrders("C2", catalogPack1, "Delivery", inputDate, testCon, "Order Status", 200, false, inputDate, inputDate);
        ct2.setPaymentTime(todayDate);
        double payAmt = 400;
        double change = 200;
        Delivery.genReceipt(ct2, payAmt, change);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setPaymentStatus method, of class Delivery.
     */
    @Test
    public void testSetPaymentStatus() {
        System.out.println("setPaymentStatus");
        boolean expectedResult = true;
        CatalogOrders ct2 = new CatalogOrders("C2", catalogPack1, "Delivery", inputDate, testCon, "Order Status", 200, false, inputDate, inputDate);
        Delivery.setPaymentStatus(ct2);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(expectedResult, ct2.isPaymentStatus());
    }

    /**
     * Test of CalculatePayment method, of class Delivery.
     */
    @Test
    public void testCalculatePayment() {
        System.out.println("CalculatePayment");
        double payAmt = 300.0;
        CatalogOrders ct2 = new CatalogOrders("C2", catalogPack1, "Delivery", inputDate, testCon, "Order Status", 200, false, inputDate, inputDate);
        double expResult = 100.0;
        double result = Delivery.CalculatePayment(payAmt, ct2);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

}
