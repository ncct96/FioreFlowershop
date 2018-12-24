/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.adt;

import java.util.Iterator;

/**
 *
 * @author Woo Guo Hau
 */
public interface CatalogPackageInterface<T> {
    public boolean addProduct(T newEntry);

    public boolean addProduct(int newPosition, T newEntry);

    public T removeProduct(int existPosition);

    public boolean replaceProduct(int givenPosition, T newEntry);

    public void clearCatalogList();

    public T getProduct(int position);

    public int getTotalEntries();

    public boolean isEmpty();

    public Iterator<T> getIterator();
}
