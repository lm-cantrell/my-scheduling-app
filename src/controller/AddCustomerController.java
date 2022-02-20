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

public class AddCustomerController implements Initializable {

    Stage stage;
    Parent scene;

    String mainMenuPath = "/view/MainMenu.fxml";

    @FXML
    private Button addCustAddButton;

    @FXML
    private TextField addCustAdressTxt;

    @FXML
    private Button addCustCancelButton;

    @FXML
    private ComboBox<?> addCustCountryCombo;

    @FXML
    private ComboBox<?> addCustDivCombo;

    @FXML
    private TextField addCustIdTxt;

    @FXML
    private TextField addCustNameTxt;

    @FXML
    private TextField addCustPhoneTxt;

    @FXML
    private TextField addCustPostCodeTxt;

    @FXML
    void onActionAddCust(ActionEvent event) {

    }

    @FXML
    void onActionCancel(ActionEvent event) {
        try {
            navigateViews(mainMenuPath, event);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
