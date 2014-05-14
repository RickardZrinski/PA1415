package game;

import java.util.ArrayList;

/**
 * Created by Oliver on 2014-05-13.
 */
public class GameData {
    private int id;
    private String gameName;
    private int numberOfThrows;
    private int numberOfDice;
    private ArrayList<WinningCondition> winningConditions;
    private ArrayList<Die> dice;
    private String rules;

    /**
     * Creates new empty GameData
     */
    public GameData(){
        this.id = -1;
        this.gameName = "Unknown";
        this.numberOfDice = 0;
        this.numberOfThrows = 0;
        winningConditions = new ArrayList<WinningCondition>();
        dice = new ArrayList<Die>();
        rules = "No rules available";
    }

    /**
     * Creates new GameData
     * @param id    identification
     * @param gameName  name of the game
     * @param numberOfDice  number of dice in game
     * @param numberOfThrows    number of throws done by player
     * @param rules rules of the game
     */
    public GameData(int id, String gameName, int numberOfDice, int numberOfThrows, String rules){
        this.id = id;
        this.gameName = gameName;
        this.numberOfThrows = numberOfThrows;
        this.numberOfDice = numberOfDice;
        this.rules = rules;
        winningConditions = new ArrayList<WinningCondition>();
        dice = new ArrayList<Die>();
    }

    /**
     * Tosses all the dice
     */
    public void toss(){
        for (int i = 0; i < dice.size(); i++)
            dice.get(i).toss();
    }

    /**
     * Removes a die from the game
     * @param index index of the die
     * @return the removed die
     */
    public Die removeDie(int index){
        return dice.remove(index);
    }

    /**
     * Adds a die to the game
     * @param die   the die
     */
    public void addDie(Die die){
        dice.add(die);
    }

    /**
     * Checks for fulfilled winning conditions
     * @param dice  the dice that will be checked.
     * @return  The fulfilled winning condition with the highest reward
     */
    public WinningCondition checkWinningConditions(Die[] dice){
        int conditionIndex = -1;
        boolean fulfilled = false;
        for (int i = 0; i < winningConditions.size() && conditionIndex == -1; i++){
            fulfilled = winningConditions.get(i).isFulfilled(dice);
            if (fulfilled)
                conditionIndex = i;
        }
        if (conditionIndex != -1)
            return winningConditions.get(conditionIndex);
        else
            return new WinningCondition();
    }

    /**
     * Adds a new winning condition, list is sorted by highest-lowest reward
     * @param wCondition    the new winning condition
     */
    public void addWinningCondition(WinningCondition wCondition){
        for (int i = 0; i < winningConditions.size(); i++){
            if (wCondition.getReward() > winningConditions.get(i).getReward()){
                winningConditions.add(i, wCondition);
            }
        }
    }

    /**
     * Removes winningCondition at specified index
     * @param index index of winningCondition
     */
    public void removeWinningCondition(int index) {
        winningConditions.remove(index);
    }

    /**
     * Compares existing WinningConditions with the chosen WinningConds parameters,
     * and removes the existing ones that are not chosen
     * @param winningConds The conditions to compare with
     */
    public void setGameWinningCondition(ArrayList<WinningCondition> winningConds) {
        for (int i=0; i< this.winningConditions.size(); i++)
        {
            if (!this.winningConditions.get(i).equals(winningConds.get(i)))
            {
                winningConditions.remove(i);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getNumberOfThrows() {
        return numberOfThrows;
    }

    public void setNumberOfThrows(int numberOfThrows) {
        this.numberOfThrows = numberOfThrows;
    }

    public int getNumberOfDice() {
        return numberOfDice;
    }

    public void setNumberOfDice(int numberOfDice) {
        this.numberOfDice = numberOfDice;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public WinningCondition getWinningCondition(int index){
        return winningConditions.get(index);
    }

    public Die getDie(int index){
        return  dice.get(index);
    }
}
