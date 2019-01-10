/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.google.maps.errors.ApiException;
import com.mycompany.fioreflowershop.adt.*;
import com.mycompany.fioreflowershop.modal.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chiu Peeng
 */
public class FioreFlowershop {

    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";
    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static DateFormat timeFormat = new SimpleDateFormat("HH:mm");

    // Lines 95 - 98, 154-158
    private static ConsumerInterface<Consumer> consumer = new ConsumerADT<>();
    private static CorporateInterface<CorporateCustomer> corporate = new CorporateADT<>();
    private static UserInterface<User> user = new UserADT<>();
//    private static SortedListInterface<User> testSort = new SortedLinkList<>();
    private static SortedListInterface<User> testSort = new SortedLinkList<>();
    private static OrderListInterface<Order> pickupOrder = new OrderList<Order>();
    private static OrderListInterface<Order> deliveryOrder = new OrderList<Order>();
    private static OrderListInterface<Order> paidOrder = new OrderList<Order>();
    private static Scanner s = new Scanner(System.in);

    private static ItemCatalogue itemCatalogue = new ItemCatalogue();
    private static CustomizePackageQueueInterface<CustomizedPackage> customizedPackages = new CustomizePackageQueue<>();
    private static OrderListInterface<Order> readyOrders = new OrderList<>();

    //Catalog Maintenance part
    private static CatalogPackageInterface<CatalogPackage> normalPackage = new CatalogPackageList<>();
    private static CatalogPackageInterface<CatalogPackage> discountedPackage = new CatalogPackageList<>();

    private static ShoppingCartListInterface<CatalogOrders> shoppingCart = new ShoppingCartList<>();
    private static OrderListInterface<CatalogOrders> catalogOrder = new OrderList<>();

    private static CatalogPackageInterface<CatalogPackage> catalogPack1 = new CatalogPackageList<>();
    private static CatalogPackageInterface<CatalogPackage> catalogPack2 = new CatalogPackageList<>();

    private static String[] origin = {"Taiping", "Penang", "Cheras", "Johor"};
    private static String[] dest = {"Taiping", "Penang", "Cheras", "Johor"};
    private static final String shopAddress = "Taiping";

    private static int firstrun = 0;

    public static void main(String[] args) {
        String test = "";
        test.compareTo("");
        ++firstrun;

        if (firstrun == 1) {
//            CatalogOrder.initializeData(catalogOrder, readyOrders);
            initializePackages();
        }

        userTypeSelection();
    }

