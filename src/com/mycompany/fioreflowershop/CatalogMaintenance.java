/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.modal.CatalogPackage;
import com.mycompany.fioreflowershop.adt.CatalogPackageInterface;
import com.mycompany.fioreflowershop.adt.CatalogPackageList;
import com.mycompany.fioreflowershop.adt.LinkedList;
import com.mycompany.fioreflowershop.adt.ListIteratorInterface;
import com.mycompany.fioreflowershop.modal.Item;
import com.mycompany.fioreflowershop.modal.ItemCatalogue;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Woo Commit for add various type of product nothing changes with the
 * code commit of stock notification
 */
public class CatalogMaintenance {

    //Data field    
    static boolean isInteger;

    //create a scanner object to get user input
    static Scanner scan = new Scanner(System.in);

    //Catalog Maintenance Main Menu
    public static void catalogMaintenance(CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage, ItemCatalogue itemCatalogue) {
        System.out.println("\nPlease Select The Options Below.");
        System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[1]" + FioreFlowershop.ConsoleColors.RESET + " Add a product to catalog");
        System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[2]" + FioreFlowershop.ConsoleColors.RESET + " Remove a product from catalog");
        System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[3]" + FioreFlowershop.ConsoleColors.RESET + " Edit the details of product in catalog");
        System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[4]" + FioreFlowershop.ConsoleColors.RESET + " Display created catalog");
        System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[5]" + FioreFlowershop.ConsoleColors.RESET + " Back");
        System.out.print("Selection: ");

        try {
            int managerChoice = scan.nextInt();
            scan.nextLine();
            String navigationMsg;
            switch (managerChoice) {
                case 1:
                    navigationMsg = "Create catalog";
                    productType(navigationMsg, normalPackage, discountedPackage, itemCatalogue);
                    break;//Add product
                case 2:
                    navigationMsg = "Delete catalog";
                    displayCatalogType(navigationMsg, normalPackage, discountedPackage, "Manager", itemCatalogue);
                    break;//Delete product
                case 3:
                    navigationMsg = "Edit catalog";
                    displayCatalogType(navigationMsg, normalPackage, discountedPackage, "Manager", itemCatalogue);
                    break;//Edit Product
                case 4:
                    navigationMsg = "Display catalog";
                    displayCatalogType(navigationMsg, normalPackage, discountedPackage, "Manager", itemCatalogue);
                    break;//Display product  
                case 5:
                    FioreFlowershop.manager();
                    break;
            }
        } catch (Exception e) {
            System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + " An Error Occured. Please Only Enter Number Only." + FioreFlowershop.ConsoleColors.RESET);
            catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
        }
    }

    //Display product type
    public static void productType(String navigationTitle, CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage, ItemCatalogue itemCatalogue) {
        //Product type menu
        int productTypes = 0;
        do {
            System.out.println("\n" + navigationTitle);
            System.out.println("===================");
            System.out.println("Product type list\n" + FioreFlowershop.ConsoleColors.GREEN + "[1]" + FioreFlowershop.ConsoleColors.RESET
                    + " Fresh flowers\n" + FioreFlowershop.ConsoleColors.GREEN + "[2]" + FioreFlowershop.ConsoleColors.RESET + " Bouquets\n" + FioreFlowershop.ConsoleColors.GREEN + "[3]" + FioreFlowershop.ConsoleColors.RESET
                    + " Flower arrangement\n" + FioreFlowershop.ConsoleColors.GREEN + "[4]" + FioreFlowershop.ConsoleColors.RESET + " Back");
            if (productTypes < 0 || productTypes > 4) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number within the range of 1 - 4." + FioreFlowershop.ConsoleColors.RESET);
            }
            System.out.print("Please enter the product type(1-4): ");
            if (scan.hasNextInt()) {
                productTypes = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only." + FioreFlowershop.ConsoleColors.RESET);
                scan.next();
            }
        } while (!(isInteger) || productTypes < 1 || productTypes > 4);

        //Check for user option and take action base on user input
        if (productTypes == 4) {//Bring manager back to manager main menu
            catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
        } else if (navigationTitle.equals("Create catalog")) {//Perform the create product for catalog
            createCatalog(navigationTitle, productTypes, normalPackage, discountedPackage, itemCatalogue);
        }
    }

    //Select which catalog type
    public static void displayCatalogType(String navigationTitle, CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage, String staffType, ItemCatalogue itemCatalogue) {
        int userMenuOption = 1;
        do {
            System.out.println("\n" + navigationTitle);
            System.out.println("===================");
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[1]" + FioreFlowershop.ConsoleColors.RESET + " Catalog");
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[2]" + FioreFlowershop.ConsoleColors.RESET + " Monthly promotion catalog");
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[3]" + FioreFlowershop.ConsoleColors.RESET + " Back");
            System.out.print("Selection : ");
            if (userMenuOption < 1 || userMenuOption > 3) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number within 1 - 3." + FioreFlowershop.ConsoleColors.RESET);
            }
            if (scan.hasNextInt()) {
                userMenuOption = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only." + FioreFlowershop.ConsoleColors.RESET);
                scan.next();
            }
        } while (!(isInteger) || userMenuOption < 1 || userMenuOption > 3);

        if (userMenuOption == 3 && staffType.equals("Manager")) { // Back to Manager menu
            catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
        } else if (userMenuOption == 3 && staffType.equals("Inverntory clerk")) {
            FioreFlowershop.inventoryClerk();
        } else if (userMenuOption == 1 && navigationTitle.equals("Display catalog")) { //Display the normal catalog            
            displayCatalog(navigationTitle, normalPackage, discountedPackage, 1);
            scan.reset();
            backToCatalogMenu(normalPackage, discountedPackage, itemCatalogue);
        } else if (userMenuOption == 2 && navigationTitle.equals("Display catalog")) { //Display the monthly promotion catalog
            displayCatalog(navigationTitle, normalPackage, discountedPackage, 2);
            scan.reset();
            backToCatalogMenu(normalPackage, discountedPackage, itemCatalogue);
        }
        //else if (userMenuOption == 1 && navigationTitle.equals("Edit catalog")) { //Go to edit catalog(Normal catalog)
