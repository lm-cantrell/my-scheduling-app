package DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.*;
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
        String sql = "UPDATE APPOINTMENTS SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";

        PreparedStatement ps = null;

        try{
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
            ps.setInt(10, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } finally {
            if(ps != null) {
                ps.close();
            }
        }


    }

    //delete
    public static int delete(int id) throws SQLException{
        System.out.println("appointment delete called");
        String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";
        PreparedStatement ps = null;

        try {
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

    //select and overloaded select
    public static ObservableList select() throws SQLException{
        System.out.println("appointment select all called");
        String sql = "SELECT * FROM APPOINTMENTS";
        PreparedStatement ps = null;
        ResultSet rs = null;

        ObservableList<Appointment> allApptList = FXCollections.observableArrayList();

        try {
            ps = JDBC.connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                int apptId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp startTS = rs.getTimestamp("Start");
                LocalDateTime start = startTS.toLocalDateTime();
                Timestamp endTS = rs.getTimestamp("End");
                LocalDateTime end = endTS.toLocalDateTime();
                int custId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                Appointment currAppointment = new Appointment(apptId, title, description, location, type, start, end, custId, userId, contactId);
                allApptList.add(currAppointment);
                System.out.println(apptId + " | " + title + " | " + description + " | " + location + " | " + type + " | " + start + " | " + end + " | " + custId + " | " + userId + " | " + contactId);
            }

            return allApptList;

        } finally {
            if(ps != null) {
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
        }
    }

    public static ObservableList select(int id) throws SQLException{
        System.out.println("appointment select all called for appt id: " + id);
        String sql = "SELECT * FROM APPOINTMENTS WHERE Appointment_ID = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        ObservableList<Appointment> allApptList = FXCollections.observableArrayList();

        try {
            ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()){
                int apptId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp startTS = rs.getTimestamp("Start");
                LocalDateTime start = startTS.toLocalDateTime();
                Timestamp endTS = rs.getTimestamp("End");
                LocalDateTime end = endTS.toLocalDateTime();
                int custId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                Appointment currAppointment = new Appointment(apptId, title, description, location, type, start, end, custId, userId, contactId);
                allApptList.add(currAppointment);
                System.out.println(apptId + " | " + title + " | " + description + " | " + location + " | " + type + " | " + start + " | " + end + " | " + custId + " | " + userId + " | " + contactId);
            }

            return allApptList;

        } finally {
            if(ps != null) {
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
        }
    }

    public static ObservableList selectByCust(int custId) throws SQLException {
        String sql = "SELECT * FROM APPOINTMENTS WHERE Customer_ID = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        ObservableList<Appointment> allApptList = FXCollections.observableArrayList();

        try {
            ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, custId);
            rs = ps.executeQuery();

            while (rs.next()){
                int apptId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp startTS = rs.getTimestamp("Start");
                LocalDateTime start = startTS.toLocalDateTime();
                Timestamp endTS = rs.getTimestamp("End");
                LocalDateTime end = endTS.toLocalDateTime();
                int currCustId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                Appointment currAppointment = new Appointment(apptId, title, description, location, type, start, end, currCustId, userId, contactId);
                allApptList.add(currAppointment);
                System.out.println(apptId + " | " + title + " | " + description + " | " + location + " | " + type + " | " + start + " | " + end + " | " + currCustId + " | " + userId + " | " + contactId);
            }

            return allApptList;

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
