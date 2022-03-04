package model;

import java.time.LocalDateTime;

public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int assocCustId;
    private int assocUserId;
    private int assocContactId;

    public Appointment(int appointmentId, String title, String description, String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, int assocCustId, int assocUserId, int assocContactId) {
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

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public int getAssocCustId() {
        return assocCustId;
    }

    public void setAssocCustId(int assocCustId) {
        this.assocCustId = assocCustId;
    }

    public int getAssocUserId() {
        return assocUserId;
    }

    public void setAssocUserId(int assocUserId) {
        this.assocUserId = assocUserId;
    }

    public int getAssocContactId() {
        return assocContactId;
    }

    public void setAssocContactId(int assocContactId) {
        this.assocContactId = assocContactId;
    }

    //create method to get localDateTime from string for appt time
    //create method to get localDateTime from string for appt start
    //create method to get localDateTime from string for appt end
}
