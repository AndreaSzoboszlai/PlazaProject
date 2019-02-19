package com.codecool.plaza.api;

import java.util.List;
import java.util.ArrayList;

public class PlazaImpl implements Plaza {
    private List<Shop> shops;
    private String name;
    private boolean isOpen;

    public PlazaImpl(String name) {
        this.shops = new ArrayList<Shop>();
        this.name = name;
        this.isOpen = false;
    }

    public List<Shop> getShops() throws PlazaIsClosedException {
        if (isOpen() == true) {
            return shops;
        }
        throw new PlazaIsClosedException();
    }

    public void addShop(Shop shop) throws ShopAlreadyExistsException, PlazaIsClosedException {
        shops.add(shop);
    }

    public void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException {
        Shop toRemove = null;
        for (Shop element : shops) {
            if (shop.equals(element)) {
                toRemove = shop;
            }
        }
        if (toRemove == null) {
            throw new NoSuchShopException();
        } else {
            shops.remove(toRemove);
        }
    }

    public Shop findShopByName(String name) throws NoSuchShopException, PlazaIsClosedException {
        for (Shop element : shops) {
            if (name.equals(element.getName())) {
                return element;
            }
        }
        throw new NoSuchShopException();
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void open() {
        isOpen = true;
    }

    public void close() {
        isOpen = false;
    }

    public String getName() {
        return name;
    }
}
