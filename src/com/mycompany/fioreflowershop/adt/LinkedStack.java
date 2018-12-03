/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.adt;

/**
 *
 * @author Nicholas
 */
public class LinkedStack<T> implements StackInterface<T> {
    
    private Node<T> topNode;
    
    public LinkedStack() {
        topNode = null;
    }
    
    @Override
    public void push(T newEntry) {
        Node<T> newNode = new Node<>(newEntry);
        
        if (isEmpty()) {
            topNode = newNode;
        } else {
            newNode.setNext(topNode);
            topNode = newNode;
            
        }
    }
    
    @Override
    public T pop() {
        T data = null;
        
        if (!isEmpty()) {
            data = topNode.getData();
            topNode = topNode.getNext();
        }
        
        return data;
    }
    
    @Override
    public T peek() {
        T data = null;
        
        if (!isEmpty()) {
            data = topNode.getData();
        }
        
        return data;
    }
    
    @Override
    public boolean isEmpty() {
        return (topNode == null);
    }
    
    @Override
    public void clear() {
        topNode = null;
    }

    @Override
    public T get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
