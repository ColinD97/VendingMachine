package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Machine {

    private List<Item> inventory = new ArrayList<>();
    private File inputFile;
    // inputFile is empty bucket that can take in any file put in and use it to load the inventory.
    private BigDecimal balance = new BigDecimal("0.00");

    public Machine(File inputFile) throws IOException {
        this.inputFile = inputFile;
        inventory = loadInventory();
        File file= new File("log.txt");
        file.createNewFile();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    private List<Item> loadInventory() {
        try (Scanner inventoryFile = new Scanner(inputFile)) {
            int counter = 0;
            while (inventoryFile.hasNextLine()) {
                String lineOfText = inventoryFile.nextLine();
                String[] itemLine = lineOfText.split("\\|");
                switch (itemLine[3]) {
                    case "Chip":
                        inventory.add(new Chips(itemLine[0], itemLine[1], itemLine[2]));
                        break;
                    case "Candy":
                        inventory.add(new Candy(itemLine[0], itemLine[1], itemLine[2]));
                        break;
                    case "Drink":
                        inventory.add(new Drinks(itemLine[0], itemLine[1], itemLine[2]));
                        break;
                    case "Gum":
                        inventory.add(new Gum(itemLine[0], itemLine[1], itemLine[2]));
                        break;
                    default:
                        break;
                }
                counter++;
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File not found");
        }
        return inventory;
    }


    public BigDecimal depositMoney(BigDecimal amountToDeposit) {
        balance = balance.add(amountToDeposit);
        audit("Feed Money", amountToDeposit, balance );
        return balance;
    }

    public void selectProduct(Item itemChosen){
        BigDecimal itemPrice = new BigDecimal("0");
        for(Item item : inventory){
            if (item.equals(itemChosen)){
                if(item.getQuantity() >0 && item.getPrice().compareTo(balance) <= 0) {
                    item.deincrementQuantity();
                    System.out.println(item.getSound());
                    itemPrice = item.getPrice();
                    balance = balance.subtract(itemPrice);
                    audit((item.getProductName() + " " + item.getSlot()), item.getPrice(), balance  );
                } else if (item.getQuantity() <= 0){
                    System.out.println("SOLD OUT");
                } else if (item.getPrice().compareTo(balance) >= 0){
                    System.out.println("INSUFFICIENT FUNDS");
                }
            }
        }
    }

        public CoinPurse makeChange(){
            CoinPurse purse = new CoinPurse();
            int quarter=0;
            int dime=0;
            int nickel=0;
            BigDecimal hundred= new BigDecimal("100");
            BigDecimal tempBalance=balance.multiply(hundred);
            int change= (tempBalance.intValue());
            while (change>=25){
                change-=25;
                quarter++;
            }
            while (change>=10){
                change-=10;
                dime++;
            }
            while (change>=5){
                change-=5;
                nickel++;
            }
            purse.setCoinQuarter(quarter);
            purse.setCoinDime(dime);
            purse.setCoinNickel(nickel);

            audit("Give change ", balance,new BigDecimal("0") );
            balance=new BigDecimal("0");
            return purse;
    }


    public void salesReport(){
        BigDecimal salesTotal = new BigDecimal("0");
        int quantityItem = 0;
        System.out.println("Generating Sales Report...");
        for (Item item : inventory){
            BigDecimal itemQuantity = new BigDecimal(0);
            itemQuantity = BigDecimal.valueOf(Item.STARTING_QUANTITY - item.getQuantity());
            salesTotal = salesTotal.add(item.getPrice().multiply(itemQuantity));
        }
        try (PrintWriter printWriter = new PrintWriter("SalesReport.txt")){
            for(Item item : inventory){
                printWriter.println(item.getProductName() +" | "+ (Item.STARTING_QUANTITY - item.getQuantity()));
            }
            printWriter.println();
            printWriter.println("**TOTAL SALES** $" + salesTotal);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void audit(String label, BigDecimal modification, BigDecimal result  ) {

        try (PrintWriter pw = new PrintWriter(new FileOutputStream("log.txt", true))) {
            pw.println(LocalDateTime.now() + " " + label + " $" + modification + " $" + result);

        } catch (FileNotFoundException e) {
            System.out.println("Log file could not be found");
        }
    }
}



