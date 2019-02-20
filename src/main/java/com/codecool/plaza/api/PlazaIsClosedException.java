package com.codecool.plaza.api;

public class PlazaIsClosedException extends ShopException{

    public PlazaIsClosedException() {
        super("The plaza is closed, press 5 to open it.");
    }
}
