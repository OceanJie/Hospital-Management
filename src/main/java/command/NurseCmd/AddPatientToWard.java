package main.java.command.NurseCmd;

import main.java.command.iCommand;

public class AddPatientToWard implements iCommand {
    NurseFunction nurseFunction = new NurseFunction();
    @Override
    public String getDesc() {
        return "Add Patient to ward";
    }

    @Override
    public void execute() {
        nurseFunction.addPatientToWard();
    }
}
