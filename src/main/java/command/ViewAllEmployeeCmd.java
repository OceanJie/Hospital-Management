package main.java.command;

import main.java.HrFunction;

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
