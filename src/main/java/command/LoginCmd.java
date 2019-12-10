package main.java.command;

import main.java.command.hrCmd.HrFunction;

public class LoginCmd implements iCommand {
    HrFunction hrCmd = new HrFunction();
    @Override
    public String getDesc() {
        return "Login";
    }

    @Override
    public void execute() {
    hrCmd.loginAs();


    }
}
