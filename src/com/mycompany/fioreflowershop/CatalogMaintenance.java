/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.modal.CatalogPackage;
//import java.util.ArrayList;
import com.mycompany.fioreflowershop.adt.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Woo
 */
public class CatalogMaintenance {

    //Data field    
    static boolean isInteger;

    //create a scanner object to get user input
    static Scanner scan = new Scanner(System.in);

    //Display item type
    public static void productType(String navigationTitle, ArrayList<CatalogPackage> freshFlower, ArrayList<CatalogPackage> bouquets, ArrayList<CatalogPackage> flowerArrangement, ArrayList<CatalogPackage> freshFlowerDiscounted,ArrayList<CatalogPackage> bouquetsDiscounted, ArrayList<CatalogPackage> flowerArrangementDiscounted) {
        //Product type menu
        int productTypes = 0;
        do {
            System.out.println("\n" + navigationTitle);
            System.out.println("===================");
            System.out.println("Item type list\n1. Fresh flowers\n2. Bouquets\n3. Flower arrangement\n4. Back");     
            if (productTypes < 0 || productTypes > 4) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number within the range of 1 - 4.");
            }
            System.out.print("Please enter the product type(1-4): ");
            if (scan.hasNextInt()) {
                productTypes = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.");
                scan.next();
            }
        } while (!(isInteger) || productTypes < 1 || productTypes > 4);

