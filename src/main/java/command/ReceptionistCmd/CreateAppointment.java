package main.java.command.ReceptionistCmd;

import main.java.command.iCommand;

public class CreateAppointment implements iCommand {
    ReceptionistFunction receptionistFunction = new ReceptionistFunction();
    @Override
    public String getDesc() {
        return "Create new Appointment";
    }

    @Override
    public void execute() {
         receptionistFunction.createNewAppointment();
    }
}
