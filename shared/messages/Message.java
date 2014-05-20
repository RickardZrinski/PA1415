package shared.messages;

import shared.users.User;
import utilities.sql.annotations.Ignore;
import utilities.sql.annotations.PrimaryKey;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author  John Mogensen
 * @since   16/05/2014
 */
public class Message {
    @PrimaryKey private int id;
    private String timestamp;
    private String fullName;
    private String subject;
    private String message;
    private String contact;
    @Ignore private MessageCategory category;
    private Boolean isRead;


    /**
     * Default constructor.
     */
    public Message() {
        this.id = 0; // should be some other value
        this.timestamp = "";
        this.fullName = "Not given";
        this.subject = "?";
        this.category = new MessageCategory();
        this.message = "?";
        this.contact = "?";
        this.isRead = false;

        // Update the timestamp
        this.updateTimestamp();
    }

    /**
     * Constructor.
     */
    public Message(String fullName, String subject, MessageCategory category, String message, String contact) {
        this.id = 0; // should be some other value
        this.fullName = fullName;
        this.subject = subject;
        this.category = category;
        this.message = message;
        this.contact = contact;
        this.isRead = false;

        // Update the timestamp
        this.updateTimestamp();
    }

    /**
     * @return the unique id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the unique id
     * @param   id      the unique id to be set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the timestamp in string format
     * @return the timestamp in string format
     */
    public String getTimestamp() {
        return this.timestamp;
    }

    /**
     * Updates the timestamp to the current time
     */
    public void updateTimestamp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.timestamp = dateFormat.format(new Date());
    }

    /**
     * Sets the full name
     * @param fullName the full name to be set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the full name of the person that has sent the message
     */
    public String getFullName() {
        return this.fullName;
    }

    /**
     * @return the subject of the message
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * Sets the subject
     * @param subject the subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return retrieves the message category
     */
    public MessageCategory getCategory() {
        return this.category;
    }

    /**
     * Sets category.
     */
    public void setCategory(MessageCategory category) {
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
     * @return true if the message was read, else false
     */
    public boolean isRead() {
        return this.isRead;
    }

    /**
     * Marks the message as read
     */
    public void markAsRead() {
        this.isRead = true;
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

    @Override
    public String toString() {
        return String.format("id: %d, timestamp: %s, subject: %s, category: %s, isRead: %b, message: %s", this.id, this.timestamp, this.subject, this.category, this.isRead, this.message);
    }
}
