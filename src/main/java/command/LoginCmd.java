package main.java.command;

import main.java.HrFunction;
import main.java.command.iCommand;

import java.sql.Statement;

public class LoginCmd implements iCommand {

    Statement myStmt;
    String table;

    LoginCmd(Statement myStmt, String table) {
        this.myStmt = myStmt;
        this.table = table;
    }

    @Override
    public String getDesc() {
        return "Login";
    }

    @Override
    public void execute() {

        HrFunction.loginUI(myStmt, table);

    }
}
