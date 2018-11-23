/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.adt.ArrayList;
import com.mycompany.fioreflowershop.adt.ListInterface;
import com.mycompany.fioreflowershop.modal.CatalogPackage;
import com.mycompany.fioreflowershop.modal.Consumer;
import com.mycompany.fioreflowershop.modal.CorporateCustomer;
import com.mycompany.fioreflowershop.modal.Order;
import com.mycompany.fioreflowershop.modal.CatalogOrder1;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Nicholas
 */
public class CatalogOrder {

    static ListInterface<CatalogOrder1> shoppingCart = FioreFlowershop.getShoppingCart();

    static ListInterface<Order> conOrder = new ArrayList<>();
    static ListInterface<Order> corpOrder = new ArrayList<>();

//    //Define item array of CatalgPackage class
//    private static ArrayList<CatalogPackage> freshFlower = new ArrayList<>();
//    private static ArrayList<CatalogPackage> bouquets = new ArrayList<>();
//    private static ArrayList<CatalogPackage> flowerArrangement = new ArrayList<>();
//    private static ArrayList<CatalogPackage> freshFlowerDiscounted = new ArrayList<>();
//    private static ArrayList<CatalogPackage> bouquetsDiscounted = new ArrayList<>();
//    private static ArrayList<CatalogPackage> flowerArrangementDiscounted = new ArrayList<>();
    //Define shopping cart queue of CatalogOrder
    private static Consumer customer;
    private static CorporateCustomer corporate;

    //create a scanner object to get user input
    private static Scanner scan = new Scanner(System.in);
    private static int userMenuOption;
    private static int itemSelection;
    private static double itemPrice;
    private static int quantity;
    private static String orderType = "Pickup";
    private static boolean isInteger;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static Date todayDate = new Date();
    private static long pickupTime = todayDate.getTime(); // or delivery time

    public static void CustomerOrderMain(ListInterface cart, Consumer customerLoggedIn, ArrayList<CatalogPackage> freshFlower, ArrayList<CatalogPackage> bouquets, ArrayList<CatalogPackage> flowerArrangement, ArrayList<CatalogPackage> freshFlowerDiscounted, ArrayList<CatalogPackage> bouquetsDiscounted, ArrayList<CatalogPackage> flowerArrangementDiscounted) {
//        testing();
        //ListInterface<CatalogOrder1> sCart = cart;
        customer = customerLoggedIn;
        displayCatalog(freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted);

    }

    public static void CorporateOrderMain(ListInterface cart, CorporateCustomer customerLoggedIn, ArrayList<CatalogPackage> freshFlower, ArrayList<CatalogPackage> bouquets, ArrayList<CatalogPackage> flowerArrangement, ArrayList<CatalogPackage> freshFlowerDiscounted, ArrayList<CatalogPackage> bouquetsDiscounted, ArrayList<CatalogPackage> flowerArrangementDiscounted) {
        //ListInterface<CatalogOrder1> sCart = cart;
        corporate = customerLoggedIn;
        displayCatalog(freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted);
    }

    //Display the catalog or monthly promotion catalog
    public static void displayCatalog(ArrayList<CatalogPackage> freshFlower, ArrayList<CatalogPackage> bouquets, ArrayList<CatalogPackage> flowerArrangement, ArrayList<CatalogPackage> freshFlowerDiscounted, ArrayList<CatalogPackage> bouquetsDiscounted, ArrayList<CatalogPackage> flowerArrangementDiscounted) {
        do {
            System.out.println("\n Catalog Order");
            System.out.println("===================");
            System.out.println("1. Catalog");
//            System.out.println("2. Monthly promotion catalog");
            System.out.println("2. Back");
            System.out.print("Please enter a number (1-2): ");
            if (scan.hasNextInt()) {
                userMenuOption = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.err.println("Please enter the number only.");
                scan.next();
            }
        } while (!(isInteger) || userMenuOption != 1 || userMenuOption != 2);
        if (userMenuOption == 2) {
            FioreFlowershop.userTypeSelection();
        } else if (userMenuOption == 1) {
            typeSelection(freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted);
        } //else if (userMenuOption == 2) {
//            monthlyPromotionCatalog(); // this part havent do the catalog order 
//        }
    }

