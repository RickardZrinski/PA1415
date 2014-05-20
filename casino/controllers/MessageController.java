package casino.controllers;

import casino.MainFrame;
import casino.events.LoginEvent;
import casino.events.MessageListener;
import casino.views.MessageView;

import java.awt.*;

/**
 * @author  John Mogensen
 * @since   20/05/2014
 */
public class MessageController implements MessageListener {
    private MessageView messageView = new MessageView();

    public MessageController() {
        this.messageView.subscribe(this);

        MainFrame.getInstance().add(this.messageView, BorderLayout.CENTER);
    }

    @Override
    public void messageSend(LoginEvent e) {
        if (e.isValid()){
            System.out.println("MESSAGE INPUT VALID");
        }
    }
}
