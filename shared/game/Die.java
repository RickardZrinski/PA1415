package shared.game;

import java.util.Random;
/**
 * Created by Oliver on 2014-05-13.
 */
public class Die {
    private int face;
    private boolean saved;

    /**
     * Creates a new Die with face value one.
     */
    public Die(){this.face = 1;}

    /**
     * Simulates a toss by assigning die.face a random value.
     * @return the new face.
     */
    public int toss(){
        Random random = new Random();
        if (saved == false)
            face = random.nextInt(6) +1;
        return face;
    }

    /**
     * Retrieves the face of the die.
     * @return the face
     */
    public int getFace() {return face;}

    /**
     * Resets the face value to 1
     */
    public void reset(){
        this.face = 1;
        saved = false;
    }

    public boolean isSaved(){
        return saved;
    }

    public void setSaved(boolean save){
        saved = save;
    }
}
