package main;

import DB.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }


    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();


        //test cust insert
//        CustomerDB.insert("lisa", "123 place", "444", "555-555-5555", 1);

//        test cust update
//        CustomerDB.update(5, "lisaC", "567 road", "444", "555-555-5555", 1);

//        test cust delete
//        CustomerDB.delete(5);

//        test cust select
//        CustomerDB.select();


        LocalDateTime ldtTest = LocalDateTime.now();
        LocalDateTime ldtTest2 = LocalDateTime.now();

        //test appt insert

//        AppointmentDB.insert("test title", "test description", "test location", "test type",
//                ldtTest, ldtTest2, 1, 1, 1);
        //test appt update
//        AppointmentDB.update(4, "update test", "updated description", "updated location", "updated type",
//                ldtTest, ldtTest2, 1, 1, 1);

        //test appt del
//        AppointmentDB.delete(4);

        //test appt select
//        AppointmentDB.select(1);

        //test user get id from login
//        UserDB.getUserIDFromLogin("test", "test");


        //test country select
//        CountryDB.select();

        //test division select;
//        DivisionDB.select("Arkansas");

        launch(args);

        JDBC.closeConnection();
    }
}
