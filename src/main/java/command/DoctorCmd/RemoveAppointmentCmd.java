package main.java.command.DoctorCmd;

import main.java.command.iCommand;

public class RemoveAppointmentCmd implements iCommand {
    DoctorFunction docCmd = new DoctorFunction();

    @Override
    public String getDesc() {
        return "Remove existing appointments";
    }

    @Override
    public void execute() {
    docCmd.removeExistingApp();
    }
}
