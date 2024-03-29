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
public interface StackInterface<T>{
    public void push(T object);
    
    public T pop();
    
    public T peek();
    
    public T get(int index);
    
    public void remove(int i);
    
    public boolean isEmpty();
    
    public int size();
    
    public void clear();
}