//            displayCatalog(navigationTitle, normalPackage, discountedPackage, 1);
//            editCatalog(navigationTitle, normalPackage, discountedPackage, 1);
//        } else if (userMenuOption == 2 && navigationTitle.equals("Edit catalog")) { //Go to edit catalog(monthly ptomotion catalog)
//            displayCatalog(navigationTitle, normalPackage, discountedPackage, 2);
//            editCatalog(navigationTitle, normalPackage, discountedPackage, 2);
//        } else if (userMenuOption == 1 && navigationTitle.equals("Delete catalog")) {
//            displayCatalog(navigationTitle, normalPackage, discountedPackage, 1);
//            deactiveCatalogItem(navigationTitle, normalPackage, discountedPackage, 1);
//        } else if (userMenuOption == 2 && navigationTitle.equals("Delete catalog")) {
//            displayCatalog(navigationTitle, normalPackage, discountedPackage, 2);
//            deactiveCatalogItem(navigationTitle, normalPackage, discountedPackage, 2);
//        } else if (navigationTitle.equals("Current stock")) {
//            displayStockAvailability(navigationTitle, normalPackage, discountedPackage, userMenuOption);
//        } else if (navigationTitle.equals("Restock quantity")) {
//            displayStockAvailability(navigationTitle, normalPackage, discountedPackage, userMenuOption);
//            restockQuantity(normalPackage, discountedPackage, userMenuOption);
//        }
    }

    //Create catalog(normal/ monthly promotion)
    public static void createCatalog(String navigationTitle, int productTypes, CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage, ItemCatalogue itemCatalogue) {
        char nextProduct = 'n', confirmationOption;
        String name, productTypesString = "", promoMonth = "";
        int style = 0, size = 0, flower = 0, accessory = 0, season = 0, flowerPot = 0;
        int discountRate = 0, promoYear = 0, month = 0, floralArrangementType = 0, flowerPotType = 0, existNameChecker = 0;
        boolean validInput, isDouble;
        double price = 0;

        ListIteratorInterface<Item> displayFlowers = new LinkedList<Item>();
        ListIteratorInterface<Item> selectedFlowers = new LinkedList<Item>();
        CatalogPackage catalogCheckingPackage = new CatalogPackage();

        do {
            scan.nextLine(); //To read the input, if no directly skip the first question
            System.out.println("\n" + navigationTitle);
            System.out.println("===================");

            do {
                validInput = true;
                existNameChecker = 0;
                System.out.print("Please enter package name: ");
                name = scan.nextLine();
                if (name == null || name.isEmpty()) {
                    validInput = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty\n" + FioreFlowershop.ConsoleColors.RESET);
                } else {
                    for (int i = 1; i < normalPackage.getTotalEntries() + 1; i++) {
                        catalogCheckingPackage = normalPackage.getProduct(i);
                        if (catalogCheckingPackage.getName().equals(name)) {
                            existNameChecker++;
                        }
                    }

                    for (int j = 1; j < discountedPackage.getTotalEntries() + 1; j++) {
                        catalogCheckingPackage = discountedPackage.getProduct(j);
                        if (catalogCheckingPackage.getName().equals(name)) {
                            existNameChecker++;
                        }
                    }

                    if (existNameChecker > 0) {
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "This name has been taken before please use another different name.\n" + FioreFlowershop.ConsoleColors.RESET);
                    }
                }
            } while (validInput == false || existNameChecker > 0);

            do {
                System.out.println("\nSelect the flower arrangement style");
                for (int i = 1; i <= itemCatalogue.getStyles().getSize(); i++) {
                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "[" + i + "]" + FioreFlowershop.ConsoleColors.RESET);
                    System.out.printf(" %s: RM%.2f\n", itemCatalogue.getStyles().getItem(i).getName(), itemCatalogue.getStyles().getItem(i).getPrice());
                }
                if (scan.hasNextInt()) {
                    style = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter a valid number" + FioreFlowershop.ConsoleColors.RESET);
                    scan.next();
                }
            } while (!(isInteger) || style < 1 || style > itemCatalogue.getStyles().getSize());

            //New update
            if (productTypes == 3) {
                do {
                    System.out.println("\nSelect the flower arrangement season");
                    for (int i = 1; i <= itemCatalogue.getSeason().getSize(); i++) {
                        System.out.print(FioreFlowershop.ConsoleColors.GREEN + "[" + i + "]" + FioreFlowershop.ConsoleColors.RESET);
                        System.out.printf(" %s\n", itemCatalogue.getSeason().getItem(i));
                    }
                    if (scan.hasNextInt()) {
                        season = scan.nextInt();
                        isInteger = true;
                    } else {
                        isInteger = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter a valid number" + FioreFlowershop.ConsoleColors.RESET);
                        scan.next();
                    }
                } while (!(isInteger) || season < 1 || season > itemCatalogue.getSeason().getSize());

                do {
                    System.out.println("\nSelect the flower arrangement pot");
                    for (int i = 1; i <= itemCatalogue.getFlowerPot().getSize(); i++) {
                        System.out.print(FioreFlowershop.ConsoleColors.GREEN + "[" + i + "]" + FioreFlowershop.ConsoleColors.RESET);
                        System.out.printf(" %s: RM%.2f\n", itemCatalogue.getFlowerPot().getItem(i).getName(), itemCatalogue.getFlowerPot().getItem(i).getPrice());
                    }
                    if (scan.hasNextInt()) {
                        flowerPot = scan.nextInt();
                        isInteger = true;
                    } else {
                        isInteger = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter a valid number" + FioreFlowershop.ConsoleColors.RESET);
                        scan.next();
                    }
                } while (!(isInteger) || flowerPot < 1 || flowerPot > itemCatalogue.getFlowerPot().getSize());
            }

            do {
                System.out.println("\nSelect the floral arrangement size");
                System.out.println("This will be multiplied by the selected flower's price");
                for (int i = 1; i <= itemCatalogue.getSizes().getSize(); i++) {
                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "[" + i + "]" + FioreFlowershop.ConsoleColors.RESET);
                    System.out.printf(" %s: Flower Price x %.0f\n", itemCatalogue.getSizes().getItem(i).getName(), itemCatalogue.getSizes().getItem(i).getPrice());
                }
                if (scan.hasNextInt()) {
                    size = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter a valid number" + FioreFlowershop.ConsoleColors.RESET);
                    scan.next();
                }
            } while (!(isInteger) || size < 1 || size > itemCatalogue.getSizes().getSize());

            scan.nextLine();

            for (int i = 1; i <= itemCatalogue.getFlowers().getSize(); i++) {
                displayFlowers.add(itemCatalogue.getFlowers().getItem(i));
            }
            boolean flowerSelected = false;

            char selection;
            do {
                do {
                    System.out.println("\nSelect the flowers for the arrangement");
                    for (int i = 1; i <= displayFlowers.getTotalEntries(); i++) {
                        System.out.print(FioreFlowershop.ConsoleColors.GREEN + "[" + i + "]" + FioreFlowershop.ConsoleColors.RESET);
                        System.out.printf(" %s: RM%.2f\n", displayFlowers.getItem(i).getName(), displayFlowers.getItem(i).getPrice());
                    }
                    if (scan.hasNextInt()) {
                        flower = scan.nextInt();
                        isInteger = true;
                    } else {
                        isInteger = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter a valid number" + FioreFlowershop.ConsoleColors.RESET);
                        scan.next();
                    }
                } while (!(isInteger) || flower < 1 || flower > displayFlowers.getTotalEntries());

                selectedFlowers.add(displayFlowers.getItem(flower));
                displayFlowers.remove(flower);

                if (displayFlowers.isEmpty()) {
                    break;
                }

                System.out.print("Add Another Flower?" + FioreFlowershop.ConsoleColors.GREEN + "[Y/N]" + FioreFlowershop.ConsoleColors.RESET + " ");
                selection = Character.toUpperCase(scan.next().charAt(0));
                scan.nextLine();
                System.out.println();
            } while (selection != 'Y' && selection != 'N' || selection == 'Y');

            do {
                System.out.println("\nSelect the accessory to be added");
                for (int i = 1; i <= itemCatalogue.getAccessories().getSize(); i++) {
                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "[" + i + "]" + FioreFlowershop.ConsoleColors.RESET);
                    System.out.printf(" %s: RM%.2f\n", itemCatalogue.getAccessories().getItem(i).getName(), itemCatalogue.getAccessories().getItem(i).getPrice());
                }
                if (scan.hasNextInt()) {
                    accessory = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter a valid number" + FioreFlowershop.ConsoleColors.RESET);
                    scan.next();
                }
            } while (!(isInteger) || accessory < 1 || accessory > itemCatalogue.getAccessories().getSize());

            //Ask user to add the package into the monthly promotion catalog ?
            System.out.print(FioreFlowershop.ConsoleColors.BLUE + "\nDo you wish add this package into Monthly Promotion Catalog?" + FioreFlowershop.ConsoleColors.RESET);
            System.out.print("\nPress 'y/Y' to add in, other key to cancel: ");
            confirmationOption = scan.next().charAt(0);

            if (confirmationOption == 'y' || confirmationOption == 'Y') {
                do {
                    System.out.print("\nPlease enter the year: ");
                    if (scan.hasNextInt()) {
                        promoYear = scan.nextInt();
                        isInteger = true;
                    } else {
                        isInteger = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.\n" + FioreFlowershop.ConsoleColors.RESET);
                        scan.next();
                    }
                } while (!(isInteger) || promoYear < 2018 || promoYear > 3000);

                do {
                    System.out.print("Please enter the month(1 - 12): ");
                    if (scan.hasNextInt()) {
                        month = scan.nextInt();
                        isInteger = true;
                    } else {
                        isInteger = false;
                        System.out.print(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.\n" + FioreFlowershop.ConsoleColors.RESET);
                        scan.next();
                    }
                } while (!(isInteger) || month < 1 || month > 12);

                do {
                    System.out.print("Please enter the discount rate for monthly promotion(1 - 99%): ");
                    if (scan.hasNextInt()) {
                        discountRate = scan.nextInt();
                        isInteger = true;
                    } else {
                        isInteger = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.\n" + FioreFlowershop.ConsoleColors.RESET);
                        scan.next();
                    }
                } while (!(isInteger) || discountRate <= 0 || discountRate > 99);
            }

            //Convert the number to string
            if (productTypes == 1) {
                productTypesString = "Fresh flower";
            } else if (productTypes == 2) {
                productTypesString = "Bouquets";
            } else if (productTypes == 3) {
                productTypesString = "Flower arrangement";
            }

            if (month == 1) {
                promoMonth = "January";
            } else if (month == 2) {
                promoMonth = "February";
            } else if (month == 3) {
                promoMonth = "March";
            } else if (month == 4) {
                promoMonth = "April";
            } else if (month == 5) {
                promoMonth = "May";
            } else if (month == 6) {
                promoMonth = "June";
            } else if (month == 7) {
                promoMonth = "July";
            } else if (month == 8) {
                promoMonth = "August";
            } else if (month == 9) {
                promoMonth = "September";
            } else if (month == 10) {
                promoMonth = "October";
            } else if (month == 11) {
                promoMonth = "November";
            } else if (month == 12) {
                promoMonth = "December";
            }

            //String name, Item style, Item size, Item accessory, String productType, String promoMonth, int promoYear, double price, int discountRate, String status
            CatalogPackage catalogPackage = new CatalogPackage();
            if (productTypes == 1 || productTypes == 2) {
                double flowerPrice = 0.00;
                for (int i = 1; i < selectedFlowers.getTotalEntries() + 1; i++) {
                    flowerPrice += selectedFlowers.getItem(i).getPrice();
                }
                price = itemCatalogue.getSizes().getItem(size).getPrice() * (itemCatalogue.getStyles().getItem(style).getPrice() + itemCatalogue.getAccessories().getItem(accessory).getPrice() + flowerPrice);

                catalogPackage = new CatalogPackage(name, itemCatalogue.getStyles().getItem(style), itemCatalogue.getSizes().getItem(size), itemCatalogue.getAccessories().getItem(accessory), productTypesString, promoMonth, promoYear, price, discountRate, "Active");
                while (!selectedFlowers.isEmpty()) {
                    catalogPackage.getFlowerList().add(selectedFlowers.getItem(1));
                    selectedFlowers.remove(1);
                }
            } else if (productTypes == 3) {
                //String name, Item style, Item size, String season, Item flowerPot, Item accessory, String productType, String promoMonth, int promoYear, double price, int discountRate, String status
                double flowerPrice = 0.00;
                for (int i = 1; i < selectedFlowers.getTotalEntries() + 1; i++) {
                    flowerPrice += selectedFlowers.getItem(i).getPrice();
                }
                price = itemCatalogue.getSizes().getItem(size).getPrice() * (itemCatalogue.getStyles().getItem(style).getPrice() + itemCatalogue.getFlowerPot().getItem(flowerPot).getPrice() + itemCatalogue.getAccessories().getItem(accessory).getPrice() + flowerPrice);

                catalogPackage = new CatalogPackage(name, itemCatalogue.getStyles().getItem(style), itemCatalogue.getSizes().getItem(size), itemCatalogue.getSeason().getItem(season), itemCatalogue.getFlowerPot().getItem(flowerPot), itemCatalogue.getAccessories().getItem(accessory), productTypesString, promoMonth, promoYear, price, discountRate, "Active");
                while (!selectedFlowers.isEmpty()) {
                    catalogPackage.getFlowerList().add(selectedFlowers.getItem(1));
                    selectedFlowers.remove(1);
                }
            }

            if (discountRate > 0) {
                discountedPackage.addProduct(catalogPackage);
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "\nThe product had been added into Monthly Promotion Catalog..." + FioreFlowershop.ConsoleColors.RESET);
            } else {
                normalPackage.addProduct(catalogPackage);
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "\nThe product had been added into Catalog..." + FioreFlowershop.ConsoleColors.RESET);
            }

            displayFlowers.clear();
            System.out.println(FioreFlowershop.ConsoleColors.BLUE + "\nDo you wish to add another product?" + FioreFlowershop.ConsoleColors.RESET);
            System.out.print("Enter Y/y for continue, enter any key for cancel: ");
            nextProduct = scan.next().charAt(0);
        } while (nextProduct == 'Y' || nextProduct == 'y');

        if (nextProduct != 'y' || nextProduct != 'Y') {
            catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
        }
    }

    //Display normal or monthly promotion catalog
    public static void displayCatalog(String navigationTitle, CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage, int catalogTypes) {
        int freshFlowerCounter = 0, bouquetsCounter = 0, flowerArrangementCounter = 0, discountFreshFlowerCounter = 0, discountBouquetsCounter = 0, discountFlowerArrangementCounter = 0;
        CatalogPackage catalogPackage = new CatalogPackage();
        CatalogPackageInterface<CatalogPackage> temporalyPackage = new CatalogPackageList<>();

        //Get current month & year
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        String curentMonthString = "";

        //Convert the month integer to String
        if (month + 1 == 1) {
            curentMonthString = "January";
        } else if (month + 1 == 2) {
            curentMonthString = "February";
        } else if (month + 1 == 3) {
            curentMonthString = "March";
        } else if (month + 1 == 4) {
            curentMonthString = "April";
        } else if (month + 1 == 5) {
            curentMonthString = "May";
        } else if (month + 1 == 6) {
            curentMonthString = "June";
        } else if (month + 1 == 7) {
            curentMonthString = "July";
        } else if (month + 1 == 8) {
            curentMonthString = "August";
        } else if (month + 1 == 9) {
            curentMonthString = "September";
        } else if (month + 1 == 10) {
            curentMonthString = "October";
        } else if (month + 1 == 11) {
            curentMonthString = "November";
        } else if (month + 1 == 12) {
            curentMonthString = "December";
        }
        if (catalogTypes == 1) {
            temporalyPackage = normalPackage;
            System.out.println("\nDisplay catalog - Normal Catalog");
            System.out.println("================================");
        } else if (catalogTypes == 2) {
            temporalyPackage = discountedPackage;
            System.out.println("\nDisplay catalog - Monthly promotion catalog" + "( " + year + " - " + curentMonthString + " )");
            System.out.println("==============================================================");
        }
        
        if (temporalyPackage.getTotalEntries() != 0) {
            String normalFreshFlowerList = "";
            String discountFreshFlowerList = "";
            String normalBouquetsList = "";
            String discountBouquetsList = "";
            String normalflowerArrangementList = "";
            String discountflowerArrangementList = "";
            System.out.println("----------------");
            System.out.println("- Fresh Flower -");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("| No | | Package Name |\t|       Style,Size,Flower,Season,FlowerPot,Accesories       |\t\t|     Price     |\t| Discounted Price |\t|   Status   |");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (int i = 1; i < temporalyPackage.getTotalEntries() + 1; i++) {
                catalogPackage = temporalyPackage.getProduct(i);
                if (catalogPackage.getProductType().equals("Fresh flower") && catalogPackage.getStatus().equals("Active") && catalogTypes == 1) {
                    for (int j = 1; j < catalogPackage.getFlowerList().getTotalEntries() + 1; j++) {
                        normalFreshFlowerList += catalogPackage.getFlowerList().getItem(j).getName() + " ";
                    }
                    System.out.printf(FioreFlowershop.ConsoleColors.GREEN + " [%d]" + FioreFlowershop.ConsoleColors.RESET, i);
                    System.out.printf("\t %-10s", catalogPackage.getName());
                    System.out.printf("\t  %s,%s,%s,%s %-30s RM%.2f %20s - %14s %s\n", catalogPackage.getStyle().getName(), catalogPackage.getSize().getName(), normalFreshFlowerList, catalogPackage.getAccessory().getName(), " ", catalogPackage.getPrice(), " ", " ", catalogPackage.getStatus());
                    freshFlowerCounter++;
                    normalFreshFlowerList = "";
                } else if (catalogPackage.getProductType().equals("Fresh flower") && catalogPackage.getPromoMonth().equals(curentMonthString) && catalogPackage.getPromoYear() == year && catalogPackage.getStatus().equals("Active") && catalogTypes == 2) {
                    for (int j = 1; j < catalogPackage.getFlowerList().getTotalEntries() + 1; j++) {
                        discountFreshFlowerList += catalogPackage.getFlowerList().getItem(j).getName() + " ";
                    }
                    double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                    System.out.printf(FioreFlowershop.ConsoleColors.GREEN + " [%d]" + FioreFlowershop.ConsoleColors.RESET, i);
                    System.out.printf("\t %-10s", catalogPackage.getName());
                    System.out.printf("\t  %s,%s,%s,%s %-30s RM%.2f %20s RM%.2f %14s %s\n", catalogPackage.getStyle().getName(), catalogPackage.getSize().getName(), discountFreshFlowerList, catalogPackage.getAccessory().getName(), " ", catalogPackage.getPrice(), " ", discountRate, " ", catalogPackage.getStatus());
                    discountFreshFlowerCounter++;
                    discountFreshFlowerList = "";
                }
            }

            if (freshFlowerCounter == 0 && catalogTypes == 1) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "There are no record found\n" + FioreFlowershop.ConsoleColors.RESET);
            } else if (discountFreshFlowerCounter == 0 && catalogTypes == 2) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "There are no record found\n" + FioreFlowershop.ConsoleColors.RESET);
            }

            System.out.println("\n\n------------");
            System.out.println("- Bouquets -");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("| No | | Package Name |\t|       Style,Size,Flower,Season,FlowerPot,Accesories       |\t\t|     Price     |\t| Discounted Price |\t|   Status   |");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (int i = 1; i < temporalyPackage.getTotalEntries() + 1; i++) {
                catalogPackage = temporalyPackage.getProduct(i);
                if (catalogPackage.getProductType().equals("Bouquets") && catalogPackage.getStatus().equals("Active") && catalogTypes == 1) {
                    for (int j = 1; j < catalogPackage.getFlowerList().getTotalEntries() + 1; j++) {
                        normalBouquetsList += catalogPackage.getFlowerList().getItem(j).getName() + " ";
                    }
                    System.out.printf(FioreFlowershop.ConsoleColors.GREEN + " [%d]" + FioreFlowershop.ConsoleColors.RESET, i);
                    System.out.printf("\t %-10s", catalogPackage.getName());
                    System.out.printf("\t  %s,%s,%s,%s %-30s RM%.2f %20s - %14s %s\n", catalogPackage.getStyle().getName(), catalogPackage.getSize().getName(), normalBouquetsList, catalogPackage.getAccessory().getName(), " ", catalogPackage.getPrice(), " ", " ", catalogPackage.getStatus());
                    bouquetsCounter++;
                    normalBouquetsList = "";
                } else if (catalogPackage.getProductType().equals("Bouquets") && catalogPackage.getPromoMonth().equals(curentMonthString) && catalogPackage.getPromoYear() == year && catalogPackage.getStatus().equals("Active") && catalogTypes == 2) {
                    for (int j = 1; j < catalogPackage.getFlowerList().getTotalEntries() + 1; j++) {
                        discountBouquetsList += catalogPackage.getFlowerList().getItem(j).getName() + " ";
                    }                    
                    double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                    System.out.printf(FioreFlowershop.ConsoleColors.GREEN + " [%d]" + FioreFlowershop.ConsoleColors.RESET, i);
                    System.out.printf("\t %-10s", catalogPackage.getName());
                    System.out.printf("\t  %s,%s,%s,%s %-30s RM%.2f %20s RM%.2f %14s %s\n", catalogPackage.getStyle().getName(), catalogPackage.getSize().getName(), discountBouquetsList, catalogPackage.getAccessory().getName(), " ", catalogPackage.getPrice(), " ", discountRate, " ", catalogPackage.getStatus());
                    discountBouquetsCounter++;
                    discountBouquetsList = "";
                }
            }

            if (bouquetsCounter == 0 && catalogTypes == 1) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "There are no record found\n" + FioreFlowershop.ConsoleColors.RESET);
            } else if (discountBouquetsCounter == 0 && catalogTypes == 2) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "There are no record found\n" + FioreFlowershop.ConsoleColors.RESET);
            }

            System.out.println("\n\n-----------------------");
            System.out.println("- Flower arrangement -");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("| No | | Package Name |\t|       Style,Size,Flower,Season,FlowerPot,Accesories       |\t\t|     Price     |\t| Discounted Price |\t|   Status   |");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (int i = 1; i < temporalyPackage.getTotalEntries() + 1; i++) {
                catalogPackage = temporalyPackage.getProduct(i);
                if (catalogPackage.getProductType().equals("Flower arrangement") && catalogPackage.getStatus().equals("Active") && catalogTypes == 1) {
                    for (int j = 1; j < catalogPackage.getFlowerList().getTotalEntries() + 1; j++) {
                        normalflowerArrangementList += catalogPackage.getFlowerList().getItem(j).getName() + " ";
                    }
                    System.out.printf(FioreFlowershop.ConsoleColors.GREEN + " [%d]" + FioreFlowershop.ConsoleColors.RESET, i);
                    System.out.printf(" %-10s", catalogPackage.getName());
                    System.out.printf("\t  %s,%s,%s,%s,%s,%s %-30s RM%.2f %20s - %14s %s\n", catalogPackage.getStyle().getName(), catalogPackage.getSize().getName(), normalflowerArrangementList, catalogPackage.getSeason(),catalogPackage.getFlowerPot().getName(),catalogPackage.getAccessory().getName(), " ", catalogPackage.getPrice(), " ", " ", catalogPackage.getStatus());
                    flowerArrangementCounter++;
                } else if (catalogPackage.getProductType().equals("Flower arrangement") && catalogPackage.getPromoMonth().equals(curentMonthString) && catalogPackage.getPromoYear() == year && catalogPackage.getStatus().equals("Active") && catalogTypes == 2) {
                    for (int j = 1; j < catalogPackage.getFlowerList().getTotalEntries() + 1; j++) {
                        discountflowerArrangementList += catalogPackage.getFlowerList().getItem(j).getName() + " ";
                    }
                    double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                    System.out.printf(FioreFlowershop.ConsoleColors.GREEN + " [%d]" + FioreFlowershop.ConsoleColors.RESET, i);
                    System.out.printf(" %s", catalogPackage.getName());
                    System.out.printf("\t  %s,%s,%s,%s,%s,%s %-30s RM%.2f %20s RM%.2f %14s %s\n", catalogPackage.getStyle().getName(), catalogPackage.getSize().getName(), discountflowerArrangementList, catalogPackage.getSeason(),catalogPackage.getFlowerPot().getName(), catalogPackage.getAccessory().getName(), " ", catalogPackage.getPrice(), " ", discountRate, " ", catalogPackage.getStatus());
                    discountFlowerArrangementCounter++;
                }
            }

            if (flowerArrangementCounter == 0 && catalogTypes == 1) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "There are no record found\n" + FioreFlowershop.ConsoleColors.RESET);
            } else if (discountFlowerArrangementCounter == 0 && catalogTypes == 2) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "There are no record found\n" + FioreFlowershop.ConsoleColors.RESET);
            }
        }
    }

