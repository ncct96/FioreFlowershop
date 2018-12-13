/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

import com.mycompany.fioreflowershop.FioreFlowershop;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class CorporateCustomer extends User{
    private String company;
    private int monthlyLimit;
    private double creditSpent;
    private boolean paymentStatus;
    
    public CorporateCustomer(){
        super();
    }
    
    public CorporateCustomer(String username, String email, String phone, String address, int monthLimit, double creditSpent, boolean paymentStat){
        super(username, email, phone, address);
        this.monthlyLimit = monthLimit;
        this.creditSpent = creditSpent;
        this.paymentStatus = paymentStat;
    }
    
    public CorporateCustomer(String username, String email, String phone, String address, String password, String company, int monthLimit, boolean paymentStat){
        super(username, email, phone, address, password);
        this.company = company;
        this.monthlyLimit = monthLimit;
        this.paymentStatus = paymentStat;
    }
    
    public void setPaymentStatus(boolean paymentStat){
        this.paymentStatus = paymentStat;
    }
    
    public boolean getPaymentStatus(){
        return paymentStatus;
    }

    public double getCreditSpent() {
        return creditSpent;
    }

    public void setCreditSpent(double creditSpent) {
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
        return super.toString()+"\n"+FioreFlowershop.ConsoleColors.BLUE+"Company Name : " + getCompany() +FioreFlowershop.ConsoleColors.RESET
                +"\n"+FioreFlowershop.ConsoleColors.BLUE + "Credit Spent : " + getCreditSpent() +FioreFlowershop.ConsoleColors.RESET
                +"\n"+FioreFlowershop.ConsoleColors.BLUE + "Preset Credit Limit : " + getMonthlyLimit() +FioreFlowershop.ConsoleColors.RESET;
    }
    
}
