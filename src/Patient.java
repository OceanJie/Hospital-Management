package iteration.one;

/**
 * Patient class
 *
 * @author Nicholas Ong
 */
public class Patient {

	private String name;
	private Appointment ap;

	public Patient(String name) {
		this.name = name;
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
	 * Adding this patient's appointment time to the list
	 *
	 * Pre-condition: The time is available
	 *
	 * @param day - Name of the days (Ex: Monday)
	 * @param hours - The hour of the appointment time in 24-hr format
	 * @param minutes - The minute of the appointment time
	 * @return True if making an appointment is successful, false otherwise
	 */
	public boolean makeAppointment(String day, int hours, int minutes) {
		if(day == NULL || hours < 0 || hours > 24 || minutes > 60 || minutes < 0)
			return false;

		Random rand = new Random();
		int id = hours * minutes + rand.nextInt(hours + minutes);
		ap = new Appointment(id);
		ap.setpatient_name(getName());
		return true;
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
