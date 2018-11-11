/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop;

import fioreflowershop.adt.ArrayList;
import fioreflowershop.adt.ArrayQueue;
import fioreflowershop.adt.ListInterface;
import fioreflowershop.adt.QueueInterface;
import fioreflowershop.modal.Consumer;
import fioreflowershop.modal.CorporateCustomer;
import fioreflowershop.modal.CustomizedPackage;
import fioreflowershop.modal.Item;
import fioreflowershop.modal.Order;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author Nicholas
 */
public class Pickup {

    static Scanner sc = new Scanner(System.in);
    ListInterface<CustomizedPackage> customPackageList = new ArrayList<>();

    public static void customSortPickup(QueueInterface customPackage, Consumer consumer) {

        initializeData();

        // ListInterface<CustomizedPackage> customPackageList = customPackage.getCustomPackageList();
    }

    public static void conCatSortPickup(ListInterface order) {

        ListInterface orderList = new ArrayList();

  
    }

    public static void corpCatSortPickup(ListInterface order) {

    }

    public static void sortPickupOrder(ListInterface conOrder, ListInterface corpOrder) {
        ListInterface<Order> conOrderList = new ArrayList();
        ListInterface<Order> corpOrderList = new ArrayList();
        
              Collections.sort(conOrderList, new Comparator<Order>() {
        @Override
        public int compare(MyObject object1, MyObject object2) {
            return (int) (object1.getDate().compareTo(object2.getDate()));
        }
    });

    }

    public static void initializeData() {

        // Retrieve Data from all List
//        customPackage.InitializePackages();
//
//        ArrayList<Item> styles = customPackage.getStyles();
//        ArrayList<Item> sizes = customPackage.getSizes();
//        ArrayList<Item> flowers = customPackage.getFlowers();
//        ArrayList<Item> accessories = customPackage.getAccessories();
//        ArrayList<Item> priorities = customPackage.getPriorities();
//        ArrayList<Item> deliveryTypes = customPackage.getDeliveryTypes();
        //CustomizedPackage customizedPackage = new CustomizedPackage(styles.getItem(1), sizes.getItem(1), flowers.getItem(1), accessories.getItem(1), priorities.getItem(1), deliveryTypes.getItem(1));
        //CorporateCustomer corpCust = new CorporateCustomer("ncct96", "ncct96@gmail.com", "0165919413", "13,Lorong Maju 6, Taman Assam Maju", "ncct96", "TARUC", true);
        //Order order = new Order(1001, "Pick Up", corpCust, customizedPackage);
    }

//    for(int i = 0; i < customPack ; i++ ){
//    
//}
//    
}
