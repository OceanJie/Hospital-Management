package main.java;

public class Appointment {
  String patient_name;
  String doctor_name;
  String prescription;
  String time;
  String date;
  int app_id;
  /*
  *Appointment class
  *
  * @author Bojun Lin
  */

  public Appointment(int id)
  {
    app_id = id;
  }

  public Appointment(int app_id, String patient_name, String doctor_name, String date, String time, String prescription) {
    this.app_id = app_id;
    this.patient_name = patient_name;
    this.doctor_name = doctor_name;
    this.date = date;
    this.prescription = prescription;
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

  public String getTime(){return time;}

  public String getDate(){return date;}
}
