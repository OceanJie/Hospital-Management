import java.util.LinkedList;

public class Hospital
{
    private DataBase db;

    private DataBase getDatabaseSupportInstance()
    {
        if(db==null)
                db=new Database();
            return db;
    }

    public boolean givePrescription(int app_id, String pre)
    {
      Appointment app = null;
      try{
        app = getDatabaseSupportInstance().getAppointment(app_id);
      }
      catch(Exeception e)
      {
        return false;
      }
      app.setPrescription(pre);
      return true;
    }

    public LinkList<Appointment> checkSchedule(string doctor_name)
    {
      LinkList<Appointment> schedule = null;
      try{
      schedule = getDatabaseSupportInstance().checkSchedule(doctor_name);
    }
    catch(Exeception e)
    {
      return null;
    }
    return schedule;
  }

    public boolean makeAppointment(String name, String day, int hours, int minutes, String doctorName) {
      Appointment ap = getDatabaseSupportInstance().getPatient(name).makeAppointment(day, hours, minutes, doctorName);
      return getDatabaseSupportInstance().addAppointment(ap);
    }

    public String getPrescription() throws ObjectNotFoundException {
      return getDatabaseSupportInstance().getPrescription();
    }
}
