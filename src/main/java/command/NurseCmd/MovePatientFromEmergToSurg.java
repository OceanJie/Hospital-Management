package main.java.command.NurseCmd;

import main.java.command.iCommand;

public class MovePatientFromEmergToSurg implements iCommand {
    NurseFunction nurseFunction = new NurseFunction();
    @Override
    public String getDesc() {
        return "Move Patient from emergency ward to Surgery ward";
    }

    @Override
    public void execute() {
        nurseFunction.movePatientFromEmergToSurg();
    }
}
