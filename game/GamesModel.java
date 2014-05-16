package game;

import java.util.ArrayList;

public class GamesModel
{
	private int nrOfGames;
    private ArrayList<GameData> games;


    public GamesModel()
    {
        games = new ArrayList<GameData>();
    }

    public int getNrOfGames()
	{
		return games.size();

	}

    // more work needed on this method, not quite sure how to fetch game attributes from database yet.
	public boolean addGame(String title)
	{
         boolean gameExist = false;
        for(int i = 0; i<games.size(); i++)
        {
           if(gameExist(title) == false )
           {

           }
        }

        return gameExist;
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
}
