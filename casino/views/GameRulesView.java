package casino.views;

import shared.View;
import casino.MainFrame;
import casino.views.forms.GameRulesResultForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author  John Mogensen
 * @since   21/05/2014
 */
public class GameRulesView extends JPanel {
    private GameRulesResultForm gameRulesForm;

    public GameRulesView() {
        this.gameRulesForm = new GameRulesResultForm("Game Rules", "RULES TEXT FROM A GAME", "Play");

        this.configure();
        this.addComponents();

        MainFrame.getInstance().setTitle("GAME TITLE");
    }

    private void configure() {
        this.setLayout(new BorderLayout());

        this.gameRulesForm.getConfirmButton().addActionListener(this::nextAction);
        this.gameRulesForm.getCancelButton().addActionListener(this::cancelAction);
    }

    private void addComponents() {
        this.add(this.gameRulesForm, BorderLayout.CENTER);
    }

    private void nextAction(ActionEvent e) {
        System.out.println("Next");
    }

    private void cancelAction(ActionEvent e) {
        System.out.println("Cancel");
    }
}
