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
        // this.setLayout(new FlowLayout());


        this.messageForm.setActionListener(this);
    }

    private void addComponents() {
        this.add(menuView);
        this.add(this.messageForm);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("next")) {
            this.notify("messageSend", new MessageEvent(this.messageForm.getNameTextField().getText(), this.messageForm.getSubjectTextField().getText(),
                        this.messageForm.getCategoryComboBox().getActionCommand(), this.messageForm.getContactDetailsTextField().getText(),
                        this.messageForm.getMessageTextArea().getText()));
        }
        if (e.getActionCommand().equals("cancel"))
        {

        }
    }
}
