/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.adt;

/**
 *
 * @author Zion Tseu
 */
public interface ShoppingCartListInterface<T> {
    public boolean addItem(T newEntry);

    public boolean addItem(int newPosition, T newEntry);

    public T removeItem(int existPosition);
  
    public void clearShoppingCartList();

    public T getItem(int position);

    public int getTotalEntries();

    public boolean isEmpty();

    public boolean isFull();
}
