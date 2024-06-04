package test;

//TODO: create storage class

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

// buffer reader and writer is better than file reader and writer here WHY?
// use an internal buffer to read and write data in larger chunks, reducing the number of I/O operations.
// bt2ra goz2 kner mn el file 3ltol f t access el disc marat a2l w io operations a2al -> asra3 fl wa2t

public class Storage {


    //public BufferedWriter userStorageWriter = new BufferedWriter(new FileWriter("userStorage.csv"));

    public static void readUserData() {
        BufferedReader userStorageRead = null;
        try {

            userStorageRead = new BufferedReader(new FileReader("userStorage.csv"));
            String row;
            int line_num = 0;
            row = userStorageRead.readLine();
            while ((row = userStorageRead.readLine()) != null) {
                System.out.println(line_num + 1);
                StringTokenizer token = new StringTokenizer(row, ",");
                while (token.hasMoreTokens()) {
                    System.out.println("\u001b[32;4m " + token.nextToken() + "\u001b[0m ");
                }
                line_num++;

            }
        } catch (IOException e) {
            System.out.println("\033[31;46;4m " + e + "\033[0m ");

        } finally {

            try {
                userStorageRead.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public void FileAddUser(String name, String pass, String ID,String email, String Gender) {
        BufferedWriter userStorageWriter = null;
        try {
            userStorageWriter = new BufferedWriter(new FileWriter("userStorage.csv", true));
            String line = ID+","+name + ","  + pass +","+email+","+Gender+"\n" ;
            userStorageWriter.write(line);


        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                userStorageWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

    }

    public int searchByName(String name) {
        BufferedReader userStorageRead = null;
        int ctr = 0;
        try {
            userStorageRead = new BufferedReader(new FileReader("userStorage.csv"));
            userStorageRead.readLine();
            String line;
            while ((line = userStorageRead.readLine()) != null) {
                ctr++;
                StringTokenizer r = new StringTokenizer(line, ",");
                r.nextToken();
                if (r.nextToken().equals(name)) {
                    return ctr;
                }

            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                userStorageRead.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return 0;
    }

    public String getID(String name) {
        BufferedReader userStorageRead = null;
        //int ctr = 0;
        try {
            userStorageRead = new BufferedReader(new FileReader("userStorage.csv"));
            userStorageRead.readLine();
            String line;
            while ((line = userStorageRead.readLine()) != null) {
                //ctr++;
                StringTokenizer r = new StringTokenizer(line, ",");
                String ID = r.nextToken();
                if (r.nextToken().equals(name)) {
                    return ID;
                }

            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                userStorageRead.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return null;
    }

    public String getEmail(String name) {
        BufferedReader userStorageRead = null;
        //int ctr = 0;
        try {
            userStorageRead = new BufferedReader(new FileReader("userStorage.csv"));
            userStorageRead.readLine();
            String line;
            while ((line = userStorageRead.readLine()) != null) {
                //ctr++;
                StringTokenizer r = new StringTokenizer(line, ",");
                r.nextToken();
                if (r.nextToken().equals(name)) {
                    r.nextToken();
                    return r.nextToken();
                }

            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                userStorageRead.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return null;
    }

    public String getGender(String name) {
        BufferedReader userStorageRead = null;
        //int ctr = 0;
        try {
            userStorageRead = new BufferedReader(new FileReader("userStorage.csv"));
            userStorageRead.readLine();
            String line;
            while ((line = userStorageRead.readLine()) != null) {
                //ctr++;
                StringTokenizer r = new StringTokenizer(line, ",");
                r.nextToken();
                if (r.nextToken().equals(name)) {
                    r.nextToken();
                    r.nextToken();
                    return r.nextToken();
                }

            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                userStorageRead.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return null;
    }



    public boolean searchByPass(String password) {
        BufferedReader userStorageRead = null;
        String pass = User.HashPass(password);
        try {
            userStorageRead = new BufferedReader(new FileReader("userStorage.csv"));
            userStorageRead.readLine();
            String line;
            while ((line = userStorageRead.readLine()) != null) {
                StringTokenizer r = new StringTokenizer(line, ",");
                //id
                r.nextToken();
                //username
                r.nextToken();
                //password
                String s = r.nextToken();
                if (s.equals(pass))//pass
                {
                    return true;
                }
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                userStorageRead.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    // return the password of a specific line number (line number of the found username)
    public String getPassByNumLine(int NumLine) {
        String pass = "";
        BufferedReader userStorageRead = null;
        if (NumLine == 0)
            return "";
        try {

            userStorageRead = new BufferedReader(new FileReader("userStorage.csv"));
            userStorageRead.readLine();
            for (int i = 1; i <= NumLine; i++) {


                String line = userStorageRead.readLine();
                if (i == NumLine) {
                    StringTokenizer sr = new StringTokenizer(line, ",");
                    sr.nextToken();
                    sr.nextToken();
                    return sr.nextToken();
                }
            }
        } catch (IOException e) {
            System.out.println(e);
            return null;
        } catch (Exception e) {
            System.out.println(e);

            return null;
        } finally {
            try {
                userStorageRead.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return pass;
    }

    public void FileUpdatePass(String userName, String Newpass)//this method just change the password without checking its correctness if you want to change password use the changePass method in user class
    {
        BufferedReader userStorageRead = null;

        boolean flag = false;
        ArrayList<String[]> details = new ArrayList<>();
        int numLine = searchByName(userName);
        try {
            userStorageRead = new BufferedReader(new FileReader("userStorage.csv"));
            String line;

            while ((line = userStorageRead.readLine()) != null) {
                line += "\n";
                String[] arr = line.split(",");
                details.add(arr);

            }
            (details.get(numLine))[2] = Newpass;
        } catch (Exception e) {
            System.out.println(e);

        } finally {
            try {
                userStorageRead.close();


            } catch (IOException e) {
                System.out.println(e);
            }


        }
        BufferedWriter userStorageWrite = null;
        try {
            userStorageWrite = new BufferedWriter(new FileWriter("userStorage.csv"));
            String line;
            for (String[] a : details) {
                line = String.join(",", a);
                userStorageWrite.write(line);

            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {

            try {
                userStorageWrite.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


    public void FileUpdateGender(String userName, String newGender)//this method just change the password without checking its correctness if you want to change password use the changePass method in user class
    {
        BufferedReader userStorageRead = null;

        boolean flag = false;
        ArrayList<String[]> details = new ArrayList<>();
        int numLine = searchByName(userName);
        try {
            userStorageRead = new BufferedReader(new FileReader("userStorage.csv"));
            String line;

            while ((line = userStorageRead.readLine()) != null) {
                String[] arr = line.split(",");
                details.add(arr);

            }
            (details.get(numLine))[4] = newGender;
        } catch (Exception e) {
            System.out.println(e);

        } finally {
            try {
                userStorageRead.close();


            } catch (IOException e) {
                System.out.println(e);
            }


        }
        BufferedWriter userStorageWrite = null;
        try {
            userStorageWrite = new BufferedWriter(new FileWriter("userStorage.csv"));
            String line;
            for (String[] a : details) {
                line = String.join(",", a);
                userStorageWrite.write(line);
                userStorageWrite.write("\n");



            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {

            try {
                userStorageWrite.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public boolean FileDeleteUser(String userName, String pass) {
        boolean check = false;
        int numLine;
            numLine = searchByName(userName);
            BufferedReader userStorageRead = null;
            ArrayList<String[]> details = new ArrayList<>();

            try {
                userStorageRead = new BufferedReader(new FileReader("userStorage.csv"));
                String line;
                while ((line = userStorageRead.readLine()) != null) {
                    String[] arr = line.split(",");
                    details.add(arr);
                }

            } catch (Exception e) {
                System.out.println(e);

            } finally {
                try {
                    userStorageRead.close();


                } catch (IOException e) {
                    System.out.println(e);
                }

            }
            BufferedWriter userStorageWrite = null;
            try {
                userStorageWrite = new BufferedWriter(new FileWriter("userStorage.csv"));
                String line;
                int ctr=0;
                for (String[] a : details) {
                    if(ctr == numLine) {
                        check = true;
                        continue;
                    }
                    line = String.join(",", a);
                    userStorageWrite.write(line);
                    userStorageWrite.newLine();
                    ctr++;
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {

                try {
                    userStorageWrite.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        return check;
        }

    }







