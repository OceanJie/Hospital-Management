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
	private ArrayList<Menu> soup;
	private ArrayList<Menu> mainCourse;
	private ArrayList<Menu> drinks;
	private ArrayList<Menu> veganMenu;
	private int ID = 101;
	private ArrayList<Order> ar = new ArrayList<>();
	private int totalChoices;

	/**
	 * Default constructor with default menu
	 */
	public Cafeteria() {
		open = false;
		soup = new ArrayList<Menu>();
		initSoupMenu();
		mainCourse = new ArrayList<Menu>();
		initMainCourse();
		drinks = new ArrayList<Menu>();
		initDrinksMenu();
		veganMenu = new ArrayList<Menu>();
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
	public Cafeteria(ArrayList<Menu> soup, ArrayList<Menu> mainCourse, ArrayList<Menu> drinks, ArrayList<Menu> veganMenu) {
		open = false;
		if(checkValidMenu(soup)) {
			this.soup = soup;
			totalChoices += soup.size();
		} else {
			System.out.println("Invalid Soup Menu. Using the default Soup Menu...");
			this.soup = new ArrayList<Menu>();
			initSoupMenu();
			totalChoices += 5;
		}
		if(checkValidMenu(mainCourse)) {
			this.mainCourse = mainCourse;
			totalChoices += mainCourse.size();
		} else {
			System.out.println("Invalid Main Course Menu. Using the default Main Course Menu...");
			this.mainCourse = new ArrayList<Menu>();
			initMainCourse();
			totalChoices += 5;
		}
		if(checkValidMenu(drinks)) {
			this.drinks = drinks;
			totalChoices += drinks.size();
		} else {
			System.out.println("Invalid Drinks Menu. Using the default Drinks Menu...");
			this.drinks = new ArrayList<Menu>();
			initDrinksMenu();
			totalChoices += 5;
		}
		if(checkValidMenu(veganMenu)) {
			this.veganMenu = veganMenu;
			totalChoices += veganMenu.size();
		} else {
			System.out.println("Invalid Vegan Menu. Using the default Vegan Menu...");
			this.veganMenu = new ArrayList<Menu>();
			initVeganMenu();
			totalChoices += 5;
		}
	}

	private void initSoupMenu() {
		soup.add(0, new Menu("Mushroom Soup", 5.99, 100));
		soup.add(1, new Menu("French Onion Soup", 6.99, 100));
		soup.add(2, new Menu("Bisque", 7.99, 100));
		soup.add(3, new Menu("Egg Drop Soup", 4.99, 100));
		soup.add(4, new Menu("Beef Noodle Soup", 5.99, 100));
	}

	private void initMainCourse() {
		mainCourse.add(0, new Menu("Meatloaf", 15.99, 100));
		mainCourse.add(1, new Menu("Chicken Cordon Bleu", 6.99, 100));
		mainCourse.add(2, new Menu("Aglio Olio", 9.99, 100));
		mainCourse.add(3, new Menu("Buttermilk Fried Chicken", 11.99, 100));
		mainCourse.add(4, new Menu("Chicken Tikka Masala", 15.99, 100));
	}

	private void initDrinksMenu() {
		drinks.add(0, new Menu("Coffee", 4.99, 100));
		drinks.add(1, new Menu("Tea", 1.99, 100));
		drinks.add(2, new Menu("Soft Drinks", 2.99, 100));
		drinks.add(3, new Menu("Lemonade", 3.99, 100));
		drinks.add(4, new Menu("Milkshake", 6.99, 100));
	}

	private void initVeganMenu() {
		veganMenu.add(0, new Menu("Zucchanoush", 7.99, 100));
		veganMenu.add(1, new Menu("Mushroom-Quinoa Burger", 8.99, 100));
		veganMenu.add(2, new Menu("Pesto Pasta", 8.99, 100));
		veganMenu.add(3, new Menu("Cucumber-Melon Soup", 5.99, 100));
		veganMenu.add(4, new Menu("Grilled Asparagus and Shitake Tacos", 7.99, 100));
	}

	/**
	 * Operate/Start the cafeteria
	 */
	public boolean operate() {
		if(!CafeteriaAttendant.ATTENDANT_LOGIN) {
			System.out.println("Unauthorized personnel.");
			return false;
		}

		if(!checkItemsAvailability()) {
			System.out.println("Some of the menu items are unavailable. Re-stock the items before operating the cafeteria.");
			return false;
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
				return false;
			}
		}

		int option = 0;

		while(true) {
			System.out.println("+---------------------------+");
			System.out.println("1. Take Order");
			System.out.println("2. Change Price");
			System.out.println("3. Add Quantity");
			System.out.println("4. Add Items to Menu");
			System.out.println("5. Remove Items from Menu");
			System.out.println("\n500. Shut Down");
			System.out.println("+---------------------------+");

			option = scan.nextInt();

			if(option == 1)
				takeOrder();
			else if(option == 2)
				changePrice();
			else if(option == 3)
				addQuantity();
			else if(option == 4)
				addItemsToMenu();
			else if(option == 5)
				removeItemsFromMenu();
			else if(option == 500) {
				System.out.println("Shutting down...");
				scan.close();
				return true;
			}
		}
	}

	/**
	 * Option 1: Take an order from a customer
	 * 
	 * Continue to take an order until it enters 500 to exit from this option
	 */
	@SuppressWarnings("resource")
	public boolean takeOrder() {
		if(!checkAnyItemsAvailable()) {
			System.out.println("All of your items are unavailable. Unavailable to take order.");
			return false;
		}

		Scanner scan = new Scanner(System.in);
		int choice = 0;
		int quantity = 0;
		String answer = "";
		int total = 1;

		while(choice != 500) {
			System.out.println("\nSoup Menu");
			for(int i=0;i<soup.size();i++) 
				System.out.printf("%d. %s @$%.2f/ea\n", total++, soup.get(i).getName(), soup.get(i).getPrice());
			System.out.println("\nMain Course Menu");
			for(int i=0;i<mainCourse.size();i++) 
				System.out.printf("%d. %s @$%.2f/ea\n", total++, mainCourse.get(i).getName(), mainCourse.get(i).getPrice());
			System.out.println("\nDrinks Menu");
			for(int i=0;i<drinks.size();i++) 
				System.out.printf("%d. %s @$%.2f/ea\n", total++, drinks.get(i).getName(), drinks.get(i).getPrice());
			System.out.println("\nVegan Menu");
			for(int i=0;i<veganMenu.size();i++) 
				System.out.printf("%d. %s @$%.2f/ea\n", total++, veganMenu.get(i).getName(), veganMenu.get(i).getPrice());
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

			int arlen = soup.size();
			int temp = choice - 1;

			if(arlen >= choice) {
				if(soup.get(temp).getQuantity() <= 0) 
					System.out.println("The item (" + soup.get(temp).getName() +") is currently unavailable.");
				else {
					System.out.println("Enter the quantity: ");
					quantity = scan.nextInt();

					while(quantity <= 0) {
						System.out.println("Invalid quantity! Enter the quantity: ");
						quantity = scan.nextInt();
					}

					//Update quantity
					if(quantity <= soup.get(temp).getQuantity()) {
						soup.get(temp).deductQuantity(quantity);
					} else {
						System.out.println("There's only " + soup.get(temp).getQuantity() + " left but you've entered " + quantity + ". Do you want to order the remaining " + 
								(soup.get(temp).getQuantity()) + " of " + soup.get(temp).getName() + "? (y/n)");
						answer = scan.next();
						quantity = 0;
						if(answer.equals("y")) {
							quantity = soup.get(temp).getQuantity();
							soup.get(temp).setQuantity(0);
						} else if(answer.equals("n")) 
							quantity = 0;
					}

					if(findDuplicatedFromArray(ar, choice) != -1)
						ar.get(findDuplicatedFromArray(ar, choice)).addQuantity(quantity);
					else
						ar.add(new Order(choice, quantity, soup.get(temp).getPrice(), soup.get(temp).getName()));
				}
			} else {
				arlen += mainCourse.size();
				if(arlen >= choice) {
					temp -= soup.size();
					if(mainCourse.get(temp).getQuantity() <= 0) 
						System.out.println("The item (" + mainCourse.get(temp).getName() +") is currently unavailable.");
					else {
						System.out.println("Enter the quantity: ");
						quantity = scan.nextInt();

						while(quantity <= 0) {
							System.out.println("Invalid quantity! Enter the quantity: ");
							quantity = scan.nextInt();
						}

						//Update quantity
						if(quantity <= mainCourse.get(temp).getQuantity()) {
							mainCourse.get(temp).deductQuantity(quantity);
						} else {
							System.out.println("There's only " + mainCourse.get(temp).getQuantity() + " left but you've entered " + quantity + ". Do you want to order the remaining " + 
									(mainCourse.get(temp).getQuantity()) + " of " + mainCourse.get(temp).getName() + "? (y/n)");
							answer = scan.next();
							quantity = 0;
							if(answer.equals("y")) {
								quantity = mainCourse.get(temp).getQuantity();
								mainCourse.get(temp).setQuantity(0);
							} else if(answer.equals("n")) 
								quantity = 0;
						}

						if(findDuplicatedFromArray(ar, choice) != -1)
							ar.get(findDuplicatedFromArray(ar, choice)).addQuantity(quantity);
						else
							ar.add(new Order(choice, quantity, mainCourse.get(temp).getPrice(), mainCourse.get(temp).getName()));
					}
				} else {
					arlen += drinks.size();
					if(arlen >= choice) {
						temp -= mainCourse.size();
						temp -= soup.size();
						if(drinks.get(temp).getQuantity() <= 0) 
							System.out.println("The item (" + drinks.get(temp).getName() +") is currently unavailable.");
						else {
							System.out.println("Enter the quantity: ");
							quantity = scan.nextInt();

							while(quantity <= 0) {
								System.out.println("Invalid quantity! Enter the quantity: ");
								quantity = scan.nextInt();
							}

							//Update quantity
							if(quantity <= drinks.get(temp).getQuantity()) {
								drinks.get(temp).deductQuantity(quantity);
							} else {
								System.out.println("There's only " + drinks.get(temp).getQuantity() + " left but you've entered " + quantity + ". Do you want to order the remaining " + 
										(drinks.get(temp).getQuantity()) + " of " + drinks.get(temp).getName() + "? (y/n)");
								answer = scan.next();
								quantity = 0;
								if(answer.equals("y")) {
									quantity = drinks.get(temp).getQuantity();
									drinks.get(temp).setQuantity(0);
								} else if(answer.equals("n")) 
									quantity = 0;
							}

							if(findDuplicatedFromArray(ar, choice) != -1)
								ar.get(findDuplicatedFromArray(ar, choice)).addQuantity(quantity);
							else
								ar.add(new Order(choice, quantity, drinks.get(temp).getPrice(), drinks.get(temp).getName()));
						}
					} else {
						arlen += veganMenu.size();
						if(arlen >= choice) {
							temp -= mainCourse.size();
							temp -= drinks.size();
							temp -= soup.size();
							if(veganMenu.get(temp).getQuantity() <= 0) 
								System.out.println("The item (" + veganMenu.get(temp).getName() +") is currently unavailable.");
							else {
								System.out.println("Enter the quantity: ");
								quantity = scan.nextInt();

								while(quantity <= 0) {
									System.out.println("Invalid quantity! Enter the quantity: ");
									quantity = scan.nextInt();
								}

								//Update quantity
								if(quantity <= veganMenu.get(temp).getQuantity()) {
									veganMenu.get(temp).deductQuantity(quantity);
								} else {
									System.out.println("There's only " + veganMenu.get(temp).getQuantity() + " left but you've entered " + quantity + ". Do you want to order the remaining " + 
											(veganMenu.get(temp).getQuantity()) + " of " + veganMenu.get(temp).getName() + "? (y/n)");
									answer = scan.next();
									quantity = 0;
									if(answer.equals("y")) {
										quantity = veganMenu.get(temp).getQuantity();
										veganMenu.get(temp).setQuantity(0);
									} else if(answer.equals("n")) 
										quantity = 0;
								}

								if(findDuplicatedFromArray(ar, choice) != -1)
									ar.get(findDuplicatedFromArray(ar, choice)).addQuantity(quantity);
								else
									ar.add(new Order(choice, quantity, veganMenu.get(temp).getPrice(), veganMenu.get(temp).getName()));
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
				System.out.printf("%d. %30s (%d)\t @$%.2f/ea\n", 
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
		return true;
	}

	/**
	 * Option 2: Change the price of the food menu's item
	 * 
	 * Continue to ask the OP to change it until it enters 500 to exit from this option
	 */
	@SuppressWarnings("resource")
	public boolean changePrice() {
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		double price = 0;
		int total = 1;

		while(choice != 500) {
			System.out.println("\nSoup Menu");
			for(int i=0;i<soup.size();i++) 
				System.out.printf("%d. %s @$%.2f/ea\n", total++, soup.get(i).getName(), soup.get(i).getPrice());
			System.out.println("\nMain Course Menu");
			for(int i=0;i<mainCourse.size();i++) 
				System.out.printf("%d. %s @$%.2f/ea\n", total++, mainCourse.get(i).getName(), mainCourse.get(i).getPrice());
			System.out.println("\nDrinks Menu");
			for(int i=0;i<drinks.size();i++) 
				System.out.printf("%d. %s @$%.2f/ea\n", total++, drinks.get(i).getName(), drinks.get(i).getPrice());
			System.out.println("\nVegan Menu");
			for(int i=0;i<veganMenu.size();i++) 
				System.out.printf("%d. %s @$%.2f/ea\n", total++, veganMenu.get(i).getName(), veganMenu.get(i).getPrice());
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

			int arlen = soup.size();
			int temp = choice - 1;

			if(arlen >= choice) {
				System.out.println("Enter the new price for " + soup.get(temp).getName() + ".");
				price = scan.nextDouble();
				soup.get(temp).setPrice(price);
				System.out.println("Successfully changed the price of " + soup.get(temp).getName() + " to $" + soup.get(temp).getPrice() + "!");
			} else {
				arlen += mainCourse.size();
				if(arlen >= choice) {
					temp -= soup.size();
					System.out.println("Enter the new price for " + mainCourse.get(temp).getName() + ".");
					price = scan.nextDouble();
					mainCourse.get(temp).setPrice(price);
					System.out.println("Successfully changed the price of " + mainCourse.get(temp).getName() + " to $" + mainCourse.get(temp).getPrice() + "!");
				} else {
					arlen += drinks.size();
					if(arlen >= choice) {
						temp -= mainCourse.size();
						temp -= soup.size();
						System.out.println("Enter the new price for " + drinks.get(temp).getName() + ".");
						price = scan.nextDouble();
						drinks.get(temp).setPrice(price);
						System.out.println("Successfully changed the price of " + drinks.get(temp).getName() + " to $" + drinks.get(temp).getPrice() + "!");
					} else {
						arlen += veganMenu.size();
						if(arlen >= choice) {
							temp -= mainCourse.size();
							temp -= drinks.size();
							temp -= soup.size();
							System.out.println("Enter the new price for " + veganMenu.get(temp).getName() + ".");
							price = scan.nextDouble();
							veganMenu.get(temp).setPrice(price);
							System.out.println("Successfully changed the price of " + veganMenu.get(temp).getName() + " to $" + veganMenu.get(temp).getPrice() + "!");
						}
					}
				}
			}
			//Reset
			total = 1;
		}
		return true;
	}

	/**
	 * Option 3: Update the quantity of the item from the Menu
	 * 
	 * Continue to update the quantity of the item until it enters 500 to exit from this option
	 */
	@SuppressWarnings("resource")
	public boolean addQuantity() {		
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		int quantity = 0;
		int total = 1;

		while(choice != 500) {
			System.out.println("\nSoup Menu");
			for(int i=0;i<soup.size();i++) 
				System.out.printf("%d. %s (%d)\n", total++, soup.get(i).getName(), soup.get(i).getQuantity());
			System.out.println("\nMain Course Menu");
			for(int i=0;i<mainCourse.size();i++) 
				System.out.printf("%d. %s (%d)\n", total++, mainCourse.get(i).getName(), mainCourse.get(i).getQuantity());
			System.out.println("\nDrinks Menu");
			for(int i=0;i<drinks.size();i++) 
				System.out.printf("%d. %s (%d)\n", total++, drinks.get(i).getName(), drinks.get(i).getQuantity());
			System.out.println("\nVegan Menu");
			for(int i=0;i<veganMenu.size();i++) 
				System.out.printf("%d. %s (%d)\n", total++, veganMenu.get(i).getName(), veganMenu.get(i).getQuantity());
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

			int arlen = soup.size();
			int temp = choice - 1;

			if(arlen >= choice) {
				System.out.println("Enter the new quantity for " + soup.get(temp).getName() + ". (Enter negative amount to deduct from current amount)");
				quantity = scan.nextInt();
				soup.get(temp).addQuantity(quantity);
				System.out.println("Successfully added the quantity of " + soup.get(temp).getName() + " to " + soup.get(temp).getQuantity() + "!");
			} else {
				arlen += mainCourse.size();
				if(arlen >= choice) {
					temp -= soup.size();
					System.out.println("Enter the new quantity for " + mainCourse.get(temp).getName() + ". (Enter negative amount to deduct from current amount)");
					quantity = scan.nextInt();
					mainCourse.get(temp).addQuantity(quantity);
					System.out.println("Successfully added the quantity of " + mainCourse.get(temp).getName() + " to " + mainCourse.get(temp).getQuantity() + "!");
				} else {
					arlen += drinks.size();
					if(arlen >= choice) {
						temp -= mainCourse.size();
						temp -= soup.size();
						System.out.println("Enter the new quantity for " + drinks.get(temp).getName() + ". (Enter negative amount to deduct from current amount)");
						quantity = scan.nextInt();
						drinks.get(temp).addQuantity(quantity);
						System.out.println("Successfully added the quantity of " + drinks.get(temp).getName() + " to " + drinks.get(temp).getQuantity() + "!");
					} else {
						arlen += veganMenu.size();
						if(arlen >= choice) {
							temp -= mainCourse.size();
							temp -= drinks.size();
							temp -= soup.size();
							System.out.println("Enter the new quantity for " + veganMenu.get(temp).getName() + ". (Enter negative amount to deduct from current amount)");
							quantity = scan.nextInt();
							veganMenu.get(temp).addQuantity(quantity);
							System.out.println("Successfully added the quantity of " + veganMenu.get(temp).getName() + " to " + veganMenu.get(temp).getQuantity() + "!");
						}
					}
				}
			}

			//Reset
			total = 1;
		}
		return true;
	}

	/**
	 * Option 4: Add items to Menu
	 * 
	 * Continue to add certain item(s) from Menu until it enters 500 to exit from this option
	 */
	@SuppressWarnings("resource")
	public void addItemsToMenu() {
		Scanner scan = new Scanner(System.in);
		int choice = 0;

		while(choice != 500) {
			System.out.println("\n1.Soup Menu");
			for(int i=0;i<soup.size();i++) 
				System.out.printf("%s %s\n", "->", soup.get(i).getName());
			System.out.println("\n2.Main Course Menu");
			for(int i=0;i<mainCourse.size();i++) 
				System.out.printf("%s %s\n", "->", mainCourse.get(i).getName());
			System.out.println("\n3.Drinks Menu");
			for(int i=0;i<drinks.size();i++) 
				System.out.printf("%s %s\n", "->", drinks.get(i).getName());
			System.out.println("\n4.Vegan Menu");
			for(int i=0;i<veganMenu.size();i++) 
				System.out.printf("%s %s\n", "->", veganMenu.get(i).getName());
			System.out.println("\n500. Exit");
			System.out.println();
			System.out.println("Select a category that you would add an item to the Menu: ");
			choice = scan.nextInt();

			if(choice == 500) break;

			while(choice < 1 || choice > 4) {
				System.out.println("Select a category that you would add an item to the Menu: ");
				choice = scan.nextInt();
				if(choice == 500) break;
			}

			if(choice == 500) break;

			String name = "";
			double price = 0;
			int amt = 0;

			System.out.println("Enter the name of the dish: ");
			name = scan.next();
			System.out.println("Enter the price of the dish: ");
			price = scan.nextDouble();
			System.out.println("Enter the amount that is available: ");
			amt = scan.nextInt();

			switch(choice) {
			case 1:
				System.out.println("Successfully added " + name + "into the Soup Menu with the price $" + price + "/ea (" + amt + ")!");
				soup.add(soup.size(), new Menu(name, price, amt));
				totalChoices += 1;
				break;
			case 2:
				System.out.println("Successfully added " + name + "into the Main Course Menu with the price $" + price + "/ea (" + amt + ")!");
				mainCourse.add(mainCourse.size(), new Menu(name, price, amt));
				totalChoices += 1;
				break;
			case 3:
				System.out.println("Successfully added " + name + "into the Drinks Menu with the price $" + price + "/ea (" + amt + ")!");
				drinks.add(drinks.size(), new Menu(name, price, amt));
				totalChoices += 1;
				break;
			case 4:
				System.out.println("Successfully added " + name + "into the Main Course Menu with the price $" + price + "/ea (" + amt + ")!");
				veganMenu.add(veganMenu.size(), new Menu(name, price, amt));
				totalChoices += 1;
				break;
			}
		}
	}

	/**
	 * Option 5: Remove items from Menu
	 * 
	 * Continue to remove certain item(s) from Menu until it enters 500 to exit from this option
	 */
	@SuppressWarnings("resource")
	public void removeItemsFromMenu() {
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		int total = 1;

		while(choice != 500) {
			System.out.println("\nSoup Menu");
			for(int i=0;i<soup.size();i++) 
				System.out.printf("%d. %s\n", total++, soup.get(i).getName());
			System.out.println("\nMain Course Menu");
			for(int i=0;i<mainCourse.size();i++) 
				System.out.printf("%d. %s\n", total++, mainCourse.get(i).getName());
			System.out.println("\nDrinks Menu");
			for(int i=0;i<drinks.size();i++) 
				System.out.printf("%d. %s\n", total++, drinks.get(i).getName());
			System.out.println("\nVegan Menu");
			for(int i=0;i<veganMenu.size();i++) 
				System.out.printf("%d. %s\n", total++, veganMenu.get(i).getName());
			System.out.println("\n500. Exit");
			System.out.println();
			System.out.println("Enter the item that you would like to remove from the Menu: ");
			choice = scan.nextInt();

			if(choice == 500) break;

			while(choice < 1 || choice > totalChoices) {
				System.out.println("Enter the item that you would like to remove from the Menu: ");
				choice = scan.nextInt();
				if(choice == 500) break;
			}

			if(choice == 500) break;

			int arlen = soup.size();
			int temp = choice - 1;

			if(arlen >= choice) {
				if(soup.size() == 1)
					System.out.println("Unable to remove " + soup.get(temp).getName() + " because it's the only item left on this category!");
				else {
					System.out.println("Successfully removed " + soup.get(temp).getName() + " from the Menu!");
					soup.remove(temp);
					totalChoices -= 1;
				}
			} else {
				arlen += mainCourse.size();
				if(arlen >= choice) {
					temp -= soup.size();
					if(mainCourse.size() == 1)
						System.out.println("Unable to remove " + mainCourse.get(temp).getName() + " because it's the only item left on this category!");
					else {
						System.out.println("Successfully removed " + mainCourse.get(temp).getName() + " from the Menu!");
						mainCourse.remove(temp);
						totalChoices -= 1;
					}
				} else {
					arlen += drinks.size();
					if(arlen >= choice) {
						temp -= mainCourse.size();
						temp -= soup.size();
						if(drinks.size() == 1)
							System.out.println("Unable to remove " + drinks.get(temp).getName() + " because it's the only item left on this category!");
						else {
							System.out.println("Successfully removed " + drinks.get(temp).getName() + " from the Menu!");
							drinks.remove(temp);
							totalChoices -= 1;
						}
					} else {
						arlen += veganMenu.size();
						if(arlen >= choice) {
							temp -= mainCourse.size();
							temp -= drinks.size();
							temp -= soup.size();
							if(veganMenu.size() == 1)
								System.out.println("Unable to remove " + veganMenu.get(temp).getName() + " because it's the only item left on this category!");
							else {
								System.out.println("Successfully removed " + veganMenu.get(temp).getName() + " from the Menu!");
								veganMenu.remove(temp);
								totalChoices -= 1;
							}
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
		for(int i=0;i<soup.size();i++) 
			if(soup.get(i).getQuantity() <= 0)
				return false;
		for(int i=0;i<mainCourse.size();i++) 
			if(mainCourse.get(i).getQuantity() <= 0)
				return false;
		for(int i=0;i<drinks.size();i++) 
			if(drinks.get(i).getQuantity() <= 0)
				return false;
		for(int i=0;i<veganMenu.size();i++) 
			if(veganMenu.get(i).getQuantity() <= 0)
				return false;
		return true;
	}

	/**
	 * Check if any of the items have more than 1 quantity
	 * 
	 * @return True if any of 1 of the items have quantity, else otherwise
	 */
	private boolean checkAnyItemsAvailable() {
		for(int i=0;i<soup.size();i++)
			if(soup.get(i).getQuantity() > 0)
				return true;
		for(int i=0;i<mainCourse.size();i++)
			if(mainCourse.get(i).getQuantity() > 0)
				return true;
		for(int i=0;i<drinks.size();i++)
			if(drinks.get(i).getQuantity() > 0)
				return true;
		for(int i=0;i<veganMenu.size();i++)
			if(veganMenu.get(i).getQuantity() > 0)
				return true;
		return false;
	}
      
	/**
	 * Checks if the given Menu has duplicated names
	 * 
	 * @param menu - Given Menu
	 * @return True if the given Menu is valid, else otherwise
	 */
	private boolean checkValidMenu(ArrayList<Menu> menu) {
		for(int i=0;i<menu.size();i++) 
			for(int j=i+1;j<menu.size();j++)
				if(menu.get(i).getName().equals(menu.get(j).getName()))
					return false;
		return true;
	}

	//Testing only
	public static void main(String[] args) {
		//Default cafeteria
		Cafeteria c = new Cafeteria();
		c.operate();
	}
}