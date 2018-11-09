/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop;
import fioreflowershop.modal.CustomizedPackage;
import fioreflowershop.modal.Item;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Chiu Peeng
 */
  
public class CustomizePackage {
    private static ArrayList<Item> styles = new ArrayList<>();
    private static ArrayList<Item> sizes = new ArrayList<>();
    private static ArrayList<Item> flowers = new ArrayList<>();
    private static ArrayList<Item> accessories = new ArrayList<>();
    private static ArrayList<Item> priorities = new ArrayList<>();
    private static ArrayList<Item> deliveryTypes = new ArrayList<>();
    
    public static void CustomizePackageControl(){
        int style = 0, size = 0, flower = 0, accessory = 0, priority = 0, deliveryType = 0;
        boolean exception;
        Scanner scan = new Scanner(System.in);
        
        InitializePackages();
        
        do{
            try{
                exception = false;
                StartUI(1);
                style = scan.nextInt() - 1;
            }catch(Exception e){
                exception = true;
            }
        }while(exception == true);
        
        do{
            try{
                exception = false;
                StartUI(2);
                size = scan.nextInt() - 1;
            }catch(Exception e){
                exception = true;
            }
        }while(exception == true);
        
        do{
            try{
                exception = false;
                StartUI(3);
                flower = scan.nextInt() - 1;
            }catch(Exception e){
                exception = true;
            }
        }while(exception == true);
        
        do{
            try{
                exception = false;
                StartUI(4);
                accessory = scan.nextInt() - 1;
            }catch(Exception e){
                exception = true;
            }
        }while(exception == true);
        
        do{
            try{
                exception = false;
                StartUI(5);
                priority = scan.nextInt() - 1;
            }catch(Exception e){
                exception = true;
            }
        }while(exception == true);
        
        do{
            try{
                exception = false;
                StartUI(6);
                deliveryType = scan.nextInt() - 1;
            }catch(Exception e){
                exception = true;
            }
        }while(exception == true);
        
        CustomizedPackage order = new CustomizedPackage(styles.get(style), sizes.get(size), flowers.get(flower), accessories.get(accessory), priorities.get(priority), deliveryTypes.get(deliveryType));
        double price = order.CalculateOrder();
        order.minusQuantity();
        System.out.println(price);
    }
    
    public static void StartUI(int step) throws Exception{
        int i = 0;
        if(step == 1){
            System.out.println("Select the flower arrangement style");
            for(Item item:styles){
                ++i;
                System.out.printf("[%d] %s: RM%.2f\n", i, item.getName(), item.getPrice());
            }
        }
        else if(step == 2){
            System.out.println("Select the floral arrangement size");
            System.out.println("This will be multiplied by the selected flower's price");
            for(Item item:sizes){
                ++i;
                System.out.printf("[%d] %s: Flower Price x %.0f\n", i, item.getName(), item.getPrice());
            }
        }
        else if(step == 3){
            System.out.println("Select the flowers for the arrangement");
            for(Item item:flowers){
                if(item.getQuantity() > 0){
                    ++i;
                    System.out.printf("[%d] %s: RM%.2f\n", i, item.getName(), item.getPrice());
                }
            }
        }
        else if(step == 4){
            System.out.println("Select the accessory to be added");
            for(Item item:accessories){
                if(item.getQuantity() > 0){
                    ++i;
                    System.out.printf("[%d] %s: RM%.2f\n", i, item.getName(), item.getPrice());
                }
            }
        }
        else if(step == 5){
            System.out.println("Select the delivery priority");
            System.out.println("This will be multiplied by the sum of the floral arrangement");
            for(Item item:priorities){
                ++i;
                System.out.printf("[%d] %s: Order price x %.0f\n", i, item.getName(), item.getPrice());
            }
        }
        else if(step == 6){
            System.out.println("Select the delivery priority");
            for(Item item:priorities){
                ++i;
                System.out.printf("[%d] %s: Extra Charges: RM%.0f\n", i, item.getName(), item.getPrice());
            }
        }
        else{
            System.out.println("Oops, something went wrong");
            throw new Exception();
        }
    }
    
    public static void InitializePackages(){
       styles.add(new Item("Fan", 10));
       styles.add(new Item("Elliptical", 10));
       styles.add(new Item("Vertical", 10));
       styles.add(new Item("Horizontal", 10));
       styles.add(new Item("Triangular", 10));
       
       sizes.add(new Item("Small", 1));
       sizes.add(new Item("Medium", 2));
       sizes.add(new Item("Large", 4));
       
       flowers.add(new Item("Sunflowers", 250, 10));
       flowers.add(new Item("Red Roses", 300, 20));
       flowers.add(new Item("White Roses", 250, 10));
       flowers.add(new Item("Tulips", 450, 50));
       flowers.add(new Item("Daffodils", 200, 20));
       
       accessories.add(new Item("None", 0, 1));
       accessories.add(new Item("Name Card", 5, 20));
       accessories.add(new Item("Bears", 50, 5));
       accessories.add(new Item("Woven Basket", 35, 10));
       
       priorities.add(new Item("Flexi", 1));
       priorities.add(new Item("Normal", 5));
       priorities.add(new Item("Express", 10));
       
       deliveryTypes.add(new Item("Pickup", 0));
       deliveryTypes.add(new Item("Deliver", 10));
    }
    
}