    public static void initializePackages() {
        //FOR TESTING ONLY, TOUCHING IS PROHIBITED, YOU HAVE BEEN WARNED
//        try{
//            Date todayDate = new Date(); Date todayFormatDate;
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            todayFormatDate = dateFormat.parse(dateFormat.format(todayDate));
//            System.out.println(todayFormatDate);
//        }catch(Exception e){
//            
//        }
        Date todayDate = new Date();
        //consumer initialize
        CorporateCustomer cc1 = new CorporateCustomer("Noice", "noice@example.com", "0123456789", "No Address", "abcdef", "Not your business", 5000, true);
        CorporateCustomer cc2 = new CorporateCustomer("NotNoice", "notnoice@example.com", "0123456781", "PV13", "abc123", "Some Merchant", 5000, true);
        Consumer c1 = new Consumer("ceekay", "abcdef123", "ceekay@example.com", "0125566922", "Johor");
        Consumer c2 = new Consumer("testing", "testing", "testing@example.com", "0125566922", "Penang");
        Consumer c3 = new Consumer("testing1", "testing", "testing1@example.com", "0125566922", "Cheras");
        Consumer c4 = new Consumer("testing2", "testing", "testing2@example.com", "0125566922", "Pahang");
        Consumer c5 = new Consumer("manager", "abc", "manager@example.com", "012", "lmao");
        Consumer c6 = new Consumer("manager1", "abc", "manager1@example.com", "012", "lmao");

        user.addUser(cc2);
        user.addUser(cc1);
        user.addUser(c1);
        user.addUser(c2);
        user.addUser(c5);
        user.addUser(c3);

        consumer.addConsumer(c1);
        consumer.addConsumer(c2);
        consumer.addConsumer(c3);
        consumer.addConsumer(c4);

        corporate.addCorporate(cc1);
        corporate.addCorporate(cc2);
        corporate.getCorporate(1).setCreditSpent(4500);
        corporate.getCorporate(2).setCreditSpent(1500);

        //Initialize shopping cart
//        CatalogPackage cp1 = new CatalogPackage("FlowerStrong", "Stylish", "Small", "", "", "Rose", "Ribbons", "Product Type", "12", 2018, 10, 50, 20, 5);
//        CatalogPackage cp2 = new CatalogPackage("FlowerWeak", "Colourful", "Medium", "", "", "Lavender", "Bow Tie", "Product Type", "11", 2018, 20, 30, 10, 4);
//        CatalogPackage cp3 = new CatalogPackage("FlowerMedium", "Elegant", "Large", "", "", "Sunflower", "Belt", "Product Type", "11", 2018, 15, 40, 5, 6);
//        catalogPack1.addProduct(cp1);
//        catalogPack1.addProduct(cp2);
//        catalogPack2.addProduct(cp1);
//        catalogPack2.addProduct(cp2);
//        catalogPack2.addProduct(cp3);
//        CatalogOrders ct1 = new CatalogOrders("C1", catalogPack1, "Pick Up", todayDate, cc1, "Order Status", 308, false, todayDate, todayDate);
//        CatalogOrders ct2 = new CatalogOrders("C2", catalogPack2, "Pick Up", todayDate, cc2, "Order Status", 200, false, todayDate, todayDate);
//        CatalogOrders ct3 = new CatalogOrders("C3", catalogPack1, "Delivery", todayDate, c3, "Order Status", 308, false, todayDate, todayDate);
//        CatalogOrders ct4 = new CatalogOrders("C4", catalogPack1, "Delivery", todayDate, c4, "Order Status", 308, false, todayDate, todayDate);
//        CatalogOrders ct5 = new CatalogOrders("C5", catalogPack1, "Delivery", todayDate, c3, "Order Status", 308, false, todayDate, todayDate);
//        CatalogOrders ct6 = new CatalogOrders("C6", catalogPack1, "Delivery", todayDate, c2, "Order Status", 308, false, todayDate, todayDate);
//        CatalogOrders ct7 = new CatalogOrders("C7", catalogPack1, "Delivery", todayDate, c1, "Order Status", 308, false, todayDate, todayDate);
//
//        catalogOrder.addOrder(ct1);
//        catalogOrder.addOrder(ct2);
//        catalogOrder.addOrder(ct3);
//        catalogOrder.addOrder(ct4);
//        catalogOrder.addOrder(ct5);
//        catalogOrder.addOrder(ct6);
//        catalogOrder.addOrder(ct7);
        ItemListInterface<Item> styles = new ItemList<>();
        ItemListInterface<Item> sizes = new ItemList<>();
        ItemListInterface<Item> flowers = new ItemList<>();
        ItemListInterface<Item> accessories = new ItemList<>();
        ItemListInterface<Item> priorities = new ItemList<>();
        ItemListInterface<Item> deliveryTypes = new ItemList<>();
        ItemListInterface<Item> flowerPot = new ItemList<>();
        ItemListInterface<String> season = new ItemList<>();

        styles.addItem(new Item("Fan", 10));
        styles.addItem(new Item("Elliptical", 10));
        styles.addItem(new Item("Vertical", 10));
        styles.addItem(new Item("Horizontal", 10));
        styles.addItem(new Item("Triangular", 10));

        sizes.addItem(new Item("Small", 1));
        sizes.addItem(new Item("Medium", 2));
        sizes.addItem(new Item("Large", 4));

        flowers.addItem(new Item("Sunflowers", 250, 10));
        flowers.addItem(new Item("Red Roses", 300, 20));
        flowers.addItem(new Item("White Roses", 250, 10));
        flowers.addItem(new Item("Tulips", 450, 50));
        flowers.addItem(new Item("Daffodils", 200, 20));

        accessories.addItem(new Item("None", 0, 1));
        accessories.addItem(new Item("Name Card", 5, 20));
        accessories.addItem(new Item("Bears", 50, 5));
        accessories.addItem(new Item("Woven Basket", 35, 10));

        priorities.addItem(new Item("Flexi", 1, 6));
        priorities.addItem(new Item("Normal", 1.5, 4));
        priorities.addItem(new Item("Express", 3, 2));

        deliveryTypes.addItem(new Item("Pick Up", 0));
        deliveryTypes.addItem(new Item("Delivery", 10));

        season.addItem("Valentine");
        season.addItem("Graduation");
        season.addItem("Grand opening");
        season.addItem("Visit patient");
        season.addItem("Funeral");

        flowerPot.addItem(new Item("Drinking glass", 10.00, 10));
        flowerPot.addItem(new Item("Glass bottle", 10.00, 9));
        flowerPot.addItem(new Item("Woolden box", 10.00, 15));
        flowerPot.addItem(new Item("Jar", 10.00, 9));
        flowerPot.addItem(new Item("Bud vase", 10.00, 12));

        itemCatalogue.setStyles(styles);
        itemCatalogue.setSizes(sizes);
        itemCatalogue.setFlowers(flowers);
        itemCatalogue.setAccessories(accessories);
        itemCatalogue.setPriorities(priorities);
        itemCatalogue.setDeliveryTypes(deliveryTypes);
        itemCatalogue.setFlowerPot(flowerPot);
        itemCatalogue.setSeason(season);

        ListIteratorInterface<Item> testFlowers = new LinkedList<>();
        testFlowers.add(flowers.getItem(1));
        testFlowers.add(flowers.getItem(3));
        testFlowers.add(flowers.getItem(5));

        //Initialization of Catalog Package
        //Normal catalog
        CatalogPackage normalPackage1 = new CatalogPackage("Package1", styles.getItem(1), sizes.getItem(1), accessories.getItem(1), "Fresh flower", "", 0, 100.00, 0, "Active", "10 10 10");
        CatalogPackage normalPackage2 = new CatalogPackage("Package2", styles.getItem(2), sizes.getItem(2), accessories.getItem(2), "Fresh flower", "", 0, 150.00, 0, "Active", "5 5 5");
        CatalogPackage normalPackage3 = new CatalogPackage("Package3", styles.getItem(3), sizes.getItem(3), accessories.getItem(3), "Bouquets", "", 0, 120.00, 0, "Active", "1 1 1");
        CatalogPackage normalPackage4 = new CatalogPackage("Package4", styles.getItem(4), sizes.getItem(1), accessories.getItem(4), "Bouquets", "", 0, 150.00, 0, "Active", "4 4 4");
        CatalogPackage normalPackage5 = new CatalogPackage("Package5", styles.getItem(2), sizes.getItem(2), season.getItem(1), flowerPot.getItem(1), accessories.getItem(1), "Flower arrangement", "", 0, 200.00, 0, "Active", "3 3 3");
        CatalogPackage normalPackage6 = new CatalogPackage("Package6", styles.getItem(3), sizes.getItem(3), season.getItem(1), flowerPot.getItem(1), accessories.getItem(2), "Flower arrangement", "", 0, 250.00, 0, "Active", "9 9 9");

        //Discounted catalog
        CatalogPackage discountPackage1 = new CatalogPackage("Package1", styles.getItem(1), sizes.getItem(1), accessories.getItem(1), "Fresh flower", "January", 2019, 100.00, 20, "Active", "10 10 10");
        CatalogPackage discountPackage2 = new CatalogPackage("Package2", styles.getItem(2), sizes.getItem(2), accessories.getItem(2), "Fresh flower", "January", 2019, 150.00, 50, "Active", "5 5 5");
        CatalogPackage discountPackage3 = new CatalogPackage("Package3", styles.getItem(3), sizes.getItem(3), accessories.getItem(3), "Bouquets", "January", 2019, 120.00, 70, "Active", "1 1 1");
        CatalogPackage discountPackage4 = new CatalogPackage("Package4", styles.getItem(4), sizes.getItem(1), accessories.getItem(4), "Bouquets", "January", 2019, 150.00, 10, "Active", "4 4 4");
        CatalogPackage discountPackage5 = new CatalogPackage("Package5", styles.getItem(2), sizes.getItem(2), season.getItem(1), flowerPot.getItem(1), accessories.getItem(1), "Flower arrangement", "January", 2019, 200.00, 30, "Active", "3 3 3");
        CatalogPackage discountPackage6 = new CatalogPackage("Package6", styles.getItem(3), sizes.getItem(3), season.getItem(1), flowerPot.getItem(1), accessories.getItem(2), "Flower arrangement", "January", 2019, 250.00, 40, "Active", "9 9 9");

        for (int i = 1; i <= testFlowers.getTotalEntries(); i++) {
            normalPackage1.getFlowerList().add(testFlowers.getItem(i));
            normalPackage2.getFlowerList().add(testFlowers.getItem(i));
            normalPackage3.getFlowerList().add(testFlowers.getItem(i));
            normalPackage4.getFlowerList().add(testFlowers.getItem(i));
            normalPackage5.getFlowerList().add(testFlowers.getItem(i));
            normalPackage6.getFlowerList().add(testFlowers.getItem(i));
            discountPackage1.getFlowerList().add(testFlowers.getItem(i));
            discountPackage2.getFlowerList().add(testFlowers.getItem(i));
            discountPackage3.getFlowerList().add(testFlowers.getItem(i));
            discountPackage4.getFlowerList().add(testFlowers.getItem(i));
            discountPackage5.getFlowerList().add(testFlowers.getItem(i));
            discountPackage6.getFlowerList().add(testFlowers.getItem(i));
        }

        normalPackage.addProduct(normalPackage1);
        normalPackage.addProduct(normalPackage2);
        normalPackage.addProduct(normalPackage3);
        normalPackage.addProduct(normalPackage4);
        normalPackage.addProduct(normalPackage5);
        normalPackage.addProduct(normalPackage6);

        discountedPackage.addProduct(discountPackage1);
        discountedPackage.addProduct(discountPackage2);
        discountedPackage.addProduct(discountPackage3);
        discountedPackage.addProduct(discountPackage4);
        discountedPackage.addProduct(discountPackage5);
        discountedPackage.addProduct(discountPackage6);

        //Initialize Catalog Order      
        catalogPack1.addProduct(normalPackage2);
        catalogPack1.addProduct(discountPackage4);
        catalogPack2.addProduct(normalPackage3);
        catalogPack2.addProduct(discountPackage2);
        catalogPack2.addProduct(discountPackage6);
        //String orderID, CatalogPackageInterface<CatalogPackage> catalogPack, Item priority,
        //String orderType, Date orderDate, Date orderTime, User user, String orderStatus, double orderAmt,
        //boolean paymentStatus, Date retrieveDate, Date retrieveTime      
        Date orderTime1 = new Date();
        Date orderTime2 = new Date();
        Date orderTime3 = new Date();
        Date orderTime4 = new Date();
        Date orderTime5 = new Date();
        Calendar orderTime_2 = Calendar.getInstance();
        Calendar orderTime_1 = Calendar.getInstance();
       
        orderTime_1.setTime(new Date()); // Now use today date.
        orderTime_1.add(Calendar.DATE, 1); // Adding 1 days
        Date orderDate = orderTime_1.getTime();
        
        orderTime_1.add(Calendar.DATE, 3); // Adding 3 days
        Date retrieveDate = orderTime_1.getTime();             
                
        orderTime_2.setTime(new Date()); // Now use today date.       
        orderTime_2.add(Calendar.HOUR_OF_DAY, 1); // adds one hour       
        String timeOrder = timeFormat.format(orderTime_2.getTime());
         
        orderTime_2.add(Calendar.HOUR_OF_DAY, 2); // adds 2 hour    
        String timeOrder2 = timeFormat.format(orderTime_2.getTime());
        
        orderTime_2.add(Calendar.HOUR_OF_DAY, 1); // adds 1 hour 
        String timeOrder3 = timeFormat.format(orderTime_2.getTime());
        
        orderTime_2.add(Calendar.HOUR_OF_DAY, 1); // adds 1 hour 
        String timeOrder4 = timeFormat.format(orderTime_2.getTime());
        
        orderTime_2.add(Calendar.HOUR_OF_DAY, 5); // adds 5 hour 
        String timeOrder5 = timeFormat.format(orderTime_2.getTime());
        try {
            orderTime1 = timeFormat.parse(timeOrder);
            orderTime2 = timeFormat.parse(timeOrder2);
            orderTime3 = timeFormat.parse(timeOrder3);
            orderTime4 = timeFormat.parse(timeOrder4);
            orderTime5 = timeFormat.parse(timeOrder5);
        } catch (ParseException ex) {
            Logger.getLogger(FioreFlowershop.class.getName()).log(Level.SEVERE, null, ex);
        }
        CatalogOrders C01 = new CatalogOrders("CO1001", catalogPack1, priorities.getItem(1), "Pick Up", todayDate, orderTime1, cc1, "Order Status", 560, false, todayDate, todayDate);
        CatalogOrders CO2 = new CatalogOrders("CO1002", catalogPack2, priorities.getItem(1), "Pick Up", todayDate, orderTime2, cc2, "Order Status", 200, false, todayDate, todayDate);
        CatalogOrders CO3 = new CatalogOrders("CO1003", catalogPack1, priorities.getItem(1), "Delivery", todayDate, orderTime3, c3, "Order Status", 360, false, retrieveDate, todayDate);
        CatalogOrders CO4 = new CatalogOrders("CO1004", catalogPack1, priorities.getItem(2), "Delivery", orderDate, orderTime4, c4, "Order Status", 380, false, retrieveDate, todayDate);
        CatalogOrders CO5 = new CatalogOrders("CO1005", catalogPack1, priorities.getItem(3), "Delivery", orderDate, orderTime5, c3, "Order Status", 320, false, retrieveDate, todayDate);

        catalogOrder.addOrder(C01);
        catalogOrder.addOrder(CO2);
        catalogOrder.addOrder(CO3);
        catalogOrder.addOrder(CO4);
        catalogOrder.addOrder(CO5);

        
        /*<------ Begin CP's Part -------->*/
        CustomizedPackage package1 = new CustomizedPackage(styles.getItem(2), sizes.getItem(3), accessories.getItem(4), priorities.getItem(1), deliveryTypes.getItem(1), c1, false);
        CustomizedPackage package2 = new CustomizedPackage(styles.getItem(1), sizes.getItem(2), accessories.getItem(3), priorities.getItem(1), deliveryTypes.getItem(2), c2, false);
        CustomizedPackage package3 = new CustomizedPackage(styles.getItem(3), sizes.getItem(1), accessories.getItem(1), priorities.getItem(1), deliveryTypes.getItem(2), c1, false);
        CustomizedPackage package4 = new CustomizedPackage(styles.getItem(4), sizes.getItem(2), accessories.getItem(1), priorities.getItem(2), deliveryTypes.getItem(1), c2, false);
        CustomizedPackage package5 = new CustomizedPackage(styles.getItem(1), sizes.getItem(1), accessories.getItem(2), priorities.getItem(3), deliveryTypes.getItem(1), c2, false);
        CustomizedPackage package6 = new CustomizedPackage(styles.getItem(2), sizes.getItem(3), accessories.getItem(2), priorities.getItem(3), deliveryTypes.getItem(2), c2, false);
        CustomizedPackage package7 = new CustomizedPackage(styles.getItem(2), sizes.getItem(3), accessories.getItem(2), priorities.getItem(3), deliveryTypes.getItem(2), c3, false);
        CustomizedPackage package8 = new CustomizedPackage(styles.getItem(2), sizes.getItem(3), accessories.getItem(2), priorities.getItem(3), deliveryTypes.getItem(2), c4, false);
        
        for (int i = 1; i <= testFlowers.getTotalEntries(); i++) {
            package1.getFlowerList().add(testFlowers.getItem(i));
            package2.getFlowerList().add(testFlowers.getItem(i));
            package3.getFlowerList().add(testFlowers.getItem(i));
            package4.getFlowerList().add(testFlowers.getItem(i));
            package5.getFlowerList().add(testFlowers.getItem(i));
            package6.getFlowerList().add(testFlowers.getItem(i));
        }

        customizedPackages.enqueuePackage(package1);
        customizedPackages.enqueuePackage(package2);
        customizedPackages.enqueuePackage(package3);
        customizedPackages.enqueuePackage(package4);
        customizedPackages.enqueuePackage(package5);
        customizedPackages.enqueuePackage(package6);
        customizedPackages.enqueuePackage(package7);
        customizedPackages.enqueuePackage(package8);
        Item style = new Item("Elliptical", 10);
        Item size = new Item("Small", 1);
        Item flower = new Item("Sunflowers", 250, 10);
        Item accessory = new Item("None", 0, 1);

        Item flexi = new Item("Flexi", 1, 6);
        Item normal = new Item("Normal", 1.5, 4);
        Item express = new Item("Express", 3, 2);
        Item delivery = new Item("Pick Up", 0);
        
        SortOrders.sortCustomizedOrders(customizedPackages);
        
        readyOrders.addOrder(customizedPackages.dequeuePackage());
        readyOrders.addOrder(customizedPackages.dequeuePackage());
        readyOrders.addOrder(customizedPackages.dequeuePackage());
        readyOrders.addOrder(customizedPackages.dequeuePackage());
        
        SortOrders.sortAllOrders(readyOrders);
        /*<------ End CP's Part -------->*/
    }

