/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop;
import fioreflowershop.adt.*;
import fioreflowershop.modal.CorporateCustomer;
import fioreflowershop.modal.Consumer;
import fioreflowershop.modal.User;
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
    //ListInterface<Consumer> customer, ListInterface<CorporateCustomer> corporate
    
    public static void customerOptions(){
        if(customerLoggedIn == null && corporateLoggedIn == null){
            System.out.println("\nPlease Login to Gain Access to More Features.\n");
            System.out.println("[1] Create New Account");
            System.out.println("[2] Login To Existing Account");
            System.out.println("[3] Back to Main Menu");
            int logOrCreate = s.nextInt();
            switch(logOrCreate){
                case 1: CreateAccount(); break;
                case 2: CustLogIn(); break;
                case 3: FioreFlowershop.userTypeSelection();
            }
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
                    case 2: FioreFlowershop.gotoCustomizePackage(customerLoggedIn);//customizable flower order
                }
        } else if (customerOptionsChoice == 2){ //View Ordered Items
            
        } else if(customerOptionsChoice == 3){ //Edit Flower Order
            
        } else if(customerOptionsChoice == 4){
            if(customerLoggedIn != null){
            customerLoggedIn = null;
            System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "Successfully Logged Out From Customer Account ! " + FioreFlowershop.ConsoleColors.RESET);
        } else if (corporateLoggedIn != null) {
            corporateLoggedIn = null;
            System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "Successfully Logged Out From Corporate Customer Account ! " + FioreFlowershop.ConsoleColors.RESET);
        }
        customerOptions();
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
                    System.out.println("\n" + FioreFlowershop.ConsoleColors.RED_BOLD + "Password Mismatched, Please Try Again.\n" + FioreFlowershop.ConsoleColors.RESET);
                }
            }while(passwCheck == false);
            CorporateCustomer Corporate = new CorporateCustomer(usern, email, number, address, passw, company);
            
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
                FioreFlowershop.getCorporate().add(Corporate);
                userSize.add(Corporate);
                System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "New Account Successfully Created ! " + FioreFlowershop.ConsoleColors.RESET);
            }
            customerOptions();
        }
    }
    
    public static void CustLogIn(){
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
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "\nWelcome Back " + FioreFlowershop.getCustomer().getItem(i).getUsername() + " !" + FioreFlowershop.ConsoleColors.RESET);
                customerLoggedIn = new Consumer(FioreFlowershop.getCustomer().getItem(i).getUsername(),FioreFlowershop.getCustomer().getItem(i).getEmail(),
                FioreFlowershop.getCustomer().getItem(i).getPhone(), FioreFlowershop.getCustomer().getItem(i).getAddress());
                customerHit = true; break;
            } else {
                customerHit = false;
            }
        }
        for(int i = 1; i <= FioreFlowershop.getCorporate().getTotalEntries(); i++){
            if(FioreFlowershop.getCorporate().getItem(i).getEmail().equals(email) && FioreFlowershop.getCorporate().getItem(i).getPassword().equals(passw)){
                System.out.println(FioreFlowershop.ConsoleColors.GREEN + "\nWelcome Back " + FioreFlowershop.getCorporate().getItem(i).getUsername() + " !" + FioreFlowershop.ConsoleColors.RESET);
                corporateLoggedIn = new CorporateCustomer(FioreFlowershop.getCorporate().getItem(i).getUsername(),
                FioreFlowershop.getCorporate().getItem(i).getCompany(), 
                FioreFlowershop.getCorporate().getItem(i).getPhone(),
                FioreFlowershop.getCorporate().getItem(i).getAddress(),
                FioreFlowershop.getCorporate().getItem(i).getMonthlyLimit(), 
                FioreFlowershop.getCorporate().getItem(i).getCreditSpent());
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
    
//    public static void CustLogOut(){
//        if(customerLoggedIn != null){
//            customerLoggedIn = null;
//            System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "Successfully Logged Out From Customer Account ! " + FioreFlowershop.ConsoleColors.RESET);
//        } else if (corporateLoggedIn != null) {
//            corporateLoggedIn = null;
//            System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "Successfully Logged Out From Corporate Customer Account ! " + FioreFlowershop.ConsoleColors.RESET);
//        }
//        customerOptions();
//    }
    
    //STAFF SECTION
    public static void staffEditType(){
        System.out.println("\nPlease Select The Type Of Customer To Edit.");
        System.out.println("[1] Customer");
        System.out.println("[2] Corporate Customer");
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
        } else if(staffEditChoice == 2){
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
      }
        staffNextOption();
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
