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
public class InvoiceADT<T> implements InvoiceInterface<T>{
    private Node<T> firstNode;
    private int numberOfEntries;
    
    public InvoiceADT(){
        firstNode = null;
        numberOfEntries = 0;
    }
    
    
    @Override
    public boolean addInvoice(T newEntry) {
        Node<T> newNode = new Node<>(newEntry);

        if (isEmpty()) {
            firstNode = newNode;
        } else {
            Node<T> lastNode = getNodeAt(numberOfEntries);
            lastNode.setNext(newNode);
        }

        numberOfEntries++;
        return true;
    }
    
    private Node<T> getNodeAt(int givenPosition) {
        Node<T> currentNode = firstNode;

        // traverse the list to locate the desired node
        for (int counter = 1; counter < givenPosition; counter++) {
            currentNode = currentNode.getNext();
        }

        return currentNode;
    }
    
    @Override
    public T getInvoice(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = getNodeAt(givenPosition).getData();
        }

        return result;
    }
    
    @Override
    public int getTotalInvoice() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        boolean result;

        if (numberOfEntries == 0) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }
}
