package controller;

import DB.AppointmentDB;
import DB.ContactDB;
import helper.Alerts;
import helper.Time;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    ObservableList<Contact> allContactList = ContactDB.select();


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
    private ComboBox<LocalTime> addApptEndTimeCombo;

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

    public AddAppointmentController() throws SQLException {
    }


    @FXML
    void onActionAddAppt(ActionEvent event) throws SQLException {


        if(allFieldsSelected()){
            String title = addApptTitletxt.getText();
            String desc = addApptDescTxt.getText();
            String location = addApptLocationTxt.getText();
            String type = addApptTypeTxt.getText();
            LocalDate localDate = addApptStartDatePick.getValue();
            LocalTime startLt = addApptStartTimeCombo.getValue();
            LocalTime endLt = addApptEndTimeCombo.getValue();
            LocalDateTime start = LocalDateTime.of(localDate, startLt);
            LocalDateTime end = LocalDateTime.of(localDate, endLt);
            int custId = Integer.valueOf(addApptCustIdTxt.getText());
            int userId = Integer.valueOf(addApptUserIdTxt.getText());
            Contact selectedContact = addApptContactCombo.getValue();
            int contactId = selectedContact.getContactId();

//            ZonedDateTime startLocal = ZonedDateTime.of(start, ZoneId.systemDefault());
//            ZonedDateTime endLocal = ZonedDateTime.of(end, ZoneId.systemDefault());
//            System.out.println("start in local: " + startLocal);
//            System.out.println("end in local: " + endLocal);
//
//
//            ZonedDateTime startUtcZdt = Time.localToUtc(startLocal);
//            ZonedDateTime endUtcZdt = Time.localToUtc(endLocal);
//            System.out.println("start in utc: " + startUtcZdt);
//            System.out.println("end in utc: " + endUtcZdt);
//
//            LocalDateTime startUtcLdt = startUtcZdt.toLocalDateTime();
//            LocalDateTime endUtcLdt = endUtcZdt.toLocalDateTime();

            if(end.isBefore(start) || end.isEqual(start)){
                Alert badTimeAlert = Alerts.customErrorAlert("Start time must be before the end of the appointment");
                badTimeAlert.showAndWait();
            } else {
                if(!hasOverlap(start, end, custId)){

                    if(localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY){
                        Alert notBusHours = Alerts.customErrorAlert("Business hours don't include weekends. Please chose another date.");
                        notBusHours.showAndWait();
                    } else{
                        addApptToDB(title, desc, location, type, start, end, custId, userId, contactId);
                        try {
                            navigateViews(mainMenuPath, event);
                        }catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                } else {
                    System.out.println("your appointment overlaps");
                }
            }

        } else {
            Alert alert = Alerts.customErrorAlert("You're missing something");
            alert.showAndWait();
        }


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
            LocalTime thisHalfTime = LocalTime.of(i, 30);
            LocalDateTime easternLdt = LocalDateTime.of(selectedDate, thisTime);
            LocalDateTime easternHalfLdt = LocalDateTime.of(selectedDate, thisHalfTime);

            ZoneId localZone = ZoneId.systemDefault();
            ZonedDateTime easternZdt = ZonedDateTime.of(easternLdt, ZoneId.of("America/New_York"));
            ZonedDateTime easternHalfZdt = ZonedDateTime.of(easternHalfLdt, ZoneId.of("America/New_York"));
            ZonedDateTime localZdt = Time.easternToLocalSys(easternZdt);
            ZonedDateTime localHalfZdt = Time.easternToLocalSys(easternHalfZdt);

            scheduleTimes.add(localZdt.toLocalTime());
            scheduleTimes.add(localHalfZdt.toLocalTime());
        }

    }

    public boolean allFieldsSelected(){
        if (addApptTitletxt.getText().isEmpty() ||
            addApptDescTxt.getText().isEmpty() ||
                addApptLocationTxt.getText().isEmpty() ||
                addApptContactCombo.getValue() == null ||
                addApptTypeTxt.getText().isEmpty() ||
                addApptCustIdTxt.getText().isEmpty() ||
                addApptUserIdTxt.getText().isEmpty() ||
                addApptStartDatePick.getValue() == null ||
                addApptStartTimeCombo.getValue() == null ||
                addApptEndTimeCombo.getValue() == null){
            System.out.println("missed a field");
            return false;
        } else {
            System.out.println("all fields have info");
            return true;
        }

    }

    public void addApptToDB(String title, String desc, String location, String type,
                            LocalDateTime start, LocalDateTime end, int custId,
                            int userId, int contactId) throws SQLException {
        try {
            AppointmentDB.insert(title, desc, location, type, start, end, custId, userId, contactId);
            System.out.println("appointment added to the db");
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean hasOverlap(LocalDateTime aStart, LocalDateTime aEnd, int currCustId) throws SQLException {

        ObservableList<Appointment> custAppts = AppointmentDB.selectByCust(currCustId);
        ArrayList<Appointment> overlap = new ArrayList<Appointment>();
        custAppts.forEach(appointment -> {
            //check current appointment for overlap with appointment argument


            LocalDateTime bStart = appointment.getStartDateTime();

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

        addApptEndTimeCombo.setItems(scheduleTimes);
        addApptEndTimeCombo.setVisibleRowCount(5);
        addApptEndTimeCombo.setPromptText("End time...");

        addApptContactCombo.setItems(allContactList);

        System.out.println("I'm initialized");
    }
}