    public static void gotoCustomizePackage(Consumer customerLoggedIn, int selection) {
        if (selection == 1) {
            CustomizePackage.customizePackageControl(itemCatalogue, customerLoggedIn, customizedPackages);
        } else if (selection == 2) {
            CustomizePackage.viewOrderHistory(customerLoggedIn, customizedPackages, readyOrders);
        }
    }

    public static void gotoCatalogOrders(Consumer customerLoggedIn, CorporateCustomer corporateLoggedIn) {
        //Zion part need change since tutor told me use one array so my multiple array is gone

        if (corporateLoggedIn == null) {
            CatalogOrder.CustomerOrderMain(catalogOrder, readyOrders, customerLoggedIn, normalPackage, discountedPackage, itemCatalogue);
        } else if (customerLoggedIn == null) {
            CatalogOrder.CorporateOrderMain(catalogOrder, readyOrders, corporateLoggedIn, normalPackage, discountedPackage, itemCatalogue);
        }
    }

    public static void userTypeSelection() {
        while (true) {
            System.out.println("\nWELCOME TO FIORE FLOWERSHOP SDN BHD !");
            System.out.println("\nPlease SELECT The Type Of User.");
            System.out.println(GREEN + "[1] " + RESET + "Customer");
            System.out.println(GREEN + "[2] " + RESET + "Staff");
            System.out.println(GREEN + "[3] " + RESET + "Exit System");
            System.out.print("Selection: ");
            int userTypeChoice = s.nextInt();
            s.nextLine();
            if (userTypeChoice == 1) {
                CustomerMaintenance.customerOptions();
            } else if (userTypeChoice == 2) {
                staffTypeSelection();
            } else {
                System.exit(0);
            }
        }
    }

