package main.java.command;

import java.util.ArrayList;

public class HrFrontEndCmd implements iFontEnd {
    ArrayList<iCommand> cmdList;


    @Override
    public void addCommand(iCommand cmd) {
    cmdList.add(cmd);
    }

    @Override
    public void removeCommand(iCommand cmd) {
    cmdList.remove(cmd);
    }

    @Override
    public void displayCommand() {
    for (int i=0;i<cmdList.size()-1;i++){
        System.out.printf("Type %d to %s",i+1,cmdList.get(i).getDesc());

    }
    }
}
