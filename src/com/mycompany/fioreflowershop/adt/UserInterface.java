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
public interface UserInterface <T extends Comparable<? super T>>{
    public boolean addUser(T newEntry);
    public int getTotalUser();
    public boolean isEmpty();
    public T getUser(int givenPosition);
    public T remove(int givenPosition);
}
