/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.adt.*;
import com.mycompany.fioreflowershop.modal.*;

/**
 *
 * @author Chiu Peeng
 */
public class SortOrders {
    
    public static OrderListInterface<Order> sortAllOrders(OrderListInterface<Order> orderList) {

        for (int i = 1; i < orderList.getTotalEntries(); i++) {
            int index = i;
            for (int j = i + 1; j <= orderList.getTotalEntries(); j++) {
                // SORT BY DATE
                if (orderList.getOrder(j).getDeliveryDate().before(orderList.getOrder(index).getDeliveryDate())) {
                    index = j;
                } // IF DATES AND PRIORITIES ARE EQUAL SORT BY ORDER NUMBER
                else if (orderList.getOrder(j).getDeliveryDate().equals(orderList.getOrder(index).getDeliveryDate()) && orderList.getOrder(j).getPriority().getQuantity() == orderList.getOrder(index).getPriority().getQuantity() && orderList.getOrder(j).getOrderNumber() < orderList.getOrder(index).getOrderNumber()) {
                    index = j;
                } // IF DATES ARE EQUAL, BUT PRIORITIES ARE UNEQUAL, SORT BY PRIORITY
                else if (orderList.getOrder(j).getDeliveryDate().equals(orderList.getOrder(index).getDeliveryDate()) && orderList.getOrder(j).getPriority().getQuantity() < orderList.getOrder(index).getPriority().getQuantity()) {
                    index = j;
                }
            }
            Order smallerNumber = orderList.getOrder(index);
            orderList.replaceOrder(index, orderList.getOrder(i));
            orderList.replaceOrder(i, smallerNumber);
        }
        return orderList;
    }
    
    public static CustomizePackageQueueInterface<CustomizedPackage> sortCustomizedOrders(CustomizePackageQueueInterface<CustomizedPackage> orderList) {

        for (int i = 1; i < orderList.getSize(); i++) {
            int index = i;
            for (int j = i + 1; j <= orderList.getSize(); j++) {
                // SORT BY DATE
                if (orderList.getOrder(j).getDeliveryDate().before(orderList.getOrder(index).getDeliveryDate())) {
                    index = j;
                } // IF DATES AND PRIORITIES ARE EQUAL SORT BY ORDER NUMBER
                else if (orderList.getOrder(j).getDeliveryDate().equals(orderList.getOrder(index).getDeliveryDate()) && orderList.getOrder(j).getPriority().getQuantity() == orderList.getOrder(index).getPriority().getQuantity() && orderList.getOrder(j).getOrderNumber() < orderList.getOrder(index).getOrderNumber()) {
                    index = j;
                } // IF DATES ARE EQUAL, BUT PRIORITIES ARE UNEQUAL, SORT BY PRIORITY
                else if (orderList.getOrder(j).getDeliveryDate().equals(orderList.getOrder(index).getDeliveryDate()) && orderList.getOrder(j).getPriority().getQuantity() < orderList.getOrder(index).getPriority().getQuantity()) {
                    index = j;
                }
            }
            CustomizedPackage smallerPackage = orderList.getOrder(index);
            orderList.replaceOrder(index, orderList.getOrder(i));
            orderList.replaceOrder(i, smallerPackage);
        }
        return orderList;
    }
}
