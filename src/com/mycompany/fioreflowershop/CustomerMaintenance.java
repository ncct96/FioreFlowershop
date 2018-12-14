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
    private static Calendar dateStack = Calendar.getInstance();
    private static Calendar currentDate;
    private static Calendar presetDate;
    private static CorporateCustomer corpEdit;
    private static Consumer custEdit;
    private static int loop = 0;
    private static LinkedList<User> user = FioreFlowershop.getUser();
    private static LinkedList<CorporateCustomer> corpC = FioreFlowershop.getCorporate();
    private static LinkedList<Consumer> cust = FioreFlowershop.getCustomer();
    //ListInterface<Consumer> customer, ListInterface<CorporateCustomer> corporate

    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";

    public static void customerOptions() {
        while (true) {
            double reminderRange = 0; //Declared for assigning reminder range
            if (customerLoggedIn == null && corporateLoggedIn == null) {//Disallow the user from gaining additional features
                System.out.println("\nPlease Select One Of The Options Below:");
                System.out.println(GREEN + "[1] " + RESET + "Create New Account");
                System.out.println(GREEN + "[1] " + RESET + "Login To Existing Account");
                System.out.println(GREEN + "[1] " + RESET + "Back to Main Menu");
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

            }
            if (corporateLoggedIn != null) {//If the customer is logged in
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
                    if (!dateStack.equals(null) && corporateLoggedIn.getCreditSpent() != 0) {
                        if (dateStack.get(Calendar.MONTH) + 2 == currentDate.get(Calendar.MONTH) + 1
                                || dateStack.get(Calendar.YEAR) + 1 == currentDate.get(Calendar.YEAR)) {
                            //New Month, New Invoice Payment
                            corporateLoggedIn.setPaymentStatus(false);
                            //If the customer have not paid after the 7th of the following month, restrict it
                            if (currentDate.after(presetDate) && corporateLoggedIn.getCreditSpent() != 0 && !corporateLoggedIn.getPaymentStatus()) {//
                                System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "Sorry, It seems like you have not paid for last month." + FioreFlowershop.ConsoleColors.RESET);
                                //Redirect back to the main menu, restrict access to making order.
                                System.out.println("\n" + FioreFlowershop.ConsoleColors.BLUE + "Thanks For Your Patronage ! :D" + FioreFlowershop.ConsoleColors.RESET);
                                corporateLoggedIn = null;
                                FioreFlowershop.userTypeSelection();
                            }
                        }
                    } else {
//                    dateStack.push(currentDate);
                        dateStack = currentDate;
                    }
                } catch (Exception e) {
                    //When exception is found, print out the exception error message to customer.
                    System.out.println(e.toString());
                }
            }//If the corporate credit spent exceeds or equals to the reminder range
            if (corporateLoggedIn.getCreditSpent() >= reminderRange) {
                System.out.println(FioreFlowershop.ConsoleColors.RED_BOLD + "Your Credit Spent For this Month is close to reaching the limit" + FioreFlowershop.ConsoleColors.RESET);
                System.out.println(FioreFlowershop.ConsoleColors.RED_BOLD + "Your Credit Spent : " + String.format("%.0f", corporateLoggedIn.getCreditSpent()) + FioreFlowershop.ConsoleColors.RESET);
                System.out.println(FioreFlowershop.ConsoleColors.RED_BOLD + "Your Maximum Limit : " + corporateLoggedIn.getMonthlyLimit() + FioreFlowershop.ConsoleColors.RESET);
            }

            customerMenu();
        }
    }

    public static void customerMenu() {
        System.out.println("\nWelcome Customers! Fiore Flowershop is at your service!");
        while (true) {
            System.out.println("Please Select One Of The Options Below:");
            System.out.println(GREEN + "[1] " + RESET + "Make Flower Order");
            System.out.println(GREEN + "[2] " + RESET + "View Ordered Items");
            System.out.println(GREEN + "[3] " + RESET + "Log Out");
            System.out.print("Selection: ");
            int customerOptionsChoice = s.nextInt();
            s.nextLine();

            if (customerOptionsChoice == 1) {
                while (true) {
                    System.out.println("\nPlease Select The Options Below.");
                    System.out.println(GREEN + "[1] " + RESET + "[1] Make Catalog Flower Orders");
                    System.out.println(GREEN + "[2] " + RESET + "[2] Make Customizable Flower Orders");
                    System.out.println(GREEN + "[3] " + RESET + "[3] Back");
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
                System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "Successfully Logged Out From Account! " + FioreFlowershop.ConsoleColors.RESET);
                break;
            }
        }
    }

    public static void createAccount() {
        boolean passwCheck;
        boolean exist = false;
        String passw = "";
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
        do {
            System.out.print("Password : ");
            passw = s.nextLine();
            System.out.print("Retype Password : ");
            String repassw = s.nextLine();
            if (repassw.equals(passw)) {
                passwCheck = true;
            } else {
                System.out.println("\n" + FioreFlowershop.ConsoleColors.RED_BOLD + "Password Mismatched, Please Try Again.\n" + FioreFlowershop.ConsoleColors.RESET);
            }
        } while (passwCheck == false);

        if (user != null) {
            for (int i = 1; i <= user.getTotalEntries(); i++) {
                if (user.getItem(i).getEmail().equals(email) && user.getItem(i).getUsername().equals(usern)) {
                    System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "Sorry, Exisiting Account Found, Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
                    exist = true;
                    break;
                }
            }
        }
        if (!exist) {
            Consumer c = new Consumer(usern, passw, email, number, address);
            cust.add(c);
            user.add(c);
            sortEmailOrder();
            System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "New Account Successfully Created ! " + FioreFlowershop.ConsoleColors.RESET);
        }
        customerOptions();
    }

    public static void sortEmailOrder() { //For sorting of email of customer and corporate customer
        User userUser;
        if (user != null) { //If the user list is not null
            for (int i = 1; i <= user.getTotalEntries(); i++) {
                for (int j = i + 1; j <= user.getTotalEntries(); j++) {
                    if (user.getItem(i).getEmail().charAt(loop)
                            == user.getItem(j).getEmail().charAt(loop)) {
                        //If the character is the same value when compared, plus the loop, then call back the method.
                        loop++;
                        sortEmailOrder();
                    } else if (user.getItem(i).getEmail().charAt(loop)
                            > user.getItem(j).getEmail().charAt(loop)) {
                        loop = 0;
                        userUser = user.getItem(i);
                        user.replace(i, user.getItem(j));
                        user.replace(j, userUser);
                    } else if (user.getItem(i).getEmail().charAt(loop)
                            < user.getItem(j).getEmail().charAt(loop)) {
                        //If the character is smaller than when compared, plus the size of I and J, to prevent infinity loop
                        i++;
                        j++;
                        loop = 0;
                    }
                }
            }
        }
    }

    public static void custLogIn() {//Customer Logging In, not
        boolean customerHit = false;
        boolean corporateHit = false;
        if (customerLoggedIn == null && corporateLoggedIn == null) {
            System.out.println("\nPlease Fill In The Confidentials As Prompted");
            System.out.print("Please Enter Your Email : ");
            String email = s.nextLine();
            System.out.print("Please Enter Your Password : ");
            String passw = s.nextLine();

            for (int i = 1; i <= cust.getTotalEntries(); i++) {
                if (cust.getItem(i).getEmail().equals(email) && cust.getItem(i).getPassword().equals(passw)) {
                    customerLoggedIn = new Consumer(cust.getItem(i).getUsername(), cust.getItem(i).getPassword(), cust.getItem(i).getEmail(),
                            cust.getItem(i).getPhone(), cust.getItem(i).getAddress());
                    System.out.println(FioreFlowershop.ConsoleColors.GREEN + "\nWelcome Back " + customerLoggedIn.getUsername() + " !" + FioreFlowershop.ConsoleColors.RESET);
                    customerHit = true;
                    break;
                } else {
                    customerHit = false;
                }
            }
            for (int i = 1; i <= corpC.getTotalEntries(); i++) {//Check for corporate customer
                if (corpC.getItem(i).getEmail().equals(email) && corpC.getItem(i).getPassword().equals(passw)) {
                    System.out.println(FioreFlowershop.ConsoleColors.GREEN + "\nWelcome Back " + corpC.getItem(i).getUsername() + " !" + FioreFlowershop.ConsoleColors.RESET);
                    corporateLoggedIn = new CorporateCustomer(corpC.getItem(i).getUsername(),
                            corpC.getItem(i).getEmail(),
                            corpC.getItem(i).getPhone(),
                            corpC.getItem(i).getAddress(),
                            corpC.getItem(i).getMonthlyLimit(),
                            corpC.getItem(i).getCreditSpent(),
                            corpC.getItem(i).getPaymentStatus());
                    corporateHit = true;
                    break;
                } else {
                    corporateHit = false;
                }
            }
            if (!customerHit && !corporateHit) {//If corporate customer is not found 
                System.out.println(FioreFlowershop.ConsoleColors.RED_BOLD + "\nInvalid Login Credentials" + FioreFlowershop.ConsoleColors.RESET);
                System.out.println("Would you like to try again or create an account?");
                System.out.println("[1] Login Again");
                System.out.println("[2] Create An Account");
                try {
                    int choice = s.nextInt();
                    s.nextLine();
                    switch (choice) {
                        case 1:
                            custLogIn();
                            break;
                        case 2:
                            createAccount();
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "An Error Occurred. Please Only Enter Number Only." + FioreFlowershop.ConsoleColors.RESET);
                    customerOptions();
                }

            } else {
                customerOptions();
            }
        } else {
            System.out.println(FioreFlowershop.ConsoleColors.RED_BOLD + "\nYou Are Already Logged In ! " + FioreFlowershop.ConsoleColors.RESET);
            //Redirect to other page
        }
    }

    //STAFF SECTION
    public static void staffEditType() {
        System.out.println("======================================================");
        System.out.println("\t Customers List.");
        System.out.println("======================================================");
        if (user != null) {
            for (int i = 1; i <= user.getTotalEntries(); i++) {
                System.out.println(FioreFlowershop.ConsoleColors.BLUE + "[" + i + "] " + FioreFlowershop.ConsoleColors.RESET + user.getItem(i).getEmail());
            }
            String email = "";
            int custEditChoice = 0;
            int corpEditChoice = 0;
            System.out.print("\nPlease Enter The Customer's Email For Editing : ");
            email = s.nextLine();
            for (int i = 1; i <= user.getTotalEntries(); i++) {
                if (email.equals(user.getItem(i).getEmail())) {
                    System.out.println(user.getItem(i).toString());
                    if (user.getItem(i) instanceof Consumer) {
                        custEdit = (Consumer) user.getItem(i);
                        custEditChoice = i;
                    } else if (user.getItem(i) instanceof CorporateCustomer) {
                        corpEdit = (CorporateCustomer) user.getItem(i);
                        corpEditChoice = i;
                    }
                }
            }
            if (custEdit != null) {
                String edit = "";
                System.out.println("\nPlease enter which field to edit");
                System.out.println("[1] Username");
                System.out.println("[2] Email");
                System.out.println("[3] Contact Number");
                System.out.println("[4] Address");
                System.out.println("[5] Password");
                int custOptionChoice = s.nextInt();
                s.nextLine();
                if (custOptionChoice == 1) { //
                    System.out.println("Old Username : " + cust.getItem(custEditChoice).getUsername());
                    System.out.print("Please Enter The New Username : ");
                    try {
                        edit = s.nextLine();
                        cust.getItem(custEditChoice).setUsername(edit);
                        System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "Successfully Modified ! " + FioreFlowershop.ConsoleColors.RESET);
                    } catch (Exception e) {
                        System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "An Error Occurred, Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
                        staffEditType();
                    }
                } else if (custOptionChoice == 2) {
                    System.out.println("Old Email : " + cust.getItem(custEditChoice).getEmail());
                    System.out.print("Please Enter The New Email : ");
                    try {
                        edit = s.nextLine();
                        if (!edit.contains("@")) {
                            System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "Invalid Email Entered. Please Retry." + FioreFlowershop.ConsoleColors.RESET);
                            staffEditType();
                        }
                        cust.getItem(custEditChoice).setEmail(edit);
                        System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "Successfully Modified ! " + FioreFlowershop.ConsoleColors.RESET);
                    } catch (Exception e) {
                        System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "An Error Occurred, Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
                        staffEditType();
                    }
                } else if (custOptionChoice == 3) {
                    System.out.println("Old Contact Number : " + cust.getItem(custEditChoice).getPhone());
                    System.out.print("Please Enter The New Contact Number : ");
                    try {
                        edit = s.nextLine();
                        if (!edit.matches("[0-9]+")) {
                            System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "Invalid Contact Number Entered. Please Retry." + FioreFlowershop.ConsoleColors.RESET);
                            staffEditType();
                        }
                        cust.getItem(custEditChoice).setPhone(edit);
                        System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "Successfully Modified ! " + FioreFlowershop.ConsoleColors.RESET);
                    } catch (Exception e) {
                        System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "An Error Occurred, Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
                        staffEditType();
                    }
                } else if (custOptionChoice == 4) {
                    System.out.println("Old Address : " + cust.getItem(custEditChoice).getAddress());
                    System.out.print("Please Enter The New Address : ");
                    try {
                        edit = s.nextLine();
                        cust.getItem(custEditChoice).setAddress(edit);
                        System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "Successfully Modified ! " + FioreFlowershop.ConsoleColors.RESET);
                    } catch (Exception e) {
                        System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "An Error Occurred, Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
                        staffEditType();
                    }

                } else if (custOptionChoice == 5) {
                    System.out.println("Old Password : " + cust.getItem(custEditChoice).getPassword());
                    System.out.print("Please Enter The New Password : ");
                    try {
                        edit = s.nextLine();
                        if (cust.getItem(custEditChoice).getPassword().equals(edit)) {
                            System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "Password cannot be same with old password. Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
                            staffEditType();
                        }
                        cust.getItem(custEditChoice).setPassword(edit);
                        System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "Successfully Modified ! " + FioreFlowershop.ConsoleColors.RESET);
                    } catch (Exception e) {
                        System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "An Error Occurred, Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
                        staffEditType();
                    }

                }
                custEdit = null;
                staffNextOption();
            } else if (corpEdit != null) {
                managerEditCorporate(corpEditChoice);
            } else {
                System.out.println(FioreFlowershop.ConsoleColors.RED + "\nSorry, No Records Found !" + FioreFlowershop.ConsoleColors.RESET);
                staffNextOption();
            }
        } else {
            System.out.println(FioreFlowershop.ConsoleColors.RED + "\nSuch Empty, Much Wow !" + FioreFlowershop.ConsoleColors.RESET);
            staffNextOption();
        }

    }

    public static void managerEditCorporate(int corpEditChoice) {
        for (int i = 1; i <= corpC.getTotalEntries(); i++) {
            if (corpC.getItem(i).getEmail().equals(user.getItem(corpEditChoice).getEmail())) {
                corpEditChoice = i;
                break;
            }
        }
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
        int corpOptionChoice = s.nextInt();
        s.nextLine();
        if (corpOptionChoice == 1) {
            System.out.println("Old Username : " + corpC.getItem(corpEditChoice).getUsername());
            System.out.print("Please Enter The New Username : ");
            try {
                edit = s.nextLine();
                corpC.getItem(corpEditChoice).setUsername(edit);
                System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "Successfully Modified ! " + FioreFlowershop.ConsoleColors.RESET);
                staffNextOption();
            } catch (Exception e) {
                System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "An Error Occurred, Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
                staffEditType();
            }
        } else if (corpOptionChoice == 2) {
            System.out.println("Old Email : " + corpC.getItem(corpEditChoice).getEmail());
            System.out.print("Please Enter The New Email : ");
            try {
                edit = s.nextLine();
                if (!edit.contains("@")) {
                    System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "Invalid Email Entered. Please Retry." + FioreFlowershop.ConsoleColors.RESET);
                    staffEditType();
                }
                corpC.getItem(corpEditChoice).setEmail(edit);
                System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "Successfully Modified ! " + FioreFlowershop.ConsoleColors.RESET);
                staffNextOption();
            } catch (Exception e) {
                System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "An Error Occurred, Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
                staffEditType();
            }

        } else if (corpOptionChoice == 3) {
            System.out.println("Old Contact Number : " + corpC.getItem(corpEditChoice).getPhone());
            System.out.print("Please Enter The New Phone : ");
            try {
                edit = s.nextLine();
                if (!edit.matches("[0-9]+")) {
                    System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "Invalid Contact Number Entered. Please Retry." + FioreFlowershop.ConsoleColors.RESET);
                    staffEditType();
                }
                corpC.getItem(corpEditChoice).setPhone(edit);
                System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "Successfully Modified ! " + FioreFlowershop.ConsoleColors.RESET);
                staffNextOption();
            } catch (Exception e) {
                System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "An Error Occurred, Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
                staffEditType();
            }

        } else if (corpOptionChoice == 4) {
            System.out.println("Old Address : " + corpC.getItem(corpEditChoice).getAddress());
            System.out.print("Please Enter The New Address : ");
            try {
                edit = s.nextLine();
                corpC.getItem(corpEditChoice).setAddress(edit);
                System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "Successfully Modified ! " + FioreFlowershop.ConsoleColors.RESET);
                staffNextOption();
            } catch (Exception e) {
                System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "An Error Occurred, Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
                staffEditType();
            }

        } else if (corpOptionChoice == 5) {
            System.out.println("Old Password : " + corpC.getItem(corpEditChoice).getPassword());
            System.out.print("Please Enter The New Password : ");
            try {
                edit = s.nextLine();
                if (corpC.getItem(corpEditChoice).getPassword().equals(edit)) {
                    System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "Password cannot be same with old password. Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
                    staffEditType();
                }
                corpC.getItem(corpEditChoice).setPassword(edit);
                System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "Successfully Modified ! " + FioreFlowershop.ConsoleColors.RESET);
                staffNextOption();
            } catch (Exception e) {
                System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "An Error Occurred, Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
                staffEditType();
            }

        } else if (corpOptionChoice == 6) {
            System.out.println("Old Company : " + corpC.getItem(corpEditChoice).getCompany());
            System.out.print("Please Enter The New Company : ");
            try {
                edit = s.nextLine();
                if (corpC.getItem(corpEditChoice).getCompany().equals(edit)) {
                    System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "Company Name cannot be same as Old Company Name, Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
                    staffEditType();
                }
                corpC.getItem(corpEditChoice).setCompany(edit);
                System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "Successfully Modified ! " + FioreFlowershop.ConsoleColors.RESET);
                staffNextOption();
            } catch (Exception e) {
                System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "An Error Occurred, Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
                staffEditType();
            }
        } else if (corpOptionChoice == 7) {
            System.out.println("Old Credit Limit : " + corpC.getItem(corpEditChoice).getMonthlyLimit());
            System.out.print("Please Enter The New Credit Limit : ");
            try {
                editLimit = s.nextInt();
                s.nextLine();
                corpC.getItem(corpEditChoice).setMonthlyLimit(editLimit);
                System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "Successfully Modified ! " + FioreFlowershop.ConsoleColors.RESET);
                staffNextOption();
            } catch (Exception e) {
                System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "An Error Occurred, Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
                staffEditType();
            }

        }
    }

    public static void staffCreateCorporate() {
        boolean passwCheck = false;
        boolean exist = false;
        String passw = "";
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
                System.out.println("\n" + FioreFlowershop.ConsoleColors.RED_BOLD + "Password Mismatched, Please Try Again.\n" + FioreFlowershop.ConsoleColors.RESET);
            }
        } while (passwCheck == false);
        CorporateCustomer Corporate = new CorporateCustomer(usern, email, number, address, passw, company, creditLimit, true);

        if (user != null) {
            for (int i = 1; i <= user.getTotalEntries(); i++) {
                if (user.getItem(i).getEmail().equals(email) && user.getItem(i).getUsername().equals(usern)) {
                    System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "Sorry, Exisiting Account Found, Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
                    exist = true;
                    break;
                }
            }
        }
        if (!exist) {
            corpC.add(Corporate);
            user.add(Corporate);
            sortEmailOrder();
            System.out.println("\n" + FioreFlowershop.ConsoleColors.GREEN + "New Account Successfully Created ! " + FioreFlowershop.ConsoleColors.RESET);
        }
        FioreFlowershop.manager();
    }

    public static void staffNextOption() {
        System.out.println("\nPlease Enter What Would You like to do.");
        System.out.println("[1] Edit Another Customer Account");
        System.out.println("[2] Go Back to Main Menu");
        try {
            int staffNextOpt = s.nextInt();
            s.nextLine();
            switch (staffNextOpt) {
                case 1:
                    staffEditType();
                    break;
                case 2:
                    FioreFlowershop.userTypeSelection();
                    break;
            }
        } catch (Exception e) {
            System.out.println("\n" + FioreFlowershop.ConsoleColors.RED + "Please only Enter Number Listed. Please Try Again." + FioreFlowershop.ConsoleColors.RESET);
            staffNextOption();
        }
    }
}
