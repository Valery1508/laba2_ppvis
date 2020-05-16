package view;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        List<Teacher> list =new ArrayList<Teacher>();
        Controller controller= new Controller(list);

        MenuFrame menuFrame = new MenuFrame(controller, primaryStage);
        menuFrame.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
