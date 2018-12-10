/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

import com.mycompany.fioreflowershop.adt.LinkedList;
import com.mycompany.fioreflowershop.adt.ListIteratorInterface;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Chiu Peeng
 */
public class CustomizedPackageTest {

    public Item style = new Item("Fan", 10);
    public Item size = new Item("Small", 1);
    public Item flower = new Item("Sunflowers", 250, 10);
    public Item accessory = new Item("None", 0, 1);
    public Item priority = new Item("Flexi", 1, 6);
    public Item deliveryType = new Item("Pick up", 0);
    public String deliveryDate, orderDate;
    ListIteratorInterface<Item> testFlowers = new LinkedList<>();
    Date todayDate, addDate;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Consumer customer = new Consumer("Johan", "abcdef", "ncct66@gmail.com", "0165919413", "Gelanggang Squash IAB Genting Highlands, Genting Highlands, 69000 Genting Highlands, Pahang");
    public CustomizedPackage testPackage = new CustomizedPackage(style, size, accessory, priority, deliveryType, customer, false);

    public CustomizedPackageTest() {
        testFlowers.add(new Item("Sunflowers", 250, 10));
        testFlowers.add(new Item("White Roses", 250, 10));
        testFlowers.add(new Item("Tulips", 450, 50));
        todayDate = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, priority.getQuantity());
        addDate = cal.getTime();

        deliveryDate = df.format(addDate);
        orderDate = df.format(todayDate);
        testPackage.setFlowerList(testFlowers);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getDeliveryType method, of class CustomizedPackage.
     */
    @Test
    public void testGetDeliveryType() {
        System.out.println("getDeliveryType");
        Item expResult = deliveryType;
        Item result = testPackage.getDeliveryType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrderDate method, of class CustomizedPackage.
     */
    @Test
    public void testGetOrderDate() {
        System.out.println("getOrderDate");
        Date expResult = new Date();
        try {
            expResult = df.parse(orderDate);
        } catch (ParseException ex) {
            Logger.getLogger(CustomizedPackageTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date result = testPackage.getOrderDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrderDateString method, of class CustomizedPackage.
     */
    @Test
    public void testGetOrderDateString() {
        System.out.println("getOrderDateString");
        String expResult = orderDate;
        String result = testPackage.getOrderDateString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUser method, of class CustomizedPackage.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        User expResult = customer;
        User result = testPackage.getUser();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStyle method, of class CustomizedPackage.
     */
    @Test
    public void testGetStyle() {
        System.out.println("getStyle");
        Item expResult = style;
        Item result = testPackage.getStyle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrderID method, of class CustomizedPackage.
     */
    @Test
    public void testGetOrderID() {
        System.out.println("getOrderID");
        String expResult = "CP4";
        String result = testPackage.getOrderID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeliveryDate method, of class CustomizedPackage.
     */
    @Test
    public void testGetDeliveryDate() {
        System.out.println("getDeliveryDate");
        Date expResult= new Date();
        try {
            expResult = df.parse(deliveryDate);
        } catch (ParseException ex) {
            Logger.getLogger(CustomizedPackageTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date result = testPackage.getDeliveryDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeliveryDateString method, of class CustomizedPackage.
     */
    @Test
    public void testGetDeliveryDateString() {
        System.out.println("getDeliveryDateString");
        String expResult = deliveryDate;
        String result = testPackage.getDeliveryDateString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSize method, of class CustomizedPackage.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        Item expResult = size;
        Item result = testPackage.getSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFlowerList method, of class CustomizedPackage.
     */
    @Test
    public void testGetFlowerList() {
        System.out.println("getFlowerList");;
        assertEquals(testFlowers, testPackage.getFlowerList());
    }

    /**
     * Test of getAccessory method, of class CustomizedPackage.
     */
    @Test
    public void testGetAccessory() {
        System.out.println("getAccessory");
        Item expResult = accessory;
        Item result = testPackage.getAccessory();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPriority method, of class CustomizedPackage.
     */
    @Test
    public void testGetPriority() {
        System.out.println("getPriority");
        Item expResult = priority;
        Item result = testPackage.getPriority();
        assertEquals(expResult, result);
    }

    /**
     * Test of CalculateOrder method, of class CustomizedPackage.
     */
    @Test
    public void testCalculateOrder() {
        System.out.println("CalculateOrder");
        double expResult = 510.0;
        double result = testPackage.CalculateOrder();
        assertEquals(expResult, result, 0.0);
    }

}
