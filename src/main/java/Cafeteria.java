package main.java;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Cafeteria class
 * 
 * @author Nicholas Ong
 */
public class Cafeteria {

	private boolean open;
	private Menu[] menu;
	private int ID = 101;
	private ArrayList<Order> ar = new ArrayList<>();

	/**
	 * Default constructor with default menu
	 */
	public Cafeteria() {
		open = false;
		menu = new Menu[10];
		initMenu();
	}

	/**
	 * Cafeteria constructor with customized menu of choice
	 * @param menu
	 */
	public Cafeteria(Menu[] menu) {
		open = false;
		if(checkValidMenu(menu))
			this.menu = menu;
		else {
			System.out.println("Invalid Menu. Using the default Menu...");
			this.menu = new Menu[10];
			initMenu();
		}
	}

	/**
	 * Initialize the default menu
	 */
	private void initMenu() {
		menu[0] = new Menu("Ribeye", 15.99, 20, false);
		menu[1] = new Menu("Salmon", 17.99, 20, false);
		menu[2] = new Menu("Curry", 9.99, 20, true);
		menu[3] = new Menu("Aglio Olio", 10.99, 20, true);
		menu[4] = new Menu("Rack of Lamb", 19.99, 20, false);
		menu[5] = new Menu("French Toast", 5.99, 20, false);
		menu[6] = new Menu("Cheesecake", 8.99, 20, false);
		menu[7] = new Menu("Tiramisu", 8.99, 20, false);
		menu[8] = new Menu("Tea", 2.99, 20, true);
		menu[9] = new Menu("Coffee", 3.99, 20, true);
	}

	/**
	 * Operate/Start the cafeteria
	 */
	public void operate() {
		if(!checkItemsAvailability()) {
			System.out.println("Some of the menu items are unavailable. Re-stock the items before operating the cafeteria.");
			return;
		}

		Scanner scan = new Scanner(System.in);

		if(!open) {
			System.out.println("The cafeteria is currently closed. Do you wish to operate the cafeteria? (y/n)");
			String input = scan.next();
			if(input.equals("y"))
				open = true;
			else {
				System.out.println("Halting operation...");
				scan.close();
				return;
			}
		}

		int option = 0;

		while(true) {
			System.out.println("+---------------------------+");
			System.out.println("1. Take Order");
			System.out.println("2. Change Price");
			System.out.println("3. Add Quantity");
			System.out.println("\n500. Shut Down");
			System.out.println("+---------------------------+");

			option = scan.nextInt();

			if(option == 1)
				takeOrderAsCashier();
			else if(option == 2)
				changePrice();
			else if(option == 3)
				addQuantity();
			else if(option == 500) {
				System.out.println("Shutting down...");
				//System.exit(0);
				scan.close();
				return;
			}
		}
	}

