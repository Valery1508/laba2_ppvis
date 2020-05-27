package view;

import controller.Controller;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import model.Teacher;

import java.util.List;

public class TeacherTable {

    private TableView<Teacher> table;
    private Controller controller;
    private boolean used = false;
    private Button nextPageBtn, previousPageBtn, lastPageBtn, firstPageBtn, choiceBtn;
    private int rowsPerPage = 10;
    private int thisPageNumber = 1;
    private int lastPage;
    private GridPane grid;
    private Label thisPageLabel, descriptionLabel;
    private TextField rowsChoice;

    public TeacherTable(Controller controller) {
        this.table = new TableView<Teacher>();
        this.controller = controller;
        this.used = false;
        this.grid = new GridPane();
        this.thisPageLabel = new Label();
        this.descriptionLabel = new Label();
        this.rowsChoice = new TextField();
    }

    public void makeTable(List<Teacher> list){
        controller = new Controller(list);
        numeration();
        makePage(controller.getPage(thisPageNumber, rowsPerPage));
    }

    private  void makePage(List<Teacher> list) {

        if(!used) {
            TableColumn<Teacher, String> facultyName = new TableColumn<Teacher, String>("Факультет");
            facultyName.setCellValueFactory(new PropertyValueFactory<>("faculty"));

            TableColumn<Teacher, String> departmentName = new TableColumn<Teacher, String>("Название кафедры");
            departmentName.setCellValueFactory(new PropertyValueFactory<Teacher, String>("department"));

            TableColumn<Teacher, String> teacher = new TableColumn<Teacher, String>("Преподаватель");

            TableColumn<Teacher, String> teacherFName = new TableColumn<Teacher, String>("Имя");
            teacherFName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Teacher, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Teacher, String> teacherStringCellDataFeatures) {
                    return new ReadOnlyObjectWrapper<>(teacherStringCellDataFeatures.getValue().getTeacherFIO().getFirstNameTeacher());
                }
            });

            TableColumn<Teacher, String> teacherSName = new TableColumn<Teacher, String>("Фамилия");
            teacherSName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Teacher, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Teacher, String> teacherStringCellDataFeatures) {
                    return new ReadOnlyObjectWrapper<>(teacherStringCellDataFeatures.getValue().getTeacherFIO().getSecondNameTeacher());
                }
            });

            TableColumn<Teacher, String> teacherOName = new TableColumn<Teacher, String>("Отчество");
            teacherOName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Teacher, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Teacher, String> teacherStringCellDataFeatures) {
                    return new ReadOnlyObjectWrapper<>(teacherStringCellDataFeatures.getValue().getTeacherFIO().getOtchestvoTeacher());
                }
            });
            teacher.getColumns().addAll(teacherFName, teacherSName, teacherOName);

            TableColumn<Teacher, String> academicRankName = new TableColumn<Teacher, String>("Ученое звание");
            academicRankName.setCellValueFactory(new PropertyValueFactory<Teacher, String>("academicRank"));

            TableColumn<Teacher, String> academicDegreeName = new TableColumn<Teacher, String>("Ученая степень");
            academicDegreeName.setCellValueFactory(new PropertyValueFactory<Teacher, String>("academicDegree"));

            TableColumn<Teacher, String> seniorityName = new TableColumn<Teacher, String>("Стаж работы");
            seniorityName.setCellValueFactory(new PropertyValueFactory<Teacher, String>("seniority"));

            grid.setPadding(new Insets(20));
            grid.setHgap(25);
            grid.setVgap(15);
            grid.add(this.table,0,0,16,5);
            grid.add(this.firstPageBtn,8,6);
            grid.add(this.previousPageBtn,9,6);
            grid.add(this.thisPageLabel,10,6);
            grid.add(this.nextPageBtn,11,6);
            grid.add(this.lastPageBtn,12,6);
            grid.add(this.rowsChoice,1,6);
            grid.add(this.choiceBtn,2,6);
            grid.add(this.descriptionLabel,0,6);
            table.getColumns().addAll(facultyName, departmentName, teacher, academicRankName, academicDegreeName, seniorityName);
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            used = true;
        }
        ObservableList<Teacher> addList= FXCollections.observableArrayList(list);
        table.setItems(addList);
    }

    public void numeration(){

        rowsChoice.setMaxWidth(40);
        thisPageNumber = 1;
        lastPage = controller.getLastPage(rowsPerPage);
        descriptionLabel.setText("Number of entries: " + controller.getSize() + "  ");

        choiceBtn = new Button("Split");
        choiceBtn.setMinWidth(100);
        choiceBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    rowsPerPage = Integer.parseInt(rowsChoice.getText());
                    thisPageNumber = 1;
                    makePage(controller.getPage(thisPageNumber, rowsPerPage));
                    lastPage = controller.getLastPage(rowsPerPage);
                    thisPageLabel.setText(thisPageNumber + "/" + lastPage);
                }
                catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Введите число!");
                    alert.showAndWait();
                }
            }
        });

        nextPageBtn=new Button(">");
        nextPageBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (thisPageNumber + 1 <= lastPage) {
                    thisPageNumber++;
                    makePage(controller.getPage(thisPageNumber, rowsPerPage));
                    thisPageLabel.setText(thisPageNumber + "/" + lastPage);
                }
            }
        });

        previousPageBtn=new Button("<");
        previousPageBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (thisPageNumber -1 > 0) {
                    thisPageNumber--;
                    makePage(controller.getPage(thisPageNumber, rowsPerPage));
                    thisPageLabel.setText(thisPageNumber + "/" + lastPage);
                }
            }
        });

        firstPageBtn=new Button("1");
        firstPageBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (thisPageNumber != 1) {
                    thisPageNumber = 1;
                    makePage(controller.getPage(thisPageNumber, rowsPerPage));
                    thisPageLabel.setText(thisPageNumber + "/" + lastPage);
                }
            }
        });

        lastPageBtn=new Button(" >> ");
        lastPageBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (thisPageNumber != lastPage) {
                    thisPageNumber = lastPage;
                    makePage(controller.getPage(thisPageNumber, rowsPerPage));
                    thisPageLabel.setText(thisPageNumber + "/" + lastPage);
                }
            }
        });
        thisPageLabel.setText(thisPageNumber + "/" + lastPage);

    }

    public  GridPane getTable() {
        return this.grid;
    }

}
