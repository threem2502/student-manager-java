package com.example.studentmanager.File;

import com.example.studentmanager.Object.Student;
import com.example.studentmanager.Object.Subject;

import java.io.*;
import java.util.ArrayList;

public class ReadWriteSub implements Serializable {
    public static void write(String urlFile, ArrayList<Subject> ListSubject){
        try{
            FileOutputStream fos = new FileOutputStream(urlFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ListSubject);
            oos.close();
            fos.close();
        } catch (IOException | ClassCastException e){
            e.printStackTrace();
        }
    }
    public static ArrayList<Subject> read(String urlFile){
        try{
            FileInputStream fis = new FileInputStream(urlFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Subject> ListSubject = (ArrayList<Subject>) ois.readObject();
            ois.close();
            fis.close();
            return ListSubject;
        } catch (IOException | ClassCastException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
    public ReadWriteSub(){}
}
