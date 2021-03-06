package DB;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** UserDB class manages sql queries related to user database.
 * @author Lisa Cantrell
 * */

public abstract class UserDB {

    /** getUserIDFromLogin returns a userID from a query using username and password, if the match exists.
     * if no match exists, returns 0
     * @param username
     * @param password
     * @return userId
     * */
    public static int getUserIDFromLogin(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if(rs.next() == false) {
                return 0;
            } else {
                int userId = rs.getInt("User_ID");
                System.out.println(userId);
                return userId;
            }
        } finally {
            if(ps != null) {
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
        }


    }

    /** selectCurrUser method queries database for specific user.
     * @param userId
     * @return currUser
     * */
    public static User selectCurrUser(int userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_ID = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        User currUser;

        try {
            ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            rs.next();

            String userName = rs.getString("User_Name");
            String userPassword = rs.getString("Password");


            currUser = new User(userId, userName, userPassword);

            return currUser;
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

