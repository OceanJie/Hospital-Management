package main.java.staff;

import main.java.staff.Employee;
import main.java.staff.Salary;

public class SecurityGuard implements Employee {

    private String securityGuardName;
    private int id;
    private Salary salary;
    private String password;

    public SecurityGuard(int id, String name, String pasword,double salary, double bonus) {
        this.id = id;
        securityGuardName = name;
        this.password = pasword;
        this.salary = new Salary(salary,bonus);

    }

    public SecurityGuard(int id, String name, String password){
        this.id = id;
        this.securityGuardName = name;
        this.password = password;

    }

    @Override
    public void setId(int newId) {
        id = newId;
    }

    @Override
    public void setName(String newName) {
        securityGuardName = newName;
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return securityGuardName;
    }

    @Override
    public Salary getSalary() {
        return this.salary;
    }

    @Override
    public double getBonus() {
        return bonus;
    }
}
