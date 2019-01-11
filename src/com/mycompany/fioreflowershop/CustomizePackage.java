/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.modal.*;
import com.mycompany.fioreflowershop.adt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Chiu Peeng
 */
public class CustomizePackage {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";

    public static void customizePackageControl(ItemCatalogue itemCatalogue, Consumer customer, CustomizePackageQueueInterface<CustomizedPackage> customizedPackages) {
        if (customer == null) {
            System.out.println(ANSI_RED + "You are not allowed to access this part of the system.\n" + ANSI_RESET);
            return;
        }

        int style = 0, size = 0, flower = 0, accessory = 0, priority = 0, deliveryType = 0;
        Date date;
        Scanner scan = new Scanner(System.in);
        ItemListInterface<Item> displayFlowers = new ItemList<Item>();
        ItemListInterface<Item> selectedFlowers = new ItemList<Item>();

        System.out.println("Customizing flower package");
        System.out.println(ANSI_BLUE + "Enter [0] at any step to cancel and return to main menu" + ANSI_RESET);

        if (!initializeChecks(itemCatalogue)) {
            return;
        }

        while (true) {
            try {
                do {
                    System.out.println("\nSelect the flower arrangement style");
                    for (int i = 1; i <= itemCatalogue.getStyles().getSize(); i++) {
                        System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                        System.out.printf(" %s: RM%.2f\n", itemCatalogue.getStyles().getItem(i).getName(), itemCatalogue.getStyles().getItem(i).getPrice());
                    }
                    style = scan.nextInt();

                    if (style == 0) {
                        return;
                    }
                    if (style < 1 || style > itemCatalogue.getStyles().getSize()) {
                        System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                    }
                } while (style < 1 || style > itemCatalogue.getStyles().getSize());
                break;
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                scan.next();
            }
        }
        while (true) {
            try {
                do {
                    System.out.println("\nSelect the floral arrangement size");
                    System.out.println("This will be multiplied by the selected flower's price");
                    for (int i = 1; i <= itemCatalogue.getSizes().getSize(); i++) {
                        System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                        System.out.printf(" %s: Flower Price x %.0f\n", itemCatalogue.getSizes().getItem(i).getName(), itemCatalogue.getSizes().getItem(i).getPrice());
                    }
                    size = scan.nextInt();

                    if (size == 0) {
                        return;
                    }
                    if (size < 1 || size > itemCatalogue.getSizes().getSize()) {
                        System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                    }
                } while (size < 1 || size > itemCatalogue.getSizes().getSize());
                break;
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                scan.next();
            }
        }
        for (int i = 1; i <= itemCatalogue.getFlowers().getSize(); i++) {
            displayFlowers.addItem(itemCatalogue.getFlowers().getItem(i));
        }
        while (true) {
            try {
                while (true) {
                    System.out.println("\nSelect the flowers for the arrangement");
                    for (int i = 1; i <= displayFlowers.getSize(); i++) {
                        if (displayFlowers.getItem(i).getQuantity() <= 0) {
                            System.out.println(ANSI_RED + "[ NO STOCK AVAILABLE ] " + displayFlowers.getItem(i).getName() + ANSI_RESET);
                        } else {
                            System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                            System.out.printf(" %s: RM%.2f\n", displayFlowers.getItem(i).getName(), displayFlowers.getItem(i).getPrice());
                        }
                    }
                    flower = scan.nextInt();

                    if (flower == 0) {
                        return;
                    }

                    if (flower < 1 || flower > displayFlowers.getSize()) {
                        System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                    } else if (displayFlowers.getItem(flower).getQuantity() <= 0) {
                        System.out.println(ANSI_RED + "Selected flower is out of stock" + ANSI_RESET);
                    } else {
                        break;
                    }
                }

                selectedFlowers.addItem(displayFlowers.getItem(flower));
                displayFlowers.removeItem(flower);

                if (displayFlowers.isEmpty()) {
                    break;
                }

                char selection;
                do {
                    System.out.print("Add Another Flower?" + ANSI_GREEN + "[Y/N]" + ANSI_RESET + " ");
                    selection = Character.toUpperCase(scan.next().charAt(0));
                    scan.nextLine();
                } while (selection != 'Y' && selection != 'N');

                if (selection == 'N') {
                    break;
                }
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                scan.next();
            }
        }
        while (true) {
            try {
                while (true) {
                    System.out.println("\nSelect the accessory to be added");
                    for (int i = 1; i <= itemCatalogue.getAccessories().getSize(); i++) {
                        if (itemCatalogue.getAccessories().getItem(i).getQuantity() <= 0) {
                            System.out.println(ANSI_RED + "[ NO STOCK AVAILABLE ] " + itemCatalogue.getAccessories().getItem(i).getName() + ANSI_RESET);
                        } else {
                            System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                            System.out.printf(" %s: RM%.2f\n", itemCatalogue.getAccessories().getItem(i).getName(), itemCatalogue.getAccessories().getItem(i).getPrice());
                        }
                    }
                    accessory = scan.nextInt();

                    if (accessory == 0) {
                        return;
                    }
                    if (accessory < 1 || accessory > itemCatalogue.getAccessories().getSize()) {
                        System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                    } else if (itemCatalogue.getAccessories().getItem(accessory).getQuantity() <= 0) {
                        System.out.println(ANSI_RED + "Selected accessory is out of stock" + ANSI_RESET);
                    } else {
                        break;
                    }
                }
                break;
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                scan.next();
            }
        }
        while (true) {
            try {
                do {
                    System.out.println("\nSelect the order priority");
                    System.out.println("This will be multiplied by the sum of the floral arrangement");
                    for (int i = 1; i <= itemCatalogue.getPriorities().getSize(); i++) {
                        System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                        System.out.printf(" %s %s days: Order price x %.0f\n", itemCatalogue.getPriorities().getItem(i).getName(), itemCatalogue.getPriorities().getItem(i).getQuantity(), itemCatalogue.getPriorities().getItem(i).getPrice());
                    }
                    priority = scan.nextInt();

                    if (priority == 0) {
                        return;
                    }
                    if (priority < 1 || priority > itemCatalogue.getPriorities().getSize()) {
                        System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                    }
                } while (priority < 1 || priority > itemCatalogue.getPriorities().getSize());
                break;
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                scan.next();
            }
        }
        while (true) {
            try {
                do {
                    System.out.println("\nSelect the delivery type");
                    for (int i = 1; i <= itemCatalogue.getDeliveryTypes().getSize(); i++) {
                        System.out.print(ANSI_GREEN + "[" + i + "]" + ANSI_RESET);
                        System.out.printf(" %s: Extra Charges: RM%.0f\n", itemCatalogue.getDeliveryTypes().getItem(i).getName(), itemCatalogue.getDeliveryTypes().getItem(i).getPrice());
                    }
                    deliveryType = scan.nextInt();

                    if (deliveryType == 0) {
                        return;
                    }
                    if (deliveryType < 1 || deliveryType > itemCatalogue.getDeliveryTypes().getSize()) {
                        System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                    }
                } while (deliveryType < 1 || deliveryType > itemCatalogue.getDeliveryTypes().getSize());
                break;
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
                scan.next();
            }
        }
        while (true) {
            try {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                System.out.println("\nEnter the delivery date in the following format: YYYY-MM-DD");
                System.out.println("(Must be at least " + itemCatalogue.getPriorities().getItem(priority).getQuantity() + " days from now)");

                String deliveryDate = scan.next();

                date = df.parse(deliveryDate);

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, itemCatalogue.getPriorities().getItem(priority).getQuantity());
                Date addDate = df.parse(df.format(cal.getTime()));

                if (date.before(addDate)) {
                    System.out.println(ANSI_RED + "Please enter a later date" + ANSI_RESET);
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Please enter a valid number" + ANSI_RESET);
            }
        }
        CustomizedPackage order = new CustomizedPackage(itemCatalogue.getStyles().getItem(style), itemCatalogue.getSizes().getItem(size), itemCatalogue.getAccessories().getItem(accessory), itemCatalogue.getPriorities().getItem(priority), itemCatalogue.getDeliveryTypes().getItem(deliveryType), date, customer, false);

        while (!selectedFlowers.isEmpty()) {
            order.getFlowerList().add(selectedFlowers.getItem(1));
            selectedFlowers.removeItem(1);
        }

        customizedPackages.enqueuePackage(order);
        order.minusQuantity();

        displayItemizedBill(order);

        SortOrders.sortCustomizedOrders(customizedPackages);
    }

