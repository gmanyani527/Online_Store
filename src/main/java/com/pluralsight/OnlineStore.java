package com.pluralsight;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.*;


public class OnlineStore {
    private static ArrayList<Product> products = new ArrayList<>();
    private static final String FILE_NAME = "products.csv";


    public static void main(String[] args) {
        loadProduct(FILE_NAME);
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
                            System.out.println("Enter a name: ");
                            String input3 = scan.nextLine();
                            System.out.println("Enter the ID number: ");
                            String input4 = scan.nextLine();
                            scan.nextLine();

                            for (Product product : products) {
                                if (input4 == product.getId()) {
                                    // product.setCheckedOut(true);
                                    // product.checkedOut(input3);
                                    System.out.println("Success! Item moved to cart!");
                                    System.out.println();
                                    System.out.println("Would you like buy something else ");
                                    String input5 = scan.nextLine();
                                    if (input5.equalsIgnoreCase("no")) {
                                        stillDeciding = false;
                                    }
                                    found = true;
                                    break;
                                }

                            }
                            if (!found) {
                                System.out.println("This item is not in our inventory");

                            }
                        } else {
                            stillDeciding = false;
                        }
                    }

                    break;
                case 2:// Show Checked Out Books
                    stillDeciding = true;
                    for (Product product : products) {
                        //   if (product.isCheckedOut()) {
                        System.out.println(product.getId() + " " + product.getProductName() + product.getPrice());
                        //  }
                    }

                    while (stillDeciding) {
                        System.out.println();
                        System.out.println("Would you like to add to cart? \n (Select C or X)");
                        String input6 = scan.nextLine();

                        if (input6.equalsIgnoreCase("x")) {
                            stillDeciding = false;
                        }
                        if (input6.equalsIgnoreCase("c")) {
                            System.out.println("Enter book ID to be checked in: ");

                            boolean found = false;
                            int input7 = scan.nextInt();
                            scan.nextLine();
                            for (Product product : products) {
                                if (input7 == book.getId() && book.isCheckedOut()) {
                                    book.checkIn();
                                    System.out.println("Book has been checked in!");
                                    System.out.println();
                                    System.out.println("Do you have another book to check in? ");
                                    String input8 = scan.nextLine();
                                    if (!input8.equalsIgnoreCase("yes")) {
                                        stillDeciding = false;
                                    }
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                System.out.println("This book was not checked out or does not exist");
                            }


                        }
                    }
                    System.out.println();
                    System.out.println("You have returned to the homepage! ");

                    break;


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

            String line;
            try {
                BufferedReader br = new BufferedReader(new FileReader("products.csv"));
                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        continue; // skip empty lines
                    }
                    String[] parts = line.split("\\|");
                    if (parts.length != 5) {
                        System.out.println("Skipping bad line: " + line);
                        continue; // skip invalid lines
                    }
                    String id = parts[1];
                    String productName = parts[2];
                    double price = Double.parseDouble(parts[3]);
                    products.add(new Product(id, productName, price));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return products;
        }



}