/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop;

import fioreflowershop.*;
import fioreflowershop.adt.*;
import fioreflowershop.modal.*;

import java.util.Scanner;

/**
 *
 * @author Chiu Peeng
 */
public class FioreFlowershop {

    static ListInterface<Consumer> customer = new ArrayList<>();
    static ListInterface<CorporateCustomer> corporate = new ArrayList<>();
    private static ListInterface<Order> pickupOrder = new ArrayList<>();
    private static Scanner s = new Scanner(System.in);
    private static ArrayList<Item> styles = new ArrayList<>();
    private static ArrayList<Item> sizes = new ArrayList<>();
    private static ArrayList<Item> flowers = new ArrayList<>();
    private static ArrayList<Item> accessories = new ArrayList<>();
    private static ArrayList<Item> priorities = new ArrayList<>();
    private static ArrayList<Item> deliveryTypes = new ArrayList<>();
    private static ArrayQueue<CustomizedPackage> customizedPackages = new ArrayQueue<>();

    //Catalog Maintenance part
    private static ArrayList<CatalogPackage> freshFlower = new ArrayList<>();
    private static ArrayList<CatalogPackage> bouquets = new ArrayList<>();
    private static ArrayList<CatalogPackage> flowerArrangement = new ArrayList<>();
    private static ArrayList<CatalogPackage> freshFlowerDiscounted = new ArrayList<>();
    private static ArrayList<CatalogPackage> bouquetsDiscounted = new ArrayList<>();
    private static ArrayList<CatalogPackage> flowerArrangementDiscounted = new ArrayList<>();

    public static void main(String[] args) {
        Consumer customer = new Consumer();
        initializePackages();
        CatalogOrder.initializeData(pickupOrder);
        Pickup.customSortPickup(customizedPackages, customer);

        /////// CHIUPEENG DEBUG LOOP //////
        for (int i = 0; i < 5; i++) {
            CustomizePackage.customizePackageControl(styles, sizes, flowers, accessories, priorities, deliveryTypes, customer, customizedPackages);
        }

        counterStaff();

        userTypeSelection();
    }

    public static void initializePackages() {
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

        deliveryTypes.add(new Item("Pickup", 0));
        deliveryTypes.add(new Item("Deliver", 10));

        Consumer customer = new Consumer();
        customizedPackages.enqueue(new CustomizedPackage(styles.getItem(2), sizes.getItem(3), flowers.getItem(1), accessories.getItem(4), priorities.getItem(3), deliveryTypes.getItem(1), customer));
        customizedPackages.enqueue(new CustomizedPackage(styles.getItem(1), sizes.getItem(2), flowers.getItem(3), accessories.getItem(3), priorities.getItem(2), deliveryTypes.getItem(2), customer));
        customizedPackages.enqueue(new CustomizedPackage(styles.getItem(3), sizes.getItem(1), flowers.getItem(2), accessories.getItem(1), priorities.getItem(2), deliveryTypes.getItem(2), customer));
        customizedPackages.enqueue(new CustomizedPackage(styles.getItem(4), sizes.getItem(2), flowers.getItem(4), accessories.getItem(1), priorities.getItem(1), deliveryTypes.getItem(1), customer));
        customizedPackages.enqueue(new CustomizedPackage(styles.getItem(1), sizes.getItem(2), flowers.getItem(5), accessories.getItem(2), priorities.getItem(1), deliveryTypes.getItem(2), customer));
    }

    //Dummy data - woo for display purpose
    public static void testing() {
        //testing purpose
        freshFlower.add(new CatalogPackage("Package 1.0", "Style 1.0", "Small", "Flower 1.0", "Bear 1.0", 10, 100.00, 20));
        freshFlower.add(new CatalogPackage("Package 1.1", "Style 1.1", "Large", "Flower 1.1", "Bear 1.1", 10, 100.00, 0));
        bouquets.add(new CatalogPackage("Package 2.0", "Style 2.0", "Small", "Flower 2.0", "Bear 2.0", 10, 100.00, 60));
        bouquets.add(new CatalogPackage("Package 2.1", "Style 2.1", "Small", "Flower 2.1", "Bear 2.1", 10, 100.00, 0));
    }

    public static void userTypeSelection() {
        System.out.println("\nWELCOME TO FIORE FLOWERSHOP SDN BHD !");
        System.out.println("\nPlease SELECT The Type Of User.");
        System.out.println("[1] Customer ");
        System.out.println("[2] Staff ");
        int userTypeChoice = s.nextInt();
        switch (userTypeChoice) {
            case 1:
                CustomerMaintenance.customerOptions();
                break;
            case 2:
                staffTypeSelection();
                break;
        }
    }

