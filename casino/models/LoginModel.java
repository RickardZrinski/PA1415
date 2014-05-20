package casino.models;
import casino.AbstractModel;

import java.util.Random;

public class LoginModel extends AbstractModel {

    public LoginModel() {}

    // Currently does nothing
    public void doSomething() {
        System.out.println("LoginModel#doSomething() invoked.");

        // Generate a random and notify the observers
        Random random = new Random();
        this.notify("loginResponse", random.nextBoolean());
    }
}
