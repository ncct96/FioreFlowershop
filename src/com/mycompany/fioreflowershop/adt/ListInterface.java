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
public interface ListInterface<T> {

    public boolean add(T newEntry);

    public boolean add(int newPosition, T newEntry);

    public T remove(int existPosition);

    public boolean replace(int givenPosition, T newEntry);

    public void clear();

    public T getItem(int position);

    public int getTotalEntries();

    public boolean isEmpty();

    public boolean isFull();
    
     public Iterator<T> getIterator();

}
