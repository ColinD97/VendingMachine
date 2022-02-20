package com.techelevator;

import javax.swing.plaf.metal.MetalCheckBoxIcon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
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
			System.out.println("1. Display Items");
			System.out.println("2. Purchase Items");
			System.out.println("3. Exit");
			System.out.println("Please enter a choice: ");
			String userChoice = userInput.nextLine();
			switch (userChoice) {
				case "1":
					//inventoryMaster.displayInventory();
					vm1.displayInventory();
					System.out.println();
					//System.out.println("choice 1");
					//isValidChoice = true;
					break;
				case "2":
					subMenu();
					//System.out.println("choice 2");
					//isValidChoice = true;
					break;
				case "3":
					// exit program
					//System.out.println("choice 3");
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

	public void subMenu() {
		boolean isValidChoice = false;
		while (!isValidChoice) {
			System.out.println("------------------------");
			System.out.println("1. Feed Money");
			System.out.println("2. Select Product");
			System.out.println("3. Finish Transaction");
			System.out.println("Please enter a choice: ");


			String userChoice = userInput.nextLine();
			switch (userChoice) {
				case "1":
					feedMoney();
					break;
				case "2":
					selectProduct();
					//isValidChoice = true;
					break;
				case "3":
					// exit program
					//TODO
					vm1.makeChange();
					//System.out.println("choice sub.3");
					isValidChoice = true;
					break;

				default:
					System.out.println("Not a valid entry");
					break;
			}
		}
	}


	private void feedMoney() {
		boolean isWholeNumber = false;
		double tempDouble = 0;
		while (!isWholeNumber) {
			System.out.println("How much would you like to deposit (whole bills only): ");
			String userChoice = userInput.nextLine();
			tempDouble = Double.parseDouble(userChoice);
			if (tempDouble % 1 == 0) {
				isWholeNumber = true;
			} else {
				System.out.println("Not a whole bill, please try again");
			}
		}
		BigDecimal tempBigDecimal = new BigDecimal(tempDouble);
		vm1.depositMoney(tempBigDecimal);
		System.out.println("balance: " + vm1.getBalance());
		//isValidChoice = true;
	}

	private void selectProduct() {

		// run loop through inventory to compare userInput to item.getSlot
		//		if equal, run selectProduct with lowercase check
		//		if not equal, ask again
		// vm1.selectProduct();
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
		System.out.println("balance: " + vm1.getBalance());
	}

}
