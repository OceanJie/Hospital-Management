import java.util.LinkedList;

public class DataBase {

	private Hashmap<String, Patient> patients;
	private Hashmap<String, Doctor> doctors;
	private Hashmap<Integer, Appointment> appointments;

		public DataBase()
		{
			patients = new Hashmap<String, Patient>;
			doctors = new Hashmap<String, Doctor>;
			appointments = new Hashtmap<Integer, Appointment>;
		}

		public void addPatient(Patient p)
		{
			patients.put(p.getName(), p);
		}

    public Patient getPatient(string patient_name)
		{
			return patients.get(patient_name);
		}

		public void addDoctor(Doctor d)
		{
			Doctor.put(d.getName(), d);
		}

		public Doctor getDoctor(string dcotor_name)
		{
			return doctors.get(doctor_name);
		}

		public void addAppointment(Appointment app)
		{
			appointments.put(app.getID(), app);
		}
		public Appointment getAppointment(int id)
		{
			return appointments.get(new Integer(id));
		}

		LinkedList<Appointment> checkSchedule(string doctor_name);
		{
			LinkedList<Appointment> res = new LinkedList();
			Iterator it = appointments.entrySet().iterator();
			 while (it.hasNext()) {
				 Appointment token = it.next();
				 if(token.getDoctor().equals(doctor_name))
				 res.add(token);
			 }
			 return res;
		}


}
