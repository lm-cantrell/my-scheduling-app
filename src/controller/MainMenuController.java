package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    private Button addAppointmentButton;

    @FXML
    private Button addCustomerButton;

    @FXML
    private RadioButton allApptsRadioButton;

    @FXML
    private ToggleGroup appointmentFilter;

    @FXML
    private Button logoutButton;

    @FXML
    private Button modifyAppointmentButton;

    @FXML
    private Button modifyCustButton;

    @FXML
    private RadioButton monthApptFilterButton;

    @FXML
    private Button reportsButton;

    @FXML
    private RadioButton weekApptFilterButton;

    @FXML
    void onActionExit(ActionEvent event) {

    }

    @FXML
    void onActionNavAddAppointment(ActionEvent event) {

    }

    @FXML
    void onActionNavAddCust(ActionEvent event) {

    }

    @FXML
    void onActionNavModifyAppointment(ActionEvent event) {

    }

    @FXML
    void onActionNavModifyCust(ActionEvent event) {

    }

    @FXML
    void onActionNavReports(ActionEvent event) {

    }

    @FXML
    void onActionShowAll(ActionEvent event) {

    }

    @FXML
    void onActionShowByMonth(ActionEvent event) {

    }

    @FXML
    void onActionShowByWeek(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I'm initialized");
    }
}
