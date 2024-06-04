package test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.JTableHeader;




import static test.Task.getTasksForToday;
import static test.ToDoList.getRowIndexByID;

public class TodayTasks extends JPanel {
    JButton addButton;
    public TodayTasks(Date selectedDate) {
        setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane();
        displayTodayTasks(selectedDate,scrollPane );
        scrollPane.setPreferredSize(new Dimension(500, 500));
        customizeButton(addButton);
        add(addButton, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.NORTH);

    }

    public void displayTodayTasks(Date selectedDate, JScrollPane scrollPane) {
        ArrayList<Task> todayTasks = getTasksForToday(selectedDate);
        // Create column names
        String[] columnNames = {"ID", "Name", "Deadline", "Status"};

        // Create data array
        Object[][] data = new Object[todayTasks.size()][4];

        // Fill data array with task information
        for (int i = 0; i < todayTasks.size(); i++) {
            Task task = todayTasks.get(i);
            data[i][0] = task.getTaskID();
            data[i][1] = task.getTaskName();
            data[i][2] = task.getDeadline_Date().toString();
            data[i][3] = task.getTaskStatus()? "Done": "Pending";
        }

        // Create table model
        DefaultTableModel modelnew = new DefaultTableModel(data, columnNames)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        // Create JTable with the model
        Color color1 = new Color(220, 199, 221);
        Color color2 = new Color(132, 66, 140, 207);
        JTable table = new JTable(modelnew){
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
  //      scrollPane.getViewport().setBackground(new Color(224, 186, 228));
         addButton = new JButton("Add Task");
          addButton.addActionListener(e -> {
            Task T = new Task(selectedDate);
            TaskPopup t = new TaskPopup(T, modelnew, table.getRowCount()-1);
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Get the selected row index
                    int rowIndex = table.getSelectedRow();
                    String ID = table.getValueAt(rowIndex, 0).toString();
                    if (rowIndex != -1) {
                        TaskTableModel model = TaskTableModel.getInstance();
                        TaskPopup t = new TaskPopup(Task.getTaskbyID(ID), model, getRowIndexByID(model,ID));

                    } else {
                        JOptionPane.showMessageDialog(null, "Please select a row to edit.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        TableColumnModel columnModel = table.getColumnModel();
        TableColumn firstColumn = columnModel.getColumn(0);
        firstColumn.setMinWidth(0);
        firstColumn.setMaxWidth(0);
        firstColumn.setWidth(0);
        firstColumn.setPreferredWidth(0);
        firstColumn.setResizable(false);

        scrollPane.setViewportView(table);
      //  setOpaque(false);


    }
    private void customizeLabel(JLabel label) {
        label.setForeground(new Color(102, 0, 102)); // Purple text color
        label.setMaximumSize(new Dimension(70,20));
        label.setFont(new Font("Arial", Font.BOLD, 12));
    }
    private void customizeButton(JButton button) {
        button.setBackground(new Color(102, 0, 102)); // Purple background
        button.setForeground(Color.white); // White text color
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(120, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFocusable(false);
    }
}