    public static void typeSelection(ArrayList<CatalogPackage> freshFlower, ArrayList<CatalogPackage> bouquets, ArrayList<CatalogPackage> flowerArrangement, ArrayList<CatalogPackage> freshFlowerDiscounted, ArrayList<CatalogPackage> bouquetsDiscounted, ArrayList<CatalogPackage> flowerArrangementDiscounted) {
        do {

            System.out.println("\nNormal Catalog - Type Selection");
            System.out.println("===============================");
            System.out.println("1.Fresh Flower");
            System.out.println("2.Bonquet");
            System.out.println("3.Flower Arrangement");
            System.out.println("4.Back");
            System.out.print("Please enter your choice (1-4):");
            if (scan.hasNextInt()) {
                userMenuOption = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.err.println("Please enter your choice (1-4).");
                scan.next();
            }
        } while (!(isInteger) || userMenuOption < 1 || userMenuOption > 4);
        if (userMenuOption == 4) {
            displayCatalog(freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted);
        } else if (userMenuOption == 1) {
            freshFlowerCatalog(freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted);
        } else if (userMenuOption == 2) {
            bouquetsCatalog(freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted);
        } else if (userMenuOption == 3) {
            flowerArrangementCatalog(freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted);
        }

    }

    //Display the normal catalog
    public static void freshFlowerCatalog(ArrayList<CatalogPackage> freshFlower, ArrayList<CatalogPackage> bouquets, ArrayList<CatalogPackage> flowerArrangement, ArrayList<CatalogPackage> freshFlowerDiscounted, ArrayList<CatalogPackage> bouquetsDiscounted, ArrayList<CatalogPackage> flowerArrangementDiscounted) {
        CatalogPackage catalogPackage = new CatalogPackage();
        System.out.println("\nDisplay catalog - normal catalog");
        System.out.println("================================================================================================");
        System.out.println("Product types\t\t\t\t\tPrice\t\t\tDiscounted price");
        if (freshFlower.getTotalEntries() != 0) {
            System.out.println("Fresh Flower");
            System.out.println("==================");
            for (int i = 1; i < freshFlower.getTotalEntries() + 1; i++) {
                catalogPackage = freshFlower.getItem(i);
                double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                if (discountRate == catalogPackage.getPrice()) {
                    System.out.printf("%d. %s\n", i, catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t     RM%.2f\t\t\t    - \n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getPrice());
                } else {
                    System.out.printf("%d. %s\n", i, catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t     RM%.2f\t\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getPrice(), discountRate);
                }

                if (catalogPackage.getDiscountRate() > 0) {
                    freshFlowerDiscounted.add(catalogPackage);
                }
            }
        } else if (freshFlower.getTotalEntries() == 0) {
            System.out.println("Sorry, the item type you  selected is not available yet.");
            typeSelection(freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted);
        }

        do {
            System.out.print("Please enter your choice in number:");
//            itemSelection = scan.nextInt();
            if (scan.hasNextInt()) {
                itemSelection = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.err.println("Please enter your choice in number only.");
                scan.next();
            }

        } while (itemSelection == 0 || itemSelection > freshFlower.getTotalEntries() || !(isInteger));
        catalogPackage = freshFlower.getItem(itemSelection);
        System.out.println("\nDisplay catalog - Quantity Selection");
        System.out.println("================================================================================================");
        System.out.println("Product types\t\t\t\tPrice\t\t\tDiscounted price");
        double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
        if (discountRate == catalogPackage.getPrice()) {
            System.out.printf("%s\n", catalogPackage.getName());
            System.out.printf("%s,%s,%s,%s \tRM%.2f\t\t\t  - \n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getPrice());
        } else {
            System.out.printf("%s\n", catalogPackage.getName());
            System.out.printf("%s,%s,%s,%s \tRM%.2f\t\t   RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getPrice(), discountRate);
        }

        if (catalogPackage.getDiscountRate() > 0) {
            freshFlowerDiscounted.add(catalogPackage);
        }
        do {
            System.out.print("Please enter quantity of this item you want to order:");
//            quantity = scan.nextInt();
            if (scan.hasNextInt()) {
                quantity = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.err.println("Please enter quantity in number only.");
                scan.next();
            }
        } while (!(isInteger));

        //Calculate total price of the selected item
        if (discountRate == catalogPackage.getPrice()) {
            itemPrice = freshFlower.getItem(itemSelection).getPrice() * quantity;
        } else {
            itemPrice = (double) (((100 - freshFlower.getItem(itemSelection).getDiscountRate()) * freshFlower.getItem(itemSelection).getPrice() / 100) * quantity);
        }

        //Add in the selected item inside the shoppingCart arraylist
        if (customer != null && corporate == null) {
            shoppingCart.add(new CatalogOrder1(customer, orderType, pickupTime, freshFlower.getItem(itemSelection).getName(), freshFlower.getItem(itemSelection).getStyle(), freshFlower.getItem(itemSelection).getSize(), freshFlower.getItem(itemSelection).getFlower(), freshFlower.getItem(itemSelection).getAccessory(), itemPrice, quantity));
        } else if (customer == null && corporate != null) {
            shoppingCart.add(new CatalogOrder1(corporate, orderType, pickupTime, freshFlower.getItem(itemSelection).getName(), freshFlower.getItem(itemSelection).getStyle(), freshFlower.getItem(itemSelection).getSize(), freshFlower.getItem(itemSelection).getFlower(), freshFlower.getItem(itemSelection).getAccessory(), itemPrice, quantity));
        }

        System.out.println("\nDisplay Shopping Cart");
        System.out.println("================================================================================================");
        System.out.println("Product types\t\t\t\tPrice\t\tQuantity");
        if (!shoppingCart.isEmpty()) {
            for (int i = 1; i < shoppingCart.getTotalEntries() + 1; i++) {
                System.out.printf("%s\n", shoppingCart.getItem(i).getItemName());
                System.out.printf("%s,%s,%s,%s \tRM%.2f\t   %d\n\n", shoppingCart.getItem(i).getItemStyle(), shoppingCart.getItem(i).getItemSize(), shoppingCart.getItem(i).getItemFlower(), shoppingCart.getItem(i).getItemAccesscory(), shoppingCart.getItem(i).getItemPrice(), shoppingCart.getItem(i).getItemQuantity());
            }
//            discountRate = (double) ((100 - freshFlower.getItem(itemSelection).getDiscountRate()) * freshFlower.getItem(itemSelection).getPrice() / 100);
//            System.out.printf("%s\n", freshFlower.getItem(itemSelection).getName());
//            System.out.printf("%s,%s,%s,%s \tRM%.2f\t   %d\n\n", freshFlower.getItem(itemSelection).getStyle(), freshFlower.getItem(itemSelection).getSize(), freshFlower.getItem(itemSelection).getFlower(), freshFlower.getItem(itemSelection).getAccessory(), discountRate * quantity, quantity);

        }
        System.out.print("Do you wish to browse through fresh flower? (Y/y = Yes, other keys = No)");
        String con = scan.next();

        if (con.equalsIgnoreCase("Y")) {
            freshFlowerCatalog(freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted);
        } else {
            typeSelection(freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted);
        }

//        System.out.println("\nDiscounted Product List");
//        System.out.println("================================================================================================");
//        System.out.println("Product types\t\t\t\tQuantity\tPrice\t\t\tDiscounted price");
//        if (freshFlowerDiscounted.getTotalEntries() != 0) {
//            System.out.println("Fresh Flower");
//            System.out.println("==================");
//            for (int i = 1; i < freshFlowerDiscounted.getTotalEntries() + 1; i++) {
//                catalogPackage = freshFlowerDiscounted.getItem(i);
//                double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
//                System.out.printf("%s\n", catalogPackage.getName());
//                System.out.printf("%s,%s,%s,%s \t   %d\t\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
//            }
//        }   
//        System.out.println("\n====================================================");
//        char userOption;
//        do {
//            System.out.print("Please enter 'Y/y' to Catalog maintenance menu: ");
//            userOption = scan.next().charAt(0);
//            System.out.println(userOption);
//
//        } while (userOption != 'y' && userOption != 'Y');
//
//        if (userOption == 'y' || userOption == 'Y') {
//            catalogMaintenanceMenu();
//        }
    }

    public static void bouquetsCatalog(ArrayList<CatalogPackage> freshFlower, ArrayList<CatalogPackage> bouquets, ArrayList<CatalogPackage> flowerArrangement, ArrayList<CatalogPackage> freshFlowerDiscounted, ArrayList<CatalogPackage> bouquetsDiscounted, ArrayList<CatalogPackage> flowerArrangementDiscounted) {
        CatalogPackage catalogPackage = new CatalogPackage();
        System.out.println("\nDisplay catalog - normal catalog");
        System.out.println("================================================================================================");
        System.out.println("Product types\t\t\t\t\tPrice\t\t\tDiscounted price");
        if (bouquets.getTotalEntries() != 0) {
            System.out.println("\nBouquets");
            System.out.println("==================");
            for (int i = 1; i < bouquets.getTotalEntries() + 1; i++) {
                catalogPackage = bouquets.getItem(i);
                double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                if (discountRate == catalogPackage.getPrice()) {
                    System.out.printf("%d. %s\n", i, catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t\tRM%.2f\t\t  - \n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getPrice());
                } else {
                    System.out.printf("%d. %s\n", i, catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t\tRM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getPrice(), discountRate);
                }
                if (catalogPackage.getDiscountRate() > 0) {
                    bouquetsDiscounted.add(catalogPackage);
                }
            }
        } else if (bouquets.getTotalEntries() == 0) {
            System.out.println("Sorry, the item type you  selected is not available yet.");
            typeSelection(freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted);
        }
        do {
            System.out.print("Please enter your choice in number:");
//            itemSelection = scan.nextInt();
            if (scan.hasNextInt()) {
                itemSelection = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.err.println("Please enter your choice number only.");
                scan.next();
            }

        } while (itemSelection == 0 || itemSelection > bouquets.getTotalEntries() || !(isInteger));
        catalogPackage = bouquets.getItem(itemSelection);
        System.out.println("\nDisplay catalog - Quantity Selection");
        System.out.println("================================================================================================");
        System.out.println("Product types\t\t\t\tPrice\t\t\tDiscounted price");
        double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
        if (discountRate == bouquets.getItem(itemSelection).getPrice()) {
            System.out.printf("%s\n", catalogPackage.getName());
            System.out.printf("%s,%s,%s,%s \tRM%.2f\t\t\t  - \n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getPrice());
        } else {
            System.out.printf("%s\n", catalogPackage.getName());
            System.out.printf("%s,%s,%s,%s \tRM%.2f\t\t   RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getPrice(), discountRate);
        }

        if (catalogPackage.getDiscountRate() > 0) {
            bouquetsDiscounted.add(catalogPackage);
        }
        do {
            System.out.print("Please enter quantity of this item you want to order:");
//            quantity = scan.nextInt();
            if (scan.hasNextInt()) {
                quantity = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.err.println("Please enter your choice number only.");
                scan.next();
            }
        } while (!(isInteger));

        //Calculate total price of the selected item
        if (discountRate == catalogPackage.getPrice()) {
            itemPrice = bouquets.getItem(itemSelection).getPrice() * quantity;
        } else {
            itemPrice = (double) (((100 - bouquets.getItem(itemSelection).getDiscountRate()) * bouquets.getItem(itemSelection).getPrice() / 100) * quantity);
        }

        //Add in the selected item inside the shoppingCart arraylist       
        if (customer != null && corporate == null) {
            shoppingCart.add(new CatalogOrder1(customer, orderType, pickupTime, bouquets.getItem(itemSelection).getName(), bouquets.getItem(itemSelection).getStyle(), bouquets.getItem(itemSelection).getSize(), bouquets.getItem(itemSelection).getFlower(), bouquets.getItem(itemSelection).getAccessory(), itemPrice, quantity));
        } else if (customer == null && corporate != null) {
            shoppingCart.add(new CatalogOrder1(corporate, orderType, pickupTime, bouquets.getItem(itemSelection).getName(), bouquets.getItem(itemSelection).getStyle(), bouquets.getItem(itemSelection).getSize(), bouquets.getItem(itemSelection).getFlower(), bouquets.getItem(itemSelection).getAccessory(), itemPrice, quantity));
        }

        System.out.println("\nDisplay Shopping Cart");
        System.out.println("================================================================================================");
        System.out.println("Product types\t\t\t\tPrice\t\tQuantity");
        if (!shoppingCart.isEmpty()) {
            for (int i = 1; i < shoppingCart.getTotalEntries() + 1; i++) {
                System.out.printf("%s\n", shoppingCart.getItem(i).getItemName());
                System.out.printf("%s,%s,%s,%s \tRM%.2f\t\t   %d\n\n", shoppingCart.getItem(i).getItemStyle(), shoppingCart.getItem(i).getItemSize(), shoppingCart.getItem(i).getItemFlower(), shoppingCart.getItem(i).getItemAccesscory(), shoppingCart.getItem(i).getItemPrice(), shoppingCart.getItem(i).getItemQuantity());
            }

        }
        String con;
        do {
            System.out.print("Do you wish to browse through bouquets? (Y/y = Yes, other keys = No)");
            con = scan.next();

            if (con != null && !con.isEmpty()) {
                System.err.println("Please do not leave this field blank.");
            }
        } while (con == null && con.isEmpty());

        if (con.equalsIgnoreCase("Y")) {
            bouquetsCatalog(freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted);
        } else {
            typeSelection(freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted);
        }
    }

    public static void flowerArrangementCatalog(ArrayList<CatalogPackage> freshFlower, ArrayList<CatalogPackage> bouquets, ArrayList<CatalogPackage> flowerArrangement, ArrayList<CatalogPackage> freshFlowerDiscounted, ArrayList<CatalogPackage> bouquetsDiscounted,ArrayList<CatalogPackage> flowerArrangementDiscounted) {
        CatalogPackage catalogPackage = new CatalogPackage();
        System.out.println("\nDisplay catalog - normal catalog");
        System.out.println("================================================================================================");
        System.out.println("Product types\t\t\t\tQuantity\tPrice\t\t\tDiscounted price");
        if (flowerArrangement.getTotalEntries() != 0) {
            System.out.println("\nFlower Arrangement");
            System.out.println("==================");
            for (int i = 1; i < flowerArrangement.getTotalEntries() + 1; i++) {
                catalogPackage = flowerArrangement.getItem(i);
                double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                if (discountRate == catalogPackage.getPrice()) {
                    System.out.printf("%s\n", catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t   %d\t\t RM%.2f\t\t  - \n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice());
                } else {
                    System.out.printf("%s\n", catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t   %d\t\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
                }
                if (catalogPackage.getDiscountRate() > 0) {
                    flowerArrangementDiscounted.add(catalogPackage);
                }
            }
        } else if (flowerArrangement.getTotalEntries() == 0) {
            System.out.println("Sorry, the item type you  selected is not available yet.");
            typeSelection(freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted);
        }
        do {
            System.out.print("Please enter your choice in number:");
            itemSelection = scan.nextInt();
            if (scan.hasNextInt()) {
                itemSelection = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.err.println("Please enter your choice in number only.");
                scan.next();
            }

        } while (itemSelection == 0 || itemSelection > flowerArrangement.getTotalEntries() || !(isInteger));
        catalogPackage = flowerArrangement.getItem(itemSelection);
        System.out.println("\nDisplay catalog - Quantity Selection");
        System.out.println("================================================================================================");
        System.out.println("Product types\t\t\t\tPrice\t\t\tDiscounted price");
        double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
        if (discountRate == catalogPackage.getPrice()) {
            System.out.printf("%s\n", catalogPackage.getName());
            System.out.printf("%s,%s,%s,%s \tRM%.2f\t\t\t  - \n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getPrice());
        } else {
            System.out.printf("%s\n", catalogPackage.getName());
            System.out.printf("%s,%s,%s,%s \tRM%.2f\t\t   RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getPrice(), discountRate);
        }

        if (catalogPackage.getDiscountRate() > 0) {
            flowerArrangementDiscounted.add(catalogPackage);
        }
        do {
            System.out.print("Please enter quantity of this item you want to order:");
//            quantity = scan.nextInt();
            if (scan.hasNextInt()) {
                quantity = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.err.println("Please enter quantity in number only.");
                scan.next();
            }

        } while (!(isInteger));

        //Calculate total price of the selected item
        if (discountRate == catalogPackage.getPrice()) {
            itemPrice = flowerArrangement.getItem(itemSelection).getPrice() * quantity;
        } else {
            itemPrice = (double) (((100 - flowerArrangement.getItem(itemSelection).getDiscountRate()) * flowerArrangement.getItem(itemSelection).getPrice() / 100) * quantity);
        }

        //Add in the selected item inside the shoppingCart arraylist
        if (customer != null && corporate == null) {
            shoppingCart.add(new CatalogOrder1(customer, orderType, pickupTime, flowerArrangement.getItem(itemSelection).getName(), flowerArrangement.getItem(itemSelection).getStyle(), flowerArrangement.getItem(itemSelection).getSize(), flowerArrangement.getItem(itemSelection).getFlower(), flowerArrangement.getItem(itemSelection).getAccessory(), itemPrice, quantity));
        } else if (customer == null && corporate != null) {
            shoppingCart.add(new CatalogOrder1(corporate, orderType, pickupTime, flowerArrangement.getItem(itemSelection).getName(), flowerArrangement.getItem(itemSelection).getStyle(), flowerArrangement.getItem(itemSelection).getSize(), flowerArrangement.getItem(itemSelection).getFlower(), flowerArrangement.getItem(itemSelection).getAccessory(), itemPrice, quantity));
        }
        System.out.println("\nDisplay Shopping Cart");
        System.out.println("================================================================================================");
        System.out.println("Product types\t\t\t\tPrice\t\tQuantity");
        if (!shoppingCart.isEmpty()) {
            for (int i = 1; i < shoppingCart.getTotalEntries() + 1; i++) {
                System.out.printf("%s\n", shoppingCart.getItem(i).getItemName());
                System.out.printf("%s,%s,%s,%s \tRM%.2f\t   %d\n\n", shoppingCart.getItem(i).getItemStyle(), shoppingCart.getItem(i).getItemSize(), shoppingCart.getItem(i).getItemFlower(), shoppingCart.getItem(i).getItemAccesscory(), shoppingCart.getItem(i).getItemPrice(), shoppingCart.getItem(i).getItemQuantity());
            }

        }
        System.out.print("Do you wish to browse through fresh flower? (Y/y = Yes, other keys = No)");
        String con = scan.next();

        if (con.equalsIgnoreCase("Y")) {
            flowerArrangementCatalog(freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted);
        } else {
            typeSelection(freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted);
        }

    }

    //Display the monthly promotion catalog
//    public static void monthlyPromotionCatalog(customerLoggedIn,freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted) {
//        CatalogPackage catalogPackage = new CatalogPackage();
//        System.out.println("\nDisplay catalog - Monthly promotion catalog");
//        System.out.println("================================================================================================");
//        System.out.println("Product types\t\t\t\tQuantity\tPrice\t\t\tDiscounted price");
//        if (freshFlowerDiscounted.getTotalEntries() != 0) {
//            System.out.println("Fresh Flower");
//            System.out.println("==================");
//            for (int i = 1; i < freshFlowerDiscounted.getTotalEntries() + 1; i++) {
//                catalogPackage = freshFlowerDiscounted.getItem(i);
//                double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
//                System.out.printf("%s\n", catalogPackage.getName());
//                System.out.printf("%s,%s,%s,%s \t   %d\t\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
//            }
//        }
//
//        if (bouquetsDiscounted.getTotalEntries() != 0) {
//            System.out.println("\nBouquets");
//            System.out.println("==================");
//            for (int i = 1; i < bouquetsDiscounted.getTotalEntries() + 1; i++) {
//                catalogPackage = bouquetsDiscounted.getItem(i);
//                double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
//                System.out.printf("%s\n", catalogPackage.getName());
//                System.out.printf("%s,%s,%s,%s \t   %d\t\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
//            }
//        }
//
//        if (flowerArrangementDiscounted.getTotalEntries() != 0) {
//            System.out.println("\nFlower Arrangement");
//            System.out.println("==================");
//            for (int i = 1; i < flowerArrangementDiscounted.getTotalEntries() + 1; i++) {
//                catalogPackage = flowerArrangementDiscounted.getItem(i);
//                double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
//                System.out.printf("%s\n", catalogPackage.getName());
//                System.out.printf("%s,%s,%s,%s \t   %d\t\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
//            }
//        }
//        System.out.println("\n====================================================");
//        char userOption;
//        do {
//            System.out.print("Please enter 'Y/y' to Catalog maintenance menu: ");
//            userOption = scan.next().charAt(0);
//            System.out.println(userOption);
//
//        } while (userOption != 'y' && userOption != 'Y');
//
//        if (userOption == 'y' || userOption == 'Y') {
//            catalogMaintenanceMenu();
//        }
//    }
    //Dummy data
//    public static void testing() {
//        //testing purpose
//        freshFlower.add(new CatalogPackage("Package 1.0", "Style 1.0", "Small", "Flower 1.0", "Bear 1.0", 10, 100.00, 20));
//        freshFlower.add(new CatalogPackage("Package 1.1", "Style 1.1", "Large", "Flower 1.1", "Bear 1.1", 10, 100.00, 0));
//        bouquets.add(new CatalogPackage("Package 2.0", "Style 2.0", "Small", "Flower 2.0", "Bear 2.0", 10, 100.00, 60));
//        bouquets.add(new CatalogPackage("Package 2.1", "Style 2.1", "Small", "Flower 2.1", "Bear 2.1", 10, 100.00, 0));
//    }
    public static void initializeData(ListInterface pickupOrder, ListInterface deliveryOrder) {

        Consumer con = new Consumer("Ncct96", "adgfafgjyaf", "0128198471", "Taiping");
        CorporateCustomer corp = new CorporateCustomer("Bxxx1", "sdgsjhd@gmail", "0165939123", "Ipoh", "211221", "TARUC", 5000);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2018);
        cal.set(Calendar.DAY_OF_MONTH, 13);
        cal.set(Calendar.MONTH, 10);
        cal.set(Calendar.HOUR_OF_DAY, 03);
        cal.set(Calendar.MINUTE, 30);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, 2018);
        cal1.set(Calendar.DAY_OF_MONTH, 20);
        cal1.set(Calendar.MONTH, 5);
        cal1.set(Calendar.HOUR_OF_DAY, 18);
        cal1.set(Calendar.MINUTE, 30);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);

        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, 2018);
        cal2.set(Calendar.DAY_OF_MONTH, 20);
        cal2.set(Calendar.MONTH, 5);
        cal2.set(Calendar.HOUR_OF_DAY, 20);
        cal2.set(Calendar.MINUTE, 30);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);

        Calendar cal3 = Calendar.getInstance();
        cal3.setTime(new Date());

        Date a = cal2.getTime();
        Date b = cal2.getTime();
        Date c = cal.getTime();
        Date d = cal.getTime();
        Date e = cal1.getTime();
        Date f = cal3.getTime();

        Order order = new Order(1001, "Pickup", con, a);
        Order order2 = new Order(1002, "Pickup", corp, b);
        Order order3 = new Order(1003, "Pickup", corp, c);
        Order order4 = new Order(1004, "Pickup", corp, d);
        Order order5 = new Order(1005, "Pickup", corp, e);
        Order order6 = new Order(1006, "Pickup", con, f);

        pickupOrder.add(order);
        pickupOrder.add(order2);
        pickupOrder.add(order3);
        pickupOrder.add(order4);
        pickupOrder.add(order5);
        pickupOrder.add(order6);

        Consumer con1 = new Consumer("ChenKang", "adgfafgjyaf", "0165554313", "No 13");
        CorporateCustomer corp1 = new CorporateCustomer("BahLI", "sdgsjhd@gmail", "058067843", "B-13-10,Pv 13 Condominium", "211221", "SUNWAY BERHAD", 5000);
        CorporateCustomer corp2 = new CorporateCustomer("BahLI", "sdgsjhd@gmail", "058017323", "Penang", "211221", "AEON BERHAD", 5000);
        CorporateCustomer corp3 = new CorporateCustomer("BahLI", "sdgsjhd@gmail", "058017323", "Cheras", "211221", "JUSCO BERHAD", 5000);
        CorporateCustomer corp4 = new CorporateCustomer("BahLI", "sdgsjhd@gmail", "058017323", "Johor", "211221", "MOMO BERHAD", 5000);
        Calendar cal4 = Calendar.getInstance();
        cal4.set(Calendar.YEAR, 2018);
        cal4.set(Calendar.DAY_OF_MONTH, 20);
        cal4.set(Calendar.MONTH, 4);
        cal4.set(Calendar.HOUR_OF_DAY, 17);
        cal4.set(Calendar.MINUTE, 30);
        cal4.set(Calendar.SECOND, 0);
        cal4.set(Calendar.MILLISECOND, 0);

        Calendar cal5 = Calendar.getInstance();
        cal5.set(Calendar.YEAR, 2018);
        cal5.set(Calendar.DAY_OF_MONTH, 20);
        cal5.set(Calendar.MONTH, 5);
        cal5.set(Calendar.HOUR_OF_DAY, 18);
        cal5.set(Calendar.MINUTE, 30);
        cal5.set(Calendar.SECOND, 0);
        cal5.set(Calendar.MILLISECOND, 0);

        Calendar cal6 = Calendar.getInstance();
        cal6.set(Calendar.YEAR, 2018);
        cal6.set(Calendar.DAY_OF_MONTH, 20);
        cal6.set(Calendar.MONTH, 6);
        cal6.set(Calendar.HOUR_OF_DAY, 20);
        cal6.set(Calendar.MINUTE, 30);
        cal6.set(Calendar.SECOND, 0);
        cal6.set(Calendar.MILLISECOND, 0);

        Calendar cal7 = Calendar.getInstance();
        cal7.setTime(new Date());

        Date g = cal4.getTime();
        Date h = cal4.getTime();
        Date i = cal6.getTime();
        Date j = cal7.getTime();
        Date k = cal7.getTime();
        Date l = cal5.getTime();

        Order order7 = new Order(1007, "Delivery", con1, g);
        Order order8 = new Order(1008, "Delivery", corp1, h);
        Order order9 = new Order(1009, "Delivery", corp2, i);
        Order order10 = new Order(1010, "Delivery", corp3, j);
        Order order11 = new Order(1011, "Delivery", corp4, k);
        Order order12 = new Order(1012, "Delivery", con1, l);

        deliveryOrder.add(order7);
        deliveryOrder.add(order8);
        deliveryOrder.add(order9);
        deliveryOrder.add(order10);
        deliveryOrder.add(order11);
        deliveryOrder.add(order12);

    }
}
