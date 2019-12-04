package main.java;

/**
 * Order class for cafeteria
 * 
 * @author Nicholas Ong
 */
public class Order {

	private int number;
	private int quantity;
	private double price;
	private String name;

	/**
	 * Constructor for this class
	 * 
	 * @param number - The number ordering of the item from the Menu
	 * @param quantity - Quantity of the item ordered 
	 * @param price - Price of the item ordered
	 */
	public Order(int number, int quantity, double price, String name) {
		this.number = number;
		this.quantity = quantity;
		this.price = price;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}

	public int getNumber() {
		return number;
	}

	public int getQuantity() {
		return quantity;
	}

	public void addQuantity(int amt) {
		quantity += amt;
	}

	/**
	 * Non-vegan * price + Vegan * 15% off original price
	 * 
	 * @return
	 */
	public double getTotalPrice() {
		return getQuantity() * getPrice();
	}
}