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

public class AddAppointmentController implements Initializable {

    @FXML
    private Button addApptAddButton;

    @FXML
    private Button addApptCancelButton;

    @FXML
    private TextField addApptContactTxt;

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
    private ComboBox<?> addApptStartTimeCombo;

    @FXML
    private TextField addApptTitletxt;

    @FXML
    private TextField addApptTypeTxt;

    @FXML
    private TextField addApptUserIdTxt;

    @FXML
    private DatePicker addAptEndDatePick;

    @FXML
    void onActionAddAppt(ActionEvent event) {

    }

    @FXML
    void onActionCancel(ActionEvent event) {

    }

    @FXML
    void onActionFilterEnd(ActionEvent event) {

    }

    @FXML
    void onActionFilterStart(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I'm initialized");
    }
}
