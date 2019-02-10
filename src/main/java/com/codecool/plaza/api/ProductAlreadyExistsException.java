package com.codecool.plaza.api;

public class ProductAlreadyExistsException extends ShopException {

    public ProductAlreadyExistsException() {
        super("Product already exists");
    }
}
