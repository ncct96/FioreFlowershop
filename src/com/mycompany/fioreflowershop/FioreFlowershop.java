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

    // Lines 95 - 98, 154-158
    private static ListInterface<Consumer> consumer = new ArrayList<>();
    private static ListInterface<CorporateCustomer> corporate = new ArrayList<>();
    private static ListInterface<User> user = new ArrayList<>();
    private static LinkedList<Order> pickupOrder = new LinkedList<Order>();
    private static LinkedList<Order> deliveryOrder = new LinkedList<Order>();
    private static LinkedList<Order> paidOrder = new LinkedList<Order>();
    private static Scanner s = new Scanner(System.in);

    private static ItemCatalogue itemCatalogue = new ItemCatalogue();
    private static QueueInterface<CustomizedPackage> customizedPackages = new ArrayQueue<>();
    private static QueueInterface<CustomizedPackage> readyOrders = new ArrayQueue<>();
    
    //Catalog Maintenance part
    private static LinkedList<CatalogPackage> normalPackage = new LinkedList<>();
    private static LinkedList<CatalogPackage> discountedPackage = new LinkedList<>();

    private static LinkedList<CatalogOrders> shoppingCart = new LinkedList<>();
    private static String[] origin = {"Taiping", "Penang", "Cheras", "Johor"};
    private static String[] dest = {"Taiping", "Penang", "Cheras", "Johor"};
    private static final String shopAddress = "Taiping";

    private static int firstrun = 0;

    public static void main(String[] args) {

        ++firstrun;

        if (firstrun == 1) {
            CatalogOrder.initializeData(pickupOrder, deliveryOrder);
            initializePackages();
        }
        testing();
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
        Consumer c1 = new Consumer("ceekay", "abcdef123", "ceekay@example.com", "0125566922", "No Address Available");
        Consumer c2 = new Consumer("testing", "testing", "testing", "0125566922", "No Address Available");
        consumer.add(c1);
        consumer.add(c2);
        corporate.add(cc1);
        corporate.getItem(1).setCreditSpent(1000);

        //Initialize users
        user.add(c1);
        user.add(c2);
        user.add(cc1);

        //Initialize shopping cart
        CatalogPackage cp1 = new CatalogPackage("FlowerStrong", "Stylish", "Small", "Rose", "Ribbons", "Product Type", "12", 2018, 10, 50, 20);
        CatalogPackage cp2 = new CatalogPackage("FlowerWeak", "Colourful", "Medium", "Lavender", "Bow Tie", "Product Type", "11", 2018, 20, 30, 10);

        CatalogOrders ct1 = new CatalogOrders("1001", cp1, 4, 20, "Delivery", todayDate, cc1, "Order Status", 200, false, todayDate);
        CatalogOrders ct2 = new CatalogOrders("1002",cp2 , 5, 10, "Delivery", todayDate, cc1, "Order Status", 300, false, todayDate);

        shoppingCart.add(ct1);
        shoppingCart.add(ct2);

        ListIteratorInterface<Item> styles = new LinkedList<>();
        ListIteratorInterface<Item> sizes = new LinkedList<>();
        ListIteratorInterface<Item> flowers = new LinkedList<>();
        ListIteratorInterface<Item> accessories = new LinkedList<>();
        ListIteratorInterface<Item> priorities = new LinkedList<>();
        ListIteratorInterface<Item> deliveryTypes = new LinkedList<>();

        styles.add(new Item("Fan", 10));
        styles.add(new Item("Elliptical", 10));
        styles.add(new Item("Vertical", 10));
        styles.add(new Item("Horizontal", 10));
        styles.add(new Item("Triangular", 10));

        sizes.add(new Item("Small", 1));
        sizes.add(new Item("Medium", 2));
        sizes.add(new Item("Large", 4));

        flowers.add(new Item("Sunflowers", 250, 10));
        flowers.add(new Item("Red Roses", 300, 20));
        flowers.add(new Item("White Roses", 250, 10));
        flowers.add(new Item("Tulips", 450, 50));
        flowers.add(new Item("Daffodils", 200, 20));

        accessories.add(new Item("None", 0, 1));
        accessories.add(new Item("Name Card", 5, 20));
        accessories.add(new Item("Bears", 50, 5));
        accessories.add(new Item("Woven Basket", 35, 10));

        priorities.add(new Item("Flexi", 1, 6));
        priorities.add(new Item("Normal", 1.5, 4));
        priorities.add(new Item("Express", 3, 2));

        deliveryTypes.add(new Item("Pick up", 0));
        deliveryTypes.add(new Item("Delivery", 10));

        Consumer customer = new Consumer("Johan","abcdef", "ncct66@gmail.com", "0165919413", "Gelanggang Squash IAB Genting Highlands, Genting Highlands, 69000 Genting Highlands, Pahang");
        Consumer customer1 = new Consumer("Baba","abcdef", "ncct66@gmail.com", "0165919413", "7, Jalan Legoland, Medini, 79250 Nusajaya, Johor");
        customizedPackages.enqueue(new CustomizedPackage(styles.getItem(2), sizes.getItem(3), flowers.getItem(1), accessories.getItem(4), priorities.getItem(3), deliveryTypes.getItem(1), customer, false));
        customizedPackages.enqueue(new CustomizedPackage(styles.getItem(1), sizes.getItem(2), flowers.getItem(3), accessories.getItem(3), priorities.getItem(2), deliveryTypes.getItem(2), customer1, false));
        customizedPackages.enqueue(new CustomizedPackage(styles.getItem(3), sizes.getItem(1), flowers.getItem(2), accessories.getItem(1), priorities.getItem(2), deliveryTypes.getItem(2), customer, false));
        customizedPackages.enqueue(new CustomizedPackage(styles.getItem(4), sizes.getItem(2), flowers.getItem(4), accessories.getItem(1), priorities.getItem(1), deliveryTypes.getItem(1), customer1, false));
//        CustomizedPackage specialPackage = new CustomizedPackage(styles.getItem(1), sizes.getItem(2), flowers.getItem(5), accessories.getItem(2), priorities.getItem(1), deliveryTypes.getItem(2), customer);
//        specialPackage.setDeliveryDate(Calendar.getInstance().getTime());
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DAY_OF_MONTH, -2);
//        specialPackage.setOrderDate(cal.getTime());
        //customizedPackages.enqueue(specialPackage);

        itemCatalogue.setStyles(styles);
        itemCatalogue.setSizes(sizes);
        itemCatalogue.setFlowers(flowers);
        itemCatalogue.setAccessories(accessories);
        itemCatalogue.setPriorities(priorities);
        itemCatalogue.setDeliveryTypes(deliveryTypes);
    }

    public static void gotoCustomizePackage(Consumer customerLoggedIn, int selection) {
        /////// CHIUPEENG DEBUG LOOP //////
//        for (int i = 0; i < 3; i++) {
//            CustomizePackage.CustomizePackageControl(styles, sizes, flowers, accessories, priorities, deliveryTypes, customer, customizedPackages);
//        }
        if (selection == 1) {
            CustomizePackage.customizePackageControl(itemCatalogue, customerLoggedIn, customizedPackages);
        }
        else if(selection == 2){
            CustomizePackage.displayOrderHistory(customerLoggedIn, customizedPackages);
        }
    }

    public static void gotoCatalogOrders(Consumer customerLoggedIn, CorporateCustomer corporateLoggedIn) {
        //Zion part need change since tutor told me use one array so my multiple array is gone

        if (corporateLoggedIn == null) {
            CatalogOrder.CustomerOrderMain(shoppingCart, customerLoggedIn, normalPackage, discountedPackage);
        } else if (customerLoggedIn == null) {
            CatalogOrder.CorporateOrderMain(shoppingCart, corporateLoggedIn, normalPackage, discountedPackage);
        }

    }

    //Dummy data - woo for display purpose
    public static void testing() {
        //testing purpose need to be update
        normalPackage.add(new CatalogPackage("Package 1.0", "Style 1.0", "Small", "Flower 1.0", "Bear 1.0", "Fresh flower", "", 0, 10, 55.00, 0));
        normalPackage.add(new CatalogPackage("Package 2.0", "Style 2.0", "Small", "Flower 2.0", "Bear 2.0", "Fresh flower", "", 0, 10, 77.00, 0));
        normalPackage.add(new CatalogPackage("Package 3.0", "Style 1.0", "Small", "Flower 1.0", "Bear 1.0", "Bouquets", "", 0, 10, 80.00, 0));
        normalPackage.add(new CatalogPackage("Package 4.0", "Style 2.0", "Small", "Flower 2.0", "Bear 2.0", "Bouquets", "r", 0, 10, 60.00, 0));
        normalPackage.add(new CatalogPackage("Package 5.0", "Style 1.0", "Small", "Flower 1.0", "Bear 1.0", "Flower arrangement", "", 0, 10, 120.00, 0));
        normalPackage.add(new CatalogPackage("Package 6.0", "Style 2.0", "Small", "Flower 2.0", "Bear 2.0", "Flower arrangement", "", 0, 10, 90.00, 0));
//        freshFlower.add(new CatalogPackage("Package 1.0", "Style 1.0", "Small", "Flower 1.0", "Bear 1.0", 10, 100.00, 20));
//        freshFlower.add(new CatalogPackage("Package 1.1", "Style 1.1", "Large", "Flower 1.1", "Bear 1.1", 10, 100.00, 0));
//        bouquets.add(new CatalogPackage("Package 2.0", "Style 2.0", "Small", "Flower 2.0", "Bear 2.0", 10, 100.00, 60));
//        bouquets.add(new CatalogPackage("Package 2.1", "Style 2.1", "Small", "Flower 2.1", "Bear 2.1", 10, 100.00, 0));
    }

    public static void userTypeSelection() {
        System.out.println("\nWELCOME TO FIORE FLOWERSHOP SDN BHD !");
        System.out.println("\nPlease SELECT The Type Of User.");
        System.out.println("[1] Customer ");
        System.out.println("[2] Staff ");
        try {
            int userTypeChoice = s.nextInt(); s.nextLine();
            switch (userTypeChoice) {
                case 1:
                    CustomerMaintenance.customerOptions();
                    break;
                case 2:
                    staffTypeSelection();
                    break;
            }
        } catch (Exception e) {
            System.out.println("\n" + ConsoleColors.RED + " An Error Occured. Please Only Enter Number Only." + ConsoleColors.RESET);
            userTypeSelection();
        }
    }

    public static void staffTypeSelection() {
        System.out.println("\nPlease Select The Type of Staff.");
        System.out.println("[1] Manager");
        System.out.println("[2] Inventory Clerk");
        System.out.println("[3] Counter Staff");
        System.out.println("[4] Florist");
        System.out.println("[5] Delivery Staff");
        System.out.println("[6] Back");

        try {
            int staffTypeChoice = s.nextInt(); s.nextLine();
            switch (staffTypeChoice) {
                case 1:
                    manager();
                    break;
                case 2:
                    inventoryClerk();
                    break;
                case 3:
                    counterStaff();
                    break;
                case 4:
                    florist();
                    break;
                case 5: {
                    try {
                        deliveryStaff();
                    } catch (ApiException ex) {
                        Logger.getLogger(FioreFlowershop.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FioreFlowershop.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(FioreFlowershop.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case 6:
                    userTypeSelection();
                    break;
            }
        } catch (Exception e) {
            System.out.println("\n" + ConsoleColors.RED + " An Error Occured. Please Only Enter Number Only." + ConsoleColors.RESET);
            staffTypeSelection();
        }
    }

    public static void manager() {
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] Customer Maintenance");
        System.out.println("[2] Create Corporate Customer Account");
        System.out.println("[3] Add a product to catalog");
        System.out.println("[4] Remove a product from catalog");
        System.out.println("[5] Edit the details of product in catalog");
        System.out.println("[6] Display created catalog");
        System.out.println("[7] Back");
        try {
            int managerChoice = s.nextInt(); s.nextLine();
            String navigationMsg;
            switch (managerChoice) {
                case 1:
                    CustomerMaintenance.staffEditType();
                    break;
                case 2:
                    CustomerMaintenance.staffCreateCorporate();
                    break;
                case 3:
                    navigationMsg = "Create catalog";
                    CatalogMaintenance.productType(navigationMsg, normalPackage, discountedPackage);
                    break;//Add product
                case 4:
                    navigationMsg = "Delete catalog";
                    CatalogMaintenance.displayCatalogType(navigationMsg, normalPackage, discountedPackage);
                    break;//Delete product
                case 5:
                    navigationMsg = "Edit catalog";
                    CatalogMaintenance.displayCatalogType(navigationMsg, normalPackage, discountedPackage);
                    break;//Edit Product
                case 6:
                    navigationMsg = "Display catalog";
                    CatalogMaintenance.displayCatalogType(navigationMsg, normalPackage, discountedPackage);
                    break;//Display product
                case 7:
                    staffTypeSelection();
                    break;//Back to staff selection
            }
        } catch (Exception e) {
            System.out.println("\n" + ConsoleColors.RED + " An Error Occured. Please Only Enter Number Only." + ConsoleColors.RESET);
            manager();
        }
    }

    public static void inventoryClerk() {
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] Check stock quantity.");
        System.out.println("[2] Restock product quantity.");
        System.out.println("[3] Edit customize floral arrangement customization options");
        System.out.println("[4] Back");
        try {
            int inventoryClerkChoice = s.nextInt(); s.nextLine();
            switch (inventoryClerkChoice) {
                case 1: //Check stock quantity
                case 2: //Restock product
                case 3:
                    CustomizePackage.itemsMenu(itemCatalogue, customizedPackages);
                    break;
                case 4:
                    userTypeSelection();
                    break;
            }
        } catch (Exception e) {
            System.out.println("\n" + ConsoleColors.RED + " An Error Occured. Please Only Enter Number Only." + ConsoleColors.RESET);
            inventoryClerk();
        }
    }

    public static void counterStaff() {
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] Corporate Customer Invoice Maintenance");
        System.out.println("[2] Order Pickup/Delivery");
        System.out.println("[3] Consumer Payment Management");
        System.out.println("[4] View Sales Order");
        System.out.println("[5] Back");
        System.out.print("Enter your option: ");

        try {
            int counterStaffChoice = s.nextInt(); s.nextLine();
            switch (counterStaffChoice) {
                case 1:
                    InvoicePayment.invoiceMaintenance();
                    break;
                case 2:
                    orderMenu();
                    break;//order pickup/delivery 
                case 3: //consumer payment management
                case 4: //view sales order
                case 5:
                    CustomizePackage.itemsMenu(itemCatalogue, customizedPackages);
                    break;
                case 6:
                    staffTypeSelection();
                    break;
            }
        } catch (Exception e) {
            System.out.println("\n" + ConsoleColors.RED + " An Error Occured. Please Only Enter Number Only." + ConsoleColors.RESET);
            counterStaff();
        }
    }

    public static void florist() {
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] View Order List");
        System.out.println("[2] Generate Itemized Bill");
        System.out.println("[3] View Customized Floral Arrangement Jobs");
        System.out.println("[4] Back");
        try {
            int floristChoice = s.nextInt(); s.nextLine();
            switch (floristChoice) {
                case 1:
                    orderMenu();
                    break;
                case 2:
                case 3:
                    CustomizePackage.updateOrders(customizedPackages, readyOrders);
                    break;
                case 4:
                    staffTypeSelection();
                    break;
            }
        } catch (Exception e) {
            System.out.println("\n" + ConsoleColors.RED + " An Error Occured. Please Only Enter Number Only." + ConsoleColors.RESET);
            florist();
        }
    }

    public static void deliveryStaff() throws ApiException, InterruptedException, IOException {
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] View Ongoing Delivery List");
        System.out.println("[2] Update Status for Delivery Order");
        System.out.println("[3] View Delivered Order");
        System.out.println("[4] View Delivery Payments");
        System.out.println("[5] Generate Payment Receipt");
        System.out.println("[6] Back");
        int deliveryStaffChoice = s.nextInt(); s.nextLine();
        switch (deliveryStaffChoice) {
            case 1:
            case 2:
            case 3:
                Delivery.sortRouteDelivery(deliveryOrder, customizedPackages, shopAddress);
                try {
                    deliveryStaff();

                } catch (ApiException ex) {
                    Logger.getLogger(FioreFlowershop.class
                            .getName()).log(Level.SEVERE, null, ex);

                } catch (InterruptedException ex) {
                    Logger.getLogger(FioreFlowershop.class
                            .getName()).log(Level.SEVERE, null, ex);

                } catch (IOException ex) {
                    Logger.getLogger(FioreFlowershop.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 4:
                //DeliveryOptimization.distanceMatrix(origin, dest);
                break;
            case 5:
            case 6:
                userTypeSelection();
                break;
        }
    }

    public static void orderMenu() {
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] Pick Up Order");
        System.out.println("[2] Delivery Order");
        System.out.println("[3] Back");
        System.out.println("Enter your option: ");

        int choice = s.nextInt(); s.nextLine();

        if (choice == 1) {
            System.out.println("\nPlease Select The Options Below.");
            System.out.println("1. Today's Pick Up Order List");
            System.out.println("2. Search Pick Up Order List by Date");
            System.out.println("3. Search Pick Up Order by Order ID to Pay");
            System.out.println("Enter your option: ");

            int pickupChoice = s.nextInt();

            if (pickupChoice == 1) {
                Pickup.sortPickupOrder(pickupOrder, customizedPackages);
            } else if (pickupChoice == 2) {

                try {
                    s.nextLine();

                    System.out.print("Please enter date to search (yyyy-MM-dd): ");

                    String dateStr = s.nextLine();

                    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

                    Date date = dateformat.parse(dateStr);

                    Pickup.searchPickUp(pickupOrder, date, customizedPackages);

                } catch (ParseException ex) {
                    Logger.getLogger(FioreFlowershop.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            } else if (pickupChoice == 3) {
                s.nextLine();
                System.out.print("Please enter Order ID to pay: ");

                String orderID = s.nextLine();

                Pickup.searchUserPickUp(orderID, shoppingCart, customizedPackages, paidOrder);
            }

        } else if (choice == 2) {
            System.out.println("\nPlease Select The Options Below.");
            System.out.println("[1] Today's Delivery Order List");
            System.out.println("[2] Search Delivery Order List by Date");
            System.out.println("[3] Back");
            System.out.println("Enter your option: ");

            int deliveryChoice = s.nextInt(); 

            if (deliveryChoice == 1) {
                Delivery.sortDeliveryOrder(deliveryOrder, customizedPackages);
            } else if (deliveryChoice == 2) {
                try {
                    s.nextLine();

                    System.out.print("Please enter date to search (yyyy-MM-dd): ");

                    String dateStr = s.nextLine();

                    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

                    Date date = dateformat.parse(dateStr);

                    Delivery.searchDelivery(deliveryOrder, date, customizedPackages);

                } catch (ParseException ex) {
                    Logger.getLogger(FioreFlowershop.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else {

            }

        } else if (choice == 3) {
            florist();
        }
    }

    public static void sortDeliveryRoute() {
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] Today's Delivery Order List");
        System.out.println("[2] Search Delivery Order List by Date");
        System.out.println("[3] Back");
        System.out.println("Enter your option: ");

        int deliveryChoice = s.nextInt(); 

        if (deliveryChoice == 1) {
            Delivery.sortDeliveryOrder(deliveryOrder, customizedPackages);
            try {
                deliveryStaff();

            } catch (ApiException ex) {
                Logger.getLogger(FioreFlowershop.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (InterruptedException ex) {
                Logger.getLogger(FioreFlowershop.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (IOException ex) {
                Logger.getLogger(FioreFlowershop.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else if (deliveryChoice == 2) {
            try {
                s.nextLine();

                System.out.print("Please enter date to search (yyyy-MM-dd): ");

                String dateStr = s.nextLine();

                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

                Date date = dateformat.parse(dateStr);

                Delivery.searchDelivery(deliveryOrder, date, customizedPackages);

            } catch (ParseException ex) {
                Logger.getLogger(FioreFlowershop.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //GETTER SETTER FOR CUSTOMER LIST
    public static void setCustomer(ListInterface<Consumer> consumer) {
        consumer = consumer;
    }

    public static ListInterface<Consumer> getCustomer() {
        return consumer;
    }

    //GETTER SETTER FOR CORPORATE LIST
    public static void setCorporate(ListInterface<CorporateCustomer> corporateCust) {
        corporate = corporateCust;
    }

    public static ListInterface<CorporateCustomer> getCorporate() {
        return corporate;
    }

    public static ListInterface<User> getUser() {
        return user;
    }

    public static LinkedList<CatalogOrders> getShoppingCart() {
        return shoppingCart;

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
