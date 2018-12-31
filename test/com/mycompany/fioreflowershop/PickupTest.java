///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.fioreflowershop;
//
//import com.mycompany.fioreflowershop.adt.LinkedList;
//import com.mycompany.fioreflowershop.adt.ListInterface;
//import com.mycompany.fioreflowershop.adt.OrderListInterface;
//import com.mycompany.fioreflowershop.modal.CatalogOrders;
//import com.mycompany.fioreflowershop.modal.CustomizedPackage;
//import com.mycompany.fioreflowershop.modal.Order;
//import java.util.Date;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author Nicholas
// */
//public class PickupTest {
//    
//    public PickupTest() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//
//    /**
//     * Test of searchPickUp method, of class Pickup.
//     */
//    @Test
//    public void testSearchPickUp() {
//        System.out.println("searchPickUp");
//        OrderListInterface<CatalogOrders> catalogOrder = null;
//        Date date = null;
//        OrderListInterface<CustomizedPackage> customizeOrder = null;
//        Pickup.searchPickUp(catalogOrder, date, customizeOrder);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of sortPickupOrder method, of class Pickup.
//     */
//    @Test
//    public void testSortPickupOrder() {
//        System.out.println("sortPickupOrder");
//        OrderListInterface<CatalogOrders> catalogOrder = null;
//        OrderListInterface<CustomizedPackage> customizeOrder = null;
//        Pickup.sortPickupOrder(catalogOrder, customizeOrder);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of displaySortedPickup method, of class Pickup.
//     */
//    @Test
//    public void testDisplaySortedPickup() {
//        System.out.println("displaySortedPickup");
//        OrderListInterface<Order> orderedList = null;
//        Pickup.displaySortedPickup(orderedList);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of searchUserPickUp method, of class Pickup.
//     */
//    @Test
//    public void testSearchUserPickUp() {
//        System.out.println("searchUserPickUp");
//        String userID = "";
//        OrderListInterface<CatalogOrders> catalogOrder = null;
//        OrderListInterface<CustomizedPackage> customOrder = null;
//        OrderListInterface<Order> paidOrder = null;
//        Pickup.searchUserPickUp(userID, catalogOrder, customOrder, paidOrder);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of genReceipt method, of class Pickup.
//     */
//    @Test
//    public void testGenReceipt() {
//        System.out.println("genReceipt");
//        Order order = null;
//        double payAmt = 0.0;
//        double change = 0.0;
//        Pickup.genReceipt(order, payAmt, change);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//}
