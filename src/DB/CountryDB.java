package DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CountryDB {

    public static ObservableList select() throws SQLException {
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = JDBC.connection.prepareStatement(sql);
            rs = ps.executeQuery();

            ObservableList<Country> allCountryList = FXCollections.observableArrayList();

            while(rs.next()){
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");

                Country currCountry = new Country(countryId, countryName);
                allCountryList.add(currCountry);
                System.out.println(countryId + " | " + countryName);
            }
            return allCountryList;

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
