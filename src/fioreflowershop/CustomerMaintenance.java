/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop;
import fioreflowershop.modal.Customer;
import java.util.*;
/**
 *
 * @author Admin
 */
public class CustomerMaintenance {
    static Scanner s = new Scanner (System.in);
    
    public static void main(String[] args) {
        // TODO code application logic here
        CreateOrLogIn();
    }
    
    public static void CreateOrLogIn(){
        System.out.println("Please Select The Options Below.");
        System.out.println("1. Create New Account");
        System.out.println("2. Log In to Exisiting Account");
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
        System.out.println("1. Consumers");
        System.out.println("2. Corporate Customers");
        int choiceCust = s.nextInt();
        CreateAccount(choiceCust);
    }
    public static void CustLogIn(){
        
    }
    
    public static void CreateAccount(int choice){
        if(choice == 1){
            
            System.out.println("Please Fill In The Fields As Prompted.");
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
           
            System.out.println();

            System.out.println("What would you like to do now?");
            System.out.println("1. Create Another Account.");
            System.out.println("2. Go back to Main Menu.");
            System.out.println("3. Log In Now.");
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
        } else if (choice == 2){
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
            char creditService = s.next().charAt(0);
            System.out.print("Password : ");
            String passw = s.nextLine();
            System.out.print("Retype Password : ");
            String repassw = s.nextLine();
        }
    }
}
