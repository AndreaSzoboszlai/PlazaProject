package com.codecool.plaza.api;

public class ShopAlreadyExistsException extends ShopException {

    public ShopAlreadyExistsException() {
        super("This shop already exists");
    }
}
