/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import static com.mycompany.fioreflowershop.FioreFlowershop.florist;
import static com.mycompany.fioreflowershop.FioreFlowershop.inventoryClerk;
import com.mycompany.fioreflowershop.modal.*;
import com.mycompany.fioreflowershop.adt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Chiu Peeng
 */
public class CustomizePackage {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    public static void customizePackageControl(ItemCatalogue itemCatalogue, Consumer customer, QueueInterface<CustomizedPackage> customizedPackages) {
        int style = 0, size = 0, flower = 0, accessory = 0, priority = 0, deliveryType = 0;
        Scanner scan = new Scanner(System.in);
        boolean cancel = false;

        System.out.println("Customizing flower package");
        System.out.println("Enter [-1] at any step to cancel and return to main menu\n");

        while (true) {
            try {
                do {
                    System.out.println("\nSelect the flower arrangement style");
                    for (int i = 1; i <= itemCatalogue.getStyles().getTotalEntries(); i++) {
                        System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                        System.out.printf(" %s: RM%.2f\n", itemCatalogue.getStyles().getItem(i).getName(), itemCatalogue.getStyles().getItem(i).getPrice());
                    }
                    style = scan.nextInt();

                    if (style == -1) {
                        cancel = true;
                        break;
                    }
                    if (style < 1 || style > itemCatalogue.getStyles().getTotalEntries()) {
                        System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                    }
                } while (style < 1 || style > itemCatalogue.getStyles().getTotalEntries());
                break;
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                scan.next();
            }
        }

