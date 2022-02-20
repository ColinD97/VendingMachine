package com.techelevator;

import java.math.BigDecimal;

public class Item {

    public static final int STARTING_QUANTITY = 5;
    private String slot;
    private String productName;
    private BigDecimal price;
    private int quantity;
    private String sound;

    public Item(String slot, String productName, String price){
        this.slot=slot;
        this.productName= productName;
        this.price = new BigDecimal(price);
        this.quantity = STARTING_QUANTITY;
    }

    public String getSlot() {
        return slot;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSound() {
        return sound;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void deincrementQuantity(){
        quantity--;
    }

    @Override
    public String toString() {
        return  getSlot() + " " + getProductName() + " "+ getPrice() + " quantity: " + getQuantity() ;
    }
}

