/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.adt.*;
import com.mycompany.fioreflowershop.modal.CatalogOrder1;
import com.mycompany.fioreflowershop.modal.CorporateCustomer;
import com.mycompany.fioreflowershop.modal.Consumer;
import com.mycompany.fioreflowershop.modal.User;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class InvoicePayment {
    static Scanner s = new Scanner(System.in);
    
    public static void invoiceMaintenance(){//Selection of menus
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] Make Corporate Customer Invoice Payment.");
        System.out.println("[2] Generate Invoice Payment for Corporate Customer.");
        System.out.println("[3] Back to Main Menu.");
        int invoiceChoice = s.nextInt();
        switch(invoiceChoice){
            case 1:invoicePaymentP1();break;
            case 2://generateInvoice();break; To be Determined
            case 3:FioreFlowershop.counterStaff();break;
        }
    }
    
    public static void invoicePaymentP1(){//First part for invoice payment
        String usern = ""; boolean status = true;
        System.out.println("\nPlease Select The Corporate Customer for to make their payment.");
        if(FioreFlowershop.getShoppingCart() != null){
            for (int i = 1; i <= FioreFlowershop.getShoppingCart().getTotalEntries() ;i++){//Loop the corporate customer that had not paid
                if(FioreFlowershop.getShoppingCart().getItem(i).getCorporate()!= null && !FioreFlowershop.getShoppingCart().getItem(i).getPaymentStat()){
                    //If duplicate user is found, do nothing
                    if(usern.equals(FioreFlowershop.getShoppingCart().getItem(i).getCorporate().getEmail())){
                        
                    }else{//No duplicate user is found
                        System.out.println(FioreFlowershop.ConsoleColors.BLUE + "[" + i + "]" + FioreFlowershop.ConsoleColors.RESET
                        + FioreFlowershop.getShoppingCart().getItem(i).getCorporate().getEmail());
                        usern = FioreFlowershop.getShoppingCart().getItem(i).getCorporate().getEmail();
                    }
                }else {
                    status = false;
                }
            }
            if(!status){//Error message when no records found
                System.out.println(FioreFlowershop.ConsoleColors.RED+"Sorry, No Records Found !"+FioreFlowershop.ConsoleColors.RESET);
            }else{
                System.out.print("\nPlease Enter The Number Listed : "); 
                int corporateChoice = s.nextInt();
                //Get the username of the selected user
                String corporateName = FioreFlowershop.getShoppingCart().getItem(corporateChoice).getCorporate().getEmail();
                invoicePaymentP2(FioreFlowershop.getShoppingCart().getItem(corporateChoice), corporateName);
            }
        } 
    }
    
    public static void invoicePaymentP2(CatalogOrder1 shopping, String email){
        double affordable = 0; double totalPrice = 0; double discountPrice = 0; int invoiceNum = 100;int credit = 0;
        System.out.println("=================================================================================================");
        System.out.println("\nFiore Flowershop SDN.BHD \t\t\t\t\t\t"+ FioreFlowershop.ConsoleColors.BLACK_BOLD +" INVOICE");
        System.out.println("\nQ-5-1, Desa Permai Indah");
        System.out.println("Bandar Gelugor, 11700 Pulau Pinang \t\t\t\t\t" + "INVOICE #["+invoiceNum+"]");
        System.out.println("Phone : 0125566922 \t\t\t\t\t\t\t" + "DATE: OCT 9, 2018");
        
        System.out.println("\nTO:");
        System.out.println("[" + shopping.getCorporate().getEmail()+ "]");
        System.out.println("[" + shopping.getCorporate().getCompany() +"]");
        System.out.println("["+ shopping.getCorporate().getAddress() +"]");
        System.out.println("[City, ST ZIP Code]");
        System.out.println("[" + shopping.getCorporate().getPhone() + "]");
        System.out.println("=================================================================================================");
        System.out.println("Description \t\t\t  | Quantity  |  Discount Rate(%) | Unit Price(RM) |  Total(RM)");
        
        for(int i = 1; i <= FioreFlowershop.getShoppingCart().getTotalEntries() ;i++){
            if(email.equals(shopping.getCorporate().getEmail()) && !FioreFlowershop.getShoppingCart().getItem(i).getPaymentStat()){
                System.out.println(FioreFlowershop.getShoppingCart().getItem(i).getCatalogPackage().getName()+ "\t\t\t  | \t" + FioreFlowershop.getShoppingCart().getItem(i).getCatalogPackage().getQuantity()+ "\t|\t" 
                +FioreFlowershop.getShoppingCart().getItem(i).getCatalogPackage().getDiscountRate()+ "\t    |\t" + FioreFlowershop.getShoppingCart().getItem(i).getCatalogPackage().getPrice() + " \t     |   " + 
                        FioreFlowershop.getShoppingCart().getItem(i).getCatalogPackage().getPrice()*FioreFlowershop.getShoppingCart().getItem(i).getCatalogPackage().getQuantity());
                totalPrice += FioreFlowershop.getShoppingCart().getItem(i).getCatalogPackage().getPrice()*FioreFlowershop.getShoppingCart().getItem(i).getCatalogPackage().getQuantity();
                if(FioreFlowershop.getShoppingCart().getItem(i).getCatalogPackage().getDiscountRate() != 0){
                    discountPrice += (FioreFlowershop.getShoppingCart().getItem(i).getCatalogPackage().getPrice()*FioreFlowershop.getShoppingCart().getItem(i).getCatalogPackage().getQuantity()
                            * FioreFlowershop.getShoppingCart().getItem(i).getCatalogPackage().getDiscountRate()) / 100; 
                }
            }
        }
        //Calculate for corporate customer's affordability
        affordable = shopping.getCorporate().getMonthlyLimit()-shopping.getCorporate().getCreditSpent();
        
            System.out.println("\n\n\t\t\t\t\t\t\t Subtotal :\t\t\t " + totalPrice);
            System.out.println("\t\t\t\t\t\t\t Discount :\t\t\t  " + discountPrice);
            System.out.println("\t\t\t\t\t\t\t =========================================");
            System.out.println("\t\t\t\t\t\t\t BALANCE DUE :\t\t\t "+ (totalPrice-discountPrice));
            
        if(affordable <= 0){//Credit limit reached
            System.out.println("\n"+FioreFlowershop.ConsoleColors.RED + "Sorry, looks like this customer's Credit Limit have exceeded. Please make payment right now.");
            for(int i = 1; i <= FioreFlowershop.getCorporate().getTotalEntries(); i++){
                if(FioreFlowershop.getCorporate().getItem(i).getEmail().equals(email)){
                    FioreFlowershop.getCorporate().getItem(i).setPaymentStatus(false);
                }
            }
        } else if(affordable <= totalPrice){//Not enough money
            System.out.println("\n"+FioreFlowershop.ConsoleColors.RED + "Sorry, looks like this customer can't afford the items.");
            for(int i = 1; i <= FioreFlowershop.getCorporate().getTotalEntries(); i++){
                if(FioreFlowershop.getCorporate().getItem(i).getEmail().equals(email)){
                    FioreFlowershop.getCorporate().getItem(i).setPaymentStatus(false);
                }
            }
        }else {//Normal Transaction carried out
            System.out.println("\n\nCurrent Corporate Customer's Credit Limit: " + shopping.getCorporate().getMonthlyLimit());
            System.out.println("Current Corporate Customer's Affordable Limit: " + affordable);
            System.out.println("After Payment Balance: "+ (affordable-(totalPrice-discountPrice)));
            s.nextLine();
            System.out.println("\nDo you wish to make this payment? [Press Enter for Yes]");
            String enter = s.nextLine();
            if(enter.isEmpty()){
                //Set corporate customer's payment status to true
                for(int i = 1; i <= FioreFlowershop.getCorporate().getTotalEntries(); i++){
                    if(FioreFlowershop.getCorporate().getItem(i).getEmail().equals(email)){
                        FioreFlowershop.getCorporate().getItem(i).setPaymentStatus(true);
                        //Set corporate customer's credit spent back to 0
                        FioreFlowershop.getCorporate().getItem(i).setCreditSpent(0);
                        //Set shopping cart payment status to true
                        for(int k = 1; k <= FioreFlowershop.getShoppingCart().getTotalEntries(); k++){
                            if(FioreFlowershop.getShoppingCart().getItem(k).getCorporate().getEmail().equals(email)){
                                FioreFlowershop.getShoppingCart().getItem(k).setPaymentStat(true);
                            }
                        }
                        //credit += FioreFlowershop.getCorporate().getItem(i).getCreditSpent() + (totalPrice-discountPrice);
                        invoiceNum++;
                        System.out.println(FioreFlowershop.ConsoleColors.GREEN+"Payment Success, Thanks for the Patronage :D "+ FioreFlowershop.ConsoleColors.RESET);
                        System.out.println(FioreFlowershop.ConsoleColors.BLUE+"Redirecting Back to User Selection Menu......" + FioreFlowershop.ConsoleColors.RESET);
                        FioreFlowershop.userTypeSelection();
                    }
                }
            }else {//Payment Cancel by user
                System.out.println(FioreFlowershop.ConsoleColors.RED+"Payment Cancelled, Redirecting back to Main Menu."+FioreFlowershop.ConsoleColors.RESET);
                FioreFlowershop.counterStaff();
            }
        }
    }
}
