package com.codecool.plaza.api;

public class ShopIsClosedException extends ShopException {

    public ShopIsClosedException() {
        super("Shop is closed right now");
    }
}
