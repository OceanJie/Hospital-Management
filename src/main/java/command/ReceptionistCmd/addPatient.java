package main.java.command.ReceptionistCmd;

import main.java.command.iCommand;
import main.java.staff.Receptionist;

public class addPatient implements iCommand {
    ReceptionistFunction recepCmd = new ReceptionistFunction();
    @Override
    public String getDesc() {
        return "Add new Patient";
    }

    @Override
    public void execute() {
        recepCmd.addPatient();
    }
}
