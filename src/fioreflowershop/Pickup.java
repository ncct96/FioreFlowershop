/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop;

import fioreflowershop.adt.ArrayList;
import fioreflowershop.adt.ArrayQueue;
import fioreflowershop.adt.ListInterface;
import fioreflowershop.adt.QueueInterface;
import fioreflowershop.modal.Consumer;
import fioreflowershop.modal.CorporateCustomer;
import fioreflowershop.modal.CustomizedPackage;
import fioreflowershop.modal.Item;
import fioreflowershop.modal.Order;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Nicholas
 */
public class Pickup {

    static Scanner sc = new Scanner(System.in);
    ListInterface<CustomizedPackage> customPackageList = new ArrayList<>();

    public static void customSortPickup(QueueInterface customPackage, Consumer consumer) {

        initializeData();

        // ListInterface<CustomizedPackage> customPackageList = customPackage.getCustomPackageList();
    }

    public static void conCatSortPickup(ListInterface order) {

        ListInterface orderList = new ArrayList();

    }

    public static void corpCatSortPickup(ListInterface order) {

    }

    public static void sortPickupOrder(ListInterface<Order> pickupOrder, QueueInterface customizeOrder) {
        ListInterface<Order> unOrderList = pickupOrder;
        ListInterface<Order> orderedList = new ArrayList<>();

        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");

        for (int i = 1; i < unOrderList.getTotalEntries(); i++) {

            String date = parser.format(unOrderList.getItem(i).getDate());

            if (orderedList.isEmpty()) {
                orderedList.add(unOrderList.getItem(i));
                unOrderList.remove(i);
            }

        }

        for (int j = 1; j <= unOrderList.getTotalEntries(); j++) {
            if (unOrderList.getItem(j).getDate().before(orderedList.getItem(j).getDate())) {
                orderedList.add((j), unOrderList.getItem(j));
            } else if (unOrderList.getItem(j).getDate().after(orderedList.getItem(j).getDate())) {
                orderedList.add((j + 1), unOrderList.getItem(j));
            } else {
                orderedList.add(j, unOrderList.getItem(j));
            }
        }

        displaySortedPickup(orderedList);

    }

    public static void displaySortedPickup(ListInterface<Order> orderedList) {

        for (int k = 1; k <= orderedList.getTotalEntries(); k++) {
            System.out.println(orderedList.getItem(k).getDate());
        }
    }

    public static void initializeData() {

        // Retrieve Data from all List
//        customPackage.InitializePackages();
//
//        ArrayList<Item> styles = customPackage.getStyles();
//        ArrayList<Item> sizes = customPackage.getSizes();
//        ArrayList<Item> flowers = customPackage.getFlowers();
//        ArrayList<Item> accessories = customPackage.getAccessories();
//        ArrayList<Item> priorities = customPackage.getPriorities();
//        ArrayList<Item> deliveryTypes = customPackage.getDeliveryTypes();
        //CustomizedPackage customizedPackage = new CustomizedPackage(styles.getItem(1), sizes.getItem(1), flowers.getItem(1), accessories.getItem(1), priorities.getItem(1), deliveryTypes.getItem(1));
        //CorporateCustomer corpCust = new CorporateCustomer("ncct96", "ncct96@gmail.com", "0165919413", "13,Lorong Maju 6, Taman Assam Maju", "ncct96", "TARUC", true);
        //Order order = new Order(1001, "Pick Up", corpCust, customizedPackage);
    }

//    for(int i = 0; i < customPack ; i++ ){
//    
//}
//    
}
