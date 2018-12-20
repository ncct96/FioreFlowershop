/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

import com.mycompany.fioreflowershop.adt.LinkedList;
import com.mycompany.fioreflowershop.adt.ListIteratorInterface;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Chiu Peeng
 */
public class ItemCatalogueTest {

    private ItemCatalogue itemCatalogue = new ItemCatalogue();
    private Item testItem = new Item("Lollipop", 30, 25);

    public ItemCatalogueTest() {
        ListIteratorInterface<Item> styles = new LinkedList<>();
        ListIteratorInterface<Item> sizes = new LinkedList<>();
        ListIteratorInterface<Item> flowers = new LinkedList<>();
        ListIteratorInterface<Item> accessories = new LinkedList<>();
        ListIteratorInterface<Item> priorities = new LinkedList<>();
        ListIteratorInterface<Item> deliveryTypes = new LinkedList<>();

        styles.add(new Item("Fan", 10));
        styles.add(new Item("Elliptical", 10));
        styles.add(new Item("Vertical", 10));
        styles.add(new Item("Horizontal", 10));
        styles.add(new Item("Triangular", 10));

        sizes.add(new Item("Small", 1));
        sizes.add(new Item("Medium", 2));
        sizes.add(new Item("Large", 4));

        flowers.add(new Item("Sunflowers", 250, 10));
        flowers.add(new Item("Red Roses", 300, 20));
        flowers.add(new Item("White Roses", 250, 10));
        flowers.add(new Item("Tulips", 450, 50));
        flowers.add(new Item("Daffodils", 200, 20));

        accessories.add(new Item("None", 0, 1));
        accessories.add(new Item("Name Card", 5, 20));
        accessories.add(new Item("Bears", 50, 5));
        accessories.add(new Item("Woven Basket", 35, 10));

        priorities.add(new Item("Flexi", 1, 6));
        priorities.add(new Item("Normal", 1.5, 4));
        priorities.add(new Item("Express", 3, 2));

        deliveryTypes.add(new Item("Pick Up", 0));
        deliveryTypes.add(new Item("Delivery", 10));

        itemCatalogue.setStyles(styles);
        itemCatalogue.setSizes(sizes);
        itemCatalogue.setFlowers(flowers);
        itemCatalogue.setAccessories(accessories);
        itemCatalogue.setPriorities(priorities);
        itemCatalogue.setDeliveryTypes(deliveryTypes);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of adding Items, of class ItemCatalogue.
     */
    @Test
    public void testAddItem() {
        System.out.println("Test for adding items into the customization catalogue");
        System.out.println("======================================================");
        System.out.println("Initial Item Catalogue:");

        for (int i = 1; i <= itemCatalogue.getAccessories().getTotalEntries(); i++) {
            System.out.print("[" + i + "]");
            System.out.printf(" %s: RM%.2f\n", itemCatalogue.getAccessories().getItem(i).getName(), itemCatalogue.getAccessories().getItem(i).getPrice());
        }
        
        System.out.println("\nItem to be added into the catalogue at position 2:");
        System.out.printf("%s: RM%.2f\n", testItem.getName(), testItem.getPrice());
        
        itemCatalogue.getAccessories().addItem(2, testItem);
        
        System.out.println("\nItem Catalogue Post-Add:");
        for (int i = 1; i <= itemCatalogue.getAccessories().getTotalEntries(); i++) {
            System.out.print("[" + i + "]");
            System.out.printf(" %s: RM%.2f\n", itemCatalogue.getAccessories().getItem(i).getName(), itemCatalogue.getAccessories().getItem(i).getPrice());
        }
        assertEquals(itemCatalogue.getAccessories().getItem(2), testItem);
    }

    /**
     * Test of deleting Items, of class ItemCatalogue.
     */
    @Test
    public void testDeleteItem() {
        itemCatalogue.getAccessories().addItem(2, testItem);
        System.out.println("\n\nTest for deleting items from the customization catalogue");
        System.out.println("======================================================");
        System.out.println("Initial Item Catalogue:");

        for (int i = 1; i <= itemCatalogue.getAccessories().getTotalEntries(); i++) {
            System.out.print("[" + i + "]");
            System.out.printf(" %s: RM%.2f\n", itemCatalogue.getAccessories().getItem(i).getName(), itemCatalogue.getAccessories().getItem(i).getPrice());
        }
        
        System.out.println("\nItem to be deleted from the catalogue at position 2:");
        System.out.printf("%s: RM%.2f\n", itemCatalogue.getAccessories().getItem(2).getName(), itemCatalogue.getAccessories().getItem(2).getPrice());
        
        itemCatalogue.getAccessories().removeItem(2);
        
        System.out.println("\nItem Catalogue Post-Delete:");
        for (int i = 1; i <= itemCatalogue.getAccessories().getTotalEntries(); i++) {
            System.out.print("[" + i + "]");
            System.out.printf(" %s: RM%.2f\n", itemCatalogue.getAccessories().getItem(i).getName(), itemCatalogue.getAccessories().getItem(i).getPrice());
        }
        assertNotEquals(itemCatalogue.getAccessories().getItem(2).getName(), testItem);
    }
    
}
