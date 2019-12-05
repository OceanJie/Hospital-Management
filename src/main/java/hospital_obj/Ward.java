package main.java.hospital_obj;

public class Ward {

	private String pName;
	private String dName;
	private int wardId;
	private String presrip;

	public Ward(int wardId, String pName, String dName, String prescrip){
		this.pName = pName;
		this.dName = dName;
		this.wardId = wardId;
		this.presrip = prescrip;
	}

	public String getpName() {
		return pName;
	}

	public String getdName() {
		return dName;
	}

	public int getWardId() {
		return wardId;
	}

	public String getPresrip(){
		return presrip;
	}
}
