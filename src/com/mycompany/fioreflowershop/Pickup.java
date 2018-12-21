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
import com.mycompany.fioreflowershop.adt.ListIteratorInterface;
import com.mycompany.fioreflowershop.adt.QueueInterface;
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

    static Scanner sc = new Scanner(System.in);

    LinkedList<CustomizedPackage> customPackageList = new LinkedList<>();

    public static void searchPickUp(LinkedList<CatalogOrders> catalogOrder, Date date, LinkedList<CustomizedPackage> customizeOrder) {
        LinkedList<CatalogOrders> unOrderList = new LinkedList<CatalogOrders>();
        LinkedList<CustomizedPackage> customOrder = new LinkedList<CustomizedPackage>();
        LinkedList<Order> matchedList = new LinkedList<>();

        Iterator<CatalogOrders> catalogIterator = catalogOrder.getIterator();
        Iterator<CustomizedPackage> CustomIterator = customizeOrder.getIterator();

        // Get all delivery order for Catalog Order
        while (catalogIterator.hasNext()) {
            CatalogOrders order = catalogIterator.next();

            if (order.getOrderType().equals("Pick Up")) {
                unOrderList.add(order);
            }
        }

        // Get all delivery order for Customize Package
        while (CustomIterator.hasNext()) {
            CustomizedPackage order = CustomIterator.next();

            if (order.getDeliveryType().getName().equals("Pick Up")) {
                customOrder.add(order);
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

        for (int i = 1; i <= unOrderList.getTotalEntries(); i++) {

            listCal.setTime(unOrderList.getItem(i).getOrderDate());

            day = listCal.get(Calendar.DAY_OF_MONTH);
            month = listCal.get(Calendar.MONTH) + 1;
            year = listCal.get(Calendar.YEAR);

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

        displaySortedPickup(matchedList);
    }

    public static void sortPickupOrder(LinkedList<CatalogOrders> catalogOrder, LinkedList<CustomizedPackage> customizeOrder) {

        ListInterface<Order> sortedList = new LinkedList<>();

        LinkedList<CatalogOrders> unOrderList = new LinkedList<CatalogOrders>();
        LinkedList<CustomizedPackage> customOrder = new LinkedList<CustomizedPackage>();
        LinkedList<Order> matchedList = new LinkedList<>();

        Iterator<CatalogOrders> catalogIterator = catalogOrder.getIterator();
        Iterator<CustomizedPackage> CustomIterator = customizeOrder.getIterator();

        // Get all delivery order for Catalog Order
        while (catalogIterator.hasNext()) {
            CatalogOrders order = catalogIterator.next();

            if (order.getOrderType().equals("Pick Up")) {
                unOrderList.add(order);
            }
        }

        // Get all delivery order for Customize Package
        while (CustomIterator.hasNext()) {
            CustomizedPackage order = CustomIterator.next();

            if (order.getDeliveryType().getName().equals("Pick Up")) {
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
            listCal.setTime(unOrderList.getItem(j).getRetrieveDate());

            day = listCal.get(Calendar.DAY_OF_MONTH);
            month = listCal.get(Calendar.MONTH) + 1;
            year = listCal.get(Calendar.YEAR);

            if (day == userDay && month == userMonth && year == userYear) {
                sortedList.add(unOrderList.getItem(j));
            }
        }

        for (int j = 1; j <= customOrder.getTotalEntries(); j++) {
            listCal.setTime(customOrder.getItem(j).getRetrieveDate());

            day = listCal.get(Calendar.DAY_OF_MONTH);
            month = listCal.get(Calendar.MONTH) + 1;
            year = listCal.get(Calendar.YEAR);

            if (day == userDay && month == userMonth && year == userYear) {
                sortedList.add(customOrder.getItem(j));
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
        for (int i = 1; i < sortedList.getTotalEntries() - 1; i++) {
            int index = i;
            for (int j = i; j <= sortedList.getTotalEntries(); j++) {
                if (sortedList.getItem(j).getRetrieveDate().before(sortedList.getItem(index).getRetrieveDate())) {
                    index = j; //searching for lowest index  
                }
            }
            Order smallerOrder = sortedList.getItem(index);
            sortedList.replace(index, sortedList.getItem(i));
            sortedList.replace(i, smallerOrder);
        }

        displaySortedPickup(sortedList);

    }

    public static void displaySortedPickup(ListInterface<Order> orderedList) {

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

        System.out.println("PICK UP ORDER");
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

    public static void searchUserPickUp(String userID, LinkedList<CatalogOrders> catalogOrder, LinkedList<CustomizedPackage> customOrder, LinkedList<Order> paidOrder) {

        LinkedList<CatalogOrders> pickuporder = catalogOrder;
        LinkedList<Order> matchOrder = new LinkedList<>();
        User user = null;

        Scanner s = new Scanner(System.in);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dt = new SimpleDateFormat("HH:mm");
        DateFormat dfdt = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        // Get all CatalogOrder with Pick Up type
        Iterator<CatalogOrders> iterator = catalogOrder.getIterator();

        while (iterator.hasNext()) {
            CatalogOrders order = iterator.next();

            if (order.getUser().getUsername().equals(userID) && order.getOrderType().equals("Pick Up") && (order.getUser() instanceof Consumer)) {
                user = order.getUser();
                matchOrder.add(order);
            }
        }

        Iterator<CustomizedPackage> customIterator = customOrder.getIterator();

        while (customIterator.hasNext()) {
            CustomizedPackage order = customIterator.next();

            if (order.getUser().getUsername().equals(userID) && order.getDeliveryType().getName().equals("Pick Up") && (order.getUser() instanceof Consumer)) {
                user = order.getUser();
                matchOrder.add(order);
            }
        }

        if (user == null && !matchOrder.isEmpty()) {
            System.out.println("User ID does not exist in system!");
            FioreFlowershop.orderMenu();

        } else if (matchOrder.isEmpty()) {
            System.out.println("User don't have any order with pending payment! Please try again!");
            FioreFlowershop.orderMenu();

        } else {

            System.out.println("Hi " + user.getUsername() + ",");
            System.out.println("These are your orders with pending payment");
            System.out.println("|No.|\t|Order ID|\t|Order Type|\t|Order Date|\t\t|Payment Amount (RM)|\t|Payment Status|\t|Pickup Date|");
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
                System.out.println("Select order to pick up & pay :");

                int orderChoice = s.nextInt();

                Order order = matchOrder.getItem(orderChoice);

                if (order instanceof CatalogOrders) {
                    if (matchOrder.getItem(orderChoice).isPaymentStatus()) {
                        System.out.println("The selected order already paid!");
                        FioreFlowershop.orderMenu();
                    } else {
                        System.out.println("Total Amount: " + String.format("%.2f", matchOrder.getItem(orderChoice).getOrderAmt()));
                        double payAmt, change = 0;

                        do {
                            System.out.println("Enter amount to pay: ");
                            payAmt = s.nextDouble();

                            if (payAmt < matchOrder.getItem(orderChoice).getOrderAmt()) {
                                System.out.println("Insufficient amount, please reenter amount!");

                            } else if (payAmt >= matchOrder.getItem(orderChoice).getOrderAmt()) {

                                change = payAmt - matchOrder.getItem(orderChoice).getOrderAmt();
                                matchOrder.getItem(orderChoice).setPaymentStatus(true);
                                matchOrder.getItem(orderChoice).setOrderStatus("Picked Up");
                                paidOrder.add(matchOrder.getItem(orderChoice));

                                if (change == 0) {
                                    System.out.println("Payment Change: No changes");
                                } else {
                                    System.out.println("Payment Change: RM " + String.format("%.2f", change));
                                }

                                matchOrder.getItem(orderChoice).setPaymentTime(new Date());
                                matchOrder.getItem(orderChoice).setDateOfReceive(new Date());
                                genReceipt(matchOrder.getItem(orderChoice), payAmt, change);

                            }
                        } while (payAmt < matchOrder.getItem(orderChoice).getOrderAmt());
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

    }

    public static double CalculatePayment(double payAmt, double totalAmt) {

        double change = payAmt - totalAmt;
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
            LinkedList<CatalogPackage> cat = ((CatalogOrders) order).getCatalogPack();
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
            System.out.println("ITEM \t\t\t QUANTITY \t PRICE (RM) \t\tDISCOUNT %\t\t AMOUNT (RM)");
            System.out.println("==================================================================================================");

            ListIteratorInterface<Item> flowerList = ((CustomizedPackage) order).getFlowerList();

            Iterator<Item> flowerIterator = flowerList.getIterator();

            Item item;

            while (flowerIterator.hasNext()) {
                item = flowerIterator.next();
                System.out.println(item.getName() + "\t\t\t" + item.getQuantity() + "\t\t" + item.getPrice() + "\t\t");
            }

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
