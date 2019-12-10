package main.java.command.DoctorCmd;

import main.java.HospitalController;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DoctorFunction {
    private static HospitalController conn = new HospitalController();
    private static String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
    private static String user = "root";
    private static String password = "1234";
    private static Statement myStmt;
    private static Scanner scan = new Scanner(System.in);
    private static String[] tablenameOption = {" ", "doctors", "surgeons", "nurses", "receptionist", "hr", "pharmacist"};
    private static final String JOBOPTION =  "1. Doctor \n" +
            "2. Surgeon \n" +
            "3. Nurse \n" +
            "4. Receptionist \n" +
            "5. Human Resources \n" +
            "6. Pharmacist \n" +
            "-1. Back to home \n";

    public static void givePrescription(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection myConn = DriverManager.getConnection(url, user, password);
            /*just use this one for the myStmt*/
            myStmt = myConn.createStatement();
            System.out.println("Enter Appointment Id");
            int appId = scan.nextInt();
            System.out.println("Enter Medicine Name");
            String medName = scan.next();
            conn.updatePrescription(myStmt,appId,medName);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void viewDoctorAppointment(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection myConn = DriverManager.getConnection(url, user, password);
            /*just use this one for the myStmt*/
            myStmt = myConn.createStatement();
            System.out.println("Please put in the doctor's username");
            String username = scan.next();
            String dname = conn.getStringEntity(myStmt,"doctors","name","username",username);
            conn.getDoctorAppointment(myStmt,dname);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
