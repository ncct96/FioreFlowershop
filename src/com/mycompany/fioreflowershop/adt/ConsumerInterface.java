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
public interface ConsumerInterface<T extends Comparable<? super T>> {
    public boolean addConsumer(T newEntry);
    public int getTotalConsumer();
    public boolean isEmpty();
    public T getConsumer(int givenPosition);
    public T remove(int givenPosition);
}
