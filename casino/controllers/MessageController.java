package casino.controllers;

import casino.MainFrame;
import casino.events.MessageEvent;
import casino.events.MessageListener;
import casino.views.MessageView;

/**
 * @author  John Mogensen
 * @since   20/05/2014
 */
public class MessageController implements MessageListener {
    private MessageView messageView;

    public MessageController() {}

    public void messageAction() {
        this.messageView = new MessageView();
        this.messageView.subscribe(this);
        MainFrame.getInstanc().add(this.messageView);
    }

    @Override
    public void messageSend(MessageEvent e) {
        if (e.isValid()){
            System.out.println("MESSAGE INPUT VALID");
        }
    }
}
