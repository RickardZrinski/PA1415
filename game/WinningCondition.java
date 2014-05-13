package game;

import java.util.ArrayList;

/**
 * Created by Oliver on 2014-05-13.
 */
public class WinningCondition {
    private ArrayList<Combination> combinations;
    private float reward;

    /**
     * Creates a new empty WinningCondition
     */
    public WinningCondition(){
        combinations = new ArrayList<Combination>();
        reward = 1f;
    }

    /**
     * Adds a new combination, list is sorted after combination.quantity
     * @param combination   the new combination
     */
    public void addCombination(Combination combination){
        for (int i = 0; i < combinations.size(); i++){
            if (combination.getQuantity() > combinations.get(i).getQuantity()){
                combinations.add(i, combination);
            }
        }
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

}