        if (!cancel) {
            while (true) {
                try {
                    do {
                        System.out.println("\nSelect the floral arrangement size");
                        System.out.println("This will be multiplied by the selected flower's price");
                        for (int i = 1; i <= itemCatalogue.getSizes().getTotalEntries(); i++) {
                            System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                            System.out.printf(" %s: Flower Price x %.0f\n", itemCatalogue.getSizes().getItem(i).getName(), itemCatalogue.getSizes().getItem(i).getPrice());
                        }
                        size = scan.nextInt();

                        if (size == -1) {
                            cancel = true;
                            break;
                        }
                        if (size < 1 || size > itemCatalogue.getSizes().getTotalEntries()) {
                            System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                        }
                    } while (size < 1 || size > itemCatalogue.getSizes().getTotalEntries());
                    break;
                } catch (InputMismatchException e) {
                    System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                    scan.next();
                }
            }
        }
        if (!cancel) {
            while (true) {
                try {
                    do {
                        System.out.println("\nSelect the flowers for the arrangement");
                        for (int i = 1; i <= itemCatalogue.getFlowers().getTotalEntries(); i++) {
                            System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                            System.out.printf(" %s: RM%.2f\n", itemCatalogue.getFlowers().getItem(i).getName(), itemCatalogue.getFlowers().getItem(i).getPrice());
                        }
                        flower = scan.nextInt();

                        if (flower == -1) {
                            cancel = true;
                            break;
                        }
                        if (flower < 1 || flower > itemCatalogue.getFlowers().getTotalEntries()) {
                            System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                        }
                    } while (flower < 1 || flower > itemCatalogue.getFlowers().getTotalEntries());
                    break;
                } catch (InputMismatchException e) {
                    System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                    scan.next();
                }
            }
        }
        if (!cancel) {
            while (true) {
                try {
                    do {
                        System.out.println("\nSelect the accessory to be added");
                        for (int i = 1; i <= itemCatalogue.getAccessories().getTotalEntries(); i++) {
                            System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                            System.out.printf(" %s: RM%.2f\n", itemCatalogue.getAccessories().getItem(i).getName(), itemCatalogue.getAccessories().getItem(i).getPrice());
                        }
                        accessory = scan.nextInt();

                        if (accessory == -1) {
                            cancel = true;
                            break;
                        }
                        if (accessory < 1 || accessory > itemCatalogue.getAccessories().getTotalEntries()) {
                            System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                        }
                    } while (accessory < 1 || accessory > itemCatalogue.getAccessories().getTotalEntries());
                    break;
                } catch (InputMismatchException e) {
                    System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                    scan.next();
                }
            }
        }
        if (!cancel) {
            while (true) {
                try {
                    do {
                        System.out.println("\nSelect the order priority");
                        System.out.println("This will be multiplied by the sum of the floral arrangement");
                        for (int i = 1; i <= itemCatalogue.getPriorities().getTotalEntries(); i++) {
                            System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                            System.out.printf(" %s: Order price x %.0f\n", itemCatalogue.getPriorities().getItem(i).getName(), itemCatalogue.getPriorities().getItem(i).getPrice());
                        }
                        priority = scan.nextInt();

                        if (priority == -1) {
                            cancel = true;
                            break;
                        }
                        if (priority < 1 || priority > itemCatalogue.getPriorities().getTotalEntries()) {
                            System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                        }
                    } while (priority < 1 || priority > itemCatalogue.getPriorities().getTotalEntries());
                    break;
                } catch (InputMismatchException e) {
                    System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                    scan.next();
                }
            }
        }
        if (!cancel) {
            while (true) {
                try {
                    do {
                        System.out.println("\nSelect the delivery type");
                        for (int i = 1; i <= itemCatalogue.getDeliveryTypes().getTotalEntries(); i++) {
                            System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                            System.out.printf(" %s: Extra Charges: RM%.0f\n", itemCatalogue.getDeliveryTypes().getItem(i).getName(), itemCatalogue.getDeliveryTypes().getItem(i).getPrice());
                        }
                        deliveryType = scan.nextInt();

                        if (deliveryType == -1) {
                            cancel = true;
                            break;
                        }
                        if (deliveryType < 1 || deliveryType > itemCatalogue.getDeliveryTypes().getTotalEntries()) {
                            System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                        }
                    } while (deliveryType < 1 || deliveryType > itemCatalogue.getDeliveryTypes().getTotalEntries());
                    break;
                } catch (InputMismatchException e) {
                    System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                    scan.next();
                }
            }
        }
        if (!cancel) {
            CustomizedPackage order = new CustomizedPackage(itemCatalogue.getStyles().getItem(style), itemCatalogue.getSizes().getItem(size), itemCatalogue.getFlowers().getItem(flower), itemCatalogue.getAccessories().getItem(accessory), itemCatalogue.getPriorities().getItem(priority), itemCatalogue.getDeliveryTypes().getItem(deliveryType), customer, false);
            customizedPackages.enqueue(order);
            order.minusQuantity();

            displayItemizedBill(order);

            ArrayQueue<CustomizedPackage> sortingQueue = new ArrayQueue<>();

            sortingQueue.enqueue(customizedPackages.dequeue());
            while (customizedPackages.getBackIndex() > -1) {
                CustomizedPackage next = customizedPackages.dequeue();
                for (int i = -1; i < sortingQueue.getBackIndex(); ++i) {
                    if (sortingQueue.getFront().getDeliveryDate().before(next.getDeliveryDate())) {
                        sortingQueue.enqueue(sortingQueue.dequeue());
                    } else {
                        sortingQueue.enqueue(next);
                        next = sortingQueue.dequeue();
                    }
                }
                sortingQueue.enqueue(next);
            }

            while (!sortingQueue.isEmpty()) {
                customizedPackages.enqueue(sortingQueue.dequeue());
            }
        }
    }

    public static void displayItemizedBill(CustomizedPackage order) {
        System.out.println("\n\n=====================================================");
        System.out.println("Fiore Flowershop");
        System.out.println("=====================================================");
        System.out.println("Item Type: Customized Package\n");
        System.out.println("Flower: " + order.getFlower().getName() + " - RM " + order.getFlower().getPrice());
        System.out.println("Size: " + order.getSize().getName() + " - Flower x " + order.getSize().getPrice());
        System.out.println("Arrangement Style: " + order.getStyle().getName() + " - RM " + order.getStyle().getPrice());
        System.out.println("Accesscories: " + order.getAccessory().getName() + " - RM " + order.getAccessory().getPrice());
        System.out.println("Delivery Type: " + order.getDeliveryType().getName() + " - RM " + order.getDeliveryType().getPrice());
        System.out.println("Order Priority: " + order.getPriority().getName() + " - Price x " + +order.getPriority().getPrice());
        System.out.println("=====================================================");
        System.out.println("Total Price: RM" + order.CalculateOrder());
        System.out.println("=====================================================");
        System.out.println("Thank you for your purchase!");
        System.out.println("=====================================================");
    }

