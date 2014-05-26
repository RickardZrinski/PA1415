package administrator.models;

import shared.dao.GameDataDao;
import shared.game.GameData;
import shared.game.WinningCondition;

import java.util.ArrayList;

public class GamesModel
{
    private ArrayList<GameData> games;

    public GamesModel() {
        games = new ArrayList<GameData>();
        AddGamesFromDB();
    }

    public int getNrOfGames() {
        return games.size();
    }

    public boolean addGame(String title) {
        boolean gameAdded = false;

        if (gameExist(title) == false) {
            GameData newGame = new GameData();
            newGame.setGameName(title);
            games.add(newGame);

            //inserts new game into the database
            GameDataDao dao = new GameDataDao();
            dao.insert(newGame);

            gameAdded = true;
        }

        return gameAdded;
    }

    /*Sends Edited Parameters for a specific shared.game*/
    public void sendEditedParameters(int index, int nrOfThrows, int nrOfDices, ArrayList<WinningCondition> winningCondition) {
        if (index < games.size()) {
            games.get(index).setNumberOfThrows(nrOfThrows);
            games.get(index).setNumberOfDice(nrOfDices);
            games.get(index).setWinningCondition(winningCondition);

            //updates changed parameters to the object in the database
            GameDataDao dao = new GameDataDao();
            dao.update(games.get(index));
        }

    }

    public GameData getGame(int index) {
        return games.get(index);
    }

    private boolean gameExist(String title) {
        boolean exist = false;

        for (int i = 0; i < games.size() && !exist; i++) {
            if (games.get(i).getGameName().equals(title)) {
                exist = true;
            }
        }

        return exist;
    }

    public String getGameTitle(int index) {

        return games.get(index).getGameName();
    }

    public WinningCondition[] getGameWinningCond(int index) {

        int nrOfWinningConditions = games.get(index).getNumberOfWinningConditions();

        WinningCondition[] array = new WinningCondition[games.get(index).getNumberOfWinningConditions()];

        for (int i = 0; i < nrOfWinningConditions; i++) {
            array[i] = games.get(index).getWinningCondition(i);
        }

        return array;
    }

    public void AddGamesFromDB()
    {
        GameDataDao dao = new GameDataDao();

        for(GameData object : dao.getCollection() )
        {
            games.add(object);
        }

    }
}