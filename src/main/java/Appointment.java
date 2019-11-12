package main.java;

public class Appointment {
	String patient_name;
	String doctor_name;
	String prescription;
	int hour;
	int min;
	int day;
	int month;
	int app_id;
	/*
	 *Appointment class
	 *
	 * @author Bojun Lin
	 */

	public Appointment(int id)
	{
		app_id = id;
	}

	public Appointment(int app_id, String patient_name, String doctor_name, int month, int day, int hour,int min, String prescription) {
		this.app_id = app_id;
		this.patient_name = patient_name;
		this.doctor_name = doctor_name;
		this.hour = hour;
		this.min = min;
		this.day = day;
		this.month = month;
		this.prescription = prescription;
	}

	public void setprescription(String pre)
	{
		prescription = pre;
	}

	public String getprescription()
	{
		return prescription;
	}

	public void setpatient_name(String name)
	{
		patient_name = name;
	}

	public String getpatient_name()
	{
		return patient_name;
	}

	public String getdoctor_name()
	{
		return doctor_name;
	}

	public void setdoctor_name(String doctor)
	{
		doctor_name = doctor;
	}

	public int getID()
	{
		return app_id;
	}

	public int getHour(){return hour;}

	public int getMin(){return min;}

	public int getMonth() {
		return month;
	}

	public int getDay(){return day;}
}
