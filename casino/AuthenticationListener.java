package casino;

import casino.events.AuthenticationFormEvent;

/**
 * @author  Dino Opijac
 * @since   18/05/2014
 */
public interface AuthenticationListener extends Listener {
    public void authenticationPerformed(AuthenticationFormEvent e);
}
