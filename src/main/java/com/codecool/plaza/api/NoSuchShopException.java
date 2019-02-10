package com.codecool.plaza.api;

public class NoSuchShopException extends ShopException {

    public NoSuchShopException() {
        super("there is no shop like that");
    }
}
