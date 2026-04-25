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

        switch (command){
            case 1:
                shoppingMenu();
                break;
            case 2:
                displayCart();
                break;
            case 3:
                break;
            default:
                System.out.println("invalid input try again!");
        }
       }while(command != 3);

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
     */
    private static void shoppingMenu() {
        int command;
        do{
            command = Console.promptForInt("""
                   Select an Option:
                   1: Display all Product
                   2: Search by name
                   3: Filter
                   4: Add to Cart
                   5: Go back""",1,5);
        switch (command) {
            case 1:
                allProducts();
                break;
            case 2:
                lookupByName();
                break;
            case 3:
                filter();
                break;
            case 4:
                processCart();
                break;
            case 5:
                break;
            default:
                System.out.println("invalid input try again!");
        }
        }while(command != 5);


    }



    /**
     * goes through all Products in arraylist inventory and send each one to displayProduct for displaying
     */
    private static void allProducts() {
        for( Product p:getInventory()){
            displayProducts(p);
        }
    }

    /**
     * Give the user the option to look for an item by its name
     */
    private static void lookupByName() {
        int command;
        do{
            command = Console.promptForInt("""
                    1: Proceed with search by name:
                    2: Go back""",1,2);
            switch (command){
                case 1:
                    String name = Console.promptForString("Enter the product name: ");
                    Product matchedProduct = getInventoryKeyName(getInventory()).get(name);
                    if(matchedProduct!=null){
                        displayProducts(matchedProduct);
                    }
                    else{
                        System.out.println("opps! Look like we don't have that item");
                    }
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid input! Try Again!!");
            }
        }while (command!=2);
    }

    /**
     * asks user for filter criteria and displays the eligible items
     */
    private static void filter() {
        int command;
        do{
            command = Console.promptForInt("""
                    1: Filter by Price:
                    2: Filter by Department
                    3: Go back""",1,3);
            switch (command){
                case 1:
                    filterByPrice();
                    break;
                case 2:
                    filterByDepartment();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid input! Try Again!!");
            }
        }while (command!=3);
    }

    /**
     * Asks the user for a department and displays all products in said department
     */
    private static void filterByDepartment() {
        int command;
        do{
            command = Console.promptForInt("""
                    1: Proceed with search:
                    2: Go back""",1,2);
            switch (command) {
                case 1:
                    String department = Console.promptForString("Enter the department name:");
                    for (Product p : getInventory()) {
                        if (p.getDepartment().equalsIgnoreCase(department)) {
                            displayProducts(p);
                        }}
                        break;
                        case 2:
                            break;
                        default:
                            System.out.println("Invalid input! Try Again!!");
            }}while (command!=2);

    }

    /**
     * asks for price range and filters product according to it
     */
    private static void filterByPrice() {
        int command;
        do{
            command = Console.promptForInt("""
                    1: Proceed with search:
                    2: Go back""",1,2);
            switch (command) {
                case 1:
                    System.out.println("Enter your price range!");
                    float rangeStart = Console.promptForFloat("Enter the minimum:");
                    float rangeEnd = Console.promptForFloat("Enter the maximum:");
                    for (Product p : getInventory()) {
                        if (p.getPrice() >= rangeStart && p.getPrice() <= rangeEnd) {
                            displayProducts(p);
                        }
                    }
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid input! Try Again!!");
            } }while (command!=2);
        }

    /**
     * creates the cart hash map
     * @return the hashmap
     */
    private static HashMap<String, Cart> getCart() {
        HashMap<String, Cart> cart = new HashMap<>();
        return cart;
    }

    /**
     * Asks the user for name look for it and send the required param to addToCart(
     */
    private static void processCart() {
        HashMap<String, Product> inventory = getInventoryKeyName(getInventory());
        String name = Console.promptForString("Enter the Name of the product");
        Product matched = inventory.get(name);
        if(matched!=null){
        addToCart(name,matched.getPrice());
        }else{
            System.out.println("No match found! Try again");
        }

    }
    /**
     * gets names and price and adds it to the cart if item already in cart increases the quantity
     * @param name of the item
     * @param price of the item
     */
    private static void addToCart(String name, float price) {
        HashMap<String, Cart> cart = getCart();
        Cart matched = cart.get(name);
        if(matched == null){
        cart.put(name,new Cart(name,price,1));}
        else{
            //todo rethink the logic
            cart.put(name,new Cart(name,price, matched.getQuantity()+1));
        }}
    /**
     * displays the Cart details in a table format
     */
    private static void displayCart() {
        System.out.println("I run");
        for(Cart c : getCart().values()){
        System.out.printf("%-35s %-15.2f %-15d\n", c.getName(),c.getPrice(),c.getQuantity());
        }
    }
    /**
     * displays the product details in a table format
     * @param p takes a Product class as parameter
     */
    private static void displayProducts(Product p) {
        System.out.printf("%-15s %-35s %-15.2f %-15s\n", p.getSku(), p.getName(),p.getPrice(),p.getDepartment());
    }
}
