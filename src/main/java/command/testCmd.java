package main.java.command;

import main.java.command.DoctorCmd.DoctorFrontEnd;
import main.java.command.DoctorCmd.UpdatePrescriptionCmd;
import main.java.command.DoctorCmd.ViewAppointmentCmd;
import main.java.command.hrCmd.HrFrontEndCmd;
import main.java.command.hrCmd.NewAccountCmd;
import main.java.command.hrCmd.RemoveEmployeeCmd;
import main.java.command.hrCmd.ViewAllEmployeeCmd;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class testCmd{
    public static void main(String[] args) {
        //After login as Cafeteria Attendant

        Scanner scan = new Scanner(System.in);

        ArrayList<iCommand>cafeCmdList = new ArrayList<iCommand>();//arrayList to store all the command
        ArrayList<iCommand>hrCmdList = new ArrayList<iCommand>();
        ArrayList<iCommand>doctorCmdList = new ArrayList<iCommand>();

        //cafeteria
        CafeteriaFrontEnd cafe = new CafeteriaFrontEnd(cafeCmdList);
        TakeOrderCmd takeOrderCmd = new TakeOrderCmd();

        cafe.addCommand(takeOrderCmd);

        //hr
        HrFrontEndCmd hr = new HrFrontEndCmd(hrCmdList);
        NewAccountCmd newAccountCmd = new NewAccountCmd();
        ViewAllEmployeeCmd viewAllEmployeeCmd = new ViewAllEmployeeCmd();
        RemoveEmployeeCmd removeEmployeeCmd = new RemoveEmployeeCmd();

        hr.addCommand(newAccountCmd);
        hr.addCommand(viewAllEmployeeCmd);
        hr.addCommand(removeEmployeeCmd);
        //hr.displayCommand();

        //doctor
        DoctorFrontEnd doctorFrontEnd = new DoctorFrontEnd(doctorCmdList);
        ViewAppointmentCmd viewAppointment = new ViewAppointmentCmd();
        UpdatePrescriptionCmd updatePrescriptionCmd = new UpdatePrescriptionCmd();
        doctorFrontEnd.addCommand(viewAppointment);
        doctorFrontEnd.addCommand(updatePrescriptionCmd);
        doctorFrontEnd.displayCommand();

        int cmdNum = scan.nextInt();
        doctorCmdList.get(cmdNum-1).execute();


    }


}
