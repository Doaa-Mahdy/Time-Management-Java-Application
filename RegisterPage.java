package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static test.Static.customizeButton;

public class RegisterPage extends javax.swing.JFrame {


    public RegisterPage() {
        initComponents();
        setSize(400, 600);
        setResizable(false);
        setLocationRelativeTo(null);//TODO:Edited by samer(center in the middle
        setDefaultCloseOperation(EXIT_ON_CLOSE);
            // Load the background image
            ImageIcon backgroundImage = new ImageIcon("background.png");
            // Create a custom JPanel to hold the background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);

            }
        };
        backgroundPanel.setBackground(new Color(192, 127, 193));
          backgroundPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
         backgroundPanel.setBounds(0, 0, getWidth(), getHeight()); // Set the bounds to cover the entire panel
            add(backgroundPanel); // Add the panel to the RegisterPage

        RegisterPage.this.setVisible(true);
    }

    public void register() {
        txtuser.grabFocus();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EmailLabel = new javax.swing.JLabel();
        EmailField = new javax.swing.JTextField();
        RegisterLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        PasswordLabel = new javax.swing.JLabel();
        SignUpBottom = new javax.swing.JButton();
        BackTologinBottom = new javax.swing.JButton();
        ConfirmPasswordLabel = new javax.swing.JLabel();
        confirmPasswordField = new javax.swing.JPasswordField();
        UserNameField = new javax.swing.JTextField();
        UserNameLabel = new javax.swing.JLabel();

        //setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));
        setLayout(null);
        ImageIcon image = new ImageIcon("icon.png");
        setIconImage((image.getImage()));

        EmailLabel.setText("Email");
        add(EmailLabel);
        EmailLabel.setBounds(80, 124, 60, 16);

        EmailField.setCaretColor(new java.awt.Color(0, 102, 204));
        EmailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        add(EmailField);
        EmailField.setBounds(80, 146, 227, 30);
        RegisterLabel.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        RegisterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RegisterLabel.setText("Register");
        add(RegisterLabel);
        RegisterLabel.setBounds(90, 18, 186, 64);

        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        add(passwordField);
        passwordField.setBounds(80, 246, 227, 30);

        PasswordLabel.setText("Password");
        add(PasswordLabel);
        PasswordLabel.setBounds(80, 224, 66, 16);

        SignUpBottom.setBackground(new java.awt.Color(0, 153, 255));
        SignUpBottom.setForeground(new java.awt.Color(255, 255, 255));
        SignUpBottom.setText("Sign Up");
        SignUpBottom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //TODO: edited by samer
                User usr = new User();
                String Email_regex="[a-zA-Z0-9@.]{5,20}";
                String Name_regex="[a-zA-Z0-9]{1,10}";
                if(!UserNameField.getText().matches(Name_regex)  || UserNameField.getText().isEmpty())

                    JOptionPane.showMessageDialog(null,"you are kidding with me right??\nyour name is invalid.","Are you serious",JOptionPane.ERROR_MESSAGE,new ImageIcon(System.getProperty("user.dir")+"\\kiddin.jpg"));

                else if( new String(passwordField.getPassword()).length()<8||new String(confirmPasswordField.getPassword()).length()<8) {
                    JOptionPane.showMessageDialog(null,"you are kidding with me right??\nyour password is very short ","Are you serious",JOptionPane.ERROR_MESSAGE,new ImageIcon(System.getProperty("user.dir")+"\\kiddin.jpg"));

                }
                else if(EmailField.getText().isEmpty() || !EmailField.getText().contains("@") || !EmailField.getText().contains(".")||!EmailField.getText().matches(Email_regex)){
                    JOptionPane.showMessageDialog(null, "you are kidding with me right??\nyou don't even know how to write an email?", "Are you serious", JOptionPane.ERROR_MESSAGE, new ImageIcon(System.getProperty("user.dir") + "\\kiddin.jpg"));
                }
                else {
                    System.out.println(System.getProperty("user.dir"));
                    String name = UserNameField.getText();
                    String email =  EmailField.getText();
                    char[] pass = passwordField.getPassword();
                    String pas = new String(pass);
                    char[] confPass = confirmPasswordField.getPassword();
                    String conpas = new String(confPass);
                    usr.AddUser(name, pas, conpas,email);
                    File file = new File(name + ".csv");
                    try {
                        // Create the file
                        if (file.createNewFile()) {
                            System.out.println("File created: " + file.getName());
                            FileWriter writer = new FileWriter(file);
                            String content = "Header,ID,TaskName,Status,startTime,endTime,startDate,endDate";
                            writer.write(content);
                            writer.close();
                        } else {
                            System.out.println("File already exists.");
                        }
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    LoginPage log = new LoginPage();
                    log.setVisible(true);
                    dispose();
                }

                }
                //Storage strg = new Storage();
                //strg.readUserData();
        });
        add(SignUpBottom);
        SignUpBottom.setBounds(80, 336, 227, 30);
        //TODO: edited by samer
        BackTologinBottom.setText("<html>Back to Login</html>");//html tags included to write full text
        BackTologinBottom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                LoginPage log = new LoginPage();
                log.show();
                dispose();
            }
        });
        add(BackTologinBottom);
        BackTologinBottom.setBounds(125, 377, 130, 30);

        ConfirmPasswordLabel.setText("<html>Confirm Password</html>");
        add(ConfirmPasswordLabel);
        ConfirmPasswordLabel.setBounds(80, 274, 150, 16);

        confirmPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField2ActionPerformed(evt);
            }
        });
        add(confirmPasswordField);
        confirmPasswordField.setBounds(80, 296, 227, 30);

        UserNameField.setCaretColor(new java.awt.Color(0, 102, 204));
        UserNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        customizeButton(BackTologinBottom);
        customizeButton(SignUpBottom);
        add(UserNameField);
        UserNameField.setBounds(80, 196, 227, 30);

        UserNameLabel.setText("User Name");
        add(UserNameLabel);
        UserNameLabel.setBounds(80, 174, 76, 16);
        setVisible(true);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPasswordField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SignUpBottom;
    private javax.swing.JButton BackTologinBottom;
    private javax.swing.JLabel EmailLabel;
    private javax.swing.JLabel RegisterLabel;
    private javax.swing.JLabel PasswordLabel;
    private javax.swing.JLabel ConfirmPasswordLabel;
    private javax.swing.JLabel UserNameLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JPasswordField confirmPasswordField;
    private javax.swing.JTextField EmailField;
    private javax.swing.JTextField UserNameField;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JTextField txtuser;
    void AddBackPage(ActionListener actionListener) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}