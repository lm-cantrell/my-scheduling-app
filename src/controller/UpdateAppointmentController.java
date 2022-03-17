package controller;

import DB.ContactDB;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class UpdateAppointmentController implements Initializable {

    Stage stage;
    Parent scene;

    String mainMenuPath = "/view/MainMenu.fxml";
    ObservableList<LocalTime> scheduleTimes = FXCollections.observableArrayList();
    ObservableList<Contact> allContactList = ContactDB.select();

    @FXML
    private Button updateApptCancelButton;

    @FXML
    private ComboBox<Contact> updateApptContactCombo;

    @FXML
    private TextField updateApptCustIdTxt;

    @FXML
    private Button updateApptDeleteButton;

    @FXML
    private TextField updateApptDescTxt;

    @FXML
    private DatePicker updateApptEndDatePick;

    @FXML
    private ComboBox<LocalTime> updateApptEndTimeCombo;

    @FXML
    private TextField updateApptIdTxt;

    @FXML
    private TextField updateApptLocationTxt;

    @FXML
    private DatePicker updateApptStartDatePick;

    @FXML
    private ComboBox<LocalTime> updateApptStartTimeCombo;

    @FXML
    private TextField updateApptTitleTxt;

    @FXML
    private TextField updateApptTypeTxt;

    @FXML
    private Button updateApptUpdateButton;

    @FXML
    private TextField updateApptUserIdTxt;

    public UpdateAppointmentController() throws SQLException {
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
    void onActionDeleteAppt(ActionEvent event) {

    }

    @FXML
    void onActionFilterEnd(ActionEvent event) {

    }

    @FXML
    void onActionFilterStart(ActionEvent event) {

    }

    @FXML
    void onActionUpdateAppt(ActionEvent event) {

    }

    public void navigateViews(String viewPath, ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(viewPath));
        stage.setScene(new Scene(scene));
        stage.show();
    }

//    public void generateTimesList() {
//        for ( int i = 8; i < 23; i++) {
//            LocalDate selectedDate = updateApptStartDatePick.getValue();
//            LocalTime thisTime = LocalTime.of(i, 0);
//            LocalTime thisHalfTime = LocalTime.of(i, 30);
//            LocalDateTime easternLdt = LocalDateTime.of(selectedDate, thisTime);
//            LocalDateTime easternHalfLdt = LocalDateTime.of(selectedDate, thisHalfTime);
//
//            ZoneId localZone = ZoneId.systemDefault();
//            ZonedDateTime easternZdt = ZonedDateTime.of(easternLdt, ZoneId.of("America/New_York"));
//            ZonedDateTime easternHalfZdt = ZonedDateTime.of(easternHalfLdt, ZoneId.of("America/New_York"));
//            ZonedDateTime localZdt = Time.easternToLocalSys(easternZdt);
//            ZonedDateTime localHalfZdt = Time.easternToLocalSys(easternHalfZdt);
//
//            scheduleTimes.add(localZdt.toLocalTime());
//            scheduleTimes.add(localHalfZdt.toLocalTime());
//        }
//
//    }

    public void sendAppt(Appointment appointment, ObservableList times) throws SQLException {
        updateApptIdTxt.setText(String.valueOf(appointment.getAppointmentId()));
        updateApptTitleTxt.setText(appointment.getTitle());
        updateApptDescTxt.setText(appointment.getDescription());
        updateApptLocationTxt.setText(appointment.getLocation());
        updateApptContactCombo.setItems(allContactList);
        int contactId = appointment.getAssocContactId();
        Contact selectedContact = ContactDB.select(contactId);
        updateApptContactCombo.setValue(selectedContact);


        updateApptTypeTxt.setText(appointment.getType());
        updateApptCustIdTxt.setText(String.valueOf(appointment.getAssocCustId()));
        updateApptUserIdTxt.setText(String.valueOf(appointment.getAssocUserId()));
        LocalDate startDate = appointment.getStartDateTime().toLocalDate();
        LocalTime startTime = appointment.getStartDateTime().toLocalTime();
        LocalTime endTime = appointment.getEndDateTime().toLocalTime();
        updateApptStartDatePick.setValue(startDate);
        updateApptStartTimeCombo.setItems(times);
        updateApptEndTimeCombo.setItems(times);
        updateApptStartTimeCombo.setValue(startTime);
        updateApptEndTimeCombo.setValue(endTime);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
