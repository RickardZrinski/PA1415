package shared.game;

import utilities.sql.annotations.Ignore;
import utilities.sql.annotations.PrimaryKey;

import java.util.ArrayList;

/**
 * @author  Oliver Nilsson
 * @since   13/05/2014
 */
public class WinningCondition {
    @PrimaryKey("ID") private int id;
    @Ignore private ArrayList<Combination> combinations;
    private float reward;
    private String name;

    /**
     * Creates a new empty WinningCondition
     */
    public WinningCondition(){
        combinations = new ArrayList<Combination>();
        reward = 0f;
        name = "No reward";
    }

    public WinningCondition(String name){
        combinations = new ArrayList<Combination>();
        reward = 0f;
        this.name = name;
    }

    /**
     * Adds a new combination, list is sorted after combination.quantity
     * @param combination   the new combination
     */
    public void addCombination(Combination combination){
        if (combinations.size() == 0)
            combinations.add(combination);
        else {
            boolean added = false;
            int nrOfCombinations = combinations.size();
            for (int i = 0; i < nrOfCombinations && !added; i++) {
                if (combination.getQuantity() > combinations.get(i).getQuantity()) {
                    combinations.add(i, combination);
                    added = true;
                }
            }
            //Add last
            if (!added)
                combinations.add(combination);
        }
    }

    public Combination removeCombination(int index){
        return combinations.remove(index);
    }

    /**
     * Retrieves number of combinations.
     * @return number of combinations.
     */
    public int getNumberOfCombinations(){return combinations.size();}

    /**
     * Retrieves combination at specified index.
     * @param index index of combination
     * @return the combination
     */
    public Combination getCombination(int index){return combinations.get(index);}

    /**
     * Retrieves the reward
     * @return the reward
     */
    public float getReward(){return reward;}

    /**
     * Checks if all combinations are fulfilled.
     * @param dice  The dice that will be checked.
     * @return <code>true</code> if fulfilled; <code>false</code> otherwise
     */
    public boolean isFulfilled(Die[] dice){
        boolean fulfilled = true;
        String usedFace = null;
        for (int i = 0; i < combinations.size() && fulfilled; i++){
            usedFace = combinations.get(i).isFulfilled(dice);
            if (usedFace == null)
                fulfilled = false;
            else
                removeUsedFace(i, usedFace);
        }

        return fulfilled;
    }

    /**
     * Removes a used face from all combinations which are yet to be checked
     * @param startingIndex index of the first combination which will have the face removed
     * @param usedFace  the face that will be removed from the combinations
     */
    private void removeUsedFace(int startingIndex, String usedFace){
        for (int i = startingIndex; i < combinations.size(); i++)
            combinations.get(i).removeFace(usedFace);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReward(float reward){
        this.reward = reward;
    }

    public int getId() {
        return this.id;
    }

    /**
     * Finds index of combination
     * @param ID ID of the combination
     * @return  If exists: index; If not: -1
     */
    public int findCombination(int ID){
        int index = -1;
        for (int i = 0; i < getNumberOfCombinations() && index == -1; i++){
            if (combinations.get(i).getId() == ID)
                index = i;
        }
        return index;
    }

    @Override
    public String toString() {
        return String.format("id: %d, reward: %f, name: %s\n\tcombinations: %s", this.id, this.reward, this.name, this.combinations);
    }
}
