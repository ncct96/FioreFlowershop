/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop;

import fioreflowershop.modal.CustomizedPackage;
import fioreflowershop.modal.Item;
import fioreflowershop.adt.ArrayList;
import fioreflowershop.adt.ListInterface;
import fioreflowershop.modal.Customer;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Chiu Peeng
 */
public class CustomizePackage {

    public static ListInterface<CustomizedPackage> customPackageList = new ArrayList<>();

    public static void CustomizePackageControl(ArrayList<Item> styles, ArrayList<Item> sizes, ArrayList<Item> flowers, ArrayList<Item> accessories, ArrayList<Item> priorities, ArrayList<Item> deliveryTypes, Customer customer) {
        int style = 0, size = 0, flower = 0, accessory = 0, priority = 0, deliveryType = 0, loop = 0;
        boolean exception;
        Scanner scan = new Scanner(System.in);

//        InitializePackages();
        try {
            do {
                System.out.println("Select the flower arrangement style");
                for (int i = 1; i <= styles.getTotalEntries(); i++) {
                    System.out.printf("[%d] %s: RM%.2f\n", i, styles.getItem(i).getName(), styles.getItem(i).getPrice());
                }
                style = scan.nextInt();
            } while (style < 1 || style >= styles.getTotalEntries());

            do {
                System.out.println("Select the floral arrangement size");
                System.out.println("This will be multiplied by the selected flower's price");
                for (int i = 1; i <= sizes.getTotalEntries(); i++) {
                    System.out.printf("[%d] %s: Flower Price x %.0f\n", i, sizes.getItem(i).getName(), sizes.getItem(i).getPrice());
                }
                size = scan.nextInt();
            } while (size < 1 || size >= sizes.getTotalEntries());

            do {
                System.out.println("Select the flowers for the arrangement");
                for (int i = 1; i <= flowers.getTotalEntries(); i++) {
                    System.out.printf("[%d] %s: RM%.2f\n", i, flowers.getItem(i).getName(), flowers.getItem(i).getPrice());
                }
                flower = scan.nextInt();
            } while (flower < 1 || flower >= flowers.getTotalEntries());

            do {
                System.out.println("Select the accessory to be added");
                for (int i = 1; i <= accessories.getTotalEntries(); i++) {
                    System.out.printf("[%d] %s: RM%.2f\n", i, accessories.getItem(i).getName(), accessories.getItem(i).getPrice());
                }
                accessory = scan.nextInt();
            } while (accessory < 1 || accessory >= accessories.getTotalEntries());

            do {
                System.out.println("Select the delivery priority");
                System.out.println("This will be multiplied by the sum of the floral arrangement");
                for (int i = 1; i <= priorities.getTotalEntries(); i++) {
                    System.out.printf("[%d] %s: Order price x %.0f\n", i, priorities.getItem(i).getName(), priorities.getItem(i).getPrice());
                }
                priority = scan.nextInt();
            } while (priority < 1 || priority >= priorities.getTotalEntries());

            do {
                System.out.println("Select the delivery type");
                for (int i = 1; i <= deliveryTypes.getTotalEntries(); i++) {
                    System.out.printf("[%d] %s: Extra Charges: RM%.0f\n", i, deliveryTypes.getItem(i).getName(), deliveryTypes.getItem(i).getPrice());
                }
                deliveryType = scan.nextInt();
            } while (deliveryType < 1 || deliveryType >= deliveryTypes.getTotalEntries());

            //Nicholas debug stuff
            System.out.println("DDD");
            Pickup pickup = new Pickup();
            pickup.sortPickup();

        } catch (InputMismatchException e) {
            System.out.println("That wasn't a number :(");
        }

        CustomizedPackage order = new CustomizedPackage(styles.getItem(style), sizes.getItem(size), flowers.getItem(flower), accessories.getItem(accessory), priorities.getItem(priority), deliveryTypes.getItem(deliveryType), customer);
        customPackageList.add(order);
        double price = order.CalculateOrder();
        order.minusQuantity();
        System.out.println(price);
    }
}
