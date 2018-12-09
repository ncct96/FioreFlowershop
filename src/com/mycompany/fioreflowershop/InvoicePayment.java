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
import com.mycompany.fioreflowershop.modal.InvoiceHistory;
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
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static InvoiceHistory ih;
    private static ListInterface<InvoiceHistory> paymentHistory = new LinkedList<>();
    private static int invoiceNumber = 100;
    
    public static void invoiceMaintenance(){//Selection of menus
        
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] Make Corporate Customer Invoice Payment.");
        System.out.println("[2] Generate Invoice Payment for Corporate Customer.");
        System.out.println("[3] View Paid Invoice History");
        System.out.println("[4] Back to Main Menu.");
        
        try{
            int invoiceChoice = s.nextInt(); s.nextLine();
            switch(invoiceChoice){
                case 1:invoicePaymentP1();break;
                case 2:generateInvoiceP1();break; 
                case 3:viewPaymentHistory1();break; 
                case 4:FioreFlowershop.counterStaff();break;
                case 5:receipt();break;
            }
        }catch (Exception e){
            System.out.println("\n"+FioreFlowershop.ConsoleColors.RED+"An Error had occurred. Please Enter The Numbers Stated"+FioreFlowershop.ConsoleColors.RESET);
            System.out.println(FioreFlowershop.ConsoleColors.BLUE+"\nRedirecting Back to Invoice Maintenance Menu......" + FioreFlowershop.ConsoleColors.RESET);
            invoiceMaintenance();
        }
    }
    
    public static void receipt(){//This is for nicholas
        System.out.println("\n\t\t   Fiore Flowershop SDN.NHD ");
        System.out.println("\t\t    2404 Aaron Smith Drive");
        System.out.println("\t\t 2404 Pennsylvania, 17404 York");
        
        System.out.println("\nID : [SAMPLE ID HERE]");
        System.out.println("Date : [TODAY DATE]");
        System.out.println("Cashier : Admin");
        System.out.println("Time : [TIME FOR PAYMENT]");
        System.out.println("=================================================================");
        System.out.println("ITEM \t\t\t QUANTITY \t PRICE \t\t AMOUNT");
        System.out.println("=================================================================");
        
        /*ITEM SOLD LISTING GOES HERE*/
        
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("\tTOTAL : \t    5 \t\t\t\t 300.00");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("CASH : \t\t\t\t\t300.00");
        System.out.println("CASH TENDERED : \t\t\t500.00");
        System.out.println("BALANCE : \t\t\t\t200.00");
        System.out.println("\n=================================================================");
        System.out.println("Thank You For Choosing Fiore Flowershop, Please Come Again :D");
        System.out.println("=================================================================");
    }
    
    public static void viewPaymentHistory1(){
        String invoiceID = ""; int count = 1; boolean stat = true;
        System.out.println("\n====================================================");
        System.out.println("\tAvailable Paid Invoice(s)");
        System.out.println("====================================================");
        if(paymentHistory != null){
            for(int i = 1; i <= paymentHistory.getTotalEntries(); i++){
                if(invoiceID.equals(paymentHistory.getItem(i).getInvoiceNumber())){
                
                }else{
                    invoiceID = paymentHistory.getItem(i).getInvoiceNumber();
                    System.out.println(FioreFlowershop.ConsoleColors.BLUE+"["+count+"] " + FioreFlowershop.ConsoleColors.RESET
                    + invoiceID);
                }
            }
            try{
                System.out.print("\n" + "Please Enter The Invoice Number ID : ");
                String enteredID = s.nextLine();
                for(int a = 1; a <= paymentHistory.getTotalEntries(); a++){
                    if(paymentHistory.getItem(a).getInvoiceNumber().equals(enteredID)){
                        ih =  paymentHistory.getItem(a);
                        stat = true;
                        break;
                    }else{
                        stat = false;
                    }
                }
                if(stat){
                   viewPaymentHistory2(ih,enteredID); 
                }else{
                    System.out.println(FioreFlowershop.ConsoleColors.RED+"\nPlease Enter A Valid Invoice Number, Try Again !"+FioreFlowershop.ConsoleColors.RESET);
                    invoiceMaintenance();
                }
            }catch(Exception e){
                System.out.println("\n"+FioreFlowershop.ConsoleColors.RED+"An Error had occurred. Please enter the format as stated."+FioreFlowershop.ConsoleColors.RESET);
                System.out.println(FioreFlowershop.ConsoleColors.BLUE+"\nRedirecting Back to Invoice Maintenance Menu......" + FioreFlowershop.ConsoleColors.RESET);
                invoiceMaintenance();
            }
            
        }else {
            System.out.println(FioreFlowershop.ConsoleColors.RED+"\nSorry, No Records Found !"+FioreFlowershop.ConsoleColors.RESET);
            invoiceMaintenance();
        }
    }
    
    public static void viewPaymentHistory2(InvoiceHistory ih, String invoiceID){
        double discountPrice = 0; double totalPrice = 0;
        System.out.println("\n=================================================================================================");
        System.out.println("\nFiore Flowershop SDN.BHD \t\t\t\t\t\t"+ FioreFlowershop.ConsoleColors.BLACK_BOLD +" INVOICE");
        System.out.println("\nQ-5-1, Desa Permai Indah \t\t\t\t\t\t"+" STATUS : PAID");
        System.out.println("Bandar Gelugor, 11700 Pulau Pinang \t\t\t\t\t" + "INVOICE #["+invoiceID+"]");
        System.out.println("Phone : 0125566922 \t\t\t\t\t\t\t" + "DATE: " + dateFormat.format(ih.getDatepay()));

        System.out.println("\nTO:");
        System.out.println("[" + ih.getCorp().getEmail()+ "]");
        System.out.println("[" + ih.getCorp().getCompany() +"]");
        System.out.println("["+ ih.getCorp().getAddress() +"]");
        System.out.println("[City, ST ZIP Code]");
        System.out.println("[" + ih.getCorp().getPhone() + "]");
        System.out.println("==================================================================================================================");
        System.out.println("Date Ordered | Description \t\t\t  | Quantity    |  Discount Rate(%) | Unit Price(RM) |  Total(RM)");
        System.out.println("==================================================================================================================");
        for(int i = 1; i <= paymentHistory.getTotalEntries(); i++){
            if(paymentHistory.getItem(i).getInvoiceNumber().equals(invoiceID)){
                System.out.println(sdf.format(paymentHistory.getItem(i).getCatalogOrder().getOrderDate())+"   | "
                        + paymentHistory.getItem(i).getCatalogOrder().getCatalogPack().getName()+ "\t\t\t  | \t" 
                        + paymentHistory.getItem(i).getCatalogOrder().getItemQuantity()+ "\t|\t" 
                        + paymentHistory.getItem(i).getCatalogOrder().getCatalogPack().getDiscountRate()+ "\t    |\t" 
                        + paymentHistory.getItem(i).getCatalogOrder().getCatalogPack().getPrice() + " \t     |   " 
                        + paymentHistory.getItem(i).getCatalogOrder().getCatalogPack().getPrice()*paymentHistory.getItem(i).getCatalogOrder().getItemQuantity());
                totalPrice += paymentHistory.getItem(i).getCatalogOrder().getCatalogPack().getPrice()*paymentHistory.getItem(i).getCatalogOrder().getItemQuantity();
                if(FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getDiscountRate() != 0){
                    discountPrice += (paymentHistory.getItem(i).getCatalogOrder().getCatalogPack().getPrice()*paymentHistory.getItem(i).getCatalogOrder().getItemQuantity()
                                * paymentHistory.getItem(i).getCatalogOrder().getCatalogPack().getDiscountRate()) / 100;
                }
            }
        }
                System.out.println("\n\n\t\t\t\t\t\t\t\t\t Subtotal :\t\t\t " + totalPrice);
                System.out.println("\t\t\t\t\t\t\t\t\t Discount :\t\t\t  " + discountPrice);
                System.out.println("\t\t\t\t\t\t\t\t\t =========================================");
                System.out.println("\t\t\t\t\t\t\t\t\t BALANCE DUE :\t\t\t "+ (totalPrice-discountPrice));
                System.out.println("\n");
        invoiceMaintenance();
    }
    
    public static void generateInvoiceP1(){
        String usern = ""; boolean stat = true;int yearEntered = 0;int monthEntered = 0;int count = 0;
        System.out.println("\n====================================================");
        System.out.println("\t Invoice Generation");
        System.out.println("======================================================");
        try{
            System.out.print("\nPlease Enter the Month and Year for Invoice (Eg. 2018-11) : ");
            String dateEntered = s.nextLine();
            yearEntered = Integer.parseInt(dateEntered.substring(0,4));
            monthEntered = Integer.parseInt(dateEntered.substring(5,7));
        }catch(Exception e){
            System.out.println("\n"+FioreFlowershop.ConsoleColors.RED+"An Error had occurred. Please enter the format as stated."+FioreFlowershop.ConsoleColors.RESET);
            System.out.println(FioreFlowershop.ConsoleColors.BLUE+"\nRedirecting Back to Invoice Maintenance Menu......" + FioreFlowershop.ConsoleColors.RESET);
            invoiceMaintenance();
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
                        count++;
                    }
                }
            }
        }
    }
        if(count <= 0){
                System.out.println(FioreFlowershop.ConsoleColors.RED+"\nSorry, No Records Found !"+FioreFlowershop.ConsoleColors.RESET);
                invoiceMaintenance();
        }else{
            try{
                int choiceCorp = 0; 
                System.out.print("\nPlease Enter The Email of Corporate Customer for Invoice Generations : ");
                String emailCorp = s.nextLine();
                for(int a = 1; a<=FioreFlowershop.getCorporate().getTotalEntries();a++){
                    if(FioreFlowershop.getCorporate().getItem(a).getEmail().equals(emailCorp)){
                        choiceCorp = a;
                        break;
                    }
                }
                generateInvoiceP2(FioreFlowershop.getCorporate().getItem(choiceCorp));
            }catch(Exception e){
                System.out.println("\n"+FioreFlowershop.ConsoleColors.RED+"\nAn Error had occurred. Please Enter The Number of the Corporate Customer."+FioreFlowershop.ConsoleColors.RESET);
                System.out.println(FioreFlowershop.ConsoleColors.BLUE+"\nRedirecting Back to Invoice Maintenance Menu......" + FioreFlowershop.ConsoleColors.RESET);
                invoiceMaintenance();
            }
                
        }
    }
    
    public static void generateInvoiceP2(CorporateCustomer user){
        double totalPrice = 0; double discountPrice = 0;
        if(user instanceof CorporateCustomer){
            cc = (CorporateCustomer) user;
            System.out.println("=================================================================================================");
            System.out.println("\nFiore Flowershop SDN.BHD \t\t\t\t\t\t"+ FioreFlowershop.ConsoleColors.BLACK_BOLD +" INVOICE");
            System.out.println("\nQ-5-1, Desa Permai Indah");
            System.out.println("Bandar Gelugor, 11700 Pulau Pinang \t\t\t\t\t" + "INVOICE #["+invoiceNumber+"]");
            System.out.println("Phone : 0125566922 \t\t\t\t\t\t\t" + "DATE: " + dateFormat.format(today));

            System.out.println("\nTO:");
            System.out.println("[" + user.getEmail()+ "]");
            System.out.println("[" + cc.getCompany() +"]");
            System.out.println("["+ user.getAddress() +"]");
            System.out.println("[City, ST ZIP Code]");
            System.out.println("[" + user.getPhone() + "]");
            System.out.println("===============================================================================================================");
            System.out.println("Date Ordered | Description \t\t\t  | Quantity    |  Discount Rate(%) | Unit Price(RM) |  Total(RM)");

            for(int i = 1; i <= FioreFlowershop.getShoppingCart().getTotalEntries() ;i++){
                if(FioreFlowershop.getShoppingCart().getItem(i).getUser().equals(user) && !FioreFlowershop.getShoppingCart().getItem(i).isPaymentStatus()){
                    System.out.println(sdf.format(FioreFlowershop.getShoppingCart().getItem(i).getOrderDate())+"   | "+FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getName()+ "\t\t\t  | \t" + FioreFlowershop.getShoppingCart().getItem(i).getItemQuantity()+ "\t|\t" 
                    +FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getDiscountRate()+ "\t    |\t" + FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getPrice() + " \t     |   " + 
                            FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getPrice()*FioreFlowershop.getShoppingCart().getItem(i).getItemQuantity());
                    totalPrice += FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getPrice()*FioreFlowershop.getShoppingCart().getItem(i).getItemQuantity();
                    if(FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getDiscountRate() != 0){
                        discountPrice += (FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getPrice()*FioreFlowershop.getShoppingCart().getItem(i).getItemQuantity()
                                * FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getDiscountRate()) / 100;
                    }
                }
            }
                System.out.println("\n\n\t\t\t\t\t\t\t\t\t Subtotal :\t\t\t " + totalPrice);
                System.out.println("\t\t\t\t\t\t\t\t\t Discount :\t\t\t  " + discountPrice);
                System.out.println("\t\t\t\t\t\t\t\t\t =========================================");
                System.out.println("\t\t\t\t\t\t\t\t\t BALANCE DUE :\t\t\t "+ (totalPrice-discountPrice));
                invoiceMaintenance();
        }
    }
    
    public static void invoicePaymentP1(){//First part for invoice payment
        String usern = "";int monthEntered = 0; int yearEntered = 0;int count = 0;
        System.out.println("\n====================================================");
        System.out.println("\t Invoice Payment");
        System.out.println("======================================================");
        try{
            System.out.print("\nPlease Enter the Month and Year for Invoice (Eg. 2018-11) : ");
            String dateEntered = s.nextLine();
            yearEntered = Integer.parseInt(dateEntered.substring(0,4));
            monthEntered = Integer.parseInt(dateEntered.substring(5,7));
        }catch(Exception e){
            System.out.println("\n"+FioreFlowershop.ConsoleColors.RED+"\nAn Error had occurred. Please enter the format as stated."+FioreFlowershop.ConsoleColors.RESET);
            invoiceMaintenance();
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
                            count++;
                        } 
                }
            }
        }
        if(count <= 0){
                System.out.println(FioreFlowershop.ConsoleColors.RED+"\nSorry, No Records Found !"+FioreFlowershop.ConsoleColors.RESET);
                invoiceMaintenance();
        }else{
            try{
                System.out.print("\nPlease Enter The Email of Corporate Customer for Invoice Generations : ");
                int choiceCorp = 0; String emailCorp = s.nextLine();
                for(int a = 1; a<=FioreFlowershop.getUser().getTotalEntries();a++){
                    if(FioreFlowershop.getUser().getItem(a).getEmail().equals(emailCorp)){
                        choiceCorp = a;
                        break;
                    }
                }
                invoicePaymentP2(FioreFlowershop.getUser().getItem(choiceCorp));
            }catch(Exception e){
                System.out.println("\n"+FioreFlowershop.ConsoleColors.RED+"\nAn Error had occurred. Please Enter The Number of the Corporate Customer."+FioreFlowershop.ConsoleColors.RESET);
                invoiceMaintenance();
            }
                
        }
    }else {System.out.println(FioreFlowershop.ConsoleColors.BLUE+ "\nWow, Much Empty for This Flower Shop" + FioreFlowershop.ConsoleColors.RESET);}
}
    
    public static void invoicePaymentP2(User user){//Part 2 of invoice payment
        double affordable = 0; double totalPrice = 0; double discountPrice = 0; int credit = 0;
        if(user instanceof CorporateCustomer){
            cc = (CorporateCustomer) user;
            System.out.println("\n=================================================================================================");
            System.out.println("\nFiore Flowershop SDN.BHD \t\t\t\t\t\t"+ FioreFlowershop.ConsoleColors.BLACK_BOLD +" INVOICE");
            System.out.println("\nQ-5-1, Desa Permai Indah");
            System.out.println("Bandar Gelugor, 11700 Pulau Pinang \t\t\t\t\t" + "INVOICE #["+invoiceNumber+"]");
            System.out.println("Phone : 0125566922 \t\t\t\t\t\t\t" + "DATE: " + dateFormat.format(today));

            System.out.println("\nTO:");
            System.out.println("[" + user.getEmail()+ "]");
            System.out.println("[" + cc.getCompany() +"]");
            System.out.println("["+ user.getAddress() +"]");
            System.out.println("[City, ST ZIP Code]");
            System.out.println("[" + user.getPhone() + "]");
            System.out.println("===============================================================================================================");
            System.out.println("Date Ordered | Description \t\t\t  | Quantity    |  Discount Rate(%) | Unit Price(RM) |  Total(RM)");

            for(int i = 1; i <= FioreFlowershop.getShoppingCart().getTotalEntries() ;i++){
                if(FioreFlowershop.getShoppingCart().getItem(i).getUser().equals(user) && !FioreFlowershop.getShoppingCart().getItem(i).isPaymentStatus()){
                    System.out.println(sdf.format(FioreFlowershop.getShoppingCart().getItem(i).getOrderDate())+"   | "
                            +FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getName()+ "\t\t\t  | \t" 
                            + FioreFlowershop.getShoppingCart().getItem(i).getItemQuantity()+ "\t|\t" 
                    +FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getDiscountRate()+ "\t    |\t" 
                            + FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getPrice() + " \t     |   " 
                            +FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getPrice()*FioreFlowershop.getShoppingCart().getItem(i).getItemQuantity());
                    totalPrice += FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getPrice()*FioreFlowershop.getShoppingCart().getItem(i).getItemQuantity();
                    if(FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getDiscountRate() != 0){
                        discountPrice += (FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getPrice()*FioreFlowershop.getShoppingCart().getItem(i).getItemQuantity()
                                * FioreFlowershop.getShoppingCart().getItem(i).getCatalogPack().getDiscountRate()) / 100; 
                    }
                }
            }
            //Calculate for corporate customer's affordability
            affordable = cc.getMonthlyLimit()-cc.getCreditSpent();

                System.out.println("\n\n\t\t\t\t\t\t\t\t\t Subtotal :\t\t\t " + totalPrice);
                System.out.println("\t\t\t\t\t\t\t\t\t Discount :\t\t\t  " + discountPrice);
                System.out.println("\t\t\t\t\t\t\t\t\t =========================================");
                System.out.println("\t\t\t\t\t\t\t\t\t BALANCE DUE :\t\t\t "+ (totalPrice-discountPrice));

            if(affordable <= 0){//Credit limit reached
                System.out.println("\n"+FioreFlowershop.ConsoleColors.RED + "Sorry, looks like this customer's Credit Limit have exceeded. Please make payment right now.");
                for(int i = 1; i <= FioreFlowershop.getCorporate().getTotalEntries(); i++){
                    if(FioreFlowershop.getCorporate().getItem(i).getEmail().equals(user.getEmail())){
                        FioreFlowershop.getCorporate().getItem(i).setPaymentStatus(false);
                    }
                }
            } else if(affordable <= totalPrice){//Not enough money
                System.out.println("\n"+FioreFlowershop.ConsoleColors.RED + "Sorry, looks like this customer can't afford the items." + FioreFlowershop.ConsoleColors.RESET);
                System.out.println(FioreFlowershop.ConsoleColors.RED + "Current Affordable Balance : "+affordable+ FioreFlowershop.ConsoleColors.RESET);
                for(int i = 1; i <= FioreFlowershop.getCorporate().getTotalEntries(); i++){
                    if(FioreFlowershop.getCorporate().getItem(i).getEmail().equals(user.getEmail())){
                        FioreFlowershop.getCorporate().getItem(i).setPaymentStatus(false);
                    }
                }
            }else {//Normal Transaction carried out
                System.out.println("\n\nCurrent Corporate Customer's Credit Limit: " + cc.getMonthlyLimit());
                System.out.println("Current Corporate Customer's Affordable Limit: " + FioreFlowershop.ConsoleColors.BLUE +affordable + FioreFlowershop.ConsoleColors.RESET);
                System.out.println("After Payment Balance: "+ FioreFlowershop.ConsoleColors.RED +(affordable-(totalPrice-discountPrice))+ FioreFlowershop.ConsoleColors.RESET);
                System.out.print("\nDo you wish to make this payment? [Press Enter for Yes]");
                String enter = s.nextLine();
                if(enter.isEmpty()){
                    //Set corporate customer's payment status to true
                    for(int i = 1; i <= FioreFlowershop.getCorporate().getTotalEntries(); i++){
                        if(FioreFlowershop.getCorporate().getItem(i).equals(user)){
                            FioreFlowershop.getCorporate().getItem(i).setPaymentStatus(true);
                            //Set corporate customer's credit spent back to 0
                            FioreFlowershop.getCorporate().getItem(i).setCreditSpent(0);
                            //credit += FioreFlowershop.getCorporate().getItem(i).getCreditSpent() + (totalPrice-discountPrice);
                        }
                    }
                        //Set shopping cart payment status to true
                    for(int k = 1; k <= FioreFlowershop.getShoppingCart().getTotalEntries(); k++){
                        if(FioreFlowershop.getShoppingCart().getItem(k).getUser().equals(user)){
                            FioreFlowershop.getShoppingCart().getItem(k).setPaymentStatus(true);
                        }
                    }
                    //Store paid invoice into an invoice link list
                    for(int l = 1;l <= FioreFlowershop.getShoppingCart().getTotalEntries(); l++){
                        if(FioreFlowershop.getShoppingCart().getItem(l).getUser().equals(user)){
                            paymentHistory.add(new InvoiceHistory(invoiceNumber,FioreFlowershop.getShoppingCart().getItem(l), cc, today));
                        }
                    }
                        ++invoiceNumber; 
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
