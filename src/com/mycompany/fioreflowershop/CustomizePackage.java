/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.modal.CustomizedPackage;
import com.mycompany.fioreflowershop.modal.Item;
import com.mycompany.fioreflowershop.adt.ArrayList;
import com.mycompany.fioreflowershop.adt.ArrayQueue;
import com.mycompany.fioreflowershop.adt.ListInterface;
import com.mycompany.fioreflowershop.adt.QueueInterface;
import com.mycompany.fioreflowershop.modal.Consumer;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Chiu Peeng
 */
public class CustomizePackage {

    public static void customizePackageControl(ArrayList<Item> styles, ArrayList<Item> sizes, ArrayList<Item> flowers, ArrayList<Item> accessories, ArrayList<Item> priorities, ArrayList<Item> deliveryTypes, Consumer customer, QueueInterface<CustomizedPackage> customizedPackages) {

        int style = 0, size = 0, flower = 0, accessory = 0, priority = 0, deliveryType = 0;
        Scanner scan = new Scanner(System.in);
        boolean cancel = false;

        System.out.println("Customizing flower package");
        System.out.println("Enter [-1] at any step to cancel and return to main menu\n");

        try {
            do {
                System.out.println("Select the flower arrangement style");
                for (int i = 1; i <= styles.getTotalEntries(); i++) {
                    System.out.printf("[%d] %s: RM%.2f\n", i, styles.getItem(i).getName(), styles.getItem(i).getPrice());
                }
                style = scan.nextInt();

                if (style == -1) {
                    cancel = true;
                    break;
                }
            } while (style < 1 || style > styles.getTotalEntries());

            if (!cancel) {
                do {
                    System.out.println("Select the floral arrangement size");
                    System.out.println("This will be multiplied by the selected flower's price");
                    for (int i = 1; i <= sizes.getTotalEntries(); i++) {
                        System.out.printf("[%d] %s: Flower Price x %.0f\n", i, sizes.getItem(i).getName(), sizes.getItem(i).getPrice());
                    }
                    size = scan.nextInt();

                    if (size == -1) {
                        cancel = true;
                        break;
                    }
                } while (size < 1 || size > sizes.getTotalEntries());
            }

            if (!cancel) {
                do {
                    System.out.println("Select the flowers for the arrangement");
                    for (int i = 1; i <= flowers.getTotalEntries(); i++) {
                        System.out.printf("[%d] %s: RM%.2f\n", i, flowers.getItem(i).getName(), flowers.getItem(i).getPrice());
                    }
                    flower = scan.nextInt();

                    if (flower == -1) {
                        cancel = true;
                        break;
                    }
                } while (flower < 1 || flower > flowers.getTotalEntries());
            }

            if (!cancel) {
                do {
                    System.out.println("Select the accessory to be added");
                    for (int i = 1; i <= accessories.getTotalEntries(); i++) {
                        System.out.printf("[%d] %s: RM%.2f\n", i, accessories.getItem(i).getName(), accessories.getItem(i).getPrice());
                    }
                    accessory = scan.nextInt();

                    if (accessory == -1) {
                        cancel = true;
                        break;
                    }
                } while (accessory < 1 || accessory > accessories.getTotalEntries());
            }

            if (!cancel) {
                do {
                    System.out.println("Select the delivery priority");
                    System.out.println("This will be multiplied by the sum of the floral arrangement");
                    for (int i = 1; i <= priorities.getTotalEntries(); i++) {
                        System.out.printf("[%d] %s: Order price x %.0f\n", i, priorities.getItem(i).getName(), priorities.getItem(i).getPrice());
                    }
                    priority = scan.nextInt();

                    if (priority == -1) {
                        cancel = true;
                        break;
                    }
                } while (priority < 1 || priority > priorities.getTotalEntries());
            }

            if (!cancel) {
                do {
                    System.out.println("Select the delivery type");
                    for (int i = 1; i <= deliveryTypes.getTotalEntries(); i++) {
                        System.out.printf("[%d] %s: Extra Charges: RM%.0f\n", i, deliveryTypes.getItem(i).getName(), deliveryTypes.getItem(i).getPrice());
                    }
                    deliveryType = scan.nextInt();

                    if (deliveryType == -1) {
                        cancel = true;
                        break;
                    }
                } while (deliveryType < 1 || deliveryType > deliveryTypes.getTotalEntries());
            }

        } catch (InputMismatchException e) {
            System.out.println("That wasn't a number :(");
        }

        if (!cancel) {
            CustomizedPackage order = new CustomizedPackage(styles.getItem(style), sizes.getItem(size), flowers.getItem(flower), accessories.getItem(accessory), priorities.getItem(priority), deliveryTypes.getItem(deliveryType), customer);
            customizedPackages.enqueue(order);
            double price = order.CalculateOrder();
            order.minusQuantity();
            System.out.println("Price: RM" + price);

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
}
