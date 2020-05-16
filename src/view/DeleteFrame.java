package view;

import controller.Controller;
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

public class DeleteFrame {
    private Controller controller;
    private Stage newFrame;
    private TeacherTable table;
    private List<Teacher> deleteList, resultList;

    public DeleteFrame(Controller controller, TeacherTable table) {
        this.controller = controller;
        newFrame = new Stage();
        this.table = table;
    }

    public void delete(){

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
        Button deleteByFacultyAndRank = new Button("Удалить по ученому званию и факультету");
////////////////////
        Label departmentName = new Label("Кафедра");
        ComboBox<String> departmentNames = new ComboBox<>();
        for(String dep:controller.getListOfDepartment()){
            departmentNames.getItems().add(dep);
        }
        Button deleteByDepartment = new Button("Удалить по названию кафедры");
////////////////////
        Label teacherFIO = new Label("Преподаватель");
        Label teacherF = new Label("Имя");
        Label teacherS = new Label("Фамилия");
        Label teacherO = new Label("Отчество");
        TextField teacherFieldF = new TextField();
        TextField teacherFieldS = new TextField();
        TextField teacherFieldO = new TextField();
        Button deleteByFIO = new Button("Удалить по ФИО");
///////////////////
        Label seniorityName = new Label("Стаж работы");
        Label low = new Label("Нижний предел");
        Label high = new Label("Верхний предел");
        TextField seniorityLow = new TextField();
        TextField seniorityHigh = new TextField();
        Button deleteBySeniority = new Button("Удалить по стажу работы");

        deleteByFacultyAndRank.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteList = controller.findByFacultyAndRank(facultyNames.getValue(), rankNames.getValue());
                dialog(deleteList.size());
                table.numeration();
                newFrame.close();
            }
        });

        deleteByDepartment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteList = controller.findByDepartment(departmentNames.getValue());
                controller.deleteTeacher(deleteList);
                dialog(deleteList.size());
                table.numeration();
                newFrame.close();
            }
        });

        deleteByFIO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteList = controller.findByFIO(new TeacherFIO(teacherFieldF.getText(),
                        teacherFieldS.getText(),teacherFieldO.getText()));
                controller.deleteTeacher(deleteList);
                dialog(deleteList.size());
                table.numeration();
                newFrame.close();
            }
        });

        deleteBySeniority.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteList = controller.findBySeniority(Integer.parseInt(seniorityLow.getText()), Integer.parseInt(seniorityHigh.getText()));
                controller.deleteTeacher(deleteList);
                dialog(deleteList.size());
                table.numeration();
                newFrame.close();
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(25);
        grid.setVgap(15);

// by department
        grid.add(departmentName,0,0);
        grid.add(departmentNames,1,0);
        grid.add(deleteByDepartment,2,0);
// by FIO
        //grid.add(teacherFIO,0,1);
        grid.add(deleteByFIO,2,4);
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
        grid.add(deleteByFacultyAndRank,2,7);
// by seniority
        grid.add(seniorityName,0,9);
        grid.add(low,0,10);
        grid.add(seniorityLow,1,10);
        grid.add(high,0,11);
        grid.add(seniorityHigh,1,11);
        grid.add(deleteBySeniority,2,11);


        //grid.add(table.getTable(),5,0,25,15);

        Scene scene = new Scene(grid,1000,600);
        newFrame.setTitle("Удаление");
        newFrame.setScene(scene);
        newFrame.show();
    }

    private void dialog(int counter){
        Label deleted = new Label();
        Label count = new Label();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.add(deleted,0,0);
        grid.add(count,1,0);
        if(counter != 0){
            deleted.setText("Было удалено ");
            count.setText(String.valueOf(counter));
        }
        else{
            deleted.setText(" команд для удаления не обнаружено");
        }
        Scene scene = new Scene(grid,300,150);
        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("Результат удаления");
        dialogWindow.setScene(scene);
        dialogWindow.show();

    }

}
