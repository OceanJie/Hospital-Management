package iteration.one;

/**
 * Patient class
 * 
 * @author Nicholas Ong
 */
public class Patient {
	
	private String name;
	
	public Patient(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Adding this patient's appointment time to the list
	 * 
	 * @param day - Name of the days (Ex: Monday)
	 * @param hours - The 
	 * @param minutes
	 * @param isAM
	 * @return
	 */
	public boolean makeAppointment(String day, int hours, int minutes, boolean isAM) {
		
		return false;
	}
	
	public String getPrescription() {
		return null;
	}
}
