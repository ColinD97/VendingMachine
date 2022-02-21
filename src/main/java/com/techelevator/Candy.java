package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Item{
    public Candy(String slot, String productName, String price) {
        super(slot, productName, price);
    }

    @Override
    public String getSound() {
        String newLine = System.getProperty("line.separator");
        String asciiText = String.join(newLine,
                "+-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+",
                "|M| |u| |n| |c| |h|   |M| |u| |n| |c| |h| |,|   |Y| |u| |m| |!|",
                "+-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+"
        );
        return asciiText;
       //return "Munch Munch, Yum!";
    }
}
