package main.java.staff;

import main.java.*;

public class Receptionist implements Employee{


	public boolean setSurgeryAppointment(Patient patient, Surgeon surgeon, int ID, int startTime, Nurse nurse, int roomID) {

		if(patient == null || surgeon == null || nurse == null || ID < 0 || startTime < 0 || roomID < 0)
			return false;


		SurgeryRoomAppointment sra = new SurgeryRoomAppointment(ID, startTime, patient, surgeon, nurse, roomID);
		HospitalController.createSurgeryAppointment(UI.myStmt, sra);
		return true;
	}

	@Override
	public void setId(int newId) {

	}

	@Override
	public void setName(String newName) {

	}

	@Override
	public boolean setSalary(double newSalary) {
		return false;
	}

	@Override
	public boolean setBonus(double newBonus) {
		return false;
	}

	@Override
	public int getId() {
		return 0;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Salary getSalary() {
		return null;
	}

	@Override
	public double getBonus() {
		return 0;
	}
}
