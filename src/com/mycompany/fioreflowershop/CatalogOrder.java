/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.adt.LinkedList;
import com.mycompany.fioreflowershop.adt.LinkedQueue;
import com.mycompany.fioreflowershop.adt.ListInterface;
import com.mycompany.fioreflowershop.modal.CatalogPackage;
import com.mycompany.fioreflowershop.modal.Consumer;
import com.mycompany.fioreflowershop.modal.CorporateCustomer;
import com.mycompany.fioreflowershop.modal.Order;
import com.mycompany.fioreflowershop.modal.CatalogOrders;
import com.mycompany.fioreflowershop.modal.CustomizedPackage;
import com.mycompany.fioreflowershop.modal.User;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Nicholas
 */
public class CatalogOrder {

    // Lines 419,437,555,571,658,674
    static LinkedList<CatalogOrders> shoppingCart = FioreFlowershop.getShoppingCart();
    static LinkedList<CatalogOrders> catalogOrder = FioreFlowershop.getCatalogOrder();

    static LinkedList<Order> conOrder = new LinkedList<>();
    static LinkedList<Order> corpOrder = new LinkedList<>();

    //Define item array of CatalgPackage class
    private static LinkedList<CatalogPackage> freshFlower = new LinkedList<>();
    private static LinkedList<CatalogPackage> bouquets = new LinkedList<>();
    private static LinkedList<CatalogPackage> flowerArrangement = new LinkedList<>();

    //Define shopping cart queue of CatalogOrder
    private static Consumer customer;
    private static CorporateCustomer corporate;

    //create a scanner object to get user input
    private static Scanner scan = new Scanner(System.in);
    private static int userMenuOption;
    private static int itemSelection;
    private static double itemPrice; //amount of price
    private static double creditSpent;
    private static int quantity;
    private static boolean isInteger;
    private static int order = 0;
    private static String orderID;
    private static String orderType;
    private static String orderStatus = "";
    private static boolean paymentStatus = false;
    private static Date todayDate = new Date();
    private static Date currentDate;
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private static String pickupDate, pickupTime, deliveryDate = "";
    private static int freshFlowerCounter = 0, bouquetsCounter = 0, flowerArrangementCounter = 0;

    public static void CustomerOrderMain(LinkedList<CatalogOrders> cart, LinkedList<CatalogOrders> catalogOrder, Consumer customerLoggedIn, LinkedList<CatalogPackage> normalPackage, LinkedList<CatalogPackage> discountedPackage) {
//        testing();
        //ListInterface<CatalogOrder1> sCart = cart;
        customer = customerLoggedIn;
        generateOrderID();

        //initialize items into catalog
        CatalogPackage catalogPackage = new CatalogPackage();
        freshFlower.clear();
        bouquets.clear();
        flowerArrangement.clear();
        //adding of normal catalog package items into the catalog
        for (int i = 1; i < normalPackage.getTotalEntries() + 1; i++) {
            catalogPackage = normalPackage.getItem(i);
            if (catalogPackage.getProductType().equals("Fresh flower")) {
                freshFlower.add(new CatalogPackage(catalogPackage.getName(), catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getProductType(), catalogPackage.getPromoMonth(), catalogPackage.getPromoYear(), catalogPackage.getQuantity(), catalogPackage.getPrice(), catalogPackage.getDiscountRate()));
                freshFlowerCounter++;
            } else if (catalogPackage.getProductType().equals("Bouquets")) {
                bouquets.add(new CatalogPackage(catalogPackage.getName(), catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getProductType(), catalogPackage.getPromoMonth(), catalogPackage.getPromoYear(), catalogPackage.getQuantity(), catalogPackage.getPrice(), catalogPackage.getDiscountRate()));
                bouquetsCounter++;
            } else if (catalogPackage.getProductType().equals("Flower arrangement")) {
                flowerArrangement.add(new CatalogPackage(catalogPackage.getName(), catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getProductType(), catalogPackage.getPromoMonth(), catalogPackage.getPromoYear(), catalogPackage.getQuantity(), catalogPackage.getPrice(), catalogPackage.getDiscountRate()));
                flowerArrangementCounter++;
            }
        }

        //adding of discounted catalog package into the catalog
        for (int i = 1; i < discountedPackage.getTotalEntries() + 1; i++) {
            catalogPackage = discountedPackage.getItem(i);
            if (catalogPackage.getProductType().equals("Fresh flower")) {
                freshFlower.add(new CatalogPackage(catalogPackage.getName(), catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getProductType(), catalogPackage.getPromoMonth(), catalogPackage.getPromoYear(), catalogPackage.getQuantity(), catalogPackage.getPrice(), catalogPackage.getDiscountRate()));
                freshFlowerCounter++;
            } else if (catalogPackage.getProductType().equals("Bouquets")) {
                bouquets.add(new CatalogPackage(catalogPackage.getName(), catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getProductType(), catalogPackage.getPromoMonth(), catalogPackage.getPromoYear(), catalogPackage.getQuantity(), catalogPackage.getPrice(), catalogPackage.getDiscountRate()));
                bouquetsCounter++;
            } else if (catalogPackage.getProductType().equals("Flower arrangement")) {
                flowerArrangement.add(new CatalogPackage(catalogPackage.getName(), catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getProductType(), catalogPackage.getPromoMonth(), catalogPackage.getPromoYear(), catalogPackage.getQuantity(), catalogPackage.getPrice(), catalogPackage.getDiscountRate()));
                flowerArrangementCounter++;
            }
        }

        displayCatalog(normalPackage, discountedPackage);

    }

