/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop;

import static fioreflowershop.CatalogMaintenance.catalogMaintenanceMenu;
import static fioreflowershop.CatalogMaintenance.isInteger;
import static fioreflowershop.CatalogMaintenance.scan;
import static fioreflowershop.CatalogMaintenance.userMenuOption;
import fioreflowershop.adt.ArrayList;
import fioreflowershop.adt.ListInterface;
import fioreflowershop.modal.CatalogPackage;
import fioreflowershop.modal.Consumer;
import fioreflowershop.modal.CorporateCustomer;
import fioreflowershop.modal.Order;
import fioreflowershop.modal.CatalogOrder1;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Nicholas
 */
public class CatalogOrder {

    static ListInterface<Order> conOrder = new ArrayList<>();
    static ListInterface<Order> corpOrder = new ArrayList<>();

    //Define item array of CatalgPackage class
    private static ArrayList<CatalogPackage> freshFlower = new ArrayList<>();
    private static ArrayList<CatalogPackage> bouquets = new ArrayList<>();
    private static ArrayList<CatalogPackage> flowerArrangement = new ArrayList<>();
    private static ArrayList<CatalogPackage> freshFlowerDiscounted = new ArrayList<>();
    private static ArrayList<CatalogPackage> bouquetsDiscounted = new ArrayList<>();
    private static ArrayList<CatalogPackage> flowerArrangementDiscounted = new ArrayList<>();

    //Define shopping cart queue of CatalogOrder
    private static ArrayList<CatalogOrder1> shoppingCart = new ArrayList<>();

    //create a scanner object to get user input
    private static Scanner scan = new Scanner(System.in);
    private static int itemSelection;
    private static double itemPrice;

    public static void initializeData() {
        Consumer con = new Consumer("Ncct96", "adgfafgjyaf", "0128198471", "No 13");
        CorporateCustomer corp = new CorporateCustomer("Bxxx1", "sdgsjhd@gmail", "27238723", "No 25", "211221", "Die");
        Order order = new Order(1001, "Pickup", con);
        Order order2 = new Order(1002, "Pickup", corp);

        conOrder.add(order);
        corpOrder.add(order2);

    }

    public static void main(String[] args) {
        testing();
        displayCatalog();
    }

    //Display the catalog or monthly promotion catalog
    public static void displayCatalog() {
        do {
            System.out.println("\n Catalog Order");
            System.out.println("===================");
            System.out.println("1. Catalog");
            System.out.println("2. Monthly promotion catalog");
            System.out.println("3. Back");
            System.out.print("Please enter a number (1-3): ");
            if (scan.hasNextInt()) {
                userMenuOption = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.err.println("Please enter the number only.");
                scan.next();
            }
        } while (!(isInteger) || userMenuOption < 1 || userMenuOption > 3);
        if (userMenuOption == 3) {
            //Back to Main Menu before the catalog order
        } else if (userMenuOption == 1) {
            typeSelection();
        } else if (userMenuOption == 2) {
            monthlyPromotionCatalog();
        }
    }

    public static void typeSelection() {
        do {

            System.out.println("\nNormal Catalog - Type Selection");
            System.out.println("===============================");
            System.out.println("1.Fresh Flower");
            System.out.println("2.Bonquet");
            System.out.println("3.Flower Arrangement");
            System.out.println("4.Back");
            System.out.print("Please enter your choice:");
            if (scan.hasNextInt()) {
                userMenuOption = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.err.println("Please enter the (1-3) only.");
                scan.next();
            }
        } while (!(isInteger) || userMenuOption < 1 || userMenuOption > 3);
        if (userMenuOption == 4) {
            displayCatalog();
        } else if (userMenuOption == 1) {
            freshFlowerCatalog();
        } else if (userMenuOption == 2) {
            bouquetsCatalog();
        } else if (userMenuOption == 3) {
            flowerArrangementCatalog();
        }

    }

    //Display the normal catalog
    public static void freshFlowerCatalog() {      
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
        }else if(freshFlower.getTotalEntries() == 0){
            System.out.println("Sorry, the item type you  selected is not available yet.");
            typeSelection();
        }
        
        do{
            System.out.print("Please enter your choice in number:");
            itemSelection = scan.nextInt();
            
        }while(itemSelection < freshFlower.getTotalEntries() && itemSelection > freshFlower.getTotalEntries());
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
        System.out.print("Please enter quantity of this item you want to order:");
        int quantity = scan.nextInt();

        //Calculate total price of the selected item
        if (discountRate == catalogPackage.getPrice()) {
            itemPrice = freshFlower.getItem(itemSelection).getPrice() * quantity;
        } else {
            itemPrice = (double) (((100 - freshFlower.getItem(itemSelection).getDiscountRate()) * freshFlower.getItem(itemSelection).getPrice() / 100)*quantity);
        }

