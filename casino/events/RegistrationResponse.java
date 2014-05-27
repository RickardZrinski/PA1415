package casino.events;

/**
 * @author  Dino Opijac
 * @since   27/05/2015
 */
public interface RegistrationResponse {
    public enum Registration {
        OCCUPIED, INVALID, PASSWORD_NO_MATCH
    }

    public void registrationSuccessful();
    public void registrationUnsuccessful(Registration reason);
}