	/**
	 * Option 1: Take an order from a customer
	 * 
	 * Continue to take an order until it enters 500 to exit from this option
	 */
	@SuppressWarnings("resource")
	public void takeOrderAsCashier() {
		if(!checkAnyItemsAvailable()) {
			System.out.println("All of your items are unavailable. Unavailable to take order.");
			return;
		}

		Scanner scan = new Scanner(System.in);
		int choice = 0;
		int quantity = 0;
		String answer = "";
		boolean vegan = false;
		int veganNum = 0;

		while(choice != 500) {
			System.out.println("\nMenu Items");
			for(int i=0;i<menu.length;i++)
				System.out.println((i + 1) + ". " + menu[i].getName());
			System.out.println("\n500. Checkout");
			System.out.println();
			System.out.println("Enter the item: ");
			choice = scan.nextInt();		

			if(choice == 500) break;

			while(choice < 1 || choice > menu.length) {
				System.out.println("Enter the item: ");
				choice = scan.nextInt();
				if(choice == 500) break;
			}

			if(choice == 500) break;

			if(menu[choice-1].getQuantity() <= 0) 
				System.out.println("The item (" + menu[choice-1].getName() +") is currently unavailable.");
			else {
				System.out.println("Enter the quantity: ");
				quantity = scan.nextInt();

				while(quantity <= 0) {
					System.out.println("Invalid quantity! Enter the quantity: ");
					quantity = scan.nextInt();
				}

				//Update quantity
				if(quantity <= menu[choice-1].getQuantity()) {
					//There's a vegan option for this, so ask
					if(menu[choice-1].hasVeganOption()) {
						System.out.println("Vegan option? (y/n)");
						answer = scan.next();
						if(answer.equals("y")) {
							if(quantity == 1) {
								veganNum = 1;
							} else {
								System.out.println("Enter the qty of vegan option: ");
								veganNum = scan.nextInt();

								while(veganNum <= 0) {
									System.out.println("Invalid vegan quantity! Enter the vegan quantity: ");
									veganNum = scan.nextInt();
								}

								if(veganNum > menu[choice-1].getQuantity()) 
									veganNum = quantity;
								else
									veganNum = Math.min(veganNum, quantity);
							}
						}
					}
					menu[choice-1].deductQuantity(quantity);
				} else {
					System.out.println("There's only " + menu[choice-1].getQuantity() + " left but you've entered " + quantity + ". Do you want to order the remaining " + 
							(menu[choice-1].getQuantity()) + " of " + menu[choice-1].getName() + "? (y/n)");
					answer = scan.next();
					quantity = 0;
					if(answer.equals("y")) {
						quantity = menu[choice-1].getQuantity();
						menu[choice-1].setQuantity(0);
						//There's a vegan option for this, so ask
						if(menu[choice-1].hasVeganOption()) {
							System.out.println("Vegan option? (y/n)");
							answer = scan.next();
							if(answer.equals("y")) {
								if(quantity == 1) {
									veganNum = 1;
								} else {
									System.out.println("Enter the qty of vegan option: ");
									veganNum = scan.nextInt();

									while(veganNum <= 0) {
										System.out.println("Invalid vegan quantity! Enter the vegan quantity: ");
										veganNum = scan.nextInt();
									}

									if(veganNum > menu[choice-1].getQuantity()) 
										veganNum = quantity;
									else
										veganNum = Math.min(veganNum, quantity);
								}
							}
						}
					} else if(answer.equals("n")) 
						quantity = 0;
				}

				if(findDuplicatedFromArray(ar, choice) != -1)
					ar.get(findDuplicatedFromArray(ar, choice)).addQuantity(quantity);
				else
					ar.add(new Order(choice, quantity, menu[choice - 1].getPrice(), vegan, veganNum));
			}
		}

		double price = 0;

		for(int i=0;i<ar.size();i++)
			price += ar.get(i).getTotalPrice();

		double amt = 0;

		while(amt < price) {
			System.out.println("\nORDER #" + ID);
			for(int i=0;i<ar.size();i++)
				if(ar.get(i).getVeganAmt() == 0)
					System.out.printf("%d. %20s (%d)\t @$%.2f/ea\n", i+1, menu[ar.get(i).getNumber() - 1].getName()
							,ar.get(i).getQuantity(), menu[ar.get(i).getNumber() - 1].getPrice());
				else
					System.out.printf("%d. %20s (%d)\t @$%.2f/ea \t(%d)\t @$%.2f/ea [15%s off each]\n", 
							i+1, menu[ar.get(i).getNumber() - 1].getName(),(ar.get(i).getQuantity() - ar.get(i).getVeganAmt()), menu[ar.get(i).getNumber() - 1].getPrice(), ar.get(i).getVeganAmt(), (menu[ar.get(i).getNumber() - 1].getPrice() - (menu[ar.get(i).getNumber() - 1].getPrice() * 0.15)), "%");
			System.out.printf("\nTotal price: $%.2f\n", price);
			System.out.println("Enter the amount paid by the customer: ");
			amt = scan.nextDouble();
		}

		if(ar.size() != 0) {
			System.out.printf("Change Due: $%.2f\n", (amt - price));
			ID += 1;
		}
		ar.clear();
	}

