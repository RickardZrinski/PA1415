package casino.views;

import casino.views.forms.GameRulesResultForm;

import javax.swing.*;
import java.awt.*;

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
    }

    private void addComponents() {
        this.add(this.gameResultForm, BorderLayout.CENTER);
    }

    public JButton getPlayAgainButton() {
        return this.gameResultForm.getConfirmButton();
    }

    public JButton getCancelButton() {
        return this.gameResultForm.getCancelButton();
    }

    public void setResult(String result) {
        this.gameResultForm.getTextTextArea().setText(result);
    }
}