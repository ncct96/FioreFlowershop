/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

import com.mycompany.fioreflowershop.adt.LinkedList;
import com.mycompany.fioreflowershop.modal.Consumer;
import com.mycompany.fioreflowershop.modal.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class CustomerMaintenanceTest {
    LinkedList<User> user = FioreFlowershop.getUser();
    public CustomerMaintenanceTest() {
    }
    
    @Before
    public void setUp() {
    }
    /**
     * Test of checkDuplicate method, of class CustomerMaintenance.
     */
    @Test
    public void testCheckDuplicate() {
        System.out.println("checkDuplicate");
        System.out.println("Pre Add Results : " + user);
        String usern = "junitTest";
        String passw = "abcdef";
        String email = "junittest@example.com";
        String number = "0123456789";
        String address = "junit test address";
        CustomerMaintenance.checkDuplicate(usern, passw, email, number, address, "", 0, "Customer");
        System.out.println("Post Add results : "+user.toString());
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of custLogInVerify method, of class CustomerMaintenance.
     */
    @Test
    public void testCustLogInVerify() {
        System.out.println("custLogInVerify");
        String email = "ceekay@example.com";
        String passw = "abcdef123";
        CustomerMaintenance.custLogInVerify(email, passw);
        // TODO review the generated test code and remove the default call to fail.
    }
}
