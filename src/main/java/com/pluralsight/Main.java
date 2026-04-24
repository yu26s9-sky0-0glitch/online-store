package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import com.pluralsight.ui.Console;

public class Main {
    static void main(){
        ArrayList<Product> inventory = getInventory();
        HashMap<String, Product> inventoryKeyName = getInventoryKeyName(inventory);
       int command;
       do{
           command = Console.promptForInt("""
                   Select an Option:
                   1: Display Product
                   2: Display Cart
                   3: Exit""",1,3);

       }while(command != 3);

        switch (command){
            case 1:
                shoppingMenu(inventory,inventoryKeyName);
                break;
            case 2:
                Cart();
                break;
            case 3:
                break;
            default:
                System.out.println("invalid input try again!");
        }




    }


    /**
     * loads the csv file splits it and loads it into a simple arraylist inventory
     * @return the inventory
     */

    private static ArrayList<Product>  getInventory() {
        ArrayList<Product> inventory = new ArrayList<Product>();
        try {
           FileReader fr = new FileReader("data/products.csv");
           BufferedReader bf = new BufferedReader(fr);
           bf.readLine();
           String input;
           while((input = bf.readLine()) != null){
               String[] parts = input.split("\\|");
               inventory.add(new Product(parts[0],parts[1],Float.parseFloat(parts[2]),parts[3]));
           }
       } catch (IOException e) {
           System.out.println(e.getMessage());
       }
        return inventory;
    }

    /**
     * get arraylist inventory and turns it into hashmap inventory with name as key
     * @param inventory as arraylist
     * @return inventory as HashMap with name key
     */
    private static HashMap<String, Product> getInventoryKeyName(ArrayList<Product>  inventory) {
        HashMap<String,Product> inventoryWithKey = new HashMap<>();
        for(Product p : inventory){
            inventoryWithKey.put(p.getName(),p);
        }
        return inventoryWithKey;
    }

    /**
     * display the list of products the store sells lets customer search filter add to cart and go back to main menu
     * @param inventory as arraylist for sorting by price and department
     * @param inventoryKeyName as hashmap for sorting through name
     */
    private static void shoppingMenu(ArrayList<Product> inventory, HashMap<String, Product> inventoryKeyName) {
    }

    /**
     * displays all the items inside the cart lets user checkout or remove items
     */
    private static void Cart() {
    }
















    /**
     * get arraylist inventory and turns it into hashmap inventory with department as key
     * @param inventory as arraylist
     * @return inventory as HashMap with department key
     */
//    private static HashMap<String, Product> getInventoryKeyDepartment(ArrayList<Product>  inventory) {
//        HashMap<String,Product> inventoryWithKey = new HashMap<>();
//        for(Product p : inventory){
//            inventoryWithKey.put(p.getDepartment(),p);
//        }
//        return inventoryWithKey;
//    }
//    /**
//     * get arraylist inventory and turns it into hashmap inventory with price as key
//     * @param inventory as arraylist
//     * @return inventory as HashMap with price key
//     */
//    private static HashMap<Float, Product> getInventoryKeyPrice(ArrayList<Product>  inventory) {
//        HashMap<Float,Product> inventoryWithKey = new HashMap<>();
//        for(Product p : inventory){
//            inventoryWithKey.put(p.getPrice(),p);
//        }
//        return inventoryWithKey;
//    }
}
