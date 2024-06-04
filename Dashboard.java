package test;

import com.toedter.calendar.JCalendar;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Calendar;
import java.io.*;
public class Dashboard extends JPanel{

    Dashboard() throws InterruptedException {


        setBounds(300,150,2000,2000);
        JPanel dash = new JPanel(new BorderLayout());
        dash.setLayout(new BorderLayout());
        // Create JCalendar component
       JCalendar calendar = new JCalendar();
        // Set today's date as the selected date by default
        calendar.setDate(Calendar.getInstance().getTime());
        // Customize JCalendar appearance
        calendar.setWeekOfYearVisible(false);
        calendar.setDecorationBackgroundColor(Color.WHITE);
        calendar.setSundayForeground(new Color(255, 102, 102)); // Light red color
        calendar.setTodayButtonVisible(true);
        calendar.setSize(350,400);
        JPanel CalendarPanelinDashboard = new JPanel(new BorderLayout()) {
            // Override paintComponent to create a gradient background
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                int width = getWidth();
                int height = getHeight();
                Color color1 = new Color(66, 11, 76);
                Color color2 = new Color(181, 169, 154);
                GradientPaint gradient = new GradientPaint(0, 0, color1, width, height, color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, width, height);
                g2d.dispose();
            }
        };

        CalendarPanelinDashboard.setBorder(new EmptyBorder(10, 10, 10, 10));
        CalendarPanelinDashboard.setBounds(1100,150,400,400);
        CalendarPanelinDashboard.add(calendar, BorderLayout.WEST);
        CalendarPanelinDashboard.setVisible(true);
        chartsPanelPieAndRing pr= new chartsPanelPieAndRing();
        dash.add(CalendarPanelinDashboard, BorderLayout.CENTER);
        dash.setVisible(true);
        add(dash);
        setOpaque(false);
    }

}
