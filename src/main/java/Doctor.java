package main.java;

public class Doctor {
	/*
*Doctor class
*
* @author Bojun Lin
*/

	private String doctor_name;
	private String id;
	private Salary salary;

	public Doctor(String name)
	{
		this("0", name, 0);
	}

	public Doctor(String id, String name, double salary) {
		doctor_name = name;
		this.salary = new Salary(salary, 0);
	}
	
	public Doctor(String id, String name) {
		this(id, name, 0);
	}
	
	public Doctor(String name, double salary) {
		this("0", name, salary);
	}

	public void setName(String name)
	{
		doctor_name = name;
	}
	public String getName()
	{
		return doctor_name;
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
	
	public String getId(){return id;}
}
