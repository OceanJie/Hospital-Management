package main.java.command.NurseCmd;

import main.java.command.iCommand;

public class AddPatientToEmerg implements iCommand {
    NurseFunction nurseFunction = new NurseFunction();
    @Override
    public String getDesc() {
        return "Add patient to emergency ward";
    }

    @Override
    public void execute() {
        nurseFunction.addPatientToEmergency();
    }
}
