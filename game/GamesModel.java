package game;

import java.util.ArrayList;

public class GamesModel
{
	private int nrOfGames;
    private ArrayList<GameData> games;


    public GamesModel()
    {
        this.nrOfGames = 0;
        games = new ArrayList<GameData>();
    }

    public int getNrOfGames()
	{
		return nrOfGames;
	}

    // more work needed on this method, not quite sure how to fetch game attributes from database yet.
	public boolean addGame(String title)
	{
        //contact database and fetch all attributes for a new game, id, and check if inputted
        // parameter title == gameName exists in the database

        //GameData newGame = new GameData(fetched attributes)
        //games.add(GameData newGame);
        return true;
	}

    /*Sends Edited Parameters for a specific game*/
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
	
}
