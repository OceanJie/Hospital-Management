import java.util.LinkedList;

public class Hospital
{
	private DataBase db;

	private DataBase getDatabaseSupportInstance()
	{
		if(db==null)
			db=new DataBase();
		return db;
	}

	public boolean givePrescription(int app_id, String pre)
	{
		Appointment app = null;
		try{
			app = getDatabaseSupportInstance().getAppointment(app_id);
		}
		catch(Exception e)
		{
			return false;
		}
		app.setprescription(pre);
		return true;
	}

	public LinkedList<Appointment> checkSchedule(String doctor_name)
	{
		LinkedList<Appointment> schedule = null;
		try{
			schedule = getDatabaseSupportInstance().checkSchedule(doctor_name);
		}
		catch(Exception e)
		{
			return null;
		}
		return schedule;
	}

	public boolean makeAppointment(String name, String day, int hours, int minutes, String doctorName) {
		Appointment ap = getDatabaseSupportInstance().getPatient(name).makeAppointment(day, hours, minutes, doctorName);
		return getDatabaseSupportInstance().addAppointment(ap);
	}

	public String getPrescription(String patient_name) {
		String prescription = "";
		try {
			prescription = getDatabaseSupportInstance().getPrescription(patient_name);
		}
		catch(Exception e) {
			return null;
		}
		return prescription;
	}
}
