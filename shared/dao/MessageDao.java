package shared.dao;

import shared.messages.Message;

/**
 * @author  Dino Opijac
 * @since   20/05/2014
 */
public class MessageDao implements IDao<Message> {
    @Override
    public Message get(Object key) {
        return null;
    }

    @Override
    public boolean insert(Message object) {
        return false;
    }

    @Override
    public boolean update(Message object) {
        return false;
    }

    @Override
    public boolean delete(Message object) {
        return false;
    }
}
