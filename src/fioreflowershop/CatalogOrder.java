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
        }

        System.out.print("Please enter your choice in number:");
        int itemSelection = scan.nextInt();
             
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
            itemPrice = (double) ((100 - freshFlower.getItem(itemSelection).getDiscountRate()) * freshFlower.getItem(itemSelection).getPrice() / 100);
        }

        //Add in the selected item inside the shoppingCart arraylist
        shoppingCart.add(new CatalogOrder1(freshFlower.getItem(itemSelection).getName(), freshFlower.getItem(itemSelection).getStyle(), freshFlower.getItem(itemSelection).getSize(), freshFlower.getItem(itemSelection).getFlower(), freshFlower.getItem(itemSelection).getAccessory(), itemPrice, quantity));
        
        
        System.out.println("\nDisplay Shopping Cart");
        System.out.println("================================================================================================");
        System.out.println("Product types\t\t\t\tPrice\t\tQuantity");
        if (!shoppingCart.isEmpty()) {

            System.out.printf("%s\n", shoppingCart.getItem(itemSelection).getItemName());
            System.out.printf("%s,%s,%s,%s \tRM%.2f\t   %d\n\n", shoppingCart.getItem(itemSelection).getItemStyle(), shoppingCart.getItem(itemSelection).getItemSize(), shoppingCart.getItem(itemSelection).getItemFlower(), shoppingCart.getItem(itemSelection).getItemAccesscory(), shoppingCart.getItem(itemSelection).getItemPrice() * quantity, shoppingCart.getItem(itemSelection).getItemQuantity());

//            discountRate = (double) ((100 - freshFlower.getItem(itemSelection).getDiscountRate()) * freshFlower.getItem(itemSelection).getPrice() / 100);
//            System.out.printf("%s\n", freshFlower.getItem(itemSelection).getName());
//            System.out.printf("%s,%s,%s,%s \tRM%.2f\t   %d\n\n", freshFlower.getItem(itemSelection).getStyle(), freshFlower.getItem(itemSelection).getSize(), freshFlower.getItem(itemSelection).getFlower(), freshFlower.getItem(itemSelection).getAccessory(), discountRate * quantity, quantity);

        }
        System.out.print("Do you wish to browse through fresh flower? (Y/y = Yes, other keys = No)");
        String con = scan.next();
        
        if(con.equalsIgnoreCase("Y")){
            freshFlowerCatalog();
        }else{
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
        if (bouquets.getTotalEntries() != 0) {
            System.out.println("\nBouquets");
            System.out.println("==================");
            for (int i = 1; i < bouquets.getTotalEntries() + 1; i++) {
                catalogPackage = bouquets.getItem(i);
                double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                if (discountRate == catalogPackage.getPrice()) {
                    System.out.printf("%s\n", catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t   %d\t\t RM%.2f\t\t  - \n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice());
                } else {
                    System.out.printf("%s\n", catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t   %d\t\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
                }
                if (catalogPackage.getDiscountRate() > 0) {
                    bouquetsDiscounted.add(catalogPackage);
                }
            }
        }

        System.out.println("\nDiscounted Product List");
        System.out.println("================================================================================================");
        System.out.println("Product types\t\t\t\tQuantity\tPrice\t\t\tDiscounted price");
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
        }
        System.out.println("\nDiscounted Product List");
        System.out.println("================================================================================================");
        System.out.println("Product types\t\t\t\tQuantity\tPrice\t\t\tDiscounted price");
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

}
