package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Teacher;
import model.TeacherFIO;

import java.util.List;

public class SearchFrame {
    private Controller controller;
    private List<Teacher> searchList;
    private TeacherTable table;

    public SearchFrame(Controller controller) {
        this.controller = controller;
        table = new TeacherTable(new Controller(FXCollections.observableArrayList()));
    }

    public void searchTeacher(){

        Label facultyName = new Label("Факультет");
        ComboBox<String> facultyNames = new ComboBox<>();
            for(String fac:controller.getListOfFaculty()){
                facultyNames.getItems().add(fac);
            }
        Label rankName = new Label("Ученое звание");
        ComboBox<String> rankNames = new ComboBox<>();
            for(String rank:controller.getListOfRank()){
                rankNames.getItems().add(rank);
            }
        Button searchByFacultyAndRank = new Button("Найти по уч. званию и факультету");
////////////////////
        Label departmentName = new Label("Кафедра");
        ComboBox<String> departmentNames = new ComboBox<>();
            for(String dep:controller.getListOfDepartment()){
                departmentNames.getItems().add(dep);
            }
        Button searchByDepartment = new Button("Найти по кафедрe");
            searchByDepartment.setMinWidth(170);
////////////////////
        Label teacherFIO = new Label("Преподаватель");
        Label teacherF = new Label("Имя");
        Label teacherS = new Label("Фамилия");
        Label teacherO = new Label("Отчество");
        TextField teacherFieldF = new TextField();
        TextField teacherFieldS = new TextField();
        TextField teacherFieldO = new TextField();
        Button searchByFIO = new Button("Найти по ФИО");
        searchByFIO.setMinWidth(170);
///////////////////
        Label seniorityName = new Label("Стаж работы");
        Label low = new Label("Нижний предел");
        Label high = new Label("Верхний предел");
        TextField seniorityLow = new TextField();
        TextField seniorityHigh = new TextField();
        Button searchBySeniority = new Button("Найти по стажу работы");

        searchByFacultyAndRank.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                searchList = controller.findByFacultyAndRank(facultyNames.getValue(), rankNames.getValue());
                table.makeTable(searchList);
            }
        });

        searchByDepartment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                searchList = controller.findByDepartment(departmentNames.getValue());
                table.makeTable(searchList);
            }
        });

        searchByFIO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                searchList=controller.findByFIO(new TeacherFIO(teacherFieldF.getText(),
                        teacherFieldS.getText(),teacherFieldO.getText()));
                table.makeTable(searchList);
            }
        });

        searchBySeniority.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                searchList = controller.findBySeniority(Integer.parseInt(seniorityLow.getText()), Integer.parseInt(seniorityHigh.getText()));
                table.makeTable(searchList);
            }
        });

        GridPane grid=new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(25);
        grid.setVgap(15);

// by department
        grid.add(departmentName,0,0);
        grid.add(departmentNames,1,0);
        grid.add(searchByDepartment,2,0);
// by FIO
        //grid.add(teacherFIO,0,1);
            grid.add(searchByFIO,2,4);
        grid.add(teacherF,0,2);
        grid.add(teacherFieldF,1,2);
        grid.add(teacherS,0,3);
        grid.add(teacherFieldS,1,3);
        grid.add(teacherO,0,4);
        grid.add(teacherFieldO,1,4);
// by faculty and rank
        grid.add(facultyName,0,6);
        grid.add(facultyNames,1,6);
        grid.add(rankName,0,7);
        grid.add(rankNames,1,7);
        grid.add(searchByFacultyAndRank,2,7);
// by seniority
        grid.add(seniorityName,0,9);
        grid.add(low,0,10);
        grid.add(seniorityLow,1,10);
        grid.add(high,0,11);
        grid.add(seniorityHigh,1,11);
        grid.add(searchBySeniority,2,11);


        grid.add(table.getTable(),5,0,25,15);

        Scene scene = new Scene(grid,1800,600);
        Stage newWindow = new Stage();
        newWindow.setTitle("Поиск");
        newWindow.setScene(scene);
        newWindow.show();


    }

}
