package main.java;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

//import javax.xml.ws.AsyncHandler;
import javax.imageio.ImageIO;
import javax.swing.*;
class EmployeeImage {
    /**{
     * 创建并显示GUI。出于线程安全的考虑，
     * 这个方法在事件调用线程中调用。
     */
    public static void PatientImageFrame(String Img_path, String patient_name, String JobTitle) {
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Patient Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(300, 200);
        JPanel jp = new JPanel();

        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(Img_path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        jp.setBounds(300, 100, 800, 400);
        ImageIcon img = new ImageIcon(myPicture);
        JLabel patient_image= new JLabel(img);
        JLabel name = new JLabel("Patient Name:" + patient_name);
        JLabel Job = new JLabel("Job Titile: " + JobTitle);
        JButton btn1=new JButton("CLOSE");
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
            }
        });
        Dimension nameSize = new Dimension(200, 200);
        Dimension pageSize = new Dimension(400, 400);
        name.setPreferredSize(nameSize);
        patient_image.setPreferredSize(pageSize);

        jp.add(patient_image);
        jp.add(name);
        jp.add(Job);
        jp.add(btn1);


        frame.add(jp);

        // 显示窗口
        frame.pack();
        frame.setVisible(true);
    }


}