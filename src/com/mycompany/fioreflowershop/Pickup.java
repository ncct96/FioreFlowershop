/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import static com.mycompany.fioreflowershop.Delivery.displaySortedDelivery;
import com.mycompany.fioreflowershop.adt.LinkedList;
import com.mycompany.fioreflowershop.adt.ArrayQueue;
import com.mycompany.fioreflowershop.adt.LinkedList;
import com.mycompany.fioreflowershop.adt.ListInterface;
import com.mycompany.fioreflowershop.adt.QueueInterface;
import com.mycompany.fioreflowershop.modal.CatalogOrders;
import com.mycompany.fioreflowershop.modal.Consumer;
import com.mycompany.fioreflowershop.modal.CorporateCustomer;
import com.mycompany.fioreflowershop.modal.CustomizedPackage;
import com.mycompany.fioreflowershop.modal.Item;
import com.mycompany.fioreflowershop.modal.Order;
import com.mycompany.fioreflowershop.modal.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Nicholas
 */
public class Pickup {

    static Scanner sc = new Scanner(System.in);

    ListInterface<CustomizedPackage> customPackageList = new LinkedList<>();

    public static void searchPickUp(ListInterface pickupOrder, Date date, QueueInterface<CustomizedPackage> customOrder) {
        ListInterface<Order> unOrderList = pickupOrder;
        ListInterface<Order> matchedList = new LinkedList<>();
        QueueInterface<CustomizedPackage> searchQueue = new ArrayQueue<>();

        int count = customOrder.getBackIndex();

        Calendar cal = Calendar.getInstance();
        Calendar listCal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        Calendar CuslistCal = Calendar.getInstance();
        cal.setTime(date);

        int day, month, year, userDay, userMonth, userYear;

        userDay = cal.get(Calendar.DAY_OF_MONTH);
        userMonth = cal.get(Calendar.MONTH) + 1;
        userYear = cal.get(Calendar.YEAR);

        for (int i = 1; i <= unOrderList.getTotalEntries(); i++) {

            listCal.setTime(unOrderList.getItem(i).getOrderDate());

            day = listCal.get(Calendar.DAY_OF_MONTH);
            month = listCal.get(Calendar.MONTH) + 1;
            year = listCal.get(Calendar.YEAR);

            if (day == userDay && month == userMonth && year == userYear) {
                matchedList.add(unOrderList.getItem(i));
            }
        }

        for (int i = -1; i <= count; i++) {

            if (!customOrder.isEmpty()) {
                CustomizedPackage order = customOrder.dequeue();
                CuslistCal.setTime(order.getDeliveryDate());

                day = CuslistCal.get(Calendar.DAY_OF_MONTH);
                month = CuslistCal.get(Calendar.MONTH) + 1;
                year = CuslistCal.get(Calendar.YEAR);

                if (day == userDay && month == userMonth && year == userYear) {
                    searchQueue.enqueue(order);
                }
            }
        }

        for (int i = 1; i < matchedList.getTotalEntries() - 1; i++) {
            int index = i;
            for (int j = i; j <= matchedList.getTotalEntries(); j++) {
                if (matchedList.getItem(j).getOrderDate().before(matchedList.getItem(index).getOrderDate())) {
                    index = j; //searching for lowest index  
                }
            }
            Order smallerOrder = matchedList.getItem(index);
            matchedList.replace(index, matchedList.getItem(i));
            matchedList.replace(i, smallerOrder);

        }

        displaySortedPickup(matchedList, searchQueue);
    }

    public static void sortPickupOrder(ListInterface<Order> pickupOrder, QueueInterface customizeOrder) {

        ListInterface<Order> sortedList = new LinkedList<>();

        QueueInterface<CustomizedPackage> customOrder = customizeOrder;

        ListInterface<Order> unOrderList = pickupOrder;

        QueueInterface<CustomizedPackage> searchQueue = new ArrayQueue<>();

        Calendar cal = Calendar.getInstance();
        Calendar listCal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        Calendar CuslistCal = Calendar.getInstance();
        cal.setTime(new Date());

        int day, month, year, userDay, userMonth, userYear;

        userDay = cal.get(Calendar.DAY_OF_MONTH);
        userMonth = cal.get(Calendar.MONTH) + 1;
        userYear = cal.get(Calendar.YEAR);

        for (int j = 1; j <= unOrderList.getTotalEntries(); j++) {
            listCal.setTime(unOrderList.getItem(j).getOrderDate());

            day = listCal.get(Calendar.DAY_OF_MONTH);
            month = listCal.get(Calendar.MONTH) + 1;
            year = listCal.get(Calendar.YEAR);

            if (day == userDay && month == userMonth && year == userYear) {
                sortedList.add(unOrderList.getItem(j));
            }
        }

        for (int i = -1; i <= customOrder.getBackIndex(); i++) {

            CustomizedPackage tempCustomOrder = customOrder.dequeue();
            CuslistCal.setTime(tempCustomOrder.getDeliveryDate());

            day = CuslistCal.get(Calendar.DAY_OF_MONTH);
            month = CuslistCal.get(Calendar.MONTH) + 1;
            year = CuslistCal.get(Calendar.YEAR);

            if (day == userDay && month == userMonth && year == userYear) {
                searchQueue.enqueue(tempCustomOrder);
            }
        }

        for (int i = 1; i < sortedList.getTotalEntries() - 1; i++) {
            int index = i;
            for (int j = i; j <= sortedList.getTotalEntries(); j++) {
                if (sortedList.getItem(j).getOrderDate().before(sortedList.getItem(index).getOrderDate())) {
                    index = j; //searching for lowest index  
                }
            }
            Order smallerOrder = sortedList.getItem(index);
            sortedList.replace(index, sortedList.getItem(i));
            sortedList.replace(i, smallerOrder);
        }

        displaySortedPickup(sortedList, searchQueue);

    }

