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
public class UserADT<T extends Comparable<? super T>> implements UserInterface<T>{
    private Node<T> firstNode;
    private int numberOfEntries;
    
    public UserADT(){
        firstNode = null;
        numberOfEntries = 0;
    }
    
    @Override
    public boolean addUser(T newEntry) {
        firstNode = add(newEntry, firstNode);
        numberOfEntries++;
        return true;  
    }
    
    private Node add(T newEntry, Node currNode)
  {
      if((currNode == null)||newEntry.compareTo((T)currNode.getData())<=0)
      {
          currNode = new Node(newEntry, currNode);
      }
      else
      {
          Node nodeAfter = add(newEntry, currNode.getNext());
          currNode.setNext(nodeAfter);
      }
      return currNode;
  }

    @Override
    public int getTotalUser() {
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

    private Node<T> getNodeAt(int givenPosition) {
        Node<T> currentNode = firstNode;

        // traverse the list to locate the desired node
        for (int counter = 1; counter < givenPosition; counter++) {
            currentNode = currentNode.getNext();
        }

        return currentNode;
    }
    
    @Override
    public T getUser(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = getNodeAt(givenPosition).getData();
        }

        return result;
    }
    
    public T remove(int givenPosition) {
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
}
