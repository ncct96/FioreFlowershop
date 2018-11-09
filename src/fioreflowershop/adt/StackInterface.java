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
interface StackInterface<T> {
    public void push(T object);
    
    public T pop();
    
    public T peek();
    
    public boolean isEmpty();
    
    public int size();
    
    public void clear();
}
