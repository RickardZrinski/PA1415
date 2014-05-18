package shared.game;

import utilities.sql.annotations.Ignore;
import utilities.sql.annotations.PrimaryKey;

import java.util.ArrayList;

/**
 * Created by Oliver on 2014-05-13.
 */
public class GameData {
    @PrimaryKey("ID") private int id;
    private String gameName;
    private int numberOfThrows;
    private int numberOfDice;
    @Ignore private ArrayList<WinningCondition> winningConditions;
    private String rules;

    /**
     * Creates new empty GameData
     */
    public GameData() {
        this.gameName = "Unknown";
        this.numberOfDice = 0;
        this.numberOfThrows = 0;
        this.winningConditions = new ArrayList<>();
        this.rules = "No rules available";
    }

    /**
     * Creates new GameData
     * @param gameName  name of the shared.game
     * @param numberOfDice  number of dice in shared.game
     * @param numberOfThrows    number of throws done by player
     * @param rules rules of the shared.game
     */
    public GameData(String gameName, int numberOfDice, int numberOfThrows, String rules) {
        this.gameName = gameName;
        this.numberOfThrows = numberOfThrows;
        this.numberOfDice = numberOfDice;
        this.winningConditions = new ArrayList<>();
        this.rules = rules;
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
     * @param winningCondition    the new winning condition
     */
    public void addWinningCondition(WinningCondition winningCondition){
        if (this.winningConditions.size() == 0)
            winningConditions.add(winningCondition);
        else {
            boolean added = false;
            int nrOfConditions = winningConditions.size();
            for (int i = 0; i < nrOfConditions; i++) {
                if (winningCondition.getReward() > this.winningConditions.get(i).getReward()) {
                    this.winningConditions.add(i, winningCondition);
                    added = true;
                }
            }
            //Add last
            if (!added)
                winningConditions.add(winningCondition);
        }
    }

    /**
     * Removes winningCondition at specified index
     * @param index index of winningCondition
     */
    public void removeWinningCondition(int index) {
        this.winningConditions.remove(index);
    }

    /**
     * Compares existing WinningConditions with the chosen WinningConds parameters,
     * and removes the existing ones that are not chosen
     * @param winningConds The conditions to compare with
     */
    public void setWinningCondition(ArrayList<WinningCondition> winningConds)
    {
        //clears existing winning condition array
        winningConditions.clear();

        //adds all winningConds array into existing WinningCondition array
        for(int i=0; i< winningConds.size(); i++)
        {
            winningConditions.add(winningConds.get(i) );
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

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

    public int getNumberOfWinningConditions(){
        return winningConditions.size();
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

    /**
     * Retrieves index of a winningCondition
     * @param ID    ID of the winningCondition
     * @return  If exists: index; if not: -1
     */
    public int findWinningCondition(int ID){
        int index = -1;
        for (int i = 0; i < getNumberOfWinningConditions() && index == -1; i++){
            if (winningConditions.get(i).getId() == ID)
                index = i;
        }
        return index;
    }

    /**
     * Finds index of winningCondition which contains a combination with the spec. ID
     * @param ID    the combination's ID
     * @return  If exists: index of the winningCondition; If not: -1
     */
    public int findCombination(int ID) {
        int index = -1;
        for (int i = 0; i < getNumberOfWinningConditions() && index == -1; i++){
            if (winningConditions.get(i).findCombination(ID) != -1)
                index = i;
        }
        return  index;
    }
    @Override
    public String toString() {
        return String.format("id: %s, gameName: %s, numberOfThrows: %d, numberOfDice: %d, Rules: %s\nWinningConditions:\n%s\nDice: %s", this.id, this.gameName, this.numberOfThrows, this.numberOfDice, this.rules, this.winningConditions);
    }
}
