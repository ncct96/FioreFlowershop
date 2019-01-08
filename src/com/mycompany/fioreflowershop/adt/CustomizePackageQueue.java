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

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public CustomizePackageQueue() {
        head = null;
        tail = null;
    }

    public void enqueuePackage(T newPackage) {
        Node newNode = new Node(newPackage, null);

        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }

        tail = newNode;
        ++size;
    }

    public T dequeuePackage() {
        T front = null;

        if (size == 1) {
            front = head.getData();
            head = null;
            tail = null;
        } else if (size > 0) {
            front = head.getData();
            head = head.getNext();
        } else {
            size = 1;
        }

        --size;
        return front;
    }

    public T getFirstPackage() {
        T front = null;

        if (!isEmpty()) {
            front = head.getData();
        }

        return front;
    }

    public boolean isEmpty() {
        return (head == null) && (tail == null);
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean contains(T item) {
        Node<T> currentNode = head;

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
    
    private Node<T> getNodeAt(int givenPosition) {
        Node<T> currentNode = head;

        // traverse the list to locate the desired node
        for (int counter = 1; counter < givenPosition; counter++) {
            currentNode = currentNode.getNext();
        }

        return currentNode;
    }
    
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
