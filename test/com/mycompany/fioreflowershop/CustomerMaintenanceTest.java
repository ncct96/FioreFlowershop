/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop;

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
     * Test of customerOptions method, of class CustomerMaintenance.
     */
    @Test
    public void testCustomerOptions() {
        System.out.println("customerOptions");
        CustomerMaintenance.customerOptions();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of CreateAccount method, of class CustomerMaintenance.
     */
    @Test
    public void testCreateAccount() {
        System.out.println("CreateAccount");
        CustomerMaintenance.CreateAccount();
        // TODO review the generated test code and remove the default call to fail.
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
     * Test of CustLogIn method, of class CustomerMaintenance.
     */
    @Test
    public void testCustLogIn() {
        System.out.println("CustLogIn");
        CustomerMaintenance.CustLogIn();
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
     * Test of staffNextOption method, of class CustomerMaintenance.
     */
    @Test
    public void testStaffNextOption() {
        System.out.println("staffNextOption");
        CustomerMaintenance.staffNextOption();
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
