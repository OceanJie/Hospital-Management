package main.java.command.hrCmd;
import main.java.HospitalController;

import java.sql.*;
import java.util.Scanner;

import static main.java.EmployeeImage.PatientImageFrame;

public class HrFunction {

    private static HospitalController conn = new HospitalController();
    private static String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
    private static String user = "root";
    private static String password = "1234";
    private static Statement myStmt;
    private static Scanner scan = new Scanner(System.in);
    private static String[] tablenameOption = {" ", "doctors", "surgeons", "nurses", "receptionist", "hr", "pharmacist"};
    private static final String JOBOPTION =  "1. Doctor \n" +
                                        "2. Surgeon \n" +
                                        "3. Nurse \n" +
                                        "4. Receptionist \n" +
                                        "5. Human Resources \n" +
                                        "6. Pharmacist \n" +
                                        "-1. Back to home \n";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection myConn = DriverManager.getConnection(url, user, password);
            /*just use this one for the myStmt*/
            myStmt = myConn.createStatement();



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        viewAllEmployeeExe();
    }
    public static void createAccExe(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection myConn = DriverManager.getConnection(url, user, password);
            /*just use this one for the myStmt*/
            myStmt = myConn.createStatement();
            createNewAccount(myStmt);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * get user's username, password, name and add it into database
     *
     * @param myStmt
     */
    public static void createNewAccount(Statement myStmt){// return name;
        String username;
        String password;
        String name;

        System.out.println("Select a number of the job title of the new employee.\n" + JOBOPTION);
        int jobOption = scan.nextInt();
        String tableName = tablenameOption[jobOption].toString();

        while(true){
            System.out.println("Enter the username");
            username = scan.next();
            if(conn.isStringEntityExist(myStmt,tableName,"username",username)){
                System.out.printf("Username %s already exist \n", username);
            }
            else{
                break;
            }
        }

        String newPass="";
        String newRePass="";
        do{
            if(!newPass.equals(newRePass)){
                System.out.println("Password not match, please enter again");
            }
            System.out.println("Enter the password");
            newPass = scan.next();
            System.out.println("Re-enter the password");
            newRePass = scan.next();
        }
        while(!newPass.equals(newRePass));
       password = newPass;


       String lastName;
       String firstName;
       System.out.println("Enter First name");
       firstName = scan.next();
       System.out.println("Enter Last Name");
       lastName = scan.next();
       name = firstName +" "+lastName;
        try {
            /* the format will be: ("INSERT INTO mydb.[table name] ([first column], [second Column]) VALUES ('[value for first column]', '[value for second column]')");*/
            myStmt.executeUpdate("INSERT INTO mydb."+tableName +" (username, password, name) VALUES ("
                    + "'" + username + "'" + "," + "'" + password + "'" + "," + "'" + name + "'" + ")");

            System.out.printf("Username: %s \t Password: %s \t Name: %s \t added in to %s", username,password,name,tableName);


        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void loginAs(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection myConn = DriverManager.getConnection(url, user, password);
            /*just use this one for the myStmt*/
            myStmt = myConn.createStatement();
            System.out.println("Enter User Name");
            String userName = scan.next();
            System.out.println("Enter password");
            String pass = scan.next();
            String tableName = checkLoginTable(myStmt,userName,pass);

            

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /**
     * Login UI
     * @param myStmt
     *
     * */
    public static String checkLoginTable(Statement myStmt, String userName, String pass){
        String tableName = "Username not exist in the table \n" ;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection myConn = DriverManager.getConnection(url, user, password);
            /*just use this one for the myStmt*/
            myStmt = myConn.createStatement();
            
            
            for (int i=0;i<tablenameOption.length;i++){
                String table = tablenameOption[i];
                if (conn.checkLogin(myStmt, table, userName, pass) == true) {
                    tableName = table;
                    break;
                }
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }




        return tableName;
    }

    public static void editEmployeePaycheck(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection myConn = DriverManager.getConnection(url, user, password);
            /*just use this one for the myStmt*/
            myStmt = myConn.createStatement();
            while(true){
                System.out.println("Select the job position to see all the employee and it's pay \n" + JOBOPTION);
                int viewJobOption = scan.nextInt();
                if(viewJobOption==-1){
                    break;
                }
                String viewTableName = tablenameOption[viewJobOption].toString();
                conn.getAllFromTable(myStmt, viewTableName);
                System.out.println("Select the employee to edit the pay\n");
                int employeeId = scan.nextInt();
                System.out.println("Please insert the new salary of the employee\n");
                int newValue = scan.nextInt();
                conn.updateColFromTable(myStmt,viewTableName,"salary",employeeId,Integer.toString(newValue));
        }

        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeExistingEmployee(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection myConn = DriverManager.getConnection(url, user, password);
            /*just use this one for the myStmt*/
            myStmt = myConn.createStatement();
            System.out.println("Select job position of the employee to delete from \n"+
                    JOBOPTION);
            int deleteJobOption = scan.nextInt();
            String tableName = tablenameOption[deleteJobOption].toString();
            conn.getAllFromTable(myStmt, tableName);
            System.out.println("Insert the employee id to remove ");
            int deleteEmployeeId= scan.nextInt();
            conn.removeEmployeeAccount(myStmt,tableName,deleteEmployeeId);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public static void viewAllEmployeeExe(){
        int viewJobOption;
        int employeeId;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection myConn = DriverManager.getConnection(url, user, password);
            /*just use this one for the myStmt*/
            myStmt = myConn.createStatement();
            while(true){
                System.out.println("Select the job position to see all the employee \n"+
                        JOBOPTION);

                viewJobOption = scan.nextInt();
                if(viewJobOption==-1){
                    break;
                }
                String viewTableName = tablenameOption[viewJobOption].toString();
                conn.getAllFromTable(myStmt, viewTableName);
                System.out.println("Select employee to view profile");
                employeeId = scan.nextInt();

                //get pic path
                String picturePath = conn.getStringEntity(myStmt,viewTableName,"picture","id",Integer.toString(employeeId));
                //get name
                String employeeName = conn.getStringEntity(myStmt,viewTableName,"name","id",Integer.toString(employeeId));
                //get job title

                {
                    // 显示应用 GUI
                    javax.swing.SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            PatientImageFrame(picturePath, employeeName,viewTableName);

                        }
                    });

                }


            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    //old stuff
    public static void hrMenu(Statement myStmt) throws InterruptedException {
        //loginUI(myStmt,"hr");
        int hrOption = 0;
        int employeeId = 0;
        do{
            System.out.println("1. create new account");
            System.out.println("2. remove existing account");
            System.out.println("3. View existing employee");

            System.out.println("4. Pay employee");
            System.out.println("5. Edit Salary");

            System.out.println();
            System.out.println("-1. return to main menu");
            hrOption=scan.nextInt();

            switch (hrOption){

                case 2:

                    break;
                case 3:


                    break;

                case 4: //pay employee
                    break;

                case 5://edit employee paycheck



                    break;
            }
            Thread.sleep(500);
        }
        while (hrOption!= -1);
    }

}
