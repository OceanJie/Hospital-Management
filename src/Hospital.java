
public class Hospital
{

    DataBase db;

    private DataBase getDatabaseSupportInstance()
    {
        if(ds==null)
                ds=new Database();
            return ds;
    }

    boolean givePrescription(int app_id, String pre) throws ObjectNotFoundException
    {
        return getDatabaseSupportInstance().getAppointment(app_id).updatePrescription(pre);

    }



}
