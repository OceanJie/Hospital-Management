package main.java;

public class CafeteriaAttendant {
	
	public static boolean ATTENDANT_LOGIN = false;
	private String name;
	private int ID;
	private Salary salary;
	
	public CafeteriaAttendant(int ID, String name, double salary) {
		ATTENDANT_LOGIN = true;
		this.name = name;
		this.ID = ID;
		this.salary = new Salary(salary, 0);
	}
	
	public boolean operateCafeteria() {
		Cafeteria c = new Cafeteria();
		return c.operate();
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return ID;
	}
	
	public Salary getSalary() {
		return salary;
	}
	
	public static void main(String[] args) {
		Cafeteria c = new Cafeteria();
		if(c.operate())
			System.out.println("OKAY");
		else
			System.out.println("ERROR");
		CafeteriaAttendant ca = new CafeteriaAttendant(1, "sohai", 0);
		ca.operateCafeteria();
	}
}
