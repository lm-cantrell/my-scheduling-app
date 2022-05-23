package controller;

import DB.AppointmentDB;
import DB.UserDB;
import helper.Alerts;
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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;



/** LoginController controls functionality and interactivity of login screen.
 * @author Lisa Cantrell
 * */
public class LoginController implements Initializable {

    Stage stage;
    Parent scene;

    String mainMenuPath = "/view/MainMenu.fxml";
    private ObservableList<Appointment> allAppointments = AppointmentDB.select();

    @FXML
    private Button exitButton;

    @FXML
    private Button loginButton;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField passwordText;

    @FXML
    private Label regionLabel;

    @FXML
    private Label regionDetailsLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField usernameText;

    public LoginController() throws SQLException {
    }


/** onActionExitApplication creates an alert to confirm exit of application.
 * @param event  */
    @FXML
    void onActionExitApplication(ActionEvent event) {
        Alert alert = Alerts.confirmExit();
        if(alert.showAndWait().get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    /** onActionLogin checks username and password combination and navigates to main menu on success.
     * @param event  */

    @FXML
    void onActionLogin(ActionEvent event) {


        try {
            String userName = usernameText.getText();
            String password = passwordText.getText();

            int currUserId = UserDB.getUserIDFromLogin(userName, password);

            String filename = "login_activity.txt";
            FileWriter fwriter = new FileWriter(filename, true);
            PrintWriter outputFile = new PrintWriter(fwriter);
            LocalDateTime loginDT = LocalDateTime.now();
            String successMsg = "Attempt to login with username: " + userName + " at " + loginDT + " was successful.";
            String failMsg = "Attempt to login with username: " + userName + " at " + loginDT + " failed.";

            if( currUserId == 0) {
                Locale locale = Locale.getDefault();
//                Locale locale = Locale.FRANCE;
                String alertString;
                if(locale == Locale.FRANCE){
                    alertString = "Aucun utilisateur avec ces informations d'identification n'existe. Veuillez entrer des informations d'identification valides";
                } else {
                    alertString = "No user with those credentials exists. Please enter valid credentials";
                }
                outputFile.println(failMsg);
                outputFile.close();
                Alert alert = Alerts.customErrorAlert(alertString);
                alert.show();
            } else {
                outputFile.println(successMsg);
                outputFile.close();
                checkFifteenMinWindow();
                navigateViews(mainMenuPath, event);
            }


        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    /** checkFifteenMinWindow checks appointments in database for a start time within 15 minutes of login. */
    public void checkFifteenMinWindow(){
        LocalDate today = LocalDate.now();
        ArrayList<Appointment> windowAppts = new ArrayList<Appointment>();
        LocalTime current = LocalTime.now();
        LocalTime endWindow = current.plusMinutes(15);
        for(Appointment appt : allAppointments){
            LocalDateTime apptStart = appt.getStartDateTime();
            LocalDate apptDate = apptStart.toLocalDate();
            if(apptDate.isEqual(today) && (apptStart.toLocalTime().isAfter(current) && apptStart.toLocalTime().isBefore(endWindow))){
                windowAppts.add(appt);
            }
        }
        if(!windowAppts.isEmpty()){
            int messageId = windowAppts.get(0).getAppointmentId();
            LocalDate messageDate = windowAppts.get(0).getStartDateTime().toLocalDate();
            LocalTime messageTime = windowAppts.get(0).getStartDateTime().toLocalTime();
            String alertMessage = "There is an appointment scheduled within the next 15 minutes. ID: " + messageId + " | Scheduled date and time: " + messageDate + " " + messageTime;
            Alert hasAppt = Alerts.customInfoAlert(alertMessage);
            hasAppt.showAndWait();
        } else {
            Alert noAppt = Alerts.customInfoAlert("No upcoming appointments");
            noAppt.showAndWait();
        }
    }

    /** navigateViews navigates to screen found at provided path.
     * @param viewPath
     * @param event
     * */
    public void navigateViews(String viewPath, ActionEvent event) throws IOException{
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(viewPath));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** initialize method sets up the stage for display.
     * @param url
     * @param resourceBundle
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Locale locale = Locale.getDefault();
//            Locale locale = Locale.FRANCE;
            ZoneId thisZone = ZoneId.systemDefault();
            resourceBundle = ResourceBundle.getBundle("assets/Nat", locale);

            regionLabel.setText(resourceBundle.getString("region"));
            regionDetailsLabel.setText(String.valueOf(thisZone));
            exitButton.setText(resourceBundle.getString("cancel"));
            loginButton.setText(resourceBundle.getString("login"));
            passwordLabel.setText(resourceBundle.getString("password"));
            titleLabel.setText(resourceBundle.getString("title"));
            usernameLabel.setText(resourceBundle.getString("username"));

            String alertText = resourceBundle.getString("wrong");

        } catch (MissingResourceException ex){
            ex.printStackTrace();
        }



        System.out.println("I'm initialized");
    }
}
