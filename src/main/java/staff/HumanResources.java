package main.java.staff;

public class HumanResources implements Employee {
	private String name;
	private int id;
	private Salary salary;
	private String picPath;

	public HumanResources() {

	}

	public boolean payEmployee(Doctor name, int hoursWorked, double payRate) {

		name.setSalary(hoursWorked * payRate);

		return true;
	}

	public boolean payEmployee(Nurse name, int hoursWorked, double payRate) {

		name.setSalary(hoursWorked * payRate);

		return true;
	}

	public boolean payEmployee(Surgeon name, int hoursWorked, double payRate) {

		name.setSalary(hoursWorked * payRate);

		return true;
	}

	@Override
	public void setId(int newId) {

	}

	@Override
	public void setName(String newName) {

	}

	@Override
	public boolean setSalary(double newSalary) {
		return false;
	}

	@Override
	public boolean setBonus(double newBonus) {
		return false;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Salary getSalary() {
		return null;
	}

	@Override
	public double getBonus() {
		return 0;
	}

//	@Override
//	public String getPicturePath() {
//		return picPath;
//	}
}
