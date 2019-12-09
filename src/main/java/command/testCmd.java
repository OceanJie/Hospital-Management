package main.java.command;

import main.java.HrFunction;

public class testCmd{
    public static void main(String[] args) {
        HrFrontEndCmd hrCmd = new HrFrontEndCmd();
        NewAccountCmd createNewAcc = new NewAccountCmd();

//        iCommand createNewAcc = new NewAccountCmd();
        hrCmd.addCommand(createNewAcc);
//        hrCmd.displayCommand();

        //createNewAcc.execute();

    }


}
