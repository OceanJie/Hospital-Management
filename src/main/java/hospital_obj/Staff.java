package main.java.hospital_obj;

public class Staff {
    private String role;
    private String name;
    private double salary;

    public Staff(String job, String name, double salary){
        this.role = job;
        this.name = name;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void printOut(){
        System.out.println("Job: " + role + " Name: " + name);
    }

    public void printOutDetails(){
        System.out.println("Job: " + role + " Name: " + name + " Salary: " + salary);
    }
}
