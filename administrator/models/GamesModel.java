package administrator.models;

import shared.game.GameData;
import shared.game.WinningCondition;
import utilities.sql.Dapper;

import java.util.ArrayList;

public class GamesModel
{
    private ArrayList<GameData> games;
    private int nrOfGames;
    public GamesModel()
    {
        games = new ArrayList<GameData>();
        nrOfGames = 0;
    }

    public int getNrOfGames()
	{
		return nrOfGames;
	}

    // more work needed on this method, not quite sure how to fetch shared.game attributes from database yet.
	public boolean addGame(String title)
	{
        boolean gameAdded = false;
        for(int i = 0; i<games.size(); i++)
        {
           if(gameExist(title) == false )
           {
               GameData newGame = new GameData(title);
               games.add(newGame);
               Dapper<GameData> sql = new Dapper<>(GameData.class);
               //inserts shared.game into database NOTE: this only works with 4 GameData base attributes so far
               // the winning condition and combinations attributes will be added soon!
               sql.insert(newGame);
               gameAdded = true;
           }
        }

        return gameAdded;
	}

    /*Sends Edited Parameters for a specific shared.game*/
	public void sendEditedParameters(int index, int nrOfThrows, int nrOfDices, ArrayList<WinningCondition> winningCondition)
	{
        if(index < games.size())
        {
            games.get(index).setNumberOfThrows(nrOfThrows);
            games.get(index).setNumberOfDice(nrOfDices);
            games.get(index).setWinningCondition(winningCondition);
        }

	}

	public GameData getGame(int index)
	{
		return games.get(index);
	}

    private boolean gameExist(String title)
    {
        boolean exist = false;
        for(int i = 0; i<games.size(); i++)
        {
            if( games.get(i).getGameName() == title)
            {
                exist = true;
            }
        }

        return exist;
    }

    public int getGameId(int index){

        return games.get(index).getId();

    }
}
