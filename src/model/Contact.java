package model;

/** Class that defines Customer objects.
 * @author Lisa Cantrell
 * */

public class Contact {

    private int contactId;
    private String contactName;
    private String email;

    /** Customer constructor.
     * @param contactId primary key
     * @param contactName
     * @param email
     * */
    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /** getter for contact ID.
     * @return contactId
     * */
    public int getContactId() {
        return contactId;
    }

    /** setter for contact ID. */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /** getter for contact name.
     * @return contactName
     * */
    public String getContactName() {
        return contactName;
    }

    /** setter for contact name. */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /** getter for email.
     * @return email
     * */
    public String getEmail() {
        return email;
    }

    /** setter for contact email. */
    public void setEmail(String email) {
        this.email = email;
    }

    /** override toString method to display contactName when called.
     * @return contactName
     * */
    @Override
    public String toString() {
        return contactName;
    }
}
