package com.techelevator;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {
	private Scanner userInput = new Scanner(System.in);
	//public Inventory inventoryMaster= new Inventory();
	private Machine vm1;

	public VendingMachineCLI() throws IOException {
		File inputFile = new File("VendingMachine.txt");
		vm1 = new Machine(inputFile);
	}


	public static void main(String[] args) throws IOException {

		VendingMachineCLI cli = new VendingMachineCLI();
		// import inventory file and set up items
		cli.run();

	}

	public void run() {
		//inventoryMaster.inventoryB =  inventoryMaster.loadInventory();
		displayWelcomeMessage();
		mainMenuUI();
		userInput.close();
	}

	public void displayWelcomeMessage() {
		System.out.println("*************************************************************");
		System.out.println("*  Welcome to our vending machine application.");
		System.out.println("*************************************************************");
		System.out.println("");
	}

	public void mainMenuUI() {
		boolean isValidChoice = false;
		while (!isValidChoice) {
			System.out.println("_____MAIN MENU____");
			System.out.println("1. Display Items");
			System.out.println("2. Purchase Items");
			System.out.println("3. Exit");
			System.out.println("Please enter a choice (1-3): ");
			String userChoice = userInput.nextLine();
			switch (userChoice) {
				case "1":
					displayInventory();
					System.out.println();
					break;
				case "2":
					subMenu();
					break;
				case "3":
					isValidChoice = true;
					break;
				case "4":
					vm1.salesReport();
					break;
				default:
					System.out.println("Not a valid entry");
					break;
			}
		}
	}

	public void displayInventory() {
		List<Item> inventory = new ArrayList<>();
		inventory = vm1.getInventory();
		System.out.println();
		System.out.println("------------------------------");
		for (Item unit : inventory) {
			if (unit.getQuantity() <= 0){
				System.out.println(unit.getSlot() + " | SOLD OUT");
			} else {
				System.out.println(unit);
			}
		}
		System.out.println("------------------------------");

	}

	public void subMenu() {
		boolean isValidChoice = false;
		CoinPurse purse = new CoinPurse();
		while (!isValidChoice) {
			System.out.println("------------------------");
			System.out.println("___PURCHASE MENU___");
			System.out.println("1. Display Items");
			System.out.println("2. Feed Money");
			System.out.println("3. Select Product");
			System.out.println("4. Finish Transaction");
			System.out.println("Current Balance: $"+vm1.getBalance());
			System.out.println("Please enter a choice (1-4): ");

			String userChoice = userInput.nextLine();
			switch (userChoice) {
				case "1":
					displayInventory();
					System.out.println();
					break;
				case "2":
					feedMoneyDisplay();
					break;
				case "3":
					selectProductDisplay();
					break;
				case "4":
					BigDecimal changeBalance = vm1.getBalance();
					purse = vm1.makeChange();
					displayChange(purse, changeBalance);
					isValidChoice = true;
					break;
				default:
					System.out.println("Not a valid entry");
					break;
			}
		}
	}


	private void feedMoneyDisplay() {
		boolean isWholeNumber = false;
		double tempDouble = 0;
		while (!isWholeNumber) {
			try {
				System.out.println("How much would you like to deposit (whole bills only): ");
				String userChoice = userInput.nextLine();
				tempDouble = Double.parseDouble(userChoice);
				if (tempDouble % 1 == 0) {
					isWholeNumber = true;
				} else {
					System.out.println("Not a whole bill, please try again");
				}
			} catch (NumberFormatException e) {
				System.out.println("Not a valid entry");
			}
		}
		BigDecimal tempBigDecimal = new BigDecimal(tempDouble);
		vm1.depositMoney(tempBigDecimal);
		System.out.println("balance: " + vm1.getBalance());
	}

	private void selectProductDisplay() {
		// run loop through inventory to compare userInput to item.getSlot
		//		if equal, run selectProduct with lowercase check
		//		if not equal, ask again
		boolean isValidSlot = false;
		String userChoice= "";
		Item itemChoice = null;
		while (!isValidSlot) {
			System.out.println("Enter Slot code: ");
			userChoice = userInput.nextLine();
			for (Item var : vm1.getInventory()) {
				if (userChoice.equalsIgnoreCase(var.getSlot())) {
					isValidSlot = true;
					itemChoice = var;
					break;
				}
			}
			if(!isValidSlot){
				System.out.println("Not Valid. Pick again.");
			}
		}
		vm1.selectProduct(itemChoice);
		System.out.println("New balance: $" + vm1.getBalance());
	}


	private void displayChange(CoinPurse purse, BigDecimal changeGiven) {
		System.out.println();
		System.out.println("Return change: $"+ changeGiven);
		System.out.println("$"+purse.getValueQuarter() + " as " + purse.getCoinQuarter() + " quarters");
		System.out.println("$"+purse.getValueDime() + " as " + purse.getCoinDime() + " dimes");
		System.out.println("$"+purse.getValueNickel() + " as " + purse.getCoinNickel() + " nickels");
		System.out.println("Balance is: $" + vm1.getBalance());
		System.out.println();
	}

}
