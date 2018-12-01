/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;
import com.mycompany.fioreflowershop.adt.LinkedList;
import com.mycompany.fioreflowershop.adt.ListIteratorInterface;

/**
 *
 * @author Chiu Peeng
 */
public class ItemCatalogue {
    private ListIteratorInterface<Item> styles = new LinkedList<>();
    private ListIteratorInterface<Item> sizes = new LinkedList<>();
    private ListIteratorInterface<Item> flowers = new LinkedList<>();
    private ListIteratorInterface<Item> accessories = new LinkedList<>();
    private ListIteratorInterface<Item> priorities = new LinkedList<>();
    private ListIteratorInterface<Item> deliveryTypes = new LinkedList<>();

    public ItemCatalogue() {
    }

    public ItemCatalogue(ListIteratorInterface<Item> styles, ListIteratorInterface<Item> sizes, ListIteratorInterface<Item> flowers, ListIteratorInterface<Item> accessories, ListIteratorInterface<Item> priorities, ListIteratorInterface<Item> deliveryTypes) {
        this.styles = styles;
        this.sizes = sizes;
        this.flowers = flowers;
        this.accessories = accessories;
        this.priorities = priorities;
        this.deliveryTypes = deliveryTypes;
    }
    
    public ListIteratorInterface<Item> getStyles() {
        return styles;
    }

    public void setStyles(ListIteratorInterface<Item> styles) {
        this.styles = styles;
    }

    public ListIteratorInterface<Item> getSizes() {
        return sizes;
    }

    public void setSizes(ListIteratorInterface<Item> sizes) {
        this.sizes = sizes;
    }

    public ListIteratorInterface<Item> getFlowers() {
        return flowers;
    }

    public void setFlowers(ListIteratorInterface<Item> flowers) {
        this.flowers = flowers;
    }

    public ListIteratorInterface<Item> getAccessories() {
        return accessories;
    }

    public void setAccessories(ListIteratorInterface<Item> accessories) {
        this.accessories = accessories;
    }

    public ListIteratorInterface<Item> getPriorities() {
        return priorities;
    }

    public void setPriorities(ListIteratorInterface<Item> priorities) {
        this.priorities = priorities;
    }

    public ListIteratorInterface<Item> getDeliveryTypes() {
        return deliveryTypes;
    }

    public void setDeliveryTypes(ListIteratorInterface<Item> deliveryTypes) {
        this.deliveryTypes = deliveryTypes;
    }
    
    
}
