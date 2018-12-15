/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.adt.LinkedList;
import com.mycompany.fioreflowershop.modal.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class CustomerMaintenanceTest {
    
    public CustomerMaintenanceTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of sortEmailOrder method, of class CustomerMaintenance.
     */
    @Test
    public void testSortEmailOrder() {
        System.out.println("sortEmailOrder");
        CustomerMaintenance.sortEmailOrder();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of staffEditType method, of class CustomerMaintenance.
     */
    @Test
    public void testStaffEditType() {
        System.out.println("staffEditType");
        CustomerMaintenance.staffEditType();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of managerEditCorporate method, of class CustomerMaintenance.
     */
    @Test
    public void testManagerEditCorporate() {
        System.out.println("managerEditCorporate");
        int corpEditChoice = 0;
        CustomerMaintenance.managerEditCorporate(corpEditChoice);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of staffCreateCorporate method, of class CustomerMaintenance.
     */
    @Test
    public void testStaffCreateCorporate() {
        System.out.println("staffCreateCorporate");
        CustomerMaintenance.staffCreateCorporate();
        // TODO review the generated test code and remove the default call to fail.
    }
    
    /**
     * Test of checkDuplicate method, of class CustomerMaintenance.
     */
    @Test
    public void testCheckDuplicate() {
        System.out.println("checkDuplicate");
        LinkedList<User> user = FioreFlowershop.getUser();
        System.out.println("Pre Add Results : " + user);
        String usern = "junitTest";
        String passw = "abcdef";
        String email = "junittest@example.com";
        String number = "0123456789";
        String address = "junit test address";
        CustomerMaintenance.checkDuplicate(usern, passw, email, number, address);
        System.out.println("Post Add results : "+user.toString());
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of custLogInVerify method, of class CustomerMaintenance.
     */
    @Test
    public void testCustLogInVerify() {
        System.out.println("custLogInVerify");
        String email = "notValidAccount@example.com";
        String passw = "abcdef";
        CustomerMaintenance.custLogInVerify(email, passw);
        // TODO review the generated test code and remove the default call to fail.
    }
}
