package test;
import com.toedter.calendar.JCalendar;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TaskCalendar extends JPanel {

    JPanel CalenderPanelTasks;
    JCalendar calendar;

    public TaskCalendar(Date Dt) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setBounds(200, 250, 1200, 600);
        // Create JCalendar instance
        calendar = new JCalendar();
        // Set today's date as the selected date by default
        calendar.setDate(Dt);
        // Customize JCalendar appearance
        calendar.setWeekOfYearVisible(false);
        calendar.setDecorationBackgroundColor(Color.WHITE);
        calendar.setSundayForeground(new Color(255, 102, 102)); // Light red color
        calendar.setTodayButtonVisible(true);
        calendar.setPreferredSize(new Dimension(700,700));
        // Create CalenderPanelTasks with BorderLayout
        CalenderPanelTasks = new JPanel(new BorderLayout()) {
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
        CalenderPanelTasks.setPreferredSize(new Dimension(1200, 600));
        // Set border and add calendar to CalenderPanelTasks
        CalenderPanelTasks.setBorder(new EmptyBorder(20, 20, 20, 20));
        CalenderPanelTasks.add(calendar, BorderLayout.WEST);

        // Add CalenderPanelTasks to TaskCalendar
        TodayTasks todayTasksPanel = new TodayTasks( Dt);
        todayTasksPanel.setPreferredSize(new Dimension(426, 700));
        CalenderPanelTasks.add(todayTasksPanel, BorderLayout.EAST);
        CalenderPanelTasks.revalidate(); // Re-layout the panel
        CalenderPanelTasks.repaint(); // Repaint the panel

        // Add action listener to the JCalendar
        calendar.getDayChooser().addPropertyChangeListener("day", evt -> {
            Date selectedDate = calendar.getDate();
            // Remove existing TodayTasks panel if any
            if (CalenderPanelTasks.getComponentCount() > 1) {
                CalenderPanelTasks.remove(1);
            }
            // Create and add new TodayTasks panel for the selected date
            TodayTasks todayTasksPanel2 = new TodayTasks(selectedDate);
            todayTasksPanel2.setPreferredSize(new Dimension(426, 700));
            CalenderPanelTasks.add(todayTasksPanel2, BorderLayout.EAST);
            CalenderPanelTasks.revalidate(); // Re-layout the panel
            CalenderPanelTasks.repaint(); // Repaint the panel
            });
        add(CalenderPanelTasks);

    }

}
