
public class Hospital
{

    private DataBase db;

    private DataBase getDatabaseSupportInstance()
    {
        if(ds==null)
                ds=new Database();
            return ds;
    }


    public boolean givePrescription(int app_id, String pre) throws ObjectNotFoundException
    {
        return getDatabaseSupportInstance().getAppointment(app_id).setPrescription(pre);

    }

    public LinkList<Appointment> checkSchedule(string doctor_name) throws ObjectNotFoundException
    {
      return getDatabaseSupportInstance().checkSchedule(doctor_name);

    }




}
