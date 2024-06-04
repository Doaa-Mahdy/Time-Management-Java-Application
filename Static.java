package test;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

import static test.MainScreen.*;

public class Static {
    public static void customizeButton(JButton button) {
        button.setBackground(new Color(102, 0, 102)); // Purple background
        button.setForeground(Color.white); // White text color
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(120, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFocusable(false);
    }
    public static void customizeLabel(JLabel label) {
        label.setForeground(new Color(102, 0, 102)); // Purple text color
        label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        label.setFont(new Font("Arial", Font.BOLD, 12));
    }
    public static void customizePasswordField(JPasswordField PasswordField) {
        PasswordField.setBackground(new Color(250, 232, 251));
        PasswordField.setForeground(new Color(102, 0, 102)); // Purple text color
        PasswordField.setSize(new Dimension(50,10));
        PasswordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(102, 0, 102)), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        PasswordField.setFont(new Font("Arial", Font.BOLD, 12));
    }
    public static void customizeRadioButton(JRadioButton radioButton) {
        radioButton.setFont(new Font("Arial", Font.PLAIN, 12));
        radioButton.setBackground(new Color(102, 0, 102));
        radioButton.setForeground(new Color(128,0,128));
}

    public static void customizeDateChooser(JDateChooser dateChooser) {
        dateChooser.setDateFormatString("dd/MM/yyyy"); // Set date format
        dateChooser.setPreferredSize(new Dimension(100, 100));
    }

    public static void customizeFormattedTextField(JFormattedTextField textField) {
        textField.setPreferredSize(new Dimension(50, 25));
        textField.setFont(new Font("Arial", Font.PLAIN, 12));
        textField.setBackground(new Color(240, 240, 240)); // Light gray background
        textField.setForeground(new Color(102, 0, 102)); // Purple text color
        textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(102, 0, 102)), BorderFactory.createEmptyBorder(0, 10, 0, 0)));

    }

    public static void customizeTextField(JTextField textField) {
        textField.setPreferredSize(new Dimension(50, 25));
        textField.setFont(new Font("Arial", Font.PLAIN, 12));
        textField.setBackground(new Color(226, 213, 228)); // Light gray background
        textField.setForeground(new Color(102, 0, 102)); // Purple text color
        textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(102, 0, 102)), BorderFactory.createEmptyBorder(0, 10, 0, 0)));

    }
    public static void customizeCheckbox(JCheckBox checkBox) {
        checkBox.setForeground(new Color(102, 0, 102)); // Purple text color
        checkBox.setFont(new Font("Arial", Font.PLAIN, 12));
        checkBox.setBackground(null); // Transparent background
        checkBox.setFocusPainted(false);
    }
    public static void handleCalendar(Date endDate)
    {
        TaskCalendar Taskcalendar  = new TaskCalendar(endDate);
        CalendarPanel.removeAll();
        MenuPanel.setVisible(false);
        slide.setIcon(new ImageIcon("right.png"));
        slide.setBounds(-10, 460, 70, 40);
        CalendarPanel.add(Taskcalendar);

    }
}
