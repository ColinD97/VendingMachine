package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Item{
    public Gum(String slot, String productName, String price) {
        super(slot, productName, price);
    }

    @Override
    public String getSound() {
        String newLine = System.getProperty("line.separator");
        String asciiText = String.join(newLine,
                "+-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+",
                "|C| |h| |e| |w|   |C| |h| |e| |w| |,|   |Y| |u| |m| |!|",
                "+-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+ +-+   +-+ +-+ +-+ +-+");
        return asciiText;
    }
}
