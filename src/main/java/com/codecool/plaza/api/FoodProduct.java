package com.codecool.plaza.api;

import java.util.Date;

public class FoodProduct extends Product {
    private int calories;
    private Date bestBefore;

    public FoodProduct(long barcode, String name, String manufacturer, int calories, Date bestBefore) {
        super(barcode, name, manufacturer);
        this.calories = calories;
        this.bestBefore = bestBefore;
    }

    public boolean isStillConsumable() {
        Date current = new Date();
        long other = bestBefore.getTime();
        long now = current.getTime();

        if (now < other) {
            return true;
        } else if (now > other) {
            return false;
        }
        return true;
    }

    public int getCalories() {
        return calories;
    }

    public String toString() {
        return getBarCode() + ", " + getName() + ", " + getManufacturer() + ", " + calories + ", " + bestBefore;
    }
}
