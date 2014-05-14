package users;

/**
 * Created by Martin on 2014-05-14.
 */
public class Administrator extends User
{
    public Administrator(int id, String username, String password, String first_name, String last_name)
    {

        super.setFirstName(first_name);
        super.setLastName(last_name);
        super.setPassword(password);
        super.setUsername(username);
        super.setId(id);
    }
}
