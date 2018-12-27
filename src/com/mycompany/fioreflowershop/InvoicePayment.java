/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.adt.*;
import com.mycompany.fioreflowershop.modal.CatalogOrders;
import com.mycompany.fioreflowershop.modal.CatalogPackage;
import com.mycompany.fioreflowershop.modal.CorporateCustomer;
import com.mycompany.fioreflowershop.modal.Consumer;
import com.mycompany.fioreflowershop.modal.InvoiceHistory;
import com.mycompany.fioreflowershop.modal.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
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
    private static InvoiceInterface<InvoiceHistory> paymentHistory = new InvoiceADT<>();
    private static OrderListInterface<CatalogOrders> tempCatalog = new OrderList<>();
    private static ListInterface<String> userEmail = new LinkedList<>();
    private static OrderListInterface<CatalogOrders> order = FioreFlowershop.getCatalogOrder();
    private static Iterator<CatalogOrders> catIterator = order.getIterator();
    private static int invoiceNumber = 100;
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";
    public static final String RED = "\033[0;31m"; 
    public static final String BLUE = "\033[0;34m"; 
    public static final String BLACK_BOLD = "\033[1;30m";
    
    public static InvoiceInterface<InvoiceHistory> getPaymentHistory(){
        return paymentHistory;
    }
    
    public static void invoiceMaintenance(){//Selection of menus
        while(true){
            System.out.println("\nPlease Select The Options Below.");
            System.out.println(GREEN+"[1]"+RESET+" Make Corporate Customer Invoice Payment.");
            System.out.println(GREEN+"[2]"+RESET+" Generate Invoice Payment for Corporate Customer.");
            System.out.println(GREEN+"[3]"+RESET+" View Paid Invoice History");
            System.out.println(GREEN+"[4]"+RESET+" Back to Main Menu.");
            System.out.print("Selection : ");
            try{
                int invoiceChoice = s.nextInt(); s.nextLine(); 
                switch(invoiceChoice){
                    case 1:invoicePaymentP1();break;
                    case 2:generateInvoiceP1();break; 
                    case 3:viewPaymentHistory1();break; 
                    case 4:FioreFlowershop.counterStaff();break;
                }
            }catch (Exception e){
                System.out.println("\n"+RED+"An Error had occurred. Please Enter The Numbers Stated"+RESET);
                System.out.println(BLUE+"\nRedirecting Back to Invoice Maintenance Menu......" + RESET);
                break;
            }
        }
    }
    
    public static double totalPrice(double price, double quantity){
        double total = 0;
        return total = price*quantity;
    }
    
    public static double discountPrice(double price, double quantity, double discountRate){
        double discount = 0;
        return discount = (price * quantity) * (discountRate/100);
    }
    
    public static void viewPaymentHistory1(){//For viewing payment history, retrieve available paid invoices first
        String invoiceID = ""; int count = 1; boolean stat = true;
        while(true){
            System.out.println("\n====================================================");
            System.out.println("\tAvailable Paid Invoice(s)");
            System.out.println("====================================================");
            if(paymentHistory.getTotalInvoice() != 0){
                for(int i = 1; i <= paymentHistory.getTotalInvoice(); i++){//Get all available paid invoices, and display it 
                    if(invoiceID.equals(paymentHistory.getInvoice(i).getInvoiceNumber())){
                    //If duplicates of invoice ID is found, do nothing
                    }else{//If invoice ID is found, print out the id, then store the ID to another variable for checking for duplicates.
                        invoiceID = paymentHistory.getInvoice(i).getInvoiceNumber();
                        System.out.println(BLUE+"["+count+"] " + RESET
                        + invoiceID);
                    }
                }
                try{//For user to enter their desired invoice number
                    System.out.print("\n" + "Please Enter The Invoice Number ID : ");
                    String enteredID = s.nextLine();
                    for(int a = 1; a <= paymentHistory.getTotalInvoice(); a++){
                        if(paymentHistory.getInvoice(a).getInvoiceNumber().equals(enteredID)){
                            ih =  paymentHistory.getInvoice(a);
                            stat = true;
                            break;
                        }else{
                            stat = false;
                        }
                    }
                    if(stat){//Pass to 2nd part of view paid invoice
                       viewPaymentHistory2(ih,enteredID); 
                    }else{//If invalid invoice number is entered, show error message
                        System.out.println(RED+"\nPlease Enter A Valid Invoice Number, Try Again !"+RESET);
    //                    invoiceMaintenance();
                    }
                }catch(Exception e){//If invalid format for invoice number is entered, show error message
                    System.out.println("\n"+RED+"An Error had occurred. Please enter the format as stated."+RESET);
                    System.out.println(BLUE+"\nRedirecting Back to Invoice Maintenance Menu......" + RESET);
    //                invoiceMaintenance();
                    break;
                }
            }else {
                System.out.println(RED+"\nSorry, No Records Found !"+RESET);
    //            invoiceMaintenance();
                break;
            }
            break;
        }
    }
    
    public static void viewPaymentHistory2(InvoiceHistory ih, String invoiceID){
        double discountPrice = 0; double totalPrice = 0;
        System.out.println("\n===============================================================================================================");
        System.out.println("\nFiore Flowershop SDN.BHD \t\t\t\t\t\t"+ BLACK_BOLD +" INVOICE");
        System.out.println("\nQ-5-1, Desa Permai Indah \t\t\t\t\t\t"+"STATUS : PAID");
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
        for(int i = 1; i <= paymentHistory.getTotalInvoice(); i++){
            if(paymentHistory.getInvoice(i).getInvoiceNumber().equals(invoiceID)){
                //for(int k = 1; k <= paymentHistory.getItem(i).getCatalogOrder().getTotalEntries(); k++){
                    for(int p = 1; p <= paymentHistory.getInvoice(i).getCatalogOrder().getOrder(i).getCatalogPack().getTotalEntries(); p++){
                        Date orderDate = paymentHistory.getInvoice(i).getCatalogOrder().getOrder(i).getOrderDate();
                        CatalogPackage orderInstance = paymentHistory.getInvoice(i).getCatalogOrder().getOrder(i).getCatalogPack().getProduct(p);
//                        System.out.println(sdf.format(orderDate)+"   | "+ orderInstance.getName()+ "\t\t\t  | \t" 
//                        + orderInstance.getUserQuantity()+ "\t|\t" + orderInstance.getDiscountRate()+ "\t    |\t" 
//                        + orderInstance.getPrice() + " \t     |   " + orderInstance.getPrice() *orderInstance.getUserQuantity());
//                        totalPrice += totalPrice(orderInstance.getPrice() , orderInstance.getUserQuantity());
//                        if(orderInstance.getDiscountRate() != 0){
//                            discountPrice += discountPrice(orderInstance.getPrice(), orderInstance.getUserQuantity(), orderInstance.getDiscountRate());
//                        }
                    }
                //}
            }
        }
        invoiceMenuFooter(totalPrice, discountPrice);
        System.out.println("\n");
        invoiceMaintenance();
    }
    
    public static void generateInvoiceP1(){
        int yearEntered = 0; int monthEntered = 0; int count = 0; boolean status = true;
        while (true){  
            System.out.println("\n====================================================");
            System.out.println("\t Invoice Generation");
            System.out.println("======================================================");
            try{
                System.out.print("\nPlease Enter the Month and Year for Invoice (Eg. 2018-11) : ");
                String dateEntered = s.nextLine();
                yearEntered = Integer.parseInt(dateEntered.substring(0,4));
                monthEntered = Integer.parseInt(dateEntered.substring(5,7));
            }catch(Exception e){
                System.out.println("\n"+RED+"An Error had occurred. Please enter the format as stated."+RESET);
                System.out.println(BLUE+"\nRedirecting Back to Invoice Maintenance Menu......" + RESET);
                break;
            }
            if(order.getTotalEntries() != 0){
            System.out.println("\n====================================================");
            System.out.println("\tAvailable Customer For Invoice Generation");
            System.out.println("====================================================");
            for(int i = 1; i <= order.getTotalEntries(); i++){
                //If the shopping cart is not null and status is false
                if(order.getOrder(i).getUser()!= null && !order.getOrder(i).isPaymentStatus()){
                    //If the entered month and the order month is the same
                    if((order.getOrder(i).getOrderDate().getMonth()+1) == monthEntered &&
                            (order.getOrder(i).getOrderDate().getYear()+1900) == yearEntered){
                        if(userEmail.getTotalEntries() != 0){
                                for(int k = 1; k <= userEmail.getTotalEntries(); k++){
                                    if(userEmail.getItem(k).equals(order.getOrder(i).getUser().getEmail())){
                                        status = false;
                                        break;
                                    }else {
                                        status = true;
                                    }
                                }
                                if(status){
                                    if(order.getOrder(i).getUser() instanceof CorporateCustomer){
                                        String email = order.getOrder(i).getUser().getEmail();
                                        userEmail.add(email);
                                    }
                                }
                            }else{
                                String email = order.getOrder(i).getUser().getEmail();
                                userEmail.add(email);
                            }
                    }
                }
            }
            for(int j = 1; j <= userEmail.getTotalEntries(); j++){
                    System.out.println(BLUE + "[" + j + "] " + RESET
                    + userEmail.getItem(j));
                    count++;
                }
        }
            if(count <= 0){
                    System.out.println(RED+"\nSorry, No Records Found !"+RESET);
                    invoiceMaintenance();
            }else{
                try{
                    int choiceCorp = 0; 
                    System.out.print("\nPlease Enter The Email of Corporate Customer for Invoice Generations : ");
                    String emailCorp = s.nextLine();
                    for(int a = 1; a<=FioreFlowershop.getCorporateList().getTotalCorporate();a++){
                        if(FioreFlowershop.getCorporateList().getCorporate(a).getEmail().equals(emailCorp)){
                            choiceCorp = a;
                            break;
                        }
                    }
                    generateInvoiceP2(FioreFlowershop.getCorporateList().getCorporate(choiceCorp));
                }catch(Exception e){
                    System.out.println("\n"+RED+"\nAn Error had occurred. Please Enter The Number of the Corporate Customer."+RESET);
                    System.out.println(BLUE+"\nRedirecting Back to Invoice Maintenance Menu......" + RESET);
//                    invoiceMaintenance();
                    break;
                }

            }
        }
    }
    
    public static void invoiceMenu(User user){
        if(user instanceof CorporateCustomer){
            cc = (CorporateCustomer) user;
            System.out.println("===============================================================================================================");
            System.out.println("\nFiore Flowershop SDN.BHD \t\t\t\t\t\t"+ BLACK_BOLD +" INVOICE");
            System.out.println("\nQ-5-1, Desa Permai Indah");
            System.out.println("Bandar Gelugor, 11700 Pulau Pinang \t\t\t\t\t" + "INVOICE #["+invoiceNumber+"]");
            System.out.println("Phone : 0125566922 \t\t\t\t\t\t\t" + "DATE: " + dateFormat.format(today));

            System.out.println("\nTO:");
            System.out.println("[" + cc.getEmail()+ "]");
            System.out.println("[" + cc.getCompany() +"]");
            System.out.println("["+ cc.getAddress() +"]");
            System.out.println("[City, ST ZIP Code]");
            System.out.println("[" + cc.getPhone() + "]");
            System.out.println("===============================================================================================================");
            System.out.println("Date Ordered | Description \t\t\t  | Quantity    |  Discount Rate(%) | Unit Price(RM) |  Total(RM)");
        }
    }
    
    public static void invoiceMenuFooter(double totalPrice, double discountPrice){
        System.out.println("\n\n\t\t\t\t\t\t\t\t\t Subtotal :\t\t\t " + totalPrice);
        System.out.println("\t\t\t\t\t\t\t\t\t Total Discount :\t\t  " + discountPrice);
        System.out.println("\t\t\t\t\t\t\t\t\t =========================================");
        System.out.println("\t\t\t\t\t\t\t\t\t BALANCE DUE :\t\t\t "+ (totalPrice-discountPrice));
        totalPrice = 0; discountPrice = 0;
    }
    
    public static void generateInvoiceP2(User user){
        double totalPrice = 0; double discountPrice = 0;
        invoiceMenu(user);
        for(int i = 1; i <= order.getTotalEntries() ;i++){
            if(order.getOrder(i).getUser().getEmail().equals(user.getEmail()) && !order.getOrder(i).isPaymentStatus()){
                for(int k = 1; k <= order.getOrder(i).getCatalogPack().getTotalEntries(); k++){
                    if(order.getOrder(k).getCatalogPack() != null){
                        CatalogPackage orderInstance = order.getOrder(i).getCatalogPack().getProduct(k);
                        Date orderDate = order.getOrder(k).getOrderDate();
//                        System.out.println(sdf.format(orderDate)+"   | "
//                        +orderInstance.getName()+ "\t\t\t  | \t" 
//                        +orderInstance.getUserQuantity()+ "\t|\t" 
//                        +orderInstance.getDiscountRate()+ "\t    |\t" 
//                        +orderInstance.getPrice() + " \t     |   " 
//                        +orderInstance.getPrice()
//                        *orderInstance.getUserQuantity());
//                        totalPrice += totalPrice(orderInstance.getPrice(), orderInstance.getUserQuantity());
//                        if(orderInstance.getDiscountRate() != 0){
//                            discountPrice += discountPrice(orderInstance.getPrice(), orderInstance.getUserQuantity(), orderInstance.getDiscountRate());
//                        }  
                    }
                }
            }
        }
            invoiceMenuFooter(totalPrice, discountPrice);
            invoiceMaintenance();
    }
    
    public static void invoicePaymentP1(){//First part for invoice payment
        String usern = "";int monthEntered = 0; int yearEntered = 0;int count = 0;
        while (true){
            System.out.println("\n====================================================");
            System.out.println("\t Invoice Payment");
            System.out.println("======================================================");
            try{
                System.out.print("\nPlease Enter the Month and Year for Invoice (Eg. 2018-11) : ");
                String dateEntered = s.nextLine();
                yearEntered = Integer.parseInt(dateEntered.substring(0,4));
                monthEntered = Integer.parseInt(dateEntered.substring(5,7));
            }catch(Exception e){
                System.out.println("\n"+RED+"\nAn Error had occurred. Please enter the format as stated."+RESET);
                break;
            }
            if(order != null){
                System.out.println("\n====================================================");
                System.out.println("\tAvailable Customer For Invoice Payment");
                System.out.println("====================================================");
                boolean status = true;
                for(int i = 1; i <= order.getTotalEntries(); i++){
                    if(order.getOrder(i).getUser()!= null && !order.getOrder(i).isPaymentStatus()){
                        if((order.getOrder(i).getOrderDate().getMonth()+1) == monthEntered &&(order.getOrder(i).getOrderDate().getYear()+1900) == yearEntered){
                            if(userEmail.getTotalEntries() != 0){
                                for(int k = 1; k <= userEmail.getTotalEntries(); k++){
                                    if(userEmail.getItem(k).equals(order.getOrder(i).getUser().getEmail())){
                                        /*DO NOTHING*/
                                        status = false;
                                        break;
                                    }else {
                                        status = true;
                                    }
                                }
                                if(status){
                                    if(order.getOrder(i).getUser() instanceof CorporateCustomer){
                                       userEmail.add(order.getOrder(i).getUser().getEmail()); 
                                    }
                                }
                            }else{
                                userEmail.add(order.getOrder(i).getUser().getEmail());
                            }
                        }
                    }
                }

                for(int j = 1; j <= userEmail.getTotalEntries(); j++){
                    System.out.println(BLUE + "[" + j + "] " + RESET
                    + userEmail.getItem(j));
                    count++;
                }

            if(count <= 0){
                    System.out.println(RED+"\nSorry, No Records Found !"+RESET);
                    invoiceMaintenance();
            }else{
                try{
                    System.out.print("\nPlease Enter The Email of Corporate Customer for Invoice Generations : ");
                    int choiceCorp = 0; String emailCorp = s.nextLine();
                    for(int a = 1; a<=FioreFlowershop.getUserList().getTotalUser();a++){
                        if(FioreFlowershop.getUserList().getUser(a).getEmail().equals(emailCorp)){
                            choiceCorp = a;
                            break;
                        }
                    }
                    invoicePaymentP2(FioreFlowershop.getUserList().getUser(choiceCorp));
                }catch(Exception e){
                    System.out.println("\n"+RED+"\nAn Error had occurred. Please Enter The Number of the Corporate Customer."+RESET);
                    break;
                }

            }
        }else {System.out.println(BLUE+ "\nWow, Much Empty for This Flower Shop" + RESET);}
    }
}
    
    public static void invoicePaymentP2(User user){//Part 2 of invoice payment
        double affordable = 0; double totalPrice = 0; double discountPrice = 0; int credit = 0;
        invoiceMenu(user);
        for(int i = 1; i <= order.getTotalEntries(); i++){
            if(order.getOrder(i)!= null){
                if(order.getOrder(i).getUser().getEmail().equals(user.getEmail()) && !order.getOrder(i).isPaymentStatus()){
                    if(order.getOrder(i).getCatalogPack() != null){
                        for(int k = 1; k <= order.getOrder(i).getCatalogPack().getTotalEntries(); k++){
                        CatalogPackage orderInstance = order.getOrder(i).getCatalogPack().getProduct(k);
                        Date orderDate = order.getOrder(k).getOrderDate();
//                        System.out.println(sdf.format(orderDate)+"   | "
//                        +orderInstance.getName()+ "\t\t\t  | \t" 
//                        +orderInstance.getUserQuantity()+ "\t|\t" 
//                        +orderInstance.getDiscountRate()+ "\t    |\t" 
//                        +orderInstance.getPrice() + " \t     |   " 
//                        +orderInstance.getPrice()
//                        *orderInstance.getUserQuantity());
//                        totalPrice += totalPrice(orderInstance.getPrice(),orderInstance.getUserQuantity());
//                            if(order.getOrder(i).getCatalogPack().getProduct(k).getDiscountRate() != 0){
//                                discountPrice += discountPrice(orderInstance.getPrice(),orderInstance.getUserQuantity(), orderInstance.getDiscountRate());
//                            }
                            tempCatalog.addOrder(order.getOrder(i));
                        }
                    }
                }
            }
        }
        //Calculate for corporate customer's affordability
        affordable = cc.getMonthlyLimit()-cc.getCreditSpent();
        invoiceMenuFooter(totalPrice, discountPrice);
        if(affordable <= 0){//Credit limit reached
            System.out.println("\n"+RED + "Sorry, looks like this customer's Credit Limit have exceeded. Please make payment right now.");
            for(int i = 1; i <= FioreFlowershop.getCorporateList().getTotalCorporate(); i++){
                if(FioreFlowershop.getCorporateList().getCorporate(i).getEmail().equals(user.getEmail())){
                    FioreFlowershop.getCorporateList().getCorporate(i).setPaymentStatus(false);
                }
            }
        } else if(affordable <= totalPrice){//Not enough money
            System.out.println("\n"+RED + "Sorry, looks like this customer can't afford the items." + RESET);
            System.out.println(RED + "Current Affordable Balance : "+affordable+ RESET);
            for(int i = 1; i <= FioreFlowershop.getCorporateList().getTotalCorporate(); i++){
                if(FioreFlowershop.getCorporateList().getCorporate(i).getEmail().equals(user.getEmail())){
                    FioreFlowershop.getCorporateList().getCorporate(i).setPaymentStatus(false);
                }
            }
        }else {//Normal Transaction carried out
            System.out.println("\n\nCurrent Corporate Customer's Credit Limit: " + cc.getMonthlyLimit());
            System.out.println("Current Corporate Customer's Affordable Limit: " + BLUE +affordable + RESET);
            System.out.println("After Payment Balance: "+ RED +(affordable-(totalPrice-discountPrice))+ RESET);
            System.out.print("\nDo you wish to make this payment? [Press Enter for Yes]");
            String enter = s.nextLine();
            if(enter.isEmpty()){
                //Set corporate customer's payment status to true
                for(int i = 1; i <= FioreFlowershop.getCorporateList().getTotalCorporate(); i++){
                    if(FioreFlowershop.getCorporateList().getCorporate(i).equals(user)){
                        FioreFlowershop.getCorporateList().getCorporate(i).setPaymentStatus(true);
                        //Set corporate customer's credit spent back to 0
                        FioreFlowershop.getCorporateList().getCorporate(i).setCreditSpent(0);
                        //credit += FioreFlowershop.getCorporate().getItem(i).getCreditSpent() + (totalPrice-discountPrice);
                    }
                }
                    //Set shopping cart payment status to true
                for(int k = 1; k <= order.getTotalEntries(); k++){
                    if(order.getOrder(k).getUser().equals(user)){
                        order.getOrder(k).setPaymentStatus(true);
                    }
                }
                //Store paid invoice into an invoice link list
                for(int l = 1;l <= order.getTotalEntries(); l++){
                    if(order.getOrder(l).getUser().equals(user)){
                        for(int m = 1; m <= userEmail.getTotalEntries(); m++){
                            if(userEmail.getItem(m).equals(order.getOrder(l).getUser().getEmail())){
                                userEmail.remove(m);
                            }
                        }
                        paymentHistory.addInvoice(new InvoiceHistory(invoiceNumber,tempCatalog, cc, today));
                    }
                }
                    ++invoiceNumber; 
                    System.out.println(GREEN+"\nPayment Success, Thanks for the Patronage :D "+ RESET);
                    System.out.println(BLUE+"Redirecting Back to User Selection Menu......" + RESET);
                    FioreFlowershop.userTypeSelection();
            }else {//Payment Cancel by user
                System.out.println(RED+"\nPayment Cancelled, Redirecting back to Main Menu."+RESET);
                FioreFlowershop.counterStaff();
            }
        }
    }
}
