package main.java;

import java.sql.*;
/**
 * @author Sheng Jie Ooi
* creating connection to mysql database, then retrieve data and print it out.
* name of the database : hospital_management
* Tables in hospital_management :
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


                Appointment app = new Appointment(1, "Jack", "John","1200","12/11","Yeet");
                createAppointment(myStmt, app);
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
     * */
    public static void createAppointment(Statement myStmt, Appointment app){
        try{
            /* the format will be: ("INSERT INTO mydb.[table name] ([first column], [second Column]) VALUES ('[value for first column]', '[value for second column]')");*/
            String exeUpdate = "INSERT INTO mydb.appointments (appointmentId, patientName, doctorName, date, time, prescription) VALUES (";
            myStmt.executeUpdate( exeUpdate+ "'"+app.getID()+"'"+","+"'"+app.getpatient_name()+"'"+","+"'"+app.getdoctor_name() + "'"+ ","+"'"+ app.getDate() + "'"+ ","
                    +"'"+ app.getTime() + "'"+ "," + "'"+ app.getprescription()+"'"+
                    ")");

            System.out.printf("Appointment Id: %s \t Patient name: %s \t Doctor Name: %s \t date: %s \t time: %s \t prescription: %s" +
                    "\nhave successfully added into database", app.getID(),app.getpatient_name(),app.getdoctor_name(), app.getDate(), app.getTime(), app.getprescription());


        }
        catch (SQLIntegrityConstraintViolationException e){
            System.out.printf("FAILED TO CREATE NEW Appointment!!! Appointment ID %s already exist \n", app.getID());

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }


}
