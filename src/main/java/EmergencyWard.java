package main.java;

public class EmergencyWard {
    String pName;
    String dName;
    int emergId;
    String presrip;
    public EmergencyWard(int emergId,String pName, String dName, String prescrip){
        this.pName = pName;
        this.dName = dName;
        this.emergId = emergId;
        this.presrip = prescrip;
    }
    public String getpName(){
        return pName;
    }
    public String getdName(){
        return dName;
    }

    public int getEmergId() {
        return emergId;
    }
    public String getPresrip(){
        return presrip;
    }
}
