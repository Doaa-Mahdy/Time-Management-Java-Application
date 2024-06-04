package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static test.CommonDataofUser.cnt;

public class SettingssPanel extends JPanel {
   public SettingssPanel() {
       setBounds(0, 0, 450, 600); // Set bounds for user info panel
       setBackground(new Color(220, 199, 221));
        JPanel userinfoPanel = new JPanel();
        userinfoPanel.setLayout(new BoxLayout(userinfoPanel, BoxLayout.Y_AXIS));
        userinfoPanel.setBounds(900, 20, 450, 600); // Set bounds for user info panel
        userinfoPanel.setBackground(new Color(220, 199, 221));

        // if condition hageb mn Common Data el gender w based on it h assign fl variable
       File photoFile = new File(System.getProperty("user.dir")+File.separator+"ProfilePhotos" +File.separator, CommonDataofUser.USERNAME+ cnt +".png");
       String image_based_on_gender = CommonDataofUser.GENDER + ".png";
       if (photoFile.exists() && CommonDataofUser.profile) {
           image_based_on_gender = System.getProperty("user.dir")+File.separator+"ProfilePhotos" +File.separator+ CommonDataofUser.USERNAME+cnt + ".png";
       }
        ImageIcon gender_image_Icon = new ImageIcon(image_based_on_gender);
        JLabel imageLabel = new JLabel(gender_image_Icon);
        imageLabel.setPreferredSize(new Dimension(gender_image_Icon.getIconWidth(),gender_image_Icon.getIconHeight()));

        //TODO NGEB EL DATA
        JLabel IDLabel = new JLabel("ID: " + CommonDataofUser.USER_ID);
        JLabel nameLabel = new JLabel("User Name: " + CommonDataofUser.USERNAME);
        JLabel mailLabel = new JLabel("Email: " + CommonDataofUser.EMAIL);
        JLabel finishedLabel = new JLabel("Finished Tasks: ");
        JLabel inProcessLabel = new JLabel("Unfinished Tasks: ");
        JLabel overdueLabel = new JLabel("Overdue Tasks: ");

        //TODO: add meme here
        JButton DeleteUser = new JButton("Delete Account");
        DeleteUser.setBackground(new Color(220, 199, 221));
        DeleteUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JPasswordField passwordField = new JPasswordField();
                Object[] message = {"Enter your password:", passwordField};
                int option = JOptionPane.showConfirmDialog(null, message, "Confirm Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon("smile.png"));

                // Check if the user confirmed with OK button
                if (option == JOptionPane.OK_OPTION) {
                    char[] passwordChars = passwordField.getPassword();
                    String password = new String(passwordChars);

                    // Validate password before deleting
                    if (User.Authenticate(CommonDataofUser.USERNAME, password)) {
                        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the user?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, new ImageIcon("wp7683467.jpg"));
                        if (response == JOptionPane.YES_OPTION) {
                            File file = new File(CommonDataofUser.USERNAME + ".csv");
                            if (file.delete()) {
                                JOptionPane.showMessageDialog(null, "User deleted successfully.", "success", JOptionPane.PLAIN_MESSAGE, new ImageIcon("ablaKamel.png"));
                                System.out.println("the neede hwa dh: " + CommonDataofUser.USERNAME + "     " + User.HashPass(password));
                               User user = new User();
                                user.deleteUser(CommonDataofUser.USERNAME, password);
                                LoginPage login = new LoginPage();
                                login.setVisible(true);
                                JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(SettingssPanel.this);
                                parent.dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "Failed to delete the user.", "failed", JOptionPane.PLAIN_MESSAGE, new ImageIcon("sideeye.png"));
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Hey! enter the correct password!!!! ", "Are you even " + CommonDataofUser.USERNAME + " ?!", JOptionPane.PLAIN_MESSAGE, new ImageIcon("cateye.png"));
                    }
                }
            }
        });

        // Optionally, set the labels' alignment along the X axis
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        IDLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        finishedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        inProcessLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        overdueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        DeleteUser.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add some vertical spacing between labels
        userinfoPanel.add(Box.createRigidArea(new Dimension(10, 50)));
        userinfoPanel.add(imageLabel);
        userinfoPanel.add(Box.createRigidArea(new Dimension(10, 30)));
        userinfoPanel.add(IDLabel);
        userinfoPanel.add(Box.createRigidArea(new Dimension(10, 40)));
        userinfoPanel.add(nameLabel);
        userinfoPanel.add(Box.createRigidArea(new Dimension(10, 40)));
        userinfoPanel.add(mailLabel);
        userinfoPanel.add(Box.createRigidArea(new Dimension(10, 40)));
        userinfoPanel.add(finishedLabel);
        userinfoPanel.add(Box.createRigidArea(new Dimension(10, 40)));
        userinfoPanel.add(inProcessLabel);
        userinfoPanel.add(Box.createRigidArea(new Dimension(10, 40)));
        userinfoPanel.add(overdueLabel);
        userinfoPanel.add(Box.createRigidArea(new Dimension(10, 40)));
        userinfoPanel.add(DeleteUser);
        userinfoPanel.setVisible(true);
        add(userinfoPanel);
    }
}
