package casino.events;

/**
 * @author  Dino Opijac
 * @since   18/05/2014
 */
public interface LoginListener {
    public void loginPerformed(LoginEvent e);
    public void authorizationPerformed();
    public void logoutPerformed();
    public void registrationPerformed(RegistrationEvent e);
}
