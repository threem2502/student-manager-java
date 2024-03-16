package com.example.studentmanager;

import com.example.studentmanager.File.ReadWrite;
import com.example.studentmanager.File.ReadWriteSub;
import com.example.studentmanager.Object.Student;
import com.example.studentmanager.Object.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private TableView<Subject> subjectTable;

    @FXML
    private TableColumn<Subject, String> subIdColumn;

    @FXML
    private TableColumn<Subject, String> subNameColumn;

    @FXML
    private TableColumn<Subject, Integer> soTcColumn;

    @FXML
    private TextField idsubText;

    private Student student;
    private ArrayList<Subject> subjectArrayList;
    private ObservableList<Subject> subjectList;
    private ArrayList<Student> studentList = ReadWrite.read("Student.txt");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        subjectArrayList = ReadWriteSub.read("Subject.txt");
        subjectList = FXCollections.observableArrayList(subjectArrayList);
        subIdColumn.setCellValueFactory(new PropertyValueFactory<Subject, String>("subId"));
        subNameColumn.setCellValueFactory(new PropertyValueFactory<Subject, String>("subName"));
        soTcColumn.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("soTc"));
        subjectTable.setItems(subjectList);
    }

    public void register(ActionEvent event) {
        Subject selected = subjectTable.getSelectionModel().getSelectedItem();
        subAlert(student.addSub(selected));
    }



    public void subAlert(boolean have){
        if(have) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Bạn đã đăng kí môn này rồi");
            alert.show();
        }
    }

    public void done(ActionEvent event) {
        studentList.removeIf(s -> Objects.equals(s.getId(), student.getId()));
        studentList.add(student);
        ReadWrite.write("Student.txt",studentList);
    }

    public void studentScreen(ActionEvent event) throws IOException {

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("screen1.fxml"));
        Parent root1 = loader.load();
        Scene scene = new Scene(root1,650, 480);
        stage.setScene(scene);
        stage.show();

    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
