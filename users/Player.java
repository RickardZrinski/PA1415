package users;

/**
 * This class creates a concrete player user.
 *
 * @author  Dino Opijac
 * @since   11/05/14
 */
public class Player extends User {
    private Account account;
    private int numberOfTrials = 0;
    private int ssn = 0;
    private String email = null;

    /**
     * Creates an empty player object
     */
    public Player() {
        this.account = new Account();
    }

    /**
     * Creates a new player object
     * @param   username    The username of the player
     * @param   firstName   The first name of the player
     * @param   lastName    The last name of the player
     * @param   ssn         The social security number of the player
     * @param   email       The email address of the player
     * @param   balance     The balance of the account
     */
    public Player(String username, String firstName, String lastName, int ssn, String email, float balance) {
        super.setUsername(username);
        super.setFirstName(firstName);
        super.setLastName(lastName);

        this.account = new Account(balance);
        this.ssn = ssn;
        this.email = email;
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

    /**
     * Retrieves the player's game account
     * @see     users.Account
     * @return  The instance of the game account
     */
    public Account getAccount() {
        return this.account;
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

    @Override
    public String toString() {
        return super.toString() + String.format(" numberOfTrials: %d, ssn: %d, email: %s, Balance: %f", this.numberOfTrials, this.ssn, this.email, this.account.getBalance());
    }
}
