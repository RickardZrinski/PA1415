package shared.users;

import utilities.sql.annotations.Ignore;
import utilities.sql.annotations.PrimaryKey;
import utilities.Hash;

/**
 * This class models a user, requires {@link utilities.Hash}
 * to store passwords
 *
 * @author  Dino Opijac
 * @since   11/05/14
 * @see     utilities.Hash
 */
public class User {
    @PrimaryKey private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int numberOfTrials = 0;
    private int ssn = 0;
    private String email = null;

    @Ignore private Account account;
    @Ignore private Role role;

    public User() {
        this("default", "default", "-", "-", 0, 0, "default@user");
    }

    public User(String username, String password, String firstName, String lastName, int numberOfTrials, int ssn, String email) {
        this(username, password, firstName, lastName, numberOfTrials, ssn, email, new Account(), new Role());
    }

    public User(String username, String password, String firstName, String lastName, int numberOfTrials, int ssn, String email, Account account, Role role) {
        this.username = username;

        try {
            this.setPassword(password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfTrials = numberOfTrials;
        this.ssn = ssn;
        this.email = email;
        this.account = account;
        this.setRole(role);
    }

    /**
     * Sets a username
     * @param   username    The desired username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the hashed password of the user
     * @param   password    The password in plain text
     */
    public void setPassword(String password) throws Exception {
        this.password = Hash.createHash(password);
    }

    /**
     * Sets the first name
     * @param   firstName   The first name of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name
     * @param   lastName    The last name of the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the number of trials
     * @param   numberOfTrials  Amount to make available for trials
     */
    public void setNumberOfTrials(int numberOfTrials) {
        this.numberOfTrials = numberOfTrials;
    }

    /**
     * Sets the players' social security number
     * @param   ssn             The social security number
     */
    public void setSSN(int ssn) {
        this.ssn = ssn;
    }

    /**
     * Sets the players' email address.
     * @param   email           The email address the player has submitted
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Retrieves the unique id of the user
     * @return  A id integer
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the username
     * @return  A username string
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the hashed password
     * @return  A password string
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retrieves the first name of the user
     * @return  A first name string
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves the last name of the user
     * @return  A last name string
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the number of trials left
     * @return  The number of trials left
     */
    public int getNumberOfTrials() {
        return numberOfTrials;
    }

    /**
     * Retrieves the social security number of the player
     * @return  The social security number
     */
    public int getSSN() {
        return ssn;
    }

    /**
     * Retrieves the email address of the player
     * @return  The email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the player's game account
     * @see     shared.users.Account
     * @return  The instance of the game account
     */
    public Account getAccount() {
        return this.account;
    }

    /**
     * Retrieves the player's game account
     * @see     shared.users.Account
     * @return  The instance of the game account
     */
    public Role getRole() {
        return this.role;
    }

    @Override
    public String toString() {
        return String.format("User: [ id: %d, username: %s, password: %s, firstName: %s, lastName: %s, numberOfTrials: %d, ssn: %d, email: %s ]\nUser.Account: [ %s ]\nUser.Role: [ %s ]",
                this.id, this.username, this.password, this.firstName, this.lastName, this.numberOfTrials, this.ssn, this.email, this.account, this.role);
    }
}