package casino.views;

import casino.views.forms.GameRulesResultForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author  John Mogensen
 * @since   21/05/2014
 */
public class GameResultView extends JPanel {
    private GameRulesResultForm gameResultForm;

    public GameResultView() {
        this.gameResultForm = new GameRulesResultForm("Results", "RESULT INFO FROM GAME\n\n\nWould you like to play again?", "Play Again");

        this.configure();
        this.addComponents();
    }

    private void configure() {
        this.setLayout(new BorderLayout());

        this.gameResultForm.getConfirmButton().addActionListener(this::nextAction);
        this.gameResultForm.getCancelButton().addActionListener(this::cancelAction);
    }

    private void addComponents() {
        this.add(this.gameResultForm, BorderLayout.CENTER);
    }

    private void nextAction(ActionEvent e) {
        System.out.println("Next");
    }

    private void cancelAction(ActionEvent e) {
        System.out.println("Cancel");
    }
}