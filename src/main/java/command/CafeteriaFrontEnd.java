package main.java.command;

import java.util.ArrayList;

public class CafeteriaFrontEnd implements iFontEnd {
    ArrayList<iCommand>cmdList = new ArrayList<iCommand>();

   public CafeteriaFrontEnd(ArrayList<iCommand>cmdList){
        this.cmdList = cmdList;
    }
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
        for (int i=0;i<cmdList.size();i++){
            System.out.printf("Type %d to %s \n",i+1,cmdList.get(i).getDesc());

        }
    }
    }


