package controller;

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
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

    Stage stage;
    Parent scene;

    private String mainMenuPath = "/view/MainMenu.fxml";

    private ObservableList<Customer> allCustomers = CustomerDB.select();
    private ObservableList<Country> allCountries = CountryDB.select();
    private ObservableList<Division> allDivisions = DivisionDB.select();
    private ObservableList<Division> filteredDivisions = FXCollections.observableArrayList();


    @FXML
    private Button addCustAddButton;

    @FXML
    private TableColumn<?,?> addCustAddressCol;

    @FXML
    private TableColumn<?,?> addCustPhoneCol;

    @FXML
    private TableColumn<?,?> addCustDivCol;

    @FXML
    private TextField addCustAdressTxt;

    @FXML
    private Button addCustCancelButton;

    @FXML
    private TableColumn<?,?> addCustCountryCol;

    @FXML
    private ComboBox<Country> addCustCountryCombo;

    @FXML
    private ComboBox<Division> addCustDivCombo;

    @FXML
    private TableColumn<?,?> addCustIdCol;

    @FXML
    private TextField addCustIdTxt;

    @FXML
    private TableColumn<?,?> addCustNameCol;

    @FXML
    private TextField addCustNameTxt;

    @FXML
    private TextField addCustPhoneTxt;

    @FXML
    private TextField addCustPostCodeTxt;

    @FXML
    private TableColumn<?,?> addCustPostCol;

    @FXML
    private TableView<Customer> addCustTableview;

    public AddCustomerController() throws SQLException {
    }

    @FXML
    void onActionAddCust(ActionEvent event) throws SQLException {
        if(allFieldsSelected()){
            String name = addCustNameTxt.getText();
            String address = addCustAdressTxt.getText();
            String postal = addCustPostCodeTxt.getText();
            String phone = addCustPhoneTxt.getText();
            Division custDivision = addCustDivCombo.getValue();
            addCustToDB(name, address, postal, phone, custDivision);
            try {
                navigateViews(mainMenuPath, event);
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            System.out.println("You missed something");
        }


    }

    @FXML
    void onCountryCombo(ActionEvent event) {
        filteredDivisions.clear();
        Country selectedCountry = addCustCountryCombo.getValue();
        for(Division div : allDivisions){
            if( div.getAssocCountryId() == selectedCountry.getCountryId()){
                filteredDivisions.add(div);
            }
        }
        addCustDivCombo.setItems(filteredDivisions);

    }

    @FXML
    void onDivisionCombo(ActionEvent event) {

    }

    @FXML
    void onActionCancel(ActionEvent event) {
        try {
            navigateViews(mainMenuPath, event);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean allFieldsSelected(){
        if ( addCustNameTxt.getText().isEmpty() ||
            addCustAdressTxt.getText().isEmpty() ||
            addCustPostCodeTxt.getText().isEmpty() ||
            addCustPhoneTxt.getText().isEmpty() ||
            addCustCountryCombo.getValue() == null||
            addCustDivCombo.getValue() == null){
            Alert alert = Alerts.customErrorAlert("You're missing a field.");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }

    }

    public void addCustToDB(String name, String address, String postal, String phone, Division division) throws SQLException {

        int divId = division.getDivisionId();

        try {
            CustomerDB.insert(name, address, postal, phone, divId);
        } catch(SQLException ex) {
            ex.printStackTrace();
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

        addCustIdTxt.setEditable(false);

        addCustTableview.setItems(allCustomers);
        addCustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        addCustNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addCustAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        addCustPostCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        addCustPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addCustCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        addCustDivCol.setCellValueFactory(new PropertyValueFactory<>("division"));

        addCustCountryCombo.setItems(allCountries);
        addCustCountryCombo.setPromptText("Select country...");

        System.out.println("I'm initialized");
    }

}
