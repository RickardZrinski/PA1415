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

            //Check if any combinations have been deleted from a winningCondition
            for (int i = 0; i < gameData.getNumberOfWinningConditions(); i++)
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
                        PreparedStatement statement = this.connection.prepareStatement(table,
                                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        statement.setInt(1, combId);
                        ResultSet result = statement.executeQuery();
                        //Remove all faces
                        while (result.next())
                            result.deleteRow();
                        //Add faces again
                        for (int k = 0; k < comb.getNumberOfFaces(); k++) {
                            result.moveToInsertRow();
                            result.updateString(1, comb.getFace(k));
                            result.updateInt(2, combId);
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

        this.gameData.delete(gameData.getId());
        if (this.gameData.getLastInsertId() == gameData.getId())
            success = true;

        return success;
    }

    /**
     *
     * @param fk gameData id
     * @param gameData
     * @return
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

    private boolean deleteUnusedCombinations(int fk, WinningCondition wc){
        boolean success = true;
        final String table = "SELECT * FROM %s WHERE fkId = ?;";
        try{
            connection = Connector.getInstance();
            PreparedStatement statement = connection.prepareStatement(String.format(table, "Combination"));
            statement.setInt(1, fk);
            ResultSet combinations = statement.executeQuery();

            while(combinations.next()){
                int id = combinations.getInt("ID");
                if (wc.findCombination(id) == -1) {
                    final String sqlCall = "CALL spRemoveCombination(?);";
                    PreparedStatement fStatement = this.connection.prepareStatement(sqlCall);
                    fStatement.setInt(1, id);
                    fStatement.executeQuery();
                }
            }
        }
        catch (SQLException ex1){
            ex1.printStackTrace();
            success = false;
        }

        return success;
    }
}
