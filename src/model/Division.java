package model;

public class Division {

    private int divisionId;
    private String divisionName;
    private int assocCountryId;

    public Division(int divisionId, String divisionName, int assocCountryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.assocCountryId = assocCountryId;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public int getAssocCountryId() {
        return assocCountryId;
    }

    public void setAssocCountryId(int assocCountryId) {
        this.assocCountryId = assocCountryId;
    }

    @Override
    public String toString() {
        return divisionName;
    }
}
