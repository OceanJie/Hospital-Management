package main.java;

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
	private Nurse[] nurses;
	
	public SurgeryRoomAppointment(int startTime, int hoursOfUse, Patient patient, Surgeon surgeon, Nurse[] nurses) {
		this.surgeon = surgeon;
		this.patient = patient;
		this.nurses = nurses;
		room = new SurgeryRoom(getARoomID(), startTime, hoursOfUse, patient, surgeon, nurses);
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
	
	public Nurse[] getNurses() {
		return nurses;
	}
	
	private int getARoomID() {
		//TODO retrieve from db maybe instead of a list
		return 0;
	}
}
