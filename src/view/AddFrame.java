package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Teacher;
import model.TeacherFIO;


public class AddFrame {

    private Controller controller;
    private Stage newFrame;
    private TeacherTable table;

    public AddFrame(Controller controller, TeacherTable table) {
        this.controller = controller;
        newFrame = new Stage();
        this.table = table;
    }

    public void addTeacher(){

        Label facultyName = new Label("Название факультета:");
        TextField facultyField = new TextField();
        Label departmentName = new Label("Название кафедры:");
        TextField departmentField = new TextField();
        Label firstName = new Label("Имя преподавателя:");
        TextField firstField = new TextField();
        Label secondName = new Label("Фамилия преподавателя:");
        TextField secondField = new TextField();
        Label otchestvoName = new Label("Отчество преподавателя:");
        TextField otchestvoField = new TextField();
        Label rankName = new Label("Ученое звание:");
        TextField rankField = new TextField();
        Label degreeName = new Label("Ученая степень:");
        TextField degreeField = new TextField();
        Label seniorityName = new Label("Стаж работы(в годах):");
        TextField seniorityField = new TextField();

        Button addTeacher = new Button("Add teacher");
        addTeacher.setStyle("-fx-background-color: green");
        addTeacher.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
                if(!facultyField.getText().equals("") && !departmentField.getText().equals("") && !firstField.getText().equals("") && !secondField.getText().equals("") &&
                !otchestvoField.getText().equals("") && !rankField.getText().equals("") && !degreeField.getText().equals("") && !seniorityField.getText().equals("")){
                    controller.addTeacher(new Teacher(facultyField.getText(), departmentField.getText(),
                            new TeacherFIO(firstField.getText(), secondField.getText(), otchestvoField.getText()),
                            rankField.getText(), degreeField.getText(), Integer.parseInt(seniorityField.getText())));
                    table.numeration();
                    Alert addAlert = new Alert(Alert.AlertType.INFORMATION);
                    addAlert.setContentText("Преподаватель добавлен.");
                    addAlert.showAndWait();
                    newFrame.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Заполните все поля!");
                    alert.showAndWait();
                }
           }
       });

        GridPane grid=new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(25);
        grid.setVgap(15);

        grid.add(facultyName,0,0);
        grid.add(facultyField,1,0);

        grid.add(departmentName,0,1);
        grid.add(departmentField,1,1);

        grid.add(firstName,0,2);
        grid.add(firstField,1,2);
        grid.add(secondName,2,2);
        grid.add(secondField,3,2);
        grid.add(otchestvoName,4,2);
        grid.add(otchestvoField,5,2);

        grid.add(rankName,0,3);
        grid.add(rankField,1,3);

        grid.add(degreeName,0,4);
        grid.add(degreeField,1,4);

        grid.add(seniorityName,0,5);
        grid.add(seniorityField,1,5);

        grid.add(addTeacher,5,6,2,2);

        Scene scene = new Scene(grid,1300,350);
        newFrame.setTitle("Add");
        newFrame.setScene(scene);
        newFrame.show();

    }
}
