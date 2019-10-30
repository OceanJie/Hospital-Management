import java.util.LinkedList;

public class HospitalController
{

  private Hospital h;

  public HospitalController()
  {
    h = new Hospital();
  }

  public boolean givePrescription(int app_id, String pre) 
  {
    return h.givePrescription(app_id, pre);
  }

  public LinkedList<Appointment> checkSchedule(String doctor_name)
  {
    return h.checkSchedule(doctor_name);
  }

  public boolean makeAppointment(String name, String day, int hours, int minutes, String doctor_name) {
    return h.makeAppointment(name, day, hours, minutes, doctor_name);
  }

  public String getPrescription(String name) {
    return h.getPrescription(name);
  }
}
