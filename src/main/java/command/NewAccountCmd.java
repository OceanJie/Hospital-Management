package main.java.command;

import main.java.HrFunction;
import main.java.HospitalController;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class NewAccountCmd implements iCommand {
    private HrFunction newCommand ;
    private static String url = "jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false";
    private static String user = "root";
    private static String password = "1234";
   private static Statement myStmt;

    Scanner scan = new Scanner(System.in);


    @Override
    public String getDesc() {
        return "Create new Account";
    }

    @Override
    public void execute() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection myConn = DriverManager.getConnection(url, user, password);
            /*just use this one for the myStmt*/
            myStmt = myConn.createStatement();
            newCommand.createNewAccount(myStmt);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}

