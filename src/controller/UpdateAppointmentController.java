package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateAppointmentController implements Initializable {

    @FXML
    private Button updateApptCancelButton;

    @FXML
    private TextField updateApptContactTxt;

    @FXML
    private TextField updateApptCustIdTxt;

    @FXML
    private Button updateApptDeleteButton;

    @FXML
    private TextField updateApptDescTxt;

    @FXML
    private DatePicker updateApptEndDatePick;

    @FXML
    private ComboBox<?> updateApptEndTimeCombo;

    @FXML
    private TextField updateApptIdTxt;

    @FXML
    private TextField updateApptLocationTxt;

    @FXML
    private DatePicker updateApptStartDatePick;

    @FXML
    private ComboBox<?> updateApptStartTimeCombo;

    @FXML
    private TextField updateApptTitleTxt;

    @FXML
    private TextField updateApptTypeTxt;

    @FXML
    private Button updateApptUpdateButton;

    @FXML
    private TextField updateApptUserIdTxt;

    @FXML
    void onActionCancel(ActionEvent event) {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I'm initialized");
    }
}
