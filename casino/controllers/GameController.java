package casino.controllers;

import casino.MainFrame;
import casino.views.GameView;
import shared.game.GameSession;

/**
 * @author  Dino Opijac
 * @since   21/05/2014
 */
public class GameController {
    private GameSession model;
    private GameView view;

    public GameController() {
        this.view = new GameView();
        this.view.subscribe(this);

        MainFrame.getInstance().add(this.view);
    }
}
