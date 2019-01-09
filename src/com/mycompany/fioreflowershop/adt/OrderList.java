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

    private Node<T> firstOrder;
    private int size;

    public OrderList() {
        clearOrderList();
    }

    public final void clearOrderList() {
        firstOrder = null;
        size = 0;
    }

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

    public T getOrder(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= size)) {
            result = getNodeAt(givenPosition).getData();
        }

        return result;
    }

    public boolean contains(T anEntry) {
        boolean found = false;
        Node<T> currentNode = firstOrder;

        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.getData())) {
                found = true;
            } else {
                currentNode = currentNode.getNext();
            }
        }

        return found;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        boolean result;

        if (size == 0) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }

    public String toString() {
        String outputStr = "";
        Node<T> currentNode = firstOrder;
        while (currentNode != null) {
            outputStr += currentNode.getData() + "\n";;
            currentNode = currentNode.getNext();
        }
        return outputStr;
    }

    private void displayChain(Node nodeOne) {
        if (nodeOne != null) {
            System.out.print(nodeOne.getData() + " ");
            displayChain(nodeOne.getNext());
        }
    }
    
    private Node<T> getNodeAt(int givenPosition) {
        Node<T> currentNode = firstOrder;

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