    public static void displaySortedPickup(ListInterface<Order> orderedList, QueueInterface<CustomizedPackage> customOrder) {

        QueueInterface<CustomizedPackage> showQueue = new ArrayQueue<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dt = new SimpleDateFormat("HH:mm");

        System.out.println("PICK UP ORDER");
        System.out.println("-------------------------------------------------------");
        System.out.println("\nCatalog Order");
        System.out.println("=======================================================");
        for (int k = 1; k <= orderedList.getTotalEntries(); k++) {

            Order order = orderedList.getItem(k);

            if (orderedList.getItem(k).getUser() instanceof CorporateCustomer) {

                CorporateCustomer corp = (CorporateCustomer) orderedList.getItem(k).getUser();

                if (order instanceof CatalogOrders) {
                    System.out.println("Order ID: " + ((CatalogOrders) order).getOrderID());
                } else {
                    System.out.println("Order ID: " + ((CustomizedPackage) order).getOrderID());
                }
                System.out.println("Company Name: " + corp.getCompany());
                System.out.println("Contact: " + corp.getPhone());
                String date = df.format(orderedList.getItem(k).getOrderDate());
                System.out.println("Pick up Date: " + date);
                String time = dt.format(orderedList.getItem(k).getOrderDate());
                System.out.println("Pick up Time: " + time + "\n");
            } else {

                Consumer con = (Consumer) orderedList.getItem(k).getUser();

                if (order instanceof CatalogOrders) {
                    System.out.println("Order ID: " + ((CatalogOrders) order).getOrderID());
                } else {
                    System.out.println("Order ID: " + ((CustomizedPackage) order).getOrderID());
                }
                System.out.println("Name: " + con.getUsername());
                System.out.println("Contact: " + con.getPhone());
                String date = df.format(orderedList.getItem(k).getOrderDate());
                System.out.println("Pick up Date: " + date);
                String time = dt.format(orderedList.getItem(k).getOrderDate());
                System.out.println("Pick up Time: " + time + "\n");

            }
        }

        if (orderedList.isEmpty()) {
            System.out.println(FioreFlowershop.ConsoleColors.RED + "No record found!");
        }

        System.out.println("\nCustomized Package");
        System.out.println("=======================================================");

        if (!customOrder.isEmpty()) {
            for (int i = -1; i <= customOrder.getBackIndex(); i++) {
                CustomizedPackage order = customOrder.dequeue();
                if (order.getDeliveryType().getName().equals("Pickup")) {
                    System.out.println("Order ID: " + order.getOrderID());
                    System.out.println("Consumer name: " + order.getUser().getUsername());
                    System.out.println("Contact: " + order.getUser().getPhone());
                    System.out.println("Pick up date: " + order.getDeliveryDateString() + "\n");
                    showQueue.enqueue(order);
                }
            }
        } else {
            System.out.println(FioreFlowershop.ConsoleColors.RED + "No record found!");
        }

    }

    public static void searchPOrderID(String orderID, LinkedList<Order> pickUpOrder, QueueInterface<CustomizedPackage> customOrder, LinkedList<Order> paidOrder) {

        Scanner s = new Scanner(System.in);
        LinkedList<Order> pickuporder = pickUpOrder;
        LinkedList<Order> matchOrder = new LinkedList<>();

        Iterator<Order> iterator = pickuporder.getIterator();

        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (((CatalogOrders) order).getOrderID().equals(orderID)) {
                matchOrder.add(order);
            } else if (((CustomizedPackage) order).getOrderID().equals(orderID)) {
                matchOrder.add(order);
            }
        }

