package controller;

import DB.AppointmentDB;
import DB.ContactDB;
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
import model.Contact;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Month;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {

    Stage stage;
    Parent scene;

    String mainMenuPath = "/view/MainMenu.fxml";
    ObservableList<Appointment> allAppointments;
    ObservableList<String> allTypes;
    ObservableList<Appointment> typeFiltered = FXCollections.observableArrayList();
    ObservableList<Appointment> monthFiltered = FXCollections.observableArrayList();
    ObservableList<Contact> allContacts;
    ObservableList<Appointment> schedule = FXCollections.observableArrayList();
    ObservableList<Month> months = FXCollections.observableArrayList();


    @FXML
    private Button reportsExitButton;

    @FXML
    private TableColumn<?, ?> apptContactCol;

    @FXML
    private TableColumn<?, ?> apptContactCol1;

    @FXML
    private TableColumn<?, ?> apptCustCol;

    @FXML
    private TableColumn<?, ?> apptCustCol1;

    @FXML
    private TableColumn<?, ?> apptDescCol;

    @FXML
    private TableColumn<?, ?> apptDescCol1;

    @FXML
    private TableColumn<?, ?> apptEndCol;

    @FXML
    private TableColumn<?, ?> apptEndCol1;

    @FXML
    private TableColumn<?, ?> apptIdCol;

    @FXML
    private TableColumn<?, ?> apptIdCol1;

    @FXML
    private TableColumn<?, ?> apptLocCol;

    @FXML
    private TableColumn<?, ?> apptLocCol1;

    @FXML
    private TableColumn<?, ?> apptStartCol;

    @FXML
    private TableColumn<?, ?> apptStartCol1;

    @FXML
    private TableColumn<?, ?> apptTitleCol;

    @FXML
    private TableColumn<?, ?> apptTitleCol1;

    @FXML
    private TableColumn<?, ?> apptTypeCol;

    @FXML
    private TableColumn<?, ?> apptTypeCol1;

    @FXML
    private TableColumn<?, ?> apptUserCol;

    @FXML
    private TableColumn<?, ?> apptUserCol1;

    @FXML
    private TableView<Appointment> contactScheduleTableview;

    @FXML
    private ComboBox<Month> apptMonthCombo;

    @FXML
    private ComboBox<String> apptTypeCombo;

    @FXML
    private TableView<Appointment> apptTypeMonthTableView;

    @FXML
    private ComboBox<Contact> contactCombo;

    @FXML
    private Label numApptResult;

    @FXML
    private ComboBox<Contact> numApptbyContactCombo;

    @FXML
    void onActionContactCombo(ActionEvent event) {
        schedule.clear();
        int selectedContactId = contactCombo.getValue().getContactId();
        for(Appointment appt: allAppointments){
            if(appt.getAssocContactId() == selectedContactId){
                schedule.add(appt);
            }
        }

        contactScheduleTableview.setItems(schedule);

    }

    @FXML
    void onActionExit(ActionEvent event) {
        try {
            navigateViews(mainMenuPath, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onApptMonthCombo(ActionEvent event){
        monthFiltered.clear();
        apptTypeMonthTableView.setItems(typeFiltered);

        Month selectedMonth = apptMonthCombo.getValue();
//        ObservableList<Appointment> currentList = apptTypeMonthTableView.getItems();
        for(Appointment appt : typeFiltered){
            if(appt.getStartDateTime().getMonth().equals(selectedMonth)){
                monthFiltered.add(appt);
            }
        }
        apptTypeMonthTableView.setItems(monthFiltered);

    }

    @FXML
    void onNumbyContactCombo(ActionEvent event){
        int contactApptCounter = 0;
        int selectedContactId = numApptbyContactCombo.getValue().getContactId();
        for(Appointment appt: allAppointments){
            if(appt.getAssocContactId() == selectedContactId){
                contactApptCounter++;
            }
        }
        numApptResult.setText(String.valueOf(contactApptCounter));

    }

    @FXML
    void onTypeComboSelect(ActionEvent event){
        typeFiltered.clear();
        monthFiltered.clear();
        apptMonthCombo.getSelectionModel().clearSelection();

        String typeSelected = apptTypeCombo.getValue();
        for(Appointment appt : allAppointments){
            if(typeSelected.equals(appt.getType())){
                typeFiltered.add(appt);
            }
        }

        apptTypeMonthTableView.setItems(typeFiltered);

        setMonthsList();
        apptMonthCombo.setItems(months);
        apptMonthCombo.setPromptText("Select Month ...");


    }

    public ObservableList<String> getApptTypes(ObservableList<Appointment> appointments){
        ObservableList<String> apptTypes = FXCollections.observableArrayList();
        for(Appointment appt : appointments){
            if(!apptTypes.contains(appt.getType())){
                apptTypes.add(appt.getType());
            }
        }
        return apptTypes;
    }

    public void setMonthsList(){
        months.clear();
        months.add(Month.JANUARY);
        months.add(Month.FEBRUARY);
        months.add(Month.MARCH);
        months.add(Month.APRIL);
        months.add(Month.MAY);
        months.add(Month.JUNE);
        months.add(Month.JULY);
        months.add(Month.AUGUST);
        months.add(Month.SEPTEMBER);
        months.add(Month.OCTOBER);
        months.add(Month.NOVEMBER);
        months.add(Month.DECEMBER);
    }


    public void navigateViews(String viewPath, ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(viewPath));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            allAppointments = AppointmentDB.select();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        allTypes = getApptTypes(allAppointments);
        apptTypeCombo.setPromptText("Select Type ...");
        apptTypeCombo.setItems(allTypes);



        try {
            allContacts = ContactDB.select();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        contactCombo.setItems(allContacts);
        contactCombo.setPromptText("Select a Contact ...");

        numApptbyContactCombo.setPromptText("Select a Contact ...");
        numApptbyContactCombo.setItems(allContacts);

        apptTypeMonthTableView.setItems(allAppointments);
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

        apptIdCol1.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleCol1.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescCol1.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptLocCol1.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptTypeCol1.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptStartCol1.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        apptEndCol1.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        apptCustCol1.setCellValueFactory(new PropertyValueFactory<>("assocCustId"));
        apptUserCol1.setCellValueFactory(new PropertyValueFactory<>("assocCustId"));
        apptContactCol1.setCellValueFactory(new PropertyValueFactory<>("assocContactId"));

        System.out.println("I'm initialized");
    }
}
