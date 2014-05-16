package shared.users.relation;

import sql.annotations.PrimaryKey;

/**
 * @author  Dino Opijac
 * @since   11/05/14
*/
public class UserRole {
    @PrimaryKey private int rid;
    private int uid;
}
