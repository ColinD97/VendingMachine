package com.techelevator;

import java.math.BigDecimal;

public class Drinks extends Item{
    public Drinks(String slot, String productName, String price) {
        super(slot, productName, price);
    }

    @Override
    public String getSound() {
        String newLine = System.getProperty("line.separator");
        String asciiText = String.join(newLine,
                "+-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+",
                "|G| |l| |u| |g|   |G| |l| |u| |g| |,|   |Y| |u| |m| |!|",
                "+-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+");

        return asciiText;
    }
}
