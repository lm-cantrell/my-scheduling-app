package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    Stage stage;
    Parent scene;

    String addCustPath = "/view/AddCustomer.fxml";
    String updateCustPath = "/view/UpdateCustomer.fxml";
    String addApptPath = "/view/AddAppointment.fxml";
    String updateApptPath = "/view/UpdateAppointment.fxml";
    String reportsPath = "/view/Reports.fxml";


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

        //implement exit alert/confirmation
        System.exit(0);
    }

    @FXML
    void onActionNavAddAppointment(ActionEvent event) {
        try {
            navigateViews(addApptPath, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionNavAddCust(ActionEvent event) {
        try {
            navigateViews(addCustPath, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionNavModifyAppointment(ActionEvent event) {
        try {
            navigateViews(updateApptPath, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionNavModifyCust(ActionEvent event) {
        try {
            navigateViews(updateCustPath, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionNavReports(ActionEvent event) {
        try {
            navigateViews(reportsPath, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
