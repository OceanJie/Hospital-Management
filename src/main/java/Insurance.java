package main.java;

public class Insurance {

	private Patient patient;
	private String[] insuranceNames = {"SafetyFirst", "Veteran", "Senior Citizen"};
	private int [] insuranceAmount = {10,15,10};
	public Insurance(Patient patient) {
		this.patient = patient;
	}
	
	public Patient getPatient() {
		return patient;
	}

	public int getInsuranceAmount(String insuranceName){
		int amount = 0;
		if(insuranceName=="null"){
			return amount;
		}
		else{
			for(int i=0;i<insuranceNames.length;i++)
				if(insuranceName.equals(insuranceNames[i])){
					amount = insuranceAmount[i];
					break;
				}

		}
		return amount;
	}

	public boolean getInsurance(String insuranceName, double amount) {
		
		for(int i=0;i<insuranceNames.length;i++) 
			if(insuranceName.equals(insuranceNames[i]))
				if(amount > 0)
					return true;
		
		return false;
	}

}
