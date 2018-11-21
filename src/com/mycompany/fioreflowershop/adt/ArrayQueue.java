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
public class ArrayQueue<T> implements QueueInterface<T> {

  private T[] queue;
  private final static int frontIndex = 0; //Front of the queue is fixed, since first index is 0
  private int backIndex;
  private static final int DEFAULT_INITIAL_CAPACITY = 50;

  public ArrayQueue() {
    this(DEFAULT_INITIAL_CAPACITY);
  } // end default constructor

  public ArrayQueue(int initialCapacity) {
    queue = (T[]) new Object[initialCapacity];
    backIndex = -1;//intialized to -1 to indicate an empty queue
  } // end constructor

  //Adds a new entry to the back of the queue.
  public void enqueue(T newEntry) {
    if (isArrayFull()) // isArrayFull and
    {
      doubleArray();   // doubleArray are private
    }
    backIndex++;
    queue[backIndex] = newEntry;
  } // end enqueue

  //Retrieves the entry at the front of the queue.
  public T getFront() {
    T front = null;

    if (!isEmpty()) {
      front = queue[frontIndex];
    }

    return front;
  } // end getFront


  //Removes and returns the entry at the front of the queue.
  public T dequeue() {
    T front = null;

    if (!isEmpty()) {
      front = queue[frontIndex];

      // shift remaining queue items forward one position
      for (int i = frontIndex; i < backIndex; ++i) {
        queue[i] = queue[i + 1];
      }
      queue[backIndex] = null;
      backIndex--;
    } // end if

    return front;
  } // end dequeue

  //Detects whether .the queue is empty.
  public boolean isEmpty() {
    return frontIndex > backIndex;
  } // end isEmpty

  //Removes all entries from the queue.
  public void clear() {
    if (!isEmpty()) { // deallocates only the used portion
      for (int index = frontIndex; index <= backIndex; index++) {
        queue[index] = null;
      } // end for

      backIndex = -1;
    } // end if
  } // end clear

  //Detects whether the queue is full
  private boolean isArrayFull() {
    return backIndex == queue.length - 1;
  } // end isArrayFull

    public int getBackIndex() {
        return backIndex;
    }

  //Doubles the size of the old Queue list
  private void doubleArray() {
    T[] oldQueue = queue;
    int oldSize = oldQueue.length;

    queue = (T[]) new Object[2 * oldSize];

    for (int index = 0; index < oldSize; index++) {
      queue[index] = oldQueue[index];
    } // end for
  } // end doubleArray
}  // end ArrayQueue
