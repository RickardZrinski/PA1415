package administrator.models;

import shared.game.GameData;
import shared.game.WinningCondition;
import utilities.sql.Dapper;

import java.util.ArrayList;

public class GamesModel {
    private ArrayList<GameData> games;
    private int nrOfGames;

    public GamesModel() {
        games = new ArrayList<GameData>();
        nrOfGames = 0;
    }

    public int getNrOfGames() {
        return nrOfGames;
    }

    // more work needed on this method, not quite sure how to fetch shared.game attributes from database yet.
    public boolean addGame(String title) {
        boolean gameAdded = false;
        for (int i = 0; i < games.size(); i++) {
            if (gameExist(title) == false) {
                GameData newGame = new GameData(title);
                games.add(newGame);

                //inserts shared.game into database


                gameAdded = true;
            }
        }

        return gameAdded;
    }

    /*Sends Edited Parameters for a specific shared.game*/
    public void sendEditedParameters(int index, int nrOfThrows, int nrOfDices, ArrayList<WinningCondition> winningCondition) {
        if (index < games.size()) {
            games.get(index).setNumberOfThrows(nrOfThrows);
            games.get(index).setNumberOfDice(nrOfDices);
            games.get(index).setWinningCondition(winningCondition);
        }

    }

    public GameData getGame(int index) {
        return games.get(index);
    }

    private boolean gameExist(String title) {
        boolean exist = false;
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getGameName() == title) {
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

}