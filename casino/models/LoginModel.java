package casino.models;
import shared.Model;
import casino.events.LoginEvent;
import casino.events.LoginResponse;
import shared.AuthenticationSession;
import shared.dao.UserDao;
import shared.users.User;
import utilities.Hash;

public class LoginModel extends Model<LoginResponse> {
    private UserDao users = new UserDao();

    public LoginModel() {}

    public void authenticate(LoginEvent event) {
        try {
            // Attempt to retrieve the user
            User user = users.get(event.getUsername());

            /**
             * Check if the user is allowed to sign in; his or her role will let us know.
             * Compare the password retrieved and the password stored in the database.
             */
            if (user.getRole().canAuthenticate() && Hash.validatePassword(event.getPassword(), user.getPassword())) {
                // Get a instance of the authentication and start it
                AuthenticationSession.start(user);

                // Let our listeners know that sign in was a success.
                this.getObservers().forEach(LoginResponse::loginSuccessful);
            } else
                this.getObservers().forEach(LoginResponse::loginUnsuccessful); // The password is wrong or the user cannot log in

        } catch (Exception e) {
            this.getObservers().forEach(LoginResponse::loginUnsuccessful);
            e.printStackTrace();
        }
    }
}
