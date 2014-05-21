package casino.views;

import casino.AbstractView;
import casino.MainFrame;
import casino.events.MessageEvent;
import casino.views.forms.MessageForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author  John Mogensen
 * @since   20/05/2014
 */
public class MessageView extends AbstractView implements ActionListener {
    private MenuView menuView = new MenuView();
    private MessageForm messageForm = new MessageForm();

    public MessageView() {
        this.configure();
        this.addComponents();

        MainFrame.getInstance().setTitle("Message");
    }

    private void configure() {
        this.messageForm.setActionListener(this);
    }

    private void addComponents() {
        this.setLayout(new BorderLayout());
        this.add(this.menuView, BorderLayout.PAGE_START);
        this.add(this.messageForm, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("send")) {
            this.notify("messageSend", new MessageEvent(this.messageForm.getNameTextField().getText(), this.messageForm.getSubjectTextField().getText(),
                        this.messageForm.getCategoryComboBox().getActionCommand(), this.messageForm.getContactDetailsTextField().getText(),
                        this.messageForm.getMessageTextArea().getText()));
        }
        else
        {
            System.out.println("User canceled.");
        }
    }
}
