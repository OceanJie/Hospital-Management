
public class Doctor {

	private String doctor_name;
	private String id;

	public Doctor(String name)
	{
		doctor_name = name;
	}
	
	public Doctor(String id, String name) {
		this.id = id;
		doctor_name = name;
	}
	
	public void setName(String name)
	{
		doctor_name = name;
	}
	public String getName()
	{
		return doctor_name;
	}

}
