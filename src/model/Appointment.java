package model;

import java.time.LocalDateTime;

/** Class that defines Appointment objects.
 * @author Lisa Cantrell
 * */

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

    /** Appointment constructor.
     * @param appointmentId primary key in database
     * @param title
     * @param description
     * @param location
     * @param type
     * @param startDateTime start date and time of appointment.
     * @param endDateTime end date and time of appointment.
     * @param assocCustId customer ID associated with appointment. foreign key.
     * @param assocUserId user ID associated with appointment. foreign key.
     * @param assocContactId contact ID associated with appointment. foreign key.
     * */

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

    /** getter for appointment ID.
     * @return appointmentId
     * */
    public int getAppointmentId() {
        return appointmentId;
    }

    /** setter for appointment ID. */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /** getter for appointment title.
     * @return title
     * */
    public String getTitle() {
        return title;
    }
    /** setter for appointment title. */
    public void setTitle(String title) {
        this.title = title;
    }

    /** getter for appointment description.
     * @return description
     * */
    public String getDescription() {
        return description;
    }

    /** setter for appointment description. */
    public void setDescription(String description) {
        this.description = description;
    }

    /** getter for appointment location.
     * @return location
     * */
    public String getLocation() {
        return location;
    }

    /** setter for appointment location. */
    public void setLocation(String location) {
        this.location = location;
    }

    /** getter for appointment type.
     * @return type
     * */
    public String getType() {
        return type;
    }

    /** setter for appointment type. */
    public void setType(String type) {
        this.type = type;
    }

    /** getter for appointment start time.
     * @return startDateTime
     * */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /** setter for appointment start time. */
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    /** getter for appointment end time.
     * @return endDateTime
     * */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /** setter for appointment end time. */
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    /** getter for appointment's associated customer ID.
     * @return assocCustId
     * */
    public int getAssocCustId() {
        return assocCustId;
    }

    /** setter for appointment's associated customer ID. */
    public void setAssocCustId(int assocCustId) {
        this.assocCustId = assocCustId;
    }

    /** getter for appointment's associated user ID.
     * @return assocUserId
     * */
    public int getAssocUserId() {
        return assocUserId;
    }

    /** setter for appointment's associated user ID. */
    public void setAssocUserId(int assocUserId) {
        this.assocUserId = assocUserId;
    }

    /** getter for appointment's associated contact ID.
     * @return assocContactId
     * */
    public int getAssocContactId() {
        return assocContactId;
    }

    /** setter for appointment's associated contact ID. */
    public void setAssocContactId(int assocContactId) {
        this.assocContactId = assocContactId;
    }

}
