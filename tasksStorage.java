package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.time.*;
//import java.time.LocalTime;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;
//import java.util.Date;

import static test.CommonDataofUser.USERFILENAME;

public class tasksStorage {
    final static String[] months={"Jan","Feb","Mar","April","May","June","July","Aug","Sep","Oct","Nov","Dec"};
    int[][] tasks=new int[3][12];
    static ArrayList<Integer> arrayofids = new ArrayList<>(Collections.nCopies(1001, 0));


    // b read all tasks and store them in array list of type Task object w return this list
    public ArrayList<Task> readAllTasks() { // b7tago awel m3ml login
        ArrayList<Task> tasks = new ArrayList<>();
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(USERFILENAME));  // 34an ye2ra el tasks bta3t el user el mo3ayan dh
            String line;

            while ((line = reader.readLine()) != null) {  // tool ma el line elly bbt2rah dh m4 b null
                String[] parts = line.split(",");  // b2ta3 el line dh f array of strings mn 3nd el commas
                if(parts[0].equals("Header")) continue;   // yb2a dh awel line fl file m4 3ayzah
                boolean isFinished = "finished".equals(parts[2]); // el gui feh tasks finished aw pending bs f lw el status equal finished hab3ato ll gui 3la eno 1 lw la hb3tlo 0(pending)

                // convert from string to (LocalTime) data type using parse
                DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;  // HH:mm:ss
                LocalTime startTime = LocalTime.parse(parts[3], timeFormatter); // saved fl file bl manzar dh "HH:mm:ss"
                LocalTime endTime = LocalTime.parse(parts[4], timeFormatter);

                // convert from string to (Date) data type using parse
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = dateFormatter.parse(parts[5]);
                Date endDate = dateFormatter.parse(parts[6]);

                // Create a new Task object and add it to the list
                tasks.add(new Task(parts[0],parts[1], isFinished, startTime, endTime, startDate, endDate));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tasks;
    }

    public boolean TaskIsThere(String id){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(USERFILENAME));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts[0].equals("Header")) continue;

                if (parts[0].equals(id)) { // lw la2et el id fl file yeb2a el task de mwgoda return true
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("error reading from file: " + e.getMessage());
        } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("error closing the reader bec it's null");
                }
        }
        return false;
    }

    public void FileAddTask(String ID, String TaskName , String TaskStatus, LocalTime startTime, LocalTime endTime, Date StartDate, Date EndDate) {
        boolean id = TaskIsThere(ID);
        // if the task already exists delete it then add it again modified, l2no m4 hyd5ol hena 8er f 7alet el update aw add task f we need to overwrite the exist task kda kda
        if(id){
            deleteTaskByID(ID);
        }
        BufferedWriter StorageWriter = null;
        try {
            StorageWriter = new BufferedWriter(new FileWriter(USERFILENAME, true));  // appending new task 34an mymsa7sh el adeem kolo
            // convert from Date to LocalDate (bna5do mn el gui Date bs added fl file k LocalDate)
            LocalDate startT = StartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endT = EndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            String line = ID + "," + TaskName + ","  + TaskStatus + "," + startTime + "," + endTime + "," + startT + "," + endT + "\n" ;
            StorageWriter.write(line);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if(StorageWriter != null) StorageWriter.close();
            } catch (IOException e) {
                System.out.println("error closing the StorageWriter bec it's null");
            }
        }
    }

    public static void deleteTaskByID(String ID) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        ArrayList<String> lines = new ArrayList<>();
        // read el file content w a7oto f arrayList m3ada el line elly feh nfs el id elly 3ayza ams7o w b3den aktb tani fl file
        try {
            reader = new BufferedReader(new FileReader(USERFILENAME));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (!parts[0].equals(ID)) {     // Keep lines not matching the ID in the arrayList
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println("error closing reader bec it's null");
            }
        }

        try {
            writer = new BufferedWriter(new FileWriter(USERFILENAME));
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if(writer != null) writer.close();
            } catch (IOException e) {
                System.out.println("error closing writer bec it's null");
            }
        }
    }


// for the statistics in the dashboard byraga3 2d array feh index[0] -> finished tasks for the 12 months
//                                                          index[1] -> unFinished tasks for the 12 months
//                                                          index[2] -> overduo tasks for the 12 months
public int[][] readTaskNumber()
{

  File file = new File("report.csv");
    BufferedReader read=null;
    try {
        // this how they stored in the report file
        // month,finished tasks,tasks in process,overdue
        // jan,0,0,0
        // feb,0,0,0
         read = new BufferedReader(new FileReader(file));
         read.readLine();  // header m4 3ayzeno
         Stream<String> arr= read.lines();  // ba2ra el lines kolaha mara wa7da (obtain a stream of lines from a BufferedReader)
         String[] arrayOfLines= arr.toArray(String[]::new);  // convert this stream of lines to array of strings contain these lines
         int ctr=0;
         for(String line: arrayOfLines) {   // bmsek kl line b2semo l words
            StringTokenizer str= new StringTokenizer(line,",");
            str.nextToken();     // esm el shahr m4 mohtama beh
             // [0],[1],[2] represent rows -> row for finished, row for unfinished, row for overdue
             // [ctr] represents the cols -> cols for the 12 months ctr goes from 0 to 11
            tasks[0][ctr]=Integer.parseInt(str.nextToken());    // ba5od fl shahr dh ana 3ndi kam task is finished w a7otaha fl 2d array bta3i
            tasks[1][ctr]=Integer.parseInt(str.nextToken());
            tasks[2][ctr]=Integer.parseInt(str.nextToken());
            ctr++;
         }

    } catch (FileNotFoundException e) {
        System.out.println(e.getMessage());
    } catch (IOException e) {
        System.out.println(e.getMessage());
    } finally {
        try {
            if(read != null) read.close();
        } catch (IOException e) {
            System.out.println("error closing read bec it's null");
        }
    }


    return tasks;
}
    //id name status
    void update_tasks()
    {
        BufferedReader readFromTasks = null;
        BufferedWriter WriteReport = null;
        int[][] tasks=new int[3][12];

        //Arrays.fill(tasks,0);

        //update stage

        try {
            WriteReport=new BufferedWriter(new FileWriter("report.csv"));
            readFromTasks= new BufferedReader(new FileReader(USERFILENAME));
            Stream<String> arr= readFromTasks.lines();
            String[] arrayOfLines= arr.toArray(String[]::new);

            for(int i=1; i<arrayOfLines.length; i++)
            {
                StringTokenizer cutter= new StringTokenizer(arrayOfLines[i],",");

                cutter.nextToken();
                cutter.nextToken();
                //cutter.nextToken();
                String status=cutter.nextToken();
                int row=-1;
                System.out.println(status);
                if(status.equals("finished"))
                    row=0;
                else if(status.equals("process"))
                    row=1;
                else
                    row=2;
                cutter.nextToken();
                cutter.nextToken();
                StringTokenizer date= new StringTokenizer(cutter.nextToken(),"-");

                    //date.nextToken();
                System.out.println(date.nextToken());
                    int month= Integer.parseInt(date.nextToken());
                System.out.println(month);
                    tasks[row][month-1]++;

            }
            WriteReport.write("month,finished tasks,tasks in process,overdue\n");
            for(int i=0;i<12;i++)
                WriteReport.write(months[i]+","+tasks[0][i]+","+tasks[1][i]+","+tasks[2][i]+"\n");
            //month,finished tasks,tasks in process,overdue
            //month[i]+","+tasks[0][i]+","+tasks[1][i]+","+tasks[2][i]
            //read
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally
        {
            try {
                WriteReport.close();
                readFromTasks.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }


}
