/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.adt;

import com.mycompany.fioreflowershop.modal.CatalogPackage;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Woo Guo Hau
 */
public class CatalogPackageList<T> implements CatalogPackageInterface<T>{
    private Node<T> firstNode;
    private int numberOfEntries;

    public CatalogPackageList() {
        clearCatalogList();
    }
    
    public final void clearCatalogList() {
        firstNode = null;
        numberOfEntries = 0;
    }
    
    @Override
    public boolean addProduct(T newEntry) {
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

    @Override
    public boolean addProduct(int newPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            Node<T> newNode = new Node<T>(newEntry);

            if (isEmpty() || (newPosition == 1)) {     // case 1: add to beginning of list
                newNode.setNext(firstNode);
                firstNode = newNode;
            } else {			// case 2: list is not empty and newPosition > 1
                Node nodeBefore = getNodeAt(newPosition - 1);
                Node nodeAfter = nodeBefore.getNext();
                newNode.setNext(nodeAfter);
                nodeBefore.setNext(newNode);
            }

            numberOfEntries++;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public T removeProduct(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            if (givenPosition == 1) {      // case 1: remove first entry
                result = firstNode.getData();     // save entry to be removed 
                firstNode = firstNode.getNext();
            } else {                         // case 2: givenPosition > 1
                Node<T> nodeBefore = getNodeAt(givenPosition - 1);
                Node<T> nodeToRemove = nodeBefore.getNext();
                Node<T> nodeAfter = nodeToRemove.getNext();
                nodeBefore.setNext(nodeAfter); // disconnect the node to be removed
                result = nodeToRemove.getData();  // save entry to be removed
            }

            numberOfEntries--;
        }

        return result;
    }

    @Override
    public boolean replaceProduct(int givenPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            Node<T> desiredNode = getNodeAt(givenPosition);
            desiredNode.setData(newEntry);
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public T getProduct(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = getNodeAt(givenPosition).getData();
        }

        return result;
    }

    @Override
    public int getTotalEntries() {
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

    public String toString() {
        String outputStr = "";
        Node<T> currentNode = firstNode;
        while (currentNode != null) {
            outputStr += currentNode.getData() + "\n";;
            currentNode = currentNode.getNext();
        }
        return outputStr;
    }

    /**
     * Task: Returns a reference to the node at a given position. Precondition:
     * List is not empty; 1 <= givenPosition <= numberOfEntries.
     */
    private Node<T> getNodeAt(int givenPosition) {
        Node<T> currentNode = firstNode;

        // traverse the list to locate the desired node
        for (int counter = 1; counter < givenPosition; counter++) {
            currentNode = currentNode.getNext();
        }

        return currentNode;
    }
    
    @Override
    public Iterator<T> getIterator() {
        return new LinkedListIterator<>();
    }
    
    public class LinkedListIterator<T> implements Iterator<T> {

        private Node currentNode;

        public LinkedListIterator() {
            currentNode = firstNode;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T returnData = (T) currentNode.getData();
                currentNode = currentNode.getNext();
                return returnData;
            } else {
                throw new NoSuchElementException("Illegal call to next(); iterator is after end of list.");
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
