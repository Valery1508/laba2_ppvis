package view;

import com.sun.deploy.net.MessageHeader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import controller.Controller;

import java.awt.*;
import java.io.File;
import java.util.List;

public class MenuFrame {

    private Controller controller;
    private Stage stage;
    private TeacherTable teacherTable;
    private FileChooser FileChooser;
    private GridPane gridPane;

    public MenuFrame(Controller controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
        this.teacherTable = new TeacherTable(this.controller);
    }

    //Controller controller = new Controller();
    /*private TextField numberOfRecordsOnPageField;
    private Label numberOfRecordsOnPageArea;
    private VBox groupOfElements;
    private HBox numbers;
    private String path;

    private Button showButton, addButton, searchButton, deleteButton, chooseDirectoryButton;*/
    private boolean tableCreated = false;
    private String pathFile;    // имя файла
    //FileChooser fileChooser;
    private Desktop desktop = Desktop.getDesktop();

    public void show() {
        stage.setTitle("menu");

        Button openButton = new Button("Open file"); //open & load file
        openButton.setLayoutX(670);
        openButton.setLayoutY(230);
        openButton.setMinSize(100,40);

        Button searchButton = new Button("Search"); //search
        searchButton.setLayoutX(670);
        searchButton.setLayoutY(300);
        searchButton.setMinSize(100,40);

        Button addButton = new Button("Add");
        searchButton.setLayoutX(670);
        searchButton.setLayoutY(210);
        searchButton.setMinSize(100,40);

        Button deleteButton = new Button("Delete");
        searchButton.setLayoutX(670);
        searchButton.setLayoutY(280);
        searchButton.setMinSize(100,40);

        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: green");
        saveButton.setLayoutX(670);
        saveButton.setLayoutY(350);
        saveButton.setMinSize(100,40);

        openButton.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open XML file");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml files", "*.xml"));
            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {
                List loadResult = controller.load(selectedFile);    //получаем массив из xml файла
                //System.out.println(loadResult);
                teacherTable.makeTable(loadResult);
                pathFile = selectedFile.getName();
            }
        });

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SearchFrame search = new SearchFrame(controller);
                search.searchTeacher();
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DeleteFrame deleteFrame = new DeleteFrame(controller, teacherTable);
                deleteFrame.delete();
            }
        });

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddFrame addFrame = new AddFrame(controller, teacherTable);
                addFrame.addTeacher();
            }
        });

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                //fileChooser.setInitialDirectory(new File("C:/Users/user/IdeaProjects/Lab2/src/sample"));
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml"));
                File file = fileChooser.showSaveDialog(stage);
                if (file != null) {
                    controller.save(file);//"src/sample/work2.xml"
                }
            }
        });

        gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(25);
        gridPane.setVgap(15);
        gridPane.add(openButton,0,0);
        gridPane.add(searchButton,0,1);
        gridPane.add(addButton,0,2);
        gridPane.add(deleteButton,0,3);
        gridPane.add(saveButton,0,4);
        gridPane.add(teacherTable.getTable(), 2, 0, 15, 5);

        Scene scene = new Scene(gridPane, 1100, 600);
        stage.setScene(scene);
        stage.show();

    }


    private void removeTable(){
        //groupOfElements.getChildren().remove(ТУТ БУДЕТ ТВОЯ ТАБЛИЦА);
        tableCreated = false;
    }

}
