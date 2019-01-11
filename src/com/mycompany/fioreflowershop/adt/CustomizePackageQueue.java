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
public class CustomizePackageQueue<T> implements CustomizePackageQueueInterface<T> {

    private Node<T> firstOrder; //First order in the queue
    private Node<T> lastOrder; //Last order in the queue
    private int size = 0; //Size of the queue

    public CustomizePackageQueue() {
        firstOrder = null;
        lastOrder = null;
    }

    //Add order to the back of the queue
    @Override
    public void enqueuePackage(T newPackage) {
        Node newNode = new Node(newPackage, null);

        if (isEmpty()) {
            firstOrder = newNode;
        } else {
            lastOrder.next = newNode;
        }

        lastOrder = newNode;
        ++size;
    }

    //Get and remove order from front of the queue
    @Override
    public T dequeuePackage() {
        T front = null;

        if (size == 1) {
            front = firstOrder.getData();
            firstOrder = null;
            lastOrder = null;
        } else if (size > 0) {
            front = firstOrder.getData();
            firstOrder = firstOrder.getNext();
        } else {
            size = 1;
        }

        --size;
        return front;
    }
    
    //Get order from front of the queue
    @Override
    public T getFirstPackage() {
        T front = null;

        if (!isEmpty()) {
            front = firstOrder.getData();
        }

        return front;
    }

    //Check if queue is empty
    @Override
    public boolean isEmpty() {
        return (firstOrder == null) && (lastOrder == null);
    }

    //Clear the queue
    @Override
    public void clear() {
        firstOrder = null;
        lastOrder = null;
        size = 0;
    }

    //Get size of the queue
    @Override
    public int getSize() {
        return size;
    }

    //Check if queue contains specified item
    @Override
    public boolean contains(T item) {
        Node<T> currentNode = firstOrder;

        while (currentNode.getNext() != null) {
            if (item.equals(currentNode.getData())) {
                return true;
            }
            if (currentNode.getNext() == null) {
                break;
            } else {
                currentNode = currentNode.getNext();
            }
        }
        return false;
    }

    //Add incoming queue to the back of the current queue
    @Override
    public boolean merge(CustomizePackageQueueInterface<T> queue) {
        if (queue.isEmpty()) {
            return false;
        }
        while (!queue.isEmpty()) {
            enqueuePackage(queue.dequeuePackage());
        }
        return true;
    }

    //Replace order at index
    public boolean replaceOrder(int index, T newEntry) {
        boolean isSuccessful = true;

        if ((index >= 1) && (index <= size)) {
            Node<T> desiredNode = getNodeAt(index);
            desiredNode.setData(newEntry);
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    //Get order at index
    public T getOrder(int index) {
        T result = null;

        if ((index >= 1) && (index <= size)) {
            result = getNodeAt(index).getData();
        }

        return result;
    }

    //Get node at index
    private Node<T> getNodeAt(int index) {
        Node<T> currentNode = firstOrder;
        for (int position = 1; position < index; position++) {
            currentNode = currentNode.getNext();
        }

        return currentNode;
    }

    //Node class
    private class Node<T> {

        private T data;
        private Node next;

        private Node(T dataPortion) {
            data = dataPortion;
            next = null;
        }

        private Node(T dataPortion, Node linkPortion) {
            data = dataPortion;
            next = linkPortion;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
