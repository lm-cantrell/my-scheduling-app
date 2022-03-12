package controller;

import DB.AppointmentDB;
import helper.Time;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    Stage stage;
    Parent scene;

    String mainMenuPath = "/view/MainMenu.fxml";
    ObservableList<LocalTime> scheduleTimes = FXCollections.observableArrayList();




    @FXML
    private Button addApptAddButton;

    @FXML
    private Button addApptCancelButton;

    @FXML
    private ComboBox<Contact> addApptContactCombo;

    @FXML
    private TextField addApptCustIdTxt;

    @FXML
    private TextField addApptDescTxt;

    @FXML
    private ComboBox<?> addApptEndTimeCombo;

    @FXML
    private TextField addApptIdTxt;

    @FXML
    private TextField addApptLocationTxt;

    @FXML
    private DatePicker addApptStartDatePick;

    @FXML
    private ComboBox<LocalTime> addApptStartTimeCombo;

    @FXML
    private TextField addApptTitletxt;

    @FXML
    private TextField addApptTypeTxt;

    @FXML
    private TextField addApptUserIdTxt;


    @FXML
    void onActionAddAppt(ActionEvent event) {

    }

    @FXML
    void onActionCancel(ActionEvent event) {
        try {
            navigateViews(mainMenuPath, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void onActionFilterStart(ActionEvent event) {

    }

    public void navigateViews(String viewPath, ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(viewPath));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void generateTimesList() {
        for ( int i = 8; i < 23; i++) {
            LocalDate selectedDate = addApptStartDatePick.getValue();
            LocalTime thisTime = LocalTime.of(i, 0);
            LocalDateTime easternLdt = LocalDateTime.of(selectedDate, thisTime);
            System.out.println(easternLdt);


            ZoneId localZone = ZoneId.systemDefault();
            ZonedDateTime easternZdt = ZonedDateTime.of(easternLdt, ZoneId.of("America/New_York"));
            ZonedDateTime localZdt = Time.easternToLocalSys(easternZdt);



            scheduleTimes.add(localZdt.toLocalTime());
        }

    }

//    public boolean allFieldsSelected(){
//        if (addApptTitletxt.getText().isEmpty() ||
//            addApptDescTxt.getText().isEmpty() ||
//                addApptLocationTxt.getText().isEmpty() ||
//
//        ){}
//    }

    public static boolean hasOverlap(Appointment appt) throws SQLException {
        int currCustId = appt.getAssocCustId();
        ObservableList<Appointment> custAppts = AppointmentDB.selectByCust(currCustId);
        ArrayList<Appointment> overlap = new ArrayList<Appointment>();
        custAppts.forEach(appointment -> {
            //check current appointment for overlap with appointment argument

            LocalDateTime aStart = appt.getStartDateTime();
            LocalDateTime bStart = appointment.getStartDateTime();
            LocalDateTime aEnd = appt.getEndDateTime();
            LocalDateTime bEnd = appointment.getEndDateTime();
            if((aStart.isAfter(bStart) || aStart.isEqual(bStart)) && (aStart.isBefore(bEnd))){
                overlap.add(appointment);
            }
            if((aEnd.isAfter(bStart)) && (aEnd.isBefore(bEnd) || aEnd.isEqual(bEnd))){
                overlap.add(appointment);
            }
            if((aStart.isBefore(bStart) || aStart.isEqual(bStart)) && (aEnd.isAfter(bEnd) || aEnd.isEqual(bEnd))){
                overlap.add(appointment);
            }
        });

        if(overlap.size() > 0){
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        addApptStartDatePick.setValue(LocalDate.now());


        generateTimesList();
        //convert from eastern to Local system time to display

        //currently not in local time
        addApptStartTimeCombo.setItems(scheduleTimes);
        addApptStartTimeCombo.setVisibleRowCount(5);
        addApptStartTimeCombo.setPromptText("Start time...");

        System.out.println("I'm initialized");
    }
}
