package controller;

import DB.AppointmentDB;
import helper.Alerts;
import helper.Time;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainMenuController implements Initializable {

    Stage stage;
    Parent scene;

    private String addCustPath = "/view/AddCustomer.fxml";
    private String updateCustPath = "/view/UpdateCustomer.fxml";
    private String addApptPath = "/view/AddAppointment.fxml";
    private String updateApptPath = "/view/UpdateAppointment.fxml";
    private String reportsPath = "/view/Reports.fxml";

    private ObservableList<Appointment> allAppointments = AppointmentDB.select();
    private ObservableList<LocalTime> scheduleTimes = FXCollections.observableArrayList();


    @FXML
    private Button addAppointmentButton;

    @FXML
    private Button addCustomerButton;

    @FXML
    private RadioButton allApptsRadioButton;

    @FXML
    private ToggleGroup appointmentFilter;

    @FXML
    private TableColumn<?, ?> apptContactCol;

    @FXML
    private TableColumn<?, ?> apptCustCol;

    @FXML
    private TableColumn<?, ?> apptDescCol;

    @FXML
    private TableColumn<?, ?> apptEndCol;

    @FXML
    private TableColumn<?, ?> apptIdCol;

    @FXML
    private TableColumn<?, ?> apptLocCol;

    @FXML
    private TableColumn<?, ?> apptStartCol;

    @FXML
    private TableColumn<?, ?> apptTitleCol;

    @FXML
    private TableColumn<?, ?> apptTypeCol;

    @FXML
    private TableColumn<?, ?> apptUserCol;

    @FXML
    private Button logoutButton;

    @FXML
    private TableView<Appointment> mainAppointmentTableview;

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

    public MainMenuController() throws SQLException {
    }

    @FXML
    void onActionExit(ActionEvent event) {
        Alert alert = Alerts.confirmExit();
        if(alert.showAndWait().get() == ButtonType.OK) {
            System.exit(0);
        }
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
//        try {
//            navigateViews(updateApptPath, event);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        generateTimesList();

        if( mainAppointmentTableview.getSelectionModel().getSelectedItem() == null){
            Alert alert = Alerts.customErrorAlert("You must select an appointment to update.");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(updateApptPath));
                loader.load();

                UpdateAppointmentController updateApptController = loader.getController();
                updateApptController.sendAppt(mainAppointmentTableview.getSelectionModel().getSelectedItem(), scheduleTimes);

                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();


            } catch (NullPointerException | IOException | SQLException e) {
                //implement missed selection error
                e.printStackTrace();
            }
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

    public void generateTimesList() {
        for ( int i = 8; i < 23; i++) {
            LocalDate date = LocalDate.now();
            LocalTime thisTime = LocalTime.of(i, 0);
            LocalTime thisHalfTime = LocalTime.of(i, 30);
            LocalDateTime easternLdt = LocalDateTime.of(date, thisTime);
            LocalDateTime easternHalfLdt = LocalDateTime.of(date, thisHalfTime);

            ZoneId localZone = ZoneId.systemDefault();
            ZonedDateTime easternZdt = ZonedDateTime.of(easternLdt, ZoneId.of("America/New_York"));
            ZonedDateTime easternHalfZdt = ZonedDateTime.of(easternHalfLdt, ZoneId.of("America/New_York"));
            ZonedDateTime localZdt = Time.easternToLocalSys(easternZdt);
            ZonedDateTime localHalfZdt = Time.easternToLocalSys(easternHalfZdt);

            scheduleTimes.add(localZdt.toLocalTime());
            scheduleTimes.add(localHalfZdt.toLocalTime());
        }

    }

    @FXML
    void onActionShowAll(ActionEvent event) {
        mainAppointmentTableview.setItems(allAppointments);
        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptLocCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptStartCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        apptEndCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        apptCustCol.setCellValueFactory(new PropertyValueFactory<>("assocCustId"));
        apptUserCol.setCellValueFactory(new PropertyValueFactory<>("assocCustId"));
        apptContactCol.setCellValueFactory(new PropertyValueFactory<>("assocContactId"));
    }

    @FXML
    void onActionShowByMonth(ActionEvent event) {
        LocalDateTime current = LocalDateTime.now();
        getMonth(current);
    }

    @FXML
    void onActionShowByWeek(ActionEvent event) {
        LocalDateTime current = LocalDateTime.now().with(LocalTime.of(0, 0));
        LocalDateTime[] weekStartEnd;
        weekStartEnd = Time.getWeek(current);

        ArrayList weekAppointments;
        weekAppointments = (ArrayList) allAppointments
                .stream()
                .filter(appointment -> (appointment.getStartDateTime().isBefore(weekStartEnd[1]) || appointment.getStartDateTime().isEqual(weekStartEnd[1])) &&
                        (appointment.getStartDateTime().isAfter(weekStartEnd[0]) || appointment.getStartDateTime().isEqual(weekStartEnd[0])))
                .collect(Collectors.toList());

        ObservableList weekApptOL = FXCollections.observableList(weekAppointments);
        mainAppointmentTableview.setItems(weekApptOL);
        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptLocCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptStartCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        apptEndCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        apptCustCol.setCellValueFactory(new PropertyValueFactory<>("assocCustId"));
        apptUserCol.setCellValueFactory(new PropertyValueFactory<>("assocCustId"));
        apptContactCol.setCellValueFactory(new PropertyValueFactory<>("assocContactId"));

    }



    public void getMonth(LocalDateTime today){
        LocalDateTime current = LocalDateTime.now();
        Month currMonth = current.getMonth();

        ArrayList monthAppointments;
        monthAppointments = (ArrayList) allAppointments
                .stream()
                        .filter(appointment -> (appointment.getStartDateTime().getMonth() == currMonth))
                                .collect(Collectors.toList());

        ObservableList monthApptOL = FXCollections.observableList(monthAppointments);

        mainAppointmentTableview.setItems(monthApptOL);
        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptLocCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptStartCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        apptEndCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        apptCustCol.setCellValueFactory(new PropertyValueFactory<>("assocCustId"));
        apptUserCol.setCellValueFactory(new PropertyValueFactory<>("assocCustId"));
        apptContactCol.setCellValueFactory(new PropertyValueFactory<>("assocContactId"));



    }

    public void navigateViews(String viewPath, ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(viewPath));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mainAppointmentTableview.setItems(allAppointments);
        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptLocCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptStartCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        apptEndCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        apptCustCol.setCellValueFactory(new PropertyValueFactory<>("assocCustId"));
        apptUserCol.setCellValueFactory(new PropertyValueFactory<>("assocCustId"));
        apptContactCol.setCellValueFactory(new PropertyValueFactory<>("assocContactId"));


        System.out.println("I'm initialized");


    }
}
