/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop;

import fioreflowershop.adt.ArrayList;
import fioreflowershop.adt.ListInterface;
import fioreflowershop.modal.CorporateCustomer;
import fioreflowershop.modal.CustomizedPackage;
import fioreflowershop.modal.Item;
import fioreflowershop.modal.Order;
import java.util.Scanner;

/**
 *
 * @author Nicholas
 */
public class Pickup {

    static Scanner sc = new Scanner(System.in);
    ListInterface<CustomizedPackage> customPackageList = new ArrayList<>();

    public void sortPickup() {

        initializeData();

        CustomizePackage customPackage = new CustomizePackage();

        // ListInterface<CustomizedPackage> customPackageList = customPackage.getCustomPackageList();
        for (int i = 1; i < customPackageList.getTotalEntries() + 1; i++) {
            System.out.println(customPackageList.getItem(i).getAccessory().getName());
        }
    }

    public static void initializeData() {

        // Retrieve Data from all List
        CustomizePackage customPackage = new CustomizePackage();
        ListInterface<CustomizedPackage> customPackageList = new ArrayList<>();

        customPackage.InitializePackages();

        ArrayList<Item> styles = customPackage.getStyles();
        ArrayList<Item> sizes = customPackage.getSizes();
        ArrayList<Item> flowers = customPackage.getFlowers();
        ArrayList<Item> accessories = customPackage.getAccessories();
        ArrayList<Item> priorities = customPackage.getPriorities();
        ArrayList<Item> deliveryTypes = customPackage.getDeliveryTypes();

        CustomizedPackage customizedPackage = new CustomizedPackage(styles.getItem(1), sizes.getItem(1), flowers.getItem(1), accessories.getItem(1), priorities.getItem(1), deliveryTypes.getItem(1));

        //CorporateCustomer corpCust = new CorporateCustomer("ncct96", "ncct96@gmail.com", "0165919413", "13,Lorong Maju 6, Taman Assam Maju", "ncct96", "TARUC", true);
        //Order order = new Order(1001, "Pick Up", corpCust, customizedPackage);
    }

    public static void pickupMenu() {
        System.out.println("1. Today's Pick Up Order List");
        System.out.println("2. Search Pick Up Order List by Date");

        int choice = sc.nextInt();

        if (choice == 1) {

        } else if (choice == 2) {

        } else {

        }
    }

//    for(int i = 0; i < customPack ; i++ ){
//    
//}
//    
}
