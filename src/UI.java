import java.util.LinkedList;
import java.util.Scanner;

/**
 * Main UI for Hospital Management System
 * 
 * @author Nicholas Ong
 */
public class UI {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the operation number");
		System.out.println("1. Add Doctor");
		System.out.println("2. Add Patient");
		System.out.println("3. Make Appointment");
		System.out.println("4. Get Prescription");
		System.out.println("5. Check Schedule");
		System.out.println("6. Give Prescription");
		System.out.println("7. Give Medicine");
		System.out.println("8. Update Inventory");
		System.out.println("Your option: ");
		
		int option = scan.nextInt();
		
		while(option < 1 || option > 8) {
			System.out.println("\nInvalid option!");
			System.out.println("Enter the operation number");
			System.out.println("1. Add Doctor");
			System.out.println("2. Add Patient");
			System.out.println("3. Make Appointment");
			System.out.println("4. Get Prescription");
			System.out.println("5. Check Schedule");
			System.out.println("6. Give Prescription");
			System.out.println("7. Give Medicine");
			System.out.println("8. Update Inventory");
			System.out.println("Your option: ");
			
			option = scan.nextInt();
		}
		
		switch(option) {
		case 1: 
			System.out.println("Enter the doctor's name: ");
			String docName = scan.next();
			System.out.println("Enter the doctor's ID: ");
			String docID = scan.next();
			boolean op = new HospitalController().addDoctor(docID, docName);
			System.out.println("Operation success boolean is " + op);
			break;
		case 2: 
			System.out.println("Enter the patient's name: ");
			String patientName = scan.next();
			System.out.println("Enter the patient's ID: ");
			String patientID = scan.next();
			boolean op2 = new HospitalController().addDoctor(patientID, patientName);
			System.out.println("Operation success boolean is " + op2);
			break;
		case 3: 
			System.out.println("Enter the patient's name: ");
			String patientName3 = scan.next();
			System.out.println("Enter the day of appointment (Ex: Wednesday): ");
			String day = scan.next();
			System.out.println("Enter the hour (24-hr format): ");
			int hours = scan.nextInt();
			System.out.println("Enter the minute: ");
			int minutes = scan.nextInt();
			System.out.println("Enter the name of the doctor: ");
			String doctor_name = scan.next();
			boolean op3 = new HospitalController().makeAppointment(patientName3, day, hours, minutes, doctor_name);
			System.out.println("Operation success boolean is " + op3);
			break;
		case 4: 
			System.out.println("Enter the patient's name: ");
			String patientName4 = scan.next();
			String op4 = new HospitalController().getPrescription(patientName4);
			System.out.println("The prescription would be: " + op4);
			break;
		case 5: 
			System.out.println("Enter the doctor's name: ");
			String docName5 = scan.next();
			LinkedList<Appointment> op5 = new HospitalController().checkSchedule(docName5);
			System.out.println("Doctor " + docName5 + " current list of schedule would be: ");
			for(int i=0;i<op5.size();i++)
				System.out.println(op5.get(i) + " ");
			break;
		case 6: 
			System.out.println("Enter the appointment's ID: ");
			int appID = scan.nextInt();
			System.out.println("Enter the prescription: ");
			String prescription = scan.next();
			boolean op6 = new HospitalController().givePrescription(appID, prescription);
			System.out.println("Operation success boolean is " + op6);
			break;
		case 7: 
			System.out.println("7");
			break;
		case 8: 
			System.out.println("8");
			break;
		}		
		scan.close();
	}
}

