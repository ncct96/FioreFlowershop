/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.google.maps.errors.ApiException;
import com.mycompany.fioreflowershop.adt.ArrayQueue;
import com.mycompany.fioreflowershop.adt.LinkedList;
import com.mycompany.fioreflowershop.adt.ListInterface;
import com.mycompany.fioreflowershop.adt.QueueInterface;
import com.mycompany.fioreflowershop.modal.CatalogOrders;
import com.mycompany.fioreflowershop.modal.CatalogPackage;
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
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicholas
 */
public class Delivery {

    public static void searchDelivery(LinkedList<CatalogOrders> catalogOrder, Date date, LinkedList<CustomizedPackage> customizeOrder) {
        LinkedList<CatalogOrders> unOrderList = new LinkedList<CatalogOrders>();
        LinkedList<CustomizedPackage> customOrder = new LinkedList<CustomizedPackage>();
        LinkedList<Order> matchedList = new LinkedList<>();

        Iterator<CatalogOrders> catalogIterator = catalogOrder.getIterator();
        Iterator<CustomizedPackage> CustomIterator = customizeOrder.getIterator();

        // Get all delivery order for Catalog Order
        while (catalogIterator.hasNext()) {
            if (catalogIterator.next().getOrderType().equals("Delivery")) {
                unOrderList.add(catalogIterator.next());
            }
        }

        // Get all delivery order for Customize Package
        while (CustomIterator.hasNext()) {
            if (CustomIterator.next().getOrderType().equals("Delivery")) {
                customOrder.add(CustomIterator.next());
            }
        }

        //int count = customOrder.getBackIndex();
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

        for (int i = 1; i <= customOrder.getTotalEntries(); i++) {

            listCal.setTime(customOrder.getItem(i).getOrderDate());

            day = listCal.get(Calendar.DAY_OF_MONTH);
            month = listCal.get(Calendar.MONTH) + 1;
            year = listCal.get(Calendar.YEAR);

            userDay = cal.get(Calendar.DAY_OF_MONTH);
            userMonth = cal.get(Calendar.MONTH) + 1;
            userYear = cal.get(Calendar.YEAR);

            if (day == userDay && month == userMonth && year == userYear) {
                matchedList.add(customOrder.getItem(i));
            }
        }

//        for (int i = -1; i < count; i++) {
//
//            if (!customOrder.isEmpty()) {
//                CustomizedPackage order = customOrder.dequeue();
//                CuslistCal.setTime(order.getDeliveryDate());
//
//                day = CuslistCal.get(Calendar.DAY_OF_MONTH);
//                month = CuslistCal.get(Calendar.MONTH) + 1;
//                year = CuslistCal.get(Calendar.YEAR);
//
//                if (day == userDay && month == userMonth && year == userYear) {
//                    searchQueue.enqueue(order);
//                }
//            }
//        }
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

        displaySortedDelivery(matchedList);
    }

    public static void sortDeliveryOrder(LinkedList<CatalogOrders> catalogOrder, LinkedList<CustomizedPackage> customizeOrder) {

        LinkedList<Order> sortedList = new LinkedList<>();

        LinkedList<CatalogOrders> unOrderList = new LinkedList<CatalogOrders>();
        LinkedList<CustomizedPackage> customOrder = customizeOrder;

        //int count = customOrder.getBackIndex();
        Iterator<CatalogOrders> catalogIterator = catalogOrder.getIterator();
        Iterator<CustomizedPackage> CustomIterator = customizeOrder.getIterator();

        // Get all delivery order for Catalog Order
        while (catalogIterator.hasNext()) {
            if (catalogIterator.next().getOrderType().equals("Delivery")) {
                unOrderList.add(catalogIterator.next());
            }
        }

        // Get all delivery order for Customize Package
        while (CustomIterator.hasNext()) {
            if (CustomIterator.next().getOrderType().equals("Delivery")) {
                customOrder.add(CustomIterator.next());
            }
        }

        //QueueInterface<CustomizedPackage> searchQueue = new ArrayQueue<>();
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

        for (int j = 1; j <= customOrder.getTotalEntries(); j++) {
            listCal.setTime(customOrder.getItem(j).getOrderDate());

            day = listCal.get(Calendar.DAY_OF_MONTH);
            month = listCal.get(Calendar.MONTH) + 1;
            year = listCal.get(Calendar.YEAR);

            if (day == userDay && month == userMonth && year == userYear) {
                sortedList.add(customOrder.getItem(j));
            }
        }

//        for (int i = -1; i < count; i++) {
//
//            CustomizedPackage tempCustomOrder = customOrder.dequeue();
//            CuslistCal.setTime(tempCustomOrder.getDeliveryDate());
//
//            day = CuslistCal.get(Calendar.DAY_OF_MONTH);
//            month = CuslistCal.get(Calendar.MONTH) + 1;
//            year = CuslistCal.get(Calendar.YEAR);
//
//            if (day == userDay && month == userMonth && year == userYear && tempCustomOrder.getDeliveryType().equals("Delivery")) {
//                searchQueue.enqueue(tempCustomOrder);
//            }
//        }
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

        displaySortedDelivery(sortedList);

    }

