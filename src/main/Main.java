package main;

import DB.CustomerDB;
import DB.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }


    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();


        //test insert
        CustomerDB.insert("lisa", "123 place", "444", "555-555-5555", 1);

//        test update
//        CustomerDB.update(4, "lisaC", "567 road", "444", "555-555-5555", 1);

//        test delete
//        CustomerDB.delete(4);

//        test select
//        CustomerDB.select(3);

        launch(args);

        JDBC.closeConnection();
    }
}
