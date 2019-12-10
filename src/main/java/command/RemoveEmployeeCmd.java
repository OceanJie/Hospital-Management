package main.java.command;

import main.java.HrFunction;

public class RemoveEmployeeCmd implements iCommand {
    HrFunction hrCmd = new HrFunction();
    @Override
    public String getDesc() {
        return "Remove Employee";
    }

    @Override
    public void execute() {
        hrCmd.removeExistingEmployee();
    }
}
