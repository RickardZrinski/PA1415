package administrator.models;

import shared.dao.GameDataDao;
import shared.dao.UserDao;
import shared.users.User;
import utilities.sql.Dapper;

import java.util.ArrayList;

/**
 * Created by Martin on 2014-05-14.
 */
public class UsersModel
{
    private ArrayList<User> users;

    public UsersModel()
    {
        users = new ArrayList<User>();

        AddUsersFromDB();
    }

    public int getNrOfUsers()
    {
        return users.size();
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

            //updates the changed parameters to the object in the database.
            UserDao dao = new UserDao();
            dao.update(users.get(index));
        }
    }

    public User getUser(int index)
    {
        return users.get(index);
    }

    public void AddUsersFromDB()
    {
        UserDao dao = new UserDao();

        for(User user: dao.getCollection())
        {
            users.add(user);
        }
    }
}
