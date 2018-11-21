/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

/**
 *
 * @author Admin
 */
public class Consumer extends User{
    public Consumer(){
        
    }
    public Consumer (String username, String email, String phone, String address){
        super(username, email, phone, address);
    }
    public Consumer(String username, String password, String email, String phone, String address){
        super(username, email, phone, address, password);
    }
}
