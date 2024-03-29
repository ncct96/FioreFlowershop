/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.adt.*;
import com.mycompany.fioreflowershop.modal.*;
import static java.lang.Character.isDigit;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;

/**
 *
 * @author Nicholas
 */
public class CatalogOrder {

    static ShoppingCartListInterface<CatalogOrders> shoppingCart = FioreFlowershop.getShoppingCart();
    static OrderListInterface<CatalogOrders> catalogOrder = FioreFlowershop.getCatalogOrder();
    static CatalogPackageInterface<CatalogPackage> catalogPack = new CatalogPackageList<>();
    static CatalogPackageInterface<CatalogPackage> custItem = new CatalogPackageList<>();

    static LinkedList<Order> conOrder = new LinkedList<>();
    static LinkedList<Order> corpOrder = new LinkedList<>();

    //Define item array of CatalgPackage class
    private static CatalogPackageInterface<CatalogPackage> freshFlower = new CatalogPackageList<>();
    private static CatalogPackageInterface<CatalogPackage> bouquets = new CatalogPackageList<>();
    private static CatalogPackageInterface<CatalogPackage> flowerArrangement = new CatalogPackageList<>();
    private static OrderListInterface<Order> readyOrders = new OrderList<>();
    //Define shopping cart queue of CatalogOrder
    private static Consumer customer;
    private static CorporateCustomer corporate;

    //create a scanner object to get user input
    private static Scanner scan = new Scanner(System.in);
    private static int userMenuOption;
    private static int itemSelection;
    private static double itemPrice; //amount of price
    private static double orderAmt;
    private static double creditSpent;
    private static int quantity;
    private static boolean isInteger;
    private static boolean haveStock = true;
    private static boolean quantityCheck;
    private static Item priority;
    private static Item deliveryType;
    private static String orderStatus = "Pending";
    private static boolean paymentStatus = false;
    private static boolean checkoutStatus = false;
    private static Date todayDate = new Date();
    private static Date currentDate = new Date();
    private static Date orderTime = new Date();
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private static String retrieveDate = "";
    private static int freshFlowerCounter = 0, bouquetsCounter = 0, flowerArrangementCounter = 0;
    private static ItemListInterface<Item> priorities = new ItemList<>();
    private static ItemCatalogue itemCatalog = new ItemCatalogue();

    public static void CustomerOrderMain(OrderListInterface<CatalogOrders> catalogOrder, OrderListInterface<Order> readyOrders, Consumer customerLoggedIn, CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage, ItemCatalogue itemCatalogue) {
        customer = customerLoggedIn;

        CatalogOrder.readyOrders = readyOrders;
        freshFlower.clearCatalogList();
        bouquets.clearCatalogList();
        flowerArrangement.clearCatalogList();
        itemCatalog = itemCatalogue;
        //initialize items into catalog
        initializeCatalog(normalPackage, discountedPackage);
        displayCatalog(normalPackage, discountedPackage);
    }

    public static void CorporateOrderMain(OrderListInterface<CatalogOrders> catalogOrder, OrderListInterface<Order> readyOrders, CorporateCustomer customerLoggedIn, CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage, ItemCatalogue itemCatalogue) {
        corporate = customerLoggedIn;
        creditSpent = corporate.getCreditSpent();

        CatalogOrder.readyOrders = readyOrders;
        freshFlower.clearCatalogList();
        bouquets.clearCatalogList();
        flowerArrangement.clearCatalogList();
        itemCatalog = itemCatalogue;
        //initialize items into catalog
        initializeCatalog(normalPackage, discountedPackage);
        displayCatalog(normalPackage, discountedPackage);
    }

    public static void initializeCatalog(CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage) {
        CatalogPackage catalogPackage = new CatalogPackage();
        //adding of normal catalog package items into the catalog
        for (int i = 1; i < normalPackage.getTotalEntries() + 1; i++) {
            catalogPackage = normalPackage.getProduct(i);
            if (catalogPackage.getProductType().equals("Fresh flower") && catalogPackage.getStatus().equals("Active") && catalogPackage.getAccessory().getQuantity() != 0) {
                freshFlower.addProduct(new CatalogPackage(catalogPackage.getName(), catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getAccessory(), catalogPackage.getProductType(), catalogPackage.getPromoMonth(), catalogPackage.getPromoYear(), catalogPackage.getPrice(), catalogPackage.getDiscountRate(), catalogPackage.getStatus(), catalogPackage.getFlowerNeeded()));
                freshFlowerCounter++;
            } else if (catalogPackage.getProductType().equals("Bouquets") && catalogPackage.getStatus().equals("Active") && catalogPackage.getAccessory().getQuantity() != 0) {
                bouquets.addProduct(new CatalogPackage(catalogPackage.getName(), catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getAccessory(), catalogPackage.getProductType(), catalogPackage.getPromoMonth(), catalogPackage.getPromoYear(), catalogPackage.getPrice(), catalogPackage.getDiscountRate(), catalogPackage.getStatus(), catalogPackage.getFlowerNeeded()));
                bouquetsCounter++;
            } else if (catalogPackage.getProductType().equals("Flower arrangement") && catalogPackage.getStatus().equals("Active") && catalogPackage.getAccessory().getQuantity() != 0) {
                flowerArrangement.addProduct(new CatalogPackage(catalogPackage.getName(), catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getSeason(), catalogPackage.getFlowerPot(), catalogPackage.getAccessory(), catalogPackage.getProductType(), catalogPackage.getPromoMonth(), catalogPackage.getPromoYear(), catalogPackage.getPrice(), catalogPackage.getDiscountRate(), catalogPackage.getStatus(), catalogPackage.getFlowerNeeded()));
                flowerArrangementCounter++;
            }
        }

        //adding of discounted catalog package into the catalog
        for (int i = 1; i < discountedPackage.getTotalEntries() + 1; i++) {
            catalogPackage = discountedPackage.getProduct(i);
            if (catalogPackage.getProductType().equals("Fresh flower") && catalogPackage.getStatus().equals("Active") && catalogPackage.getAccessory().getQuantity() != 0) {
                freshFlower.addProduct(new CatalogPackage(catalogPackage.getName(), catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getAccessory(), catalogPackage.getProductType(), catalogPackage.getPromoMonth(), catalogPackage.getPromoYear(), catalogPackage.getPrice(), catalogPackage.getDiscountRate(), catalogPackage.getStatus(), catalogPackage.getFlowerNeeded()));
                freshFlowerCounter++;
            } else if (catalogPackage.getProductType().equals("Bouquets") && catalogPackage.getStatus().equals("Active") && catalogPackage.getAccessory().getQuantity() != 0) {
                bouquets.addProduct(new CatalogPackage(catalogPackage.getName(), catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getAccessory(), catalogPackage.getProductType(), catalogPackage.getPromoMonth(), catalogPackage.getPromoYear(), catalogPackage.getPrice(), catalogPackage.getDiscountRate(), catalogPackage.getStatus(), catalogPackage.getFlowerNeeded()));
                bouquetsCounter++;
            } else if (catalogPackage.getProductType().equals("Flower arrangement") && catalogPackage.getStatus().equals("Active") && catalogPackage.getAccessory().getQuantity() != 0) {
                flowerArrangement.addProduct(new CatalogPackage(catalogPackage.getName(), catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getSeason(), catalogPackage.getFlowerPot(), catalogPackage.getAccessory(), catalogPackage.getProductType(), catalogPackage.getPromoMonth(), catalogPackage.getPromoYear(), catalogPackage.getPrice(), catalogPackage.getDiscountRate(), catalogPackage.getStatus(), catalogPackage.getFlowerNeeded()));
                flowerArrangementCounter++;
            }
        }
    }

    //display of shopping cart after item is added 
    public static void displayShoppingCart(CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage) {
        String flowerList = "";
        CatalogPackage catalogPackage = new CatalogPackage();

        if (!catalogPack.isEmpty()) {
            System.out.println("\nDisplay Shopping Cart");
            System.out.println("====================================================================================================");
            System.out.println("Product Name\t\t\t\tTotal Price\t\t\tQuantity");

            for (int i = 1; i < catalogPack.getTotalEntries() + 1; i++) {
                System.out.printf("%s\n", catalogPack.getProduct(i).getName());
                if (catalogPack.getProduct(i).getDiscountRate() == 0) {
                    for (int j = 1; j < normalPackage.getTotalEntries() + 1; j++) {
                        if (normalPackage.getProduct(j).getName().equals(catalogPack.getProduct(i).getName())) {
                            catalogPackage = normalPackage.getProduct(j);
                        }
                    }
                    for (int j = 1; j < catalogPackage.getFlowerList().getTotalEntries() + 1; j++) {
                        flowerList += catalogPackage.getFlowerList().getItem(j).getName() + " ";
                    }
                    if (catalogPack.getProduct(i).getProductType().equals("Flower arrangement")) {
                        System.out.printf("%s,\n%-10s,%-6s,%-12s,\n %-13s,%-14s \t\tRM%7.2f\t\t\t   %d\n\n", flowerList, catalogPack.getProduct(i).getStyle().getName(), catalogPack.getProduct(i).getSize().getName(), catalogPack.getProduct(i).getSeason(), catalogPack.getProduct(i).getFlowerPot().getName(), catalogPack.getProduct(i).getAccessory().getName(), catalogPack.getProduct(i).getPrice() * catalogPack.getProduct(i).getUserQuantity(), catalogPack.getProduct(i).getUserQuantity());
                        flowerList = "";
                    } else {
                        System.out.printf("%s,\n%-10s,%-6s,%-12s  \tRM%7.2f\t\t\t   %d\n\n", flowerList, catalogPack.getProduct(i).getStyle().getName(), catalogPack.getProduct(i).getSize().getName(), catalogPack.getProduct(i).getAccessory().getName(), catalogPack.getProduct(i).getPrice() * catalogPack.getProduct(i).getUserQuantity(), catalogPack.getProduct(i).getUserQuantity());
                        flowerList = "";
                    }

                } else if (catalogPack.getProduct(i).getDiscountRate() != 0) {
                    for (int j = 1; j < discountedPackage.getTotalEntries() + 1; j++) {
                        if (discountedPackage.getProduct(j).getName().equals(catalogPack.getProduct(i).getName())) {
                            catalogPackage = discountedPackage.getProduct(j);
                        }
                    }
                    for (int j = 1; j < catalogPackage.getFlowerList().getTotalEntries() + 1; j++) {
                        flowerList += catalogPackage.getFlowerList().getItem(j).getName() + " ";
                    }
                    double itemPrice = (double) ((100 - catalogPack.getProduct(i).getDiscountRate()) * catalogPack.getProduct(i).getPrice() / 100) * catalogPack.getProduct(i).getUserQuantity();
                    if (catalogPack.getProduct(i).getProductType().equals("Flower arrangement")) {
                        System.out.printf("%s,\n%-10s,%-6s,%-13s,\n %-14s,%-12s \t\tRM%7.2f\t\t\t   %d\n\n", flowerList, catalogPack.getProduct(i).getStyle().getName(), catalogPack.getProduct(i).getSize().getName(), catalogPack.getProduct(i).getSeason(), catalogPack.getProduct(i).getFlowerPot().getName(), catalogPack.getProduct(i).getAccessory().getName(), itemPrice, catalogPack.getProduct(i).getUserQuantity());
                        flowerList = "";
                    } else {
                        System.out.printf("%s,\n%-10s,%-6s,%-12s  \tRM%7.2f\t\t\t   %d\n\n", flowerList, catalogPack.getProduct(i).getStyle().getName(), catalogPack.getProduct(i).getSize().getName(), catalogPack.getProduct(i).getAccessory().getName(), itemPrice, catalogPack.getProduct(i).getUserQuantity());
                        flowerList = "";
                    }

                }

            }
        }
        if (corporate != null && customer == null) {
            System.out.printf("Credit left:" + FioreFlowershop.ConsoleColors.RED + " RM %7.2f\n" + FioreFlowershop.ConsoleColors.BLACK, corporate.getMonthlyLimit() - corporate.getCreditSpent());
        }
    }

