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
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

    public static void searchPickUp(ListInterface pickupOrder, Date date) {
        ListInterface<Order> unOrderList = pickupOrder;
        ListInterface<Order> matchedList = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        Calendar listCal = Calendar.getInstance();
        cal.setTime(date);

        for (int i = 1; i <= unOrderList.getTotalEntries(); i++) {

            listCal.setTime(unOrderList.getItem(i).getDate());
            int day, month, year, userDay, userMonth, userYear;

            day = listCal.get(Calendar.DAY_OF_MONTH);
            month = listCal.get(Calendar.MONTH) + 1;
            year = listCal.get(Calendar.YEAR);

            userDay = cal.get(Calendar.DAY_OF_MONTH);
            userMonth = cal.get(Calendar.MONTH) + 1;
            userYear = cal.get(Calendar.YEAR);

            if (day == userDay && month == userMonth && year == userYear) {
                matchedList.add(unOrderList.getItem(i));
            }
        }

        displaySortedPickup(matchedList);
    }

    public static void sortPickupOrder(ListInterface<Order> pickupOrder, QueueInterface customizeOrder) {
        ListInterface<Order> orderedList = pickupOrder;

        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");

//        for (int i = 1; i < unOrderList.getTotalEntries(); i++) {
//
//            String date = parser.format(unOrderList.getItem(i).getDate());
//
//            if (orderedList.isEmpty()) {
//                orderedList.add(unOrderList.getItem(i));
//            }
//
//        }
//
//        for (int j = 2; j <= unOrderList.getTotalEntries() + 1; j++) {
//
//            for (int l = 1; l <= orderedList.getTotalEntries(); l++) {
//                if (unOrderList.getItem(j).getDate().before(orderedList.getItem(l).getDate())) {
//                    orderedList.add((l - 1), unOrderList.getItem(j));
//                    added = true;
//                } else if (unOrderList.getItem(j).getDate().after(orderedList.getItem(l).getDate())) {
//                    orderedList.add((l + 1), unOrderList.getItem(j));
//                    added = true;
//                } else {
//                    orderedList.add(l, unOrderList.getItem(j));
//                    added = true;
//                }
//            }
//}
        for (int i = 1; i < orderedList.getTotalEntries() - 1; i++) {
            int index = i;
            for (int j = i; j <= orderedList.getTotalEntries(); j++) {
                if (orderedList.getItem(j).getDate().before(orderedList.getItem(index).getDate())) {
                    index = j; //searching for lowest index  
                }
            }
            Order smallerOrder = orderedList.getItem(index);
            orderedList.replace(index, orderedList.getItem(i));
            orderedList.replace(i, smallerOrder);
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
