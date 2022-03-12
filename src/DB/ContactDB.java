package DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ContactDB {

    public static ObservableList<Contact> select() throws SQLException{
        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = JDBC.connection.prepareStatement(sql);
            rs = ps.executeQuery();

            ObservableList<Contact> allContactsList = FXCollections.observableArrayList();

            while (rs.next()){
                int id = rs.getInt("Contact_ID");
                String name = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                Contact currContact = new Contact(id, name, email);
                allContactsList.add(currContact);
                System.out.println(id + " | " + name + " | " + email);
            }

            return allContactsList;

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