    public static void staffTypeSelection() {
        while (true) {
            System.out.println("\nPlease Select The Type of Staff.");
            System.out.println(GREEN + "[1] " + RESET + "Manager");
            System.out.println(GREEN + "[2] " + RESET + "Inventory Clerk");
            System.out.println(GREEN + "[3] " + RESET + "Counter Staff");
            System.out.println(GREEN + "[4] " + RESET + "Florist");
            System.out.println(GREEN + "[5] " + RESET + "Delivery Staff");
            System.out.println(GREEN + "[6] " + RESET + "Log Out");
            System.out.print("Selection: ");
            int staffTypeChoice = s.nextInt();
            s.nextLine();
            if (staffTypeChoice == 1) {
                manager();
            }
            if (staffTypeChoice == 2) {
                inventoryClerk();
            }
            if (staffTypeChoice == 3) {
                counterStaff();
            }
            if (staffTypeChoice == 4) {
                florist();
            }
            if (staffTypeChoice == 5) {
                try {
                    deliveryStaff();
                } catch (ApiException | InterruptedException | IOException ex) {
                    Logger.getLogger(FioreFlowershop.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                break;
            }
        }
    }

    public static void manager() {
        while (true) {
            System.out.println("\nPlease Select The Options Below.");
            System.out.println(GREEN + "[1] " + RESET + "Edit Customer Details");
            System.out.println(GREEN + "[2] " + RESET + "Create Corporate Customer Account");
            System.out.println(GREEN + "[3] " + RESET + "Remove Customer Account");
            System.out.println(GREEN + "[4] " + RESET + "Modify the product catalogue");
            System.out.println(GREEN + "[5] " + RESET + "Back");
            System.out.print("Selection: ");
            int managerChoice = s.nextInt();
            s.nextLine();

            if (managerChoice == 1) {
                CustomerMaintenance.staffEditType();
            } else if (managerChoice == 2) {
                CustomerMaintenance.staffCreateCorporate();
            } else if (managerChoice == 3) {
                CustomerMaintenance.staffRemoveCust();
            } else if (managerChoice == 4) {
                CatalogMaintenance.catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
            } else {
                break;
            }
        }
    }

    public static void inventoryClerk() {
        while (true) {
            String navigationMsg;
            CatalogMaintenance.stockNotification(itemCatalogue);
            System.out.println("\nPlease Select One Of The Options Below.");
            System.out.println(GREEN + "[1] " + RESET + "Check stock quantity.");
            System.out.println(GREEN + "[2] " + RESET + "Restock product quantity.");
            System.out.println(GREEN + "[3] " + RESET + "Edit customize floral arrangement customization options");
            System.out.println(GREEN + "[4] " + RESET + "Back");
            System.out.print("Selection: ");
            int inventoryClerkChoice = s.nextInt();
            s.nextLine();
            if (inventoryClerkChoice == 1) {
                navigationMsg = "Current stock";
                CatalogMaintenance.displayStockAvailability(navigationMsg, itemCatalogue);
                CatalogMaintenance.backToInventoryClerkMenu(navigationMsg); //Seperate for JUnit testing purpose
            } else if (inventoryClerkChoice == 2) {
                navigationMsg = "Restock quantity";
                CatalogMaintenance.displayStockAvailability(navigationMsg, itemCatalogue);
                CatalogMaintenance.restockQuantity(itemCatalogue);
            } else if (inventoryClerkChoice == 3) {
                CustomizePackageMaintenance.itemsMenu(itemCatalogue, customizedPackages);
            } else {
                break;
            }
        }
    }

    public static void counterStaff() {
        while (true) {
            System.out.println("\nPlease Select One Of The Options Below.");
            System.out.println(GREEN + "[1] " + RESET + "Corporate Customer Invoice Maintenance");
            System.out.println(GREEN + "[2] " + RESET + "Order Pickup/Delivery");
            System.out.println(GREEN + "[3] " + RESET + "Consumer Payment Management");
            System.out.println(GREEN + "[4] " + RESET + "View Sales Order");
            System.out.println(GREEN + "[5] " + RESET + "Back");
            System.out.print("Selection: ");
            int counterStaffChoice = s.nextInt();
            s.nextLine();
            if (counterStaffChoice == 1) {
                InvoicePayment.invoiceMaintenance();
            } else if (counterStaffChoice == 2) {
                orderMenu();
            } else if (counterStaffChoice == 3) {
                //MISSING FUNCTION
            } else if (counterStaffChoice == 4) {
                //MISSING FUNCTION
            } else if (counterStaffChoice == 5) {
                userTypeSelection();
            } else {
                break;
            }
        }
    }

    public static void florist() {
        while (true) {
            System.out.println("\nPlease Select One Of The Options Below.");
            System.out.println(GREEN + "[1] " + RESET + "View Order List");
            System.out.println(GREEN + "[2] " + RESET + "Generate Itemized Bill");
            System.out.println(GREEN + "[3] " + RESET + "View Customized Floral Arrangement Jobs");
            System.out.println(GREEN + "[4] " + RESET + "Back");
            System.out.print("Selection: ");
            int floristChoice = s.nextInt();
            s.nextLine();
            if (floristChoice == 1) {
                orderMenu();
            } else if (floristChoice == 2) {
                //MISSING FUNCTION
            } else if (floristChoice == 3) {
                CustomizePackageMaintenance.updateOrders(customizedPackages, readyOrders);
            } else {
                break;
            }
        }
    }

    public static void deliveryStaff() throws ApiException, InterruptedException, IOException {
        while (true) {
            System.out.println("\nPlease Select One Of The Options Below.");
            System.out.println(GREEN + "[1] " + RESET + "View Ongoing Delivery List");
            System.out.println(GREEN + "[2] " + RESET + "Payment for Delivery Order");
            System.out.println(GREEN + "[3] " + RESET + "View Delivered Order");
            System.out.println(GREEN + "[4] " + RESET + "View Delivery Payments");
            System.out.println(GREEN + "[5] " + RESET + "Back");
            System.out.print("Selection: ");
            int deliveryStaffChoice = s.nextInt();
            s.nextLine();
            if (deliveryStaffChoice == 1) {

                System.out.println(GREEN + "[1]" + RESET + "Today's Sorted Delivery Order List");
                System.out.println(GREEN + "[2]" + RESET + "Search Sorted Delivery Order List by Date");
                int deliveryChoice = s.nextInt();
                s.nextLine();

                if (deliveryChoice == 1) {
                    try {
                        Delivery.sortRouteDelivery(readyOrders, shopAddress);
                    } catch (ApiException | InterruptedException | IOException ex) {
                        Logger.getLogger(FioreFlowershop.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (deliveryChoice == 2) {
                    try {
                        System.out.print("Please enter date to search (yyyy-MM-dd): ");

                        String dateStr = s.nextLine();

                        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

                        Date date = dateformat.parse(dateStr);

                        Delivery.searchSortRouteDelivery(readyOrders, shopAddress, date);
                    } catch (ParseException ex) {
                        Logger.getLogger(FioreFlowershop.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (deliveryStaffChoice == 2) {
                try {
                    Delivery.sortRouteDelivery(readyOrders, shopAddress);

                } catch (ApiException | InterruptedException | IOException ex) {
                    Logger.getLogger(FioreFlowershop.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (deliveryStaffChoice == 3) {
                //MISSING FUNCTION
            } else if (deliveryStaffChoice == 4) {
                Delivery.searchPaidDelivery(paidOrder);
            } else if (deliveryStaffChoice == 5) {
                break;
            } else {
                break;
            }
        }
    }

    public static void orderMenu() {
        while (true) {
            System.out.println("\nPlease Select The Options Below.");
            System.out.println(GREEN + "[1] " + RESET + "Pick Up Order");
            System.out.println(GREEN + "[2] " + RESET + "Delivery Order");
            System.out.println(GREEN + "[3] " + RESET + "Back");
            System.out.print("Selection: ");

            int choice = s.nextInt();
            s.nextLine();

            if (choice == 1) {
                System.out.println("\nPlease Select One Of The Options Below.");
                System.out.println("1. Today's Pick Up Order List");
                System.out.println("2. Search Pick Up Order List by Date");
                System.out.println("3. Search Pick Up Order by User ID to Pay");
                System.out.println("Enter your option: ");

                int pickupChoice = s.nextInt();

                if (pickupChoice == 1) {
                    Pickup.sortPickupOrder(readyOrders);
                } else if (pickupChoice == 2) {

                    try {
                        s.nextLine();

                        System.out.print("Please enter date to search (yyyy-MM-dd): ");

                        String dateStr = s.nextLine();

                        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

                        Date date = dateformat.parse(dateStr);

                        Pickup.searchPickUp(date, readyOrders);

                    } catch (ParseException ex) {
                        Logger.getLogger(FioreFlowershop.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (pickupChoice == 3) {
                    s.nextLine();

                    System.out.print("Please enter User ID to pay: ");

                    String userID = s.nextLine();

                    Pickup.searchUserPickUp(userID, readyOrders, paidOrder);
                }
            } else if (choice == 2) {
                while (true) {
                    System.out.println("\nPlease Select The Options Below.");
                    System.out.println("[1] Today's Delivery Order List");
                    System.out.println("[2] Search Delivery Order List by Date");
                    System.out.println("[3] Back");
                    System.out.println("Enter your option: ");

                    int deliveryChoice = s.nextInt();

                    if (deliveryChoice == 1) {
                        Delivery.sortDeliveryOrder(readyOrders);
                    } else if (deliveryChoice == 2) {
                        try {
                            s.nextLine();

                            System.out.print("Please enter date to search (yyyy-MM-dd): ");

                            String dateStr = s.nextLine();

                            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

                            Date date = dateformat.parse(dateStr);

                            Delivery.searchDelivery(date, readyOrders);

                        } catch (ParseException ex) {
                            Logger.getLogger(FioreFlowershop.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        break;
                    }
                }
            } else {
                break;
            }
        }
    }

    public static void sortDeliveryRoute() {
        while (true) {
            System.out.println("\nPlease Select One Of The Options Below.");
            System.out.println(GREEN + "[1] " + RESET + "Today's Delivery Order List");
            System.out.println(GREEN + "[2] " + RESET + "Search Delivery Order List by Date");
            System.out.println(GREEN + "[3] " + RESET + "Back");
            System.out.print("Selection: ");

            int deliveryChoice = s.nextInt();

            if (deliveryChoice == 1) {
                Delivery.sortDeliveryOrder(readyOrders);
                try {
                    deliveryStaff();

                } catch (ApiException | InterruptedException | IOException ex) {
                    Logger.getLogger(FioreFlowershop.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (deliveryChoice == 2) {
                try {
                    s.nextLine();

                    System.out.print("Please enter date to search (yyyy-MM-dd): ");

                    String dateStr = s.nextLine();

                    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

                    Date date = dateformat.parse(dateStr);

                    Delivery.searchDelivery(date, readyOrders);

                } catch (ParseException ex) {
                    Logger.getLogger(FioreFlowershop.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                break;
            }
        }
    }

    //GETTER SETTER FOR CUSTOMER LIST
    public static void setCustomer(ListInterface<Consumer> consumer) {
        consumer = consumer;
    }

    public static ConsumerInterface<Consumer> getConsumerList() {
        return consumer;
    }

    public static CorporateInterface<CorporateCustomer> getCorporateList() {
        return corporate;
    }

    public static UserInterface<User> getUserList() {
        return user;
    }

    public static ShoppingCartListInterface<CatalogOrders> getShoppingCart() {
        return shoppingCart;

    }

    public static OrderListInterface<CatalogOrders> getCatalogOrder() {
        return catalogOrder;
    }

    public static OrderListInterface<Order> getReadyOrder() {
        return readyOrders;
    }

    public static OrderListInterface<Order> getPaidOrder() {
        return paidOrder;
    }

    public class ConsoleColors {

        // Reset
        public static final String RESET = "\033[0m";  // Text Reset
        // Regular Colors
        public static final String BLACK = "\033[0;30m";   // BLACK
        public static final String RED = "\033[0;31m";     // RED
        public static final String GREEN = "\033[0;32m";   // GREEN
        public static final String YELLOW = "\033[0;33m";  // YELLOW
        public static final String BLUE = "\033[0;34m";    // BLUE
        public static final String PURPLE = "\033[0;35m";  // PURPLE
        public static final String CYAN = "\033[0;36m";    // CYAN
        public static final String WHITE = "\033[0;37m";   // WHITE

        // Bold
        public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
        public static final String RED_BOLD = "\033[1;31m";    // RED
        public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
        public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
        public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
        public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
        public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
        public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

        // Underline
        public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
        public static final String RED_UNDERLINED = "\033[4;31m";    // RED
        public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
        public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
        public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
        public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
        public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
        public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

        // Background
        public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
        public static final String RED_BACKGROUND = "\033[41m";    // RED
        public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
        public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
        public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
        public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
        public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
        public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

        // High Intensity
        public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
        public static final String RED_BRIGHT = "\033[0;91m";    // RED
        public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
        public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
        public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
        public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
        public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
        public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

        // Bold High Intensity
        public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
        public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
        public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
        public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
        public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
        public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
        public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
        public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

        // High Intensity backgrounds
        public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
        public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
        public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
        public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
        public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
        public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
        public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
        public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
    }
}
