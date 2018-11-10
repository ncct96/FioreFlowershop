/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop;

import fioreflowershop.adt.ArrayList;
import fioreflowershop.adt.ListInterface;
import fioreflowershop.modal.CustomizedPackage;
import fioreflowershop.modal.Item;

/**
 *
 * @author Nicholas
 */
public class Pickup {

    public void sortPickup() {
        
        CustomizePackage customPackage = new CustomizePackage();

        ListInterface<CustomizedPackage> customPackageList = customPackage.getCustomPackageList();

        for(int i = 1; i < customPackageList.getTotalEntries() + 1; i++){
            System.out.println(customPackageList.getItem(i).getAccessory().getName());
        }
    }

//    for(int i = 0; i < customPack ; i++ ){
//    
//}
//    
}
