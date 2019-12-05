package main.java.staff;

public class Doctor implements Employee {
	/*
*Doctor class
*
* @author Bojun Lin
*/

	private String doctor_name;
	private int id;
	private Salary salary;
	private double bonus;

	public Doctor(String name)
	{
		this("0", name, 0);
	}

	public Doctor(String id, String name, double salary) {
		doctor_name = name;
		this.salary = new Salary(salary, 0);
		this.bonus = 0;
	}
	
	public Doctor(String id, String name) {
		this(id, name, 0);

}


	public Doctor(String name, double salary) {
		this("0", name, salary);
	}

	@Override
	public void setId(int newId) {

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
		return this.salary;
	}

	@Override
	public double getBonus() {
		return bonus;
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
	
	public int getId(){return id;}
}
