package DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class AppointmentDB {

    //insert
    public static int insert(String title, String desc, String loc, String type, LocalDateTime start, LocalDateTime end, int custId, int userId, int contactId) throws SQLException {
        System.out.println("appointment insert called");
        String sql = "INSERT INTO APPOINTMENTS (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = null;
        try {
            ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, desc);
            ps.setString(3, loc);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setInt(7, custId);
            ps.setInt(8, userId);
            ps.setInt(9, contactId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } finally {
            if(ps != null) {
                ps.close();
            }
        }


    }

    //update
    public static int update(int id, String title, String desc, String loc, String type, LocalDateTime start, LocalDateTime end, int custId, int userId, int contactId) throws SQLException{
        System.out.println("appointment update called");
        return 0;
    }

    //delete
    public static int delete(int id) throws SQLException{
        System.out.println("appointment delete called");
        return 0;
    }

    //select and overloaded select
    public static ObservableList select() throws SQLException{
        System.out.println("appointment select all called");
        ObservableList<Appointment> allApptList = FXCollections.observableArrayList();
        return allApptList;
    }

    public static ObservableList select(int id) throws SQLException{
        System.out.println("appointment select all called for appt id: " + id);
        ObservableList<Appointment> allApptList = FXCollections.observableArrayList();
        return allApptList;
    }
}
