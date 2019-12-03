package main.java.hospital_obj;

import javax.swing.*;
import java.util.*;
public class Lab {

    public static String BloodTest()
    {
        String blood_type;
        int rand = new Random().nextInt() %4;
        if(rand == 1)
            blood_type = "A";
        else if(rand == 2)
            blood_type = "B";
        else if(rand == 3)
            blood_type = "AB";
        else
            blood_type = "0";
        return blood_type;
    }

}
