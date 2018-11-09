/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop.modal;

/**
 *
 * @author Admin
 */
public class Customer extends User{
    public Customer(){
        
    }
    public Customer(String username, String email, String phone, String address, String password){
        super(username, email, phone, address, password);
    }
}
