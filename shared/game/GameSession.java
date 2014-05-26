package shared.game;

import casino.events.GameResponse;
import shared.Model;
import shared.dao.GameDataDao;
import shared.users.User;

import java.util.ArrayList;

/**
 * Created by Oliver on 2014-05-13.
 */
public class GameSession extends Model<GameResponse>{
    private double bet;
    private boolean active;
    private User user;
    private GameData gameData;
    private ArrayList<Die> dice;
    private GameDataDao dao;
    private int numberOfThrows;

    /**
     * Creates a new empty GameSession
     */
    public GameSession() {
        this.bet = 0;
        this.active = false;
        this.dice = new ArrayList<Die>();
        this.user = null;
        this.gameData = null;
        dao = new GameDataDao();
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
        this.dao = new GameDataDao();
    }

    /**
     * Tosses all dice in shared.game
     */
    public void toss(){
        GameResponse[] observers = (GameResponse[])this.getObservers().toArray();

        for (int i = 0; i < getNumberOfDice(); i++){
            if (!dice.get(i).isSaved()) {
                dice.get(i).toss();
                for (int j = 0; j < this.getObservers().size(); j++){
                    observers[j].updateDie(i, dice.get(i));
                }
            }

        }
        this.numberOfThrows--;
    }

    /**
     * Sets bet
     * @param bet player's bet
     */
    public void bet(double bet){
        this.bet = bet;
        //@TODO implement transaction

        //If successful
        this.getObservers().forEach(GameResponse::betSuccessful);
        this.getObservers().forEach(o -> o.updateNumberOfThrows(numberOfThrows));
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
        WinningCondition reward = calculateReward();
        String rewardString = "You have won %f SEK by betting %f SEK";
        double winnings = bet * reward.getReward();
        this.getObservers().forEach(o -> o.displayResult(String.format(rewardString, winnings, bet)));
        return reward;
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
    public double getBet() {
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

    public void selectGame(int id){
        gameData = dao.get(id);
        this.numberOfThrows = gameData.getNumberOfThrows();
        this.getObservers().forEach(o -> o.displayRules(gameData.getRules()));
    }


}
