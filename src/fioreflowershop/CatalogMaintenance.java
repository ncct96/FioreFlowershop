/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop;

import fioreflowershop.modal.CatalogPackage;
//import java.util.ArrayList;
import fioreflowershop.adt.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Woo
 */
public class CatalogMaintenance {

    private static ArrayList<CatalogPackage> freshFlower = new ArrayList<>();
    private static ArrayList<CatalogPackage> bouquets = new ArrayList<>();
    private static ArrayList<CatalogPackage> flowerArrangement = new ArrayList<>();
    private static ArrayList<CatalogPackage> discountedCatalog = new ArrayList<>();

    //Data field
    static int userMenuOption;
    static boolean isInteger;

    //create a scanner object to get user input
    static Scanner scan = new Scanner(System.in);

    //First UI
    public static void catalogMaintenanceMenu() {
        do {
            System.out.println("Catalog Maintenance");
            System.out.println("===================");
            System.out.println("1. Display catalog\n2. Create catalog\n3. Update catalog\n4. Delete catalog\n5. Stock Maintenance\n6. Main menu");
            System.out.println("===================");
            System.out.print("Please choose an option (1-6): ");
            if (scan.hasNextInt()) {
                userMenuOption = scan.nextInt();
                isInteger = true;
                if (userMenuOption == 1) {
                    String navigationMsg = "Display catalog";
                    displayCatalog(navigationMsg);
                } else if (userMenuOption == 2) {
                    String navigationMsg = "Create catalog";
                    productType(navigationMsg);
                } else if (userMenuOption == 3) {
                    String navigationMsg = "Update catalog";
                    productType(navigationMsg);
                } else if (userMenuOption == 4) {
                    String navigationMsg = "Delete catalog";
                    productType(navigationMsg);
                } else if (userMenuOption == 5) {

                } else if (userMenuOption == 6) {
//                    FioreFlowershop.main(args);
                }
            } else {
                isInteger = false;
                System.err.println("Please enter the number only.");
                scan.next();
            }
        } while (!(isInteger) || userMenuOption < 1 || userMenuOption > 6);
    }

    //Display item type
    public static void productType(String navigationTitle) {
        int productTypes = 0;
        do {
            System.out.println(navigationTitle);
            System.out.println("===================");
            System.out.println("Item type list\n1. Fresh flowers\n2. Bouquets\n3. Flower arrangement\n4. Back");
            System.out.print("Please enter the product type(1-4): ");
            if (scan.hasNextInt()) {
                productTypes = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.err.println("Please enter the number only.");
                scan.next();
            }
        } while (!(isInteger) || productTypes < 1 || productTypes > 4);
        if (productTypes == 4) {
            catalogMaintenanceMenu();
        } else if (navigationTitle.equals("Create catalog")) {
            createCatalog(navigationTitle, productTypes);
        }
    }

    //Create catalog UI
    public static void createCatalog(String navigationTitle, int productTypes) {
        char nextProduct;
        do {
            System.out.println(navigationTitle);
            System.out.println("===================");
            System.out.print("Please enter package name: ");
            scan.nextLine();
            String name = scan.nextLine();
            System.out.println(name);
            System.out.print("Please enter package style: ");
            String style = scan.nextLine();
            System.out.println(style);

            int sizeOption = 0;
            String size = "";
            
            do {
                System.out.print("Please enter package size (1 = Small, 2 = Medium, 3 = Large): ");
                if (scan.hasNextInt()) {
                    sizeOption = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.err.println("Please enter the number only.");
                    scan.next();
                }
            } while (!(isInteger));

            if (sizeOption == 1) {
                size = "Small";
            } else if (sizeOption == 2) {
                size = "Medium";
            } else if (sizeOption == 3) {
                size = "Large";
            }

            System.out.println("No = " + size);
            System.out.print("Please enter flower: ");
            scan.nextLine();
            String flower = scan.nextLine();
            System.out.println(flower);
            System.out.print("Please enter acessories: ");
            String acessories = scan.nextLine();
            System.out.println(acessories);

            int quantity = 0;
            do {
                System.out.print("Please enter package quantity: ");
                if (scan.hasNextInt()) {
                    quantity = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.err.println("Please enter the number only.");
                    scan.next();
                }
            } while (!(isInteger));
            System.out.println("No = " + quantity);

            boolean isDouble;
            double price = 0;
            do {
                System.out.print("Please enter package price: ");
                if (scan.hasNextDouble()) {
                    price = scan.nextDouble();
                    isDouble = true;
                } else {
                    isDouble = false;
                    System.err.println("Please enter the number only.");
                    scan.next();
                }
            } while (!(isDouble));
            System.out.println("No = " + price);

            int discountRate = 0;
            do {
                System.out.print("Please enter the discount rate for monthly promotion(0 - 100%): ");
                if (scan.hasNextInt()) {
                    discountRate = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.err.println("Please enter the number only.");
                    scan.next();
                }
            } while (!(isInteger) || discountRate < 0 || discountRate > 100);
            System.out.println(discountRate);
            CatalogPackage catalogPackage = new CatalogPackage(name, style, size, flower, acessories, quantity, price, discountRate);

            if (productTypes == 1) {
                freshFlower.add(catalogPackage);
            } else if (productTypes == 2) {
                bouquets.add(catalogPackage);
            } else if (productTypes == 3) {
                flowerArrangement.add(catalogPackage);
            }

            System.out.print("Do you wish to add another product ?\nY/y for continue, enter any key for cancel: ");
            nextProduct = scan.next().charAt(0);
        } while (nextProduct == 'Y' || nextProduct == 'y');

        if (nextProduct != 'y' || nextProduct != 'Y') {
            productType(navigationTitle);
        }
    }

    //Display the catalog or monthly promotion catalog
    public static void displayCatalog(String navigationTitle) {
        do {
            System.out.println(navigationTitle);
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
            catalogMaintenanceMenu();
        }else if(userMenuOption == 1){
            normalCatalog();
        }
    }
    
    //Display the normal catalog
    public static void normalCatalog() {
        CatalogPackage catalogPackage = new CatalogPackage();
        System.out.println("Display catalog - normal catalog");
        System.out.println("================================================================================================");
        System.out.println("Package Name                      Quantity            Price                Discounted price     ");
        for(int i = 1; i < freshFlower.getTotalEntries(); i++){
            catalogPackage = freshFlower.getItem(i);
            System.out.printf("%d", catalogPackage.getDiscountRate());
            double discountRate = catalogPackage.getPrice() - (double)(catalogPackage.getDiscountRate() / 100.00);
            System.out.printf("%s\n", catalogPackage.getName());
            System.out.printf("%s,%s,%s,%s\t\t%d  %.2f  %.2f", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
        }
        System.out.println("Display catalog - normal catalog");
    }
}
