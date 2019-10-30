import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class DataBase {

	private HashMap<String, Patient> patients;
	private HashMap<String, Doctor> doctors;
	private HashMap<Integer, Appointment> appointments;

	public DataBase()
	{
		patients = new HashMap<String, Patient>();
		doctors = new HashMap<String, Doctor>();
		appointments = new HashMap<Integer, Appointment>();
	}

	public void addPatient(Patient p)
	{
		patients.put(p.getName(), p);
	}

	public Patient getPatient(String patient_name)
	{
		return patients.get(patient_name);
	}

	public void addDoctor(Doctor d)
	{
		doctors.put(d.getName(), d);
	}

	public Doctor getDoctor(String dcotor_name)
	{
		return doctors.get(dcotor_name);
	}

	public boolean addAppointment(Appointment app)
	{
		appointments.put(app.getID(), app);
		return true;
	}
	public Appointment getAppointment(int id)
	{
		return appointments.get(id);
	}

	public String getPrescription(String patient_name) {
		return getPatient(patient_name).getPrescription();
	}
	
	LinkedList<Appointment> checkSchedule(String doctor_name)
	{
		LinkedList<Appointment> res = new LinkedList<>();
		Iterator it = appointments.entrySet().iterator();
		while (it.hasNext()) {
			Appointment token = it.next();
			if(token.getDoctor().equals(doctor_name))
				res.add(token);
		}
		return res;
	}
}
