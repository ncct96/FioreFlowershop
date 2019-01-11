/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.adt;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Chiu Peeng
 */
public class OrderList<T> implements OrderListInterface<T> {

    private Node<T> firstOrder; //First order in the list
    private int size; //Size of the list

    public OrderList() {
        clear();
    }

    //Clear the list
    @Override
    public final void clear() {
        firstOrder = null;
        size = 0;
    }

    //Add order to the rear of the list
    @Override
    public boolean addOrder(T newEntry) {
        Node<T> newNode = new Node<>(newEntry);

        if (isEmpty()) {
            firstOrder = newNode;
        } else {
            Node<T> lastNode = getNodeAt(size);
            lastNode.setNext(newNode);
        }

        size++;
        return true;
    }

    //Add order at index
    @Override
    public boolean addOrder(int newPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= size + 1)) {
            Node<T> newNode = new Node<T>(newEntry);

            if (isEmpty() || (newPosition == 1)) {
                newNode.setNext(firstOrder);
                firstOrder = newNode;
            } else {
                Node nodeBefore = getNodeAt(newPosition - 1);
                Node nodeAfter = nodeBefore.getNext();
                newNode.setNext(nodeAfter);
                nodeBefore.setNext(newNode);
            }

            size++;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    //Remove order at index
    @Override
    public T removeOrder(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= size)) {
            if (givenPosition == 1) {
                result = firstOrder.getData();
                firstOrder = firstOrder.getNext();
            } else {
                Node<T> nodeBefore = getNodeAt(givenPosition - 1);
                Node<T> nodeToRemove = nodeBefore.getNext();
                Node<T> nodeAfter = nodeToRemove.getNext();
                nodeBefore.setNext(nodeAfter);
                result = nodeToRemove.getData();
            }

            size--;
        }

        return result;
    }

    //Replace order at index
    @Override
    public boolean replaceOrder(int givenPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= size)) {
            Node<T> desiredNode = getNodeAt(givenPosition);
            desiredNode.setData(newEntry);
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    //Get order at index
    @Override
    public T getOrder(int index) {
        T result = null;

        if ((index >= 1) && (index <= size)) {
            result = getNodeAt(index).getData();
        }

        return result;
    }

    //Check if list contains specified order
    @Override
    public boolean contains(T order) {
        boolean found = false;
        Node<T> currentNode = firstOrder;

        while (!found && (currentNode != null)) {
            if (order.equals(currentNode.getData())) {
                found = true;
            } else {
                currentNode = currentNode.getNext();
            }
        }

        return found;
    }

    //Get size of list
    @Override
    public int getSize() {
        return size;
    }

    //Check if list is empty
    @Override
    public boolean isEmpty() {
        boolean result;

        if (size == 0) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }

    //Get list data as a string
    @Override
    public String toString() {
        String outputStr = "";
        Node<T> currentNode = firstOrder;
        while (currentNode != null) {
            outputStr += currentNode.getData() + "\n";;
            currentNode = currentNode.getNext();
        }
        return outputStr;
    }

    //Get node at index
    private Node<T> getNodeAt(int index) {
        Node<T> currentNode = firstOrder;

        for (int position = 1; position < index; position++) {
            currentNode = currentNode.getNext();
        }

        return currentNode;
    }

    //Get iterator for the list
    @Override
    public Iterator<T> getIterator() {
        return new LinkedListIterator<>();
    }

    public class LinkedListIterator<T> implements Iterator<T> {

        private Node currentNode;

        public LinkedListIterator() {
            currentNode = firstOrder;
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
    }
}