    public static void CorporateOrderMain(LinkedList<CatalogOrders> cart, LinkedList<CatalogOrders> catalogOrder, CorporateCustomer customerLoggedIn, LinkedList<CatalogPackage> normalPackage, LinkedList<CatalogPackage> discountedPackage) {
        //ListInterface<CatalogOrder1> sCart = cart;
        corporate = customerLoggedIn;
        generateOrderID();

        //initialize items into catalog
        CatalogPackage catalogPackage = new CatalogPackage();
        freshFlower.clear();
        bouquets.clear();
        flowerArrangement.clear();
        //adding of normal catalog package items into the catalog
        for (int i = 1; i < normalPackage.getTotalEntries() + 1; i++) {
            catalogPackage = normalPackage.getItem(i);
            if (catalogPackage.getProductType().equals("Fresh flower")) {
                freshFlower.add(new CatalogPackage(catalogPackage.getName(), catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getProductType(), catalogPackage.getPromoMonth(), catalogPackage.getPromoYear(), catalogPackage.getQuantity(), catalogPackage.getPrice(), catalogPackage.getDiscountRate()));
                freshFlowerCounter++;
            } else if (catalogPackage.getProductType().equals("Bouquets")) {
                bouquets.add(new CatalogPackage(catalogPackage.getName(), catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getProductType(), catalogPackage.getPromoMonth(), catalogPackage.getPromoYear(), catalogPackage.getQuantity(), catalogPackage.getPrice(), catalogPackage.getDiscountRate()));
                bouquetsCounter++;
            } else if (catalogPackage.getProductType().equals("Flower arrangement")) {
                flowerArrangement.add(new CatalogPackage(catalogPackage.getName(), catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getProductType(), catalogPackage.getPromoMonth(), catalogPackage.getPromoYear(), catalogPackage.getQuantity(), catalogPackage.getPrice(), catalogPackage.getDiscountRate()));
                flowerArrangementCounter++;
            }
        }

        //adding of discounted catalog package into the catalog
        for (int i = 1; i < discountedPackage.getTotalEntries() + 1; i++) {
            catalogPackage = discountedPackage.getItem(i);
            if (catalogPackage.getProductType().equals("Fresh flower")) {
                freshFlower.add(new CatalogPackage(catalogPackage.getName(), catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getProductType(), catalogPackage.getPromoMonth(), catalogPackage.getPromoYear(), catalogPackage.getQuantity(), catalogPackage.getPrice(), catalogPackage.getDiscountRate()));
                freshFlowerCounter++;
            } else if (catalogPackage.getProductType().equals("Bouquets")) {
                bouquets.add(new CatalogPackage(catalogPackage.getName(), catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getProductType(), catalogPackage.getPromoMonth(), catalogPackage.getPromoYear(), catalogPackage.getQuantity(), catalogPackage.getPrice(), catalogPackage.getDiscountRate()));
                bouquetsCounter++;
            } else if (catalogPackage.getProductType().equals("Flower arrangement")) {
                flowerArrangement.add(new CatalogPackage(catalogPackage.getName(), catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getProductType(), catalogPackage.getPromoMonth(), catalogPackage.getPromoYear(), catalogPackage.getQuantity(), catalogPackage.getPrice(), catalogPackage.getDiscountRate()));
                flowerArrangementCounter++;
            }
        }

        displayCatalog(normalPackage, discountedPackage);
    }

    public static void generateOrderID() {
        String id100 = "100";
        String id10 = "10";
        String id1 = "1";
        if (order == 0) {
            order++;
            id100 += String.valueOf(order);
            orderID = id100;
        } else if (order == 9) {
            order++;
            id10 += String.valueOf(order);
            orderID = id10;
        } else if (String.valueOf(order).length() == 2) {
            order++;
            id10 += String.valueOf(order);
            orderID = id10;
        } else if (order == 99) {
            order++;
            id1 += String.valueOf(order);
            orderID = id1;
        } else if (String.valueOf(order).length() == 3) {
            order++;
            id1 += String.valueOf(order);
            orderID = id1;
        }

    }

