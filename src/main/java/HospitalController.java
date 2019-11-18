package main.java;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class HospitalController {

  /**
   * Test case for the method
   */
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
      Appointment app = new Appointment(1,"Ken Lee","Nick Ong",19971010,1800,"yeet");
      addPatientToWard(myStmt,app,1);
      deletePatientFromTable(myStmt,"emergency_ward",1);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
public static void getAllFromTable(Statement myStmt, String tableName) {
  try {
    /*this is the command to doctors table*/
    String sql = "select * from mydb." + "tableName";
    ResultSet rs = myStmt.executeQuery(sql); //Return the query based on the sql that is parsed
    if (tableName.equals("doctors")) {
      System.out.printf("%-20s %-20s %n", "Doctor id", "Doctor Name");
      /* while loop that prints everything from mydb.doctors*/
      while (rs.next()) {
        /*"doctorName" is the name of the column of the doctors table"*/
        System.out.printf("%-20s %-20s %n", rs.getString("doctorId"), rs.getString("doctorName"));
      }
    }
    if (tableName.equals("patients")) {
      System.out.printf("%-20s %-20s %n", "Patient id", "Patient Name");
      /* while loop that prints everything from mydb.patients*/
      while (rs.next()) {
        /*"patientName" and "patientId" are the name of the column of the patient table"*/
        System.out.printf("%-20s %-20s %n", rs.getString("patientId"), rs.getString("patientName"));
      }
    }
    if (tableName.equals("pharmacy")) {

    }
    if (tableName.equals("appointments")) {
      System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %n", "Appointment id", "Patient Name", "Doctor Name", "Date", "Time", "Prescription");
      /* while loop that prints everything from mydb.patients*/
      while (rs.next()) {
        /*"Appointment id", "Patient Id", "Patient Name", "Doctor Name", "Date", "Time" are the name of the column of the appointment table"*/
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %n", rs.getString("appointmentId"),
                rs.getString("patientName"), rs.getString("doctorName"), rs.getString("date"), rs.getString("time"), rs.getString("prescription"));
      }
      if (tableName.equals("emergency_ward")) {
        System.out.printf("%-20s %-20s %-20s %n", "Emergency id", "Patient Name","Doctor Name");
        /* while loop that prints everything from mydb.patients*/
        while (rs.next()) {
          /*"patientName" and "patientId" are the name of the column of the patient table"*/
          System.out.printf("%-20s %-20s %n", rs.getString("emergId"), rs.getString("patientName"), rs.getString("doctorName"));
        }
      }
      if (tableName.equals("inventory")) {
        System.out.printf("%-20s %-20s %-20d%n", "med id", "med Name", "amount");
        /* while loop that prints everything from mydb.patients*/
        while (rs.next()) {
          /*"patientName" and "patientId" are the name of the column of the patient table"*/
          System.out.printf("%-20s %-20s %-20d %n", rs.getString("medId"), rs.getString("medName"), rs.getString("amount"));
        }
      }
      if (tableName.equals("surgeryapp")) {
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20d %-20s %-20d %n", "Surgury App ID", "Patient Name", "Surgeon Name", "Nurse Name", "Nurse Name", "Nurse Name",
                "Start time", "Hour of Use");
        /* while loop that prints everything from mydb.patients*/
        while (rs.next()) {
          /*"Appointment id", "Patient Id", "Patient Name", "Doctor Name", "Date", "Time" are the name of the column of the appointment table"*/
          System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20d %-20s %-20d %n", rs.getString("surgAppId"),
                  rs.getString("patientName"), rs.getString("surgeonName"), rs.getString("nurseName"), rs.getString("nurseName2"), rs.getString("nurseName3"),
                  rs.getString("startTime"), rs.getString("hourOfUse"));
        }
      }
      if (tableName.equals("ward")) {
        System.out.printf("%-20s %-20s %-20s %-20s %n", "Ward id", "Patient Name","Doctor Name", "Prescription");
        /* while loop that prints everything from mydb.patients*/
        while (rs.next()) {
          /*"patientName" and "patientId" are the name of the column of the patient table"*/
          System.out.printf("%-20s %-20s %-20s %-20s %n", rs.getString("wardId"), rs.getString("patientName"),rs.getString("doctorName"), rs.getString("prescription"));
        }
      }

    }
  } catch (SQLException e) {
    e.printStackTrace();
  }
}
  /**
   * print all doctors from the doctor table on mySql in this format: doctorId    doctorName
   *
   * @param myStmt PS: do not change the value of myStmt, just use the one that is implemented on the main method.
   */
  public static void getAllDoctor(Statement myStmt) {
    try {
      /*this is the command to doctors table*/
      String sql = "select * from mydb.doctors";
      ResultSet rs = myStmt.executeQuery(sql); //Return the query based on the sql that is parsed
      System.out.printf("%-20s %-20s %n", "Doctor id", "Doctor Name");
      /* while loop that prints everything from mydb.doctors*/
      while (rs.next()) {
        /*"doctorName" is the name of the column of the doctors table"*/
        System.out.printf("%-20s %-20s %n", rs.getString("doctorId"), rs.getString("doctorName"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }


  }

  /**
   * print a doctor's name and id based on the doctor ID given
   *
   * @param myStmt PS: do not change the value of myStmt, just use the one that is implemented on the main method.
   * @poram doctorId
   */
  public static void getDoctor(Statement myStmt, int doctorId) {
    try {
      /*this is the command to doctors table*/
      String sql = "select * from mydb.doctors where doctorId = " + doctorId;
      ResultSet rs = myStmt.executeQuery(sql); //Return the query based on the sql that is parsed
      System.out.printf("%-20s %-20s %n", "Doctor id", "Doctor Name");
      /* while loop that prints everything from mydb.doctors*/
      while (rs.next()) {
        /*"doctorName" is the name of the column of the doctors table"*/
        System.out.printf("%-20s %-20s %n", rs.getString("doctorId"), rs.getString("doctorName"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Create a new doctor entity on the mySQL database
   *
   * @param myStmt
   * @param d
   */
  public static void createDoctor(Statement myStmt, Doctor d) {
    try {
      /* the format will be: ("INSERT INTO mydb.[table name] ([first column], [second Column]) VALUES ('[value for first column]', '[value for second column]')");*/
      myStmt.executeUpdate("INSERT INTO mydb.doctors (doctorId, doctorName) VALUES ("
              + "'" + d.getId() + "'" + "," + "'" + d.getName() + "'" + ")");

      System.out.printf("Doctor Id: %s \t Doctor Name: %s \n have successfully added into database", d.getId(), d.getName());


    } catch (SQLIntegrityConstraintViolationException e) {
      System.out.printf("FAILED TO CREATE NEW DOCTOR!!! Doctor ID %s already exist \n", d.getId());

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * return false if the input String is not a entity in the column of a table , else return true
   *
   * @param myStmt
   * @param tableName
   * @param columnName
   * @param inputString
   */
  public static boolean isStringEntityExist(Statement myStmt, String tableName, String columnName, String inputString) {
    boolean exist = false;
    try {
      String sql_res = "select * from mydb." + tableName + " where " + columnName + " = '" + inputString + "'";
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
  public static String getStringEntity(Statement myStmt, String tableName, String desireCol,String givenCol, String inputString) {

    try {
      String sql_res = "select"+ desireCol +"from mydb." + tableName + " where " + givenCol + " = '" + inputString + "'";
      ResultSet rs = myStmt.executeQuery(sql_res);
      String a = String.valueOf(rs.next());
      if (a.equals("false")) {
        System.out.printf("input: %s not exist", inputString);

      }
      return a;
    } catch (SQLException e) {

      e.printStackTrace();


    }
  return "";
  }
  /**
   * print out all the entities from the patient table in the format of : patientId   patientName
   *
   * @param myStmt PS: do not change the value of myStmt, just use the one that is implemented on the main method.
   */
  public static void getAllPatients(Statement myStmt) {
    try {
      /*this is the command to patients table*/
      String sql = "select * from mydb.patients";
      ResultSet rs = myStmt.executeQuery(sql); //Return the query based on the sql that is parsed
      System.out.printf("%-20s %-20s %n", "Patient id", "Patient Name");
      /* while loop that prints everything from mydb.patients*/
      while (rs.next()) {
        /*"patientName" and "patientId" are the name of the column of the patient table"*/
        System.out.printf("%-20s %-20s %n", rs.getString("patientId"), rs.getString("patientName"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Print a patient information based on the patient's id given in the format of : patientId     patientName
   *
   * @param myStmt    PS: do not change the value of myStmt, just use the one that is implemented on the main method.
   * @param patientId
   */
  public static void getPatient(Statement myStmt, int patientId) {
    try {
      /*this is the command to doctors table*/
      String sql = "select * from mydb.patients where patientId = " + patientId;
      ResultSet rs = myStmt.executeQuery(sql); //Return the query based on the sql that is parsed
      System.out.printf("%-20s %-20s %n", "Patient id", "Patient Name");
      /* while loop that prints everything from mydb.doctors*/
      while (rs.next()) {
        /*"PatientName" is the name of the column of the doctors table"*/
        System.out.printf("%-20s %-20s %n", rs.getString("patientId"), rs.getString("patientName"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Create a new patient and add it into the database
   *
   * @param myStmt
   * @param p
   */
  public static void createPatient(Statement myStmt, Patient p) {
    try {
      /* the format will be: ("INSERT INTO mydb.[table name] ([first column], [second Column]) VALUES ('[value for first column]', '[value for second column]')");*/
      myStmt.executeUpdate("INSERT INTO mydb.patients (patientId, patientName) VALUES ("
              + "'" + p.getID() + "'" + "," + "'" + p.getName() + "'" + ")");

      System.out.printf("Patient Id: %s \t Patient Name: %s \n have successfully added into database", p.getID(), p.getName());


    } catch (SQLIntegrityConstraintViolationException e) {
      System.out.printf("FAILED TO CREATE NEW PATIENT!!! Patient ID %s already exist \n", p.getID());

    } catch (SQLException e) {
      e.printStackTrace();
    }


  }

  /**
   * Return true if patient exist in database, else return false
   *
   * @param myStmt
   * @param p
   */
  public static boolean isPatientExist(Statement myStmt, String p) {
    boolean exist = false;
    try {
      String sql_res = "select * from mydb.patients where patientName = '" + p + "'";
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
   *
   * @param myStmt
   * @param app
   */
  public static void createAppointment(Statement myStmt, Appointment app) {
    try {
      /* the format will be: ("INSERT INTO mydb.[table name] ([first column], [second Column]) VALUES ('[value for first column]', '[value for second column]')");*/
      String exeUpdate = "INSERT INTO mydb.appointments (appointmentId, patientName, doctorName, date,time, prescription) VALUES (";
      myStmt.executeUpdate(exeUpdate + "'" + app.getID() + "'" + "," + "'" + app.getpatient_name() + "'" + "," + "'" + app.getdoctor_name() + "'" + "," + "'" + app.getDate() + "'" +  ","
              + "'" + app.getTime()*100 + "'" + ","  + "'" + app.getprescription() + "'" +
              ")");

      System.out.printf("Appointment Id: %s \t Patient name: %s \t Doctor Name: %s \t date(yyyymmdd): %d \t time(hhmmss): %d \t prescription: %s" +
              "\nhave successfully added into database", app.getID(), app.getpatient_name(), app.getdoctor_name(), app.getDate(), app.getTime(), app.getprescription());

      if (isStringEntityExist(myStmt, "patients", "patientName", app.getpatient_name())) {
        System.out.printf("Patient Name: %s does not exist. Please create patient before making appointment", app.getpatient_name());
      }

    } catch (SQLIntegrityConstraintViolationException e) {
      System.out.printf("FAILED TO CREATE NEW Appointment!!! Appointment ID %s already exist \n", app.getID());

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  /**
   * print out all the appointment from the database
   *
   * @param myStmt PS: do not change the value of myStmt, just use the one that is implemented on the main method.
   */
  public static void getAllAppointment(Statement myStmt) {
    try {
      /*this is the command to patients table*/
      String sql = "select * from mydb.appointment";
      ResultSet rs = myStmt.executeQuery(sql); //Return the query based on the sql that is parsed
      System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %n", "Appointment id", "Patient Name", "Doctor Name", "Date", "Time",  "Prescription");
      /* while loop that prints everything from mydb.patients*/
      while (rs.next()) {
        /*"Appointment id", "Patient Id", "Patient Name", "Doctor Name", "Date", "Time" are the name of the column of the appointment table"*/
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %n", rs.getString("appointmentId"),
                rs.getString("patientName"), rs.getString("doctorName"), rs.getString("date"), rs.getString("time"),  rs.getString("prescription"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * print out all appointment from the table
   *
   * @param appointmentId
   * @param myStmt        PS: do not change the value of myStmt, just use the one that is implemented on the main method.
   */
  public static void getAppointment(Statement myStmt, int appointmentId) {
    try {
      /*this is the command to patients table*/
      String sql = "select * from mydb.appointment where appointmentId = " + appointmentId;

      ResultSet rs = myStmt.executeQuery(sql); //Return the query based on the sql that is parsed
      System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %n", "Appointment id", "Patient Name", "Doctor Name", "Date", "Time",  "Prescription");
      /* while loop that prints everything from mydb.patients*/
      while (rs.next()) {
        /*"Appointment id", "Patient Name", "Doctor Name", "Month", "Day", "Hour","Minutes","Prescription" are the name of the column of the appointment table"*/
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %n", rs.getString("appointmentId"),
                rs.getString("patientName"), rs.getString("doctorName"), rs.getString("date"), rs.getString("time"),  rs.getString("prescription"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * get prescription of a patient
   *
   * @param myStmt
   * @param pName
   */

  public static void getPatientPrescrip(Statement myStmt, String pName) {
    if (!isPatientExist(myStmt, pName)) {
      System.out.printf("Patient Name %s does not exist", pName);
      return;
    }
    try {
      /*this is the command to doctors table*/
      String sql = "select prescription from mydb.appointments where patientName = " + "'" + pName + "'";
      ResultSet rs = myStmt.executeQuery(sql); //Return the query based on the sql that is parsed

      /* while loop that prints the prescription of patient*/
      while (rs.next()) {
        /*"PatientName" is the name of the column of the doctors table"*/
        System.out.printf("prescription: %s ", rs.getString("prescription"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * return all appointment of a doctor
   *
   * @param myStmt
   * @param dName
   */
  public static void getDoctorAppointment(Statement myStmt, String dName) {

    if (!isStringEntityExist(myStmt, "appointments", "doctorName", dName)) {
      System.out.printf("Doctor name: %s does not have appointment", dName);
      return;
    }
    if (isStringEntityExist(myStmt, "doctors", "doctorName", dName)) {
      System.out.printf("Doctor Name: %s does not exist. ", dName);
      return;
    }
    try {
      /*this is the command to appointment table and retrieve all entities with doctor name*/
      String sql = "select * from mydb.appointments where doctorName = " + "'" + dName + "'";
      ResultSet rs = myStmt.executeQuery(sql); //Return the query based on the sql that is parsed


      System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %n", "Appointment id", "Patient Name", "Doctor Name", "Date", "Time",  "Prescription");
      /* while loop that prints everything from mydb.patients*/
      while (rs.next()) {
        /*"Appointment id", "Patient Name", "Doctor Name", "Month", "Day", "Hour","Minutes","Prescription" are the name of the column of the appointment table"*/
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %n", rs.getString("appointmentId"),
                rs.getString("patientName"), rs.getString("doctorName"), rs.getString("date"), rs.getString("time"),  rs.getString("prescription"));
      }

    } catch (Exception e) {
      e.printStackTrace();
    }


  }

  public static void updatePrescription(Statement myStmt, int appId, String newPrescrip) {
    if (!isStringEntityExist(myStmt, "appointments", "appointmentId", Integer.toString(appId))) {
      System.out.printf("Appointment id %d does not exist", appId);
      return;
    }
    try {
      /*this is the command to appointment table and retrieve all entities with doctor name*/
      String sql = "UPDATE appointments SET prescription =" + "'" + newPrescrip + "'" + "WHERE appointmentId = " + "'" + appId + "'";
      myStmt.executeUpdate(sql); //Return the query based on the sql that is parsed


      System.out.printf("Appointment Id: %s ,Prescription : %s has successfully updated!", appId, newPrescrip);


    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static void updateInventory(Statement myStmt, int medId, int newAmount) {
    if (!isStringEntityExist(myStmt, "inventory", "medId", Integer.toString(medId))) {
      System.out.printf("Medicine id %d does not exist %n", medId);
      return;
    }

    try {
      /*this is the command to appointment table and retrieve all entities with doctor name*/
      String sql = "UPDATE inventory SET amount =" + "'" + newAmount + "'" + "WHERE medId = " + "'" + medId + "'";
      String getAmount="Select amount from mydb.inventory where medId = "+ medId;
      myStmt.executeUpdate(sql); //Return the query based on the sql that is parsed

      System.out.printf("Medicine Id: %s ,New Amount : %s has successfully updated!", medId, newAmount);


    } catch (Exception e) {
      e.printStackTrace();
    }
  }
//need to check
  /**
   * add patient to emergency ward, if patient does not exist, it will create a new patient
   * return false if doctorName and emergID does not exist
   *
   * @param myStmt
   * @param p
   * @param doctorName
   * @param emergId
   * */
  public static void addPatientToEmerg(Statement myStmt,Patient p,String doctorName,int emergId){
    if(!isStringEntityExist(myStmt, "emergency_ward","emergId",Integer.toString(emergId))){
      System.out.printf("Emergency id : %d not found", emergId);
      return;
    }
    if(!isStringEntityExist(myStmt, "emergency_ward","doctorName",doctorName)){
      System.out.printf("Doctor Name : %s not found", doctorName);
      return;
    }
    if(!isStringEntityExist(myStmt, "patients","patientName",p.getName())){
      createPatient(myStmt, p);
    }
    try {
      /*this is the command to appointment table and retrieve all entities with doctor name*/
      String exeUpdate = "INSERT INTO mydb.emergency_ward (emergencyId, patientName, doctorName) VALUES (";
      myStmt.executeUpdate(exeUpdate + "'" + emergId + "'" + "," + "'" + p.getName() + "'" + "," + "'" + doctorName + "'"+
              ")");


    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  //need to check
  public static void movePatientFromEmergToWard(Statement myStmt, EmergencyWard emerg,int wardId){
    try {
      /*this is the command to appointment table and retrieve all entities with doctor name*/

      myStmt.executeUpdate("UPDATE mydb.ward SET patientName ='"+ emerg.getpName()+"'"+"doctorName = '"+emerg.getdName()+"'"+  "WHERE (emergId = '"+wardId+"'"+
              ")");
      deletePatientFromTable(myStmt,"emergency_ward",emerg.getEmergId());

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  //need to check
  public static void movePatientFromEmergToSurg(Statement myStmt, Patient p, SurgeryRoom surgeryRoom,int surgId){
    try {
      /*this is the command to appointment table and retrieve all entities with doctor name*/
      myStmt.executeUpdate("UPDATE mydb.surgeryapp SET patientName ='"+ p.getName()+"'"+"surgeonName = '"+surgeryRoom.getSurgeon().getName()+"'"+
              "nurseName = '"+surgeryRoom.getNurses()+"'"+
              "WHERE (surgId = '"+surgId+"'"+
              ")");



    } catch (Exception e) {
      e.printStackTrace();
    }
  }

/**
 * add patient from appointment to ward
 * @param myStmt
 * @param app
 * @param wardId 
 * */
  public static void addPatientToWard(Statement myStmt,Appointment app, int wardId){
    //do the checking for if patient name exist
//if(!isStringEntityExist(myStmt,"mydb.ward","patientName",null)){
//  System.out.printf("ward Id: %d is already taken",wardId);
//  return;
//}
    try {
      /*this is the command to appointment table and retrieve all entities with doctor name*/
      String exeUpdate = "UPDATE mydb.ward SET patientName = ";
      myStmt.executeUpdate(exeUpdate + "'" + app.getpatient_name() + "'" + "," + "doctorName= '" + app.getdoctor_name() + "'" + "," +
              "prescription = '"+app.getprescription()+ "' WHERE (wardId = '"+wardId+"'"+
              ")");

      System.out.printf("Patient:%s has added to ward %d",app.getpatient_name(),wardId);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void deletePatientFromTable(Statement myStmt,String table,  int wardId){
    try {
      /*this is the command to appointment table and retrieve all entities with doctor name*/
      if(table=="emergency_ward"){

        myStmt.executeUpdate("UPDATE mydb.emergency_ward SET patientName = NULL, doctorName = NULL WHERE (emergId = '"+wardId+"'"+
                ")");

        System.out.printf("Removed patient from emergency ward %d",wardId);
      }
      if(table == "ward" ){

        myStmt.executeUpdate("UPDATE mydb.ward SET patientName = NULL, doctorName = NULL, prescription = NULL WHERE (wardId = '"+wardId+"'"+
                ")");

        System.out.printf("Remove patient from ward %d",wardId);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
