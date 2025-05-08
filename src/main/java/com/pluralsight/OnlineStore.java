package com.pluralsight;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;

import java.util.*;


public class OnlineStore {
    private static ArrayList<Product> products = new ArrayList<>();
    private static final String FILE_NAME = "products.csv";
    private static ArrayList<Product> cart = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        boolean stillDeciding = true;


        boolean on = true;

        System.out.println("--------------------------------");
        System.out.println();
        System.out.println("         HOME SCREEN");
        System.out.println("Welcome to the Universal Library");
        System.out.println();
        System.out.println("--------------------------------");

        while (on) {
            System.out.println();
            System.out.println("1. Show Products \n2. Show Cart \n3. Exit \n(Enter number selection)");


            int userInput = scan.nextInt();
            scan.nextLine();
            switch (userInput) {
                case 1: // Show Available Books
                    stillDeciding = true;
                    loadProduct(FILE_NAME);
                    for (Product product : products) {
                        if (product != null) {
                            System.out.println(product);
                        }
                    }
                    System.out.println();
                    while (stillDeciding) {
                        boolean found = false;
                        System.out.println("Would you like to add a product to the cart \n(yes/no) ");
                        String input2 = scan.nextLine();

                        if (input2.equalsIgnoreCase("yes")) {
                            addToCart();
                        } else {
                            stillDeciding = false;
                        }
                    }

                    break;
                case 2:// Show Checked Out Books
                    loadProduct(FILE_NAME);
                    stillDeciding = true;
                    while (stillDeciding) {
                        System.out.println("Items currently in your cart");
                        double total_salesAmount = 0;
                        if (cart.isEmpty()) {
                            System.out.println("Your cart is empty");
                        } else {
                            for (Product item : cart) {
                                System.out.println(item);
                                total_salesAmount += item.getPrice();
                            }
                            System.out.printf("Total: $%.2f%n", total_salesAmount);
                        }
                        System.out.println();
                        System.out.println("Would you like to add to cart? \n (Select C or X)");
                        String input6 = scan.nextLine();

                        if (input6.equalsIgnoreCase("x")) {
                            stillDeciding = false;
                        } else if (input6.equalsIgnoreCase("c")) {
                            System.out.println("Enter the product ID you'd like to add to cart:");
                            String input7 = scan.nextLine();


                            boolean found = false;
                            for (Product product : products) {
                                if (product.getId().equalsIgnoreCase(input7)) {
                                    cart.add(product);

                                    System.out.println("Product found and added to cart!");
                                    // You can write this to a cart.csv file if needed
                                    System.out.println("Updated Cart:");

                                    double updatedTotal = 0;
                                    for (Product item : cart) {
                                        System.out.println(item);
                                        updatedTotal += item.getPrice();
                                    }
                                    System.out.printf("New total: $%.2f%n", updatedTotal);
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                System.out.println("Product ID not found in inventory.");
                            }


                        } else{
                                System.out.println("Invalid input. Please select C or X.");
                            }
                        }



                    System.out.println();
                    System.out.println("You have returned to the homepage! ");

                    break;

                case 3:
                    
                case 4: // Exit
                    System.out.println();
                    System.out.println("See ya next Time!");
                    on = false;
                    break;
                default:
                    System.out.println("I did not understand the inputs. Can you try again? ");


                    break;
            }
        }
    }
        public static ArrayList<Product> loadProduct(String fileName){
            products.clear(); // Prevent reloading duplicates

            String line;
            try {
                BufferedReader br = new BufferedReader(new FileReader("products.csv"));
                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        continue; // skip empty lines
                    }
                    String[] parts = line.split("\\|");
                    if (parts.length != 3) {
                        System.out.println("Skipping bad line: " + line);
                        continue; // skip invalid lines
                    }
                    String id = parts[0];
                    String productName = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    products.add(new Product(id, productName, price));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return products;
        }

    private static void addToCart() {
        Scanner scanner = new Scanner(System.in);
        boolean rightAnswer = true;
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("products.csv", true));
            // Collect date, time, description, vendor, and amount from user
            System.out.println("Enter the product Id:  ");
            String inputID = scanner.nextLine();
            System.out.println(inputID);

            System.out.println("Enter the product name: ");
            String inputName = scanner.nextLine();
            System.out.println(inputName);

            System.out.println("Enter the price of the product: ");
            double inputPrice = scanner.nextDouble();
            scanner.nextLine();
            System.out.println(inputPrice);
            // Create and store transaction
            boolean found = false;
            for (Product item : cart) {
                if (item.getId().equalsIgnoreCase(inputID)) {
                    System.out.println("This product is already in your cart.");
                    return;
                }
            }
            if (!found) {
                System.out.println("This item is not in our inventory");
                Product product = new Product(inputID, inputName, inputPrice );
                products.add(product);

                cart.add(product);
                bufferedWriter.write(product.toString());
                bufferedWriter.newLine();
                System.out.println("Product successfully added to inventory and cart!");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}