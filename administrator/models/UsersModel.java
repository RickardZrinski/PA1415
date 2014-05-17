package administrator.models;

import shared.users.User;

import java.util.ArrayList;

/**
 * Created by Martin on 2014-05-14.
 */
public class UsersModel
{
    private ArrayList<User> users;
    private int nrOfUsers;
    public UsersModel()
    {
        nrOfUsers = 0;
        users = new ArrayList<User>();
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

            try {
                users.get(index).setPassword(password);
            } catch (Exception e) {
                e.printStackTrace();
            }

            users.get(index).setFirstName(firstName);
            users.get(index).setLastName(lastName);
        }
    }

    public User getUser(int index)
    {
        return users.get(index);
    }
}