    public static void displayItemizedBill(CustomizedPackage order) {
        System.out.println("\n\n=====================================================");
        System.out.println("Fiore Flowershop");
        System.out.println("=====================================================");
        System.out.println("Item Type: Customized Package\n");
        System.out.println("Flowers");
        for (int i = 1; i <= order.getFlowerList().getTotalEntries(); i++) {
            System.out.println(" [" + i + "]: " + order.getFlowerList().getItem(i).getName() + " - RM " + order.getFlowerList().getItem(i).getPrice());
        }
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

    public static void viewOrderHistory(Consumer customer, CustomizePackageQueueInterface<CustomizedPackage> customizedPackages, OrderListInterface<Order> readyOrders) {
        if (customer == null) {
            System.out.println(ANSI_RED + "\nYou are not allowed to access this part of the system.\n" + ANSI_RESET);
            return;
        }

        while (true) {
            try {
                System.out.println("\nWhat do you wish to do?");
                System.out.println(ANSI_GREEN + "[1] " + ANSI_RESET + "View Pending Orders");
                System.out.println(ANSI_GREEN + "[2] " + ANSI_RESET + "View Ready Orders");
                System.out.println(ANSI_GREEN + "[0] " + ANSI_RESET + "Return to previous menu");
                System.out.print("Selection: ");
                Scanner scan = new Scanner(System.in);
                int selection = scan.nextInt();
                if (selection == 1) {
                    displayPendingOrders(customer, customizedPackages);
                } else if (selection == 2) {
                    displayReadyOrders(customer, readyOrders);
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Please select one of the above options" + ANSI_RESET);
            }
        }

    }

    public static void displayPendingOrders(Consumer customer, CustomizePackageQueueInterface<CustomizedPackage> customizedPackages) {
        if (customizedPackages.isEmpty()) {
            System.out.println("No order history found");
        } else {
            int selection;
            while (true) {
                try {
                    System.out.println("\nOrder by:");
                    System.out.println(ANSI_GREEN + "[1] " + ANSI_RESET + "Oldest to newest");
                    System.out.println(ANSI_GREEN + "[2] " + ANSI_RESET + "Newest to oldest");
                    System.out.print("Selection: ");
                    Scanner scan = new Scanner(System.in);
                    selection = scan.nextInt();
                    if (selection == 1 || selection == 2) {
                        break;
                    } else {
                        System.out.println(ANSI_RED + "Please select one of the above options" + ANSI_RESET);
                    }
                } catch (Exception e) {
                    System.out.println(ANSI_RED + "Please select one of the above options" + ANSI_RESET);
                }
            }

            Boolean found = false;
            System.out.println("\nYour Order History:");
            System.out.println("================================================");
            if (selection == 1) {
                for (int i = 1; i <= customizedPackages.getSize(); i++) {
                    CustomizedPackage order = customizedPackages.getOrder(i);
                    if (order.getUser().getUsername() == customer.getUsername() && order.getUser().getPassword() == customer.getPassword()) {
                        System.out.println(order.getOrderDateString() + " " + order.getOrderID());
                        System.out.println("Flowers:");
                        for (int j = 1; j <= order.getFlowerList().getTotalEntries(); j++) {
                            System.out.println(" [" + j + "]: " + order.getFlowerList().getItem(j).getName() + " - RM " + order.getFlowerList().getItem(j).getPrice());
                        }
                        System.out.println("Arrangement: " + order.getSize().getName() + " " + order.getStyle().getName());
                        System.out.println("Price: RM" + order.CalculateOrder() + "\n");
                        found = true;
                    }
                }
            } else {
                for (int i = customizedPackages.getSize(); i >= 1; i--) {
                    CustomizedPackage order = customizedPackages.getOrder(i);
                    if (order.getUser().getUsername() == customer.getUsername() && order.getUser().getPassword() == customer.getPassword()) {
                        System.out.println(order.getOrderDateString() + " " + order.getOrderID());
                        System.out.println("Flowers:");
                        for (int j = 1; j <= order.getFlowerList().getTotalEntries(); j++) {
                            System.out.println(" [" + j + "]: " + order.getFlowerList().getItem(j).getName() + " - RM " + order.getFlowerList().getItem(j).getPrice());
                        }
                        System.out.println("Arrangement: " + order.getSize().getName() + " " + order.getStyle().getName());
                        System.out.println("Price: RM" + order.CalculateOrder() + "\n");
                        found = true;
                    }
                }
            }
            if (!found) {
                System.out.println("No order history found");
            }
            System.out.println("==============END OF ORDER HISTORY==============");
        }
    }

    public static void displayReadyOrders(Consumer customer, OrderListInterface<Order> readyOrders) {
        if (readyOrders.isEmpty()) {
            System.out.println("No order history found");
        } else {
            int selection;
            while (true) {
                try {
                    System.out.println("\nOrder by:");
                    System.out.println(ANSI_GREEN + "[1] " + ANSI_RESET + "Oldest to newest");
                    System.out.println(ANSI_GREEN + "[2] " + ANSI_RESET + "Newest to oldest");
                    System.out.print("Selection: ");
                    Scanner scan = new Scanner(System.in);
                    selection = scan.nextInt();
                    if (selection == 1 || selection == 2) {
                        break;
                    } else {
                        System.out.println(ANSI_RED + "Please select one of the above options" + ANSI_RESET);
                    }
                } catch (Exception e) {
                    System.out.println(ANSI_RED + "Please select one of the above options" + ANSI_RESET);
                }
            }

            Boolean found = false;
            System.out.println("\nYour Order History:");
            System.out.println("================================================");
            if (selection == 1) {
                for (int i = 1; i <= readyOrders.getSize(); i++) {
                    if (readyOrders.getOrder(i) instanceof CustomizedPackage) {
                        CustomizedPackage order = (CustomizedPackage) readyOrders.getOrder(i);
                        if (order.getUser().getUsername() == customer.getUsername() && order.getUser().getPassword() == customer.getPassword()) {
                            System.out.println(order.getOrderDateString() + " " + order.getOrderID());
                            System.out.println("Flowers:");
                            for (int j = 1; j <= order.getFlowerList().getTotalEntries(); j++) {
                                System.out.println(" [" + j + "]: " + order.getFlowerList().getItem(j).getName() + " - RM " + order.getFlowerList().getItem(j).getPrice());
                            }
                            System.out.println("Arrangement: " + order.getSize().getName() + " " + order.getStyle().getName());
                            System.out.println("Price: RM" + order.CalculateOrder());
                            System.out.println("Order status: " + order.getOrderStatus() + "\n");
                            found = true;
                        }
                    }
                }
            } else {
                for (int i = readyOrders.getSize(); i >= 1; i--) {
                    if (readyOrders.getOrder(i) instanceof CustomizedPackage) {
                        CustomizedPackage order = (CustomizedPackage) readyOrders.getOrder(i);
                        if (order.getUser().getUsername() == customer.getUsername() && order.getUser().getPassword() == customer.getPassword()) {
                            System.out.println(order.getOrderDateString() + " " + order.getOrderID());
                            System.out.println("Flowers:");
                            for (int j = 1; j <= order.getFlowerList().getTotalEntries(); j++) {
                                System.out.println(" [" + j + "]: " + order.getFlowerList().getItem(j).getName() + " - RM " + order.getFlowerList().getItem(j).getPrice());
                            }
                            System.out.println("Arrangement: " + order.getSize().getName() + " " + order.getStyle().getName());
                            System.out.println("Price: RM" + order.CalculateOrder());
                            System.out.println("Order status: " + order.getOrderStatus() + "\n");
                            found = true;
                        }
                    }
                }
            }

            if (!found) {
                System.out.println("No order history found");
            }
            System.out.println("==============END OF ORDER HISTORY==============");
        }
    }

    public static boolean initializeChecks(ItemCatalogue itemCatalogue) {
        boolean cancel = false;

        if (itemCatalogue.getAccessories().isEmpty()) {
            System.out.println(ANSI_RED + "INITIALIZATION ERROR: [NO ACCESSORIES FOUND]" + ANSI_RESET);
            return false;
        } else if (itemCatalogue.getDeliveryTypes().isEmpty()) {
            System.out.println(ANSI_RED + "INITIALIZATION ERROR: [NO DELIVERY TYPES FOUND]" + ANSI_RESET);
            return false;
        } else if (itemCatalogue.getFlowers().isEmpty()) {
            System.out.println(ANSI_RED + "INITIALIZATION ERROR: [NO FLOWERS FOUND]" + ANSI_RESET);
            return false;
        } else if (itemCatalogue.getPriorities().isEmpty()) {
            System.out.println(ANSI_RED + "INITIALIZATION ERROR: [NO ORDER PRIORITIES FOUND]" + ANSI_RESET);
            return false;
        } else if (itemCatalogue.getSizes().isEmpty()) {
            System.out.println(ANSI_RED + "INITIALIZATION ERROR: [NO SIZES FOUND]" + ANSI_RESET);
            return false;
        } else if (itemCatalogue.getStyles().isEmpty()) {
            System.out.println(ANSI_RED + "INITIALIZATION ERROR: [NO ARRANGEMENT STYLES FOUND]" + ANSI_RESET);
            return false;
        }
        for (int i = 1; i <= itemCatalogue.getFlowers().getSize(); i++) {
            if (itemCatalogue.getFlowers().getItem(i).getQuantity() > 0) {
                cancel = false;
                break;
            }
        }
        if (cancel) {
            System.out.println(ANSI_RED + "INITIALIZATION ERROR: [ALL FLOWERS ARE OUT OF STOCK]" + ANSI_RESET);
            return false;
        }

        return true;
    }
}
