package tests.administrator.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.dao.GameDataDao;
import shared.game.GameData;
import shared.game.WinningCondition;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GamesModelTest
{

    @Before
    public void setUp() throws Exception
    {

    }

    @After
    public void tearDown() throws Exception
    {

    }


    @Test
    public void testSendEditedParameters() throws Exception
    {


        GameDataDao dao = new GameDataDao();
        ArrayList<GameData> games = new ArrayList<GameData>();
        games.add(dao.get(1));

        System.out.println(dao.get(1));

        ArrayList<WinningCondition> winCond = new ArrayList<WinningCondition>();
        WinningCondition test = new WinningCondition("Royal Straight Flush");
        WinningCondition test2 = new WinningCondition("Full House");
        winCond.add(test);
        winCond.add(test2);
        games.get(0).setNumberOfThrows(2);
        games.get(0).setNumberOfDice(4);
        games.get(0).setWinningCondition(winCond);

        //updates changed parameters to the object in the database

        dao.update(games.get(0));
        System.out.println(dao.get(1));
    }
}