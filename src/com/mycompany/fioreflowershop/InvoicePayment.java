/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.adt.*;
import com.mycompany.fioreflowershop.modal.CatalogOrders;
import com.mycompany.fioreflowershop.modal.CorporateCustomer;
import com.mycompany.fioreflowershop.modal.Consumer;
import com.mycompany.fioreflowershop.modal.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class InvoicePayment {
    private static Scanner s = new Scanner(System.in);
    private static Date today = new Date();
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static CorporateCustomer cc;
    
    public static void invoiceMaintenance(){//Selection of menus
        
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] Make Corporate Customer Invoice Payment.");
        System.out.println("[2] Generate Invoice Payment for Corporate Customer.");
        System.out.println("[3] Back to Main Menu.");
        
        try{
            int invoiceChoice = s.nextInt(); s.nextLine();
            switch(invoiceChoice){
                case 1:invoicePaymentP1();break;
                case 2:generateInvoiceP1();break; 
                case 3:FioreFlowershop.counterStaff();break;
            }
        }catch (Exception e){
            System.out.println("\n"+FioreFlowershop.ConsoleColors.RED+"An Error had occurred. Please Enter The Numbers Stated"+FioreFlowershop.ConsoleColors.RESET);
            System.out.println(FioreFlowershop.ConsoleColors.BLUE+"\nRedirecting Back to Invoice Maintenance Menu......" + FioreFlowershop.ConsoleColors.RESET);
            invoiceMaintenance();
        }
    }
    
    public static void generateInvoiceP1(){
        s.nextLine();
        String usern = ""; boolean stat = false;int yearEntered = 0;int monthEntered = 0;
        try{
            System.out.print("Please Enter the Month and Year for Invoice (Eg. 2018-11) : ");
            String dateEntered = s.nextLine();
            yearEntered = Integer.parseInt(dateEntered.substring(0,4));
            monthEntered = Integer.parseInt(dateEntered.substring(5,7));
        }catch(Exception e){
            System.out.println("\n"+FioreFlowershop.ConsoleColors.RED+"An Error had occurred. Please enter the format as stated."+FioreFlowershop.ConsoleColors.RESET);
            System.out.println(FioreFlowershop.ConsoleColors.BLUE+"\nRedirecting Back to Invoice Maintenance Menu......" + FioreFlowershop.ConsoleColors.RESET);
            generateInvoiceP1();
        }
        if(FioreFlowershop.getShoppingCart() != null){
        System.out.println("\n====================================================");
        System.out.println("\tAvailable Customer For Invoice Generation");
        System.out.println("====================================================");
        for(int i = 1; i <= FioreFlowershop.getShoppingCart().getTotalEntries(); i++){
            //If the shopping cart is not null and status is false
            if(FioreFlowershop.getShoppingCart().getItem(i).getUser()!= null && 
                    !FioreFlowershop.getShoppingCart().getItem(i).isPaymentStatus()){
                //If the entered month and the order month is the same
                if((FioreFlowershop.getShoppingCart().getItem(i).getOrderDate().getMonth()+1) == monthEntered &&
                        (FioreFlowershop.getShoppingCart().getItem(i).getOrderDate().getYear()+1900) == yearEntered){
                    //If duplicate user is found, do nothing
                    if(usern.equals(FioreFlowershop.getShoppingCart().getItem(i).getUser().getEmail())){
                        
                    }else{//No duplicate user is found
                        System.out.println(FioreFlowershop.ConsoleColors.BLUE + "[" + i + "] " + FioreFlowershop.ConsoleColors.RESET
                        + FioreFlowershop.getShoppingCart().getItem(i).getUser().getEmail());
                        usern = FioreFlowershop.getShoppingCart().getItem(i).getUser().getEmail();
                    }
                }else{
                    stat = false;
                }
            }else {
                stat = false;
            }
        }
    }
        if(!stat){
                System.out.println(FioreFlowershop.ConsoleColors.RED+"\nSorry, No Records Found !"+FioreFlowershop.ConsoleColors.RESET);
                invoiceMaintenance();
        }else{
            try{
                System.out.print("\nPlease Enter The Number of Corporate Customer for Invoice Generations : ");
                int choiceCorp = s.nextInt(); s.nextLine();
                String newEmail = FioreFlowershop.getShoppingCart().getItem(choiceCorp).getUser().getEmail();
                generateInvoiceP2(FioreFlowershop.getShoppingCart().getItem(choiceCorp),newEmail);
            }catch(Exception e){
                System.out.println("\n"+FioreFlowershop.ConsoleColors.RED+"\nAn Error had occurred. Please Enter The Number of the Corporate Customer."+FioreFlowershop.ConsoleColors.RESET);
                System.out.println(FioreFlowershop.ConsoleColors.BLUE+"\nRedirecting Back to Invoice Maintenance Menu......" + FioreFlowershop.ConsoleColors.RESET);
                invoiceMaintenance();
            }
                
        }
    }
    
    public static void generateInvoiceP2(CatalogOrders shopping, String email){
        double totalPrice = 0; double discountPrice = 0; int invoiceNum = 100;
        if(shopping.getUser() instanceof CorporateCustomer){
            cc = (CorporateCustomer) shopping.getUser();
            System.out.println("=================================================================================================");
            System.out.println("\nFiore Flowershop SDN.BHD \t\t\t\t\t\t"+ FioreFlowershop.ConsoleColors.BLACK_BOLD +" INVOICE");
            System.out.println("\nQ-5-1, Desa Permai Indah");
            System.out.println("Bandar Gelugor, 11700 Pulau Pinang \t\t\t\t\t" + "INVOICE #["+invoiceNum+"]");
            System.out.println("Phone : 0125566922 \t\t\t\t\t\t\t" + "DATE: " + dateFormat.format(today));

            System.out.println("\nTO:");
            System.out.println("[" + shopping.getUser().getEmail()+ "]");
            System.out.println("[" + cc.getCompany() +"]");
            System.out.println("["+ shopping.getUser().getAddress() +"]");
            System.out.println("[City, ST ZIP Code]");
            System.out.println("[" + shopping.getUser().getPhone() + "]");
            System.out.println("=================================================================================================");
            System.out.println("Description \t\t\t  | Quantity  |  Discount Rate(%) | Unit Price(RM) |  Total(RM)");

            for(int i = 1; i <= FioreFlowershop.getShoppingCart().getTotalEntries() ;i++){
                if(email.equals(shopping.getUser().getEmail()) && !FioreFlowershop.getShoppingCart().getItem(i).isPaymentStatus()){
                    System.out.println(FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getName()+ "\t\t\t  | \t" + FioreFlowershop.getShoppingCart().getItem(i).getItemQuantity()+ "\t|\t" 
                    +FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getDiscountRate()+ "\t    |\t" + FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getPrice() + " \t     |   " + 
                            FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getPrice()*FioreFlowershop.getShoppingCart().getItem(i).getItemQuantity());
                    totalPrice += FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getPrice()*FioreFlowershop.getShoppingCart().getItem(i).getItemQuantity();
                    if(FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getDiscountRate() != 0){
                        discountPrice += (FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getPrice()*FioreFlowershop.getShoppingCart().getItem(i).getItemQuantity()
                                * FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getDiscountRate()) / 100; 
                    }
                }
            }
                System.out.println("\n\n\t\t\t\t\t\t\t Subtotal :\t\t\t " + totalPrice);
                System.out.println("\t\t\t\t\t\t\t Discount :\t\t\t  " + discountPrice);
                System.out.println("\t\t\t\t\t\t\t =========================================");
                System.out.println("\t\t\t\t\t\t\t BALANCE DUE :\t\t\t "+ (totalPrice-discountPrice));
                invoiceMaintenance();
        }
    }
    
    public static void invoicePaymentP1(){//First part for invoice payment
        s.nextLine();String usern = ""; boolean stat = true;int monthEntered = 0; int yearEntered = 0;
        try{
            System.out.print("Please Enter the Month and Year for Invoice (Eg. 2018-11) : ");
            String dateEntered = s.nextLine();
            yearEntered = Integer.parseInt(dateEntered.substring(0,4));
            monthEntered = Integer.parseInt(dateEntered.substring(5,7));
        }catch(Exception e){
            System.out.println("\n"+FioreFlowershop.ConsoleColors.RED+"\nAn Error had occurred. Please enter the format as stated."+FioreFlowershop.ConsoleColors.RESET);
            s.nextLine();invoiceMaintenance();
        }
        if(FioreFlowershop.getShoppingCart() != null){
            System.out.println("\n====================================================");
            System.out.println("\tAvailable Customer For Invoice Payment");
            System.out.println("====================================================");
            for(int i = 1; i <= FioreFlowershop.getShoppingCart().getTotalEntries(); i++){
            //If the shopping cart is not null and status is false
            if(FioreFlowershop.getShoppingCart().getItem(i).getUser()!= null && 
                    !FioreFlowershop.getShoppingCart().getItem(i).isPaymentStatus()){
                //If the entered month and the order month is the same
                if((FioreFlowershop.getShoppingCart().getItem(i).getOrderDate().getMonth()+1) == monthEntered && 
                        (FioreFlowershop.getShoppingCart().getItem(i).getOrderDate().getYear()+1900) == yearEntered){
                    //If duplicate user is found, do nothing
                       if(usern.equals(FioreFlowershop.getShoppingCart().getItem(i).getUser().getEmail())){
                        
                        }else{//No duplicate user is found
                            System.out.println(FioreFlowershop.ConsoleColors.BLUE + "[" + i + "] " + FioreFlowershop.ConsoleColors.RESET
                            + FioreFlowershop.getShoppingCart().getItem(i).getUser().getEmail());
                            usern = FioreFlowershop.getShoppingCart().getItem(i).getUser().getEmail();
                        } 
                }else{
                    stat = false;
                }
            }else {
                stat = false;
            }
        }
        if(!stat){
                System.out.println(FioreFlowershop.ConsoleColors.RED+"\nSorry, No Records Found !"+FioreFlowershop.ConsoleColors.RESET);
                invoiceMaintenance();
        }else{
            try{
                System.out.print("\nPlease Enter The Number of Corporate Customer for Invoice Generations : ");
                int choiceCorp = s.nextInt(); s.nextLine();
                String newEmail = FioreFlowershop.getShoppingCart().getItem(choiceCorp).getUser().getEmail();
                invoicePaymentP2(FioreFlowershop.getShoppingCart().getItem(choiceCorp),newEmail);
            }catch(Exception e){
                System.out.println("\n"+FioreFlowershop.ConsoleColors.RED+"\nAn Error had occurred. Please Enter The Number of the Corporate Customer."+FioreFlowershop.ConsoleColors.RESET);
                invoiceMaintenance();
            }
                
        }
    }else {System.out.println(FioreFlowershop.ConsoleColors.BLUE+ "\nWow, Much Empty for This Flower Shop" + FioreFlowershop.ConsoleColors.RESET);}
        
        //System.out.println("\nPlease Select The Corporate Customer for to make their payment.");
//        if(FioreFlowershop.getShoppingCart() != null){
//            for (int i = 1; i <= FioreFlowershop.getShoppingCart().getTotalEntries() ;i++){
//                //Loop the corporate customer that had not paid
//                if(FioreFlowershop.getShoppingCart().getItem(i).getCorporate()!= null && !FioreFlowershop.getShoppingCart().getItem(i).getPaymentStat()){
//                    //If duplicate user is found, do nothing
//                    if(usern.equals(FioreFlowershop.getShoppingCart().getItem(i).getCorporate().getEmail())){
//                        
//                    }else{//No duplicate user is found
//                        System.out.println(FioreFlowershop.ConsoleColors.BLUE + "[" + i + "]" + FioreFlowershop.ConsoleColors.RESET
//                        + FioreFlowershop.getShoppingCart().getItem(i).getCorporate().getEmail());
//                        usern = FioreFlowershop.getShoppingCart().getItem(i).getCorporate().getEmail();
//                    }
//                }else {
//                    status = false;
//                }
//            }
//            if(!status){//Error message when no records found
//                System.out.println(FioreFlowershop.ConsoleColors.RED+"Sorry, No Records Found !"+FioreFlowershop.ConsoleColors.RESET);
//            }else{
//                System.out.print("\nPlease Enter The Number Listed : "); 
//                int corporateChoice = s.nextInt();//Get the corporate customer choice of the user
//                //Get the email of the selected user
//                String corporateEmail = FioreFlowershop.getShoppingCart().getItem(corporateChoice).getCorporate().getEmail();
//                invoicePaymentP2(FioreFlowershop.getShoppingCart().getItem(corporateChoice), corporateEmail);
//            }
//        } 
    }
    
    public static void invoicePaymentP2(CatalogOrders shopping, String email){//Part 2 of invoice payment
        double affordable = 0; double totalPrice = 0; double discountPrice = 0; int invoiceNum = 100;int credit = 0;
        if(shopping.getUser() instanceof CorporateCustomer){
            cc = (CorporateCustomer) shopping.getUser();
            System.out.println("\n=================================================================================================");
            System.out.println("\nFiore Flowershop SDN.BHD \t\t\t\t\t\t"+ FioreFlowershop.ConsoleColors.BLACK_BOLD +" INVOICE");
            System.out.println("\nQ-5-1, Desa Permai Indah");
            System.out.println("Bandar Gelugor, 11700 Pulau Pinang \t\t\t\t\t" + "INVOICE #["+invoiceNum+"]");
            System.out.println("Phone : 0125566922 \t\t\t\t\t\t\t" + "DATE: " + dateFormat.format(today));

            System.out.println("\nTO:");
            System.out.println("[" + shopping.getUser().getEmail()+ "]");
            System.out.println("[" + cc.getCompany() +"]");
            System.out.println("["+ shopping.getUser().getAddress() +"]");
            System.out.println("[City, ST ZIP Code]");
            System.out.println("[" + shopping.getUser().getPhone() + "]");
            System.out.println("=================================================================================================");
            System.out.println("Description \t\t\t  | Quantity  |  Discount Rate(%) | Unit Price(RM) |  Total(RM)");

            for(int i = 1; i <= FioreFlowershop.getShoppingCart().getTotalEntries() ;i++){
                if(email.equals(shopping.getUser().getEmail()) && !FioreFlowershop.getShoppingCart().getItem(i).isPaymentStatus()){
                    System.out.println(FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getName()+ "\t\t\t  | \t" + FioreFlowershop.getShoppingCart().getItem(i).getItemQuantity()+ "\t|\t" 
                    +FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getDiscountRate()+ "\t    |\t" + FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getPrice() + " \t     |   " + 
                            FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getPrice()*FioreFlowershop.getShoppingCart().getItem(i).getItemQuantity());
                    totalPrice += FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getPrice()*FioreFlowershop.getShoppingCart().getItem(i).getItemQuantity();
                    if(FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getDiscountRate() != 0){
                        discountPrice += (FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getPrice()*FioreFlowershop.getShoppingCart().getItem(i).getItemQuantity()
                                * FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getDiscountRate()) / 100; 
                    }
                }
            }
            //Calculate for corporate customer's affordability
            affordable = cc.getMonthlyLimit()-cc.getCreditSpent();

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
                System.out.println("\n\nCurrent Corporate Customer's Credit Limit: " + cc.getMonthlyLimit());
                System.out.println("Current Corporate Customer's Affordable Limit: " + FioreFlowershop.ConsoleColors.BLUE +affordable + FioreFlowershop.ConsoleColors.RESET);
                System.out.println("After Payment Balance: "+ FioreFlowershop.ConsoleColors.RED +(affordable-(totalPrice-discountPrice))+ FioreFlowershop.ConsoleColors.RESET);
                s.nextLine();
                System.out.print("\nDo you wish to make this payment? [Press Enter for Yes]");
                String enter = s.nextLine();
                if(enter.isEmpty()){
                    //Set corporate customer's payment status to true
                    for(int i = 1; i <= FioreFlowershop.getCorporate().getTotalEntries(); i++){
                        if(FioreFlowershop.getCorporate().getItem(i).getEmail().equals(email)){
                            FioreFlowershop.getCorporate().getItem(i).setPaymentStatus(true);
                            //Set corporate customer's credit spent back to 0
                            FioreFlowershop.getCorporate().getItem(i).setCreditSpent(0);
                            //credit += FioreFlowershop.getCorporate().getItem(i).getCreditSpent() + (totalPrice-discountPrice);
                        }
                    }
                        //Set shopping cart payment status to true
                    for(int k = 1; k <= FioreFlowershop.getShoppingCart().getTotalEntries(); k++){
                        if(FioreFlowershop.getShoppingCart().getItem(k).getUser().getEmail().equals(email)){
      //                      FioreFlowershop.getShoppingCart().getItem(k).isPaymentStatus(true);
                        }
                    }
                        invoiceNum++;
                        System.out.println(FioreFlowershop.ConsoleColors.GREEN+"\nPayment Success, Thanks for the Patronage :D "+ FioreFlowershop.ConsoleColors.RESET);
                        System.out.println(FioreFlowershop.ConsoleColors.BLUE+"Redirecting Back to User Selection Menu......" + FioreFlowershop.ConsoleColors.RESET);
                        FioreFlowershop.userTypeSelection();
                }else {//Payment Cancel by user
                    System.out.println(FioreFlowershop.ConsoleColors.RED+"\nPayment Cancelled, Redirecting back to Main Menu."+FioreFlowershop.ConsoleColors.RESET);
                    FioreFlowershop.counterStaff();
                }
            }
        }
    }
}
