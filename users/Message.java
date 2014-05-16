package users;

/**
 * Created by JamJaws.
 */
public class Message {
    private int id;
    private long timestamp;
    private String subject;
    private String category;
    private String message;
    private String contact;
    private boolean read; // not in conceptual still might be needed


    /**
     * Default constructor.
     */
    public Message() {
        this.id = 0; // should be some other value
        this.timestamp = System.currentTimeMillis() / 1000; // UNIX time when message was created
        this.subject = "?";
        this.category = "?";
        this.message = "?";
        this.contact = "?";
        this.read = false;
    }

    /**
     * Constructor.
     */
    public Message(String subject, String category, String message, String contact) {
        this.id = 0; // should be some other value
        this.timestamp = System.currentTimeMillis() / 1000; // UNIX time when message was created
        this.subject = subject;
        this.category = category;
        this.message = message;
        this.contact = contact;
        this.read = false;
    }

    /**
     * Gets id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets timestamp.
     */
    public long getTimestamp() {
        return this.timestamp;
    }

    /**
     * Sets timestamp.
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets subject.
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * Sets subject.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets category.
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Sets category.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets contact.
     */
    public String getContact() {
        return this.contact;
    }

    /**
     * Sets contact.
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Gets read.
     */
    public boolean isRead() {
        return read;
    }

    /**
     * Sets read.
     */
    public void setRead(boolean read) {
        this.read = read;
    }

    /**
     * Sends message to a user.
     */
    public void send(User user) {
        // somehome add this message to a users messages
    }
}
