package test;

public class  CommonDataofUser {
    public static  String EMAIL = "dumpemail@nu";
    //public static  String SECOND_EMAIL = "dumpemail@nu";
    public static  String USERNAME = "RODOZOMOSA";
    public static  String USER_ID = "ayID";
    public static  String USERFILENAME = "tasks.csv";
    //public static  String PHONE_NUMBER = "01016121406";
    public static String GENDER = "Female";
    public static int cnt=0;
    public static boolean profile = false;
   // public static String JOB_TITLE = "Student";


    public CommonDataofUser(String mail, String username,String id, String Gender) {
        EMAIL = mail;
        USERNAME =  username;
       USER_ID = id;
        GENDER= Gender;
        USERFILENAME= username + ".csv";
      tasksStorage  taskstrg = new tasksStorage();
        Task.taskArrayList = taskstrg.readAllTasks();
        taskstrg.update_tasks();

    }
}