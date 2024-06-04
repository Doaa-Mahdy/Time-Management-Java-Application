package test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

public class User {
    public String name;
    public String pass;
    public String ID;
    public String EmailAddress;
    public Storage usrDBManager= null;
    public static int ctr=0;
    public User()
    {
        name=null;
        pass=null;
        ID= "";
    }

    public User(String name, String pass,String email) {
        this.name = name;
        this.pass = pass;
        this.EmailAddress=email;
        this.ID = generateID();

    }

    public static String HashPass(String password)
    {
        MessageDigest m = null;
        String HashPass="";
        try {

            m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes());
            byte[] result = m.digest();
            StringBuilder pass_in_build= new StringBuilder("");
            for(byte b:result )
            {
                pass_in_build.append(b);
            }
            HashPass=pass_in_build.toString();


        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


        return HashPass;
    }


    // Generate unique ID for each user
    public String generateID()
    {
        Calendar c = Calendar.getInstance();
        long time_in_seconds= c.getTimeInMillis();
        String year= String.valueOf(c.get(Calendar.YEAR));
        String Day= String.valueOf(c.get(Calendar.DAY_OF_YEAR));
        String ResultID= time_in_seconds+""+ctr+year.substring(2)+Day;

        ResultID=ResultID.substring(9);

        ctr++;
        return ResultID;
    }



    // compare that the password matches this username
    public static boolean Authenticate(String userName,String Password)
    {
        boolean check=false;
        Storage obj = new Storage();
        if(userName.isEmpty() || Password.isEmpty())
         return false;
        // check if the password is equal to the password stored in the same line of the username
        try{
            if (obj.getPassByNumLine(obj.searchByName(userName)).equals(HashPass(Password)))
                return true;
            else
                return false;
        }catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
    }
    //TODO: check this method
    //TODO:final method that used the sub method update method
    public boolean ChangePass(String userName, String oldPass, String newPass)
    {
        boolean check= false;
        usrDBManager= new Storage();
        String HashedOldPass= HashPass(oldPass);

        try{
            if (Authenticate(userName, oldPass)) {
                String HashedNewPass = HashPass(newPass);
                usrDBManager.FileUpdatePass(userName, HashedNewPass);
                check = true;

            }
        }catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            return check;
        }

    }

    public boolean AddUser(String userName, String pass, String checkPass,String email)
    {

        boolean check=false;
        usrDBManager=new Storage();

        try {
            if (usrDBManager.searchByName(userName) == 0) {
                String hashed_pass = HashPass(pass);
                String hashed_confirm_pass = HashPass(checkPass);
                if (hashed_pass.equals(hashed_confirm_pass)) {
                    usrDBManager.FileAddUser(userName, hashed_pass, generateID(),email, "Female");
                    check=true;
                    System.out.println("added successfully");
                }
            } else {
                System.out.println("user name is repeated, cannot add!");

            }
        }catch(Exception e)
        {

            System.out.println(e);
        }
        return check;
    }


    public boolean deleteUser(String userName, String pass)
    {
        String HashedPass=HashPass(pass);
        usrDBManager = new Storage();
        boolean check=false;
        try {
            boolean isOk = Authenticate(userName, pass);
            //System.out.println(isOk);
            if (isOk) {
                check = usrDBManager.FileDeleteUser(userName, HashedPass);


            }
        }catch(Exception e)
        {
            System.out.println(e);
        }
        finally {
            return check;
        }



    }



}
