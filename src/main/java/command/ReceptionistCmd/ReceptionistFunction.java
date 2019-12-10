package main.java.command.ReceptionistCmd;

import main.java.HospitalController;
import main.java.Patient;
import main.java.command.DoctorCmd.DoctorFunction;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ReceptionistFunction {

    private static HospitalController conn = new HospitalController();
    private static String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
    private static String user = "root";
    private static String password = "1234";
    private static Statement myStmt;
    private static Scanner scan = new Scanner(System.in);


    public static void createNewAppointment(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection myConn = DriverManager.getConnection(url, user, password);
            /*just use this one for the myStmt*/
            myStmt = myConn.createStatement();

            System.out.println("Enter the patient's First name: No space in between");
            String patientFirstName_case3 = scan.next();
            System.out.println("Enter the patient's Last Name: No space in between");
            String patientLastName_case3 = scan.next();
            String patientName_case3 = patientFirstName_case3 + " " + patientLastName_case3;
            /*check if patient exist or not, require to create new patient before proceeding to make appointment*/
            if (!conn.isPatientExist(myStmt, patientName_case3)) {
                System.out.printf("Patient Name: %s does not exist. Please create a new patient before making appointment or use a existing patient ", patientName_case3);

            }
            System.out.println("Enter the First name of the doctor: No space in between");
            String doctorFirstName_case3 = scan.next();
            System.out.println("Enter the last name of the doctor: No space in between");
            String doctorLastName_case3 = scan.next();
            String dname = doctorFirstName_case3+doctorLastName_case3;

            /*check  if doctor exist in the table*/
            if (!conn.isStringEntityExist(myStmt, "doctors", "doctorName", dname)) {
                System.out.printf("Doctor Name: %s does not exist. Please use a existing doctor ", dname);
                System.out.println("This is all the doctor available: \n");
                getAllDoctor();
                return;
            }
            System.out.printf("This is Dr %s current appointment: (Please avoid time conflict)",dname);
            conn.getDoctorAppointment(myStmt,dname);
            System.out.println("Enter the date of appointment (yyyymmdd): ");
            int date = scan.nextInt();

            System.out.println("Enter the time (24-hr format hhmm): ");
            int time = scan.nextInt();
            time *= 100;


            /*prescription not given yet so initialize it as null*/
            String prescrip = "";

            String exeUpdate = "INSERT INTO mydb.appointments ( patientName, doctorName, date,time, prescription) VALUES (";
            myStmt.executeUpdate(exeUpdate + "'" + patientName_case3 + "'" + "," + "'" + dname + "'" + "," + "'" + date + "'" +  ","
                    + "'" + time + "'" + ","  + "'" + prescrip + "'" +
                    ")");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void getAllDoctor(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection myConn = DriverManager.getConnection(url, user, password);
            /*just use this one for the myStmt*/
            myStmt = myConn.createStatement();
            conn.getAllFromTable(myStmt,"doctors");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void viewPatientProfile(){
        DoctorFunction dFunc = new DoctorFunction();
        dFunc.viewPatientProfile();
    }
    public static void addPatient(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection myConn = DriverManager.getConnection(url, user, password);
            /*just use this one for the myStmt*/
            myStmt = myConn.createStatement();
            System.out.println("Enter Patient first Name");
            String pFirstName = scan.next();
            System.out.println("Enter Patient Last Name");
            String pLastName = scan.next();
            String pName = pFirstName+pLastName;
            System.out.println("Enter BloodType");
            String bloodType = scan.next();
            System.out.println("Enter insurance amount,enter 0 if no insurance");
            int insuranceAmount = scan.nextInt();
            System.out.println("Enter payment type: cash or credit card");
            String paymentType = scan.next();
            conn.createPatient(myStmt,pName,bloodType,paymentType,insuranceAmount);


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
            System.out.println("Please enter doctor's name");
            String dName = scan.next();

            conn.getDoctorAppointment(myStmt,dName);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void checkDoctorSchedule(){
        DoctorFunction dFunc = new DoctorFunction();
        dFunc.viewDoctorAppointment();
    }
}