        //Add in the selected item inside the shoppingCart arraylist
        shoppingCart.add(new CatalogOrder1(freshFlower.getItem(itemSelection).getName(), freshFlower.getItem(itemSelection).getStyle(), freshFlower.getItem(itemSelection).getSize(), freshFlower.getItem(itemSelection).getFlower(), freshFlower.getItem(itemSelection).getAccessory(), itemPrice, quantity));

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
            freshFlowerCatalog();
        } else {
            typeSelection();
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

    public static void bouquetsCatalog() {
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
                    System.out.printf("%d. %s\n", i,catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t\tRM%.2f\t\t  - \n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getPrice());
                } else {
                    System.out.printf("%d. %s\n", i, catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t\tRM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getPrice(), discountRate);
                }
                if (catalogPackage.getDiscountRate() > 0) {
                    bouquetsDiscounted.add(catalogPackage);
                }
            }
        }else if(bouquets.getTotalEntries() == 0){
            System.out.println("Sorry, the item type you  selected is not available yet.");
            typeSelection();
        }
        do{
            System.out.print("Please enter your choice in number:");
            itemSelection = scan.nextInt();
            
        }while(itemSelection < bouquets.getTotalEntries() && itemSelection > bouquets.getTotalEntries());
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
        System.out.print("Please enter quantity of this item you want to order:");
        int quantity = scan.nextInt();

        //Calculate total price of the selected item
        if (discountRate == catalogPackage.getPrice()) {
            itemPrice = bouquets.getItem(itemSelection).getPrice() * quantity;
        } else {
            itemPrice = (double) (((100 - bouquets.getItem(itemSelection).getDiscountRate()) * bouquets.getItem(itemSelection).getPrice() / 100)*quantity);
        }

        //Add in the selected item inside the shoppingCart arraylist
        shoppingCart.add(new CatalogOrder1(bouquets.getItem(itemSelection).getName(), bouquets.getItem(itemSelection).getStyle(), bouquets.getItem(itemSelection).getSize(), bouquets.getItem(itemSelection).getFlower(), bouquets.getItem(itemSelection).getAccessory(), itemPrice, quantity));

        System.out.println("\nDisplay Shopping Cart");
        System.out.println("================================================================================================");
        System.out.println("Product types\t\t\t\tPrice\t\tQuantity");
        if (!shoppingCart.isEmpty()) {
            for (int i = 1; i < shoppingCart.getTotalEntries() + 1; i++) {
                System.out.printf("%s\n", shoppingCart.getItem(i).getItemName());
                System.out.printf("%s,%s,%s,%s \tRM%.2f\t\t   %d\n\n", shoppingCart.getItem(i).getItemStyle(), shoppingCart.getItem(i).getItemSize(), shoppingCart.getItem(i).getItemFlower(), shoppingCart.getItem(i).getItemAccesscory(), shoppingCart.getItem(i).getItemPrice(), shoppingCart.getItem(i).getItemQuantity());
            }
            
        }
        System.out.print("Do you wish to browse through bouquets? (Y/y = Yes, other keys = No)");
        String con = scan.next();

        if (con.equalsIgnoreCase("Y")) {
            bouquetsCatalog();
        } else {
            typeSelection();
        }
    }

    public static void flowerArrangementCatalog() {
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
        }else if(flowerArrangement.getTotalEntries() == 0){
            System.out.println("Sorry, the item type you  selected is not available yet.");
            typeSelection();
        }
        do{
            System.out.print("Please enter your choice in number:");
            itemSelection = scan.nextInt();
            
        }while(itemSelection < flowerArrangement.getTotalEntries() && itemSelection > flowerArrangement.getTotalEntries());
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
        System.out.print("Please enter quantity of this item you want to order:");
        int quantity = scan.nextInt();

        //Calculate total price of the selected item
        if (discountRate == catalogPackage.getPrice()) {
            itemPrice = flowerArrangement.getItem(itemSelection).getPrice() * quantity;
        } else {
            itemPrice = (double) (((100 - flowerArrangement.getItem(itemSelection).getDiscountRate()) * flowerArrangement.getItem(itemSelection).getPrice() / 100)*quantity);
        }

        //Add in the selected item inside the shoppingCart arraylist
        shoppingCart.add(new CatalogOrder1(flowerArrangement.getItem(itemSelection).getName(), flowerArrangement.getItem(itemSelection).getStyle(), flowerArrangement.getItem(itemSelection).getSize(), flowerArrangement.getItem(itemSelection).getFlower(), flowerArrangement.getItem(itemSelection).getAccessory(), itemPrice, quantity));

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
            flowerArrangementCatalog();
        } else {
            typeSelection();
        }

    }

    //Display the monthly promotion catalog
    public static void monthlyPromotionCatalog() {
        CatalogPackage catalogPackage = new CatalogPackage();
        System.out.println("\nDisplay catalog - Monthly promotion catalog");
        System.out.println("================================================================================================");
        System.out.println("Product types\t\t\t\tQuantity\tPrice\t\t\tDiscounted price");
        if (freshFlowerDiscounted.getTotalEntries() != 0) {
            System.out.println("Fresh Flower");
            System.out.println("==================");
            for (int i = 1; i < freshFlowerDiscounted.getTotalEntries() + 1; i++) {
                catalogPackage = freshFlowerDiscounted.getItem(i);
                double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                System.out.printf("%s\n", catalogPackage.getName());
                System.out.printf("%s,%s,%s,%s \t   %d\t\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
            }
        }

        if (bouquetsDiscounted.getTotalEntries() != 0) {
            System.out.println("\nBouquets");
            System.out.println("==================");
            for (int i = 1; i < bouquetsDiscounted.getTotalEntries() + 1; i++) {
                catalogPackage = bouquetsDiscounted.getItem(i);
                double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                System.out.printf("%s\n", catalogPackage.getName());
                System.out.printf("%s,%s,%s,%s \t   %d\t\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
            }
        }

        if (flowerArrangementDiscounted.getTotalEntries() != 0) {
            System.out.println("\nFlower Arrangement");
            System.out.println("==================");
            for (int i = 1; i < flowerArrangementDiscounted.getTotalEntries() + 1; i++) {
                catalogPackage = flowerArrangementDiscounted.getItem(i);
                double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                System.out.printf("%s\n", catalogPackage.getName());
                System.out.printf("%s,%s,%s,%s \t   %d\t\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
            }
        }

        System.out.println("\n====================================================");
        char userOption;
        do {
            System.out.print("Please enter 'Y/y' to Catalog maintenance menu: ");
            userOption = scan.next().charAt(0);
            System.out.println(userOption);

        } while (userOption != 'y' && userOption != 'Y');

        if (userOption == 'y' || userOption == 'Y') {
            catalogMaintenanceMenu();
        }
    }

    //Dummy data
    public static void testing() {
        //testing purpose
        freshFlower.add(new CatalogPackage("Package 1.0", "Style 1.0", "Small", "Flower 1.0", "Bear 1.0", 10, 100.00, 20));
        freshFlower.add(new CatalogPackage("Package 1.1", "Style 1.1", "Large", "Flower 1.1", "Bear 1.1", 10, 100.00, 0));
        bouquets.add(new CatalogPackage("Package 2.0", "Style 2.0", "Small", "Flower 2.0", "Bear 2.0", 10, 100.00, 60));
        bouquets.add(new CatalogPackage("Package 2.1", "Style 2.1", "Small", "Flower 2.1", "Bear 2.1", 10, 100.00, 0));
    }
    
    public static void initializeData(ListInterface pickupOrder) {

        Consumer con = new Consumer("Ncct96", "adgfafgjyaf", "0128198471", "No 13");
        CorporateCustomer corp = new CorporateCustomer("Bxxx1", "sdgsjhd@gmail", "27238723", "No 25", "211221", "Die");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.DAY_OF_MONTH, 20);
        cal.set(Calendar.MONTH, 4);
        cal.set(Calendar.HOUR_OF_DAY, 17);
        cal.set(Calendar.MINUTE, 30);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, 2016);
        cal1.set(Calendar.DAY_OF_MONTH, 20);
        cal1.set(Calendar.MONTH, 5);
        cal1.set(Calendar.HOUR_OF_DAY, 18);
        cal1.set(Calendar.MINUTE, 30);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);

        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, 2015);
        cal2.set(Calendar.DAY_OF_MONTH, 20);
        cal2.set(Calendar.MONTH, 6);
        cal2.set(Calendar.HOUR_OF_DAY, 20);
        cal2.set(Calendar.MINUTE, 30);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);

        Date a = cal2.getTime();
        Date b = cal2.getTime();
        Date c = cal.getTime();
        Date d = cal.getTime();
        Date e = cal1.getTime();
        Date f = cal2.getTime();

        Order order = new Order(1001, "Pickup", con, a);
        Order order2 = new Order(1002, "Pickup", corp, b);
        Order order3 = new Order(1003, "Pickup", corp, c);
        Order order4 = new Order(1004, "Pickup", corp, d);
        Order order5 = new Order(1005, "Pickup", corp, e);
        Order order6 = new Order(1006, "Pickup", corp, f);

        System.out.print(order2.getDate());

        pickupOrder.add(order);
        pickupOrder.add(order2);
        pickupOrder.add(order3);
        pickupOrder.add(order4);
        pickupOrder.add(order5);
        pickupOrder.add(order6);

    }
}
