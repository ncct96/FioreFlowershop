/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

import com.mycompany.fioreflowershop.FioreFlowershop;

/**
 *
 * @author Admin
 */
public class User{
    private String username;
    private String email;
    private String password;
    private String address;
    private String phone;
    
    public User(){
        
    }
    
    public User(String username, String email, String phone, String address){
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    
    public User(String username, String email, String phone, String address, String password){
        this.username = username;
        this.email = email;
        this.address = address;
        this.password = password;
        this.phone = phone;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return  "\n"+FioreFlowershop.ConsoleColors.BLUE + "Username : " + username +FioreFlowershop.ConsoleColors.RESET
                +"\n"+FioreFlowershop.ConsoleColors.BLUE + "Email : " + email +FioreFlowershop.ConsoleColors.RESET
                +"\n"+FioreFlowershop.ConsoleColors.BLUE +  "Contact Number : " + phone +FioreFlowershop.ConsoleColors.RESET
                +"\n"+FioreFlowershop.ConsoleColors.BLUE + "Address : " + address +FioreFlowershop.ConsoleColors.RESET
                +"\n"+FioreFlowershop.ConsoleColors.BLUE + "Password : " + password +FioreFlowershop.ConsoleColors.RESET;
    }
    
}
