package main.java.command;

import java.util.ArrayList;

public class testCmd{
    public static void main(String[] args) {
        ArrayList<iCommand>cmdList = new ArrayList<iCommand>();
        HrFrontEndCmd hrCmd = new HrFrontEndCmd(cmdList);
        NewAccountCmd createNewAcc = new NewAccountCmd();

//        iCommand createNewAcc = new NewAccountCmd();
        String checkDescription = createNewAcc.getDesc();

        hrCmd.addCommand(createNewAcc);
        String checkArrDesc = cmdList.get(0).getDesc();
        hrCmd.displayCommand();


//        createNewAcc.execute();

    }


}
