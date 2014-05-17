package shared.dao;

import shared.users.User;
import shared.users.Role;
import shared.users.Account;
import shared.users.relation.UserRole;
import utilities.sql.Dapper;

import java.util.ArrayList;
import java.util.Collection;

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
        this.userData = new Dapper<>(User.class);
        this.accountData = new Dapper<>(Account.class);
        this.roleData = new Dapper<>(Role.class);
        this.userRoleData = new Dapper<>(UserRole.class);
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

    public Collection<User> getCollection() {
        Collection<User> users = new ArrayList<>();

        for(User user: userData.getCollection())
            users.add(get(user.getId()));

        return users;
    }

    @Override
    public boolean insert(User user) {
        boolean success = true;

        try {
            // Insert the user
            userData.insert(user);

            int lastUserId = userData.getLastInsertId();

            // Retrieve the last id and push it to the account
            user.getAccount().setId(lastUserId);

            // Insert account
            accountData.insert(user.getAccount());

            // Insert role
            userRoleData.insert(new UserRole(user.getRole().getId(), lastUserId));
        } catch(Exception e) {
            success = false;
        }

        return success;
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
