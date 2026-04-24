package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    static void main(){
        ArrayList<Product> inventory = getInventory();
        HashMap<String, Product> inventoryKeyName = getInventoryKeyName(inventory);
        HashMap<Float, Product> inventoryKeyPrice = getInventoryKeyPrice(inventory);
        HashMap<String, Product> inventoryKeyDepartment = getInventoryKeyDepartment(inventory);






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
     * get arraylist inventory and turns it into hashmap inventory with department as key
     * @param inventory as arraylist
     * @return inventory as HashMap with department key
     */
    private static HashMap<String, Product> getInventoryKeyDepartment(ArrayList<Product>  inventory) {
        HashMap<String,Product> inventoryWithKey = new HashMap<>();
        for(Product p : inventory){
            inventoryWithKey.put(p.getDepartment(),p);
        }
        return inventoryWithKey;
    }
    /**
     * get arraylist inventory and turns it into hashmap inventory with price as key
     * @param inventory as arraylist
     * @return inventory as HashMap with price key
     */
    private static HashMap<Float, Product> getInventoryKeyPrice(ArrayList<Product>  inventory) {
        HashMap<Float,Product> inventoryWithKey = new HashMap<>();
        for(Product p : inventory){
            inventoryWithKey.put(p.getPrice(),p);
        }
        return inventoryWithKey;
    }
}
