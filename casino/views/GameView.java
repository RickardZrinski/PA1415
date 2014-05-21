package casino.views;

import casino.AbstractView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class GameView extends AbstractView implements ActionListener {

    public GameView() {
        this.configure();
        this.addComponents();
    }

    private void configure() {

    }

    private void addComponents() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
    }
}
