/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import static com.mycompany.fioreflowershop.Delivery.displaySortedDelivery;
import com.mycompany.fioreflowershop.adt.LinkedList;
import com.mycompany.fioreflowershop.adt.ArrayQueue;
import com.mycompany.fioreflowershop.adt.CatalogPackageInterface;
import com.mycompany.fioreflowershop.adt.ConsumerInterface;
import com.mycompany.fioreflowershop.adt.CorporateInterface;
import com.mycompany.fioreflowershop.adt.LinkedList;
import com.mycompany.fioreflowershop.adt.ListInterface;
import com.mycompany.fioreflowershop.adt.ListIteratorInterface;
import com.mycompany.fioreflowershop.adt.OrderList;
import com.mycompany.fioreflowershop.adt.OrderListInterface;
import com.mycompany.fioreflowershop.adt.QueueInterface;
import com.mycompany.fioreflowershop.adt.UserInterface;
import com.mycompany.fioreflowershop.modal.CatalogOrders;
import com.mycompany.fioreflowershop.modal.CatalogPackage;
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

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    static Scanner sc = new Scanner(System.in);

    OrderListInterface<CustomizedPackage> customPackageList = new OrderList<>();

    public static void searchPickUp(Date date, OrderListInterface<Order> readyOrder) {

        OrderListInterface<Order> unSortedList = new OrderList<>();
        OrderListInterface<Order> sortedList = new OrderList<>();

        Iterator<Order> orderIterator = readyOrder.getIterator();

        // Get all delivery order for Catalog Order
        while (orderIterator.hasNext()) {
            Order order = orderIterator.next();

            if (order.getOrderType().equals("Pick Up")) {
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

        for (int i = 1; i <= unSortedList.getSize(); i++) {

            listCal.setTime(unSortedList.getOrder(i).getDeliveryDate());

            day = listCal.get(Calendar.DAY_OF_MONTH);
            month = listCal.get(Calendar.MONTH) + 1;
            year = listCal.get(Calendar.YEAR);

            if (day == userDay && month == userMonth && year == userYear) {
                sortedList.addOrder(unSortedList.getOrder(i));
            }
        }

//        for (int i = -1; i <= count; i++) {
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
        for (int i = 1; i < sortedList.getSize(); i++) {
            int index = i;
            for (int j = i; j <= sortedList.getSize(); j++) {
                if (sortedList.getOrder(j).getOrderDate().before(sortedList.getOrder(index).getDeliveryDate())) {
                    index = j; //searching for lowest index  
                }
            }
            Order smallerOrder = sortedList.getOrder(index);
            sortedList.replaceOrder(index, sortedList.getOrder(i));
            sortedList.replaceOrder(i, smallerOrder);

        }

        displaySortedPickup(sortedList);
    }

    public static void sortPickupOrder(OrderListInterface<Order> readyOrder) {

        OrderListInterface<Order> unSortedList = new OrderList<>();
        OrderListInterface<Order> sortedList = new OrderList<>();

        Iterator<Order> orderIterator = readyOrder.getIterator();

        // Get all pickup order for Catalog Order
        while (orderIterator.hasNext()) {
            Order order = orderIterator.next();

            if (order.getOrderType().equals("Pick Up")) {
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

//        for (int i = -1; i <= customOrder.getBackIndex(); i++) {
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
        for (int i = 1; i < sortedList.getSize(); i++) {
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

        displaySortedPickup(sortedList);

    }

    public static void displaySortedPickup(OrderListInterface<Order> orderedList) {

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

        System.out.println("\nPICK UP ORDER");
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
                System.out.println("Pick Up Date: " + date);
                System.out.println("Pick Up Status: " + order.getOrderStatus() + "\n");
            } else {

                Consumer con = (Consumer) catalogOrder.getItem(k).getUser();

                System.out.println("Order ID: " + order.getOrderID());

                System.out.println("Name: " + con.getUsername());
                System.out.println("Contact: " + con.getPhone());
                String date = df.format(catalogOrder.getItem(k).getDeliveryDate());
                System.out.println("Pick Up Date: " + date);
                System.out.println("Pick Up Status: " + order.getOrderStatus() + "\n");
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
                System.out.println("Pick Up Date: " + date);
                System.out.println("Pick Up Status: " + order.getOrderStatus() + "\n");

            } else {

                Consumer con = (Consumer) customOrder.getItem(k).getUser();

                System.out.println("Order ID: " + order.getOrderID());

                System.out.println("Name: " + con.getUsername());
                System.out.println("Contact: " + con.getPhone());
                String date = df.format(customOrder.getItem(k).getDeliveryDate());
                System.out.println("Pick Up Date: " + date);
                System.out.println("Pick Up Status: " + order.getOrderStatus() + "\n");

            }
        }

        if (customOrder.isEmpty()) {
            System.out.println(FioreFlowershop.ConsoleColors.RED + "No record found!");
        }

//        System.out.println("\nCustomized Package");
//        System.out.println("=======================================================");
//
//        if (!customOrder.isEmpty()) {
//            for (int i = -1; i <= customOrder.getBackIndex(); i++) {
//                CustomizedPackage order = customOrder.dequeue();
//                if (order.getDeliveryType().getName().equals("Pickup")) {
//                    System.out.println("Order ID: " + order.getOrderID());
//                    System.out.println("Consumer name: " + order.getUser().getUsername());
//                    System.out.println("Contact: " + order.getUser().getPhone());
//                    System.out.println("Pick up date: " + order.getDeliveryDateString() + "\n");
//                    showQueue.enqueue(order);
//                }
//            }
//        } else {
//            System.out.println(FioreFlowershop.ConsoleColors.RED + "No record found!");
//        }
    }

    public static void searchUserPickUp(String userID, OrderListInterface<Order> readyOrder, OrderListInterface<Order> paidOrder) {

        OrderListInterface<Order> matchOrder = new OrderList<>();
        ConsumerInterface<Consumer> conList = FioreFlowershop.getConsumerList();
        User user = null;

        Scanner s = new Scanner(System.in);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dt = new SimpleDateFormat("HH:mm");
        DateFormat dfdt = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        // Get all CatalogOrder with Pick Up type
        Iterator<Order> orderIterator = readyOrder.getIterator();

        while (orderIterator.hasNext()) {
            Order order = orderIterator.next();

            if (order.getUser().getUsername().equals(userID) && order.getOrderType().equals("Pick Up") && (order.getUser() instanceof Consumer) && !order.isPaymentStatus()) {
                matchOrder.addOrder(order);
            }
        }

        // Get all consumer list and do checking on existence
        for (int i = 1; i <= conList.getTotalConsumer(); i++) {
            if (conList.getConsumer(i).getUsername().equals(userID)) {
                user = conList.getConsumer(i);
            }
        }

        if (user == null && !matchOrder.isEmpty()) {
            System.out.println("\nUser are not a Consumer!");
            FioreFlowershop.orderMenu();

        } else if (user != null && matchOrder.isEmpty()) {
            System.out.println(ANSI_RED + "User don't have any order with pending payment! Please try again!" + ANSI_RESET);
            FioreFlowershop.orderMenu();

        } else if (user == null && matchOrder.isEmpty()) {
            System.out.println("\nUser ID does not exist in system!");
            FioreFlowershop.orderMenu();
        } else {

            System.out.println("Hi " + user.getUsername() + ",");
            System.out.println("These are your orders with pending payment");
            System.out.println("|No.|\t|Order ID|\t|Order Type|\t|Order Date|\t\t|Payment Amount (RM)|\t|Payment Status|\t|Pickup Date|");
            System.out.println("=============================================================================================================================================================");

            for (int i = 1; i <= matchOrder.getSize(); i++) {
                Order order = matchOrder.getOrder(i);

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
                            System.out.print("Paid \t\t");
                        } else {
                            System.out.print("Pending \t\t");
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
                            System.out.print("Paid \t\t");
                        } else {
                            System.out.print("Pending \t\t");
                        }
                        if (order.getDateOfReceive() == null) {
                            System.out.print("Pending \n");
                        } else {
                            System.out.print(dfdt.format(order.getDateOfReceive()) + "\n");
                        }
                    }
                }
            }

            System.out.println("\n\n1. Pick Up & Pay");
            System.out.println("2. Back");
            System.out.println("Your selection: ");
            int payChoice = s.nextInt();

            if (payChoice == 1) {
                System.out.println("Select order number to pick up & pay :");

                int orderChoice = s.nextInt();

                Order order = matchOrder.getOrder(orderChoice);

                if (order instanceof CatalogOrders) {
                    if (matchOrder.getOrder(orderChoice).isPaymentStatus()) {
                        System.out.println("The selected order already paid!");
                        FioreFlowershop.orderMenu();
                    } else {
                        System.out.println("Total Amount: " + String.format("%.2f", matchOrder.getOrder(orderChoice).getOrderAmt()));
                        double payAmt, change = 0;

                        do {
                            System.out.println("Enter amount to pay: ");
                            payAmt = s.nextDouble();

                            if (payAmt < matchOrder.getOrder(orderChoice).getOrderAmt()) {
                                System.out.println("Insufficient amount, please reenter amount!");

                            } else if (payAmt >= matchOrder.getOrder(orderChoice).getOrderAmt()) {

                                change = payAmt - matchOrder.getOrder(orderChoice).getOrderAmt();
                                matchOrder.getOrder(orderChoice).setPaymentStatus(true);
                                matchOrder.getOrder(orderChoice).setOrderStatus("Picked Up");
                                paidOrder.addOrder(matchOrder.getOrder(orderChoice));

                                if (change == 0) {
                                    System.out.println("Payment Change: No changes");
                                } else {
                                    System.out.println("Payment Change: RM " + String.format("%.2f", change));
                                }

                                matchOrder.getOrder(orderChoice).setPaymentTime(new Date());
                                matchOrder.getOrder(orderChoice).setDateOfReceive(new Date());
                                genReceipt(matchOrder.getOrder(orderChoice), payAmt, change);
                                break;
                            }
                        } while (payAmt < matchOrder.getOrder(orderChoice).getOrderAmt());
                    }
                } else {
                    if (order.isPaymentStatus()) {
                        System.out.println("The selected order already paid!");
                        FioreFlowershop.orderMenu();
                    } else {
                        System.out.println("Total Amount: " + String.format("%.2f", ((CustomizedPackage) order).CalculateOrder()));
                        double payAmt, change = 0;

                        do {
                            System.out.println("Enter amount to pay: ");
                            payAmt = s.nextDouble();

                            if (payAmt < ((CustomizedPackage) order).CalculateOrder()) {
                                System.out.println("Insufficient amount, please reenter amount!");

                            } else if (payAmt >= ((CustomizedPackage) order).CalculateOrder()) {

                                change = CalculatePayment(payAmt, ((CustomizedPackage) order).CalculateOrder());
                                setPickUpPaymentStatus(order);
                                paidOrder.addOrder(order);

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

    }

    public static void searchCorpPickUp(String userID, OrderListInterface<Order> readyOrder, OrderListInterface<Order> paidOrder) {

        OrderListInterface<Order> matchOrder = new OrderList<>();
        CorporateInterface<CorporateCustomer> corpList = FioreFlowershop.getCorporateList();
        User user = null;
        String confirm = "";

        Scanner s = new Scanner(System.in);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dt = new SimpleDateFormat("HH:mm");
        DateFormat dfdt = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        // Get all CatalogOrder with Pick Up type
        Iterator<Order> orderIterator = readyOrder.getIterator();

        while (orderIterator.hasNext()) {
            Order order = orderIterator.next();

            if (order.getUser().getUsername().equals(userID) && order.getOrderType().equals("Pick Up") && (order.getUser() instanceof CorporateCustomer) && order.getOrderStatus().equals("Pending")) {
                matchOrder.addOrder(order);
            }
        }

        // Get all consumer list and do checking on existence
        for (int i = 1; i <= corpList.getTotalCorporate(); i++) {
            if (corpList.getCorporate(i).getUsername().equals(userID)) {
                user = corpList.getCorporate(i);
            }
        }

        if (user == null && !matchOrder.isEmpty()) {
            System.out.println(ANSI_RED + "\nUser are not a Corporate Customer!" + ANSI_RESET);
            FioreFlowershop.orderMenu();

        } else if (user != null && matchOrder.isEmpty()) {
            System.out.println(ANSI_RED + "User don't have any order with pending payment! Please try again!" + ANSI_RESET);
            FioreFlowershop.orderMenu();

        } else if (user == null && matchOrder.isEmpty()) {
            System.out.println(ANSI_RED + "\nUser ID does not exist in system!" + ANSI_RESET);
            FioreFlowershop.orderMenu();
        } else {

            do {
                System.out.println("Hi " + user.getUsername() + ",");
                System.out.println("These are your orders with pending payment");
                System.out.println("|No.|\t|Order ID|\t|Order Type|\t|Order Date|\t\t|Payment Amount (RM)|\t|Payment Status|\t|Pickup Date|");
                System.out.println("=============================================================================================================================================================");

                for (int i = 1; i <= matchOrder.getSize(); i++) {
                    Order order = matchOrder.getOrder(i);

                    if (user instanceof CorporateCustomer) {
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
                                System.out.print("Paid \t\t");
                            } else {
                                System.out.print("Pending \t\t");
                            }
                            if (order.getDateOfReceive() == null) {
                                System.out.print("Pending \n");
                            } else {
                                System.out.print(dfdt.format(order.getDateOfReceive()) + "\n");
                            }
                        }
                    }
                }

                System.out.println("\n\n1. Pick Up");
                System.out.println("2. Back");
                System.out.println("Your selection: ");
                int choice = s.nextInt();

                if (choice == 1) {
                    System.out.println("Select order number to pick up: ");

                    int orderChoice = s.nextInt();

                    Order order = matchOrder.getOrder(orderChoice);

                    if (order instanceof CatalogOrders) {

                        System.out.println("Confirm Pick Up? (Y/y = yes OR N/n = no)");
                        confirm = s.next();

                        s.nextLine();

                        if (confirm.equalsIgnoreCase("Y")) {
                            setPickUpStatus(order);
                            System.out.println(ANSI_GREEN + "Order Picked Up Successfully!" + ANSI_RESET);
                        }
                    } else {

                    }
                } else {
                    break;
                }
            } while (!confirm.equalsIgnoreCase("Y"));

        }

    }

    public static void searchPickedUp(OrderListInterface<Order> readyOrder) {
        User user = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dt = new SimpleDateFormat("HH:mm");
        DateFormat dfdt = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        OrderListInterface<Order> matchOrder = new OrderList<>();

        Iterator<Order> orderIterator = readyOrder.getIterator();

        while (orderIterator.hasNext()) {
            Order order = orderIterator.next();

            if (order.getOrderType().equals("Pick Up") && order.getOrderStatus().equals("Picked Up")) {
                matchOrder.addOrder(order);
                user = order.getUser();
            }
        }

        System.out.println("These are orders that picked up by customer");
        System.out.println("|No.|\t|Order ID|\t|Order Type|\t|Order Date|\t\t|Payment Amount (RM)|\t|Payment Status|\t|Pickup Date|");
        System.out.println("=============================================================================================================================================================");
        for (int i = 1; i <= matchOrder.getSize(); i++) {
            Order order = matchOrder.getOrder(i);

            if (user instanceof CorporateCustomer) {
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
                        System.out.print("Paid \t\t");
                    } else {
                        System.out.print("Pending \t\t");
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
                        System.out.print("Paid \t\t");
                    } else {
                        System.out.print("Pending \t\t");
                    }
                    if (order.getDateOfReceive() == null) {
                        System.out.print("Pending \n");
                    } else {
                        System.out.print(dfdt.format(order.getDateOfReceive()) + "\n");
                    }
                }
            }
        }
    }

    public static double CalculatePayment(double payAmt, double totalAmt) {

        double change = payAmt - totalAmt;
        return change;
    }

    public static void setDeliveryPaymentStatus(Order order) {
        order.setPaymentStatus(true);
        order.setOrderStatus("Delivered");
        order.setPaymentTime(new Date());
        order.setDateOfReceive(new Date());

    }

    public static void setPickUpPaymentStatus(Order order) {
        order.setPaymentStatus(true);
        order.setOrderStatus("Picked Up");
        order.setPaymentTime(new Date());
        order.setDateOfReceive(new Date());
    }

    public static void setPickUpStatus(Order order) {
        order.setOrderStatus("Picked Up");
        order.setDateOfReceive(new Date());
    }

    public static void genReceipt(Order order, double payAmt, double change) {
        DateFormat dfdt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dt = new SimpleDateFormat("HH:mm");

        if (order instanceof CatalogOrders) {
            System.out.println("\n\t\t\t\t   Fiore Flowershop SDN.NHD ");
            System.out.println("\t\t\t\t    178, Jalan Sehala, ");
            System.out.println("\t\t\t\t 2404 No U Turn, 53300 Kuala Lumpur");

            System.out.println("\nID : " + ((CatalogOrders) order).getOrderID());
            System.out.println("Payment Date : " + df.format(order.getPaymentTime()));
            System.out.println("Cashier : Admin");
            System.out.println("Payment Time : " + dt.format(order.getPaymentTime()));
            System.out.println("==================================================================================================");
            System.out.println("ITEM \t\t\t QUANTITY \t PRICE (RM) \t\tDISCOUNT %\t\t AMOUNT (RM)");
            System.out.println("==================================================================================================");
            CatalogPackageInterface<CatalogPackage> cat = ((CatalogOrders) order).getCatalogPack();
            CatalogPackage item;
            Iterator<CatalogPackage> catIterator = cat.getIterator();

            while (catIterator.hasNext()) {
                item = catIterator.next();
                double nett = item.getPrice() * item.getUserQuantity();
                double subtotal = nett - ((nett * item.getDiscountRate()) / 100);
                System.out.println(item.getName() + "\t\t\t" + item.getUserQuantity() + "\t  " + String.format("%.2f", item.getPrice()) + "\t\t\t" + item.getDiscountRate() + "\t\t\t" + String.format("%.2f", subtotal));
            }

            System.out.println("\n-------------------------------------------------------------------------------------------------");
            System.out.println("\tTOTAL (RM) : \t     \t\t\t\t\t\t\t\t" + String.format("%.2f", order.getOrderAmt()));
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.println("CASH : \t\t\t\t\t" + String.format("%.2f", order.getOrderAmt()));
            System.out.println("CASH TENDERED : \t\t\t" + String.format("%.2f", payAmt));
            System.out.println("BALANCE : \t\t\t\t" + String.format("%.2f", change));
            System.out.println("\n==================================================================================================");
            System.out.println("Thank You For Choosing Fiore Flowershop, Please Come Again :D");
            System.out.println("====================================================================================================");
        } else {

            System.out.println("\n\t\t\t\t   Fiore Flowershop SDN.BHD ");
            System.out.println("\t\t\t\t    178, Jalan Sehala, ");
            System.out.println("\t\t\t\t 2404 No U Turn, 53300 Kuala Lumpur");

            System.out.println("\nID : " + ((CustomizedPackage) order).getOrderID());
            System.out.println("Payment Date : " + df.format(order.getPaymentTime()));
            System.out.println("Cashier : Admin");
            System.out.println("Payment Time : " + dt.format(order.getPaymentTime()));
            System.out.println("==================================================================================================");
            System.out.println("ITEM \t\t\t\t PRICE (RM) \t\t\t\t AMOUNT (RM)");
            System.out.println("==================================================================================================");

            for (int i = 1; i <= ((CustomizedPackage) order).getFlowerList().getTotalEntries(); i++) {
                System.out.println(((CustomizedPackage) order).getFlowerList().getItem(i).getName() + ((CustomizedPackage) order).getFlowerList().getItem(i).getPrice());
            }

            System.out.println("Size: " + ((CustomizedPackage) order).getSize().getName() + "" + ((CustomizedPackage) order).getSize().getPrice());
            System.out.println("Arrangement Style: " + ((CustomizedPackage) order).getStyle().getName() + "" + ((CustomizedPackage) order).getStyle().getPrice());
            System.out.println("Accesscories: " + ((CustomizedPackage) order).getAccessory().getName() + "" + ((CustomizedPackage) order).getAccessory().getPrice());
            System.out.println("Delivery Type: " + ((CustomizedPackage) order).getDeliveryType().getName() + " - RM " + ((CustomizedPackage) order).getDeliveryType().getPrice());
            System.out.println("Order Priority: " + order.getPriority().getName() + " - Price x " + +((CustomizedPackage) order).getPriority().getPrice());

            System.out.println("\n-------------------------------------------------------------------------------------------------");
            System.out.println("\tTOTAL (RM) : \t     \t\t\t\t\t\t\t\t" + String.format("%.2f", ((CustomizedPackage) order).CalculateOrder()));
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.println("TOTAL NETT AMOUNT (RM) : \t\t\t\t\t" + String.format("%.2f", ((CustomizedPackage) order).CalculateOrder()));
            System.out.println("CASH TENDERED (RM) : \t\t\t" + String.format("%.2f", payAmt));
            System.out.println("CHANGE (RM) : \t\t\t\t" + String.format("%.2f", change));
            System.out.println("\n==================================================================================================");
            System.out.println("Thank You For Choosing Fiore Flowershop, Please Come Again :D");
            System.out.println("====================================================================================================");
        }
    }
}

//    for(int i = 0; i < customPack ; i++ ){
//    
//}
//    
