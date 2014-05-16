package shared.users;

import java.util.ArrayList;

/**
 * Created by Martin on 2014-05-14.
 */
public class UsersModel
{
    private ArrayList<users.User> users;
    private int nrOfUsers;
    public UsersModel()
    {
        nrOfUsers = 0;
        users = new ArrayList<users.User>();
    }

    public int getNrOfUsers()
    {
        return nrOfUsers;
    }

    public void editUser(int index, String userName, String password, String firstName, String lastName)
    {
        if(index < users.size())
        {
            users.get(index).setUsername(userName);
            users.get(index).setPassword(password);
            users.get(index).setFirstName(firstName);
            users.get(index).setLastName(lastName);
        }
    }

    public users.User getUser(int index)
    {
        return users.get(index);
    }
}
