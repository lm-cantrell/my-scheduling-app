package DB;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserDB {

    public static User selectCurrUser(String username, String password) throws SQLException {
        System.out.println("logging in");
        String sql = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        User currUser;

        try {
            ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            rs.next();

            int userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String userPassword = rs.getString("Password");


            currUser = new User(userId, userName, userPassword);


            System.out.println("current user is " + userName);

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

