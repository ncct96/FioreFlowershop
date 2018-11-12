/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop.modal;

/**
 *
 * @author Zion Tseu
 */
public class CatalogOrder1 {
//    private CatalogPackage item;
    private String itemName;
    private String itemStyle;
    private String itemSize;
    private String itemFlower;
    private String itemAccesscory;
    private int itemQuantity;
    private double itemPrice;

    public CatalogOrder1() {
    }
    
    public CatalogOrder1(String itemName, String itemStyle, String itemSize, String itemFlower, String itemAccessory,double itemPrice,int itemQuantity) {
        this.itemName = itemName;
        this.itemStyle = itemStyle;
        this.itemSize = itemSize;
        this.itemFlower = itemFlower;
        this.itemAccesscory = itemAccessory;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;    
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(String itemStyle) {
        this.itemStyle = itemStyle;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public String getItemFlower() {
        return itemFlower;
    }

    public void setItemFlower(String itemFlower) {
        this.itemFlower = itemFlower;
    }

    public String getItemAccesscory() {
        return itemAccesscory;
    }

    public void setItemAccesscory(String itemAccesscory) {
        this.itemAccesscory = itemAccesscory;
    }   

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
    
    public double getItemPrice() {
        return itemPrice;
    }
    
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

//    public CatalogPackage getItem() {
//        return item;
//    }
//
//    public void setItem(CatalogPackage item) {
//        this.item = item;
//    }
    

//    @Override
//    public String toString() {
//        return "CatalogOrder{" + "item=" + item + ", itemQuantity=" + itemQuantity + '}';
//    }

    @Override
    public String toString() {
        return "CatalogOrder{" + "itemName=" + itemName + ", itemStyle=" + itemStyle + ", itemSize=" + itemSize + ", itemFlower=" + itemFlower + ", itemQuantity=" + itemQuantity + ", itemPrice=" + itemPrice + '}';
    }
    
}