    public static void displaySortedDelivery(LinkedList<Order> orderedList) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dt = new SimpleDateFormat("HH:mm");

        LinkedList<CatalogOrders> catalogOrder = new LinkedList<CatalogOrders>();
        LinkedList<CustomizedPackage> customOrder = new LinkedList<CustomizedPackage>();

        for (int i = 1; i <= orderedList.getTotalEntries(); i++) {
            Order order = orderedList.getItem(i);
            if (order instanceof CatalogOrders) {
                catalogOrder.add((CatalogOrders) order);
            } else {
                customOrder.add((CustomizedPackage) order);
            }
        }

        System.out.println("DELIVERY ORDER");
        System.out.println("-------------------------------------------------------");
        System.out.println("\nCatalog Order");
        System.out.println("=======================================================");
        for (int k = 1; k <= catalogOrder.getTotalEntries(); k++) {

            CatalogOrders order = catalogOrder.getItem(k);

            if (catalogOrder.getItem(k).getUser() instanceof CorporateCustomer) {

                CorporateCustomer corp = (CorporateCustomer) catalogOrder.getItem(k).getUser();

                System.out.println("Order ID: " + order.getOrderID());

                System.out.println("Company Name: " + corp.getCompany());
                System.out.println("Contact: " + corp.getPhone());
                String date = df.format(catalogOrder.getItem(k).getOrderDate());
                System.out.println("Delivery Date: " + date + "\n");
            } else {

                Consumer con = (Consumer) catalogOrder.getItem(k).getUser();

                System.out.println("Order ID: " + order.getOrderID());

                System.out.println("Name: " + con.getUsername());
                System.out.println("Contact: " + con.getPhone());
                String date = df.format(catalogOrder.getItem(k).getOrderDate());
                System.out.println("Delivery Date: " + date + "\n");
            }
        }

        if (catalogOrder.isEmpty()) {
            System.out.println(FioreFlowershop.ConsoleColors.RED + "No record found!");
        }

        System.out.println("\nCustomized Package");
        System.out.println("=======================================================");

        for (int k = 1; k <= customOrder.getTotalEntries(); k++) {

            CustomizedPackage order = customOrder.getItem(k);

            if (customOrder.getItem(k).getUser() instanceof CorporateCustomer) {

                CorporateCustomer corp = (CorporateCustomer) customOrder.getItem(k).getUser();

                System.out.println("Order ID: " + order.getOrderID());

                System.out.println("Company Name: " + corp.getCompany());
                System.out.println("Contact: " + corp.getPhone());
                String date = df.format(customOrder.getItem(k).getOrderDate());
                System.out.println("Delivery Date: " + date + "\n");
            } else {

                Consumer con = (Consumer) customOrder.getItem(k).getUser();

                System.out.println("Order ID: " + order.getOrderID());

                System.out.println("Name: " + con.getUsername());
                System.out.println("Contact: " + con.getPhone());
                String date = df.format(customOrder.getItem(k).getOrderDate());
                System.out.println("Delivery Date: " + date + "\n");
            }
        }

