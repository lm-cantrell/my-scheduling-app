package controller;

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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateAppointmentController implements Initializable {

    Stage stage;
    Parent scene;

    String mainMenuPath = "/view/MainMenu.fxml";

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I'm initialized");
    }
}
