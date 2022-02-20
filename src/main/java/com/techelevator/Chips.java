package com.techelevator;

import java.math.BigDecimal;

public class Chips extends Item {


    public Chips(String slot, String productName, String price) {
        super(slot, productName, price);

    }

    @Override
    public String getSound() {

        return "Crunch Crunch, Yum!";
    }


}
