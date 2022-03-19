package DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** ContactDB class manages sql queries related to contact database.
 * @author Lisa Cantrell
 * */

public abstract class ContactDB {

    /** select method queries database for all contacts.
     * @return allContactsList
     * */
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

    /** select method overloaded with contact ID queries database for specific contact.
     * @param contactId
     * @return selectedContact
     * */
    public static Contact select(int contactId) throws SQLException {
        String sql = "SELECT * FROM contacts WHERE Contact_ID = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, contactId);
            rs = ps.executeQuery();
            rs.next();

            int id = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");

            Contact selectedContact = new Contact(id, contactName, email);
            return selectedContact;

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
