package shared;

import shared.users.User;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class AuthenticationSession {
    private static AuthenticationSession session = null;
    private User user;

    /**
     * Disable default constructor
     */
    private AuthenticationSession() {}

    /**
     * The constructor is disabled from the outside
     * @param   user  the user to add to the session.
     */
    private AuthenticationSession(User user) {
        this.user = user;
    }

    /**
     * Retrieves the authentication instance
     * @return              a instance of the authentication session
     * @throws  Exception   when there is no authentication started
     */
    public static AuthenticationSession getInstance() throws Exception {
        if (AuthenticationSession.session == null)
            throw new Exception("There is no session started.");
        else
            return AuthenticationSession.session;
    }

    /**
     * Starts the user session
     * @param   user  the user to start the session with
     */
    public static void start(User user) {
        if (AuthenticationSession.session == null)
            AuthenticationSession.session = new AuthenticationSession(user);
    }

    /**
     * Stops the session (effectively logging out)
     */
    public static void stop() {
        // Destroy all data inside this class
        AuthenticationSession.session = null;
    }

    /**
     * @return  the user that is attached to the session
     */
    public User getUser() {
        return this.user;
    }
}
