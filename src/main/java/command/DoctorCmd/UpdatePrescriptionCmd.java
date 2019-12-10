package main.java.command.DoctorCmd;

import main.java.command.iCommand;

public class UpdatePrescriptionCmd implements iCommand {
    DoctorFunction doctorFunction = new DoctorFunction();
    @Override
    public String getDesc() {
        return "Give Prescription";
    }

    @Override
    public void execute() {
        doctorFunction.givePrescription();
    }
}
