package test;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Date;

import static test.Static.customizeButton;
import static test.Static.handleCalendar;
import static test.Static.customizeCheckbox;
import static test.Static.customizeLabel;
import static test.Static.customizeDateChooser;
import static test.Static.customizeFormattedTextField;
import static test.Static.customizeTextField;
import static test.LoginPage.taskstrg;
import static test.Task.taskArrayList;
import static test.Task.taskinthelist;
import static test.ToDoList.model;
import static test.tasksStorage.deleteTaskByID;

// TaskPopup bta5od dayman Task y2ma t3ml constructor w tdeholha 3shan t3ml add, lw edit f pass el task kolo 3ady htshtgh w t3dloh

public class TaskPopup extends JFrame {
    private JDateChooser startDateChooser, endDateChooser;
    private JFormattedTextField startTimeField, endTimeField;
    private JTextField taskNameField;
    private static Point mouseDownCompCoords;
    private JCheckBox taskStatusCheckbox;
    private DefaultTableModel modelintaskpopup;
    private int rowIndex;

    // Constructor

    public TaskPopup(Task T, DefaultTableModel modell, int rowIndex ) {
        this.modelintaskpopup = modell;
        this.rowIndex = rowIndex;
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setTitle("Task Details");
        setSize(550, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 13))
        {
            @Override
            protected void paintComponent(Graphics graphcs) {
            super.paintComponent(graphcs);
            // Create Graphics2D object
            Graphics2D g2d = (Graphics2D) graphcs;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            // Create gradient paint
            GradientPaint gp = new GradientPaint(0, 0,
                    new Color(146, 89, 154, 207), 0, getHeight(),
                    new Color(201, 156, 214));

            // Set paint to gradient
            g2d.setPaint(gp);

            // Fill the panel with gradient
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
        };
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setUndecorated(true);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseDownCompCoords = e.getPoint();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
            }
        });

        JLabel taskname = new JLabel("Task Name:");
        customizeLabel(taskname);
        panel.add(taskname);
        taskNameField = new JTextField(T.getTaskName());
        customizeTextField(taskNameField);
        panel.add(taskNameField);
      //  setOpacity(0.9f);

        JLabel taskstatus= new JLabel("Task Status:");
        customizeLabel(taskstatus);
        panel.add(taskstatus);
        taskStatusCheckbox = new JCheckBox("Done");
        if(T.getTaskStatus()==true)
            taskStatusCheckbox.setSelected(true);
        customizeCheckbox(taskStatusCheckbox);
        panel.add(taskStatusCheckbox);
        MaskFormatter timeFormatter = null;
        try {
            timeFormatter = new MaskFormatter("##:##");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JLabel startDateLabel = new JLabel("Start Date:");
        startDateChooser = new JDateChooser();
        startDateChooser.setDate(T.getStart_Date());
        JLabel startTimeLabel = new JLabel("Start Time:");
        startTimeField = new JFormattedTextField(timeFormatter);
        startTimeField.setValue(T.getStart_Time());
        customizeFormattedTextField(startTimeField);
        customizeLabel(startTimeLabel);
        customizeDateChooser(startDateChooser);
        customizeLabel(startDateLabel);

        JLabel endDateLabel = new JLabel("End Date:");
        endDateChooser = new JDateChooser();
        endDateChooser.setDate(T.getDeadline_Date());
        JLabel endTimeLabel = new JLabel("End Time:");
        endTimeField = new JFormattedTextField(timeFormatter);
        endTimeField.setValue(T.getStart_Time());
        customizeFormattedTextField(endTimeField);
        customizeDateChooser(endDateChooser);
        customizeLabel(endDateLabel);
        customizeLabel(endTimeLabel);


        panel.add(startDateLabel);
        panel.add(startDateChooser);
        panel.add(startTimeLabel);
        panel.add(startTimeField);
        panel.add(endDateLabel);
        panel.add(endDateChooser);
        panel.add(endTimeLabel);
        panel.add(endTimeField);

        JPanel Buttons = new JPanel(new GridLayout(0,2,1,1));
        Buttons.setBackground( new Color(146, 89, 154, 207));

        JButton addtaskButton = new JButton("Update Task");
        addtaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmission(T);
            }
        });
        customizeButton(addtaskButton);
        panel.add(addtaskButton);

        JButton deleteTaskButton = new JButton("Delete Task");
        deleteTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               if (T.TaskDelete(T.getTaskID()))
               {  JOptionPane.showMessageDialog(TaskPopup.this, "Task deleted successfully.");
                 if(model == modelintaskpopup)  model.removeRow(rowIndex);
                   deleteTaskByID(T.getTaskID());
                   handleCalendar(Date.from(endDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                   dispose();
               }
               else {
                   JOptionPane.showMessageDialog(TaskPopup.this, "You didn't even create the task yet");
                   if (modelintaskpopup == model)
                       modelintaskpopup.removeRow(rowIndex);
               }
               dispose();
            }
        });
        customizeButton(deleteTaskButton);
        Buttons.add(deleteTaskButton);
        JButton CanelButton = new JButton("Cancel");
        CanelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!taskinthelist(T.getTaskID()) && model == modelintaskpopup)
                {
                   modelintaskpopup.removeRow(rowIndex);
                }
                //
                dispose();
            }
        });
        customizeButton(CanelButton);
        Buttons.add(CanelButton);
        panel.add(Buttons);
        add(panel);

        setVisible(true);
    }

    private void handleSubmission(Task task) {
        LocalDate startDate;
        LocalDate endDate;
        LocalTime startTime;
        LocalTime endTime;

        try {
            startDate = startDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid start date.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            endDate = endDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid end date.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String startTimeText = startTimeField.getText();
        try {
            startTime = LocalTime.parse(startTimeText);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Invalid start time format. Please enter time in HH:mm format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String endTimeText = endTimeField.getText();
        try {
            endTime = LocalTime.parse(endTimeText);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Invalid end time format. Please enter time in HH:mm format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Handle the selected dates and times
        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);
        if (startDateTime.isAfter(endDateTime)) {
            JOptionPane.showMessageDialog(this, "Start time cannot be after end time.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean status = taskStatusCheckbox.isSelected();

        if (endDateTime.isBefore(LocalDateTime.now()) && status == false) {
            // make it overdue
        }
        String taskname = taskNameField.getText();
        task.TaskEdit(taskname, status ,startTime, endTime, Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        modelintaskpopup.setValueAt(taskname, rowIndex, 1); // Update task name
        modelintaskpopup.setValueAt(endDateTime, rowIndex, 2); // Update deadline date
        modelintaskpopup.setValueAt(status, rowIndex, 3); // Update status
        if(modelintaskpopup != model)
        {
            model.addRow(new Object[]{task.getTaskID(),taskname, endDateTime, status});
        }
        taskstrg.update_tasks();
        JOptionPane.showMessageDialog(this, "Task Updated successfully.");
       // new TodayTasks(Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        dispose();
        // You can perform further processing or validation here
    }

}
