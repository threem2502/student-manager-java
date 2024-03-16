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
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Controller implements Initializable{
    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, String> idColumn;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, String> sdtColumn;

    @FXML
    private TableColumn<Student, Float> pointColumn;



    @FXML
    private TextField idText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField sdtText;

    @FXML
    private TextField pointText;


    private ArrayList<Student> studentArrayList;
    private ObservableList<Student> studentList;

    @Override
    public void initialize(URL location, ResourceBundle resoure) {
        studentArrayList = ReadWrite.read("Student.txt");
        studentList = FXCollections.observableArrayList(studentArrayList);
        idColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        sdtColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("sdt"));
        pointColumn.setCellValueFactory(new PropertyValueFactory<Student, Float>("avgPoint"));
        studentTable.setItems(studentList);
    }

    public void alert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Đã có sinh viên");
        alert.show();
    }

    public void add (ActionEvent event) {
        boolean have = false;
        for(Student s : studentList){
            if(Objects.equals(s.getId(), idText.getId())) {
                alert();
                have = true;
                break;
            }
        }
        if(!have) {
            Student student = new Student(idText.getText(), nameText.getText(),  sdtText.getText(), Float.parseFloat(pointText.getText()));
            studentArrayList.add(student);
            studentList.add(student);
        }

    }

    public void delete (ActionEvent event) {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        studentArrayList.removeIf(s -> Objects.equals(s.getId(), selected.getId()));
        studentList.removeIf(s -> Objects.equals(s.getId(), selected.getId()));
    }

    public void edit (ActionEvent event)  {

        Student selected = studentTable.getSelectionModel().getSelectedItem();

        Student student = new Student(idText.getText(),nameText.getText(),sdtText.getText(),Float.parseFloat(pointText.getText()));

        studentList.add(student);
        studentArrayList.add(student);
        studentList.remove(selected);
        studentArrayList.remove(selected);
    }


    public void filter (ActionEvent event) {
        Dialog<Float> dialog = new Dialog<>();
        dialog.setTitle("Filter");

        ButtonType loc = new ButtonType("Lọc", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loc, ButtonType.CANCEL);

        GridPane grid =  new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,150,10,10));

        TextField filterPoint = new TextField();

        grid.add(new Label("Điểm cần lọc: "), 0 , 0);
        grid.add(filterPoint,1,0);

        Node locButton = dialog.getDialogPane().lookupButton(loc);
        locButton.setDisable(true);

        filterPoint.textProperty().addListener((observable, oldValue, newValue) -> {    //Nhập vào mới ấn được button
            locButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == loc){
                return Float.valueOf(filterPoint.getText());
            }
            return null;
        });
        dialog.showAndWait();

        studentList.removeIf(s -> s.getAvgPoint() < dialog.getResult());
    }

    public void importPoint(ActionEvent event) throws IOException {

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("importPoint.fxml"));
        Parent root2 = loader.load();
        Scene scene = new Scene(root2,650, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void save(ActionEvent event) {
        ReadWrite.write("Student.txt", studentArrayList);
    }

    public void registerSceen(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("register.fxml"));
        Parent root2 = loader.load();
        RegisterController controller  = loader.getController();
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        controller.setStudent(selected);
        Scene scene = new Scene(root2,650, 480);
        stage.setScene(scene);
        stage.show();

    }

    public void subScreen(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("screen2.fxml"));
        Parent root2 = loader.load();
        Scene scene = new Scene(root2,650, 480);
        stage.setScene(scene);
        stage.show();
    }

}

