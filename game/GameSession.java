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

    /**
     * Sets the game to active
     */
    public void start(){
        active = true;
    }

    /**
     * Called when the game ends
     * @return the fulfilled winningCondition, if none: default WinningCondition
     */
    public WinningCondition end(){
        active = false;
        //Save all remaining dice
        for (int i = 0; i < gameData.getNumberOfDice(); i++){
            saveDie(i);
        }
        return calculateReward();
    }

    /**
     * Resets the game to its original state
     */
    public void playAgain(){
        for (int i = 0; i < diceHand.size(); i++)
            diceHand.remove(i);
        bet = 0;

        //RESET GAMEDATA FIX
    }
    /**
     * Moves die in gameData to diceHand
     * @param index index of die in gameData
     */
    public void saveDie(int index){
        diceHand.add(gameData.getDie(index));
        gameData.removeDie(index);
    }

    /**
     * Moves die from diceHand to gameData
     * @param index index of the die in diceHand
     */
    public void unsaveDie(int index){
        gameData.addDie(diceHand.remove(index));
    }


    private WinningCondition calculateReward(){
        return gameData.checkWinningConditions((Die[]) diceHand.toArray());
    }
    /**
     * Retrieves a die from gameData
     * @param index index of the die in gameData
     * @return  the die
     */
    public Die getGameDie(int index){
        return gameData.getDie(index);
    }

    /**
     * Retrieves a die from diceHand
     * @param index index of the die in diceHand
     * @return  the die
     */
    public Die getSavedDie(int index){
        return diceHand.get(index);
    }

    /**
     * Retrieves number of dice currently in game (excluding dice in diceHand)
     * @return number of dice.
     */
    public int getNumberOfGameDice(){
        return gameData.getNumberOfDice();
    }

    /**
     * Retrieves number of dice in diceHand
     * @return number of dice
     */
    public int getNumberOfHandDice(){
        return diceHand.size();
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

    public Player getPlayer() {
        return player;
    }
}
