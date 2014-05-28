package shared.game;

import java.util.Random;

/**
 * @author  Oliver Nilsson
 * @since   13/05/2014
 */
public class Die {
    private int face;
    private boolean saved;

    /**
     * Creates a new Die with face value one.
     */
    public Die(){
        this.face = 1;
        this.saved = false;
    }

    /**
     * Simulates a toss by assigning die.face a random value.
     * @return the new face.
     */
    public int toss(){
        Random random = new Random();
        if (!saved)
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

    public void toggleSaved(){
        if (saved)
            saved = false;
        else
            saved = true;
    }
}