        if (customOrder.isEmpty()) {
            System.out.println(FioreFlowershop.ConsoleColors.RED + "No record found!");
        }
//        if (!customOrder.isEmpty()) {
//            for (int i = -1; i <= customOrder.getBackIndex(); i++) {
//                CustomizedPackage order = customOrder.dequeue();
//                if (order.getDeliveryType().getName().equals("Deliver")) {
//                    System.out.println("Order ID: " + order.getOrderID());
//                    System.out.println("Consumer name: " + order.getUser().getUsername());
//                    System.out.println("Contact: " + order.getUser().getPhone());
//                    System.out.println("Delivery date: " + order.getDeliveryDateString() + "\n");
//                    showQueue.enqueue(order);
//                }
//            }
//        } else {
//            System.out.println(FioreFlowershop.ConsoleColors.RED + "No record found!");
//        }
    }

    public static void sortRouteDelivery(LinkedList<CatalogOrders> catalogOrder, LinkedList<CustomizedPackage> customizeOrder, String shopAddress) throws ApiException, InterruptedException, IOException {
        LinkedList<Order> sortedList = new LinkedList<>();

        LinkedList<CatalogOrders> unOrderList = new LinkedList<CatalogOrders>();
        LinkedList<CustomizedPackage> customOrder = customizeOrder;

        //int count = customOrder.getBackIndex();
        Iterator<CatalogOrders> catalogIterator = catalogOrder.getIterator();
        Iterator<CustomizedPackage> CustomIterator = customizeOrder.getIterator();

        // Get all delivery order for Catalog Order
        while (catalogIterator.hasNext()) {
            CatalogOrders order = catalogIterator.next();

            if (order.getOrderType().equals("Delivery")) {
                unOrderList.add(order);
            }
        }

        // Get all delivery order for Customize Package
        while (CustomIterator.hasNext()) {
            CustomizedPackage order = CustomIterator.next();

            if (order.getDeliveryType().getName().equals("Delivery")) {
                customOrder.add(order);
            }
        }

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

        for (int j = 1; j <= customOrder.getTotalEntries(); j++) {
            listCal.setTime(customOrder.getItem(j).getOrderDate());

            day = listCal.get(Calendar.DAY_OF_MONTH);
            month = listCal.get(Calendar.MONTH) + 1;
            year = listCal.get(Calendar.YEAR);

            if (day == userDay && month == userMonth && year == userYear) {
                sortedList.add(customOrder.getItem(j));
            }
        }

//        for (int i = -1; i < count; i++) {
//
//            CustomizedPackage tempCustomOrder = customOrder.dequeue();
//            CuslistCal.setTime(tempCustomOrder.getDeliveryDate());
//
//            day = CuslistCal.get(Calendar.DAY_OF_MONTH);
//            month = CuslistCal.get(Calendar.MONTH) + 1;
//            year = CuslistCal.get(Calendar.YEAR);
//
//            if (day == userDay && month == userMonth && year == userYear) {
//                searchQueue.enqueue(tempCustomOrder);
//            }
//        }
        sortRoute(sortedList, shopAddress);
    }

    public static void sortRoute(LinkedList<Order> sortedList, String shopAddress) throws ApiException, InterruptedException, IOException {

        Date date = new Date();
        TSPSolver solver;

        LinkedList<Order> dest = new LinkedList<>();

        for (int i = 1; i <= sortedList.getTotalEntries(); i++) {
            dest.add(sortedList.getItem(i));
        }

//        while (!searchQueue.isEmpty()) {
//            dest.add(dest.getTotalEntries(), searchQueue.dequeue());
//        }
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

    public static void searchUserPickUp(String userID, LinkedList<CatalogOrders> catalogOrder, LinkedList<CustomizedPackage> customOrder, LinkedList<Order> paidOrder) {

        LinkedList<CatalogOrders> pickuporder = catalogOrder;
        LinkedList<Order> matchOrder = new LinkedList<>();

        Scanner s = new Scanner(System.in);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        User user = null;

        // Get all CatalogOrder with Pick Up type
        Iterator<CatalogOrders> iterator = pickuporder.getIterator();

        while (iterator.hasNext()) {
            CatalogOrders order = iterator.next();

            if (order.getUser().getUsername().equals(userID)) {
                user = order.getUser();
                matchOrder.add(order);
            }
        }

        Iterator<CustomizedPackage> customIterator = customOrder.getIterator();

        while (customIterator.hasNext()) {
            CustomizedPackage order = customIterator.next();

            if (order.getUser().getUsername().equals(userID)) {
                user = order.getUser();
                matchOrder.add(order);
            }
        }

        if (user == null) {
            System.out.println("User ID does not exist in system!");

        } else if (matchOrder.isEmpty()) {
            System.out.println("No order with pending payment found! Please try again!");

        } else {

            System.out.println("Hi " + user.getUsername() + ",");
            System.out.println("These are your orders with pending payment");
            System.out.println("|No.|\t|Order ID|\t\t|Order Type|\t\t|Order Date|\t\t|Customer Name|\t\t|Payment Amount|\t\t|Payment Status|");
            System.out.println("=======================================================================================");

            for (int i = 1; i <= matchOrder.getTotalEntries(); i++) {
                Order order = matchOrder.getItem(i);

                if (user instanceof Consumer) {

                    if (order instanceof CatalogOrders) {
                        System.out.print(i + "\t");
                        System.out.print(((CatalogOrders) order).getOrderID() + "\t");
                        System.out.print(order.getOrderType() + "\t");
                        System.out.print(df.format(order.getOrderDate()) + "\t");
                        //System.out.print("Username: " + order.getUser().getUsername());
                        //System.out.print("Contact: " + order.getUser().getPhone());
                        //System.out.print("Order Details: " + ((CatalogOrders) order).getCatalogPack());
                        System.out.print(order.getOrderAmt() + "\t");
                        //System.out.print("Quantity: " + ((CatalogOrders) order).getItemQuantity());
                        System.out.print(order.isPaymentStatus() + "\n");
                    } else {
                        System.out.print(((CustomizedPackage) order).getOrderID());
                        System.out.print(order.getOrderType());
                        System.out.print(df.format(order.getOrderDate()));
                        System.out.print(order.getUser().getUsername());
                        System.out.print(order.getUser().getPhone());
                        System.out.print(((CustomizedPackage) order).getFlower());
                        System.out.print(order.getOrderAmt());
                        System.out.print(((CatalogOrders) order).getCatalogPack().getItem(i).getUserQuantity());
                        System.out.print(order.isPaymentStatus());
                    }
                } else {

                    if (order instanceof CatalogOrders) {
                        System.out.print(i + "\t");
                        System.out.print(((CatalogOrders) order).getOrderID() + "\t");
                        System.out.print(order.getOrderType() + "\t");
                        System.out.print(df.format(order.getOrderDate()) + "\t");
                        System.out.print(((CorporateCustomer) user).getCompany() + "\t");
                        //System.out.println("Contact: " + matchOrder.getItem(1).getUser().getPhone());
                        //System.out.println("Order Details: " + ((CatalogOrders) order).getCatalogPack().toString());
                        System.out.print(((CatalogOrders) order).getOrderAmt() + "\t");
                        //System.out.println("Quantity: " + ((CatalogOrders) order).getItemQuantity());
                        System.out.print(((CatalogOrders) order).isPaymentStatus() + "\t");
                        //System.out.println("Order Status: " + matchOrder.getItem(1).getOrderStatus());
                    } else {
                        System.out.print(((CustomizedPackage) order).getOrderID() + "\t");
                        System.out.print(order.getOrderType() + "\t");
                        System.out.print(df.format(order.getOrderDate()) + "\t");
                        System.out.print(((CorporateCustomer) user).getCompany() + "\t");
                        //System.out.println("Contact: " + matchOrder.getItem(1).getUser().getPhone());
                        //System.out.println("Order Details: " + ((CustomizedPackage) order).getFlower());
                        System.out.print(((CustomizedPackage) order).CalculateOrder() + "\t");
                        System.out.print(((CustomizedPackage) order).isPaymentStatus() + "\n");
                        //System.out.println("Order Status: " + matchOrder.getItem(1).getOrderStatus());
                    }
                }
            }

            System.out.println("\n\n1. Pick Up & Pay");
            System.out.println("2. Back");
            System.out.println("Your selection: ");
            int payChoice = s.nextInt();

            if (payChoice == 1) {
                System.out.println("Select order to pick up & pay");

                int orderChoice = s.nextInt();

                if (matchOrder.getItem(orderChoice).isPaymentStatus()) {
                    System.out.println("The order already paid!");
                } else {
                    System.out.println("Total Amount: " + matchOrder.getItem(orderChoice).getOrderAmt());
                    double payAmt, change = 0;

                    do {
                        System.out.println("\n Enter amount to pay: ");
                        payAmt = s.nextDouble();

                        if (payAmt < matchOrder.getItem(orderChoice).getOrderAmt()) {
                            System.out.println("Insufficient amount, please reenter amount!");

                        } else if (payAmt >= matchOrder.getItem(orderChoice).getOrderAmt()) {

                            change = payAmt - matchOrder.getItem(orderChoice).getOrderAmt();
                            matchOrder.getItem(orderChoice).setPaymentStatus(true);
                            matchOrder.getItem(orderChoice).setOrderStatus("Delivered");
                            paidOrder.add(matchOrder.getItem(orderChoice));

                            if (change == 0) {
                                System.out.println("Payment Change: No changes");
                            } else {
                                System.out.println("Payment Change: RM " + change);
                            }

                            matchOrder.getItem(orderChoice).setPaymentTime(new Date());
                            matchOrder.getItem(orderChoice).setDateOfReceive(new Date());
                            genReceipt(matchOrder.getItem(orderChoice), payAmt, change);

                        }
                    } while (payAmt < matchOrder.getItem(orderChoice).getOrderAmt());
                }
            } else {
                FioreFlowershop.counterStaff();
            }
        }

    }

    public static void genReceipt(Order order, double payAmt, double change) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        if (order instanceof CatalogOrders) {
            System.out.println("\n\t\t   Fiore Flowershop SDN.NHD ");
            System.out.println("\t\t    178, Jalan Sehala, ");
            System.out.println("\t\t 2404 No U Turn, 53300 Kuala Lumpur");

            System.out.println("\nID : " + ((CatalogOrders) order).getOrderID());
            System.out.println("Payment Date : " + order.isPaymentStatus());
            System.out.println("Cashier : Admin");
            System.out.println("Payment Time : " + df.format(order.getPaymentTime()));
            System.out.println("=================================================================");
            System.out.println("ITEM \t\t\t QUANTITY \t PRICE \t\t AMOUNT");
            System.out.println("=================================================================");
            LinkedList<CatalogPackage> cat = ((CatalogOrders) order).getCatalogPack();
            CatalogPackage item;
            Iterator<CatalogPackage> catIterator = cat.getIterator();

            while (catIterator.hasNext()) {
                item = catIterator.next();
                System.out.println(item.getName() + item.getFlower() + "\t\t\t" + item.getUserQuantity() + "\t\t" + item.getPrice() + "\t\t");
            }

            System.out.println("\n-----------------------------------------------------------------");
            System.out.println("\tTOTAL : \t    5 \t\t\t\t 300.00");
            System.out.println("-----------------------------------------------------------------");
            System.out.println("CASH : \t\t\t\t\t300.00");
            System.out.println("CASH TENDERED : \t\t\t" + payAmt);
            System.out.println("BALANCE : \t\t\t\t" + change);
            System.out.println("\n=================================================================");
            System.out.println("Thank You For Choosing Fiore Flowershop, Please Come Again :D");
            System.out.println("=================================================================");
        } else {
            System.out.println("\n\t\t   Fiore Flowershop SDN.NHD ");
            System.out.println("\t\t    178, Jalan Sehala, ");
            System.out.println("\t\t 2404 No U Turn, 53300 Kuala Lumpur");

            System.out.println("\nID : " + ((CustomizedPackage) order).getOrderID());
            System.out.println("Payment Date : " + order.isPaymentStatus());
            System.out.println("Cashier : Admin");
            System.out.println("Payment Time : " + df.format(order.getPaymentTime()));
            System.out.println("=================================================================");
            System.out.println("ITEM \t\t\t QUANTITY \t PRICE \t\t AMOUNT");
            System.out.println("=================================================================");

            System.out.println("\n-----------------------------------------------------------------");
            System.out.println("\tTOTAL : \t    5 \t\t\t\t 300.00");
            System.out.println("-----------------------------------------------------------------");
            System.out.println("CASH : \t\t\t\t\t300.00");
            System.out.println("CASH TENDERED : \t\t\t" + payAmt);
            System.out.println("BALANCE : \t\t\t\t" + change);
            System.out.println("\n=================================================================");
            System.out.println("Thank You For Choosing Fiore Flowershop, Please Come Again :D");
            System.out.println("=================================================================");
        }
    }

}
