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

    static Scanner s = new Scanner(System.in);
    private static Consumer customerLoggedIn;
    private static CorporateCustomer corporateLoggedIn;
    private static Calendar dateLogin = Calendar.getInstance();
    private static Calendar currentDate;
    private static Calendar presetDate;
    private static CorporateCustomer corpEdit;
    private static Consumer custEdit;
    private static int loop = 0;
    private static UserInterface<User> user = FioreFlowershop.getUserList();
//    private static SortedListInterface<User> testSort = FioreFlowershop.getSortedUser();
    private static CorporateInterface<CorporateCustomer> corpC = FioreFlowershop.getCorporateList();
    private static ConsumerInterface<Consumer> cust = FioreFlowershop.getConsumerList();
    //ListInterface<Consumer> customer, ListInterface<CorporateCustomer> corporate

    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";
    public static final String RED = "\033[0;31m";
    public static final String BLUE = "\033[0;34m";
    public static final String RED_BOLD = "\033[1;31m";

    public static void customerOptions() {
        while (true) {
            if (customerLoggedIn == null && corporateLoggedIn == null) {//Disallow the user from gaining additional features
                System.out.println("\nPlease Select One Of The Options Below:");
                System.out.println(GREEN + "[1] " + RESET + "Create New Account");
                System.out.println(GREEN + "[2] " + RESET + "Login To Existing Account");
                System.out.println(GREEN + "[3] " + RESET + "Back to Main Menu");
                System.out.print("Selection: ");
                int logOrCreate = s.nextInt();
                s.nextLine();
                if (logOrCreate == 1) {
                    createAccount();
                } else if (logOrCreate == 2) {
                    custLogIn();
                } else {
                    break;
                }
            } else {
                customerMenu();
            }
        }
    }

    public static void checkDate() {
        while (true) {
            double reminderRange = 0; //Declared for assigning reminder range
            reminderRange = corporateLoggedIn.getMonthlyLimit() * .9; //Get the monthly limit of customer, then multiplies it with 90%
            try {
                //Set Preset Date
                presetDate = Calendar.getInstance();
                presetDate.set(presetDate.get(Calendar.YEAR), presetDate.get(Calendar.MONTH), 7, 0, 0, 0);

                //Get Current Date
                currentDate = Calendar.getInstance();
                currentDate.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH),
                        currentDate.get(Calendar.DAY_OF_MONTH), currentDate.get(Calendar.HOUR_OF_DAY),
                        currentDate.get(Calendar.MINUTE));

                //Set the payment status back to false when a new month have passed
                if (!dateLogin.equals(null) && corporateLoggedIn.getCreditSpent() != 0) {
                    if (dateLogin.get(Calendar.MONTH) + 2 == currentDate.get(Calendar.MONTH) + 1
                            || dateLogin.get(Calendar.YEAR) + 1 == currentDate.get(Calendar.YEAR)) {
                        //New Month, New Invoice Payment
                        corporateLoggedIn.setPaymentStatus(false);
                        //If the customer have not paid after the 7th of the following month, restrict it
                        if (currentDate.after(presetDate) && corporateLoggedIn.getCreditSpent() != 0 && !corporateLoggedIn.getPaymentStatus()) {//
                            System.out.println("\n" + RED + "Sorry, It seems like you have not paid for last month." + RESET);
                            //Redirect back to the main menu, restrict access to making order.
                            System.out.println("\n" + BLUE + "Thanks For Your Patronage ! :D" + RESET);
                            corporateLoggedIn = null;
                            break;
                        }
                    }
                } else {
                    //                    dateLogin.push(currentDate);
                    dateLogin = currentDate;
                }
            } catch (Exception e) {
                //When exception is found, print out the exception error message to customer.
                System.out.println(e.toString());
                break;
            }
            if (corporateLoggedIn.getCreditSpent() >= reminderRange) {
                System.out.println(RED_BOLD + "\nYour Credit Spent For this Month is close to reaching the limit" + RESET);
                System.out.println(RED_BOLD + "Your Credit Spent : " + String.format("%.0f", corporateLoggedIn.getCreditSpent()) + RESET);
                System.out.println(RED_BOLD + "Your Maximum Limit : " + corporateLoggedIn.getMonthlyLimit() + RESET);
            }
            break;
        }
    }

    public static void customerMenu() {
        System.out.println("\nWelcome Customers! Fiore Flowershop is at your service!");
        while (true) {
            try {
                if (corporateLoggedIn != null) {
                    checkDate();
                }
                System.out.println("\nPlease Select One Of The Options Below:");
                System.out.println(GREEN + "[1] " + RESET + "Make Flower Order");
                System.out.println(GREEN + "[2] " + RESET + "View Customized Package Order History");
                System.out.println(GREEN + "[3] " + RESET + "View Payment History");
                System.out.println(GREEN + "[4] " + RESET + "Log Out");
                System.out.print("Selection : ");
                int customerOptionsChoice = s.nextInt();
                s.nextLine();
                if (customerOptionsChoice == 1) {
                    while (true) {
                        System.out.println("\nPlease Select The Options Below.");
                        System.out.println(GREEN + "[1] " + RESET + "Make Catalog Flower Orders");
                        System.out.println(GREEN + "[2] " + RESET + "Make Customizable Flower Orders");
                        System.out.println(GREEN + "[3] " + RESET + "Back");
                        System.out.print("Selection: ");
                        int orderChoice = s.nextInt();
                        s.nextLine();
                        if (orderChoice == 1) {
                            FioreFlowershop.gotoCatalogOrders(customerLoggedIn, corporateLoggedIn);
                        } else if (orderChoice == 2) {
                            FioreFlowershop.gotoCustomizePackage(customerLoggedIn, 1);
                        } else {
                            break;
                        }
                    }
                } else if (customerOptionsChoice == 2) { //View Ordered Items
                    FioreFlowershop.gotoCustomizePackage(customerLoggedIn, 2);
                } else {
                    customerLoggedIn = null;
                    corporateLoggedIn = null;
                    System.out.println("\n" + GREEN + "Successfully Logged Out From Account! " + RESET);
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }
    }

    public static void createAccount() {
        while (true) {
            boolean passwCheck;
            int consumerType = 0;
            String passw = "";
            passwCheck = false;
            System.out.println("\nPlease Fill In The Fields As Prompted.");
            System.out.print("Username : ");
            String usern = s.nextLine();
            if(!usern.matches("[a-zA-Z]+")){
                System.out.println("\n"+RED_BOLD+"Username only contain Alphabets. Please Try Again."+RESET); break;
            }
            System.out.print("Email : ");
            String email = s.nextLine();
            if(!email.matches("^(.+)@(.+)$")){
                System.out.println("\n" + RED_BOLD+"Email Must Contain '@' and '.com'. Please Try Again."+RESET); break;
            }
            System.out.print("Contact Number : ");
            String number = s.nextLine();
            if(!number.matches("[0-9]+")){
                System.out.println("\n" + RED_BOLD+"Contact Numbers can only contain Numbers. Please Try Again."+RESET);
            }
            System.out.print("Address (For Delivery Services) : ");
            String address = s.nextLine();
            do {
                System.out.print("Password : ");
                passw = s.nextLine();
                System.out.print("Retype Password : ");
                String repassw = s.nextLine();
                if (repassw.equals(passw)) {
                    passwCheck = true;
                } else {
                    System.out.println("\n" + RED_BOLD + "Password Mismatched, Please Try Again.\n" + RESET);
                }
            } while (passwCheck == false);
            //Check for duplicate account created, disallow duplicate entries
            checkDuplicate(usern, passw, email, number, address, "", 0, "Customer");
            break;
        }

    }

    public static void checkDuplicate(String usern, String passw, String email, String number, String address, String company, int creditLimit, String consumerType) {
        boolean exist = false;
        if (user != null) {
            for (int i = 1; i <= user.getTotalUser(); i++) {
                if (user.getUser(i).getEmail().equals(email) && user.getUser(i).getUsername().equals(usern)) {
                    System.out.println("\n" + RED + "Sorry, Exisiting Account Found, Please Try Again." + RESET);
                    exist = true;
                    break;
                }
            }
        }
        if (!exist && consumerType.equals("Customer")) {
            Consumer c = new Consumer(usern, passw, email, number, address);
            cust.addConsumer(c);
            user.addUser(c);
            System.out.println("\n" + GREEN + "New Account Successfully Created ! " + RESET);
        } else if (!exist && consumerType.equals("Corporate")) {
            CorporateCustomer cc = new CorporateCustomer(usern, email, number, address, passw, company, creditLimit, true);
            corpC.addCorporate(cc);
            user.addUser(cc);
            System.out.println("\n" + GREEN + "New Account Successfully Created ! " + RESET);
        }
    }

    public static void custLogIn() {
        while (true) {
            if (customerLoggedIn == null && corporateLoggedIn == null) {
                System.out.println("\nPlease Fill In The Confidentials As Prompted");
                System.out.print("Please Enter Your Email : ");
                String email = s.nextLine();
                System.out.print("Please Enter Your Password : ");
                String passw = s.nextLine();
                //Verify for valid account
                custLogInVerify(email, passw);
                break;
            } else {
                System.out.println(RED_BOLD + "\nYou Are Already Logged In ! " + RESET);
                break;
            }
        }
    }

    public static void custLogInVerify(String email, String passw) {
        while (true) {
            boolean customerHit = false;
            boolean corporateHit = false;
            for (int i = 1; i <= cust.getTotalConsumer(); i++) {
                if (cust.getConsumer(i).getEmail().equals(email) && cust.getConsumer(i).getPassword().equals(passw)) {
                    customerLoggedIn = new Consumer(cust.getConsumer(i).getUsername(), cust.getConsumer(i).getPassword(), cust.getConsumer(i).getEmail(),
                            cust.getConsumer(i).getPhone(), cust.getConsumer(i).getAddress());
                    System.out.println(GREEN + "\nWelcome Back " + customerLoggedIn.getUsername() + " !" + RESET);
                    customerHit = true;
                    break;
                } else {
                    customerHit = false;
                }
            }
            for (int i = 1; i <= corpC.getTotalCorporate(); i++) {//Check for corporate customer
                if (corpC.getCorporate(i).getEmail().equals(email) && corpC.getCorporate(i).getPassword().equals(passw)) {
                    System.out.println(GREEN + "\nWelcome Back " + corpC.getCorporate(i).getUsername() + " !" + RESET);
                    CorporateCustomer corporateInstance = corpC.getCorporate(i);
                    corporateLoggedIn = new CorporateCustomer(corporateInstance.getUsername(),
                            corporateInstance.getEmail(),
                            corporateInstance.getPhone(),
                            corporateInstance.getAddress(),
                            corporateInstance.getMonthlyLimit(),
                            corporateInstance.getCreditSpent(),
                            corporateInstance.getPaymentStatus());
                    corporateHit = true;
                    break;
                } else {
                    corporateHit = false;
                }
            }
            if (!customerHit && !corporateHit) {//If corporate customer is not found 
                custTryAgain();
            } else {
                customerMenu();
            }
            break;
        }
    }

    public static void custTryAgain() {
        while (true) {
            System.out.println(RED_BOLD + "\nInvalid Login Credentials" + RESET);
            System.out.println("Would you like to try again or create an account?");
            System.out.println("[1] Login Again");
            System.out.println("[2] Create An Account");
            System.out.println("[3] Back");
            try {
                int choice = s.nextInt();
                s.nextLine();
                if (choice == 1) {
                    custLogIn();
                } else if (choice == 2) {
                    createAccount();
                } else if (choice == 3) {
                    customerOptions();
                } else {
                    break;
                }
                break;
            } catch (Exception e) {
                System.out.println("\n" + RED + "An Error Occurred. Please Only Enter Number Only." + RESET);
                break;
            }
        }
    }

    //STAFF SECTION
    public static void staffRemoveCust() {
        Consumer consumerInstance = null;
        CorporateCustomer corporateInstance = null;
        boolean remove = false;
        while (true) {
            try {
                customerListHeader();
                String email = "";
                System.out.print("\nPlease Enter The Email To be Removed : ");
                email = s.nextLine();
                for (int i = 1; i <= user.getTotalUser(); i++) {
                    if (user.getUser(i).getEmail().equals(email)) {
                        System.out.print("\n" + RED + "Are you Sure? Press Enter To Continue/Other Keys To Cancel : " + RESET);
                        String enter = s.nextLine();
                        if (enter.isEmpty()) {
                            user.remove(i);
                            remove = true;
                        } else {
                            break;
                        }

                        if (user.getUser(i) instanceof Consumer) {
                            consumerInstance = (Consumer) user.getUser(i);
                        } else if (user.getUser(i) instanceof CorporateCustomer) {
                            corporateInstance = (CorporateCustomer) user.getUser(i);
                        } else {
                            remove = false;
                        }
                        break;
                    }
                }
                if (remove) {
                    if (consumerInstance != null) {
                        for (int k = 1; k <= cust.getTotalConsumer(); k++) {
                            if (cust.getConsumer(k).equals(consumerInstance)) {
                                cust.remove(k);
                                break;
                            }
                        }
                    } else if (corporateInstance != null) {
                        for (int j = 1; j <= corpC.getTotalCorporate(); j++) {
                            if (corpC.getCorporate(j).equals(corporateInstance)) {
                                corpC.remove(j);
                                break;
                            }
                        }
                    }
                    System.out.println("\n" + GREEN + "Customer Successfully Removed ! " + RESET);
                    break;
                } else {
                    System.out.println("\n" + RED + "Request Canceled by User, Customer Removal Halted." + RESET);
                    break;
                }
            } catch (Exception e) {
                System.out.println("\n" + RED + "An Error Occurred. Please Try Again." + RESET);
                s.next();
            }
        }

    }

    public static void customerListHeader() {
        System.out.println("\n======================================================");
        System.out.println("\t Customers List.");
        System.out.println("======================================================");
        if (user != null) {
            for (int i = 1; i <= user.getTotalUser(); i++) {
                String type = "";
                if (user.getUser(i) instanceof Consumer) {
                    type = " (Consumer)";
                } else if (user.getUser(i) instanceof CorporateCustomer) {
                    type = " (Corporate)";
                }
                System.out.println(BLUE + "[" + i + "] " + RESET + user.getUser(i).getEmail() + type);
            }
        } else {
            System.out.println(RED + "\nSuch Empty, Much Wow !" + RESET);
        }
    }

    public static void staffEditCust(int custEditChoice) {
        while (true) {
            try {
                String edit = "";
                System.out.println("\nPlease enter which field to edit");
                System.out.println("[1] Username");
                System.out.println("[2] Email");
                System.out.println("[3] Contact Number");
                System.out.println("[4] Address");
                System.out.println("[5] Password");
                System.out.println("[6] Back");
                System.out.print("Selection : ");
                int custOptionChoice = s.nextInt();
                s.nextLine();
                if (custOptionChoice == 1) { //
                    System.out.println("Old Username : " + cust.getConsumer(custEditChoice).getUsername());
                    System.out.print("Please Enter The New Username : ");
                    try {
                        edit = s.nextLine();
                        if (!edit.matches("^[a-zA-Z]+")) {
                            System.out.println("\n" + RED + "Invalid Format For Username, Only Alphanumeric is Allowed." + RESET);
                            break;
                        }
                        cust.getConsumer(custEditChoice).setUsername(edit);
                        System.out.println("\n" + GREEN + "Successfully Modified ! " + RESET);
                        custEdit = null;
                        break;
                    } catch (Exception e) {
                        System.out.println("\n" + RED + "An Error Occurred, Please Try Again." + RESET);
                        s.next();
                    }
                } else if (custOptionChoice == 2) {
                    System.out.println("Old Email : " + cust.getConsumer(custEditChoice).getEmail());
                    System.out.print("Please Enter The New Email : ");
                    try {
                        edit = s.nextLine();
                        if (!edit.matches("^(.+)@(.+)$")) {
                            System.out.println("\n" + RED + "Invalid Email Entered. Please Retry." + RESET);
                            break;
                        }
                        cust.getConsumer(custEditChoice).setEmail(edit);
                        System.out.println("\n" + GREEN + "Successfully Modified ! " + RESET);
                        custEdit = null;
                        break;
                    } catch (Exception e) {
                        System.out.println("\n" + RED + "An Error Occurred, Please Try Again." + RESET);
                        s.next();
                    }
                } else if (custOptionChoice == 3) {
                    System.out.println("Old Contact Number : " + cust.getConsumer(custEditChoice).getPhone());
                    System.out.print("Please Enter The New Contact Number : ");
                    try {
                        edit = s.nextLine();
                        if (!edit.matches("[0-9]+")) {
                            System.out.println("\n" + RED + "Invalid Contact Number Entered. Please Retry." + RESET);
                            break;
                        }
                        cust.getConsumer(custEditChoice).setPhone(edit);
                        System.out.println("\n" + GREEN + "Successfully Modified ! " + RESET);
                        custEdit = null;
                        break;
                    } catch (Exception e) {
                        System.out.println("\n" + RED + "An Error Occurred, Please Try Again." + RESET);
                        s.next();
                    }
                } else if (custOptionChoice == 4) {
                    System.out.println("Old Address : " + cust.getConsumer(custEditChoice).getAddress());
                    System.out.print("Please Enter The New Address : ");
                    try {
                        edit = s.nextLine();
                        cust.getConsumer(custEditChoice).setAddress(edit);
                        System.out.println("\n" + GREEN + "Successfully Modified ! " + RESET);
                        custEdit = null;
                        break;
                    } catch (Exception e) {
                        System.out.println("\n" + RED + "An Error Occurred, Please Try Again." + RESET);
                        s.next();
                    }

                } else if (custOptionChoice == 5) {
                    System.out.println("Old Password : " + cust.getConsumer(custEditChoice).getPassword());
                    System.out.print("Please Enter The New Password : ");
                    try {
                        edit = s.nextLine();
                        if (cust.getConsumer(custEditChoice).getPassword().equals(edit)) {
                            System.out.println("\n" + RED + "Password cannot be same with old password. Please Try Again." + RESET);
                        }
                        cust.getConsumer(custEditChoice).setPassword(edit);
                        System.out.println("\n" + GREEN + "Successfully Modified ! " + RESET);
                        custEdit = null;
                        break;
                    } catch (Exception e) {
                        System.out.println("\n" + RED + "An Error Occurred, Please Try Again." + RESET);
                        s.next();
                    }
                } else {
                    custEdit = null;
                    break;
                }
            } catch (Exception e) {
                System.out.println(RED + "\nSorry, Please Only Enter Number." + RESET);
                s.next();
            }
        }
    }

    public static void staffEditType() {
        while (true) {
            try {
                customerListHeader();
                String email = "";
                int custEditChoice = 0;
                int corpEditChoice = 0;
                System.out.print("\nPlease Enter The Customer's Email For Editing (ENTER B to BACK) : ");
                email = s.nextLine();
                if (email.equalsIgnoreCase("B")) {
                    break;
                } else {
                    for (int i = 1; i <= user.getTotalUser(); i++) {
                        if (email.equals(user.getUser(i).getEmail())) {
                            if (user.getUser(i) instanceof Consumer) {
                                custEdit = (Consumer) user.getUser(i);
                                custEditChoice = i;
                            } else if (user.getUser(i) instanceof CorporateCustomer) {
                                corpEdit = (CorporateCustomer) user.getUser(i);
                                corpEditChoice = i;
                            }
                            System.out.println(user.getUser(i).toString());
                        }
                    }
                }

                if (custEdit != null) {
                    for (int i = 1; i <= cust.getTotalConsumer(); i++) {
                        if (cust.getConsumer(i).getEmail().equals(user.getUser(custEditChoice).getEmail())) {
                            custEditChoice = i;
                            break;
                        }
                    }
                    staffEditCust(custEditChoice);
                } else if (corpEdit != null) {
                    for (int i = 1; i <= corpC.getTotalCorporate(); i++) {
                        if (corpC.getCorporate(i).getEmail().equals(user.getUser(corpEditChoice).getEmail())) {
                            corpEditChoice = i;
                            break;
                        }
                    }
                    managerEditCorporate(corpEditChoice);
                } else {
                    System.out.println("\n" + RED + "Please Enter A Valid Email Address Listed." + RESET);
                    break;
                }
            } catch (Exception e) {
                s.next();
            }
        }
    }

    public static void managerEditCorporate(int corpEditChoice) {
        while (true) {
            try {
                String edit;
                int editLimit;
                System.out.println("\nPlease enter which field to edit");
                System.out.println("[1] Username");
                System.out.println("[2] Email");
                System.out.println("[3] Contact Number");
                System.out.println("[4] Address");
                System.out.println("[5] Password");
                System.out.println("[6] Company");
                System.out.println("[7] Credit Limit");
                System.out.println("[8] Back");
                System.out.print("Selection : ");
                int corpOptionChoice = s.nextInt();
                s.nextLine();
                if (corpOptionChoice == 1) {
                    System.out.println("Old Username : " + corpC.getCorporate(corpEditChoice).getUsername());
                    System.out.print("Please Enter The New Username : ");
                    try {
                        edit = s.nextLine();
                        if (!edit.matches("^[a-zA-Z0-9]+")) {
                            System.out.println("\n" + RED + "Invalid Format For Username, Please Only Enter Alphabet/Number/Alphanumeric." + RESET);
                            break;
                        }
                        corpC.getCorporate(corpEditChoice).setUsername(edit);
                        System.out.println("\n" + GREEN + "Successfully Modified ! " + RESET);
                        corpEdit = null;
                        break;
                    } catch (Exception e) {
                        System.out.println("\n" + RED + "An Error Occurred, Please Try Again." + RESET);
                        s.next();
                    }
                } else if (corpOptionChoice == 2) {
                    System.out.println("Old Email : " + corpC.getCorporate(corpEditChoice).getEmail());
                    System.out.print("Please Enter The New Email : ");
                    try {
                        edit = s.nextLine();
                        if (!edit.matches("^(.+)@(.+)$")) {
                            System.out.println("\n" + RED + "Invalid Email Entered. Please Retry." + RESET);
                            break;
                        }
                        corpC.getCorporate(corpEditChoice).setEmail(edit);
                        System.out.println("\n" + GREEN + "Successfully Modified ! " + RESET);
                        corpEdit = null;
                        break;
                    } catch (Exception e) {
                        System.out.println("\n" + RED + "An Error Occurred, Please Try Again." + RESET);
                        s.next();
                    }

                } else if (corpOptionChoice == 3) {
                    System.out.println("Old Contact Number : " + corpC.getCorporate(corpEditChoice).getPhone());
                    System.out.print("Please Enter The New Phone : ");
                    try {
                        edit = s.nextLine();
                        if (!edit.matches("[0-9]+")) {
                            System.out.println("\n" + RED + "Invalid Contact Number Entered. Please Retry." + RESET);
                            break;
                        }
                        corpC.getCorporate(corpEditChoice).setPhone(edit);
                        System.out.println("\n" + GREEN + "Successfully Modified ! " + RESET);
                        corpEdit = null;
                        break;
                    } catch (Exception e) {
                        System.out.println("\n" + RED + "An Error Occurred, Please Try Again." + RESET);
                        s.next();
                    }

                } else if (corpOptionChoice == 4) {
                    System.out.println("Old Address : " + corpC.getCorporate(corpEditChoice).getAddress());
                    System.out.print("Please Enter The New Address : ");
                    try {
                        edit = s.nextLine();
                        corpC.getCorporate(corpEditChoice).setAddress(edit);
                        System.out.println("\n" + GREEN + "Successfully Modified ! " + RESET);
                        corpEdit = null;
                        break;
                    } catch (Exception e) {
                        System.out.println("\n" + RED + "An Error Occurred, Please Try Again." + RESET);
                        s.next();
                    }

                } else if (corpOptionChoice == 5) {
                    System.out.println("Old Password : " + corpC.getCorporate(corpEditChoice).getPassword());
                    System.out.print("Please Enter The New Password : ");
                    try {
                        edit = s.nextLine();
                        if (corpC.getCorporate(corpEditChoice).getPassword().equals(edit)) {
                            System.out.println("\n" + RED + "Password cannot be same with old password. Please Try Again." + RESET);
                            break;
                        }
                        corpC.getCorporate(corpEditChoice).setPassword(edit);
                        System.out.println("\n" + GREEN + "Successfully Modified ! " + RESET);
                        corpEdit = null;
                        break;
                    } catch (Exception e) {
                        System.out.println("\n" + RED + "An Error Occurred, Please Try Again." + RESET);
                        s.next();
                    }

                } else if (corpOptionChoice == 6) {
                    System.out.println("Old Company : " + corpC.getCorporate(corpEditChoice).getCompany());
                    System.out.print("Please Enter The New Company : ");
                    try {
                        edit = s.nextLine();
                        if (corpC.getCorporate(corpEditChoice).getCompany().equals(edit)) {
                            System.out.println("\n" + RED + "Company Name cannot be same as Old Company Name, Please Try Again." + RESET);
                            break;
                        }
                        corpC.getCorporate(corpEditChoice).setCompany(edit);
                        System.out.println("\n" + GREEN + "Successfully Modified ! " + RESET);
                        corpEdit = null;
                        break;
                    } catch (Exception e) {
                        System.out.println("\n" + RED + "An Error Occurred, Please Try Again." + RESET);
                        s.next();
                    }
                } else if (corpOptionChoice == 7) {
                    System.out.println("Old Credit Limit : " + corpC.getCorporate(corpEditChoice).getMonthlyLimit());
                    System.out.print("Please Enter The New Credit Limit : ");
                    try {
                        editLimit = s.nextInt();
                        s.nextLine();
                        corpC.getCorporate(corpEditChoice).setMonthlyLimit(editLimit);
                        System.out.println("\n" + GREEN + "Successfully Modified ! " + RESET);
                        corpEdit = null;
                        break;
                    } catch (Exception e) {
                        System.out.println("\n" + RED + "An Error Occurred, Please Try Again." + RESET);
                        s.next();
                    }
                } else {
                    corpEdit = null;
                    break;
                }
            } catch (Exception e) {
                System.out.println(RED + "\nSorry, Please Only Enter Number." + RESET);
                s.next();
            }
        }
    }

    public static void staffCreateCorporate() {
        while (true) {
            boolean passwCheck = false;
            boolean exist = false;
            String passw = "";
            System.out.println("Please Fill In The Fields As Prompted.");
            try {
                System.out.print("Username : ");
                String usern = s.nextLine();
                if (!usern.matches("^[a-zA-Z]+")) {
                    System.out.println("\n" + RED + "Please only Enter Alphabets for Username." + RESET);
                    break;
                }
                System.out.print("Email : ");
                String email = s.nextLine();
                if (!email.matches("^(.+)@(.+)$")) {
                    System.out.println("\n" + RED + "Please Enter The Valid Email Address." + RESET);
                    break;
                }
                System.out.print("Contact Number : ");
                String number = s.nextLine();
                if (!number.matches("^[0-9]+")) {
                    System.out.println("\n" + RED + "Please Enter Valid Contact Number." + RESET);
                    break;
                }
                System.out.print("Address (For Delivery Services) : ");
                String address = s.nextLine();
                System.out.print("Company : ");
                String company = s.nextLine();
                if (!company.matches("^[a-zA-Z]+")) {
                    System.out.println("\n" + RED + "Invalid Format For Company Name. Please Try Again." + RESET);
                    break;
                }
                System.out.print("Credit Limit : ");
                int creditLimit = s.nextInt();
                do {
                    s.nextLine();
                    System.out.print("Password : ");
                    passw = s.nextLine();
                    System.out.print("Retype Password : ");
                    String repassw = s.nextLine();
                    if (repassw.equals(passw)) {
                        passwCheck = true;
                    } else {
                        System.out.println("\n" + RED_BOLD + "Password Mismatched, Please Try Again.\n" + RESET);
                    }
                } while (passwCheck == false);
                checkDuplicate(usern, passw, email, number, address, company, creditLimit, "Corporate");
                break;
            } catch (Exception e) {
                s.nextLine();
                System.out.println("\n" + RED + "An Error Occured. Please Try Again." + RESET); break;
            }
        }
    }
}
