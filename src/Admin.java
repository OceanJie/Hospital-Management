package iteration.one;

import java.util.ArrayList;
import java.util.List;

/**
 * Admin class
 * @author Sheng Jie Ooi
 */
public class Admin {
	
	private List<Patient> listofPatient;
	private List<Doctor> listofDoctor;
	
	public Admin() {
		listofPatient = new ArrayList<>();
		listofDoctor = new ArrayList<>();
	}

	/**
	 * return true when successfully added a new doctor obj into the ArrayList
	 * return false when doctor already exist
	 * @param doctor_name
	 * @return boolean
	 */
	public boolean addDoctor(String doctor_name) {
		Doctor doc = new Doctor (doctor_name);
		if(listofDoctor.indexof(doc)== -1) {
			System.out.println(doctor_name+" already exist")
			return false;
		}
		else{
			listofDoctor.add(doc);
		}
		return true;
	}

	/**
	 * return true when successfully added a new patient obj into the ArrayList
	 * return false when patient already exist
	 * @param patient_name
	 * @return boolean
	 */
	public boolean addPatient(String patient_name) {
		Patient p = new Patient (patient_name);
		
		if(listofPatient.indexof(p)== -1) {
			System.out.println(patient_name+" already exist")
			return false;
		}
		else{
			listofPatient.add(p);
		}
		return true;
	}
}