        //Check for user option and take action base on user input
        if (productTypes == 4) {//Bring manager back to manager main menu
            FioreFlowershop.manager();
        } else if (navigationTitle.equals("Create catalog")) {//Perform the create product for catalog
            char nextProduct;
            String name, style, flower, acessories;
            boolean validInput;

            do {
                scan.nextLine(); //To read the input, if no directly skip the first question
                System.out.println("\n" + navigationTitle);
                System.out.println("===================");

                do {
                    validInput = true;
                    System.out.print("Please enter package name: ");
                    name = scan.nextLine();
                    if (name == null || name.isEmpty()) {
                        validInput = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty");
                    }
                } while (validInput == false);

                do {
                    validInput = true;
                    System.out.print("Please enter package style: ");
                    style = scan.nextLine();
                    if (style == null || style.isEmpty()) {
                        validInput = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty");
                    }
                } while (validInput == false);

                int sizeOption = 0;
                String size = "";
                do {
                    System.out.print("Please enter package size (1 = Small, 2 = Medium, 3 = Large): ");
                    if (scan.hasNextInt()) {
                        sizeOption = scan.nextInt();
                        isInteger = true;
                    } else {
                        isInteger = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.");
                        scan.next();
                    }
                } while (!(isInteger) || sizeOption < 1 || sizeOption > 3);

                if (sizeOption == 1) {
                    size = "Small";
                } else if (sizeOption == 2) {
                    size = "Medium";
                } else if (sizeOption == 3) {
                    size = "Large";
                }

                scan.nextLine();
                do {
                    validInput = true;
                    System.out.print("Please enter flower: ");
                    flower = scan.nextLine();

                    if (flower == null || flower.isEmpty()) {
                        validInput = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty");
                    }
                } while (validInput == false);

                do {
                    validInput = true;
                    System.out.print("Please enter acessories: ");
                    acessories = scan.nextLine();
                    if (acessories == null || acessories.isEmpty()) {
                        validInput = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty");
                    }
                } while (validInput == false);

                int quantity = 0;
                do {
                    System.out.print("Please enter package quantity: ");
                    if (scan.hasNextInt()) {
                        quantity = scan.nextInt();
                        isInteger = true;
                    } else {
                        isInteger = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.");
                        scan.next();
                    }
                } while (!(isInteger));

                boolean isDouble;
                double price = 0;
                do {
                    System.out.print("Please enter package price: ");
                    if (scan.hasNextDouble()) {
                        price = scan.nextDouble();
                        isDouble = true;
                    } else {
                        isDouble = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.");
                        scan.next();
                    }
                } while (!(isDouble));

                int discountRate = 0;
                do {
                    System.out.print("Please enter the discount rate for monthly promotion(0 - 100%): ");
                    if (scan.hasNextInt()) {
                        discountRate = scan.nextInt();
                        isInteger = true;
                    } else {
                        isInteger = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.");
                        scan.next();
                    }
                } while (!(isInteger) || discountRate < 0 || discountRate > 100);
                CatalogPackage catalogPackage = new CatalogPackage(name, style, size, flower, acessories, quantity, price, discountRate);

                if (productTypes == 1) {
                    if (discountRate > 0) {
                        freshFlowerDiscounted.add(catalogPackage);
                    }
                    freshFlower.add(catalogPackage);

                } else if (productTypes == 2) {
                    if (discountRate > 0) {
                        bouquetsDiscounted.add(catalogPackage);
                    }
                    bouquets.add(catalogPackage);
                } else if (productTypes == 3) {
                    if (discountRate > 0) {
                        flowerArrangementDiscounted.add(catalogPackage);
                    }
                    flowerArrangement.add(catalogPackage);
                }

                System.out.print("Do you wish to add another product ?\nY/y for continue, enter any key for cancel: ");
                nextProduct = scan.next().charAt(0);
            } while (nextProduct == 'Y' || nextProduct == 'y');

            if (nextProduct != 'y' || nextProduct != 'Y') {
               FioreFlowershop.manager();
            }
        }
    }

    //Display the catalog or monthly promotion catalog
    public static void displayCatalog(String navigationTitle, ArrayList<CatalogPackage> freshFlower, ArrayList<CatalogPackage> bouquets, ArrayList<CatalogPackage> flowerArrangement, ArrayList<CatalogPackage> freshFlowerDiscounted, ArrayList<CatalogPackage> bouquetsDiscounted, ArrayList<CatalogPackage> flowerArrangementDiscounted) {
        int userMenuOption = 1;
        do {
            System.out.println("\n" + navigationTitle);
            System.out.println("===================");
            System.out.println("1. Catalog");
            System.out.println("2. Monthly promotion catalog");
            System.out.println("3. Back");
            if (userMenuOption < 1 || userMenuOption > 3) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number within 1 - 3.");
            }            
            System.out.print("Please enter a number (1-3): ");
            if (scan.hasNextInt()) {
                userMenuOption = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.");
                scan.next();
            }
        } while (!(isInteger) || userMenuOption < 1 || userMenuOption > 3);

        if (userMenuOption == 3) { // Back to Manager menu
            FioreFlowershop.manager();
        } else if (userMenuOption == 1) { //Display the normal catalog
            CatalogPackage catalogPackage = new CatalogPackage();
            System.out.println("\nDisplay catalog - normal catalog");
            System.out.println("================================================================================================");
            System.out.println("Product types\t\t\t\tQuantity\tPrice\t\t\tDiscounted price");
            if (freshFlower.getTotalEntries() != 0) {
                System.out.println("Fresh Flower");
                System.out.println("==================");
                for (int i = 1; i < freshFlower.getTotalEntries() + 1; i++) {
                    catalogPackage = freshFlower.getItem(i);
                    double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                    if (discountRate == catalogPackage.getPrice()) {
                        System.out.printf("%s\n", catalogPackage.getName());
                        System.out.printf("%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t  - \n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice());
                    } else {
                        System.out.printf("%s\n", catalogPackage.getName());
                        System.out.printf("%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
                    }

//                    if (catalogPackage.getDiscountRate() > 0) {
//                        freshFlowerDiscounted.add(catalogPackage);
//                    }
                }
            }

            if (bouquets.getTotalEntries() != 0) {
                System.out.println("\nBouquets");
                System.out.println("==================");
                for (int i = 1; i < bouquets.getTotalEntries() + 1; i++) {
                    catalogPackage = bouquets.getItem(i);
                    double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                    if (discountRate == catalogPackage.getPrice()) {
                        System.out.printf("%s\n", catalogPackage.getName());
                        System.out.printf("%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t  - \n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice());
                    } else {
                        System.out.printf("%s\n", catalogPackage.getName());
                        System.out.printf("%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
                    }
//                    if (catalogPackage.getDiscountRate() > 0) {
//                        bouquetsDiscounted.add(catalogPackage);
//                    }
                }
            }

            if (flowerArrangement.getTotalEntries() != 0) {
                System.out.println("\nFlower Arrangement");
                System.out.println("==================");
                for (int i = 1; i < flowerArrangement.getTotalEntries() + 1; i++) {
                    catalogPackage = flowerArrangement.getItem(i);
                    double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                    if (discountRate == catalogPackage.getPrice()) {
                        System.out.printf("%s\n", catalogPackage.getName());
                        System.out.printf("%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t  - \n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice());
                    } else {
                        System.out.printf("%s\n", catalogPackage.getName());
                        System.out.printf("%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
                    }
//                    if (catalogPackage.getDiscountRate() > 0) {
//                        flowerArrangementDiscounted.add(catalogPackage);
//                    }
                }
            }

            System.out.println("\nDiscounted Product List");
            System.out.println("================================================================================================");
            System.out.println("Product types\t\t\t\tQuantity\tPrice\t\t\tDiscounted price");
            if (freshFlowerDiscounted.getTotalEntries() != 0) {
                System.out.println("Fresh Flower");
                System.out.println("==================");
                for (int i = 1; i < freshFlowerDiscounted.getTotalEntries() + 1; i++) {
                    catalogPackage = freshFlowerDiscounted.getItem(i);
                    double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                    System.out.printf("%s\n", catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
                }
            }

            if (bouquetsDiscounted.getTotalEntries() != 0) {
                System.out.println("\nBouquets");
                System.out.println("==================");
                for (int i = 1; i < bouquetsDiscounted.getTotalEntries() + 1; i++) {
                    catalogPackage = bouquetsDiscounted.getItem(i);
                    double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                    System.out.printf("%s\n", catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
                }
            }

            if (flowerArrangementDiscounted.getTotalEntries() != 0) {
                System.out.println("\nFlower Arrangement");
                System.out.println("==================");
                for (int i = 1; i < flowerArrangementDiscounted.getTotalEntries() + 1; i++) {
                    catalogPackage = flowerArrangementDiscounted.getItem(i);
                    double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                    System.out.printf("%s\n", catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
                }
            }

            System.out.println("\n====================================================");
            char userOption;
            do {
                System.out.print("Please enter 'Y/y' to Manager menu: ");
                userOption = scan.next().charAt(0);
                System.out.println(userOption);

            } while (userOption != 'y' && userOption != 'Y');

            if (userOption == 'y' || userOption == 'Y') {
                FioreFlowershop.manager();
            }
        } else if (userMenuOption == 2) { //Display the monthly promotion catalog
            CatalogPackage catalogPackage = new CatalogPackage();
            //Displaying monthly promotion
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
                    System.out.printf("%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
                }
            }

            if (bouquetsDiscounted.getTotalEntries() != 0) {
                System.out.println("\nBouquets");
                System.out.println("==================");
                for (int i = 1; i < bouquetsDiscounted.getTotalEntries() + 1; i++) {
                    catalogPackage = bouquetsDiscounted.getItem(i);
                    double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                    System.out.printf("%s\n", catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
                }
            }

            if (flowerArrangementDiscounted.getTotalEntries() != 0) {
                System.out.println("\nFlower Arrangement");
                System.out.println("==================");
                for (int i = 1; i < flowerArrangementDiscounted.getTotalEntries() + 1; i++) {
                    catalogPackage = flowerArrangementDiscounted.getItem(i);
                    double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                    System.out.printf("%s\n", catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
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
                FioreFlowershop.manager();
            }
        }
    }
}
