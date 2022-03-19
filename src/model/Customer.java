package model;

/** Class that defines Customer objects.
 * @author Lisa Cantrell
 * */

public class Customer {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String country;
    private String division;

    /** Customer constructor.
     * @param customerId primary key
     * @param customerName
     * @param address
     * @param postalCode
     * @param phoneNumber
     * @param country
     * @param division
     * */
    public Customer(int customerId, String customerName, String address, String postalCode, String phoneNumber, String country, String division) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.division = division;
    }

    /** getter for customer ID.
     * @return customerId
     * */
    public int getCustomerId() {
        return customerId;
    }

    /** setter for customer ID. */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /** getter for customer name.
     * @return customerName
     * */
    public String getCustomerName() {
        return customerName;
    }

    /** setter for customer name. */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /** getter for customer address.
     * @return address
     * */
    public String getAddress() {
        return address;
    }

    /** setter for customer address. */
    public void setAddress(String address) {
        this.address = address;
    }

    /** getter for customer postal code.
     * @return postalCode
     * */
    public String getPostalCode() {
        return postalCode;
    }

    /** setter for customer postal code. */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /** getter for customer phone number.
     * @return phoneNumber
     * */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /** setter for customer phone number. */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /** getter for customer country.
     * @return country
     * */
    public String getCountry() {
        return country;
    }

    /** setter for customer country. */
    public void setCountry(String country) {
        this.country = country;
    }

    /** getter for customer division.
     * @return division
     * */
    public String getDivision() {
        return division;
    }

    /** setter for customer division. */
    public void setDivision(String division) {
        this.division = division;
    }


}
