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
public interface CustomizePackageQueueInterface<T> {
    /**
   * Task: Adds a new entry to the back of the queue.
   *
   * @param newEntry an object to be added
   */
  public void addCustomizedPackage(T newEntry);

  /**
   * Task: Removes and returns the entry at the front of the queue.
   *
   * @return either the object at the front of the queue or, if the queue is
   * empty before the operation, null
   */
  public T removeCustomizedPackage();

  /**
   * Task: Retrieves the entry at the front of the queue.
   *
   * @return either the object at the front of the queue or, if the queue is
   * empty, null
   */
  public T getFirstPackage();

  /**
   * Task: Detects whether .the queue is empty.
   *
   * @return true if the queue is empty, or false otherwise
   */
  public boolean isEmpty();

  /**
   * Task: Removes all entries from the queue.
   */
  public void clear();
 
  public int getBackIndex();
}
