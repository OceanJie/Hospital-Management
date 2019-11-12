package main.java;

/**
 * Surgery Room
 * 
 * @author Nicholas Ong
 * @version 1.0
 */
public class SurgeryRoom {
	
	private int ID;
	private int startTime;
	private int hoursOfUse;
	private Patient patient;
	private Surgeon surgeon;
	private Nurse[] nurses;
	
	/**
	 * Constructor 
	 * 
	 * @param ID - Room ID
	 * @param startTime - The starting time (24 hrs format) of the room's usage
	 * @param hoursOfUse - The number of hours that this room will be occupied
	 * @param patient - The patient that will undergo surgery
	 * @param surgeon - The surgeon that will perform surgery
	 */
	public SurgeryRoom(int ID, int startTime, int hoursOfUse, Patient patient, Surgeon surgeon, Nurse[] nurses) {
		this.ID = ID;
		this.startTime = startTime;
		this.hoursOfUse = hoursOfUse;
		this.patient = patient;
		this.surgeon = surgeon;
		this.nurses = nurses;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public int getHoursOfUse() {
		return hoursOfUse;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public Surgeon getSurgeon() {
		return surgeon;
	}
	
	public Nurse[] getNurses() {
		return nurses;
	}
	
	/**
	 * For now, retrieve a constant amount of tools from the inventory
	 * 
	 * @return True if the amount of tools is enough, false otherwise
	 */
	public boolean retrieveTools() {
		Inventory inventory = new Inventory();
		//TODO 
		return false;
	}
}
