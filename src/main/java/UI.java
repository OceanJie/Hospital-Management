package main.java;

import main.java.staff.Doctor;
import main.java.staff.HumanResources;
import main.java.staff.Nurse;
import main.java.staff.Surgeon;

import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Main UI for Hospital Management System
 * 
 * @author Nicholas Ong
 * @author Sheng-Jie 
 */
public class UI {

	public static Statement myStmt;

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
			myStmt = myConn.createStatement();

			Scanner scan = new Scanner(System.in);
			do {
				System.out.println();

				System.out.println("Enter the operation number");
				System.out.println("1. Login as Admin"); //Can access all employee's function
				System.out.println("2. Login as HR"); //add new employee, delete employee, edit, pay, edit paycheck
				System.out.println("3. Login as Nurse");// view doctor's appointments, view patient profile(name, doctor, prescription...),schedule doctor's appointment, move patient (ward2surgery, emergency2ward, vice versa)
				System.out.println("4. Login as Doctor/Surgeon");// view appointment, edit prescription, view patient profile, edit/remove appointments, check surgery room status (clean?available?)
				System.out.println("5. Login as Lab");//view patient, check blood, edit patient blood type.
				System.out.println("6. Login as Receptionist");// view doctor appointment's, add new patient, add new appointment, delete appointment, charge patient.
				System.out.println("7. Login as Pharmacist");// give medicine, request new order from store manager, view all medicine (in stock or not),
				//new use case
				System.out.println("8. Login as Store manager");// manage inventory, keep track of medicine stock/equipment, order new equipment/medicine

				System.out.println();
				System.out.println("-1: Exit");
				System.out.println("Your option: ");

				option = scan.nextInt();
				


				if (option < -1||option==0 || option > 15) {
					System.out.println("Invalid Input");
					System.out.println();
					System.out.println("Enter the operation number");
					System.out.println("1. Login as Admin");
					System.out.println("2. Add Patient");
					System.out.println("3. Make Appointment");
					System.out.println("4. Get Prescription");
					System.out.println("5. Check Schedule");
					System.out.println("6. Give Prescription");
					System.out.println("7. Give Medicine");
					System.out.println("8. Update Inventory");
					System.out.println("9. Make Surgery Appointment");
					System.out.println("10. Pay an Employee");
					System.out.println("11. BloodTest");
					System.out.println("12. add patient to ward");
					System.out.println("13. add patient to emergency");
					System.out.println("14. Move patient from emergency ward to surgery room");
					System.out.println("15. Move patient from emergency ward to ward");
					System.out.println();
					System.out.println("-1: Exit");
					System.out.println("Your option: ");

					option = scan.nextInt();
				}

				switch (option) {
					/*Add doctor*/

					case 1:
						int adminOption = 0;

							System.out.println("Enter Admin User Name");
							String userName = scan.next();
							System.out.println("Enter Admin password");
							String adminPass = scan.next();
							if(conn.checkLogin(myStmt,"admin" ,userName, adminPass) == false){
								System.out.println(" or Wrong password ");
								return;
							}
							do{
							System.out.println("1. add new doctor");
							System.out.println("-1. return to main menu");
							adminOption=scan.nextInt();

							switch (adminOption){
								case 1:
									System.out.println("Enter the doctor's First name: No space in between");
									String docFirstName = scan.next();
									System.out.println("Enter the doctor's Last Name");
									String docLastName = scan.next();
									String docName = docFirstName + " " + docLastName;
									System.out.println("Enter the doctor's ID: ");
									String docID = scan.next();
									Doctor doc = new Doctor(docID, docName);
									conn.createDoctor(myStmt, doc);
									System.out.println();
									break;
							}
							Thread.sleep(500);
						}
						while (adminOption!= -1);


						break;


					/*Login as HR*/
					case 2:
						EmployeeUI.hrMenu(myStmt);
						break;
					/*Make Appointment*/
					case 3:
						System.out.println("Enter the appointment ID: ");
						int appId = scan.nextInt();
						System.out.println("Enter the patient's First name: No space in between");
						String patientFirstName_case3 = scan.next();
						System.out.println("Enter the patient's Last Name: No space in between");
						String patientLastName_case3 = scan.next();
						String patientName_case3 = patientFirstName_case3 + " " + patientLastName_case3;
						/*check if patient exist or not, require to create new patient before proceeding to make appointment*/
						if (!conn.isPatientExist(myStmt, patientName_case3)) {
							System.out.printf("Patient Name: %s does not exist. Please create a new patient before making appointment or use a existing patient ", patientName_case3);
							break;
						}

						System.out.println("Enter the date of appointment (yyyymmdd): ");
						int date = scan.nextInt();

						System.out.println("Enter the time (24-hr format hhmm): ");
						int time = scan.nextInt();
						time *= 100;

						/*check if the date and time are valid or not*/
						//					if(day<1||day>31 ||month>12||month<1 || hours < 0 || hours > 24 || minutes > 60 || minutes < 0) {
						//						System.out.println("Invalid Date and time, Please use 24 hours format. E.g Hours: 24 Minutes: 30. Please make sure the date and time is valid. ");
						//						break;
						//					}

						System.out.println("Enter the First name of the doctor: No space in between");
						String doctorFirstName_case3 = scan.next();
						System.out.println("Enter the last name of the doctor: No space in between");
						String doctorLastName_case3 = scan.next();
						String doctorName_case3 = doctorFirstName_case3 + " " + doctorLastName_case3;
						/*check  if doctor exist in the table*/
						if (!conn.isStringEntityExist(myStmt, "doctors", "doctorName", doctorName_case3)) {
							System.out.printf("Doctor Name: %s does not exist. Please create a new patient before making appointment or use a existing doctor ", doctorName_case3);
							break;
						}
						/*prescription not given yet so initialize it as null*/
						String prescrip = "";
						//

						Appointment app = new Appointment(appId, patientName_case3, doctorName_case3, date, time, prescrip);
						conn.createAppointment(myStmt, app);

						break;
					/*Get Prescription*/
					case 4:
						System.out.println("Enter the patient's First name: No space in between");
						String patientFirstName_case4 = scan.next();
						System.out.println("Enter the patient's Last Name: No space in between");
						String patientLastName_case4 = scan.next();
						String patientName_case4 = patientFirstName_case4 + " " + patientLastName_case4;
						conn.getPatientPrescrip(myStmt, patientName_case4);
						break;
					/*Check Schedule*/
					case 5:
						System.out.println("Enter the First name of the doctor: No space in between");
						String doctorFirstName_case5 = scan.next();
						System.out.println("Enter the last name of the doctor: No space in between");
						String doctorLastName_case5 = scan.next();
						String doctorName_case5 = doctorFirstName_case5 + " " + doctorLastName_case5;

						conn.getDoctorAppointment(myStmt, doctorName_case5);


						break;
					/*Give Prescription*/
					case 6:
						System.out.println("Enter the appointment's ID: ");
						int appID = scan.nextInt();
						System.out.println("Enter the prescription: ");
						String prescription = scan.next();
						conn.updatePrescription(myStmt, appID, prescription);

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
						conn.updateInventory(myStmt, medId, newAmount);
						break;
					/*Exit program*/
					case 9:
						System.out.println("Enter the surgery appointment ID: ");
						int saID = scan.nextInt();
						System.out.println("Enter the patient's First name: No space in between");
						String patientFN = scan.next();
						System.out.println("Enter the patient's Last Name: No space in between");
						String patientLN = scan.next();
						String patientN = patientFN + " " + patientLN;
						System.out.println("Enter the patient's ID: ");
						String pID = scan.next();
						/*check if patient exist or not, require to create new patient before proceeding to make appointment*/
						if (!HospitalController.isPatientExist(myStmt, patientN)){
							System.out.printf("Patient Name: %s does not exist. Please create a new patient before making appointment or use a existing patient ", patientN);
							break;
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
						/*check  if doctor exist in the table*/
						if(!HospitalController.isStringEntityExist(myStmt,"nurses","nurseName", nurseN)){
							System.out.printf("Nurse Name: %s does not exist. Please create a new nurse before making a surgery appointment or use a existing nurse ", nurseN);
							break;
						}

						Patient p = new Patient(pID, patientN);
						Surgeon s = new Surgeon(surgeonN, 0);
						Nurse n = new Nurse(nurseN, 0);
						p.reqSurgeryAppointment(saID, sraDate, sraHour, sraMin, s, n, roomID);
						break;
					case 10:
						System.out.println("Enter the First name of the employee: No space in between");
						String employeeFN = scan.next();
						System.out.println("Enter the last name of the employee: No space in between");
						String employeeLN = scan.next();
						String employeeN = employeeFN + " " + employeeLN;
						System.out.println("Enter the employee's amount of hours worked: ");
						int hoursWorked = scan.nextInt();
						System.out.println("Enter the employee's pay rate per hour: ");
						double payRate = scan.nextDouble();
						HumanResources hr = new HumanResources();
						boolean paid = false;

						//When the employee is a nurse
						if(HospitalController.isStringEntityExist(myStmt,"nurses","nurseName", employeeN)){
							Nurse name = new Nurse(employeeN, 0);
							paid = hr.payEmployee(name, hoursWorked, payRate);
							if(paid)
								System.out.printf("Employee Name: %s has been paid with the amount %.2f. \n", employeeN, (double)payRate * hoursWorked);
							else
								System.out.printf("Employee Name: %s has yet to be paid with the amount %.2f. \n", employeeN, payRate * hoursWorked);
							break;
						}

						//When the employee is a doctor
						if(HospitalController.isStringEntityExist(myStmt,"doctors","doctorName", employeeN)){
							Doctor name = new Doctor(employeeN, 0);
							paid = hr.payEmployee(name, hoursWorked, payRate);
							if(paid)
								System.out.printf("Employee Name: %s has been paid with the amount %.2f. \n", employeeN, payRate * hoursWorked);
							else
								System.out.printf("Employee Name: %s has yet to be paid with the amount %.2f. \n", employeeN, payRate * hoursWorked);
							break;
						}

						//When the employee is a surgeon
						if(HospitalController.isStringEntityExist(myStmt,"surgeons","surgeonName", employeeN)){
							Surgeon name = new Surgeon(employeeN, 0);
							paid = hr.payEmployee(name, hoursWorked, payRate);
							if(paid)
								System.out.printf("Employee Name: %s has been paid with the amount %.2f. \n", employeeN, payRate * hoursWorked);
							else
								System.out.printf("Employee Name: %s has yet to be paid with the amount %.2f. \n", employeeN, payRate * hoursWorked);
							break;
						}

						System.out.printf("Employee: %s does not exist.", employeeN);
						break;
					case 11:
						System.out.println("Enter the id of patient who got the blood Test");
						int pat_id = scan.nextInt();
						String table_name = "patients";
						conn.BloodTest(myStmt, table_name, pat_id);
						break;
					case 12:
						System.out.println("Enter the appointment ID: ");
						int appId12 = scan.nextInt();
						if(!conn.isStringEntityExist(myStmt,"appointments","appointmentId",Integer.toString(appId12))){
							System.out.printf("appointment id %d does not exist %n",appId12);
							break;
						}
						String patientName_case12 = conn.getStringEntity(myStmt,"appointments","patientName","appointmentId",Integer.toString(appId12));
						String patientId_case12 = conn.getStringEntity(myStmt,"patients","patientId","patientName",patientName_case12);
						String docName = conn.getStringEntity(myStmt,"appointments","doctorName","appointmentId",Integer.toString(appId12));
						String docId =conn.getStringEntity(myStmt,"doctors","doctorId","doctorName",docName); ;



						System.out.println("Enter ward id");
						int wardID12 = scan.nextInt();


						conn.addPatientToWard(myStmt, Integer.parseInt(patientId_case12),Integer.parseInt(docId),wardID12);
						break;
					case 13:
						System.out.println("Enter the patient's First name: No space in between");
						String patientFirstName_case13 = scan.next();
						System.out.println("Enter the patient's Last Name: No space in between");
						String patientLastName_case13 = scan.next();
						String patientName_case13 = patientFirstName_case13 + " " + patientLastName_case13;

						System.out.println("Enter the doctor's First name: No space in between");
						String docFirstName = scan.next();
						System.out.println("Enter the doctor's Last Name");
						String docLastName = scan.next();
						String docName13 = docFirstName + " " + docLastName;


						System.out.println("enter Emergency ward id");
						int wardId = scan.nextInt();

						conn.addPatientToEmerg(myStmt,patientName_case13,docName13,wardId);
						break;
					case 14:
						System.out.println("Enter the Emergency ID: ");
						int emergId14 = scan.nextInt();
						System.out.println("Enter the Surgery appointment ID: ");
						int appId14 = scan.nextInt();
						System.out.println("Enter the Surgery room ID: ");
						int surgRoomId14 = scan.nextInt();
						String patientName_case14 = conn.getStringEntity(myStmt,"emergency_ward","patientName","emergId",Integer.toString(emergId14));
						String patientId_case14 = conn.getStringEntity(myStmt,"patients","patientId","patientName",patientName_case14);
						Patient p14 = new Patient(patientId_case14,patientName_case14);
						/*check if patient exist or not, require to create new patient before proceeding to make appointment*/
						if (!conn.isPatientExist(myStmt, patientName_case14)) {
							System.out.printf("Patient Name: %s does not exist. Please create a new patient before making appointment or use a existing patient ", patientName_case14);
							break;
						}

						System.out.println("Enter the date of appointment (yyyymmdd): ");
						int date14 = scan.nextInt();

						System.out.println("Enter the time (24-hr format hhmm): ");
						int time14 = scan.nextInt();
						time14 *= 100;
						System.out.println("Enter the First name of the surgeon: No space in between");
						String doctorFirstName_case14 = scan.next();
						System.out.println("Enter the last name of the surgeon: No space in between");
						String doctorLastName_case14 = scan.next();
						String doctorName_case14 = doctorFirstName_case14 + " " + doctorLastName_case14;
						/*check  if doctor exist in the table*/
						if (!conn.isStringEntityExist(myStmt, "surgeons", "surgeonName", doctorName_case14)) {
							System.out.printf("Surgeon Name: %s does not exist. Please create a new patient before making appointment or use a existing surgeon ", doctorName_case14);
							break;
						}
						System.out.println("Enter the First name of the nurse: No space in between");
						String nurseFN14 = scan.next();
						System.out.println("Enter the last name of the nurse: No space in between");
						String nurseLN14 = scan.next();
						String nurseN14 = nurseFN14 + " " + nurseLN14;
						if (!conn.isStringEntityExist(myStmt, "nurses", "nurseName", nurseN14)) {
							System.out.printf("Nurse Name: %s does not exist. Please create a new nurse before making appointment or use a existing nurse %n", nurseN14);
							break;
						}
						/*prescription not given yet so initialize it as null*/

						String prescrip14 = "";
						Nurse n14 = new Nurse(nurseN14,0,0);
						Surgeon sur14 = new Surgeon (doctorName_case14,0,0);
						//
						System.out.println("Enter Surgery room id");
						int wardID14 = scan.nextInt();
						time14=time14/100;
						SurgeryRoomAppointment app14 = new SurgeryRoomAppointment(appId14,time14,p14,sur14,n14,surgRoomId14);
						System.out.println();
						conn.movePatientFromEmergToSurg(myStmt,emergId14,app14);
						break;
					case 15:
						System.out.println("Enter the Emergency ID: ");
						int emergId15 = scan.nextInt();
						String patientName_case15 = conn.getStringEntity(myStmt,"emergency_ward","patientName","emergId",Integer.toString(emergId15));
						String patientId_case15 = conn.getStringEntity(myStmt,"patients","patientId","patientName",patientName_case15);

						System.out.println("Enter the doctor's First name: No space in between");
						String docFirstName15 = scan.next();
						System.out.println("Enter the doctor's Last Name");
						String docLastName15 = scan.next();
						String docName15 = docFirstName15 + " " + docLastName15;

						System.out.println("Enter Ward id that patient going move to");
						int wardId15 = scan.nextInt();

						conn.movePatientFromEmergToWard(myStmt,emergId15,wardId15);


				}
				Thread.sleep(2000);
			}
			while (option != -1);
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}


