/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop;

import static fioreflowershop.Pickup.displaySortedPickup;
import fioreflowershop.adt.ArrayList;
import fioreflowershop.adt.ArrayQueue;
import fioreflowershop.adt.ListInterface;
import fioreflowershop.adt.QueueInterface;
import fioreflowershop.modal.CustomizedPackage;
import fioreflowershop.modal.Order;
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

        displaySortedDelivery(matchedList, customOrder);
    }

    public static void sortDeliveryOrder(ListInterface<Order> deliveryOrder, QueueInterface customizeOrder) {

        ListInterface<Order> sortedList = new ArrayList<>();

        QueueInterface customOrder = customizeOrder;

        ListInterface<Order> unOrderList = deliveryOrder;

        Calendar cal = Calendar.getInstance();
        Calendar listCal = Calendar.getInstance();
        cal.setTime(new Date());

        for (int j = 1; j <= unOrderList.getTotalEntries(); j++) {
            listCal.setTime(unOrderList.getItem(j).getDate());
            int day, month, year, userDay, userMonth, userYear;

            day = listCal.get(Calendar.DAY_OF_MONTH);
            month = listCal.get(Calendar.MONTH) + 1;
            year = listCal.get(Calendar.YEAR);

            userDay = cal.get(Calendar.DAY_OF_MONTH);
            userMonth = cal.get(Calendar.MONTH) + 1;
            userYear = cal.get(Calendar.YEAR);

            if (day == userDay && month == userMonth && year == userYear) {
                sortedList.add(unOrderList.getItem(j));
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

        displaySortedDelivery(sortedList, customOrder);

    }

    public static void displaySortedDelivery(ListInterface<Order> orderedList, QueueInterface<CustomizedPackage> customOrder) {

        QueueInterface<CustomizedPackage> showQueue = new ArrayQueue<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dt = new SimpleDateFormat("HH:mm");

        System.out.println("DELIVERY ORDER");
        System.out.println("-------------------------------------------------------");
        System.out.println("\n\nCatalog Order");
        System.out.println("=======================================================");
        for (int k = 1; k <= orderedList.getTotalEntries(); k++) {

            if (orderedList.getItem(k).getCorp().getCompany() != null) {
                System.out.println("Order ID: " + orderedList.getItem(k).getOrderID());
                System.out.println("Company Name: " + orderedList.getItem(k).getCorp().getCompany());
                String date = df.format(orderedList.getItem(k).getDate());
                System.out.println("Delivery Date: " + date + "\n");
            } else {
                System.out.println("Order ID: " + orderedList.getItem(k).getOrderID());
                System.out.println("Name: " + orderedList.getItem(k).getCon().getUsername());
                String date = df.format(orderedList.getItem(k).getDate());
                System.out.println("Delivery Date: " + date + "\n");
            }
        }

        System.out.println("\n\nCustomized Package");
        System.out.println("=======================================================");
        for (int i = -1; i <= customOrder.getBackIndex(); i++) {
            CustomizedPackage order = customOrder.dequeue();
            System.out.println("Order ID: " + order.getOrderNum());
            System.out.println("Consumer name: " + order.getCustomer().getUsername());
            System.out.println("Contact: " + order.getCustomer().getPhone());
            System.out.println("Delivery date: " + order.getDeliveryDateString() + "\n");
            showQueue.enqueue(order);
        }

    }

}
