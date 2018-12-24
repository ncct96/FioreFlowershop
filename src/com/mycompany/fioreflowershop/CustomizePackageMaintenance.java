/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.adt.*;
import com.mycompany.fioreflowershop.modal.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Chiu Peeng
 */
public class CustomizePackageMaintenance {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    public static void printItem(ItemCatalogue itemCatalogue, int type) {
        if (type == 1) {
            for (int i = 1; i <= itemCatalogue.getStyles().getSize(); i++) {
                System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                System.out.printf(" %s: RM%.2f\n", itemCatalogue.getStyles().getItem(i).getName(), itemCatalogue.getStyles().getItem(i).getPrice());
            }
        } else if (type == 2) {
            for (int i = 1; i <= itemCatalogue.getSizes().getSize(); i++) {
                System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                System.out.printf(" %s: x %.0f\n", itemCatalogue.getSizes().getItem(i).getName(), itemCatalogue.getSizes().getItem(i).getPrice());
            }
        } else if (type == 3) {
            for (int i = 1; i <= itemCatalogue.getFlowers().getSize(); i++) {
                System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                System.out.printf(" %s: RM%.2f\n", itemCatalogue.getFlowers().getItem(i).getName(), itemCatalogue.getFlowers().getItem(i).getPrice());
            }
        } else if (type == 4) {
            for (int i = 1; i <= itemCatalogue.getAccessories().getSize(); i++) {
                System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                System.out.printf(" %s: RM%.2f\n", itemCatalogue.getAccessories().getItem(i).getName(), itemCatalogue.getAccessories().getItem(i).getPrice());
            }
        }
    }

    public static void itemsMenu(ItemCatalogue itemCatalogue, CustomizePackageQueueInterface<CustomizedPackage> customizedPackages) {
        while (true) {
            System.out.println("\nWhat do you wish to do?");
            System.out.println(ANSI_GREEN + "[1] " + ANSI_RESET + "Update Stock Quantity");
            System.out.println(ANSI_GREEN + "[2] " + ANSI_RESET + "Add New Items");
            System.out.println(ANSI_GREEN + "[3] " + ANSI_RESET + "Delete Items");
            System.out.println(ANSI_GREEN + "[4] " + ANSI_RESET + "Rearrange Catalogue");
            System.out.println(ANSI_GREEN + "[5] " + ANSI_RESET + "Return to previous menu");
            System.out.print("Selection: ");
            Scanner scan = new Scanner(System.in);
            int selection = scan.nextInt();
            if (selection == 1) {
                updateStock(itemCatalogue);
            } else if (selection == 2) {
                addItems(itemCatalogue);
            } else if (selection == 3) {
                deleteItems(itemCatalogue);
            } else if (selection == 4) {
                rearrangeCatalogue(itemCatalogue);
            } else {
                break;
            }
        }
    }