    public static void displayOrderHistory(Consumer customer, QueueInterface<CustomizedPackage> customizedPackages) {
        QueueInterface<CustomizedPackage> displayQueue = customizedPackages;
        Boolean found = false;
        System.out.println("Your Order History:");
        System.out.println("================================================");
        while (!displayQueue.isEmpty()) {
            CustomizedPackage order = displayQueue.dequeue();
            if (order.getUser().getUsername() == customer.getUsername() && order.getUser().getPassword() == customer.getPassword()) {
                System.out.println(order.getOrderDateString() + " " + order.getOrderID() + " | " + order.getFlower().getName() + " " + order.getSize().getName() + " | RM" + order.CalculateOrder());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No order history found");
        }

        System.out.println("================================================");
    }

    public static void itemsMenu(ItemCatalogue itemCatalogue, QueueInterface<CustomizedPackage> customizedPackages) {
        while (true) {
            System.out.println("What do you wish to do?");
            System.out.println(ANSI_GREEN + "[1] " + ANSI_RESET + "Update Stock Quantity");
            System.out.println(ANSI_GREEN + "[2] " + ANSI_RESET + "Add New Items");
            System.out.println(ANSI_GREEN + "[3] " + ANSI_RESET + "Delete Items");
            System.out.println(ANSI_GREEN + "[4] " + ANSI_RESET + "Return to previous menu");
            Scanner scan = new Scanner(System.in);
            int selection = scan.nextInt();
            System.out.println();
            
            if (selection == 1) {
                updateStock(itemCatalogue);
            } else if (selection == 2) {
                addItems(itemCatalogue);
            } else if (selection == 3) {
                deleteItems(itemCatalogue);
            } else {
                inventoryClerk();
            }
        }
    }

    public static void updateStock(ItemCatalogue itemCatalogue) {
        Scanner scan = new Scanner(System.in);
        int selection, newQuantity;

        while (true) {
            try {
                do {
                    System.out.println("Select the type of customization item");
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
            for (int i = 1; i <= itemCatalogue.getFlowers().getTotalEntries(); i++) {
                System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                System.out.printf(" %s: RM%.2f\n", itemCatalogue.getFlowers().getItem(i).getName(), itemCatalogue.getFlowers().getItem(i).getQuantity());
            }

            while (true) {
                try {
                    do {
                        System.out.print("Select the item to update: ");
                        selection = scan.nextInt();
                    } while (selection < 1 || selection > itemCatalogue.getFlowers().getTotalEntries());

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

            System.out.println("Stock quantity successfully updated!");
            itemCatalogue.getFlowers().getItem(selection).setQuantity(newQuantity);
        }
    }

    public static void addItems(ItemCatalogue itemCatalogue) {
        Scanner scan = new Scanner(System.in);
        int selection, position, maxSize = 0;
        Item newItem = new Item();
        String type = "";

        System.out.println("Select the type of item you wish to add");
        selection = selectItem();

        if (selection == 1) {
            type = "arrangement style: ";
            maxSize = itemCatalogue.getStyles().getTotalEntries();
        } else if (selection == 2) {
            type = "arrangement size: ";
            maxSize = itemCatalogue.getSizes().getTotalEntries();
        } else if (selection == 3) {
            type = "flower: ";
            maxSize = itemCatalogue.getFlowers().getTotalEntries();
        } else if (selection == 4) {
            type = "accessory: ";
            maxSize = itemCatalogue.getAccessories().getTotalEntries();
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

        System.out.println("Select the position to display the new item in the catalogue");
        System.out.println("(If you select a position with an existing item, that item will be moved down one slot together with all proceeding items)");
        System.out.println("=========================================================================================================================");
        printItem(itemCatalogue, selection);
        System.out.println(ANSI_GREEN + "[" + (itemCatalogue.getStyles().getTotalEntries() + 1) + "]" + ANSI_RESET + "[ NEW SLOT ]");

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
            itemCatalogue.getStyles().add(position, newItem);
        } else if (selection == 2) {
            itemCatalogue.getSizes().add(position, newItem);
        } else if (selection == 3) {
            itemCatalogue.getFlowers().add(position, newItem);
        } else if (selection == 4) {
            itemCatalogue.getAccessories().add(position, newItem);
        }
    }

    public static void deleteItems(ItemCatalogue itemCatalogue) {
        Scanner scan = new Scanner(System.in);
        int selection, maxSize = 0, position = 0;

        selection = selectItem();

        if (selection == 1) {
            maxSize = itemCatalogue.getStyles().getTotalEntries();
        } else if (selection == 2) {
            maxSize = itemCatalogue.getSizes().getTotalEntries();
        } else if (selection == 3) {
            maxSize = itemCatalogue.getFlowers().getTotalEntries();
        } else if (selection == 4) {
            maxSize = itemCatalogue.getAccessories().getTotalEntries();
        }
        System.out.println("Select the item to be deleted");
        printItem(itemCatalogue, selection);

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
            itemCatalogue.getStyles().remove(position);
        } else if (selection == 2) {
            itemCatalogue.getSizes().remove(position);
        } else if (selection == 3) {
            itemCatalogue.getFlowers().remove(position);
        } else if (selection == 4) {
            itemCatalogue.getAccessories().remove(position);
        }
    }

    public static void printItem(ItemCatalogue itemCatalogue, int type) {

        if (type == 1) {
            for (int i = 1; i <= itemCatalogue.getStyles().getTotalEntries(); i++) {
                System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                System.out.printf(" %s: RM%.2f\n", itemCatalogue.getStyles().getItem(i).getName(), itemCatalogue.getStyles().getItem(i).getPrice());
            }
        } else if (type == 2) {
            for (int i = 1; i <= itemCatalogue.getSizes().getTotalEntries(); i++) {
                System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                System.out.printf(" %s: x %.0f\n", itemCatalogue.getSizes().getItem(i).getName(), itemCatalogue.getSizes().getItem(i).getPrice());
            }
        } else if (type == 3) {
            for (int i = 1; i <= itemCatalogue.getFlowers().getTotalEntries(); i++) {
                System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                System.out.printf(" %s: RM%.2f\n", itemCatalogue.getFlowers().getItem(i).getName(), itemCatalogue.getFlowers().getItem(i).getPrice());
            }
        } else if (type == 4) {
            for (int i = 1; i <= itemCatalogue.getAccessories().getTotalEntries(); i++) {
                System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                System.out.printf(" %s: RM%.2f\n", itemCatalogue.getAccessories().getItem(i).getName(), itemCatalogue.getAccessories().getItem(i).getPrice());
            }
        } else if (type == 5) {

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

    public static void updateOrders(QueueInterface<CustomizedPackage> customizedPackages, ListInterface<CustomizedPackage> readyOrders) {
        while (true) {
            Scanner scan = new Scanner(System.in);
            char selection;

            if (!customizedPackages.isEmpty()) {
                System.out.println("==================================");
                System.out.println("Next Order");
                System.out.println("==================================");
                System.out.println("Job ID: " + customizedPackages.getFront().getOrderID());
                System.out.println("Delivery Date: " + customizedPackages.getFront().getDeliveryDateString());
                System.out.println("Priority: " + customizedPackages.getFront().getPriority());
                System.out.println("Arrangement: " + customizedPackages.getFront().getSize() + " " + customizedPackages.getFront().getStyle() + " " + customizedPackages.getFront().getFlower());
                System.out.println("Accessories: " + customizedPackages.getFront().getAccessory());
                System.out.println("==================================");
                do {
                    System.out.print("Complete order?" + ANSI_GREEN + "[Y/N]" + ANSI_RESET + " ");
                    selection = Character.toUpperCase(scan.next().charAt(0));
                } while (selection != 'Y' && selection != 'N');

                if (selection == 'Y') {
                    System.out.println("Order marked as ready to deliver!");
                    readyOrders.add(customizedPackages.dequeue());
                    do {
                        System.out.print("Continue to next order?" + ANSI_GREEN + "[Y/N]" + ANSI_RESET + " ");
                        selection = Character.toUpperCase(scan.next().charAt(0));
                    } while (selection != 'Y' && selection != 'N');

                    if (selection == 'N') {
                        break;
                    }
                } else {
                    florist();
                }
            }
        }
    }
}
