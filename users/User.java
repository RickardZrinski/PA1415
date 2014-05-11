package users;

/**
 * This class models a user
 *
 * @author  Dino Opijac
 * @since   11/05/14
 */
public abstract class User {
    private int id = 0;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    
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
    public void setPassword(String password) {
        this.password = this.hash(password);
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
     * Hashes and salts a password.
     * @param   password    The password to salt
     * @return  The hashed password
     */
    public String hash(String password) {
        return String.format("this.is.a.hashed.password.-%s", password);
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

    @Override
    public String toString() {
        return String.format("Id: %d, Username: %s, Password: %s, First Name: %s, Last Name: %s", this.id, this.username, this.password, this.firstName, this.lastName);
    }
}
