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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class SubController implements Initializable {
    @FXML
    private TableView<Subject> subjectTable;

    @FXML
    private TableColumn<Subject, String> subIdColumn;

    @FXML
    private TableColumn<Subject, String> subNameColumn;

    @FXML
    private TableColumn<Subject, Integer> soTcColumn;



    @FXML
    private TextField subIdText;

    @FXML
    private TextField subNameText;

    @FXML
    private TextField soTcText;

    private ArrayList<Subject> subjectArrayList;
    private ObservableList<Subject> subjectList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        subjectArrayList = ReadWriteSub.read("Subject.txt");
        subjectList = FXCollections.observableArrayList(subjectArrayList);
        subIdColumn.setCellValueFactory(new PropertyValueFactory<Subject, String>("subId"));
        subNameColumn.setCellValueFactory(new PropertyValueFactory<Subject, String>("subName"));
        soTcColumn.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("soTc"));
        subjectTable.setItems(subjectList);
    }

    public void alert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Đã có môn");
        alert.show();
    }

    public void add (ActionEvent event) {
        boolean have = false;
        for(Subject s : subjectList) {
            if(Objects.equals(s.getSubId(), subIdText.getText())) {
                alert();
                have = true;
                break;
            }
        }
        if(!have) {
            Subject subject = new Subject(subIdText.getText(), subNameText.getText(), Integer.parseInt(soTcText.getText()));
            subjectArrayList.add(subject);
            subjectList.add(subject);
        }

    }

    public void delete (ActionEvent event) {
        Subject selected = subjectTable.getSelectionModel().getSelectedItem();
        subjectArrayList.removeIf(s -> Objects.equals(s.getSubId(), selected.getSubId()));
        subjectList.removeIf(s -> Objects.equals(s.getSubId(), selected.getSubId()));
    }

    public void edit(ActionEvent event) {
        Subject selected = subjectTable.getSelectionModel().getSelectedItem();
        Subject subject = new Subject(subIdText.getText(), subNameText.getText(), Integer.parseInt(soTcText.getText()));
        subjectList.remove(selected);
        subjectArrayList.remove(selected);
        subjectArrayList.add(subject);
        subjectList.add(subject);
    }

    public void save(ActionEvent event) {
        ReadWriteSub.write("Subject.txt", subjectArrayList);
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
}


