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
public class StackADT<T> implements StackInterface<T> {

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

        if (topIndex >= stack.length) {
            expandStack();
        }
        stack[topIndex] = newEntry;
    }

    public T peek() {
        T item = null;

        if (!isEmpty()) {
            item = stack[topIndex];
        } else {
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
        } else {
            throw new RuntimeException("STACK_UNDERFLOW");
        }

        return item;
    }

    public T get(int index) {
        T item = null;
        if ((index >= 1) && (index <= topIndex)) {
            item = stack[index - 1];
        } else {
            throw new RuntimeException("INDEX_OUT_OF_BOUNDS");
        }
        return item;
    }
    
    public void remove(int index) {
        stack[index] = null;
        
        for(int i = index; i < topIndex; i++){
            stack[i] = stack[i + 1];
        }
        stack[topIndex] = null;
        --topIndex;
    }

    public boolean isEmpty() {
        return topIndex < 0;
    }

    public void clear() {
        for (T item : stack) {
            item = null;
        }
        topIndex = -1;
    }

    public int size() {
        return topIndex;
    }

    @Override
    public String toString(){
        String string = "";
        
        for(T item:stack){
            string += item.toString() + "\n";
        }
        return string;
    }
    private void expandStack() {
        T[] oldStack = stack;
        int oldSize = oldStack.length;

        stack = (T[]) new Object[oldSize + 1];
        System.arraycopy(oldStack, 0, stack, 0, oldSize);
    }
}
