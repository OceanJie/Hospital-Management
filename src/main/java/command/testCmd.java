package main.java.command;

import java.util.ArrayList;
import java.util.Scanner;

public class testCmd{
    public static void main(String[] args) {
        //After login as Cafeteria Attendant

        Scanner scan = new Scanner(System.in);

        ArrayList<iCommand>cmdList = new ArrayList<iCommand>();//arrayList to store all the command

        CafeteriaFrontEnd cafe = new CafeteriaFrontEnd(cmdList);
        TakeOrderCmd takeOrderCmd = new TakeOrderCmd();

        cafe.addCommand(takeOrderCmd);

        cafe.displayCommand();

        int cmdNum = scan.nextInt();

        cmdList.get(cmdNum-1).execute();
    }


}
