/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.modal.Item;
import com.mycompany.fioreflowershop.adt.ArrayList;
import com.mycompany.fioreflowershop.adt.ArrayQueue;
import com.mycompany.fioreflowershop.adt.QueueInterface;
import com.mycompany.fioreflowershop.modal.Consumer;
import com.mycompany.fioreflowershop.modal.CustomizedPackage;
import com.mycompany.fioreflowershop.modal.ItemCatalogue;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Chiu Peeng
 */
public class CustomizePackage {

    public static void customizePackageControl(ItemCatalogue itemCatalogue, Consumer customer, QueueInterface<CustomizedPackage> customizedPackages) {

        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";

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
}