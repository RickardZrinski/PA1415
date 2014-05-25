package casino.views;

import casino.events.GameListener;
import shared.View;
import casino.views.components.Box;
import casino.views.forms.SimpleForm;
import utilities.ComponentUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class GameView extends View<GameListener> {
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
    }

    private void nextAction(ActionEvent e) {
        this.cards.next(this.card);
    }
}
