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
	private boolean veganOption;
	private int veganAmt;

	/**
	 * Constructor for this class
	 * 
	 * @param number - The number ordering of the item from the Menu
	 * @param quantity - Quantity of the item ordered 
	 * @param price - Price of the item ordered
	 * @param veganOption - If this item has vegan option
	 * @param veganAmt - Amount of vegan option if opted for it
	 */
	public Order(int number, int quantity, double price, boolean veganOption, int veganAmt) {
		this.number = number;
		this.quantity = quantity;
		this.price = price;
		this.veganOption = veganOption;
		this.veganAmt = veganAmt;
	}

	public boolean isVeganOption() {
		return veganOption;
	}

	public int getVeganAmt() {
		return veganAmt;
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
		return ((quantity - veganAmt) * price) + (veganAmt * (price - (price * 0.15)));
	}
}