    //Display the catalog or monthly promotion catalog
    public static void displayCatalog(LinkedList<CatalogPackage> normalPackage, LinkedList<CatalogPackage> discountedPackage) {
        do {
            System.out.println(FioreFlowershop.ConsoleColors.BLACK + "\n Catalog Order");
            System.out.println("===================");
            System.out.println("1. Catalog");
            System.out.println("2. My Shopping Cart");
//            System.out.println("2. Monthly promotion catalog");
            System.out.println("3. Back");
            System.out.print("Please enter a number (1-2): ");
            if (scan.hasNextInt()) {
                userMenuOption = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.err.println(FioreFlowershop.ConsoleColors.BLACK + "Please enter shown number only.");
                scan.next();
            }
        } while (!(isInteger) || userMenuOption < 1 || userMenuOption > 3);
        if (userMenuOption == 1) {
            typeSelection(normalPackage, discountedPackage);
        } else if (userMenuOption == 2) {
            if (!shoppingCart.isEmpty()) {
                System.out.println("\nDisplay Shopping Cart");
                System.out.println("====================================================================================================");
                System.out.println("Product Name  \t\t\t\tUnit Price\t\tQuantity\t\tTotal Price");
                double payAmount2 = 0;
                if (!shoppingCart.isEmpty()) {
                    for (int i = 1; i < shoppingCart.getTotalEntries() + 1; i++) {
                        payAmount2 += shoppingCart.getItem(i).getOrderAmt();
                        System.out.printf("%d. %s\n", i, shoppingCart.getItem(i).getCatalogPack().getName());
                        double discountedPrice = (double) ((100 - shoppingCart.getItem(i).getDiscountRate()) * shoppingCart.getItem(i).getCatalogPack().getPrice() / 100);
                        System.out.printf("%s,%s,%s,%s \tRM%7.2f\t\t   %d\t\t\t RM%7.2f\n\n", shoppingCart.getItem(i).getCatalogPack().getStyle(), shoppingCart.getItem(i).getCatalogPack().getSize(), shoppingCart.getItem(i).getCatalogPack().getFlower(), shoppingCart.getItem(i).getCatalogPack().getAccessory(), discountedPrice, shoppingCart.getItem(i).getItemQuantity(), shoppingCart.getItem(i).getOrderAmt());
                    }
                }
                System.out.printf(FioreFlowershop.ConsoleColors.RED + "Amount: \t\t\t\t\t\t\t\t\t         RM%7.2f\n", payAmount2);
                System.out.println(FioreFlowershop.ConsoleColors.BLACK + "====================================================================================================");

                System.out.print("Do you want to proceed to select your item retrieval method? (Y/y = yes AND No = any key, go back to menu)");
                String con = scan.next();

                if (con.equalsIgnoreCase("Y")) {
                    int retrieveItem = 0;
                    do {
                        System.out.println("==========================================================================");
                        System.out.println("How do you want to retrieve your ordered items?\n1.Delivery\n2.Self Pickup");
                        if (scan.hasNextInt()) {
                            retrieveItem = scan.nextInt();
                            isInteger = true;
                        } else {
                            isInteger = false;
                            System.err.println("Please enter shown number only.");
                            scan.next();
                        }
                    } while (!(isInteger) || retrieveItem < 1 || retrieveItem > 2);

                    if (retrieveItem == 1) { //Delivery
                        orderType = "Delivery";
                        int delivery = 0;
                        do { //User retrieve item method either delivery or self pickup
                            System.out.println("Delivery Options");
                            System.out.println("===========================");
                            System.out.println("1.Express Delivery (2 days)");
                            System.out.println("2.Standard Delivery (4 days)");
                            if (scan.hasNextInt()) {
                                delivery = scan.nextInt();
                                isInteger = true;
                            } else {
                                isInteger = false;
                                System.err.println("Please enter shown number only.");
                                scan.next();
                            }
                        } while (!(isInteger) || delivery < 1 || delivery > 2);
                        Calendar c = Calendar.getInstance();
                        c.setTime(new Date()); // Set to today date.
                        //set the date of delivery
                        if (delivery == 1) { //Express Delivery                                               
                            c.add(Calendar.DATE, 2); // Adding 2 days
                            deliveryDate = dateFormat.format(c.getTime());

                            //set the orderType to Delivery and set the deliveryDate
                            for (int x = 1; x < shoppingCart.getTotalEntries() + 1; x++) {
                                shoppingCart.getItem(x).setOrderType(orderType);
                                try {
                                    shoppingCart.getItem(x).setRetrieveDate(dateFormat.parse(deliveryDate));
                                } catch (Exception ex) {
                                    System.out.print("\n" + FioreFlowershop.ConsoleColors.RED + "Please supply a valid delivery date." + FioreFlowershop.ConsoleColors.BLACK + "\n");
                                }
                            }

                            System.out.println("");
                            System.out.println("=====================================================");
                            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "Your items will be delivered to you by " + deliveryDate);
                            System.out.println("=====================================================");

                            //Generate sales order for different customer
                            if (customer != null && corporate == null) {
                                salesOrder(shoppingCart.getItem(1), customer);
                            } else if (customer == null && corporate != null) {
                                salesOrder(shoppingCart.getItem(1), corporate);
                            }

                        } else if (delivery == 2) { //Normal Delivery                       
                            c.add(Calendar.DATE, 4); // Adding 4 days
                            deliveryDate = dateFormat.format(c.getTime());

                            //set the orderType to Delivery and set the deliveryDate
                            for (int x = 1; x < shoppingCart.getTotalEntries() + 1; x++) {
                                shoppingCart.getItem(x).setOrderType(orderType);
                                try {
                                    shoppingCart.getItem(x).setRetrieveDate(dateFormat.parse(deliveryDate));
                                } catch (Exception ex) {
                                    System.out.print("\n" + FioreFlowershop.ConsoleColors.RED + "Please supply a valid delivery date." + FioreFlowershop.ConsoleColors.BLACK + "\n");
                                }
                            }

                            System.out.println("");
                            System.out.println("=====================================================");
                            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "Your items will be delviered to you by " + deliveryDate);
                            System.out.println("=====================================================");

                            //Generate sales order for different customer
                            if (customer != null && corporate == null) {
                                salesOrder(shoppingCart.getItem(1), customer);
                            } else if (customer == null && corporate != null) {
                                salesOrder(shoppingCart.getItem(1), corporate);
                            }

                        }
                    } else if (retrieveItem == 2) { //Self Pickup
                        orderType = "Pickup";
                        String pickDate;
                        boolean checkDate = false;
                        do {
                            System.out.println(FioreFlowershop.ConsoleColors.BLACK + "When do you want to pickup your items? (yyyy-MM-dd)");
                            pickDate = scan.next();
                            if (!pickDate.isEmpty()) {
                                try {
                                    Date pickDate2 = dateFormat.parse(pickDate);
                                    if (pickDate2.after(todayDate)) {
                                        pickupDate = dateFormat.format(dateFormat.parse(pickDate));
                                        //set the orderType to Delivery and set the deliveryDate
                                        for (int x = 1; x < shoppingCart.getTotalEntries() + 1; x++) {
                                            shoppingCart.getItem(x).setOrderType(orderType);
                                            try {
                                                shoppingCart.getItem(x).setRetrieveDate(dateFormat.parse(pickupDate));
                                            } catch (Exception ex) {
                                                System.out.print("\n" + FioreFlowershop.ConsoleColors.RED + "Please supply a valid pickup date." + FioreFlowershop.ConsoleColors.BLACK + "\n");
                                            }
                                        }
                                        System.out.println("");
                                        System.out.println("=====================================================");
                                        System.out.println(FioreFlowershop.ConsoleColors.GREEN + "You can pickup your items by " + pickupDate);
                                        System.out.println("=====================================================");
//                                System.out.print(FioreFlowershop.ConsoleColors.RED + pickupDate); checking on user date input
                                        checkDate = true;

                                    } else if (todayDate.equals(dateFormat.parse(pickDate))) {
                                        System.err.println(FioreFlowershop.ConsoleColors.RED + "Sorry, you cannot pickup your items by today.");
                                        checkDate = false;
                                    } else if (todayDate.after(dateFormat.parse(pickDate))) {
                                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter a valid date after today's date.");
                                        checkDate = false;
                                    }

                                } catch (Exception e) {
                                    System.out.print("\n" + FioreFlowershop.ConsoleColors.RED + "Please supply a valid pickup date." + FioreFlowershop.ConsoleColors.BLACK + "\n");
                                }
                            }
                        } while (!checkDate);
                    }
                    for (int i = 1; i < shoppingCart.getTotalEntries() + 1; i++) {
                        catalogOrder.add(new CatalogOrders(shoppingCart.getItem(i).getOrderID(), shoppingCart.getItem(i).getCatalogPack(), shoppingCart.getItem(i).getItemQuantity(), shoppingCart.getItem(i).getDiscountRate(), shoppingCart.getItem(i).getOrderType(), shoppingCart.getItem(i).getOrderDate(), shoppingCart.getItem(i).getUser(), shoppingCart.getItem(i).getOrderStatus(), shoppingCart.getItem(i).getOrderAmt(), paymentStatus, shoppingCart.getItem(i).getRetrieveDate(), shoppingCart.getItem(i).getRetrieveTime()));
                    }
                    System.out.print(catalogOrder.getTotalEntries());
                    System.out.print("Do you wish to checkout? (Y/y = yes OR N/n = no)");
                    String checkout = scan.next();

                    if (checkout.equalsIgnoreCase("Y")) {
                        shoppingCart.clear();

                        //Generate sales order for different customer
                        if (customer != null && corporate == null) {
                            salesOrder(catalogOrder.getItem(1), customer);
                        } else if (customer == null && corporate != null) {
                            salesOrder(catalogOrder.getItem(1), corporate);
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
            } else if (shoppingCart.isEmpty()) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Your shopping cart is empty. You have not add in any item yet.");
                displayCatalog(normalPackage, discountedPackage);
            }

        } else if (userMenuOption == 3) {
            FioreFlowershop.userTypeSelection();
        }
    }

