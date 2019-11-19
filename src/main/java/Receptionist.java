package main.java;

public class Receptionist {


	public boolean setSurgeryAppointment(Patient patient, Surgeon surgeon, int ID, int startTime, Nurse nurse, int roomID) {

		if(patient == null || surgeon == null || nurse == null || ID < 0 || startTime < 0 || roomID < 0)
			return false;


		SurgeryRoomAppointment sra = new SurgeryRoomAppointment(ID, startTime, patient, surgeon, nurse, roomID);
		HospitalController.createSurgeryAppointment(UI.myStmt, sra);
		return true;
	}
}
