package shared.dao;

import com.mysql.jdbc.Connection;
import shared.game.Combination;
import shared.game.GameData;
import shared.game.WinningCondition;
import utilities.sql.Connector;
import utilities.sql.Dapper;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Oliver on 2014-05-16.
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
     * WARNING! Not implemented! Returns FAKE values
     * @return a collection of gameData objects
     */
    public Collection<GameData> getCollection() {
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
            for (int i = 0; i < gameData.getNumberOfWinningConditions(); i++) {
                WinningCondition wc = gameData.getWinningCondition(i);
                this.winningConditionData.update(wc.getId(), wc);

                for (int j = 0; j < wc.getNumberOfCombinations(); j++) {
                    Combination comb = wc.getCombination(j);
                    this.combinationData.update(comb.getId(), comb);

                    final String table = "SELECT * FROM Face WHERE c_id = ?;";
                    try {
                        PreparedStatement statement = this.connection.prepareStatement(table,
                                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        statement.setInt(1, comb.getId());
                        ResultSet result = statement.executeQuery();
                        while (result.next())
                            result.deleteRow();

                        for (int k = 0; k < comb.getNumberOfFaces(); k++) {
                            result.moveToInsertRow();
                            result.updateString(1, comb.getFace(k));
                            result.updateInt(2, comb.getId());
                            result.insertRow();
                            result.moveToCurrentRow();
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
        if (this.gameData.count("ID", String.valueOf(gameData.getId())) > 0){
            success = true;
            for (int i = 0; i < gameData.getNumberOfWinningConditions(); i++){
                WinningCondition wc = gameData.getWinningCondition(i);
                for (int j = 0; j < wc.getNumberOfCombinations(); j++){
                    Combination comb = wc.getCombination(j);
                    final String table = "SELECT * FROM Face WHERE c_id = ?;";
                    try {
                        PreparedStatement statement = this.connection.prepareStatement(table, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        statement.setInt(1, comb.getId());
                        ResultSet result = statement.executeQuery();
                        while (result.next())
                            result.deleteRow();
                    }
                    catch (SQLException ex1){
                        ex1.printStackTrace();
                        success = false;
                    }
                    combinationData.delete(comb.getId());
                }
                winningConditionData.delete(wc.getId());
            }
            this.gameData.delete(gameData.getId());
        }
        return success;
    }
}
