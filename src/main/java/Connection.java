package main.java;

import java.sql.*;
/**
 * @author Sheng Jie Ooi
* class that consist methods to retrieve and update the mySQL database
* name of the database : mydb
* Tables in mydb :
*   doctors
*   patients
*   pharmacyinventory
*   appointment
*
*/
public class Connection {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
        String user = "root";
        String password = "1234";
            try {
                /*downloading the driver for jbdc*/
                Class.forName("com.mysql.cj.jdbc.Driver");
                java.sql.Connection myConn = DriverManager.getConnection(url, user, password);
                /*just use this one for the myStmt*/
                Statement myStmt = myConn.createStatement();

                Patient p = new Patient("2","Ken Lee");
                System.out.println(isStringEntityExist(myStmt,"patients","patientName","Ken Lee"));



                getDoctorAppointment(myStmt,"Lol lmao");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * print all doctors from the doctor table on mySql in this format: doctorId    doctorName
         * @param myStmt
         * PS: do not change the value of myStmt, just use the one that is implemented on the main method.
         * */
        public static void getAllDoctor(Statement myStmt){
        try{
            /*this is the command to doctors table*/
            String sql = "select * from mydb.doctors";
            ResultSet rs = myStmt.executeQuery(sql); //Return the query based on the sql that is parsed
            System.out.printf("%-20s %-20s %n",  "Doctor id", "Doctor Name");
            /* while loop that prints everything from mydb.doctors*/
            while (rs.next()){
                /*"doctorName" is the name of the column of the doctors table"*/
                System.out.printf("%-20s %-20s %n",rs.getString("doctorId"), rs.getString("doctorName"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


        }
    /**
     * print a doctor's name and id based on the doctor ID given
     * @param myStmt
     * PS: do not change the value of myStmt, just use the one that is implemented on the main method.
     * @poram doctorId
    * */
    public static void getDoctor(Statement myStmt, int doctorId){
        try{
            /*this is the command to doctors table*/
            String sql = "select * from mydb.doctors where doctorId = "+ doctorId;
            ResultSet rs = myStmt.executeQuery(sql); //Return the query based on the sql that is parsed
            System.out.printf("%-20s %-20s %n",  "Doctor id", "Doctor Name");
            /* while loop that prints everything from mydb.doctors*/
            while (rs.next()){
                /*"doctorName" is the name of the column of the doctors table"*/
                System.out.printf("%-20s %-20s %n",rs.getString("doctorId"), rs.getString("doctorName"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Create a new doctor entity on the mySQL database
     * @param myStmt
     * @param d
     * */
    public static void createDoctor(Statement myStmt, Doctor d){
        try{
            /* the format will be: ("INSERT INTO mydb.[table name] ([first column], [second Column]) VALUES ('[value for first column]', '[value for second column]')");*/
            myStmt.executeUpdate("INSERT INTO mydb.doctors (doctorId, doctorName) VALUES ("
                    +"'"+d.getId()+"'"+","+"'"+d.getName()+"'"+")");

            System.out.printf("Doctor Id: %s \t Doctor Name: %s \n have successfully added into database", d.getId(),d.getName());


        }
        catch (SQLIntegrityConstraintViolationException e){
            System.out.printf("FAILED TO CREATE NEW DOCTOR!!! Doctor ID %s already exist \n", d.getId());

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    /**
     * return false if the input String is not a entity in the column of a table , else return true
     * @param myStmt
     * @param tableName
     * @param columnName
     * @param inputString
     * */
    public static boolean isStringEntityExist(Statement myStmt , String tableName, String columnName,String inputString) {
        boolean exist = false;
        try {
            String sql_res = "select * from mydb."+tableName +" where "+ columnName +" = '" + inputString+"'";
            ResultSet rs = myStmt.executeQuery(sql_res);
            String a = String.valueOf(rs.next());
            if (a.equals("true")) {
                exist = true;

            }
        } catch (SQLException e) {

            e.printStackTrace();


        }
        return exist;
    }

    /**
     * print out all the entities from the patient table in the format of : patientId   patientName
     * @param myStmt
     * PS: do not change the value of myStmt, just use the one that is implemented on the main method.
     * */
    public static void getAllPatients(Statement myStmt){
        try{
            /*this is the command to patients table*/
            String sql = "select * from mydb.patients";
            ResultSet rs = myStmt.executeQuery(sql); //Return the query based on the sql that is parsed
            System.out.printf("%-20s %-20s %n",  "Patient id", "Patient Name");
            /* while loop that prints everything from mydb.patients*/
            while (rs.next()){
                /*"patientName" and "patientId" are the name of the column of the patient table"*/
                System.out.printf("%-20s %-20s %n",rs.getString("patientId"), rs.getString("patientName"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Print a patient information based on the patient's id given in the format of : patientId     patientName
     * @param myStmt
     * PS: do not change the value of myStmt, just use the one that is implemented on the main method.
     * @param patientId
     * */
    public static void getPatient(Statement myStmt, int patientId){
        try{
            /*this is the command to doctors table*/
            String sql = "select * from mydb.patients where patientId = "+ patientId;
            ResultSet rs = myStmt.executeQuery(sql); //Return the query based on the sql that is parsed
            System.out.printf("%-20s %-20s %n",  "Patient id", "Patient Name");
            /* while loop that prints everything from mydb.doctors*/
            while (rs.next()){
                /*"PatientName" is the name of the column of the doctors table"*/
                System.out.printf("%-20s %-20s %n",rs.getString("patientId"), rs.getString("patientName"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Create a new patient and add it into the database
     * @param myStmt
     * @param p
     *
     * */
    public static void createPatient(Statement myStmt, Patient p){
        try{
            /* the format will be: ("INSERT INTO mydb.[table name] ([first column], [second Column]) VALUES ('[value for first column]', '[value for second column]')");*/
            myStmt.executeUpdate("INSERT INTO mydb.patients (patientId, patientName) VALUES ("
                    +"'"+p.getID()+"'"+","+"'"+p.getName()+"'"+")");

            System.out.printf("Patient Id: %s \t Patient Name: %s \n have successfully added into database", p.getID(),p.getName());


        }
        catch (SQLIntegrityConstraintViolationException e){
            System.out.printf("FAILED TO CREATE NEW PATIENT!!! Patient ID %s already exist \n", p.getID());

        }
        catch (SQLException e){
            e.printStackTrace();
        }


    }
    /**
     * Return true if patient exist in database, else return false
     * @param myStmt
     * @param p
     * */
    public static boolean isPatientExist(Statement myStmt , String p) {
        boolean exist = false;
        try {
            String sql_res = "select * from mydb.patients where patientName = '" + p+"'";
            ResultSet rs = myStmt.executeQuery(sql_res);
            String a = String.valueOf(rs.next());
            if (a.equals("true")) {
                exist = true;

            }
        } catch (SQLException e) {

            e.printStackTrace();


        }
            return exist;
    }


    /**
     * creating a new appointment entity
     * return false if patient and doctor does not exist.
     * @param myStmt
     * @param app
     * */
    public static void createAppointment(Statement myStmt, Appointment app){
        try{
            /* the format will be: ("INSERT INTO mydb.[table name] ([first column], [second Column]) VALUES ('[value for first column]', '[value for second column]')");*/
            String exeUpdate = "INSERT INTO mydb.appointments (appointmentId, patientName, doctorName, month, day, hour, minute, prescription) VALUES (";
            myStmt.executeUpdate( exeUpdate+ "'"+app.getID()+"'"+","+"'"+app.getpatient_name()+"'"+","+"'"+app.getdoctor_name() + "'"+ ","+"'"+ app.getMonth() + "'"+ "," + "'"+ app.getDay() + "'"+ ","
                    +"'"+ app.getHour() + "'"+ ","+"'"+ app.getMin() + "'"+ "," + "'"+ app.getprescription()+"'"+
                    ")");

            System.out.printf("Appointment Id: %s \t Patient name: %s \t Doctor Name: %s \t date: %d/%d \t time: %d%d \t prescription: %s" +
                    "\nhave successfully added into database", app.getID(),app.getpatient_name(),app.getdoctor_name(),app.getMonth(),app.getDay(), app.getHour(), app.getMin(), app.getprescription());

            if(isStringEntityExist(myStmt, "patients", "patientName", app.getpatient_name())){
                System.out.printf("Patient Name: %s does not exist. Please create patient before making appointment",app.getpatient_name());
            }

        }
        catch (SQLIntegrityConstraintViolationException e){
            System.out.printf("FAILED TO CREATE NEW Appointment!!! Appointment ID %s already exist \n", app.getID());

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
    /**
     * print out all the appointment from the database
     * @param myStmt
     * PS: do not change the value of myStmt, just use the one that is implemented on the main method.
     * */
    public static void getAllAppointment(Statement myStmt){
        try{
            /*this is the command to patients table*/
            String sql = "select * from mydb.appointment";
            ResultSet rs = myStmt.executeQuery(sql); //Return the query based on the sql that is parsed
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %n",  "Appointment id", "Patient Id", "Patient Name","Doctor Id", "Doctor Name", "Date", "Time");
            /* while loop that prints everything from mydb.patients*/
            while (rs.next()){
                /*"Appointment id", "Patient Id", "Patient Name","Doctor Id", "Doctor Name", "Date", "Time" are the name of the column of the appointment table"*/
                System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %n",  rs.getString("appointmentId"),rs.getString("patientId"),
                        rs.getString("patientName"), rs.getString("doctorId"), rs.getString("doctorName"),rs.getString("Date"),rs.getString("time"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * print out all appointment from the table
     * @param appointmentId
     * @param myStmt
     * PS: do not change the value of myStmt, just use the one that is implemented on the main method.
     * */
    public static void getAppointment(Statement myStmt, int appointmentId){
        try{
            /*this is the command to patients table*/
            String sql = "select * from mydb.appointment where appointmentId = "+ appointmentId;

            ResultSet rs = myStmt.executeQuery(sql); //Return the query based on the sql that is parsed
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %n",  "Appointment id", "Patient Name", "Doctor Name", "Month", "Day", "Hour","Minutes","Prescription");
            /* while loop that prints everything from mydb.patients*/
            while (rs.next()){
                /*"Appointment id", "Patient Name", "Doctor Name", "Month", "Day", "Hour","Minutes","Prescription" are the name of the column of the appointment table"*/
                System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %n",  rs.getString("appointmentId"),
                        rs.getString("patientName"),  rs.getString("doctorName"),rs.getString("month"),rs.getString("day"),rs.getString("hour")
                        ,rs.getString("minute"),rs.getString("prescription"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
/**
 * get prescription of a patient
 * @param myStmt
 * @param pName
 */

    public static void getPatientPrescrip(Statement myStmt, String pName){
        if(!isPatientExist(myStmt,pName)){
            System.out.printf("Patient Name %s does not exist",pName);
            return;
        }
        try{
            /*this is the command to doctors table*/
            String sql = "select prescription from mydb.appointments where patientName = "+ "'"+pName+"'";
            ResultSet rs = myStmt.executeQuery(sql); //Return the query based on the sql that is parsed

            /* while loop that prints the prescription of patient*/
            while (rs.next()){
                /*"PatientName" is the name of the column of the doctors table"*/
                System.out.printf("prescription: %s ",rs.getString("prescription"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
/**
 * return all appointment of a doctor
 * @param myStmt
 * @param dName
 * */
    public static void getDoctorAppointment(Statement myStmt, String dName){

        if(!isStringEntityExist(myStmt,"appointments","doctorName", dName))
        {
            System.out.printf("Doctor name: %s does not have appointment",dName);
            return;
        }
        if(isStringEntityExist(myStmt,"doctors","doctorName",dName)){
            System.out.printf("Doctor Name: %s does not exist. ",dName);
            return;
        }
        try{
            /*this is the command to appointment table and retrieve all entities with doctor name*/
            String sql = "select * from mydb.appointments where doctorName = "+"'"+dName+"'";
            ResultSet rs = myStmt.executeQuery(sql); //Return the query based on the sql that is parsed


            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %n",  "Appointment id", "Patient Name", "Doctor Name", "Month", "Day", "Hour","Minutes","Prescription");
            /* while loop that prints everything from mydb.patients*/
            while (rs.next()){
                /*"Appointment id", "Patient Name", "Doctor Name", "Month", "Day", "Hour","Minutes","Prescription" are the name of the column of the appointment table"*/
                System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %n",  rs.getString("appointmentId"),
                        rs.getString("patientName"),  rs.getString("doctorName"),rs.getString("month"),rs.getString("day"),rs.getString("hour")
                        ,rs.getString("minute"),rs.getString("prescription"));
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }


    }


}
