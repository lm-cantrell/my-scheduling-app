package controller;

import DB.UserDB;
import helper.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    Stage stage;
    Parent scene;

    String mainMenuPath = "/view/MainMenu.fxml";

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
        Alert alert = Alerts.confirmExit();
        if(alert.showAndWait().get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @FXML
    void onActionLogin(ActionEvent event) {

        //implement input stuff
        //implement password verification

        try {
            String userName = usernameText.getText();
            String password = passwordText.getText();

            int currUserId = UserDB.getUserIDFromLogin(userName, password);

            if( currUserId == 0) {
                Alert alert = Alerts.customErrorAlert("No user with those credentials exists. Please enter valid credentials.");
                alert.show();
            } else {
                navigateViews(mainMenuPath, event);
            }


        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void navigateViews(String viewPath, ActionEvent event) throws IOException{
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
