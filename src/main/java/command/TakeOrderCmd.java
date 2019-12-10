package main.java.command;

import main.java.Cafeteria;

public class TakeOrderCmd implements iCommand {
    Cafeteria cafe = new Cafeteria();
    @Override
    public String getDesc() {
        return "Take Order";
    }

    @Override
    public void execute() {
    cafe.takeOrder();
    }

}
