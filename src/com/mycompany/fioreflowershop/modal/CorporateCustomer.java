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
public class CorporateCustomer extends User{
    private String company;
    private int monthlyLimit;
    private int creditSpent;
    
    public CorporateCustomer(){
        
    }
    
    public CorporateCustomer(String username, String email, String phone, String address, int monthLimit, int creditSpent){
        super(username, email, phone, address);
        this.monthlyLimit = monthLimit;
        this.creditSpent = creditSpent;
    }
    
    public CorporateCustomer(String username, String email, String phone, String address, String password, String company, int monthLimit){
        super(username, email, phone, address, password);
        this.company = company;
        this.monthlyLimit = monthLimit;
    }

    public int getCreditSpent() {
        return creditSpent;
    }

    public void setCreditSpent(int creditSpent) {
        this.creditSpent = creditSpent;
    }
    
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(int monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }

    @Override
    public String toString() {
        return super.toString()+ "\nCompany Name : " + company + "\nCredit Spent : " + creditSpent + "\nPreset Credit Limit : " + monthlyLimit;
    }
    
}
