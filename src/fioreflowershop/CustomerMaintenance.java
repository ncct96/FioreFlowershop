/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop;
import fioreflowershop.adt.*;
import fioreflowershop.modal.CorporateCustomer;
import fioreflowershop.modal.Customer;
import java.util.Scanner;
/**
 *
 * @author Admin
 */
public class CustomerMaintenance {
    static Scanner s = new Scanner (System.in);
    static ListInterface<Customer> customer = new ArrayList<>();
    static ListInterface<CorporateCustomer> corporate = new ArrayList<>();
    static String username = null; static String address = null; static String email = null; static boolean status; static int monthLimit;
    
    public static void customerOptions(){
        if(username == null){
            System.out.println("\nPlease Login to Gain Access to More Features.\n");
            System.out.println("[1] Create New Account");
            System.out.println("[2] Login To Existing Account");
            int logOrCreate = s.nextInt();
            switch(logOrCreate){
                case 1: CreateAccount(); break;
                case 2: CustLogIn(); break;
            }
        }
        System.out.println("\nWelcome Customers ! Fiore Flowershop is at your service :D ");
        System.out.println("Please Select The Options Below.");
        System.out.println("[1] Make Flower Order");
        System.out.println("[2] View Ordered Items");
        System.out.println("[3] Edit Flower Order");
        System.out.println("[4] Back to Main Menu");
        int customerOptionsChoice = s.nextInt();
        switch(customerOptionsChoice){
            case 1: //Make Flower Order
                System.out.println("\nPlease Select The Options Below.");
                System.out.println("[1] Make Catalog Flower Orders");
                System.out.println("[2] Make Customizable Flower Orders");
                int orderChoice = s.nextInt();
                switch(orderChoice){
                    case 1: //catalog flower order
                    case 2: //customizable flower order
                }
            case 2: //View Ordered Items
            case 3: //Edit Flower Order
            case 4: FioreFlowershop.userTypeSelection(); break;
        }
    }
    
    public static void CreateAccount(){
        boolean passwCheck;
        System.out.println("\nPlease Select The Type of Customer.");
        System.out.println("[1] Consumers");
        System.out.println("[2] Corporate Customers");
        int choiceCust = s.nextInt();
        if(choiceCust == 1){
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
                    System.out.println("\n" + FioreFlowershop.ConsoleColors.RED_BOLD + "Password Mismatched, Please Try Again.\n");
                }
            }while(passwCheck == false);
            
           
            Customer c = new Customer(usern,passw,email,number,address);
            for(int i = 1; i <= customer.getTotalEntries(); i++){
                if(customer.getItem(i).getUsername().equals(usern) || customer.getItem(i).getEmail().equals(email)){
                    System.out.println(FioreFlowershop.ConsoleColors.RED_BOLD + "\nSorry, Exisiting Account Found, Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
                    exist = true;
                    break;
                }
            }
            if(!exist){
                customer.add(c);
            }
            System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "New Account Successfully Created ! " + FioreFlowershop.ConsoleColors.RESET); 
            NextOption();
    
        } else if (choiceCust == 2){
            passwCheck = false; boolean exist = false; String passw = ""; 
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
                    System.out.println("\n" + FioreFlowershop.ConsoleColors.RED_BOLD + "Password Mismatched, Please Try Again.\n");
                }
            }while(passwCheck == false);
            CorporateCustomer Corporate = new CorporateCustomer(usern, email, number, address, passw, company);
            
            for(int i = 1; i <= corporate.getTotalEntries(); i++){
                if(corporate.getItem(i).getEmail().equals(email) && corporate.getItem(i).getUsername().equals(usern)){
                    System.out.println("\nSorry, Exisiting Account Found, Please Try Again.");
                    exist = true;
                    break;
                }
            }
            if(!exist){
                corporate.add(Corporate);
            }
            System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "New Account Successfully Created ! " + FioreFlowershop.ConsoleColors.RESET);
            NextOption();
        }
    }
    
    public static void CustLogIn(){
        boolean customerHit = false; boolean corporateHit = false;
        if(username == null){
        System.out.println("\nPlease Fill In The Confidentials As Prompted");
        s.nextLine();
        System.out.print("Please Enter Your Username : ");
        String usern = s.nextLine();
        System.out.print("Please Enter Your Password : ");
        String passw = s.nextLine();
        
        for(int i = 1; i <= customer.getTotalEntries(); i++){
            if(customer.getItem(i).getUsername().equals(usern) && customer.getItem(i).getPassword().equals(passw)){
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "\nWelcome Back " + customer.getItem(i).getUsername() + " !" + FioreFlowershop.ConsoleColors.RESET);
                username = customer.getItem(i).getUsername();
                address = customer.getItem(i).getAddress();
                email = customer.getItem(i).getEmail();
                customer.getItem(i).setStatus(true);
                status = customer.getItem(i).getStatus();
                customerHit = true; break;
            } else {
                customerHit = false;
            }
        }
        for(int i = 1; i <= corporate.getTotalEntries(); i++){
            if(corporate.getItem(i).getUsername().equals(usern) && corporate.getItem(i).getPassword().equals(passw)){
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "\nWelcome Back " + corporate.getItem(i).getUsername() + " !" + FioreFlowershop.ConsoleColors.RESET);
                username = corporate.getItem(i).getUsername();
                address = corporate.getItem(i).getAddress();
                email = corporate.getItem(i).getCompany();
                if(corporate.getItem(i).getCreditService()){
                    monthLimit = corporate.getItem(i).getMonthlyLimit();
                }
                corporate.getItem(i).setStatus(true);
                status = corporate.getItem(i).getStatus();
                corporateHit = true; break;
            } else {
                corporateHit = false;
            }
        }
        if(!customerHit && !corporateHit){
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
    
    public static void NextOption(){
        System.out.println("\nWhat would you like to do now?");
            System.out.println("[1] Create Another Account.");
            System.out.println("[2] Go back to Main Menu.");
            System.out.println("[3] Log In Now.");
            int choiceNow = s.nextInt();
            switch(choiceNow){
                case 1: CreateAccount(); break;
                case 2: /*REDIRECT TO MAIN MENU PAGE*/ break;
                case 3: CustLogIn(); break;
            }
    }
}
