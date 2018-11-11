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
    private boolean creditService;
    
    public CorporateCustomer(){
        
    }
    
    public CorporateCustomer(String username, String email, String phone, String address, String password, String company){
        super(username, email, phone, address, password);
        this.company = company;
    }

    public void setCreditService(boolean creditService){
        this.creditService = creditService;
    }
    
    public boolean getCreditService(){
        return creditService;
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
        return "\nCompany Name : " + company + "\nCredit Service : " + creditService + "\nPreset Credit Limit : " + monthlyLimit;
    }
    
}
