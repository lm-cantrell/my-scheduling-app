package DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CustomerDB {

    public static int insert(String name, String address, String postalCode, String phoneNum, int divId) throws SQLException {

        System.out.println("customer insert called");
        String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?)";

        PreparedStatement ps = null;
        ResultSet rs = null;
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
            if(rs != null) {
                rs.close();
            }
        }

    }

    public static int update(int id, String name, String address, String postalCode, String phoneNum, int divId) throws SQLException {
        System.out.println("customer update called");
        String sql = "UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phoneNum);
        ps.setInt(5, divId);
        ps.setInt(6, id);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    public static int delete(int id) throws SQLException{
        System.out.println("customer delete called");
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, id);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    public static ObservableList select() throws SQLException{
        String sql = "SELECT * FROM CUSTOMERS";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ObservableList<Customer> allCustList = FXCollections.observableArrayList();

        while (rs.next()){
            int custId = rs.getInt("Customer_ID");
            String custName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            int divId = rs.getInt("Division_ID");

            Customer currCustomer = new Customer(custId, custName, address, postCode, phone, divId);
            allCustList.add(currCustomer);
            System.out.println(custId + " | " + custName + " | " + address + " | " + postCode + " | " + phone + " | " + divId);
        }

        return allCustList;

    }

    public static void select(int id) throws SQLException{
        String sql = "SELECT * FROM CUSTOMERS WHERE Customer_ID = ?";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int custId = rs.getInt("Customer_ID");
            String custName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            int divId = rs.getInt("Division_ID");
            System.out.println(custId + " | " + custName + " | " + address + " | " + postCode + " | " + phone + " | " + divId);
        }
    }
}
