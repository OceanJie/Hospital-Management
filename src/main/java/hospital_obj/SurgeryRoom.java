package main.java.hospital_obj;

import main.java.Patient;
import main.java.hospital_obj.Inventory;
import main.java.staff.Nurse;
import main.java.staff.Surgeon;

/**
 * Surgery Room
 *
 * @author Nicholas Ong
 * @version 1.0
 */
public class SurgeryRoom {

	private int ID;
	private int startTime;
	private Patient patient;
	private Surgeon surgeon;
	private Nurse nurse;

	/**
	 * Constructor
	 *
	 * @param ID - Room ID
	 * @param startTime - The starting time (24 hrs format) of the room's usage
	 * @param nurse
	 * @param patient - The patient that will undergo surgery
	 * @param surgeon - The surgeon that will perform surgery
	 */
	public SurgeryRoom(int ID, int startTime, Patient patient, Surgeon surgeon, Nurse nurse) {
		this.ID = ID;
		this.startTime = startTime;
		this.patient = patient;
		this.surgeon = surgeon;
		this.nurse = nurse;
	}

	public int getID() {
		return ID;
	}

	public int getStartTime() {
		return startTime;
	}


	public Patient getPatient() {
		return patient;
	}

	public Surgeon getSurgeon() {
		return surgeon;
	}

	public Nurse getNurse() {
		return nurse;
	}

	/**
	 * For now, retrieve a constant amount of tools from the inventory
	 *
	 * @return True if the amount of tools is enough, false otherwise
	 */
	public boolean retrieveTools() {
		Inventory inventory = new Inventory();
		//TODO waiting for inventory's class to have tools, for now just return true
		return true;
	}
}
