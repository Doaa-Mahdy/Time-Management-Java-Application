package test;

import javax.swing.table.DefaultTableModel;

public class TaskTableModel extends DefaultTableModel {
    private static TaskTableModel instance;

    // Private constructor to prevent instantiation from outside
    private TaskTableModel() {
        super(new Object[]{"ID", "Task", "Task Deadline", "Check"}, 0);
    }

    // Static method to get the singleton instance
    public static TaskTableModel getInstance() {
        if (instance == null) {
            instance = new TaskTableModel();
        }
        return instance;
    }
    public static TaskTableModel getTabelModel() {

           return  new TaskTableModel();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        // Make only the "Check" column editable
        return column == 3;
    }
}
