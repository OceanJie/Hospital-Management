package main.java;

import main.java.hospital_obj.Staff;
import main.java.staff.Employee;

import java.sql.Statement;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Team {
    private ArrayList<Staff> names;
    private ArrayList<Projects> projects;
    private String startTime;
    private String endTime;
    private static HospitalController conn = new HospitalController();
    private static String[] tablenameOption = {" ", "doctors", "surgeons", "nurses", "receptionist", "hr", "pharmacist", "security"};

    public Team(String start, String end){
        this.startTime = start;
        this.endTime = end;
        names = new ArrayList<>();
        projects = new ArrayList<>();
    }

    public boolean addStaff(String job, String name, double salary){
        for(Staff temp : names){
            if(temp.getName().equals(name)) {
                System.out.println("The person is already in the shift");
                return false;
            }
        }
        this.names.add(new Staff(job, name, salary));
        System.out.println("the person is successfully added");
        return true;
    }

    public boolean removeStaff(String name){
        int count = 0;
        if(!names.isEmpty()) {
            for (Staff temp : names) {
                if (temp.getName().equals(name)) {
                    this.names.remove(count);
                    if(!projects.isEmpty()){
                        int i = 0;
                        for(Projects pTemp: projects){
                            if(pTemp.findStaff(name)) {
                                pTemp.removeStaff(name);
                            }
                            i++;
                        }
                    }
                    System.out.println("the person is successfully removed from the team");
                    return true;
                }
                count++;
            }
        }
        System.out.println("The person is not in the Shift");
        return false;
    }

    public String getStartTime(){
        return startTime;
    }

    public String getEndTime(){
        return endTime;
    }

    public void changeStart(String time){
        startTime = time;
    }

    public void changeEnd(String time){
        endTime = time;
    }

    private boolean inRange(int n) {
        int tempStart = parseInt("" + startTime.charAt(0) + startTime.charAt(1));
        int tempEnd = parseInt("" + endTime.charAt(0) + endTime.charAt(1));
        ArrayList<Integer> temp = new ArrayList<>();
        while (tempStart != tempEnd) {
            if (tempStart == 24)
                tempStart = 0;
            temp.add(tempStart);
            tempStart++;
        }
        temp.add(tempStart);
        for (int num : temp) {
            if (n == num)
                return true;
        }
        return false;
    }

    private boolean isOverlap(String start, String end){
        int startHour = parseInt(""+start.charAt(0) + start.charAt(1));
        int endHour = parseInt(""+end.charAt(0) + end.charAt(1));
        int startMin = parseInt(""+start.charAt(3) + start.charAt(4));
        int endMin = parseInt(""+end.charAt(3) + end.charAt(4));
        int oriStartHour = parseInt(""+startTime.charAt(0) + startTime.charAt(1));
        int oriEndHour = parseInt(""+endTime.charAt(0) + endTime.charAt(1));
        int oriStartMin = parseInt(""+startTime.charAt(3) + startTime.charAt(4));
        int oriEndMin = parseInt(""+endTime.charAt(3) + endTime.charAt(4));

        if(!inRange(startHour) || !inRange(endHour))
            return false;
        if(inRange(startHour) && startHour == oriStartHour && oriStartMin - startMin > 0)
            return false;
        if(inRange(endHour) && endHour == oriEndHour && oriEndMin - endMin < 0)
            return false;

        return true;
    }
    public boolean createProject(String description, String start, String end){

        if(!isOverlap( start, end)){
            System.out.println("Time does not match the with the shift time");
            return false;
        }
        else {
            if(!projects.isEmpty()) {
                for (Projects temp: projects) {
                    if(temp.getDescription().equals(description)) {
                        System.out.println("Project existed");
                        return false;
                    }
                    else {
                        projects.add(new Projects(description, start, end));
                        System.out.println("Project has been successfully created");
                        return true;
                    }
                }
            } else {
                projects.add(new Projects(description, start, end));
                System.out.println("Project has been successfully created");
                return true;
            }
            return false;
        }
    }

    public boolean deleteProject(int choice){
        if(!projects.isEmpty()){
            if(choice > projects.size()) {
                System.out.println("There is no " + choice + " option.");
                return false;
            }
            else {
                projects.remove(choice - 1);
                return true;
            }
        }
            System.out.println("There are currently no projects to be remove");
            return false;
    }

    public boolean addPeopleToProject(int pChoice, int nChoice){
        if(!projects.isEmpty()){
            if(!names.isEmpty()){
               projects.get(pChoice - 1).addStaff(names.get(nChoice - 1).getName(), names.get(nChoice - 1).getRole(), names.get(nChoice - 1).getSalary());
            } else{
                System.out.println("There are no staff in the team");
            }

        } else {
            System.out.println("There are currently no projects");
            return false;
        }
        return false;
    }

    public boolean removePeopleFromProject(int pChoice, String nChoice){
        if(!projects.isEmpty()){
            if(pChoice - 1> projects.size()) {
                System.out.println("There is no " + pChoice + " option.");
                return false;
            }
            else {
                    projects.get(pChoice - 1).removeStaff(nChoice);
                    return true;
            }
        }
        System.out.println("There are currently no projects");
        return false;
    }

    public void printSelectedProjectPplList(int pChoice){
        if(!projects.isEmpty()){
            if(pChoice - 1< projects.size()) {
                projects.get(pChoice - 1).printNames();
            }
        }
        else{
            System.out.println("There are currently no project in the team");
        }
    }

    public void printProjectsList(){
        if(!projects.isEmpty()){
            System.out.println("Projects");
            System.out.println("=================================\n");
            for(int i = 0; i < projects.size(); i++){
                System.out.println("" + (i + 1) + ". " + projects.get(i).getDescription());
            }
        } else
            System.out.println("There are currently no projects");
    }

    public void printPoeplesList(){
        int count = 1;
        if(!names.isEmpty()) {
            System.out.println("\nPeople");
            System.out.println("=================================\n");
            for (Staff temp : this.names) {
                System.out.print(count + ". ");
                temp.printOut();
                count++;
            }
            System.out.println("\n");
        }
        else {
            System.out.println("There are currently no peoples");
        }
    }


    public void showDetail(){
        System.out.println("Shift: " + startTime + " - " + endTime);
        if(!names.isEmpty()) {
            System.out.println("People: ");
            for (Staff temp : this.names) {
                temp.printOut();
            }
            System.out.println("\n");
        }
        if(!projects.isEmpty()){
            for(Projects temp : this.projects){
                temp.showDetail();
                System.out.println("\n");
            }
        }
        if(projects.isEmpty()){
            System.out.println("There is no projects now");
        }
    }

    public void printShift(){
        System.out.println("Shift: " + startTime + " - " + endTime);
    }

    public double calculateTotalCost(){
        double totalCost = 0.00;
        if(!projects.isEmpty()){
            for(Projects temp : this.projects){
                totalCost += temp.calculateCost();
            }
        }
        return totalCost;
    }

    public double getProjectCost(int pChoice){
        double cost = 0;
        if(!projects.isEmpty()){
            cost = projects.get(pChoice - 1).calculateCost();
        }
        return cost;
    }
}
