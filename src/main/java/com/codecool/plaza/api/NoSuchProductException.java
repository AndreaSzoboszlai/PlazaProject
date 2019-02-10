package com.codecool.plaza.api;

public class NoSuchProductException extends ShopException {

    public NoSuchProductException() {
        super("No such a product");
    }
}
