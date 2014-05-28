package casino.views;

import casino.views.forms.GameRulesResultForm;

import javax.swing.*;
import java.awt.*;

/**
 * @author  John Mogensen
 * @since   21/05/2014
 */
public class GameRulesView extends JPanel {
    private GameRulesResultForm gameRulesForm;

    public GameRulesView() {
        this.gameRulesForm = new GameRulesResultForm("Game Rules", "RULES GOES HERE", "Play");

        this.configure();
        this.addComponents();
    }

    private void configure() {
        this.setLayout(new BorderLayout());
    }

    private void addComponents() {
        this.add(this.gameRulesForm, BorderLayout.CENTER);
    }

    public JButton getNextButton() {
        return this.gameRulesForm.getConfirmButton();
    }

    public JButton getCancelButton() {
        return this.gameRulesForm.getCancelButton();
    }

    public void setRules(String rules) {
        this.gameRulesForm.getTextTextArea().setText(rules);
    }
}
