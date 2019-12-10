package main.java.command.NurseCmd;

import main.java.command.iCommand;

public class viewPatientProfile implements iCommand {
    NurseFunction nurseFunction = new NurseFunction();

    @Override
    public String getDesc() {
        return "View Patient Profile";
    }

    @Override
    public void execute() {
    nurseFunction.viewPatientProfile();
    }
}
