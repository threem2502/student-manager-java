package com.example.studentmanager.File;



import com.example.studentmanager.Object.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ReadWrite implements Serializable  {
    public static void write(String urlFile,ArrayList<Student> ListStudent){
        try{
            FileOutputStream fos = new FileOutputStream(urlFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ListStudent);
            oos.close();
            fos.close();
        } catch (IOException | ClassCastException e){
            e.printStackTrace();
        }
    }
    public static ArrayList<Student> read(String urlFile){
        try{
            FileInputStream fis = new FileInputStream(urlFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Student> ListStudent = (ArrayList<Student>) ois.readObject();
            ois.close();
            fis.close();
            return ListStudent;
        } catch (IOException | ClassCastException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
    public ReadWrite() {
    }
}

