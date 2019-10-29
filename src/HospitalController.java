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
    return checkSchedule(doctor_name);
  }
}
