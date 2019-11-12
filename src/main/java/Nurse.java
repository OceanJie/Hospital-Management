package main.java;

/**
 * Nurse class
 * 
 * @author Nicholas Ong
 */
public class Nurse {
	
	private String name;
	private Salary salary;
	
	public Nurse(String name, double salary) {
		this(name, salary, 0);
	}
	
	public Nurse(String name, double salary, double bonus) {
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
