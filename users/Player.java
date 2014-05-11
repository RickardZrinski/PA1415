package users;

/**
 * This class creates a concrete player user.
 *
 * @author  Dino Opijac
 * @since   11/05/14
 */
public class Player extends User {
    private int numberOfTrials;
    private int ssn;
    private String email;

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
        return super.toString() + String.format(" numberOfTrials: %d, ssn: %d, email: %s", this.numberOfTrials, this.ssn, this.email);
    }
}
