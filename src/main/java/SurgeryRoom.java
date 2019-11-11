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
	
	/**
	 * Constructor 
	 * 
	 * @param ID - Room ID
	 * @param startTime - The starting time (24 hrs format) of the room's usage
	 * @param hoursOfUse - The number of hours that this room will be occupied
	 * @param patient - The patient that will undergo surgery
	 * @param surgeon - The surgeon that will perform surgery
	 */
	public SurgeryRoom(int ID, int startTime, int hoursOfUse, Patient patient, Surgeon surgeon) {
		this.ID = ID;
		this.startTime = startTime;
		this.hoursOfUse = hoursOfUse;
		this.patient = patient;
		this.surgeon = surgeon;
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
	
	/**
	 * Check if this room is available 
	 * 
	 * @return
	 */
	public boolean isAvailable() {
		return false;
	}
	
	/**
	 * Set that this room will be in use for some designated time
	 * 
	 * @return True if the room is available at this time, false otherwise
	 */
	public boolean setRoomUse() {
		if(isAvailable())
			return false;
		else {
			//TODO
			return true;
		}
	}
}
