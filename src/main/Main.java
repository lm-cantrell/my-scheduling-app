package main;

import DB.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

/** Main class of the application.
 * @author Lisa Cantrell
 * */

public class Main extends Application {

    /** Loads the stage and screen that display the GUI.
     * @param stage path and configuration of the display */

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        stage.setTitle("Appointment Scheduling System");
        stage.setScene(new Scene(root, 650, 600));
        stage.show();
    }

    /** The entry point of the application. Launches command line args. Opens and closes the database connection.
     * @param args command line arguments. */


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

//        LocalDate ldTest = LocalDate.of(2020, 5, 28);
//        LocalTime ltTest = LocalTime.of(22, 30);
//        LocalTime ltTest2 = LocalTime.of(23, 30);
//
//        LocalDateTime ldtTest = LocalDateTime.of(ldTest, ltTest);
//        LocalDateTime ldtTest2 = LocalDateTime.of(ldTest, ltTest2);
//
//        Appointment testAppointment= new Appointment(5, "title", "desc", "loc", "type", ldtTest, ldtTest2, 1, 1, 1);
//        boolean hasAnOverlap = AddAppointmentController.hasOverlap(testAppointment);
//
//        if(hasAnOverlap){
//            System.out.println("The appointment overlaps");
//        } else {
//            System.out.println("No overlap found");
//        }

        // test contact select
//        ContactDB.select();
//        LocalDateTime curr = LocalDateTime.now();
//        LocalDateTime start = LocalDateTime.of(2022, 3, 17, 11, 40);
//        LocalDateTime end = LocalDateTime.of(2022, 3, 17, 12, 40);
//        Time.getWeek(curr);

//        AppointmentDB.insert("testAlert", "test", "now", "test", start, end, 1, 1, 1);


        launch(args);

        JDBC.closeConnection();
    }
}
