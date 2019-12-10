package main.java.command.hrCmd;

import main.java.command.iCommand;

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
