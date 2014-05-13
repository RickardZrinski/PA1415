package game;

import java.util.ArrayList;

/**
 * Created by Oliver on 2014-05-13.
 */
public class Combination {
    private int quantity;
    private ArrayList<String> faces;

    /**
     * Creates an empty Combination object
     */
    public Combination() {
        this.quantity = 0;
        this.faces = new ArrayList<String>();

    }

    /**
     * Creates a new Combination object.
     * @param   quantity    Number of identical faces needed
     * @param   faces       List containing allowed faces
     */
    public Combination(int quantity, String[] faces) {
        this.quantity = quantity;
        this.faces = new ArrayList<String>();
    }

    /**
     * Adds an allowed face to faces
     * @param   face    The new face that will be added to the faces-list.
     */
    public void addFace(String face) {
        faces.add(face);
    }

    /**
     * Removes a face from the faces-list
     * @param face  The face that will be removed from the faces-list.
     * @return  The success of the operation.
     */
    public boolean removeFace(String face){
        return faces.remove(face);
    }

    /**
     * Decides if the combination is fulfilled
     * @param dice  The dice that will be checked.
     * @return  the used face if fulfilled; null otherwise
     */
    public String isFulfilled(Die[] dice) {
        int count = 0;
        String usedFace = null;
        /* Count number of identical faces in dice which also
           exist in faces.*/
        for (int i = 0; i < faces.size() && usedFace == null; i++){
            count = 0;
            for (int j = 1; j < dice.length; j++){
                if (faces.get(i).equals(String.valueOf(dice[j].getFace()))){
                    count++;
                }
            }
            if (count >= quantity) {
                usedFace = faces.get(i);
            }
        }
        return usedFace;
    }

    public int getQuantity(){
        return quantity;
    }
}
