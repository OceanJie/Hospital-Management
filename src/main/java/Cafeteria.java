package main.java;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Cafeteria class
 * 
 * @author Nicholas Ong
 */
public class Cafeteria {

	//marketing google api (e.g) whats the price of insulin

	private boolean open;
	private Menu[] soup;
	private Menu[] mainCourse;
	private Menu[] drinks;
	private Menu[] veganMenu;
	private int ID = 101;
	private ArrayList<Order> ar = new ArrayList<>();
	private int totalChoices;

	/**
	 * Default constructor with default menu
	 */
	public Cafeteria() {
		open = false;
		soup = new Menu[5];
		initSoupMenu();
		mainCourse = new Menu[5];
		initMainCourse();
		drinks = new Menu[5];
		initDrinksMenu();
		veganMenu = new Menu[5];
		initVeganMenu();
		totalChoices = 20;
	}

	/**
	 * Cafeteria constructor with customized menus of choice
	 * 
	 * @param soup - Soup Menu
	 * @param mainCourse - Main Course Menu
	 * @param drinks - Drinks Menu
	 * @param veganMenu - Vegan Menu
	 */
	public Cafeteria(Menu[] soup, Menu[] mainCourse, Menu[] drinks, Menu[] veganMenu) {
		open = false;
		if(checkValidMenu(soup)) 
			this.soup = soup;
		else {
			System.out.println("Invalid Soup Menu. Using the default Soup Menu...");
			this.soup = new Menu[5];
			initSoupMenu();
		}
		if(checkValidMenu(mainCourse))
			this.mainCourse = mainCourse;
		else {
			System.out.println("Invalid Main Course Menu. Using the default Main Course Menu...");
			this.mainCourse = new Menu[5];
			initMainCourse();
		}
		if(checkValidMenu(drinks))
			this.drinks = drinks;
		else {
			System.out.println("Invalid Drinks Menu. Using the default Drinks Menu...");
			this.drinks = new Menu[5];
			initDrinksMenu();
		}
		if(checkValidMenu(veganMenu))
			this.veganMenu = veganMenu;
		else {
			System.out.println("Invalid Vegan Menu. Using the default Vegan Menu...");
			this.veganMenu = new Menu[5];
			initVeganMenu();
		}
		totalChoices = soup.length + mainCourse.length + drinks.length + veganMenu.length;
	}

	private void initSoupMenu() {
		soup[0] = new Menu("Mushroom Soup", 5.99, 100);
		soup[1] = new Menu("French Onion Soup", 6.99, 100);
		soup[2] = new Menu("Bisque", 7.99, 100);
		soup[3] = new Menu("Egg Drop Soup", 4.99, 100);
		soup[4] = new Menu("Beef Noodle Soup", 5.99, 100);
	}

	private void initMainCourse() {
		mainCourse[0] = new Menu("Meatloaf", 15.99, 100);
		mainCourse[1] = new Menu("Chicken Cordon Bleu", 6.99, 100);
		mainCourse[2] = new Menu("Aglio Olio", 9.99, 100);
		mainCourse[3] = new Menu("Buttermilk Fried Chicken", 11.99, 100);
		mainCourse[4] = new Menu("Chicken Tikka Masala", 15.99, 100);
	}

	private void initDrinksMenu() {
		drinks[0] = new Menu("Coffee", 4.99, 100);
		drinks[1] = new Menu("Tea", 1.99, 100);
		drinks[2] = new Menu("Soft Drinks", 2.99, 100);
		drinks[3] = new Menu("Lemonade", 3.99, 100);
		drinks[4] = new Menu("Milkshake", 6.99, 100);
	}

