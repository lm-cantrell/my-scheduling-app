package controller;

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

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    Stage stage;
    Parent scene;

    String mainMenuPath = "/view/MainMenu.fxml";
    ObservableList<LocalTime> scheduleTimesEastern = FXCollections.observableArrayList();




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
    private ComboBox<LocalTime> addApptStartTimeCombo;

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
        try {
            navigateViews(mainMenuPath, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionFilterEnd(ActionEvent event) {

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
            LocalTime thisTime = LocalTime.of(i, 0);
            scheduleTimesEastern.add(thisTime);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        generateTimesList();

        //convert from eastern to Local system time to display

        //currently not in local time
        addApptStartTimeCombo.setItems(scheduleTimesEastern);
        addApptStartTimeCombo.setVisibleRowCount(5);
        addApptStartTimeCombo.setPromptText("Start time...");

        System.out.println("I'm initialized");
    }
}
