package shared.dao;

import com.mysql.jdbc.Connection;
import shared.game.Combination;
import shared.game.GameData;
import shared.game.WinningCondition;
import utilities.sql.Connector;
import utilities.sql.Dapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author  Oliver Nilsson
 * @since   16/05/2014
 */
public class GameDataDao implements IDao<GameData> {
    Dapper<GameData> gameData;
    Dapper<WinningCondition> winningConditionData;
    Dapper<Combination> combinationData;
    Connection connection;

    public GameDataDao(){
        this.gameData = new Dapper<>(GameData.class);
        this.winningConditionData = new Dapper<>(WinningCondition.class);
        this.combinationData = new Dapper<>(Combination.class);
    }

    /**
     * Retrieves a collection of GameData objects
     *
     * @return collection of GameData objects
     */
    public Collection<GameData> getCollection() {
        Collection<GameData> data = new ArrayList<>();

        for(GameData game: this.gameData.getCollection())
            data.add(this.get(game.getId()));

        return data;
    }

    public Collection<GameData> getCollectionNames(){
        return this.gameData.getCollection();
    }

    /**
     * Retrieves gameData from the database
     * @param key   The gameData's ID
     * @return If exists: The gameData-object; If not: null
     */
    @Override
    public GameData get(Object key){
        Integer primaryKey = (Integer)key;
        if (gameData.count("ID", String.valueOf(primaryKey)) > 0) {
            GameData game = gameData.getUsingPrimaryKey(primaryKey);
            ArrayList<WinningCondition> winningConditions = (ArrayList<WinningCondition>) winningConditionData.getCollectionUsingForeignKey("fkId",
                    game.getId());

            for (int i = 0; i < winningConditions.size(); i++) {
                game.addWinningCondition(winningConditions.get(i));
                ArrayList<Combination> combinations = (ArrayList<Combination>) combinationData.getCollectionUsingForeignKey("fkId",
                        winningConditions.get(i).getId());
                for (int j = 0; j < combinations.size(); j++) {
                    winningConditions.get(i).addCombination(combinations.get(j));

                    final String table = "SELECT Face FROM Face WHERE c_id = ?;";
                    ResultSet face = null;
                    try {
                        this.connection = Connector.getInstance();
                        PreparedStatement statement = this.connection.prepareStatement(String.format(table));
                        statement.setInt(1, combinations.get(j).getId());
                        face = statement.executeQuery();
                        while (face.next())
                            combinations.get(j).addFace(face.getString(1));
                    } catch (SQLException ex2) {
                        ex2.printStackTrace();
                    }
                }

            }
            return game;
        }
        return null;
    }

    /**
     * Inserts a GameData-object into the database.
     * @param gameData  the GameData-object to be inserted
     * @return  the success of the operation
     */
    @Override
    public boolean insert(GameData gameData) {
        boolean success = true;
        this.gameData.insert(gameData);
        int gameDataId = this.gameData.getLastInsertId();

        //Insert winningConditions
        for (int i = 0; i < gameData.getNumberOfWinningConditions(); i++) {
            WinningCondition wc = gameData.getWinningCondition(i);
            this.winningConditionData.insert(wc);
            int wcId = winningConditionData.getLastInsertId();
            //Update foreign key for WinningCondition
            winningConditionData.updateColumnValue(wcId, "fkId", gameDataId);


            //Insert combinations
            for (int j = 0; j < wc.getNumberOfCombinations(); j++){
                Combination comb = wc.getCombination(j);
                this.combinationData.insert(comb);
                int combId = combinationData.getLastInsertId();
                //Update foreign key for Combination
                combinationData.updateColumnValue(combId, "fkId", wcId);

                //Insert faces
                for (int k = 0; k < comb.getNumberOfFaces(); k++){
                    final String faceQuery = "INSERT INTO Face SET %s = ?, %s = ?;";
                    try {
                        this.connection = Connector.getInstance();
                        PreparedStatement statement = connection.prepareStatement(String.format(faceQuery,
                                "c_id", "face"));

                        statement.setInt(1, combId);
                        statement.setString(2, comb.getFace(k));
                        statement.executeUpdate();
                    }
                    catch (SQLException ex1){
                        ex1.printStackTrace();
                        success = false;
                    }
                }
            }
        }

        return success;
    }

