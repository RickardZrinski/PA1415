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

    /**
     * Retrieves a user name from the database with a given key. Imagine this table
     * in the database:
     *
     * ----+----------+-----------+---------
     *  id | username | firstName | lastName
     * ----+----------+-----------+---------
     *  1  | user1    | Carl      | Sagan
     *  2  | marcus   | Marcus    | Fenix
     *
     * Example usage:
     *
     * UserDao dao = new UserDao();
     * User user = dao.get(1); // user.getFirstName() = Carl, user.getLastName() = Sagan
     * User user = dao.get(2); // user.getFirstName() = Marcus, user.getLastName() = Fenix
     *
     * @param   key     The key (id) to get from the database
     * @return          A user object that contains the user and all
     *                  (including account and role)
     */
    @Override
    public User get(Object key) {
        Integer primaryKey = (Integer)key;

        User user = userData.getUsingPrimaryKey(primaryKey);
        Role role = roleData.getUsingPrimaryKey(userRoleData.getUsingPrimaryKey(primaryKey).getRid());

        user.setAccount(accountData.getUsingPrimaryKey(primaryKey));
        user.setRole(role);

        return user;
    }

    /**
     * Returns a role using a name.
     *
     * IMPORTANT: This does not return a role for a user. It returns a object
     * for a role INSIDE the database!
     *
     * Example usage:
     * UserDao dao = new UserDao();
     * Role admin  = dao.getRole("Administrator");
     * Role user   = dao.getRole("User");
     *
     * System.out.println( admin.canAccessAdministration() ); // this will print: true
     * System.out.println( user.canAccessAdministration() );  // this will print: false
     *
     * @param   roleName    the role you want to retrieve
     * @return              a role object with all data
     */
    public Role getRole(String roleName) {
        return roleData.getUsingForeignKey("name", roleName);
    }

    /**
     * Retrieves a collection of users from the database. You can use
     * this method to get a long list of all users in the database. You
     * even cast it to the desired collection.
     *
     * Example usage:
     * UserDao dao = new UserDao();
     * ArrayList<Users> allUsers = (ArrayList<User>)dao.getCollection();
     *
     * 'allUsers' now contains ALL users in the database.
     *
     * @return  a collection that can be cast to ArrayList or similar.
     */
    public Collection<User> getCollection() {
        Collection<User> users = new ArrayList<>();

        for(User user: userData.getCollection())
            users.add(this.get(user.getId()));

        return users;
    }

    /**
     * Inserts a new user to the database.
     *
     * Example usage:
     * UserDao dao  = new UserDao();
     * User newUser = new User("BoUser", "password", "Bo", "Anderson", 0, 196003012047, "bo@anderson.se");
     *
     * if(dao.insert(newUser)) {
     *     Bo was added to the database
     * } else {
     *     Bo could not be added to the database, something is wrong
     * }
     *
     * @param   user    the user object to insert
     * @return          true if the user was inserted, else false
     */
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

    /**
     * Retrieves a user object and updates it
     *
     * IMPORTANT: This object requires you to have contents inside of Account and
     * Role.
     *
     * UserDao dao = new UserDao();
     * User editUser = dao.get(1);
     *
     * editUser.setFirstName("Kelly");
     * editUser.setLastName("Swanson");
     *
     * if(dao.update(editUser)) {
     *     User updated
     * } else
     *     User not updated
     * }
     *
     * @param user the user object to update.
     * @return true if the update was a success, false otherwise
     */
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

    /**
     * Deletes a user from the database. This object requires you to have
     * User id set. It cannot be the default value.
     *
     * Usage:
     * // Assume that userToDelete contains a User object
     * UserDao dao = new UserDao();
     *
     * if (dao.delete(userToDelete)) {
     *     User was deleted
     * } else {
     *     User was not deleted, something went wrong
     * }
     *
     * @param   user    the user object to delete
     * @return          true if the user was deleted, else false
     */
    @Override
    public boolean delete(User user) {
        return this.delete(user.getId());
    }

    /**
     * Does the same thing as {@link shared.dao.UserDao#delete(shared.users.User)} but
     * it uses user id parameter instead of the whole user object.
     *
     * Usage:
     * UserDao dao = new UserDao();
     *
     * if (dao.delete(10)) {
     *     User was deleted
     * } else {
     *     User was not deleted, something went wrong
     * }
     *
     * @param   id      the unique id number of the user to delete
     * @return          true if the user was deleted, else false
     */
    public boolean delete(int id) {
        boolean success = true;

        if (id > 0) {
            try {
                userData.delete(id);
                accountData.delete(id);
                userRoleData.delete(id);
            } catch(Exception e) {
                success = false;
            }
        } else {
            success = false;
        }

        return success;
    }
}
