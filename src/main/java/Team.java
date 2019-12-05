package main.java;

import java.sql.Statement;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Team {
    private ArrayList<String> names;
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

    public boolean addStaff(String name){
        for(String temp : names){
            if(temp == name) {
                System.out.println("The person is already in the shift");
                return false;
            }
        }
        this.names.add(name);
        System.out.println("the person is successfully added");
        return true;
    }

    public boolean removeStaff(String name){
        for(String temp : names){
            if(temp == name) {
                this.names.remove(name);
                System.out.println("the person is successfully removed");
                return true;
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
                    if(temp.getDescription().equals(description))
                        System.out.println("Project has been created");
                    else
                        projects.add(new Projects(description, start, end));
                }
            } else
                projects.add(new Projects(description, start, end));
            return true;
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
            if(pChoice > projects.size() || nChoice > names.size()) {
                System.out.println("There is no " + pChoice + " option or " + nChoice +" for people");
                return false;
            }
            else {
                if(!names.isEmpty()) {
                    projects.get(pChoice - 1).addStaff(names.get(nChoice - 1));
                    return true;
                }
                else{
                    System.out.println("There is no one to add");
                    return false;
                }
            }
        }
        System.out.println("There are currently no projects");
        return false;
    }

    public boolean removePeopleFromProject(int pChoice, int nChoice){
        if(!projects.isEmpty()){
            if(pChoice > projects.size()) {
                System.out.println("There is no " + pChoice + " option.");
                return false;
            }
            else {
                    projects.get(pChoice - 1).removeStaff(nChoice - 1);
                    return true;
            }
        }
        System.out.println("There are currently no projects");
        return false;
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
        if(!names.isEmpty()) {
            System.out.println("People");
            System.out.println("=================================\n");
            for (int i = 0; i < names.size(); i++) {
                System.out.println("" + (i + 1) + ". " + names.get(i));
            }
            System.out.println("There are currently no peoples");
        }
    }


    public void showDetail(Statement myStmt){
        System.out.println("Shift: " + startTime + " - " + endTime);
        if(!names.isEmpty()) {
            System.out.println("People: ");
            for (String name : this.names) {
                for (int i = 0; i < tablenameOption.length; i++) {
                    if (conn.isStringEntityExist(myStmt, tablenameOption[i], "name", name)) {
                        System.out.println(name + "position: " + tablenameOption[i]);
                    }
                }
            }
            System.out.println("\n");
        }
        if(!projects.isEmpty()){
            for(Projects temp : this.projects){
                temp.showDetail(myStmt);
                System.out.println("\n");
            }
        }
    }

    public double calculateTotalCost(Statement myStmt){
        double totalCost = 0.00;
        if(!projects.isEmpty()){
            for(Projects temp : this.projects){
                totalCost += temp.calculateCost(myStmt);
            }
            System.out.println("Total cost of all the projects: " + totalCost);
        }
        else{
            System.out.println("Total cost of all the projects: " + totalCost);
        }
        return totalCost;
    }
}
