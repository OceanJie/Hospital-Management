package main.java;

import main.java.hospital_obj.SurgeryRoom;
import main.java.staff.Nurse;
import main.java.staff.Surgeon;

/**
 * Appointment class specifically for surgery rooms
 *
 * @author Nicholas Ong
 * @version 1.0
 */
public class SurgeryRoomAppointment {

	private SurgeryRoom room;
	private Surgeon surgeon;
	private Patient patient;
	private Nurse nurse;
	private int ID;

	public SurgeryRoomAppointment(int ID, int startTime, Patient patient, Surgeon surgeon, Nurse nurse, int roomID) {
		this.surgeon = surgeon;
		this.patient = patient;
		this.nurse = nurse;
		this.ID = ID;
		room = new SurgeryRoom(roomID, startTime, patient, surgeon, nurse);
	}

	public int getID() {
		return ID;
	}

	public Patient getPatient() {
		return patient;
	}

	public SurgeryRoom getSurgeryRoom() {
		return room;
	}

	public Surgeon getSurgeon() {
		return surgeon;
	}

	public Nurse getNurse() {
		return nurse;
	}

	public int getRoomID() {
		return room.getID();
	}
}
