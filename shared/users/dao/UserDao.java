package shared.users.dao;

import shared.users.User;
import shared.users.Role;
import shared.users.Account;
import sql.Connector;
import sql.Dapper;

import java.sql.Connection;

/**
 * @author  Dino Opijac
 * @since   16/05/14
 */
public class UserDao implements IDao<User> {
    Dapper<User> userData;
    Dapper<Account> accountData;
    Dapper<Role> roleData;

    public UserDao() {
        Connection connection = Connector.getConnection();

        // Let the dapper share the same instance of connection.
        this.userData = new Dapper<>(User.class, connection);
        this.accountData = new Dapper<>(Account.class, connection);
        this.roleData = new Dapper<>(Role.class, connection);
    }

    @Override
    public User get(Object key) {
        Integer primaryKey = (Integer)key;

        User user = userData.getUsingPrimaryKey(primaryKey);
        user.setAccount(accountData.getUsingPrimaryKey(primaryKey));
        user.setRole(roleData.getUsingPrimaryKey(primaryKey));

        return user;
    }

    @Override
    public User insert(User user) {
        // Insert the user
        userData.insert(user);

        // Retrieve the last id and push it to the account
        user.getAccount().setId(userData.getLastInsertId());

        // Insert account
        accountData.insert(user.getAccount());

        return user;
    }

    @Override
    public boolean update(User user) {
        int key = user.getId();

        if (key >= 0) {
            userData.update(user.getId(), user);
            accountData.update(user.getId(), user.getAccount());

            return true;
        }

        return false;
    }

    @Override
    public boolean delete(User user) {
        int key = user.getId();

        if (key >= 0) {
            userData.delete(key);
            accountData.delete(key);

            return true;
        }

        return false;
    }
}
