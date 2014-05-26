package shared.game;

import utilities.sql.annotations.Ignore;
import utilities.sql.annotations.PrimaryKey;

import java.util.ArrayList;

/**
 * @author  Oliver Nilsson
 * @since   13/05/2014
 */
public class Combination {
    @PrimaryKey("ID") private int id;
    private int quantity;
    @Ignore private ArrayList<String> faces;
    private String name;

    /**
     * Creates an empty Combination object
     */
    public Combination() {
        this.quantity = 0;
        this.faces = new ArrayList<>();
        name = "No name";
    }

    public Combination(String name){
        this.quantity = 0;
        this.faces = new ArrayList<>();
        this.name = name;
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
            for (int j = 0; j < dice.length; j++){
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

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public int getNumberOfFaces(){
        return faces.size();
    }

    public ArrayList<String> getFaces()
    {
        return faces;
    }

    public void setFaces(ArrayList<String> faces)
    {
        this.faces = faces;
    }

    public String getFace(int index){
        return faces.get(index);
    }

    public int findFace(String face){
        int index = -1;
        for (int i = 0; i < faces.size() && index == -1; i++){
            if (getFace(i).equals(face))
                index = i;
        }
        return index;
    }

    @Override
    public String toString() {
        return String.format("id: %d name: %s, faces: %s, quantity: %d\n", this.id, this.name, this.faces, this.quantity);
    }
}
