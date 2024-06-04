package test;

import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.*;
import java.util.*;

public class LoadingScreen extends JFrame
{

    LoadingScreen() throws FontFormatException {

        setSize(600,400);
        setTitle(" Magic Manager");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        MyLoadingScreenContentPanel content= new MyLoadingScreenContentPanel();
        this.setUndecorated(true);// hide the bar
        this.setLocationRelativeTo(null);//set the screen in the middle
        this.setContentPane(content);
        this.setVisible(true);


    }

}
