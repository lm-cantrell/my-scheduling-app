package helper;

import javafx.scene.control.Alert;

public class Alerts {

    public static Alert confirmExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setContentText("Are you sure you want to exit the application?");
        return alert;
    }
}
