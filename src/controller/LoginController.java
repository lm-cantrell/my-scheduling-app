package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button exitButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordText;

    @FXML
    private TextField usernameText;

    @FXML
    void onActionExitApplication(ActionEvent event) {

    }

    @FXML
    void onActionLogin(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I'm initialized");
    }
}
