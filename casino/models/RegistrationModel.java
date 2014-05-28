package casino.models;

import casino.events.RegistrationEvent;
import casino.events.RegistrationResponse;
import shared.Model;
import shared.dao.UserDao;
import shared.users.User;

/**
 * @author  Dino Opijac
 * @since   27/05/2015
 */
public class RegistrationModel extends Model<RegistrationResponse> {
    private UserDao users = new UserDao();

    public RegistrationModel() {}

    /**
     * Creates a new user using the event
     * @param e The event carrying the user data
     */
    public void create(RegistrationEvent e) {
        // Check if the user exists
        if (users.exists(e.getUsername())) {
            this.getObservers().forEach(o -> o.registrationUnsuccessful(RegistrationResponse.Registration.OCCUPIED));
        } else {
            // Create the new user using the details given from the event
            User newUser = new User(e.getUsername(), String.valueOf(e.getPassword()), e.getFirstName(), e.getLastName(), 5, e.getSsn(), e.getEmail());

            if (e.getPassword().length > 0 && users.insert(newUser))
                this.getObservers().forEach(RegistrationResponse::registrationSuccessful);
            else
                this.getObservers().forEach(o -> o.registrationUnsuccessful(RegistrationResponse.Registration.INVALID));
        }
    }
}
