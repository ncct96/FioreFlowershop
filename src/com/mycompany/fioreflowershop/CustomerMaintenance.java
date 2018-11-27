/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.adt.*;
import com.mycompany.fioreflowershop.modal.CorporateCustomer;
import com.mycompany.fioreflowershop.modal.Consumer;
import com.mycompany.fioreflowershop.modal.User;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
/**
 *
 * @author Admin
 */
public class CustomerMaintenance {
    static Scanner s = new Scanner (System.in);
    private static Consumer customerLoggedIn; 
    private static CorporateCustomer corporateLoggedIn;
    private static ListInterface<User> userSize = new ArrayList<>();
    private static StackInterface<Calendar> dateStack = new StackADT<>();
    private static Calendar currentDate; private static Calendar presetDate;
    //ListInterface<Consumer> customer, ListInterface<CorporateCustomer> corporate
    
    public static void customerOptions(){
        if(customerLoggedIn == null && corporateLoggedIn == null){//Disallow the user from gaining additional features
            System.out.println("\nPlease Login to Gain Access to More Features.\n");
            System.out.println("[1] Create New Account");
            System.out.println("[2] Login To Existing Account");
            System.out.println("[3] Back to Main Menu");
            int logOrCreate = s.nextInt();
            switch(logOrCreate){
                case 1: CreateAccount(); break;
                case 2: CustLogIn(); break;
                case 3: FioreFlowershop.userTypeSelection(); break;
            }
        }//Welcome message for when user logged in as valid user
        if(corporateLoggedIn.getCreditSpent()!= 0){
            if(corporateLoggedIn.getCreditSpent() >= corporateLoggedIn.getMonthlyLimit()){
                System.out.println("\n"+FioreFlowershop.ConsoleColors.RED+"Sorry, Your maximum spending limit has reached, please pay before making further orders."+FioreFlowershop.ConsoleColors.RESET);
                System.out.println("\n" + FioreFlowershop.ConsoleColors.BLUE + "Thanks For Your Patronage ! :D"+FioreFlowershop.ConsoleColors.RESET);
                corporateLoggedIn = null;
                FioreFlowershop.userTypeSelection();
            }
        }
        
        try{
            //Set Preset Date
            presetDate = Calendar.getInstance();
            presetDate.set(presetDate.get(Calendar.YEAR), presetDate.get(Calendar.MONTH), 7, 0, 0, 0);

            //Get Current Date
            currentDate = Calendar.getInstance();
            currentDate.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), 
                    currentDate.get(Calendar.DAY_OF_MONTH), currentDate.get(Calendar.HOUR_OF_DAY), 
                    currentDate.get(Calendar.MINUTE));
            
