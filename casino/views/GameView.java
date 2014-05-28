package casino.views;

import casino.MainFrame;
import casino.events.GameListener;
import casino.events.GameResponse;
import casino.views.components.Box;
import casino.views.components.MenuBar;
import casino.views.forms.GameRulesResultForm;
import shared.View;
import casino.views.forms.SimpleForm;
import shared.game.GameData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collection;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class GameView extends View<GameListener> implements GameResponse {
    private MenuBar menu = new casino.views.components.MenuBar();
    private CardLayout cards = new CardLayout();
    private JPanel card = new JPanel(cards);

    // All views contained in the UI.
    private JPanel selectableGamesView;
    private SimpleForm betView = new SimpleForm("Amount to bet", "Bet");
    private PlayView playView = new PlayView();

    private GameRulesResultForm rules   = new GameRulesResultForm("Game Rules", ":rules:", "Play");
    private GameRulesResultForm results = new GameRulesResultForm("Results", ":play again:", "Play Again");

    public GameView() {
        MainFrame.getInstance().setTitle("Games");

        this.configure();
        this.addComponents();
    }

    private void configure() {
        this.setLayout(new BorderLayout());

        this.cards.setVgap(20);
        this.cards.setHgap(20);

        // configure buttons
        this.rules.getConfirmButton().addActionListener(this::nextAction);
        this.rules.getCancelButton().addActionListener(this::cancelAction);
        this.results.getConfirmButton().addActionListener(this::playAgainAction);
        this.results.getCancelButton().addActionListener(this::cancelAction);
        this.betView.getConfirmButton().addActionListener(this::betAction);
        this.playView.getTossButton().addActionListener(this::tossAction);
        this.playView.getFinishButton().addActionListener(this::finishAction);
    }

    private void addComponents() {
        this.add(this.menu, BorderLayout.PAGE_START);

        // Add all elements to the carded view
        this.card.add(this.rules, "2");
        this.card.add(this.betView, "3");
        this.card.add(this.playView, "4");
        this.card.add(this.results, "5");

        // Add the carded view to the center
        this.add(this.card);
    }

    private void nextAction(ActionEvent e) {
        this.cards.next(this.card);
    }

    private void cancelAction(ActionEvent e) {
        this.cards.show(this.card, "1");
    }

    private void selectGameAction(ActionEvent e) {
        int id = new Integer(e.getActionCommand());
        this.getObservers().forEach(o -> o.selectGame(id));
    }

    private void betAction(ActionEvent e) {
        try {
            double amount = new Double(this.betView.getFormattedField().getText());

            if (amount > 0.0) {
                this.getObservers().forEach(o -> o.bet(amount));

                this.playView.getTossButton().setVisible(true);
                this.playView.getFinishButton().setVisible(false);

                // Disable all buttons
                this.playView.disableBoxButtons();
            } else {
                this.betUnsuccessful();
            }
        } catch(Exception exception) {
            // This was probably a number format exception
            this.betUnsuccessful();
        }

    }

    private void finishAction(ActionEvent actionEvent) {
        this.cards.show(this.card, "5");
    }

    private void tossAction(ActionEvent e) {
        this.getObservers().forEach(GameListener::toss);

        // Enable all buttons once the tossing has started
        this.playView.enableBoxButtons();
    }

    private void playAgainAction(ActionEvent e) {
        // Update all observers
        this.getObservers().forEach(GameListener::playAgain);
    }

    private void toggleDieAction(ActionEvent e) {
        this.getObservers().forEach(o -> o.toggleSaveDie(new Integer(e.getActionCommand())));
    }

    @Override
    public void displayRules(String rules) {
        // Set the rules and show the next card
        this.rules.getTextTextArea().setText(rules);
        this.cards.show(this.card, "2");
    }

    @Override
    public void betSuccessful() {
        this.cards.show(this.card, "4");
    }

    @Override
    public void betUnsuccessful() {
        JOptionPane.showMessageDialog(null, "Could not perform bet", "Wrong bet", JOptionPane.WARNING_MESSAGE);
        this.cards.show(this.card, "1");
    }

    @Override
    public void updateDie(int index, int face) {
        this.playView.updateBox(index, face);
        this.playView.repaint();
    }

    @Override
    public void updateNumberOfThrows(int numberOfThrows) {
        this.playView.updateProgress(numberOfThrows);
    }

    @Override
    public void updateNumberOfDice(int numberOfDice) {
        this.playView.addBoxes(numberOfDice, this::toggleDieAction);
    }

    @Override
    public void updateBalance(double amount) {
        this.menu.setBalanceLabelValue(amount);
    }

    @Override
    public void displayResult(String result) {
        // Set the game result
        this.results.getTextTextArea().setText(result);

        // Toggle the buttons (show 'Finish' and hide 'Toss')
        this.playView.getTossButton().setVisible(false);
        this.playView.getFinishButton().setVisible(true);
    }

    @Override
    public void showAllGames(Collection<GameData> games) {
        this.selectableGamesView = new JPanel();
        this.selectableGamesView.setLayout(new GridLayout(0, 5));

        for (GameData game : games) {
            Box box = new Box("shared/resources/1.png", game.getGameName());
            box.getButton().setActionCommand(String.valueOf(game.getId()));
            box.getButton().addActionListener(this::selectGameAction);

            this.selectableGamesView.add(box);
        }

        this.card.add(this.selectableGamesView, "1");
        this.cards.show(this.card, "1");
    }
}
