/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;
import com.mycompany.fioreflowershop.adt.ItemList;
import com.mycompany.fioreflowershop.adt.ItemListInterface;

/**
 *
 * @author Chiu Peeng
 */
public class ItemCatalogue {
    private ItemListInterface<Item> styles = new ItemList<>();
    private ItemListInterface<Item> sizes = new ItemList<>();
    private ItemListInterface<Item> flowers = new ItemList<>();
    private ItemListInterface<Item> accessories = new ItemList<>();
    private ItemListInterface<Item> priorities = new ItemList<>();
    private ItemListInterface<Item> deliveryTypes = new ItemList<>();
    private ItemListInterface<Item> flowerPot = new ItemList<>();
    private ItemListInterface<Item> season = new ItemList<>();

    public ItemCatalogue() {
    }
    
    public ItemCatalogue(ItemListInterface<Item> styles, ItemListInterface<Item> sizes, ItemListInterface<Item> flowers, ItemListInterface<Item> accessories, ItemListInterface<Item> priorities, ItemListInterface<Item> deliveryTypes , ItemListInterface<Item> flowerPot, ItemListInterface<Item> season) {
        this.styles = styles;
        this.sizes = sizes;
        this.flowers = flowers;
        this.accessories = accessories;
        this.priorities = priorities;
        this.deliveryTypes = deliveryTypes;
        this.flowerPot = flowerPot;
        this.season = season;
    }

    public ItemCatalogue(ItemListInterface<Item> styles, ItemListInterface<Item> sizes, ItemListInterface<Item> flowers, ItemListInterface<Item> accessories, ItemListInterface<Item> priorities, ItemListInterface<Item> deliveryTypes) {
        this.styles = styles;
        this.sizes = sizes;
        this.flowers = flowers;
        this.accessories = accessories;
        this.priorities = priorities;
        this.deliveryTypes = deliveryTypes;
    }
    
    public ItemListInterface<Item> getStyles() {
        return styles;
    }

    public void setStyles(ItemListInterface<Item> styles) {
        this.styles = styles;
    }

    public ItemListInterface<Item> getSizes() {
        return sizes;
    }

    public void setSizes(ItemListInterface<Item> sizes) {
        this.sizes = sizes;
    }

    public ItemListInterface<Item> getFlowers() {
        return flowers;
    }

    public void setFlowers(ItemListInterface<Item> flowers) {
        this.flowers = flowers;
    }

    public ItemListInterface<Item> getAccessories() {
        return accessories;
    }

    public void setAccessories(ItemListInterface<Item> accessories) {
        this.accessories = accessories;
    }

    public ItemListInterface<Item> getPriorities() {
        return priorities;
    }

    public void setPriorities(ItemListInterface<Item> priorities) {
        this.priorities = priorities;
    }

    public ItemListInterface<Item> getDeliveryTypes() {
        return deliveryTypes;
    }

    public void setDeliveryTypes(ItemListInterface<Item> deliveryTypes) {
        this.deliveryTypes = deliveryTypes;
    }

    public ItemListInterface<Item> getFlowerPot() {
        return flowerPot;
    }

    public void setFlowerPot(ItemListInterface<Item> flowerPot) {
        this.flowerPot = flowerPot;
    }

    public ItemListInterface<Item> getSeason() {
        return season;
    }

    public void setSeason(ItemListInterface<Item> season) {
        this.season = season;
    }
    
}
