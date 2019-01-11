/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.google.maps.errors.ApiException;
import static com.mycompany.fioreflowershop.Pickup.genReceipt;
import com.mycompany.fioreflowershop.adt.ArrayQueue;
import com.mycompany.fioreflowershop.adt.CatalogPackageInterface;
import com.mycompany.fioreflowershop.adt.LinkedList;
import com.mycompany.fioreflowershop.adt.ListInterface;
import com.mycompany.fioreflowershop.adt.OrderList;
import com.mycompany.fioreflowershop.adt.OrderListInterface;
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

    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN

    public static OrderListInterface<Order> searchDelivery(Date date, OrderListInterface<Order> readyOrder) {
        
        OrderListInterface<Order> unSortedList = new OrderList<Order>();
        OrderListInterface<Order> matchedList = new OrderList<>();

        Iterator<Order> orderIterator = readyOrder.getIterator();

        // Get all delivery order for Catalog Order
        while (orderIterator.hasNext()) {

            Order order = orderIterator.next();

            if (order.getOrderType().equals("Delivery")) {
                unSortedList.addOrder(order);
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

        for (int i = 1; i <= unSortedList.getSize(); i++) {

            listCal.setTime(unSortedList.getOrder(i).getDeliveryDate());

            day = listCal.get(Calendar.DAY_OF_MONTH);
            month = listCal.get(Calendar.MONTH) + 1;
            year = listCal.get(Calendar.YEAR);

            userDay = cal.get(Calendar.DAY_OF_MONTH);
            userMonth = cal.get(Calendar.MONTH) + 1;
            userYear = cal.get(Calendar.YEAR);

            if (day == userDay && month == userMonth && year == userYear) {
                matchedList.addOrder(unSortedList.getOrder(i));
            }
        }

        for (int i = 1; i < matchedList.getSize() - 1; i++) {
            int index = i;
            for (int j = i; j <= matchedList.getSize(); j++) {
                if (matchedList.getOrder(j).getDeliveryDate().before(matchedList.getOrder(index).getDeliveryDate())) {
                    index = j; //searching for lowest index  
                }
            }
            Order smallerOrder = matchedList.getOrder(index);
            matchedList.replaceOrder(index, matchedList.getOrder(i));
            matchedList.replaceOrder(i, smallerOrder);

        }

        displaySortedDelivery(matchedList);

        return matchedList;
    }

    public static void sortDeliveryOrder(OrderListInterface<Order> readyOrder) {

        OrderListInterface<Order> unSortedList = new OrderList<>();
        OrderListInterface<Order> sortedList = new OrderList<>();

        //int count = customOrder.getBackIndex();
        Iterator<Order> orderIterator = readyOrder.getIterator();

        // Get all delivery order for Catalog Order
        while (orderIterator.hasNext()) {
            Order order = orderIterator.next();

            if (order.getOrderType().equals("Delivery")) {
                unSortedList.addOrder(order);
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

        for (int j = 1; j <= unSortedList.getSize(); j++) {
            listCal.setTime(unSortedList.getOrder(j).getDeliveryDate());

            day = listCal.get(Calendar.DAY_OF_MONTH);
            month = listCal.get(Calendar.MONTH) + 1;
            year = listCal.get(Calendar.YEAR);

            if (day == userDay && month == userMonth && year == userYear) {
                sortedList.addOrder(unSortedList.getOrder(j));
            }
        }

        for (int i = 1; i < sortedList.getSize() - 1; i++) {
            int index = i;
            for (int j = i; j <= sortedList.getSize(); j++) {
                if (sortedList.getOrder(j).getDeliveryDate().before(sortedList.getOrder(index).getDeliveryDate())) {
                    index = j; //searching for lowest index  
                }
            }
            Order smallerOrder = sortedList.getOrder(index);
            sortedList.replaceOrder(index, sortedList.getOrder(i));
            sortedList.replaceOrder(i, smallerOrder);
        }
        
        displaySortedDelivery(sortedList);
    }

    public static void displaySortedDelivery(OrderListInterface<Order> orderedList) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dt = new SimpleDateFormat("HH:mm");

        LinkedList<CatalogOrders> catalogOrder = new LinkedList<CatalogOrders>();
        LinkedList<CustomizedPackage> customOrder = new LinkedList<CustomizedPackage>();

        for (int i = 1; i <= orderedList.getSize(); i++) {
            Order order = orderedList.getOrder(i);
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
                String date = df.format(catalogOrder.getItem(k).getDeliveryDate());
                System.out.println("Delivery Date: " + date + "\n");
            } else {

                Consumer con = (Consumer) catalogOrder.getItem(k).getUser();

                System.out.println("Order ID: " + order.getOrderID());

                System.out.println("Name: " + con.getUsername());
                System.out.println("Contact: " + con.getPhone());
                String date = df.format(catalogOrder.getItem(k).getDeliveryDate());
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
                String date = df.format(customOrder.getItem(k).getDeliveryDate());
                System.out.println("Delivery Date: " + date + "\n");
            } else {

                Consumer con = (Consumer) customOrder.getItem(k).getUser();

                System.out.println("Order ID: " + order.getOrderID());

                System.out.println("Name: " + con.getUsername());
                System.out.println("Contact: " + con.getPhone());
                String date = df.format(customOrder.getItem(k).getDeliveryDate());
                System.out.println("Delivery Date: " + date + "\n");
            }
        }

        if (customOrder.isEmpty()) {
            System.out.println(FioreFlowershop.ConsoleColors.RED + "No record found!");
        }
    }

    public static void sortRouteDelivery(OrderListInterface<Order> readyOrder, String shopAddress) throws ApiException, InterruptedException, IOException {
        OrderListInterface<Order> unSortedList = new OrderList<>();
        OrderListInterface<Order> sortedList = new OrderList<>();

        //int count = customOrder.getBackIndex();
        Iterator<Order> orderIterator = readyOrder.getIterator();

        // Get all delivery order for Catalog Order
        while (orderIterator.hasNext()) {
            Order order = orderIterator.next();

            if (order.getOrderType().equals("Delivery")) {
                unSortedList.addOrder(order);
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

        for (int j = 1; j <= unSortedList.getSize(); j++) {
            listCal.setTime(unSortedList.getOrder(j).getDeliveryDate());

            day = listCal.get(Calendar.DAY_OF_MONTH);
            month = listCal.get(Calendar.MONTH) + 1;
            year = listCal.get(Calendar.YEAR);

            if (day == userDay && month == userMonth && year == userYear) {
                sortedList.addOrder(unSortedList.getOrder(j));
            }
        }

        sortRoute(sortedList, shopAddress, new Date());
    }

    public static void searchSortRouteDelivery(OrderListInterface<Order> readyOrder, String shopAddress, Date date) throws ApiException, InterruptedException, IOException {
        OrderListInterface<Order> unSortedList = new OrderList<>();
        OrderListInterface<Order> sortedList = new OrderList<>();

        //int count = customOrder.getBackIndex();
        Iterator<Order> orderIterator = readyOrder.getIterator();

        // Get all delivery order for Catalog Order
        while (orderIterator.hasNext()) {
            Order order = orderIterator.next();

            if (order.getOrderType().equals("Delivery")) {
                unSortedList.addOrder(order);
            }
        }

        Calendar cal = Calendar.getInstance();
        Calendar listCal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        Calendar CuslistCal = Calendar.getInstance();
        cal.setTime(date);

        int day, month, year, userDay, userMonth, userYear;

        userDay = cal.get(Calendar.DAY_OF_MONTH);
        userMonth = cal.get(Calendar.MONTH) + 1;
        userYear = cal.get(Calendar.YEAR);

        for (int j = 1; j <= unSortedList.getSize(); j++) {
            listCal.setTime(unSortedList.getOrder(j).getDeliveryDate());

            day = listCal.get(Calendar.DAY_OF_MONTH);
            month = listCal.get(Calendar.MONTH) + 1;
            year = listCal.get(Calendar.YEAR);

            if (day == userDay && month == userMonth && year == userYear) {
                sortedList.addOrder(unSortedList.getOrder(j));
            }
        }

        sortRoute(sortedList, shopAddress, date);
    }

    public static void sortRoute(OrderListInterface<Order> sortedList, String shopAddress, Date userDate) throws ApiException, InterruptedException, IOException {

        Date date = new Date();
        TSPSolver solver;

        OrderListInterface<Order> dest = new OrderList<>();

        for (int i = 1; i <= sortedList.getSize(); i++) {
            dest.addOrder(sortedList.getOrder(i));
        }

        try {
            solver = DeliveryOptimization.distanceMatrix(shopAddress, dest);
            displaySortRoute(solver, dest, shopAddress, userDate);
        } catch (IOException ex) {
            Logger.getLogger(Delivery.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void displaySortRoute(TSPSolver solver, OrderListInterface<Order> dest, String shopAddress, Date userDate) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dt = new SimpleDateFormat("HH:mm");
        DateFormat dfdt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Scanner s = new Scanner(System.in);
        Date date = new Date();
        List<Integer> tour = solver.getTour();
        double totalPayment = 0, payAmt;
        Order matchOrder = new Order();
        OrderListInterface<Order> paidOrder = FioreFlowershop.getPaidOrder();
        int payChoice;

        System.out.println("Date: " + dateFormat.format(userDate));
        System.out.println("===========================");
        System.out.println("Today's Delivery Route (Nearest to Furthest) ");
        System.out.println("================================================" + "\n");

        System.out.println("Start from Origin: " + shopAddress + "\n");

        for (int i = 0; i < tour.size() - 1; i++) {
            int tourCount = tour.get(i).intValue();
            User user;

            if (i > 0 && i < tour.size() - 1) {
                user = dest.getOrder(tourCount).getUser();
                Order order = dest.getOrder(tourCount);

                if (user instanceof CorporateCustomer) {
                    CorporateCustomer corp = (CorporateCustomer) dest.getOrder(tourCount).getUser();
                    System.out.println("Delivery Order " + (i));
                    System.out.println("================");
                    System.out.println("Address: " + order.getUser().getAddress());
                    if (order instanceof CatalogOrders) {
                        System.out.println("Order ID: " + ((CatalogOrders) order).getOrderID());
                        System.out.println("Company Name: " + corp.getCompany());
                        System.out.println("Name: " + dest.getOrder(tourCount).getUser().getUsername());
                        System.out.println("Contact: " + dest.getOrder(tourCount).getUser().getPhone());
                        System.out.println("Delivery Date: " + dateFormat.format(dest.getOrder(tourCount).getDeliveryDate()));
                        System.out.println("Order type: " + dest.getOrder(tourCount).getOrderType());
                        System.out.println("Payment: RM" + String.format("%.2f", dest.getOrder(tourCount).getOrderAmt()) + "\n");

                        totalPayment += order.getOrderAmt();
                    } else {
                        System.out.println("Order ID: " + ((CustomizedPackage) order).getOrderID());
                        System.out.println("Company Name: " + corp.getCompany());
                        System.out.println("Name: " + dest.getOrder(tourCount).getUser().getUsername());
                        System.out.println("Contact: " + dest.getOrder(tourCount).getUser().getPhone());
                        System.out.println("Delivery Date: " + dateFormat.format(dest.getOrder(tourCount).getDeliveryDate()));
                        System.out.println("Order type: " + ((CustomizedPackage) order).getDeliveryType().getName());
                        System.out.println("Payment: RM" + String.format("%.2f", ((CustomizedPackage) order).CalculateOrder()) + "\n");

                        totalPayment += ((CustomizedPackage) order).CalculateOrder();

                    }

                } else {
                    System.out.println("Delivery Order " + (i));
                    System.out.println("================");
                    System.out.println("Address: " + dest.getOrder(tourCount).getUser().getAddress());
                    if (order instanceof CatalogOrders) {
                        System.out.println("Order ID: " + ((CatalogOrders) order).getOrderID());
                        System.out.println("Name: " + dest.getOrder(tourCount).getUser().getUsername());
                        System.out.println("Contact: " + dest.getOrder(tourCount).getUser().getPhone());
                        System.out.println("Delivery Date: " + dateFormat.format(dest.getOrder(tourCount).getDeliveryDate()));
                        System.out.println("Order type: " + dest.getOrder(tourCount).getOrderType());
                        System.out.println("Payment: RM" + String.format("%.2f", order.getOrderAmt()) + "\n");

                        totalPayment += order.getOrderAmt();

                    } else {
                        System.out.println("Order ID: " + ((CustomizedPackage) order).getOrderID());
                        System.out.println("Name: " + dest.getOrder(tourCount).getUser().getUsername());
                        System.out.println("Contact: " + dest.getOrder(tourCount).getUser().getPhone());
                        System.out.println("Delivery Date: " + dateFormat.format(dest.getOrder(tourCount).getDeliveryDate()));
                        System.out.println("Order type: " + ((CustomizedPackage) order).getDeliveryType().getName());
                        System.out.println("Payment: RM" + String.format("%.2f", ((CustomizedPackage) order).CalculateOrder()) + "\n");

                        totalPayment += ((CustomizedPackage) order).CalculateOrder();
                    }

                }

            }
        }

        System.out.println("Back to Origin: " + shopAddress);
        System.out.println("\nTotal Payment Amount: RM" + String.format("%.2f", totalPayment) + "\n");

        System.out.println("1. Record Payment");
        System.out.println("2. Back");
        System.out.println("Your selection: ");
        int choice = s.nextInt();

        if (choice == 1) {
            s.nextLine();
            System.out.println("Enter Order ID to make payment :");
            String orderID = s.nextLine();

            OrderListInterface<Order> readyOrder = FioreFlowershop.getReadyOrder();

            Iterator<Order> orderIterator = readyOrder.getIterator();

            System.out.println("These are your orders with pending payment\n");
            System.out.println("|Order ID|\t|Order Type|\t|Order Date|\t\t|Payment Amount (RM)|\t|Payment Status|\t|Pickup Date|");
            System.out.println("=============================================================================================================================================================");

            while (orderIterator.hasNext()) {

                Order order = orderIterator.next();

                if (order.getID().equals(orderID) && order.isPaymentStatus() == false) {
                    System.out.print(order.getID() + "\t\t");
                    System.out.print(order.getOrderType() + "\t");
                    System.out.print(df.format(order.getOrderDate()) + "\t\t");
                    //System.out.print("Username: " + order.getUser().getUsername());
                    //System.out.print("Contact: " + order.getUser().getPhone());
                    //System.out.print("Order Details: " + ((CatalogOrders) order).getCatalogPack());
                    System.out.print(String.format("%.2f", order.getOrderAmt()) + "\t\t\t");
                    //System.out.print("Quantity: " + ((CatalogOrders) order).getItemQuantity());
                    if (order.isPaymentStatus()) {
                        System.out.print("Paid \t\t");
                    } else {
                        System.out.print("Pending \t\t");
                    }
                    if (order.getDateOfReceive() == null) {
                        System.out.print("Pending \n");
                    } else {
                        System.out.print(dfdt.format(order.getDateOfReceive()) + "\n");
                    }
                    matchOrder = order;
                }
            }

//            while (cusIterator.hasNext()) {
//
//                CustomizedPackage order = cusIterator.next();
//
//                if (order.getOrderID().equals(orderID) && order.isPaymentStatus() == false) {
//
//                    if (order.getUser() instanceof Consumer) {
//                        if (order instanceof CustomizedPackage) {
//                            System.out.print(((CustomizedPackage) order).getOrderID() + "\t\t");
//                            System.out.print(((CustomizedPackage) order).getDeliveryType().getName() + "\t");
//                            System.out.print(df.format(order.getOrderDate()) + "\t\t");
//                            //System.out.print(order.getUser().getUsername() + "\t");
//                            //System.out.print(order.getUser().getPhone() + "\t");
//                            // System.out.print(((CustomizedPackage) order).getFlower() + "\t");
//                            System.out.print(((CustomizedPackage) order).CalculateOrder() + "\t\t\t");
//                            //System.out.print(((CatalogOrders) order).getCatalogPack().getItem(i).getUserQuantity() + "\t");
//                            if (order.isPaymentStatus()) {
//                                System.out.print("Paid \t\t");
//                            } else {
//                                System.out.print("Pending \t\t");
//                            }
//                            if (order.getDateOfReceive() == null) {
//                                System.out.print("Pending \n");
//                            } else {
//                                System.out.print(dfdt.format(order.getDateOfReceive()) + "\n");
//                            }
//                            matchOrder = order;
//                        }
//                    }
//                }
//            }

            do {

                System.out.println("1. Pay");
                System.out.println("2. Back");
                System.out.println("Please enter your choice: ");
                payChoice = s.nextInt();

                if (payChoice == 1) {
                    do {
                        System.out.println("Enter amount to pay: ");
                        payAmt = s.nextDouble();

                        if (payAmt < matchOrder.getOrderAmt()) {
                            System.out.println("Insufficient amount, please reenter amount!");

                        } else if (payAmt >= matchOrder.getOrderAmt()) {

                            double change = payAmt - matchOrder.getOrderAmt();
                            matchOrder.setPaymentStatus(true);
                            matchOrder.setOrderStatus("Picked Up");
                            paidOrder.addOrder(matchOrder);

                            if (change == 0) {
                                System.out.println("Payment Change: No changes");
                            } else {
                                System.out.println("Payment Change: RM " + String.format("%.2f", change));
                            }

                            matchOrder.setPaymentTime(new Date());
                            matchOrder.setDateOfReceive(new Date());
                            genReceipt(matchOrder, payAmt, change);
                        }
                    } while (payAmt < matchOrder.getOrderAmt());
                } else if (payChoice == 2) {
                    try {
                        FioreFlowershop.deliveryStaff();
                    } catch (ApiException ex) {
                        Logger.getLogger(Delivery.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Delivery.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Delivery.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println("Invalid input, please try again!");
                }

            } while (payChoice != 1 || payChoice != 2);
        }
    }

    public static void searchUserDelivery(String userID, LinkedList<Order> readyOrders, LinkedList<CustomizedPackage> customOrder, LinkedList<Order> paidOrder) {

        LinkedList<Order> matchOrder = new LinkedList<>();
        User user = null;

        Scanner s = new Scanner(System.in);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dt = new SimpleDateFormat("HH:mm");
        DateFormat dfdt = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        // Get all Order with Pick Up type
        Iterator<Order> iterator = readyOrders.getIterator();

        while (iterator.hasNext()) {
            Order order = iterator.next();

            if (order.getUser().getUsername().equals(userID) && order.getOrderType().equals("Delivery") && (order.getUser() instanceof Consumer)) {
                user = order.getUser();
                matchOrder.add(order);
            }
        }

        if (user == null && !matchOrder.isEmpty()) {
            System.out.println("User ID does not exist in system!");
            FioreFlowershop.orderMenu();

        } else if (user != null && matchOrder.isEmpty()) {
            System.out.println("User don't have any order with pending payment! Please try again!");
            FioreFlowershop.orderMenu();

        } else {

            System.out.println("Hi " + user.getUsername() + ",");
            System.out.println("These are your orders with pending payment");
            System.out.println("|No.|\t|Order ID|\t|Order Type|\t|Order Date|\t\t|Payment Amount (RM)|\t|Payment Status|\t|Delivered Date|");
            System.out.println("=============================================================================================================================================================");

            for (int i = 1; i <= matchOrder.getTotalEntries(); i++) {
                Order order = matchOrder.getItem(i);

                if (user instanceof Consumer) {
                    if (order instanceof CatalogOrders) {
                        System.out.print(i + "\t");
                        System.out.print(((CatalogOrders) order).getOrderID() + "\t\t");
                        System.out.print(order.getOrderType() + "\t\t");
                        System.out.print(df.format(order.getOrderDate()) + "\t\t");
                        //System.out.print("Username: " + order.getUser().getUsername());
                        //System.out.print("Contact: " + order.getUser().getPhone());
                        //System.out.print("Order Details: " + ((CatalogOrders) order).getCatalogPack());
                        System.out.print(String.format("%.2f", order.getOrderAmt()) + "\t\t\t");
                        //System.out.print("Quantity: " + ((CatalogOrders) order).getItemQuantity());
                        if (order.isPaymentStatus()) {
                            System.out.print("Paid \t\t\t");
                        } else {
                            System.out.print("Pending \t\t\t");
                        }
                        if (order.getDateOfReceive() == null) {
                            System.out.print("Pending \n");
                        } else {
                            System.out.print(dfdt.format(order.getDateOfReceive()) + "\n");
                        }
                    } else {
                        System.out.print(i + "\t");
                        System.out.print(((CustomizedPackage) order).getOrderID() + "\t\t");
                        System.out.print(((CustomizedPackage) order).getDeliveryType().getName() + "\t\t");
                        System.out.print(df.format(order.getOrderDate()) + "\t\t");
                        //System.out.print(order.getUser().getUsername() + "\t");
                        //System.out.print(order.getUser().getPhone() + "\t");
                        // System.out.print(((CustomizedPackage) order).getFlower() + "\t");
                        System.out.print(String.format("%.2f", ((CustomizedPackage) order).CalculateOrder()) + "\t\t\t");
                        //System.out.print(((CatalogOrders) order).getCatalogPack().getItem(i).getUserQuantity() + "\t");
                        if (order.isPaymentStatus()) {
                            System.out.print("Paid \t\t\t");
                        } else {
                            System.out.print("Pending \t\t\t");
                        }
                        if (order.getDateOfReceive() == null) {
                            System.out.print("Pending \n");
                        } else {
                            System.out.print(dfdt.format(order.getDateOfReceive()) + "\n");
                        }
                    }
                }
            }

            System.out.println("\n\n1.Pay");
            System.out.println("2. Back");
            System.out.println("Your selection: ");
            int payChoice = s.nextInt();

            if (payChoice == 1) {
                System.out.println("Select order to pick up & pay :");

                int orderChoice = s.nextInt();

                Order order = matchOrder.getItem(orderChoice);

                if (order instanceof CatalogOrders) {

                } else {

                }
                if (order.isPaymentStatus()) {
                    System.out.println("The selected order already paid!");
                    FioreFlowershop.orderMenu();
                } else {
                    System.out.println("Total Amount: " + String.format("%.2f", ((CustomizedPackage) order).CalculateOrder()));
                    double payAmt, change = 0;

                    do {
                        System.out.println("Enter amount to pay: ");
                        payAmt = s.nextDouble();

                        if (payAmt < matchOrder.getItem(orderChoice).getOrderAmt()) {
                            System.out.println("Insufficient amount, please reenter amount!");

                        } else if (payAmt >= ((CustomizedPackage) order).CalculateOrder()) {

                            change = CalculatePayment(payAmt, order);
                            setPaymentStatus(order);
                            paidOrder.add(order);

                            if (change == 0) {
                                System.out.println("Payment Change: No changes");
                            } else {
                                System.out.println("Payment Change: RM " + String.format("%.2f", change));
                            }

                            genReceipt(order, payAmt, change);

                        }
                    } while (payAmt < ((CustomizedPackage) order).CalculateOrder());
                }
            }
        }

    }

    public static double CalculatePayment(double payAmt, Order order) {
        double change = payAmt - order.getOrderAmt();
        return change;
    }

    public static void setPaymentStatus(Order order) {
        order.setPaymentStatus(true);
        order.setOrderStatus("Delivered");
        order.setPaymentTime(new Date());
        order.setDateOfReceive(new Date());

    }

    public static void genReceipt(Order order, double payAmt, double change) {
        DateFormat dfdt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dt = new SimpleDateFormat("HH:mm");

        if (order instanceof CatalogOrders) {
            System.out.println("\n\t\t\t\t   Fiore Flowershop SDN.BHD. ");
            System.out.println("\t\t\t\t    178, Jalan Sehala, ");
            System.out.println("\t\t\t\t  No U Turn,53300 Kuala Lumpur");

            System.out.println("\nID : " + ((CatalogOrders) order).getOrderID());
            System.out.println("Payment Date : " + df.format(order.getPaymentTime()));
            System.out.println("Cashier : Admin");
            System.out.println("Payment Time : " + dt.format(order.getPaymentTime()));
            System.out.println("==================================================================================================");
            System.out.println("ITEM \t\t\t QUANTITY \t PRICE (RM) \t\tDISCOUNT (%)\t\t AMOUNT (RM)");
            System.out.println("==================================================================================================");
            CatalogPackageInterface<CatalogPackage> cat = ((CatalogOrders) order).getCatalogPack();
            CatalogPackage item;
            Iterator<CatalogPackage> catIterator = cat.getIterator();

            while (catIterator.hasNext()) {
                item = catIterator.next();
//                double nett = item.getPrice() * item.getUserQuantity();
//                double subtotal = nett - ((nett * item.getDiscountRate()) / 100);
//                System.out.println(item.getName() + "\t\t\t" + item.getUserQuantity() + "\t  " + String.format("%.2f", item.getPrice()) + "\t\t\t" + item.getDiscountRate() + "\t\t\t" + String.format("%.2f", subtotal));
            }

            System.out.println("\n-------------------------------------------------------------------------------------------------");
            System.out.println("\tTOTAL (RM) : \t     \t\t\t\t\t\t\t\t" + String.format("%.2f", order.getOrderAmt()));
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.println("TOTAL NETT AMOUNT (RM) : \t\t" + String.format("%.2f", order.getOrderAmt()));
            System.out.println("CASH TENDERED (RM) : \t\t\t" + String.format("%.2f", payAmt));
            System.out.println("CHANGE (RM) : \t\t\t\t" + String.format("%.2f", change));
            System.out.println("\n==================================================================================================");
            System.out.println("Thank You For Choosing Fiore Flowershop, Please Come Again! :D");
            System.out.println("====================================================================================================");
        } else {

//            ListIteratorInterface<Item> cus = ((CustomizedPackage) order).getFlowerList();
//            CatalogPackage item;
//            Iterator<CatalogPackage> catIterator = cat.getIterator();
//
//            while (catIterator.hasNext()) {
//                item = catIterator.next();
//                System.out.println(item.getName() + item.getFlower() + "\t\t\t" + item.getUserQuantity() + "\t\t" + item.getPrice() + "\t\t");
//            }
            System.out.println("\n\t\t\t\t   Fiore Flowershop SDN.NHD ");
            System.out.println("\t\t\t\t    178, Jalan Sehala, ");
            System.out.println("\t\t\t\t 2404 No U Turn, 53300 Kuala Lumpur");

            System.out.println("\nID : " + ((CustomizedPackage) order).getOrderID());
            System.out.println("Payment Date : " + df.format(order.getPaymentTime()));
            System.out.println("Cashier : Admin");
            System.out.println("Payment Time : " + dt.format(order.getPaymentTime()));
            System.out.println("==================================================================================================");
            System.out.println("ITEM \t\t\t QUANTITY \t PRICE (RM) \t\tDISCOUNT (%)\t\t AMOUNT (RM)");
            System.out.println("==================================================================================================");

            System.out.println("\n-------------------------------------------------------------------------------------------------");
            System.out.println("\tTOTAL (RM) : \t     \t\t\t\t\t\t\t\t" + String.format("%.2f", ((CustomizedPackage) order).CalculateOrder()));
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.println("TOTAL NETT AMOUNT (RM) : \t\t\t\t\t" + String.format("%.2f", ((CustomizedPackage) order).CalculateOrder()));
            System.out.println("CASH TENDERED (RM) : \t\t\t" + String.format("%.2f", payAmt));
            System.out.println("CHANGE (RM) : \t\t\t\t" + String.format("%.2f", change));
            System.out.println("\n==================================================================================================");
            System.out.println("Thank You For Choosing Fiore Flowershop, Please Come Again! :D");
            System.out.println("====================================================================================================");
        }

        //FioreFlowershop.counterStaff();
    }

    public static void searchPaidDelivery(OrderListInterface<Order> paidOrder) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dt = new SimpleDateFormat("HH:mm");
        DateFormat dfdt = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Order order = paidOrder.getOrder(1);

        System.out.println("\nDelivery Order Payment History");
        System.out.println("|No.|\t|Order ID|\t|Order Type|\t|Order Date|\t\t|Payment Amount (RM)|\t|Payment Status|\t|Delivered Date|");
        System.out.println("=============================================================================================================================================================");

        if (order == null) {
            System.out.println(RED + "No order found!");
        } else if (order instanceof CatalogOrders) {

            System.out.print(1 + "\t");
            System.out.print(((CatalogOrders) order).getOrderID() + "\t\t");
            System.out.print(order.getOrderType() + "\t\t");
            System.out.print(df.format(order.getOrderDate()) + "\t\t");
            //System.out.print(order.getUser().getUsername() + "\t");
            //System.out.print(order.getUser().getPhone() + "\t");
            // System.out.print(((CustomizedPackage) order).getFlower() + "\t");
            System.out.print(String.format("%.2f", order.getOrderAmt()) + "\t\t\t");
            //System.out.print(((CatalogOrders) order).getCatalogPack().getItem(i).getUserQuantity() + "\t");
            if (order.isPaymentStatus()) {
                System.out.print("Paid \t\t\t");
            } else {
                System.out.print("Pending \t\t\t");
            }
            if (order.getDateOfReceive() == null) {
                System.out.print("Pending \n");
            } else {
                System.out.print(dfdt.format(order.getDateOfReceive()) + "\n");
            }
        } else {
            System.out.print(1 + "\t");
            System.out.print(((CustomizedPackage) order).getOrderID() + "\t\t");
            System.out.print(((CustomizedPackage) order).getDeliveryType().getName() + "\t\t");
            System.out.print(df.format(order.getOrderDate()) + "\t\t");
            //System.out.print(order.getUser().getUsername() + "\t");
            //System.out.print(order.getUser().getPhone() + "\t");
            // System.out.print(((CustomizedPackage) order).getFlower() + "\t");
            System.out.print(String.format("%.2f", ((CustomizedPackage) order).CalculateOrder()) + "\t\t\t");
            //System.out.print(((CatalogOrders) order).getCatalogPack().getItem(i).getUserQuantity() + "\t");
            if (order.isPaymentStatus()) {
                System.out.print("Paid \t\t\t");
            } else {
                System.out.print("Pending \t\t\t");
            }
            if (order.getDateOfReceive() == null) {
                System.out.print("Pending \n");
            } else {
                System.out.print(dfdt.format(order.getDateOfReceive()) + "\n");
            }
        }
    }

}
