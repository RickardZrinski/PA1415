package shared.messages;

import utilities.sql.annotations.PrimaryKey;

/**
 * @author  John Mogensen
 * @since   20/05/2014
 */
public class MessageCategory {
    @PrimaryKey private int cid;
    private String name;

    /**
     * Creates a default category
     */
    public MessageCategory() {
        this.cid = 1;
        this.name = "Customer Service";
    }

    /**
     * Sets the category id and name
     * @param id    the unique id
     * @param name  the name of the category
     */
    public MessageCategory(int id, String name) {
        this.cid = id;
        this.name = name;
    }

    /**
     * @return retrieves the unique category id
     */
    public int getCid() {
        return this.cid;
    }

    /**
     * Sets a unique category id
     * @param cid the unique category id
     */
    public void setCid(int cid) {
        this.cid = cid;
    }

    /**
     * @return the name of the category
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a name to the category
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
