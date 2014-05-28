package casino.models;

import casino.events.GameResponse;
import shared.AuthenticationSession;
import shared.Model;
import shared.dao.DAOFactory;
import shared.dao.GameDataDao;
import shared.game.Die;
import shared.game.GameData;
import shared.game.WinningCondition;
import shared.users.User;

import java.util.ArrayList;

/**
 * @author  Oliver Nilsson
 * @since   13/05/2014
 */
public class GameSession extends Model<GameResponse> {
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
        this.dice = new ArrayList<>();
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
        this.dice = new ArrayList<>();
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
        boolean tossed = false;
        for (int i = 0; i < getNumberOfDice(); i++){
            if (!dice.get(i).isSaved()) {
                dice.get(i).toss();
                int index = i;
                tossed = true;
                this.getObservers().forEach(o -> o.updateDie(index, dice.get(index).getFace()));
            }
        }

        if (tossed)
            this.numberOfThrows--;

        if (numberOfThrows == 0 || !tossed)
            this.end();

        // Notify our observers
        this.getObservers().forEach(o -> o.updateNumberOfThrows(this.numberOfThrows));
    }

    /**
     * Sets a bet and checks its validity. Responds to all listeners
     * by invoking either betSuccessful or betUnsuccessful
     *
     * @param bet player's bet
     */
    public void bet(double bet) {
        this.bet = 0;

        try {
            User user = AuthenticationSession.getInstance().getUser();

            if (user.getAccount().isWithdrawable(bet)) {
                // Set the proper bet
                this.bet = bet;

                user.getAccount().withdraw(bet);        // Withdraw money from the account
                DAOFactory.getUserDao().update(user);   // Store in the database

                this.getObservers().forEach(GameResponse::betSuccessful);
                this.getObservers().forEach(o -> o.updateBalance(user.getAccount().getBalance()));  // Update the balance
                this.getObservers().forEach(o -> o.updateNumberOfThrows(numberOfThrows));
                this.getObservers().forEach(o -> o.updateNumberOfDice(getNumberOfDice()));
            } else {
                // The bet could not be transferred.
                this.getObservers().forEach(GameResponse::betUnsuccessful);
            }
        } catch(Exception e) {
            this.getObservers().forEach(GameResponse::betUnsuccessful);
        }
    }

    public void trial(){
        try {
            user = AuthenticationSession.getInstance().getUser();
            if (user.getNumberOfTrials() > 0 && user.getRole().getName().equals("Trial Player")) {
                int trials = user.getNumberOfTrials() - 1;
                user.setNumberOfTrials(trials);

                //Promote to player if all trails have been spent.
                if (user.getNumberOfTrials() == 0)
                    user.setRole(DAOFactory.getUserDao().getRole("Player"));

                DAOFactory.getUserDao().update(user);
                this.getObservers().forEach(GameResponse::trialSuccessful);
                this.getObservers().forEach(o -> o.updateNumberOfThrows(numberOfThrows));
                this.getObservers().forEach(o -> o.updateNumberOfDice(getNumberOfDice()));
                this.getObservers().forEach(o -> o.updateNumberOfTrials(trials));
            }
            else
                this.getObservers().forEach(GameResponse::trialUnsuccessful);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    private WinningCondition end() {
        active = false;
        WinningCondition reward = calculateReward();
        String rewardString = "You have achieved %s!\nYou have won %d SEK by betting %d SEK";

        double winnings = bet * reward.getReward();
        this.getObservers().forEach(o -> o.displayResult(String.format(rewardString, reward.getName(), (int)winnings, (int)bet)));

        // Deposit winnings to the user account
        try {
            User user = AuthenticationSession.getInstance().getUser();
            user.getAccount().deposit(winnings);

            // Update the user
            DAOFactory.getUserDao().update(user);
            this.getObservers().forEach(o -> o.updateBalance(user.getAccount().getBalance()));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return reward;
    }

    /**
     * Resets the shared.game to its original state
     */
    public void playAgain(){
        bet = 0;

        resetDice();
        resetData();
        active = true;
        this.getObservers().forEach(o -> o.displayRules(gameData.getRules()));
    }

    /**
     * Toggles saved in die
     * @param index index of die
     */
    public void toggleSaveDie(int index){
        dice.get(index).toggleSaved();
    }

    private WinningCondition calculateReward(){
        Die[] dice = new Die[this.dice.size()];
        dice = this.dice.toArray(dice);
        return gameData.checkWinningConditions(dice);
    }
    /**
     * Retrieves a die face from gameData
     * @param   index   index of the die in gameData
     * @return          the die
     */
    public Die getDie(int index){
        return dice.get(index);
    }

    /**
     * Retrieves number of dice currently in shared.game (excluding dice in diceHand)
     * @return  number of dice.
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

        if (gameData != null) {
            numberOfThrows = gameData.getNumberOfThrows();
            return true;
        }
        else
            return  false;
    }

    /**
     * Resets all dice to their original state
     */
    private void resetDice(){
        for (Die die : dice)
            die.reset();
    }

    /**
     * Selects a GameData
     * @param   id  id of GameData-object
     */
    public void selectGame(int id){
        this.gameData = dao.get(id);
        this.numberOfThrows = gameData.getNumberOfThrows();

        // Empty the dice array before adding new dice
        this.dice.clear();

        for (int i = 0; i < gameData.getNumberOfDice(); i++)
            dice.add(new Die());

        this.getObservers().forEach(o -> o.displayRules(gameData.getRules()));
    }

    /**
     * Loads all games from the database and shows it to the view
     */
    public void loadAll() {
        this.getObservers().forEach(o -> o.showAllGames(DAOFactory.getGameDataDao().getCollectionNames()));
    }
}
