package main.java.command.NurseCmd;

import main.java.HospitalController;
import main.java.Patient;
import main.java.SurgeryRoomAppointment;
import main.java.command.ReceptionistCmd.ReceptionistFunction;
import main.java.hospital_obj.SurgeryRoom;
import main.java.staff.Receptionist;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class NurseFunction {
    private static HospitalController conn = new HospitalController();
    private static String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
    private static String user = "root";
    private static String password = "1234";
    private static Statement myStmt;
    private static Scanner scan = new Scanner(System.in);

    public static void viewPatientProfile() {
        ReceptionistFunction receptionistFunction = new ReceptionistFunction();
        receptionistFunction.viewPatientProfile();
    }

    public static void addPatientToWard() {
        System.out.println("Enter Patient Id");
        int pId = scan.nextInt();
        System.out.println("Enter Doctor Id");
        int docId = scan.nextInt();
        System.out.println("Enter Ward ID");
        int wardId = scan.nextInt();
        conn.addPatientToWard(myStmt, pId, docId, wardId);
    }

    public static void addPatientToEmergency() {
        System.out.println("Enter Patient Name");
        String patientName = scan.next();
        System.out.println("Enter Doctor Name");
        String doctorName = scan.next();
        System.out.println("Enter Emergency Ward ID");
        int emergId = scan.nextInt();
        conn.addPatientToEmerg(myStmt, patientName, doctorName, emergId);
    }

    public static void movePatientFromEmergToWard() {
        System.out.println("Enter Emergency Ward ID");
        int emergId = scan.nextInt();
        System.out.println("Enter Ward ID that patient move to");
        int wardId = scan.nextInt();
        conn.movePatientFromEmergToWard(myStmt, emergId, wardId);
    }

    public static void movePatientFromEmergToSurg() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection myConn = DriverManager.getConnection(url, user, password);
            /*just use this one for the myStmt*/
            myStmt = myConn.createStatement();
            System.out.println("Enter Emergency Ward ID");
            int emergId = scan.nextInt();
            String pName = conn.getStringEntity(myStmt,"emergId","patientName","emergId",Integer.toString(emergId));

            String pId = conn.getStringEntity(myStmt,"patients","id","name",pName);
            Patient p = new Patient(pId,pName);
            if (!conn.isStringEntityExist(myStmt, "patients", "patientName", pName)) {
                System.out.printf("Patient Name: %s does not exist. Please create patient before making appointment", pName);
            }
            System.out.println("Enter the date: ");
            int sraDate = scan.nextInt();
            System.out.println("Enter the time (hour): ");
            int sraHour = scan.nextInt();
            System.out.println("Enter the time (minute): ");
            int sraMin = scan.nextInt();
            System.out.println("Enter the room ID: ");
            int roomID = scan.nextInt();
            System.out.println("Enter the First name of the surgeon: No space in between");
            String surgeonFN = scan.next();
            System.out.println("Enter the last name of the surgeon: No space in between");
            String surgeonLN = scan.next();
            String surgeonN = surgeonFN + " " + surgeonLN;
            System.out.println("Enter the First name of the nurse: No space in between");
            String nurseFN = scan.next();
            System.out.println("Enter the last name of the nurse: No space in between");
            String nurseLN = scan.next();
            String nurseN = nurseFN + " " + nurseLN;
            if(!HospitalController.isStringEntityExist(myStmt,"nurses","name", nurseN)){
                System.out.printf("Nurse Name: %s does not exist. Please create a new nurse before making a surgery appointment or use a existing nurse ", nurseN);
                return;
            }
            String exeUpdate = "INSERT INTO mydb.surgeryapp ( patientName, surgeonName, nurseName, startTime, roomID) VALUES (";
            myStmt.executeUpdate(exeUpdate  + "'" + pName + "'" + "," + "'" + surgeonN + "'" + ","
                    + "'" + nurseN + "'" +  "," + "'" + sraHour + "'" + ","  + "'" + roomID + "'"  + ")");

            System.out.printf("\t Patient name: %s \t Surgeon Name: %s \t Nurse Name: %s \t Starting Time: %d \t Room ID: %d" +
                    "\nhave successfully added into database",  pName, surgeonN, nurseN, sraHour, roomID);



        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    }

