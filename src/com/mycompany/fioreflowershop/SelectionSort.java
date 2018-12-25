/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.adt.*;
import com.mycompany.fioreflowershop.modal.*;
import java.util.Calendar;

/**
 *
 * @author Chiu Peeng
 */
public class SelectionSort {

    static Item style = new Item("Elliptical", 10);
    static Item size = new Item("Small", 1);
    static Item flower = new Item("Sunflowers", 250, 10);
    static Item accessory = new Item("None", 0, 1);

    static Item flexi = new Item("Flexi", 1, 6);
    static Item normal = new Item("Normal", 1.5, 4);
    static Item express = new Item("Express", 3, 2);

    static Item delivery = new Item("Pick Up", 0);

    public static ItemListInterface<CustomizedPackage> doSelectionSort(ItemListInterface<CustomizedPackage> arr) {

        for (int i = 1; i < arr.getSize(); i++) {
            int index = i;
            for (int j = i + 1; j <= arr.getSize(); j++) {
                // SORT BY DATE
                if (arr.getItem(j).getDeliveryDate().before(arr.getItem(index).getDeliveryDate())) {
                    index = j;
                } // IF DATES AND PRIORITIES ARE EQUAL SORT BY ORDER NUMBER
                else if (arr.getItem(j).getDeliveryDate().equals(arr.getItem(index).getDeliveryDate()) && arr.getItem(j).getPriority().getQuantity() == arr.getItem(index).getPriority().getQuantity() && arr.getItem(j).getOrderNumber() < arr.getItem(index).getOrderNumber()) {
                    index = j;
                } // IF DATES ARE EQUAL, BUT PRIORITIES ARE UNEQUAL, SORT BY PRIORITY
                else if (arr.getItem(j).getDeliveryDate().equals(arr.getItem(index).getDeliveryDate()) && arr.getItem(j).getPriority().getQuantity() < arr.getItem(index).getPriority().getQuantity()) {
                    index = j;
                }
            }
            CustomizedPackage smallerNumber = arr.getItem(index);
            arr.replaceItem(index, arr.getItem(i));
            arr.replaceItem(i, smallerNumber);
        }
        return arr;
    }

    public static void main(String args[]) {

        ItemListInterface<CustomizedPackage> arr1 = new ItemList<>();
        arr1.addItem(new CustomizedPackage(style, size, accessory, express, delivery, new User(), false));
        arr1.addItem(new CustomizedPackage(style, size, accessory, flexi, delivery, new User(), false));

        CustomizedPackage specialPackage1 = new CustomizedPackage(style, size, accessory, express, delivery, new User(), false);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 6);
        specialPackage1.setDeliveryDate(cal.getTime());

        arr1.addItem(specialPackage1);

        arr1.addItem(new CustomizedPackage(style, size, accessory, normal, delivery, new User(), false));
        arr1.addItem(new CustomizedPackage(style, size, accessory, normal, delivery, new User(), false));
        arr1.addItem(new CustomizedPackage(style, size, accessory, flexi, delivery, new User(), false));
        arr1.addItem(new CustomizedPackage(style, size, accessory, express, delivery, new User(), false));

        CustomizedPackage specialPackage2 = new CustomizedPackage(style, size, accessory, express, delivery, new User(), false);
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DAY_OF_MONTH, 6);
        specialPackage2.setDeliveryDate(cal2.getTime());

        arr1.addItem(specialPackage2);
        
        ItemListInterface<CustomizedPackage> arr2 = doSelectionSort(arr1);
        for (int i = 1; i <= arr2.getSize(); i++) {
            System.out.println(i + ": " + arr2.getItem(i).getOrderID() + " " + arr2.getItem(i).getDeliveryDate() + " " + arr2.getItem(i).getPriority().getName());
        }
    }
}
