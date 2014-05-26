package casino.controllers;

import casino.events.GameListener;
import casino.views.GameView;
import shared.dao.GameDataDao;
import shared.game.GameSession;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class GameController implements GameListener {
    private GameView view;
    private GameSession gameSession;

    public GameController() {
        this.view = new GameView();
        this.view.subscribe(this);
        this.gameSession = new GameSession();
    }

    @Override
    public void selectGame(int id) {
        gameSession.selectGame(id);
        System.out.println(String.format("GameController: Selected game index: %d", id));
    }

    @Override
    public void bet(double amount) {
        gameSession.bet(amount);
        System.out.println(String.format("GameController: Bet %f", amount));
    }

    @Override
    public void toss() {
        gameSession.toss();
        System.out.println("GameController: Toss performed");
    }

    @Override
    public void saveDie(int index) {
        gameSession.saveDie(index);
        System.out.println(String.format("GameController: Save die index: %d", index));
    }

    @Override
    public void playAgain() {
        gameSession.playAgain();
        System.out.println("GameController: playAgain");
    }
}
