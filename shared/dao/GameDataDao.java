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

    @Override
    public GameData get(Object key){
        Integer primaryKey = (Integer)key;
        if (gameData.count("ID", String.valueOf(primaryKey)) > 0) {
            GameData game = gameData.getUsingPrimaryKey(primaryKey);
            ArrayList<WinningCondition> winningConditions = (ArrayList<WinningCondition>) winningConditionData.getCollectionUsingForeignKey("fkId", game.getId());

            for (int i = 0; i < winningConditions.size(); i++) {
                game.addWinningCondition(winningConditions.get(i));
                ArrayList<Combination> combinations = (ArrayList<Combination>) combinationData.getCollectionUsingForeignKey("fkId", winningConditions.get(i).getId());
                for (int j = 0; j < combinations.size(); j++) {
                    winningConditions.get(i).addCombination(combinations.get(j));

                    final String table = "SELECT Face FROM Face WHERE c_id = ?;";
                    ResultSet face = null;
                    try {
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
        return new GameData();
    }

    @Override
    public GameData insert(GameData gameData) {
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
                        PreparedStatement statement = connection.prepareStatement(String.format(faceQuery, "c_id", "face"));

                        statement.setInt(1, combId);
                        statement.setString(2, comb.getFace(k));
                        statement.executeUpdate();
                    }
                    catch (SQLException ex1){
                        ex1.printStackTrace();

                    }
                }
            }
        }

        return new GameData();
    }

    @Override
    public boolean update(GameData gameData){
        this.gameData.update(gameData.getId(), gameData);
        for (int i = 0; i < gameData.getNumberOfWinningConditions(); i++) {
            WinningCondition wc = gameData.getWinningCondition(i);
            this.winningConditionData.update(wc.getId(), wc);
            for (int j = 0; j < wc.getNumberOfCombinations(); j++){
                Combination comb = wc.getCombination(j);
                this.combinationData.update(comb.getId(), comb);


                final String table = "SELECT * FROM Face WHERE c_id = ?;";
                try {
                    PreparedStatement statement = this.connection.prepareStatement(table, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    statement.setInt(1, comb.getId());
                    ResultSet result = statement.executeQuery();
                   // statement.close();
                    while (result.next())
                        result.deleteRow();
                    for (int k = 0; k < comb.getNumberOfFaces(); k++) {
                            result.moveToInsertRow();
                            result.updateString(1, comb.getFace(k));
                            result.updateInt(2, comb.getId());
                            result.insertRow();
                            result.moveToCurrentRow();
                    }
                }
                catch (SQLException ex3){
                    ex3.printStackTrace();
                }

            }

        }

        return false;
    }

    public boolean delete(GameData gameData1){
        return false;
    }
}
