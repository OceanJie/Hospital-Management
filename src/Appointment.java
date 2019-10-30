public class Appointment {
  String patient_name;
  String doctor_name;
  String prescription;
  int app_id;

  public Appointment(int id)
  {
    app_id = id;
  }

  public void setprescription(String pre)
  {
    prescription = pre;
  }

  public String getprescription()
  {
    return prescription;
  }

  public void setpatient_name(String name)
  {
    patient_name = name;
  }

  public String getpatient_name()
  {
    return patient_name;
  }


  public String getdoctor_name()
  {
    return doctor_name;
  }

  public void setdoctor_name(String doctor)
  {
    doctor_name = doctor;
  }

  public int getID()
  {
    return app_id;
  }
}
