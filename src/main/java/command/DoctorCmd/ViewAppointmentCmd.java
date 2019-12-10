package main.java.command.DoctorCmd;

import main.java.HospitalController;
import main.java.command.iCommand;

public class ViewAppointmentCmd implements iCommand {
    DoctorFunction doctorCmd = new DoctorFunction();
    @Override
    public String getDesc() {
        return "View Appointment";
    }

    @Override
    public void execute() {
        doctorCmd.viewDoctorAppointment();
    }
}
