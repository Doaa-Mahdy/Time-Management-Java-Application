package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PopupWindow extends JFrame{
    public JDialog dialog;
    private JButton triggerButton;
    public PopupWindow(JFrame parent, JButton triggerButton) {
        this.triggerButton = triggerButton;
        setLayout(null);
        setSize(300,500);
        dialog = new JDialog(parent, "Profile Details", false);
        dialog.setLayout(new BorderLayout());
        dialog.setUndecorated(true);
        dialog.setSize(400,500);
        JPanel panel = new JPanel(new GridLayout(4, 1, 10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(0, 0, 0, 0));
        // Create labels
        JLabel emailLabel = new JLabel(CommonDataofUser.EMAIL);
        emailLabel.setForeground(new Color(31, 2, 37));
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel greetingLabel = new JLabel("Hi, "+ CommonDataofUser.USERNAME + "!");
        greetingLabel.setForeground(new Color(31, 2, 37));
        greetingLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(e -> {
           // JOptionPane.showMessageDialog(dialog, "Sign Out clicked");
            LoginPage l = new LoginPage();
            l.setVisible(true);
            parent.dispose();
        });

        signOutButton.setBackground(new Color(247, 239, 229));
        signOutButton.setForeground(new Color(31, 2, 37));
        signOutButton.setFocusPainted(false);

        JButton switchAccountButton = new JButton("Switch Account");
        switchAccountButton.addActionListener(e -> {
           // JOptionPane.showMessageDialog(dialog, "Switch Account clicked");
            LoginPage l = new LoginPage();
            l.setVisible(true);
            parent.dispose();
        });
        switchAccountButton.setBackground(new Color(247, 239, 229));
        switchAccountButton.setForeground(new Color(31, 2, 37));
        switchAccountButton.setFocusPainted(false);

        // Add labels and buttons to the panel
        panel.add(greetingLabel);
        panel.add(emailLabel);
        panel.add(signOutButton);
        panel.add(switchAccountButton);
        panel.setBackground(new Color(220, 199, 221));
        dialog.add(panel, BorderLayout.CENTER);
        dialog.pack();

        // Calculate position relative to trigger button
        Point buttonLocation = triggerButton.getLocationOnScreen();
        dialog.setLocation(buttonLocation.x , buttonLocation.y + triggerButton.getHeight());
    }

    public void showPopup() {
        dialog.setVisible(true);
    }
}
