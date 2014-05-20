package shared;

/**
 * @author  John Mogensen
 * @since   20/05/2014
 */
public class MessageCategory {
    private int id;
    private String name;

    public MessageCategory() {
        this.id = 0;
        this.name = "?";
    }

    public MessageCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
