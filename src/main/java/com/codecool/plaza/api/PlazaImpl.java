package com.codecool.plaza.api;

import java.util.List;
import java.util.ArrayList;

public class PlazaImpl implements Plaza {
    private List<Shop> shops;

    public PlazaImpl() {
        shops = new ArrayList<Shop>();
    }

    public List<Shop> getShops() throws PlazaIsClosedException {
        return shops;
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
        return false;
    }

    public void open() {

    }

    public void close() {

    }
}
