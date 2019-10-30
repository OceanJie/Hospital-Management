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

    public boolean givePrescription(int app_id, String pre) throws ObjectNotFoundException
    {
        return getDatabaseSupportInstance().getAppointment(app_id).setPrescription(pre);
    }

    public LinkedList<Appointment> checkSchedule(String doctor_name) throws ObjectNotFoundException
    {
      return getDatabaseSupportInstance().checkSchedule(doctor_name);
    }

    public boolean makeAppointment(String name, String day, int hours, int minutes, String doctorName) throws ObjectNotFoundException {
      Appointment ap = getDatabaseSupportInstance().getPatient(name).makeAppointment(day, hours, minutes, doctorName);
      return getDatabaseSupportInstance().addAppointment(ap);
    }

    public String getPrescription() throws ObjectNotFoundException {
      return getDatabaseSupportInstance().getPrescription();
    }
}
