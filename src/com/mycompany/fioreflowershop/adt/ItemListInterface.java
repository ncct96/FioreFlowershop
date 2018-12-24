/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.adt;

import java.util.Iterator;

/**
 *
 * @author Chiu Peeng
 */
public interface ItemListInterface<T> {

    public boolean addItem(T newItem);

    public boolean addItem(int position, T newItem);
    
    public boolean addFront(T newItem);

    public T removeItem(int position);
    
    public T removeItem();
    
    public T removeFront();

    public boolean replaceItem(int position, T newItem);

    public void clear();

    public T getItem(int position);

    public boolean contains(T item);
            
    public int getSize();

    public boolean isEmpty();
}
