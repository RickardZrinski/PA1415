package shared.dao;

import shared.users.User;
import shared.users.Role;
import shared.users.Account;
import shared.users.relation.UserRole;
import utilities.sql.Connector;
import utilities.sql.Dapper;

import java.sql.Connection;

/**
 * @author  Dino Opijac
 * @since   16/05/14
 */
public class UserDao implements IDao<User> {
    Dapper<User> userData;
    Dapper<Account> accountData;
    Dapper<Role> roleData;
    Dapper<UserRole> userRoleData;

    public UserDao() {
        // Let the dapper share the same instance of connection.
        Connection connection = Connector.getConnection();

        this.userData = new Dapper<>(User.class, connection);
        this.accountData = new Dapper<>(Account.class, connection);
        this.roleData = new Dapper<>(Role.class, connection);
        this.userRoleData = new Dapper<>(UserRole.class, connection);
    }

    @Override
    public User get(Object key) {
        Integer primaryKey = (Integer)key;

        User user = userData.getUsingPrimaryKey(primaryKey);
        Role role = roleData.getUsingPrimaryKey(userRoleData.getUsingPrimaryKey(primaryKey).getRid());

        user.setAccount(accountData.getUsingPrimaryKey(primaryKey));
        user.setRole(role);


        return user;
    }

    @Override
    public User insert(User user) {
        // Insert the user
        userData.insert(user);

        int lastUserId = userData.getLastInsertId();

        // Retrieve the last id and push it to the account
        user.getAccount().setId(lastUserId);

        // Insert account
        accountData.insert(user.getAccount());

        // Insert role
        userRoleData.insert(new UserRole(user.getRole().getId(), lastUserId));

        return user;
    }

    @Override
    public boolean update(User user) {
        int key = user.getId();

        if (key >= 0) {
            userData.update(user.getId(), user);
            accountData.update(user.getId(), user.getAccount());

            UserRole userRole = userRoleData.getUsingPrimaryKey(user.getId());

            // Roles are not equal, update
            if (userRole.getRid() != user.getRole().getId()) {
                userRole.setRid(user.getRole().getId());
                userRoleData.update(user.getId(), userRole);
            }

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
            userRoleData.delete(key);

            return true;
        }

        return false;
    }
}
