package main.java;

import java.util.LinkedList;

/*
*Hospital class
*
* @author Bojun Lin
*/

public class Hospital
{
	private DataBase db;

	private DataBase getDatabaseSupportInstance()
	{
		if(db==null)
			db=new DataBase();
		return db;
	}

	public boolean addPatient(String patient_id, String patient_name)
	{
		Patient patient = null;
		try{
			patient = getDatabaseSupportInstance().getPatient(patient_id);
		}
		catch (Exception e){
			System.out.println("patient id already exist");
			return false;
		}
		patient = new Patient (patient_id, patient_name);
		db.addPatient(patient);
		return true;
	}
	public boolean addDoctor(String doctor_id, String doctor_name)
	{
		Doctor doctor = null;
		try{
			doctor = getDatabaseSupportInstance().getDoctor(doctor_id);
		}
		catch (Exception e){
			System.out.println("doctor id already exist");
			return false;
		}
		doctor = new Doctor(doctor_id,doctor_name);
		db.addDoctor(doctor);
		return true;
	}

	public boolean givePrescription(int app_id, String pre)
	{
		Appointment app = null;
		try{
			app = getDatabaseSupportInstance().getAppointment(app_id);
		}
		catch(Exception e)
		{
			return false;
		}
		app.setprescription(pre);
		return true;
	}

	public LinkedList<Appointment> checkSchedule(String doctor_name)
	{
		LinkedList<Appointment> schedule = null;
		try{
			schedule = getDatabaseSupportInstance().checkSchedule(doctor_name);
		}
		catch(Exception e)
		{
			return null;
		}
		return schedule;
	}

	public boolean makeAppointment(String name, String day, int hours, int minutes, String doctorName) {
		Patient p = getDatabaseSupportInstance().getPatient(name);
		Appointment ap = p.makeAppointment(day, hours, minutes, doctorName);
		return getDatabaseSupportInstance().addAppointment(ap);
	}

	public String getPrescription(String patient_name) {
		String prescription = "";
		try {
			prescription = getDatabaseSupportInstance().getPrescription(patient_name);
		}
		catch(Exception e) {
			return null;
		}
		return prescription;
	}
}
