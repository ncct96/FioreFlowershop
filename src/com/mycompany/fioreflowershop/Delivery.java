/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.google.maps.errors.ApiException;
import com.mycompany.fioreflowershop.adt.ArrayList;
import com.mycompany.fioreflowershop.adt.ArrayQueue;
import com.mycompany.fioreflowershop.adt.LinkedList;
import com.mycompany.fioreflowershop.adt.ListInterface;
import com.mycompany.fioreflowershop.adt.QueueInterface;
import com.mycompany.fioreflowershop.modal.CatalogOrders;
import com.mycompany.fioreflowershop.modal.Consumer;
import com.mycompany.fioreflowershop.modal.CorporateCustomer;
import com.mycompany.fioreflowershop.modal.CustomizedPackage;
import com.mycompany.fioreflowershop.modal.Order;
import com.mycompany.fioreflowershop.modal.User;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

            listCal.setTime(unOrderList.getItem(i).getOrderDate());

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

        for (int i = -1; i < count; i++) {

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
        displaySortedDelivery(matchedList, searchQueue);
    }

    public static void sortDeliveryOrder(ListInterface<Order> deliveryOrder, QueueInterface customizeOrder) {

        ListInterface<Order> sortedList = new ArrayList<>();

        QueueInterface<CustomizedPackage> customOrder = customizeOrder;

        int count = customOrder.getBackIndex();

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
            listCal.setTime(unOrderList.getItem(j).getOrderDate());

            day = listCal.get(Calendar.DAY_OF_MONTH);
            month = listCal.get(Calendar.MONTH) + 1;
            year = listCal.get(Calendar.YEAR);

            if (day == userDay && month == userMonth && year == userYear) {
                sortedList.add(unOrderList.getItem(j));
            }
        }

        for (int i = -1; i < count; i++) {

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
                System.out.println("Delivery Date: " + date + "\n");
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
                    System.out.println("Order ID: " + order.getOrderID());
                    System.out.println("Consumer name: " + order.getUser().getUsername());
                    System.out.println("Contact: " + order.getUser().getPhone());
                    System.out.println("Delivery date: " + order.getDeliveryDateString() + "\n");
                    showQueue.enqueue(order);
                }
            }
        } else {
            System.out.println(FioreFlowershop.ConsoleColors.RED + "No record found!");
        }

    }

    public static void sortRouteDelivery(LinkedList<Order> deliveryOrder, QueueInterface<CustomizedPackage> customizeOrder, String shopAddress) throws ApiException, InterruptedException, IOException {
        LinkedList<Order> sortedList = new LinkedList<>();

        QueueInterface<CustomizedPackage> customOrder = customizeOrder;

        LinkedList<Order> unOrderList = deliveryOrder;

        int count = customOrder.getBackIndex();

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

        for (int i = -1; i < count; i++) {

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

    public static void sortRoute(LinkedList<Order> sortedList, QueueInterface<CustomizedPackage> searchQueue, String shopAddress) throws ApiException, InterruptedException, IOException {

        Date date = new Date();
        TSPSolver solver;

        LinkedList<Order> dest = new LinkedList<>();

        for (int i = 1; i <= sortedList.getTotalEntries(); i++) {
            dest.add(sortedList.getItem(i));
        }

        while (!searchQueue.isEmpty()) {
            dest.add(dest.getTotalEntries(), searchQueue.dequeue());
        }

        try {
            solver = DeliveryOptimization.distanceMatrix(shopAddress, dest);
            displaySortRoute(solver, dest, shopAddress);
        } catch (IOException ex) {
            Logger.getLogger(Delivery.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void displaySortRoute(TSPSolver solver, LinkedList<Order> dest, String shopAddress) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        List<Integer> tour = solver.getTour();
        double totalPayment = 0;
        System.out.println("Date: " + dateFormat.format(date));
        System.out.println("===========================");
        System.out.println("Today's Delivery Route (Nearest to Furthest) ");
        System.out.println("================================================" + "\n");

        System.out.println("Origin: " + shopAddress + "\n");

        for (int i = 0; i < tour.size() - 1; i++) {
            int tourCount = tour.get(i).intValue();
            User user;

            if (i > 0 && i < tour.size() - 1) {
                user = dest.getItem(tourCount).getUser();
                Order order = dest.getItem(tourCount);

                if (user instanceof CorporateCustomer) {
                    CorporateCustomer corp = (CorporateCustomer) dest.getItem(tourCount).getUser();
                    System.out.println("Delivery Order " + (i));
                    System.out.println("================");
                    System.out.println("Address: " + dest.getItem(tourCount).getUser().getAddress());
                    if (order instanceof CatalogOrders) {
                        System.out.println("Order ID: " + ((CatalogOrders) order).getOrderID());
                    } else {
                        System.out.println("Order ID: " + ((CustomizedPackage) order).getOrderID());

                    }
                    System.out.println("Company Name: " + corp.getCompany());
                    System.out.println("Name: " + dest.getItem(tourCount).getUser().getUsername());
                    System.out.println("Contact: " + dest.getItem(tourCount).getUser().getPhone());
                    System.out.println("Delivery Date: " + dateFormat.format(dest.getItem(tourCount).getOrderDate()));
                    System.out.println("Order type: " + dest.getItem(tourCount).getOrderType());
                    System.out.println("Payment: RM" + dest.getItem(tourCount).getOrderAmt() + "\n");
                } else {
                    System.out.println("Delivery Order " + (i));
                    System.out.println("================");
                    System.out.println("Address: " + dest.getItem(tourCount).getUser().getAddress());
                    if (order instanceof CatalogOrders) {
                        System.out.println("Order ID: " + ((CatalogOrders) order).getOrderID());
                    } else {
                        System.out.println("Order ID: " + ((CustomizedPackage) order).getOrderID());

                    }
                    System.out.println("Name: " + dest.getItem(tourCount).getUser().getUsername());
                    System.out.println("Contact: " + dest.getItem(tourCount).getUser().getPhone());
                    System.out.println("Delivery Date: " + dateFormat.format(dest.getItem(tourCount).getOrderDate()));
                    System.out.println("Order type: " + dest.getItem(tourCount).getOrderType());
                    System.out.println("Payment: RM" + dest.getItem(tourCount).getOrderAmt() + "\n");
                }
                totalPayment += dest.getItem(tourCount).getOrderAmt();
            }
        }

        System.out.println("Origin: " + shopAddress);
        System.out.println("Total Payment Amount: RM" + totalPayment);
    }

}
