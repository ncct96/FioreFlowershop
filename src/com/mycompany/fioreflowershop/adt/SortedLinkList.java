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
public class SortedLinkList<T extends Comparable<? super T>> implements SortedListInterface<T> {

  private Node firstNode;
  private int length;

  public SortedLinkList() {
    firstNode = null;
    length = 0;
  }

  public boolean add(T newEntry) {
//    Node newNode = new Node(newEntry);
//
//    Node nodeBefore = null;							// For linked list traversal: to reference the node before the current node
//    Node currentNode = firstNode;				// For linked list traversal: to reference the current node
//    while (currentNode != null && newEntry.compareTo(currentNode.data) > 0) {
//      nodeBefore = currentNode;
//      currentNode = currentNode.next;
//    }
//
//    if (isEmpty() || (nodeBefore == null)) { // CASE 1: add at beginning
//      newNode.next = firstNode;
//      firstNode = newNode;
//    } else {	// CASE 2: add in the middle or at the end, i.e. after nodeBefore
//      newNode.next = currentNode;
//      nodeBefore.next = newNode;
//    }
//    length++;
    
    firstNode = add(newEntry, firstNode);
    length++;
    return true;  
  }

  private Node add(T newEntry, Node currNode)
  {
      if((currNode == null)||newEntry.compareTo(currNode.data)<=0)
      {
          currNode = new Node(newEntry, currNode);
      }
      else
      {
          Node nodeAfter = add(newEntry, currNode.next);
          currNode.next = nodeAfter;
      }
      return currNode;
  }
  
  public boolean remove(T anEntry) {
    throw new UnsupportedOperationException();	// Left as Practical exercise
  }

  public int getPosition(T anEntry) {
    throw new UnsupportedOperationException();	// Left as Practical exercise
  }

  public T getEntry(int givenPosition) {
    T result = null;

    if ((givenPosition >= 1) && (givenPosition <= length)) {
      Node currentNode = firstNode;
      for (int i = 0; i < givenPosition - 1; ++i) {
        currentNode = currentNode.next;		// advance currentNode to next node
      }
      result = currentNode.data;	// currentNode is pointing to the node at givenPosition
    }

    return result;
  }

  public boolean contains(T anEntry) {
//    boolean found = false;
//    Node currentNode = firstNode;
//
//    while (!found && (currentNode != null)) {
//      if (anEntry.equals(currentNode.data)) {
//        found = true;
//      } else {
//        currentNode = currentNode.next;
//      }
//    }
//
//    return found;
    
    
    boolean found = false;
    Node tempNode = firstNode;
    int pos = 1;

    while (!found && (tempNode != null)) {
      if (anEntry.compareTo(tempNode.data) <= 0) {
        found = true;
      } else {
        tempNode = tempNode.next;
        pos++;
      }
    }
    System.out.println("\n***TRACE: tempNode.data is " + tempNode.data + " " + pos);
    if (tempNode != null && tempNode.data.equals(anEntry))
      return true;
    else 
      return false;
  }

  public T remove(int givenPosition) {
    T result = null;

    if ((givenPosition >= 1) && (givenPosition <= length)) {
      if (givenPosition == 1) {      	// CASE 1: remove first entry
        result = firstNode.data;     	// save entry to be removed 
        firstNode = firstNode.next;		// update firstNode to point to the next node
      } else {                         	// CASE 2: remove interior entry or last entry
        Node nodeBefore = firstNode;
        for (int i = 1; i < givenPosition - 1; ++i) {
          nodeBefore = nodeBefore.next;		// advance nodeBefore to its next node
        }
        result = nodeBefore.next.data;  	// save entry to be removed	
        nodeBefore.next = nodeBefore.next.next;	// make node before point to node after the 
      } 															// one to be deleted (to disconnect node from chain)

      length--;
    }

    return result;
  }

  public final void clear() {
    firstNode = null;
    length = 0;
  }

  public int getLength() {
    return length;
  }

  public boolean isEmpty() {
    return (length == 0);
  }

  public boolean isFull() {
    return false;
  }

  public String toString() {
    String outputStr = "";
    Node currentNode = firstNode;
    while (currentNode != null) {
      outputStr += currentNode.data + "\n";;
      currentNode = currentNode.next;
    }
    return outputStr;
  }

  private class Node {

    private T data;
    private Node next;

    private Node(T dataPortion) {
      data = dataPortion;
      next = null;
    }

    private Node(T dataPortion, Node nextNode) {
      data = dataPortion;
      next = nextNode;
    }
  }
}
