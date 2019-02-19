package com.codecool.plaza.cmdprog;

import com.codecool.plaza.api.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CmdProgram {
    private Scanner reader = new Scanner(System.in);
    private List<Product> cart;
    private List<Float> prices;
    private PlazaImpl plaza;

    public CmdProgram(String[] args) {

    }

    public void run() {
        start();
    }

    public void start() {
        System.out.println("There are no plaza created yet! Press\n" +
                "1) to create a new plaza.\n" +
                "2) to exit.");
            int option = Integer.parseInt(reader.nextLine());

        switch (option) {
            case 1: System.out.println("New plaza's name: ");
                    plaza = new PlazaImpl(reader.nextLine());
                    shopMenu();
                    break;
            case 2: break;
        }
    }

    public void shopMenu() {
        while(true) {
        System.out.println("Welcome to the " + plaza.getName() + " plaza! Press\n" +
                "1) to list all shops.\n" +
                "2) to add a new shop.\n" +
                "3) to remove an existing shop.\n" +
                "4) enter a shop by name.\n" +
                "5) to open the plaza.\n" +
                "6) to close the plaza.\n" +
                "7) to check if the plaza is open or not.\n" +
                "8) leave plaza.");

            switch (reader.nextLine()) {
                case "1":
                    listAllshop();
                    break;
                case "2":
                    System.out.println("Give a shop name: ");
                    String name = reader.nextLine();
                    System.out.println("Give a shop owner:");
                    String owner = reader.nextLine();
                    try {
                        plaza.addShop(new ShopImpl(name, owner));
                    } catch (ShopAlreadyExistsException | PlazaIsClosedException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    System.out.println("To remove a shop: ");
                    System.out.println("Give a shop name: ");
                    String sName = reader.nextLine();
                    Shop shop = null;
                    try {
                        shop = plaza.findShopByName(sName);
                        plaza.removeShop(shop);
                    } catch (NoSuchShopException | PlazaIsClosedException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "4": try {
                        System.out.println("Give a shop name");
                        String shopName = reader.nextLine();
                        shopMenuByName(findShop(shopName));
                        } catch (NoSuchShopException ex) {
                            System.out.println(ex.getMessage());
                        }
                         break;
                case "5": plaza.open();
                    System.out.println("Plaza is opened");
                        break;
                case "6": plaza.close();
                    System.out.println("Plaza is closed down");
                        break;
                case "7": System.out.println(plaza.isOpen());
                        break;
                case "8": start();
                        break;
                default:
                    System.out.println("No option like that");
                    break;
            }
        }



    }

    public void listAllshop() {
        List<Shop> shops = null;
        try {
            shops = plaza.getShops();
            System.out.println("Existing shops: ");

            for (Shop shop : shops) {
                System.out.println(shop);
            }
        } catch (PlazaIsClosedException e) {
            System.out.println(e.getMessage());
        }
    }

    public Shop findShop(String name) throws NoSuchShopException{
        Shop shopFound = null;
        try {
            for (Shop shop : plaza.getShops()) {
                if (shop.getName().equals(name)) {
                    shopFound = shop;
                }
            }
        } catch (PlazaIsClosedException ex) {
            System.out.println(ex.getMessage());
        }
        if (shopFound == null) {
            throw new NoSuchShopException();
        }
        return shopFound;

    }

    public void shopMenuByName(Shop shop) {
        System.out.println("Hi! This is the " + shop.getName() + " , welcome! Press\n" +
                "1) to list available products.\n" +
                "2) to find products by name.\n" +
                "3) to display the shop's owner.\n" +
                "4) to open the shop.\n" +
                "5) to close the shop.\n" +
                "6) to add new product to the shop.\n" +
                "7) to add existing products to the shop.\n" +
                "8) to buy a product by barcode.\n" +
                "9) check price by barcode\n" +
                "10) exit from shop.");

        while(true) {
            switch (reader.nextLine()) {
                case "1": try {
                            listProducts(shop.getProducts());
                            } catch (ShopIsClosedException ex) {
                                System.out.println(ex.getMessage());
                            }
                            break;
                case "2":
                        System.out.println("Product you want to find: ");
                        String product = reader.nextLine();
                        try {
                            System.out.println(shop.findByName(product).toString());
                        } catch (NoSuchProductException | ShopIsClosedException ex) {
                            System.out.println(ex.getMessage());
                        }

                case "3":
                        System.out.println(shop.getOwner());
                        break;
                case "4": shop.open();
                            System.out.println("shop is open now.");
                            break;
                case "5": shop.close();
                        System.out.println("Shop is closed now.");
                        break;
                case "6": //shop.addNewProduct();
                        break;
                case "7": //shop.addProduct();
                        break;
                case "8": buyByBarcode(shop);
                        break;
                case "9": checkPriceByBarcode(shop);
                case "10": break;
                default: System.out.println("No option like that");
                        break;
            }
        }

    }

    public void listProducts(List<Product> products) {
        for (Product product : products) {
            if (product instanceof FoodProduct) {
                if (((FoodProduct) product).isStillConsumable() == true) {
                    System.out.println(product.toString());
                }
            } else if (product instanceof ClothingProduct) {
                System.out.println(product.toString());
            }
        }
    }

    public Product addOne() {
        System.out.println("Create a product: ");
        System.out.println("1. Food product \n2. Clothing product\n ");
        String chosen;
        while (true) {
            chosen = reader.nextLine();
            switch (chosen) {
                case "1": break;
                case "2": break;
                default:
                    System.out.println("not a valid option");
            }
        }
    }

    private void buyByBarcode(Shop shop) {
        System.out.println("Product's barcode: ");
        long barcode = Long.valueOf(reader.nextLine());
        try {
            shop.buyProduct(barcode);
            System.out.println("Successfully bought");
        } catch (NoSuchProductException | OutOfStockException | ShopIsClosedException ex) {
            System.out.println(ex.getMessage());
        }


    }

    private void checkPriceByBarcode(Shop shop) {
        System.out.println("Product's barcode: ");
        long barcode = Long.valueOf(reader.nextLine());
        try {
            System.out.println(shop.getPrice(barcode));
        } catch (NoSuchProductException | ShopIsClosedException ex) {
            System.out.println(ex.getMessage());
        }


    }





}
