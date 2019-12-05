package main.java.staff;

public interface Employee {
    public int id = 0;
    public Salary salary = new Salary(0,0);
    public double bonus = 0;
    public void setId(int newId);
    public void setName(String newName);
    public boolean setSalary(double newSalary);
    public boolean setBonus(double newBonus);
    public int getId();
    public String getName();
    public Salary getSalary();
    public double getBonus();


}
