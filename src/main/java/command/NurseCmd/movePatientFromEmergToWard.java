package main.java.command.NurseCmd;

import main.java.command.iCommand;

public class movePatientFromEmergToWard implements iCommand {
    NurseFunction nurseFunction = new NurseFunction();
    @Override
    public String getDesc() {
        return "Move patient from emergency ward to ward";
    }

    @Override
    public void execute() {
    nurseFunction.movePatientFromEmergToWard();
    }
}
