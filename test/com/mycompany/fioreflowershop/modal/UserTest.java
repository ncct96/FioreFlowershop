/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fioreflowershop.modal;

import com.mycompany.fioreflowershop.FioreFlowershop;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nicholas
 */
public class UserTest {
    
    public User instance;
    
    public UserTest() {
        String username = "Username";
        String email = "ceekay@gmail.com";
        String phone = "0123456789";
        String address = "Address";
        String password = "abcdef123";
        instance = new User(username, email, phone, address, password); 
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getUsername method, of class User.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        String expResult = "Username"; //Username
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setUsername method, of class User.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "Username";
        instance.setUsername(username);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getEmail method, of class User.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        String expResult = "ceekay@gmail.com";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setEmail method, of class User.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "ceekay@gmail.com";
        instance.setEmail(email);
        // TODO review the generated test code and remove the default call to fail.
    }
    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        String expResult = "abcdef123";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "abcdef123";
        instance.setPassword(password);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getAddress method, of class User.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        String expResult = "Address";
        String result = instance.getAddress();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setAddress method, of class User.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String address = "Address";
        instance.setAddress(address);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getPhone method, of class User.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        String expResult = "0123456789";
        String result = instance.getPhone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setPhone method, of class User.
     */
    @Test
    public void testSetPhone() {
        System.out.println("setPhone");
        String phone = "0123456789";
        instance.setPhone(phone);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of toString method, of class User.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "\n"+FioreFlowershop.ConsoleColors.BLUE+"Username : Username"+FioreFlowershop.ConsoleColors.RESET+"\n"
                +FioreFlowershop.ConsoleColors.BLUE+"Email : ceekay@gmail.com"+FioreFlowershop.ConsoleColors.RESET+"\n"
                +FioreFlowershop.ConsoleColors.BLUE+"Contact Number : 0123456789"+FioreFlowershop.ConsoleColors.RESET+"\n"
                +FioreFlowershop.ConsoleColors.BLUE+"Address : Address"+FioreFlowershop.ConsoleColors.RESET+"\n"
                +FioreFlowershop.ConsoleColors.BLUE+"Password : abcdef123"+FioreFlowershop.ConsoleColors.RESET;
        String result = instance.toString();
        System.out.println(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
