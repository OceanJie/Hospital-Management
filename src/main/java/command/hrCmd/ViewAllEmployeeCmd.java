package main.java.command.hrCmd;

import main.java.command.iCommand;

public class ViewAllEmployeeCmd implements iCommand {
    HrFunction hrCmd = new HrFunction();

    @Override
    public String getDesc() {
        return "View All Employee";
    }

    @Override
    public void execute() {
        hrCmd.viewAllEmployeeExe();
    }
}
