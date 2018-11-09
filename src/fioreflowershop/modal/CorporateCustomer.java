/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop.modal;

/**
 *
 * @author Admin
 */
public class CorporateCustomer extends User{
    private String company;
    private int monthlyLimit;
    
    public CorporateCustomer(){
        
    }
    
    public CorporateCustomer(String username, String email, String phone, String address, String password){
        super(username, email, phone, address, password);
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(int monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }

    @Override
    public String toString() {
        return "CorporateCustomer{" + "company=" + company + ", monthlyLimit=" + monthlyLimit + '}';
    }
    
}
