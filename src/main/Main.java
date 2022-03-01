package main;

import DB.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }


    public static void main(String[] args) {
        JDBC.openConnection();

        launch(args);
        JDBC.closeConnection();
    }
}
