package main.java.staff;

/**
 * Salary for employees
 * 
 * @author Nicholas Ong
 * @version 1.0
 */
public class Salary {

	private double salary;
	private double bonus;
	
	public Salary(double salary, double bonus) {
		this.salary = salary;
		this.bonus = bonus;
	}
	
	public double getBaseSalary() {
		return salary;
	}
	
	public double getBonus() {
		return bonus;
	}
	
	public double getTotalSalary() {
		return getBaseSalary() + getBonus();
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
}
