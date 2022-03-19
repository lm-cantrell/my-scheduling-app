package model;

/** Class that defines Division objects.
 * @author Lisa Cantrell
 * */
public class Division {

    private int divisionId;
    private String divisionName;
    private int assocCountryId;

    /** Division constructor.
     * @param divisionId primary key
     * @param divisionName
     * @param assocCountryId foreign key
     * */
    public Division(int divisionId, String divisionName, int assocCountryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.assocCountryId = assocCountryId;
    }

    /** getter for division ID.
     * @return divisionId
     * */
    public int getDivisionId() {
        return divisionId;
    }

    /** setter for division ID. */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /** getter for division name.
     * @return divisionName
     * */
    public String getDivisionName() {
        return divisionName;
    }

    /** setter for division name. */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /** getter for division's associated country id.
     * @return assocCountryId
     * */
    public int getAssocCountryId() {
        return assocCountryId;
    }

    /** setter for division's associated country ID. */
    public void setAssocCountryId(int assocCountryId) {
        this.assocCountryId = assocCountryId;
    }

    /** override toString method to display contactName when called.
     * @return contactName
     * */
    @Override
    public String toString() {
        return divisionName;
    }
}
