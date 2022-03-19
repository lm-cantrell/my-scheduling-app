package model;

/** Class that defines User objects.
 * @author Lisa Cantrell
 * */

public class User {

    private int userId;
    private String userName;
    private String password;

    /** User constructor.
     * @param userId
     * @param userName
     * @param password
     * */
    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;

    }

    /** getter for user ID.
     * @return userId
     * */
    public int getUserId() {
        return userId;
    }

    /** setter for user ID. */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** getter for username.
     * @return userName
     * */
    public String getUserName() {
        return userName;
    }

    /** setter for username. */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** getter for user password.
     * @return userId
     * */
    public String getPassword() {
        return password;
    }

    /** setter for user password. */
    public void setPassword(String password) {
        this.password = password;
    }

}
