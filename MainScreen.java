package test;
import com.toedter.calendar.JCalendar;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import static test.LoginPage.taskstrg;

public class MainScreen extends JFrame
{
    private JPanel InsideMenuPanel;
    private ToDoList ToDoListPanel;
    private JPanel DashBoardPanel;
    private JPanel CalendarPanelinDashboard;
    private JPanel StudywithmePanel;
    private JPanel SettingsPanel;
    private JPanel CalenderPanelinDashboard;
    public static JPanel CalendarPanel;
    private JPanel panel_ProfileButton;
    private JButton SettingsButton;
    private JButton StudywithmeButton;
    private JButton CalenderButton;
    private JButton ToDOListButton;
    private JButton DashboardButton;
    private JButton ProfileButton;
    private ImageIcon ProfileimageIcon;
    private JCalendar calendar;
    public static JButton slide;
    public static  JPanel MenuPanel;
    private PopupWindow popup;
    private JLabel textLabel;
    private JLabel imageLabel;
    private JLabel welcomeLabel;
    private ImageIcon backgroundImage;
    private String welcomeMessage = "Hii "+ CommonDataofUser.USERNAME + " Welcome Back!";
    private ImageIcon backGround = new ImageIcon("background.png");
    private  Dashboard dash;
    public void init() throws InterruptedException {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        // Basics
        setTitle("Home Page");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(500, 500));
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);


        // Icon
        ImageIcon image = new ImageIcon("icon.png");
        setIconImage((image.getImage()));
        getContentPane().setLayout(null);

        // TODO : Menu Panel Creation
         MenuPanel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs);
                Graphics2D g2d = (Graphics2D) grphcs;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0,
                        new Color(196, 167, 204, 255), 0, getHeight(),
                        new Color(220, 199, 221));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
