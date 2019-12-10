package main.java.command.ReceptionistCmd;

import main.java.command.DoctorCmd.DoctorFunction;
import main.java.command.iCommand;
import main.java.staff.Receptionist;

public class ViewPatientProfileCmd implements iCommand {
    ReceptionistFunction recepCmd = new ReceptionistFunction();//share the same function as doctor
    @Override
    public String getDesc() {
        return "View Patient Profile";
    }

    @Override
    public void execute() {
        recepCmd.viewPatientProfile();
    }
}