        if (matchOrder.isEmpty()) {
            System.out.println("No record found! Please try again!");
        } else {
            User user = matchOrder.getItem(1).getUser();

            Order order = matchOrder.getItem(1);

            if (user instanceof Consumer) {

                if (order instanceof CatalogOrders) {

                    System.out.println("Order ID: " + ((CatalogOrders) order).getOrderID());
                    System.out.println("Order Type: " + order.getOrderType());
                    System.out.println("Order Date: " + order.getOrderDate());
                    System.out.println("Username: " + order.getUser().getUsername());
                    System.out.println("Contact: " + order.getUser().getPhone());
                    System.out.println("Order Details: " + ((CatalogOrders) order).getCatalogPack());
                    System.out.println("Total Amount: " + order.getOrderAmt());
                    System.out.println("Quantity: " + ((CatalogOrders) order).getItemQuantity());
                    System.out.println("Payment Status: " + order.isPaymentStatus());
                } else {
                    System.out.println("Order ID: " + ((CustomizedPackage) order).getOrderID());
                    System.out.println("Order Type: " + order.getOrderType());
                    System.out.println("Order Date: " + order.getOrderDate());
                    System.out.println("Username: " + order.getUser().getUsername());
                    System.out.println("Contact: " + order.getUser().getPhone());
                    System.out.println("Order Details: " + ((CustomizedPackage) order).getFlower());
                    System.out.println("Total Amount: " + order.getOrderAmt());
                    System.out.println("Quantity: " + ((CatalogOrders) order).getItemQuantity());
                    System.out.println("Payment Status: " + order.isPaymentStatus());
                }
            } else {

                CorporateCustomer corp = (CorporateCustomer) matchOrder.getItem(1).getUser();

                if (order instanceof CatalogOrders) {
                    System.out.println("Order ID: " + ((CatalogOrders) order).getOrderID());
                    System.out.println("Order Type: " + matchOrder.getItem(1).getOrderType());
                    System.out.println("Order Date: " + matchOrder.getItem(1).getOrderDate());
                    System.out.println("Company Name: " + corp.getCompany());
                    System.out.println("Contact: " + matchOrder.getItem(1).getUser().getPhone());
                    System.out.println("Order Details: " + ((CatalogOrders) order).getCatalogPack().toString());
                    System.out.println("Total Amount: " + ((CatalogOrders) order).getOrderAmt());
                    System.out.println("Quantity: " + ((CatalogOrders) order).getItemQuantity());
                    System.out.println("Payment Status: " + ((CatalogOrders) order).isPaymentStatus());
                    System.out.println("Order Status: " + matchOrder.getItem(1).getOrderStatus());
                } else {
                    System.out.println("Order ID: " +((CustomizedPackage) order).getOrderID());
                    System.out.println("Order Type: " + matchOrder.getItem(1).getOrderType());
                    System.out.println("Order Date: " + matchOrder.getItem(1).getOrderDate());
                    System.out.println("Company Name: " + corp.getCompany());
                    System.out.println("Contact: " + matchOrder.getItem(1).getUser().getPhone());
                    System.out.println("Order Details: " + ((CustomizedPackage) order).getFlower());
                    System.out.println("Total Amount: " + ((CustomizedPackage) order).CalculateOrder());
                    System.out.println("Payment Status: " + ((CustomizedPackage) order).isPaymentStatus());
                    System.out.println("Order Status: " + matchOrder.getItem(1).getOrderStatus());
                }
            }

            System.out.println("1. Pick Up & Pay");
            System.out.println("2. Back");
            System.out.println("\n Your selection: ");
            int payChoice = s.nextInt();

//            if (payChoice == 1) {
//                System.out.println("Total Amount: " + matchOrder.getItem(1).getItemPrice());
//                double payAmt, change;
//
//                do {
//                    System.out.println("\n Enter amount to pay: ");
//                    payAmt = s.nextDouble();
//
//                    if (payAmt < matchOrder.getItem(1).getItemPrice()) {
//                        System.out.println("Insufficient amount, please reenter amount!");
//
//                    } else if (payAmt >= matchOrder.getItem(1).getItemPrice()) {
//
//                        change = payAmt - matchOrder.getItem(1).getItemPrice();
//                        matchOrder.getItem(1).setPaymentStat(true);
//                        matchOrder.getItem(1).setOrderStatus("Picked Up");
//                        paidOrder.add(matchOrder);
//
//                        if (change == 0) {
//                            System.out.println("Payment Change: No changes");
//                        } else {
//                            System.out.println("Payment Change: " + change);
//                        }
//                    }
//                } while (payAmt < matchOrder.getItem(1).getItemPrice());
//
//            }

            FioreFlowershop.counterStaff();

        }

    }
}

//    for(int i = 0; i < customPack ; i++ ){
//    
//}
//    
