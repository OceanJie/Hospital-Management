
public class Appointment {
  string patient_name;
  string doctor_name;
  string prescription;
  int app_id;

  public Appointment(int id)
  {
    app_id = id;
  }

  public void setprescription(string pre)
  {
    prescription = pre;
  }

  public string getprescription()
  {
    return prescription;
  }

  public void setpatient_name(string name)
  {
    patient_name = name;
  }

  public string getpatient_name()
  {
    return patient_name;
  }


  public string getdoctor_name()
  {
    return doctor_name;
  }

  public void setdoctor_name(string doctor)
  {
    doctor_name = name;
  }

}