	/**
	 * Option 2: Change the price of the food menu's item
	 * 
	 * Continue to ask the OP to change it until it enters 500 to exit from this option
	 */
	@SuppressWarnings("resource")
	public void changePrice() {
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		double price = 0;

		while(choice != 500) {
			System.out.println("\nMenu Items");
			for(int i=0;i<menu.length;i++) 
				System.out.printf("%d. %s @$%.2f/ea\n", i+1, menu[i].getName(), menu[i].getPrice());
			System.out.println("\n500. Exit");
			System.out.println();

			System.out.println("Enter the item that you would like to change: ");
			choice = scan.nextInt();

			if(choice == 500) break;

			while(choice < 1 || choice > menu.length) {
				System.out.println("Enter the item that you would like to change: ");
				choice = scan.nextInt();
				if(choice == 500) break;
			}

			if(choice == 500) break;

			System.out.println("Enter the new price for " + menu[choice-1].getName() + ".");
			price = scan.nextDouble();
			menu[choice-1].setPrice(price);
			System.out.println("Successfully changed the price of " + menu[choice-1].getName() + " to $" + menu[choice-1].getPrice() + "!");
		}
	}

	/**
	 * Option 3: Update the quantity of the item from the Menu
	 * 
	 * Continue to update the quantity of the item until it enters 500 to exit from this option
	 */
	@SuppressWarnings("resource")
	public void addQuantity() {		
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		int quantity = 0;

		while(choice != 500) {
			System.out.println("\nMenu Items");
			for(int i=0;i<menu.length;i++) 
				System.out.printf("%d. %s (%d)\n", i+1, menu[i].getName(), menu[i].getQuantity());
			System.out.println("\n500. Exit");
			System.out.println();
			System.out.println("Enter the item that you would like to change: ");
			choice = scan.nextInt();

			if(choice == 500) break;

			while(choice < 1 || choice > menu.length) {
				System.out.println("Enter the item that you would like to change: ");
				choice = scan.nextInt();
				if(choice == 500) break;
			}

			if(choice == 500) break;

			System.out.println("Enter the new quantity for " + menu[choice-1].getName() + ". (Enter negative amount to deduct from current amount)");
			quantity = scan.nextInt();
			menu[choice-1].addQuantity(quantity);
			System.out.println("Successfully added the quantity of " + menu[choice-1].getName() + " to " + menu[choice-1].getQuantity() + "!");
		}
	}

	/**
	 * Is the cafeteria open?
	 * 
	 * @return True if the cafeteria is open
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * Find duplicated orders from Order array
	 * @param ar - Order array
	 * @param num - Index number
	 * @return The index of the duplicated order
	 */
	private int findDuplicatedFromArray(ArrayList<Order> ar, int num) {
		for(int i=0;i<ar.size();i++)
			if(ar.get(i).getNumber() == num)
				return i;
		return -1;
	}

	/**
	 * Check if this item is available
	 * @return True if it's available, else otherwise
	 */
	private boolean checkItemsAvailability() {
		for(int i=0;i<menu.length;i++) 
			if(menu[i].getQuantity() <= 0)
				return false;
		return true;
	}

	/**
	 * Check if any of the items have more than 1 quantity
	 * 
	 * @return True if any of 1 of the items have quantity, else otherwise
	 */
	private boolean checkAnyItemsAvailable() {
		for(int i=0;i<menu.length;i++)
			if(menu[i].getQuantity() > 0)
				return true;
		return false;
	}

	/**
	 * Checks if the given Menu has duplicated names
	 * 
	 * @param menu - Given Menu
	 * @return True if the given Menu is valid, else otherwise
	 */
	private boolean checkValidMenu(Menu[] menu) {
		for(int i=0;i<menu.length;i++) 
			for(int j=i+1;j<menu.length;j++)
				if(menu[i].getName().equals(menu[j].getName()))
					return false;
		return true;
	}

	public static void main(String[] args) {
		//Default cafeteria
		Cafeteria c = new Cafeteria();
		c.operate();

		//Constructor with customized menu (Invalid menu)
		//		Menu[] menu = new Menu[2];
		//		menu[0] = new Menu("Ribeye", 15.99, 20);
		//		menu[1] = new Menu("Ribeye", 17.99, 20);
		//		
		//		Menu[] menu2 = new Menu[2];
		//		menu2[0] = new Menu("Ribeye", 15.99, 20);
		//		menu2[1] = new Menu("Salmon", 17.99, 20);
		//		Cafeteria c = new Cafeteria(menu2);
		//		c.operate();
	}
}