    //display shopping cart from catalog menu
    public static void showShoppingCart(CatalogPackageInterface<CatalogPackage> catalogPack, CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage) {
        String flowerList = "";
        CatalogPackage catalogPackage = new CatalogPackage();
        if (!catalogPack.isEmpty()) {
            System.out.println("\nShopping Cart");
            System.out.println("===========================================================================================================");
            System.out.println("Product Name  \t\t\t\tUnit Price\t\tQuantity\t\tTotal Price");
            double payAmount2 = 0;
            if (!catalogPack.isEmpty()) {
                for (int i = 1; i < catalogPack.getTotalEntries() + 1; i++) {
                    if (catalogPack.getProduct(i).getDiscountRate() == 0) {
                        for (int j = 1; j < normalPackage.getTotalEntries() + 1; j++) {
                            if (normalPackage.getProduct(j).getName().equals(catalogPack.getProduct(i).getName())) {
                                catalogPackage = normalPackage.getProduct(j);
                            }
                        }
                        flowerList = "";
                        for (int j = 1; j < catalogPackage.getFlowerList().getTotalEntries() + 1; j++) {
                            flowerList += catalogPackage.getFlowerList().getItem(j).getName() + " ";
                        }
                    } else if (catalogPack.getProduct(i).getDiscountRate() != 0) {
                        for (int j = 1; j < discountedPackage.getTotalEntries() + 1; j++) {
                            if (discountedPackage.getProduct(j).getName().equals(catalogPack.getProduct(i).getName())) {
                                catalogPackage = discountedPackage.getProduct(j);
                            }
                        }
                        flowerList = "";
                        for (int j = 1; j < catalogPackage.getFlowerList().getTotalEntries() + 1; j++) {
                            flowerList += catalogPackage.getFlowerList().getItem(j).getName() + " ";
                        }
                    }

                    System.out.printf("%d. %s\n", i, catalogPack.getProduct(i).getName());
                    double discountedPrice = (double) ((100 - catalogPack.getProduct(i).getDiscountRate()) * catalogPack.getProduct(i).getPrice() / 100);
                    payAmount2 += (discountedPrice * catalogPack.getProduct(i).getUserQuantity());
                    if (catalogPack.getProduct(i).getProductType().equals("Flower arrangement")) {
                        System.out.printf("%s,\n%-10s,%-6s,%-13s,\n %-13s,%-12s \t\tRM%7.2f\t\t   %d\t\t\t RM%7.2f\n\n", flowerList, catalogPack.getProduct(i).getStyle().getName(), catalogPack.getProduct(i).getSize().getName(), catalogPack.getProduct(i).getSeason(), catalogPack.getProduct(i).getFlowerPot().getName(), catalogPack.getProduct(i).getAccessory().getName(), discountedPrice, catalogPack.getProduct(i).getUserQuantity(), discountedPrice * catalogPack.getProduct(i).getUserQuantity());
                    } else {
                        System.out.printf("%s,\n%-10s,%-6s,%-12s \t\tRM%7.2f\t\t   %d\t\t\t RM%7.2f\n\n", flowerList, catalogPack.getProduct(i).getStyle().getName(), catalogPack.getProduct(i).getSize().getName(), catalogPack.getProduct(i).getAccessory().getName(), discountedPrice, catalogPack.getProduct(i).getUserQuantity(), discountedPrice * catalogPack.getProduct(i).getUserQuantity());
                    }
                }
            }
            System.out.printf(FioreFlowershop.ConsoleColors.RED + "Amount: \t\t\t\t\t\t\t\t\t         RM%7.2f\n", payAmount2);
            System.out.println(FioreFlowershop.ConsoleColors.BLACK + "============================================================================================================");
        }
    }

//Display the catalog or monthly promotion catalog
    public static void displayCatalog(CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage) {
        do {
            System.out.println(FioreFlowershop.ConsoleColors.BLACK + "\n Catalog Order");
            System.out.println("===================");
            System.out.println("1. Catalog");
            System.out.println("2. My Shopping Cart");
            System.out.println("3. Remove Item from Shopping Cart");
            System.out.println("4. Back");
            System.out.print("Please enter a number (1-4): ");
            if (scan.hasNext()) {
                String menuOption = scan.next();
                if (isDigit(menuOption.charAt(0))) {
                    userMenuOption = Character.getNumericValue(menuOption.charAt(0));
                    isInteger = true;
                } else if (!isDigit(menuOption.charAt(0))) {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter shown number only." + FioreFlowershop.ConsoleColors.BLACK);
                }
            } else {
                System.out.println("");
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter shown number only." + FioreFlowershop.ConsoleColors.BLACK);
            }
        } while (!(isInteger) || userMenuOption < 1 || userMenuOption > 4);

        if (userMenuOption == 1) {
            typeSelection(normalPackage, discountedPackage);
        } else if (userMenuOption == 2) {
            if (!catalogPack.isEmpty()) {
                showShoppingCart(catalogPack, normalPackage, discountedPackage);

                System.out.print("\nDo you want to proceed to select your item retrieval method? (Y/y = yes AND No = any key, go back to menu)");

                String con = scan.next();

                scan.nextLine();

                if (con.equalsIgnoreCase("Y")) {
                    int retrieveItem = 0;
                    do {
                        System.out.println("\n=============================================");
                        System.out.println("How do you want to retrieve your ordered items?\n1.Delivery (Extra Fee = 10$)\n2.Self Pickup (Extra Fee = 0$)");
                        if (scan.hasNextInt()) {
                            retrieveItem = scan.nextInt();
                            isInteger = true;
                        } else {
                            isInteger = false;
                            System.err.println("Please enter shown number only.");
                            scan.next();
                        }
                    } while (!(isInteger) || retrieveItem < 1 || retrieveItem > 2);

                    priorities.removeItem();
                    priorities.addItem(new Item("Flexi", 1, 5));
                    priorities.addItem(new Item("Normal", 1.5, 3));
                    priorities.addItem(new Item("Express", 3, 1));

                    if (retrieveItem == 1) { // Delivery
                        deliveryType = new Item("Delivery", 10);
                        Calendar validDeliveryDate = Calendar.getInstance();
                        validDeliveryDate.setTime(new Date()); // Now use today date.
                        int dayUntilDelivery = 0;

                        int delivery = 0;
                        do { //User retrieve item method either delivery or self pickup
                            System.out.println("\nDelivery Options");
                            System.out.println("===========================");
                            System.out.println("1.Express Delivery (1 day, Extra Fee = Order Amount x 3)");
                            System.out.println("2.Normal Delivery (3 days, Extra Fee = Order Amount x 1.5)");
                            System.out.println("3.Flexi Delivery (5 days, Extra Fee = Order Amount x 1)");
                            if (scan.hasNextInt()) {
                                delivery = scan.nextInt();
                                isInteger = true;
                            } else {
                                isInteger = false;
                                System.err.println("Please enter shown number only.");
                                scan.next();
                            }
                        } while (!(isInteger) || delivery < 1 || delivery > 3);

                        //set the date of delivery
                        if (delivery == 1) { //Express Delivery                                      
                            priority = itemCatalog.getPriorities().getItem(3);  // delivery after today
                            dayUntilDelivery = 1;
                        } else if (delivery == 2) { //Normal Delivery                       
                            priority = itemCatalog.getPriorities().getItem(2);
                            validDeliveryDate.add(Calendar.DATE, 2); // Adding 2 days, delivery after 2 days
                            dayUntilDelivery = 2;
                        } else if (delivery == 3) { //Flexi Delivery                       
                            priority = itemCatalog.getPriorities().getItem(1);
                            validDeliveryDate.add(Calendar.DATE, 4); // Adding 4 days, delivery after 4 days
                            dayUntilDelivery = 4;
                        }

                        //Checking on coporate customer's credit
                        if (customer == null && corporate != null) {
                            creditSpent = creditSpent - orderAmt + calculateOrderAmount();
                            if (corporate.getMonthlyLimit() - creditSpent < 0) {
                                System.out.println(FioreFlowershop.ConsoleColors.RED + "You have exceeded your credit limit after selected your delivery priority." + FioreFlowershop.ConsoleColors.BLACK);
                                creditSpent = creditSpent - calculateOrderAmount() + orderAmt;
                                System.out.printf("\nCredit Left : RM %.2f\n", corporate.getMonthlyLimit() - creditSpent);
                                displayCatalog(normalPackage, discountedPackage);
                            } else if (corporate.getMonthlyLimit() - creditSpent >= 0) {
                                corporate.setCreditSpent(creditSpent);
                            }
                        }

                        orderAmt = calculateOrderAmount();

                        String deliveryDate;
                        boolean checkDate = false;
                        do {
                            System.out.println("");
                            System.out.println(FioreFlowershop.ConsoleColors.BLACK + "When do you want your items to be delivered to you? (yyyy-MM-dd)");
                            deliveryDate = scan.next();
                            if (!deliveryDate.isEmpty() && deliveryDate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                                try {
                                    Date deliveryDate2 = dateFormat.parse(deliveryDate);
                                    if (deliveryDate2.after(validDeliveryDate.getTime())) {
                                        retrieveDate = dateFormat.format(dateFormat.parse(deliveryDate));

                                        System.out.println("");
                                        System.out.println("=========================================================");
                                        System.out.println(FioreFlowershop.ConsoleColors.GREEN + "Your ordered items will be delivered to you by " + retrieveDate + FioreFlowershop.ConsoleColors.BLACK);
                                        System.out.println("=========================================================");
                                        checkDate = true;

                                    } else {
                                        System.out.println("");
                                        System.out.printf(FioreFlowershop.ConsoleColors.RED + "Please enter a valid date, at least %d day after today.", dayUntilDelivery);
                                        System.out.println("");
                                        checkDate = false;
                                    }

                                } catch (Exception e) {
                                    System.out.print("\n" + FioreFlowershop.ConsoleColors.RED + "Please supply a valid delivery date." + FioreFlowershop.ConsoleColors.BLACK + "\n");
                                }
                            } else {
                                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter a date with correct format e.g: 2018-12-25.");
                                checkDate = false;
                            }
                        } while (!checkDate);

                    } else if (retrieveItem == 2) { //Self Pickup
                        deliveryType = new Item("Pick Up", 0);
                        Calendar validPickupDate = Calendar.getInstance();
                        validPickupDate.setTime(new Date()); // Now use today date.
                        int pickUp = 0;
                        int dayUntilPickUp = 0;

                        do {
                            System.out.println("\nPickup Priority");
                            System.out.println("===========================");
                            System.out.println("1.Express Pickup (1 day)");
                            System.out.println("2.Normal Pickup (3 days)");
                            System.out.println("3.Flexi Pickup (5 days)");

                            System.out.print("Please enter your pickup priority : ");

                            if (scan.hasNextInt()) {
                                pickUp = scan.nextInt();
                                isInteger = true;
                            } else {
                                isInteger = false;
                                System.err.println("Please enter shown number only.");
                                scan.next();
                            }
                        } while (!isInteger || pickUp < 1 || pickUp > 3);

                        if (pickUp == 1) { //Express Pickup 
                            priority = itemCatalog.getPriorities().getItem(3); // pickup after today
                            dayUntilPickUp = 1;
                        } else if (pickUp == 2) { //Normal Pickup
                            priority = itemCatalog.getPriorities().getItem(2);
                            validPickupDate.add(Calendar.DATE, 2); // Adding 2 days, pickup after 2 days
                            dayUntilPickUp = 2;
                        } else if (pickUp == 3) { //Flexi Pickup
                            priority = itemCatalog.getPriorities().getItem(1);
                            validPickupDate.add(Calendar.DATE, 4); // Adding 4 days, pickup after 4 days
                            dayUntilPickUp = 4;
                        }

                        //Checking on coporate customer's credit
                        if (customer == null && corporate != null) {
                            creditSpent = creditSpent - orderAmt + calculateOrderAmount();
                            if (corporate.getMonthlyLimit() - creditSpent < 0) {
                                System.out.println(FioreFlowershop.ConsoleColors.RED + "You have exceeded your credit limit after selected your pickup priority." + FioreFlowershop.ConsoleColors.BLACK);
                                creditSpent = creditSpent - calculateOrderAmount() + orderAmt;
                                System.out.printf("\nCredit Left : RM %.2f\n", corporate.getMonthlyLimit() - creditSpent);
                                displayCatalog(normalPackage, discountedPackage);
                            } else if (corporate.getMonthlyLimit() - creditSpent >= 0) {
                                corporate.setCreditSpent(creditSpent);
                            }
                        }

                        orderAmt = calculateOrderAmount();

                        String pickDate;
                        boolean checkDate = false;
                        do {
                            System.out.println("");
                            System.out.println(FioreFlowershop.ConsoleColors.BLACK + "When do you want to pickup your items? (yyyy-MM-dd)");
                            pickDate = scan.next();
                            if (!pickDate.isEmpty() && pickDate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                                try {
                                    Date pickDate2 = dateFormat.parse(pickDate);
                                    if (pickDate2.after(validPickupDate.getTime())) {
                                        retrieveDate = dateFormat.format(dateFormat.parse(pickDate));

                                        System.out.println("");
                                        System.out.println("=====================================================");
                                        System.out.println(FioreFlowershop.ConsoleColors.GREEN + "You can pickup your items by " + retrieveDate + FioreFlowershop.ConsoleColors.BLACK);
                                        System.out.println("=====================================================");
                                        checkDate = true;

                                    } else {
                                        System.out.println("");
                                        System.out.printf(FioreFlowershop.ConsoleColors.RED + "Please enter a valid date, at least %d day after today.", dayUntilPickUp);
                                        System.out.println("");
                                        checkDate = false;
                                    }

                                } catch (Exception e) {
                                    System.out.print("\n" + FioreFlowershop.ConsoleColors.RED + "Please supply a valid pickup date." + FioreFlowershop.ConsoleColors.BLACK + "\n");
                                }
                            } else {
                                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter a date with correct format e.g: 2018-12-25.");
                                checkDate = false;
                            }
                        } while (!checkDate);
                    }

                    System.out.print("Do you wish to checkout? (Y/y = yes OR N/n = no)");
                    String checkout = scan.next();

                    scan.nextLine();

                    if (checkout.equalsIgnoreCase("Y")) {
                        checkoutStatus = true;
                        //Generate sales order for different customer
                        if (customer != null && corporate == null) {
                            salesOrder();
                        } else if (customer == null && corporate != null) {
                            salesOrder();
                        }
                    }

                    System.out.println("");
                    System.out.println(FioreFlowershop.ConsoleColors.GREEN + "Back to Catalog Order Menu....");
                    displayCatalog(normalPackage, discountedPackage);

                } else {
                    System.out.println("");
                    System.out.println(FioreFlowershop.ConsoleColors.GREEN + "Back to Catalog Order Menu....");
                    displayCatalog(normalPackage, discountedPackage);
                }

            } else if (catalogPack.isEmpty()) {
                System.out.println("");
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Your shopping cart is empty. You have not added in any item yet." + FioreFlowershop.ConsoleColors.BLACK);
                displayCatalog(normalPackage, discountedPackage);
            }

        } else if (userMenuOption == 3) {
            if (!catalogPack.isEmpty()) {
                removeCartItem(normalPackage, discountedPackage);
            } else if (catalogPack.isEmpty()) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Your shopping cart is empty. You have not added in any item yet.");
                displayCatalog(normalPackage, discountedPackage);
            }

        } else if (userMenuOption == 4) {
            if (!checkoutStatus && !(catalogPack.isEmpty())) { //to prevent different order id for different ordered item for one order
                System.out.println("");
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please checkout from your shopping cart before back to main menu." + FioreFlowershop.ConsoleColors.BLACK);
                System.out.println("");
                displayCatalog(normalPackage, discountedPackage);
            } else if (catalogPack.isEmpty()) {
                FioreFlowershop.userTypeSelection();
            }
        }
    }

    public static double calculateOrderAmount() {
        double amount = 0;
        amount = (orderAmt * priority.getPrice()) + deliveryType.getPrice();
        return amount;
    }

    public static CatalogPackageInterface<CatalogPackage> getCartItem() {
        return catalogPack;
    }

    public static void removeCartItem(CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage) {
        showShoppingCart(catalogPack, normalPackage, discountedPackage);

        int choice = 0;
        do {
            System.out.print("Which item do you wish to remove?\nPlease enter the number of the item :");
            if (scan.hasNext()) {
                String removeChoice = scan.next();
                if (isDigit(removeChoice.charAt(0))) {
                    choice = Character.getNumericValue(removeChoice.charAt(0));
                    isInteger = true;
                } else if (!isDigit(removeChoice.charAt(0))) {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter shown number only." + FioreFlowershop.ConsoleColors.BLACK);
                }
            } else {
                System.out.println("");
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter shown number only." + FioreFlowershop.ConsoleColors.BLACK);
            }
        } while (!(isInteger) || choice < 1 || choice > catalogPack.getTotalEntries());

        String confirm = "";
        boolean checkConfirm = false;
        do {
            System.out.print(FioreFlowershop.ConsoleColors.RED + "Do you wish to remove the selected item ?" + FioreFlowershop.ConsoleColors.BLACK + "(Yes = Y/y , No = N/n)");
            confirm = scan.next();

            double creditReduction = 0;
            if (confirm.equalsIgnoreCase("Y")) {

                //add in credit spent reduction
                if (catalogPack.getProduct(choice).getDiscountRate() == 0) {
                    creditReduction = (catalogPack.getProduct(choice).getPrice() * catalogPack.getProduct(choice).getUserQuantity());
                    orderAmt -= creditReduction; //Reduce from the order amount
                    if (customer == null && corporate != null) {
                        creditSpent -= creditReduction;
                    }

                } else if (catalogPack.getProduct(choice).getDiscountRate() != 0) {
                    double discountedPrice = (double) ((100 - catalogPack.getProduct(choice).getDiscountRate()) * catalogPack.getProduct(choice).getPrice() / 100);
                    creditReduction = discountedPrice * catalogPack.getProduct(choice).getUserQuantity();
                    orderAmt -= creditReduction; //Reduce from the order amount
                    if (customer == null && corporate != null) {
                        creditSpent -= creditReduction;
                    }
                }
                if (customer == null && corporate != null) {
                    corporate.setCreditSpent(creditSpent);
                }

                //Update stock quantity of each item in the selected package
                //get the stock quantity of each item 
                int accessory = 0;
                CatalogPackage catalogPackage3 = new CatalogPackage();
                if (catalogPack.getProduct(choice).getDiscountRate() == 0) {
                    for (int i = 1; i < normalPackage.getTotalEntries() + 1; i++) {
                        if (normalPackage.getProduct(i).getName().equals(catalogPack.getProduct(choice).getName())) {
                            catalogPackage3 = normalPackage.getProduct(i);
                        }
                    }
                } else if (catalogPack.getProduct(choice).getDiscountRate() != 0) {
                    for (int i = 1; i < discountedPackage.getTotalEntries() + 1; i++) {
                        if (discountedPackage.getProduct(i).getName().equals(catalogPack.getProduct(choice).getName())) {
                            catalogPackage3 = discountedPackage.getProduct(i);
                        }
                    }
                }

                for (int i = 1; i < itemCatalog.getAccessories().getSize() + 1; i++) {
                    if (itemCatalog.getAccessories().getItem(i).getName().equals(catalogPack.getProduct(choice).getAccessory().getName())) {
                        accessory = itemCatalog.getAccessories().getItem(i).getQuantity();
                    }
                }

                int[] flower = new int[catalogPackage3.getFlowerList().getTotalEntries()];
                int[] flowerQuantity = new int[catalogPackage3.getFlowerList().getTotalEntries()];

                for (int i = 1; i < catalogPackage3.getFlowerList().getTotalEntries() + 1; i++) {
                    flower[i - 1] = catalogPackage3.getFlowerList().getItem(i).getQuantity();
                }

                //update the stock quantity of each item
                catalogPackage3.getAccessory().setQuantity(accessory + catalogPack.getProduct(choice).getUserQuantity());

                String[] flowerStock = catalogPack.getProduct(choice).getFlowerNeeded().split(" ");
                for (int i = 0; i < catalogPackage3.getFlowerList().getTotalEntries(); i++) {
                    flowerQuantity[i] = Integer.parseInt(flowerStock[i]);
                }

                for (int i = 1; i < catalogPackage3.getFlowerList().getTotalEntries() + 1; i++) {
                    int stockLeft = flower[i - 1] + (flowerQuantity[i - 1] * catalogPack.getProduct(choice).getUserQuantity());
                    catalogPackage3.getFlowerList().getItem(i).setQuantity(stockLeft);
                }

                //remove selected package from shopping cart
                catalogPack.removeProduct(choice);
                checkConfirm = true;
                System.out.println("");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "Selected item is successfully removed." + FioreFlowershop.ConsoleColors.BLACK);
                System.out.println("");
            } else if (confirm.equalsIgnoreCase("N")) {
                String con = "";
                boolean checkCon = false;
                do {
                    System.out.print("Back to Catalog Menu? (Y/y = Yes , N/n = No)");
                    con = scan.next();
                    if (con.equalsIgnoreCase("Y")) {
                        displayCatalog(normalPackage, discountedPackage);
                        checkCon = true;
                    } else if (con.equalsIgnoreCase("N") && !(catalogPack.isEmpty())) {
                        removeCartItem(normalPackage, discountedPackage);
                        checkCon = true;
                    } else if (con.equalsIgnoreCase("N") && catalogPack.isEmpty()) {
                        System.out.println("");
                        System.out.println(FioreFlowershop.ConsoleColors.GREEN + "There is no item left in your shopping cart" + FioreFlowershop.ConsoleColors.BLACK);
                        System.out.println("");
                        displayCatalog(normalPackage, discountedPackage);
                        checkCon = true;
                    }
                } while (!checkCon);
                checkConfirm = true;
            }
        } while (!checkConfirm);

        showShoppingCart(catalogPack, normalPackage, discountedPackage);

        String con = "";
        boolean checkCon = false;
        do {
            System.out.print("Back to Catalog Menu? (Y/y = Yes , N/n = No)");
            con = scan.next();
            if (con.equalsIgnoreCase("Y")) {
                displayCatalog(normalPackage, discountedPackage);
                checkCon = true;
            } else if (con.equalsIgnoreCase("N") && !(catalogPack.isEmpty())) {
                removeCartItem(normalPackage, discountedPackage);
                checkCon = true;
            } else if (con.equalsIgnoreCase("N") && catalogPack.isEmpty()) {
                System.out.println("");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "There is no item left in your shopping cart" + FioreFlowershop.ConsoleColors.BLACK);
                System.out.println("");
                displayCatalog(normalPackage, discountedPackage);
                checkCon = true;
            }
        } while (!checkCon);

    }

    public static void salesOrder() {
        int index = 0;
        for (int i = 1; i < catalogPack.getTotalEntries() + 1; i++) {
            custItem.addProduct(new CatalogPackage(catalogPack.getProduct(i).getName(), catalogPack.getProduct(i).getStyle(), catalogPack.getProduct(i).getSize(), catalogPack.getProduct(i).getSeason(), catalogPack.getProduct(i).getFlowerPot(),
                    catalogPack.getProduct(i).getAccessory(), catalogPack.getProduct(i).getProductType(),
                    catalogPack.getProduct(i).getPromoMonth(), catalogPack.getProduct(i).getPromoYear(),
                    catalogPack.getProduct(i).getPrice(), catalogPack.getProduct(i).getDiscountRate(), catalogPack.getProduct(i).getStatus(), catalogPack.getProduct(i).getUserQuantity(), catalogPack.getProduct(i).getFlowerNeeded()));
            index++;
        }

        Date retrieveDate2;
        try {
            retrieveDate2 = dateFormat.parse(retrieveDate);
            String orderTime2 = timeFormat.format(orderTime);
            orderTime = timeFormat.parse(orderTime2);
            //if (customer != null && corporate == null) {
            //shoppingCart.addItem(new CatalogOrders(custItem, priority, deliveryType, currentDate, orderTime, customer, orderStatus, orderAmt, paymentStatus, retrieveDate2, retrieveDate2));
            //} else if (customer == null && corporate != null) {
            //shoppingCart.addItem(new CatalogOrders(custItem, priority, deliveryType, currentDate, orderTime, corporate, orderStatus, orderAmt, paymentStatus, retrieveDate2, retrieveDate2));
            // }
            CatalogOrders catalogOrder1 = new CatalogOrders();
            if (customer != null && corporate == null) {
                catalogOrder1 = new CatalogOrders(priority, deliveryType, currentDate, orderTime, customer, orderStatus, orderAmt, paymentStatus, retrieveDate2, retrieveDate2);

            } else if (customer == null && corporate != null) {
                catalogOrder1 = new CatalogOrders(priority, deliveryType, currentDate, orderTime, corporate, orderStatus, orderAmt, paymentStatus, retrieveDate2, retrieveDate2);

            }
            for (int i = 1; i < catalogPack.getTotalEntries() + 1; i++) {
                catalogOrder1.getCatalogPack().addProduct(new CatalogPackage(catalogPack.getProduct(i).getName(), catalogPack.getProduct(i).getStyle(), 
                    catalogPack.getProduct(i).getSize(), catalogPack.getProduct(i).getSeason(), catalogPack.getProduct(i).getFlowerPot(),
                    catalogPack.getProduct(i).getAccessory(), catalogPack.getProduct(i).getProductType(),
                    catalogPack.getProduct(i).getPromoMonth(), catalogPack.getProduct(i).getPromoYear(),
                    catalogPack.getProduct(i).getPrice(), catalogPack.getProduct(i).getDiscountRate(), catalogPack.getProduct(i).getStatus(), 
                    catalogPack.getProduct(i).getUserQuantity(), catalogPack.getProduct(i).getFlowerNeeded()));
            }   
            catalogOrder.addOrder(catalogOrder1);
            readyOrders.addOrder(catalogOrder.getOrder(catalogOrder.getSize()));
            SortOrders.sortAllOrders(readyOrders);

        } catch (ParseException ex) {
            Logger.getLogger(CatalogOrder.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        shoppingCart.clearShoppingCartList();
        int c = catalogOrder.getSize();
        CatalogOrders shopping = catalogOrder.getOrder(c);

        double totalPrice = 0;
        System.out.println("\n=================================================================================================");
        System.out.println("\nFiore Flowershop SDN.BHD \t\t\t\t\t\t" + FioreFlowershop.ConsoleColors.BLACK_BOLD + " SALES ORDER");
        System.out.println("\nQ-5-1, Desa Permai Indah");
        System.out.println("Bandar Gelugor, 11700 Pulau Pinang \t\t\t\t\t" + "Sales Order #[" + catalogOrder.getOrder(catalogOrder.getSize()).getID() + "]");
        System.out.println("Phone : 0125566922 \t\t\t\t\t\t\t" + "DATE: " + dateFormat.format(currentDate));

        System.out.println("\nTO:");
        System.out.println("[" + shopping.getUser().getEmail() + "]");
        if (customer == null && corporate != null && corporate.getCompany() != null) {
            System.out.println("[" + corporate.getCompany() + "]");

            if (corporate.getCompany() == null) {
                System.out.println("[ - ]");
            }
        }

        System.out.println("[" + shopping.getUser().getAddress() + "]");
        System.out.println("[City, ST ZIP Code]");
        System.out.println("[" + shopping.getUser().getPhone() + "]");
        System.out.println("=================================================================================================");
        System.out.println("Description \t\t\t  | Quantity  |  Discount Rate(%) | Unit Price(RM) |  Total(RM)");

        if (catalogOrder.getSize() > 1) {
            int startIndex = catalogOrder.getOrder(c).getCatalogPack().getTotalEntries() - index;
            for (int i = startIndex + 1; i < catalogOrder.getOrder(c).getCatalogPack().getTotalEntries() + 1; i++) {

                double total = (double) ((100 - catalogOrder.getOrder(c).getCatalogPack().getProduct(i).getDiscountRate()) * catalogOrder.getOrder(c).getCatalogPack().getProduct(i).getPrice() / 100) * catalogOrder.getOrder(c).getCatalogPack().getProduct(i).getUserQuantity();
                System.out.printf("%s  \t\t\t  | \t  %d  |\t         %d\t|\t   %7.2f |   %7.2f\n", catalogOrder.getOrder(c).getCatalogPack().getProduct(i).getName(), catalogOrder.getOrder(c).getCatalogPack().getProduct(i).getUserQuantity(), catalogOrder.getOrder(c).getCatalogPack().getProduct(i).getDiscountRate(), catalogOrder.getOrder(c).getCatalogPack().getProduct(i).getPrice(), total);
            }
            System.out.println("=================================================================================================");
        } else if (catalogOrder.getSize() == 1) {

            for (int i = 1; i < catalogPack.getTotalEntries() + 1; i++) {

                double total = (double) ((100 - catalogOrder.getOrder(c).getCatalogPack().getProduct(i).getDiscountRate()) * catalogOrder.getOrder(c).getCatalogPack().getProduct(i).getPrice() / 100) * catalogOrder.getOrder(c).getCatalogPack().getProduct(i).getUserQuantity();
                System.out.printf("%s  \t\t\t  | \t  %d  |\t         %d\t|\t   %7.2f |   %7.2f\n", catalogOrder.getOrder(c).getCatalogPack().getProduct(i).getName(), catalogOrder.getOrder(c).getCatalogPack().getProduct(i).getUserQuantity(), catalogOrder.getOrder(c).getCatalogPack().getProduct(i).getDiscountRate(), catalogOrder.getOrder(c).getCatalogPack().getProduct(i).getPrice(), total);
            }
        }
//        for (int i = 1; i < catalogPack.getTotalEntries() + 1; i++) {
//            totalPrice += (double) ((100 - catalogPack.getProduct(i).getDiscountRate()) * catalogPack.getProduct(i).getPrice() / 100) * catalogPack.getProduct(i).getUserQuantity();
//        }

        System.out.println(FioreFlowershop.ConsoleColors.BLACK + "\n\n\t\t\t\t\t\t\t Subtotal :\t\t\t " + FioreFlowershop.ConsoleColors.GREEN + "RM " + orderAmt + FioreFlowershop.ConsoleColors.BLACK);
        if (customer == null && corporate != null) {
            System.out.printf("\n    \t\t\t\t\t\tCredit Balance :\t\t\t RM %.2f", corporate.getMonthlyLimit() - creditSpent);
        }
        System.out.println("\n\n\t\t\t\t\t\t\tOrder Type :\t\t\t " + shopping.getOrderType());
        System.out.println("\n\n\t\t\t\t\t\t Delivery/Pickup Date :\t\t\t " + dateFormat.format(shopping.getDeliveryDate()));
        catalogPack.clearCatalogList();
        orderAmt = 0;
    }

    public static void typeSelection(CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage) {
        do {

            System.out.println("\nNormal Catalog - Type Selection");
            System.out.println("===============================");
            System.out.println("1.Fresh Flower");
            System.out.println("2.Bonquet");
            System.out.println("3.Flower Arrangement");
            System.out.println("4.Back");
            System.out.print("Please enter your choice (1-4):");
            if (scan.hasNext()) {
                String MenuOption = scan.next();
                if (isDigit(MenuOption.charAt(0))) {
                    userMenuOption = Character.getNumericValue(MenuOption.charAt(0));
                    isInteger = true;
                } else if (!isDigit(MenuOption.charAt(0))) {
                    isInteger = false;
                    System.err.println("Please enter your choice (1-4).");
                }
            }
        } while (!(isInteger) || userMenuOption < 1 || userMenuOption > 4);
        if (userMenuOption == 4) {
            scan.nextLine();
            displayCatalog(normalPackage, discountedPackage);
        } else if (userMenuOption == 1) {
            freshFlowerCatalog(normalPackage, discountedPackage);
        } else if (userMenuOption == 2) {
            bouquetsCatalog(normalPackage, discountedPackage);
        } else if (userMenuOption == 3) {
            flowerArrangementCatalog(normalPackage, discountedPackage);
        }

    }

    public static int checkQuantity(CatalogPackage catalogPackage, CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage) {
        int availableStock = 0;
        int totalItem = 1;
        int accessory = 0;
        int flowerPot = 0;
        int noStock = 0;
        int stock = 0;
        String[] flower = catalogPackage.getFlowerNeeded().split(" ");

        CatalogPackage catalogPackage2 = new CatalogPackage();
        if (catalogPackage.getDiscountRate() == 0) {
            for (int i = 1; i < normalPackage.getTotalEntries() + 1; i++) {
                if (normalPackage.getProduct(i).getName().equals(catalogPackage.getName())) {
                    catalogPackage2 = normalPackage.getProduct(i);
                }
            }
        } else if (catalogPackage.getDiscountRate() != 0) {
            for (int i = 1; i < discountedPackage.getTotalEntries() + 1; i++) {
                if (discountedPackage.getProduct(i).getName().equals(catalogPackage.getName())) {
                    catalogPackage2 = discountedPackage.getProduct(i);
                }
            }
        }
        if (catalogPackage2.getAccessory().getName().equals("None")) {
            totalItem = 0;
        }
        totalItem += catalogPackage2.getFlowerList().getTotalEntries();
        if (catalogPackage2.getProductType().equals("Flower arrangement")) {
            totalItem += 1;
        }
        int[] itemStock = new int[totalItem];
        int[] flowerNeededQ = new int[catalogPackage2.getFlowerList().getTotalEntries()];
        ArrayList<Integer> flowerQuantity = new ArrayList<>();

        if (!catalogPackage2.getAccessory().getName().equalsIgnoreCase("None")) {
            for (int i = 1; i < itemCatalog.getAccessories().getSize() + 1; i++) {
                if (itemCatalog.getAccessories().getItem(i).getName().equals(catalogPackage2.getAccessory().getName())) {
                    accessory = itemCatalog.getAccessories().getItem(i).getQuantity();
                }
            }
        } else if (catalogPackage2.getAccessory().getName().equals("None")) {
            accessory = 1;
        }

        if (catalogPackage2.getProductType().equals("Flower arrangement")) {
            for (int i = 1; i < itemCatalog.getFlowerPot().getSize() + 1; i++) {
                if (itemCatalog.getFlowerPot().getItem(i).getName().equals(catalogPackage2.getFlowerPot().getName())) {
                    flowerPot = itemCatalog.getFlowerPot().getItem(i).getQuantity();
                }
            }
        }

        for (int i = 0; i < catalogPackage2.getFlowerList().getTotalEntries(); i++) {
            flowerNeededQ[i] = Integer.parseInt(flower[i]);
        }

        int j = 0;
        for (int k = 1; k < itemCatalog.getFlowers().getSize() + 1; k++) {
            if (itemCatalog.getFlowers().getItem(k).getName().equals(catalogPackage2.getFlowerList().getItem(j + 1).getName())) {
                flowerQuantity.add(itemCatalog.getFlowers().getItem(k).getQuantity());
                j++;
                k = 0;
                if (j == catalogPackage2.getFlowerList().getTotalEntries()) {
                    break;
                }
            }
        }

        for (int i = 0; i < catalogPackage2.getFlowerList().getTotalEntries(); i++) {
            if (flowerQuantity.get(i) < flowerNeededQ[i]) {
                noStock++;
                stock = (int) (flowerQuantity.get(i) / flowerNeededQ[i]);
                itemStock[i] = stock;
            } else if (flowerQuantity.get(i) > flowerNeededQ[i]) {
                stock = (int) (flowerQuantity.get(i) / flowerNeededQ[i]);
                itemStock[i] = stock;
            }
        }

        if (!catalogPackage2.getAccessory().getName().equals("None")) {
            itemStock[catalogPackage2.getFlowerList().getTotalEntries()] = accessory;

            if (catalogPackage2.getProductType().equals("Flower arrangement")) {
                itemStock[catalogPackage2.getFlowerList().getTotalEntries() + 1] = flowerPot;
                if (flowerPot == 0) {
                    noStock++;
                }
            }
        }
        if (catalogPackage2.getAccessory().getName().equals("None")) {
            if (catalogPackage2.getProductType().equals("Flower arrangement")) {
                itemStock[catalogPackage2.getFlowerList().getTotalEntries()] = flowerPot;
                if (flowerPot == 0) {
                    noStock++;
                }
            }
        }
        if (accessory == 0) {
            noStock++;
        }

        if (noStock == 0) {
            availableStock = getSmallest(itemStock, totalItem);
        } else if (noStock > 0) {
            availableStock = 0;
        }
        return availableStock;
    }

    public static int getSmallest(int[] a, int total) {
        int temp;
        for (int i = 0; i < total; i++) {
            for (int j = i + 1; j < total; j++) {
                if (a[i] > a[j]) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        return a[0];
    }

    private static String getFlowers(CatalogPackage catalogPackage, CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage) {
        String flowerList = "";
        CatalogPackage catalogPackage2 = new CatalogPackage();

        if (catalogPackage.getDiscountRate() == 0) {
            for (int i = 1; i < normalPackage.getTotalEntries() + 1; i++) {
                if (normalPackage.getProduct(i).getName().equals(catalogPackage.getName())) {
                    catalogPackage2 = normalPackage.getProduct(i);
                }
            }
            for (int j = 1; j < catalogPackage2.getFlowerList().getTotalEntries() + 1; j++) {
                flowerList += catalogPackage2.getFlowerList().getItem(j).getName() + " ";
            }
        } else if (catalogPackage.getDiscountRate() != 0) {
            for (int i = 1; i < discountedPackage.getTotalEntries() + 1; i++) {
                if (discountedPackage.getProduct(i).getName().equals(catalogPackage.getName())) {
                    catalogPackage2 = discountedPackage.getProduct(i);
                }
            }
            for (int j = 1; j < catalogPackage2.getFlowerList().getTotalEntries() + 1; j++) {
                flowerList += catalogPackage2.getFlowerList().getItem(j).getName() + " ";
            }
        }

        return flowerList;
    }

    //Display the normal catalog
    public static void freshFlowerCatalog(CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage) {
        String flowerList = "";
        System.out.println("\nDisplay catalog");
        System.out.println("========================================================================================================");
        System.out.println("Product types\t\t\t\t\tPrice\t\t\tDiscounted price");
        System.out.println("Fresh Flower");
        System.out.println("==================");

        if (freshFlowerCounter != 0 && freshFlower.getTotalEntries() != 0) {
            for (int i = 1; i < freshFlower.getTotalEntries() + 1; i++) {
                flowerList = getFlowers(freshFlower.getProduct(i), normalPackage, discountedPackage);
                System.out.printf("%d. %s\n", i, freshFlower.getProduct(i).getName());
                if (freshFlower.getProduct(i).getDiscountRate() == 0) {
                    System.out.printf("%s,\n%-9s,%-6s,%-12s \t   \t\tRM%7.2f\t\t        - \n\n", flowerList, freshFlower.getProduct(i).getStyle().getName(), freshFlower.getProduct(i).getSize().getName(), freshFlower.getProduct(i).getAccessory().getName(), freshFlower.getProduct(i).getPrice());
                } else if (freshFlower.getProduct(i).getDiscountRate() != 0) {
                    double discountedPrice = (double) ((100 - freshFlower.getProduct(i).getDiscountRate()) * freshFlower.getProduct(i).getPrice() / 100);
                    System.out.printf("%s,\n%-9s,%-6s,%-12s \t    \t\tRM%7.2f\t\t    RM%7.2f \n\n", flowerList, freshFlower.getProduct(i).getStyle().getName(), freshFlower.getProduct(i).getSize().getName(), freshFlower.getProduct(i).getAccessory().getName(), freshFlower.getProduct(i).getPrice(), discountedPrice);
                }
            }
        } else if (freshFlower.getTotalEntries() == 0) {
            System.err.println("\nSorry, the item type you have selected is not available yet.");
            typeSelection(normalPackage, discountedPackage);
        }

        do {
            haveStock = true;
            isInteger = true;
            System.out.print("Please enter your choice in number (Enter 0 to previous page):");
            if (scan.hasNextInt()) {
                String itemChoice = scan.next();
                int choiceCheck = 0;

                if (itemChoice.trim().length() > freshFlower.getTotalEntries() || !isDigit(itemChoice.charAt(0))) {
                    isInteger = false;
                    System.out.println("");
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter your choice the number within the range only.\n" + FioreFlowershop.ConsoleColors.BLACK);
                } else if (itemChoice.length() <= freshFlower.getTotalEntries() && isDigit(itemChoice.charAt(0))) { //more than shown range of package list
                    for (int i = 0; i < itemChoice.trim().length(); i++) {
                        if (isDigit(itemChoice.trim().charAt(i))) {
                            choiceCheck++;
                        }
                    }
                    if (itemChoice.charAt(0) == '0') {
                        typeSelection(normalPackage, discountedPackage);
                    }
                    if (choiceCheck == itemChoice.length()) {
                        itemSelection = Integer.valueOf(itemChoice);
                        if (itemSelection > freshFlower.getTotalEntries() || itemSelection < 0) { //more than shown range of package list
                            isInteger = false;
                            System.out.println("");
                            System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter your choice the number within the range only." + FioreFlowershop.ConsoleColors.BLACK);
                        } else {
                            if (checkQuantity(freshFlower.getProduct(itemSelection), normalPackage, discountedPackage) == 0) {
                                haveStock = false;
                                System.out.println("");
                                System.out.println(FioreFlowershop.ConsoleColors.RED + "Sorry, the item you have selected is out of stock.\n" + FioreFlowershop.ConsoleColors.BLACK);
                            } else {
                                isInteger = true;
                            }
                        }
                    } else if (choiceCheck != itemChoice.length()) { //other character is included besides integer
                        isInteger = false;
                        System.out.println("");
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter your choice the number within the range only." + FioreFlowershop.ConsoleColors.BLACK);
                    }
                }
            } else {
                isInteger = false;
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter your choice the number within the range only." + FioreFlowershop.ConsoleColors.BLACK);
            }

        } while (itemSelection < 0 || itemSelection > freshFlower.getTotalEntries() || !(isInteger) || !(haveStock));

        System.out.println("\nDisplay catalog - Quantity Selection");
        System.out.println("========================================================================================================");
        System.out.println("Product types\t\t\t\tQuantity\tPrice\t\t\tDiscounted price");
        System.out.printf("%s\n", freshFlower.getProduct(itemSelection).getName());
        flowerList = getFlowers(freshFlower.getProduct(itemSelection), normalPackage, discountedPackage);
        if (freshFlower.getProduct(itemSelection).getDiscountRate() == 0) {
            System.out.printf("%s,\n%-9s,%-6s,%-12s\t\t%d  \t\tRM%7.2f\t\t        -\n\n", flowerList, freshFlower.getProduct(itemSelection).getStyle().getName(), freshFlower.getProduct(itemSelection).getSize().getName(), freshFlower.getProduct(itemSelection).getAccessory().getName(), checkQuantity(freshFlower.getProduct(itemSelection), normalPackage, discountedPackage), freshFlower.getProduct(itemSelection).getPrice());
        } else if (freshFlower.getProduct(itemSelection).getDiscountRate() != 0) {
            double discountedPrice = (double) ((100 - freshFlower.getProduct(itemSelection).getDiscountRate()) * freshFlower.getProduct(itemSelection).getPrice() / 100);
            System.out.printf("%s,\n%-9s,%-6s,%-12s\t\t%d  \t\tRM%7.2f\t\t    RM%7.2f\n\n", flowerList, freshFlower.getProduct(itemSelection).getStyle().getName(), freshFlower.getProduct(itemSelection).getSize().getName(), freshFlower.getProduct(itemSelection).getAccessory().getName(), checkQuantity(freshFlower.getProduct(itemSelection), normalPackage, discountedPackage), freshFlower.getProduct(itemSelection).getPrice(), discountedPrice);
        }

        do {
            do {
                System.out.println("");
                System.out.print("Please enter quantity of this item you want to order:");

                if (scan.hasNextInt()) {
                    quantity = scan.nextInt();

                    if (quantity < 0) {
                        isInteger = false;
                    } else if (quantity > 0) {
                        isInteger = true;
                    }

                } else {
                    isInteger = false;
                    System.err.println("Please enter the quantity in number only.");
                    scan.next();
                }
            } while (!(isInteger));

            if (quantity <= checkQuantity(freshFlower.getProduct(itemSelection), normalPackage, discountedPackage) && quantity != 0) {
                quantityCheck = true;

            } else if (quantity > checkQuantity(freshFlower.getProduct(itemSelection), normalPackage, discountedPackage) || quantity == 0) {
                quantityCheck = false;
                System.out.println("");
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter a quantity that is not more that the current item's quantity" + FioreFlowershop.ConsoleColors.RESET);
            }

        } while (quantityCheck == false);

        //Calculate total price of the selected item
        if (freshFlower.getProduct(itemSelection).getDiscountRate() == 0) {
            itemPrice = freshFlower.getProduct(itemSelection).getPrice() * quantity;
            orderAmt += itemPrice;
        } else if (freshFlower.getProduct(itemSelection).getDiscountRate() != 0) {
            itemPrice = (double) ((100 - freshFlower.getProduct(itemSelection).getDiscountRate()) * freshFlower.getProduct(itemSelection).getPrice() / 100) * quantity;
            orderAmt += itemPrice;
        }

        //Add in the selected item inside the shoppingCart arraylist
        if (customer != null && corporate == null) {
            int accessory = 0;
            CatalogPackage catalogPackage3 = new CatalogPackage();

            if (freshFlower.getProduct(itemSelection).getDiscountRate() == 0) {
                for (int i = 1; i < normalPackage.getTotalEntries() + 1; i++) {
                    if (normalPackage.getProduct(i).getName().equals(freshFlower.getProduct(itemSelection).getName())) {
                        catalogPackage3 = normalPackage.getProduct(i);
                    }
                }
            } else if (freshFlower.getProduct(itemSelection).getDiscountRate() != 0) {
                for (int i = 1; i < discountedPackage.getTotalEntries() + 1; i++) {
                    if (discountedPackage.getProduct(i).getName().equals(freshFlower.getProduct(itemSelection).getName())) {
                        catalogPackage3 = discountedPackage.getProduct(i);
                    }
                }
            }

            //get the stock quantity of each item
            for (int i = 1; i < itemCatalog.getAccessories().getSize() + 1; i++) {
                if (itemCatalog.getAccessories().getItem(i).getName().equals(catalogPackage3.getAccessory().getName())) {
                    accessory = itemCatalog.getAccessories().getItem(i).getQuantity();
                }
            }

            int[] flower = new int[catalogPackage3.getFlowerList().getTotalEntries()];
            int[] flowerQuantity = new int[catalogPackage3.getFlowerList().getTotalEntries()];

            for (int i = 1; i < catalogPackage3.getFlowerList().getTotalEntries() + 1; i++) {
                flower[i - 1] = catalogPackage3.getFlowerList().getItem(i).getQuantity();
            }

            //update the stock quantity of each item
            catalogPackage3.getAccessory().setQuantity(accessory - quantity);

            String[] flowerStock = catalogPackage3.getFlowerNeeded().split(" ");
            for (int i = 0; i < catalogPackage3.getFlowerList().getTotalEntries(); i++) {
                flowerQuantity[i] = Integer.parseInt(flowerStock[i]);
            }

            for (int i = 1; i < catalogPackage3.getFlowerList().getTotalEntries() + 1; i++) {
                int stockLeft = flower[i - 1] - (flowerQuantity[i - 1] * quantity);
                catalogPackage3.getFlowerList().getItem(i).setQuantity(stockLeft);
            }

            int sameIndex = 0;
            boolean sameItem = false;
            for (int i = 1; i < catalogPack.getTotalEntries() + 1; i++) {
                if (freshFlower.getProduct(itemSelection).getName().equals(catalogPack.getProduct(i).getName())) {
                    sameItem = true;
                    sameIndex = i;
                }
            }
            if (sameItem) {
                catalogPack.getProduct(sameIndex).setUserQuantity(quantity + catalogPack.getProduct(sameIndex).getUserQuantity());
            } else if (!sameItem) {
                catalogPack.addProduct(new CatalogPackage(freshFlower.getProduct(itemSelection).getName(), freshFlower.getProduct(itemSelection).getStyle(), freshFlower.getProduct(itemSelection).getSize(), new Item(), freshFlower.getProduct(itemSelection).getFlowerPot(), freshFlower.getProduct(itemSelection).getAccessory(), freshFlower.getProduct(itemSelection).getProductType(), freshFlower.getProduct(itemSelection).getPromoMonth(), freshFlower.getProduct(itemSelection).getPromoYear(), freshFlower.getProduct(itemSelection).getPrice(), freshFlower.getProduct(itemSelection).getDiscountRate(), freshFlower.getProduct(itemSelection).getStatus(), quantity, freshFlower.getProduct(itemSelection).getFlowerNeeded()));
            }
        } else if (customer == null && corporate != null) {
            //calculate credit spent by corporate customer          
            creditSpent += itemPrice;

            corporate.setCreditSpent(creditSpent); //set credit spent for checking
            //CK MADE SOME CHANGES HERE
            if ((corporate.getMonthlyLimit() - corporate.getCreditSpent()) < 0) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Sorry, it seems that you will exceed the credit limit after this purchase." + FioreFlowershop.ConsoleColors.RESET);
                if (catalogPack.isEmpty()) {
                    creditSpent = 0;
                } else if (!catalogPack.isEmpty()) {
                    creditSpent -= itemPrice;
                    corporate.setCreditSpent(creditSpent);
                }
            } else if ((corporate.getMonthlyLimit() - corporate.getCreditSpent()) > 0) {
                int accessory = 0;
                CatalogPackage catalogPackage3 = new CatalogPackage();

                if (freshFlower.getProduct(itemSelection).getDiscountRate() == 0) {
                    for (int i = 1; i < normalPackage.getTotalEntries() + 1; i++) {
                        if (normalPackage.getProduct(i).getName().equals(freshFlower.getProduct(itemSelection).getName())) {
                            catalogPackage3 = normalPackage.getProduct(i);
                        }
                    }
                } else if (freshFlower.getProduct(itemSelection).getDiscountRate() != 0) {
                    for (int i = 1; i < discountedPackage.getTotalEntries() + 1; i++) {
                        if (discountedPackage.getProduct(i).getName().equals(freshFlower.getProduct(itemSelection).getName())) {
                            catalogPackage3 = discountedPackage.getProduct(i);
                        }
                    }
                }

                //Update stock quantity of each item in the package selected
                //get the stock quantity of each item
                for (int i = 1; i < itemCatalog.getAccessories().getSize() + 1; i++) {
                    if (itemCatalog.getAccessories().getItem(i).getName().equals(catalogPackage3.getAccessory().getName())) {
                        accessory = itemCatalog.getAccessories().getItem(i).getQuantity();
                    }
                }

                int[] flower = new int[catalogPackage3.getFlowerList().getTotalEntries()];
                int[] flowerQuantity = new int[catalogPackage3.getFlowerList().getTotalEntries()];

                for (int i = 1; i < catalogPackage3.getFlowerList().getTotalEntries() + 1; i++) {
                    flower[i - 1] = catalogPackage3.getFlowerList().getItem(i).getQuantity();
                }

                //update the stock quantity of each item
                catalogPackage3.getAccessory().setQuantity(accessory - quantity);

                String[] flowerStock = catalogPackage3.getFlowerNeeded().split(" ");
                for (int i = 0; i < catalogPackage3.getFlowerList().getTotalEntries(); i++) {
                    flowerQuantity[i] = Integer.parseInt(flowerStock[i]);
                }

                for (int i = 1; i < catalogPackage3.getFlowerList().getTotalEntries() + 1; i++) {
                    int stockLeft = flower[i - 1] - (flowerQuantity[i - 1] * quantity);
                    catalogPackage3.getFlowerList().getItem(i).setQuantity(stockLeft);
                }

                int sameIndex = 0;
                boolean sameItem = false;
                for (int i = 1; i < catalogPack.getTotalEntries() + 1; i++) {
                    if (freshFlower.getProduct(itemSelection).getName().equals(catalogPack.getProduct(i).getName())) {
                        sameItem = true;
                        sameIndex = i;
                    }
                }
                if (sameItem) {
                    catalogPack.getProduct(sameIndex).setUserQuantity(quantity + catalogPack.getProduct(sameIndex).getUserQuantity());
                } else if (!sameItem) {
                    catalogPack.addProduct(new CatalogPackage(freshFlower.getProduct(itemSelection).getName(), freshFlower.getProduct(itemSelection).getStyle(), freshFlower.getProduct(itemSelection).getSize(), new Item(), freshFlower.getProduct(itemSelection).getFlowerPot(), freshFlower.getProduct(itemSelection).getAccessory(), freshFlower.getProduct(itemSelection).getProductType(), freshFlower.getProduct(itemSelection).getPromoMonth(), freshFlower.getProduct(itemSelection).getPromoYear(), freshFlower.getProduct(itemSelection).getPrice(), freshFlower.getProduct(itemSelection).getDiscountRate(), freshFlower.getProduct(itemSelection).getStatus(), quantity, freshFlower.getProduct(itemSelection).getFlowerNeeded()));
                }
            }
        }
        displayShoppingCart(normalPackage, discountedPackage);
        System.out.print("\nDo you wish to browse through fresh flower? (Y/y = Yes, other keys = No)");
        String con = scan.next();

        if (con.trim().equalsIgnoreCase("Y")) {
            freshFlowerCatalog(normalPackage, discountedPackage);
        } else if (!con.trim().equalsIgnoreCase("Y")) {
            typeSelection(normalPackage, discountedPackage);
        }

    }

    public static void bouquetsCatalog(CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage) {
        String flowerList = ""; //to get the quantity of each flower in the package 
        System.out.println("\nDisplay catalog");
        System.out.println("========================================================================================================");
        System.out.println("Product types\t\t\t\t\tPrice\t\t\tDiscounted price");

        if (bouquetsCounter != 0 && bouquets.getTotalEntries() != 0) {
            System.out.println("\nBouquets");
            System.out.println("==================");
            for (int i = 1; i < bouquets.getTotalEntries() + 1; i++) {
                flowerList = getFlowers(bouquets.getProduct(i), normalPackage, discountedPackage);
                System.out.printf("%d. %s\n", i, bouquets.getProduct(i).getName());
                if (bouquets.getProduct(i).getDiscountRate() == 0) {
                    System.out.printf("%s,\n%-9s,%-6s,%-12s \t \t\tRM%7.2f\t\t        - \n\n", flowerList, bouquets.getProduct(i).getStyle().getName(), bouquets.getProduct(i).getSize().getName(), bouquets.getProduct(i).getAccessory().getName(), bouquets.getProduct(i).getPrice());
                } else if (bouquets.getProduct(i).getDiscountRate() != 0) {
                    double discountedPrice = (double) ((100 - bouquets.getProduct(i).getDiscountRate()) * bouquets.getProduct(i).getPrice() / 100);
                    System.out.printf("%s,\n%-9s,%-6s,%-12s \t \t\tRM%7.2f\t\t    RM%7.2f \n\n", flowerList, bouquets.getProduct(i).getStyle().getName(), bouquets.getProduct(i).getSize().getName(), bouquets.getProduct(i).getAccessory().getName(), bouquets.getProduct(i).getPrice(), discountedPrice);
                }
            }

        } else if (bouquets.getTotalEntries() == 0) {
            System.err.println("\nSorry, the item type you have selected is not available yet.");
            typeSelection(normalPackage, discountedPackage);
        }
        do {
            haveStock = true;

            System.out.print("Please enter your choice in number (Enter 0 to previous page):");
            if (scan.hasNextInt()) {
                String itemChoice = scan.next();
                int choiceCheck = 0;

                if (itemChoice.trim().length() > bouquets.getTotalEntries() || !isDigit(itemChoice.charAt(0))) {
                    isInteger = false;
                    System.out.println("");
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter your choice the number within the range only.\n" + FioreFlowershop.ConsoleColors.BLACK);
                } else if (itemChoice.length() <= bouquets.getTotalEntries() && isDigit(itemChoice.charAt(0))) { //more than shown range of package list
                    for (int i = 0; i < itemChoice.trim().length(); i++) {
                        if (isDigit(itemChoice.trim().charAt(i))) {
                            choiceCheck++;
                        }
                    }
                    if (itemChoice.charAt(0) == '0') {
                        typeSelection(normalPackage, discountedPackage);
                    }
                    if (choiceCheck == itemChoice.length()) {
                        itemSelection = Integer.valueOf(itemChoice);
                        if (itemSelection > bouquets.getTotalEntries() || itemSelection < 0) { //more than shown range of package list
                            isInteger = false;
                            System.out.println("");
                            System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter your choice the number within the range only." + FioreFlowershop.ConsoleColors.BLACK);
                        } else {
                            if (checkQuantity(bouquets.getProduct(itemSelection), normalPackage, discountedPackage) == 0) {
                                haveStock = false;
                                System.out.println("");
                                System.out.println(FioreFlowershop.ConsoleColors.RED + "Sorry, the item you have selected is out of stock.\n" + FioreFlowershop.ConsoleColors.BLACK);
                            } else {
                                isInteger = true;
                            }
                        }
                    } else if (choiceCheck != itemChoice.length()) { //other character is included besides integer
                        isInteger = false;
                        System.out.println("");
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter your choice the number within the range only." + FioreFlowershop.ConsoleColors.BLACK);
                    }
                }
            } else {
                isInteger = false;
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter your choice the number within the range only." + FioreFlowershop.ConsoleColors.BLACK);
                scan.next();
            }

        } while (itemSelection == 0 || itemSelection > bouquets.getTotalEntries() || !(isInteger) || !(haveStock));

        System.out.println("\nDisplay catalog - Quantity Selection");
        System.out.println("========================================================================================================");
        System.out.println("Product types\t\t\t\tQuantity\tPrice\t\t\tDiscounted price");
        System.out.printf("%s\n", bouquets.getProduct(itemSelection).getName());
        flowerList = getFlowers(bouquets.getProduct(itemSelection), normalPackage, discountedPackage);
        if (bouquets.getProduct(itemSelection).getDiscountRate() == 0) {
            System.out.printf("%s,\n%-10s,%-6s,%-12s  \t    %d  \t\tRM%7.2f\t\t        -\n\n", flowerList, bouquets.getProduct(itemSelection).getStyle().getName(), bouquets.getProduct(itemSelection).getSize().getName(), bouquets.getProduct(itemSelection).getAccessory().getName(), checkQuantity(bouquets.getProduct(itemSelection), normalPackage, discountedPackage), bouquets.getProduct(itemSelection).getPrice());
        } else if (bouquets.getProduct(itemSelection).getDiscountRate() != 0) {
            double discountedPrice = (double) ((100 - bouquets.getProduct(itemSelection).getDiscountRate()) * bouquets.getProduct(itemSelection).getPrice() / 100);
            System.out.printf("%s,\n%-10s,%-6s,%-12s  \t    %d  \t\tRM%7.2f\t\t    RM%7.2f\n\n", flowerList, bouquets.getProduct(itemSelection).getStyle().getName(), bouquets.getProduct(itemSelection).getSize().getName(), bouquets.getProduct(itemSelection).getAccessory().getName(), checkQuantity(bouquets.getProduct(itemSelection), normalPackage, discountedPackage), bouquets.getProduct(itemSelection).getPrice(), discountedPrice);

        }

        do {
            do {
                System.out.print("Please enter quantity of this item you want to order:");

                if (scan.hasNextInt()) {
                    quantity = scan.nextInt();
                    if (quantity < 0) {
                        isInteger = false;
                    } else if (quantity > 0) {
                        isInteger = true;
                    }
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the quantity in number only." + FioreFlowershop.ConsoleColors.BLACK);
                    scan.next();
                }
            } while (!(isInteger));

            if (quantity <= checkQuantity(bouquets.getProduct(itemSelection), normalPackage, discountedPackage) && quantity != 0) {
                quantityCheck = true;

            } else if (quantity > checkQuantity(bouquets.getProduct(itemSelection), normalPackage, discountedPackage) || quantity == 0) {
                quantityCheck = false;
                System.out.println("");
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter a quantity that is not more that the current item's quantity" + FioreFlowershop.ConsoleColors.RESET);
                System.out.println("");
            }
        } while (!(quantityCheck));

        //Calculate total price of the selected item
        if (bouquets.getProduct(itemSelection).getDiscountRate() == 0) {
            itemPrice = bouquets.getProduct(itemSelection).getPrice() * quantity;
            orderAmt += itemPrice;
        } else if (bouquets.getProduct(itemSelection).getDiscountRate() != 0) {
            itemPrice = (double) ((100 - bouquets.getProduct(itemSelection).getDiscountRate()) * bouquets.getProduct(itemSelection).getPrice() / 100) * quantity;
            orderAmt += itemPrice;
        }

        //Add in the selected item inside the shoppingCart arraylist       
        if (customer != null && corporate == null) {
            int accessory = 0;
            CatalogPackage catalogPackage3 = new CatalogPackage();

            if (bouquets.getProduct(itemSelection).getDiscountRate() == 0) {
                for (int i = 1; i < normalPackage.getTotalEntries() + 1; i++) {
                    if (normalPackage.getProduct(i).getName().equals(bouquets.getProduct(itemSelection).getName())) {
                        catalogPackage3 = normalPackage.getProduct(i);
                    }
                }
            } else if (bouquets.getProduct(itemSelection).getDiscountRate() != 0) {
                for (int i = 1; i < discountedPackage.getTotalEntries() + 1; i++) {
                    if (discountedPackage.getProduct(i).getName().equals(bouquets.getProduct(itemSelection).getName())) {
                        catalogPackage3 = discountedPackage.getProduct(i);
                    }
                }
            }

            //get the stock quantity of each item
            for (int i = 1; i < itemCatalog.getAccessories().getSize() + 1; i++) {
                if (itemCatalog.getAccessories().getItem(i).getName().equals(catalogPackage3.getAccessory().getName())) {
                    accessory = itemCatalog.getAccessories().getItem(i).getQuantity();
                }
            }

            int[] flower = new int[catalogPackage3.getFlowerList().getTotalEntries()];
            int[] flowerQuantity = new int[catalogPackage3.getFlowerList().getTotalEntries()];

            for (int i = 1; i < catalogPackage3.getFlowerList().getTotalEntries() + 1; i++) {
                flower[i - 1] = catalogPackage3.getFlowerList().getItem(i).getQuantity();
            }

            //update the stock quantity of each item
            catalogPackage3.getAccessory().setQuantity(accessory - quantity);

            String[] flowerStock = catalogPackage3.getFlowerNeeded().split(" ");
            for (int i = 0; i < catalogPackage3.getFlowerList().getTotalEntries(); i++) {
                flowerQuantity[i] = Integer.parseInt(flowerStock[i]);
            }

            for (int i = 1; i < catalogPackage3.getFlowerList().getTotalEntries() + 1; i++) {
                int stockLeft = flower[i - 1] - (flowerQuantity[i - 1] * quantity);
                catalogPackage3.getFlowerList().getItem(i).setQuantity(stockLeft);
            }

            int sameIndex = 0;
            boolean sameItem = false;
            for (int i = 1; i < catalogPack.getTotalEntries() + 1; i++) {
                if (bouquets.getProduct(itemSelection).getName().equals(catalogPack.getProduct(i).getName())) {
                    sameItem = true;
                    sameIndex = i;
                }
            }
            if (sameItem) {
                catalogPack.getProduct(sameIndex).setUserQuantity(quantity + catalogPack.getProduct(sameIndex).getUserQuantity());
            } else if (!sameItem) {
                catalogPack.addProduct(new CatalogPackage(bouquets.getProduct(itemSelection).getName(), bouquets.getProduct(itemSelection).getStyle(), bouquets.getProduct(itemSelection).getSize(), new Item(), bouquets.getProduct(itemSelection).getFlowerPot(), bouquets.getProduct(itemSelection).getAccessory(), bouquets.getProduct(itemSelection).getProductType(), bouquets.getProduct(itemSelection).getPromoMonth(), bouquets.getProduct(itemSelection).getPromoYear(), bouquets.getProduct(itemSelection).getPrice(), bouquets.getProduct(itemSelection).getDiscountRate(), bouquets.getProduct(itemSelection).getStatus(), quantity, bouquets.getProduct(itemSelection).getFlowerNeeded()));
            }
        } else if (customer == null && corporate != null) {
            //adding credit spent by corporate customer for selected item
            creditSpent += itemPrice;

            corporate.setCreditSpent(creditSpent); //set credit spent for checking
            //CK MADE SOME CHANGES HERE
            if ((corporate.getMonthlyLimit() - corporate.getCreditSpent()) < 0) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Sorry, it seems that you will exceed the credit limit after this purchase." + FioreFlowershop.ConsoleColors.RESET);
                if (catalogPack.isEmpty()) {
                    creditSpent = 0;
                } else if (!catalogPack.isEmpty()) {
                    creditSpent -= itemPrice;
                    corporate.setCreditSpent(creditSpent);
                }
            } else if ((corporate.getMonthlyLimit() - corporate.getCreditSpent()) > 0) {

                int accessory = 0;
                CatalogPackage catalogPackage3 = new CatalogPackage();

                if (bouquets.getProduct(itemSelection).getDiscountRate() == 0) {
                    for (int i = 1; i < normalPackage.getTotalEntries() + 1; i++) {
                        if (normalPackage.getProduct(i).getName().equals(bouquets.getProduct(itemSelection).getName())) {
                            catalogPackage3 = normalPackage.getProduct(i);
                        }
                    }
                } else if (bouquets.getProduct(itemSelection).getDiscountRate() != 0) {
                    for (int i = 1; i < discountedPackage.getTotalEntries() + 1; i++) {
                        if (discountedPackage.getProduct(i).getName().equals(bouquets.getProduct(itemSelection).getName())) {
                            catalogPackage3 = discountedPackage.getProduct(i);
                        }
                    }
                }

                //get the stock quantity of each item
                for (int i = 1; i < itemCatalog.getAccessories().getSize() + 1; i++) {
                    if (itemCatalog.getAccessories().getItem(i).getName().equals(catalogPackage3.getAccessory().getName())) {
                        accessory = itemCatalog.getAccessories().getItem(i).getQuantity();
                    }
                }

                int[] flower = new int[catalogPackage3.getFlowerList().getTotalEntries()];
                int[] flowerQuantity = new int[catalogPackage3.getFlowerList().getTotalEntries()];

                for (int i = 1; i < catalogPackage3.getFlowerList().getTotalEntries() + 1; i++) {
                    flower[i - 1] = catalogPackage3.getFlowerList().getItem(i).getQuantity();
                }

                //update the stock quantity of each item
                catalogPackage3.getAccessory().setQuantity(accessory - quantity);

                String[] flowerStock = catalogPackage3.getFlowerNeeded().split(" ");
                for (int i = 0; i < catalogPackage3.getFlowerList().getTotalEntries(); i++) {
                    flowerQuantity[i] = Integer.parseInt(flowerStock[i]);
                }

                for (int i = 1; i < catalogPackage3.getFlowerList().getTotalEntries() + 1; i++) {
                    int stockLeft = flower[i - 1] - (flowerQuantity[i - 1] * quantity);
                    catalogPackage3.getFlowerList().getItem(i).setQuantity(stockLeft);
                }

                int sameIndex = 0;
                boolean sameItem = false;
                for (int i = 1; i < catalogPack.getTotalEntries() + 1; i++) {
                    if (bouquets.getProduct(itemSelection).getName().equals(catalogPack.getProduct(i).getName())) {
                        sameItem = true;
                        sameIndex = i;
                    }
                }
                if (sameItem) {
                    catalogPack.getProduct(sameIndex).setUserQuantity(quantity + catalogPack.getProduct(sameIndex).getUserQuantity());
                } else if (!sameItem) {
                    catalogPack.addProduct(new CatalogPackage(bouquets.getProduct(itemSelection).getName(), bouquets.getProduct(itemSelection).getStyle(), bouquets.getProduct(itemSelection).getSize(), new Item(), bouquets.getProduct(itemSelection).getFlowerPot(), bouquets.getProduct(itemSelection).getAccessory(), bouquets.getProduct(itemSelection).getProductType(), bouquets.getProduct(itemSelection).getPromoMonth(), bouquets.getProduct(itemSelection).getPromoYear(), bouquets.getProduct(itemSelection).getPrice(), bouquets.getProduct(itemSelection).getDiscountRate(), bouquets.getProduct(itemSelection).getStatus(), quantity, bouquets.getProduct(itemSelection).getFlowerNeeded()));
                }
            }
        }
        displayShoppingCart(normalPackage, discountedPackage);
        String con;

        System.out.print("Do you wish to browse through bouquets? (Y/y = Yes, other keys = No)");
        con = scan.next();

        if (con.trim().equalsIgnoreCase("Y")) {
            bouquetsCatalog(normalPackage, discountedPackage);
        } else if (!con.trim().equalsIgnoreCase("Y")) {
            typeSelection(normalPackage, discountedPackage);
        }
    }

    public static void flowerArrangementCatalog(CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage) {
        String flowerList = "";
        System.out.println("\nDisplay catalog");
        System.out.println("========================================================================================================");
        System.out.println("Product types\t\t\t\t\tPrice\t\t\tDiscounted price");

        System.out.println("\nFlower Arrangement");
        System.out.println("==================");
        for (int i = 1; i < flowerArrangement.getTotalEntries() + 1; i++) {
            flowerList = getFlowers(flowerArrangement.getProduct(i), normalPackage, discountedPackage);
            System.out.printf("%d. %s\n", i, flowerArrangement.getProduct(i).getName());
            if (flowerArrangement.getProduct(i).getDiscountRate() == 0) {
                //String name, Item style, Item size, String season, Item flowerPot, Item accessory, String productType, String promoMonth, int promoYear, double price, int discountRate, String status, String flowerNeeded
                System.out.printf("%s,\n%s,%s,%s,\n%-13s,%-12s \t   \t\tRM%7.2f\t\t        -\n\n", flowerList, flowerArrangement.getProduct(i).getStyle().getName(), flowerArrangement.getProduct(i).getSize().getName(), flowerArrangement.getProduct(i).getSeason(), flowerArrangement.getProduct(i).getFlowerPot().getName(), flowerArrangement.getProduct(i).getAccessory().getName(), flowerArrangement.getProduct(i).getPrice());
            } else if (flowerArrangement.getProduct(i).getDiscountRate() != 0) {
                double discountedPrice = (double) ((100 - flowerArrangement.getProduct(i).getDiscountRate()) * flowerArrangement.getProduct(i).getPrice() / 100);
                System.out.printf("%s,\n%s,%s,%s,\n%-13s,%-12s \t   \t\tRM%7.2f\t\t    RM%7.2f \n\n", flowerList, flowerArrangement.getProduct(i).getStyle().getName(), flowerArrangement.getProduct(i).getSize().getName(), flowerArrangement.getProduct(i).getSeason(), flowerArrangement.getProduct(i).getFlowerPot().getName(), flowerArrangement.getProduct(i).getAccessory().getName(), flowerArrangement.getProduct(i).getPrice(), discountedPrice);
            }
        }
        if (flowerArrangementCounter != 0 && flowerArrangement.getTotalEntries() != 0) {
        } else if (flowerArrangement.getTotalEntries() == 0) {
            System.err.println("\nSorry, the item type you have selected is not available yet.");
            typeSelection(normalPackage, discountedPackage);
        }
        do {
            haveStock = true;

            System.out.print("Please enter your choice in number (Enter 0 to previous page):");

            if (scan.hasNextInt()) {
                String itemChoice = scan.next();
                int choiceCheck = 0;

                if (itemChoice.trim().length() > flowerArrangement.getTotalEntries() || !isDigit(itemChoice.charAt(0))) {
                    isInteger = false;
                    System.out.println("");
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter your choice the number within the range only.\n" + FioreFlowershop.ConsoleColors.BLACK);
                } else if (itemChoice.length() <= flowerArrangement.getTotalEntries() && isDigit(itemChoice.charAt(0))) { //more than shown range of package list
                    for (int i = 0; i < itemChoice.trim().length(); i++) {
                        if (isDigit(itemChoice.trim().charAt(i))) {
                            choiceCheck++;
                        }
                    }
                    if (itemChoice.charAt(0) == '0') {
                        typeSelection(normalPackage, discountedPackage);
                    }
                    if (choiceCheck == itemChoice.length()) {
                        itemSelection = Integer.valueOf(itemChoice);
                        if (itemSelection > flowerArrangement.getTotalEntries() || itemSelection < 0) { //more than shown range of package list
                            isInteger = false;
                            System.out.println("");
                            System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter your choice the number within the range only." + FioreFlowershop.ConsoleColors.BLACK);
                        } else {
                            if (checkQuantity(flowerArrangement.getProduct(itemSelection), normalPackage, discountedPackage) == 0) {
                                haveStock = false;
                                System.out.println("");
                                System.out.println(FioreFlowershop.ConsoleColors.RED + "Sorry, the item you have selected is out of stock.\n" + FioreFlowershop.ConsoleColors.BLACK);
                            } else {
                                isInteger = true;
                            }
                        }
                    } else if (choiceCheck != itemChoice.length()) { //other character is included besides integer
                        isInteger = false;
                        System.out.println("");
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter your choice the number within the range only." + FioreFlowershop.ConsoleColors.BLACK);
                    }
                }
            } else {
                isInteger = false;
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter your choice the number within the range only." + FioreFlowershop.ConsoleColors.BLACK);
                scan.next();
            }

        } while (!(isInteger) || !(haveStock));

        System.out.println("\nDisplay catalog - Quantity Selection");
        System.out.println("========================================================================================================");
        System.out.println("Product types\t\t\t\tQuantity\tPrice\t\t\tDiscounted price");

        System.out.printf("%s\n", flowerArrangement.getProduct(itemSelection).getName());
        flowerList = getFlowers(flowerArrangement.getProduct(itemSelection), normalPackage, discountedPackage);
        if (flowerArrangement.getProduct(itemSelection).getDiscountRate() == 0) {
            System.out.printf("%s,\n%s,%s,%s,\n%-13s,%-12s  \t\t %3d\t\tRM%7.2f\t\t        -\n\n", flowerList, flowerArrangement.getProduct(itemSelection).getStyle().getName(), flowerArrangement.getProduct(itemSelection).getSize().getName(), flowerArrangement.getProduct(itemSelection).getSeason(), flowerArrangement.getProduct(itemSelection).getFlowerPot().getName(), flowerArrangement.getProduct(itemSelection).getAccessory().getName(), checkQuantity(flowerArrangement.getProduct(itemSelection), normalPackage, discountedPackage), flowerArrangement.getProduct(itemSelection).getPrice());
        } else if (flowerArrangement.getProduct(itemSelection).getDiscountRate() != 0) {
            double discountedPrice = (double) ((100 - flowerArrangement.getProduct(itemSelection).getDiscountRate()) * flowerArrangement.getProduct(itemSelection).getPrice() / 100);
            System.out.printf("%s,\n%s,%s,%s,\n%-13s,%-12s  \t\t %3d\t\tRM%7.2f\t\t    RM%7.2f\n\n", flowerList, flowerArrangement.getProduct(itemSelection).getStyle().getName(), flowerArrangement.getProduct(itemSelection).getSize().getName(), flowerArrangement.getProduct(itemSelection).getSeason(), flowerArrangement.getProduct(itemSelection).getFlowerPot().getName(), flowerArrangement.getProduct(itemSelection).getAccessory().getName(), checkQuantity(flowerArrangement.getProduct(itemSelection), normalPackage, discountedPackage), flowerArrangement.getProduct(itemSelection).getPrice(), discountedPrice);
        }

        do {
            do {
                System.out.print("Please enter quantity of this item you want to order:");

                if (scan.hasNextInt()) {
                    quantity = scan.nextInt();
                    if (quantity < 0) {
                        isInteger = false;
                    } else if (quantity > 0) {
                        isInteger = true;
                    }
                } else {
                    isInteger = false;
                    System.err.println("Please enter the quantity in number only.");
                    scan.next();
                }
            } while (!(isInteger));

            if (quantity <= checkQuantity(flowerArrangement.getProduct(itemSelection), normalPackage, discountedPackage) && quantity != 0) {
                quantityCheck = true;

            } else if (quantity > checkQuantity(flowerArrangement.getProduct(itemSelection), normalPackage, discountedPackage) || quantity == 0) {
                quantityCheck = false;
                System.out.println("");
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter a quantity that is not more that the current item's quantity" + FioreFlowershop.ConsoleColors.RESET);
                System.out.println("");
            }
        } while (!(quantityCheck));

        //Calculate total price of the selected item
        if (flowerArrangement.getProduct(itemSelection).getDiscountRate() == 0) {
            itemPrice = flowerArrangement.getProduct(itemSelection).getPrice() * quantity;
            orderAmt += itemPrice;
        } else if (flowerArrangement.getProduct(itemSelection).getDiscountRate() != 0) {
            itemPrice = (double) ((100 - flowerArrangement.getProduct(itemSelection).getDiscountRate()) * flowerArrangement.getProduct(itemSelection).getPrice() / 100) * quantity;
            orderAmt += itemPrice;
        }

        //Add in the selected item inside the shoppingCart arraylist
        if (customer != null && corporate == null) {
            int sameIndex = 0;
            boolean sameItem = false;
            for (int i = 1; i < catalogPack.getTotalEntries() + 1; i++) {
                if (flowerArrangement.getProduct(itemSelection).getName().equals(catalogPack.getProduct(i).getName())) {
                    sameItem = true;
                    sameIndex = i;
                }
            }
            if (sameItem) {
                catalogPack.getProduct(sameIndex).setUserQuantity(quantity + catalogPack.getProduct(sameIndex).getUserQuantity());
            } else if (!sameItem) {
                //String name, Item style, Item size, String season, Item flowerPot, Item accessory, String productType, String promoMonth, int promoYear, double price, int discountRate, String status, int userQuantity, String flowerNeeded
                catalogPack.addProduct(new CatalogPackage(flowerArrangement.getProduct(itemSelection).getName(), flowerArrangement.getProduct(itemSelection).getStyle(), flowerArrangement.getProduct(itemSelection).getSize(), flowerArrangement.getProduct(itemSelection).getSeason(), flowerArrangement.getProduct(itemSelection).getFlowerPot(), flowerArrangement.getProduct(itemSelection).getAccessory(), flowerArrangement.getProduct(itemSelection).getProductType(), flowerArrangement.getProduct(itemSelection).getPromoMonth(), flowerArrangement.getProduct(itemSelection).getPromoYear(), flowerArrangement.getProduct(itemSelection).getPrice(), flowerArrangement.getProduct(itemSelection).getDiscountRate(), flowerArrangement.getProduct(itemSelection).getStatus(), quantity, flowerArrangement.getProduct(itemSelection).getFlowerNeeded()));
            }
        } else if (customer == null && corporate != null) {
            //add credit spent by corporate customer for selected item
            creditSpent += itemPrice;

            corporate.setCreditSpent(creditSpent); //set credit spent for checking
            //CK MADE SOME CHANGES HERE
            if ((corporate.getMonthlyLimit() - corporate.getCreditSpent()) < 0) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Sorry, it seems that you will exceed the credit limit after this purchase." + FioreFlowershop.ConsoleColors.RESET);
                if (catalogPack.isEmpty()) {
                    creditSpent = 0;
                } else if (!catalogPack.isEmpty()) {
                    creditSpent -= itemPrice;
                    corporate.setCreditSpent(creditSpent);
                }
            } else if ((corporate.getMonthlyLimit() - corporate.getCreditSpent()) > 0) {
                int accessory = 0;
                int flowerPot = 0;
                CatalogPackage catalogPackage3 = new CatalogPackage();

                if (flowerArrangement.getProduct(itemSelection).getDiscountRate() == 0) {
                    for (int i = 1; i < normalPackage.getTotalEntries() + 1; i++) {
                        if (normalPackage.getProduct(i).getName().equals(flowerArrangement.getProduct(itemSelection).getName())) {
                            catalogPackage3 = normalPackage.getProduct(i);
                        }
                    }
                } else if (flowerArrangement.getProduct(itemSelection).getDiscountRate() != 0) {
                    for (int i = 1; i < discountedPackage.getTotalEntries() + 1; i++) {
                        if (discountedPackage.getProduct(i).getName().equals(flowerArrangement.getProduct(itemSelection).getName())) {
                            catalogPackage3 = discountedPackage.getProduct(i);
                        }
                    }
                }

                //get the stock quantity of each item
                for (int i = 1; i < itemCatalog.getAccessories().getSize() + 1; i++) {
                    if (itemCatalog.getAccessories().getItem(i).getName().equals(catalogPackage3.getAccessory().getName())) {
                        accessory = itemCatalog.getAccessories().getItem(i).getQuantity();
                    }
                    if (itemCatalog.getFlowerPot().getItem(i).getName().equals(catalogPackage3.getFlowerPot().getName())) {
                        flowerPot = itemCatalog.getFlowerPot().getItem(i).getQuantity();
                    }
                }

                int[] flower = new int[catalogPackage3.getFlowerList().getTotalEntries()];
                int[] flowerQuantity = new int[catalogPackage3.getFlowerList().getTotalEntries()];

                for (int i = 1; i < catalogPackage3.getFlowerList().getTotalEntries() + 1; i++) {
                    flower[i - 1] = catalogPackage3.getFlowerList().getItem(i).getQuantity();
                }

                //update the stock quantity of each item
                catalogPackage3.getAccessory().setQuantity(accessory - quantity);
                catalogPackage3.getFlowerPot().setQuantity(flowerPot - quantity);

                String[] flowerStock = catalogPackage3.getFlowerNeeded().split(" ");
                for (int i = 0; i < catalogPackage3.getFlowerList().getTotalEntries(); i++) {
                    flowerQuantity[i] = Integer.parseInt(flowerStock[i]);
                }

                for (int i = 1; i < catalogPackage3.getFlowerList().getTotalEntries() + 1; i++) {
                    int stockLeft = flower[i - 1] - (flowerQuantity[i - 1] * quantity);
                    catalogPackage3.getFlowerList().getItem(i).setQuantity(stockLeft);
                }

                int sameIndex = 0;
                boolean sameItem = false;
                for (int i = 1; i < catalogPack.getTotalEntries() + 1; i++) {
                    if (flowerArrangement.getProduct(itemSelection).getName().equals(catalogPack.getProduct(i).getName())) {
                        sameItem = true;
                        sameIndex = i;
                    }
                }
                if (sameItem) {
                    catalogPack.getProduct(sameIndex).setUserQuantity(quantity + catalogPack.getProduct(sameIndex).getUserQuantity());
                } else if (!sameItem) {
                    catalogPack.addProduct(new CatalogPackage(flowerArrangement.getProduct(itemSelection).getName(), flowerArrangement.getProduct(itemSelection).getStyle(), flowerArrangement.getProduct(itemSelection).getSize(), flowerArrangement.getProduct(itemSelection).getSeason(), flowerArrangement.getProduct(itemSelection).getFlowerPot(), flowerArrangement.getProduct(itemSelection).getAccessory(), flowerArrangement.getProduct(itemSelection).getProductType(), flowerArrangement.getProduct(itemSelection).getPromoMonth(), flowerArrangement.getProduct(itemSelection).getPromoYear(), flowerArrangement.getProduct(itemSelection).getPrice(), flowerArrangement.getProduct(itemSelection).getDiscountRate(), flowerArrangement.getProduct(itemSelection).getStatus(), quantity, flowerArrangement.getProduct(itemSelection).getFlowerNeeded()));
                }
            }
        }
        displayShoppingCart(normalPackage, discountedPackage);
        System.out.print("Do you wish to browse through flower arrangement? (Y/y = Yes, other keys = No)");
        String con = scan.next();

        if (con.trim().equalsIgnoreCase("Y")) {
            flowerArrangementCatalog(normalPackage, discountedPackage);
        } else if (!con.trim().equalsIgnoreCase("Y")) {
            typeSelection(normalPackage, discountedPackage);
        }
    }
}

