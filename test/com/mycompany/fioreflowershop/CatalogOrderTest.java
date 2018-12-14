/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.adt.LinkedList;
import com.mycompany.fioreflowershop.modal.CatalogOrders;
import com.mycompany.fioreflowershop.modal.CatalogPackage;
import com.mycompany.fioreflowershop.modal.Consumer;
import com.mycompany.fioreflowershop.modal.CorporateCustomer;
import com.mycompany.fioreflowershop.modal.CustomizedPackage;
import com.mycompany.fioreflowershop.modal.User;
import java.util.Calendar;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zion Tseu
 */
public class CatalogOrderTest {
    private CatalogOrders CO1, CO2;
    private int order = 1001;
    private String orderID = "CO" + String.valueOf(order);
    private String orderType = "Delivery";
    private boolean paymentStatus = false;
    private Date orderDate = new Date();
    private String orderStatus = "Processed";
    private double orderAmt = 0;
    private Date retrieveDate;
    private Date retrieveTime;
    private LinkedList<CatalogPackage> catalogPackage = new LinkedList<>();
    private User corporate = new CorporateCustomer();  
    
    public CatalogOrderTest() {
    }
    
    @Before
    public void setUp() {
        Calendar validRetrieveDate = Calendar.getInstance();
        validRetrieveDate.setTime(new Date()); // Now use today date.
        validRetrieveDate.add(Calendar.DATE, 2); // Adding 2 days
        retrieveDate = validRetrieveDate.getTime();
        retrieveTime = validRetrieveDate.getTime();
        
        catalogPackage.add(new CatalogPackage("FlowerStrong", "Stylish", "Small", "Rose", "Ribbons", "Product Type", "12", 2018, 10, 50, 20, 5));
        catalogPackage.add(new CatalogPackage("FlowerWeak", "Colourful", "Medium", "Lavender", "Bow Tie", "Product Type", "11", 2018, 20, 30, 10, 5));
        catalogPackage.add(new CatalogPackage("FlowerMedium", "Elegant", "Large", "Sunflower", "Belt", "Product Type", "11", 2018, 15, 40, 5, 2));
        
        corporate = new CorporateCustomer("Noice", "noice@example.com", "0123456789", "Petaling Jaya", "abcdef", "Not your business", 5000, true);
        
        for(int i = 1; i<catalogPackage.getTotalEntries() + 1; i++){
            double discountedPrice = (double) ((100 - catalogPackage.getItem(i).getDiscountRate()) * catalogPackage.getItem(i).getPrice() / 100);
            orderAmt += (discountedPrice * catalogPackage.getItem(i).getUserQuantity());
        }
        
        CO1 = new CatalogOrders(orderID, catalogPackage,orderType, orderDate, corporate, orderStatus, orderAmt, paymentStatus, retrieveDate, retrieveTime);
    }

    /**
     * Test of CustomerOrderMain method, of class CatalogOrder.
     */
    @Test
    public void testCustomerOrderMain() {
        
    }

    /**
     * Test of CorporateOrderMain method, of class CatalogOrder.
     */
    @Test
    public void testCorporateOrderMain() {
        
    }

    /**
     * Test of initializeCatalog method, of class CatalogOrder.
     */
    @Test
    public void testInitializeCatalog() {
        
    }

    /**
     * Test of displayShoppingCart method, of class CatalogOrder.
     */
    @Test
    public void testDisplayShoppingCart() {
        
    }

    /**
     * Test of showShoppingCart method, of class CatalogOrder.
     */
    @Test
    public void testShowShoppingCart() {
        if (!catalogPackage.isEmpty()) {
            System.out.println("\nShopping Cart");
            System.out.println("====================================================================================================");
            System.out.println("Product Name  \t\t\t\tUnit Price\t\tQuantity\t\tTotal Price");
            double payAmount2 = 0;
            if (!catalogPackage.isEmpty()) {
                for (int i = 1; i < catalogPackage.getTotalEntries() + 1; i++) {
                    System.out.printf("%d. %s\n", i, catalogPackage.getItem(i).getName());
                    double discountedPrice = (double) ((100 - catalogPackage.getItem(i).getDiscountRate()) * catalogPackage.getItem(i).getPrice() / 100);
                    payAmount2 += (discountedPrice * catalogPackage.getItem(i).getUserQuantity());
                    System.out.printf("%s,%s,%s,%s \tRM%7.2f\t\t   %d\t\t\t RM%7.2f\n\n", catalogPackage.getItem(i).getStyle(), catalogPackage.getItem(i).getSize(), catalogPackage.getItem(i).getFlower(), catalogPackage.getItem(i).getAccessory(), discountedPrice, catalogPackage.getItem(i).getUserQuantity(), discountedPrice * catalogPackage.getItem(i).getUserQuantity());
                }
            }
            System.out.printf(FioreFlowershop.ConsoleColors.RED + "Amount: \t\t\t\t\t\t\t\t\t         RM%7.2f\n", payAmount2);
            System.out.println(FioreFlowershop.ConsoleColors.BLACK + "==========================================================================================================");
        }
    }

    /**
     * Test of displayCatalog method, of class CatalogOrder.
     */
    @Test
    public void testDisplayCatalog() {
        
    }

    /**
     * Test of removeCartItem method, of class CatalogOrder.
     */
    @Test
    public void testRemoveCartItem() {
        
    }

    /**
     * Test of salesOrder method, of class CatalogOrder.
     */
    @Test
    public void testSalesOrder() {
        
    }

    /**
     * Test of typeSelection method, of class CatalogOrder.
     */
    @Test
    public void testTypeSelection() {
       
    }

    /**
     * Test of freshFlowerCatalog method, of class CatalogOrder.
     */
    @Test
    public void testFreshFlowerCatalog() {
        
    }

    /**
     * Test of bouquetsCatalog method, of class CatalogOrder.
     */
    @Test
    public void testBouquetsCatalog() {
        
    }

    /**
     * Test of flowerArrangementCatalog method, of class CatalogOrder.
     */
    @Test
    public void testFlowerArrangementCatalog() {
        
    }

    /**
     * Test of initializeData method, of class CatalogOrder.
     */
    @Test
    public void testInitializeData() {
        
    }
    
}
