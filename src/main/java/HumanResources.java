package main.java;

import java.util.Scanner;

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
	
	@SuppressWarnings("resource")
	public boolean useSearchEngine() {
		Scanner scan = new Scanner(System.in);
		String input = "";
		
		while(true) {
			System.out.println("Do you want to use the search engine? (y/n)");
			input = scan.next();
			if(input.equalsIgnoreCase("y")) {
				Marketing m = new Marketing();
				m.search();
				return true;
			} else
				break;
		}
		
		scan.close();
		return false;
	}
	
	//Testing
	public static void main(String[] args) {
		HumanResources hr = new HumanResources();
		System.out.println(hr.useSearchEngine());
				
	}
}
