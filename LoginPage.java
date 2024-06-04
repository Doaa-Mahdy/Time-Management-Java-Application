package test;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.table.DefaultTableModel;

import static test.Static.customizeButton;
import static test.Task.taskArrayList;


public class LoginPage extends javax.swing.JFrame{
   static tasksStorage taskstrg;
    public static Clip bgclip = null;//the clip that will play the bg music
    public LoginPage() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        initComponents();
        setSize(400, 600);
        setResizable(false);
        setLocationRelativeTo(null);//TODO: edited by samer
        ImageIcon image = new ImageIcon("icon.png");
        setIconImage((image.getImage()));
        ImageIcon backgroundImage = new ImageIcon("background.png");
        getContentPane().setLayout(null);
        // Create a custom JPanel to hold the background image
          JPanel backgroundPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);

                }
            };
        backgroundPanel.setBackground(new Color(192, 127, 193));
        backgroundPanel.setBounds(0, 0, getWidth(), getHeight()); // Set the bounds to cover the entire frame
        getContentPane().add(backgroundPanel); // Add the panel to the content pane
        LoginPage.this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        txtuser = new javax.swing.JTextField();
        usernameLabel = new javax.swing.JLabel();
        loginLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();
        txtpass = new javax.swing.JPasswordField();
        justforspacinglabel = new javax.swing.JLabel();

        AudioInputStream bgMusic = null;
        try {

            bgMusic = AudioSystem.getAudioInputStream(new File("bg_music.wav"));
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            bgclip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            bgclip.open(bgMusic);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(133, 121, 109));

        getContentPane().add(txtuser);
        txtuser.setBounds(80, 200, 218, 30);


        usernameLabel.setText("Username");
        usernameLabel.setForeground(new Color(31, 2, 37));
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        getContentPane().add(usernameLabel);
        usernameLabel.setBounds(80, 178, 100, 16);

        loginLabel.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        loginLabel.setText("Log in");
        loginLabel.setForeground(new Color(31, 2, 37));
        getContentPane().add(loginLabel);
        loginLabel.setBounds(120, 60, 173, 64);

        passwordLabel.setText("Password");
        passwordLabel.setForeground(new Color(31, 2, 37));
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        getContentPane().add(passwordLabel);
        passwordLabel.setBounds(80, 228, 100, 16);

        loginButton.setBackground(new java.awt.Color(0, 153, 255));
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setText("Log in");
        Clip finalClip = bgclip;
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {//log in button

                //TODO: Edited by samer
                String regex= "[a-zA-Z0-9]{1,10}";//include all charcters and numbers only from 1 to 10 characters only

                // TODO add your handling code here:
                String userName = txtuser.getText();
                char[] passwordChars = txtpass.getPassword();
                String password = new String(passwordChars);
                User usr = new User();
                Storage stg = new Storage();
                taskstrg = new tasksStorage();


                // TODO authenticate should take username and password
                boolean isAuthenticated = usr.Authenticate(userName, password);

                if(!userName.matches(regex)|| userName.isEmpty() || password.isEmpty())
                    JOptionPane.showMessageDialog(null,"Hey! enter a valid data!!!! ","you won't trick me!",JOptionPane.PLAIN_MESSAGE,new ImageIcon(System.getProperty("user.dir")+"\\src\\Angry spongebob.jpg"));
                else if(isAuthenticated){
                    ToDoList.model = TaskTableModel.getTabelModel();
                    String Email = stg.getEmail(userName);
                    // Adding
                    CommonDataofUser newData = new CommonDataofUser(Email, userName, stg.getID(userName), stg.getGender(userName));
                    taskArrayList = taskstrg.readAllTasks();
                    taskstrg.update_tasks();
                    MainScreen b = new MainScreen();
                    try {
                        b.init();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if(!finalClip.isRunning())
                    {
                        finalClip.setMicrosecondPosition(0);
                        finalClip.start();

                    }

                    setVisible(false);
                }else {
                    // Authentication failed
                    JOptionPane.showMessageDialog(null,"Wrong Username or password ","you won't trick me!",JOptionPane.PLAIN_MESSAGE,new ImageIcon(System.getProperty("user.dir")+"\\src\\Angry spongebob.jpg"));

                }

            }
        });
        getContentPane().add(loginButton);
        loginButton.setBounds(80, 333, 218, 30);

        registerButton.setForeground(new java.awt.Color(0, 153, 255));
        //TODO:edited by samer
        registerButton.setText("<html>Sign up</html>");//html is added to write full text

        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                //jButton2ActionPerformed(evt);
                RegisterPage reg = new RegisterPage();

                //  reg.setVisible(true);
                reg.show();
                dispose();

            }
        });

        customizeButton(registerButton);
        customizeButton(loginButton);
        getContentPane().add(registerButton);
        registerButton.setBounds(135, 380, 100, 30);

        txtpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpassActionPerformed(evt);
            }
        });
        getContentPane().add(txtpass);
        txtpass.setBounds(80, 250, 220, 30);


        //jLabel5.setText("jLabel5");
        getContentPane().add(justforspacinglabel);
        justforspacinglabel.setBounds(0, 0, 350, 443);

        pack();

        repaint();

        setVisible(true);
    }
    private void txtpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpassActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton loginButton;
    private javax.swing.JButton registerButton;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel justforspacinglabel;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables
}