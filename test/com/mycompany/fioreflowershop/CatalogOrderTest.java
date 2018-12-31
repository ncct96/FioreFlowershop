/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.adt.CatalogPackageInterface;
import com.mycompany.fioreflowershop.adt.OrderListInterface;
import com.mycompany.fioreflowershop.modal.CatalogOrders;
import com.mycompany.fioreflowershop.modal.CatalogPackage;
import com.mycompany.fioreflowershop.modal.Consumer;
import com.mycompany.fioreflowershop.modal.CorporateCustomer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    private int order = 1001;
    private String orderID = "CO" + String.valueOf(order);
    private String orderType = "Delivery";
    private boolean paymentStatus = false;
    private Date orderDate = new Date();
    private String orderStatus = "Processed";
    private double orderAmt = 0;
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String retrievalDate = "";
    private Date retrieveDate;
    private Date retrieveTime;
    CatalogPackageInterface<CatalogPackage> cartItem = CatalogOrder.getCartItem();
    OrderListInterface<CatalogOrders> catalogOrder = FioreFlowershop.getCatalogOrder();
    private CorporateCustomer corporate = new CorporateCustomer();
    private Consumer customer = new Consumer();

    public CatalogOrderTest() {
    }

    @Before
    public void setUp() {
        Calendar validRetrieveDate = Calendar.getInstance();
        validRetrieveDate.setTime(new Date()); // Now use today date.
        validRetrieveDate.add(Calendar.DATE, 2); // Adding 2 days
        retrieveDate = validRetrieveDate.getTime();
        retrievalDate = dateFormat.format(validRetrieveDate.getTime());
        retrieveTime = validRetrieveDate.getTime();

        for (int i = 1; i < cartItem.getTotalEntries() + 1; i++) {
            double discountedPrice = (double) ((100 - cartItem.getProduct(i).getDiscountRate()) * cartItem.getProduct(i).getPrice() / 100);
            orderAmt += (discountedPrice * cartItem.getProduct(i).getUserQuantity());
        }
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
//        cartItem.addProduct(new CatalogPackage("FlowerStrong", "Stylish", "Small", "Vase", "Valentine", "Rose", "Ribbons", "Product Type", "12", 2018, 10, 50, 20, 5));
//        cartItem.addProduct(new CatalogPackage("FlowerSmall", "Simple", "Medium", "Jar", "Graduation", "Lavender", "Bow Tie", "Product Type", "11", 2018, 20, 30, 10, 5));
//        cartItem.addProduct(new CatalogPackage("FlowerMedium", "Elegant", "Large", "Wooden box", "Grand Opening", "Sunflower", "Belt", "Product Type", "11", 2018, 15, 40, 5, 2));
        System.out.println("testShowSoppingCart");
//        CatalogOrder.showShoppingCart(cartItem);
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
        System.out.println("\n\ntestSalesOrder");      

        corporate = new CorporateCustomer("Noice", "noice@example.com", "0123456789", "Petaling Jaya", "abcdef", "Not your business", 5000, true);
//        catalogOrder.addOrder(new CatalogOrders(orderID, cartItem, orderType, orderDate, corporate, orderStatus, orderAmt, paymentStatus, retrieveDate, retrieveTime));

        System.out.println("\n=================================================================================================");
        System.out.println("\nFiore Flowershop SDN.BHD \t\t\t\t\t\t" + FioreFlowershop.ConsoleColors.BLACK_BOLD + " SALES ORDER");
        System.out.println("\nQ-5-1, Desa Permai Indah");
        System.out.println("Bandar Gelugor, 11700 Pulau Pinang \t\t\t\t\t" + "Sales Order #[" + orderID + "]");
        System.out.println("Phone : 0125566922 \t\t\t\t\t\t\t" + "DATE: " + dateFormat.format(orderDate));

        System.out.println("\nTO:");
        System.out.println("[" + catalogOrder.getOrder(1).getUser().getEmail() + "]");
        if (customer == null && corporate != null && corporate.getCompany() != null) {
            System.out.println("[" + corporate.getCompany() + "]");
        } else if (corporate.getCompany() == null) {
            System.out.println("[ - ]");
        }
        System.out.println("[" + catalogOrder.getOrder(1).getUser().getAddress() + "]");
        System.out.println("[City, ST ZIP Code]");
        System.out.println("[" + catalogOrder.getOrder(1).getUser().getPhone() + "]");
        System.out.println("=================================================================================================");
        System.out.println("Description \t\t\t  | Quantity  |  Discount Rate(%) | Unit Price(RM) |  Total(RM)");

        for (int i = 1; i < cartItem.getTotalEntries() + 1; i++) {

            double total = (double) ((100 - catalogOrder.getOrder(1).getCatalogPack().getProduct(i).getDiscountRate()) * 
                    catalogOrder.getOrder(1).getCatalogPack().getProduct(i).getPrice() / 100)
                    * catalogOrder.getOrder(1).getCatalogPack().getProduct(i).getUserQuantity();
            System.out.printf("%s  \t\t\t  | \t  %d  |\t         %d\t|\t   %7.2f |   %7.2f\n", catalogOrder.getOrder(1).getCatalogPack().getProduct(i).getName(), 
                    catalogOrder.getOrder(1).getCatalogPack().getProduct(i).getUserQuantity(),catalogOrder.getOrder(1).getCatalogPack().getProduct(i).getDiscountRate(), 
                    catalogOrder.getOrder(1).getCatalogPack().getProduct(i).getPrice(), total);
        }

        for (int i = 1; i < cartItem.getTotalEntries() + 1; i++) {
            orderAmt += (double) ((100 - cartItem.getProduct(i).getDiscountRate()) * cartItem.getProduct(i).getPrice() / 100) * cartItem.getProduct(i).getUserQuantity();
        }

        System.out.println(FioreFlowershop.ConsoleColors.BLACK + "\n\n\t\t\t\t\t\t\t Subtotal :\t\t\t " + FioreFlowershop.ConsoleColors.GREEN + "RM " + orderAmt 
                + FioreFlowershop.ConsoleColors.BLACK);
        System.out.println("\n\t\t\t\t\t\t\tOrder Type :\t\t\t " + catalogOrder.getOrder(1).getOrderType());
        System.out.println("\n\t\t\t\t\t\t Delivery/Pickup Date :\t\t\t " + dateFormat.format(catalogOrder.getOrder(1).getDeliveryDate()));
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
