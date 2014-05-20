package shared.dao;

import shared.messages.Message;
import shared.messages.MessageCategory;
import shared.messages.relation.MessageCategories;
import utilities.sql.Dapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author  Dino Opijac
 * @since   20/05/2014
 */
public class MessageDao implements IDao<Message> {
    private Dapper<Message> messageData = new Dapper<>(Message.class);
    private Dapper<MessageCategory> messageCategoryData = new Dapper<>(MessageCategory.class);
    private Dapper<MessageCategories> messageCategoriesData = new Dapper<>(MessageCategories.class);

    /**
     * Retrieve a message from the database using a unique key
     * @param   key     the unique key
     * @return          a message object associated with the unique key in the database
     */
    @Override
    public Message get(Object key) {
        Message message = null;

        if (messageData.count() > 0) {
            Integer primaryKey = (Integer)key;

            message = messageData.getUsingPrimaryKey(primaryKey);
            MessageCategory category = messageCategoryData.getUsingPrimaryKey(messageCategoriesData.getUsingPrimaryKey(primaryKey).getCid());

            message.setCategory(category);
        }

        return message;
    }

    /**
     * @return a collection of message objects
     */
    public Collection<Message> getCollection() {
        Collection<Message> messages = new ArrayList<>();

        for(Message message: messageData.getCollection())
            messages.add(this.get(message.getId()));

        return messages;
    }

    /**
     * Inserts a message to the database using a object made in Java
     * @param   message     the message object constructed by the application
     * @return              true if the message was inserted, else false
     */
    @Override
    public boolean insert(Message message) {
        boolean success = true;

        try {
            // Set the current time for the object
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            message.setTimestamp(dateFormat.format(new Date()));

            // Insert object
            this.messageData.insert(message);
            this.messageCategoriesData.insert(new MessageCategories(this.messageData.getLastInsertId(), message.getCategory().getCid()));
        } catch(Exception e) {
            success = false;
        }

        return success;
    }

    /**
     * Updates a message using a message object
     * @param   message     the message object to update
     * @return              true if the message was updated, else false
     */
    @Override
    public boolean update(Message message) {
        Boolean success = true;

        try {
            this.messageData.update(message.getId(), message);
        } catch (Exception e) {
            success = false;
        }

        return success;
    }

    /**
     * Deletes a message using a object
     * @see     MessageDao#delete(Object)
     * @param   message     the message object constructed by the application
     * @return              true if the message was deleted, else false
     */
    @Override
    public boolean delete(Message message) {
        return this.delete(message.getId());
    }

    /**
     * Deletes a message using a unique key (used by {@link MessageDao#delete(Message)})
     * @param   id          the unique key
     * @return              true if the message was deleted, else false
     */
    public boolean delete(int id) {
        Boolean success = true;

        if (id > 0) {
            try {
                messageData.delete(id);
                messageCategoriesData.delete(id);
            } catch(Exception e) {
                success = false;
            }
        } else {
            success = false;
        }

        return success;
    }
}
