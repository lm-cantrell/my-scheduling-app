package model;

/** Class that defines Country objects.
 * @author Lisa Cantrell
 * */
public class Country {

    private int countryId;
    private String countryName;


    /** Country constructor.
     * @param countryId primary key
     * @param countryName
     * */
    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /** getter for country ID.
     * @return countryId
     * */
    public int getCountryId() {
        return countryId;
    }

    /** setter for countryId. */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /** getter for country name.
     * @return countryName
     * */
    public String getCountryName() {
        return countryName;
    }

    /** setter for countryName. */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /** override toString method to display countryName when called.
     * @return countryName
     * */
    @Override
    public String toString() {
        return countryName;
    }
}
