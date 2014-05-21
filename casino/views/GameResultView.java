package casino.views;

import casino.AbstractView;
import casino.views.forms.GameRulesResultForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author  John Mogensen
 * @since   21/05/2014
 */
public class GameResultView  extends AbstractView implements ActionListener {
    private MenuView menuView = new MenuView();
    private GameRulesResultForm gameResultForm;


    public GameResultView() {
        this.gameResultForm = new GameRulesResultForm("Results", "RESULT INFO FROM GAME\n\n\nWould you like to play again?", "Play Again");

        this.configure();
        this.addComponents();
    }

    private void configure() {
        this.setLayout(new BorderLayout());
        this.gameResultForm.setActionListener(this);
    }

    private void addComponents() {
        this.add(this.menuView, BorderLayout.PAGE_START);
        this.add(this.gameResultForm, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("cancel")){
            System.out.println("User canceled.");
        }
        else
        {
            System.out.println("Play Again");
        }
    }
}
