package com.example.studentmanager;

import com.example.studentmanager.File.ReadWrite;
import com.example.studentmanager.File.ReadWriteSub;
import com.example.studentmanager.Object.Student;
import com.example.studentmanager.Object.Subject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {

//        Subject subject = new Subject("MA101", "Logic đếm", 3);
//        ArrayList<Subject> subjectList = new ArrayList<>();
//        subjectList.add(subject);
//        ReadWriteSub.write("Subject.txt", subjectList);
//
//        Student student = new Student("A40417", "Mạnh", "0862019623", 10F);
//        ArrayList<Student> listStudent = new ArrayList<>();
//        listStudent.add(student);
//        student.addSub(subject);
//        ReadWrite.write("Student.txt",listStudent);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root1 = FXMLLoader.load(((Objects.requireNonNull(getClass().getResource("screen1.fxml")))));
        Scene scene1 =  new Scene(root1, 650,480);

        primaryStage.setTitle("Student Manager");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
}
