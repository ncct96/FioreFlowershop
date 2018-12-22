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
public interface CustomizePackageQueueInterface<T> {

    public void addCustomizedPackage(T newEntry);

    public T removeCustomizedPackage();

    public T getFirstPackage();

    public boolean isEmpty();

    public void clear();

    public int getSize();
}
