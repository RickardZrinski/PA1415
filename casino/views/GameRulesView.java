package casino.views;

import casino.AbstractView;
import casino.MainFrame;
import casino.views.forms.GameRulesResultForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author  John Mogensen
 * @since   21/05/2014
 */
public class GameRulesView extends AbstractView implements ActionListener {
    private GameRulesResultForm gameRulesForm;


    public GameRulesView() {
        this.gameRulesForm = new GameRulesResultForm("Game Rules", "RULES TEXT FROM A GAME", "Play");

        this.configure();
        this.addComponents();

        MainFrame.getInstance().setTitle("GAME TITLE");
    }

    private void configure() {
        this.setLayout(new BorderLayout());
        this.gameRulesForm.setActionListener(this);
    }

    private void addComponents() {
        this.add(this.gameRulesForm, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("cancel")){
            System.out.println("User canceled.");
        }
        else
        {
            System.out.println("Play");
        }

    }
}