//    //Display one record only for normal or monthly promotion catalog
//    public static void displayEditResult(CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage, int catalogTypes, int productNumber) {
//        CatalogPackage catalogPackage = new CatalogPackage();
//        CatalogPackageInterface<CatalogPackage> temporalyPackage = new CatalogPackageList<>();
//
//        if (catalogTypes == 1) {
//            temporalyPackage = normalPackage;
//            System.out.println("Display catalog - Normal Catalog");
//        } else if (catalogTypes == 2) {
//            temporalyPackage = discountedPackage;
//            catalogPackage = temporalyPackage.getProduct(productNumber);
//            System.out.println("Display catalog - Monthly promotion catalog" + "( " + catalogPackage.getPromoYear() + " - " + catalogPackage.getPromoMonth() + " )");
//        }
//        catalogPackage = temporalyPackage.getProduct(productNumber);
//        System.out.println("================================================================================================");
//        System.out.println("Product types\t\t\t\tQuantity\tPrice\t\t\tDiscounted price");
//        if (temporalyPackage.getTotalEntries() != 0) {
//            System.out.println(catalogPackage.getProductType());
//            System.out.println("==================");
//            if (catalogTypes == 1 && !catalogPackage.getProductType().equals("Flower arrangement")) {
//                System.out.printf(" %s\n", catalogPackage.getName());
//                System.out.printf("%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t  - \n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice());
//            } else if (catalogTypes == 2 && !catalogPackage.getProductType().equals("Flower arrangement")) {
//                double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
//                System.out.printf(" %s\n", catalogPackage.getName());
//                System.out.printf("%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
//            } else if (catalogTypes == 1 && catalogPackage.getProductType().equals("Flower arrangement")) {
//                System.out.printf(" %s\n", catalogPackage.getName());
//                System.out.printf("%s,%s,%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t  - \n\n", catalogPackage.getStyle(), catalogPackage.getFloralArrangement(), catalogPackage.getFlowerPot(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice());
//            } else if (catalogTypes == 2 && catalogPackage.getProductType().equals("Flower arrangement")) {
//                double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
//                System.out.printf(" %s\n", catalogPackage.getName());
//                System.out.printf("%s,%s,%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getFloralArrangement(), catalogPackage.getFlowerPot(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
//            }
//        }
//    }
//
//    //Edit Catalog
//    public static void editCatalog(String navigationTitle, CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage, int catalogTypes) {
//        //Data decleration
//        int productNumber = 0, catalogSize = 0, productField = 0, sizeOption = 0, productTypes = 0, quantity = 0, discountRate = 0, promoYear = 0, month = 0, minRange = 1, maxRange = 0,
//                floralArrangementType = 0, flowerPotType = 0;
//        String name = "", style = "", flower = "", acessories = "", size = "", promoMonth = "";
//        boolean validInput, isDouble, editAllCatalog = false, editAllMonthlyCatalog = false;
//        double price = 0;
//
//        //Check for normal catalog size or promotion catalog size
//        if (catalogTypes == 1) {
//            catalogSize = normalPackage.getTotalEntries();
//            maxRange = 10;
//        } else if (catalogTypes == 2) {
//            maxRange = 13;
//            catalogSize = discountedPackage.getTotalEntries();
//        }
//
//        CatalogPackage catalogPackage = new CatalogPackage();
//
//        //Ask user to choose which product need to be edit
//        do {
//            System.out.print("Please enter the number(" + minRange + " - " + catalogSize + "): ");
//            if (scan.hasNextInt()) {
//                productNumber = scan.nextInt();
//                isInteger = true;
//            } else {
//                isInteger = false;
//                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.\n" + FioreFlowershop.ConsoleColors.RESET);
//                scan.next();
//            }
//        } while (!(isInteger) || productNumber < 1 || productNumber > catalogSize);
//
//        //Edit each of the product field(Normal Catalog Editor)
//        do {
//            if (catalogTypes == 1) {
//                System.out.print("\nWhich part of the product you wish to edit?\n"
//                        + FioreFlowershop.ConsoleColors.GREEN + "[1]" + FioreFlowershop.ConsoleColors.RESET + " Name\n" + FioreFlowershop.ConsoleColors.GREEN + "[2]" + FioreFlowershop.ConsoleColors.RESET + " Style\n" + FioreFlowershop.ConsoleColors.GREEN + "[3]" + FioreFlowershop.ConsoleColors.RESET + " Size\n"
//                        + FioreFlowershop.ConsoleColors.GREEN + "[4]" + FioreFlowershop.ConsoleColors.RESET + " Flower\n" + FioreFlowershop.ConsoleColors.GREEN + "[5]" + FioreFlowershop.ConsoleColors.RESET + " Accessory\n" + FioreFlowershop.ConsoleColors.GREEN + "[6]" + FioreFlowershop.ConsoleColors.RESET + " Product type\n"
//                        + FioreFlowershop.ConsoleColors.GREEN + "[7]" + FioreFlowershop.ConsoleColors.RESET + " Quantity\n" + FioreFlowershop.ConsoleColors.GREEN + "[8]" + FioreFlowershop.ConsoleColors.RESET + " Price\n" + FioreFlowershop.ConsoleColors.GREEN + "[9]" + FioreFlowershop.ConsoleColors.RESET + " All\n" + FioreFlowershop.ConsoleColors.GREEN + "[10]" + FioreFlowershop.ConsoleColors.RESET + " Cancel\n");
//            } else if (catalogTypes == 2) {
//                System.out.print("\nWhich part of the product you wish to edit?\n"
//                        + FioreFlowershop.ConsoleColors.GREEN + "[1]" + FioreFlowershop.ConsoleColors.RESET + " Name\n" + FioreFlowershop.ConsoleColors.GREEN + "[2]" + FioreFlowershop.ConsoleColors.RESET + " Style\n" + FioreFlowershop.ConsoleColors.GREEN + "[3]" + FioreFlowershop.ConsoleColors.RESET + " Size\n"
//                        + FioreFlowershop.ConsoleColors.GREEN + "[4]" + FioreFlowershop.ConsoleColors.RESET + " Flower\n" + FioreFlowershop.ConsoleColors.GREEN + "[5]" + FioreFlowershop.ConsoleColors.RESET + " Accessory\n" + FioreFlowershop.ConsoleColors.GREEN + "[6]" + FioreFlowershop.ConsoleColors.RESET + " Product type\n" + FioreFlowershop.ConsoleColors.GREEN
//                        + "[7]" + FioreFlowershop.ConsoleColors.RESET + " Promotion month\n" + FioreFlowershop.ConsoleColors.GREEN + "[8]" + FioreFlowershop.ConsoleColors.RESET + " Promotion year\n" + FioreFlowershop.ConsoleColors.GREEN + "[9]" + FioreFlowershop.ConsoleColors.RESET + " Quantity\n" + FioreFlowershop.ConsoleColors.GREEN + "[10]" + FioreFlowershop.ConsoleColors.RESET
//                        + " Price\n" + FioreFlowershop.ConsoleColors.GREEN + "[11]" + FioreFlowershop.ConsoleColors.RESET + " Discount rate\n" + FioreFlowershop.ConsoleColors.GREEN + "[12]" + FioreFlowershop.ConsoleColors.RESET + " All\n" + FioreFlowershop.ConsoleColors.GREEN + "[13]" + FioreFlowershop.ConsoleColors.RESET + " Cancel\n");
//            }
//
//            if (scan.hasNextInt()) {
//                productField = scan.nextInt();
//                isInteger = true;
//            } else {
//                isInteger = false;
//                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number (" + minRange + " - " + maxRange + ") only.\n" + FioreFlowershop.ConsoleColors.RESET);
//                scan.next();
//            }
//        } while (!(isInteger) || productField < minRange || productField > maxRange);
//
//        if (productField == 9 && catalogTypes == 1) {
//            editAllCatalog = true;
//        }
//        if (productField == 12 && catalogTypes == 2) {
//            editAllMonthlyCatalog = true;
//        }
//
//        if (productField == 1 || editAllCatalog == true || editAllMonthlyCatalog == true) {
//            scan.nextLine(); //To read the input, if no directly skip the first question
//            do {
//                validInput = true;
//                System.out.print("Please enter new package name: ");
//                name = scan.nextLine();
//                if (name == null || name.isEmpty()) {
//                    validInput = false;
//                    System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty\n" + FioreFlowershop.ConsoleColors.RESET);
//                }
//            } while (validInput == false);
//
//            if (catalogTypes == 1) {
//                catalogPackage = normalPackage.getProduct(productNumber);
//                if (editAllCatalog == false) {
//                    System.out.println("\nBefore: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    catalogPackage.setName(name);
//                    System.out.println("\nAfter: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//                    catalogMaintenance(normalPackage, discountedPackage);
//                }
//            } else if (catalogTypes == 2) {
//                catalogPackage = discountedPackage.getProduct(productNumber);
//                if (editAllMonthlyCatalog == false) {
//                    System.out.println("\nBefore: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    catalogPackage.setName(name);
//                    System.out.println("\nAfter: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//                    catalogMaintenance(normalPackage, discountedPackage);
//                }
//            }
//        }
//
//        scan.nextLine();
//
//        if (productField == 2 || editAllCatalog == true || editAllMonthlyCatalog == true) {
//            do {
//                validInput = true;
//                System.out.print("Please enter new style: ");
//                style = scan.nextLine();
//                if (style == null || style.isEmpty()) {
//                    validInput = false;
//                    System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty\n" + FioreFlowershop.ConsoleColors.RESET);
//                }
//            } while (validInput == false);
//
//            if (catalogTypes == 1) {
//                catalogPackage = normalPackage.getProduct(productNumber);
//                if (editAllCatalog == false) {
//                    System.out.println("\nBefore: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    catalogPackage.setStyle(style);
//                    System.out.println("\nAfter: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//                    catalogMaintenance(normalPackage, discountedPackage);
//                }
//            } else if (catalogTypes == 2) {
//                catalogPackage = discountedPackage.getProduct(productNumber);
//                if (editAllMonthlyCatalog == false) {
//                    System.out.println("\nBefore: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    catalogPackage.setStyle(style);
//                    System.out.println("\nAfter: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//                    catalogMaintenance(normalPackage, discountedPackage);
//                }
//            }
//        }
//
//        if (productField == 3 || editAllCatalog == true || editAllMonthlyCatalog == true) {
//            do {
//                System.out.print("Please enter new size (1 = Small, 2 = Medium, 3 = Large): ");
//                if (scan.hasNextInt()) {
//                    sizeOption = scan.nextInt();
//                    isInteger = true;
//                } else {
//                    isInteger = false;
//                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.\n" + FioreFlowershop.ConsoleColors.RESET);
//                    scan.next();
//                }
//            } while (!(isInteger) || sizeOption < 1 || sizeOption > 3);
//
//            if (sizeOption == 1) {
//                size = "Small";
//            } else if (sizeOption == 2) {
//                size = "Medium";
//            } else if (sizeOption == 3) {
//                size = "Large";
//            }
//
//            if (catalogTypes == 1) {
//                catalogPackage = normalPackage.getProduct(productNumber);
//                if (editAllCatalog == false) {
//                    System.out.println("\nBefore: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    catalogPackage.setSize(size);
//                    System.out.println("\nAfter: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//                    catalogMaintenance(normalPackage, discountedPackage);
//                }
//            } else if (catalogTypes == 2) {
//                catalogPackage = discountedPackage.getProduct(productNumber);
//                if (editAllMonthlyCatalog == false) {
//                    System.out.println("\nBefore: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    catalogPackage.setSize(size);
//                    System.out.println("\nAfter: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//                    catalogMaintenance(normalPackage, discountedPackage);
//                }
//            }
//        }
//
//        if (productField == 4 || editAllCatalog == true || editAllMonthlyCatalog == true) {
//            scan.nextLine(); // To read next input
//            do {
//                validInput = true;
//                System.out.print("Please enter new flower: ");
//                flower = scan.nextLine();
//
//                if (flower == null || flower.isEmpty()) {
//                    validInput = false;
//                    System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty\n" + FioreFlowershop.ConsoleColors.RESET);
//                }
//            } while (validInput == false);
//
//            if (catalogTypes == 1) {
//                catalogPackage = normalPackage.getProduct(productNumber);
//                if (editAllCatalog == false) {
//                    System.out.println("\nBefore: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    catalogPackage.setFlower(flower);
//                    System.out.println("\nAfter: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//                    catalogMaintenance(normalPackage, discountedPackage);
//                }
//            } else if (catalogTypes == 2) {
//                catalogPackage = discountedPackage.getProduct(productNumber);
//                if (editAllMonthlyCatalog == false) {
//                    System.out.println("\nBefore: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    catalogPackage.setFlower(flower);
//                    System.out.println("\nAfter: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//                    catalogMaintenance(normalPackage, discountedPackage);
//                }
//            }
//
//        }
//
//        if (productField == 5 || editAllCatalog == true || editAllMonthlyCatalog == true) {
//            if (catalogTypes == 2) {
//                scan.nextLine();
//            }
//            do {
//                validInput = true;
//                System.out.print("Please enter new acessories: ");
//                acessories = scan.nextLine();
//                if (acessories == null || acessories.isEmpty()) {
//                    validInput = false;
//                    System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty\n" + FioreFlowershop.ConsoleColors.RESET);
//                }
//            } while (validInput == false);
//
//            if (catalogTypes == 1) {
//                catalogPackage = normalPackage.getProduct(productNumber);
//                if (editAllCatalog == false) {
//                    System.out.println("\nBefore: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    catalogPackage.setAccessory(acessories);
//                    System.out.println("\nAfter: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//                    catalogMaintenance(normalPackage, discountedPackage);
//                }
//            } else if (catalogTypes == 2) {
//                catalogPackage = discountedPackage.getProduct(productNumber);
//                if (editAllMonthlyCatalog == false) {
//                    System.out.println("\nBefore: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    catalogPackage.setAccessory(acessories);
//                    System.out.println("\nAfter: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//                    catalogMaintenance(normalPackage, discountedPackage);
//                }
//            }
//        }
//
//        if (productField == 6 || editAllCatalog == true || editAllMonthlyCatalog == true) {
//            do {
//                System.out.println("Product type list\n" + FioreFlowershop.ConsoleColors.GREEN + "[1]" + FioreFlowershop.ConsoleColors.RESET
//                        + " Fresh flowers\n" + FioreFlowershop.ConsoleColors.GREEN + "[2]" + FioreFlowershop.ConsoleColors.RESET + " Bouquets\n" + FioreFlowershop.ConsoleColors.GREEN + "[3]" + FioreFlowershop.ConsoleColors.RESET
//                        + " Flower arrangement");
//                System.out.print("Please enter the product type(1-3): ");
//                if (scan.hasNextInt()) {
//                    productTypes = scan.nextInt();
//                    isInteger = true;
//                } else {
//                    isInteger = false;
//                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.\n" + FioreFlowershop.ConsoleColors.RESET);
//                    scan.next();
//                }
//            } while (!(isInteger) || productTypes < 1 || productTypes > 3);
//
//            if (productTypes == 3) {
//                do {
//                    System.out.println("\nFloral arrangment list:\n" + FioreFlowershop.ConsoleColors.GREEN + "[1]" + FioreFlowershop.ConsoleColors.RESET
//                            + " Valentine\n" + FioreFlowershop.ConsoleColors.GREEN + "[2]" + FioreFlowershop.ConsoleColors.RESET + " Graduation\n" + FioreFlowershop.ConsoleColors.GREEN + "[3]" + FioreFlowershop.ConsoleColors.RESET
//                            + " Grand opening\n" + FioreFlowershop.ConsoleColors.GREEN + "[4]" + FioreFlowershop.ConsoleColors.RESET + " Visit patient\n" + FioreFlowershop.ConsoleColors.GREEN + "[5]" + FioreFlowershop.ConsoleColors.RESET + " Funeral");
//                    System.out.print("Please enter a number: ");
//                    if (scan.hasNextInt()) {
//                        floralArrangementType = scan.nextInt();
//                        isInteger = true;
//                    } else {
//                        isInteger = false;
//                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.\n" + FioreFlowershop.ConsoleColors.RESET);
//                        scan.next();
//                    }
//                } while (!(isInteger) || floralArrangementType < 1 || floralArrangementType > 5);
//
//                do {
//                    System.out.println("\nFlowerpot list:\n" + FioreFlowershop.ConsoleColors.GREEN + "[1]" + FioreFlowershop.ConsoleColors.RESET + " Drinking glass\n" + FioreFlowershop.ConsoleColors.GREEN + "[2]" + FioreFlowershop.ConsoleColors.RESET + " Glass bottle\n" + FioreFlowershop.ConsoleColors.GREEN + "[3]" + FioreFlowershop.ConsoleColors.RESET
//                            + " Woolden box\n" + FioreFlowershop.ConsoleColors.GREEN + "[4]" + FioreFlowershop.ConsoleColors.RESET + " Jar\n" + FioreFlowershop.ConsoleColors.GREEN + "[5]" + FioreFlowershop.ConsoleColors.RESET + " Bud vase");
//                    System.out.print("Please enter a number: ");
//                    if (scan.hasNextInt()) {
//                        flowerPotType = scan.nextInt();
//                        isInteger = true;
//                    } else {
//                        isInteger = false;
//                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.\n" + FioreFlowershop.ConsoleColors.RESET);
//                        scan.next();
//                    }
//                } while (!(isInteger) || flowerPotType < 1 || flowerPotType > 5);
//            }
//
//            if (catalogTypes == 1) {
//                catalogPackage = normalPackage.getProduct(productNumber);
//                if (editAllCatalog == false) {
//                    System.out.println("\nBefore: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    if (productTypes == 1) {
//                        catalogPackage.setProductType("Fresh flower");
//                    } else if (productTypes == 2) {
//                        catalogPackage.setProductType("Bouquets");
//                    } else if (productTypes == 3) {
//                        catalogPackage.setProductType("Flower arrangement");
//                        if (floralArrangementType == 1) {
//                            catalogPackage.setFloralArrangement("Valentine");
//                        } else if (floralArrangementType == 2) {
//                            catalogPackage.setFloralArrangement("Graduation");
//                        } else if (floralArrangementType == 3) {
//                            catalogPackage.setFloralArrangement("Grand opening");
//                        } else if (floralArrangementType == 4) {
//                            catalogPackage.setFloralArrangement("Visit patient");
//                        } else if (floralArrangementType == 5) {
//                            catalogPackage.setFloralArrangement("Funeral");
//                        }
//
//                        if (flowerPotType == 1) {
//                            catalogPackage.setFlowerPot("Drinking glass");
//                        } else if (flowerPotType == 2) {
//                            catalogPackage.setFlowerPot("Glass bottle");
//                        } else if (flowerPotType == 3) {
//                            catalogPackage.setFlowerPot("Woolden box");
//                        } else if (flowerPotType == 4) {
//                            catalogPackage.setFlowerPot("Jar");
//                        } else if (flowerPotType == 5) {
//                            catalogPackage.setFlowerPot("Bud vase");
//                        }
//                    }
//
//                    System.out.println("\nAfter: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//                    catalogMaintenance(normalPackage, discountedPackage);
//                }
//            } else if (catalogTypes == 2) {
//                catalogPackage = discountedPackage.getProduct(productNumber);
//                if (editAllMonthlyCatalog == false) {
//                    System.out.println("\nBefore: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    if (productTypes == 1) {
//                        catalogPackage.setProductType("Fresh flower");
//                    } else if (productTypes == 2) {
//                        catalogPackage.setProductType("Bouquets");
//                    } else if (productTypes == 3) {
//                        catalogPackage.setProductType("Flower arrangement");
//                        if (floralArrangementType == 1) {
//                            catalogPackage.setFloralArrangement("Valentine");
//                        } else if (floralArrangementType == 2) {
//                            catalogPackage.setFloralArrangement("Graduation");
//                        } else if (floralArrangementType == 3) {
//                            catalogPackage.setFloralArrangement("Grand opening");
//                        } else if (floralArrangementType == 4) {
//                            catalogPackage.setFloralArrangement("Visit patient");
//                        } else if (floralArrangementType == 5) {
//                            catalogPackage.setFloralArrangement("Funeral");
//                        }
//
//                        if (flowerPotType == 1) {
//                            catalogPackage.setFlowerPot("Drinking glass");
//                        } else if (flowerPotType == 2) {
//                            catalogPackage.setFlowerPot("Glass bottle");
//                        } else if (flowerPotType == 3) {
//                            catalogPackage.setFlowerPot("Woolden box");
//                        } else if (flowerPotType == 4) {
//                            catalogPackage.setFlowerPot("Jar");
//                        } else if (flowerPotType == 5) {
//                            catalogPackage.setFlowerPot("Bud vase");
//                        }
//                    }
//                    System.out.println("\nAfter: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//                    catalogMaintenance(normalPackage, discountedPackage);
//                }
//            }
//        }
//
//        if (productField == 7 || editAllCatalog == true || editAllMonthlyCatalog == true) {
//            if (catalogTypes == 1) {
//                do {
//                    System.out.print("Please enter new quantity: ");
//                    if (scan.hasNextInt()) {
//                        quantity = scan.nextInt();
//                        isInteger = true;
//                    } else {
//                        isInteger = false;
//                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.\n" + FioreFlowershop.ConsoleColors.RESET);
//                        scan.next();
//                    }
//                } while (!(isInteger));
//
//                catalogPackage = normalPackage.getProduct(productNumber);
//
//                if (editAllCatalog == false) {
//                    System.out.println("\nBefore: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    catalogPackage.setQuantity(quantity);
//                    System.out.println("\nAfter: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//                    catalogMaintenance(normalPackage, discountedPackage);
//                }
//            } else if (catalogTypes == 2) {
//                do {
//                    System.out.print("Please enter the new month(1 - 12): ");
//                    if (scan.hasNextInt()) {
//                        month = scan.nextInt();
//                        isInteger = true;
//                    } else {
//                        isInteger = false;
//                        System.out.print(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.\n" + FioreFlowershop.ConsoleColors.RESET);
//                        scan.next();
//                    }
//                } while (!(isInteger) || month < 1 || month > 12);
//
//                //Convert the month integer to String
//                if (month == 1) {
//                    promoMonth = "January";
//                } else if (month == 2) {
//                    promoMonth = "February";
//                } else if (month == 3) {
//                    promoMonth = "March";
//                } else if (month == 4) {
//                    promoMonth = "April";
//                } else if (month == 5) {
//                    promoMonth = "May";
//                } else if (month == 6) {
//                    promoMonth = "June";
//                } else if (month == 7) {
//                    promoMonth = "July";
//                } else if (month == 8) {
//                    promoMonth = "August";
//                } else if (month == 9) {
//                    promoMonth = "September";
//                } else if (month == 10) {
//                    promoMonth = "October";
//                } else if (month == 11) {
//                    promoMonth = "November";
//                } else if (month == 12) {
//                    promoMonth = "December";
//                }
//
//                catalogPackage = discountedPackage.getProduct(productNumber);
//
//                if (editAllMonthlyCatalog == false) {
//                    System.out.println("\nBefore: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    catalogPackage.setPromoMonth(promoMonth);
//                    System.out.println("\nAfter: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//                    catalogMaintenance(normalPackage, discountedPackage);
//                }
//            }
//        }
//
//        if (productField == 8 || editAllCatalog == true || editAllMonthlyCatalog == true) {
//            if (catalogTypes == 1 || catalogTypes == 1 && productField == 9) {
//                do {
//                    System.out.print("Please enter new price: ");
//                    if (scan.hasNextDouble()) {
//                        price = scan.nextDouble();
//                        isDouble = true;
//                    } else {
//                        isDouble = false;
//                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.\n" + FioreFlowershop.ConsoleColors.RESET);
//                        scan.next();
//                    }
//                } while (!(isDouble));
//                catalogPackage = normalPackage.getProduct(productNumber);
//
//                if (editAllCatalog == false) {
//                    System.out.println("\nBefore: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    catalogPackage.setPrice(price);
//                    System.out.println("\nAfter: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//                    catalogMaintenance(normalPackage, discountedPackage);
//                }
//            } else if (catalogTypes == 2) {
//                do {
//                    System.out.print("Please enter the new year: ");
//                    if (scan.hasNextInt()) {
//                        promoYear = scan.nextInt();
//                        isInteger = true;
//                    } else {
//                        isInteger = false;
//                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.\n" + FioreFlowershop.ConsoleColors.RESET);
//                        scan.next();
//                    }
//                } while (!(isInteger) || promoYear < 2018 || promoYear > 3000);
//                catalogPackage = discountedPackage.getProduct(productNumber);
//
//                if (editAllMonthlyCatalog == false) {
//                    System.out.println("\nBefore: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    catalogPackage.setPromoYear(promoYear);
//                    System.out.println("\nAfter: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//                    catalogMaintenance(normalPackage, discountedPackage);
//                }
//            }
//        }
//
//        if (productField == 9 && catalogTypes == 2 || editAllMonthlyCatalog == true) {
//            do {
//                System.out.print("Please enter new quantity: ");
//                if (scan.hasNextInt()) {
//                    quantity = scan.nextInt();
//                    isInteger = true;
//                } else {
//                    isInteger = false;
//                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.\n" + FioreFlowershop.ConsoleColors.RESET);
//                    scan.next();
//                }
//            } while (!(isInteger));
//            catalogPackage = discountedPackage.getProduct(productNumber);
//
//            if (editAllMonthlyCatalog == false) {
//                System.out.println("\nBefore: ");
//                displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                catalogPackage.setQuantity(quantity);
//                System.out.println("\nAfter: ");
//                displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//                catalogMaintenance(normalPackage, discountedPackage);
//            }
//        }
//
//        if (productField == 10 || editAllMonthlyCatalog == true) {
//            if (catalogTypes == 1) {
//                catalogMaintenance(normalPackage, discountedPackage);
//            } else if (catalogTypes == 2) {
//                do {
//                    System.out.print("Please enter new price: ");
//                    if (scan.hasNextDouble()) {
//                        price = scan.nextDouble();
//                        isDouble = true;
//                    } else {
//                        isDouble = false;
//                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.\n" + FioreFlowershop.ConsoleColors.RESET);
//                        scan.next();
//                    }
//                } while (!(isDouble));
//                catalogPackage = discountedPackage.getProduct(productNumber);
//
//                if (editAllMonthlyCatalog == false) {
//                    System.out.println("\nBefore: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    catalogPackage.setPrice(price);
//                    System.out.println("\nAfter: ");
//                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//                    catalogMaintenance(normalPackage, discountedPackage);
//                }
//            }
//        }
//
//        if (productField == 11 || editAllMonthlyCatalog == true) {
//            do {
//                System.out.print("Please enter the discount rate for monthly promotion(0 - 100%): ");
//                if (scan.hasNextInt()) {
//                    discountRate = scan.nextInt();
//                    isInteger = true;
//                } else {
//                    isInteger = false;
//                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.\n" + FioreFlowershop.ConsoleColors.RESET);
//                    scan.next();
//                }
//            } while (!(isInteger) || discountRate < 0 || discountRate > 100);
//            catalogPackage = discountedPackage.getProduct(productNumber);
//
//            if (editAllMonthlyCatalog == false) {
//                System.out.println("\nBefore: ");
//                displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//
//                catalogPackage.setDiscountRate(discountRate);
//
//                System.out.println("\nAfter: ");
//                displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//                System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//                catalogMaintenance(normalPackage, discountedPackage);
//            }
//        }
//
//        if (productField == 13 && catalogTypes == 2) {
//            catalogMaintenance(normalPackage, discountedPackage);
//        }
//
//        if (editAllMonthlyCatalog = true && catalogTypes == 2) {
//            catalogPackage = discountedPackage.getProduct(productNumber);
//            System.out.println("\nBefore: ");
//            displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//            catalogPackage.setName(name);
//            catalogPackage.setStyle(style);
//            catalogPackage.setSize(size);
//            catalogPackage.setFlower(flower);
//            catalogPackage.setAccessory(acessories);
//            if (productTypes == 1) {
//                catalogPackage.setProductType("Fresh flower");
//            } else if (productTypes == 2) {
//                catalogPackage.setProductType("Bouquets");
//            } else if (productTypes == 3) {
//                catalogPackage.setProductType("Flower arrangement");
//                if (floralArrangementType == 1) {
//                    catalogPackage.setFloralArrangement("Valentine");
//                } else if (floralArrangementType == 2) {
//                    catalogPackage.setFloralArrangement("Graduation");
//                } else if (floralArrangementType == 3) {
//                    catalogPackage.setFloralArrangement("Grand opening");
//                } else if (floralArrangementType == 4) {
//                    catalogPackage.setFloralArrangement("Visit patient");
//                } else if (floralArrangementType == 5) {
//                    catalogPackage.setFloralArrangement("Funeral");
//                }
//
//                if (flowerPotType == 1) {
//                    catalogPackage.setFlowerPot("Drinking glass");
//                } else if (flowerPotType == 2) {
//                    catalogPackage.setFlowerPot("Glass bottle");
//                } else if (flowerPotType == 3) {
//                    catalogPackage.setFlowerPot("Woolden box");
//                } else if (flowerPotType == 4) {
//                    catalogPackage.setFlowerPot("Jar");
//                } else if (flowerPotType == 5) {
//                    catalogPackage.setFlowerPot("Bud vase");
//                }
//            }
//            catalogPackage.setPromoMonth(promoMonth);
//            catalogPackage.setPromoYear(promoYear);
//            catalogPackage.setQuantity(quantity);
//            catalogPackage.setPrice(price);
//            catalogPackage.setDiscountRate(discountRate);
//            System.out.println("\nAfter: ");
//            displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//            System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//            catalogMaintenance(normalPackage, discountedPackage);
//        } else if (editAllCatalog = true && catalogTypes == 1) {
//            catalogPackage = normalPackage.getProduct(productNumber);
//            System.out.println("\nBefore: ");
//            displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//            catalogPackage.setName(name);
//            catalogPackage.setStyle(style);
//            catalogPackage.setSize(size);
//            catalogPackage.setFlower(flower);
//            catalogPackage.setAccessory(acessories);
//            if (productTypes == 1) {
//                catalogPackage.setProductType("Fresh flower");
//            } else if (productTypes == 2) {
//                catalogPackage.setProductType("Bouquets");
//            } else if (productTypes == 3) {
//                catalogPackage.setProductType("Flower arrangement");
//                if (floralArrangementType == 1) {
//                    catalogPackage.setFloralArrangement("Valentine");
//                } else if (floralArrangementType == 2) {
//                    catalogPackage.setFloralArrangement("Graduation");
//                } else if (floralArrangementType == 3) {
//                    catalogPackage.setFloralArrangement("Grand opening");
//                } else if (floralArrangementType == 4) {
//                    catalogPackage.setFloralArrangement("Visit patient");
//                } else if (floralArrangementType == 5) {
//                    catalogPackage.setFloralArrangement("Funeral");
//                }
//
//                if (flowerPotType == 1) {
//                    catalogPackage.setFlowerPot("Drinking glass");
//                } else if (flowerPotType == 2) {
//                    catalogPackage.setFlowerPot("Glass bottle");
//                } else if (flowerPotType == 3) {
//                    catalogPackage.setFlowerPot("Woolden box");
//                } else if (flowerPotType == 4) {
//                    catalogPackage.setFlowerPot("Jar");
//                } else if (flowerPotType == 5) {
//                    catalogPackage.setFlowerPot("Bud vase");
//                }
//            }
//            catalogPackage.setQuantity(quantity);
//            catalogPackage.setPrice(price);
//            System.out.println("\nAfter: ");
//            displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
//            System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
//            catalogMaintenance(normalPackage, discountedPackage);
//        }
//    }
//
//    //deactive product in Catalog / Monthly promotion Catalog
//    public static void deactiveCatalogItem(String navigationTitle, CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage, int catalogTypes) {
//        int productNumber = 0, catalogSize = 0;
//        char userConfirmation;
//        CatalogPackage catalogPackage = new CatalogPackage();
//        if (catalogTypes == 1) {
//            catalogSize = normalPackage.getTotalEntries();
//        } else if (catalogTypes == 2) {
//            catalogSize = discountedPackage.getTotalEntries();
//        }
//
//        //Ask user to choose which product need to be delete
//        do {
//            System.out.print("Please enter the number : ");
//            if (scan.hasNextInt()) {
//                productNumber = scan.nextInt();
//                isInteger = true;
//            } else {
//                isInteger = false;
//                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only." + FioreFlowershop.ConsoleColors.RESET);
//                scan.next();
//            }
//        } while (!(isInteger) || productNumber < 1 || productNumber > catalogSize);
//
//        do {
//            System.out.print("Are you sure you want to delete this record" + FioreFlowershop.ConsoleColors.GREEN + "[y/n]" + FioreFlowershop.ConsoleColors.RESET + " ? ");
//            userConfirmation = Character.toLowerCase(scan.next().charAt(0));
//            if (userConfirmation != 'y' && userConfirmation != 'n') {
//                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter 'y' or 'n' only." + FioreFlowershop.ConsoleColors.RESET);
//            }
//        } while (userConfirmation != 'y' && userConfirmation != 'n');
//
//        if (userConfirmation == 'y' && catalogTypes == 1) {
//            catalogPackage = normalPackage.getProduct(productNumber);
//            catalogPackage.setStatus("Deactive");
//            System.out.println(FioreFlowershop.ConsoleColors.GREEN + normalPackage.getProduct(productNumber).getName() + " has been successfully deactive..." + FioreFlowershop.ConsoleColors.RESET);
//            catalogMaintenance(normalPackage, discountedPackage);
//        } else if (userConfirmation == 'y' && catalogTypes == 2) {
//            catalogPackage = discountedPackage.getProduct(productNumber);
//            catalogPackage.setStatus("Deactive");
//            System.out.println(FioreFlowershop.ConsoleColors.GREEN + discountedPackage.getProduct(productNumber).getName() + " has been successfully deactive..." + FioreFlowershop.ConsoleColors.RESET);
//            catalogMaintenance(normalPackage, discountedPackage);
//        } else if (userConfirmation == 'n') {
//            catalogMaintenance(normalPackage, discountedPackage);
//        }
//    }
//
//    //Display stock
//    public static void displayStockAvailability(String navigationTitle, CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage, int catalogTypes) {
//        CatalogPackageInterface<CatalogPackage> temporalyPackage = new CatalogPackageList<>();
//        String catalogType = "";
//        if (catalogTypes == 1) {
//            temporalyPackage = normalPackage;
//            catalogType = "normal catalog";
//        } else if (catalogTypes == 2) {
//            temporalyPackage = discountedPackage;
//            catalogType = "Monthly promotion catalog";
//        }
//
//        int freshFlowerCounter = 0, bouquetsCounter = 0, flowerArrangementCounter = 0;
//        CatalogPackage catalogPackage = new CatalogPackage();
//        System.out.println("\n" + navigationTitle + " - " + catalogType);
//        System.out.println("========================================");
//        System.out.println("Product types\t\t\tQuantity");
//        if (temporalyPackage.getTotalEntries() != 0) {
//            System.out.println("Fresh Flower");
//            System.out.println("==================");
//            for (int i = 1; i < temporalyPackage.getTotalEntries() + 1; i++) {
//                catalogPackage = temporalyPackage.getProduct(i);
//                if (catalogPackage.getProductType().equals("Fresh flower") && catalogPackage.getStatus().equals("Active")) {
//                    if (catalogPackage.getQuantity() < 10) {
//                        System.out.printf(FioreFlowershop.ConsoleColors.RED + "[%d] %s\t \t\t%d\n" + FioreFlowershop.ConsoleColors.RESET, i, catalogPackage.getName(), catalogPackage.getQuantity());
//                    } else {
//                        System.out.printf("[%d] %s\t \t\t%d\n", i, catalogPackage.getName(), catalogPackage.getQuantity());
//                    }
//                    freshFlowerCounter++;
//                }
//            }
//
//            if (freshFlowerCounter == 0) {
//                System.out.println(FioreFlowershop.ConsoleColors.RED + "There are no record found\n" + FioreFlowershop.ConsoleColors.RESET);
//            }
//
//            System.out.println("\nBouquets");
//            System.out.println("==================");
//            for (int i = 1; i < temporalyPackage.getTotalEntries() + 1; i++) {
//                catalogPackage = temporalyPackage.getProduct(i);
//                if (catalogPackage.getProductType().equals("Bouquets") && catalogPackage.getStatus().equals("Active")) {
//                    if (catalogPackage.getQuantity() < 10) {
//                        System.out.printf(FioreFlowershop.ConsoleColors.RED + "[%d] %s\t \t\t%d\n" + FioreFlowershop.ConsoleColors.RESET, i, catalogPackage.getName(), catalogPackage.getQuantity());
//                    } else {
//                        System.out.printf("[%d] %s\t \t\t%d\n", i, catalogPackage.getName(), catalogPackage.getQuantity());
//                    }
//                    bouquetsCounter++;
//                }
//            }
//
//            if (bouquetsCounter == 0) {
//                System.out.println(FioreFlowershop.ConsoleColors.RED + "There are no record found\n" + FioreFlowershop.ConsoleColors.RESET);
//            }
//
//            System.out.println("\nFlower Arrangement");
//            System.out.println("==================");
//            for (int i = 1; i < temporalyPackage.getTotalEntries() + 1; i++) {
//                catalogPackage = temporalyPackage.getProduct(i);
//                if (catalogPackage.getProductType().equals("Flower arrangement") && catalogPackage.getStatus().equals("Active")) {
//                    if (catalogPackage.getQuantity() < 10) {
//                        System.out.printf(FioreFlowershop.ConsoleColors.RED + "[%d] %s\t \t\t%d\n" + FioreFlowershop.ConsoleColors.RESET, i, catalogPackage.getName(), catalogPackage.getQuantity());
//                    } else {
//                        System.out.printf("[%d] %s\t \t\t%d\n", i, catalogPackage.getName(), catalogPackage.getQuantity());
//                    }
//                    flowerArrangementCounter++;
//                }
//            }
//
//            if (flowerArrangementCounter == 0) {
//                System.out.println(FioreFlowershop.ConsoleColors.RED + "There are no record found\n" + FioreFlowershop.ConsoleColors.RESET);
//            }
//        } else {
//            System.out.println(FioreFlowershop.ConsoleColors.RED + "There are no record found\n" + FioreFlowershop.ConsoleColors.RESET);
//            FioreFlowershop.inventoryClerk();
//        }
//    }
//
//    //
//    public static void restockQuantity(CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage, int catalogTypes) {
//        CatalogPackageInterface<CatalogPackage> temporalyPackage = new CatalogPackageList<>();
//        if (catalogTypes == 1) {
//            temporalyPackage = normalPackage;
//        } else if (catalogTypes == 2) {
//            temporalyPackage = discountedPackage;
//        }
//        int packageNumber = 0, packageQuantity = 0;
//        CatalogPackage catalogPackage = new CatalogPackage();
//        //Ask user to restock the package quantity ?
//        do {
//            System.out.println("\nPlease enter the number of package to update the stock amount.");
//            System.out.print("Enter 0 to cancel: ");
//            if (scan.hasNextInt()) {
//                packageNumber = scan.nextInt();
//                isInteger = true;
//            } else {
//                isInteger = false;
//                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only." + FioreFlowershop.ConsoleColors.RESET);
//                scan.next();
//            }
//        } while (!(isInteger) || packageNumber < 0 || packageNumber > temporalyPackage.getTotalEntries());
//
//        if (packageNumber > 0) {
//            do {
//                System.out.print("Enter the quantity for the package that you wish to add: ");
//                if (scan.hasNextInt()) {
//                    packageQuantity = scan.nextInt();
//                    isInteger = true;
//                } else {
//                    isInteger = false;
//                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only." + FioreFlowershop.ConsoleColors.RESET);
//                    scan.next();
//                }
//            } while (!(isInteger));
//
//            if (catalogTypes == 1) {
//                catalogPackage = normalPackage.getProduct(packageNumber);
//                catalogPackage.setQuantity(catalogPackage.getQuantity() + packageQuantity);
//                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "\nThe " + catalogPackage.getName() + " has successfully update the stock amount into catalog..." + FioreFlowershop.ConsoleColors.RESET);
//                FioreFlowershop.inventoryClerk();
//            } else if (catalogTypes == 2) {
//                catalogPackage = discountedPackage.getProduct(packageNumber);
//                catalogPackage.setQuantity(catalogPackage.getQuantity() + packageQuantity);
//                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "\nThe " + catalogPackage.getName() + " has successfully update the stock amount into monthly promotion catalog..." + FioreFlowershop.ConsoleColors.RESET);
//                FioreFlowershop.inventoryClerk();
//            }
//        } else {
//            FioreFlowershop.inventoryClerk();
//        }
//    }
//
//    //Stock Availability Checking for the notification
//    public static void stockNotification(CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage) {
//        int normalInsufficient = 0, discountInsufficient = 0;
//        CatalogPackage catalogPackage = new CatalogPackage();
//        if (normalPackage.getTotalEntries() != 0) {
//            for (int i = 1; i < normalPackage.getTotalEntries() + 1; i++) {
//                catalogPackage = normalPackage.getProduct(i);
//                if (catalogPackage.getQuantity() < 10 && catalogPackage.getStatus().equals("Active")) {
//                    normalInsufficient++;
//                }
//            }
//        }
//        if (discountedPackage.getTotalEntries() != 0) {
//            for (int i = 1; i < discountedPackage.getTotalEntries() + 1; i++) {
//                catalogPackage = discountedPackage.getProduct(i);
//                if (catalogPackage.getQuantity() < 10 && catalogPackage.getStatus().equals("Active")) {
//                    discountInsufficient++;
//                }
//            }
//        }
//
//        if (normalInsufficient > 0 && discountInsufficient == 0) {
//            System.out.println(FioreFlowershop.ConsoleColors.RED + "\nThe NORMAL catalog product quantity insufficient." + FioreFlowershop.ConsoleColors.RESET);
//        } else if (discountInsufficient > 0 && normalInsufficient == 0) {
//            System.out.println(FioreFlowershop.ConsoleColors.RED + "\nThe MONTHLY promotion catalog product quantity insufficient." + FioreFlowershop.ConsoleColors.RESET);
//        } else if (normalInsufficient > 0 && discountInsufficient > 0) {
//            System.out.println(FioreFlowershop.ConsoleColors.RED + "\nThe NORMAL catalog & MONTHLY promotion catalog product quantity insufficient." + FioreFlowershop.ConsoleColors.RESET);
//        } else {
//            System.out.println("");
//        }
//    }
//
    //Back to catalogMaintenance()
    public static void backToCatalogMenu(CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage, ItemCatalogue itemCatalogue) {
        System.out.println("\n====================================================");
        char userOption;
        do {
            System.out.print("Please enter 'Y/y' to Catalog maintenance menu: ");
            userOption = scan.next().charAt(0);

        } while (userOption != 'y' && userOption != 'Y');

        if (userOption == 'y' || userOption == 'Y') {
            catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
        }
    }
//
//    //Back to iventory clerk menu
//    public static void backToInventoryClerkMenu(String navigationTitle) {
//        if (navigationTitle.equals("Current stock")) {
//            System.out.println("\n====================================================");
//            char userOption;
//            do {
//                System.out.print("Please enter 'Y/y' to Inventory clerk menu: ");
//                userOption = scan.next().charAt(0);
//                System.out.println(userOption);
//
//            } while (userOption != 'y' && userOption != 'Y');
//
//            if (userOption == 'y' || userOption == 'Y') {
//                FioreFlowershop.inventoryClerk();
//            }
//        }
//    }
}
