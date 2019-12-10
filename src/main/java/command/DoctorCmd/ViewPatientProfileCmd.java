package main.java.command.DoctorCmd;

import main.java.command.iCommand;

public class ViewPatientProfileCmd implements iCommand {
    DoctorFunction docCmd = new DoctorFunction();
    @Override
    public String getDesc() {
        return "View Patient Profile";
    }

    @Override
    public void execute() {
        docCmd.viewPatientProfile();
    }
}
