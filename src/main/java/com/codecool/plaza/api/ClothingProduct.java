package com.codecool.plaza.api;

import java.util.Date;


public class ClothingProduct extends Product {
    private String material;
    private String type;

    public ClothingProduct(long barcode, String name, String manufacturer, String material, String type) {
        super(barcode, name, manufacturer);
        this.material = material;
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return "Barcode: " + getBarCode() + ", Name: " + getName() + ", MAnufacturer: " + getManufacturer() + ", Material " + material + ", Type: " + type;
    }
}
