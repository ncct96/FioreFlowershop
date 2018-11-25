/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.modal.CatalogPackage;
import com.mycompany.fioreflowershop.adt.ArrayList;
import java.util.Calendar;
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

    //Display item type (Add)
    public static void productType(String navigationTitle, ArrayList<CatalogPackage> normalPackage, ArrayList<CatalogPackage> discountedPackage) {
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
            String name, style, flower, acessories, productTypesString = "", promoMonth = "";
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

                //Ask user to add the package into the monthly promotion catalog ?
                char confirmationOption;
                int discountRate = 0, promoYear = 0, month = 0;

                System.out.print("Do you wish add this package into Monthly Promotion Catalog?\nPress 'y/Y' to add in, other key to cancel: ");
                confirmationOption = scan.next().charAt(0);

                if (confirmationOption == 'y' || confirmationOption == 'Y') {
                    do {
                        System.out.print("Please enter the year: ");
                        if (scan.hasNextInt()) {
                            promoYear = scan.nextInt();
                            isInteger = true;
                        } else {
                            isInteger = false;
                            System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.");
                            scan.next();
                        }
                    } while (!(isInteger) || promoYear < 2018 || promoYear > 3000);

                    do {
                        System.out.print("Please enter the month: ");
                        if (scan.hasNextInt()) {
                            month = scan.nextInt();
                            isInteger = true;
                        } else {
                            isInteger = false;
                            System.out.print(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.");
                            scan.next();
                        }
                    } while (!(isInteger) || month < 1 || month > 12);

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
                }

                //Convert the product type number to string
                if (productTypes == 1) {
                    productTypesString = "Fresh flower";
                } else if (productTypes == 2) {
                    productTypesString = "Bouquets";
                } else if (productTypes == 3) {
                    productTypesString = "Flower arrangement";
                }

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

                CatalogPackage catalogPackage = new CatalogPackage(name, style, size, flower, acessories, productTypesString, promoMonth, promoYear, quantity, price, discountRate);

                if (discountRate > 0) {
                    discountedPackage.add(catalogPackage);
                } else {
                    normalPackage.add(catalogPackage);
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
    public static void displayCatalogType(String navigationTitle, ArrayList<CatalogPackage> normalPackage, ArrayList<CatalogPackage> discountedPackage) {
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
        } else if (userMenuOption == 1 && navigationTitle.equals("Display catalog")) { //Display the normal catalog            
            displayNormalCatalog(navigationTitle, normalPackage, discountedPackage);
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
        } else if (userMenuOption == 2 && navigationTitle.equals("Display catalog")) { //Display the monthly promotion catalog
            displayPromotionCatalog(navigationTitle, normalPackage, discountedPackage);
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
        } else if (userMenuOption == 1 && navigationTitle.equals("Edit catalog")) { //Go to edit catalog(Normal catalog)
            displayNormalCatalog(navigationTitle, normalPackage, discountedPackage);
            editCatalog(navigationTitle, normalPackage, discountedPackage, 1);
        } else if (userMenuOption == 2 && navigationTitle.equals("Edit catalog")) { //Go to edit catalog(monthly ptomotion catalog)
            displayPromotionCatalog(navigationTitle, normalPackage, discountedPackage);
            editCatalog(navigationTitle, normalPackage, discountedPackage, 2);
        } 
    }

    //Display normal catalog
    public static void displayNormalCatalog(String navigationTitle, ArrayList<CatalogPackage> normalPackage, ArrayList<CatalogPackage> discountedPackage) {
        int freshFlowerCounter = 0, bouquetsCounter = 0, flowerArrangementCounter = 0;
        CatalogPackage catalogPackage = new CatalogPackage();
        System.out.println("\nDisplay catalog - normal catalog");
        System.out.println("================================================================================================");
        System.out.println("Product types\t\t\t\tQuantity\tPrice\t\t\tDiscounted price");
        if (normalPackage.getTotalEntries() != 0) {
            System.out.println("Fresh Flower");
            System.out.println("==================");
            for (int i = 1; i < normalPackage.getTotalEntries() + 1; i++) {
                catalogPackage = normalPackage.getItem(i);
                if (catalogPackage.getProductType().equals("Fresh flower")) {
                    System.out.printf("ID : %d - Name : %s\n", i, catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t  - \n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice());
                    freshFlowerCounter++;
                }

                if (freshFlowerCounter == 0 && i == normalPackage.getTotalEntries()) {
                    System.out.println("There are no record found\n");
                }
            }

            System.out.println("\nBouquets");
            System.out.println("==================");
            for (int i = 1; i < normalPackage.getTotalEntries() + 1; i++) {
                catalogPackage = normalPackage.getItem(i);
                if (catalogPackage.getProductType().equals("Bouquets")) {
                    System.out.printf("ID : %d - Name : %s\n", i, catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t  - \n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice());
                    bouquetsCounter++;
                }

                if (bouquetsCounter == 0 && i == normalPackage.getTotalEntries()) {
                    System.out.println("There are no record found\n");
                }
            }

            System.out.println("\nFlower Arrangement");
            System.out.println("==================");
            for (int i = 1; i < normalPackage.getTotalEntries() + 1; i++) {
                catalogPackage = normalPackage.getItem(i);
                if (catalogPackage.getProductType().equals("Flower arrangement")) {
                    System.out.printf("ID : %d - Name : %s\n", i, catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t  - \n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice());
                    flowerArrangementCounter++;
                }
                if (flowerArrangementCounter == 0 && i == normalPackage.getTotalEntries()) {
                    System.out.println("There are no record found\n");
                }
            }
        }
    }

    public static void displayPromotionCatalog(String navigationTitle, ArrayList<CatalogPackage> normalPackage, ArrayList<CatalogPackage> discountedPackage) {
        //Get current month & year
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        String curentMonthString = "";

        //Counter
        int discountFreshFlowerCounter = 0, discountBouquetsCounter = 0, discountFlowerArrangement = 0;
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
        CatalogPackage catalogPackage = new CatalogPackage();
        //Displaying monthly promotion
        System.out.println("\nDisplay catalog - Monthly promotion catalog" + "( " + year + " - " + curentMonthString + " )");
        System.out.println("================================================================================================");
        System.out.println("Product types\t\t\t\tQuantity\tPrice\t\t\tDiscounted price");
        if (discountedPackage.getTotalEntries() != 0) {
            System.out.println("Fresh Flower");
            System.out.println("==================");
            for (int i = 1; i < discountedPackage.getTotalEntries() + 1; i++) {
                catalogPackage = discountedPackage.getItem(i);
                double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                if (catalogPackage.getProductType().equals("Fresh flower") && catalogPackage.getPromoMonth().equals(curentMonthString) && catalogPackage.getPromoYear() == year) {
                    System.out.printf("ID : %d - Name : %s\n", i, catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
                    discountFreshFlowerCounter++;
                }
                if (discountFreshFlowerCounter == 0 && i == discountedPackage.getTotalEntries()) {
                    System.out.println("There are no record found\n");
                }
            }

            System.out.println("\nBouquets");
            System.out.println("==================");
            for (int i = 1; i < discountedPackage.getTotalEntries() + 1; i++) {
                catalogPackage = discountedPackage.getItem(i);
                double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                if (catalogPackage.getProductType().equals("Bouquets") && catalogPackage.getPromoMonth().equals(curentMonthString) && catalogPackage.getPromoYear() == year) {
                    System.out.printf("ID : %d - Name : %s\n", i, catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
                    discountBouquetsCounter++;
                }
                if (discountBouquetsCounter == 0 && i == discountedPackage.getTotalEntries()) {
                    System.out.println("There are no record found\n");
                }
            }

            System.out.println("\nFlower Arrangement");
            System.out.println("==================");
            for (int i = 1; i < discountedPackage.getTotalEntries() + 1; i++) {
                catalogPackage = discountedPackage.getItem(i);
                double discountRate = (double) ((100 - catalogPackage.getDiscountRate()) * catalogPackage.getPrice() / 100);
                if (catalogPackage.getProductType().equals("Flower arrangement") && catalogPackage.getPromoMonth().equals(curentMonthString) && catalogPackage.getPromoYear() == year) {
                    System.out.printf("ID : %d - Name : %s\n", i, catalogPackage.getName());
                    System.out.printf("%s,%s,%s,%s \t\t\t\t%d\t RM%.2f\t\t RM%.2f\n\n", catalogPackage.getStyle(), catalogPackage.getSize(), catalogPackage.getFlower(), catalogPackage.getAccessory(), catalogPackage.getQuantity(), catalogPackage.getPrice(), discountRate);
                    discountFlowerArrangement++;
                }
                if (discountFlowerArrangement == 0 && i == discountedPackage.getTotalEntries()) {
                    System.out.println("There are no record found\n");
                }
            }
        }
    }

    //Edit Catalog
    public static void editCatalog(String navigationTitle, ArrayList<CatalogPackage> normalPackage, ArrayList<CatalogPackage> discountedPackage, int arrayType) {
        //Data decleration
        int productId = 0, catalogSize = 0, productField = 0, sizeOption = 0, productTypes = 0, quantity = 0, discountRate = 0, promoYear = 0, month = 0;
        String name, style, flower, acessories, size = "", promoMonth = "";
        boolean validInput, isDouble;
        double price = 0;

        //Check for normal catalog size or promotion catalog size
        if (arrayType == 1) {
            catalogSize = normalPackage.getTotalEntries();
        } else if (arrayType == 2) {
            catalogSize = discountedPackage.getTotalEntries();
        }

        CatalogPackage catalogPackage = new CatalogPackage();

        //Ask user to choose which product need to be edit
        do {
            System.out.println("Please enter the ID : ");
            if (scan.hasNextInt()) {
                productId = scan.nextInt();
                isInteger = true;
            } else {
                isInteger = false;
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.");
                scan.next();
            }
        } while (!(isInteger) || productId < 1 || productId > catalogSize);

        //Edit each of the product field(Normal Catalog Editor)
        if (arrayType == 1) {
            do {
                System.out.print("Which part of the product you wish to edit?\n"
                        + "1. Name\n2. Style\n3. Size\n4. Flower\n5. Accessory\n6. Product type\n7. Quantity\n8. Price\n9. All\n10. Cancel\n");
                if (scan.hasNextInt()) {
                    productField = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.");
                    scan.next();
                }
            } while (!(isInteger) || productField < 1 || productField > 10);

            if (productField == 1) {
                scan.nextLine(); //To read the input, if no directly skip the first question
                do {
                    validInput = true;
                    System.out.print("Please enter new package name: ");
                    name = scan.nextLine();
                    if (name == null || name.isEmpty()) {
                        validInput = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty");
                    }
                } while (validInput == false);
                catalogPackage = normalPackage.getItem(productId);
                catalogPackage.setName(name);
                FioreFlowershop.manager();
            } else if (productField == 2) {
                scan.nextLine(); //To read the input
                do {
                    validInput = true;
                    System.out.print("Please enter new style: ");
                    style = scan.nextLine();
                    if (style == null || style.isEmpty()) {
                        validInput = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty");
                    }
                } while (validInput == false);
                catalogPackage = normalPackage.getItem(productId);
                catalogPackage.setStyle(style);
                FioreFlowershop.manager();
            } else if (productField == 3) {
                do {
                    System.out.print("Please enter new size (1 = Small, 2 = Medium, 3 = Large): ");
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
                catalogPackage = normalPackage.getItem(productId);
                catalogPackage.setSize(size);
                FioreFlowershop.manager();
            } else if (productField == 4) {
                scan.nextLine(); // To read next input
                do {
                    validInput = true;
                    System.out.print("Please enter new flower: ");
                    flower = scan.nextLine();

                    if (flower == null || flower.isEmpty()) {
                        validInput = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty");
                    }
                } while (validInput == false);
                catalogPackage = normalPackage.getItem(productId);
                catalogPackage.setFlower(flower);
                FioreFlowershop.manager();
            } else if (productField == 5) {
                scan.nextLine(); //To read next input
                do {
                    validInput = true;
                    System.out.print("Please enter new acessories: ");
                    acessories = scan.nextLine();
                    if (acessories == null || acessories.isEmpty()) {
                        validInput = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty");
                    }
                } while (validInput == false);
                catalogPackage = normalPackage.getItem(productId);
                catalogPackage.setAccessory(acessories);
                FioreFlowershop.manager();
            } else if (productField == 6) {
                do {
                    System.out.println("Item type list\n1. Fresh flowers\n2. Bouquets\n3. Flower arrangement");
                    System.out.print("Please enter the product type(1-3): ");
                    if (scan.hasNextInt()) {
                        productTypes = scan.nextInt();
                        isInteger = true;
                    } else {
                        isInteger = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.");
                        scan.next();
                    }
                } while (!(isInteger) || productTypes < 1 || productTypes > 3);
                catalogPackage = normalPackage.getItem(productId);
                if (productTypes == 1) {
                    catalogPackage.setProductType("Fresh flower");
                } else if (productTypes == 2) {
                    catalogPackage.setProductType("Bouquets");
                } else if (productTypes == 3) {
                    catalogPackage.setProductType("Flower arrangement");
                }
                FioreFlowershop.manager();
            } else if (productField == 7) {
                do {
                    System.out.print("Please enter new quantity: ");
                    if (scan.hasNextInt()) {
                        quantity = scan.nextInt();
                        isInteger = true;
                    } else {
                        isInteger = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.");
                        scan.next();
                    }
                } while (!(isInteger));
                catalogPackage = normalPackage.getItem(productId);
                catalogPackage.setQuantity(quantity);
                FioreFlowershop.manager();
            } else if (productField == 8) {
                do {
                    System.out.print("Please enter new price: ");
                    if (scan.hasNextDouble()) {
                        price = scan.nextDouble();
                        isDouble = true;
                    } else {
                        isDouble = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.");
                        scan.next();
                    }
                } while (!(isDouble));
                catalogPackage = normalPackage.getItem(productId);
                catalogPackage.setPrice(price);
                FioreFlowershop.manager();
            } else if (productField == 9) {
                scan.nextLine(); //To read the input, if no directly skip the first question
                do {
                    validInput = true;
                    System.out.print("Please enter new package name: ");
                    name = scan.nextLine();
                    if (name == null || name.isEmpty()) {
                        validInput = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty");
                    }
                } while (validInput == false);

                do {
                    validInput = true;
                    System.out.print("Please enter new style: ");
                    style = scan.nextLine();
                    if (style == null || style.isEmpty()) {
                        validInput = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty");
                    }
                } while (validInput == false);

                do {
                    System.out.print("Please enter new size (1 = Small, 2 = Medium, 3 = Large): ");
                    if (scan.hasNextInt()) {
                        sizeOption = scan.nextInt();
                        isInteger = true;
                    } else {
                        isInteger = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.");
                        scan.next();
                    }
                } while (!(isInteger) || sizeOption < 1 || sizeOption > 3);

                scan.nextLine(); // To read next input
                do {
                    validInput = true;
                    System.out.print("Please enter new flower: ");
                    flower = scan.nextLine();

                    if (flower == null || flower.isEmpty()) {
                        validInput = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty");
                    }
                } while (validInput == false);

                do {
                    validInput = true;
                    System.out.print("Please enter new acessories: ");
                    acessories = scan.nextLine();
                    if (acessories == null || acessories.isEmpty()) {
                        validInput = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty");
                    }
                } while (validInput == false);

                do {
                    System.out.println("Item type list\n1. Fresh flowers\n2. Bouquets\n3. Flower arrangement");
                    System.out.print("Please enter the product type(1-3): ");
                    if (scan.hasNextInt()) {
                        productTypes = scan.nextInt();
                        isInteger = true;
                    } else {
                        isInteger = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.");
                        scan.next();
                    }
                } while (!(isInteger) || productTypes < 1 || productTypes > 3);

                do {
                    System.out.print("Please enter new quantity: ");
                    if (scan.hasNextInt()) {
                        quantity = scan.nextInt();
                        isInteger = true;
                    } else {
                        isInteger = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.");
                        scan.next();
                    }
                } while (!(isInteger));

                do {
                    System.out.print("Please enter new price: ");
                    if (scan.hasNextDouble()) {
                        price = scan.nextDouble();
                        isDouble = true;
                    } else {
                        isDouble = false;
                        System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.");
                        scan.next();
                    }
                } while (!(isDouble));

                //Convert integer to string
                if (sizeOption == 1) {
                    size = "Small";
                } else if (sizeOption == 2) {
                    size = "Medium";
                } else if (sizeOption == 3) {
                    size = "Large";
                }

                //Save the update
                catalogPackage = normalPackage.getItem(productId);
                catalogPackage.setName(name);
                catalogPackage.setStyle(style);
                catalogPackage.setSize(size);
                catalogPackage.setFlower(flower);
                catalogPackage.setAccessory(acessories);
                if (productTypes == 1) {
                    catalogPackage.setProductType("Fresh flower");
                } else if (productTypes == 2) {
                    catalogPackage.setProductType("Bouquets");
                } else if (productTypes == 3) {
                    catalogPackage.setProductType("Flower arrangement");
                }
                catalogPackage.setQuantity(quantity);
                catalogPackage.setPrice(price);
                FioreFlowershop.manager();
            } else if (productField == 10) {
                FioreFlowershop.manager();
            }
        } else if (arrayType == 2) {
            do {
                System.out.print("Which part of the product you wish to edit?\n"
                        + "1. Name\n2. Style\n3. Size\n4. Flower\n5. Accessory\n6. Product type\n7. Promotion month\n8. Promotion year\n9. Quantity\n10. Price\n11. Discount rate\n12. All\n13. Cancel\n");
                if (scan.hasNextInt()) {
                    productField = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.");
                    scan.next();
                }
            } while (!(isInteger) || productField < 1 || productField > 13);
        }

        //Edit for each product field (Monthly Promotion Catalog)
        if (productField == 1) {
            scan.nextLine(); //To read the input, if no directly skip the first question
            do {
                validInput = true;
                System.out.print("Please enter new package name: ");
                name = scan.nextLine();
                if (name == null || name.isEmpty()) {
                    validInput = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty");
                }
            } while (validInput == false);
            catalogPackage = discountedPackage.getItem(productId);
            catalogPackage.setName(name);
            FioreFlowershop.manager();
        } else if (productField == 2) {
            scan.nextLine(); //To read the input
            do {
                validInput = true;
                System.out.print("Please enter new style: ");
                style = scan.nextLine();
                if (style == null || style.isEmpty()) {
                    validInput = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty");
                }
            } while (validInput == false);
            catalogPackage = discountedPackage.getItem(productId);
            catalogPackage.setStyle(style);
            FioreFlowershop.manager();
        } else if (productField == 3) {
            do {
                System.out.print("Please enter new size (1 = Small, 2 = Medium, 3 = Large): ");
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
            catalogPackage = discountedPackage.getItem(productId);
            catalogPackage.setSize(size);
            FioreFlowershop.manager();
        } else if (productField == 4) {
            scan.nextLine(); // To read next input
            do {
                validInput = true;
                System.out.print("Please enter new flower: ");
                flower = scan.nextLine();

                if (flower == null || flower.isEmpty()) {
                    validInput = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty");
                }
            } while (validInput == false);
            catalogPackage = discountedPackage.getItem(productId);
            catalogPackage.setFlower(flower);
            FioreFlowershop.manager();
        } else if (productField == 5) {
            scan.nextLine(); //To read next input
            do {
                validInput = true;
                System.out.print("Please enter new acessories: ");
                acessories = scan.nextLine();
                if (acessories == null || acessories.isEmpty()) {
                    validInput = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty");
                }
            } while (validInput == false);
            catalogPackage = discountedPackage.getItem(productId);
            catalogPackage.setAccessory(acessories);
            FioreFlowershop.manager();
        } else if (productField == 6) {
            do {
                System.out.println("Item type list\n1. Fresh flowers\n2. Bouquets\n3. Flower arrangement");
                System.out.print("Please enter the product type(1-3): ");
                if (scan.hasNextInt()) {
                    productTypes = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.");
                    scan.next();
                }
            } while (!(isInteger) || productTypes < 1 || productTypes > 3);
            catalogPackage = discountedPackage.getItem(productId);
            if (productTypes == 1) {
                catalogPackage.setProductType("Fresh flower");
            } else if (productTypes == 2) {
                catalogPackage.setProductType("Bouquets");
            } else if (productTypes == 3) {
                catalogPackage.setProductType("Flower arrangement");
            }
            FioreFlowershop.manager();
        } else if (productField == 7) {
            do {
                System.out.print("Please enter the month: ");
                if (scan.hasNextInt()) {
                    month = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.print(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.");
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

            catalogPackage = discountedPackage.getItem(productId);
            catalogPackage.setPromoMonth(promoMonth);
            FioreFlowershop.manager();
        } else if (productField == 8) {
            do {
                System.out.print("Please enter the year: ");
                if (scan.hasNextInt()) {
                    promoYear = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.");
                    scan.next();
                }
            } while (!(isInteger) || promoYear < 2018 || promoYear > 3000);
            catalogPackage = discountedPackage.getItem(productId);
            catalogPackage.setPromoYear(promoYear);
            FioreFlowershop.manager();
        } else if (productField == 9) {
            do {
                System.out.print("Please enter new quantity: ");
                if (scan.hasNextInt()) {
                    quantity = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.");
                    scan.next();
                }
            } while (!(isInteger));
            catalogPackage = discountedPackage.getItem(productId);
            catalogPackage.setQuantity(quantity);
            FioreFlowershop.manager();
        } else if (productField == 10) {
            do {
                System.out.print("Please enter new price: ");
                if (scan.hasNextDouble()) {
                    price = scan.nextDouble();
                    isDouble = true;
                } else {
                    isDouble = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.");
                    scan.next();
                }
            } while (!(isDouble));
            catalogPackage = discountedPackage.getItem(productId);
            catalogPackage.setPrice(price);
            FioreFlowershop.manager();
        } else if (productField == 11) {
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
            catalogPackage = discountedPackage.getItem(productId);
            catalogPackage.setDiscountRate(discountRate);
            FioreFlowershop.manager();
        } else if (productField == 12) {
            scan.nextLine(); //To read the input, if no directly skip the first question
            do {
                validInput = true;
                System.out.print("Please enter new package name: ");
                name = scan.nextLine();
                if (name == null || name.isEmpty()) {
                    validInput = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty");
                }
            } while (validInput == false);

            do {
                validInput = true;
                System.out.print("Please enter new style: ");
                style = scan.nextLine();
                if (style == null || style.isEmpty()) {
                    validInput = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty");
                }
            } while (validInput == false);

            do {
                System.out.print("Please enter new size (1 = Small, 2 = Medium, 3 = Large): ");
                if (scan.hasNextInt()) {
                    sizeOption = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.");
                    scan.next();
                }
            } while (!(isInteger) || sizeOption < 1 || sizeOption > 3);

            scan.nextLine(); // To read next input
            do {
                validInput = true;
                System.out.print("Please enter new flower: ");
                flower = scan.nextLine();

                if (flower == null || flower.isEmpty()) {
                    validInput = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty");
                }
            } while (validInput == false);

            do {
                validInput = true;
                System.out.print("Please enter new acessories: ");
                acessories = scan.nextLine();
                if (acessories == null || acessories.isEmpty()) {
                    validInput = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "This field cannot be empty");
                }
            } while (validInput == false);

            do {
                System.out.println("Item type list\n1. Fresh flowers\n2. Bouquets\n3. Flower arrangement");
                System.out.print("Please enter the product type(1-3): ");
                if (scan.hasNextInt()) {
                    productTypes = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.");
                    scan.next();
                }
            } while (!(isInteger) || productTypes < 1 || productTypes > 3);

            do {
                System.out.print("Please enter the month: ");
                if (scan.hasNextInt()) {
                    month = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.print(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.");
                    scan.next();
                }
            } while (!(isInteger) || month < 1 || month > 12);

            do {
                System.out.print("Please enter the year: ");
                if (scan.hasNextInt()) {
                    promoYear = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.");
                    scan.next();
                }
            } while (!(isInteger) || promoYear < 2018 || promoYear > 3000);

            do {
                System.out.print("Please enter new quantity: ");
                if (scan.hasNextInt()) {
                    quantity = scan.nextInt();
                    isInteger = true;
                } else {
                    isInteger = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the whole number only.");
                    scan.next();
                }
            } while (!(isInteger));

            do {
                System.out.print("Please enter new price: ");
                if (scan.hasNextDouble()) {
                    price = scan.nextDouble();
                    isDouble = true;
                } else {
                    isDouble = false;
                    System.out.println(FioreFlowershop.ConsoleColors.RED + "Please enter the number only.");
                    scan.next();
                }
            } while (!(isDouble));

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

            //Convert integer to string
            if (sizeOption == 1) {
                size = "Small";
            } else if (sizeOption == 2) {
                size = "Medium";
            } else if (sizeOption == 3) {
                size = "Large";
            }

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

            //Store update
            catalogPackage = discountedPackage.getItem(productId);
            catalogPackage.setName(name);
            catalogPackage.setStyle(style);
            catalogPackage.setSize(size);
            catalogPackage.setFlower(flower);
            catalogPackage.setAccessory(acessories);
            if (productTypes == 1) {
                catalogPackage.setProductType("Fresh flower");
            } else if (productTypes == 2) {
                catalogPackage.setProductType("Bouquets");
            } else if (productTypes == 3) {
                catalogPackage.setProductType("Flower arrangement");
            }
            catalogPackage.setPromoMonth(promoMonth);
            catalogPackage.setPromoYear(promoYear);
            catalogPackage.setQuantity(quantity);
            catalogPackage.setPrice(price);
            catalogPackage.setDiscountRate(discountRate);
            FioreFlowershop.manager();
        } else if (productField == 13) {
            FioreFlowershop.manager();
        }
    }    
}