    public static void salesOrder(CatalogOrders shopping, User user) {
        double totalPrice = 0;
        System.out.println("\n=================================================================================================");
        System.out.println("\nFiore Flowershop SDN.BHD \t\t\t\t\t\t" + FioreFlowershop.ConsoleColors.BLACK_BOLD + " SALES ORDER");
        System.out.println("\nQ-5-1, Desa Permai Indah");
        System.out.println("Bandar Gelugor, 11700 Pulau Pinang \t\t\t\t\t" + "Sales Order #[" + orderID + "]");
        System.out.println("Phone : 0125566922 \t\t\t\t\t\t\t" + "DATE: " + dateFormat.format(currentDate));

        System.out.println("\nTO:");
        System.out.println("[" + shopping.getUser().getEmail() + "]");
        if (customer == null && corporate != null) {
            System.out.println("[" + corporate.getCompany() + "]");
        }
        System.out.println("[" + shopping.getUser().getAddress() + "]");
        System.out.println("[City, ST ZIP Code]");
        System.out.println("[" + shopping.getUser().getPhone() + "]");
        System.out.println("=================================================================================================");
        System.out.println("Description \t\t\t  | Quantity  |  Discount Rate(%) | Unit Price(RM) |  Total(RM)");

//        for (int i = 0; i < catalogOrder.getTotalEntries() + 1; i++) {
//                System.out.print(catalogOrder.getItem(i).getCatalogPack().getName() + "\t\t\t  | \t" + catalogOrder.getItem(i).getItemQuantity() + "\t|\t"
//                        + catalogOrder.getItem(i).getCatalogPack().getDiscountRate() + "\t    |\t" + catalogOrder.getItem(i).getCatalogPack().getPrice() + " \t     |   "
//                        + catalogOrder.getItem(i).getCatalogPack().getPrice() * catalogOrder.getItem(i).getItemQuantity());
//                totalPrice += catalogOrder.getItem(i).getOrderAmt();
//        }
        System.out.println("\n\n\t\t\t\t\t\t\t Subtotal :\t\t\t " + totalPrice);
        System.out.println("\n\n\t\t\t\t\t\t\tOrder Type :\t\t\t " + shopping.getOrderType());
        System.out.println("\n\n\t\t\t\t\t\t Delivery/Pickup Date :\t\t\t " + dateFormat.format(shopping.getRetrieveDate()));
    }

