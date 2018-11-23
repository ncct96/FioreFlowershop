/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.google.maps.errors.ApiException;
import com.mycompany.fioreflowershop.adt.ArrayList;
import com.mycompany.fioreflowershop.adt.ArrayQueue;
import com.mycompany.fioreflowershop.adt.ListInterface;
import com.mycompany.fioreflowershop.adt.QueueInterface;
import com.mycompany.fioreflowershop.modal.Consumer;
import com.mycompany.fioreflowershop.modal.CustomizedPackage;
import com.mycompany.fioreflowershop.modal.Order;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author Nicholas
 */
public class Delivery {

    public static void searchDelivery(ListInterface deliveryOrder, Date date, QueueInterface<CustomizedPackage> customOrder) {
        ListInterface<Order> unOrderList = deliveryOrder;
        ListInterface<Order> matchedList = new ArrayList<>();
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

            listCal.setTime(unOrderList.getItem(i).getDate());

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
                if (matchedList.getItem(j).getDate().before(matchedList.getItem(index).getDate())) {
                    index = j; //searching for lowest index  
                }
            }
            Order smallerOrder = matchedList.getItem(index);
            matchedList.replace(index, matchedList.getItem(i));
            matchedList.replace(i, smallerOrder);

        }
        displaySortedDelivery(matchedList, searchQueue);
    }

    public static void sortDeliveryOrder(ListInterface<Order> deliveryOrder, QueueInterface customizeOrder) {

        ListInterface<Order> sortedList = new ArrayList<>();

        QueueInterface<CustomizedPackage> customOrder = customizeOrder;

        ListInterface<Order> unOrderList = deliveryOrder;

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
            listCal.setTime(unOrderList.getItem(j).getDate());

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
                if (sortedList.getItem(j).getDate().before(sortedList.getItem(index).getDate())) {
                    index = j; //searching for lowest index  
                }
            }
            Order smallerOrder = sortedList.getItem(index);
            sortedList.replace(index, sortedList.getItem(i));
            sortedList.replace(i, smallerOrder);
        }

        displaySortedDelivery(sortedList, searchQueue);

    }

    public static void displaySortedDelivery(ListInterface<Order> orderedList, QueueInterface<CustomizedPackage> customOrder) {

        QueueInterface<CustomizedPackage> showQueue = new ArrayQueue<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dt = new SimpleDateFormat("HH:mm");

        System.out.println("DELIVERY ORDER");
        System.out.println("-------------------------------------------------------");
        System.out.println("\nCatalog Order");
        System.out.println("=======================================================");
        for (int k = 1; k <= orderedList.getTotalEntries(); k++) {

            if (orderedList.getItem(k).getCorp().getCompany() != null) {
                System.out.println("Order ID: " + orderedList.getItem(k).getOrderID());
                System.out.println("Company Name: " + orderedList.getItem(k).getCorp().getCompany());
                System.out.println("Contact: " + orderedList.getItem(k).getCorp().getPhone());
                String date = df.format(orderedList.getItem(k).getDate());
                System.out.println("Delivery Date: " + date + "\n");
            } else {
                System.out.println("Order ID: " + orderedList.getItem(k).getOrderID());
                System.out.println("Name: " + orderedList.getItem(k).getCon().getUsername());
                System.out.println("Contact: " + orderedList.getItem(k).getCon().getPhone());
                String date = df.format(orderedList.getItem(k).getDate());
                System.out.println("Delivery Date: " + date + "\n");
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
                if (order.getDeliveryType().getName().equals("Deliver")) {
                    System.out.println("Order ID: " + order.getOrderNum());
                    System.out.println("Consumer name: " + order.getCustomer().getUsername());
                    System.out.println("Contact: " + order.getCustomer().getPhone());
                    System.out.println("Delivery date: " + order.getDeliveryDateString() + "\n");
                    showQueue.enqueue(order);
                }
            }
        } else {
            System.out.println(FioreFlowershop.ConsoleColors.RED + "No record found!");
        }

    }

    public static void sortRouteDelivery(ListInterface<Order> deliveryOrder, QueueInterface customizeOrder, String shopAddress) throws ApiException, InterruptedException, IOException {
        ListInterface<Order> sortedList = new ArrayList<>();

        QueueInterface<CustomizedPackage> customOrder = customizeOrder;

        ListInterface<Order> unOrderList = deliveryOrder;

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
            listCal.setTime(unOrderList.getItem(j).getDate());

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

        sortRoute(sortedList, searchQueue, shopAddress);
    }

    public static void sortRoute(ListInterface<Order> sortedList, QueueInterface<CustomizedPackage> searchQueue, String shopAddress) throws ApiException, InterruptedException, IOException {

        Date date = new Date();
        Consumer con = new Consumer(shopAddress, shopAddress, shopAddress, shopAddress);
        Order shop = new Order(0, "Delivery", con, date);
        
//        ListInterface<Order> sortedCustomOrder = new ArrayList<>();
        ListInterface<Order> origin = new ArrayList<>((sortedList.getTotalEntries()) + (searchQueue.getBackIndex() + 1) + 1);
        ListInterface<Order> dest = new ArrayList<>((sortedList.getTotalEntries()) + (searchQueue.getBackIndex() + 1) + 1);
        

        origin.add(1, shop);
        dest.add(1, shop);
        
        for(int i = 1; i < sortedList.getTotalEntries(); i++){
            origin.add(sortedList.getItem(i + 1));
            dest.add(sortedList.getItem(i + 1));
        }

        while(!searchQueue.isEmpty()) {
            origin.add(origin.getTotalEntries() + 1,searchQueue.dequeue());
            dest.add(origin.getTotalEntries() + 1,searchQueue.dequeue());
        }
        
        try {
            DeliveryOptimization.distanceMatrix(origin, dest);
        } catch (IOException ex) {
            Logger.getLogger(Delivery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
