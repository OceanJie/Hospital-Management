package main.java;

public class HumanResources {
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
}