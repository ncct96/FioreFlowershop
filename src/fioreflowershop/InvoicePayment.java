/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop;

import fioreflowershop.modal.CatalogOrder1;
import fioreflowershop.modal.CorporateCustomer;
import fioreflowershop.modal.User;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class InvoicePayment {
    static Scanner s = new Scanner(System.in);
    
    public static void invoiceMaintenance(){
        System.out.println("\nPlease Select The Options Below.");
        System.out.println("[1] Make Corporate Customer Invoice Payment.");
        System.out.println("[2] Generate Invoice Payment for Corporate Customer.");
        System.out.println("[3] Back to Main Menu.");
        int invoiceChoice = s.nextInt();
        switch(invoiceChoice){
            case 1:invoicePaymentP1();break;
            case 2://generate and verify whether paid or not.
            case 3:FioreFlowershop.counterStaff();
        }
    }
    
    public static void invoicePaymentP1(){
        System.out.println("\nPlease Select The Corporate Customer for to make their payment.");
        if(FioreFlowershop.getShoppingCart() != null){
            for (int i = 1; i <= FioreFlowershop.getShoppingCart().getTotalEntries() ;i++){
                if(FioreFlowershop.getShoppingCart().getItem(i).getCorporate()!= null){
                    System.out.println(FioreFlowershop.ConsoleColors.BLUE + "[" + i + "]" + FioreFlowershop.ConsoleColors.RESET
                    + FioreFlowershop.getShoppingCart().getItem(i).getCorporate().getUsername());
                }
            }
            System.out.print("\nPlease Enter The Number Listed : "); int corporateChoice = s.nextInt();
            invoicePaymentP2(FioreFlowershop.getShoppingCart().getItem(corporateChoice));
        } else {
            System.out.println(FioreFlowershop.ConsoleColors.RED+"Sorry, No Records Found !"+FioreFlowershop.ConsoleColors.RESET);
        }
    }
    
    public static void invoicePaymentP2(CatalogOrder1 shopping){
        
        System.out.println("\nFiore Flowershop SDN.BHD \t\t\t\t\t\t"+ FioreFlowershop.ConsoleColors.BLACK_BOLD +" INVOICE");
        System.out.println("\nQ-5-1, Desa Permai Indah");
        System.out.println("Bandar Gelugor, 11700 Pulau Pinang \t\t\t\t\t" + "INVOICE #[100]");
        System.out.println("Phone : 0125566922 \t\t\t\t\t\t\t" + "DATE: OCT 9, 2018");
        
        System.out.println("\nTO:");
        System.out.println("[Name]");
        System.out.println("[Company Name]");
        System.out.println("[Street Address]");
        System.out.println("[City, ST ZIP Code]");
        System.out.println("[Phone]");
        System.out.println("=================================================================================================");
        System.out.println("Quantity \t | Description \t\t\t\t\t\t | Unit Price(RM) | Total");
        System.out.println("1 \t\t | Flowerisdgsadfg \t\t\t\t\t\t | 100.00 \t\t | 5");
    }
}
