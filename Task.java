package test;
import java.time.LocalTime;
import java.util.*;

import static test.Static.handleCalendar;


public class Task {
    static ArrayList<Integer> arrayofids = new ArrayList<>(Collections.nCopies(5001, 0));
    static  ArrayList<Task> taskArrayList = new ArrayList<>();
    static ArrayList<Task> TodaysTasks = new ArrayList<>();
    private String TaskName="";
    private String TaskID = "";
    private boolean TaskStatus; // 0 low pending / 1 lw done /-1 low fatek
    Date StartDate;
    Date DeadlineDate;
    LocalTime StartTime;
    LocalTime DeadlineTime;
    public Task()
    {
        TaskName="";
        TaskStatus= false; // 0 low pending / 1 lw done /-1 low fatek
        setTaskID();
        setStart_Time();
        setStart_Date();
        setDeadline_Time();
        setDeadline_Date();
    }
    public Task(Date D)
    {
        TaskName="";
        TaskStatus= false; // 0 low pending / 1 lw done /-1 low fatek
        setTaskID();
        setStart_Time();
        StartDate =  D;
        setDeadline_Time();
        DeadlineDate = D;
    }
    public Task(String TaskID, String TaskName , boolean TaskStatus, LocalTime StartTime, LocalTime DeadlineTime, Date StartDate, Date DeadlineDate) {
        this.TaskName=TaskName;
        this.TaskID = TaskID;
        arrayofids.set(Integer.parseInt(TaskID.substring(0,4))-3000,1);
        this.TaskStatus=TaskStatus;
        this.StartDate  = StartDate;
        this.StartTime = StartTime;
        this.DeadlineDate  = DeadlineDate;
        this.DeadlineTime = DeadlineTime;

    }
    public Task(String TaskName , boolean TaskStatus, LocalTime StartTime, LocalTime DeadlineTime, Date StartDate, Date DeadlineDate) {
        this.TaskName=TaskName;
        setTaskID();
        this.TaskStatus=TaskStatus;
        this.StartDate  = StartDate;
        this.StartTime = StartTime;
        this.DeadlineDate  = DeadlineDate;
        this.DeadlineTime = DeadlineTime;
        taskArrayList.add(this);
    }
    public void TaskEdit(String TaskName , boolean TaskStatus, LocalTime StartTime, LocalTime DeadlineTime, Date StartDate, Date DeadlineDate) {
        this.TaskName=TaskName;
        this.TaskStatus=TaskStatus;
        this.StartDate  = StartDate;
        this.StartTime = StartTime;
        this.DeadlineDate  = DeadlineDate;
        this.DeadlineTime = DeadlineTime;
        if(!taskinthelist(this.TaskID))  taskArrayList.add(this);
        tasksStorage taskStor = new tasksStorage();
        taskStor.FileAddTask(TaskID, TaskName, TaskStatus? "finished": "process", StartTime, DeadlineTime,StartDate, DeadlineDate);
        handleCalendar(Date.from(DeadlineDate.toInstant()));

    }
    public boolean TaskDelete (String taskId) {
        for (Task task : taskArrayList) {
            if (task.getTaskID().equals(taskId)) {
                taskArrayList.remove(task);
                return true;
            }
        }
        return false; // Task with specified ID not found
    }

    public boolean TaskDelete (Task T) {
        return TaskDelete(T.getTaskID());
    }
    public static Task getTaskbyID(String ID)
    {
        for (Task task : taskArrayList) {
            if (task.getTaskID().equals(ID)) {
                return task;
            }
            }
        return null;
    }
    public static ArrayList<Task> getTasksForToday(Date D) {
        ArrayList<Task> todayTasks = new ArrayList<>();
        for (Task task : taskArrayList) {
            if (isSameDate(task.getDeadline_Date(), D) || isSameDate(task.getStart_Date(), D)) {
                todayTasks.add(task);
            }
        }
        return todayTasks;
    }
    public static boolean taskinthelist(String ID)
    {
        for (Task task: taskArrayList)
        {
            if(ID == task.getTaskID())
                return true;
        }
        return false;
    }
    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String TaskName) {
        this.TaskName = TaskName;
    }

    public String getTaskID() {
        return this.TaskID;
    }

    public void setTaskID() {
        this.TaskID = Generate_ID();
    }

    public boolean getTaskStatus() {
        return TaskStatus;
    }

    public void setTaskStatus(boolean TaskStatus) {
        this.TaskStatus = TaskStatus;
    }


    public Date getDeadline_Date() {
        return DeadlineDate;
    }

    public void setDeadline_Date() {
        DeadlineDate =  Calendar.getInstance().getTime();
    }

    public Date getStart_Date() {
        return StartDate;
    }

    public void setStart_Date() {
        StartDate =  Calendar.getInstance().getTime();
    }

    public LocalTime getStart_Time() {
        return StartTime;
    }

    public void setStart_Time() {
        StartTime = LocalTime.now();
    }

    public LocalTime getDeadline_Time() {
        return DeadlineTime;
    }

    public void setDeadline_Time() {
        DeadlineTime = LocalTime.now();
    }

    public static String Generate_ID()
    {
        Random random = new Random();
        int MinId = 3000;
        int MaxId = 8000;
        int RandomId = random.nextInt(MaxId - MinId + 1) + MinId;
        while(arrayofids.get(RandomId-MinId)==1)
        {
            RandomId = random.nextInt(MaxId - MinId + 1) + MinId;
        }
        arrayofids.set(RandomId-MinId,1);
        String str = String.valueOf(RandomId);
       str= str+ CommonDataofUser.USER_ID;
        return str;
    }
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        // Compare year, month, and day
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

}

