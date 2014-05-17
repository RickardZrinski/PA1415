package shared;

import shared.users.User;

/**
 * @author  John Mogensen
 * @since   16/05/2014
 */
public class Message {
    private int id;
    private int timestamp;
    private String subject;
    private String message;
    private String contact;

    // @TODO: There needs to be a Category class
    private String category;

    // @TODO: Not in conceptual model. Might be needed -John
    private boolean read;


    /**
     * Default constructor.
     */
    public Message() {
        this.id = 0; // should be some other value
        this.timestamp = (int) System.currentTimeMillis() / 1000; // UNIX time when message was created
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
        this.timestamp = (int) System.currentTimeMillis() / 1000; // UNIX time when message was created
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
    public int getTimestamp() {
        return this.timestamp;
    }

    /**
     * Sets timestamp.
     */
    public void setTimestamp(int timestamp) {
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
     * Sends a message to a user
     * @param user the user to send the message to
     * @TODO:   Change logical layer class diagram in Assignment 3 from void to boolean
     */
    public boolean send(User user) {
        boolean sent = false;

        if (!user.getEmail().isEmpty()) {
            String email = user.getEmail();
            // Validate email address
            // @TODO: Add library javax.mail
            return true;
        }

        return sent;
    }
}
