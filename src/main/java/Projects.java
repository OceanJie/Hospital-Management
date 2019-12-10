package main.java;

import main.java.hospital_obj.Staff;
import main.java.staff.Employee;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class Projects {
    private String description;
    private ArrayList<Staff> names;
    private String startTime;
    private String endTime;
    private static HospitalController conn = new HospitalController();
    private static String[] tablenameOption = {" ", "doctors", "surgeons", "nurses", "receptionist", "hr", "pharmacist", "security"};

    public Projects(String description, String start, String end){
        this.description = description;
        this.startTime = start;
        this.endTime = end;
        names = new ArrayList<>();
    }

    public boolean addStaff (String name , String job, double salary){
        if(!names.isEmpty()) {
            for (Staff temp : names) {
                if (temp.getName().equals(name)) {
                    System.out.println("The person is already in the project");
                    return false;
                } else{
                    names.add(new Staff(job, name, salary));
                    System.out.println("the person has been successfully added");
                    return true;
                }
            }
        } else{
            names.add(new Staff(job, name, salary));
            System.out.println("the person has been successfully added");
            return true;
        }
        return false;
    }

    public void removeStaff (String name){
        int count = 0;
        for(Staff staff: names){
            if(staff.getName().equals(name)){
                names.remove(count);
            }
            count ++;
        }
    }

    public void printNames(){
        for( Staff name : this.names){
            name.printOut();
        }
    }

    public boolean findStaff(String name){
        for (Staff temp: names){
            if(temp.getName().equals(name))
                return true;
        }
        return false;
    }

    public String getDescription(){
        return description;
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

    private double getDuration(){
        double duration = 0.00;
        int startHour = parseInt(""+startTime.charAt(0) + startTime.charAt(1));
        int endHour = parseInt(""+endTime.charAt(0) + endTime.charAt(1));
        int startMin = parseInt(""+startTime.charAt(3) + startTime.charAt(4));
        int endMin = parseInt(""+endTime.charAt(3) + endTime.charAt(4));
        double hours = 0;
        double mins = 0.00;

        while(startMin != endMin){
            startMin++;
            mins ++;
            if(startMin == 60) {
                startMin = 0;
                startHour++;
            }
        }

        while(startHour != endHour){
            if(startHour == 24)
                startHour = 0;
            startHour++;
            hours ++;
        }

        duration = hours + (mins/60);
        return duration;
    }

    public double calculateCost(){
        double hourly = 0;
        double cost = 0;
        if(!names.isEmpty()){
            for (Staff name : this.names) {
                hourly = name.getSalary() / 160;
                cost += hourly * getDuration();
                    }
        }
        return cost;
    }

    public void showDetail(){
        System.out.println(description + " Time: " + startTime + " - " + endTime);
        if(names.isEmpty()) {
            System.out.println("There is no one in this project");
        }
        else {
            for (Staff name : this.names) {
                name.printOutDetails();
            }
        }
    }
}
