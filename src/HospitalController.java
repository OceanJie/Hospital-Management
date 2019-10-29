public class HospitalController
{

  private Hospital h;

  public HospitalController()
  {
    h = new Hospital();
  }

  public boolean givePrescription(int app_id, String pre) throws ObjectNotFoundException
  {
    return h.givePrescription(app_id, pre);
  }

  public LinkList<Appointment> checkSchedule(string doctor_name) throws ObjectNotFoundException
  {
    return h.checkSchedule(doctor_name);
  }

  public boolean makeAppointment(String day, int hours, int minutes) throws ObjectNotFoundException {
    return h.makeAppointment(day, hours, minutes);
  }

  public String getPrescription() throws ObjectNotFoundException {
    return h.getPrescription();
  }
}
