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
public interface InvoiceInterface<T> {
    public boolean addInvoice(T newEntry);
    public int getTotalInvoice();
    public T getInvoice(int givenPosition);
    public boolean isEmpty();
}
