package main.java;

import main.java.staff.Employee;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class Projects {
    private String description;
    private ArrayList<String> names;
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

    public void addStaff (String name){
        this.names.add(name);
    }

    public void removeStaff (int index){
        this.names.remove(index);
    }

    public void printNames(){
        for( String name : this.names){
            System.out.println(name);
        }
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

    public double calculateCost(Statement myStmt){
        double hourly = 0;
        double cost = 0;
        if(!names.isEmpty()){
            for (String name : this.names) {
                for (int i = 0; i < tablenameOption.length; i++) {
                    if (conn.isStringEntityExist(myStmt, tablenameOption[i], "name", name)) {
                        hourly = Double.parseDouble(conn.getStringEntity(myStmt, tablenameOption[i], "salary", "name", name));
                        hourly /= 160;
                        cost += hourly * getDuration();
                    }
                }
            }
        }
        return cost;
    }

    public void showDetail(Statement myStmt){
        System.out.println(description);
        if(!names.isEmpty()) {
            for (String name : this.names) {
                for (int i = 0; i < tablenameOption.length; i++) {
                    if (conn.isStringEntityExist(myStmt, tablenameOption[i], "name", name)) {
                        System.out.println(name + " position: " + tablenameOption[i]);
                    }
                }
            }
        }
        else
            System.out.println("There is no one in this project");
    }
}
