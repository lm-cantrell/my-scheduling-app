package main;

import DB.JDBC;
import controller.AddAppointmentController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Appointment;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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


//        LocalDateTime ldtTest = LocalDateTime.now();
//        LocalDateTime ldtTest2 = LocalDateTime.now();

        LocalDate ldTest = LocalDate.of(2020, 5, 28);
        LocalTime ltTest = LocalTime.of(22, 30);
        LocalTime ltTest2 = LocalTime.of(23, 30);

        LocalDateTime ldtTest = LocalDateTime.of(ldTest, ltTest);
        LocalDateTime ldtTest2 = LocalDateTime.of(ldTest, ltTest2);

        Appointment testAppointment= new Appointment(5, "title", "desc", "loc", "type", ldtTest, ldtTest2, 1, 1, 1);
        boolean hasAnOverlap = AddAppointmentController.hasOverlap(testAppointment);

        if(hasAnOverlap){
            System.out.println("The appointment overlaps");
        } else {
            System.out.println("No overlap found");
        }


        launch(args);

        JDBC.closeConnection();
    }
}