//TODO:close panel on clicking on the confirmation button
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm= JOptionPane.showConfirmDialog(null,"are you sure you want to exit?","sure",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,new ImageIcon("wp7683467.jpg"));
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        // Setting Menu Panel
        MenuPanel.setLayout(null);
        Dimension frameSize = getSize();
        MenuPanel.setBounds(0, 0, 200, 1080);
        MenuPanel.setLayout(new BorderLayout());
        MenuPanel.setBounds(0, 210, 200, 500);

        // Add content to the menu panel
        InsideMenuPanel = new JPanel(new GridLayout(5, 1, 10, 40));
        InsideMenuPanel.setOpaque(false);
        DashboardButton =   new JButton("Dashboard");
        customizeButton(DashboardButton);
        ToDOListButton = new JButton("To Do list");
        customizeButton(ToDOListButton);
        CalenderButton = new JButton("Calender");
        customizeButton(CalenderButton);
        StudywithmeButton = new JButton("Study with me");
        customizeButton(StudywithmeButton);
        SettingsButton = new JButton("Settings");
        customizeButton(SettingsButton);
        DashboardButton.addActionListener(e -> {
            try {
                switchToPage(DashBoardPanel,  "DashBoardPanel");
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        ToDOListButton.addActionListener(e -> {
            try {
                switchToPage(ToDoListPanel, "ToDoListPanel");
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        CalenderButton.addActionListener(e -> {
            try {
                switchToPage(CalendarPanel, "CalenderPanel");
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        StudywithmeButton.addActionListener(e -> {
            try {
                switchToPage(StudywithmePanel, "StudywithmePanel");
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        SettingsButton.addActionListener(e -> {
            try {
                switchToPage(SettingsPanel, "SettingsPanel");
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });

        InsideMenuPanel.add(DashboardButton);
        InsideMenuPanel.add(ToDOListButton);
        InsideMenuPanel.add(CalenderButton);
        InsideMenuPanel.add(StudywithmeButton);
        InsideMenuPanel.add(SettingsButton);
        InsideMenuPanel.setBorder(BorderFactory.createEmptyBorder(90, 0, 0, 0));
        MenuPanel.add(InsideMenuPanel, BorderLayout.NORTH);

        //TODO: Profile Button
        ProfileButton = new JButton();
        ProfileButton.setBounds(1350, 10, 150, 50); // Set button bounds
        ProfileButton.setBackground(Color.white);
        panel_ProfileButton = new JPanel();
        panel_ProfileButton.setLayout(null);
        panel_ProfileButton.setBounds(1130, 10, 150, 50);
        panel_ProfileButton.setBackground(Color.white);

        // Create an ImageIcon with the image you want to use
        ProfileimageIcon = new ImageIcon("profile.png");
        // Create a JLabel with the image
        imageLabel = new JLabel(ProfileimageIcon);
        imageLabel.setBounds(0, 0, ProfileimageIcon.getIconWidth(), ProfileimageIcon.getIconHeight()); // Set label bounds to fit the image
        panel_ProfileButton.add(imageLabel);
        textLabel = new JLabel("Profile");
        textLabel.setBounds(50, 10, 50, 20); // Set label bounds to fit the image
        panel_ProfileButton.add(textLabel);
        ProfileButton.add(panel_ProfileButton);


        getContentPane().add(ProfileButton);
        ProfileButton.addActionListener(e -> {
            PopupWindow popup = new PopupWindow(this, ProfileButton); // Pass reference to main JFrame
            popup.setBackground(new Color(220, 199, 221));
            popup.showPopup();
            ProfileButton.setEnabled(false);
            dash.setBounds(1100,270,400,300);
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(popup.dialog.isVisible() && !popup.dialog.getBounds().contains(e.getLocationOnScreen())) {
                        popup.dialog.dispose();
                        removeMouseListener(this);
                        ProfileButton.setEnabled(true);
                        dash.setBounds(1100,150,400,300);
                        //DashBoardPanel.repaint();
                    }
                }
            });
        });
        //TODO : Slider
        slide= new JButton();

        slide.setIcon(new ImageIcon("right.png"));
        slide.setBounds(-10, 460, 70, 40);
        slide.setBorderPainted(false);
        slide.setOpaque(false);
        slide.setContentAreaFilled(false);
        add(slide);
        MenuPanel.setVisible(false);
        slide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //MenuPanel.setBounds(-210,0,200,1080);
                if( MenuPanel.isVisible())
                {
                    MenuPanel.setVisible(false);
                    slide.setIcon(new ImageIcon("right.png"));
                    slide.setBounds(-10, 460, 70, 40);
                }else
                {
                    MenuPanel.setVisible(true);
                    slide.setIcon(new ImageIcon("left.png"));
                    slide.setBounds(175,460,70,40);

                }
                //repaint();

            }
        });

        getContentPane().add(MenuPanel);
        // TODO : Welcome
        JPanel welcomePanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics graphcs) {
                super.paintComponent(graphcs);
                // Draw background image
                graphcs.drawImage(backGround.getImage(), 0, 0, 2000, 120, null);

                // Create Graphics2D object
                Graphics2D g2d = (Graphics2D) graphcs;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                // Create gradient paint
                GradientPaint gp = new GradientPaint(0, 0,
                        new Color(108, 49, 115, 207), 0, getHeight(),
                        new Color(201, 156, 214));

                // Set paint to gradient
                g2d.setPaint(gp);

                // Fill the panel with gradient
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        welcomePanel.setLayout(null);
        welcomePanel.setBounds(0, 0, 2000, 120);
        welcomeLabel = new JLabel(welcomeMessage);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBounds(250, 50, 700, 50);
        welcomePanel.add(welcomeLabel);
        add(welcomePanel);

        /// TODO : DashBoardPanel
        backgroundImage = new ImageIcon("background.png");
        DashBoardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
                }
            }
        };
        DashBoardPanel.setLayout(null);
        DashBoardPanel.setBounds(0, 0, 2000, 1080);
        DashBoardPanel.setBackground(new Color(220, 199, 221));
        setBackground(new Color(220, 199, 221));

        taskstrg.update_tasks();
        chartsPanelPieAndRing pr= new chartsPanelPieAndRing();
        barChart b= new barChart();
         dash = new Dashboard();
        dash.setBounds(1100,150,400,300);

        DashBoardPanel.add(dash, BorderLayout.CENTER);
        DashBoardPanel.add(pr, BorderLayout.CENTER);
        DashBoardPanel.add(b, BorderLayout.CENTER);
        add(DashBoardPanel, BorderLayout.NORTH);

        //Todo: To do list panel
        ToDoListPanel = new ToDoList()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
                }
            }
        };
        ToDoListPanel.setBackground(new Color(220, 199, 221));
        add(ToDoListPanel);
        ToDoListPanel.setVisible(false);

        // TODO: Study with me

        StudywithmePanel = new JPanel()
    {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
                }
            }
        };
        StudywithmePanel.setBackground(new Color(220, 199, 221));
        StudywithmePanel.setBounds(-200,120,2000,2000);
        StudyWithMe st = new StudyWithMe();
        StudywithmePanel.add(st);
        add(StudywithmePanel, BorderLayout.NORTH);
        StudywithmePanel.setVisible(false);

