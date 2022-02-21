package com.techelevator;

import java.math.BigDecimal;

public class Chips extends Item{
    public Chips(String slot, String productName, String price) {
        super(slot, productName, price);
    }

    @Override
    public String getSound() {
        String newLine = System.getProperty("line.separator");
        String asciiText = String.join(newLine,
                "+-+ +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+",
                "|C| |r| |u| |n| |c| |h|   |C| |r| |u| |n| |c| |h| |,|   |Y| |u| |m| |!|",
                "+-+ +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+");
        return asciiText;
    }
}
