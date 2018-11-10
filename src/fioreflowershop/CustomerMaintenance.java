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
    static String username = null; static String address = null; static String email = null; static boolean status; static int monthLimit = 0;
    
    public static void main(String[] args) {
        // TODO code application logic here
        CreateOrLogIn();
    }
    
    public static void CreateOrLogIn(){
        System.out.println("Please Select The Options Below.");
        System.out.println("[1] Create New Account");
        System.out.println("[2] Log In to Exisiting Account");
        int customer = s.nextInt();
        switch (customer) {
            case 1:
                CustTypeSelect();
                break;
            case 2:
                CustLogIn();
                break;
        } 
    }
    
    public static void CustTypeSelect(){
        System.out.println("Please Select The Type of Customer.");
        System.out.println("[1] Consumers");
        System.out.println("[2] Corporate Customers");
        int choiceCust = s.nextInt();
        CreateAccount(choiceCust);
    }
    
    public static void CustLogIn(){
        boolean customerHit = false; boolean corporateHit = false;
        if(username == null){
        System.out.println("Please Fill In The Confidentials As Prompted");
        s.nextLine();
        System.out.print("Please Enter Your Username : ");
        String usern = s.nextLine();
        System.out.print("Please Enter Your Password : ");
        String passw = s.nextLine();
        
        for(int i = 1; i <= customer.getTotalEntries(); i++){
            if(customer.getItem(i).getUsername().equals(usern) && customer.getItem(i).getPassword().equals(passw)){
                System.out.println("Welcome Back " + customer.getItem(i).getUsername() + " !");
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
                System.out.println("Welcome Back " + corporate.getItem(i).getUsername() + " !");
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
            System.out.println("\nInvalid Login Credentials");
                System.out.println("Would you like to try again or create an account?");
                System.out.println("[1] Login Again");
                System.out.println("[2] Create An Account");
                int choice = s.nextInt();
                switch(choice){
                    case 1:
                        CustLogIn();
                        break;
                    case 2:
                        CustTypeSelect();
                        break;
                }
        }
        CreateOrLogIn();
        } else {
            System.out.println("You Are Already Logged In ! ");
            //Redirect to other page
        }
    }
    
    public static void NextOption(){
        System.out.println("What would you like to do now?");
            System.out.println("[1] Create Another Account.");
            System.out.println("[2] Go back to Main Menu.");
            System.out.println("[3] Log In Now.");
            int choiceNow = s.nextInt();
            switch(choiceNow){
                case 1:
                    CustTypeSelect();
                    break;
                case 2:
                    //REDIRECT TO MAIN MENU PAGE
                    break;
                case 3:
                    CustLogIn();
                    break;
            }
    }
    
    public static void CreateAccount(int choice){
        if(choice == 1){
            int num = 0; boolean exist = false;
            
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
            System.out.print("Password : ");
            String passw = s.nextLine();
            System.out.print("Retype Password : ");
            String repassw = s.nextLine();
           
            Customer c = new Customer(usern,passw,email,number,address);
            for(int i = 1; i <= customer.getTotalEntries(); i++){
                if(customer.getItem(i).getUsername().equals(usern) || customer.getItem(i).getEmail().equals(email)){
                    System.out.println("\nSorry, Exisiting Account Found, Please Try Again.");
                    exist = true;
                    break;
                }
            }
            if(!exist){
                customer.add(c);
            }
            
            System.out.println(customer); NextOption();
    
        } else if (choice == 2){
            boolean service = false; boolean exist = false;
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
            System.out.print("Would you like to Sign Up for Monthly Credit Service? (Y/N) : ");
            String creditService = s.nextLine();
            
            if(creditService.equalsIgnoreCase("Y") || creditService.equalsIgnoreCase("YES")){
                service = true;
            }
            System.out.print("Password : ");
            String passw = s.nextLine();
            System.out.print("Retype Password : ");
            String repassw = s.nextLine();
            
            CorporateCustomer Corporate = new CorporateCustomer(usern, email, number, address, passw, company, service);
            
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
            System.out.println(corporate.toString()); NextOption();
        }
    }
}