    /**
     * Updates a GameData-object in the database
     * @param gameData  The updated GameData-object
     * @return the success of the operation
     */
    @Override
    public boolean update(GameData gameData){
        boolean success = false;
        if (this.gameData.count("ID", String.valueOf(gameData.getId())) > 0) {
            success = true;
            this.gameData.update(gameData.getId(), gameData);
            int gameDataId = gameData.getId();
            //Check if any combinations have been deleted from a winningCondition
            for (int i = 0; i < gameData.getNumberOfWinningConditions(); i++) //@TODO FIX weird iteration
                deleteUnusedCombinations(gameData.getWinningCondition(i).getId(), gameData.getWinningCondition(i));

            //Check if any winningConditions have been deleted
            deleteUnusedWC(gameData.getId(), gameData);

            for (int i = 0; i < gameData.getNumberOfWinningConditions(); i++) {
                WinningCondition wc = gameData.getWinningCondition(i);
                int wcId = 0;
                if (winningConditionData.count("ID", String.valueOf(wc.getId())) > 0){
                    this.winningConditionData.update(wc.getId(), wc);
                    wcId = wc.getId();
                }
                else{
                    winningConditionData.insert(wc);
                    wcId = winningConditionData.getLastInsertId();
                    //Update foreign key for WinningCondition
                    winningConditionData.updateColumnValue(wcId, "fkId", gameData.getId());
                }


                for (int j = 0; j < wc.getNumberOfCombinations(); j++) {
                    Combination comb = wc.getCombination(j);
                    int combId = 0;
                    if (combinationData.count("ID", String.valueOf(comb.getId())) > 0) {
                        this.combinationData.update(comb.getId(), comb);
                        combId = comb.getId();
                    }
                    else{
                        combinationData.insert(comb);
                        combId = combinationData.getLastInsertId();
                        combinationData.updateColumnValue(combId, "fkId", wcId);
                    }

                    final String table = "SELECT * FROM Face WHERE c_id = ?;";
                    try {
                        deleteUnusedFaces(comb.getId(), comb);

                        PreparedStatement statement = this.connection.prepareStatement(table,
                                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        statement.setInt(1, combId);
                        ResultSet result = statement.executeQuery();
                        for (int k = 0; k < comb.getNumberOfFaces(); k++){
                            result.next();
                            //Add faces
                            if (faceExists(comb.getId(), comb.getFace(k))) {
                                result.updateString(1, comb.getFace(k));
                            }
                            else{
                                result.moveToInsertRow();
                                result.updateString(1, comb.getFace(k));
                                result.updateInt(2, combId);
                                result.insertRow();
                                result.moveToCurrentRow();
                            }
                        }
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                        success = false;
                    }
                }
            }
        }
        return success;
    }

    /**
     * Deletes a GameData-object from the database
     * @param gameData The object to be deleted
     * @return the success of the operation
     */
    @Override
    public boolean delete(GameData gameData){
        boolean success = false;

        this.gameData.delete(gameData.getId());
        if (this.gameData.getLastInsertId() == gameData.getId())
            success = true;

        return success;
    }

    /**
     * Deletes any winningCondition that has a relation to gameData in the database but does not exist in the
     * GameData-object
     * @param fk gameData id
     * @param gameData gameData containing the winningconditions
     * @return the success of the operation
     */
    private boolean deleteUnusedWC(int fk, GameData gameData){
        boolean success = true;
        final String table = "SELECT * FROM %s WHERE fkId = ?;";
        try{
            connection = Connector.getInstance();
            PreparedStatement statement = connection.prepareStatement(String.format(table, "WinningCondition"));
            statement.setInt(1, fk);
            ResultSet winningConditions = statement.executeQuery();

            while(winningConditions.next()){
                int id = winningConditions.getInt("ID");
                if (gameData.findWinningCondition(id) == -1)
                    winningConditionData.delete(id);
            }
        }
        catch (SQLException ex1){
            ex1.printStackTrace();
            success = false;
        }
        return  success;
    }

    /**
     * Deletes all combinations from the database which have been removed from their winningCondition
     * @param fk The primary key of the winningCondition
     * @param wc The winningCondition containing the combinations
     * @return The success of the operation
     */
    private boolean deleteUnusedCombinations(int fk, WinningCondition wc){
        boolean success = true;
        final String table = "SELECT * FROM %s WHERE fkId = ?;";
        try{
            connection = Connector.getInstance();
            //Get all children of wc
            PreparedStatement statement = connection.prepareStatement(String.format(table, "Combination"));
            statement.setInt(1, fk);
            ResultSet combinations = statement.executeQuery();

            //Check wc's combinations stored in the database
            while(combinations.next()){
                //Get id from the current row
                int id = combinations.getInt("ID");
                /*if winningCondition does not contain the combination, the combination have been removed from the
                 WinningCondition-object and should also be removed from the database */
                if (wc.findCombination(id) == -1) {
                    combinationData.delete(id);
                }
            }
        }
        catch (SQLException ex1){
            ex1.printStackTrace();
            success = false;
        }

        return success;
    }

    private boolean deleteUnusedFaces(int fk, Combination comb){
        boolean success = true;
        final String table = "SELECT * FROM %s WHERE c_Id = ?;";
        try {
            connection = Connector.getInstance();
            //Get all faces with FOREIGN KEY to comb
            PreparedStatement statement = this.connection.prepareStatement(String.format(table, "Face"),
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1, fk);
            ResultSet faces = statement.executeQuery();

            while (faces.next()){
                String face = faces.getString("face");
                if (comb.findFace(face) == -1){
                    //Remove face
                    faces.deleteRow();
                }
            }
        }
        catch (SQLException ex1){
            ex1.printStackTrace();
            success = false;
        }
        return  success;
    }

    /**
     * Determines if a face, with relation to a specified combination, exists in the database
     * @param fkId id of the combination
     * @param face face to check
     * @return  If exists: true; If not: false;
     */
    private boolean faceExists(int fkId, String face){
        try{
            final String sqlCount = "SELECT COUNT(*) FROM face WHERE c_id = ? AND face = ?;";
            PreparedStatement statement = this.connection.prepareStatement(sqlCount);
            statement.setInt(1, fkId);
            statement.setString(2, face);
            ResultSet result = statement.executeQuery();
            result.next();
            int count = result.getInt(1);
            if (count > 0)
                return  true;
        }
        catch (SQLException ex1){
            ex1.printStackTrace();
        }
        return  false;
    }

    public GameData getLastInsertedGameData(){
        return gameData.getUsingPrimaryKey(gameData.getLastInsertId());
    }

    public GameData getLastInserted(){
        return get(gameData.getLastInsertId());
    }
}

