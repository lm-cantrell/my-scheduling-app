package helper;

import javafx.scene.control.Alert;

/** Alerts class contains various custom alerts for use in controllers.
 * @author Lisa Cantrell
 * */
public class Alerts {

    /** confirmExit creates a confirmation alert to confirm exit of application */
    public static Alert confirmExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setContentText("Are you sure you want to exit the application?");
        return alert;
    }

    /** customErrorAlert creates an error alert that displays a custom message from string argument.
     * @param message  */
    public static Alert customErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        return alert;
    }

    /** customConfirmationAlert creates a confirmation alert that displays a custom message from string argument.
     * @param message  */
    public static Alert customConfirmationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm your action");
        alert.setContentText(message);
        return alert;
    }

    /** customInfoAlert creates an info alert that displays a custom message from string argument.
     * @param message  */
    public static Alert customInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Appointment information");
        alert.setContentText(message);
        return alert;
    }
}
