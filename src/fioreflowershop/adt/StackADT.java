/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop.adt;

/**
 *
 * @author Chiu Peeng
 */
public class StackADT<T> implements StackInterface<T>{

    private T[] stack;
    private int topIndex;
    private static final int DEFAULT_CAPACITY = 100;
    
    public StackADT() {
        this(DEFAULT_CAPACITY);
    }

    public StackADT(int initialCapacity) {
        stack = (T[]) new Object[initialCapacity];
        topIndex = -1;
    }
  
    public void push(T newEntry) {
    topIndex++;

    if (topIndex >= stack.length)
    {
      expandStack();
    }
    stack[topIndex] = newEntry;
  }

  public T peek() {
    T item = null;

    if (!isEmpty()) {
      item = stack[topIndex];
    }
    else {
        throw new RuntimeException("STACK_UNDERFLOW");
    }

    return item;
  } 

  public T pop() {
    T item = null;

    if (!isEmpty()) {
      item = stack[topIndex];
      stack[topIndex] = null;
      topIndex--;
    }
    else {
        throw new RuntimeException("STACK_UNDERFLOW");
    }
    
    return item;
  }

  public boolean isEmpty() {
    return topIndex < 0;
  }

  public void clear() {
      for(T item:stack){
          item = null;
      }
      topIndex = -1;
  }
  
  public int size() {
      return topIndex;
  }
  
  private void expandStack() {
    T[] oldStack = stack;
    int oldSize = oldStack.length;

    stack = (T[]) new Object[oldSize + 1];
    System.arraycopy(oldStack, 0, stack, 0, oldSize);
  }
}
