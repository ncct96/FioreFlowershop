/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.adt;

/**
 *
 * @author Admin
 */
public interface CorporateInterface<T extends Comparable<? super T>> {
    public boolean addCorporate(T newEntry);
    public int getTotalEntries();
    public boolean isEmpty();
    public T getItem(int givenPosition);
    public T remove(int givenPosition);
}
