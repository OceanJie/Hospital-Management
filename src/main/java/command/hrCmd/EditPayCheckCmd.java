package main.java.command.hrCmd;

import main.java.command.iCommand;

public class EditPayCheckCmd implements iCommand {
    HrFunction hrCmd = new HrFunction();
    @Override
    public String getDesc() {
        return "Edit employee paycheck";
    }

    @Override
    public void execute() {
    hrCmd.editEmployeePaycheck();
    }
}
