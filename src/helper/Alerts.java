package helper;

import javafx.scene.control.Alert;

public class Alerts {

    public static Alert confirmExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setContentText("Are you sure you want to exit the application?");
        return alert;
    }

    public static Alert customErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        return alert;
    }

    public static Alert customConfirmationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm your action");
        alert.setContentText(message);
        return alert;
    }

    public static Alert customInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Appointment informagion");
        alert.setContentText(message);
        return alert;
    }
}
