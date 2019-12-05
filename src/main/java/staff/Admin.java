package main.java.staff;

/**
 * Admin class
 * @author Sheng Jie Ooi
 */
public class Admin implements Employee {

	private int id;
	private String name;
	private String adminPassword;
	private Salary salary;



	public Admin(int adminId, String adminName, String adminPassword,double salary, double bonus){
		this.id = adminId;
		this.name =adminPassword;
		this.adminPassword = adminPassword;
		this.salary = new Salary(salary,bonus);
	}

	public Admin(int adminId, String adminName, String adminPassword){
		this.id = adminId;
		this.name =adminPassword;
		this.adminPassword = adminPassword;

	}

	@Override
	public void setId(int newId) {
	id = newId;
	}

	@Override
	public void setName(String newName) {
	name = newName;
	}

	@Override
	public boolean setSalary(double newSalary) {
		if(newSalary<=0){
			return false;
		}
		salary.setSalary(newSalary);
		return true;
	}

	@Override
	public boolean setBonus(double newBonus) {
		if(newBonus <= 0)
			return false;
		else {
			salary.setBonus(newBonus);
			return true;
		}
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Salary getSalary() {
		return this.salary;
	}

	@Override
	public double getBonus() {
		return this.salary.getBonus();
	}
}

