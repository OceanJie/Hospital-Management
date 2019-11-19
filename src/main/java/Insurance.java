package main.java;

public class Insurance {

	private Patient patient;
	private String[] insuranceNames = {"SafetyFirst", "Veteran", "Senior Citizen"};
	
	public Insurance(Patient patient) {
		this.patient = patient;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public boolean getInsurance(String insuranceName, double amount) {
		
		for(int i=0;i<insuranceNames.length;i++) 
			if(insuranceName.equals(insuranceNames[i]))
				if(amount > 0)
					return true;
		
		return false;
	}

}
