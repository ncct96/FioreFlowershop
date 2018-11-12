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
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Nicholas
 */
public class CatalogOrder {

    public static void initializeData(ListInterface pickupOrder) {

        Consumer con = new Consumer("Ncct96", "adgfafgjyaf", "0128198471", "No 13");
        CorporateCustomer corp = new CorporateCustomer("Bxxx1", "sdgsjhd@gmail", "27238723", "No 25", "211221", "Die");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 17);
        cal.set(Calendar.MINUTE, 30);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.HOUR_OF_DAY, 18);
        cal1.set(Calendar.MINUTE, 30);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);

        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.HOUR_OF_DAY, 20);
        cal2.set(Calendar.MINUTE, 30);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);

        Date a = cal2.getTime();
        Date b = cal1.getTime();
        Date c = cal.getTime();
        Date d = cal.getTime();
        Date e = cal1.getTime();
        Date f = cal2.getTime();

        Order order = new Order(1001, "Pickup", con, a);
        Order order2 = new Order(1002, "Pickup", corp, b);
        Order order3 = new Order(1003, "Pickup", corp, c);
        Order order4 = new Order(1004, "Pickup", corp, d);
        Order order5 = new Order(1005, "Pickup", corp, e);
        Order order6 = new Order(1006, "Pickup", corp, f);

        System.out.print(order2.getDate());

        pickupOrder.add(order);
        pickupOrder.add(order2);
        pickupOrder.add(order3);
        pickupOrder.add(order4);
        pickupOrder.add(order5);
        pickupOrder.add(order6);

    }

}
