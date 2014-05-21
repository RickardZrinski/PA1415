package casino.events;

/**
 * @author  Dino Opijac
 * @since   18/05/2014
 */
public class LoginEvent {
    private String username = null;
    private char[] password = null;

    public LoginEvent(String username, char[] password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the password as a char array
     * @return the password as a char array
     */
    public char[] getPassword() {
        return this.password;
    }

    /**
     * Retrieves the password as a string
     * @return the password as a string
     */
    public String getPasswordString() {
        return String.valueOf(this.password);
    }

    /**
     * Retrieves the password fired by the event and stores it as a hash in
     * the class. To read more about why the password is a char see
     * <a href="http://bit.ly/S6pagF">What are the security reasons for JPasswordField.getPassword()?</a>
     *
     * @param   password    the character representation of the password
     */
    public void setPassword(char[] password) {
        this.password = password;
    }

    /**
     * Checks that the event is valid, i.e the username is set and
     * the password is not null
     *
     * @return true if the event is valid, else false
     */
    public boolean isValid() {
        boolean valid = false;

        if (!this.username.isEmpty() && !this.getPasswordString().isEmpty())
            valid = true;

        return valid;
    }

    @Override
    public String toString() {
        return String.format("%s: username: %s, password: %s, isValid: %b", this.hashCode(), this.username, this.getPasswordString(), this.isValid());
    }
}
