package shared.game;

import shared.dao.GameDataDao;
import shared.users.User;

import java.util.ArrayList;

/**
 * Created by Oliver on 2014-05-13.
 */
public class GameSession {
    private int bet;
    private boolean active;
    private User user;
    private GameData gameData;
    private ArrayList<Die> dice;

    /**
     * Creates a new empty GameSession
     */
    public GameSession() {
        this.bet = 0;
        this.active = false;
        this.dice = new ArrayList<Die>();
        this.user = null;
        this.gameData = null;
    }

    /**
     * Creates a new GameSession
     * @param user      the player
     * @param gameData  object containing shared.game data
     */
    public GameSession(User user, GameData gameData) {
        this.bet = 0;
        this.active = false;
        this.dice = new ArrayList<Die>();
        for (int i = 0; i < gameData.getNumberOfDice(); i++)
           dice.add(new Die());
        this.user = user;
        this.gameData = gameData;
    }

    /**
     * Tosses all dice in shared.game
     */
    public void toss(){
        for (Die die: dice){
            die.toss();
        }
    }

    /**
     * Sets bet
     * @param bet player's bet
     */
    public void bet(int bet){
        this.bet = bet;
    }

    /**
     * Sets the shared.game to active
     */
    public void start(){
        active = true;
    }

    /**
     * Called when the shared.game ends
     * @return the fulfilled winningCondition, if none: default WinningCondition
     */
    public WinningCondition end(){
        active = false;
        return calculateReward();
    }

    /**
     * Resets the shared.game to its original state
     */
    public boolean playAgain(){
        bet = 0;

        resetDice();
        return resetData();
    }
    /**
     * Moves die in gameData to diceHand
     * @param index index of die in gameData
     */
    public void saveDie(int index){
        dice.get(index).setSaved(true);
    }

    /**
     * Moves die from diceHand to gameData
     * @param index index of the die in diceHand
     */
    public void unsaveDie(int index){
        dice.get(index).setSaved(false);
    }


    private WinningCondition calculateReward(){
        Die[] dice = new Die[this.dice.size()];
        dice = this.dice.toArray(dice);
        return gameData.checkWinningConditions(dice);
    }
    /**
     * Retrieves a die face from gameData
     * @param index index of the die in gameData
     * @return  the die
     */
    public Die getGameDie(int index){
        return dice.get(index);
    }

    /**
     * Retrieves number of dice currently in shared.game (excluding dice in diceHand)
     * @return number of dice.
     */
    public int getNumberOfDice(){
        return dice.size();
    }


    /**
     * Retrieves number Of Throws
     * @return  number of throws
     */
    public int getNumberOfThrows(){
        return gameData.getNumberOfThrows();
    }

    /**
     * Retrieves bet-value
     * @return  the bet-value
     */
    public int getBet() {
        return bet;
    }

    public boolean isActive() {
        return active;
    }

    public User getUser() {
        return user;
    }

    /**
     * Resets gameData by retrieving it from the database
     * @return the success of the operation.
     */
    private boolean resetData(){
        GameDataDao dao = new GameDataDao();
        gameData = dao.get(gameData.getId());
        if (gameData != null)
            return  true;
        else
            return  false;
    }

    /**
     * Moves all dice from diceHand and resets all dice
     */
    private void resetDice(){
        for (Die die : dice)
            die.reset();
    }
}
