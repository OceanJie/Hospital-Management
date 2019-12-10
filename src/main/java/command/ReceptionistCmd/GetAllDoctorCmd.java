package main.java.command.ReceptionistCmd;

import main.java.command.iCommand;
import main.java.staff.Receptionist;

public class GetAllDoctorCmd implements iCommand {
    ReceptionistFunction receptionistFunction = new ReceptionistFunction();

    @Override
    public String getDesc() {
        return "Get all Doctor";
    }

    @Override
    public void execute() {
    receptionistFunction.getAllDoctor();
    }
}
