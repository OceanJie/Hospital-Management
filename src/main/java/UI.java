package main.java;

import java.sql.DriverManager;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Main UI for Hospital Management System
 * 
 * @author Nicholas Ong
 */
public class UI {
	public static void main(String[] args) {
		/*Connecting to the local server*/
		String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
		String user = "root";
		String password = "1234";
		HospitalController conn = new HospitalController();
		int option = 0;

		try {
			/*downloading the driver for jbdc*/
			Class.forName("com.mysql.cj.jdbc.Driver");
			java.sql.Connection myConn = DriverManager.getConnection(url, user, password);
			/*just use this one for the myStmt*/
			Statement myStmt = myConn.createStatement();

			Scanner scan = new Scanner(System.in);
			do{
				System.out.println();
				System.out.println("Enter the operation number");
				System.out.println("1. Add Doctor");
				System.out.println("2. Add Patient");
				System.out.println("3. Make Appointment");
				System.out.println("4. Get Prescription");
				System.out.println("5. Check Schedule");
				System.out.println("6. Give Prescription");
				System.out.println("7. Give Medicine");
				System.out.println("8. Update Inventory");
				System.out.println("9: Exit");
				System.out.println("Your option: ");

				option = scan.nextInt();

				if (option < 0 || option > 8 ) {
					System.out.println("Invalid Input");
					System.out.println("Enter the operation number");
					System.out.println("1. Add Doctor");
					System.out.println("2. Add Patient");
					System.out.println("3. Make Appointment");
					System.out.println("4. Get Prescription");
					System.out.println("5. Check Schedule");
					System.out.println("6. Give Prescription");
					//System.out.println("7. Give Medicine");
					System.out.println("7. Update Inventory");
					System.out.println("8. Exit program");
					System.out.println("Your option: ");

					option = scan.nextInt();
				}

				switch(option) {
					/*Add doctor*/

					case 1:

						System.out.println("Enter the doctor's First name: No space in between");
						String docFirstName = scan.next();
						System.out.println("Enter the doctor's Last Name");
						String docLastName = scan.next();
						String docName = docFirstName + " "+ docLastName;
						System.out.println("Enter the doctor's ID: ");
						String docID = scan.next();
						Doctor doc = new Doctor(docID,docName);
						conn.createDoctor(myStmt,doc);

						break;


					/*Add patient*/
					case 2:

						System.out.println("Enter the patient's First name: No space in between");
						String patientFirstName_case2 = scan.next();
						System.out.println("Enter the patient's Last Name: No space in between");
						String patientLastName_case2 = scan.next();
						String patientName_case2 = patientFirstName_case2 + " "+ patientLastName_case2;
						System.out.println("Enter the patient's ID: ");
						String patientID = scan.next();

						Patient p_case2 = new Patient(patientID,patientName_case2);
						conn.createPatient(myStmt,p_case2);
						break;
					/*Make Appointment*/
					case 3:
						System.out.println("Enter the appointment ID: ");
						int appId = scan.nextInt();
						System.out.println("Enter the patient's First name: No space in between");
						String patientFirstName_case3 = scan.next();
						System.out.println("Enter the patient's Last Name: No space in between");
						String patientLastName_case3 = scan.next();
						String patientName_case3 = patientFirstName_case3+ " "+patientLastName_case3;
						/*check if patient exist or not, require to create new patient before proceeding to make appointment*/
						if (!conn.isPatientExist(myStmt,patientName_case3)){
							System.out.printf("Patient Name: %s does not exist. Please create a new patient before making appointment or use a existing patient ",patientName_case3);
							break;
						}

						System.out.println("Enter the Month of appointment (Ex: 12): ");
						int month = scan.nextInt();
						System.out.println("Enter the day of the appointment (Ex: 30)");
						int day = scan.nextInt();
						System.out.println("Enter the hour (24-hr format): ");
						int hours = scan.nextInt();
						System.out.println("Enter the minute: ");
						int minutes = scan.nextInt();
						/*check if the date and time are valid or not*/
						if(day<1||day>31 ||month>12||month<1 || hours < 0 || hours > 24 || minutes > 60 || minutes < 0) {
							System.out.println("Invalid Date and time, Please use 24 hours format. E.g Hours: 24 Minutes: 30. Please make sure the date and time is valid. ");
							break;
						}

						System.out.println("Enter the First name of the doctor: No space in between");
						String doctorFirstName_case3 = scan.next();
						System.out.println("Enter the last name of the doctor: No space in between");
						String doctorLastName_case3 = scan.next();
						String doctorName_case3 = doctorFirstName_case3 + " "+ doctorLastName_case3;
						/*check  if doctor exist in the table*/
						if(!conn.isStringEntityExist(myStmt,"doctors","doctorName",doctorName_case3)){
							System.out.printf("Doctor Name: %s does not exist. Please create a new patient before making appointment or use a existing doctor ",doctorName_case3);
							break;
						}
						/*prescription not given yet so initialize it as null*/
						String prescrip = "";
//
						Appointment app = new Appointment(appId,patientName_case3,doctorName_case3,month,day,hours,minutes,prescrip);
						conn.createAppointment(myStmt,app);

						break;
					/*Get Prescription*/
					case 4:
						System.out.println("Enter the patient's First name: No space in between");
						String patientFirstName_case4 = scan.next();
						System.out.println("Enter the patient's Last Name: No space in between");
						String patientLastName_case4 = scan.next();
						String patientName_case4 = patientFirstName_case4+ " "+patientLastName_case4;
						conn.getPatientPrescrip(myStmt,patientName_case4);
						break;
					/*Check Schedule*/
					case 5:
						System.out.println("Enter the First name of the doctor: No space in between");
						String doctorFirstName_case5 = scan.next();
						System.out.println("Enter the last name of the doctor: No space in between");
						String doctorLastName_case5 = scan.next();
						String doctorName_case5 = doctorFirstName_case5 + " "+ doctorLastName_case5;

						conn.getDoctorAppointment(myStmt,doctorName_case5);


						break;
					/*Give Prescription*/
					case 6:
						System.out.println("Enter the appointment's ID: ");
						int appID = scan.nextInt();
						System.out.println("Enter the prescription: ");
						String prescription = scan.next();
						conn.updatePrescription(myStmt, appID,prescription);

						break;
					/*Give Medicine (Not implemented yet)*/
					case 7:
						System.out.println("7");
						break;
					/*Update inventory (not implemented yet)*/
					case 8:
						System.out.println("Enter the medicine Id: ");
						int medId = scan.nextInt();
						System.out.println("Enter the new Amount: ");
						int newAmount = scan.nextInt();
						conn.updateInventory(myStmt,medId,newAmount);
						break;
					/*Exit program*/
					case 0:
						System.out.println("Program end");
						break;
				}
				Thread.sleep(2000);
			}
			while(option!=0);

			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}

