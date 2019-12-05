package main.java;

/**
 * 
 * The menu for the cafeteria
 * 
 * @author Nicholas Ong
 */
public class Menu {

	private String name;
	private double price;
	private int quantity;
	private boolean veganOption;

	public Menu(String name, double price, int quantity, boolean veganOption) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.veganOption = veganOption;
	}

	public boolean hasVeganOption() {
		return veganOption;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int amount) {
		quantity = amount;
	}

	public void addQuantity(int amount) {
		quantity += amount;
		if(quantity < 0)
			quantity = 0;
	}

	public void consume() {
		quantity -= 1;
	}

	public void deductQuantity(int amount) {
		quantity -= amount;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}