	private void initVeganMenu() {
		veganMenu[0] = new Menu("Zucchanoush", 7.99, 100);
		veganMenu[1] = new Menu("Mushroom-Quinoa Burger", 8.99, 100);
		veganMenu[2] = new Menu("Pesto Pasta", 8.99, 100);
		veganMenu[3] = new Menu("Cucumber-Melon Soup", 5.99, 100);
		veganMenu[4] = new Menu("Grilled Asparagus and Shitake Tacos", 7.99, 100);
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
		int total = 1;

		while(choice != 500) {
			System.out.println("\nSoup Menu");
			for(int i=0;i<soup.length;i++) 
				System.out.printf("%d. %s @$%.2f/ea\n", total++, soup[i].getName(), soup[i].getPrice());
			System.out.println("\nMain Course Menu");
			for(int i=0;i<mainCourse.length;i++) 
				System.out.printf("%d. %s @$%.2f/ea\n", total++, mainCourse[i].getName(), mainCourse[i].getPrice());
			System.out.println("\nDrinks Menu");
			for(int i=0;i<drinks.length;i++) 
				System.out.printf("%d. %s @$%.2f/ea\n", total++, drinks[i].getName(), drinks[i].getPrice());
			System.out.println("\nVegan Menu");
			for(int i=0;i<veganMenu.length;i++) 
				System.out.printf("%d. %s @$%.2f/ea\n", total++, veganMenu[i].getName(), veganMenu[i].getPrice());
			System.out.println("\n500. Checkout");
			System.out.println();

			System.out.println("Enter the item: ");
			choice = scan.nextInt();		

			if(choice == 500) break;

			while(choice < 1 || choice > totalChoices) {
				System.out.println("Enter the item: ");
				choice = scan.nextInt();
				if(choice == 500) break;
			}

			if(choice == 500) break;

			int arlen = soup.length;
			int temp = choice - 1;

			if(arlen >= choice) {
				if(soup[temp].getQuantity() <= 0) 
					System.out.println("The item (" + soup[temp].getName() +") is currently unavailable.");
				else {
					System.out.println("Enter the quantity: ");
					quantity = scan.nextInt();

					while(quantity <= 0) {
						System.out.println("Invalid quantity! Enter the quantity: ");
						quantity = scan.nextInt();
					}

					//Update quantity
					if(quantity <= soup[temp].getQuantity()) {
						soup[temp].deductQuantity(quantity);
					} else {
						System.out.println("There's only " + soup[temp].getQuantity() + " left but you've entered " + quantity + ". Do you want to order the remaining " + 
								(soup[temp].getQuantity()) + " of " + soup[temp].getName() + "? (y/n)");
						answer = scan.next();
						quantity = 0;
						if(answer.equals("y")) {
							quantity = soup[temp].getQuantity();
							soup[temp].setQuantity(0);
						} else if(answer.equals("n")) 
							quantity = 0;
					}

					if(findDuplicatedFromArray(ar, choice) != -1)
						ar.get(findDuplicatedFromArray(ar, choice)).addQuantity(quantity);
					else
						ar.add(new Order(choice, quantity, soup[temp].getPrice(), soup[temp].getName()));
				}
			} else {
				arlen += mainCourse.length;
				if(arlen >= choice) {
					temp -= soup.length;
					if(mainCourse[temp].getQuantity() <= 0) 
						System.out.println("The item (" + mainCourse[temp].getName() +") is currently unavailable.");
					else {
						System.out.println("Enter the quantity: ");
						quantity = scan.nextInt();

						while(quantity <= 0) {
							System.out.println("Invalid quantity! Enter the quantity: ");
							quantity = scan.nextInt();
						}

						//Update quantity
						if(quantity <= mainCourse[temp].getQuantity()) {
							drinks[temp].deductQuantity(quantity);
						} else {
							System.out.println("There's only " + mainCourse[temp].getQuantity() + " left but you've entered " + quantity + ". Do you want to order the remaining " + 
									(mainCourse[temp].getQuantity()) + " of " + mainCourse[temp].getName() + "? (y/n)");
							answer = scan.next();
							quantity = 0;
							if(answer.equals("y")) {
								quantity = mainCourse[temp].getQuantity();
								mainCourse[temp].setQuantity(0);
							} else if(answer.equals("n")) 
								quantity = 0;
						}

						if(findDuplicatedFromArray(ar, choice) != -1)
							ar.get(findDuplicatedFromArray(ar, choice)).addQuantity(quantity);
						else
							ar.add(new Order(choice, quantity, mainCourse[temp].getPrice(), mainCourse[temp].getName()));
					}
				} else {
					arlen += drinks.length;
					if(arlen >= choice) {
						temp -= mainCourse.length;
						temp -= soup.length;
						if(drinks[temp].getQuantity() <= 0) 
							System.out.println("The item (" + drinks[temp].getName() +") is currently unavailable.");
						else {
							System.out.println("Enter the quantity: ");
							quantity = scan.nextInt();

							while(quantity <= 0) {
								System.out.println("Invalid quantity! Enter the quantity: ");
								quantity = scan.nextInt();
							}

							//Update quantity
							if(quantity <= drinks[temp].getQuantity()) {
								drinks[temp].deductQuantity(quantity);
							} else {
								System.out.println("There's only " + drinks[temp].getQuantity() + " left but you've entered " + quantity + ". Do you want to order the remaining " + 
										(drinks[temp].getQuantity()) + " of " + drinks[temp].getName() + "? (y/n)");
								answer = scan.next();
								quantity = 0;
								if(answer.equals("y")) {
									quantity = drinks[temp].getQuantity();
									drinks[temp].setQuantity(0);
								} else if(answer.equals("n")) 
									quantity = 0;
							}

							if(findDuplicatedFromArray(ar, choice) != -1)
								ar.get(findDuplicatedFromArray(ar, choice)).addQuantity(quantity);
							else
								ar.add(new Order(choice, quantity, drinks[temp].getPrice(), drinks[temp].getName()));
						}
					} else {
						arlen += veganMenu.length;
						if(arlen >= choice) {
							temp -= mainCourse.length;
							temp -= drinks.length;
							temp -= soup.length;
							if(veganMenu[temp].getQuantity() <= 0) 
								System.out.println("The item (" + veganMenu[temp].getName() +") is currently unavailable.");
							else {
								System.out.println("Enter the quantity: ");
								quantity = scan.nextInt();

								while(quantity <= 0) {
									System.out.println("Invalid quantity! Enter the quantity: ");
									quantity = scan.nextInt();
								}

								//Update quantity
								if(quantity <= veganMenu[temp].getQuantity()) {
									veganMenu[temp].deductQuantity(quantity);
								} else {
									System.out.println("There's only " + veganMenu[temp].getQuantity() + " left but you've entered " + quantity + ". Do you want to order the remaining " + 
											(veganMenu[temp].getQuantity()) + " of " + veganMenu[temp].getName() + "? (y/n)");
									answer = scan.next();
									quantity = 0;
									if(answer.equals("y")) {
										quantity = veganMenu[temp].getQuantity();
										veganMenu[temp].setQuantity(0);
									} else if(answer.equals("n")) 
										quantity = 0;
								}

								if(findDuplicatedFromArray(ar, choice) != -1)
									ar.get(findDuplicatedFromArray(ar, choice)).addQuantity(quantity);
								else
									ar.add(new Order(choice, quantity, veganMenu[temp].getPrice(), veganMenu[temp].getName()));
							}
						}
					}
				}
			}
			//Reset
			total = 1;
		}

		double price = 0;

		for(int i=0;i<ar.size();i++)
			price += ar.get(i).getTotalPrice();

		double amt = 0;

		while(amt < price) {
			System.out.println("\nORDER #" + ID);
			for(int i=0;i<ar.size();i++)
				System.out.printf("%d. %20s (%d)\t @$%.2f/ea\n", 
						i+1, ar.get(i).getName(), ar.get(i).getQuantity(), ar.get(i).getPrice());
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
		int total = 1;

		while(choice != 500) {
			System.out.println("\nSoup Menu");
			for(int i=0;i<soup.length;i++) 
				System.out.printf("%d. %s @$%.2f/ea\n", total++, soup[i].getName(), soup[i].getPrice());
			System.out.println("\nMain Course Menu");
			for(int i=0;i<mainCourse.length;i++) 
				System.out.printf("%d. %s @$%.2f/ea\n", total++, mainCourse[i].getName(), mainCourse[i].getPrice());
			System.out.println("\nDrinks Menu");
			for(int i=0;i<drinks.length;i++) 
				System.out.printf("%d. %s @$%.2f/ea\n", total++, drinks[i].getName(), drinks[i].getPrice());
			System.out.println("\nVegan Menu");
			for(int i=0;i<veganMenu.length;i++) 
				System.out.printf("%d. %s @$%.2f/ea\n", total++, veganMenu[i].getName(), veganMenu[i].getPrice());
			System.out.println("\n500. Exit");
			System.out.println();

			System.out.println("Enter the item that you would like to change: ");
			choice = scan.nextInt();

			if(choice == 500) break;

			while(choice < 1 || choice > totalChoices) {
				System.out.println("Enter the item that you would like to change: ");
				choice = scan.nextInt();
				if(choice == 500) break;
			}

			if(choice == 500) break;

			int arlen = soup.length;
			int temp = choice - 1;

			if(arlen >= choice) {
				System.out.println("Enter the new price for " + soup[temp].getName() + ".");
				price = scan.nextDouble();
				soup[temp].setPrice(price);
				System.out.println("Successfully changed the price of " + soup[temp].getName() + " to $" + soup[temp].getPrice() + "!");
			} else {
				arlen += mainCourse.length;
				if(arlen >= choice) {
					temp -= soup.length;
					System.out.println("Enter the new price for " + mainCourse[temp].getName() + ".");
					price = scan.nextDouble();
					mainCourse[temp].setPrice(price);
					System.out.println("Successfully changed the price of " + mainCourse[temp].getName() + " to $" + mainCourse[temp].getPrice() + "!");
				} else {
					arlen += drinks.length;
					if(arlen >= choice) {
						temp -= drinks.length;
						temp -= soup.length;
						System.out.println("Enter the new price for " + drinks[temp].getName() + ".");
						price = scan.nextDouble();
						drinks[temp].setPrice(price);
						System.out.println("Successfully changed the price of " + drinks[temp].getName() + " to $" + drinks[temp].getPrice() + "!");
					} else {
						arlen += veganMenu.length;
						if(arlen >= choice) {
							temp -= veganMenu.length;
							temp -= drinks.length;
							temp -= soup.length;
							System.out.println("Enter the new price for " + veganMenu[temp].getName() + ".");
							price = scan.nextDouble();
							veganMenu[temp].setPrice(price);
							System.out.println("Successfully changed the price of " + veganMenu[temp].getName() + " to $" + veganMenu[temp].getPrice() + "!");
						}
					}
				}
			}
			//Reset
			total = 1;
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
		int total = 1;

		while(choice != 500) {
			System.out.println("\nSoup Menu");
			for(int i=0;i<soup.length;i++) 
				System.out.printf("%d. %s (%d)\n", total++, soup[i].getName(), soup[i].getQuantity());
			System.out.println("\nMain Course Menu");
			for(int i=0;i<mainCourse.length;i++) 
				System.out.printf("%d. %s (%d)\n", total++, mainCourse[i].getName(), mainCourse[i].getQuantity());
			System.out.println("\nDrinks Menu");
			for(int i=0;i<drinks.length;i++) 
				System.out.printf("%d. %s (%d)\n", total++, drinks[i].getName(), drinks[i].getQuantity());
			System.out.println("\nVegan Menu");
			for(int i=0;i<veganMenu.length;i++) 
				System.out.printf("%d. %s (%d)\n", total++, veganMenu[i].getName(), veganMenu[i].getQuantity());
			System.out.println("\n500. Exit");
			System.out.println();
			System.out.println("Enter the item that you would like to change: ");
			choice = scan.nextInt();

			if(choice == 500) break;

			while(choice < 1 || choice > totalChoices) {
				System.out.println("Enter the item that you would like to change: ");
				choice = scan.nextInt();
				if(choice == 500) break;
			}

			if(choice == 500) break;

			int arlen = soup.length;
			int temp = choice - 1;

			if(arlen >= choice) {
				System.out.println("Enter the new quantity for " + soup[temp].getName() + ". (Enter negative amount to deduct from current amount)");
				quantity = scan.nextInt();
				soup[temp].addQuantity(quantity);
				System.out.println("Successfully added the quantity of " + soup[temp].getName() + " to " + soup[temp].getQuantity() + "!");
			} else {
				arlen += mainCourse.length;
				if(arlen >= choice) {
					temp -= soup.length;
					System.out.println("Enter the new quantity for " + mainCourse[temp].getName() + ". (Enter negative amount to deduct from current amount)");
					quantity = scan.nextInt();
					mainCourse[temp].addQuantity(quantity);
					System.out.println("Successfully added the quantity of " + mainCourse[temp].getName() + " to " + mainCourse[temp].getQuantity() + "!");
				} else {
					arlen += drinks.length;
					if(arlen >= choice) {
						temp -= drinks.length;
						temp -= soup.length;
						System.out.println("Enter the new quantity for " + drinks[temp].getName() + ". (Enter negative amount to deduct from current amount)");
						quantity = scan.nextInt();
						drinks[temp].addQuantity(quantity);
						System.out.println("Successfully added the quantity of " + drinks[temp].getName() + " to " + drinks[temp].getQuantity() + "!");
					} else {
						arlen += veganMenu.length;
						if(arlen >= choice) {
							temp -= veganMenu.length;
							temp -= drinks.length;
							temp -= soup.length;
							System.out.println("Enter the new quantity for " + veganMenu[temp].getName() + ". (Enter negative amount to deduct from current amount)");
							quantity = scan.nextInt();
							veganMenu[temp].addQuantity(quantity);
							System.out.println("Successfully added the quantity of " + veganMenu[temp].getName() + " to " + veganMenu[temp].getQuantity() + "!");
						}
					}
				}
			}

			//Reset
			total = 1;
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
		for(int i=0;i<soup.length;i++) 
			if(soup[i].getQuantity() <= 0)
				return false;
		for(int i=0;i<mainCourse.length;i++) 
			if(mainCourse[i].getQuantity() <= 0)
				return false;
		for(int i=0;i<drinks.length;i++) 
			if(drinks[i].getQuantity() <= 0)
				return false;
		for(int i=0;i<veganMenu.length;i++) 
			if(veganMenu[i].getQuantity() <= 0)
				return false;
		return true;
	}

	/**
	 * Check if any of the items have more than 1 quantity
	 * 
	 * @return True if any of 1 of the items have quantity, else otherwise
	 */
	private boolean checkAnyItemsAvailable() {
		for(int i=0;i<soup.length;i++)
			if(soup[i].getQuantity() > 0)
				return true;
		for(int i=0;i<mainCourse.length;i++)
			if(mainCourse[i].getQuantity() > 0)
				return true;
		for(int i=0;i<drinks.length;i++)
			if(drinks[i].getQuantity() > 0)
				return true;
		for(int i=0;i<veganMenu.length;i++)
			if(veganMenu[i].getQuantity() > 0)
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

	//Testing only
	public static void main(String[] args) {
		//Default cafeteria
//		Cafeteria c = new Cafeteria();
//		c.operate();
		
		Menu[] main = new Menu[3];
		main[0] = new Menu("Meatloaf", 15.99, 100);
		main[1] = new Menu("Chicken Cordon Bleu", 6.99, 100);
		main[2] = new Menu("Aglio Olio", 9.99, 100);
		Menu[] soup = new Menu[5];
		soup[0] = new Menu("Mushroom Soup", 5.99, 100);
		soup[1] = new Menu("French Onion Soup", 6.99, 100);
		soup[2] = new Menu("Bisque", 7.99, 100);
		soup[3] = new Menu("Egg Drop Soup", 4.99, 100);
		soup[4] = new Menu("Beef Noodle Soup", 5.99, 100);
		Menu[] vegan = new Menu[1];
		vegan[0] = new Menu("Zucchanoush", 7.99, 100);
		Menu[] drink = new Menu[2];
		drink[0] = new Menu("Coffee", 4.99, 100);
		drink[1] = new Menu("Tea", 1.99, 100);
		
		Cafeteria c = new Cafeteria(soup, main, drink, vegan);
		c.operate();
	}
}