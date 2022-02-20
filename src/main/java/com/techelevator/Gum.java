package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Item{
    public Gum(String slot, String productName, String price) {
        super(slot, productName, price);
    }

    @Override
    public String getSound() {
        return "Chew Chew, Yum!";
    }
}
