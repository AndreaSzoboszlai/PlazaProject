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
        try {
            findShopByName(shop.getName());
            if (isOpen == true) {
                shops.add(shop);
            } else {
                throw new PlazaIsClosedException();
            }
        } catch (NoSuchShopException ex) {
            throw new ShopAlreadyExistsException();
        }

    }

    public void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException {
        if (isOpen == true) {
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
        } else {
            throw new PlazaIsClosedException();
        }
    }

    public Shop findShopByName(String name) throws NoSuchShopException, PlazaIsClosedException {
        if (isOpen == true) {
            for (Shop element : shops) {
                if (name.equals(element.getName())) {
                    return element;
                }
            }
            throw new NoSuchShopException();
        } else {
            throw new PlazaIsClosedException();
        }
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
