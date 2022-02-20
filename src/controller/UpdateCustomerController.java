package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateCustomerController implements Initializable {

    Stage stage;
    Parent scene;

    String mainMenuPath = "/view/MainMenu.fxml";

    @FXML
    private TextField updateCustAddressTxt;

    @FXML
    private Button updateCustCancelButton;

    @FXML
    private ComboBox<?> updateCustCountryCombo;

    @FXML
    private Button updateCustDeleteButton;

    @FXML
    private ComboBox<?> updateCustDivCombo;

    @FXML
    private TextField updateCustIdTxt;

    @FXML
    private TextField updateCustNameTxt;

    @FXML
    private TextField updateCustPhoneTxt;

    @FXML
    private TextField updateCustPostCodeTxt;

    @FXML
    private Button updateCustUpdateButton;

    @FXML
    void onActionCancel(ActionEvent event) {

        try {
            navigateViews(mainMenuPath, event);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onActionDeleteCust(ActionEvent event) {

    }

    @FXML
    void onActionUpdateCust(ActionEvent event) {

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
