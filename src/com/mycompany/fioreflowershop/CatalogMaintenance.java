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
        int managerChoice = 0;
        String navigationMsg;

        do {
            System.out.println("\nPlease Select The Options Below.");
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[1]" + FioreFlowershop.ConsoleColors.RESET + " Add a product to catalog");
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[2]" + FioreFlowershop.ConsoleColors.RESET + " Remove a product from catalog");
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[3]" + FioreFlowershop.ConsoleColors.RESET + " Edit the details of product in catalog");
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[4]" + FioreFlowershop.ConsoleColors.RESET + " Display created catalog");
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[5]" + FioreFlowershop.ConsoleColors.RESET + " Create new item");
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[6]" + FioreFlowershop.ConsoleColors.RESET + " Delete item");
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[7]" + FioreFlowershop.ConsoleColors.RESET + " Update item");
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[8]" + FioreFlowershop.ConsoleColors.RESET + " Display item");
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[9]" + FioreFlowershop.ConsoleColors.RESET + " Back");
            System.out.print("Selection: ");
            if (scan.hasNextInt()) {
                managerChoice = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.out.println(FioreFlowershop.ConsoleColors.RED + "An Error Occured. Please Only Enter Number Only." + FioreFlowershop.ConsoleColors.RESET);
                scan.next();
            }
        } while (!(isInteger) || managerChoice < 1 || managerChoice > 9);

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
                addItems(normalPackage, discountedPackage, itemCatalogue);
                catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
                break;
            case 6:
                deleteItems(itemCatalogue);
                catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
                break;
            case 7:
                updateItem(normalPackage, discountedPackage, itemCatalogue);
            case 8:
                displayStockAvailability("Current stock", itemCatalogue);
                catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
                break;
            case 9:
                FioreFlowershop.manager();
                break;
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
            if (isInteger == false) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only." + FioreFlowershop.ConsoleColors.RESET);
            }
            System.out.print("Please enter the product type(1-4): ");
            if (scan.hasNextInt()) {
                productTypes = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
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

            if (userMenuOption < 1 || userMenuOption > 3) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number within 1 - 3." + FioreFlowershop.ConsoleColors.RESET);
            }

            System.out.print("Selection : ");
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
        } else if (userMenuOption == 1 && navigationTitle.equals("Edit catalog")) { //Go to edit catalog(Normal catalog)
            displayCatalog(navigationTitle, normalPackage, discountedPackage, 1);
            editCatalog(navigationTitle, normalPackage, discountedPackage, 1, itemCatalogue);
        } else if (userMenuOption == 2 && navigationTitle.equals("Edit catalog")) { //Go to edit catalog(monthly ptomotion catalog)
            displayCatalog(navigationTitle, normalPackage, discountedPackage, 2);
            editCatalog(navigationTitle, normalPackage, discountedPackage, 2, itemCatalogue);
        } else if (userMenuOption == 1 && navigationTitle.equals("Delete catalog")) {
            displayCatalog(navigationTitle, normalPackage, discountedPackage, 1);
            deactiveCatalogItem(navigationTitle, normalPackage, discountedPackage, 1, itemCatalogue);
        } else if (userMenuOption == 2 && navigationTitle.equals("Delete catalog")) {
            displayCatalog(navigationTitle, normalPackage, discountedPackage, 2);
            deactiveCatalogItem(navigationTitle, normalPackage, discountedPackage, 2, itemCatalogue);
        }
    }

    //Create catalog(normal/ monthly promotion)
    public static void createCatalog(String navigationTitle, int productTypes, CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage, ItemCatalogue itemCatalogue) {
        char nextProduct = 'n', confirmationOption;
        String name, productTypesString = "", promoMonth = "", flowerNeeded = "";
        int style = 0, size = 0, flower = 0, accessory = 0, season = 0, flowerPot = 0, numberNeeded;
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

                    if (flower > 0 && flower < displayFlowers.getTotalEntries() + 1) {
                        do {
                            System.out.println("\nPlease enter the flower quantity needed for this package: ");
                            if (scan.hasNextInt()) {
                                numberNeeded = scan.nextInt();
                                isInteger = true;
                                flowerNeeded += numberNeeded + " ";
                            } else {
                                isInteger = false;
                                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter a valid number" + FioreFlowershop.ConsoleColors.RESET);
                                scan.next();
                            }
                        } while (!isInteger);
                    }
                } while (!(isInteger) || flower < 1 || flower > displayFlowers.getTotalEntries());

                selectedFlowers.add(displayFlowers.getItem(flower));
                displayFlowers.remove(flower);

                if (displayFlowers.isEmpty()) {
                    break;
                }

                do {
                    System.out.print("Add Another Flower?" + FioreFlowershop.ConsoleColors.GREEN + "[Y/N]" + FioreFlowershop.ConsoleColors.RESET + " ");
                    selection = Character.toUpperCase(scan.next().charAt(0));
                    scan.nextLine();
                } while (selection != 'Y' && selection != 'N');
                System.out.println();
            } while (selection == 'Y');

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

                catalogPackage = new CatalogPackage(name, itemCatalogue.getStyles().getItem(style), itemCatalogue.getSizes().getItem(size), itemCatalogue.getAccessories().getItem(accessory), productTypesString, promoMonth, promoYear, price, discountRate, "Active", flowerNeeded);
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

                catalogPackage = new CatalogPackage(name, itemCatalogue.getStyles().getItem(style), itemCatalogue.getSizes().getItem(size), itemCatalogue.getSeason().getItem(season), itemCatalogue.getFlowerPot().getItem(flowerPot), itemCatalogue.getAccessories().getItem(accessory), productTypesString, promoMonth, promoYear, price, discountRate, "Active", flowerNeeded);
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
                    System.out.printf("\t  %s,%s,%s,%s,%s,%s %-30s RM%.2f %20s - %14s %s\n", catalogPackage.getStyle().getName(), catalogPackage.getSize().getName(), normalflowerArrangementList, catalogPackage.getSeason().getName(), catalogPackage.getFlowerPot().getName(), catalogPackage.getAccessory().getName(), " ", catalogPackage.getPrice(), " ", " ", catalogPackage.getStatus());
                    flowerArrangementCounter++;
                    normalflowerArrangementList = "";
                } else if (catalogPackage.getProductType().equals("Flower arrangement") && catalogPackage.getPromoMonth().equals(curentMonthString) && catalogPackage.getPromoYear() == year && catalogPackage.getStatus().equals("Active") && catalogTypes == 2) {
                    for (int j = 1; j < catalogPackage.getFlowerList().getTotalEntries() + 1; j++) {
                        discountflowerArrangementList += catalogPackage.getFlowerList().getItem(j).getName() + " ";
                    }
                    double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                    System.out.printf(FioreFlowershop.ConsoleColors.GREEN + " [%d]" + FioreFlowershop.ConsoleColors.RESET, i);
                    System.out.printf(" %s", catalogPackage.getName());
                    System.out.printf("\t  %s,%s,%s,%s,%s,%s %-30s RM%.2f %20s RM%.2f %14s %s\n", catalogPackage.getStyle().getName(), catalogPackage.getSize().getName(), discountflowerArrangementList, catalogPackage.getSeason().getName(), catalogPackage.getFlowerPot().getName(), catalogPackage.getAccessory().getName(), " ", catalogPackage.getPrice(), " ", discountRate, " ", catalogPackage.getStatus());
                    discountFlowerArrangementCounter++;
                    discountflowerArrangementList = "";
                }
            }

            if (flowerArrangementCounter == 0 && catalogTypes == 1) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "There are no record found\n" + FioreFlowershop.ConsoleColors.RESET);
            } else if (discountFlowerArrangementCounter == 0 && catalogTypes == 2) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "There are no record found\n" + FioreFlowershop.ConsoleColors.RESET);
            }
        }
    }

    //Display one record only for normal or monthly promotion catalog
    public static void displayEditResult(CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage, int catalogTypes, int productNumber) {
        CatalogPackage catalogPackage = new CatalogPackage();
        CatalogPackageInterface<CatalogPackage> temporalyPackage = new CatalogPackageList<>();
        String flowerList = "";

        if (catalogTypes == 1) {
            temporalyPackage = normalPackage;
            System.out.println("Display catalog - Normal Catalog");
        } else if (catalogTypes == 2) {
            temporalyPackage = discountedPackage;
            catalogPackage = temporalyPackage.getProduct(productNumber);
            System.out.println("Display catalog - Monthly promotion catalog" + "( " + catalogPackage.getPromoYear() + " - " + catalogPackage.getPromoMonth() + " )");
        }

        catalogPackage = temporalyPackage.getProduct(productNumber);

        System.out.println("================================================================================================");
        if (temporalyPackage.getTotalEntries() != 0) {
            System.out.println("\n\n-----------------------");
            System.out.println("- " + catalogPackage.getProductType() + " -");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("| No | | Package Name |\t|       Style,Size,Flower,Season,FlowerPot,Accesories       |\t\t|     Price     |\t| Discounted Price |\t|   Status   |");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
            if (catalogTypes == 1 && !catalogPackage.getProductType().equals("Flower arrangement")) {
                for (int j = 1; j < catalogPackage.getFlowerList().getTotalEntries() + 1; j++) {
                    flowerList += catalogPackage.getFlowerList().getItem(j).getName() + " ";
                }
                System.out.printf(FioreFlowershop.ConsoleColors.GREEN + " [%d]" + FioreFlowershop.ConsoleColors.RESET, productNumber);
                System.out.printf("\t %-10s", catalogPackage.getName());
                System.out.printf("\t  %s,%s,%s,%s %-30s RM%.2f %20s - %14s %s\n", catalogPackage.getStyle().getName(), catalogPackage.getSize().getName(), flowerList, catalogPackage.getAccessory().getName(), " ", catalogPackage.getPrice(), " ", " ", catalogPackage.getStatus());
                flowerList = "";
            } else if (catalogTypes == 2 && !catalogPackage.getProductType().equals("Flower arrangement")) {
                for (int j = 1; j < catalogPackage.getFlowerList().getTotalEntries() + 1; j++) {
                    flowerList += catalogPackage.getFlowerList().getItem(j).getName() + " ";
                }
                double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                System.out.printf(FioreFlowershop.ConsoleColors.GREEN + " [%d]" + FioreFlowershop.ConsoleColors.RESET, productNumber);
                System.out.printf("\t %-10s", catalogPackage.getName());
                System.out.printf("\t  %s,%s,%s,%s %-30s RM%.2f %20s RM%.2f %14s %s\n", catalogPackage.getStyle().getName(), catalogPackage.getSize().getName(), flowerList, catalogPackage.getAccessory().getName(), " ", catalogPackage.getPrice(), " ", discountRate, " ", catalogPackage.getStatus());
                flowerList = "";
            } else if (catalogTypes == 1 && catalogPackage.getProductType().equals("Flower arrangement")) {
                for (int j = 1; j < catalogPackage.getFlowerList().getTotalEntries() + 1; j++) {
                    flowerList += catalogPackage.getFlowerList().getItem(j).getName() + " ";
                }
                System.out.printf(FioreFlowershop.ConsoleColors.GREEN + " [%d]" + FioreFlowershop.ConsoleColors.RESET, productNumber);
                System.out.printf(" %-10s", catalogPackage.getName());
                System.out.printf("\t  %s,%s,%s,%s,%s,%s %-30s RM%.2f %20s - %14s %s\n", catalogPackage.getStyle().getName(), catalogPackage.getSize().getName(), flowerList, catalogPackage.getSeason(), catalogPackage.getFlowerPot().getName(), catalogPackage.getAccessory().getName(), " ", catalogPackage.getPrice(), " ", " ", catalogPackage.getStatus());
                flowerList = "";
            } else if (catalogTypes == 2 && catalogPackage.getProductType().equals("Flower arrangement")) {
                for (int j = 1; j < catalogPackage.getFlowerList().getTotalEntries() + 1; j++) {
                    flowerList += catalogPackage.getFlowerList().getItem(j).getName() + " ";
                }
                double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                System.out.printf(FioreFlowershop.ConsoleColors.GREEN + " [%d]" + FioreFlowershop.ConsoleColors.RESET, productNumber);
                System.out.printf(" %s", catalogPackage.getName());
                System.out.printf("\t  %s,%s,%s,%s,%s,%s %-30s RM%.2f %20s RM%.2f %14s %s\n", catalogPackage.getStyle().getName(), catalogPackage.getSize().getName(), flowerList, catalogPackage.getSeason(), catalogPackage.getFlowerPot().getName(), catalogPackage.getAccessory().getName(), " ", catalogPackage.getPrice(), " ", discountRate, " ", catalogPackage.getStatus());
                flowerList = "";
            }
        }
    }

    //Edit Catalog
    public static void editCatalog(String navigationTitle, CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage, int catalogTypes, ItemCatalogue itemCatalogue) {
        //Data decleration
        int productNumber = 0, catalogSize = 0, productField = 0, sizeOption = 0, productTypes = 0, quantity = 0, discountRate = 0, promoYear = 0, month = 0, minRange = 0, maxRange = 0,
                season = 0, flowerPot = 0, existNameChecker = 0, style = 0, size = 0, acessories = 0, numberNeeded, flower = 0, accessory = 0, maxNumber = 0;
        String name = "", promoMonth = "", flowerNeeded = "";
        boolean validInput, isDouble, editAllCatalog = false, editAllMonthlyCatalog = false, validNumber = false;
        double price = 0;
        ListIteratorInterface<Integer> validNumberList = new LinkedList<Integer>();

        ListIteratorInterface<Item> displayFlowers = new LinkedList<Item>();
        ListIteratorInterface<Item> selectedFlowers = new LinkedList<Item>();
        CatalogPackage catalogSizeChecking = new CatalogPackage();
        CatalogPackage catalogCheckingPackage = new CatalogPackage();

        //Get current month & year
        Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        int currentMonth = c.get(Calendar.MONTH);
        String curentMonthString = "";

        //Convert the month integer to String
        if (currentMonth + 1 == 1) {
            curentMonthString = "January";
        } else if (currentMonth + 1 == 2) {
            curentMonthString = "February";
        } else if (currentMonth + 1 == 3) {
            curentMonthString = "March";
        } else if (currentMonth + 1 == 4) {
            curentMonthString = "April";
        } else if (currentMonth + 1 == 5) {
            curentMonthString = "May";
        } else if (currentMonth + 1 == 6) {
            curentMonthString = "June";
        } else if (currentMonth + 1 == 7) {
            curentMonthString = "July";
        } else if (currentMonth + 1 == 8) {
            curentMonthString = "August";
        } else if (currentMonth + 1 == 9) {
            curentMonthString = "September";
        } else if (currentMonth + 1 == 10) {
            curentMonthString = "October";
        } else if (currentMonth + 1 == 11) {
            curentMonthString = "November";
        } else if (currentMonth + 1 == 12) {
            curentMonthString = "December";
        }

        //Check for normal catalog size or promotion catalog size
        if (catalogTypes == 1) {
            validNumberList.clear();
            for (int i = 1; i < normalPackage.getTotalEntries() + 1; i++) {
                if (normalPackage.getProduct(i).getStatus().equals("Active")) {
                    validNumberList.add(i);
                    catalogSize++;
                }
            }
            for (int i = 1; i < normalPackage.getTotalEntries() + 1; i++) {
                if (normalPackage.getProduct(i).getStatus().equals("Active")) {
                    maxNumber = i;
                }
            }
            for (int i = 1; i < normalPackage.getTotalEntries() + 1; i++) {
                if (normalPackage.getProduct(i).getStatus().equals("Active")) {
                    minRange = i;
                    break;
                }
            }
            maxRange = 8;
        } else if (catalogTypes == 2) {
            validNumberList.clear();
            for (int i = 1; i < discountedPackage.getTotalEntries() + 1; i++) {
                if (discountedPackage.getProduct(i).getStatus().equals("Active") && discountedPackage.getProduct(i).getPromoMonth().equals(curentMonthString) && discountedPackage.getProduct(i).getPromoYear() == currentYear) {
                    validNumberList.add(i);
                    catalogSize++;
                }
            }
            for (int i = 1; i < discountedPackage.getTotalEntries() + 1; i++) {
                if (discountedPackage.getProduct(i).getStatus().equals("Active") && discountedPackage.getProduct(i).getPromoMonth().equals(curentMonthString) && discountedPackage.getProduct(i).getPromoYear() == currentYear) {
                    maxNumber = i;
                }
            }
            for (int i = 1; i < discountedPackage.getTotalEntries() + 1; i++) {
                if (discountedPackage.getProduct(i).getStatus().equals("Active") && discountedPackage.getProduct(i).getPromoMonth().equals(curentMonthString) && discountedPackage.getProduct(i).getPromoYear() == currentYear) {
                    minRange = i;
                    break;
                }
            }
            maxRange = 11;
        }

        CatalogPackage catalogPackage = new CatalogPackage();

        //Ask user to choose which product need to be edit
        if (catalogSize != 0) {
            do {
                System.out.print("Please enter the number(" + minRange + " - " + maxNumber + "): ");
                if (scan.hasNextInt()) {
                    productNumber = scan.nextInt();
                    isInteger = true;

                    for (int i = 1; i < validNumberList.getTotalEntries() + 1; i++) {
                        if (productNumber == validNumberList.getItem(i)) {
                            validNumber = true;
                        }
                    }
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.\n" + FioreFlowershop.ConsoleColors.RESET);
                    scan.next();
                }
            } while (!(isInteger) || validNumber == false);

            //Edit each of the product field(Normal Catalog Editor)
            do {
                if (catalogTypes == 1) {
                    System.out.print("\nWhich part of the product you wish to edit?\n" + FioreFlowershop.ConsoleColors.BLUE + "Note 1: if want to edit the flower needed quantity, please select Flower[4]\n" + FioreFlowershop.ConsoleColors.RESET + FioreFlowershop.ConsoleColors.BLUE + "Note 2: if want to edit the season & flower pot, select the flower arrangement in the product type[6]\n" + FioreFlowershop.ConsoleColors.RESET
                            + FioreFlowershop.ConsoleColors.GREEN + "[1]" + FioreFlowershop.ConsoleColors.RESET + " Name\n" + FioreFlowershop.ConsoleColors.GREEN + "[2]" + FioreFlowershop.ConsoleColors.RESET + " Style\n" + FioreFlowershop.ConsoleColors.GREEN + "[3]" + FioreFlowershop.ConsoleColors.RESET + " Size\n"
                            + FioreFlowershop.ConsoleColors.GREEN + "[4]" + FioreFlowershop.ConsoleColors.RESET + " Flower\n" + FioreFlowershop.ConsoleColors.GREEN + "[5]" + FioreFlowershop.ConsoleColors.RESET + " Accessory\n" + FioreFlowershop.ConsoleColors.GREEN + "[6]" + FioreFlowershop.ConsoleColors.RESET + " Product type\n"
                            + FioreFlowershop.ConsoleColors.GREEN + "[7]" + FioreFlowershop.ConsoleColors.RESET + " All\n" + FioreFlowershop.ConsoleColors.GREEN + "[8]" + FioreFlowershop.ConsoleColors.RESET + " Cancel\n");
                } else if (catalogTypes == 2) {
                    System.out.print("\nWhich part of the product you wish to edit?\n" + FioreFlowershop.ConsoleColors.BLUE + "Note 1: if want to edit the flower needed quantity, please select Flower[4]\n" + FioreFlowershop.ConsoleColors.RESET + FioreFlowershop.ConsoleColors.BLUE + "Note 2: if want to edit the season & flower pot, select the flower arrangement in the product type[6]\n" + FioreFlowershop.ConsoleColors.RESET
                            + FioreFlowershop.ConsoleColors.GREEN + "[1]" + FioreFlowershop.ConsoleColors.RESET + " Name\n" + FioreFlowershop.ConsoleColors.GREEN + "[2]" + FioreFlowershop.ConsoleColors.RESET + " Style\n" + FioreFlowershop.ConsoleColors.GREEN + "[3]" + FioreFlowershop.ConsoleColors.RESET + " Size\n"
                            + FioreFlowershop.ConsoleColors.GREEN + "[4]" + FioreFlowershop.ConsoleColors.RESET + " Flower\n" + FioreFlowershop.ConsoleColors.GREEN + "[5]" + FioreFlowershop.ConsoleColors.RESET + " Accessory\n" + FioreFlowershop.ConsoleColors.GREEN + "[6]" + FioreFlowershop.ConsoleColors.RESET + " Product type\n" + FioreFlowershop.ConsoleColors.GREEN
                            + "[7]" + FioreFlowershop.ConsoleColors.RESET + " Promotion month\n" + FioreFlowershop.ConsoleColors.GREEN + "[8]" + FioreFlowershop.ConsoleColors.RESET + " Promotion year\n" + FioreFlowershop.ConsoleColors.GREEN + "[9]" + FioreFlowershop.ConsoleColors.RESET + " Discount rate\n" + FioreFlowershop.ConsoleColors.GREEN + "[10]"
                            + FioreFlowershop.ConsoleColors.RESET + " All\n" + FioreFlowershop.ConsoleColors.GREEN + "[11]" + FioreFlowershop.ConsoleColors.RESET + " Cancel\n");
                }

                if (scan.hasNextInt()) {
                    productField = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number (1 - " + maxRange + ") only.\n" + FioreFlowershop.ConsoleColors.RESET);
                    scan.next();
                }
            } while (!(isInteger) || productField < 1 || productField > maxRange);

            if (productField == 7 && catalogTypes == 1) {
                editAllCatalog = true;
            }

            if (productField == 10 && catalogTypes == 2) {
                editAllMonthlyCatalog = true;
            }

            if (productField == 1 || editAllCatalog == true || editAllMonthlyCatalog == true) {
                scan.nextLine();
                do {
                    existNameChecker = 0;
                    validInput = true;
                    System.out.print("\nPlease enter new package name: ");
                    name = scan.nextLine();
                    if (name == null || name.isEmpty()) {
                        validInput = false;
                        System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "This field cannot be empty" + FioreFlowershop.ConsoleColors.RESET);
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
                            System.out.println(FioreFlowershop.ConsoleColors.RED + "This name has been taken before please use another different name." + FioreFlowershop.ConsoleColors.RESET);
                        }
                    }
                } while (validInput == false || existNameChecker > 0);

                if (catalogTypes == 1) {
                    catalogPackage = normalPackage.getProduct(productNumber);
                    if (editAllCatalog == false) {
                        System.out.println("\nBefore: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        catalogPackage.setName(name);
                        System.out.println("\nAfter: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
                        catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
                    }
                } else if (catalogTypes == 2) {
                    catalogPackage = discountedPackage.getProduct(productNumber);
                    if (editAllMonthlyCatalog == false) {
                        System.out.println("\nBefore: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        catalogPackage.setName(name);
                        System.out.println("\nAfter: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
                        catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
                    }
                }
            }

            if (productField == 2 || editAllCatalog == true || editAllMonthlyCatalog == true) {
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

                if (catalogTypes == 1) {
                    catalogPackage = normalPackage.getProduct(productNumber);
                    if (editAllCatalog == false) {
                        System.out.println("\nBefore: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        double previousStylePrice = itemCatalogue.getStyles().getItem(style).getPrice();
                        catalogPackage.setStyle(itemCatalogue.getStyles().getItem(style));
                        price = catalogPackage.getPrice() - previousStylePrice + catalogPackage.getStyle().getPrice();
                        catalogPackage.setPrice(price);
                        System.out.println("\nAfter: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
                        catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
                    }
                } else if (catalogTypes == 2) {
                    catalogPackage = discountedPackage.getProduct(productNumber);
                    if (editAllMonthlyCatalog == false) {
                        System.out.println("\nBefore: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        double previousStylePrice = itemCatalogue.getStyles().getItem(style).getPrice();
                        catalogPackage.setStyle(itemCatalogue.getStyles().getItem(style));
                        price = catalogPackage.getPrice() - previousStylePrice + catalogPackage.getStyle().getPrice();
                        catalogPackage.setPrice(price);
                        System.out.println("\nAfter: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
                        catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
                    }
                }
            }

            if (productField == 3 || editAllCatalog == true || editAllMonthlyCatalog == true) {
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

                if (catalogTypes == 1) {
                    catalogPackage = normalPackage.getProduct(productNumber);
                    if (editAllCatalog == false) {
                        System.out.println("\nBefore: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        double previousSizePrice = catalogPackage.getSize().getPrice();
                        catalogPackage.setSize(itemCatalogue.getSizes().getItem(size));
                        price = catalogPackage.getSize().getPrice() * (catalogPackage.getPrice() / previousSizePrice);
                        catalogPackage.setPrice(price);
                        System.out.println("\nAfter: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
                        catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
                    }
                } else if (catalogTypes == 2) {
                    catalogPackage = discountedPackage.getProduct(productNumber);
                    if (editAllMonthlyCatalog == false) {
                        System.out.println("\nBefore: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        double previousSizePrice = catalogPackage.getSize().getPrice();
                        catalogPackage.setSize(itemCatalogue.getSizes().getItem(size));
                        price = catalogPackage.getSize().getPrice() * (catalogPackage.getPrice() / previousSizePrice);
                        catalogPackage.setPrice(price);
                        System.out.println("\nAfter: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
                        catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
                    }
                }
            }

            if (productField == 4 || editAllCatalog == true || editAllMonthlyCatalog == true) {
                scan.reset(); // To read next input
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

                        if (flower > 0 && flower < displayFlowers.getTotalEntries() + 1) {
                            do {
                                System.out.println("\nPlease enter the flower quantity needed for this package: ");
                                if (scan.hasNextInt()) {
                                    numberNeeded = scan.nextInt();
                                    isInteger = true;
                                    flowerNeeded += numberNeeded + " ";
                                } else {
                                    isInteger = false;
                                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter a valid number" + FioreFlowershop.ConsoleColors.RESET);
                                    scan.next();
                                }
                            } while (!isInteger);
                        }
                    } while (!(isInteger) || flower < 1 || flower > displayFlowers.getTotalEntries());

                    selectedFlowers.add(displayFlowers.getItem(flower));
                    displayFlowers.remove(flower);

                    if (displayFlowers.isEmpty()) {
                        break;
                    }

                    do {
                        System.out.print("Add Another Flower?" + FioreFlowershop.ConsoleColors.GREEN + "[Y/N]" + FioreFlowershop.ConsoleColors.RESET + " ");
                        selection = Character.toUpperCase(scan.next().charAt(0));
                        scan.nextLine();
                    } while (selection != 'Y' && selection != 'N');
                    System.out.println();
                } while (selection == 'Y');

                if (catalogTypes == 1) {
                    catalogPackage = normalPackage.getProduct(productNumber);
                    if (editAllCatalog == false) {
                        System.out.println("\nBefore: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        double previousFlowerPrice = 0.00, currentFlowerPrice = 0.00;
                        for (int i = 1; i < catalogPackage.getFlowerList().getTotalEntries() + 1; i++) {
                            previousFlowerPrice += catalogPackage.getFlowerList().getItem(i).getPrice();
                        }
                        catalogPackage.getFlowerList().clear();
                        while (!selectedFlowers.isEmpty()) {
                            catalogPackage.getFlowerList().add(selectedFlowers.getItem(1));
                            currentFlowerPrice += selectedFlowers.getItem(1).getPrice();
                            selectedFlowers.remove(1);
                        }
                        price = catalogPackage.getPrice() - previousFlowerPrice + currentFlowerPrice;
                        catalogPackage.setPrice(price);
                        catalogPackage.setFlowerNeeded(flowerNeeded);
                        System.out.println("\nAfter: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
                        flowerNeeded = "";
                        catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
                    }
                } else if (catalogTypes == 2) {
                    catalogPackage = discountedPackage.getProduct(productNumber);
                    if (editAllMonthlyCatalog == false) {
                        System.out.println("\nBefore: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        double previousFlowerPrice = 0.00, currentFlowerPrice = 0.00;
                        for (int i = 1; i < catalogPackage.getFlowerList().getTotalEntries() + 1; i++) {
                            previousFlowerPrice += catalogPackage.getFlowerList().getItem(i).getPrice();
                        }
                        catalogPackage.getFlowerList().clear();
                        while (!selectedFlowers.isEmpty()) {
                            catalogPackage.getFlowerList().add(selectedFlowers.getItem(1));
                            currentFlowerPrice += selectedFlowers.getItem(1).getPrice();
                            selectedFlowers.remove(1);
                        }
                        price = catalogPackage.getPrice() - previousFlowerPrice + currentFlowerPrice;
                        catalogPackage.setPrice(price);
                        catalogPackage.setFlowerNeeded(flowerNeeded);
                        System.out.println("\nAfter: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
                        flowerNeeded = "";
                        catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
                    }
                }

            }

            if (productField == 5 || editAllCatalog == true || editAllMonthlyCatalog == true) {
                if (catalogTypes == 2) {
                    scan.reset();
                }
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

                if (catalogTypes == 1) {
                    catalogPackage = normalPackage.getProduct(productNumber);
                    if (editAllCatalog == false) {
                        System.out.println("\nBefore: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        double previousAccessoryPrice = catalogPackage.getAccessory().getPrice();
                        catalogPackage.setAccessory(itemCatalogue.getAccessories().getItem(accessory));
                        price = catalogPackage.getPrice() - previousAccessoryPrice + itemCatalogue.getAccessories().getItem(accessory).getPrice();
                        catalogPackage.setPrice(price);
                        System.out.println("\nAfter: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
                        catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
                    }
                } else if (catalogTypes == 2) {
                    catalogPackage = discountedPackage.getProduct(productNumber);
                    if (editAllMonthlyCatalog == false) {
                        System.out.println("\nBefore: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        double previousAccessoryPrice = catalogPackage.getAccessory().getPrice();
                        catalogPackage.setAccessory(itemCatalogue.getAccessories().getItem(accessory));
                        price = catalogPackage.getPrice() - previousAccessoryPrice + itemCatalogue.getAccessories().getItem(accessory).getPrice();
                        catalogPackage.setPrice(price);
                        System.out.println("\nAfter: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
                        catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
                    }
                }
            }

            if (productField == 6 || editAllCatalog == true || editAllMonthlyCatalog == true) {
                do {
                    System.out.println("\nProduct type list\n" + FioreFlowershop.ConsoleColors.GREEN + "[1]" + FioreFlowershop.ConsoleColors.RESET
                            + " Fresh flowers\n" + FioreFlowershop.ConsoleColors.GREEN + "[2]" + FioreFlowershop.ConsoleColors.RESET + " Bouquets\n" + FioreFlowershop.ConsoleColors.GREEN + "[3]" + FioreFlowershop.ConsoleColors.RESET
                            + " Flower arrangement");
                    System.out.print("Please enter the product type(1-3): ");
                    if (scan.hasNextInt()) {
                        productTypes = scan.nextInt();
                        isInteger = true;
                    } else {
                        isInteger = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.\n" + FioreFlowershop.ConsoleColors.RESET);
                        scan.next();
                    }
                } while (!(isInteger) || productTypes < 1 || productTypes > 3);

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

                if (catalogTypes == 1) {
                    catalogPackage = normalPackage.getProduct(productNumber);
                    if (editAllCatalog == false) {
                        System.out.println("\nBefore: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        if (productTypes == 1) {
                            String previousProductType = catalogPackage.getProductType();
                            catalogPackage.setProductType("Fresh flower");
                            if (previousProductType.equals("Flower arrangement")) {
                                catalogPackage.setSeason(null);
                                price = catalogPackage.getPrice() - catalogPackage.getFlowerPot().getPrice();
                                catalogPackage.setFlowerPot(null);
                                catalogPackage.setPrice(price);
                            }
                        } else if (productTypes == 2) {
                            String previousProductType = catalogPackage.getProductType();
                            catalogPackage.setProductType("Bouquets");
                            if (previousProductType.equals("Flower arrangement")) {
                                catalogPackage.setSeason(null);
                                price = catalogPackage.getPrice() - catalogPackage.getFlowerPot().getPrice();
                                catalogPackage.setFlowerPot(null);
                                catalogPackage.setPrice(price);
                            }
                        } else if (productTypes == 3) {
                            String previousProductType = catalogPackage.getProductType();
                            catalogPackage.setProductType("Flower arrangement");
                            catalogPackage.setSeason(itemCatalogue.getSeason().getItem(season));

                            if (previousProductType.equals("Flower arrangement")) {
                                double previousFlowerPotPrice = catalogPackage.getFlowerPot().getPrice();
                                catalogPackage.setFlowerPot(itemCatalogue.getFlowerPot().getItem(flowerPot));
                                price = catalogPackage.getPrice() - previousFlowerPotPrice + catalogPackage.getFlowerPot().getPrice();
                            }

                            if (!previousProductType.equals("Flower arrangement")) {
                                catalogPackage.setFlowerPot(itemCatalogue.getFlowerPot().getItem(flowerPot));
                                price = catalogPackage.getPrice() + catalogPackage.getFlowerPot().getPrice();
                            }

                            catalogPackage.setPrice(price);
                        }

                        System.out.println("\nAfter: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
                        catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
                    }
                } else if (catalogTypes == 2) {
                    catalogPackage = discountedPackage.getProduct(productNumber);
                    if (editAllMonthlyCatalog == false) {
                        System.out.println("\nBefore: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        if (productTypes == 1) {
                            String previousProductType = catalogPackage.getProductType();
                            catalogPackage.setProductType("Fresh flower");
                            if (previousProductType.equals("Flower arrangement")) {
                                catalogPackage.setSeason(null);
                                price = catalogPackage.getPrice() - catalogPackage.getFlowerPot().getPrice();
                                catalogPackage.setFlowerPot(null);
                                catalogPackage.setPrice(price);
                            }
                        } else if (productTypes == 2) {
                            String previousProductType = catalogPackage.getProductType();
                            catalogPackage.setProductType("Bouquets");
                            if (previousProductType.equals("Flower arrangement")) {
                                catalogPackage.setSeason(null);
                                price = catalogPackage.getPrice() - catalogPackage.getFlowerPot().getPrice();
                                catalogPackage.setFlowerPot(null);
                                catalogPackage.setPrice(price);
                            }
                        } else if (productTypes == 3) {
                            String previousProductType = catalogPackage.getProductType();
                            catalogPackage.setProductType("Flower arrangement");
                            catalogPackage.setSeason(itemCatalogue.getSeason().getItem(season));

                            if (previousProductType.equals("Flower arrangement")) {
                                double previousFlowerPotPrice = catalogPackage.getFlowerPot().getPrice();
                                catalogPackage.setFlowerPot(itemCatalogue.getFlowerPot().getItem(flowerPot));
                                price = catalogPackage.getPrice() - previousFlowerPotPrice + catalogPackage.getFlowerPot().getPrice();
                            }

                            if (!previousProductType.equals("Flower arrangement")) {
                                catalogPackage.setFlowerPot(itemCatalogue.getFlowerPot().getItem(flowerPot));
                                price = catalogPackage.getPrice() + catalogPackage.getFlowerPot().getPrice();
                            }

                            catalogPackage.setPrice(price);
                        }
                        System.out.println("\nAfter: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
                        catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
                    }
                }
            }

            if (productField == 7 || editAllCatalog == true || editAllMonthlyCatalog == true) {
                if (catalogTypes == 2) {
                    do {
                        System.out.print("Please enter the new month(1 - 12): ");
                        if (scan.hasNextInt()) {
                            month = scan.nextInt();
                            isInteger = true;
                        } else {
                            isInteger = false;
                            System.out.print(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.\n" + FioreFlowershop.ConsoleColors.RESET);
                            scan.next();
                        }
                    } while (!(isInteger) || month < 1 || month > 12);

                    //Convert the month integer to String
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

                    catalogPackage = discountedPackage.getProduct(productNumber);

                    if (editAllMonthlyCatalog == false) {
                        System.out.println("\nBefore: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        catalogPackage.setPromoMonth(promoMonth);
                        System.out.println("\nAfter: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
                        catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
                    }
                }
            }

            if (productField == 8 || editAllCatalog == true || editAllMonthlyCatalog == true) {
                if (catalogTypes == 1 && productField == 8) {
                    catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
                } else if (catalogTypes == 2) {
                    do {
                        System.out.print("Please enter the new year: ");
                        if (scan.hasNextInt()) {
                            promoYear = scan.nextInt();
                            isInteger = true;
                        } else {
                            isInteger = false;
                            System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.\n" + FioreFlowershop.ConsoleColors.RESET);
                            scan.next();
                        }
                    } while (!(isInteger) || promoYear < 2018 || promoYear > 3000);
                    catalogPackage = discountedPackage.getProduct(productNumber);

                    if (editAllMonthlyCatalog == false) {
                        System.out.println("\nBefore: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        catalogPackage.setPromoYear(promoYear);
                        System.out.println("\nAfter: ");
                        displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                        System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
                        catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
                    }
                }
            }

            if (productField == 9 || editAllMonthlyCatalog == true) {
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
                catalogPackage = discountedPackage.getProduct(productNumber);

                if (editAllMonthlyCatalog == false) {
                    System.out.println("\nBefore: ");
                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);

                    catalogPackage.setDiscountRate(discountRate);

                    System.out.println("\nAfter: ");
                    displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
                    catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
                }
            }

            if (productField == 11 && catalogTypes == 2) {
                catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
            }

            if (editAllMonthlyCatalog = true && catalogTypes == 2) {
                catalogPackage = discountedPackage.getProduct(productNumber);
                System.out.println("\nBefore: ");
                displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                catalogPackage.setName(name);
                catalogPackage.setStyle(itemCatalogue.getStyles().getItem(style));
                catalogPackage.setSize(itemCatalogue.getSizes().getItem(size));
                double flowerPrice = 0.00;
                for (int i = 1; i < selectedFlowers.getTotalEntries() + 1; i++) {
                    flowerPrice += selectedFlowers.getItem(i).getPrice();
                }
                catalogPackage.getFlowerList().clear();
                while (!selectedFlowers.isEmpty()) {
                    catalogPackage.getFlowerList().add(selectedFlowers.getItem(1));
                    selectedFlowers.remove(1);
                }
                catalogPackage.setFlowerNeeded(flowerNeeded);
                catalogPackage.setAccessory(itemCatalogue.getAccessories().getItem(accessory));
                if (productTypes == 1) {
                    String previousProductType = catalogPackage.getProductType();
                    catalogPackage.setProductType("Fresh flower");
                    if (previousProductType.equals("Flower arrangement")) {
                        catalogPackage.setSeason(null);
                        catalogPackage.setFlowerPot(null);
                    }
                    price = itemCatalogue.getSizes().getItem(size).getPrice() * (itemCatalogue.getStyles().getItem(style).getPrice() + itemCatalogue.getAccessories().getItem(accessory).getPrice() + flowerPrice);
                    catalogPackage.setPrice(price);
                } else if (productTypes == 2) {
                    String previousProductType = catalogPackage.getProductType();
                    catalogPackage.setProductType("Bouquets");
                    if (previousProductType.equals("Flower arrangement")) {
                        catalogPackage.setSeason(null);
                        catalogPackage.setFlowerPot(null);
                    }
                    price = itemCatalogue.getSizes().getItem(size).getPrice() * (itemCatalogue.getStyles().getItem(style).getPrice() + itemCatalogue.getAccessories().getItem(accessory).getPrice() + flowerPrice);
                    catalogPackage.setPrice(price);
                } else if (productTypes == 3) {
                    catalogPackage.setProductType("Flower arrangement");
                    catalogPackage.setSeason(itemCatalogue.getSeason().getItem(season));
                    catalogPackage.setFlowerPot(itemCatalogue.getFlowerPot().getItem(flowerPot));
                    price = itemCatalogue.getSizes().getItem(size).getPrice() * (itemCatalogue.getStyles().getItem(style).getPrice() + itemCatalogue.getFlowerPot().getItem(flowerPot).getPrice() + itemCatalogue.getAccessories().getItem(accessory).getPrice() + flowerPrice);
                    catalogPackage.setPrice(price);
                }
                catalogPackage.setPromoMonth(promoMonth);
                catalogPackage.setPromoYear(promoYear);
                catalogPackage.setDiscountRate(discountRate);
                System.out.println("\nAfter: ");
                displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
                flowerNeeded = "";
                catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
            } else if (editAllCatalog = true && catalogTypes == 1) {
                catalogPackage = normalPackage.getProduct(productNumber);
                System.out.println("\nBefore: ");
                displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                catalogPackage.setName(name);
                catalogPackage.setStyle(itemCatalogue.getStyles().getItem(style));
                catalogPackage.setSize(itemCatalogue.getSizes().getItem(size));
                double flowerPrice = 0.00;
                for (int i = 1; i < selectedFlowers.getTotalEntries() + 1; i++) {
                    flowerPrice += selectedFlowers.getItem(i).getPrice();
                }
                catalogPackage.getFlowerList().clear();
                while (!selectedFlowers.isEmpty()) {
                    catalogPackage.getFlowerList().add(selectedFlowers.getItem(1));
                    selectedFlowers.remove(1);
                }
                catalogPackage.setFlowerNeeded(flowerNeeded);
                catalogPackage.setAccessory(itemCatalogue.getAccessories().getItem(accessory));
                if (productTypes == 1) {
                    String previousProductType = catalogPackage.getProductType();
                    catalogPackage.setProductType("Fresh flower");
                    if (previousProductType.equals("Flower arrangement")) {
                        catalogPackage.setSeason(null);
                        catalogPackage.setFlowerPot(null);
                    }
                    price = itemCatalogue.getSizes().getItem(size).getPrice() * (itemCatalogue.getStyles().getItem(style).getPrice() + itemCatalogue.getAccessories().getItem(accessory).getPrice() + flowerPrice);
                    catalogPackage.setPrice(price);
                } else if (productTypes == 2) {
                    String previousProductType = catalogPackage.getProductType();
                    catalogPackage.setProductType("Bouquets");
                    if (previousProductType.equals("Flower arrangement")) {
                        catalogPackage.setSeason(null);
                        catalogPackage.setFlowerPot(null);
                    }
                    price = itemCatalogue.getSizes().getItem(size).getPrice() * (itemCatalogue.getStyles().getItem(style).getPrice() + itemCatalogue.getAccessories().getItem(accessory).getPrice() + flowerPrice);
                    catalogPackage.setPrice(price);
                } else if (productTypes == 3) {
                    catalogPackage.setProductType("Flower arrangement");
                    catalogPackage.setSeason(itemCatalogue.getSeason().getItem(season));
                    catalogPackage.setFlowerPot(itemCatalogue.getFlowerPot().getItem(flowerPot));
                    price = itemCatalogue.getSizes().getItem(size).getPrice() * (itemCatalogue.getStyles().getItem(style).getPrice() + itemCatalogue.getFlowerPot().getItem(flowerPot).getPrice() + itemCatalogue.getAccessories().getItem(accessory).getPrice() + flowerPrice);
                    catalogPackage.setPrice(price);
                }

                System.out.println("\nAfter: ");
                displayEditResult(normalPackage, discountedPackage, catalogTypes, productNumber);
                System.out.print(FioreFlowershop.ConsoleColors.GREEN + "The product has been successfully edited ...\n" + FioreFlowershop.ConsoleColors.RESET);
                flowerNeeded = "";
                catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
            }
        } else {
            if (catalogTypes == 1) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "There is no created catalog ...\n" + FioreFlowershop.ConsoleColors.RESET);
            } else if (catalogTypes == 2) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "There is no created current month promotion catalog ...\n" + FioreFlowershop.ConsoleColors.RESET);
            }
            catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
        }
    }

    //deactive product in Catalog / Monthly promotion Catalog
    public static void deactiveCatalogItem(String navigationTitle, CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage, int catalogTypes, ItemCatalogue itemCatalogue) {
        int productNumber = 0, catalogSize = 0;
        boolean validNumber = false;
        ListIteratorInterface<Integer> validNumberList = new LinkedList<Integer>();
        char userConfirmation;
        CatalogPackage catalogPackage = new CatalogPackage();
        //Get current month & year
        Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        int currentMonth = c.get(Calendar.MONTH);
        String curentMonthString = "";

        //Convert the month integer to String
        if (currentMonth + 1 == 1) {
            curentMonthString = "January";
        } else if (currentMonth + 1 == 2) {
            curentMonthString = "February";
        } else if (currentMonth + 1 == 3) {
            curentMonthString = "March";
        } else if (currentMonth + 1 == 4) {
            curentMonthString = "April";
        } else if (currentMonth + 1 == 5) {
            curentMonthString = "May";
        } else if (currentMonth + 1 == 6) {
            curentMonthString = "June";
        } else if (currentMonth + 1 == 7) {
            curentMonthString = "July";
        } else if (currentMonth + 1 == 8) {
            curentMonthString = "August";
        } else if (currentMonth + 1 == 9) {
            curentMonthString = "September";
        } else if (currentMonth + 1 == 10) {
            curentMonthString = "October";
        } else if (currentMonth + 1 == 11) {
            curentMonthString = "November";
        } else if (currentMonth + 1 == 12) {
            curentMonthString = "December";
        }

        if (catalogTypes == 1) {
            validNumberList.clear();
            for (int i = 1; i < normalPackage.getTotalEntries() + 1; i++) {
                if (normalPackage.getProduct(i).getStatus().equals("Active")) {
                    validNumberList.add(i);
                    catalogSize++;
                }
            }
        } else if (catalogTypes == 2) {
            validNumberList.clear();
            for (int i = 1; i < discountedPackage.getTotalEntries() + 1; i++) {
                if (discountedPackage.getProduct(i).getStatus().equals("Active") && discountedPackage.getProduct(i).getPromoMonth().equals(curentMonthString) && discountedPackage.getProduct(i).getPromoYear() == currentYear) {
                    validNumberList.add(i);
                    catalogSize++;
                }
            }
        }

        if (catalogSize > 0) {
            //Ask user to choose which product need to be delete
            do {
                System.out.print("Please enter the number : ");
                if (scan.hasNextInt()) {
                    productNumber = scan.nextInt();
                    isInteger = true;

                    for (int i = 1; i < validNumberList.getTotalEntries() + 1; i++) {
                        if (productNumber == validNumberList.getItem(i)) {
                            validNumber = true;
                        }
                    }
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only." + FioreFlowershop.ConsoleColors.RESET);
                    scan.next();
                }
            } while (!(isInteger) || validNumber == false);

            do {
                System.out.print("Are you sure you want to delete this record" + FioreFlowershop.ConsoleColors.GREEN + "[y/n]" + FioreFlowershop.ConsoleColors.RESET + " ? ");
                userConfirmation = Character.toLowerCase(scan.next().charAt(0));
                if (userConfirmation != 'y' && userConfirmation != 'n') {
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter 'y' or 'n' only." + FioreFlowershop.ConsoleColors.RESET);
                }
            } while (userConfirmation != 'y' && userConfirmation != 'n');

            if (userConfirmation == 'y' && catalogTypes == 1) {
                catalogPackage = normalPackage.getProduct(productNumber);
                catalogPackage.setStatus("Deactive");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + normalPackage.getProduct(productNumber).getName() + " has been successfully deactive..." + FioreFlowershop.ConsoleColors.RESET);
                catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
            } else if (userConfirmation == 'y' && catalogTypes == 2) {
                catalogPackage = discountedPackage.getProduct(productNumber);
                catalogPackage.setStatus("Deactive");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + discountedPackage.getProduct(productNumber).getName() + " has been successfully deactive..." + FioreFlowershop.ConsoleColors.RESET);
                catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
            } else if (userConfirmation == 'n') {
                catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
            }
        } else {
            System.out.println(FioreFlowershop.ConsoleColors.RED + "No record found." + FioreFlowershop.ConsoleColors.RESET);
            catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
        }
    }

    //Display stock
    public static void displayStockAvailability(String navigationTitle, ItemCatalogue itemCatalogue) {
        System.out.println("\n" + navigationTitle);
        System.out.println("====================================================");
        if (itemCatalogue != null) {
            System.out.println("------------------------------------------------");
            System.out.println("------------------- Style ----------------------");
            System.out.println("------------------------------------------------");
            System.out.println("No\tCatalogue item\t\tPrice");
            if (itemCatalogue.getStyles().getSize() > 0) {
                for (int a = 1; a <= itemCatalogue.getStyles().getSize(); a++) {
                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "[" + a + "]" + FioreFlowershop.ConsoleColors.RESET);
                    System.out.printf("\t%s\t\tRM%.2f\n", itemCatalogue.getStyles().getItem(a).getName(), itemCatalogue.getStyles().getItem(a).getPrice());
                }
            } else {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "No created style exist ..." + FioreFlowershop.ConsoleColors.RESET);
            }

            System.out.println("------------------------------------------------");
            System.out.println("-------------------- Size ----------------------");
            System.out.println("------------------------------------------------");
            System.out.println("No\tCatalogue item\tMultiplier");
            if (itemCatalogue.getSizes().getSize() > 0) {
                for (int b = 1; b <= itemCatalogue.getSizes().getSize(); b++) {
                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "[" + b + "]" + FioreFlowershop.ConsoleColors.RESET);
                    System.out.printf("\t%s\t\t%.0f\n", itemCatalogue.getSizes().getItem(b).getName(), itemCatalogue.getSizes().getItem(b).getPrice());
                }
            } else {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "No created Size exist ..." + FioreFlowershop.ConsoleColors.RESET);
            }

            System.out.println("------------------------------------------------");
            System.out.println("------------------- Season ---------------------");
            System.out.println("------------------------------------------------");
            System.out.println("No\tCatalogue item");
            if (itemCatalogue.getSeason().getSize() > 0) {
                for (int c = 1; c <= itemCatalogue.getSeason().getSize(); c++) {
                    System.out.print(FioreFlowershop.ConsoleColors.GREEN + "[" + c + "]" + FioreFlowershop.ConsoleColors.RESET);
                    System.out.printf("\t%s\n", itemCatalogue.getSeason().getItem(c).getName());
                }
            } else {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "No created Season exist ..." + FioreFlowershop.ConsoleColors.RESET);
            }

            System.out.println("------------------------------------------------");
            System.out.println("-------------------- Flower --------------------");
            System.out.println("------------------------------------------------");
            System.out.println("No\tCatalogue item\t\t\tQuantity");
            if (itemCatalogue.getFlowers().getSize() > 0) {
                for (int i = 1; i < itemCatalogue.getFlowers().getSize() + 1; i++) {
                    if (itemCatalogue.getFlowers().getItem(i).getQuantity() < 50) {
                        System.out.printf(FioreFlowershop.ConsoleColors.GREEN + "[%d]\t" + FioreFlowershop.ConsoleColors.RESET + FioreFlowershop.ConsoleColors.RED + "%s\t\t\t%d\n" + FioreFlowershop.ConsoleColors.RESET, i, itemCatalogue.getFlowers().getItem(i).getName(), itemCatalogue.getFlowers().getItem(i).getQuantity());
                    }
                    if (itemCatalogue.getFlowers().getItem(i).getQuantity() >= 50) {
                        System.out.printf(FioreFlowershop.ConsoleColors.GREEN + "[%d]\t" + FioreFlowershop.ConsoleColors.RESET + "%s\t\t\t%d\n", i, itemCatalogue.getFlowers().getItem(i).getName(), itemCatalogue.getFlowers().getItem(i).getQuantity());
                    }
                }
            } else {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "No created Flower exist ..." + FioreFlowershop.ConsoleColors.RESET);
            }

            System.out.println("------------------------------------------------");
            System.out.println("------------------ Accessories -----------------");
            System.out.println("------------------------------------------------");
            System.out.println("No\tCatalogue item\t\t\tQuantity");
            if (itemCatalogue.getAccessories().getSize() > 0) {
                for (int j = 1; j < itemCatalogue.getAccessories().getSize() + 1; j++) {
                    if (itemCatalogue.getAccessories().getItem(j).getQuantity() < 15 && !itemCatalogue.getAccessories().getItem(j).getName().equals("None")) {
                        System.out.printf(FioreFlowershop.ConsoleColors.GREEN + "[%d]\t" + FioreFlowershop.ConsoleColors.RESET + FioreFlowershop.ConsoleColors.RED + "%s\t\t\t%d\n" + FioreFlowershop.ConsoleColors.RESET, j, itemCatalogue.getAccessories().getItem(j).getName(), itemCatalogue.getAccessories().getItem(j).getQuantity());
                    }
                    if (itemCatalogue.getAccessories().getItem(j).getQuantity() >= 15 && !itemCatalogue.getAccessories().getItem(j).getName().equals("None")) {
                        System.out.printf(FioreFlowershop.ConsoleColors.GREEN + "[%d]\t" + FioreFlowershop.ConsoleColors.RESET + "%s\t\t\t%d\n", j, itemCatalogue.getAccessories().getItem(j).getName(), itemCatalogue.getAccessories().getItem(j).getQuantity());
                    }
                }
            } else {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "No created Accessories exist ..." + FioreFlowershop.ConsoleColors.RESET);
            }

            System.out.println("\n----------------------------------------------");
            System.out.println("------------------ Flower Pot ------------------");
            System.out.println("------------------------------------------------");
            System.out.println("No\tCatalogue item\t\t\tQuantity");
            if (itemCatalogue.getFlowerPot().getSize() > 0) {
                for (int k = 1; k < itemCatalogue.getFlowerPot().getSize() + 1; k++) {
                    if (itemCatalogue.getFlowerPot().getItem(k).getQuantity() < 15) {
                        System.out.printf(FioreFlowershop.ConsoleColors.GREEN + "[%d]\t" + FioreFlowershop.ConsoleColors.RESET + FioreFlowershop.ConsoleColors.RED + "%s\t\t\t%d\n" + FioreFlowershop.ConsoleColors.RESET, k, itemCatalogue.getFlowerPot().getItem(k).getName(), itemCatalogue.getFlowerPot().getItem(k).getQuantity());
                    }
                    if (itemCatalogue.getFlowerPot().getItem(k).getQuantity() >= 15) {
                        System.out.printf(FioreFlowershop.ConsoleColors.GREEN + "[%d]\t" + FioreFlowershop.ConsoleColors.RESET + "%s\t\t\t%d\n", k, itemCatalogue.getFlowerPot().getItem(k).getName(), itemCatalogue.getFlowerPot().getItem(k).getQuantity());
                    }
                }
            } else {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "No created Flower Pot exist ..." + FioreFlowershop.ConsoleColors.RESET);
            }
        } else {
            System.out.println(FioreFlowershop.ConsoleColors.RED + "There are no record found\n" + FioreFlowershop.ConsoleColors.RESET);
            FioreFlowershop.inventoryClerk();
        }
    }

    //Update current stock quantity
    public static void restockQuantity(ItemCatalogue itemCatalogue) {
        int itemNewQuantity = 0, itemType = 0, itemIndex = 0, minRange = 0, maxRange = 0;
        String itemTypeString = "";
        if (itemCatalogue != null) {
            do {
                System.out.println("\nWhich item type do you wish to edit?");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[1]" + FioreFlowershop.ConsoleColors.RESET + " Flower");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[2]" + FioreFlowershop.ConsoleColors.RESET + " Accessories");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[3]" + FioreFlowershop.ConsoleColors.RESET + " Flower Pot");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[4]" + FioreFlowershop.ConsoleColors.RESET + " Back");
                System.out.print("Enter the item type (1 - 3) only: ");
                if (scan.hasNextInt()) {
                    itemType = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only." + FioreFlowershop.ConsoleColors.RESET);
                    scan.next();
                }
                if (itemType == 4) {
                    FioreFlowershop.inventoryClerk();
                }
            } while (!(isInteger) || itemType < 1 || itemType > 4);

            do {
                if (itemType == 1) {
                    itemTypeString = "Flower";
                    minRange = 1;
                    maxRange = itemCatalogue.getFlowers().getSize();
                } else if (itemType == 2) {
                    itemTypeString = "Accessories";
                    minRange = 2;
                    maxRange = itemCatalogue.getAccessories().getSize();
                } else if (itemType == 3) {
                    itemTypeString = "Flower Pot";
                    minRange = 1;
                    maxRange = itemCatalogue.getFlowerPot().getSize();
                }
                if (itemType == 2 && itemIndex == 1) {
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Number 1 is remove from accessories menu" + FioreFlowershop.ConsoleColors.RESET);
                } else {
                    System.out.print("");
                }
                System.out.printf("\nPlease enter the " + FioreFlowershop.ConsoleColors.BLUE + itemTypeString + FioreFlowershop.ConsoleColors.RESET + " number (" + minRange + " - " + maxRange + "): ");
                if (scan.hasNextInt()) {
                    itemIndex = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only." + FioreFlowershop.ConsoleColors.RESET);
                    scan.next();
                }
            } while (!(isInteger) || itemIndex < minRange || itemIndex > maxRange);

            do {
                if (itemType == 1) {
                    System.out.printf("\nPlease enter the new quantity for add into the " + FioreFlowershop.ConsoleColors.BLUE + itemCatalogue.getFlowers().getItem(itemIndex).getName() + FioreFlowershop.ConsoleColors.RESET + " stock: ");
                } else if (itemType == 2) {
                    System.out.printf("\nPlease enter the new quantity for add into the " + FioreFlowershop.ConsoleColors.BLUE + itemCatalogue.getAccessories().getItem(itemIndex).getName() + FioreFlowershop.ConsoleColors.RESET + " stock: ");
                } else if (itemType == 3) {
                    System.out.printf("\nPlease enter the new quantity for add into the " + FioreFlowershop.ConsoleColors.BLUE + itemCatalogue.getFlowerPot().getItem(itemIndex).getName() + FioreFlowershop.ConsoleColors.RESET + " stock: ");
                }

                if (scan.hasNextInt()) {
                    itemNewQuantity = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only." + FioreFlowershop.ConsoleColors.RESET);
                    scan.next();
                }
            } while (!(isInteger));

            if (itemType == 1) {
                itemCatalogue.getFlowers().getItem(itemIndex).setQuantity(itemCatalogue.getFlowers().getItem(itemIndex).getQuantity() + itemNewQuantity);
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "\nThe quantity of the " + itemCatalogue.getFlowers().getItem(itemIndex).getName() + " has been updated ..." + FioreFlowershop.ConsoleColors.RESET);
            } else if (itemType == 2) {
                itemCatalogue.getAccessories().getItem(itemIndex).setQuantity(itemCatalogue.getAccessories().getItem(itemIndex).getQuantity() + itemNewQuantity);
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "\nThe quantity of the " + itemCatalogue.getAccessories().getItem(itemIndex).getName() + " has been updated ..." + FioreFlowershop.ConsoleColors.RESET);
            } else if (itemType == 3) {
                itemCatalogue.getFlowerPot().getItem(itemIndex).setQuantity(itemCatalogue.getFlowerPot().getItem(itemIndex).getQuantity() + itemNewQuantity);
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "\nThe quantity of the " + itemCatalogue.getFlowerPot().getItem(itemIndex).getName() + " has been updated ..." + FioreFlowershop.ConsoleColors.RESET);
            }
        } else {
            System.out.println(FioreFlowershop.ConsoleColors.RED + "There are no records found ... " + FioreFlowershop.ConsoleColors.RESET);
            FioreFlowershop.inventoryClerk();
        }
    }

    //Stock Availability Checking for the notification
    public static void stockNotification(ItemCatalogue itemCatalogue) {
        int flowerInsufficient = 0, accessoriesInsufficient = 0, flowerPotInsufficient = 0;
        String notificationMsg = "\nThe stock quantity of ( ";
        if (itemCatalogue != null) {
            for (int i = 1; i < itemCatalogue.getFlowers().getSize() + 1; i++) {
                if (itemCatalogue.getFlowers().getItem(i).getQuantity() < 50) {
                    flowerInsufficient++;
                }
            }

            for (int j = 1; j < itemCatalogue.getAccessories().getSize() + 1; j++) {
                if (itemCatalogue.getAccessories().getItem(j).getQuantity() < 15 && !itemCatalogue.getAccessories().getItem(j).getName().equals("None")) {
                    accessoriesInsufficient++;
                }
            }

            for (int k = 1; k < itemCatalogue.getFlowerPot().getSize() + 1; k++) {
                if (itemCatalogue.getFlowerPot().getItem(k).getQuantity() < 15) {
                    flowerPotInsufficient++;
                }
            }

            if (flowerInsufficient > 0) {
                notificationMsg += "Flower ";
            }
            if (accessoriesInsufficient > 0) {
                notificationMsg += "Accessories ";
            }
            if (flowerPotInsufficient > 0) {
                notificationMsg += "Flower Pot ";
            }

            if (flowerInsufficient > 0 || accessoriesInsufficient > 0 || flowerPotInsufficient > 0) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + notificationMsg + ")are insufficient ..." + FioreFlowershop.ConsoleColors.RESET);
            }
        }
    }

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

    //Back to iventory clerk menu
    public static void backToInventoryClerkMenu(String navigationTitle) {
        if (navigationTitle.equals("Current stock")) {
            System.out.println("\n====================================================");
            char userOption;
            do {
                System.out.print("Please enter 'Y/y' to Inventory clerk menu: ");
                userOption = scan.next().charAt(0);

            } while (userOption != 'y' && userOption != 'Y');

            if (userOption == 'y' || userOption == 'Y') {
                FioreFlowershop.inventoryClerk();
            }
        }
    }

    //Adding item
    public static void addItems(CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage, ItemCatalogue itemCatalogue) {
        Scanner scan = new Scanner(System.in);
        int selection;
        int position;
        int maxSize = 0;
        String type = "";
        boolean validInput = false;
        int existNameChecker;
        String name = "";
        while (true) {
            Item newItem = new Item();
            System.out.println("\nSelect the type of item you wish to add");
            do {
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[1] " + FioreFlowershop.ConsoleColors.RESET + "Arrangement Styles");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[2] " + FioreFlowershop.ConsoleColors.RESET + "Arrangement Sizes");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[3] " + FioreFlowershop.ConsoleColors.RESET + "Flowers");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[4] " + FioreFlowershop.ConsoleColors.RESET + "Accessories");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[5] " + FioreFlowershop.ConsoleColors.RESET + "Arrangement Season");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[6] " + FioreFlowershop.ConsoleColors.RESET + "Flower Pot");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[0] " + FioreFlowershop.ConsoleColors.RESET + "Return to previous menu");
                System.out.print("Selection: ");
                selection = scan.nextInt();
            } while (selection < 0 || selection > 6);

            if (selection == 1) {
                type = "arrangement style: ";
                maxSize = itemCatalogue.getStyles().getSize();
            } else if (selection == 2) {
                type = "arrangement size: ";
                maxSize = itemCatalogue.getSizes().getSize();
            } else if (selection == 3) {
                type = "flower: ";
                maxSize = itemCatalogue.getFlowers().getSize();
            } else if (selection == 4) {
                type = "accessory: ";
                maxSize = itemCatalogue.getAccessories().getSize();
            } else if (selection == 5) {
                type = "season: ";
                maxSize = itemCatalogue.getSeason().getSize();
            } else if (selection == 6) {
                type = "flower pot: ";
                maxSize = itemCatalogue.getFlowerPot().getSize();
            } else if (selection == 0) {
                catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
            }

            if (selection != 5) {
                System.out.print("Enter the name of the new " + type);
                scan.nextLine();
                newItem.setName(scan.nextLine());
            }

            if (selection == 1 || selection == 3 || selection == 4 || selection == 6) {
                while (true) {
                    try {
                        System.out.print("Enter the price of the " + type + "RM");
                        newItem.setPrice(scan.nextDouble());
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter a valid number" + FioreFlowershop.ConsoleColors.RESET);
                        scan.next();
                    }
                }
            } else if (selection == 5) {
                do {
                    validInput = true;
                    existNameChecker = 0;
                    System.out.print("Please enter new season: ");
                    name = scan.nextLine();
                    if (name == null || name.isEmpty()) {
                        validInput = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty\n" + FioreFlowershop.ConsoleColors.RESET);
                    } else {
                        for (int i = 1; i < itemCatalogue.getSeason().getSize() + 1; i++) {

                            if (itemCatalogue.getSeason().getItem(i).equals(name)) {
                                existNameChecker++;
                            }
                        }
                        if (existNameChecker > 0) {
                            System.out.println(FioreFlowershop.ConsoleColors.RED + "This name has been taken before please use another different name.\n" + FioreFlowershop.ConsoleColors.RESET);
                        }
                    }
                } while (validInput == false || existNameChecker > 0);
            } else if (selection == 2) {
                while (true) {
                    try {
                        System.out.print("Enter the multiplier of the " + type);
                        newItem.setPrice(scan.nextDouble());
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter a valid number" + FioreFlowershop.ConsoleColors.RESET);
                        scan.next();
                    }
                }
            }
            if (selection != 1 && selection != 2 && selection != 5) {
                while (true) {
                    try {
                        do {
                            System.out.print("Enter the stock quantity: ");
                            newItem.setQuantity(scan.nextInt());
                        } while (newItem.getQuantity() < 0);
                        break;
                    } catch (Exception e) {
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter a valid number" + FioreFlowershop.ConsoleColors.RESET);
                        scan.next();
                    }
                }
            } else {
                newItem.setQuantity(0);
            }
            System.out.println("\nSelect the position to display the new item in the catalogue");
            System.out.println("(If you select a position with an existing item,\nthat item will be moved down one slot together with all proceeding items)");
            System.out.println("================================================");
            printItem(itemCatalogue, selection);
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[" + (maxSize + 1) + "]" + FioreFlowershop.ConsoleColors.RESET + "[ NEW SLOT ]");
            while (true) {
                try {
                    do {
                        System.out.print("Position: ");
                        position = scan.nextInt();
                    } while (position < 1 || position > maxSize + 1);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter a valid number" + FioreFlowershop.ConsoleColors.RESET);
                    scan.next();
                }
            }
            System.out.println("Item successfully added!");
            if (selection == 1) {
                itemCatalogue.getStyles().addItem(position, newItem);
            } else if (selection == 2) {
                itemCatalogue.getSizes().addItem(position, newItem);
            } else if (selection == 3) {
                itemCatalogue.getFlowers().addItem(position, newItem);
            } else if (selection == 4) {
                itemCatalogue.getAccessories().addItem(position, newItem);
            } else if (selection == 5) {
                itemCatalogue.getSeason().addItem(new Item(name, 0));
            } else if (selection == 6) {
                itemCatalogue.getFlowerPot().addItem(position, newItem);
            }

            do {
                System.out.print("Add Another Item?" + FioreFlowershop.ConsoleColors.GREEN + "[Y/N]" + FioreFlowershop.ConsoleColors.RESET + " ");
                selection = Character.toUpperCase(scan.next().charAt(0));
                scan.nextLine();
                System.out.println();
            } while (selection != 'Y' && selection != 'N');
            if (selection == 'N') {
                break;
            }
        }
    }

    //Delete item
    public static void deleteItems(ItemCatalogue itemCatalogue) {
        Scanner scan = new Scanner(System.in);
        int selection = -1;
        int maxSize = 0;
        int position = 0;
        do {
            System.out.println("\nSelect the type of item you wish to delete");
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[1] " + FioreFlowershop.ConsoleColors.RESET + "Arrangement Styles");
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[2] " + FioreFlowershop.ConsoleColors.RESET + "Arrangement Sizes");
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[3] " + FioreFlowershop.ConsoleColors.RESET + "Flowers");
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[4] " + FioreFlowershop.ConsoleColors.RESET + "Accessories");
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[5] " + FioreFlowershop.ConsoleColors.RESET + "Arrangement Season");
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[6] " + FioreFlowershop.ConsoleColors.RESET + "Flower Pot");
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[0] " + FioreFlowershop.ConsoleColors.RESET + "Return to previous menu");
            System.out.print("Selection: ");

            if (scan.hasNextInt()) {
                selection = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only." + FioreFlowershop.ConsoleColors.RESET);
                scan.next();
            }
        } while (!(isInteger) || selection < 0 || selection > 6);

        if (selection == 1) {
            if (itemCatalogue.getStyles().isEmpty()) {
                System.out.println(FioreFlowershop.ConsoleColors.BLUE + "Style catalogue is empty" + FioreFlowershop.ConsoleColors.RESET);
                return;
            }
            maxSize = itemCatalogue.getStyles().getSize();
        } else if (selection == 2) {
            if (itemCatalogue.getSizes().isEmpty()) {
                System.out.println(FioreFlowershop.ConsoleColors.BLUE + "Size catalogue is empty" + FioreFlowershop.ConsoleColors.RESET);
                return;
            }
            maxSize = itemCatalogue.getSizes().getSize();
        } else if (selection == 3) {
            if (itemCatalogue.getFlowers().isEmpty()) {
                System.out.println(FioreFlowershop.ConsoleColors.BLUE + "Flower catalogue is empty" + FioreFlowershop.ConsoleColors.RESET);
                return;
            }
            maxSize = itemCatalogue.getFlowers().getSize();
        } else if (selection == 4) {
            if (itemCatalogue.getAccessories().isEmpty()) {
                System.out.println(FioreFlowershop.ConsoleColors.BLUE + "Accessory catalogue is empty" + FioreFlowershop.ConsoleColors.RESET);
                return;
            }
            maxSize = itemCatalogue.getAccessories().getSize();
        } else if (selection == 5) {
            if (itemCatalogue.getSeason().isEmpty()) {
                System.out.println(FioreFlowershop.ConsoleColors.BLUE + "Season catalogue is empty" + FioreFlowershop.ConsoleColors.RESET);
                return;
            }
            maxSize = itemCatalogue.getSeason().getSize();
        } else if (selection == 6) {
            if (itemCatalogue.getFlowerPot().isEmpty()) {
                System.out.println(FioreFlowershop.ConsoleColors.BLUE + "Flower pot catalogue is empty" + FioreFlowershop.ConsoleColors.RESET);
                return;
            }
            maxSize = itemCatalogue.getFlowerPot().getSize();
        } else {
            return;
        }
        System.out.println("\nSelect the item to be deleted");
        printItem(itemCatalogue, selection);
        while (true) {
            try {
                do {
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please note this action is final and cannot be undone" + FioreFlowershop.ConsoleColors.RESET);
                    System.out.print("Delete item no: ");
                    position = scan.nextInt();
                } while (position < 1 || position > maxSize);
                break;
            } catch (InputMismatchException e) {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter a valid number" + FioreFlowershop.ConsoleColors.RESET);
                scan.next();
            }
        }
        System.out.println("Item Successfully deleted!\n");
        if (selection == 1) {
            itemCatalogue.getStyles().removeItem(position);
        } else if (selection == 2) {
            itemCatalogue.getSizes().removeItem(position);
        } else if (selection == 3) {
            itemCatalogue.getFlowers().removeItem(position);
        } else if (selection == 4) {
            itemCatalogue.getAccessories().removeItem(position);
        } else if (selection == 5) {
            itemCatalogue.getSeason().removeItem(position);
        } else if (selection == 6) {
            itemCatalogue.getFlowerPot().removeItem(position);
        }
    }

    //Update item
    public static void updateItem(CatalogPackageInterface<CatalogPackage> normalPackage, CatalogPackageInterface<CatalogPackage> discountedPackage, ItemCatalogue itemCatalogue) {
        displayStockAvailability("Current Item", itemCatalogue);

        int itemNewQuantity = 0, itemType = 0, itemIndex = 0, minRange = 0, maxRange = 0;
        double newPrice = 0.00;
        String itemTypeString = "", newName = "";
        boolean validInput, isDouble;
        int existNameChecker;
        if (itemCatalogue != null) {
            do {
                System.out.println("\nWhich item type do you wish to edit?");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[1]" + FioreFlowershop.ConsoleColors.RESET + " Style");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[2]" + FioreFlowershop.ConsoleColors.RESET + " Size");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[3]" + FioreFlowershop.ConsoleColors.RESET + " Season");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[4]" + FioreFlowershop.ConsoleColors.RESET + " Flower");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[5]" + FioreFlowershop.ConsoleColors.RESET + " Accessories");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[6]" + FioreFlowershop.ConsoleColors.RESET + " Flower Pot");
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "[7]" + FioreFlowershop.ConsoleColors.RESET + " Back");
                System.out.print("Enter the item type (1 - 6) only: ");
                if (scan.hasNextInt()) {
                    itemType = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only." + FioreFlowershop.ConsoleColors.RESET);
                    scan.next();
                }
                if (itemType == 7) {
                    catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
                }
            } while (!(isInteger) || itemType < 1 || itemType > 7);

            do {
                if (itemType == 1) {
                    itemTypeString = "Style";
                    minRange = 1;
                    maxRange = itemCatalogue.getStyles().getSize();
                } else if (itemType == 2) {
                    itemTypeString = "Size";
                    minRange = 1;
                    maxRange = itemCatalogue.getSizes().getSize();
                } else if (itemType == 3) {
                    itemTypeString = "Season";
                    minRange = 1;
                    maxRange = itemCatalogue.getSeason().getSize();
                } else if (itemType == 4) {
                    itemTypeString = "Flower";
                    minRange = 1;
                    maxRange = itemCatalogue.getFlowers().getSize();
                } else if (itemType == 5) {
                    itemTypeString = "Accessories";
                    minRange = 2;
                    maxRange = itemCatalogue.getAccessories().getSize();
                } else if (itemType == 6) {
                    itemTypeString = "Flower Pot";
                    minRange = 1;
                    maxRange = itemCatalogue.getFlowerPot().getSize();
                }

                if (itemType == 5 && itemIndex == 1) {
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Number 1 is remove from accessories menu" + FioreFlowershop.ConsoleColors.RESET);
                } else {
                    System.out.print("");
                }

                System.out.printf("\nPlease enter the " + FioreFlowershop.ConsoleColors.BLUE + itemTypeString + FioreFlowershop.ConsoleColors.RESET + " number (" + minRange + " - " + maxRange + "): ");
                if (scan.hasNextInt()) {
                    itemIndex = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only." + FioreFlowershop.ConsoleColors.RESET);
                    scan.next();
                }
            } while (!(isInteger) || itemIndex < minRange || itemIndex > maxRange);

            if (itemType == 1 && itemCatalogue.getStyles().getSize() > 0) {                
                do {
                    validInput = true;
                    existNameChecker = 0;
                    System.out.print("Please enter the new style name: ");
                    scan.nextLine();
                    newName = scan.nextLine();
                    if (newName == null || newName.isEmpty()) {
                        validInput = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty\n" + FioreFlowershop.ConsoleColors.RESET);
                    } else {
                        for (int i = 1; i < itemCatalogue.getStyles().getSize() + 1; i++) {
                            if (itemCatalogue.getStyles().getItem(i).getName().equals(newName)) {
                                existNameChecker++;
                            }
                        }
                        if (existNameChecker > 0) {
                            System.out.println(FioreFlowershop.ConsoleColors.RED + "This name has been taken before please use another different name.\n" + FioreFlowershop.ConsoleColors.RESET);
                        }
                    }
                } while (validInput == false || existNameChecker > 0);

                do {
                    System.out.print("Enter the new price: ");
                    if (scan.hasNextDouble()) {
                        isDouble = true;
                        newPrice = scan.nextDouble();
                    } else {
                        isDouble = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number(can include decimal) only." + FioreFlowershop.ConsoleColors.RESET);
                        scan.next();
                    }
                } while (!(isDouble));
            } else if (itemType == 2 && itemCatalogue.getSizes().getSize() > 0) {
                scan.nextLine();
                do {
                    validInput = true;
                    existNameChecker = 0;
                    System.out.print("Please enter the new size:");
                    newName = scan.nextLine();
                    if (newName == null || newName.isEmpty()) {
                        validInput = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty\n" + FioreFlowershop.ConsoleColors.RESET);
                    } else {
                        for (int i = 1; i < itemCatalogue.getSizes().getSize() + 1; i++) {

                            if (itemCatalogue.getSizes().getItem(i).getName().equals(newName)) {
                                existNameChecker++;
                            }
                        }
                        if (existNameChecker > 0) {
                            System.out.println(FioreFlowershop.ConsoleColors.RED + "This name has been taken before please use another different name.\n" + FioreFlowershop.ConsoleColors.RESET);
                        }
                    }
                } while (validInput == false || existNameChecker > 0);

                do {
                    System.out.print("Enter the new multiplier: ");
                    if (scan.hasNextInt()) {
                        newPrice = scan.nextInt();
                        isInteger = true;
                    } else {
                        isInteger = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only." + FioreFlowershop.ConsoleColors.RESET);
                        scan.next();
                    }
                } while (!(isInteger));
            } else if (itemType == 3) {
                do {
                    validInput = true;
                    existNameChecker = 0;
                    System.out.print("Please enter new season: ");
                    scan.nextLine();
                    newName = scan.nextLine();
                    if (newName == null || newName.isEmpty()) {
                        validInput = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty\n" + FioreFlowershop.ConsoleColors.RESET);
                    } else {
                        for (int i = 1; i < itemCatalogue.getSeason().getSize() + 1; i++) {
                            if (itemCatalogue.getSeason().getItem(i).getName().equals(newName)) {
                                existNameChecker++;
                            }
                        }
                        if (existNameChecker > 0) {
                            System.out.println(FioreFlowershop.ConsoleColors.RED + "This name has been taken before please use another different name.\n" + FioreFlowershop.ConsoleColors.RESET);
                        }
                    }
                } while (validInput == false || existNameChecker > 0);
            } else if (itemType == 4 && itemCatalogue.getFlowers().getSize() > 0 || itemType == 5 && itemCatalogue.getAccessories().getSize() > 0 || itemType == 6 && itemCatalogue.getFlowerPot().getSize() > 0) {
                do {
                    validInput = true;
                    existNameChecker = 0;
                    System.out.print("Please enter the new " + itemTypeString + " name:");
                    scan.nextLine();
                    newName = scan.nextLine();
                    if (newName == null || newName.isEmpty()) {
                        validInput = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty\n" + FioreFlowershop.ConsoleColors.RESET);
                    } else {
                        if (itemType == 4) {
                            for (int i = 1; i < itemCatalogue.getFlowers().getSize() + 1; i++) {
                                if (itemCatalogue.getFlowers().getItem(i).getName().equals(newName)) {
                                    existNameChecker++;
                                }
                            }
                        }else if(itemType == 5){
                            for (int i = 1; i < itemCatalogue.getAccessories().getSize() + 1; i++) {
                                if (itemCatalogue.getAccessories().getItem(i).getName().equals(newName)) {
                                    existNameChecker++;
                                }
                            }
                        }else if(itemType == 6){
                            for (int i = 1; i < itemCatalogue.getFlowerPot().getSize() + 1; i++) {
                                if (itemCatalogue.getFlowerPot().getItem(i).getName().equals(newName)) {
                                    existNameChecker++;
                                }
                            }
                        }
                        if (existNameChecker > 0) {
                            System.out.println(FioreFlowershop.ConsoleColors.RED + "This name has been taken before please use another different name.\n" + FioreFlowershop.ConsoleColors.RESET);
                        }
                    }
                } while (validInput == false || existNameChecker > 0);

                do {
                    System.out.print("Enter the new price: ");
                    if (scan.hasNextDouble()) {
                        isDouble = true;
                        newPrice = scan.nextDouble();
                    } else {
                        isDouble = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number(can include decimal) only." + FioreFlowershop.ConsoleColors.RESET);
                        scan.next();
                    }
                } while (!(isDouble));                
                
                do {
                    System.out.print("Enter the new quantity: ");
                    if (scan.hasNextInt()) {
                        itemNewQuantity = scan.nextInt();
                        isInteger = true;
                    } else {
                        isInteger = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only." + FioreFlowershop.ConsoleColors.RESET);
                        scan.next();
                    }
                } while (!(isInteger));
            }

            if (itemType == 1 && itemCatalogue.getStyles().getSize() > 0) {
                itemCatalogue.getStyles().getItem(itemIndex).setName(newName);
                itemCatalogue.getStyles().getItem(itemIndex).setPrice(newPrice);
            } else if (itemType == 2 && itemCatalogue.getSizes().getSize() > 0) {
                itemCatalogue.getSizes().getItem(itemIndex).setName(newName);
                itemCatalogue.getSizes().getItem(itemIndex).setPrice(newPrice);
            } else if (itemType == 3 && itemCatalogue.getSeason().getSize() > 0) {
                itemCatalogue.getSeason().getItem(itemIndex).setName(newName);
            } else if (itemType == 4 && itemCatalogue.getSeason().getSize() > 0) {
                itemCatalogue.getFlowers().getItem(itemIndex).setName(newName);
                itemCatalogue.getFlowers().getItem(itemIndex).setPrice(newPrice);
                itemCatalogue.getFlowers().getItem(itemIndex).setQuantity(itemNewQuantity);
            } else if (itemType == 5 && itemCatalogue.getAccessories().getSize() > 0) {
                itemCatalogue.getAccessories().getItem(itemIndex).setName(newName);
                itemCatalogue.getAccessories().getItem(itemIndex).setPrice(newPrice);
                itemCatalogue.getAccessories().getItem(itemIndex).setQuantity(itemNewQuantity);
            } else if (itemType == 6 && itemCatalogue.getFlowerPot().getSize() > 0) {
                itemCatalogue.getFlowerPot().getItem(itemIndex).setName(newName);
                itemCatalogue.getFlowerPot().getItem(itemIndex).setPrice(newPrice);
                itemCatalogue.getFlowerPot().getItem(itemIndex).setQuantity(itemNewQuantity);
            }
            System.out.println(FioreFlowershop.ConsoleColors.GREEN + "The " + itemTypeString + " has been successfuly updated... " + FioreFlowershop.ConsoleColors.RESET);
            catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
        } else {
            System.out.println(FioreFlowershop.ConsoleColors.RED + "There are no records found ... " + FioreFlowershop.ConsoleColors.RESET);
            catalogMaintenance(normalPackage, discountedPackage, itemCatalogue);
        }
    }

    //print Item
    public static void printItem(ItemCatalogue itemCatalogue, int type) {
        if (type == 1) {
            for (int i = 1; i <= itemCatalogue.getStyles().getSize(); i++) {
                System.out.print(FioreFlowershop.ConsoleColors.GREEN + "[" + i + "]" + FioreFlowershop.ConsoleColors.RESET);
                System.out.printf(" %s: RM%.2f\n", itemCatalogue.getStyles().getItem(i).getName(), itemCatalogue.getStyles().getItem(i).getPrice());
            }
        } else if (type == 2) {
            for (int i = 1; i <= itemCatalogue.getSizes().getSize(); i++) {
                System.out.print(FioreFlowershop.ConsoleColors.GREEN + "[" + i + "]" + FioreFlowershop.ConsoleColors.RESET);
                System.out.printf(" %s: x %.0f\n", itemCatalogue.getSizes().getItem(i).getName(), itemCatalogue.getSizes().getItem(i).getPrice());
            }
        } else if (type == 3) {
            for (int i = 1; i <= itemCatalogue.getFlowers().getSize(); i++) {
                System.out.print(FioreFlowershop.ConsoleColors.GREEN + "[" + i + "]" + FioreFlowershop.ConsoleColors.RESET);
                System.out.printf(" %s: RM%.2f\n", itemCatalogue.getFlowers().getItem(i).getName(), itemCatalogue.getFlowers().getItem(i).getPrice());
            }
        } else if (type == 4) {
            for (int i = 1; i <= itemCatalogue.getAccessories().getSize(); i++) {
                System.out.print(FioreFlowershop.ConsoleColors.GREEN + "[" + i + "]" + FioreFlowershop.ConsoleColors.RESET);
                System.out.printf(" %s: RM%.2f\n", itemCatalogue.getAccessories().getItem(i).getName(), itemCatalogue.getAccessories().getItem(i).getPrice());
            }
        } else if (type == 5) {
            for (int i = 1; i <= itemCatalogue.getSeason().getSize(); i++) {
                System.out.print(FioreFlowershop.ConsoleColors.GREEN + "[" + i + "]" + FioreFlowershop.ConsoleColors.RESET);
                System.out.printf(" %s\n", itemCatalogue.getSeason().getItem(i));
            }
        } else if (type == 6) {
            for (int i = 1; i <= itemCatalogue.getFlowerPot().getSize(); i++) {
                System.out.print(FioreFlowershop.ConsoleColors.GREEN + "[" + i + "]" + FioreFlowershop.ConsoleColors.RESET);
                System.out.printf(" %s: RM%.2f\n", itemCatalogue.getFlowerPot().getItem(i).getName(), itemCatalogue.getFlowerPot().getItem(i).getPrice());
            }
        }
    }
}