// TODO : CalendarPanel
        CalendarPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
                }
            }
        };

        CalendarPanel.setBackground(new Color(220, 199, 221));
        CalendarPanel.setBounds(-130,120,2000,2000);
        TaskCalendar Taskcalendar  = new TaskCalendar(Calendar.getInstance().getTime());
        CalendarPanel.add(Taskcalendar);
        add(CalendarPanel, BorderLayout.NORTH);
        CalendarPanel.setVisible(false);


///

        SettingsPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
                }
            }
        };
        SettingsPanel.setBackground(new Color(220, 199, 221));
        SettingsPanel.setBounds(-130,120,2000,2000);
        SettingsPanel.add(new settings());
        add(SettingsPanel, BorderLayout.NORTH);
        SettingsPanel.setVisible(false);


    // Visibility
        setVisible(true);

    }

    private void customizeButton(JButton button) {
        button.setFocusPainted(false);
        button.setSize(10, 20);
        button.setBackground(new Color(169, 194, 207)); // Light purple background
        button.setForeground(new Color(102, 0, 102)); // Dark purple text color
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        button.setPreferredSize(new Dimension(120, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 102))); // Border color
        button.setBorderPainted(true);
        button.setOpaque(true);
        button.setFocusable(false);
    }
    private void switchToPage(JPanel pagePanel, String panelName) throws InterruptedException {
        DashBoardPanel.setVisible(false);
         SettingsPanel.setVisible(false);
        StudywithmePanel.setVisible(false);
        CalendarPanel.setVisible(false);
        ToDoListPanel.setVisible(false);
        pagePanel.setVisible(true);
        if (panelName != null) {
            switch (panelName) {
                case "DashBoardPanel":
                { welcomeMessage = "Welcome back, " + CommonDataofUser.USERNAME + ", to the Dashboard!";
                    DashBoardPanel.removeAll();
                    taskstrg.update_tasks();
                    chartsPanelPieAndRing pr= new chartsPanelPieAndRing();
                    barChart b= new barChart();
                    DashBoardPanel.add(dash, BorderLayout.CENTER);
                    DashBoardPanel.add(pr, BorderLayout.CENTER);
                    DashBoardPanel.add(b, BorderLayout.CENTER);
                    break;}
                case "SettingsPanel":
                    welcomeMessage = "Welcome back, " + CommonDataofUser.USERNAME + ", to the Settings!";
                    break;
                case "StudywithmePanel":
                    welcomeMessage = "Welcome back, " + CommonDataofUser.USERNAME + ", to the Study with me!";
                    break;
                case "CalendarPanel":
                    welcomeMessage = "Welcome back, " + CommonDataofUser.USERNAME + ", to the Calendar!";
                    break;
                case "ToDoListPanel":
                    welcomeMessage = "Welcome back, " + CommonDataofUser.USERNAME + ", to the ToDoList!";
                    break;
                default:
                    welcomeMessage = "Welcome back, " + CommonDataofUser.USERNAME + "!";
                    break;
            }
        } else {
            welcomeMessage = "Welcome back, " + CommonDataofUser.USERNAME + "!";
        }
        welcomeLabel.setText(welcomeMessage);
        revalidate();
       // repaint();
    }
}
