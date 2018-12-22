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

    private Node firstNode;
    private Node lastNode;
    private int size = 0;

    public CustomizePackageQueue() {
        firstNode = null;
        lastNode = null;
    }

    public void addCustomizedPackage(T newEntry) {
        Node newNode = new Node(newEntry, null);

        if (isEmpty()) {
            firstNode = newNode;
        } else {
            lastNode.next = newNode;
        }

        lastNode = newNode;
        ++size;
    }

    public T getFirstPackage() {
        T front = null;

        if (!isEmpty()) {
            front = firstNode.data;
        }

        return front;
    }

    public T removeCustomizedPackage() {
        T front = null;

        if (!isEmpty()) {
            front = firstNode.data;
            firstNode = firstNode.next;

            if (firstNode == null) {
                lastNode = null;
            }

            --size;
        }
        return front;
    }

    public boolean isEmpty() {
        return (firstNode == null) && (lastNode == null);
    }

    public void clear() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    private class Node {

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
    }
}
