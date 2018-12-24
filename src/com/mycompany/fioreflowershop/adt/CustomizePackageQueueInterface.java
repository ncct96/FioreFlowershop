/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.adt;

/**
 *
 * @author Chiu Peeng
 */
public interface CustomizePackageQueueInterface<T> {

    public void enqueuePackage(T newPackage);

    public T dequeuePackage();

    public T getFirstPackage();

    public boolean isEmpty();

    public void clear();

    public int getSize();

    public boolean contains(T item);

    public boolean merge(CustomizePackageQueueInterface<T> queue);
}
