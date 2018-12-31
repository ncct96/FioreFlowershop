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
    
    public static OrderListInterface<Order> doSelectionSort(OrderListInterface<Order> arr) {

        for (int i = 1; i < arr.getTotalEntries(); i++) {
            int index = i;
            for (int j = i + 1; j <= arr.getTotalEntries(); j++) {
                // SORT BY DATE
                if (arr.getOrder(j).getDeliveryDate().before(arr.getOrder(index).getDeliveryDate())) {
                    index = j;
                } // IF DATES AND PRIORITIES ARE EQUAL SORT BY ORDER NUMBER
                else if (arr.getOrder(j).getDeliveryDate().equals(arr.getOrder(index).getDeliveryDate()) && arr.getOrder(j).getPriority().getQuantity() == arr.getOrder(index).getPriority().getQuantity() && arr.getOrder(j).getOrderNumber() < arr.getOrder(index).getOrderNumber()) {
                    index = j;
                } // IF DATES ARE EQUAL, BUT PRIORITIES ARE UNEQUAL, SORT BY PRIORITY
                else if (arr.getOrder(j).getDeliveryDate().equals(arr.getOrder(index).getDeliveryDate()) && arr.getOrder(j).getPriority().getQuantity() < arr.getOrder(index).getPriority().getQuantity()) {
                    index = j;
                }
            }
            Order smallerNumber = arr.getOrder(index);
            arr.replaceOrder(index, arr.getOrder(i));
            arr.replaceOrder(i, smallerNumber);
        }
        return arr;
    }
}
