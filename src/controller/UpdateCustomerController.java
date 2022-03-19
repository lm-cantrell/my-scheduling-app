package controller;

import DB.AppointmentDB;
import DB.CountryDB;
import DB.CustomerDB;
import DB.DivisionDB;
import helper.Alerts;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointment;
import model.Country;
import model.Customer;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateCustomerController implements Initializable {

    Stage stage;
    Parent scene;

    String mainMenuPath = "/view/MainMenu.fxml";

    private ObservableList<Customer> allCustomers = CustomerDB.select();
    private ObservableList<Country> allCountries = CountryDB.select();
    private ObservableList<Division> allDivisions = DivisionDB.select();
    private ObservableList<Division> filteredDivisions = FXCollections.observableArrayList();

    @FXML
    private TextField updateCustAddressTxt;

    @FXML
    private Button updateCustCancelButton;

    @FXML
    private ComboBox<Country> updateCustCountryCombo;

    @FXML
    private TableColumn<?,?> updateCustCountryCol;

    @FXML
    private Button updateCustDeleteButton;

    @FXML
    private ComboBox<Division> updateCustDivCombo;

    @FXML
    private TableColumn<?,?> updateCustDivCol;

    @FXML
    private TableColumn<?,?> updateCustIDCol;

    @FXML
    private TextField updateCustIdTxt;

    @FXML
    private TextField updateCustNameTxt;

    @FXML
    private TextField updateCustPhoneTxt;

    @FXML
    private TextField updateCustPostCodeTxt;

    @FXML
    private TableColumn<?,?> updateCustPostCol;

    @FXML
    private TableColumn<?,?> updateCustNameCol;

    @FXML
    private TableColumn<?,?> updateCustPhoneCol;

    @FXML
    private TableColumn<?,?> updateCustAddressCol;

    @FXML
    private TableView<Customer> updateCustTableview;

    @FXML
    private Button updateCustUpdateButton;

    public UpdateCustomerController() throws SQLException {
    }

    @FXML
    void onActionCancel(ActionEvent event) {
        Alert alert = Alerts.customConfirmationAlert("Do you want to discard any changes?");
        if(alert.showAndWait().get() == ButtonType.OK){
            try {
                navigateViews(mainMenuPath, event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    @FXML
    void onClickCheckSelection(MouseEvent event){
        filteredDivisions.clear();

        Customer selectedCust =  updateCustTableview.getSelectionModel().getSelectedItem();

        updateCustIdTxt.setText(String.valueOf(selectedCust.getCustomerId()));
        updateCustNameTxt.setText(selectedCust.getCustomerName());
        updateCustAddressTxt.setText(selectedCust.getAddress());
        updateCustPostCodeTxt.setText(selectedCust.getPostalCode());
        updateCustPhoneTxt.setText(selectedCust.getPhoneNumber());

        for(Country country : allCountries){
            if (country.getCountryName().equals(selectedCust.getCountry())){
                updateCustCountryCombo.setValue(country);
                for(Division div : allDivisions){
                    if( div.getAssocCountryId() == country.getCountryId()){
                        filteredDivisions.add(div);
                    }
                }
            }
        }

        updateCustDivCombo.setItems(filteredDivisions);

        for(Division div : filteredDivisions){
            if(div.getDivisionName().equals(selectedCust.getDivision())){
                updateCustDivCombo.setValue(div);
            }
        }

    }

    @FXML
    void onCountryCombo(ActionEvent event) {
        filteredDivisions.clear();
        Country selectedCountry = updateCustCountryCombo.getValue();
        for(Division div : allDivisions){
            if( div.getAssocCountryId() == selectedCountry.getCountryId()){
                filteredDivisions.add(div);
            }
        }
        updateCustDivCombo.setItems(filteredDivisions);
    }

    @FXML
    void onActionDeleteCust(ActionEvent event) throws SQLException {
        if(updateCustTableview.getSelectionModel().getSelectedItem() != null){
            Alert alert = Alerts.customConfirmationAlert("Are you sure you want to delete the customer and all associated appointments?");
            if(alert.showAndWait().get() == ButtonType.OK){
                boolean hasAppointments = true;
                int custId = Integer.valueOf(updateCustIdTxt.getText());
                ObservableList<Appointment> custAppts = AppointmentDB.selectByCust(custId);
                custAppts.forEach( appointment -> {
                    try {
                        AppointmentDB.delete(appointment.getAppointmentId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
                CustomerDB.delete(custId);
                try{
                    navigateViews(mainMenuPath, event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }else {
            Alert alert = Alerts.customInfoAlert("You need to select a customer to delete.");
            alert.showAndWait();
        }
    }

    @FXML
    void onActionUpdateCust(ActionEvent event) throws SQLException {

        int id = Integer.parseInt(updateCustIdTxt.getText());
        String name = updateCustNameTxt.getText();
        String address = updateCustAddressTxt.getText();
        String postal = updateCustPostCodeTxt.getText();
        String phone = updateCustPhoneTxt.getText();
        Country selCountry = updateCustCountryCombo.getValue();
        Division selDivision = updateCustDivCombo.getValue();
        int divId = selDivision.getDivisionId();

        Alert alert = Alerts.customConfirmationAlert("Are you sure you want to make these changes?");
        if(alert.showAndWait().get() == ButtonType.OK){
            CustomerDB.update(id, name, address, postal, phone, divId);
            try {
                navigateViews(mainMenuPath, event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }

    public void navigateViews(String viewPath, ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(viewPath));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        updateCustIdTxt.setEditable(false);

        updateCustTableview.setItems(allCustomers);
        updateCustIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        updateCustNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        updateCustAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        updateCustPostCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        updateCustPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        updateCustCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        updateCustDivCol.setCellValueFactory(new PropertyValueFactory<>("division"));

        updateCustCountryCombo.setItems(allCountries);


        System.out.println("I'm initialized");
    }
}
