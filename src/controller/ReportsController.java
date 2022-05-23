package controller;

import DB.AppointmentDB;
import DB.ContactDB;
import DB.CustomerDB;
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
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Month;
import java.util.ResourceBundle;


/** ReportsController controls functionality and interactivity of reports screen.
 * @author Lisa Cantrell
 * */
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
    private Label apptTotalLabel;

    @FXML
    private Label numApptsLabel;

    @FXML
    private TableColumn<?, ?> apptContactCol11;

    @FXML
    private TableColumn<?, ?> apptContactCol1;

    @FXML
    private TableColumn<?, ?> apptCustCol11;

    @FXML
    private TableColumn<?, ?> apptCustCol1;

    @FXML
    private TableColumn<?, ?> apptDescCol11;

    @FXML
    private TableColumn<?, ?> apptDescCol1;

    @FXML
    private TableColumn<?, ?> apptEndCol11;

    @FXML
    private TableColumn<?, ?> apptEndCol1;

    @FXML
    private TableColumn<?, ?> apptIdCol11;

    @FXML
    private TableColumn<?, ?> apptIdCol1;

    @FXML
    private TableColumn<?, ?> apptLocCol11;

    @FXML
    private TableColumn<?, ?> apptLocCol1;

    @FXML
    private TableColumn<?, ?> apptStartCol11;

    @FXML
    private TableColumn<?, ?> apptStartCol1;

    @FXML
    private TableColumn<?, ?> apptTitleCol11;

    @FXML
    private TableColumn<?, ?> apptTitleCol1;

    @FXML
    private TableColumn<?, ?> apptTypeCol11;

    @FXML
    private TableColumn<?, ?> apptTypeCol1;

    @FXML
    private TableColumn<?, ?> apptUserCol11;

    @FXML
    private TableColumn<?, ?> apptUserCol1;

    @FXML
    private TableView<Appointment> contactScheduleTableview;

    @FXML
    private ComboBox<Month> apptMonthCombo;

    @FXML
    private ComboBox<String> apptTypeCombo;


    @FXML
    private TableView<Appointment> apptByCustTableview;

    @FXML
    private ComboBox<Contact> contactCombo;

    @FXML
    private Label numApptResult;

    @FXML
    private TextField searchNameTextView;

    @FXML
    private ComboBox<Contact> numApptbyContactCombo;

    /** onActionContactCombo on selecting contact from combobox method populates tableview with appointments associated with the contact.
     * @param event  */
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
    void onActionSearchName(ActionEvent event) throws SQLException {
        String searchText = searchNameTextView.getText();
        ObservableList<Appointment> apptResults = searchByCustomerName(searchText);

        apptByCustTableview.setItems(apptResults);

    }

    /** onActionExit on clicking button navigates back to main menu screen.
     * @param event  */
    @FXML
    void onActionExit(ActionEvent event) {
        try {
            navigateViews(mainMenuPath, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** onApptMonthCombo on selecting a month in combobox this method filters appointments in tableview to only the selected month.
     * @param event  */
    @FXML
    void onApptMonthCombo(ActionEvent event){
        monthFiltered.clear();
//        apptTypeMonthTableView.setItems(typeFiltered);

        Month selectedMonth = apptMonthCombo.getValue();
        for(Appointment appt : typeFiltered){
            if(appt.getStartDateTime().getMonth().equals(selectedMonth)){
                monthFiltered.add(appt);
            }
        }
        int numAppts = monthFiltered.size();
        apptTotalLabel.setText(String.valueOf(numAppts));

    }

    /** onNumbyContactCombo on selecting a contact from the combo box this method calculates how many appointments that contact has.
     * @param event  */
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

    /** onTypeComboSelect on selecting a type from the combo box this method filters appointments in the tableview and populates the Month combobox.
     * @param event  */
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

        int numApptByType = typeFiltered.size();
        apptTotalLabel.setText(String.valueOf(numApptByType));

        setMonthsList();
        apptMonthCombo.setItems(months);
        apptMonthCombo.setPromptText("Select Month ...");


    }

    /** getApptTypes gets a list of all appointment types by querying the database and processing the appointments returned.
     * @param appointments  */
    public ObservableList<String> getApptTypes(ObservableList<Appointment> appointments){
        ObservableList<String> apptTypes = FXCollections.observableArrayList();
        for(Appointment appt : appointments){
            if(!apptTypes.contains(appt.getType())){
                apptTypes.add(appt.getType());
            }
        }
        return apptTypes;
    }

    /** setMonthsList populates the months combo box. */
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

    /** navigateViews navigates to screen found at provided path.
     * @param viewPath
     * @param event
     * */
    public void navigateViews(String viewPath, ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(viewPath));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public ObservableList<Appointment> searchByCustomerName(String partialName) throws SQLException {
        ObservableList<Appointment> custAppts = FXCollections.observableArrayList();
        ObservableList<Appointment> allAppts = AppointmentDB.select();
        for( Appointment appt : allAppts) {
            int custId = appt.getAssocCustId();
            Customer currCust = CustomerDB.select(custId);
            if(currCust.getCustomerName().contains(partialName)) {
                custAppts.add(appt);
            }
        }

        return custAppts;
    }

    /** initialize method sets up the stage for display.
     * @param url
     * @param resourceBundle
     * */
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


        apptIdCol11.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleCol11.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescCol11.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptLocCol11.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptTypeCol11.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptStartCol11.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        apptEndCol11.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        apptCustCol11.setCellValueFactory(new PropertyValueFactory<>("assocCustId"));
        apptUserCol11.setCellValueFactory(new PropertyValueFactory<>("assocCustId"));
        apptContactCol11.setCellValueFactory(new PropertyValueFactory<>("assocContactId"));

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
