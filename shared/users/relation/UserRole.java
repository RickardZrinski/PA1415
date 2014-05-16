package shared.users.relation;

import sql.annotations.PrimaryKey;

/**
 * @author  Dino Opijac
 * @since   11/05/14
*/
public class UserRole {
    @PrimaryKey private int uid;
    private int rid;

    public int getRid() {
        return rid;
    }

    public int getUid() {
        return uid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public UserRole() {
        this.rid = 0;
        this.uid = 0;
    }

    public UserRole(int rid, int uid) {
        this.rid = rid;
        this.uid = uid;
    }
}
