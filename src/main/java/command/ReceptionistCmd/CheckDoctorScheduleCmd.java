package main.java.command.ReceptionistCmd;

import main.java.command.iCommand;

public class CheckDoctorScheduleCmd implements iCommand {
    ReceptionistFunction recepCmd = new ReceptionistFunction();
    @Override
    public String getDesc() {
        return "Get Doctor's schedule";
    }

    @Override
    public void execute() {
        recepCmd.viewDoctorAppointment();
    }
}
