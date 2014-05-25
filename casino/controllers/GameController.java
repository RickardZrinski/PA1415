package casino.controllers;

import casino.events.GameListener;
import casino.views.GameView;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class GameController implements GameListener {
    private GameView view;

    public GameController() {
        this.view = new GameView();
        this.view.subscribe(this);
    }

    @Override
    public void selectGame(int index) {
        System.out.println(String.format("GameController: Selected game index: %d", index));
    }

    @Override
    public void bet(double amount) {
        System.out.println(String.format("GameController: Bet %f", amount));
    }

    @Override
    public void toss() {
        System.out.println("GameController: Toss performed");
    }

    @Override
    public void saveDie(int index) {
        System.out.println(String.format("GameController: Save die index: %d", index));
    }

    @Override
    public void end() {
        System.out.println("GameController: end");
    }

    @Override
    public void playAgain() {
        System.out.println("GameController: playAgain");
    }
}
