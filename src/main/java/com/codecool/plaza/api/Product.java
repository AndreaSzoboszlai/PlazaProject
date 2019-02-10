package com.codecool.plaza.api;

public abstract class Product {
    protected long barcode;
    protected String name;
    protected String manufacturer;

    protected Product(long barcode, String name, String manufacturer) {
        this.barcode = barcode;
        this.name = name;
        this.manufacturer = manufacturer;
    }

    public long getBarCode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String toString() {
        return "";
    }

}
