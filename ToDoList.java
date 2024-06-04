package test;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static test.Task.taskArrayList;

public class ToDoList extends JPanel
{
    static DefaultTableModel model = TaskTableModel.getInstance();

    ToDoList() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setBounds(0, 0, 2000, 1080);
        setLayout(null);

        JScrollPane scroll = new JScrollPane();
        JButton addBtn = new JButton("+");
        addBtn.setBounds(310, 740, 780, 30);
        scroll.setBounds(300, 140, 800, 600);
        scroll.setBackground(new Color(10,200,0,0));
        /// 5od el color da ya samer 7ot0 back   setBackground(new Color(220, 199, 221));
        //String[] coloumnNames= {"task","checked","deadline date"};

        // model.addRow(coloumnNames);
        Color color1 = new Color(220, 199, 221);
        Color color2 = new Color(132, 66, 140, 207);
        JTable tableOfTasks = new JTable(model){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                if (isRowSelected(row)) {
                    component.setBackground(getSelectionBackground());
                } else {
                    component.setBackground(row % 2 == 0 ? color1 : color2); // Alternate between color1 and color2
                }
                return component;
            }
        };

        tableOfTasks.setSize(400, 400);
        ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
        //creating cell rendrer
        //tableOfTasks.getColumnModel().getColumn(2).setCellRenderer(new CheckBoxRenderer());
        tableOfTasks.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JCheckBox()));

        scroll.setViewportView(tableOfTasks);
        scroll.getViewport().setOpaque(false);
        // Add previous tasks
        for(Task task: taskArrayList)
        {
            model.addRow(new Object[]{task.getTaskID(),task.getTaskName(), task.getDeadline_Date(), task.getTaskStatus()? "Done": "pending"});
        }
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task T = new Task();
                model.addRow(new Object[]{T.getTaskID(),"", "", "pending"});
                TaskPopup t = new TaskPopup(T, model, tableOfTasks.getRowCount()-1);
            }
        });
        tableOfTasks.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Get the selected row index
                    int rowIndex = tableOfTasks.getSelectedRow();
                    String ID = tableOfTasks.getValueAt(rowIndex, 0).toString();
                    System.out.println(ID);
                    if (rowIndex != -1) {
                        // Create a TaskPopup instance with the selected row index
                        TaskPopup t = new TaskPopup(Task.getTaskbyID(ID), model, rowIndex);

                    } else {
                        JOptionPane.showMessageDialog(null, "Please select a row to edit.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        tableOfTasks.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getColumn() == 3) { // Check if the changed column is the "checked" column
                    int rowIndex = e.getFirstRow();
                    boolean newValue= (boolean) tableOfTasks.getValueAt(rowIndex, 3);
                    String ID = tableOfTasks.getValueAt(rowIndex, 0).toString();
                    // moshkla hena
                    Task task = Task.getTaskbyID(ID);
                    task.TaskEdit( task.getTaskName() , newValue , task.StartTime, task.DeadlineTime, task.StartDate, task.DeadlineDate);
                }
            }
        });


        TableColumnModel columnModel = tableOfTasks.getColumnModel();
        TableColumn firstColumn = columnModel.getColumn(0);
        firstColumn.setMinWidth(0);
        firstColumn.setMaxWidth(0);
        firstColumn.setWidth(0);
        firstColumn.setPreferredWidth(0);
        firstColumn.setResizable(false);

        tableOfTasks.setBackground(new Color(224, 186, 228));
        setBackground(color1);
      // scroll.setVisible(true);
        add(scroll);
        add(addBtn);

       // setVisible(true);

    }
    public static int getRowIndexByID(DefaultTableModel model, String ID) {
        for (int rowIndex = 0; rowIndex < model.getRowCount(); rowIndex++) {
            if (model.getValueAt(rowIndex, 0).toString().equals(ID)) { // Assuming ID is in the first column
                return rowIndex;
            }
        }
        return -1; // Return -1 if the ID is not found
    }
}