    public static void typeSelection(LinkedList<CatalogPackage> normalPackage, LinkedList<CatalogPackage> discountedPackage) {
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
            displayCatalog(normalPackage, discountedPackage);
        } else if (userMenuOption == 1) {
            freshFlowerCatalog(normalPackage, discountedPackage);
        } else if (userMenuOption == 2) {
            bouquetsCatalog(normalPackage, discountedPackage);
        } else if (userMenuOption == 3) {
            flowerArrangementCatalog(normalPackage, discountedPackage);
        }

    }

    //Display the normal catalog
    public static void freshFlowerCatalog(LinkedList<CatalogPackage> normalPackage, LinkedList<CatalogPackage> discountedPackage) {

        System.out.println("\nDisplay catalog - normal catalog");
        System.out.println("========================================================================================================");
        System.out.println("Product types\t\t\t\t\tQuantity\tPrice\t\t\tDiscounted price");
        System.out.println("Fresh Flower");
        System.out.println("==================");

        if (freshFlowerCounter != 0 && freshFlower.getTotalEntries() != 0) {
            for (int i = 1; i < freshFlower.getTotalEntries() + 1; i++) {
                System.out.printf("%d. %s\n", i, freshFlower.getItem(i).getName());
                if (freshFlower.getItem(i).getDiscountRate() == 0) {
                    System.out.printf("%s,%s,%s,%s \t           %d\t\tRM%7.2f\t\t        - \n\n", freshFlower.getItem(i).getStyle(), freshFlower.getItem(i).getSize(), freshFlower.getItem(i).getFlower(), freshFlower.getItem(i).getAccessory(), freshFlower.getItem(i).getQuantity(), freshFlower.getItem(i).getPrice());
                } else if (freshFlower.getItem(i).getDiscountRate() != 0) {
                    double discountedPrice = (double) ((100 - freshFlower.getItem(i).getDiscountRate()) * freshFlower.getItem(i).getPrice() / 100);
                    System.out.printf("%s,%s,%s,%s \t           %d\t\tRM%7.2f\t\t\t\tRM%7.2f \n\n", freshFlower.getItem(i).getStyle(), freshFlower.getItem(i).getSize(), freshFlower.getItem(i).getFlower(), freshFlower.getItem(i).getAccessory(), freshFlower.getItem(i).getQuantity(), freshFlower.getItem(i).getPrice(), discountedPrice);
                }
            }
        } else if (freshFlower.getTotalEntries() == 0) {
            System.err.println("\nSorry, the item type you have selected is not available yet.");
            typeSelection(normalPackage, discountedPackage);
        }

        do {
            System.out.print("Please enter your choice in number:");
            if (scan.hasNextInt()) {
                itemSelection = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.err.println("Please enter your choice in number only.");
                scan.next();
            }
        } while (itemSelection == 0 || itemSelection > freshFlower.getTotalEntries() || !(isInteger));

        System.out.println("\nDisplay catalog - Quantity Selection");
        System.out.println("========================================================================================================");
        System.out.println("Product types\t\t\t\tQuantity\tPrice\t\t\tDiscounted price");
        System.out.printf("%s\n", freshFlower.getItem(itemSelection).getName());
        if (freshFlower.getItem(itemSelection).getDiscountRate() == 0) {
            System.out.printf("%s,%s,%s,%s  \t   %d  \t\tRM%7.2f\t\t        -\n\n", freshFlower.getItem(itemSelection).getStyle(), freshFlower.getItem(itemSelection).getSize(), freshFlower.getItem(itemSelection).getFlower(), freshFlower.getItem(itemSelection).getAccessory(), freshFlower.getItem(itemSelection).getQuantity(), freshFlower.getItem(itemSelection).getPrice());
        } else if (freshFlower.getItem(itemSelection).getDiscountRate() != 0) {
            double discountedPrice = (double) ((100 - freshFlower.getItem(itemSelection).getDiscountRate()) * freshFlower.getItem(itemSelection).getPrice() / 100);
            System.out.printf("%s,%s,%s,%s  \t   %d  \t\tRM%7.2f\t\t    RM%7.2f\n\n", freshFlower.getItem(itemSelection).getStyle(), freshFlower.getItem(itemSelection).getSize(), freshFlower.getItem(itemSelection).getFlower(), freshFlower.getItem(itemSelection).getAccessory(), freshFlower.getItem(itemSelection).getQuantity(), freshFlower.getItem(itemSelection).getPrice(), discountedPrice);
        }

        do {
            System.out.print("Please enter quantity of this item you want to order:");

            if (scan.hasNextInt()) {
                quantity = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.err.println("Please enter the quantity in number only.");
                scan.next();
            }
        } while (!(isInteger));

        //Calculate total price of the selected item
        if (freshFlower.getItem(itemSelection).getDiscountRate() == 0) {
            itemPrice = freshFlower.getItem(itemSelection).getPrice() * quantity;
        } else if (freshFlower.getItem(itemSelection).getDiscountRate() != 0) {
            itemPrice = (double) ((100 - freshFlower.getItem(itemSelection).getDiscountRate()) * freshFlower.getItem(itemSelection).getPrice() / 100) * quantity;
        }

        //calculate credit spent by corporate customer
        if (shoppingCart.isEmpty()) {
            creditSpent += itemPrice;
        } else if (!shoppingCart.isEmpty()) {
            creditSpent = 0;
            for (int i = 1; i < shoppingCart.getTotalEntries() + 1; i++) {
                creditSpent += shoppingCart.getItem(i).getOrderAmt();
            }
            creditSpent += itemPrice;
        }

        corporate.setCreditSpent(creditSpent); //set credit spent for checking       
        //Add in the selected item inside the shoppingCart arraylist
        if (customer != null && corporate == null) {
            try {
                currentDate = dateFormat.parse(dateFormat.format(todayDate));
                //replace deliveryDate with currentDate since parsing of String deliveryDate will result in throwing the exception cannot parse "" and skip adding in shoppingCart 
                shoppingCart.add(new CatalogOrders(orderID, freshFlower.getItem(itemSelection), quantity, freshFlower.getItem(itemSelection).getDiscountRate(), orderType, currentDate, customer, orderStatus, itemPrice, paymentStatus, currentDate, currentDate));
            } catch (ParseException ex) {

            }
        } else if (customer == null && corporate != null) {

            //CK MADE SOME CHANGES HERE
            if ((corporate.getMonthlyLimit() - corporate.getCreditSpent()) < 0) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Sorry, it seems that you will exceed the credit limit after this purchase." + FioreFlowershop.ConsoleColors.RESET);
                if (shoppingCart.isEmpty()) {
                    creditSpent = 0;
                } else if (!shoppingCart.isEmpty()) {
                    creditSpent -= itemPrice;
                }
            } else {
                try {
                    currentDate = dateFormat.parse(dateFormat.format(todayDate));
                    System.out.print(orderID);
                    shoppingCart.add(new CatalogOrders(orderID, freshFlower.getItem(itemSelection), quantity, freshFlower.getItem(itemSelection).getDiscountRate(), orderType, currentDate, corporate, orderStatus, itemPrice, paymentStatus, currentDate, currentDate));
                } catch (ParseException ex) {
                }
            }
            if (!shoppingCart.isEmpty()) {
                System.out.println("\nDisplay Shopping Cart");
                System.out.println("================================================================================================");
                System.out.println("Product types\t\t\t\tPrice\t\tQuantity");

                for (int i = 1; i < shoppingCart.getTotalEntries() + 1; i++) {
                    System.out.printf("%s\n", shoppingCart.getItem(i).getCatalogPack().getName());
                    System.out.printf("%s,%s,%s,%s \tRM%7.2f\t   %d\n\n", shoppingCart.getItem(i).getCatalogPack().getStyle(), shoppingCart.getItem(i).getCatalogPack().getSize(), shoppingCart.getItem(i).getCatalogPack().getFlower(), shoppingCart.getItem(i).getCatalogPack().getAccessory(), shoppingCart.getItem(i).getOrderAmt(), shoppingCart.getItem(i).getItemQuantity());
                }
            }
            System.out.print("Do you wish to browse through fresh flower? (Y/y = Yes, other keys = No)");
            String con = scan.next();

            if (con.equalsIgnoreCase("Y")) {
                freshFlowerCatalog(normalPackage, discountedPackage);
            } else {
                typeSelection(normalPackage, discountedPackage);
            }
        }
    }

    public static void bouquetsCatalog(LinkedList<CatalogPackage> normalPackage, LinkedList<CatalogPackage> discountedPackage) {
        System.out.println("\nDisplay catalog - normal catalog");
        System.out.println("========================================================================================================");
        System.out.println("Product types\t\t\t\t\tQuantity\tPrice\t\t\tDiscounted price");

        if (bouquetsCounter != 0 && bouquets.getTotalEntries() != 0) {
            System.out.println("\nBouquets");
            System.out.println("==================");
            for (int i = 1; i < bouquets.getTotalEntries() + 1; i++) {
                System.out.printf("%d. %s\n", i, bouquets.getItem(i).getName());
                if (bouquets.getItem(i).getDiscountRate() == 0) {
                    System.out.printf("%s,%s,%s,%s \t           %d\t\tRM%7.2f\t\t        - \n\n", bouquets.getItem(i).getStyle(), bouquets.getItem(i).getSize(), bouquets.getItem(i).getFlower(), bouquets.getItem(i).getAccessory(), bouquets.getItem(i).getQuantity(), bouquets.getItem(i).getPrice());
                } else if (bouquets.getItem(i).getDiscountRate() != 0) {
                    double discountedPrice = (double) ((100 - bouquets.getItem(itemSelection).getDiscountRate()) * bouquets.getItem(itemSelection).getPrice() / 100);
                    System.out.printf("%s,%s,%s,%s \t           %d\t\tRM%7.2f\t\t    RM%7.2f \n\n", bouquets.getItem(i).getStyle(), bouquets.getItem(i).getSize(), bouquets.getItem(i).getFlower(), bouquets.getItem(i).getAccessory(), bouquets.getItem(i).getQuantity(), bouquets.getItem(i).getPrice(), discountedPrice);
                }
            }

        } else if (bouquets.getTotalEntries() == 0) {
            System.err.println("\nSorry, the item type you have selected is not available yet.");
            typeSelection(normalPackage, discountedPackage);
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
//        catalogPackage = bouquets.getItem(itemSelection);
        System.out.println("\nDisplay catalog - Quantity Selection");
        System.out.println("========================================================================================================");
        System.out.println("Product types\t\t\t\tQuantity\tPrice\t\t\tDiscounted price");
        System.out.printf("%s\n", bouquets.getItem(itemSelection).getName());
        if (bouquets.getItem(itemSelection).getDiscountRate() == 0) {
            System.out.printf("%s,%s,%s,%s  \t   %d  \t\tRM%7.2f\t\t        -\n\n", bouquets.getItem(itemSelection).getStyle(), bouquets.getItem(itemSelection).getSize(), bouquets.getItem(itemSelection).getFlower(), bouquets.getItem(itemSelection).getAccessory(), bouquets.getItem(itemSelection).getQuantity(), bouquets.getItem(itemSelection).getPrice());
        } else if (bouquets.getItem(itemSelection).getDiscountRate() != 0) {
            double discountedPrice = (double) ((100 - bouquets.getItem(itemSelection).getDiscountRate()) * bouquets.getItem(itemSelection).getPrice() / 100);
            System.out.printf("%s,%s,%s,%s  \t   %d  \t\tRM%7.2f\t\t    RM%7.2f\n\n", bouquets.getItem(itemSelection).getStyle(), bouquets.getItem(itemSelection).getSize(), bouquets.getItem(itemSelection).getFlower(), bouquets.getItem(itemSelection).getAccessory(), bouquets.getItem(itemSelection).getQuantity(), bouquets.getItem(itemSelection).getPrice(), discountedPrice);

        }

        do {
            System.out.print("Please enter quantity of this item you want to order:");

            if (scan.hasNextInt()) {
                quantity = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the quantity in number only." + FioreFlowershop.ConsoleColors.BLACK);
                scan.next();
            }
        } while (!(isInteger));

        //Calculate total price of the selected item
        if (bouquets.getItem(itemSelection).getDiscountRate() == 0) {
            itemPrice = bouquets.getItem(itemSelection).getPrice() * quantity;
        } else if (bouquets.getItem(itemSelection).getDiscountRate() != 0) {
            itemPrice = (double) ((100 - bouquets.getItem(itemSelection).getDiscountRate()) * bouquets.getItem(itemSelection).getPrice() / 100) * quantity;
        }

        //calculate credit spent by corporate customer
        if (shoppingCart.isEmpty()) {
            creditSpent += itemPrice;
        } else if (!shoppingCart.isEmpty()) {
            creditSpent = 0;
            for (int i = 1; i < shoppingCart.getTotalEntries() + 1; i++) {
                creditSpent += shoppingCart.getItem(i).getOrderAmt();
            }
            creditSpent += itemPrice;
        }
        corporate.setCreditSpent(creditSpent); //set credit spent for checking

        //Add in the selected item inside the shoppingCart arraylist       
        if (customer != null && corporate == null) {
            try {
                currentDate = dateFormat.parse(dateFormat.format(todayDate));
                shoppingCart.add(new CatalogOrders(orderID, bouquets.getItem(itemSelection), quantity, bouquets.getItem(itemSelection).getDiscountRate(), orderType, currentDate, customer, orderStatus, itemPrice, paymentStatus, currentDate, currentDate));
            } catch (ParseException ex) {

            }
        } else if (customer == null && corporate != null) {
            //CK MADE SOME CHANGES HERE
            if ((corporate.getMonthlyLimit() - corporate.getCreditSpent()) < 0) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Sorry, it seems that you will exceed the credit limit after this purchase." + FioreFlowershop.ConsoleColors.RESET);
                if (shoppingCart.isEmpty()) {
                    creditSpent = 0;
                } else if (!shoppingCart.isEmpty()) {
                    creditSpent -= itemPrice;
                }
            } else {
                try {
                    currentDate = dateFormat.parse(dateFormat.format(todayDate));
                    shoppingCart.add(new CatalogOrders(orderID, bouquets.getItem(itemSelection), quantity, bouquets.getItem(itemSelection).getDiscountRate(), orderType, currentDate, corporate, orderStatus, itemPrice, paymentStatus, currentDate, currentDate));
                } catch (ParseException ex) {
                }
            }
        }
        if (!shoppingCart.isEmpty()) {
            System.out.println("\nDisplay Shopping Cart");
            System.out.println("====================================================================================================");
            System.out.println("Product types\t\t\t\tPrice\t\t\tQuantity");

            for (int i = 1; i < shoppingCart.getTotalEntries() + 1; i++) {
                System.out.printf("%s\n", shoppingCart.getItem(i).getCatalogPack().getName());
                System.out.printf("%s,%s,%s,%s \tRM%7.2f\t\t   %d\n\n", shoppingCart.getItem(i).getCatalogPack().getStyle(), shoppingCart.getItem(i).getCatalogPack().getSize(), shoppingCart.getItem(i).getCatalogPack().getFlower(), shoppingCart.getItem(i).getCatalogPack().getAccessory(), shoppingCart.getItem(i).getOrderAmt(), shoppingCart.getItem(i).getItemQuantity());
            }

        }
        String con;

        System.out.print("Do you wish to browse through bouquets? (Y/y = Yes, other keys = No)");
        con = scan.next();

        if (con.equalsIgnoreCase("Y")) {
            bouquetsCatalog(normalPackage, discountedPackage);
        } else {
            typeSelection(normalPackage, discountedPackage);
        }
    }

    public static void flowerArrangementCatalog(LinkedList<CatalogPackage> normalPackage, LinkedList<CatalogPackage> discountedPackage) {

        System.out.println("\nDisplay catalog - normal catalog");
        System.out.println("========================================================================================================");
        System.out.println("Product types\t\t\t\tQuantity\tPrice\t\t\tDiscounted price");

        System.out.println("\nFlower Arrangement");
        System.out.println("==================");
        for (int i = 1; i < flowerArrangement.getTotalEntries() + 1; i++) {
            System.out.printf("%d. %s\n", i, flowerArrangement.getItem(i).getName());
            if (flowerArrangement.getItem(i).getDiscountRate() == 0) {
                System.out.printf("%s,%s,%s,%s \t   %d\t\tRM%7.2f\t\t        -\n\n", flowerArrangement.getItem(i).getStyle(), flowerArrangement.getItem(i).getSize(), flowerArrangement.getItem(i).getFlower(), flowerArrangement.getItem(i).getAccessory(), flowerArrangement.getItem(i).getQuantity(), flowerArrangement.getItem(i).getPrice());
            } else if (flowerArrangement.getItem(i).getDiscountRate() != 0) {
                double discountedPrice = (double) ((100 - flowerArrangement.getItem(itemSelection).getDiscountRate()) * flowerArrangement.getItem(itemSelection).getPrice() / 100);
                System.out.printf("%s,%s,%s,%s \t   %d\t\tRM%7.2f\t\t    RM%7.2f \n\n", flowerArrangement.getItem(i).getStyle(), flowerArrangement.getItem(i).getSize(), flowerArrangement.getItem(i).getFlower(), flowerArrangement.getItem(i).getAccessory(), flowerArrangement.getItem(i).getQuantity(), flowerArrangement.getItem(i).getPrice(), discountedPrice);
            }
        }
        if (flowerArrangementCounter != 0 && flowerArrangement.getTotalEntries() != 0) {
        } else if (flowerArrangement.getTotalEntries() == 0) {
            System.err.println("\nSorry, the item type you have selected is not available yet.");
            typeSelection(normalPackage, discountedPackage);
        }
        do {
            System.out.print("Please enter your choice in number:");

            if (scan.hasNextInt()) {
                itemSelection = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.err.println("Please enter your choice in number only.");
                scan.next();
            }

        } while (itemSelection == 0 || itemSelection > flowerArrangement.getTotalEntries() || !(isInteger));

        System.out.println("\nDisplay catalog - Quantity Selection");
        System.out.println("========================================================================================================");
        System.out.println("Product types\t\t\t\tPrice\t\t\tDiscounted price");

        System.out.printf("%s\n", flowerArrangement.getItem(itemSelection).getName());
        if (flowerArrangement.getItem(itemSelection).getDiscountRate() == 0) {
            System.out.printf("%s,%s,%s,%s  \t   %d  \t\tRM%7.2f\t\t        -\n\n", flowerArrangement.getItem(itemSelection).getStyle(), flowerArrangement.getItem(itemSelection).getSize(), flowerArrangement.getItem(itemSelection).getFlower(), flowerArrangement.getItem(itemSelection).getAccessory(), flowerArrangement.getItem(itemSelection).getPrice());
        } else if (flowerArrangement.getItem(itemSelection).getDiscountRate() != 0) {
            double discountedPrice = (double) ((100 - flowerArrangement.getItem(itemSelection).getDiscountRate()) * flowerArrangement.getItem(itemSelection).getPrice() / 100);
            System.out.printf("%s,%s,%s,%s  \t   %d  \t\tRM%7.2f\t\t    RM%7.2f\n\n", flowerArrangement.getItem(itemSelection).getStyle(), flowerArrangement.getItem(itemSelection).getSize(), flowerArrangement.getItem(itemSelection).getFlower(), flowerArrangement.getItem(itemSelection).getAccessory(), flowerArrangement.getItem(itemSelection).getPrice(), discountedPrice);
        }

        do {
            System.out.print("Please enter quantity of this item you want to order:");
//            quantity = scan.nextInt();
            if (scan.hasNextInt()) {
                quantity = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.err.println("Please enter the quantity in number only.");
                scan.next();
            }

        } while (!(isInteger));

        //Calculate total price of the selected item
        if (flowerArrangement.getItem(itemSelection).getDiscountRate() == 0) {
            itemPrice = flowerArrangement.getItem(itemSelection).getPrice() * quantity;
        } else if (flowerArrangement.getItem(itemSelection).getDiscountRate() != 0) {
            itemPrice = (double) ((100 - flowerArrangement.getItem(itemSelection).getDiscountRate()) * flowerArrangement.getItem(itemSelection).getPrice() / 100) * quantity;
        }

        //calculate credit spent by corporate customer
        if (shoppingCart.isEmpty()) {
            creditSpent += itemPrice;
        } else if (!shoppingCart.isEmpty()) {
            creditSpent = 0;
            for (int i = 1; i < shoppingCart.getTotalEntries() + 1; i++) {
                creditSpent += shoppingCart.getItem(i).getOrderAmt();
            }
            creditSpent += itemPrice;
        }
        corporate.setCreditSpent(creditSpent); //set credit spent for checking

        //Add in the selected item inside the shoppingCart arraylist
        if (customer != null && corporate == null) {
            try {
                currentDate = dateFormat.parse(dateFormat.format(todayDate));
                shoppingCart.add(new CatalogOrders(orderID, flowerArrangement.getItem(itemSelection), quantity, flowerArrangement.getItem(itemSelection).getDiscountRate(), orderType, currentDate, customer, orderStatus, itemPrice, paymentStatus, currentDate, currentDate));
            } catch (ParseException ex) {

            }
        } else if (customer == null && corporate != null) {
            //CK MADE SOME CHANGES HERE
            if ((corporate.getMonthlyLimit() - corporate.getCreditSpent()) < 0) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Sorry, it seems that you will exceed the credit limit after this purchase." + FioreFlowershop.ConsoleColors.RESET);
                if (shoppingCart.isEmpty()) {
                    creditSpent = 0;
                } else if (!shoppingCart.isEmpty()) {
                    creditSpent -= itemPrice;
                }
            } else {
                try {
                    currentDate = dateFormat.parse(dateFormat.format(todayDate));
                    shoppingCart.add(new CatalogOrders(orderID, flowerArrangement.getItem(itemSelection), quantity, flowerArrangement.getItem(itemSelection).getDiscountRate(), orderType, currentDate, corporate, orderStatus, itemPrice, paymentStatus, currentDate, currentDate));
                } catch (ParseException ex) {
                }
            }
        }
        if (!shoppingCart.isEmpty()) {
            System.out.println("\nDisplay Shopping Cart");
            System.out.println("====================================================================================================");
            System.out.println("Product types\t\t\t\tPrice\t\t\tQuantity");

            for (int i = 1; i < shoppingCart.getTotalEntries() + 1; i++) {
                System.out.printf("%s\n", shoppingCart.getItem(i).getCatalogPack().getName());
                System.out.printf("%s,%s,%s,%s \tRM%7.2f\t\t   %d\n\n", shoppingCart.getItem(i).getCatalogPack().getStyle(), shoppingCart.getItem(i).getCatalogPack().getSize(), shoppingCart.getItem(i).getCatalogPack().getFlower(), shoppingCart.getItem(i).getCatalogPack().getAccessory(), shoppingCart.getItem(i).getOrderAmt(), shoppingCart.getItem(i).getItemQuantity());
            }

        }

        System.out.print("Do you wish to browse through flower arrangement? (Y/y = Yes, other keys = No)");
        String con = scan.next();

        if (con.equalsIgnoreCase("Y")) {
            flowerArrangementCatalog(normalPackage, discountedPackage);
        } else {
            typeSelection(normalPackage, discountedPackage);
        }

    }

    public static void initializeData(LinkedList<CatalogOrders> catalogOrders, LinkedList<CustomizedPackage> customOrder) {

        Consumer con = new Consumer("Ncct96", "adgfafgjyaf", "ncct@gmail.com", "0128198471", "Ipoh");
        CorporateCustomer corp = new CorporateCustomer("Ah Hock", "sdgsjhd@gmail", "0165939123", "Penang", "211221", "TARUC", 5000, true);
        Consumer con1 = new Consumer("ChenKang", "adgfafgjyaf", "chenkang@hotmail.com", "0165554313", "No 13");
        Consumer con2 = new Consumer("Lim Sim", "ncct7777", "ncct7Z@gmail.com", "0185532123", "Cheras");
        CorporateCustomer corp1 = new CorporateCustomer("David", "sdgsjhd@gmail", "058067843", "Cheras", "211221", "SUNWAY BERHAD", 5000, true);
        CorporateCustomer corp2 = new CorporateCustomer("Louis Lim", "sdgsjhd@gmail", "058017323", "Pv 13 Condominium", "211221", "AEON BERHAD", 5000, true);
        CorporateCustomer corp3 = new CorporateCustomer("Nancy Goh", "sdgsjhd@gmail", "058017323", "Pahang", "211221", "JUSCO BERHAD", 5000, true);
        CorporateCustomer corp4 = new CorporateCustomer("Jacob", "sdgsjhd@gmail", "058017323", "Johor", "211221", "MOMO BERHAD", 5000, true);

        
        
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

        Calendar cal8 = Calendar.getInstance();
        cal8.set(Calendar.YEAR, 2018);
        cal8.set(Calendar.DAY_OF_MONTH, 10);
        cal8.set(Calendar.MONTH, 11);
        cal8.set(Calendar.HOUR_OF_DAY, 10);
        cal8.set(Calendar.MINUTE, 30);
        cal8.set(Calendar.SECOND, 0);
        cal8.set(Calendar.MILLISECOND, 0);

        Date a = cal2.getTime();
        Date b = cal2.getTime();
        Date c = cal.getTime();
        Date d = cal.getTime();
        Date e = cal1.getTime();
        Date f = cal3.getTime();
        Date g = cal7.getTime();
        Date h = cal4.getTime();
        Date i = cal6.getTime();
        Date j = cal7.getTime();
        Date k = cal7.getTime();
        Date l = cal5.getTime();
        Date m = cal8.getTime();

//        CatalogOrders order1 = new CatalogOrders("C0001", "Pickup", a, con, "Processed", 400.00, false, a);
//        CatalogOrders order2 = new CatalogOrders("C0001", "Pickup", b, corp, "Processed", 100.00, false, b);
//        CatalogOrders order3 = new CatalogOrders("C0002", "Pickup", c, corp, "Processed", 500.00, false, c);
//        CustomizedPackage order4 = new CustomizedPackage("CP0003", "Pickup", d, corp, "Processed", 250.00, false, m);
//        CustomizedPackage order5 = new CustomizedPackage("C0004", "Pickup", e, corp, "Processed", 1450.00, false, e);
//        CustomizedPackage order6 = new CustomizedPackage("C0002", "Pickup", f, con, "Processed", 200.00, false, m);
//
//
//        CatalogOrders order7 = new CatalogOrders("C0001", "Delivery", g, con1, "Processed", 20.00, false, f);
//        CustomizedPackage order8 = new CustomizedPackage("CP0001", "Delivery", h, corp1, "Processed", 99.00, false, f);
//        CatalogOrders order9 = new CatalogOrders("CP0002", "Delivery", j, corp2, "Processed", 100.00, false, f);
//        CustomizedPackage order10 = new CustomizedPackage("CP0003", "Delivery", j, corp3, "Processed", 600.00, false, f);
//        CatalogOrders order11 = new CatalogOrders("C0002", "Delivery", k, corp4, "Processed", 200.00, false, f);
//        CustomizedPackage order12 = new CustomizedPackage("C0003", "Delivery", l, con1, "Processed", 210.00, false, f);
//        CustomizedPackage order13 = new CustomizedPackage("C0004", "Delivery", j, con2, "Processed", 450.00, false, f);

        
        
    }
}
