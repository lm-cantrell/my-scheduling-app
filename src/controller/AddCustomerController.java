package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

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

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I'm initialized");
    }
}
