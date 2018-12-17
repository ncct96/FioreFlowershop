/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.adt.LinkedList;
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

    Consumer testCon = new Consumer("testingCon", "testingCon", "testing@example.com", "0125566922", "Pahang");
    CorporateCustomer testCorp = new CorporateCustomer("testingCorp", "testingCorp@example.com", "0123456789", "PV 21", "abcdef", "Testing Corp", 5000, true);

    // Initialize Date
    Date todayDate = new Date();
    Calendar cal = Calendar.getInstance();

    // Initialize Lists
    private static LinkedList<CatalogPackage> catalogPack1 = new LinkedList<>();
    private static LinkedList<CatalogOrders> catalogOrder = new LinkedList<>();
    private static LinkedList<Order> testOrder = new LinkedList<>();
    private static LinkedList<CustomizedPackage> customizeOrder = new LinkedList<>();
    private static LinkedList<Order> result = new LinkedList<>();

    // Initialize Testing Package
    CatalogPackage cp1 = new CatalogPackage("FlowerStrong", "Stylish", "Small", "Rose", "Ribbons", "Product Type", "12", 2018, 10, 50, 20, 5);
    CatalogPackage cp2 = new CatalogPackage("FlowerWeak", "Colourful", "Medium", "Lavender", "Bow Tie", "Product Type", "11", 2018, 20, 30, 10, 4);
    CatalogPackage cp3 = new CatalogPackage("FlowerMedium", "Elegant", "Large", "Sunflower", "Belt", "Product Type", "11", 2018, 15, 40, 5, 6);

    public DeliveryTest() {

        FioreFlowershop.initializePackages();

        cal.set(Calendar.YEAR, 2018);
        cal.set(Calendar.DAY_OF_MONTH, 13);
        cal.set(Calendar.MONTH, 10);
        cal.set(Calendar.HOUR_OF_DAY, 03);
        cal.set(Calendar.MINUTE, 30);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date a = cal.getTime();

        catalogPack1.add(cp1);
        catalogPack1.add(cp2);
        catalogPack1.add(cp3);

        // Initialize Some Orders
        CatalogOrders ct1 = new CatalogOrders("C1", catalogPack1, "Delivery", todayDate, testCon, "Order Status", 308, false, todayDate, todayDate);
        CatalogOrders ct2 = new CatalogOrders("C2", catalogPack1, "Delivery", a, testCon, "Order Status", 200, false, a, a);

        catalogOrder.add(ct1);
        catalogOrder.add(ct2);

        testOrder.add(ct1);

        customizeOrder = FioreFlowershop.getReadyOrder();
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of searchDelivery method, of class Delivery.
     */
    @Test
    public void testSearchDelivery() {
        System.out.println("searchDelivery");
        LinkedList<CatalogOrders> catalogOrder = DeliveryTest.catalogOrder;
        Date date = new Date();
        LinkedList<Order> expectedResult = testOrder;
        result = Delivery.searchDelivery(catalogOrder, date, customizeOrder);
        assertEquals(expectedResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of sortDeliveryOrder method, of class Delivery.
     */
    @Test
    public void testSortDeliveryOrder() {
        System.out.println("sortDeliveryOrder");
        LinkedList<CatalogOrders> catalogOrder = null;
        LinkedList<CustomizedPackage> customizeOrder = null;
        Delivery.sortDeliveryOrder(catalogOrder, customizeOrder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displaySortedDelivery method, of class Delivery.
     */
    @Test
    public void testDisplaySortedDelivery() {
        System.out.println("displaySortedDelivery");
        LinkedList<Order> orderedList = null;
        Delivery.displaySortedDelivery(orderedList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortRouteDelivery method, of class Delivery.
     */
    @Test
    public void testSortRouteDelivery() throws Exception {
        System.out.println("sortRouteDelivery");
        LinkedList<CatalogOrders> catalogOrder = null;
        LinkedList<CustomizedPackage> customizeOrder = null;
        String shopAddress = "Test";
        Delivery.sortRouteDelivery(catalogOrder, customizeOrder, shopAddress);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortRoute method, of class Delivery.
     */
    @Test
    public void testSortRoute() throws Exception {
        System.out.println("sortRoute");
        LinkedList<Order> sortedList = null;
        String shopAddress = "";
        Delivery.sortRoute(sortedList, shopAddress);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displaySortRoute method, of class Delivery.
     */
    @Test
    public void testDisplaySortRoute() {
        System.out.println("displaySortRoute");
        TSPSolver solver = null;
        LinkedList<Order> dest = null;
        String shopAddress = "";
        Delivery.displaySortRoute(solver, dest, shopAddress);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchUserDelivery method, of class Delivery.
     */
    @Test
    public void testSearchUserDelivery() {
        System.out.println("searchUserDelivery");
        String userID = "";
        LinkedList<CatalogOrders> catalogOrder = null;
        LinkedList<CustomizedPackage> customOrder = null;
        LinkedList<Order> paidOrder = null;
        Delivery.searchUserDelivery(userID, catalogOrder, customOrder, paidOrder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of genReceipt method, of class Delivery.
     */
    @Test
    public void testGenReceipt() {
        System.out.println("genReceipt");
        Order order = null;
        double payAmt = 0.0;
        double change = 0.0;
        Delivery.genReceipt(order, payAmt, change);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchPaidDelivery method, of class Delivery.
     */
    @Test
    public void testSearchPaidDelivery() {
        System.out.println("searchPaidDelivery");
        LinkedList<Order> paidOrder = null;
        Delivery.searchPaidDelivery(paidOrder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
