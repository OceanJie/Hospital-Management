package main.java;

import java.util.Random;

/**
 * Patient class
 *
 * @author Nicholas Ong
 */
public class Patient {

	private String name;
	private String id;
	private Appointment ap;
	private String sickness;

	public Patient(String id, String name) {
		this.id = id;
		this.name = name;
		sickness = "NONE";
	}
	
	public Patient(String name) {
		this("0", name);
	}

	/**
	 * Get this patient's name
	 *
	 * @return The name of this patient
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the sickness of this patient
	 * 
	 * @return The sickness of this patient
	 */
	public String getSickness() {
		return sickness;
	}
	
	/**
	 * Sets the sickness of this patient
	 * @param sickness Patient's sickness
	 */
	public void setSickness(String sickness) {
		this.sickness = sickness;
	}
	
	/**
	 * Gets the ID of this patient
	 * 
	 * @return ID of this patient
	 */
	public String getID() {
		return id;
	}

	/**
	 * Adding this patient's appointment time to the list
	 *
	 * Pre-condition: The time is available
	 *
	 * @param day - Name of the days (Ex: Monday)
	 * @param hours - The hour of the appointment time in 24-hr format
	 * @param minutes - The minute of the appointment time
	 * @return True if making an appointment is successful, false otherwise
	 */
	public Appointment makeAppointment(String day, int hours, int minutes, String doctorName) {
		if(day == null || hours < 0 || hours > 24 || minutes > 60 || minutes < 0)
			return null;

		if(day.equalsIgnoreCase("Monday") || day.equalsIgnoreCase("Tuesday") || day.equalsIgnoreCase("Wednesday") || day.equalsIgnoreCase("Thursday") ||
				day.equalsIgnoreCase("Friday") || day.equalsIgnoreCase("Saturday") || day.equalsIgnoreCase("Sunday")) {
			Random rand = new Random();
			int id = hours * minutes + rand.nextInt(hours + minutes);
			ap = new Appointment(id);
			ap.setpatient_name(getName());
			ap.setdoctor_name(doctorName);
			return ap;
		} else {
			return null;
		}
	}

	/**
	 * Adding this patient's appointment time to the list
	 *
	 * @return The prescription for this patient
	 */
	public String getPrescription() {
		return ap.getprescription();
	}
}
