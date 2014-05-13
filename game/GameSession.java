package game;

import users.Player;

import java.util.ArrayList;

/**
 * Created by Oliver on 2014-05-13.
 */
public class GameSession {
    private int bet;
    private boolean active;
    private ArrayList<Die> diceHand;
    private Player player;
    private GameData gameData;

    /**
     * Creates a new empty GameSession
     */
    public GameSession() {
        bet = 0;
        active = false;
        diceHand = new ArrayList<Die>();
        player = null;
        gameData = null;
    }

    /**
     * Creates a new GameSession
     * @param player    the player
     * @param gameData  object containing game data
     */
    public GameSession(Player player, GameData gameData) {
        bet = 0;
        active = false;
        diceHand = new ArrayList<Die>();
        this.player = player;
        this.gameData = gameData;
    }

    /**
     * Tosses all dice in game
     */
    public void toss(){
        gameData.toss();
    }

    public void start(){
        active = true;

    }




}
