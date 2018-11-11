/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop;
import fioreflowershop.CustomizePackage;
import fioreflowershop.modal.Customer;
/**
 *
 * @author Chiu Peeng
 */
public class FioreFlowershop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Customer customer = new Customer();
        
        CustomizePackage.CustomizePackageControl(customer);
        CatalogMaintenance.catalogMaintenanceMenu();
    }
    public void startUI(){
        
    }
}
