package casino.views;

import casino.MainFrame;
import casino.events.GameListener;
import casino.events.GameResponse;
import com.sun.codemodel.internal.JOp;
import shared.View;
import casino.views.components.Box;
import casino.views.forms.SimpleForm;
import shared.game.Die;
import utilities.ComponentUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class GameView extends View<GameListener> implements GameResponse{
    private MenuView menu = new MenuView();
    private JButton nextButton = new JButton("Go to next card");
    private CardLayout cards = new CardLayout();
    private JPanel card = new JPanel(cards);

    // All views contained in the UI.
    private JPanel selectableGamesView;
    private GameRulesView gameRulesView = new GameRulesView();
    private SimpleForm betView = new SimpleForm("Amount to bet", "Bet");
    private PlayView playView = new PlayView(15);
    private GameResultView gameResultView = new GameResultView();

    public GameView() {
        this.configure();
        this.addComponents();
    }

    private void configure() {
        this.setLayout(new BorderLayout());
        this.nextButton.addActionListener(this::nextAction);

        // configure buttons
        this.gameRulesView.getNextButton().addActionListener(this::nextAction);
        this.gameRulesView.getCancelButton().addActionListener(this::cancelAction);
        this.betView.getConfirmButton().addActionListener(this::betAction);
        this.playView.getTossButton().addActionListener(this::tossAction);
        this.gameResultView.getPlayAgainButton().addActionListener(this::playAgainAction);
        this.gameResultView.getCancelButton().addActionListener(this::cancelAction);

    }

    private void addComponents() {
        this.add(this.menu, BorderLayout.PAGE_START);

        // Create the first selectable games view
        this.selectableGamesView = Box.create(15, "Text");

        ComponentUtilities.printDimensions(this.selectableGamesView);

        // Add all elements to the carded view
        this.card.add(this.selectableGamesView, "1");
        this.card.add(this.gameRulesView, "2");
        this.card.add(this.betView, "3");
        this.card.add(this.playView, "4");
        this.card.add(this.gameResultView, "5");

        // Add the carded view to the center
        this.add(this.card, BorderLayout.CENTER);

        // Add the next button to the bottom
        this.add(this.nextButton, BorderLayout.PAGE_END);

        MainFrame.getInstance().add(this);
    }

    private void nextAction(ActionEvent e) {
        this.cards.next(this.card);
    }

    private void cancelAction(ActionEvent e) {
        this.cards.show(this.card, "1");
    }

    private void betAction(ActionEvent e) {
        double amount = new Double(this.betView.getFormattedField().getText());
        this.getObservers().forEach(o -> o.bet(amount));
    }

    private void tossAction(ActionEvent e) {
        this.getObservers().forEach(o -> o.toss());
    }

    private void playAgainAction(ActionEvent e) {
        this.getObservers().forEach(o -> o.playAgain());
    }

    @Override
    public void displayRules(String rules) {
        this.gameRulesView.setRules(rules);
        this.cards.show(this.card, "2");
    }

    @Override
    public void betSuccessful() {
        this.cards.show(this.card, "4");
    }

    @Override
    public void betUnSuccessful() {
        JOptionPane.showMessageDialog(null, "Bet was unsuccessful.");
        this.cards.show(this.card, "1");
    }

    @Override
    public void updateDie(int index, int face) {

    }

    @Override
    public void updateNumberOfThrows(int numberOfThrows) {

    }

    @Override
    public void displayResult(String result) {
        this.gameResultView.setResult(result);
        this.cards.show(this.card, "5");
    }
}
