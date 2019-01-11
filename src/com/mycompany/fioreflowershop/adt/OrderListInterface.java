/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.adt;

import java.util.Iterator;

/**
 *
 * @author Nicholas
 */
public interface OrderListInterface<T> {

    public boolean addOrder(T newEntry);

    public boolean addOrder(int newPosition, T newEntry);

    public T removeOrder(int existPosition);

    public boolean replaceOrder(int position, T order);

    public void clear();

    public T getOrder(int position);

    public int getSize();
    
    public boolean contains(T order);

    public boolean isEmpty();

    public Iterator<T> getIterator();
}
