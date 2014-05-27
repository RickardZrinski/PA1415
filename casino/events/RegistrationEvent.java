package casino.events;

/**
 * @author  Dino Opijac
 * @since   27/05/2014
 */
public class RegistrationEvent {
    public String username;
    public String firstName;
    public String lastName;
    public char[] password;
    int ssn;
    String email;

    /**
     * Creates a new RegistrationEvent
     * @param username      the username
     * @param firstName     the first name of the user
     * @param lastName      the last name of the user
     * @param password      the password of the user
     * @param ssn           the social security number of the user
     * @param email         the email address of the user
     */
    public RegistrationEvent(String username, String firstName, String lastName, char[] password, int ssn, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.ssn = ssn;
        this.email = email;
    }

    /**
     * @return the username of the user
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @return the first name of the user
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * @return the last name of the user
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * @return the hashed password of the user
     */
    public char[] getPassword() {
        return this.password;
    }

    /**
     * @return the social security number of the user
     */
    public int getSsn() {
        return this.ssn;
    }

    /**
     * @return the email address of the user
     */
    public String getEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        return String.format("username: %s, password: %s, firstName: %s, lastName: %s ssn: %d, email: %s", this.username, String.valueOf(this.password), this.firstName, this.lastName, this.ssn, this.email);
    }
}
