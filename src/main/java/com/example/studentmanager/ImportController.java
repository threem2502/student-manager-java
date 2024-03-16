package com.example.studentmanager;

import com.example.studentmanager.File.ReadWrite;
import com.example.studentmanager.Object.Student;
import com.example.studentmanager.Object.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class ImportController implements Initializable {
    private Student student;
    private void setStudent() {
        String id = idInput();
        for (Student s : studentList) {
            if (Objects.equals(s.getId(), id)) {
                student = s;
                break;
            }
        }
    }

    @FXML
    TableView<Subject> subjectTableView;

    @FXML
    TableColumn<Subject, String> id;

    @FXML
    TableColumn<Subject, String> name;

    @FXML
    TableColumn<Subject,  Float> point;

    @FXML
    TextField pointText;


    private ObservableList<Subject> studentListSubject;

    private ArrayList<Student> studentList = ReadWrite.read("Student.txt");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            setStudent();
            studentListSubject = FXCollections.observableArrayList(student.getSubjectList());
            id.setCellValueFactory(new PropertyValueFactory<Subject, String>("subId"));
            name.setCellValueFactory(new PropertyValueFactory<Subject, String>("subName"));
            point.setCellValueFactory(new PropertyValueFactory<Subject, Float>("point"));
            subjectTableView.setItems(studentListSubject);
    }

    public void importPoint(ActionEvent event) {
        Subject selected = subjectTableView.getSelectionModel().getSelectedItem();
        selected.setPoint(Float.parseFloat(pointText.getText()));
    }

    public String idInput() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("StudentID");

        ButtonType apply = new ButtonType("Apply", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(apply, ButtonType.CANCEL);

        GridPane grid =  new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,150,10,10));

        TextField idText = new TextField();

        grid.add(new Label("ID"), 0 , 0);
        grid.add(idText,1,0);

        Node applyButton = dialog.getDialogPane().lookupButton(apply);
        applyButton.setDisable(true);

        idText.textProperty().addListener((observable, oldValue, newValue) -> {    //Nhập vào mới ấn được button
            applyButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == apply){
                return idText.getText();
            }
            return null;
        });
        dialog.showAndWait();
        return dialog.getResult();
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

    public void done(ActionEvent event) {
        studentList.removeIf(s -> Objects.equals(s.getId(), student.getId()));
        student.setAvgPoint();
        studentList.add(student);
        ReadWrite.write("Student.txt",studentList);
    }


}
