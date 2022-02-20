package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Item{
    public Candy(String slot, String productName, String price) {
        super(slot, productName, price);
    }

    @Override
    public String getSound() {
        return "Munch Munch, Yum!";
    }
}