            //Set the payment status back to false when a new month have passed
            if(!dateStack.isEmpty() && corporateLoggedIn.getCreditSpent() != 0){
                if(dateStack.peek().get(Calendar.MONTH)+2 == currentDate.get(Calendar.MONTH)+1
                    || dateStack.peek().get(Calendar.YEAR)+1 == currentDate.get(Calendar.YEAR)){
                    //New Month, New Invoice Payment
                    corporateLoggedIn.setPaymentStatus(false);
                    //If the customer have not paid after the 7th of the following month, restrict it
                    if(currentDate.after(presetDate) && corporateLoggedIn.getCreditSpent() != 0 && !corporateLoggedIn.getPaymentStatus()){//
                        System.out.println("\n"+ FioreFlowershop.ConsoleColors.RED +"Sorry, It seems like you have not paid for last month." + FioreFlowershop.ConsoleColors.RESET);
                        //Redirect back to the main menu, restrict access to making order.
                        System.out.println("\n" + FioreFlowershop.ConsoleColors.BLUE + "Thanks For Your Patronage ! :D"+FioreFlowershop.ConsoleColors.RESET);
                        corporateLoggedIn = null;
                        FioreFlowershop.userTypeSelection();
                    } 
                }
            }else {
                dateStack.push(currentDate);
            }
            
        }catch(Exception e){
            System.out.println(e.toString());
        }
        
        System.out.println("\nWelcome Customers ! Fiore Flowershop is at your service :D ");
        System.out.println("Please Select The Options Below.");
        System.out.println("[1] Make Flower Order");
        System.out.println("[2] View Ordered Items");
        System.out.println("[3] Edit Flower Order");
        System.out.println("[4] Log Out From This Account");
        int customerOptionsChoice = s.nextInt();
        if(customerOptionsChoice == 1){ //Make Flower Order
                System.out.println("\nPlease Select The Options Below.");
                System.out.println("[1] Make Catalog Flower Orders");
                System.out.println("[2] Make Customizable Flower Orders");
                int orderChoice = s.nextInt();
                switch(orderChoice){
                    case 1: FioreFlowershop.gotoCatalogOrders(customerLoggedIn,corporateLoggedIn); break; //catalog flower order
                    case 2: FioreFlowershop.gotoCustomizePackage(customerLoggedIn);break;//customizable flower order
                }
        } else if (customerOptionsChoice == 2){ //View Ordered Items
            
        } else if(customerOptionsChoice == 3){ //Edit Flower Order
            
        } else if(customerOptionsChoice == 4){
            if(customerLoggedIn != null){
            customerLoggedIn = null;
            System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "Successfully Logged Out From Customer Account ! " + FioreFlowershop.ConsoleColors.RESET);
            FioreFlowershop.userTypeSelection();
        } else if (corporateLoggedIn != null) {
            corporateLoggedIn = null;
            System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "Successfully Logged Out From Corporate Customer Account ! " + FioreFlowershop.ConsoleColors.RESET);
            FioreFlowershop.userTypeSelection();
        }
    }
}
    
    public static void CreateAccount(){
        boolean passwCheck;      
        boolean exist = false; String passw = "";
        passwCheck = false;
        System.out.println("\nPlease Fill In The Fields As Prompted.");
        s.nextLine();
        System.out.print("Username : ");
        String usern = s.nextLine();
        System.out.print("Email : ");
        String email = s.nextLine();
        System.out.print("Contact Number : ");
        String number = s.nextLine();
        System.out.print("Address (For Delivery Services) : ");
        String address = s.nextLine();
        do{
            System.out.print("Password : ");
            passw = s.nextLine();
            System.out.print("Retype Password : ");
            String repassw = s.nextLine();
            if(repassw.equals(passw)){
                passwCheck = true;
            } else {
                System.out.println("\n" + FioreFlowershop.ConsoleColors.RED_BOLD + "Password Mismatched, Please Try Again.\n" + FioreFlowershop.ConsoleColors.RESET);
            }
        }while(passwCheck == false);
        
        Consumer c = new Consumer(usern,passw,email,number,address);

    if(userSize != null){
        for(int i = 1; i <= userSize.getTotalEntries(); i++){
            if(userSize.getItem(i).getEmail().equals(email) && userSize.getItem(i).getUsername().equals(usern)){
                System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "Sorry, Exisiting Account Found, Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
                exist = true;
                break;
            } 
        }
    }
        if(!exist){
            FioreFlowershop.getCustomer().add(c);
            userSize.add(c);
            System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "New Account Successfully Created ! " + FioreFlowershop.ConsoleColors.RESET); 
        }
        customerOptions();
    }
    
    public static void CustLogIn(){//Customer Logging In, not
        boolean customerHit = false; boolean corporateHit = false;
        if(customerLoggedIn == null && corporateLoggedIn == null){
        System.out.println("\nPlease Fill In The Confidentials As Prompted");
        s.nextLine();
        System.out.print("Please Enter Your Email : ");
        String email = s.nextLine();
        System.out.print("Please Enter Your Password : ");
        String passw = s.nextLine();
        
        for(int i = 1; i <= FioreFlowershop.getCustomer().getTotalEntries(); i++){
            if(FioreFlowershop.getCustomer().getItem(i).getEmail().equals(email) && FioreFlowershop.getCustomer().getItem(i).getPassword().equals(passw)){
                customerLoggedIn = new Consumer(FioreFlowershop.getCustomer().getItem(i).getUsername(),FioreFlowershop.getCustomer().getItem(i).getEmail(),
                FioreFlowershop.getCustomer().getItem(i).getPhone(), FioreFlowershop.getCustomer().getItem(i).getAddress());
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "\nWelcome Back " + customerLoggedIn.getUsername() + " !" + FioreFlowershop.ConsoleColors.RESET);
                customerHit = true; break;
            } else {
                customerHit = false;
            }
        }
        for(int i = 1; i <= FioreFlowershop.getCorporate().getTotalEntries(); i++){//Check for corporate customer
            if(FioreFlowershop.getCorporate().getItem(i).getEmail().equals(email) && FioreFlowershop.getCorporate().getItem(i).getPassword().equals(passw)){
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "\nWelcome Back " + FioreFlowershop.getCorporate().getItem(i).getUsername() + " !" + FioreFlowershop.ConsoleColors.RESET);
                corporateLoggedIn = new CorporateCustomer(FioreFlowershop.getCorporate().getItem(i).getUsername(),
                FioreFlowershop.getCorporate().getItem(i).getCompany(), 
                FioreFlowershop.getCorporate().getItem(i).getPhone(),
                FioreFlowershop.getCorporate().getItem(i).getAddress(),
                FioreFlowershop.getCorporate().getItem(i).getMonthlyLimit(), 
                FioreFlowershop.getCorporate().getItem(i).getCreditSpent(),
                FioreFlowershop.getCorporate().getItem(i).getPaymentStatus());
                corporateHit = true; break;
            } else {
                corporateHit = false;
            }
        }
        if(!customerHit && !corporateHit){//If corporate customer is found 
            System.out.println(FioreFlowershop.ConsoleColors.RED_BOLD + "\nInvalid Login Credentials" + FioreFlowershop.ConsoleColors.RESET);
                System.out.println("Would you like to try again or create an account?");
                System.out.println("[1] Login Again");
                System.out.println("[2] Create An Account");
                int choice = s.nextInt();
                switch(choice){
                    case 1: CustLogIn(); break;
                    case 2: CreateAccount(); break;
                }
        }
        customerOptions(); //Test Run only, Supposed to Redirect to other page
        } else {
            System.out.println(FioreFlowershop.ConsoleColors.RED_BOLD + "\nYou Are Already Logged In ! " + FioreFlowershop.ConsoleColors.RESET);
            //Redirect to other page
        }
    }
    
    //STAFF SECTION
    public static void staffEditType(){
        System.out.println("\nPlease Select The Type Of Customer To Edit.");
        System.out.println("[1] Customer");
        System.out.println("[2] Corporate Customer");
        System.out.println("[3] Back");
        int staffEditChoice = s.nextInt();
        if(staffEditChoice == 1){
            String edit;
        for(int i = 1; i <= FioreFlowershop.getCustomer().getTotalEntries(); i++){
            System.out.println("\n"+FioreFlowershop.ConsoleColors.BLUE + "["+ i + "] "+ FioreFlowershop.ConsoleColors.RESET +FioreFlowershop.getCustomer().toString());
        } 
        System.out.println("Please Enter The Number of Customer You Would Want To Edit.");
        int custEditChoice = s.nextInt();
        System.out.println("\nPlease enter which field to edit");
        System.out.println("[1] Username");
        System.out.println("[2] Email");
        System.out.println("[3] Contact Number");
        System.out.println("[4] Address");
        System.out.println("[5] Password");
        int custOptionChoice = s.nextInt(); s.nextLine();
        if(custOptionChoice == 1){ //
            System.out.println("Old Username : " + FioreFlowershop.getCustomer().getItem(custEditChoice).getUsername());
            System.out.print("Please Enter The New Username : ");
            edit = s.nextLine();
            FioreFlowershop.getCustomer().getItem(custEditChoice).setUsername(edit);
            System.out.println("Successfully Modified ! ");
        } else if(custOptionChoice == 2){
            System.out.println("Old Email : " + FioreFlowershop.getCustomer().getItem(custEditChoice).getEmail());
            System.out.print("Please Enter The New Email : ");
            edit = s.nextLine();
            FioreFlowershop.getCustomer().getItem(custEditChoice).setEmail(edit);
            System.out.println("Successfully Modified ! ");
        } else if(custOptionChoice == 3){
            System.out.println("Old Contact Number : " + FioreFlowershop.getCustomer().getItem(custEditChoice).getPhone());
            System.out.print("Please Enter The New Contact Number : ");
            edit = s.nextLine();
            FioreFlowershop.getCustomer().getItem(custEditChoice).setPhone(edit);
            System.out.println("Successfully Modified ! ");
        } else if(custOptionChoice == 4){
            System.out.println("Old Address : " + FioreFlowershop.getCustomer().getItem(custEditChoice).getAddress());
            System.out.print("Please Enter The New Address : ");
            edit = s.nextLine();
            FioreFlowershop.getCustomer().getItem(custEditChoice).setAddress(edit);
            System.out.println("Successfully Modified ! ");
        } else if(custOptionChoice == 5){
            System.out.println("Old Password : " + FioreFlowershop.getCustomer().getItem(custEditChoice).getPassword());
            System.out.print("Please Enter The New Password : ");
            edit = s.nextLine();
            FioreFlowershop.getCustomer().getItem(custEditChoice).setPassword(edit);
            System.out.println("Successfully Modified ! ");
        }
        staffNextOption();
        } else if(staffEditChoice == 2){
            staffCorporateChoice();
        } else if(staffEditChoice == 3){
            FioreFlowershop.counterStaff();
        }
    }
    
    public static void staffCorporateChoice(){
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] Edit Corporate Customer's Details");
        System.out.println("[2] Create Corporate Customer");
        System.out.println("[3] Back");
        int choice = s.nextInt();
        
        switch(choice){
            case 1:staffEditCorporate();break;
            case 2:staffCreateCorporate();break;
            case 3:staffEditType();break;
        }
    }
    
    public static void staffEditCorporate(){
        String edit; int editLimit;
        for(int i = 1; i <= FioreFlowershop.getCustomer().getTotalEntries(); i++){
            System.out.println("\n"+FioreFlowershop.ConsoleColors.BLUE + "["+ i + "] "+ FioreFlowershop.ConsoleColors.RESET + FioreFlowershop.getCorporate().toString());
        }
            System.out.println("Please Enter The Number of Customer You Would Want To Edit.");
            int corpEditChoice = s.nextInt();
            System.out.println("\nPlease enter which field to edit");
            System.out.println("[1] Username");
            System.out.println("[2] Email");
            System.out.println("[3] Contact Number");
            System.out.println("[4] Address");
            System.out.println("[5] Password");
            System.out.println("[6] Company");
            System.out.println("[7] Credit Limit");
        int corpOptionChoice = s.nextInt(); s.nextLine();
        if(corpOptionChoice == 1){
            System.out.println("Old Username : " + FioreFlowershop.getCorporate().getItem(corpEditChoice).getUsername());
            System.out.print("Please Enter The New Username : ");
            edit = s.nextLine();
            FioreFlowershop.getCorporate().getItem(corpEditChoice).setUsername(edit);
            System.out.println("Successfully Modified ! ");
        } else if(corpOptionChoice == 2){
            System.out.println("Old Email : " + FioreFlowershop.getCorporate().getItem(corpEditChoice).getEmail());
            System.out.print("Please Enter The New Email : ");
            edit = s.nextLine();
            FioreFlowershop.getCorporate().getItem(corpEditChoice).setEmail(edit);
            System.out.println("Successfully Modified ! ");
        } else if(corpOptionChoice == 3){
            System.out.println("Old Contact Number : " + FioreFlowershop.getCorporate().getItem(corpEditChoice).getPhone());
            System.out.print("Please Enter The New Phone : ");
            edit = s.nextLine();
            FioreFlowershop.getCorporate().getItem(corpEditChoice).setPhone(edit);
            System.out.println("Successfully Modified ! ");
        } else if(corpOptionChoice == 4){
            System.out.println("Old Address : " + FioreFlowershop.getCorporate().getItem(corpEditChoice).getAddress());
            System.out.print("Please Enter The New Address : ");
            edit = s.nextLine();
            FioreFlowershop.getCorporate().getItem(corpEditChoice).setAddress(edit);
            System.out.println("Successfully Modified ! ");
        } else if(corpOptionChoice == 5){
            System.out.println("Old Password : " + FioreFlowershop.getCorporate().getItem(corpEditChoice).getPassword());
            System.out.print("Please Enter The New Password : ");
            edit = s.nextLine();
            FioreFlowershop.getCorporate().getItem(corpEditChoice).setPassword(edit);
            System.out.println("Successfully Modified ! ");
        } else if(corpOptionChoice == 6){
            System.out.println("Old Company : " + FioreFlowershop.getCorporate().getItem(corpEditChoice).getCompany());
            System.out.print("Please Enter The New Company : ");
            edit = s.nextLine();
            FioreFlowershop.getCorporate().getItem(corpEditChoice).setCompany(edit);
            System.out.println("Successfully Modified ! ");
        } else if(corpOptionChoice == 7){
            System.out.println("Old Credit Limit : " + FioreFlowershop.getCorporate().getItem(corpEditChoice).getMonthlyLimit());
            System.out.print("Please Enter The New Credit Limit : ");
            editLimit = s.nextInt();
            FioreFlowershop.getCorporate().getItem(corpEditChoice).setMonthlyLimit(editLimit);
            System.out.println("Successfully Modified ! ");
        }
        staffNextOption();
    }
    
    public static void staffCreateCorporate(){
        boolean passwCheck = false; boolean exist = false; String passw = ""; 
        s.nextLine();
        System.out.println("Please Fill In The Fields As Prompted.");
        System.out.print("Username : ");
        String usern = s.nextLine();
        System.out.print("Email : ");
        String email = s.nextLine();
        System.out.print("Contact Number : ");
        String number = s.nextLine();
        System.out.print("Address (For Delivery Services) : ");
        String address = s.nextLine();
        System.out.print("Company : ");
        String company = s.nextLine();
        do{
            System.out.print("Password : ");
            passw = s.nextLine();
            System.out.print("Retype Password : ");
            String repassw = s.nextLine();
            if(repassw.equals(passw)){
                passwCheck = true;
            } else {
                System.out.println("\n" + FioreFlowershop.ConsoleColors.RED_BOLD + "Password Mismatched, Please Try Again.\n" + FioreFlowershop.ConsoleColors.RESET);
            }
        }while(passwCheck == false);
        CorporateCustomer Corporate = new CorporateCustomer(usern, email, number, address, passw, company,5000,true);

        if(FioreFlowershop.getUser() != null){
            for(int i = 1; i <= FioreFlowershop.getUser().getTotalEntries(); i++){
                if(FioreFlowershop.getUser().getItem(i).getEmail().equals(email) && FioreFlowershop.getUser().getItem(i).getUsername().equals(usern)){
                    System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "Sorry, Exisiting Account Found, Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
                    exist = true;
                    break;
                }
            }
        }
        if(!exist){
            FioreFlowershop.getCorporate().add(Corporate);
            FioreFlowershop.getUser().add(Corporate);
            System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "New Account Successfully Created ! " + FioreFlowershop.ConsoleColors.RESET);
        }
        customerOptions();
    }
    
    public static void staffNextOption(){
        System.out.println("\nPlease Enter What Would You like to do.");
        System.out.println("[1] Edit Another Customer Account");
        System.out.println("[2] Edit Another Corporate Customer Account");
        System.out.println("[3] Go Back to Main Menu");
        int staffNextOpt = s.nextInt();
        switch(staffNextOpt){
            case 1: staffEditType(); break;
            case 2: FioreFlowershop.userTypeSelection(); break;
        }
    }
}