/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop;

import fioreflowershop.adt.ArrayList;
import fioreflowershop.adt.ListInterface;
import fioreflowershop.modal.Consumer;
import fioreflowershop.modal.CorporateCustomer;
import fioreflowershop.modal.Order;

/**
 *
 * @author Nicholas
 */
public class CatalogOrder {
    
    static ListInterface<Order> conOrder = new ArrayList<>();
    static ListInterface<Order> corpOrder = new ArrayList<>();
    
    public static void initializeData(){ 
        Consumer con = new Consumer("Ncct96", "adgfafgjyaf", "0128198471", "No 13");
        CorporateCustomer corp = new CorporateCustomer("Bxxx1", "sdgsjhd@gmail", "27238723", "No 25", "211221", "Die");
        Order order = new Order(1001, "Pickup", con);
        Order order2 = new Order(1002, "Pickup", corp);
        
        conOrder.add(order);
        corpOrder.add(order2);
        
        
    }
    
}
