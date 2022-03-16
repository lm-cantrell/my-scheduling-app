package controller;

import DB.UserDB;
import helper.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.MissingResourceException;
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
//                Locale locale = Locale.getDefault();
                Locale locale = Locale.FRANCE;
                String alertString;
                if(locale == Locale.FRANCE){
                    alertString = "Aucun utilisateur avec ces informations d'identification n'existe. Veuillez entrer des informations d'identification valides";
                } else {
                    alertString = "No user with those credentials exists. Please enter valid credentials";
                }
                Alert alert = Alerts.customErrorAlert(alertString);
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
        try {
//            Locale locale = Locale.getDefault();
            Locale locale = Locale.FRANCE;
            resourceBundle = ResourceBundle.getBundle("assets/Nat", locale);
            System.out.println("got the bundle");

            regionLabel.setText(resourceBundle.getString("region"));
            regionDetailsLabel.setText(String.valueOf(locale));
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