    public static void staffTypeSelection() {
        System.out.println("\nPlease Select The Type of Staff.");
        System.out.println("[1] Manager");
        System.out.println("[2] Inventory Clerk");
        System.out.println("[3] Counter Staff");
        System.out.println("[4] Florist");
        System.out.println("[5] Delivery Staff");
        int staffTypeChoice = s.nextInt();
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
            case 5:
                deliveryStaff();
                break;
        }
    }

    public static void manager() {
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] Add a product to catalog");
        System.out.println("[2] Remove a product from catalog");
        System.out.println("[3] Edit the details of product in catalog");
        System.out.println("[4] Display created catalog");
        int managerChoice = s.nextInt();

        String navigationMsg;
        switch (managerChoice) {
            case 1: //Add product
                navigationMsg = "Create catalog";
                CatalogMaintenance.productType(navigationMsg, freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted);
                break;
            case 2: //Delete product
            case 3: //Edit Product
            case 4: //Display product
                navigationMsg = "Display catalog";
                CatalogMaintenance.displayCatalog(navigationMsg, freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted);
                break;
        }
    }

    public static void inventoryClerk() {
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] Check stock quantity.");
        System.out.println("[2] Restock product quantity.");
        int inventoryClerkChoice = s.nextInt();
        switch (inventoryClerkChoice) {
            case 1: //Check stock quantity
            case 2: //Restock product
        }
    }

    public static void counterStaff() {
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] Customer Maintenance");
        System.out.println("[2] Corporate Customer Invoice Maintenance");
        System.out.println("[3] Order Pickup/Delivery");
        System.out.println("[4] Consumer Payment Management");
        System.out.println("[5] View Sales Order");
        int counterStaffChoice = s.nextInt();
        switch (counterStaffChoice) {
            case 1: //Customer Maintenance
            case 2: //corporate customer invoice
            case 3: //order pickup/delivery
                orderMenu();
                break;
            case 4: //consumer payment management
            case 5: //view sales order
        }
    }

    public static void florist() {
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] View Today's Pick Up List");
        System.out.println("[2] View Today's Delivery List");
        System.out.println("[3] Generate Itemized Bill");
        int floristChoice = s.nextInt();
        switch (floristChoice) {
            case 1:
            case 2:
            case 3:
        }
    }

    public static void deliveryStaff() {
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] View Ongoing Delivery List");
        System.out.println("[2] Update Status for Delivery Order");
        System.out.println("[3] View Delivered Order");
        System.out.println("[4] View Delivery Payments");
        System.out.println("[5] Generate Payment Receipt");
        int deliveryStaffChoice = s.nextInt();
        switch (deliveryStaffChoice) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
        }
    }

    public static void orderMenu() {

        System.out.println("1. Pick Up Order");
        System.out.println("2. Delivery Order");

        int choice = s.nextInt();

        if (choice == 1) {
            System.out.println("1. Today's Pick Up Order List");
            System.out.println("2. Search Pick Up Order List by Date");

            int pickupChoice = s.nextInt();

            if (pickupChoice == 1) {
                Pickup.sortPickupOrder(pickupOrder, customizedPackages);
            } else if (pickupChoice == 2) {

            } else {

            }

        } else if (choice == 2) {
            System.out.println("1. Today's Delivery Order List");
            System.out.println("2. Search Delivery Order List by Date");

            int deliveryChoice = s.nextInt();

            if (deliveryChoice == 1) {
                Pickup.sortPickupOrder(pickupOrder, customizedPackages);
            } else if (deliveryChoice == 2) {

            } else {

            }

        } else {

        }
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
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop;

import fioreflowershop.*;
import fioreflowershop.adt.*;
import fioreflowershop.modal.*;
import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chiu Peeng
 */
public class FioreFlowershop {

    private static ListInterface<Consumer> customer = new ArrayList<>();
    private static ListInterface<CorporateCustomer> corporate = new ArrayList<>();
    private static ListInterface<Order> pickupOrder = new ArrayList<Order>();
    private static Scanner s = new Scanner(System.in);
    private static ArrayList<Item> styles = new ArrayList<>();
    private static ArrayList<Item> sizes = new ArrayList<>();
    private static ArrayList<Item> flowers = new ArrayList<>();
    private static ArrayList<Item> accessories = new ArrayList<>();
    private static ArrayList<Item> priorities = new ArrayList<>();
    private static ArrayList<Item> deliveryTypes = new ArrayList<>();
    private static ArrayQueue<CustomizedPackage> customizedPackages = new ArrayQueue<>();

    //Catalog Maintenance part
    private static ArrayList<CatalogPackage> freshFlower = new ArrayList<>();
    private static ArrayList<CatalogPackage> bouquets = new ArrayList<>();
    private static ArrayList<CatalogPackage> flowerArrangement = new ArrayList<>();
    private static ArrayList<CatalogPackage> freshFlowerDiscounted = new ArrayList<>();
    private static ArrayList<CatalogPackage> bouquetsDiscounted = new ArrayList<>();
    private static ArrayList<CatalogPackage> flowerArrangementDiscounted = new ArrayList<>();

    public static void main(String[] args) {
        Consumer customer = new Consumer();
        initializePackages();
        CatalogOrder.initializeData(pickupOrder);

        //Pickup.customSortPickup(customizedPackages, customer);

        //CustomizePackage.CustomizePackageControl(styles, sizes, flowers, accessories, priorities, deliveryTypes, customer, customizedPackages);

        
        /////// CHIUPEENG DEBUG LOOP //////
//        for (int i = 0; i < 3; i++) {
//            CustomizePackage.CustomizePackageControl(styles, sizes, flowers, accessories, priorities, deliveryTypes, customer, customizedPackages);
//        }

        //counterStaff();
        Pickup.customSortPickup(customizedPackages, customer);

//        CustomizePackage.CustomizePackageControl(styles, sizes, flowers, accessories, priorities, deliveryTypes, customer, customizedPackages);
        /////// CHIUPEENG DEBUG LOOP //////
//        for (int i = 0; i < 5; i++) {
//            CustomizePackage.CustomizePackageControl(styles, sizes, flowers, accessories, priorities, deliveryTypes, customer, customizedPackages);
//        }
        counterStaff();

        userTypeSelection();
    }

    public static void initializePackages() {
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

        deliveryTypes.add(new Item("Pickup", 0));
        deliveryTypes.add(new Item("Deliver", 10));
        
        Consumer customer = new Consumer();
        customizedPackages.enqueue(new CustomizedPackage(styles.getItem(2), sizes.getItem(3), flowers.getItem(1), accessories.getItem(4), priorities.getItem(3), deliveryTypes.getItem(1), customer));
        customizedPackages.enqueue(new CustomizedPackage(styles.getItem(1), sizes.getItem(2), flowers.getItem(3), accessories.getItem(3), priorities.getItem(2), deliveryTypes.getItem(2), customer));
        customizedPackages.enqueue(new CustomizedPackage(styles.getItem(3), sizes.getItem(1), flowers.getItem(2), accessories.getItem(1), priorities.getItem(2), deliveryTypes.getItem(2), customer));
        customizedPackages.enqueue(new CustomizedPackage(styles.getItem(4), sizes.getItem(2), flowers.getItem(4), accessories.getItem(1), priorities.getItem(1), deliveryTypes.getItem(1), customer));
        customizedPackages.enqueue(new CustomizedPackage(styles.getItem(1), sizes.getItem(2), flowers.getItem(5), accessories.getItem(2), priorities.getItem(1), deliveryTypes.getItem(2), customer));
    }

    //Dummy data - woo for display purpose
    public static void testing() {
        //testing purpose
        freshFlower.add(new CatalogPackage("Package 1.0", "Style 1.0", "Small", "Flower 1.0", "Bear 1.0", 10, 100.00, 20));
        freshFlower.add(new CatalogPackage("Package 1.1", "Style 1.1", "Large", "Flower 1.1", "Bear 1.1", 10, 100.00, 0));
        bouquets.add(new CatalogPackage("Package 2.0", "Style 2.0", "Small", "Flower 2.0", "Bear 2.0", 10, 100.00, 60));
        bouquets.add(new CatalogPackage("Package 2.1", "Style 2.1", "Small", "Flower 2.1", "Bear 2.1", 10, 100.00, 0));
    }

    public static void userTypeSelection() {
        System.out.println("\nWELCOME TO FIORE FLOWERSHOP SDN BHD !");
        System.out.println("\nPlease SELECT The Type Of User.");
        System.out.println("[1] Customer ");
        System.out.println("[2] Staff ");
        int userTypeChoice = s.nextInt();
        switch (userTypeChoice) {
            case 1:
                CustomerMaintenance.customerOptions();
                break;
            case 2:
                staffTypeSelection();
                break;
        }
    }

    public static void staffTypeSelection() {
        System.out.println("\nPlease Select The Type of Staff.");
        System.out.println("[1] Manager");
        System.out.println("[2] Inventory Clerk");
        System.out.println("[3] Counter Staff");
        System.out.println("[4] Florist");
        System.out.println("[5] Delivery Staff");
        int staffTypeChoice = s.nextInt();
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
            case 5:
                deliveryStaff();
                break;
        }
    }

    public static void manager() {
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] Add a product to catalog");
        System.out.println("[2] Remove a product from catalog");
        System.out.println("[3] Edit the details of product in catalog");
        System.out.println("[4] Display created catalog");
        int managerChoice = s.nextInt();

        String navigationMsg;
        switch (managerChoice) {
            case 1: //Add product
                navigationMsg = "Create catalog";
                CatalogMaintenance.productType(navigationMsg, freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted);
                break;
            case 2: //Delete product
            case 3: //Edit Product
            case 4: //Display product
                navigationMsg = "Display catalog";
                CatalogMaintenance.displayCatalog(navigationMsg, freshFlower, bouquets, flowerArrangement, freshFlowerDiscounted, bouquetsDiscounted, flowerArrangementDiscounted);
                break;
        }
    }

    public static void inventoryClerk() {
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] Check stock quantity.");
        System.out.println("[2] Restock product quantity.");
        int inventoryClerkChoice = s.nextInt();
        switch (inventoryClerkChoice) {
            case 1: //Check stock quantity
            case 2: //Restock product
        }
    }

    public static void counterStaff() {
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] Customer Maintenance");
        System.out.println("[2] Corporate Customer Invoice Maintenance");
        System.out.println("[3] Order Pickup/Delivery");
        System.out.println("[4] Consumer Payment Management");
        System.out.println("[5] View Sales Order");
        int counterStaffChoice = s.nextInt();
        switch (counterStaffChoice) {
            case 1: CustomerMaintenance.staffEditType();
            case 2: //corporate customer invoice
            case 3: //order pickup/delivery
                orderMenu();
                break;
            case 4: //consumer payment management
            case 5: //view sales order
        }
    }

    public static void florist() {
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] View Today's Pick Up List");
        System.out.println("[2] View Today's Delivery List");
        System.out.println("[3] Generate Itemized Bill");
        int floristChoice = s.nextInt();
        switch (floristChoice) {
            case 1:
            case 2:
            case 3:
        }
    }

    public static void deliveryStaff() {
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] View Ongoing Delivery List");
        System.out.println("[2] Update Status for Delivery Order");
        System.out.println("[3] View Delivered Order");
        System.out.println("[4] View Delivery Payments");
        System.out.println("[5] Generate Payment Receipt");
        int deliveryStaffChoice = s.nextInt();
        switch (deliveryStaffChoice) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
        }
    }

    public static void orderMenu() {

        System.out.println("1. Pick Up Order");
        System.out.println("2. Delivery Order");

        int choice = s.nextInt();

        if (choice == 1) {
            System.out.println("1. Today's Pick Up Order List");
            System.out.println("2. Search Pick Up Order List by Date");

            int pickupChoice = s.nextInt();

            if (pickupChoice == 1) {
                Pickup.sortPickupOrder(pickupOrder, customizedPackages);
            } else if (pickupChoice == 2) {

                try {
                    s.nextLine();
                    
                    System.out.println("Please enter date to search (yyyy-MM-dd): ");
                    
                    String dateStr = s.nextLine();
                    
                    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                    
                    Date date = dateformat.parse(dateStr);
                    
                    Pickup.searchPickUp(pickupOrder, date);
                } catch (ParseException ex) {
                    Logger.getLogger(FioreFlowershop.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {

            }

        } else if (choice == 2) {
            System.out.println("1. Today's Delivery Order List");
            System.out.println("2. Search Delivery Order List by Date");

            int deliveryChoice = s.nextInt();

            if (deliveryChoice == 1) {
                Pickup.sortPickupOrder(pickupOrder, customizedPackages);
            } else if (deliveryChoice == 2) {

            } else {

            }

        } else {

        }
    }
    
    //GETTER SETTER FOR CUSTOMER LIST
    public static void setCustomer(ListInterface<Consumer> consumer){
        customer = consumer;
    }
    
    public static ListInterface<Consumer> getCustomer(){
        return customer;
    }
    
    //GETTER SETTER FOR CORPORATE LIST
    public static void setCorporate(ListInterface<CorporateCustomer> corporateCust){
        corporate = corporateCust;
    }
    
    public static ListInterface<CorporateCustomer> getCorporate(){
        return corporate;
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
