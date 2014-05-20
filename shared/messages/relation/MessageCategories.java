package shared.messages.relation;

import utilities.sql.annotations.PrimaryKey;

/**
 * @author  Dino Opijac
 * @since   20/05/2014
 */
public class MessageCategories {
    @PrimaryKey private int mid;
    private int cid;

    /**
     * Creates the standard message category
     */
    public MessageCategories() {
        this.mid = 1;
        this.cid = 1;
    }

    /**
     * Sets the relation between two objects using the mid and cid
     * @param mid the message id
     * @param cid the category id
     */
    public MessageCategories(int mid, int cid) {
        this.mid = mid;
        this.cid = cid;
    }

    /**
     * Sets the category id
     * @param cid the category id
     */
    public void setCid(int cid) {
        this.cid = cid;
    }

    /**
     * Sets the message id
     * @param mid the message id
     */
    public void setMid(int mid) {
        this.mid = mid;
    }

    /**
     * @return the category id
     */
    public int getCid() {
        return this.cid;
    }

    /**
     * @return the message id
     */
    public int getMid() {
        return this.mid;
    }

}
