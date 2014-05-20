package casino.events;

/**
 * @author  John Mogensen
 * @since   20/05/2014
 */
public class MessageEvent {
    private String fullName;
    private String subject;
    private String category;
    private String contactDetails;
    private String message;


    public MessageEvent(String fullName, String subject, String category, String contactDetails, String message) {
        this.fullName = fullName;
        this.subject = subject;
        this.category = category;
        this.contactDetails = contactDetails;
        this.message = message;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isValid() {
        return !this.fullName.isEmpty() && !this.subject.isEmpty() &&
               !this.contactDetails.isEmpty() && !this.message.isEmpty();
    }
}
