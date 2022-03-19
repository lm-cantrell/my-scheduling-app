package DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CustomerDB {

    public static int insert(String name, String address, String postalCode, String phoneNum, int divId) throws SQLException {

        String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?)";

        PreparedStatement ps = null;

        try{
            ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phoneNum);
            ps.setInt(5, divId);

            int rowsAffected = ps.executeUpdate();

            return rowsAffected;

        } finally {
            if(ps != null) {
                ps.close();
            }
        }

    }

    public static int update(int id, String name, String address, String postalCode, String phoneNum, int divId) throws SQLException {
        String sql = "UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";

        PreparedStatement ps = null;

        try{
            ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phoneNum);
            ps.setInt(5, divId);
            ps.setInt(6, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } finally {
            if(ps != null) {
                ps.close();
            }
        }

    }

    public static int delete(int id) throws SQLException{
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = null;

        try{
            ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();

            return rowsAffected;
        } finally {
            if(ps != null) {
                ps.close();
            }
        }

    }

    public static ObservableList select() throws SQLException{
        String sql = "SELECT * FROM CUSTOMERS";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            ps = JDBC.connection.prepareStatement(sql);
            rs = ps.executeQuery();

            ObservableList<Customer> allCustList = FXCollections.observableArrayList();

            while (rs.next()){
                int custId = rs.getInt("Customer_ID");
                String custName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int divId = rs.getInt("Division_ID");


                String divsql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Division_ID = ?";

                PreparedStatement psd = JDBC.connection.prepareStatement(divsql);
                psd.setInt(1, divId);
                ResultSet rsd = psd.executeQuery();

                rsd.next();

                String division = rsd.getString("Division");
                int countryId = rsd.getInt("Country_ID");


                String countrysql = "SELECT * FROM COUNTRIES WHERE Country_ID = ?";
                PreparedStatement psc = JDBC.connection.prepareStatement(countrysql);
                psc.setInt(1, countryId);
                ResultSet rsc = psc.executeQuery();

                rsc.next();

                String country = rsc.getString("Country");

                Customer currCustomer = new Customer(custId, custName, address, postCode, phone, country, division);
                allCustList.add(currCustomer);
            }

            return allCustList;
        } finally {
            if(ps != null) {
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
        }


    }

    public static Customer select(int id) throws SQLException{
        String sql = "SELECT * FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();

            int custId = rs.getInt("Customer_ID");
            String custName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            int divId = rs.getInt("Division_ID");

            String divsql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Division_ID = ?";

            PreparedStatement psd = JDBC.connection.prepareStatement(divsql);
            psd.setInt(1, divId);
            ResultSet rsd = psd.executeQuery();

            rsd.next();

            String division = rsd.getString("Division");
            int countryId = rsd.getInt("Country_ID");

            String countrysql = "SELECT * FROM COUNTRIES WHERE Country_ID = ?";
            PreparedStatement psc = JDBC.connection.prepareStatement(countrysql);
            psc.setInt(1, countryId);
            ResultSet rsc = psc.executeQuery();

            rsc.next();

            String country = rsc.getString("Country");



            Customer currCustomer = new Customer(custId, custName, address, postCode, phone, country, division);

            return currCustomer;
        } finally {
            if(ps != null) {
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
        }
    }
}
