package DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** DivisionDB class manages sql queries related to division database.
 * @author Lisa Cantrell
 * */

public abstract class DivisionDB {

    /** select method queries database for all divisions.
     * @return allDivisionsList
     * */
    public static ObservableList select() throws SQLException{
        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = JDBC.connection.prepareStatement(sql);
            rs = ps.executeQuery();

            ObservableList<Division> allDivisionsList = FXCollections.observableArrayList();

            while(rs.next()){
                int divId = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int assocCountryId = rs.getInt("Country_ID");

                Division currDivision = new Division(divId, division, assocCountryId);
                allDivisionsList.add(currDivision);
            }

            return allDivisionsList;

        }finally {
            if(ps != null) {
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
        }
    }

    /** select method overloaded with division name queries database for specific division.
     * @param division_name
     * @return selectedDivision
     * */
    public static Division select(String division_name) throws SQLException {
        String sql = "SELECT * FROM first_level_divisions WHERE Division = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, division_name);
            rs = ps.executeQuery();
            rs.next();

            int divId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int assocCountryId = rs.getInt("Country_ID");

            Division selectedDivision = new Division(divId, division, assocCountryId);

            return selectedDivision;

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
