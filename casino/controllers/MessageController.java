package casino.controllers;

import casino.MainFrame;
import casino.views.MessageView;

import java.awt.*;

/**
 * @author  John Mogensen
 * @since   20/05/2014
 */
public class MessageController {
    private MessageView messageView = new MessageView();

    public MessageController() {
        this.messageView.subscribe(this);

        MainFrame.getInstance().add(this.messageView, BorderLayout.CENTER);
    }
}
