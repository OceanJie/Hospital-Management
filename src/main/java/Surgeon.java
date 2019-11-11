package main.java;

/**
 * Surgeon class
 * 
 * @author Nicholas Ong
 * @version 1.0
 */
public class Surgeon {

	private String name;
	private Salary salary;
	
	public Surgeon(String name, double salary) {
		this(name, salary, 0);
	}
	
	public Surgeon(String name, double salary, double bonus) {
		this.name = name;
		this.salary = new Salary(salary, bonus);
	}
	
	public String getName() {
		return name;
	}
	
	public Salary getSalary() {
		return salary;
	}
	
	public boolean setSalary(double newSalary) {
		if(newSalary <= 0) 
			return false;
		else {
			salary.setSalary(newSalary);
			return true;	
		}
	}
	
	public boolean setBonus(double newBonus) {
		if(newBonus <= 0) 
			return false;
		else {
			salary.setBonus(newBonus);
			return true;
		}
	}
}
