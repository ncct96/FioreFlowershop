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
public class ItemList<T> implements ItemListInterface<T> {

    private Node<T> head; //First item in the list
    private Node<T> tail; //Last item in the list
    private int size; //Size of the list

    public ItemList() {
        head = null;
        tail = null;
    }

    //Add to front of list
    @Override
    public boolean addFront(T newItem) {
        Node newNode = new Node(newItem);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }

        ++size;
        return true;
    }

    //Add to rear of list
    @Override
    public boolean addItem(T newEntry) {
        Node<T> newNode = new Node<>(newEntry);

        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }

        tail = newNode;
        size++;
        return true;
    }

    //Add item at index
    @Override
    public boolean addItem(int newPosition, T newEntry) {

        if (newPosition == 1) {
            addFront(newEntry);
        } else if (newPosition == size + 1) {
            addItem(newEntry);
        } else if ((newPosition >= 1) && (newPosition <= size + 1)) {
            Node<T> newNode = new Node<T>(newEntry);
            Node nodeBefore = getNodeAt(newPosition - 1);
            Node nodeAfter = nodeBefore.getNext();
            newNode.setNext(nodeAfter);
            nodeBefore.setNext(newNode);
            ++size;
        } else {
            return false;
        }
        return true;
    }

    //Remove from front of list
    @Override
    public T removeFront() {
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

    //Remove from rear of list
    @Override
    public T removeItem() {
        T rear = null;

        if (size == 1) {
            rear = tail.getData();
            head = null;
            tail = null;
        } else if (size > 0) {
            rear = tail.getData();

            Node node = head;
            while (node.getNext() != tail) {
                node = node.getNext();
            }
            tail = node;
            tail.setNext(null);
        } else {
            size = 1;
        }

        --size;
        return rear;
    }

    //Remove from index
    @Override
    public T removeItem(int position) {
        T result = null;

        if (position == 1) {
            removeFront();
        } else if (position == size) {
            removeItem();
        } else if ((position >= 1) && (position <= size)) {
            Node<T> nodeBefore = getNodeAt(position - 1);
            Node<T> nodeToRemove = nodeBefore.getNext();
            Node<T> nodeAfter = nodeToRemove.getNext();
            nodeBefore.setNext(nodeAfter);
            result = nodeToRemove.getData();
            --size;
        }
        return result;
    }

    //Replace item at index
    @Override
    public boolean replaceItem(int index, T newEntry) {
        boolean isSuccessful = true;

        if ((index >= 1) && (index <= size)) {
            Node<T> desiredNode = getNodeAt(index);
            desiredNode.setData(newEntry);
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    //Get item at index
    @Override
    public T getItem(int index) {
        T result = null;

        if ((index >= 1) && (index <= size)) {
            result = getNodeAt(index).getData();
        }

        return result;
    }

    //Check if list contains specified item
    @Override
    public boolean contains(T item) {
        Node<T> currentNode = head;

        while (currentNode.getNext() != null) {
            if (item.equals(currentNode.getData())) {
                return true;
            }
            if(currentNode.getNext() == null)
                break;
            else
                currentNode = currentNode.getNext();
        }
        return false;
    }

    //Get size of the list
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
        Node<T> currentNode = head;
        while (currentNode != null) {
            outputStr += currentNode.getData() + "\n";;
            currentNode = currentNode.getNext();
        }
        return outputStr;
    }

    //Get node at index
    private Node<T> getNodeAt(int index) {
        Node<T> currentNode = head;
        for (int position = 1; position < index; position++) {
            currentNode = currentNode.getNext();
        }

        return currentNode;
    }

    //Clear the list
    public final void clear() {
        head = null;
        size = 0;
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
