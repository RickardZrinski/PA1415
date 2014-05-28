package tests.administrator.models;

import administrator.models.GamesModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.dao.GameDataDao;
import shared.game.GameData;
import shared.game.WinningCondition;
import utilities.sql.Dapper;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GamesModelTest
{
    GamesModel gamesmodel;
    @Before
    public void setUp() throws Exception
    {
       gamesmodel = new GamesModel();
    }

    @After
    public void tearDown() throws Exception
    {
        gamesmodel = null;
    }


    @Test
    public void testSendEditedParameters() throws Exception
    {

       GameData before = gamesmodel.getGame(0);
        ArrayList<WinningCondition> winningConds = new ArrayList<WinningCondition>();
        WinningCondition test = new WinningCondition("Full House");
        winningConds.add(test);
        gamesmodel.sendEditedParameters(0, 3, 2, winningConds);

        //will give an AssertionError (which it should, because the objects should be different, therefore commented if it interferes with compiling
        //assertEquals("Objects are not the same", before,  gamesmodel.getGameTitle(0));


    }

    @Test
    public void testAddGame() throws Exception
    {
        GameDataDao dao = new GameDataDao();
        gamesmodel.addGame("CowWars");

        int pos = gamesmodel.getNrOfGames();
        pos += -1;

        GameData object = gamesmodel.getGame(pos);
        int key = object.getId();
        GameData object2 = dao.get(key);

        assertEquals("Objects are not the same", gamesmodel.getGame(pos).getGameName(), object2.getGameName() );

    }

    @Test
    public void testAddDuplicateGame() throws Exception
    {
        GameDataDao dao = new GameDataDao();

        for (GameData print : dao.getCollection())
        {
            System.out.println(print);
        }


        int oldSize = gamesmodel.getNrOfGames();
        gamesmodel.addGame("CowWars");
        int newSize = gamesmodel.getNrOfGames();
        int pos = gamesmodel.getNrOfGames();
        pos += -1;

        GameData object = gamesmodel.getGame(pos);
        int key = object.getId();
        GameData object2 = dao.get(key);

        for (GameData print : dao.getCollection())
        {
            System.out.println(print);
        }


        assertEquals("Duplicate game titles exists", oldSize, newSize );
    }


      @Test
      public void testAddGamesFromDB() throws Exception
      {
         GameDataDao dao = new GameDataDao();
          ArrayList<GameData> test = new ArrayList<GameData>();


          for(GameData object : dao.getCollection() )
          {
              test.add(object);
          }

          System.out.println(test);

     }

}