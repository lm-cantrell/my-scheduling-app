package controller;

import DB.AppointmentDB;
import DB.ContactDB;
import helper.Alerts;
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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
    void onActionFilterStart(ActionEvent event){

    }

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
    void onActionDeleteAppt(ActionEvent event) throws SQLException {

        int id = Integer.valueOf(updateApptIdTxt.getText());
        String type = updateApptTypeTxt.getText();
        Alert alert = Alerts.customConfirmationAlert("Are you sure you want to delete this " + type + " appointment with ID: " + id + "?");
        if(alert.showAndWait().get() == ButtonType.OK){
            int idToDelete = Integer.parseInt(updateApptIdTxt.getText());
            AppointmentDB.delete(idToDelete);

            try {
                navigateViews(mainMenuPath, event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onActionUpdateAppt(ActionEvent event) throws SQLException {
        
        if(allFieldsSelected()){
            Alert alert = Alerts.customConfirmationAlert("Are you sure you want to change this appointment?");
            if(alert.showAndWait().get() == ButtonType.OK){
                String title = updateApptTitleTxt.getText();
                String desc = updateApptDescTxt.getText();
                String location = updateApptLocationTxt.getText();
                String type = updateApptTypeTxt.getText();
                LocalDate localDate = updateApptStartDatePick.getValue();
                LocalTime startLt = updateApptStartTimeCombo.getValue();
                LocalTime endLt = updateApptEndTimeCombo.getValue();
                LocalDateTime start = LocalDateTime.of(localDate, startLt);
                LocalDateTime end = LocalDateTime.of(localDate, endLt);
                int custId = Integer.valueOf(updateApptCustIdTxt.getText());
                int userId = Integer.valueOf(updateApptUserIdTxt.getText());
                Contact selectedContact = updateApptContactCombo.getValue();
                int contactId = selectedContact.getContactId();
                int apptId = Integer.valueOf(updateApptIdTxt.getText());

                if(end.isBefore(start) || end.isEqual(start)){
                    Alert badTimeAlert = Alerts.customErrorAlert("Start time must be before the end of the appointment");
                    badTimeAlert.showAndWait();
                } else {
                    if(!hasOverlap(start, end, custId, apptId)){

                        if(localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY){
                            Alert notBusHours = Alerts.customErrorAlert("Business hours don't include weekends. Please chose another date.");
                            notBusHours.showAndWait();
                        } else {
                            AppointmentDB.update(apptId, title, desc, location, type, start, end, custId, userId, contactId);
                            try {
                                navigateViews(mainMenuPath, event);
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                        }

                    } else {
                        Alert overlapAlert = Alerts.customErrorAlert("There's an overlap with an existing appointment");
                        overlapAlert.showAndWait();
                    }
                }
                
            }
        } else{
            Alert alert = Alerts.customErrorAlert("You're missing something.");
            alert.showAndWait();
        }

    }

    public void navigateViews(String viewPath, ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(viewPath));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public boolean allFieldsSelected(){
        if (updateApptTitleTxt.getText().isEmpty() ||
                updateApptDescTxt.getText().isEmpty() ||
                updateApptLocationTxt.getText().isEmpty() ||
                updateApptContactCombo.getValue() == null ||
                updateApptTypeTxt.getText().isEmpty() ||
                updateApptCustIdTxt.getText().isEmpty() ||
                updateApptUserIdTxt.getText().isEmpty() ||
                updateApptStartDatePick.getValue() == null ||
                updateApptStartTimeCombo.getValue() == null ||
                updateApptEndTimeCombo.getValue() == null){
            Alert alert = Alerts.customErrorAlert("You missed a field.");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }

    }

    public static boolean hasOverlap(LocalDateTime aStart, LocalDateTime aEnd, int currCustId, int inputApptId) throws SQLException {

        ObservableList<Appointment> custAppts = AppointmentDB.selectByCust(currCustId);
        ArrayList<Appointment> overlap = new ArrayList<Appointment>();
        custAppts.forEach(appointment -> {

            LocalDateTime bStart = appointment.getStartDateTime();

            LocalDateTime bEnd = appointment.getEndDateTime();
            if((aStart.isAfter(bStart) || aStart.isEqual(bStart)) && (aStart.isBefore(bEnd))){
                if(appointment.getAppointmentId() != inputApptId){
                    overlap.add(appointment);
                }

            }
            if((aEnd.isAfter(bStart)) && (aEnd.isBefore(bEnd) || aEnd.isEqual(bEnd))){
                if(appointment.getAppointmentId() != inputApptId){
                    overlap.add(appointment);
                }
            }
            if((aStart.isBefore(bStart) || aStart.isEqual(bStart)) && (aEnd.isAfter(bEnd) || aEnd.isEqual(bEnd))){
                if(appointment.getAppointmentId() != inputApptId){
                    overlap.add(appointment);
                }
            }
        });

        if(overlap.size() > 0){
            return true;
        } else {
            return false;
        }
    }

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
