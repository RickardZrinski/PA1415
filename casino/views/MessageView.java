package casino.views;

import casino.views.components.MenuBar;
import shared.View;
import casino.MainFrame;
import casino.events.MessageEvent;
import casino.events.MessageListener;
import casino.views.forms.MessageForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author  John Mogensen
 * @since   20/05/2014
 */
public class MessageView extends View<MessageListener> {
    private MenuBar menuView = new casino.views.components.MenuBar();
    private MessageForm messageForm = new MessageForm();
    private JPanel view = new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 20));

    public MessageView() {
        this.configure();
        this.addComponents();

        MainFrame.getInstance().setTitle("Message");
    }

    private void configure() {
        this.messageForm.getSendButton().addActionListener(this::sendAction);
        this.messageForm.getCancelButton().addActionListener(this::cancelAction);
    }

    private void addComponents() {
        this.setLayout(new BorderLayout());

        this.view.add(this.messageForm);

        this.add(this.menuView, BorderLayout.PAGE_START);
        this.add(this.view, BorderLayout.CENTER);
    }

    private void sendAction(ActionEvent e) {
        this.getObservers().forEach(o -> o.messageSend(
            new MessageEvent(this.messageForm.getNameTextField().getText(), this.messageForm.getSubjectTextField().getText(),
                             this.messageForm.getCategoryComboBox().getActionCommand(), this.messageForm.getContactDetailsTextField().getText(),
                             this.messageForm.getMessageTextArea().getText())
        ));
    }

    private void cancelAction(ActionEvent e) {
        System.out.println("Canceled");
    }
}