    public static void updateOrders(CustomizePackageQueueInterface<CustomizedPackage> customizedPackages, OrderListInterface<CustomizedPackage> readyOrders) {
        while (true) {
            Scanner scan = new Scanner(System.in);
            char selection;
            if (!customizedPackages.isEmpty()) {
                System.out.println("\n==================================");
                System.out.println("Next Order");
                System.out.println("==================================");
                System.out.println("Job ID: " + customizedPackages.getFirstPackage().getOrderID());
                System.out.println("Delivery Date: " + customizedPackages.getFirstPackage().getDeliveryDateString());
                System.out.println("Priority: " + customizedPackages.getFirstPackage().getPriority());
                System.out.println("Arrangement: " + customizedPackages.getFirstPackage().getSize() + " " + customizedPackages.getFirstPackage().getStyle() + " " + customizedPackages.getFirstPackage().getFlower());
                System.out.println("Accessories: " + customizedPackages.getFirstPackage().getAccessory());
                System.out.println("==================================");
                do {
                    System.out.print("Complete order?" + ANSI_GREEN + "[Y/N]" + ANSI_RESET + " ");
                    selection = Character.toUpperCase(scan.next().charAt(0));
                } while (selection != 'Y' && selection != 'N');
                if (selection == 'Y') {
                    System.out.println("Order marked as ready to deliver!");

                    readyOrders.addOrder(customizedPackages.removeCustomizedPackage());

                    if (customizedPackages.isEmpty()) {
                        System.out.println("No more orders is queue!\n");
                        break;
                    }
                    do {
                        System.out.print("Continue to next order?" + ANSI_GREEN + "[Y/N]" + ANSI_RESET + " ");
                        selection = Character.toUpperCase(scan.next().charAt(0));
                    } while (selection != 'Y' && selection != 'N');
                    if (selection == 'N') {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }

    public static void updateStock(ItemCatalogue itemCatalogue) {
        Scanner scan = new Scanner(System.in);
        int selection;
        int newQuantity = 0;
        while (true) {
            try {
                do {
                    System.out.println("\nSelect the type of customization item");
                    System.out.println(ANSI_GREEN + "[1] " + ANSI_RESET + "Flowers");
                    System.out.println(ANSI_GREEN + "[2] " + ANSI_RESET + "Accessories");
                    System.out.println(ANSI_GREEN + "[3] " + ANSI_RESET + "Return to previous menu");
                    selection = scan.nextInt();
                } while (selection < 1 || selection > 3);
                break;
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                scan.next();
            }
        }
        if (selection == 1) {
            for (int i = 1; i <= itemCatalogue.getFlowers().getSize(); i++) {
                System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                System.out.printf(" %s: %d\n", itemCatalogue.getFlowers().getItem(i).getName(), itemCatalogue.getFlowers().getItem(i).getQuantity());
            }
            System.out.println(ANSI_GREEN + "[0]" + ANSI_RESET + "[ BACK ]");
            while (true) {
                try {
                    do {
                        System.out.print("Select the item to update: ");
                        selection = scan.nextInt();
                    } while (selection < 0 || selection > itemCatalogue.getFlowers().getSize());

                    if (selection == 0) {
                        break;
                    }

                    do {
                        System.out.print("Enter the new stock quantity: ");
                        newQuantity = scan.nextInt();
                    } while (selection < 0);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                    scan.next();
                }
            }
            if (selection != 0) {
                System.out.println("Stock quantity successfully updated!");
                itemCatalogue.getFlowers().getItem(selection).setQuantity(newQuantity);
            }
        } else if (selection == 2) {
            for (int i = 2; i <= itemCatalogue.getAccessories().getSize(); i++) {
                System.out.print(ANSI_GREEN + "[" + (i - 1) + "]" + ANSI_RESET);
                System.out.printf(" %s: %d\n", itemCatalogue.getAccessories().getItem(i).getName(), itemCatalogue.getAccessories().getItem(i).getQuantity());
            }
            System.out.println(ANSI_GREEN + "[0]" + ANSI_RESET + "[ BACK ]");
            while (true) {
                try {
                    do {
                        System.out.print("Select the item to update: ");
                        selection = scan.nextInt();
                    } while (selection < 0 || selection > itemCatalogue.getAccessories().getSize());

                    if (selection == 0) {
                        break;
                    }

                    do {
                        System.out.print("Enter the new stock quantity: ");
                        newQuantity = scan.nextInt();
                    } while (selection < 0);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                    scan.next();
                }
            }
            if (selection != 0) {
                System.out.println("Stock quantity successfully updated!");
                itemCatalogue.getFlowers().getItem(selection + 1).setQuantity(newQuantity);
            }
        }
    }

    public static int selectItem() {
        Scanner scan = new Scanner(System.in);
        int selection;
        while (true) {
            try {
                do {
                    System.out.println(ANSI_GREEN + "[1] " + ANSI_RESET + "Arrangement Styles");
                    System.out.println(ANSI_GREEN + "[2] " + ANSI_RESET + "Arrangement Sizes");
                    System.out.println(ANSI_GREEN + "[3] " + ANSI_RESET + "Flowers");
                    System.out.println(ANSI_GREEN + "[4] " + ANSI_RESET + "Accessories");
                    System.out.println(ANSI_GREEN + "[5] " + ANSI_RESET + "Return to previous menu");
                    System.out.print("Selection: ");
                    selection = scan.nextInt();
                } while (selection < 1 || selection > 5);
                break;
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                scan.next();
            }
        }
        System.out.println();
        return selection;
    }

    public static void addItems(ItemCatalogue itemCatalogue) {
        Scanner scan = new Scanner(System.in);
        int selection;
        int position;
        int maxSize = 0;
        String type = "";
        while (true) {
            Item newItem = new Item();
            System.out.println("Select the type of item you wish to add");
            selection = CustomizePackageMaintenance.selectItem();
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
            }
            System.out.print("Enter the name of the new " + type);
            newItem.setName(scan.nextLine());
            if (selection != 2) {
                while (true) {
                    try {
                        System.out.print("Enter the price of the " + type + "RM");
                        newItem.setPrice(scan.nextDouble());
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                        scan.next();
                    }
                }
            } else {
                while (true) {
                    try {
                        System.out.print("Enter the multiplier of the " + type);
                        newItem.setPrice(scan.nextDouble());
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                        scan.next();
                    }
                }
            }
            if (selection != 1 && selection != 2) {
                while (true) {
                    try {
                        do {
                            System.out.print("Enter the stock quantity: ");
                            newItem.setQuantity(scan.nextInt());
                        } while (newItem.getQuantity() < 0);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                        scan.next();
                    }
                }
            } else {
                newItem.setQuantity(0);
            }
            System.out.println("/nSelect the position to display the new item in the catalogue");
            System.out.println("(If you select a position with an existing item,\nthat item will be moved down one slot together with all proceeding items)");
            System.out.println("================================================");
            CustomizePackageMaintenance.printItem(itemCatalogue, selection);
            System.out.println(ANSI_GREEN + "[" + (itemCatalogue.getStyles().getSize() + 1) + "]" + ANSI_RESET + "[ NEW SLOT ]");
            while (true) {
                try {
                    do {
                        System.out.print("Position: ");
                        position = scan.nextInt();
                    } while (position < 1 || position > maxSize + 1);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
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
            }

            do {
                System.out.print("Add Another Item?" + ANSI_GREEN + "[Y/N]" + ANSI_RESET + " ");
                selection = Character.toUpperCase(scan.next().charAt(0));
                scan.nextLine();
                System.out.println();
            } while (selection != 'Y' && selection != 'N');
            if (selection == 'N') {
                break;
            }
        }
    }

    public static void deleteItems(ItemCatalogue itemCatalogue) {
        Scanner scan = new Scanner(System.in);
        int selection;
        int maxSize = 0;
        int position = 0;
        selection = CustomizePackageMaintenance.selectItem();
        if (selection == 1) {
            maxSize = itemCatalogue.getStyles().getSize();
        } else if (selection == 2) {
            maxSize = itemCatalogue.getSizes().getSize();
        } else if (selection == 3) {
            maxSize = itemCatalogue.getFlowers().getSize();
        } else if (selection == 4) {
            maxSize = itemCatalogue.getAccessories().getSize();
        }
        System.out.println("Select the item to be deleted");
        CustomizePackageMaintenance.printItem(itemCatalogue, selection);
        while (true) {
            try {
                do {
                    System.out.println(ANSI_RED + "Please note this action is final and cannot be undone" + ANSI_RESET);
                    System.out.print("Delete item no: ");
                    position = scan.nextInt();
                } while (position < 1 || position > maxSize);
                break;
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
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
        }
    }

    public static void rearrangeCatalogue(ItemCatalogue itemCatalogue) {
        Scanner scan = new Scanner(System.in);
        int selection;
        int position;
        int maxSize = 0;
        String type = "";
        Item item = new Item();

        while (true) {
            Item newItem = new Item();
            System.out.println("Select the catalogue to modify");
            selection = CustomizePackageMaintenance.selectItem();
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
            }

            System.out.println("Select the item to be moved");
            System.out.println("================================================");
            CustomizePackageMaintenance.printItem(itemCatalogue, selection);
            System.out.println(ANSI_GREEN + "[0]" + ANSI_RESET + "[ BACK ]");
            while (true) {
                try {
                    do {
                        System.out.print("Position: ");
                        position = scan.nextInt();
                    } while (position < 0 || position > maxSize);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                    scan.next();
                }
            }

            if(position == 0)
                break;
            
            if (selection == 1) {
                item = itemCatalogue.getStyles().getItem(position);
                itemCatalogue.getStyles().removeItem(position);
            } else if (selection == 2) {
                item = itemCatalogue.getSizes().getItem(position);
                itemCatalogue.getSizes().removeItem(position);
            } else if (selection == 3) {
                item = itemCatalogue.getFlowers().getItem(position);
                itemCatalogue.getFlowers().removeItem(position);
            } else if (selection == 4) {
                item = itemCatalogue.getAccessories().getItem(position);
                itemCatalogue.getAccessories().removeItem(position);
            }

            System.out.println("Select the new position to display the item in the catalogue");
            System.out.println("(If you select a position with an existing item,\nthat item will be moved down one slot together with all proceeding items)");
            System.out.println("================================================");
            CustomizePackageMaintenance.printItem(itemCatalogue, selection);
            System.out.println(ANSI_GREEN + "[" + (itemCatalogue.getStyles().getSize() + 1) + "]" + ANSI_RESET + "[ NEW SLOT ]");
            while (true) {
                try {
                    do {
                        System.out.print("Position: ");
                        position = scan.nextInt();
                    } while (position < 1 || position > maxSize + 1);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                    scan.next();
                }
            }

            System.out.println("Catalogue successfully updated!");
            if (selection == 1) {
                itemCatalogue.getStyles().addItem(position, item);
            } else if (selection == 2) {
                item = itemCatalogue.getSizes().getItem(position);
                itemCatalogue.getSizes().addItem(position, item);
            } else if (selection == 3) {
                item = itemCatalogue.getFlowers().getItem(position);
                itemCatalogue.getFlowers().addItem(position, item);
            } else if (selection == 4) {
                item = itemCatalogue.getAccessories().getItem(position);
                itemCatalogue.getAccessories().addItem(position, item);
            }
            
            do {
                System.out.print("Continue modifying the catalogue?" + ANSI_GREEN + "[Y/N]" + ANSI_RESET + " ");
                selection = Character.toUpperCase(scan.next().charAt(0));
                scan.nextLine();
                System.out.println();
            } while (selection != 'Y' && selection != 'N');
            if (selection == 'N') {
                break;
            }
        }
    }
}
