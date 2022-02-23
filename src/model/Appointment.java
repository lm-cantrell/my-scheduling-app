package model;

public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private String startDateTime;
    private String endDateTime;
    private String assocCustId;
    private String assocUserId;
    private String assocContactId;

    public Appointment(int appointmentId, String title, String description, String location, String type, String startDateTime, String endDateTime, String assocCustId, String assocUserId, String assocContactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.assocCustId = assocCustId;
        this.assocUserId = assocUserId;
        this.assocContactId = assocContactId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getAssocCustId() {
        return assocCustId;
    }

    public void setAssocCustId(String assocCustId) {
        this.assocCustId = assocCustId;
    }

    public String getAssocUserId() {
        return assocUserId;
    }

    public void setAssocUserId(String assocUserId) {
        this.assocUserId = assocUserId;
    }

    public String getAssocContactId() {
        return assocContactId;
    }

    public void setAssocContactId(String assocContactId) {
        this.assocContactId = assocContactId;
    }

    //create method to get localDateTime from string for appt time
    //create method to get localDateTime from string for appt start
    //create method to get localDateTime from string for appt end
}
