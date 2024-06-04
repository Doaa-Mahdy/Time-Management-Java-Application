package test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static java.lang.Math.min;
import static test.CommonDataofUser.cnt;
import static test.Static.*;

public class settings extends JPanel {
    private String currentUserId;
    private final JDialog fixedPositionDialog;
    User user = new User();
    Storage storage = new Storage();
    private  JLabel imageLabel;
    private JPanel userinfoPanel;

    public settings() {
        this.setLayout(null); // Use null layout for custom positioning
        this.setPreferredSize(new Dimension(1500, 1000)); // Adjusted to accommodate user info panel
        this.setOpaque(false);

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBounds(230, 20, 600, 600); // Set bounds for main panel
        mainPanel.setBackground(new Color(220, 199, 221));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 15));
        mainPanel.add(Box.createRigidArea(new Dimension(10, 50)));


        // Change Password Section
        JButton changePasswordButton = new JButton("Change Password");
        JLabel currentPasswordLabel = new JLabel("Enter your current password");
        JLabel newPasswordLabel = new JLabel("Enter your new password");
        JLabel confirmNewPasswordLabel = new JLabel("Confirm your new password");
        final JPasswordField currPasswordField = new JPasswordField();
        final JPasswordField newPasswordField = new JPasswordField();
        final JPasswordField confirmNewPasswordField = new JPasswordField();
        Dimension textFieldSize = new Dimension(200, 25); // Set a consistent size for text fields
        currPasswordField.setPreferredSize(textFieldSize);
        newPasswordField.setPreferredSize(textFieldSize);
        confirmNewPasswordField.setPreferredSize(textFieldSize);
        JPanel panelPasswordChange = new JPanel(new GridLayout(3, 2, 10, 20));
        panelPasswordChange.setPreferredSize(new Dimension(300, 100));
        panelPasswordChange.setBackground(new Color(220, 199, 221));
        customizeLabel(currentPasswordLabel);
        customizePasswordField(currPasswordField);
        customizeLabel(newPasswordLabel);
        customizePasswordField(newPasswordField);
        customizeLabel(confirmNewPasswordLabel);
        customizePasswordField(confirmNewPasswordField);
        customizeButton(changePasswordButton);
        panelPasswordChange.add(currentPasswordLabel);
        panelPasswordChange.add(currPasswordField);
        panelPasswordChange.add(newPasswordLabel);
        panelPasswordChange.add(newPasswordField);
        panelPasswordChange.add(confirmNewPasswordLabel);
        panelPasswordChange.add(confirmNewPasswordField);
        mainPanel.add(panelPasswordChange);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        changePasswordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(changePasswordButton);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 40)));


        // Add Second Email Section
        JButton addSecondEmailButton = new JButton("Add Second Email");
        JLabel secondMailLabel = new JLabel("Enter your other Mail");
        JTextField secondMailField = new JTextField();
        secondMailField.setPreferredSize(textFieldSize); // Set consistent size for the text field
        JPanel panelAddSecondMail = new JPanel(new GridLayout(1, 2, 10, 20));
        panelAddSecondMail.setPreferredSize(new Dimension(500, 20));
        panelAddSecondMail.setBackground(new Color(220, 199, 221));
        panelAddSecondMail.add(secondMailLabel);
        panelAddSecondMail.add(secondMailField);
        customizeLabel(secondMailLabel);
        Static.customizeTextField(secondMailField);
        customizeButton(addSecondEmailButton);
        mainPanel.add(panelAddSecondMail);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        addSecondEmailButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(addSecondEmailButton);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 40)));


        // Set Gender Section
        JButton setGenderButton = new JButton("Set Gender");
        JRadioButton maleRadioButton = new JRadioButton("Male");
        JRadioButton femaleRadioButton = new JRadioButton("Female");
        ButtonGroup genderButtonGroup = new ButtonGroup();
        genderButtonGroup.add(maleRadioButton);
        genderButtonGroup.add(femaleRadioButton);
        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new GridLayout(1, 2, 10, 20));
        genderPanel.setPreferredSize(new Dimension(500, 40));
        JLabel genderLabel = new JLabel("Select gender:");
        customizeLabel(genderLabel);
        genderPanel.add(genderLabel);
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        genderPanel.setBackground(new Color(220, 199, 221));
        customizeButton(setGenderButton);
        Static.customizeRadioButton(maleRadioButton);
        Static.customizeRadioButton(femaleRadioButton);

        maleRadioButton.setBackground(new Color(220, 199, 221));
        femaleRadioButton.setBackground(new Color(220, 199, 221));
        if (CommonDataofUser.GENDER.equals("Female")) femaleRadioButton.setSelected(true);
        else maleRadioButton.setSelected(true);
        mainPanel.add(genderPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 1)));
        setGenderButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(setGenderButton);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 40)));

        // Update Phone Section
        /*JButton updatePhoneButton = new JButton("Update Phone");
        JLabel secondphoneLabel = new JLabel("Enter your new number");
        JTextField secondPhoneField = new JTextField();
        secondPhoneField.setPreferredSize(textFieldSize); // Set consistent size for the text field

        customizeLabel(secondphoneLabel);
        Static.customizeTextField(secondPhoneField);

        customizeButton(updatePhoneButton);

        JPanel PanelUpdatePhone = new JPanel(new GridLayout(1, 2, 10, 20));
        PanelUpdatePhone.setPreferredSize(new Dimension(400, 20));
        PanelUpdatePhone.setBackground(new Color(220, 199, 221));
        PanelUpdatePhone.add(secondphoneLabel);
        PanelUpdatePhone.add(secondPhoneField);

        mainPanel.add(PanelUpdatePhone);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 5)));
        updatePhoneButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(updatePhoneButton);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 30)));

         */

        // Upload your photo
        JLabel imageLabel = new JLabel("Change Your Profile Photo");
        customizeLabel(imageLabel);
        imageLabel.setPreferredSize(new Dimension(200, 200));
        JButton uploadButton = new JButton("Upload Image");
        customizeButton(uploadButton);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(imageLabel);
        mainPanel.add(uploadButton);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 40)));

        this.add(mainPanel); // Add mainPanel to the custom position

        // Dialog settings
        this.fixedPositionDialog = new JDialog();
        this.fixedPositionDialog.setUndecorated(true);
        this.fixedPositionDialog.setSize(0, 0);
        this.fixedPositionDialog.setLocation(100, 100);

        // ------------------------- user info -------------------------------
        userinfoPanel = new JPanel();
        userinfoPanel.setLayout(null);
        userinfoPanel.setBounds(900, 20, 450, 600); // Set bounds for user info panel
        userinfoPanel.add(new SettingssPanel());
        userinfoPanel.setVisible(true);
        add(userinfoPanel); // Add userinfoPanel to the custom position


        // ------------------------- user info -------------------------------

        // Action Listeners
        changePasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                char[] currentPass = currPasswordField.getPassword();
                String currentPassword = new String(currentPass);

                char[] newPass = newPasswordField.getPassword();
                String newPassword = new String(newPass);

                char[] confPass = confirmNewPasswordField.getPassword();
                String confirmNewPassword = new String(confPass);

                if (storage.searchByPass(currentPassword)) { //yt2kd en el current pass mwgod
                    if (newPassword.equals(confirmNewPassword)) {
                        if (newPassword.length() < 8) {
                            JOptionPane.showMessageDialog(null, "you are kidding with me right??\nyour password is very short ", "Are you serious", JOptionPane.ERROR_MESSAGE, new ImageIcon(System.getProperty("user.dir") + "\\kiddin.jpg"));
                        } else {
                            JOptionPane.showMessageDialog(settings.this.fixedPositionDialog, "Password changed successfully!");
                            user.ChangePass(CommonDataofUser.USERNAME, currentPassword, newPassword);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "FOCUS!\nThe confirmed password is different from the new password ", "Are you serious", JOptionPane.ERROR_MESSAGE, new ImageIcon(System.getProperty("user.dir") + "\\kiddin.jpg"));

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "FOCUS!\nThe current password is incorrect", "Are you serious", JOptionPane.ERROR_MESSAGE, new ImageIcon(System.getProperty("user.dir") + "\\kiddin.jpg"));

                }
            }
        });

        addSecondEmailButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newEmail = secondMailField.getText();
                if (newEmail != null && !newEmail.isEmpty()) {
                    // EDIT LATER
                    boolean emailAdded =  true;
                    if (emailAdded) {
                        JOptionPane.showMessageDialog(null, "Second email added successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to add second email. Please try again.");
                    }
                }
            }
        });

        setGenderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedGender = null;
                if (maleRadioButton.isSelected()) {
                    selectedGender = "Male";
                } else if (femaleRadioButton.isSelected()) {
                    selectedGender = "Female";
                }

                if (selectedGender != null) {
                    JOptionPane.showMessageDialog(null, "Gender set to " + selectedGender);
                    storage.FileUpdateGender(CommonDataofUser.USERNAME, selectedGender);
                    CommonDataofUser.GENDER = selectedGender;
                    userinfoPanel.removeAll();
                    userinfoPanel.add(new SettingssPanel());
                    userinfoPanel.setVisible(true);
                    userinfoPanel.repaint();
                    userinfoPanel.revalidate();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a gender");
                }
            }
        });

       /* updatePhoneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newPhoneNumber = secondPhoneField.getText();
                if (newPhoneNumber != null && !newPhoneNumber.isEmpty()) {
                    backend.setCurrentUserId(settings.this.currentUserId);
                    boolean updated = backend.updatePhoneNumber(newPhoneNumber);
                    if (updated) {
                        JOptionPane.showMessageDialog(null, "Phone number updated to: " + newPhoneNumber);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update phone number. Please try again");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No phone number entered or operation canceled.");
                }
            }
        });

        */
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Upload New", "Use System Default", "Cancel"};
                int choice = JOptionPane.showOptionDialog(null, "Select an option:",
                        "Change Profile Photo", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                if (choice == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "System default photo selected.");
                    CommonDataofUser.profile  = false;
                    userinfoPanel.removeAll();
                    userinfoPanel.add(new SettingssPanel());
                    userinfoPanel.setVisible(true);
                    userinfoPanel.repaint();
                    userinfoPanel.revalidate();
                }
                else if (choice == JOptionPane.YES_OPTION){
                 CommonDataofUser.profile = true;
                JFileChooser fileChooser = new JFileChooser();
                // Set file chooser to allow only image files
                fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                    public boolean accept(File f) {
                        return f.getName().toLowerCase().endsWith(".jpg") || f.getName().toLowerCase().endsWith(".jpeg") ||
                                f.getName().toLowerCase().endsWith(".png") || f.isDirectory();
                    }

                    public String getDescription() {
                        return "Image files (*.jpg, *.jpeg, *.png)";
                    }
                });

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        // Specify the directory where you want to save the image next to src
                        BufferedImage originalImage = ImageIO.read(selectedFile);
                        BufferedImage resizedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
                        resizedImage.createGraphics().drawImage(originalImage.getScaledInstance(min(originalImage.getWidth(),200), min(originalImage.getHeight(),200), java.awt.Image.SCALE_SMOOTH), 0, 0, null);
                        cnt++;
                        String imagePath = System.getProperty("user.dir") + File.separator+ "ProfilePhotos" + File.separator + CommonDataofUser.USERNAME+ cnt + ".png";
                        File destinationFile = new File(imagePath);
                        ImageIO.write(resizedImage, "png", destinationFile);
                        userinfoPanel.removeAll();
                        userinfoPanel.add(new SettingssPanel());
                        userinfoPanel.setVisible(true);
                        userinfoPanel.repaint();
                        userinfoPanel.revalidate();
                        JOptionPane.showMessageDialog(null, "Profile Photo Updated successfully");

                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Failed to update profile photo. Please try again");

                    }
                }
                }
            }
        });
    }
}
